<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 -  登录注册</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/help_center.css"/>
    <style>
        html {
            font-size: 100px;
        }

        .container {
            width: 100%;
            background: #FFFFFF;
            margin-top: 0.88rem;
        }

    </style>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
         登录注册
    </div>
</div>
<div class="container ">
    <ul class="help_center_list">
        <li>
            <div class="slideToggle_lists"><span>如何注册</span><span></span></div>
            <div class="slideToggle_show">
                <p>A、网页端：登录升升投官网，点击网页右上角【注册】，填写相关信息，输入手机号码，获取验证码即可注册成功。</p>
                <p>B、微信端：关注微信公众号【升升投】，点击下方【升升投】，登录升升投账户，点击【账户】-【免费注册】-进入注册页面输入手机号码获取验证码，按照步骤注册成功。</p>
                <p>注：18周岁以下用户不能注册。</p>
            </div>
        </li>
        <li>
            <div class="slideToggle_lists"><span>注册手机号要求</span><span></span></div>
            <div class="slideToggle_show">
                <p>注册时的手机号码最好是需要绑定银行卡的银行预留手机号，否则在开通富滇银行存管账户前，需要先将注册手机号变更为银行预留手机号。</p>
            </div>
        </li>
        <li>
            <div class="slideToggle_lists"><span>忘记密码怎么办</span><span></span></div>
            <div class="slideToggle_show">
                <p>A、网页端：进入升升投官网首页点击【登录】-【忘记密码】填写手机号码和验证码，点击获取验证码，然后填写新密码即可修改成功。</p>
                <p>B、微信端：点击【忘记密码】，填写手机号，验证码点击【获取验证码】-获取验证码填写新密码即可修改成功。</p>
            </div>
        </li>
        <li>
            <div class="slideToggle_lists"><span>修改登录密码</span><span></span></div>
            <div class="slideToggle_show">
                <p>登录升升投账户，点击【我的账户】-【账户设置】-【修改密码】，输入“当前密码”、“设置新密码“、“确认新密码”，完成修改。</p>
            </div>
        </li>
        <li>
            <div class="slideToggle_lists"><span>注册时收不到验证码</span><span></span></div>
            <div class="slideToggle_show">
                <p>A、请确认手机是否安装短信拦截或过滤软件；</p>
                <p>B、请确认手机是否能够正常接收短信（信号问题、欠费、停机等）；</p>
                <p>C、短信收发过程中可能会存在延迟，请耐心等待；</p>
                <p>D、联系客服，寻求帮助（服务热线：400-9266-114）。</p>
            </div>
        </li>
        <li>
            <div class="slideToggle_lists"><span>修改注册手机号</span><span></span></div>
            <div class="slideToggle_show">
                <p>登录升升投账户，点击【我的账户】-【账户设置】-【修改手机号】，输入【图形验证码】，点击【获取验证码】，输入短信验证码，点击【确认】，输入需要变更的手机号码，输入【图形验证码】，点击【获取验证码】，输入短信验证码，点击【确认】,完成修改</p>
            </div>
        </li>
    </ul>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script>
    $(document).ready(function(){
        $(".slideToggle_lists").click(function(){/*slideToggle();*/
            $(this).next(".slideToggle_show").slideDown("slow");
            $(this).parent("li").siblings("li").find(".slideToggle_show").slideUp()

        });
    });
</script>
</body>
</html>