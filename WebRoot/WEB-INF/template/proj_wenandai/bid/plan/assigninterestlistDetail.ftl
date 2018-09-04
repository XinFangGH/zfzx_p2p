<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 债权匹配详情</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
</head>
<body >
   <div class="finlist" style="border:0;">
    	 <div class="content"style="margin:0; padding:0;">    
            <div class="tab">
            	<table width="100%" border="0" class="tab_css_plan" align="center" cellpadding="0" cellspacing="0">
                       <tr>
                          <th width="10%" align="center" bgcolor="#47B2D6">期数</th>
                            <th width="20%" align="center" bgcolor="#47B2D6">类型</th>
                        <th width="20%" align="center" bgcolor="#47B2D6">计划回款金额</th>
                        <th width="20%"  align="center" bgcolor="#47B2D6">实际回款金额</th>
                        <th width="10%" align="center" bgcolor="#47B2D6">计划到账日</th>
                        <th width="10%" align="center" bgcolor="#47B2D6">实际到账日</th>
                        
						
                      </tr>
	                     <#list assigninterestlist as list>
	                        <tr style="height:35px;">
								<td>第${list.periods}期</td>
								<td><#if list.fundType=="principalRepayment">本金
								    <#elseif list.fundType=="loanInterest">利息 
								    <#elseif list.fundType=="liquidatedDamages">提前赎回违约金
								    <#elseif list.fundType=="riskRate">风险保证金
								    <#elseif list.fundType=="couponInterest">优惠券利息奖励
								    <#elseif list.fundType=="principalCoupons">优惠券返现奖励
								    <#elseif list.fundType=="subjoinInterest">加息券加息奖励
								    <#elseif list.fundType=="commoninterest">普通加息
								    <#elseif list.fundType=="raiseinterest">募集期奖励
								    </#if>
								</td>
								<td>¥<#if list.incomeMoney==0>-<#if list.payMoney==0>0.00<#else><#if list.payMoney lt 1000>${list.payMoney}<#else>${list.payMoney?string(',###.00')}</#if></#if>
								<#else><#if list.incomeMoney==0>0.00<#else><#if list.incomeMoney lt 1000>${list.incomeMoney}<#else>${list.incomeMoney?string(',###.00')}</#if></#if></#if></td>
								<td>¥<#if list.incomeMoney==0>-</#if><#if list.afterMoney==0>0.00<#else><#if list.afterMoney lt 1000>${list.afterMoney}<#else>${list.afterMoney?string(',###.00')}</#if></#if></td>
								<td>${list.intentDate}</td>
								<td>${list.factDate}</td>
								
							 </tr>   
                            </#list>          
					</table>
            </div>  
         </div>
         </div>
</body>
</html>