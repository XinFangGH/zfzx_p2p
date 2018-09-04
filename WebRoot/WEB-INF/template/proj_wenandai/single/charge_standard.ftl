<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 收费标准</title>
    <meta name="description" content="${systemConfig.metaTitle} - 收费标准,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 收费标准,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>

<script type="text/javascript" src="${base}/js/Calendar3.js"></script>

</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">



<div class="main">
	<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
	<div style="float:left; padding:0px 10px;">
    	<!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_helpdesk.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->
	<div style="float:right; padding:0 20px; width:710px;">
		<h2 class="huge" style="font-weight:500; color:#0697da;">收费标准</h2>
		<p class="middle gray"><span style="color:#0697da;">出借人：</span>${systemConfig.metaTitle}平台的出借人享有注册免费，充值免费，提现免费的专属权利，但公司会收取出借人投资收益的10%作为账户管理费，此笔费用将作为平台所有业务的共同风险保证金累积，正可谓是“我为人人，人人为我”。</p>
		<p class="middle gray"><span style="color:#0697da;">借款人：</span>${systemConfig.metaTitle}平台的借款人根据所选借款产品的不同，需承担相应的出借人资金收益，平台借款融资咨询费及风险保证金等费用，平台将从借款人角度出发为借款人打造包含所有费用的一揽子融资计划，融资成功之前不收取任何费用。</p>			
	</div>
</div>
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>