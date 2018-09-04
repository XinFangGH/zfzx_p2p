<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${systemConfig.metaTitle} - 承诺函</title>
    <meta name="description" content="${systemConfig.metaTitle} - 关于我们,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 关于我们,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="承诺函";</script>
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

    <div class="abouts-cont fr" >
        <div class="abouts-border" style="padding:30px 40px;min-height: 930px;">
            <img src="${base}/theme/${systemConfig.theme}/images/mypic/cnh.jpg" style="width:100%;" alt="">
        </div>
    </div>
</div>

<!--end: Container -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<script type="text/javascript" src="${base}/js/jquery.fancybox-1.3.1.pack.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" />
<script type="text/javascript" src="${base}/js/page/jquery.page.js"></script>
<!--<script type="text/javascript" src="${base}/js/wad_common.js"></script>-->

</body>
</html>