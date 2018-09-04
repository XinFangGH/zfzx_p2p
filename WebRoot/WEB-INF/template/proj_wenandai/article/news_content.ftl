<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <#list listArticle as list>
       <title>${list.title}_${articleCategory.name}_${systemConfig.metaTitle}</title>
    <meta name="description" content="${list.metaDescription}">
    <meta name="keywords" content="${list.metaKeywords}">
   </#list>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
<script type="text/javascript">var m1="关于我们",m2="",m3="${articleCategory.name}";</script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<div class="main">
	<div class="abouts-nav fl">
    <!-- 以下内容为左侧三级导航条 -->
	<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_aboutus.ftl">
    <!-- 左侧三级导航结束 end-->
    </div>
	<div class="abouts-cont fr">
	<div class="abouts-border">
	    <#--<div class="news-cont-title">
			<h4 class="blue middle"> 
		      	<a href="#">关于我们</a>&nbsp;&nbsp;<i class="news_detail_icon"></i>&nbsp;&nbsp;<a href="${base}/article/newslistArticle.do?lid=${articleCategory.id}">${articleCategory.name}</a>
		    </h4>
		</div>-->
        <div class="company_title">
            <span class="company_title_span"></span>
            <span  class="company_title_JJ">${articleCategory.name}</span>
        </div>

		<div class="news-cont">
		    <#list listArticle as list>	
		    <div class="wad_news_cont_header" style="border-bottom: 1px solid #e5e5e5;">
				<h2 class="big">${list.title}</h2>
			    <input type="hidden" id="title" value="${list.title}">
			    <p class="normal wad_normal">时间：${list.createDate} </p>
                <hr style="border:0.03px solid #808080">
		    </div>
			<p class="normal" >${list.content}</p><#--style="padding:10px 20px 10px 0; line-height:30px;"-->
			<#-- <p style="display:block;height20px;text-align:center;"></p><br/>-->
		    <#--<p class="normal" style="padding:0 20px; line-height:30px;"></p><br/>-->
		   </#list>
		</div>
	</div>
	</div>
</div>
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

 <script>
	 var right_height = $(".abouts-cont").height();
	 console.log(right_height);
	 $(".leftsidebar").css("height",right_height);


	/* alert(${list.title});*/

 </script>
	</body>
</html>