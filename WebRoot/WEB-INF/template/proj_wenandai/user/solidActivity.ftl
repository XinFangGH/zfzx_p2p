<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>${systemConfig.metaTitle} - 优惠活动</title>
    <meta name="description" content="${systemConfig.metaTitle} - 优惠活动,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 优惠活动,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/detailsFunds.js"></script>

<script type="text/javascript" src="${base}/js/Calendar3.js"></script>

<script type="text/javascript">var m1="优惠活动",m2="",m3="";</script>
<script type="text/javascript">
$(function(){
	var listsize = $("#listsize").text();
	if(listsize=="0"){
		$("#list-one1").css("display","block");
		$("#list-one2").css("display","block");
		$("#list-one3").css("display","block");
	}else if(listsize=="1"){
		$("#list-one1").css("display","block");
		$("#list-one2").css("display","block");
	}else if(listsize=="2"){
		$("#list-one2").css("display","block");
	}
	
})
</script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div class="banner-img"></div>
<a href="${base}/htmlreg.do"><div class="activit-cont1"></div></a>
<a href="#"><div class="activit-cont2"></div></a>
<a href="#"><div class="activit-cont3"></div></a>
<!--end: main -->
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>