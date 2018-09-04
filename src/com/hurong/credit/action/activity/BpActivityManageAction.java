package com.hurong.credit.action.activity;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.integral.IntegralManage;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.core.web.paging.PageBean;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.activity.BpActivityManage;
import com.hurong.credit.model.coupon.BpCoupons;
import com.hurong.credit.model.creditFlow.bonusSystem.record.WebBonusRecord;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.activity.BpActivityManageService;
import com.hurong.credit.service.coupon.BpCouponsService;
import com.hurong.credit.service.creditFlow.bonusSystem.record.WebBonusRecordService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;
/**
 * 
 * @author 
 *
 */
public class BpActivityManageAction extends BaseAction{
	@Resource
	private BpActivityManageService bpActivityManageService;
	private BpActivityManage bpActivityManage;
	@Resource
	private IntegralManage integralManage;
	@Resource
	private BpCouponsService bpCouponsService;
	@Resource
	private WebBonusRecordService webBonusRecordService;
	private Long activityId;

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public BpActivityManage getBpActivityManage() {
		return bpActivityManage;
	}

	public void setBpActivityManage(BpActivityManage bpActivityManage) {
		this.bpActivityManage = bpActivityManage;
	}

	public String showMyCoupons(){
	     if(isLogin()){
	    	 jsonString = bpActivityManageService.showMyCoupons(getRequest()); 
	     }else{
	 		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			return "freemarker";
	     }
		return SUCCESS;
	}
	
	
	/**
	 * 显示列表
	 */
	public String list(){
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		QueryFilter filter=new QueryFilter(this.getRequest());
		//活动状态，0开启1关闭
		filter.addFilter("Q_status_I_EQ", Integer.valueOf("0"));
		filter.addFilter("Q_flag_I_EQ", Integer.valueOf("3"));
		//判断是否过期
		filter.addFilter("Q_activityEndDate_D_GT", sdf.format(d));
		filter.getPagingBean().setPageSize(6);
		List<BpActivityManage> list= bpActivityManageService.getAll(filter);
		//将数据转化为json格式
		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer();
		String strList = gson.toJson(list);
		sb.append("{\"success\":true,\"result\":");
		 sb.append(gson.toJson(strList));
			sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
	/**
	 * 更多列表显示
	 * @return
	 */
	public String activityAllList(){
		String isMobile=this.getRequest().getParameter("isMobile");
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//累计总积分
		int totalRecordNumber=webBonusRecordService.findRecordNumber(bpCustMember.getId(), "1");
		this.getRequest().setAttribute("totalRecordNumber", totalRecordNumber);
		//已使用积分
		int useRecordNumber=webBonusRecordService.findRecordNumber(bpCustMember.getId(), "2");
		this.getRequest().setAttribute("useRecordNumber", useRecordNumber);
		//可用积分
		int usableRecordNumber = totalRecordNumber-useRecordNumber;
		this.getRequest().setAttribute("usableRecordNumber", usableRecordNumber);
		if (pager == null) {
			pager = new Pager();
		}
		QueryFilter filter=new QueryFilter(this.getRequest());
		//活动状态，0开启1关闭
		filter.addFilter("Q_status_I_EQ", Integer.valueOf("0"));
		filter.addFilter("Q_flag_I_EQ", Integer.valueOf("3"));
	//	filter.addFilter("Q_activityEndDate_D_GT", sdf.format(d));
		filter.getPagingBean().setStart((pager.getPageNumber()-1)*pager.getPageSize());
		List<BpActivityManage> list= bpActivityManageService.getAll(filter);
		pager.setTotalCount(filter.getPagingBean().getTotalItems());
		pager.setList(list);
		if(null!=isMobile&&!"".equals(isMobile)&&"1".equals(isMobile)){
			StringBuffer sb = new StringBuffer();
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			sb.append("{\"success\":true,\"data\":");
			sb.append(gson.toJson(list));
			sb.append("}");
			setJsonString(sb.toString());
		    return SUCCESS;
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BPACTIVITYLIST).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 积分兑换
	 * @return
	 */
	public String exchange(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String activityId = this.getRequest().getParameter("activityId");
		Gson gson=new GsonBuilder().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer();
		BpCoupons bp=new BpCoupons();
		try {
			//累计总积分
			int totalRecordNumber=webBonusRecordService.findRecordNumber(mem.getId(), "1");
			//已使用积分
			int useRecordNumber=webBonusRecordService.findRecordNumber(mem.getId(), "2");
			//可用积分
			int usableRecordNumber = totalRecordNumber-useRecordNumber;
			BpActivityManage actiManage = bpActivityManageService.get(Long.valueOf(activityId));
			//判断此兑换活动是否过期
			Date d = new Date();
			if(actiManage.getActivityEndDate().getTime()<d.getTime()){
				sb.append("{\"success\":false,\"result\":");
				sb.append(gson.toJson("所选择的兑换活动已经结束!"));
				sb.append("}");
			}else{
				if(actiManage.getNeedIntegral()<=usableRecordNumber){
					//判断是否该积分兑换有限制兑换次数
					boolean num=true;
					if(actiManage.getIsGetAstrict()!=null&&!actiManage.getIsGetAstrict().equals("")&&actiManage.getIsGetAstrict().equals("1")){
						//查询该兑换券兑换了几次了。
						QueryFilter filter = new QueryFilter(this.getRequest());
						filter.addFilter("Q_couponResourceType_S_EQ", "couponResourceType_active");
						filter.addFilter("Q_resourceId_L_EQ", activityId);
						filter.addFilter("Q_belongUserId_L_EQ", mem.getId());
						filter.getPagingBean().setPageSize(100000000);
						List<BpCoupons> coupon = bpCouponsService.getAll(filter);
						if(coupon.size()>=actiManage.getGetAstrictNumber()){
							num=false;
						}
					}
					if(num){
						//添加优惠券
						bp.setCouponResourceType(BpCoupons.COUPONRESOURCE_ACTIVE);//来源
						bp.setCompanyId(Long.valueOf(1));
						bp.setResourceId(actiManage.getActivityId());
						bp.setCreateName(mem.getLoginname());
						bp.setCreateUserId(Long.valueOf(0));
						bp.setCreateDate(new Date());
						bp.setCouponType(actiManage.getCouponType());
						
						bp.setCouponStatus(Short.valueOf("5"));
						bp.setCouponValue(actiManage.getParValue());
						bp.setCouponStartDate(new Date());
						bp.setCouponEndDate(DateUtil.addDaysToDate(new Date(), actiManage.getValidNumber()));
						bp.setCouponNumber(bpCouponsService.createCouponNumber(BpCoupons.COUPONRESOURCE_ACTIVE));
						bp.setBindOpratorName(mem.getLoginname());
						bp.setBindOpraterDate(new Date());
						bp.setCouponMoney(new BigDecimal(0));
						bp.setBelongUserId(mem.getId());
						bp.setBelongUserName(mem.getTruename());
						bp.setIntention(actiManage.findSendType());//操作点名称
						bp.setCouponsDescribe(actiManage.getActivityExplain());//描述
						bpCouponsService.save(bp);
						//增加积分记录  减少积分
						integralManage.addSocreBpActivityManage(Long.valueOf(mem.getId()), Long.valueOf(-actiManage.getNeedIntegral()), actiManage);
						sb.append("{\"success\":true,\"result\":");
						sb.append(gson.toJson("兑换成功,请到我的优惠券里查看!"));
						sb.append("}");
					}else{
						sb.append("{\"success\":false,\"result\":");
						sb.append(gson.toJson("兑换失败,你已达到该兑换总次数!"));
						sb.append("}");
					}
				}else{
					sb.append("{\"success\":false,\"result\":");
					sb.append(gson.toJson("可用积分不足,不能兑换!"));
					sb.append("}");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			bpCouponsService.remove(bp);
			sb.append("{\"success\":false,\"result\":");
			sb.append(gson.toJson("积分兑换失败!"));
			sb.append("}");
		}
		setJsonString(sb.toString());
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				bpActivityManageService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String find(){
		BpActivityManage bpActivityManage=bpActivityManageService.get(activityId);
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bpActivityManage));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	
	/***
	 * 根据活动类型及当前日期生成活动编号
	 */
	public void findActivityNumber(){
		String flag=this.getRequest().getParameter("flag");
		bpActivityManageService.findActivityNumber(flag);
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		/*StringBuffer sb = new StringBuffer();
		String flag=this.getRequest().getParameter("bpActivityManage.flag");
		if(null==bpActivityManage.getActivityId()){
			//需判断如果是同一活动类型_同一发送类型_活动的起止日期不允许交叉                                          @1
			boolean isExist=true;
			boolean tempFlag="3".equals(flag)?false:true;//3代表积分兑换优惠券  此时不要判断@1
			if(tempFlag){
				isExist=bpActivityManageService.findExistCrossDate(bpActivityManage);
			}
			if(isExist && tempFlag){
				sb.append("{success:true,flag:false,msg:'在该[时间周期内]已提交过该[发送类型]的[活动]'}");
			}else{
				bpActivityManage.setCompanyId(ContextUtil.getLoginCompanyId());
				bpActivityManage.setCreaterId(ContextUtil.getCurrentUserId());
				bpActivityManage.setCreateDate(new Date());
				bpActivityManageService.save(bpActivityManage);
				sb.append("{success:true,flag:true,msg:'成功提交活动'}");
			}
		}else{
			BpActivityManage orgBpActivityManage=bpActivityManageService.get(bpActivityManage.getActivityId());
			try{
				BeanUtil.copyNotNullProperties(orgBpActivityManage, bpActivityManage);
				bpActivityManageService.save(orgBpActivityManage);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString(sb.toString());*/
		return SUCCESS;
		
	}
	
	/***
	 * 关闭活动
	 */
	public String close(){
		String[] ids = getRequest().getParameterValues("ids");
		boolean flag=bpActivityManageService.closeActivity(ids);
		if(flag){
			setJsonString("{success:true,msg:'关闭活动成功'}");
		}else{
			setJsonString("{success:true,msg:'关闭活动失败'}");
		}
		return SUCCESS;
	}
	//判断用户是否登录
	public boolean isLogin(){
		Boolean boo = false;
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			boo = true;
		}
		return boo;
	}


}
