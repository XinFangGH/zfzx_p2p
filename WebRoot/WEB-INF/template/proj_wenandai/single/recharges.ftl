<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>${systemConfig.metaTitle} - 充值提现</title>
    <meta name="description" content="${systemConfig.metaTitle} - 充值提现,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 充值提现,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script src="${base}/js/user/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="充值提现";</script>
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
                <span  class="company_title_JJ">充值提现</span>
            </div>

            <ul class="loginreg_content"><#--.media-cont {padding: 20px 0;}-->
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >如何充值</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>为保证充值的正常进行，需开通富滇银行存管账户。</p>
                        <p>A、网页端：登录升升投网官网，点击【我的账户】-【账户总览】-【充值】选择充值方式和充值金额后，跳转到富滇银行资金存管页面，填写交易密码，按照页面提示输入信息即可完成充值。</p>
                        <p>B、微信端：登录升升投账户，点击【账户】-【充值/提现】-【充值】输入充值金额后，跳转到厦门银行资金存管页面，填写交易密码即可完成充值。</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >充值限额</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>升升投没有设置充值限额，充值时的单笔及当日转账上限为各个银行限制；</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >充值到账时间</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>A、正常情况下，充值即时到账。如果银行提示扣款之后账户余额没有相应增加，可能是存管系统延迟或者银行掉单导致：系统延迟会在10-30分钟后到账，到账后不影响正常使用；</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >充值失败</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>A、银行卡余额不足以支付充值金额;</p>
                        <p>B、交易密码输入有误;</p>
                        <p>C、银行卡出现挂失、注销或信息变更等状况;</p>
                        <p>D、联系客服，寻求帮助（服务热线：400-9266-114）。</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >如何提现</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>A、网页端：登录升升投官网，在【我的账户】-【提现】中输入您要提现的金额，在跳转的富滇银行资金存管页面中输入交易密码即可提现成功;</p>
                        <p>B、微信端：登录升升投账户，点击【账户】-【充值/提现】-【提现】中输入您要提现的金额，在跳转的富滇银行资金存管页面中输入交易密码即可提现成功；</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >提现手续费</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>提现时富滇银行收取单笔一元的提现手续费，升升投不收取任何提现费用</p>
                    </div>
                </li>

                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >提现限额</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>单笔最低提现2元，没有最高额度限制</p>
                    </div>
                </li>

                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >提现到账时间</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>成功提交提现申请后，存管银行进行受理，T+1个工作日到账；遇双休日、国家法定节假日，到账日期顺延;</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >提现未到账前是否能解绑银行卡</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>成功提交提现申请后，未到账之前都不可进行解卡操作;</p>
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