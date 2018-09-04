<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>${systemConfig.metaTitle} - 公司资质</title>
    <meta name="description" content="${systemConfig.metaTitle} - 公司资质,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 公司资质,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="团队介绍";</script>

</head>
<style type="text/css">
    .article-content {
        color: #6c6c6c;
        font-size: 14px;
        line-height: 40px;
        text-indent: 28px;
    }
    strong, b {
        font-weight: normal;
    }
</style>
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
        <div class="abouts-border">
            <div class="titie_h1">
                <span class="left_pic"></span><h1>获得证书</h1><span class="right_pic"></span>
            </div>

            <div class="article" style="margin-top:20px;">
                <div class="Qualification">
                    <img style="width: 324px;height: 236px;" src="${base}/theme/${systemConfig.theme}/images/Qualification/Zhengshu01.jpg">
                    <img style="width: 324px;height: 236px;" src="${base}/theme/${systemConfig.theme}/images/Qualification/Zhengshu02.jpg">
                    <img style="margin-left: 56px;" src="${base}/theme/${systemConfig.theme}/images/Qualification/01.jpg">
                    <img style="margin-left:70px;" src="${base}/theme/${systemConfig.theme}/images/Qualification/02.jpg">
                    <img style="margin-left: 56px;"  src="${base}/theme/${systemConfig.theme}/images/Qualification/03.jpg">
                    <img style="margin-left:70px;" src="${base}/theme/${systemConfig.theme}/images/Qualification/04.jpg">
                    <img style="margin-left: 56px;"  src="${base}/theme/${systemConfig.theme}/images/Qualification/05.jpg">
                    <img style="margin-left:70px;" src="${base}/theme/${systemConfig.theme}/images/Qualification/06.jpg">
                    <img style="margin-left: 56px;"  src="${base}/theme/${systemConfig.theme}/images/Qualification/07.jpg">
                    <img style="margin-left:70px;" src="${base}/theme/${systemConfig.theme}/images/Qualification/08.png">
                    <img  style="width: 328px;height: 236px;" src="${base}/theme/${systemConfig.theme}/images/Qualification/09.jpg">
                    <img style=" width: 328px;height: 236px;"  src="${base}/theme/${systemConfig.theme}/images/Qualification/10.jpg">
                </div>
            </div>
        </div>
    </div>
</div>
<!--end: Container -->
</body>
</html>