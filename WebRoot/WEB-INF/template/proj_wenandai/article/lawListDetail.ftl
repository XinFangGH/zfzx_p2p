<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 详情页</title>
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

<div class="main">
	<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
    <div class="abouts-nav fl">
        <!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_aboutus.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>

    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
		
	    <!-- 左侧页面 end -->
	<div class="abouts-cont fr">
	 	<div class="abouts-border">
	    	<div class="news-cont-title">
		      	<h4 class="blue middle"> 
		      	<a href="#">法律法规</a> > <a href="${base}/article/lawListArticle.do?lid=${articleCategory.id}">${articleCategory.name}</a>
		      	</h4>
		    </div>
		 	<div class="news-cont">
		    	<div class="wad_news_cont_header">
			    	<h2 class="big">${article.title}</h2>
			    	<p class="normal wad_normal">时间：${article.createDate} </p>
		    	</div>
		    	<p class="normal" style="padding:10px 20px 10px 0; line-height:30px;">${article.content}</p>
		    	<p style="text-align:center;"></p><br/>
		    	<p class="normal" style="padding:0 20px; line-height:30px;"></p><br/>
		    </div>
	    </div>
	</div>
</div>
</div>
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>