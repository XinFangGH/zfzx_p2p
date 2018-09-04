<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>${systemConfig.metaTitle} - ${helpTitle.name}</title>
    <meta name="description" content="${helpTitle.metaDescription}">
      <meta name="keywords" content="${helpTitle.metaKeywords}">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="${helpTitle.name}";</script>
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
    <!-- 左侧页面 end -->
    <div class="abouts-cont fr">
        <div class="abouts-border" style="padding:30px 40px;min-height: 633px;">
            <#--<div class="titie_h1">
                <span class="left_pic"></span><h1>${helpTitle}</h1><span class="right_pic"></span>
            </div>-->
	    	<#--<h1>${helpTitle}</h1>-->
                <div class="company_title">
                    <span class="company_title_span"></span>
                    <span  class="company_title_JJ">${helpTitle.name}</span>
                </div>

            <ul class="media-cont news_list">
			<#list pager.list as list>	
				<li class="media-list" >
					<span class="new-list fl" >
						<#--<em class="media-dian">●</em>-->
						<a href="${base}/article/${list.id}.html">${list.title}</a>
					</span>
                    <span class="fr">${list.createDate}</span>
                </li>
			</#list>
		<#import "/WEB-INF/template/common/pager.ftl" as p>
		<#assign parameterMap = {} />
		<@p.pager pager = pager baseUrl = "/article/newslistArticle.do?lid=${lid}" parameterMap = parameterMap />
            </ul>
        </div>
    </div>
</div>
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

<script>
    /*$(".about-navbar").css("height","1329px");*/
</script>
</body>
</html>