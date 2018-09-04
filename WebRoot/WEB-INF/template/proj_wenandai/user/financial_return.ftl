<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 理财回款</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript">var m1="个人中心",m2="理财回款",m3="理财回款";</script>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>

<style type="text/css">
dd {
display: block;
-webkit-margin-start: 40px;
}


dl {
display: block;
-webkit-margin-before: 1em;
-webkit-margin-after: 1em;
-webkit-margin-start: 0px;
-webkit-margin-end: 0px;
}

.query-btn {
margin-left: 10px;
color: #fff!important;
padding: 6px;
cursor: pointer;
border-radius: 3px;
text-decoration: none;
background: #e74649;
background: -moz-linear-gradient(top, #e74649 0%, #e03336 100%);
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#e74649), color-stop(100%,#e03336));
background: -webkit-linear-gradient(top, #e74649 0%,#e03336 100%);
background: -o-linear-gradient(top, #e74649 0%,#e03336 100%);
background: -ms-linear-gradient(top, #e74649 0%,#e03336 100%);
background: linear-gradient(to bottom, #e74649 0%,#e03336 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e74649', endColorstr='#e03336',GradientType=0 );
}


</style>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">


<div class="main-box">
<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
    	<!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
        <!-- 左侧三级导航结束 end-->
 
    <!-- 左侧页面 end -->
       <!-- <div style="float:left; height:700px; padding:10px 0px; width:1px; border:0px; background-color:#e0e0e0;"></div>-->
    <!-- 右侧主体内容 start  右侧内容宽740px -->
    <div class="main-cont">
    	<!-- 我的个人信息 start -->
        	<div class="content">
        		<div style=" height:40px; line-height:40px;margin-bottom:20px; ">
            		<span style="font-size:22px;color: #0096c4;">理财回款</span>
            	</div>

            <div class="tab-list">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <th class="top-line left-line" align="center" width="25%" >产品名称</th>
                      <th class="top-line" align="center" width="15%">还款进度（期）</th>
                      <th class="top-line" align="center"  width="15%">应付本金（元）</th>
                      <th class="top-line" align="center"  width="15%">应付利息（元）</th>
                      <th class="top-line" align="center">应付罚息（元）</th>
                      <th class="top-line right-line" align="center" width="15%">下期还款日</th>
                    </tr>
                     <#list bidPlanFinancial as list>
                     <#if list.allCount!=null>
                    <tr>
                      <td align="left" class="td-top">
                          <span class="tit"><a style="color:#0096c4;" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}">${list.bidProName!}</a></span>
                          <span class="small">利率${(list.interestRate)!}%   期限  ${(list.loanLife)!}</span>
                      </td>
                      <td align="center">${(list.nowCount)!}/${(list.allCount)!}</td>
                      <td align="center"> <#if list.financialAfterMoney==null>0.00<#else><#if list.financialAfterMoney lt 1000>${list.financialAfterMoney} <#else>${list.financialAfterMoney?string(',###.00')}</#if></#if></td>
                      <td align="center"> <#if list.financialNotMoney==0>0.00<#else> <#if list.financialNotMoney lt 1000>${list.financialNotMoney}<#else>${list.financialNotMoney?string(',###.00')}</#if></#if></td>
                      <td align="center"> <#if list.financialPayMoney==0>0.00<#else> <#if list.financialPayMoney lt 1000>${list.financialPayMoney}<#else>${list.financialPayMoney?string(',###.00')}</#if></#if></td>
                      <td align="center"><#if list.fullTime==null>-- --<#else>${list.fullTime}</#if></td>
                    </tr>
                    </#if>
                    </#list>
                   
              	</table>
            </div>
            
            </div>
           
				
			</div>

<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>