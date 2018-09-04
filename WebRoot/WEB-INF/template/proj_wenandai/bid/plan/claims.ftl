<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 债权清单</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/detailsFunds.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript">var m1="个人中心",m2="我的理财",m3="债权清单";</script>
</head>
<body >
<!-- header --><!-- navbar -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<!-- 页面分为左右两个部分   -->
    <div class="main">
    	<div class="user-cont">
    		<div class="user-name-nav fl">
				<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
			</div>
    	<!-- 我的个人信息 start -->
    	<div class="user-cont-fr fr">
			<div class="user-cont-right">
			<h3 class="zqqd-tit">
				<span>${plManageMoneyPlanBuyinfo.mmName}</span>
				<a href="${base}/creditFlow/financingAgency/managelist1PlManageMoneyPlanBuyinfo.do?tab='holding'"><em class="back"></em>返回</a>
			</h3>
        	<div class="hurong-list" style="padding:0;">
	            <div class="tab-list">
	           	 	<div class="ul-box">
	           	 		<ul>
		           	 		<li>
		           	 			<span>加入金额  </span>
		           	 			<span><em class="cor-org">${plManageMoneyPlanBuyinfo.buyMoney}</em>元</span>
		           	 		</li>
		           	 		<li><span>预期年化收益  </span><span><em class="cor-org">${plManageMoneyPlanBuyinfo.promisYearRate}</em>%</span></li>
		           	 		<li><span>预期收益 </span><span><em class="cor-org">${plManageMoneyPlanBuyinfo.promisIncomeSum}</em>元</span></li>
		           	 		<li><span>收益处理方式  </span><span><em class="cor-org">${plManageMoneyPlanBuyinfo.investTypeName}</em></span></li>
		           	 		<li><span>到期日期</span><span><em class="cor-org">${plManageMoneyPlanBuyinfo.endinInterestTime}</em></span></li>
		           	 		<li style="clear:both;"><span>加入日期</span><span><em class="cor-org">${plManageMoneyPlanBuyinfo.buyDatetime}</em></span></li>
		           	 		<li><span>起息日期</span><span><em class="cor-org">${plManageMoneyPlanBuyinfo.startinInterestTime}</em></span></li>
		           	 		<li><span>退出日期</span><span><em class="cor-org">${plManageMoneyPlanBuyinfo.earlierOutDate}</em></span></li>
		           	 	</ul>
	           	 	</div>
	                <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
	                    <tr>
	                      <th align="center">债权名称</th>
	                      <th align="center">投资金额</th>
	                      <th align="center">状态</th>
	                      <th align="center">投资时间</th>	                     
	                    </tr>
	                    <#list newClaimsList as list>
		                    <tr>
		                      <td align="center">
		                      <#if plManageMoneyPlanBuyinfo.keystr=='UPlan'>
		                        <a style="color:blue" target="_blank" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.parentOrBidId}" title="${list.parentOrBidName}"><#if list.parentOrBidName?length gt 15>${list.parentOrBidName?substring(0,15)}<#else>${list.parentOrBidName}</#if></a></a>
		                      <#else>
		                        <a style="color:blue" target="_blank" href="${base}/creditFlow/financingAgency/getlineChildrenPlBidPlan.do?bidId=${list.parentOrBidId}" title="${list.parentOrBidName}"><#if list.parentOrBidName?length gt 15>${list.parentOrBidName?substring(0,15)}<#else>${list.parentOrBidName}</#if></a></a>
		                      </#if>
		                      </td>
							  <td align="center">¥${list.matchingMoney}</td>
		                      <td align="center">还款中</td>
		                      <td align="center">${list.matchingStartDate}</td>
		                    </tr>
	                    </#list>
	              	</table>
	            </div>
            </div>
		</div>
	</div>
	</div>
</div>
	<!--end: main -->
	<!--end: Container -->
    <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	</body>
</html>