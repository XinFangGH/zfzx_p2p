<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>${systemConfig.metaTitle} - 标的类</title>
    <meta name="description" content="${systemConfig.metaTitle} - 标的类,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 标的类,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script src="${base}/js/user/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="标的类";</script>
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
                <span  class="company_title_JJ">标的类</span>
            </div>

            <ul class="loginreg_content"><#--.media-cont {padding: 20px 0;}-->
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >标的发布时间</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>工作日固定发标时间在12:00、17:00，其余时间与周末随机发标;</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >投资成功后起息时间</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>A、若投资日标的未满标，则投资当日按照募集期利率开始计息；</p>
                        <p>B、若投资日为满标日，则投资当日按照年化利率开始计息；</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >募集期利息</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>升升投为保证用户资金利益最大化，从用户投资日到满标日期间按照募集期利率开始计息，直到满标放款后，开始按照年华收益率计息</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >募集期时间</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>成功投资日到满标放款日期间为募集期时间</p>
                    </div>
                </li>
               <#-- <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >如何设置自动投标</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p></p>
                    </div>
                </li>-->
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >如何查看收益</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>A、网页端：登录升升投官网，点击【我的账户】-【我的散标】-【回款中】的标的查看收益  ；</p>
                        <p>B、微信端：登录升升投账户，点击【账户】-【散标出借】-【回款中】的标的查看收益；</p>
                    </div>
                </li>
            </ul>
        </div>
    </div>


</div>




<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

<script>
    $(document).ready(function(){
        $(".loginreg_lan").click(function(){
            $(this).next(".loginreg_div").slideToggle();
        });
    });
</script>
</body>
</html>