<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 还款计划</title>
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
                        <th width="15%" align="center">计划罚息/补偿息</th>
                        <th width="15%" align="center">实际回款金额</th>
                        <th width="10%" align="center">计划到账日</th>
                        <th width="10%" align="center">实际到账日</th>
						<th width="10%" align="center">回款状态</th>
                      </tr>
                      <#list LoanList as fund>
							<tr style="height:35px;">
								<td>${fund.payintentPeriod}期</td><!-- 和平台产生的综合服务费fund.comprehensiveMoney-->
								<td>¥<#if fund.intenttotal==0>0.00<#else>${fund.intenttotal}</#if></td>
								<td>¥<#if fund.compensationMoney==0>0.00<#else>${fund.compensationMoney}</#if></td>
								<td>¥<#if fund.backmoney==0><#if fund.loanerRepayMentStatus==1>${fund.repaymentTotal}<#else>0.00</#if><#else>${fund.backmoney}</#if></td>
								<td>${fund.intentDate?string('yyyy-MM-dd')}</td>
								<td><#if fund.factDate==null>-- --<#else>${fund.factDate}</#if></td>
								<td style="color:#fd7754;"><#if fund.repaymentTotal==0>已回款<#else><#if fund.loanerRepayMentStatus==1>已回款<#else><#if (fund.repaymentTotal<fund.intenttotal) &&(fund.repaymentTotal>0)>部分回款(剩余未回款金额需借款人偿还)</#if></#if></#if></td>
							</tr>
					</#list>          
				</table>
            </div>  
         </div>
         </div>
</body>
</html>