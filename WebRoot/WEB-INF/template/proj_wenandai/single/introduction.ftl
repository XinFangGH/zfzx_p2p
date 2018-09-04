<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<title>${systemConfig.metaTitle} - 主页</title>
    <meta name="description" content="${systemConfig.metaTitle} - 主页,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 主页,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>

<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
<style type="text/css"> 
	.navul{
		list-style:none;
	}
	.navli{
		float:left;
		margin-left:20px;	
	}
	.nvaSpan:hover{
		color:#1bb8e2;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		//$("#nav_body").load("${base}/userguide/index.html",function(){return false});
		$("li").click(function() {
			$(this).prevAll("li").children("span").css("color","#6C6C6C");
			$(this).nextAll("li").children("span").css("color","#6C6C6C");
        	$(this).children("span").css("color","#1bb8e2");
        	$("#nav_div").css("margin-left",$(this).attr("name")+50+"px");
			$("#nav_body").load("/hurong_p2p_v3.5.1"+$(this).attr("url"),function(){return false});
   		 });
	});
</script>
</head>
<body>
  <!-- topbar -->
  <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div style="margin: auto;width: 100%; height: 100%;background-color:#F3F2F1;">
<div style="width:100%;height: 20px;"></div>
<div style="border:none; margin:auto; padding:0px; width:800px; height:30px;">
	<ul class="navul">
		<#list articleList as article>
			<#if article_index==0><li class="navli" name="${article_index}" url="${article.htmlFilePath}" style="cursor: pointer; margin-left: -50px;color:#1bb8e2;" id="wytz"><span style="display:block; font-size: 20px;" class="nvaSpan">${article.title}</span></li>
			<#else>
			<li class="navli" style="cursor: pointer;"name="${article_index}" url="${article.htmlFilePath}" id="wyjk"><span style="display:block;font-size: 20px;" class="nvaSpan">${article.title}</span></li>
			</#if>
		</#list>
    </ul>
</div>
<div style="width:1000px;height:5px; background-color:#999; margin:auto; margin-top: 10px;">	
	<div style="width:80px;height:5px; background-color:#1bb8e2; margin-left:50px;" id="nav_div">&nbsp;</div>
</div>
<div id="nav_body" style="height:auto; text-align:center; background-color:#F3F2F1;"></div>
<#list articleList as article>
	${article.title}
</#list>
</div>
	
 <!-- partner-pic --><!-- partner-text --><!-- sitemap --><!-- copyright --><!-- permit -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
 
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl">
</body>
</html>
