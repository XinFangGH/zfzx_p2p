<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>${systemConfig.metaTitle} - 绑定银行卡</title>
    <meta name="description" content="${systemConfig.metaTitle} - 绑定银行卡,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 绑定银行卡,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script src="${base}/js/user/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="绑定银行卡";</script>
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
                <span  class="company_title_JJ">绑定银行卡</span>
            </div>
            <ul class="loginreg_content"><#--.media-cont {padding: 20px 0;}-->
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >如何绑卡</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>新用户只需在注册过程中填写身份证号码、银行卡号、密码等信息即可快速绑定银行卡。银行卡需要是本人名下的有效期内的借记卡。</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >绑卡支持哪些银行</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>绑定银行卡页面有支持银行卡的列表</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >可以绑卡数量</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>升投目前只能绑定一张提现银行卡暂不支持多张银行卡绑定。</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >如何设置交易密码</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>在开通富滇银行存管账户时，会要求投资人设置交易密码，此密码将做为投资人充值、提现、投资等操作时的交易密码。</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >绑卡失败</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>A、根据页面提示的报错信息，对自己填写的姓名、身份证号码、银行卡号码、手机号（与银行卡预留手机号保持一致）进行校对，确认填写无误。</p>
                        <p>B、暂不支持您输入的银行卡，建议更换银行卡。</p>
                        <p>C、注册时手机号与银行卡预留手机号不一致（受富滇银行政策影响，升升投注册手机号必须与银行卡预留手机号保持一致），必须先修改手机号后再进行绑卡操作；</p>
                        <p>D、联系客服，寻求帮助（服务热线：400-9266-114）。</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >交易密码如何找回</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>若投资人需要修改或找回交易密码，需要进入富滇银行管理页面进行操作。</p>
                    </div>
                </li>


                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >如何解绑</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>A、网页端：点击【我的账户】-【账户总览】-【银行卡】-【解绑】，跳转到富滇银行解绑银行卡界面，输入【图像验证码】，输入【短信验证码】，【交易密码】，点击【确认】，完成解绑操作</p>
                       <#-- <p>B、微信端：</p>-->
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >绑卡解绑是否收取手续费</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>绑卡解绑操作升升投平台不收取任何费用</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >什么情况下会出现解绑失败</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>A、银行卡出现挂失、注销或信息变更等状况。</p>
                        <p>B、账户内存在未提现的余额；</p>
                        <p>C、账户内余额提现未到账时；</p>
                    </div>
                </li>
                <li >
                    <div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >账户有余额能否解绑银行卡</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>账户内有余额存在时不允许进行解绑银行卡操作，必须先将账户余额全部提取后才可进行解绑操作</p>
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