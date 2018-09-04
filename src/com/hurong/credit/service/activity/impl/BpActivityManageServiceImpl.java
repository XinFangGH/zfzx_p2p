package com.hurong.credit.service.activity.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hurong.core.integral.IntegralManage;
import com.hurong.core.integral.IntegralManageImpl;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.paging.PageBean;
import com.hurong.credit.dao.activity.BpActivityManageDao;
import com.hurong.credit.dao.coupon.BpCouponsDao;
import com.hurong.credit.dao.user.BpCustMemberDao;
import com.hurong.credit.model.activity.BpActivityManage;
import com.hurong.credit.model.coupon.BpCoupons;
import com.hurong.credit.model.creditFlow.bonusSystem.gradeSet.MemberGradeSet;
import com.hurong.credit.model.creditFlow.bonusSystem.record.WebBonusRecord;
import com.hurong.credit.model.customer.BpCustRelation;
import com.hurong.credit.model.p2p.redMoney.BpCustRedMember;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.activity.BpActivityManageService;
import com.hurong.credit.service.coupon.BpCouponsService;
import com.hurong.credit.service.creditFlow.bonusSystem.gradeSet.MemberGradeSetService;
import com.hurong.credit.service.creditFlow.bonusSystem.record.WebBonusRecordService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.customer.BpCustRelationService;
import com.hurong.credit.service.p2p.redMoney.BpCustRedMemberService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

/**
 * 
 * @author 
 *
 */
public class BpActivityManageServiceImpl extends BaseServiceImpl<BpActivityManage> implements BpActivityManageService{
	private BpActivityManageDao dao;
	
	@Resource
	private BpCustMemberDao bpCustMemberDao;
	@Resource
	private BpCouponsDao bpCouponsDao;
	@Resource
	private BpCouponsService bpCouponsService;
	@Resource
	private IntegralManage integralManage;
	@Resource
	private BpCustRedMemberService bpCustRedMemberService;
	@Resource
	private MemberGradeSetService memberGradeSetService;
	@Resource
	private WebBonusRecordService webBonusRecordService;
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	@Resource
	private BpCustRelationService bpCustRelationService;
	
	public BpActivityManageServiceImpl(BpActivityManageDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void findActivityNumber(String flag) {
		StringBuffer sb = new StringBuffer("{success:true,data:");
		String str_Date = DateUtil.getNowDateTime("yyyyMMdd");
		StringBuffer num = new StringBuffer();
		int number = 0;
		if("0".equals(flag)){
			num.append("JF").append(str_Date);
		}else if("1".equals(flag)){
			num.append("HB").append(str_Date);
		}else if("2".equals(flag)){
			num.append("YH").append(str_Date);
		}else if("3".equals(flag)){
			num.append("JFDHYH").append(str_Date);
		}
		List<BpActivityManage> list=dao.findActivityNumber(flag);
		if(null!=list && list.size()>0){
			number=list.size();
			number+=1;
			if(list.size()<10){
				num.append("000").append(number);
			}else if(list.size()>=10&&list.size()<100){
				num.append("00").append(number);
			}else if(list.size()>=100){
				num.append("0").append(number);
			}
		}else{
			num.append("000").append(number);
		}
		sb.append("'").append(num).append("'}");
		JsonUtil.responseJsonString(sb.toString());
	}

	@Override
	public boolean findExistCrossDate(BpActivityManage bpActivityManage) {
		List<BpActivityManage> list=dao.findExistCrossDate(bpActivityManage);
		if(null!=list && list.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean closeActivity(String[] ids) {
		try{
			for (String id : ids) {
				BpActivityManage oldManage=dao.get(Long.valueOf(id));
				if(oldManage.getStatus()==0){
					oldManage.setStatus(1);
					dao.merge(oldManage);
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("删除活动失败:" + e.getMessage());
			return false;
		}
	}

	@Override
	public void findList(PageBean<BpActivityManage> pageBean) {
		dao.findList(pageBean);
	}
	
	
	@Override
	public List<BpActivityManage> listActivity(String activityType) {
		return dao.listActivity(Long.valueOf(activityType));
	}
	
	
	/**
	 * 自动分发活动奖励引擎
	 * 活动中心，所有活动查询
	 * --自动派发积分，
	 *   自动派发红包
	 *   自动派发优惠券
     * @param bpCustMemberId   --p2p账户ID
     * @param money   --金额--用于投标，充值时进行条件判断
     * @param activityType   --活动操作类型
     * 		  		["注册", "1"],
					["邀请", "2"],
					["投标", "3"],
					["充值", "4"],
					["邀请好友第一次投标", "5"]
					["累计投资", "6"],
					["累计充值", "7"],
					["首投", "8"],
					["累计推荐投资", "9"]			
     * 
     * 
     * 
     * @author LIUSL
	 */
	@Override
	public void  autoDistributeEngine(String activityType, String bpCustMemberId,BigDecimal money) {
		 
		/**
		 * 此方法被调用的地方有   注册，邀请，投标，充值，邀请好友第一次投标
		 * 每次传一个操作点类型过来，进行三个活动查询，分别进行循环，依次派发对应奖励
		 */
		try {
				//判断是否是内部人员操作,内部人员不参加活动
				BpCustRelation relation =bpCustRelationService.getCustOffine(Long.valueOf(bpCustMemberId), "p_staff");
				if(relation!=null){
				}else{
					//标识--是否加过活动积分
					boolean indexFlag = false;
					//查出同一个操作点的全部活动
					List<BpActivityManage> list = dao.listActivity(Long.valueOf(activityType));
					
					if(list!=null&&list.size()>0){
						for(int i = 0 ; i < list.size() ; i++ ){
							//拿到具体的活动实例
							BpActivityManage bpActivityManage = list.get(i);
							//查询一个活动中累计交易的金额6，7，9
							if(activityType.equals("6")||activityType.equals("7")||activityType.equals("9")){
								money=sumMoney(activityType,bpCustMemberId,bpActivityManage);
							}
							//判断活动的类型，区分是积分活动，红包活动，优惠券活动
							if(bpActivityManage.getFlag()!=null&&bpActivityManage.getFlag()==0){//积分
								
								/*-------------------------------自动增加积分---------------------------------------------*/
								//如果积分活动是注册则直接增加积分
								if("1".equals(activityType)){
									/*******************/
									//更改标识--标识为活动积分增加过
									indexFlag= true;
									/*******************/
									integralManage.addSocreBpActivityManage(Long.valueOf(bpCustMemberId), bpActivityManage.getParValue().longValue(), bpActivityManage);
								}else{//否则根据条件奖励积分
									/*******************/
									//更改标识--标识为活动积分增加过
									indexFlag= true;
									/*******************/
									//判断每一个梯度只能奖励一次，只有累计的判断6，7，9
									boolean send = checkSend(bpActivityManage,bpCustMemberId,activityType,"0"); 
									//校验积分活动和红包活动是否带有条件约束
									boolean flag = checkCriteria(bpActivityManage, bpCustMemberId, money);
									if(flag&&send){
										
										//如果bpActivityManage.getReferenceUnit()不等于3----发一次奖励
										if(bpActivityManage.getReferenceUnit().compareTo(Long.valueOf(3))!=0){
											integralManage.addSocreBpActivityManage(Long.valueOf(bpCustMemberId), bpActivityManage.getParValue().longValue(), bpActivityManage);
										}else{//否则发以金额成倍奖励   --以投标金额翻倍
											//倍数
											BigDecimal multiple = money.divide(new BigDecimal(bpActivityManage.getMoney()),0,RoundingMode.DOWN);
											//成倍奖励  --带for就有多条，不带for就一条
											for(int x = 0 ; x < multiple.intValue() ; x ++){
												integralManage.addSocreBpActivityManage(Long.valueOf(bpCustMemberId), bpActivityManage.getParValue().longValue(), bpActivityManage);
											} 
											
										}
									}
								}
								
								
							}else if(bpActivityManage.getFlag()!=null&&bpActivityManage.getFlag()==1){//红包
								/*-------------------------------自动派发红包---------------------------------------------*/
								//如果红包活动是注册则直接增加积分
								if("1".equals(activityType)){
									
									bpCustRedMemberService.saveByBpActivityManage(Long.valueOf(bpCustMemberId), bpActivityManage.getParValue(), bpActivityManage);
								}else{//否则根据条件奖励积分
									
									//判断每一个梯度只能奖励一次，只有累计的判断6，7，9
									boolean send = checkSend(bpActivityManage,bpCustMemberId,activityType,"1"); 
									//校验积分活动和红包活动是否带有条件约束	
									boolean flag = checkCriteria(bpActivityManage, bpCustMemberId, money);
									if(flag&&send){
										
										//如果bpActivityManage.getReferenceUnit()不等于3----发一次奖励
										if(bpActivityManage.getReferenceUnit().compareTo(Long.valueOf(3))!=0){
											bpCustRedMemberService.saveByBpActivityManage(Long.valueOf(bpCustMemberId), bpActivityManage.getParValue(), bpActivityManage);
										}else{//否则发以金额成倍奖励
											//倍数
											BigDecimal multiple = money.divide(new BigDecimal(bpActivityManage.getMoney()),0,RoundingMode.DOWN);
											//成倍奖励   --带for就有多条，不带for就一条
											for(int x = 0 ; x < multiple.intValue() ; x ++){
												bpCustRedMemberService.saveByBpActivityManage(Long.valueOf(bpCustMemberId),bpActivityManage.getParValue().multiply(multiple), bpActivityManage);
											} 
										}
									}
								}	
							}else if(bpActivityManage.getFlag()!=null&&bpActivityManage.getFlag()==2){//优惠券
								/*-------------------------------自动派发优惠券---------------------------------------------*/
								
								//如果活动是注册则直接增加
								if("1".equals(activityType)){
									bpCouponsService.saveBpCoupons(bpCustMemberId, bpActivityManage);
								}else{//否则根据条件奖励积分
									//判断每一个梯度只能奖励一次，只有累计的判断6，7，9
									boolean send = checkSend(bpActivityManage,bpCustMemberId,activityType,"2"); 
									//校验优惠券是否带有条件约束
									boolean flag = checkCriteria(bpActivityManage, bpCustMemberId, money);
									if(flag&&send){
										//如果bpActivityManage.getReferenceUnit()不等于3----发一次奖励
										if(bpActivityManage.getReferenceUnit().compareTo(Long.valueOf(3))!=0){
											bpCouponsService.saveBpCoupons(bpCustMemberId, bpActivityManage);
										}else{//否则发以金额成倍奖励
											//倍数
											BigDecimal multiple = money.divide(new BigDecimal(bpActivityManage.getMoney()),0,RoundingMode.DOWN);
											//成倍奖励   --带for就有多条，不带for就一条
											for(int x = 0 ; x < multiple.intValue() ; x ++){
												bpCouponsService.saveBpCoupons(bpCustMemberId, bpActivityManage);
											} 
										}
									}
								}
								
								
							}
							
						}
					}
					
					
					//加入新的逻辑-----如果活动积分增加了积分-----则普通积分规则不能加分
					//如果活动积分没有增加过，则查询普通积分规则进行增加
					if(!indexFlag){
						try {
							IntegralManage integralManage = new IntegralManageImpl();
							String operationKey = "";
							/**
							1	["注册", "register"],
							2	["邀请", "invite"],
							3	["投标", "tender"],
							4	["充值", "recharge"],
							5	["邀请第一次投标", "inviteOnce"]
							 */
							if("1".equals(activityType)){
								operationKey = "register";
							}else if("2".equals(activityType)){
								operationKey = "invite";
							}else if("3".equals(activityType)){
								operationKey = "tender";
							}else if("4".equals(activityType)){
								operationKey = "recharge";
							}else if("5".equals(activityType)){
								operationKey = "inviteOnce";
							}
							//查询普通积分规则进行加分
							integralManage.addScordByFlagKey(Long.valueOf(bpCustMemberId), operationKey);
						} catch (Exception e) {
							logger.error("--------------------普通积分奖励报错------------------------");
							e.printStackTrace();
						}
					}
				}
		} catch (Exception e) {
			logger.error("活动分发方法报错----------");
			e.printStackTrace();
		}
	
	}
	
	
	/**
	 * 校验积分活动和红包活动是否带有条件约束
	 * @param bpActivityManage   活动对象
	 * @param bpCustMemberId p2p账户ID
	 * @param money  金额
	 * @return  true条件通过，条件不通过false
	 */
	private boolean checkCriteria(BpActivityManage bpActivityManage, String bpCustMemberId,BigDecimal money){
		try {
			if(bpActivityManage.getReferenceUnit()!=null){
				//小于等于条件
				if(bpActivityManage.getReferenceUnit().compareTo(Long.valueOf(1))==0){
					//会员等级设定不为空，并且会员等级设置不为无
					if(bpActivityManage.getLevelId()!=null&&bpActivityManage.getLevelId().compareTo(Long.valueOf(0))!=0){
						BpCustMember bpCustMember = bpCustMemberDao.get(Long.valueOf(bpCustMemberId));
						//当前用户会员等级
						MemberGradeSet memberGradeSet = memberGradeSetService.findByUserScore(bpCustMember.getScore());
						//配置的会员等级
						MemberGradeSet confMemberGradeSet = memberGradeSetService.get(bpActivityManage.getLevelId());
						//当前用户会员等级大于配置的会员等级
						if(Long.valueOf(memberGradeSet.getLevelName()).compareTo(Long.valueOf(confMemberGradeSet.getLevelName()))>0){
							//则不满足条件返回false
							return false;
						}
					}
					//判断金额--如果当前金额大于条件所设定的金额，则返回false
					if(money.compareTo(new BigDecimal(bpActivityManage.getMoney()))>0){
						return false;
					}
					return true;
				//大于等于条件
				}else if(bpActivityManage.getReferenceUnit().compareTo(Long.valueOf(2))==0){
					//会员等级设定不为空，并且会员等级设置不为无
					if(bpActivityManage.getLevelId()!=null&&bpActivityManage.getLevelId().compareTo(Long.valueOf(0))!=0){
						BpCustMember bpCustMember = bpCustMemberDao.get(Long.valueOf(bpCustMemberId));
						//当前用户会员等级
						MemberGradeSet memberGradeSet = memberGradeSetService.findByUserScore(bpCustMember.getScore());
						//配置的会员等级
						MemberGradeSet confMemberGradeSet = memberGradeSetService.get(bpActivityManage.getLevelId());
						//当前用户会员等级小于配置的会员等级
						if(Long.valueOf(memberGradeSet.getLevelName()).compareTo(Long.valueOf(confMemberGradeSet.getLevelName()))<0){
							//则不满足条件返回false
							return false;
						}
					}
					//判断金额--如果当前金额小于条件所设定的金额，则返回false
					if(money.compareTo(new BigDecimal(bpActivityManage.getMoney()))<0){
						return false;
					}
					return true;
				//满足条件金额成倍奖励---默认为两个条件都大于等于
				}else if(bpActivityManage.getReferenceUnit().compareTo(Long.valueOf(3))==0){
					//会员等级设定不为空，并且会员等级设置不为无
					if(bpActivityManage.getLevelId()!=null&&bpActivityManage.getLevelId().compareTo(Long.valueOf(0))!=0){
						BpCustMember bpCustMember = bpCustMemberDao.get(Long.valueOf(bpCustMemberId));
						//当前用户会员等级
						MemberGradeSet memberGradeSet = memberGradeSetService.findByUserScore(bpCustMember.getScore());
						//配置的会员等级
						MemberGradeSet confMemberGradeSet = memberGradeSetService.get(bpActivityManage.getLevelId());
						//当前用户会员等级小于配置的会员等级
						if(Long.valueOf(memberGradeSet.getLevelName()).compareTo(Long.valueOf(confMemberGradeSet.getLevelName()))<0){
							//则不满足条件返回false
							return false;
						}
					}
					//判断金额--如果当前金额小于条件所设定的金额，则返回false
					if(money.compareTo(new BigDecimal(bpActivityManage.getMoney()))<0){
						return false;
					}
					return true;
				}else{//没有条件
					return true;
				}
			}else{//没有条件
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 查询设置的活动梯度是否已经派发
	 * ["累计投资", "6"],
		["累计充值", "7"],
		["首投", "8"],
		["累计推荐投资", "9"]	
	 * @param activityNumber
	 * @param bpCustMemberId
	 * @return
	 */
	private boolean checkSend(BpActivityManage bpActivityManage,String bpCustMemberId,String activityType,String flag){
		if(activityType.equals("6")||activityType.equals("7")||activityType.equals("9")){
			if(flag.equals("0")){//判断积分
				List<WebBonusRecord> list = webBonusRecordService.getActivityNumber(bpActivityManage.getActivityNumber(), bpCustMemberId, null);
				if(list.size()>=1){
					return false;
				}else{
					return true;
				}
			}else if(flag.equals("1")){//判断红包
				List<BpCustRedMember> list = bpCustRedMemberService.getActivityNumber(bpActivityManage.getActivityNumber(), bpCustMemberId, null);
				if(list.size()>=1){
					return false;
				}else{
					return true;
				}
			}else if(flag.equals("2")){//判断优惠券
				List<BpCoupons> list = bpCouponsService.getActivityType(BpCoupons.COUPONRESOURCE_ACTIVE,bpActivityManage.getActivityId(),bpCustMemberId);
				if(list.size()>=1){
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		}else{
			return true;
		}
		
	
	}
	/**
	 * 查询一个活动范围内的累计交易金额
	 * @param activityType
	 * @param bpCustMemberId
	 * @param bpActivityManage
	 * @return
	 */
	 private BigDecimal sumMoney(String activityType,String bpCustMemberId,BpActivityManage bpActivityManage)
	 {
		    BigDecimal sumMoney = null;
			if(activityType.equals("6")){//累计投资
				sumMoney = obAccountDealInfoService.sumPersonMoney("4", Long.valueOf(bpCustMemberId), bpActivityManage.getActivityStartDate().toString(), bpActivityManage.getActivityEndDate().toString());
			}else if(activityType.equals("7")){//累计充值
				sumMoney = obAccountDealInfoService.sumPersonMoney("1", Long.valueOf(bpCustMemberId), bpActivityManage.getActivityStartDate().toString(), bpActivityManage.getActivityEndDate().toString());
			}else if(activityType.equals("9")){//累计推荐投资
				 sumMoney = obAccountDealInfoService.sumPersonMoney("4", Long.valueOf(bpCustMemberId), bpActivityManage.getActivityStartDate().toString(), bpActivityManage.getActivityEndDate().toString());
			}
			return sumMoney;
	 }

	@Override
	public String showMyCoupons(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<BpActivityManage> list = dao.showMyCoupons(request);
		Integer listNums = dao.showMyCoupons(request).size();
		return CreateJson(list,listNums!=0?listNums:1);
	}

	
	@Override
	public String CreateJson(Object obj,Integer Num) {
        StringBuffer buff1 = new StringBuffer("{\"success\":true,\"totalCounts\":").append(Num).append(",\"result\":");
        JSONSerializer json = JsonUtil.getJSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
		buff1.append(json.serialize(obj));
		buff1.append("}");
		return buff1.toString();
	}

	
	
	
}