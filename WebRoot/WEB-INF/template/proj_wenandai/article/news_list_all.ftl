<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 主页面</title>
    <meta name="description" content="${systemConfig.metaTitle} - 主页面,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 主页面,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript">var m1="帮助中心",m2="",m3="${helpTitle}";</script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">


<div style="width:100%; background:#EFF3F4;padding:20px 0;">
<div class="main">
	<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
	<div style="float:left; padding:0px 10px; ">
    	<!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_helpdesk.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
   <!-- 左侧页面 end -->
		 <div class="contact" style="background:#fff; margin:0px 0 20px 0px; width:720px; min-height:580px;  padding:10px 20px; float:left;">
		 <h4 style="height:40px; line-height:40px; font-size:20px; font-weight:normal;color:#0096c4; border-bottom:1px solid #cecece;">${helpTitle}</h4>
			
			<#list pager.list as list>	
			<div class="company wad_company" >
				<p class="company_l"><img style="width:20px;" src="${base}/theme/${systemConfig.theme}/images/company_pic.png"  /><span><a href="${base}/article/newscontentArticle.do?catId=${list.id}">${list.title}</a></span></p>
				<p class="company_r">${list.createDate}</p>										
			</div>
			</#list>
			
		</div>
</div>
</div>
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>