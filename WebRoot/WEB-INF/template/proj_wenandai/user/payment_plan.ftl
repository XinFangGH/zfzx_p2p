<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 回款计划</title>
    <meta name="description" content="${systemConfig.metaTitle} - 回款计划,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 回款计划,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
</head>
<body >
   <div class="finlist" style="border:0;">
    	 <div class="content"style="margin:0; padding:0;">    
            <div class="tab">
            	<table width="100%" border="0" class="tab_css_one" align="center" cellpadding="0" cellspacing="0" style="border:1px solid #ddd;">
                       <tr>
                        <th width="10%" align="center">期数</th>
                        <th width="15%" align="center">计划本息金额</th>
                        <th width="15%" align="center">计划奖励金额</th>
                        <th width="15%" align="center">计划罚息/补偿息</th>
                        <th width="10%" align="center">计划到账日</th>
                        <th width="15%"  align="center">实际回款金额</th>
                        <th width="10%" align="center">实际到账日</th>
						<th width="10%"  align="center">回款状态</th>
                      </tr>
                      
                      
                      <#list fundIntentpay as fund>
							<tr style="height:35px;">
								<td>${fund.payintentPeriod}期</td>
								<td>¥<#if fund.payIntestPrinMoney==0>0.00<#else>${fund.payIntestPrinMoney}</#if></td>
								<td>¥<#if fund.couponMoney==0>0.00<#else>${fund.couponMoney}</#if></td>
								<td>¥<#if fund.compensationMoney==0>0.00<#else>${fund.compensationMoney}</#if></td>
								<td>${fund.intentDate?string('yyyy-MM-dd')}</td>
								<td>¥<#if fund.factPaymentsMoney==0>0.00<#else>${fund.factPaymentsMoney}</#if></td>
								<td><#if fund.factDate==null>-- --<#else>${fund.factDate}</#if></td>
								<td  style="color:#fd7754;"><#if fund.factPaymentsMoney==0>未回款<#else>已回款</#if></td>
							</tr>
					 </#list>  
                             
						</table>
            </div>  
         </div>
         </div>
</body>
</html>