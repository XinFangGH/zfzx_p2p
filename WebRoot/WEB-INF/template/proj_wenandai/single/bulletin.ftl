<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${systemConfig.metaTitle} - 网站公告</title>
    <meta name="description" content="${systemConfig.metaTitle} - 关于我们,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 关于我们,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="网站公告";</script>
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" />
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_common.css" />
</head>
<body >
<!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<style>
    .article-content {
        font-size: 14px;
        color: #6c6c6c;
        line-height: 40px;
        text-indent: 28px;
    }
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
        <div class="abouts-border" style="padding:30px 40px; min-height: 1060px;">
            <div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">网站公告</span>
            </div>
            <div class="operate_content">
                暂无数据
            </div>



        </div>
    </div>
</div>

<!--end: Container -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<script type="text/javascript" src="${base}/js/jquery.fancybox-1.3.1.pack.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" />
<!--<script type="text/javascript" src="${base}/js/wad_common.js"></script>-->
<script type="text/javascript">
    $(function () {
        function wad_gytupian() {
            $("a[rel=group]").fancybox({
                'titlePosition' : 'over',
                openEffect: 'elastic',
                'cyclic'        : true,
                'centerOnScroll': true,
                'titleFormat'   : function(title, currentArray, currentIndex, currentOpts) {
                    return '<span id="fancybox-title-over">' + (currentIndex + 1) +
                            ' / ' + currentArray.length + (title.length ? '   ' + title : '') + '</span>';
                },
                onStart: function () {
                    $('body').css('overflow','hidden');
                },
                onClosed: function () {
                    $('body').css('overflow','auto');
                },

            });
        }
        wad_gytupian();
    });
</script>

<script>
    $(".about-navbar").css("height","1060px");
</script>

</body>
</html>