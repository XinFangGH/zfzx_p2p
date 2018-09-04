<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${systemConfig.metaTitle} - 重大风险信息</title>
    <meta name="description" content="${systemConfig.metaTitle} - 关于我们,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 关于我们,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="重大风险信息";</script>
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
        <div class="abouts-border" style="padding:30px 40px; min-height: 633px;">
            <div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">重大风险信息</span>
            </div>
            <div class="risk_content">
                <table>
                    <thead>
                    <tr>
                        <th class="title_one">重大事项</th><th>披露</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr><td  class="title_one">合并、分立、解散或者申请破产</td><td   class="title_two">无</td></tr>
                    <tr><td  class="title_one">从业机构受到刑事处罚</td><td class="title_two">无</td></tr>
                    <tr><td  class="title_one">公司被责令停业、整顿、关闭</td><td class="title_two">无</td></tr>
                    <tr><td  class="title_one">重大诉讼或者仲裁事项</td><td class="title_two">无</td></tr>
                    <tr><td  class="title_one">实际控制人与持股 5%以上的股东、董事、监事、高级管理人员涉及的重大诉讼、仲裁事项或重大行政处罚</td><td class="title_two">无</td></tr>
                    <tr><td  class="title_one">不适用公司主要或者全部业务陷入停顿</td><td class="title_two">无</td></tr>
                    <tr><td  class="title_one">存在欺诈、损害出借人利益等其他影响网络借贷信息中介机构经营活动的重大事项</td><td class="title_two">无</td></tr>
                    <tr><td  class="title_one">公司涉及重大诉讼、仲裁，或涉嫌违法违规被有权机关调查，或受到刑事处罚、重大行政处罚</td><td class="title_two">无</td></tr>
                    </tbody>
                </table>

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
    /*$(".about-navbar").css("height","535px");*/
</script>

</body>
</html>