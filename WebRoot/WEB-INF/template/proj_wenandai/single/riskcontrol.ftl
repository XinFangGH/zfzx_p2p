<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 风险控制</title>
    <meta name="description" content="${systemConfig.metaTitle} - 风险控制,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 风险控制,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript">var m1="关于我们",m2="",m3="风险保证";</script>

</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">

<div style="width:100%; background:#EFF3F4;padding:20px 0;">
<div class="main">
	<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
	<div style="float:left; padding:0px 10px;">
    	<!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_aboutus.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->
	<div style="float:right;background:#fff;width:730px; padding:10px 30px; margin-top:0px; overflow:hidden;height:400px;">
		<h4 style="height:40px; line-height:40px; font-size:20px; font-weight:normal;color:#0096c4; border-bottom:1px solid #cecece;">风险保证</h4>
	
	</div>
</div>
</div>
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>