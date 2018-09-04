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
                        <th width="20%" align="center" bgcolor="#47B2D6">债权名称</th>
                        <th width="10%" align="center" bgcolor="#47B2D6">匹配金额</th>
                        <th width="10%"  align="center" bgcolor="#47B2D6">日化利率</th>
                        <th width="20%"  align="center" bgcolor="#47B2D6">匹配开始日</th>
                        <th width="10%" align="center" bgcolor="#47B2D6">匹配结束日</th>
                        <th width="10%" align="center" bgcolor="#47B2D6">匹配收益</th>
                        
						
                      </tr>
	                     <#list orMacthingDetaillist as list>
	                        <tr style="height:35px;">
								<td>${list.parentOrBidName}期</td>
								<td>¥<#if list.matchingMoney==0>0.00<#else>${list.matchingMoney?string(',###.00')}</#if></td>
								<td>${list.childrenOrDayRate}</td>
								<td>${list.matchingStartDate}</td>
								<td>${list.matchingEndDate}</td>
								<td>¥<#if list.matchingGetMoney==0>0.00<#else>${list.matchingGetMoney?string(',###.00')}</#if></td>
								
							 </tr>   
                            </#list>          
					</table>
            </div>  
         </div>
         </div>
</body>
</html>