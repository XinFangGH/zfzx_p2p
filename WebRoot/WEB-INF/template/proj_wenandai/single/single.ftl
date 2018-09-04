<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign article=article?eval>
<#assign articleCat=articleCat?eval>
<#assign articleList=newArticleListByCat?eval>
<head>
<title>${systemConfig.metaTitle} - ${article.title}</title>
    <meta name="description" content="${systemConfig.metaTitle} - ${article.title},专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - ${article.title},投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

<script type="text/javascript">var m1="${articleCat.name}",m2="", m3="${article.title}";
 //文章列表
<#include "/WEB-INF/template/${systemConfig.theme}/single/article_cat.ftl">
</script>
</head>
<body >
<!-- header --><!-- navbar -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div class="main">
	<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
	<div style="float:left;width:229px; padding:0px 10px;">
    	<!-- 以下内容为左侧三级导航条 -->
    		<div class="trinavbar">
				<ul id="articCat">
				<div id="catload"></div>
			       
				</ul>
			</div>
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->
    <!-- 右侧主体内容 start  右侧内容宽740px -->
    <div style="float:left; width:740px; margin:10px 0px 0px 0px; min-height:600px; height:auto !important; height:600px; overflow:visible; border-left:1px solid #e0e0e0; ">
    	<!-- 我的个人信息 start -->
        	<div style="width:640px; height:40px; padding:10px 0px 0px 40px;">
            	<img src="${base}/theme/${systemConfig.theme}/images/spicon1.jpg" style="vertical-align:middle; margin:0px 20px 7px 0px;" /><span class="blue large">${article.title}</span>
            </div>
            <div class="dashedline"></div>
        		<div style="width:650px; min-height:500px; height:auto !important; height:500px; overflow:visible; padding:20px 20px 20px 50px; " class="black normal">
	            	<!--此处放置内容-->					
	            	${content}
		        </div>
		    </div>
	<!-- 右侧主体内容结束 -->
	</div>
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
 
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl">	
	</body>
</html>