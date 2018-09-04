package com.hurong.credit.action.coupon;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.credit.model.coupon.BpCouponSetting;
import com.hurong.credit.service.coupon.BpCouponSettingService;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.web.action.BaseAction;

/**
 * 
 * @author 
 *
 */
public class BpCouponSettingAction extends BaseAction{
	@Resource
	private BpCouponSettingService bpCouponSettingService;
	
	private BpCouponSetting bpCouponSetting;
	
	private Long categoryId;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public BpCouponSetting getBpCouponSetting() {
		return bpCouponSetting;
	}

	public void setBpCouponSetting(BpCouponSetting bpCouponSetting) {
		this.bpCouponSetting = bpCouponSetting;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<BpCouponSetting> list= bpCouponSettingService.getAll(filter);
		
		Type type=new TypeToken<List<BpCouponSetting>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		jsonString=buff.toString();
		System.out.println(jsonString);
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
				bpCouponSettingService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true,msg:\"删除成功\"}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		BpCouponSetting bpCouponSetting=bpCouponSettingService.get(categoryId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bpCouponSetting));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(bpCouponSetting.getCategoryId()==null){
			
			/*if(bpCouponSetting.getCouponType()!=null){
				bpCouponSetting.setCouponTypeValue(dictionaryIndependentService.getByDicKey(bpCouponSetting.getCouponType()).get(0).getItemValue());
			}*/
			if(bpCouponSetting.getCounponCount()!=null&&bpCouponSetting.getCouponValue()!=null){
				BigDecimal totalMoney=bpCouponSetting.getCouponValue().multiply(new BigDecimal(bpCouponSetting.getCounponCount()));
				bpCouponSetting.setTotalCouponValue(totalMoney);
			}else{
				bpCouponSetting.setTotalCouponValue(new BigDecimal("0"));
			}
			bpCouponSetting.setCompanyId(ContextUtil.getLoginCompanyId());
			bpCouponSetting.setCreateDate(new Date());
			/*bpCouponSetting.setCreateUserId(ContextUtil.getCurrentUserId());
			bpCouponSetting.setCreateName(ContextUtil.getCurrentUser().getFullname());*/
			bpCouponSettingService.save(bpCouponSetting);
		}else{
			BpCouponSetting orgBpCouponSetting=bpCouponSettingService.get(bpCouponSetting.getCategoryId());
			try{
				BeanUtil.copyNotNullProperties(orgBpCouponSetting, bpCouponSetting);
				bpCouponSettingService.save(orgBpCouponSetting);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	/**
	 * 用来审核优惠券
	 * @return
	 */
	public String check(){
		String checkType=this.getRequest().getParameter("checkType");
		String msg=bpCouponSettingService.check(checkType,categoryId);
		setJsonString("{success:true,msg:\""+msg+"\"}");
		return SUCCESS;
		
	}
}
