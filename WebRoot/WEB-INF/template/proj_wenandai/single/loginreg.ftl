<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>${systemConfig.metaTitle} - 登录注册</title>
    <meta name="description" content="${systemConfig.metaTitle} - 登录注册,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 登录注册,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script src="${base}/js/user/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="登录注册";</script>
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
        <div class="abouts-border" style="padding:30px 40px;min-height: 590px;">
            <div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">登录注册</span>
            </div>
            <ul class="loginreg_content"><#--.media-cont {padding: 20px 0;}-->
				<li >
					<div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >如何注册</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>A、网页端：登录升升投官网，点击网页右上角【注册】，填写相关信息，输入手机号码，获取验证码即可注册成功。</p>
                        <p>B、微信端：关注微信公众号【升升投】，点击下方【升升投】，登录升升投账户，点击【账户】-【免费注册】-进入注册页面输入手机号码获取验证码，按照步骤注册成功。</p>
                        <p>注：18周岁以下用户不能注册。</p>
                    </div>
                </li>
                <li >
					<div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >注册手机号要求</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>注册时的手机号码最好是需要绑定银行卡的银行预留手机号，否则在开通富滇银行存管账户前，需要先将注册手机号变更为银行预留手机号。</p>
                    </div>
                </li>
                <li >
					<div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >忘记密码怎么办</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>A、网页端：进入升升投官网首页点击【登录】-【忘记密码】填写手机号码和验证码，点击获取验证码，然后填写新密码即可修改成功。</p>
                        <p>B、微信端：点击【忘记密码】，填写手机号，验证码点击【获取验证码】-获取验证码填写新密码即可修改成功。</p>
                    </div>
                </li>
                <li >
					<div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >修改登录密码</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>登录升升投账户，点击【我的账户】-【账户设置】-【修改密码】，输入“当前密码”、“设置新密码“、“确认新密码”，完成修改。</p>
                    </div>
                </li>
                <li >
					<div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >注册时收不到验证码</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>A、请确认手机是否安装短信拦截或过滤软件；</p>
                        <p>B、请确认手机是否能够正常接收短信（信号问题、欠费、停机等）；</p>
                        <p>C、短信收发过程中可能会存在延迟，请耐心等待；</p>
                        <p>D、联系客服，寻求帮助（服务热线：400-9266-114）。</p>
                    </div>
                </li>
                <li >
					<div class="loginreg_lan" >
                        <span class="new-list fl  loginreg_itle" >修改注册手机号</span>
                        <span class="fr slide_div"></span>
                    </div>
                    <div class="loginreg_div">
                        <p>登录升升投账户，点击【我的账户】-【账户设置】-【修改手机号】，输入【图形验证码】，点击【获取验证码】，输入短信验证码，点击【确认】，输入需要变更的手机号码，输入【图形验证码】，点击【获取验证码】，输入短信验证码，点击【确认】,完成修改</p>
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