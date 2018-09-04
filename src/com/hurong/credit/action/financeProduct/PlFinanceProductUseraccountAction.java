package com.hurong.credit.action.financeProduct;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.financeProduct.PlFinanceProductUseraccount;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.financeProduct.PlFinanceProductService;
import com.hurong.credit.service.financeProduct.PlFinanceProductUserAccountInfoService;
import com.hurong.credit.service.financeProduct.PlFinanceProductUseraccountService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;
/**
 * 
 * @author 
 *
 */
@SuppressWarnings("serial")
public class PlFinanceProductUseraccountAction extends BaseAction{
	@Resource
	private PlFinanceProductUseraccountService plFinanceProductUseraccountService;
	@Resource
	private PlFinanceProductService plFinanceProductService;
	@Resource
	private PlFinanceProductUserAccountInfoService plFinanceProductUserAccountInfoService;
	private PlFinanceProductUseraccount plFinanceProductUseraccount;
	
	
	private Long id;
	//购买金额
	private BigDecimal amount;

	private Long productId;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlFinanceProductUseraccount getPlFinanceProductUseraccount() {
		return plFinanceProductUseraccount;
	}

	public void setPlFinanceProductUseraccount(PlFinanceProductUseraccount plFinanceProductUseraccount) {
		this.plFinanceProductUseraccount = plFinanceProductUseraccount;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		PagingBean  pb=new PagingBean(start,limit);
		List<PlFinanceProductUseraccount> list= plFinanceProductUseraccountService.getUserAccountList(this.getRequest(),pb);
		Type type=new TypeToken<List<PlFinanceProductUseraccount>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(pb.getTotalItems()).append(",result:");
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
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
				plFinanceProductUseraccountService.remove(new Long(id));
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
		PlFinanceProductUseraccount plFinanceProductUseraccount=plFinanceProductUseraccountService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(plFinanceProductUseraccount));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(plFinanceProductUseraccount.getId()==null){
			plFinanceProductUseraccountService.save(plFinanceProductUseraccount);
		}else{
			PlFinanceProductUseraccount orgPlFinanceProductUseraccount=plFinanceProductUseraccountService.get(plFinanceProductUseraccount.getId());
			try{
				BeanUtil.copyNotNullProperties(orgPlFinanceProductUseraccount, plFinanceProductUseraccount);
				plFinanceProductUseraccountService.save(orgPlFinanceProductUseraccount);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	//用户购买互融宝产品列表
	public String  transferToPlate(){
		try {
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			if(mem!=null){
				//检查是否具备购买资格
				Object[]  flag=plFinanceProductService.checkCondition(amount,productId,mem);
				if(flag[0].equals(Constants.CODE_SUCCESS)){//可以购买站岗资金产品
					Object[]  flagi=plFinanceProductUserAccountInfoService.buyRecord(amount,productId,mem,false);
					webMsgInstance("0", flagi[0].toString(), flagi[1].toString(),  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}else{
					//设置 返回提示消息
					webMsgInstance("0", flag[0].toString(), flag[1].toString(),  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "资金转入出错,请联系管理员",  "", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}
		return "freemarker";
	}
	
	//用户转出互融宝产品列表
	public String  transferFromPlate(){
		try {
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			if(mem!=null){
				Object[]  flag=plFinanceProductService.checkFromCondition(amount,productId,mem);
				if(flag[0].equals(Constants.CODE_SUCCESS)){//可以转出站岗资金产品金额
					Object[]  flagi=plFinanceProductUserAccountInfoService.transferFromPlate(amount,productId,mem);
					webMsgInstance("0", flagi[0].toString(), flagi[1].toString(),  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}else{
					//设置 返回提示消息
					webMsgInstance("0", flag[0].toString(), flag[1].toString(),  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}

			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "资金转出出错,请联系管理员",  "", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}
		return "freemarker";
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getProductId() {
		return productId;
	}
	
}
