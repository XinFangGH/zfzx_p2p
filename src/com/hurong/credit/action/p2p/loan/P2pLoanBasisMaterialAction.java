package com.hurong.credit.action.p2p.loan;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.util.BeanUtil;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.web.action.BaseAction;


import com.hurong.credit.model.p2p.loan.P2pLoanBasisMaterial;
import com.hurong.credit.service.p2p.loan.P2pLoanBasisMaterialService;
/**
 * 
 * @author 
 *
 */
public class P2pLoanBasisMaterialAction extends BaseAction{
	@Resource
	private P2pLoanBasisMaterialService p2pLoanBasisMaterialService;
	private P2pLoanBasisMaterial p2pLoanBasisMaterial;
	
	private Long materialId;

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public P2pLoanBasisMaterial getP2pLoanBasisMaterial() {
		return p2pLoanBasisMaterial;
	}

	public void setP2pLoanBasisMaterial(P2pLoanBasisMaterial p2pLoanBasisMaterial) {
		this.p2pLoanBasisMaterial = p2pLoanBasisMaterial;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		QueryFilter filter=new QueryFilter(getRequest());
		String Q_materialState_L_EQ = this.getRequest().getParameter("Q_materialState");
		String Q_operationType_S_EQ = this.getRequest().getParameter("Q_aoperationType");
		if(Q_materialState_L_EQ!=null&&!Q_materialState_L_EQ.equals("")){
			filter.addFilter("Q_materialState_L_EQ", Q_materialState_L_EQ);
		}
		if(Q_operationType_S_EQ!=null&&!Q_operationType_S_EQ.equals("")){
			filter.addFilter("Q_operationType_S_EQ", Q_operationType_S_EQ);
		}
		filter.addFilter("Q_materialState_L_GT", "0");
		List<P2pLoanBasisMaterial> list= p2pLoanBasisMaterialService.getAll(filter);
		
		Type type=new TypeToken<List<P2pLoanBasisMaterial>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
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
		
		String ids=getRequest().getParameter("ids");
		if(ids!=null){
			String id[] = ids.split(",");
			for(int i=0;i<id.length;i++){
				P2pLoanBasisMaterial p2pLoanBasisMaterial = p2pLoanBasisMaterialService.get(new Long(id[i]));
				p2pLoanBasisMaterial.setMaterialState(Long.valueOf("0"));//假删除
				p2pLoanBasisMaterialService.save(p2pLoanBasisMaterial);
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
		P2pLoanBasisMaterial p2pLoanBasisMaterial=p2pLoanBasisMaterialService.get(materialId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(p2pLoanBasisMaterial));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(p2pLoanBasisMaterial.getMaterialId()==null){
			p2pLoanBasisMaterialService.save(p2pLoanBasisMaterial);
		}else{
			P2pLoanBasisMaterial orgP2pLoanBasisMaterial=p2pLoanBasisMaterialService.get(p2pLoanBasisMaterial.getMaterialId());
			try{
				BeanUtil.copyNotNullProperties(orgP2pLoanBasisMaterial, p2pLoanBasisMaterial);
				p2pLoanBasisMaterialService.save(orgP2pLoanBasisMaterial);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
}
