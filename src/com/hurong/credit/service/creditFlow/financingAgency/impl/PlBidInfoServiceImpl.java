package com.hurong.credit.service.creditFlow.financingAgency.impl;

/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
 */
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hurong.core.Constants;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.UUIDGenerator;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidInfoDao;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.customer.BpCustRelation;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.pay.MoneyMoreMore;
import com.hurong.credit.model.pay.ResultBean;
import com.hurong.credit.model.thirdInterface.Fuiou;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.customer.BpCustRelationService;
import com.hurong.credit.service.customer.InvestPersonInfoService;
import com.hurong.credit.service.pay.IPayService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.MyUserSession;

/**
 * 
 * @author
 * 
 */
public class PlBidInfoServiceImpl extends BaseServiceImpl<PlBidInfo> implements
		PlBidInfoService {
	@SuppressWarnings("unused")
	private PlBidInfoDao dao;
	@Resource
	private IPayService iPayService;
	@Resource
	private InvestPersonInfoService investPersonInfoService;
	@Resource
	private BpCustRelationService bpCustRelationService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private PlBidPlanService plBidPlanService;
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	
	private static final short PERSION_TYPE_0=0;//线上客户
	private static final short PERSION_TYPE_1=1;//线下客户
	private static final String SMALLLOAN="SmallLoan";//业务类别
	private static final short  FUNDRESOURCE_0=0;//个人
	private static final short  FUNDRESOURCE_1=1;//企业

	public PlBidInfoServiceImpl(PlBidInfoDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public String[] bidByMoneyMore(BpCustMember cpCut, PlBidInfo plBidInfo,
			MoneyMoreMore moneyMoreMore, String basePath,String orderNum,HttpServletResponse response) {
		String[] ret = new String[2];
		// 生成转账信息
		moneyMoreMore = createMoneyMoreMore(moneyMoreMore, cpCut, plBidInfo,orderNum);
		// 生成订单号
		plBidInfo.setOrderNo(orderNum);
		System.out.println(moneyMoreMore.getLoanInMoneymoremore1()+"=="+moneyMoreMore.getLoanOutMoneymoremore1()+"=="+moneyMoreMore.getAmount1());
		if (null==moneyMoreMore.getLoanInMoneymoremore1()||moneyMoreMore.getLoanInMoneymoremore1().equals("")
				|| moneyMoreMore.getLoanOutMoneymoremore1().equals("")
				|| moneyMoreMore.getAmount1().equals("")) {
			ret[0] = Constants.CODE_FAILED;
			ret[1] = "投标失败 借款人可能未开通第三方支付";
		} else {
			ResultBean result = null;
			try {
				result = iPayService.transferNotify(transfer(moneyMoreMore,
						basePath,response));
				ret[0] = Constants.CODE_SUCCESS;
				ret[1] = "对接成功!";
			} catch (Exception e) {
				ret[0] = Constants.CODE_FAILED;
				ret[1] = "投标失败与第三方支付链接失败，请检查网络!投标金额";
			}
		}
		return ret;
	}

	

	/**
	 * 生成钱多多 投标数据
	 * 
	 * @param moneyMoreMore
	 * @param cpCut
	 * @param plBidInfo
	 * @return
	 */
	private MoneyMoreMore createMoneyMoreMore(MoneyMoreMore moneyMoreMore,
			BpCustMember cpCut, PlBidInfo plBidInfo,String orderNum) {
		// 标
		PlBidPlan bidplan = plBidPlanService.get(plBidInfo.getBidId());
		// 借款人关系 获取 网站注册用户信息
		BpCustRelation custRelation = new BpCustRelation();
		// 网站注册用户
		BpCustMember custMamber = new BpCustMember();
		moneyMoreMore = new MoneyMoreMore();
		// 付款标识
		moneyMoreMore.setLoanOutMoneymoremore1(cpCut.getThirdPayFlagId());
		// 项目类型 企业直投 B_Dir 企业 债权 B_Or 个人直投 P_Dir 个人债权 P_Or * @return String
		String loanUserType = "";
		Long loanUserId = null;
		Long custMamberId = null;
		if (bidplan.getProType().equals("B_Dir")) {
			loanUserType = "b_loan";
			loanUserId = bidplan.getBpBusinessDirPro().getBusinessId();
			moneyMoreMore.setFullAmount1(bidplan.getBpBusinessDirPro()
					.getBidMoney().toString());
		} else if (bidplan.getProType().equals("B_Or")) {
			loanUserType = "b_loan";
			loanUserId = bidplan.getBpBusinessOrPro().getBusinessId();
			moneyMoreMore.setFullAmount1(bidplan.getBpBusinessOrPro()
					.getBidMoney().toString());
		} else if (bidplan.getProType().equals("P_Dir")) {
			loanUserType = "p_loan";
			loanUserId = bidplan.getBpPersionDirPro().getPersionId();
			moneyMoreMore.setFullAmount1(bidplan.getBpPersionDirPro()
					.getBidMoney().toString());
		} else if (bidplan.getProType().equals("P_Or")) {
			loanUserType = "p_loan";
			loanUserId = bidplan.getBpPersionOrPro().getPersionId();
			moneyMoreMore.setFullAmount1(bidplan.getBpPersionOrPro()
					.getBidMoney().toString());
		}
		if (loanUserId != null) {
			BpCustRelation bpCustRelation = bpCustRelationService
					.getByLoanTypeAndId(loanUserType, loanUserId);
			if (bpCustRelation != null) {
				custMamberId = bpCustRelation.getP2pCustId();
			}
		}
		if (custMamberId != null && !custMamberId.equals("")) {
			String mid = bpCustMemberService.get(custMamberId)
					.getThirdPayFlagId();
			// 收款标识
			moneyMoreMore.setLoanInMoneymoremore1(mid);
		}
		moneyMoreMore.setOrderNo(orderNum);
		moneyMoreMore.setBatchNo1(plBidInfo.getBidId().toString());
		moneyMoreMore.setAmount1(plBidInfo.getUserMoney().toString());
		moneyMoreMore.setTransferName1("");
		moneyMoreMore.setRemark1("");
		return moneyMoreMore;

	}
	/**
	 * 通过 标的信息获取借款人注册信息
	 * @return
	 */
	@Override
	public BpCustMember getLoanMember(PlBidInfo plBidInfo){
		PlBidPlan bidplan = plBidPlanService.get(plBidInfo.getBidId());
		return this.getLoanMember(bidplan);
	}
	/**
	 * 通过 标的信息获取借款人借款金额
	 * @return
	 */
	@Override
	public String getLoanMoney(PlBidInfo plBidInfo){
		String  money="";
		// 标
		PlBidPlan bidplan = plBidPlanService.get(plBidInfo.getBidId());
		if (bidplan.getProType().equals("B_Dir")) {
			money=bidplan.getBpBusinessDirPro()
					.getBidMoney().toString();
		} else if (bidplan.getProType().equals("B_Or")) {
			money=bidplan.getBpBusinessOrPro()
					.getBidMoney().toString();
		} else if (bidplan.getProType().equals("P_Dir")) {
			money=bidplan.getBpPersionDirPro()
					.getBidMoney().toString();
		} else if (bidplan.getProType().equals("P_Or")) {
			money=bidplan.getBpPersionOrPro()
					.getBidMoney().toString();
		}
		
		return money;
	}

	/**
	 * 将投资人信息存入投资人列表
	 * @param orderNum 订单编号
	 * @param bidid
	 */
	@Override
	public void saveToERP(String bidInfoId,String orderNum) {
		PlBidInfo bid=this.get(Long.valueOf(bidInfoId));
		
		bid.setState(PlBidInfo.TYPE1);
		this.merge(bid);

		PlBidPlan bidplan=plBidPlanService.get(bid.getBidId());
		if (bid!= null) {
			try{
			InvestPersonInfo investPersonInfo=new InvestPersonInfo();
			investPersonInfo.setInvestPersonId(bid.getUserId());//投资人ID
			investPersonInfo.setPersionType(PERSION_TYPE_0);//线上客户
			investPersonInfo.setInvestPersonName(bid.getUserName());//投资人用户名
			investPersonInfo.setInvestMoney(bid.getUserMoney());//投资金额
			
			investPersonInfo.setRemark("");//备注
			investPersonInfo.setBusinessType(SMALLLOAN);
			Long proId=null; //项目id
			Long moneyPlanId=null; //资金方案Id
			BigDecimal investPercent=new BigDecimal(0);
			//项目类型  企业直投  B_Dir  企业 债权 B_Or  个人直投 P_Dir 个人债权 P_Or	 * @return String
			if(bidplan.getProType()!=null&&bidplan.getProType().equals("B_Dir")){
				investPersonInfo.setFundResource(FUNDRESOURCE_1); //资金来源  add by zcb 2014--2-12 1 企业 0个人 // 是企业还是个人 针对线下投资用户  Yuanzc
				//项目id
				proId=bidplan.getBpBusinessDirPro().getProId();
			   //方案Id
				moneyPlanId=bidplan.getBpBusinessDirPro().getMoneyPlanId();
				//投资金额占比
				//investPercent=bid.getUserMoney().divide(bidplan.getBpBusinessDirPro().getBidMoney(),5,BigDecimal.ROUND_HALF_EVEN).multiply(new  BigDecimal(100));
			}else if(bidplan.getProType()!=null&&bidplan.getProType().equals("B_Or")){
				investPersonInfo.setFundResource(FUNDRESOURCE_1); //资金来源  add by zcb 2014--2-12 1 企业 0个人 // 是企业还是个人 针对线下投资用户  Yuanzc
				proId=bidplan.getBpBusinessOrPro().getProId();
				moneyPlanId=bidplan.getBpBusinessOrPro().getMoneyPlanId();
				//投资金额占比
				//investPercent=bid.getUserMoney().divide(bidplan.getBpBusinessOrPro().getBidMoney(),5,BigDecimal.ROUND_HALF_EVEN).multiply(new  BigDecimal(100));
	
			}else if(bidplan.getProType()!=null&&bidplan.getProType().equals("P_Dir")){
				investPersonInfo.setFundResource(FUNDRESOURCE_0); //资金来源  add by zcb 2014--2-12 1 企业 0个人 // 是企业还是个人 针对线下投资用户  Yuanzc
				proId=bidplan.getBpPersionDirPro().getProId();
				moneyPlanId=bidplan.getBpPersionDirPro().getMoneyPlanId();
				//投资金额占比
				//investPercent=bid.getUserMoney().divide(bidplan.getBpPersionDirPro().getBidMoney(),5,BigDecimal.ROUND_HALF_EVEN).multiply(new  BigDecimal(100));
	
			}else if(bidplan.getProType()!=null&&bidplan.getProType().equals("P_Or")){
				investPersonInfo.setFundResource(FUNDRESOURCE_0); //资金来源  add by zcb 2014--2-12 1 企业 0个人 // 是企业还是个人 针对线下投资用户  Yuanzc
				proId=bidplan.getBpPersionOrPro().getProId();
				moneyPlanId=bidplan.getBpPersionOrPro().getMoneyPlanId();
				//投资金额占比
				//investPercent=bid.getUserMoney().divide(bidplan.getBpPersionOrPro().getBidMoney(),5,BigDecimal.ROUND_HALF_EVEN).multiply(new  BigDecimal(100));
	
			}
			investPercent=bid.getUserMoney().divide(bidplan.getBidMoney(),5,BigDecimal.ROUND_HALF_EVEN).multiply(new  BigDecimal(100));
			investPersonInfo.setOrderNo(orderNum);
			investPersonInfo.setInvestPercent(investPercent.setScale(2, BigDecimal.ROUND_HALF_UP));//投资金额所占比例
			if(proId!=null){
			investPersonInfo.setProjectId(proId); //项目id 
			}

			if(moneyPlanId!=null){
			investPersonInfo.setMoneyPlanId(moneyPlanId); // 应该保存资金方案表的 id
			}
			investPersonInfo.setProjectId(proId); 
			
			investPersonInfo.setBidPlanId(bidplan.getBidId()); //设置标的id
			investPersonInfoService.save(investPersonInfo);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("保存投资人 出错");
			}
		}
	}

	/**
	 * 转账接口
	 */
	public String transfer(MoneyMoreMore moneyMoreMore, String basePath,HttpServletResponse response) {
		moneyMoreMore.setTransferAction(MoneyMoreMore.TACTION_1);
		moneyMoreMore.setAction(MoneyMoreMore.ACTION_1);
		moneyMoreMore.setTransferType(MoneyMoreMore.TTYPE_2);
		moneyMoreMore.setNeedAudit("1"); // 空.需要审核 1.自动通过
		// 生成转账列表
		moneyMoreMore = iPayService.loanJsonList(moneyMoreMore);
		return iPayService.transfer(moneyMoreMore, basePath,response);
	}
	
	
	@Override
	public BpCustMember getLoanMember(PlBidPlan bidplan) {
		BpCustMember member=new BpCustMember();
		// 借款人关系 获取 网站注册用户信息
		BpCustRelation custRelation = new BpCustRelation();
		// 网站注册用户
		// 项目类型 企业直投 B_Dir 企业 债权 B_Or 个人直投 P_Dir 个人债权 P_Or * @return String
		String loanUserType = "";
		Long loanUserId = null;
		Long custMamberId = null;
		if (bidplan.getProType().equals("B_Dir")) {
			loanUserType = "b_loan";
			loanUserId = bidplan.getBpBusinessDirPro().getBusinessId();
		} else if (bidplan.getProType().equals("B_Or")) {//有原始债权人概念
			String loginName=bidplan.getBpBusinessOrPro().getReceiverP2PAccountNumber();
			member=bpCustMemberService.getMemberUserName(loginName,null);
		} else if (bidplan.getProType().equals("P_Dir")) {
			loanUserType = "p_loan";
			loanUserId = bidplan.getBpPersionDirPro().getPersionId();
			
		} else if (bidplan.getProType().equals("P_Or")) {//有原始债权人概念
			String loginName=bidplan.getBpPersionOrPro().getReceiverP2PAccountNumber();
			member=bpCustMemberService.getMemberUserName(loginName,null);
			
		}
		if (loanUserId != null) {
			BpCustRelation bpCustRelation = bpCustRelationService.getByLoanTypeAndId(loanUserType, loanUserId);
			if (bpCustRelation != null) {
				custMamberId = bpCustRelation.getP2pCustId();
			}
		}
		if (custMamberId != null && !custMamberId.equals("")) {
			member = bpCustMemberService.get(custMamberId);
		}
		return member;
	}

	@Override
	public PlBidInfo getByOrdId(String ordId) {
		return dao.getByOrdId(ordId);
	}

	@Override
	public List<PlBidInfo> getBidLoanAfter() {
		return dao.getBidLoanAfter();
	}

	@Override
	public List<PlBidInfo> getPlBidList(int state) {
		return dao.getPlBidList(state);
	}

	@Override
	public PlBidInfo getOrderNumber(String requestNo) {
		return dao.getByOrdId(requestNo);
	}

	@Override
	public Long getPlanInfoCount(Long userId, String type) {
		return dao.getPlanInfoCount(userId,type);
	}

	@Override
	public Long getPlanInfoFailCount(Long userId, String type) {
		return dao.getPlanInfoFailCount(userId,type);
	}

	@Override
	public List<PlBidInfo> getBidList(Long userId, String type) {
		return dao.getBidList( userId,  type);
	}

	@Override
	public List<PlBidInfo> getBidOrderNoList(Long userId,String type) {
		return dao.getBidOrderNoList(userId,type);
	}

	@Override
	public BigDecimal getLoanTotal(Long userId,String type) {
		return dao.getLoanTotal(userId,type);
	}

	@Override
	public List<PlBidInfo> getIntentInfo(Long bidId,String group) {
		return dao.getIntentInfo( bidId,group);
	}

	@Override
	public BigDecimal getUserMoneyGroup(Long bidId, Long userId) {
		return dao.getUserMoneyGroup( bidId,  userId);
	}

	@Override
	public List<PlBidInfo> getIntentInfo(Long bidId,Long userId) {
		return dao.getIntentInfo( bidId,userId);
	}

	@Override
	public BigDecimal queryUserMoney(Long bidId, Long userId,String orderNo) {
		return dao.queryUserMoney( bidId,  userId,orderNo); 
	}

	@Override
	public List<PlBidInfo> getbyPersonAndPlan(Long id, String state, Pager pager) {
		
		return dao.getbyPersonAndPlan(id,state,pager);
	}
	
	
	

	@Override
	public Map<String, Object> getbyPersonAndPlan2(Long id, String planstate,
			String start, String limit) {
		return dao.getbyPersonAndPlan(id,planstate,start,limit);
	}

	@Override
	public PlBidInfo getByNewOrdId(String ordId) {
		// TODO Auto-generated method stub
		return dao.getByNewOrdId(ordId);
	}

	@Override
	public BigDecimal queryAllProfit(Long userId) {
		// TODO Auto-generated method stub
		return dao.queryAllProfit(userId);
	}

	@Override
	public BpPersonCenterData queryAllManage(Long userId) {
		// TODO Auto-generated method stub
		BpCustMember mem = bpCustMemberService.get(userId);
		if(mem!=null){
			return dao.queryAllManage(userId,mem.getLoginname());
		}else{
			BpPersonCenterData data = new BpPersonCenterData();
			return data;	
		}
	}

	@Override
	public BpPersonCenterData queryAllBid(Long userId) {
		// TODO Auto-generated method stub
		return dao.queryAllBid(userId);
	}
	public List<PlBidInfo> monthsort(){
		return dao.monthsort();
	}
	public List<PlBidInfo> allsort(){
		 return dao.allsort();
	}
	public List<PlBidInfo> weeksort(){
		 return dao.weeksort();
	}

	//新增
	@Override
	public List<PlBidInfo> getListInfo(HttpServletRequest request,Integer start,Integer limit) {
		return dao.getListInfo(request,start, limit);
	}
}