package com.hurong.credit.action.coupon;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.coupon.BpCoupons;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.coupon.BpCouponsService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;


/**
 * 
 * @author 
 *
 */
public class BpCouponsAction extends BaseAction{
	@Resource
	private BpCouponsService bpCouponsService;
	@Resource
	private BpCustMemberService bpCustmemberService;
	private BpCoupons bpCoupons;
	
	private Long couponId;

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public BpCoupons getBpCoupons() {
		return bpCoupons;
	}

	public void setBpCoupons(BpCoupons bpCoupons) {
		this.bpCoupons = bpCoupons;
	}

	/**
	 * 查询出来没有派发给P2P用户的优惠券显示列表
	 */
	public String list(){
		//	系统自动派发的时间为准确时间
		SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd 23:59:59");
	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date d= new Date();
	String time =sdf1.format(d).toString();	
/*		
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date d= new Date();
	String time = sdf.format(d).toString();*/
	BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
	String couponStatus = this.getRequest().getParameter("couponStatus");
	String couponType = this.getRequest().getParameter("couponType");
	String mycouponsType = this.getRequest().getParameter("mycouponsType");
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	StringBuffer sb = new StringBuffer();
	if(mem!=null){
		QueryFilter filter=new QueryFilter(this.getRequest());
		if(couponStatus!=null&&!couponStatus.equals("")){
			if(mycouponsType!=null&&!mycouponsType.equals("")&&mycouponsType.equals("4")){//查询加息券
				filter.addFilter("Q_couponType_L_EQ", "3");
			}else{
				filter.addFilter("Q_couponType_L_EQ", "1");
			}
			filter.addFilter("Q_belongUserId_L_EQ", mem.getId());
			filter.addFilter("Q_couponStatus_SN_EQ",couponStatus);
			filter.addFilter("Q_couponStartDate_D_LE",time);
		}else{
			filter.getPagingBean().setPageSize(100);
			filter.addFilter("Q_belongUserId_L_EQ", mem.getId());
		}
		
		if(null !=couponType && !"".equals(couponType)){
			if(Long.valueOf(couponType)==3){
				List<Long> listLong = new ArrayList<Long>();
				listLong.add(Long.valueOf("1"));
				listLong.add(Long.valueOf("2"));
				filter.addFilterIn("Q_couponType_L_IN",listLong);
			}
		}
		filter.getPagingBean().setPageSize(10000000);
		filter.addSorted("createDate", "desc");
		List<BpCoupons> list= bpCouponsService.getAll(filter);
		for(BpCoupons bp:list){
			if(bp.getCouponStatus().toString().equals("5")){
				if(bp.getCouponEndDate().getTime()<d.getTime()){
					bp.setCouponStatus(Short.valueOf("4"));
					bpCouponsService.save(bp);
				}
			}
		}
		String strList = gson.toJson(list);
		sb.append("{\"success\":true,\"result\":");
		sb.append(gson.toJson(strList));
		sb.append("}");
	}
	setJsonString(sb.toString());
	return SUCCESS;}
	

	/**
	 * 新UI查询优惠券的方法
	 * @return
	 */
	public String listCouponsNew(){
		BpCustMember member = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);

		jsonString=bpCouponsService.listCouponsNew(member, getRequest());
		
		return SUCCESS;
	}
	/**
	 * 更多列表显示
	 * @return
	 */
	public String couponsAllList(){
		if (pager == null) {
			pager = new Pager();
		}
		String isMobile =this.getRequest().getParameter("isMobile");
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(isMobile!=null){
			String userid = this.getRequest().getParameter("userid");
			long userid1 = Long.valueOf(userid).longValue();
			if(bpCustMember==null){
				bpCustMember = bpCustmemberService.get(userid1);
			}else{
				bpCustMember.setId(userid1);
			}
		}
		QueryFilter filter=new QueryFilter(this.getRequest());
		String couType = this.getRequest().getParameter("couType");
		if(couType!=null&&!couType.equals("")){
			if(couType.equals("wsy")){
				filter.addFilter("Q_couponStatus_SN_EQ", "5");
			}else if(couType.equals("ysy")){
				List<Short> listShort = new ArrayList<Short>();
				listShort.add(Short.valueOf("1"));
				listShort.add(Short.valueOf("10"));
				filter.addFilterIn("Q_couponStatus_SN_IN",listShort);
			}else if(couType.equals("ygq")){
				filter.addFilter("Q_couponStatus_SN_EQ", "4");
			}
			this.getRequest().setAttribute("couType", couType);
		}else{
			filter.addFilter("Q_couponStatus_SN_EQ", "5");
		}
		filter.addSorted("createDate", "desc");
		filter.addFilter("Q_belongUserId_L_EQ", bpCustMember.getId());
		filter.getPagingBean().setStart((pager.getPageNumber()-1)*pager.getPageSize());
		filter.getPagingBean().setPageSize(pager.getPageSize());
		List<BpCoupons> list= bpCouponsService.getAll(filter);
		pager.setTotalCount(filter.getPagingBean().getTotalItems());
		pager.setList(list);
		if(null!=isMobile&&isMobile.endsWith("1")){
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			StringBuffer sb = new StringBuffer();
			sb.append("{\"success\":true,\"result\":");
			sb.append(gson.toJson(pager.getList()));
			sb.append(",\"totalCounts\":");
			sb.append(gson.toJson(pager.getList().size()));
			sb.append("}");
			setJsonString(sb.toString());
			return SUCCESS;
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.COUPONSALLLIST).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 查询出来已经派发给P2P用户的优惠券列表
	 * @return
	 */
	public String bouponBelongList(){
		
		List<BpCoupons> list= bpCouponsService.bouponBelongList(this.getRequest(),start,limit);
		List<BpCoupons> listcount= bpCouponsService.bouponBelongList(this.getRequest(),null,null);
		Type type=new TypeToken<List<BpCoupons>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(listcount!=null?listcount.size():0).append(",result:");
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		System.out.println(buff.toString());
		return SUCCESS;
	}
	/**
	 * 批量对优惠券进行禁用和开启操作
	 * @return
	 */
	public String forbidOrEnable(){
		String msg="";
		String[]ids=getRequest().getParameterValues("ids");
		String couponStatus=this.getRequest().getParameter("couponStatus");
		if(couponStatus!=null&&!"".equals(couponStatus)){
			if(ids!=null){
				for(String id:ids){
					BpCoupons bps =bpCouponsService.get(new Long(id));
					bps.setCouponStatus(Short.valueOf(couponStatus));
					bpCouponsService.merge(bps);
				}
				msg="操作成功";
			}else{
				msg="没有选择操作记录";
			}
		}else{
			msg="没有选择操作类型";
		}
		
		jsonString="{success:true}";
		return SUCCESS;
	}
	/**
	 * 给投资人派发优惠券方法
	 * @return
	 */
	public String couponsDistribute(){
		String msg="";
		String p2pAccount=this.getRequest().getParameter("p2pAccount");
		if(couponId!=null&&!"".equals(couponId)){
			BpCoupons bps=	bpCouponsService.get(couponId);
			if(bps!=null){
				if(p2pAccount!=null&&!"".equals(p2pAccount)){
					BpCustMember member=bpCustmemberService.getMemberUserName(p2pAccount);
					if(member!=null){
						/*Boolean flag=bpCouponsService.couponDistribute(member.getId(),member.getTruename(),null,null,null,ContextUtil.getCurrentUserId(),ContextUtil.getCurrentUser().getFullname(),bps);
						if(flag){
							msg="成功给用户【"+p2pAccount+"】派发了优惠券";
						}else{
							msg="优惠券派发出错,请稍后再试";
						}*/
					}else{
						msg="派发用户信息没有找到";
					}
				}else{
					msg="没有填写派发用户的P2P登陆账号";
				}
			}else{
				msg="没有优惠券信息，无法派发";
			}
		}else{
			msg="没有选中派发的优惠券";
		}
		jsonString="{success:true,msg:\""+msg+"\"}";
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
				bpCouponsService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		BpCoupons bpCoupons=bpCouponsService.get(couponId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bpCoupons));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(bpCoupons.getCouponId()==null){
			bpCouponsService.save(bpCoupons);
		}else{
			BpCoupons orgBpCoupons=bpCouponsService.get(bpCoupons.getCouponId());
			try{
				BeanUtil.copyNotNullProperties(orgBpCoupons, bpCoupons);
				bpCouponsService.save(orgBpCoupons);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	/**
	 * 激活优惠券
	 * @return
	 */
	public String activateCoupons(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		Gson gson=new GsonBuilder().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer();
		String colorText =this.getRequest().getParameter("colorText");
		if(colorText!=null&&!colorText.equals("")){
			QueryFilter filter = new QueryFilter(this.getRequest());
			filter.addFilter("Q_couponNumber_S_EQ", colorText);
			List<BpCoupons> coupon = bpCouponsService.getAll(filter);
			if(coupon.size()>0){
				if(coupon.get(0).getCouponStatus()!=null){
//					if(coupon.get(0).getBelongUserId()==null||coupon.get(0).getBelongUserId().equals("")){
						if(coupon.get(0).getCouponStatus().toString().equals("3")){
							sb.append("{\"success\":true,\"result\":");
							sb.append(gson.toJson("该优惠券激活码已经被禁用,请核对后输入"));
							sb.append("}");
						}else if(coupon.get(0).getCouponStatus().toString().equals("4")){
							sb.append("{\"success\":true,\"result\":");
							sb.append(gson.toJson("该优惠券激活码已经过期,请核对后输入"));
							sb.append("}");
						}else if(coupon.get(0).getCouponStatus().toString().equals("1")){
							sb.append("{\"success\":true,\"result\":");
							sb.append(gson.toJson("该优惠券激活码已在使用中,请核对后输入"));
							sb.append("}");
						}else if(coupon.get(0).getCouponStatus().toString().equals("10")){
							sb.append("{\"success\":true,\"result\":");
							sb.append(gson.toJson("该优惠券激活码已经被使用,请核对后输入"));
							sb.append("}");
						}else if(coupon.get(0).getCouponStatus().toString().equals("5")){
							sb.append("{\"success\":true,\"result\":");
							sb.append(gson.toJson("该优惠券激活码已经被用户激活,请核对后输入"));
							sb.append("}");
						}/*else if(coupon.get(0).getBelongUserId()!=mem.getId()){
							sb.append("{\"success\":true,\"result\":");
							sb.append(gson.toJson("该优惠券激活码绑定的用户不是当前用户,请核对后输入"));
							sb.append("}");
						}*/else{
							//判断改优惠券是否过期
							Date d = new Date();
							if(d.getTime()<coupon.get(0).getCouponEndDate().getTime()){
								//激活优惠券
								if(mem!=null){
									for(BpCoupons cp:coupon){
										cp.setBelongUserId(mem.getId());
										cp.setBelongUserName(mem.getLoginname());
										cp.setBindOpraterDate(new Date());
										cp.setCouponStatus(Short.valueOf("5"));
										bpCouponsService.save(cp);
									}
									sb.append("{\"success\":true,\"result\":");
									sb.append(gson.toJson("ok"));
									sb.append("}");
								}
							}else{
								//设置为过期状态
								for(BpCoupons cp:coupon){
									cp.setCouponStatus(Short.valueOf("4"));
									bpCouponsService.save(cp);
								}
								
								sb.append("{\"success\":true,\"result\":");
								sb.append(gson.toJson("该优惠券激活码已经过期,请核对后输入"));
								sb.append("}");
							}
						}
						/*}else{
						sb.append("{\"success\":true,\"result\":");
						sb.append(gson.toJson("该优惠券ID已经派发,请核对后输入"));
						sb.append("}");
					}*/
				}else{
					sb.append("{\"success\":true,\"result\":");
					sb.append(gson.toJson("该优惠券激活码尚未派发,请核对后输入"));
					sb.append("}");
				}
			}else{
				sb.append("{\"success\":true,\"result\":");
				sb.append(gson.toJson("不存在该优惠券激活码,请核对后输入"));
				sb.append("}");
			}
		}else{
			sb.append("{\"success\":true,\"result\":");
			sb.append(gson.toJson("请先填写优惠券激活码"));
			sb.append("}");
		}
		setJsonString(sb.toString());
		return SUCCESS;
	}
	
	/**
	 * 查询出来体验标优惠券显示列表
	 */
	public String listByExperience(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d= new Date();
		String time = DateUtil.getFirstDate(d,"end");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String couponStatus = this.getRequest().getParameter("couponStatus");
		String couponType = this.getRequest().getParameter("couponType");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		StringBuffer sb = new StringBuffer();
		if(mem!=null){
			QueryFilter filter=new QueryFilter(this.getRequest());
			if(couponStatus!=null&&!couponStatus.equals("")){
				filter.addFilter("Q_belongUserId_L_EQ", mem.getId());
				filter.addFilter("Q_couponStatus_SN_EQ",couponStatus);
				filter.addFilter("Q_couponStartDate_D_LE",time);
			}
			if(null !=couponType && !"".equals(couponType)){
				if(Long.valueOf(couponType)==3){
					List<Long> listLong = new ArrayList<Long>();
					listLong.add(Long.valueOf("1"));
					listLong.add(Long.valueOf("2"));
					filter.addFilterIn("Q_couponType_L_IN",listLong);
				}
			}
			filter.addSorted("createDate", "desc");
			List<BpCoupons> list= bpCouponsService.getAll(filter);
			for(BpCoupons bp:list){
				if(bp.getCouponStatus().toString().equals("5")){
					if(bp.getCouponEndDate().getTime()<d.getTime()){
						bp.setCouponStatus(Short.valueOf("4"));
						bpCouponsService.save(bp);
					}
				}
			}
			String strList = gson.toJson(list);
			sb.append("{\"success\":true,\"result\":");
			sb.append(gson.toJson(strList));
			sb.append("}");
		}
		setJsonString(sb.toString());
		return SUCCESS;
	}
}
