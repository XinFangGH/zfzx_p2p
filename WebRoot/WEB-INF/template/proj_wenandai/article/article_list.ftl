<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title>${systemConfig.metaTitle} - 文章列表</title>
    <meta name="description" content="${systemConfig.metaTitle} - 文章列表,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 文章列表,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

<script type="text/javascript">var m1="",m2="", m3="";</script>
<script type="text/javascript">
  //文章列表
</script>
</head>
<body >
<!-- header --><!-- navbar -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div class="main">
	<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
    
	<div class="ucleftdiv">
    	<!-- 以下内容为左侧三级导航条 -->
    		<div class="trinavbar">
				<ul >
				<li class="title">新闻类别</li>
				
				  <#list listArticleCat as list>
			       <li class="trititle">
			       <a href='${base}/article/listArticle.do?catId=${list.id}'><span>${list.name}</span></a>
			       </li>
			       </#list>
				</ul>
			</div>
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->
    <div class="ucvline"></div>
    <!-- 右侧主体内容 start  右侧内容宽740px -->
    <div class="ucrightdiv">
    	<!-- 我的个人信息 start -->
    	 <#list pager.list as list>
        	<div style="width:640px; height:25px; padding:10px 0px 0px 50px;">
            	<a href="${base}${list.htmlFilePath}"><span class="normal black">${list.title}</span></a> <span style ="float:right;width:80px" class="blue normal">${list.createDate}</span>
            </div>
            	<#if   (list_index+1)<10>
            <div class="dashedline"></div>
            </#if>
        	</#list>
        	<div style="width:600px;position:absolute;left:40%;top:560px; height:40px;">
                   <#import "/WEB-INF/template/common/pager.ftl" as p>
         			<#assign parameterMap = {"catId": (articleCategory.id)!"25"} />
         			<@p.pager pager = pager baseUrl = "/article/listArticle.do?catId=${articleCategory.id}"  parameterMap = parameterMap />
         			</div>
		    </div>
		    

		    <!-- 右侧主体内容结束 -->
	</div>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">	
	</body>
</html>