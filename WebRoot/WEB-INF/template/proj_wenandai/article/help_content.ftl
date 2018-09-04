<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#list listArticle as list>
       <title>${list.title}_${articleCategory.name}_${systemConfig.metaTitle}</title>
    <meta name="description" content="${list.title}_${articleCategory.name}_${systemConfig.metaTitle},专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${list.title}_${articleCategory.name}_${systemConfig.metaTitle}">
	</#list>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
<script type="text/javascript">var m1="关于我们",m2="",m3="${articleCategory.name}";</script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_common.css">
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<div class="main wad_main">
	<div class="abouts-nav fl">
    	<!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_helpdesk.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <div class="abouts-cont fr">
    <div class="abouts-border">
		<div class="news-cont-title">
		      	<h4 class="blue middle"> 
		      	<a href="#">帮助中心</a> > <a href="${base}/article/helplistArticle.do?lid=${articleCategory.id}">${articleCategory.name}</a>
		      	</h4>
		 </div>
		 <div class="news-cont">
		    <#list listArticle as list>	
		    	<div class="wad_news_cont_header">
			    	<h2 class="big">${list.title}</h2>
			    	<input type="hidden" id="title" value="${list.title}">
			    	<p class="normal wad_normal">时间：${list.createDate} </p>
		    	</div>
		    	<p class="normal" style="padding:10px 20px 10px 0; line-height:30px;">${list.content}</p>
		    	<p style="text-align:center;"></p><br/>
		    	<p class="normal" style="padding:0 20px; line-height:30px;"></p><br/>
		   </#list>
		  </div>
	</div>
	</div>
</div>
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	</body>
</html>