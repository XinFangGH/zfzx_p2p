<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>${systemConfig.metaTitle} - 平台操作说明</title>
    <meta name="description" content="${systemConfig.metaTitle} - 推荐浏览器,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 推荐浏览器,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script src="${base}/js/user/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="平台操作说明";</script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_common.css">
    <link rel="stylesheet" href="${base}/theme/proj_wenandai/css/swiper.min.css">

</head>
<body >
<!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">

<div class="main">
    <div class="abouts-nav fl">
        <!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_helpdesk.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->
    <div class="abouts-cont fr">
        <div class="abouts-border"  style="padding:30px 40px;min-height: 590px;">
            <div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">平台操作说明</span>
            </div>
            <ul class="loginreg_content">
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >平台操作说明：<a href="https://pan.baidu.com/s/1AmHl-1RCqSV9Is-pYihyvg" target="_blank"  style="color:#409af6;">https://pan.baidu.com/s/1AmHl-1RCqSV9Is-pYihyvg</a></span>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

</body>
</html>