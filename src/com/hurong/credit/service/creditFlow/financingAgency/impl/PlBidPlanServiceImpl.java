package com.hurong.credit.service.creditFlow.financingAgency.impl;

/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
 */

import com.hurong.core.command.QueryFilter;
import com.hurong.core.jms.MailMessageConsumer;
import com.hurong.core.model.MailModel;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.JdbcConnection;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.creditFlow.finance.BpFundIntentDao;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidPlanDao;
import com.hurong.credit.dao.creditFlow.financingAgency.business.BpBusinessDirProDao;
import com.hurong.credit.dao.creditFlow.financingAgency.business.BpBusinessOrProDao;
import com.hurong.credit.dao.creditFlow.financingAgency.persion.BpPersionDirProDao;
import com.hurong.credit.dao.creditFlow.financingAgency.persion.BpPersionOrProDao;
import com.hurong.credit.dao.creditFlow.fund.project.BpFundProjectDao;
import com.hurong.credit.dao.customer.BpCustRelationDao;
import com.hurong.credit.dao.customer.InvestPersonInfoDao;
import com.hurong.credit.dao.user.BpCustMemberDao;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.InvestprojectPlan;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatory;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;
import com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessDirPro;
import com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessOrPro;
import com.hurong.credit.model.creditFlow.financingAgency.business.PlBusinessDirProKeep;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionDirPro;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionOrPro;
import com.hurong.credit.model.creditFlow.financingAgency.persion.PlPersionDirProKeep;
import com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlKeepGtorz;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.model.system.MailData;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.creditFlow.customer.person.BpCustPersonEducationService;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.finance.compensatory.PlBidCompensatoryService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.creditFlow.financingAgency.business.BpBusinessDirProService;
import com.hurong.credit.service.creditFlow.financingAgency.business.PlBusinessDirProKeepService;
import com.hurong.credit.service.creditFlow.financingAgency.persion.PlPersionDirProKeepService;
import com.hurong.credit.service.creditFlow.fund.project.BpFundProjectService;
import com.hurong.credit.service.customer.InvestPersonInfoService;
import com.hurong.credit.service.thirdInterface.FuiouService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * @author
 * 
 */
public class PlBidPlanServiceImpl extends BaseServiceImpl<PlBidPlan> implements
		PlBidPlanService {
	@SuppressWarnings("unused")
	private PlBidPlanDao dao;
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	public  final static  HashMap<Integer,Lock> selectPlanmap=new HashMap<Integer,Lock>();
	static{
		for(int i=0;i<100;i++){
			selectPlanmap.put(i, new ReentrantLock());
		}
	}
	
	@Resource
	private PlBusinessDirProKeepService plBusinessDirProKeepService;
	@Resource
	private PlPersionDirProKeepService plPersionDirProKeepService;
	@Resource
	private BpFundProjectService bpFundProjectService;
	@Resource
	private BpCustPersonEducationService bpCustPersonEducationService;
	@Resource
	private ObSystemAccountService obSystemAccountService;

	@Resource
	private BpBusinessDirProService bpBusinessDirProService;
    
	@Resource
	private InvestPersonInfoService investPersonInfoService;
	@Resource
	private BpBusinessDirProDao bpBusinessDirProDao;
	@Resource
	private BpBusinessOrProDao bpBusinessOrProDao;
	@Resource
	private BpPersionDirProDao bpPersionDirProDao;
	@Resource
	private BpPersionOrProDao bpPersionOrProDao;
	@Resource
	private BpFundProjectDao bpFundProjectDao;
	@Resource
	private BpFundIntentDao bpFundIntentDao;
	@Resource
	private InvestPersonInfoDao investPersonInfoDao;
	@Resource
	private FuiouService fuiouService;
	@Resource
	private BpFundIntentService bpFundIntentService;
	@Resource
	private PlBidCompensatoryService plBidCompensatoryService;
	@Resource
	private BpCustRelationDao bpCustRelationDao;
	@Resource
	private BpCustMemberDao bpCustMemberDao;
	
	public  String  explain;//标的说明svn：songwj
	public String acctulData;//周期数svn：songwj
	public String repaymentPeriod;//还款期限svn：songwj
	
	
	
	


	/**
	 * 获取标的状态 （0 未发布 1招标中 2已齐标 3已流标4手动关闭）
	 * 
	 * @return
	 */
	public String[] bidStat(Long bidId) {
		String[] relt = new String[2];
		int stat = this.get(bidId).getState();
		switch (stat) {
		case 0:
			relt[0] = String.valueOf(stat);
			relt[1] = "未发布不能进行投标！";
			break;
		case 1:
			relt[0] = String.valueOf(stat);
			relt[1] = "可以投标！";
			break;
		case 2:
			relt[0] = String.valueOf(stat);
			relt[1] = "已齐标！";
			break;
		case 3:
			relt[0] = String.valueOf(stat);
			relt[1] = "已流标！";
			break;
		case 4:
			relt[0] = String.valueOf(stat);
			relt[1] = "已关闭！";
			break;

		default:
			break;
		}
		return relt;
	}


	@Override
	public PlBidPlan setMobileProperty(PlBidPlan plan){
		//返款方式
		if("1".equals(plan.getPayIntersetWay())){
			plan.setTheWayBack("等额本息");
		}else if("2".equals(plan.getPayIntersetWay())){
			plan.setTheWayBack("等额本金");
		}else if("3".equals(plan.getPayIntersetWay())){
			plan.setTheWayBack("等本等息");
		}else if("4".equals(plan.getPayIntersetWay())){
			plan.setTheWayBack("按期计息到期还本");
		}else if("5".equals(plan.getPayIntersetWay())){
			plan.setTheWayBack("期末一次性支付本息");
		}
		String[] ret = mobileBidDynamic(plan);
		plan.setProgress(Double.valueOf(ret[0]));
		plan.setAfterMoney(Double.valueOf(ret[1]));
		if(ret[2]!=null&&!"".equals(ret[2])){
			plan.setRemainingTime(Long.valueOf(ret[2]));
		}
		return plan;
	}

	@Override
	public String[] mobileBidDynamic(PlBidPlan plan){
		String[] ret = new String[3];
		double progress;
		//投标金额合计
		BigDecimal sumInvestMoney = investPersonInfoService.getSumInvestMoney(plan.getBidId());
		//剩余可投金额
		BigDecimal afterMoney = plan.getBidMoney().subtract(sumInvestMoney);

		if (plan.getBidMoney().compareTo(new BigDecimal(0)) == 0) {
			progress = 0;
		} else {
			progress = Double.valueOf(
					sumInvestMoney.divide(plan.getBidMoney(), 3,
							BigDecimal.ROUND_HALF_EVEN).multiply(
							new BigDecimal(100)).toString()).intValue();
		}
		ret[0] = String.valueOf(progress);
		ret[1] = String.valueOf(afterMoney);
		if (plan.getBidEndTime() != null) {
			Date currTime = new Date();
			Date endTime = plan.getBidEndTime();
			long timeDelta = (endTime.getTime() - currTime.getTime()) / 1000;// 单位是秒
            ret[2] = String.valueOf(timeDelta);// 剩余时间
        }
		return ret;
	}



	@Override
	public PlBidPlan setProperty(PlBidPlan plan, HttpServletRequest request) {try {
		//返款方式
		if("1".equals(plan.getPayIntersetWay())){
			plan.setTheWayBack("等额本息");
		}else if("2".equals(plan.getPayIntersetWay())){
			plan.setTheWayBack("等额本金");
		}else if("3".equals(plan.getPayIntersetWay())){
			plan.setTheWayBack("等本等息");
		}else if("4".equals(plan.getPayIntersetWay())){
			plan.setTheWayBack("按期计息到期还本");
		}else if("5".equals(plan.getPayIntersetWay())){
			plan.setTheWayBack("期末一次性支付本息");
		}
		//处理过期
		getAfterTime(plan);

		String[] ret = bidDynamic(plan);
		plan.setProgress(Double.valueOf(ret[0]));
		plan.setAfterMoney(Double.valueOf(ret[2]));

		if (plan.getProType().equals("B_Dir")) {
			if (plan.getBpBusinessDirPro() != null ){
			plan.setMortgage(findMortgageBySQL(plan.getBpBusinessDirPro().getProId().toString()));//抵押担保物类别
			plan.setAssureType(findAssureTypeBySQL(plan.getBpBusinessDirPro().getProId().toString()));//获取担保方式
			plan.setLoanTotalMoney(new BigDecimal(this.findLoanTotalMoneyBySQL(plan.getBpBusinessDirPro().getProId().toString())));
			plan.setOrgMoney(new BigDecimal(this.findOrgMoneyBySQL(plan.getBpBusinessDirPro().getProId().toString(), "1")));
			}

			PlBusinessDirProKeep businessKeep = plBusinessDirProKeepService.getDirProKeepByDirIdType(plan.getBdirProId(),plan.getProType());
			if(businessKeep!=null){
				plan.setProKeepType(businessKeep.getPlKeepProtype().getName());
				plan.setProKeeyRemark(businessKeep.getPlKeepProtype().getRemark());
				plan.setCreditLevel(businessKeep.getPlKeepCreditlevel().getName());
			}
			if (plan.getBpBusinessDirPro() != null) {
				BpBusinessDirPro dirPro = plan.getBpBusinessDirPro();
				// 信用等级
				PlBusinessDirProKeep planKeep = plBusinessDirProKeepService.getDirProKeepByDirIdType(dirPro.getBdirProId(),"B_Dir");
				dirPro.setPlBusinessDirProKeep(planKeep);
				plan.setBpBusinessDirPro(dirPro);
				// 借款类型
				BpFundProject project = bpFundProjectService.getBpFundProject(dirPro.getProId(), (short) 0);
				if (null == project || null == project.getInvestName()) {
					if(businessKeep!=null){
						PlKeepGtorz plkeep = businessKeep.getPlKeepGtorz();
						plan.setOrgName(plkeep==null?"":plkeep.getName());//合作机构
						plan.setOrgKeyStr(plkeep==null?"":plkeep.getKeyStr());
					}
				} else {
					if (businessKeep.getProGtOrzId() != null) {
						plan.setOrgName(project.getInvestName());// 合作机构
					} else {
						plan.setOrgName("无");// 合作机构
					}
					if (null != project.getFundResource()&& "Pawn".equals(project.getFundResource())) {
						plan.setOrgKeyStr("dian");
					} else if (null != project.getFundResource()&& "SmallLoan".equals(project.getFundResource())) {
						plan.setOrgKeyStr("zhi");
					}
				}
			} else {
				if(businessKeep!=null){
					plan.setOrgName(businessKeep.getPlKeepGtorz().getName());
					plan.setOrgKeyStr(businessKeep.getPlKeepGtorz().getKeyStr());
				}
			}


		}
		if (plan.getProType().equals("B_Or")) {
			plan.setMortgage(findMortgageBySQL(plan.getBpBusinessOrPro().getProId().toString()));//抵押担保物类别
			plan.setAssureType(findAssureTypeBySQL(plan.getBpBusinessOrPro().getProId().toString()));//获取担保方式
			PlBusinessDirProKeep businessKeep = plBusinessDirProKeepService.getDirProKeepByDirIdType(plan.getBorProId(),plan.getProType());
			if(businessKeep!=null){
				plan.setProKeepType(businessKeep.getPlKeepProtype().getName());
				plan.setProKeeyRemark(businessKeep.getPlKeepProtype().getRemark());
				plan.setCreditLevel(businessKeep.getPlKeepCreditlevel().getName());
				PlKeepGtorz plKeepGtorz = businessKeep.getPlKeepGtorz();
				plan.setOrgName(plKeepGtorz==null?"":plKeepGtorz.getName());
				plan.setOrgKeyStr(plKeepGtorz==null?"":plKeepGtorz.getKeyStr());
			}
			if (null != plan.getBpBusinessOrPro()) {
				BpBusinessOrPro BOrPro = plan.getBpBusinessOrPro();
				// 信用等级
				PlBusinessDirProKeep planBussKeep = plBusinessDirProKeepService.getDirProKeepByDirIdType(BOrPro.getBorProId(),"B_Or");
				BOrPro.setPlBusinessDirProKeep(planBussKeep);
				plan.setKeepCreditlevelName(BOrPro.getPlBusinessDirProKeep().getPlKeepCreditlevel().getName());//信用等级
				plan.setBpBusinessOrPro(BOrPro);
			}
			plan.setLoanTotalMoney(new BigDecimal(this.findLoanTotalMoneyBySQL(plan.getBpBusinessOrPro().getProId().toString())));
			plan.setOrgMoney(new BigDecimal(this.findOrgMoneyBySQL(plan.getBpBusinessOrPro().getProId().toString(), "1")));
		}
		if (plan.getProType().equals("P_Dir")) {
			selectEducationSchool(plan);
			plan.setMortgage(findMortgageBySQL(plan.getBpPersionDirPro().getProId().toString()));//抵押担保物类别
			plan.setAssureType(findAssureTypeBySQL(plan.getBpPersionDirPro().getProId().toString()));//获取担保方式
			//修改
			PlPersionDirProKeep persionKeep = plPersionDirProKeepService.getDirProKeepByDirIdType(plan.getPdirProId(),plan.getProType());
			if(persionKeep!=null){
				plan.setProKeepType(persionKeep.getPlKeepProtype().getName());
				plan.setProKeeyRemark(persionKeep.getPlKeepProtype().getRemark());
				plan.setCreditLevel(persionKeep.getPlKeepCreditlevel().getName());
			}
			if (null != plan.getBpPersionDirPro()) {
				BpPersionDirPro dirPro = plan.getBpPersionDirPro();
				// 信用等级
				PlPersionDirProKeep planKeep = plPersionDirProKeepService.getDirProKeepByDirIdType(dirPro.getPdirProId(),"P_Dir");
				dirPro.setPlPersionDirProKeep(planKeep);
				plan.setKeepCreditlevelName(dirPro.getPlPersionDirProKeep().getPlKeepCreditlevel().getName());//信用等级
				plan.setBpPersionDirPro(dirPro);
				// 借款类型
				BpFundProject project = bpFundProjectService.getBpFundProject(dirPro.getProId(), (short) 0);
				if (null == project || null == project.getInvestName()) {
					if(persionKeep!=null){
						PlKeepGtorz plKeep =persionKeep.getPlKeepGtorz();
						plan.setOrgName(plKeep==null?"":plKeep.getName());// 担保机构
						plan.setOrgKeyStr(plKeep==null?"":plKeep.getKeyStr());
					}
					
				} else {
					if (persionKeep.getProGtOrzId() != null) {
						plan.setOrgName(project.getInvestName());// 合作机构
					} else {
						plan.setOrgName("无");// 合作机构
					}
					if (null != project.getFundResource()
							&& "Pawn".equals(project.getFundResource())) {
						plan.setOrgKeyStr("dian");
					} else if (null != project.getFundResource()
							&& "SmallLoan"
									.equals(project.getFundResource())) {
						plan.setOrgKeyStr("zhi");
					}
				}
			} else {
				if(persionKeep!=null){
					plan.setOrgName(persionKeep.getPlKeepGtorz().getName());
					plan.setOrgKeyStr(persionKeep.getPlKeepGtorz().getKeyStr());
				}
			}

			plan.setLoanTotalMoney(new BigDecimal(this.findLoanTotalMoneyBySQL(plan.getBpPersionDirPro().getProId().toString())));
			plan.setOrgMoney(new BigDecimal(this.findOrgMoneyBySQL(plan.getBpPersionDirPro().getProId().toString(), "1")));

		}
		if (plan.getProType().equals("P_Or")) {
			selectEducationSchool(plan);
			plan.setMortgage(findMortgageBySQL(plan.getBpPersionOrPro().getProId().toString()));//抵押担保物类别
			plan.setAssureType(findAssureTypeBySQL(plan.getBpPersionOrPro().getProId().toString()));//获取担保方式
			PlPersionDirProKeep persionKeep = plPersionDirProKeepService.getDirProKeepByDirIdType(plan.getPOrProId(),plan.getProType());
			if(persionKeep!=null){
				plan.setProKeepType(persionKeep.getPlKeepProtype().getName());
				plan.setProKeeyRemark(persionKeep.getPlKeepProtype().getRemark());
				plan.setCreditLevel(persionKeep.getPlKeepCreditlevel().getName());
				PlKeepGtorz plKeepGtorz = persionKeep.getPlKeepGtorz();
				plan.setOrgName(plKeepGtorz==null?"":plKeepGtorz.getName());
				plan.setOrgKeyStr(plKeepGtorz==null?"":plKeepGtorz.getKeyStr());
			}
			if (null != plan.getBpPersionOrPro()) {
				BpPersionOrPro OrPro = plan.getBpPersionOrPro();
				// 信用等级
				PlPersionDirProKeep planKeep = plPersionDirProKeepService.getDirProKeepByDirIdType(OrPro.getPorProId(),"P_Or");
				OrPro.setPlPersionDirProKeep(planKeep);
				System.out.println(OrPro);
				if(null!=OrPro.getPlPersionDirProKeep()){
					plan.setKeepCreditlevelName(OrPro.getPlPersionDirProKeep().getPlKeepCreditlevel().getName());//信用等级
				}
				plan.setBpPersionOrPro(OrPro);
			}
			plan.setLoanTotalMoney(new BigDecimal(this.findLoanTotalMoneyBySQL(plan.getBpPersionOrPro().getProId().toString())));
			plan.setOrgMoney(new BigDecimal(this.findOrgMoneyBySQL(plan.getBpPersionOrPro().getProId().toString(), "1")));
		}
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("获取 属性错误" + e.getMessage());
	}
	return plan;
	}
	/**
	 * 得到学校
	 * @param plan
	 */
	public void selectEducationSchool(PlBidPlan plan){
		if(plan.getProType().equals("P_Or")){
			BpPersionOrPro personOr = plan.getBpPersionOrPro();
			personOr.getPersionId();
			if(personOr!=null){
				//String educationSchool = bpCustPersonEducationService.getSchool(personOr.getPersionId());
				plan.setEducationSchool(bpCustPersonEducationService.getSchool(personOr.getPersionId()));
			}
		}
		if(plan.getProType().equals("P_Dir")){
			BpPersionDirPro persionDir = plan.getBpPersionDirPro();
			if(persionDir!=null){
				//String educationSchool = bpCustPersonEducationService.getSchool(persionDir.getPersionId());
				plan.setEducationSchool(bpCustPersonEducationService.getSchool(persionDir.getPersionId()));
			}
		}
	}
	@Override
	public int updateStatByMoney(Long bidId, BigDecimal currMoney) {
		int s=-1;
		PlBidPlan plan = this.get(bidId);
		// 投标金额合计
		BigDecimal totalMoney = bidedMoney(bidId).add(currMoney);
		if (totalMoney.compareTo(plan.getBidMoney()) == 0) {
			// 设置为已经齐标
			plan.setState(PlBidPlan.STATE2);
			// 发送邮件
			if (AppUtil.getSysConfig().get("systemEmail") != null) {
				String[] StrArr = AppUtil.getSysConfig().get("systemEmail")
						.toString().split("\\|");
				if (StrArr != null && StrArr.length > 0) {
					for (int i = 0; i < StrArr.length; i++) {
						MailData md = new MailData("满标提醒",
								StrArr[i].split(",")[0],
								StrArr[i].split(",")[1]);
						md.setProjName(plan.getBidProName());
						md.setProjNumber(plan.getBidProNumber());
						md.setBidId(plan.getBidId().toString());
						sendMail("mail/bidfullMsg.vm", md);
					}
				}
			}
			plan.setFullTime(new Date());// 设置齐标日期
			this.save(plan);
			s=0;
		}else if (totalMoney.compareTo(plan.getBidMoney())> 0){
			s=1;
		}else {
			s=-1;
		}
		return s;
	}
	
	@Override
	public int updateStatByOrderNomber(Long bidId, BigDecimal currMoney,String orderNo) {
		int s=-1;
		PlBidPlan plan = this.get(bidId);
		// 投标金额合计
		BigDecimal totalMoney = bidedMoneyByOrderNo(bidId,orderNo).add(currMoney);
		if (totalMoney.compareTo(plan.getBidMoney()) == 0) {
			// 设置为已经齐标
			plan.setState(PlBidPlan.STATE2);
			// 发送邮件
			if (AppUtil.getSysConfig().get("systemEmail") != null) {
				String[] StrArr = AppUtil.getSysConfig().get("systemEmail")
						.toString().split("\\|");
				if (StrArr != null && StrArr.length > 0) {
					for (int i = 0; i < StrArr.length; i++) {
						MailData md = new MailData("满标提醒",
								StrArr[i].split(",")[0],
								StrArr[i].split(",")[1]);
						md.setProjName(plan.getBidProName());
						md.setProjNumber(plan.getBidProNumber());
						md.setBidId(plan.getBidId().toString());
						sendMail("mail/bidfullMsg.vm", md);
					}
				}
			}
			plan.setFullTime(new Date());// 设置齐标日期
			this.save(plan);
			s=0;
		}else if (totalMoney.compareTo(plan.getBidMoney())> 0){
			s=1;
		}else {
			s=-1;
		}
		return s;
	}
	
	@Override
	public BigDecimal bidedMoneyByOrderNo(Long bidId,String orderNo) {
		List<InvestPersonInfo> investList=investPersonInfoService.bidedMoneyByOrderNo(bidId,orderNo);
		// 投标成功金额合计
		BigDecimal totalMoney = new BigDecimal(0);
		for(InvestPersonInfo invest:investList){
			totalMoney = totalMoney.add(invest.getInvestMoney());
		}
		return totalMoney;
	}
	
	@Override
	public BigDecimal bidedMoney(Long bidId) {
		List<InvestPersonInfo> investList=investPersonInfoService.getByPlanId(bidId);
		// 投标成功金额合计
		BigDecimal totalMoney = new BigDecimal(0);
		for(InvestPersonInfo invest:investList){
			totalMoney = totalMoney.add(invest.getInvestMoney());
		}
		return totalMoney;
	}
	

	/**
	 * 如果投标金额小于 等于 招标金额 返回 true 可以投标 false 不可以投标
	 */
	@Override
	public boolean compare(Long bidId, BigDecimal currMoney) {
		PlBidPlan plan = this.get(bidId);
		boolean ret = false;
		BigDecimal currTotalMoney = bidedMoney(bidId).add(currMoney);
		if (currTotalMoney.compareTo(plan.getBidMoney()) != 1) {
			ret = true;
		}
		return ret;
	}
	
	/**
	 * 如果投标金额小于 等于 招标上限金额 返回 true 可以投标 false 不可以投标
	 */
	@Override
	public boolean compareMaxMoney(Long bidId, BigDecimal currMoney) {
		PlBidPlan plan = this.get(bidId);
		boolean ret = false;
		if(plan.getMaxMoney()==null){
			ret=true;
		}else if (currMoney.compareTo(plan.getBidMoney())< 1) {
			ret = true;
		}else{
			ret =false;
		}
		return ret;
	}

	/**
	 * 如果剩余金额小于起头金额 不进行限制并且必须全部投 true 可以投标 false 不可以投标
	 */
	@Override
	public boolean compareIsLasterMoney(Long bidId, BigDecimal currMoney) {
		PlBidPlan plan = this.get(bidId);
		boolean ret = false;
		BigDecimal currTotalMoney = bidedMoney(bidId);
		BigDecimal afterMoney = plan.getBidMoney().subtract(currTotalMoney);
		if (afterMoney.compareTo(plan.getStartMoney()) != 1) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 如果剩余金额小于起头金额 不进行限制并且必须全部投 true 可以投标 false 不可以投标
	 */
	@Override
	public boolean compareCurMIsEQAft(Long bidId, BigDecimal currMoney) {
		PlBidPlan plan = this.get(bidId);
		boolean ret = false;
		BigDecimal currTotalMoney = bidedMoney(bidId);
		BigDecimal afterMoney = plan.getBidMoney().subtract(currTotalMoney);
		if (afterMoney.compareTo(plan.getStartMoney()) != 1
				&& currMoney.compareTo(afterMoney) == 0) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 投标金额必须大于 投标起始金额 而且必须被 递增金额整除 true 可以投标 false 不可以投标
	 */
	@Override
	public boolean compareToStatrMoney(Long bidId, BigDecimal currMoney) {
		PlBidPlan plan = this.get(bidId);
		boolean ret = false;
		if (currMoney.compareTo(plan.getStartMoney()) >= 0) {
			/*
			 * if(plan.getRiseMoney().compareTo(new BigDecimal(0))==0){ return
			 * true; }else{
			 * if(currMoney.subtract(plan.getStartMoney()).remainder
			 * (plan.getRiseMoney()).compareTo(new BigDecimal(0))==0){ return
			 * true; }else { return false; } }
			 */
			ret = true;
		}
		return ret;
	}

	/**
	 * 获取标的动态信息 如 进度 投标人数 投标金额
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String[] bidDynamic(PlBidPlan plBidPlan) {
		String[] ret = new String[4];
		double progress;// 进度
		int persionNum = 0;// 人数
		BigDecimal money = new BigDecimal(0);// 剩余金额
		if (null == plBidPlan || null == plBidPlan.getPlBidInfos()) {
			return ret;
		}
		Iterator<PlBidInfo> it = plBidPlan.getPlBidInfos().iterator();
		// 投标金额合计
		BigDecimal totalMoney = new BigDecimal(0);

		List<InvestPersonInfo> investList=investPersonInfoService.getByPlanId(plBidPlan.getBidId());
		// 投标成功金额合计
		for(InvestPersonInfo invest:investList){
			totalMoney = totalMoney.add(invest.getInvestMoney());
			persionNum = persionNum + 1;
		}
		
		money = plBidPlan.getBidMoney().subtract(totalMoney);
		if (plBidPlan.getBidMoney().compareTo(new BigDecimal(0)) == 0) {
			progress = 0;
		} else {
			progress = Double.valueOf(
					totalMoney.divide(plBidPlan.getBidMoney(), 3,
							BigDecimal.ROUND_HALF_EVEN).multiply(
							new BigDecimal(100)).toString()).intValue();
		}

		ret[0] = String.valueOf(progress);// 进度
		ret[1] = String.valueOf(persionNum);// 人数
		ret[2] = String.valueOf(money);// 剩余金额
		if (plBidPlan.getBidEndTime() != null) {
			Date currTime = new Date();
		Date endTime = plBidPlan.getBidEndTime();
		long timeDelta = (endTime.getTime() - currTime.getTime()) / 1000;// 单位是秒
		ret[3] = String.valueOf(timeDelta);// 剩余时间
	}
		return ret;

	}

	@Override
	public String findLoanTotalMoneyBySQL(String pid) {
		return dao.findLoanTotalMoneyBySQL(pid);
	}

	@Override
	public String findAssureTypeBySQL(String pid) {
		return dao.findAssureTypeBySQL(pid);
	}

	@Override
	public String findOrgMoneyBySQL(String pid, String flag) {
		return dao.findOrgMoneyBySQL(pid, flag);
	}

	@Override
	public List<InvestprojectPlan> getInvestPlanList(HttpServletRequest request) {
		return dao.getInvestPlanList(request);
	}

	/*
	 * @Override public PlBidPlan getBidDynamic(PlBidPlan plBidPlan) { double
	 * progress;//进度 int persionNum = 0;//人数 BigDecimal money=new
	 * BigDecimal(0);//剩余金额 Iterator<PlBidInfo> it
	 * =plBidPlan.getPlBidInfos().iterator(); //投标金额合计 BigDecimal totalMoney=new
	 * BigDecimal(0); while (it.hasNext()) { PlBidInfo bidInfo=it.next();
	 * if(!bidInfo.getState().equals("0")||!bidInfo.getState().equals("4")){
	 * System.out.println("========"+bidInfo.getState());
	 * totalMoney=totalMoney.add(bidInfo.getUserMoney());
	 * persionNum=persionNum+1; } }
	 * money=plBidPlan.getBidMoney().subtract(totalMoney);
	 * progress=Double.valueOf
	 * (totalMoney.divide(plBidPlan.getBidMoney(),BigDecimal
	 * .ROUND_HALF_EVEN).multiply(new BigDecimal(100)).toString());
	 * plBidPlan.setProgress(progress);//进度
	 * plBidPlan.setPersionNum(persionNum);//人数
	 * plBidPlan.setAfterMoney(money.doubleValue());//剩余金额 return plBidPlan; }
	 */

	@Override
	public PlBidPlan getAfterTime(PlBidPlan plan) {
		Long nowTime = new Date().getTime();
		Date date = plan.getBidEndTime();
		if (date != null) {
			Long endTime = date.getTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long l = endTime - nowTime;
			if (null != plan.getState() && plan.getState() != 1) {
				if (plan.getState() == 0) {
					plan.setLaveTime("未发布");
				} else if (plan.getState() == 2) {
					plan.setLaveTime("已满标");
				} else if (plan.getState() == 3) {
					plan.setLaveTime("已流标");
				} else if (plan.getState() == 4) {
					plan.setLaveTime("已关闭");
				} else if (plan.getState() == 5) {
					plan.setLaveTime("还款中");
				} else if (plan.getState() == 6) {
					plan.setLaveTime("等待放款");
				} else {
					plan.setLaveTime(plan.getState().toString());
				}
			} else if ((l < 0)&&plan.getState()==1) {
				plan.setState(4);//为已过期
				plan.setLaveTime("招标已结束");
			} else {
				long day = l / (24 * 60 * 60 * 1000);
				long hour = (l / (60 * 60 * 1000) - day * 24);
				long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
				plan.setLaveTime("" + day + "天" + hour + "小时" + min + "分");
			}

		}
		// plan.setLaveTime("招标已结束");
		return plan;
	}

	@Override
	public boolean compareToRiseMoney(Long bidId, BigDecimal userMoney) {
		PlBidPlan plan = this.get(bidId);
		boolean ret = false;
		if (plan.getRiseMoney() != null) {
			userMoney = userMoney.subtract(plan.getStartMoney());
			if (userMoney.compareTo(new BigDecimal(0)) != 0) {
				// BigDecimal result = userMoney.divide(plan.getRiseMoney());
				if (0 == Double.valueOf(plan.getRiseMoney().toString())) {
					ret = true;
				} else {
					double ab = (Double.valueOf(userMoney.toString()) * 100)
							% (Double.valueOf(plan.getRiseMoney().toString()) * 100);
					if (ab == 0) {
						ret = true;
					}
				}
			} else {
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * 发送邮件
	 * 
	 * @param vm
	 * @param md
	 * @return
	 */
	public boolean sendMail(String vm, MailData md) {
		boolean ret = false;
		// 邮件实体
		MailModel mode = new MailModel();

		// 邮件模版需要的数据
		Map<String, Object> mailData = new HashMap<String, Object>();
		mailData.put("mailData", md);
		mode.setMailData(mailData);
		mode.setTo(md.getToEmail());
		mode.setSubject(md.getSubject());
		// 邮件发送类
		MailMessageConsumer mailMessage = new MailMessageConsumer();
		// 发送
		try {
			mailMessage.sendMail(vm, mode);
			ret = true;
		} catch (Exception e) {
		}
		return ret;
	}

	@Override
	public BigDecimal[] statisticalFinance() {
		String sql2 = "select sum(payMoney) from bp_fund_intent where fundType  = 'principalLending' and bidPlanId in (select bidId from pl_bid_plan where isLoan = 1)";
		String sql1 = "select sum(incomeMoney) from bp_fund_intent where fundType = 'loanInterest' and bidPlanId in (select bidId from pl_bid_plan where isLoan = 1)";
		BigDecimal[] baseFinance = new BigDecimal[2];
		baseFinance[0] = dao.statisticalFinance(sql1);
		baseFinance[1] = dao.statisticalFinance(sql2);
		return baseFinance;
	}

	@Override
	public String findSmallloanProjectBySQL(String pid) {
		return dao.findSmallloanProjectBySQL(pid);
	}

	@Override
	public String findMortgageBySQL(String pid) {
		return dao.findMortgageBySQL(pid);
	}

	/**
	 *返回 PlBidPlan，主要显示标的还款周期使用
	 * 
	 */
	public PlBidPlan returnPlBidPlan(PlBidPlan plan) {
		
		
		if (plan != null) {
			if (plan.getProType().equals("P_Or")) {
				BpPersionOrPro pop = plan.getBpPersionOrPro();
				plan.setInterestRate(pop.getYearInterestRate());//年华利率
				plan.setAddRate(plan.getAddRate()!=null?plan.getAddRate():null);//加息利率
				String custDateStr = "";
				if (pop.getCustDate() == null) {
					custDateStr = "0";
				} else {
					custDateStr = pop.getCustDate().toString();
				}
				String loanLife = "";
				loanLife = returnLoanLifeOr(new Date(), pop
						.getLoanEndTime(), "dayPay", custDateStr);// 返回页面上的期限值
				plan.setLoanLife(loanLife);
				plan.setAcctulData(acctulData);//还款期限
				plan.setExplain(explain);//还款周期
				plan.setRepaymentPeriod(repaymentPeriod);//返款期限的单位
			}

			if (plan.getProType().equals("B_Or")) {
				String custDateStr = "";
				BpBusinessOrPro pop = plan.getBpBusinessOrPro();
				plan.setInterestRate(pop.getYearInterestRate());//年华利率
				plan.setAddRate(plan.getAddRate()!=null?plan.getAddRate():null);//加息利率
				if (pop.getCustDate() == null) {
					custDateStr = "0";
				} else {
					custDateStr = pop.getCustDate().toString();
				}
				String loanLife = "";
				loanLife = returnLoanLifeOr(new Date(), pop
						.getLoanEndTime(), "dayPay", custDateStr);// 返回页面上的期限值
				plan.setLoanLife(loanLife);
				plan.setAcctulData(acctulData);//标的说明
				plan.setExplain(explain);//标的说明
				plan.setRepaymentPeriod(repaymentPeriod);
			}
			if (plan.getProType().equals("P_Dir")) {
				BpPersionDirPro pop = plan.getBpPersionDirPro();
				plan.setInterestRate(pop!=null?pop.getYearInterestRate():null);//年华利率
				plan.setAddRate(plan.getAddRate()!=null?plan.getAddRate():null);//加息利率
				String custDateStr = "";
				if (pop.getCustDate() == null) {
					custDateStr = "0";
				} else {
					custDateStr = pop.getCustDate().toString();
				}
				String loanLife = "";
				loanLife = returnLoanLifeDir(pop.getLoanLife().toString(), pop
						.getPayAcctualType(), custDateStr);// 返回页面上的期限值
				plan.setLoanLife(loanLife);//还款周期
				plan.setExplain(explain);//标的说明
				plan.setAcctulData(acctulData);//标的说明
				plan.setRepaymentPeriod(repaymentPeriod);
				 
			}
			if (plan.getProType().equals("B_Dir")) {
				String custDateStr = "";
				BpBusinessDirPro pop = plan.getBpBusinessDirPro();
				plan.setInterestRate(pop.getYearInterestRate());//年华利率
				plan.setAddRate(plan.getAddRate()!=null?plan.getAddRate():null);//加息利率
				if (pop.getCustDate() == null) {
					custDateStr = "0";
				} else {
					custDateStr = pop.getCustDate().toString();
				}
				String loanLife = "";
				loanLife = returnLoanLifeDir(pop.getLoanLife().toString(), pop
						.getPayAcctualType(), custDateStr);// 返回页面上的期限值
				plan.setLoanLife(loanLife);//还款周期
				plan.setExplain(explain);//标的说明
				plan.setAcctulData(acctulData);//标的说明
				plan.setRepaymentPeriod(repaymentPeriod);
			}
		}
		return plan;
	}

	public PlBidPlan mobileReturnPlBidPlan(PlBidPlan plan) {

		if(plan != null){
			String custDateStr = "";
			if(plan.getBdirProId()!=null) {
				BpBusinessDirPro pop = bpBusinessDirProDao.get(plan.getBdirProId());
				plan.setInterestRate(pop.getYearInterestRate());//年华利率
				plan.setAddRate(plan.getAddRate() != null ? plan.getAddRate() : null);//加息利率
				if (pop.getCustDate() == null) {
					custDateStr = "0";
				} else {
					custDateStr = pop.getCustDate().toString();
				}
			}
			String loanLife = "";
			loanLife = returnLoanLifeDir(plan.getPayMoneyTime().toString(),plan.getPayMoneyTimeType(),
			 custDateStr);// 返回页面上的期限值
			plan.setLoanLife(loanLife);//还款周期
            Integer cycle = 0;
            cycle = returnCycle(plan.getPayMoneyTime().toString(), plan.getPayMoneyTimeType(), custDateStr);
            plan.setCycle(cycle);

		}
		return plan;

	}

    private Integer returnCycle(String loanLife, String payAcctualType,
                                String custDate) {
        Integer returnCy = 0;
        if ("owerPay".equals(payAcctualType)) {// 自定义
            returnCy += (Integer.valueOf(loanLife) * Integer.valueOf(custDate));
            acctulData = loanLife;//周期数
            repaymentPeriod = "天";//还款周期
            explain = "每" + custDate + "天还款";
        } else if ("dayPay".equals(payAcctualType)) {// 日
            acctulData = loanLife;//周期数
            repaymentPeriod = "日";//还款周期
            explain = "日";
            returnCy += Integer.valueOf(loanLife);
        } else if ("monthPay".equals(payAcctualType)) {// 月
            acctulData = loanLife;//周期数
            repaymentPeriod = "月";//还款周期
            explain = "月";
            returnCy += Integer.valueOf(loanLife) * 30;
        } else if ("seasonPay".equals(payAcctualType)) {// 季
            acctulData = loanLife;//周期数
            repaymentPeriod = "季";//还款周期
            explain = "季";
            returnCy += Integer.valueOf(loanLife);
        } else if ("yearPay".equals(payAcctualType)) {// 年
            acctulData = loanLife;//周期数
            repaymentPeriod = "年";//还款周期
            explain = "年";
            returnCy += Integer.valueOf(loanLife) * 365;
        }
        return returnCy;
    }


	// 返回页面上的期限
	// svn：songwj
	public String returnLoanLifeOr(Date loanStarTime, Date loanEndTime,
			String payAcctualType, String custDate) {
		String returnStr = "";
		if ("owerPay".equals(payAcctualType)) {// 自定义.
			
			returnStr +=  returnLoanLife(loanStarTime, loanEndTime, 1)+"天";//周期数
			acctulData = returnLoanLife(loanStarTime, loanEndTime, 1);//周期数
			repaymentPeriod =custDate+"天";//周期
			explain = "每"+custDate+ "天还款";
		} else if ("dayPay".equals(payAcctualType)) {// 日
			acctulData =returnLoanLife(loanStarTime, loanEndTime, 1);//周期数
			repaymentPeriod ="天";//还款周期
			explain = "天";
			returnStr += returnLoanLife(loanStarTime, loanEndTime, 1) + "天";
		} else if ("monthPay".equals(payAcctualType)) {// 月
			acctulData =returnLoanLife(loanStarTime, loanEndTime, 2);//周期数
			repaymentPeriod ="月";//还款周期
			explain = "月";
			returnStr += returnLoanLife(loanStarTime, loanEndTime, 2) + "个月";
		} else if ("seasonPay".equals(payAcctualType)) {// 季
			acctulData =returnLoanLife(loanStarTime, loanEndTime, 3);//周期数
			repaymentPeriod ="季度";//还款周期
			explain = "季度";
			returnStr += returnLoanLife(loanStarTime, loanEndTime, 3) + "个季度";
		} else if ("yearPay".equals(payAcctualType)) {// 年
			acctulData =returnLoanLife(loanStarTime, loanEndTime, 4);//周期数
			repaymentPeriod ="年";//还款周期
			explain = "年";
			returnStr += returnLoanLife(loanStarTime, loanEndTime, 4) + "年";
		}
		return returnStr;
	}

	// 返回页面上的期限
	// svn:songwj
	public String returnLoanLifeDir(String loanLife, String payAcctualType,
			String custDate) {

		String returnStr = "";
		if ("owerPay".equals(payAcctualType)) {// 自定义
			returnStr += (Integer.valueOf(loanLife)*Integer.valueOf(custDate))+"天";
			acctulData = loanLife;//周期数
			repaymentPeriod ="天";//还款周期
			explain = "每"+custDate+"天还款";
		} else if ("dayPay".equals(payAcctualType)) {// 日
			acctulData =loanLife;//周期数
			repaymentPeriod ="日";//还款周期
			explain = "日";
			returnStr += loanLife + "天";
		} else if ("monthPay".equals(payAcctualType)) {// 月
			acctulData =loanLife;//周期数
			repaymentPeriod ="月";//还款周期
			explain = "月";
			returnStr += loanLife + "个月";
		} else if ("seasonPay".equals(payAcctualType)) {// 季
			acctulData =loanLife;//周期数
			repaymentPeriod ="季";//还款周期
			explain = "季";
			returnStr += loanLife + "个季度";
		} else if ("yearPay".equals(payAcctualType)) {// 年
			acctulData =loanLife;//周期数
			repaymentPeriod ="年";//还款周期
			explain = "年";
			returnStr += loanLife + "年";
		}
		return returnStr;
	}

	// 获得周期值

	/**
	 * loanStarTime:开始时间 loanEndTime：结束时间 type：返回类型 1 天 2 月 3季 4年 svn:songwj
	 */
	public String returnLoanLife(Date loanStarTime, Date loanEndTime,
			Integer type) {
		String loanLife = "";
		if (type == 1) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(loanStarTime);
			long timeStart = cal.getTimeInMillis();
			cal.setTime(loanEndTime);
			long timeEnd = cal.getTimeInMillis();
			Long a = (timeEnd - timeStart) / ((24 * 60 * 60 * 1000));// 求相差多少天
			loanLife = a.toString();
		} else if (type == 2 || type == 3 || type == 4) {// 计算月份、季度、年
			Calendar cal1 = new GregorianCalendar();
			cal1.setTime(loanEndTime);
			Calendar cal2 = new GregorianCalendar();
			cal2.setTime(loanStarTime);
			int c = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12
					+ cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
			if (type == 2) {//计算月份
				loanLife = Long.valueOf(c).toString();
			} else if (type == 3) {//计算季度
				loanLife = Long.valueOf(c / 3).toString();
			} else if (type == 4) {//计算年
				loanLife = Long.valueOf(c / 12).toString();
			}
		}
		return loanLife;
	}
	
	
	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getAcctulData() {
		return acctulData;
	}

	public void setAcctulData(String acctulData) {
		this.acctulData = acctulData;
	}

	public String getRepaymentPeriod() {
		return repaymentPeriod;
	}

	public void setRepaymentPeriod(String repaymentPeriod) {
		this.repaymentPeriod = repaymentPeriod;
	}

	public PlBidPlanServiceImpl(PlBidPlanDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public PlBidPlan getById(Long bidId) {
		return dao.getById(bidId);
	}

	@Override
	public List<PlBidPlan> queryBidPlan(String userName, String type) {
		return dao.queryBidPlan( userName,  type);
	}

	@Override
	public String getPrepaymentData(Long bidPlanId,BpCustMember mem) {
		StringBuffer sb=new StringBuffer();
		try{
			
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
			PlBidPlan plBidPlan = this.dao.get(bidPlanId);
			Long projectId=null;
			if(plBidPlan.getProType().equals("B_Dir")){
				BpBusinessDirPro bdirpro = bpBusinessDirProDao.get(plBidPlan.getBdirProId());
				projectId = bdirpro.getMoneyPlanId();
			}else if(plBidPlan.getProType().equals("B_Or")){
				BpBusinessOrPro borpro = bpBusinessOrProDao.get(plBidPlan.getBorProId());
				projectId = borpro.getMoneyPlanId();
			}else if(plBidPlan.getProType().equals("P_Dir")){
				BpPersionDirPro pdirpro = bpPersionDirProDao.get(plBidPlan.getPdirProId());
				projectId = pdirpro.getMoneyPlanId();
			}else if(plBidPlan.getProType().equals("P_Or")){
				BpPersionOrPro porpro = bpPersionOrProDao.get(plBidPlan.getPOrProId());
				projectId = porpro.getMoneyPlanId();
			}
			BpFundProject project=bpFundProjectDao.get(projectId);
			//剩余本金（提前还款金额=剩余本金）
			BigDecimal principalMoney=bpFundIntentDao.getAllPrincipalMoney(bidPlanId,null);
			sb.append("{\"principalMoney\":"+"\""+principalMoney+"\"");
			//逾期金额(如果逾期金额为0就可以提前还款)
			BigDecimal overdueMoney=new BigDecimal(0);
			//款项的逾期金额
			BigDecimal fundMoney=bpFundIntentDao.getOverdueMoney(bidPlanId);
			overdueMoney=overdueMoney.add(fundMoney);
			/*//费用的逾期金额
			BigDecimal chargeMoney=slActualToChargeDao.getOverdueMoney(bidPlanId);
			overdueMoney=overdueMoney.add(chargeMoney);*/
			sb.append(",\"overdueMoney\":"+"\""+overdueMoney+"\"");
			
			//start 判断是否有代偿借款
			QueryFilter filter = new QueryFilter();
			filter.addFilter("Q_loanerp2pId_L_EQ", mem.getId());
			filter.addFilter("Q_backStatus_N_EQ", "0");
			filter.getPagingBean().setPageSize(100000000);
			List<PlBidCompensatory> compensatoryList = plBidCompensatoryService.getAll(filter);
			if(null != compensatoryList && compensatoryList.size()>0){
				sb.append(",\"platSize\":"+"\""+1+"\"");
			}else{
				sb.append(",\"platSize\":"+"\""+0+"\"");
			}
			//end
			
			//补偿息天数
			Integer penaltyDays=(plBidPlan.getPenaltyDays()==null?0:plBidPlan.getPenaltyDays());
			sb.append(",\"penaltyDays\":"+"\""+penaltyDays+"\"");
			//补偿息金额
			BigDecimal interestPenalty=principalMoney.multiply(project.getAccrual().divide(new BigDecimal(3000),5,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(penaltyDays)).setScale(2,BigDecimal.ROUND_HALF_UP);
			sb.append(",\"interestPenalty\":"+"\""+interestPenalty+"\"");
			//截止利息总额
			List<InvestPersonInfo> list=investPersonInfoDao.getByPlanId(bidPlanId);
			BigDecimal interestMoney=new BigDecimal(0);
			BigDecimal managementConsultingMoney=new BigDecimal(0);
			BigDecimal financeServiceMoney=new BigDecimal(0);
			if(null!=list && list.size()>0){
				InvestPersonInfo p=list.get(0);
				//按提前还款日期时间查询当前所在期（不能查询已对账的款项）
				List<BpFundIntent> flist=bpFundIntentDao.listByEarlyDate(bidPlanId, p.getOrderNo(), sd.format(new Date()), "loanInterest");
				//查询出离提前还款日期最近的未还款款项台账（不能查询已对账的款项）
				List<BpFundIntent> filist2=bpFundIntentDao.queryNotPay(bidPlanId, p.getOrderNo(), "loanInterest");
				//防止客户非正常还款，例如：在4月份的时候已经正常还了6月份应还的款项，再进行提前还款时，如果按提前还款日期锁定目前所在期数，会出错
				if(null!=flist && flist.size()>0){
					BpFundIntent f=flist.get(0);
					int days=DateUtil.getDaysBetweenDate(sd.format(f.getInterestStarTime()), sd.format(new Date()));
					if(days<=0){
						days = 0;
					}
					/*if(AppUtil.getInterest().equals("1")){
						days=days+1;
					}*/
//					interestMoney=principalMoney.multiply(project.getAccrual()
//					.divide(new BigDecimal(3000),10,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(days)).setScale(2,BigDecimal.ROUND_HALF_UP);
//					
//					managementConsultingMoney=principalMoney.multiply(project.getManagementConsultingOfRate()==null?new BigDecimal(0):project.getManagementConsultingOfRate()
//					.divide(new BigDecimal(3000),10,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(days)).setScale(2,BigDecimal.ROUND_HALF_UP);
//					financeServiceMoney=principalMoney.multiply(project.getFinanceServiceOfRate()==null?new BigDecimal(0):project.getFinanceServiceOfRate()
//					.divide(new BigDecimal(3000),10,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(days)).setScale(2,BigDecimal.ROUND_HALF_UP);
					
					// 计算金额按照日利率计算
					interestMoney=principalMoney.multiply(project.getDayAccrualRate().multiply(new BigDecimal(days)).divide(new BigDecimal(100))).setScale(2,BigDecimal.ROUND_HALF_UP);
					managementConsultingMoney=principalMoney.multiply(project.getDayManagementConsultingOfRate()==null?new BigDecimal(0):project.getDayManagementConsultingOfRate())
					.multiply(new BigDecimal(days)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
					financeServiceMoney=principalMoney.multiply(project.getDayFinanceServiceOfRate()==null?new BigDecimal(0):project.getDayFinanceServiceOfRate())
					.multiply(new BigDecimal(days)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
				}else if(null!=filist2 && filist2.size()>0){
					BpFundIntent f2=filist2.get(0);
					int days=DateUtil.getDaysBetweenDate(sd.format(f2.getInterestStarTime()), sd.format(new Date()));
					if(days<=0){
						days = 0;
					}
//					days = Math.abs(days);
					
					// 计算金额按照日利率计算
					interestMoney=principalMoney.multiply(project.getDayAccrualRate().multiply(new BigDecimal(days)).divide(new BigDecimal(100))).setScale(2,BigDecimal.ROUND_HALF_UP);
					managementConsultingMoney=principalMoney.multiply(project.getDayManagementConsultingOfRate()==null?new BigDecimal(0):project.getDayManagementConsultingOfRate())
					.multiply(new BigDecimal(days)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
					financeServiceMoney=principalMoney.multiply(project.getDayFinanceServiceOfRate()==null?new BigDecimal(0):project.getDayFinanceServiceOfRate())
					.multiply(new BigDecimal(days)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
				}
				
			}
			sb.append(",\"interestMoney\":"+"\""+interestMoney+"\"");
			//截止咨询费
			sb.append(",\"managementConsultingMoney\":"+"\""+managementConsultingMoney+"\"");
			//截止服务费
			sb.append(",\"financeServiceMoney\":"+"\""+financeServiceMoney+"\"");
			//提前还款支付总额
			BigDecimal allMoney=principalMoney.add(interestPenalty).add(interestMoney).add(managementConsultingMoney).add(financeServiceMoney);
			sb.append(",\"allMoney\":"+"\""+allMoney+"\"");
			
			try {
				BigDecimal[] ret =obSystemAccountService.sumTypeTotalMoney(mem.getId(),ObSystemAccount.type0.toString());
				if(ret!=null){
					mem.setTotalMoney(ret[1]);
					mem.setFreezeMoney(ret[2]);
					mem.setAvailableInvestMoney(ret[3]);
					mem.setTotalInvestMoney(ret[4]);
					mem.setAllInterest(ret[5]);
					mem.setPrincipalRepayment(ret[6]);
					mem.setTotalRecharge(ret[7]);
					mem.setTotalEnchashment(ret[8]);
					mem.setTotalLoanMoney(ret[9]);
					mem.setTotalPrincipalRepaymentMoney(ret[10]);
					mem.setTotalNotPrincipalRepaymentMoney(ret[11]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//账户余额
			sb.append(",\"availableInvestMoney\":"+"\""+mem.getAvailableInvestMoney()+"\"");
			sb.append(",\"bidPlanId\":"+"\""+bidPlanId+"\"");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		sb.append("}");
		return sb.toString();
	}
	
	@Override
	public void bidComplete(Long planId,HttpServletRequest req) {
		QueryFilter filter=new QueryFilter(req);
		filter.getCommands().removeAll(filter.getCommands());
		filter.addFilter("Q_bidPlanId_L_EQ", planId.toString());
		List<BpFundIntent> bplist=bpFundIntentService.getAll(filter);
		BigDecimal notMoney=new BigDecimal(0);
		if(bplist!=null){
			for(BpFundIntent bp:bplist){
				notMoney=notMoney.add(bp.getNotMoney());
			}
			if(notMoney.compareTo(BigDecimal.ZERO)==0){
				PlBidPlan plan=this.get(planId);
				plan.setState(PlBidPlan.STATE7);
				this.merge(plan);
			}
		}

		
	}

	@Override
	public PlBidPlan returnImgUrl(PlBidPlan plBidPlan) {
		if (plBidPlan.getProType().equals("B_Dir")) {
			plBidPlan.setLogoURL(plBusinessDirProKeepService.getImgUrl("bp_business_dir_pro."+plBidPlan.getBdirProId()));
		}
		if (plBidPlan.getProType().equals("B_Or")) {
			plBidPlan.setLogoURL(plBusinessDirProKeepService.getImgUrl("bp_business_or_pro."+plBidPlan.getBorProId()));
		}
		if (plBidPlan.getProType().equals("P_Dir")) {
			plBidPlan.setLogoURL(plBusinessDirProKeepService.getImgUrl("bp_persion_dir_pro."+plBidPlan.getPdirProId()));
		}
		if (plBidPlan.getProType().equals("P_Or")) {
			plBidPlan.setLogoURL(plBusinessDirProKeepService.getImgUrl("bp_persion_or_pro."+plBidPlan.getPOrProId()));
		}
		return plBidPlan;
	}

	@Override
	public List<PlBidPlan> findBidPlan(Long guarantorsId, String states,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return dao.findBidPlan(guarantorsId, states, request);
	}

	@Override
	public List<PlBidPlan> findBidPlan1(Long guarantorsId, String states,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return dao.findBidPlan1(guarantorsId, states, request);
	}

	
	@Override
	public Long findBidPlanCount(Long guarantorsId, String states) {
		// TODO Auto-generated method stub
		return dao.findBidPlanCount(guarantorsId, states);
	}

	@Override
	public BigDecimal findBidPlanMoney(Long guarantorsId, String states) {
		// TODO Auto-generated method stub
		return dao.findBidPlanMoney(guarantorsId, states);
	}

	@Override
	public Long findOverdueBidPlanCount(Long guarantorsId) {
		// TODO Auto-generated method stub
		return dao.findOverdueBidPlanCount(guarantorsId);
	}
	
	/**
	 * 使用jdbc查询数据库数据
	 */
	@Override
	public int getBidSumMoney(Long bidId, BigDecimal userMoney) {
		int s=1;//默认是超标模式
		try{
					PlBidPlan plan = this.get(bidId);
					// 投标金额合计
					BigDecimal totalMoney = getFromJDBC(bidId).add(userMoney);
					if (totalMoney.compareTo(plan.getBidMoney()) <= 0) {
						if(totalMoney.compareTo(plan.getBidMoney()) == 0){// 设置为已经齐标
							plan.setState(PlBidPlan.STATE2);
							// 发送邮件
							if (AppUtil.getSysConfig().get("systemEmail") != null) {
								String[] StrArr = AppUtil.getSysConfig().get("systemEmail")
										.toString().split("\\|");
								if (StrArr != null && StrArr.length > 0) {
									for (int i = 0; i < StrArr.length; i++) {
										MailData md = new MailData("满标提醒",
												StrArr[i].split(",")[0],
												StrArr[i].split(",")[1]);
										md.setProjName(plan.getBidProName());
										md.setProjNumber(plan.getBidProNumber());
										md.setBidId(plan.getBidId().toString());
										sendMail("mail/bidfullMsg.vm", md);
									}
								}
							}
							plan.setFullTime(new Date());// 设置齐标日期
							this.save(plan);
							s=0;
						}else{
							s=-1;
						}
						
					}else {
						s=1;
					}
		}catch(Exception e){
			e.printStackTrace();
		}
		return s;
		
	
	}
	
	/**
	 * 使用jdbc查询获取数据库查询数据
	 * @param bidId
	 * @return
	 */
	private BigDecimal getFromJDBC(Long bidId){
		int flag=(int) (bidId%selectPlanmap.size());
		BigDecimal totalMoney = new BigDecimal(0);
		try{
			selectPlanmap.get(flag).lock();
			 try {
				 	Connection conn = JdbcConnection.createConnn();
				 	if(conn!=null){
				 		String sql = "SELECT sum(p.investMoney) FROM `invest_person_info` as p where p.bidPlanId="+bidId;
				        PreparedStatement pstmt;
				        pstmt = (PreparedStatement)conn.prepareStatement(sql);
				        ResultSet rs = pstmt.executeQuery();
				        double d = 0;
				        while(rs.next()){
				        	 d = rs.getDouble(1);	
				        }
				        totalMoney=BigDecimal.valueOf(d);
				        rs.close();
				        pstmt.close();
				        conn.close();
				 	}
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
		}finally{
			selectPlanmap.get(flag).unlock();
		}
		return totalMoney;
	}

	@Override
	public List<PlBidPlan> getNewPlanList(PagingBean pb,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return dao.getNewPlanList(pb, request);
	}

	@Override
	public List<PlBidSale> getSaleList(PagingBean pb, Long bidId) {
		// TODO Auto-generated method stub
		return dao.getSaleList(pb, bidId);
	}

	@Override
	public List<PlBidPlan> getListByState(String state) {
		return dao.getListByState(state);
	}

	@Override
	public List<PlBidPlan> getBidListByDuration(Integer state) {
		return dao.getBidListByDuration(state);
	}

	@Override
	public List<PlBidPlan> getIndexPlanList() {
		return dao.getIndexPlanList();
	}

	@Override
	public List<PlBidPlan> getPlanDetail(long bidId) {
		return dao.getPlanDetail(bidId);
	}

	@Override
	public List<PlBidPlan> getNewcomerList() {
		return dao.getNewcomerList();
	}

	@Override
	public PlBidPlan setProperty1(PlBidPlan plan, HttpServletRequest request) {
		try {
			//返款方式
			if("1".equals(plan.getPayIntersetWay())){
				plan.setTheWayBack("等额本息");
			}else if("2".equals(plan.getPayIntersetWay())){
				plan.setTheWayBack("等额本金");
			}else if("3".equals(plan.getPayIntersetWay())){
				plan.setTheWayBack("等本等息");
			}else if("4".equals(plan.getPayIntersetWay())){
				plan.setTheWayBack("按期计息到期还本");
			}else if("5".equals(plan.getPayIntersetWay())){
				plan.setTheWayBack("期末一次性支付本息");
			}
			//处理过期
			getAfterTime(plan);

			String[] ret = bidDynamic(plan);
			plan.setProgress(Double.valueOf(ret[0]));
			plan.setAfterMoney(Double.valueOf(ret[2]));
			if (plan.getBpBusinessDirPro() != null ){
				plan.setMortgage(findMortgageBySQL(plan.getBpBusinessDirPro().getProId().toString()));//抵押担保物类别
				plan.setAssureType(findAssureTypeBySQL(plan.getBpBusinessDirPro().getProId().toString()));//获取担保方式
				plan.setLoanTotalMoney(new BigDecimal(this.findLoanTotalMoneyBySQL(plan.getBpBusinessDirPro().getProId().toString())));
				plan.setOrgMoney(new BigDecimal(this.findOrgMoneyBySQL(plan.getBpBusinessDirPro().getProId().toString(), "1")));
			}

			PlBusinessDirProKeep businessKeep = plBusinessDirProKeepService.getDirProKeepByDirIdType1(plan.getBdirProId(),plan.getProType());
			if(businessKeep!=null){
				plan.setProKeepType(businessKeep.getPlKeepProtype().getName());
				plan.setProKeeyRemark(businessKeep.getPlKeepProtype().getRemark());
				plan.setCreditLevel(businessKeep.getPlKeepCreditlevel().getName());
			}
			if (plan.getBpBusinessDirPro() != null) {
				BpBusinessDirPro dirPro = plan.getBpBusinessDirPro();
				// 信用等级
				PlBusinessDirProKeep planKeep = plBusinessDirProKeepService.getDirProKeepByDirIdType(dirPro.getBdirProId(),"B_Dir");
				dirPro.setPlBusinessDirProKeep(planKeep);
				plan.setBpBusinessDirPro(dirPro);
				// 借款类型
				BpFundProject project = bpFundProjectService.getBpFundProject(dirPro.getProId(), (short) 0);
				if (null == project || null == project.getInvestName()) {
					if(businessKeep!=null){
						PlKeepGtorz plkeep = businessKeep.getPlKeepGtorz();
						plan.setOrgName(plkeep==null?"":plkeep.getName());//合作机构
						plan.setOrgKeyStr(plkeep==null?"":plkeep.getKeyStr());
					}
				} else {
					if (businessKeep.getProGtOrzId() != null) {
						plan.setOrgName(project.getInvestName());// 合作机构
					} else {
						plan.setOrgName("无");// 合作机构
					}
					if (null != project.getFundResource()&& "Pawn".equals(project.getFundResource())) {
						plan.setOrgKeyStr("dian");
					} else if (null != project.getFundResource()&& "SmallLoan".equals(project.getFundResource())) {
						plan.setOrgKeyStr("zhi");
					}
				}
			} else {
				if(businessKeep!=null){
					plan.setOrgName(businessKeep.getPlKeepGtorz().getName());
					plan.setOrgKeyStr(businessKeep.getPlKeepGtorz().getKeyStr());
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取 属性错误" + e.getMessage());
		}
		return plan;
	}

	@Override
	public PlBidPlan returnPlBidPlan1(PlBidPlan plan) {
		if(plan != null){
			//String custDateStr = "";
			String loanLife = "";
			//System.out.println(plan.toString()+"baiobioabiaobiaobiaobiaobiao");
			//System.out.println(plan.getPayMoneyTime().toString()+"  "+plan.getPayMoneyTimeType()+"chaxunyi");
			loanLife = returnLoanLifeDir(plan.getPayMoneyTime().toString(),plan.getPayMoneyTimeType(),
					"45");// 返回页面上的期限值
			//System.out.println(loanLife.toString()+"chaxuner");
			plan.setLoanLife(loanLife);//还款周期
			plan.setTheWayBack("按期计息");
		}
		return plan;
	}

	@Override
	public List<PlBidPlan> getIndexPlanList1(Integer page, Integer limit) {
		return dao.getIndexPlanList1(page, limit);
	}

	@Override
	public Integer getCount() {
		return dao.getCount();
	}

	@Override
	public List<PlBidPlan> getNewPlanList1(Integer start, Integer limit, HttpServletRequest request) {
		return dao.getNewPlanList1(start,limit,request);
	}


}