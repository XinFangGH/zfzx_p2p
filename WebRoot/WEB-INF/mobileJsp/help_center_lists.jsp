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
            <div class="slideToggle_lists"><span>如何注册</span><span></span></div>
            <div class="slideToggle_show">
                <p>A、网页端：登录升升投官网，点击网页右上角【注册】，填写相关信息，输入手机号码，获取验证码即可注册成功。</p>
                <p>B、微信端：关注微信公众号【升升投】，点击下方【升升投】，登录升升投账户，点击【账户】-【免费注册】-进入注册页面输入手机号码获取验证码，按照步骤注册成功。</p>
                <p>注：18周岁以下用户不能注册。</p>
            </div>
        </li>
    </ul>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script>
    $(document).ready(function(){
        $(".slideToggle_lists").click(function(){
            $(this).next(".slideToggle_show").slideToggle();
        });
    });
</script>
</body>
</html>