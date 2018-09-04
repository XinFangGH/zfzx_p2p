package com.hurong.credit.service.financingAgency.manageMoney.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.financingAgency.manageMoney.PlManageMoneyPlanDao;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyType;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyTypeService;
import com.hurong.credit.service.financingAgency.manageMoney.PlMmOrderAssignInterestService;
import com.hurong.credit.service.thirdInterface.YeePayService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.thirdPayInterface.CommonRequestInvestRecord;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayInterfaceUtil;

public class PlManageMoneyPlanServiceImpl extends BaseServiceImpl<PlManageMoneyPlan> implements
		PlManageMoneyPlanService {
	private PlManageMoneyPlanDao dao;
	@Resource
	private PlManageMoneyTypeService plManageMoneyTypeService;
	@Resource
	private ObSystemAccountService obSystemAccountService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private YeePayService yeePayService;
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	@Resource
	private PlManageMoneyPlanBuyinfoService plManageMoneyPlanBuyinfoService;
	@Resource
	private PlMmOrderAssignInterestService plMmOrderAssignInterestService;

	public PlManageMoneyPlanServiceImpl(PlManageMoneyPlanDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<PlManageMoneyPlan> getMoneyPlanByType(Long typeId) {
		String hql = "from PlManageMoneyPlan as mp where mp.manageMoneyTypeId = ?  and mp.state=1 order by mp.mmplanId desc limit 4";
		return dao.findByHql(hql, new Object[]{typeId});
	}

	@Override
	public PlManageMoneyPlan bidDynamic(PlManageMoneyPlan moneyPlan) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		double progress = 0D;//进度
		int persionNum = 0;//人数
		BigDecimal money=new BigDecimal(0);//剩余金额
		moneyPlan.setAfterMoney(moneyPlan.getSumMoney());
		//Iterator<PlManageMoneyPlanBuyinfo> it =moneyPlan.getPlManageMoneyPlanBuyinfos().iterator();
		//投标金额合计
		BigDecimal totalMoney=new BigDecimal(0);
		/*while (it.hasNext()) {
			PlManageMoneyPlanBuyinfo buyInfo=it.next();
			totalMoney=totalMoney.add(buyInfo.getBuyMoney());
			persionNum=persionNum+1;
		}*/
		if(moneyPlan.getMmplanId() == 3044){
			System.out.println(moneyPlan.getMmplanId());
		}
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_mmplanId_L_EQ", moneyPlan.getMmplanId());
		List<PlManageMoneyPlan> list = dao.getAll(filter);
		BigDecimal bg = new BigDecimal(0);
		if(list.size()>0){
			bg = list.get(0).getInvestedMoney();
		}
		totalMoney=bg;
		if(bg==null){
			totalMoney = new BigDecimal(0);
		}
		if(moneyPlan.getAfterMoney()!=null){
			money=moneyPlan.getAfterMoney().subtract(totalMoney);
		}
		if(null!=moneyPlan.getSumMoney()&&0!=moneyPlan.getSumMoney().compareTo(new BigDecimal(0))){
			progress=Double.valueOf(totalMoney.divide(moneyPlan.getSumMoney(),BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)).toString());
		}
		if (moneyPlan.getBuyEndTime() != null) {
			Date currTime = new Date();
			Date endTime = moneyPlan.getBuyEndTime();
			long timeDelta = (endTime.getTime() - currTime.getTime()) / 1000;// 单位是秒
			moneyPlan.setAfterTime(String.valueOf(timeDelta)) ;// 剩余时间
		}
		moneyPlan.setProgress(progress);//进度
		moneyPlan.setPersionNum(persionNum);//人数
		moneyPlan.setAfterMoney(money);//剩余金额
		moneyPlan.setStartTime(sd.format(moneyPlan.getBuyStartTime()));//ftl页面中用来判断是投标中还是预售中
		moneyPlan.setNowDate(sd.format(new Date()));//ftl页面中用来判断是投标中还是预售中
		return moneyPlan;
	}
	  public BpCustMember getMemmber(BpCustMember bpcm){
		  BigDecimal[] ret = obSystemAccountService.sumTypeTotalMoney(bpcm.getId(),ObSystemAccount.type0.toString());
		  BpCustMember b = new BpCustMember();
		  b.setAvailableInvestMoney(ret[3]);
		  return b;
	  }
	@Override
	public List<PlManageMoneyPlan> getMoneyPlan(HttpServletRequest request,
			boolean isByb) {
		List<PlManageMoneyPlan> listMoneyPlan = new ArrayList<PlManageMoneyPlan>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		QueryFilter filter = new QueryFilter(request);
		filter.addFilter("Q_keystr_S_EQ", "mmplan");
		if(isByb==true){
			filter.addFilter("Q_name_S_NEQ", "百翼宝");
		}else{
			filter.addFilter("Q_name_S_EQ", "百翼宝");
		}
		listMoneyPlan = new ArrayList<PlManageMoneyPlan>();
		List<PlManageMoneyType> list = plManageMoneyTypeService.getAll(filter); //获取理财计划类型
		if(list!=null&&list.size()!=0){
			for(PlManageMoneyType moneyType : list){
				List<PlManageMoneyPlan> planList = getMoneyPlanByType(moneyType.getManageMoneyTypeId());
				if(planList!=null&&planList.size()!=0){
					PlManageMoneyPlan moneyPlan = planList.get(0);
					if(moneyPlan!=null){
						bidDynamic(moneyPlan);
						moneyPlan.setManageMoneyTypeName(moneyType.getName());
						listMoneyPlan.add(moneyPlan);
					}
				}
			}
		}
		return listMoneyPlan;
	}
	
	/**
	 * 
	 */
	@Override
	public Boolean getPlanLimitStatus(PlManageMoneyPlan plManageMoneyPlan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlManageMoneyPlan> getMoneyPlanBYsategroup(Integer state) {
		String hql = "from PlManageMoneyPlan as mp where   mp.state=?  group by mp.manageMoneyTypeId";
		String sql = "SELECT * FROM pl_managemoney_plan a, ( " +
						"SELECT	manageMoneyTypeId,max(createtime) maxdate FROM " +
						" pl_managemoney_plan " +
						" GROUP BY manageMoneyTypeId " +
					") b WHERE	" +
					"a.manageMoneyTypeId = b.manageMoneyTypeId " +
					//"AND a.createtime = b.maxdate " +
					"AND a.state='"+state+"';";
		return dao.indexProduct(sql);
	//	return dao.findByHql(hql, new Object[]{state});
	}

	/**
	 * 招标中
	 */
	@Override
	public List<PlManageMoneyPlan> getOpenProduct(Integer state) {
		String sql = "SELECT * from pl_managemoney_plan p " +
				"WHERE p.state = '1' " +
				"AND p.manageMoneyTypeId='"+state+"' " +
				"AND p.buyStartTime=(" +
					"SELECT MAX(buyStartTime) FROM pl_managemoney_plan " +
					"WHERE state ='1' " +
					"AND manageMoneyTypeId='"+state+"'" +" AND buyStartTime<NOW() AND buyEndTime>NOW()"+
				");";
		return dao.indexProduct(sql);
	//	return dao.findByHql(hql, new Object[]{state});
	}
	/**
	 * 预售中
	 */
	@Override
	public List<PlManageMoneyPlan> getPresellProduct(Integer state) {
		String sql = "SELECT * from pl_managemoney_plan p WHERE p.state = '1' AND p.manageMoneyTypeId='"+state+"' AND buyStartTime>NOW();";
		return dao.indexProduct(sql);
	//	return dao.findByHql(hql, new Object[]{state});
	}
	/**
	 * 齐标
	 */
	@Override
	public List<PlManageMoneyPlan> getFinishProduct(Integer state) {
		String sql = "SELECT * from pl_managemoney_plan p WHERE p.state = '2' AND p.manageMoneyTypeId='"+state+"';";
		return dao.indexProduct(sql);
	//	return dao.findByHql(hql, new Object[]{state});
	}
	
	
	@Override
	public Pager getmyplmanage(Pager p) {
		p.setList(dao.listpmmp(p));
		p.setTotalCount(dao.getCount(p).intValue());
		return p;
	}

	@Override
	public List<PlManageMoneyPlan> getPlmanage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlManageMoneyPlan> getCurrentList() {
		// TODO Auto-generated method stub
		return dao.getCurrentList();
	}

	@Override
	public PlManageMoneyPlan setDetail(PlManageMoneyPlan plManageMoneyPlan) {
		if(null !=plManageMoneyPlan && !"".equals(plManageMoneyPlan)){
			double progress;// 进度
			int persionNum = 0;// 人数
			BigDecimal money = new BigDecimal(0);// 剩余金额
			BigDecimal yqincomMoney=new BigDecimal("0");//预期收益
			Date currTime = new Date();
			Date startTime=plManageMoneyPlan.getBuyStartTime();
			Date buyTime = plManageMoneyPlan.getBuyEndTime();
			long timeSale=(startTime.getTime() - currTime.getTime())/1000;// 单位是秒
			long timeDelta = (buyTime.getTime() - currTime.getTime()) / 1000;// 单位是秒
			if (null == plManageMoneyPlan || null == plManageMoneyPlan.getPlManageMoneyPlanBuyinfos()) {
				plManageMoneyPlan.setProgress(Double.valueOf(0));//进度
				plManageMoneyPlan.setPersionNum(persionNum);//投标人数
				plManageMoneyPlan.setAfterMoney(money);//剩余金额
				plManageMoneyPlan.setSaleTime(String.valueOf(timeSale));//剩余开始投标时间
				plManageMoneyPlan.setAfterTime(String.valueOf(timeDelta));//剩余投标时间
				
				return plManageMoneyPlan;
			}
			Iterator<PlManageMoneyPlanBuyinfo> it = plManageMoneyPlan.getPlManageMoneyPlanBuyinfos().iterator();
			// 投标金额合计
			BigDecimal totalMoney = new BigDecimal(0);
			while (it.hasNext()) {
				PlManageMoneyPlanBuyinfo planBuyinfo = (PlManageMoneyPlanBuyinfo) it.next();
				// 大于0 的 为 投标成功的 等于 0 为 未投标成功
				if (planBuyinfo.getState() ==1 || planBuyinfo.getState()==2 || planBuyinfo.getState()==7 || planBuyinfo.getState()==10) {
					totalMoney = totalMoney.add(planBuyinfo.getBuyMoney());
					persionNum = persionNum + 1;
				}
			}
			money = plManageMoneyPlan.getSumMoney().subtract(totalMoney);
			if (plManageMoneyPlan.getSumMoney().compareTo(new BigDecimal(0)) == 0) {
				progress = 0;
			} else {
				progress = Double.valueOf(
						totalMoney.divide(plManageMoneyPlan.getSumMoney(), 3,
								BigDecimal.ROUND_HALF_EVEN).multiply(
								new BigDecimal(100)).toString()).intValue();
			}
			plManageMoneyPlan.setProgress(Double.valueOf(progress));//进度
			plManageMoneyPlan.setPersionNum(persionNum);//投标人数
			plManageMoneyPlan.setAfterMoney(money);//剩余金额
			plManageMoneyPlan.setSaleTime(String.valueOf(timeSale));//剩余开始投标时间
			plManageMoneyPlan.setAfterTime(String.valueOf(timeDelta));//剩余投标时间
			plManageMoneyPlan.setYqincomMoney(plManageMoneyPlan.getAfterMoney()
					.multiply(plManageMoneyPlan.getYeaRate()
					.multiply(BigDecimal.valueOf(plManageMoneyPlan.getInvestlimit())))
					.divide(new BigDecimal(36000),2,BigDecimal.ROUND_HALF_UP)
					);//预期收益
			
			//增加预售判断时间
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			plManageMoneyPlan.setStartTime(sd.format(plManageMoneyPlan.getBuyStartTime()));
			plManageMoneyPlan.setNowDate(sd.format(new Date()));
			return plManageMoneyPlan;
		}
		return plManageMoneyPlan;
	}

	@Override
	public List<PlManageMoneyPlan> getUPlanList(String typeId) {
		return dao.getUPlanList(typeId);
	}
	/**
	 * 注册账户起息方法
	 * @param orgPlManageMoneyPlan
	 * @param plManageMoneyPlanBuyinfo
	 * @return
	 */
	public void zczhLoan(PlManageMoneyPlan orgPlManageMoneyPlan,PlManageMoneyPlanBuyinfo plManageMoneyPlanBuyinfo){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String loginName=orgPlManageMoneyPlan.getMoneyReceiver();
			BpCustMember moneyReciver=bpCustMemberService.getMemberUserName(loginName,null);
			BpCustMember customer=bpCustMemberService.get(plManageMoneyPlanBuyinfo.getInvestPersonId());
			String amount=plManageMoneyPlanBuyinfo.getBuyMoney().toString();
			String	requestNo=ContextUtil.createRuestNumber();//第三方账号及系统账号的生成方法
			
			List<CommonRequestInvestRecord> li=new ArrayList<CommonRequestInvestRecord>();
			CommonRequestInvestRecord t1=new CommonRequestInvestRecord();
			t1.setRequestNo(plManageMoneyPlanBuyinfo.getDealInfoNumber());
			t1.setInvest_thirdPayConfigId(customer.getThirdPayFlagId());
			t1.setInvestCusterType("MEMBER");
			t1.setLoaner_thirdPayConfigId(moneyReciver.getThirdPayFlagId());
			t1.setLoanerCusterType("MEMBER");
			t1.setAmount(new BigDecimal(plManageMoneyPlanBuyinfo.getBuyMoney().add(plManageMoneyPlanBuyinfo.getJoinMoney()).toString()));
			li.add(t1);
			CommonRequst common=new CommonRequst();
			common.setRequsetNo(requestNo);//流水号
			common.setBidId(orgPlManageMoneyPlan.getMmplanId().toString());//标id
			common.setThirdPayConfigId(moneyReciver.getThirdPayFlagId());//借款人第三方标识
			common.setInvest_thirdPayConfigId(customer.getThirdPayFlagId());//投资人第三方标识
			common.setFee(plManageMoneyPlanBuyinfo.getJoinMoney());
			common.setLoanList(li);//还款集合
			common.setAmount(plManageMoneyPlanBuyinfo.getBuyMoney());
			common.setSubOrdId(plManageMoneyPlanBuyinfo.getDealInfoNumber());//投标时的流水号
			common.setSubOrdDate(sdf.format(plManageMoneyPlanBuyinfo.getBuyDatetime()));//投标时间 
			common.setConfimStatus(true);//审核状态。
			common.setBussinessType(ThirdPayConstants.BT_MMLOANUSER);//业务类型
			common.setTransferName(ThirdPayConstants.TN_MMLOANUSER);//业务名称
			if(plManageMoneyPlanBuyinfo.getPreAuthorizationNum()!=null){
				common.setLoanNoList(plManageMoneyPlanBuyinfo.getPreAuthorizationNum());//审核流水号.解冻标识
			}else{
				common.setLoanNoList(plManageMoneyPlanBuyinfo.getDealInfoNumber());//审核流水号.解冻标识
			}
			CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(common);
			if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
				plManageMoneyPlanBuyinfo.setState((short)2);
				if(plManageMoneyPlanBuyinfo.getKeystr().equals("UPlan")){//U计划默认为托管模式
					plManageMoneyPlanBuyinfo.setIsAtuoMatch(1);//默认托管模式，可进行自动债权匹配
				}else{
					plManageMoneyPlanBuyinfo.setIsAtuoMatch(0);//默认为非托管模式，不可进行自动债权匹配
				}
				//增加投资人流水
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("investPersonId",plManageMoneyPlanBuyinfo.getInvestPersonId());//投资人Id
				map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
				map.put("transferType",ObAccountDealInfo.T_INVEST);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
				map.put("money", plManageMoneyPlanBuyinfo.getBuyMoney());//交易金额
				map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
				map.put("DealInfoId",null);//交易记录id，没有默认为null
				map.put("recordNumber",plManageMoneyPlanBuyinfo.getDealInfoNumber());//交易流水号
				map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
				String[] ret =obAccountDealInfoService.updateAcountInfo(map);
				
				//更新加入费用资金流水记录
				Map<String,Object> mapUPlan=new HashMap<String,Object>();
				mapUPlan.put("investPersonId",plManageMoneyPlanBuyinfo.getInvestPersonId());//投资人Id
				mapUPlan.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
				mapUPlan.put("transferType",ObAccountDealInfo.T_JOIN);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
				mapUPlan.put("money", plManageMoneyPlanBuyinfo.getJoinMoney());//加入费用
				mapUPlan.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
				mapUPlan.put("DealInfoId",null);//交易记录id，没有默认为null
				mapUPlan.put("recordNumber",plManageMoneyPlanBuyinfo.getDealInfoNumber()+"TJ");//交易流水号
				mapUPlan.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
				obAccountDealInfoService.updateAcountInfo(mapUPlan);
				
				//增加收款专户资金流水
				Map<String,Object> mapL=new HashMap<String,Object>();
				mapL.put("investPersonId",moneyReciver.getId());//投资人Id
				mapL.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
				mapL.put("transferType",ObAccountDealInfo.T_INMONEY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
				mapL.put("money", plManageMoneyPlanBuyinfo.getBuyMoney());//交易金额
				mapL.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
				mapL.put("DealInfoId",null);//交易记录id，没有默认为null
				mapL.put("recordNumber",plManageMoneyPlanBuyinfo.getDealInfoNumber()+"-L");//交易流水号
				mapL.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
				String[] retL =obAccountDealInfoService.operateAcountInfo(mapL);
				
				plManageMoneyPlanBuyinfo.setContractNo(DateUtil.dateToStr(new Date(), "yyyyMMdd")+"_"+plManageMoneyPlanBuyinfo.getOrderId());
				plManageMoneyPlanBuyinfoService.save(plManageMoneyPlanBuyinfo);
				plMmOrderAssignInterestService.createUPlanAssignInerestlist(plManageMoneyPlanBuyinfo,orgPlManageMoneyPlan);
			}
		} catch (Exception e) {
			System.out.println("即时起息出错开始---注册账户起息方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			e.printStackTrace();
			System.out.println("即时起息出错结束---注册账户起息方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
		
	}
	
	/**
	 * 平台账户起息方法
	 * @param orgPlManageMoneyPlan
	 * @param plManageMoneyPlanBuyinfo
	 * @return
	 */
	public void ptzhLoan(PlManageMoneyPlan orgPlManageMoneyPlan,PlManageMoneyPlanBuyinfo plManageMoneyPlanBuyinfo){
		BpCustMember customer=bpCustMemberService.get(plManageMoneyPlanBuyinfo.getInvestPersonId());
		String	requestNo=ContextUtil.createRuestNumber();//第三方账号及系统账号的生成方法
		CommonRequst commonRequest = new CommonRequst();
		commonRequest.setThirdPayConfigId(customer.getThirdPayFlagId());//用户第三方标识
		commonRequest.setRequsetNo(requestNo);//请求流水号
		commonRequest.setAmount(plManageMoneyPlanBuyinfo.getBuyMoney().add(plManageMoneyPlanBuyinfo.getJoinMoney()));//交易金额
		commonRequest.setCustMemberType("0");
		commonRequest.setBussinessType(ThirdPayConstants.BT_MMLOANPLATF);//业务类型
		commonRequest.setTransferName(ThirdPayConstants.TN_MMLOANPLATF);//业务名称
		commonRequest.setLoanNoList(plManageMoneyPlanBuyinfo.getPreAuthorizationNum());//双乾审核流水号
		CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequest);
		if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
			plManageMoneyPlanBuyinfo.setState((short)2);
			if(plManageMoneyPlanBuyinfo.getKeystr().equals("UPlan")){//U计划默认为托管模式
				plManageMoneyPlanBuyinfo.setIsAtuoMatch(1);//默认托管模式，可进行自动债权匹配
			}else{
				plManageMoneyPlanBuyinfo.setIsAtuoMatch(0);//默认为非托管模式，不可进行自动债权匹配
			}
			//增加投资人流水
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("investPersonId",plManageMoneyPlanBuyinfo.getInvestPersonId());//投资人Id
			map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
			map.put("transferType",ObAccountDealInfo.T_INVEST);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
			map.put("money", plManageMoneyPlanBuyinfo.getBuyMoney());//交易金额
			map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
			map.put("DealInfoId",null);//交易记录id，没有默认为null
			map.put("recordNumber",plManageMoneyPlanBuyinfo.getDealInfoNumber());//交易流水号
			map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
			String[] ret =obAccountDealInfoService.updateAcountInfo(map);
			
			//更新加入费用资金流水记录
			Map<String,Object> mapUPlan=new HashMap<String,Object>();
			mapUPlan.put("investPersonId",plManageMoneyPlanBuyinfo.getInvestPersonId());//投资人Id
			mapUPlan.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
			mapUPlan.put("transferType",ObAccountDealInfo.T_JOIN);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
			mapUPlan.put("money", plManageMoneyPlanBuyinfo.getJoinMoney());//加入费用
			mapUPlan.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
			mapUPlan.put("DealInfoId",null);//交易记录id，没有默认为null
			mapUPlan.put("recordNumber",plManageMoneyPlanBuyinfo.getDealInfoNumber()+"TJ");//交易流水号
			mapUPlan.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
			obAccountDealInfoService.updateAcountInfo(mapUPlan);
			
			plManageMoneyPlanBuyinfo.setContractNo(DateUtil.dateToStr(new Date(), "yyyyMMdd")+"_"+plManageMoneyPlanBuyinfo.getOrderId());
			plManageMoneyPlanBuyinfoService.save(plManageMoneyPlanBuyinfo);
			//生成款项台账
			plMmOrderAssignInterestService.createUPlanAssignInerestlist(plManageMoneyPlanBuyinfo,orgPlManageMoneyPlan);
		}
	}

	@Override
	public PlManageMoneyPlan setLogoUrl(PlManageMoneyPlan moneyPlan) {
		String logoUrl=dao.getLogoUrl("pl_managemoney_plan."+moneyPlan.getMmplanId());
		moneyPlan.setLogoUrl(logoUrl);
		return moneyPlan;
	}
	/**
	 *根据收款用户名称查询理财计划列表
	 */
	@Override
	public List<PlManageMoneyPlan> listByMoneyReceiver(String moneyReceiver) {
		// TODO Auto-generated method stub
		return dao.listByMoneyReceiver(moneyReceiver);
	}
}
