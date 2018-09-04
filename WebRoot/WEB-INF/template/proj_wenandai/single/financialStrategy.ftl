<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>理财攻略_理财小知识_互金理财技巧_升升投</title>
    <meta name="description" content="升升投理财攻略频道主要提供互联网金融行业理财攻略,理财小知识,理财技巧,银行理财等干货内容,为投资人提供参考学习.">
    <meta name="keywords" content="理财攻略,理财小知识,互金理财技巧">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="理财攻略";</script>
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" />
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_common.css" />
</head>
<body >
<!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<style>


</style>

<div class="main">
    <!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
    <div class="abouts-nav fl">
        <!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_aboutus.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->

    <div class="abouts-cont fr finacialS">
        <img class="banner" src="${base}/theme/proj_wenandai/images/mypic/info_banner1.jpg" alt="">
        <div class="abouts-border" style="padding:30px 40px;min-height: 930px;">
            <div class="con">
                <#list  listParent as parent>
                    <div class="company_title">
                        <span class="company_title_span"></span>
                        <span class="company_title_JJ">${parent.name}</span>
                    </div>
                    <ul class="lists">

                    <#list articleList as list >
                        <#if  list.articleCategory.parentId == parent.id>
                            <li>
                                    <a href="${base}/gl/${list.id}.html"><span class="pl">${list.title} </span></a>
                                    <span class="pr">${list.createDate}</span>
                            </li>
                        </#if>
                    </#list>

                    </ul>
                </#list>
            </div>
        </div>
    </div>
</div>


<!--end: Container -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
    <script type="text/javascript" src="${base}/js/jquery.fancybox-1.3.1.pack.js"></script>
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" />
    <script type="text/javascript" src="${base}/js/page/jquery.page.js"></script>
    <!--<script type="text/javascript" src="${base}/js/wad_common.js"></script>-->
<script>
    $(function(){
        if($('.current').find('a').text()=="理财攻略"){
            $('.current .user-arrow').css("transform","rotate(90deg)");
            $('.current').siblings().each(function(){
                if($(this).attr('data-i')=="hj"){
                    $(this).show();
                }
            });
        }
    });
</script>
</body>
</html>