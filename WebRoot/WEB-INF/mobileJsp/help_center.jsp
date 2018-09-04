<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 帮助中心</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/help_center.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/message_center.css"/>
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
        帮助中心
    </div>
</div>
<div class="container ">
    <ul class="help_center">
        <li><a href="javaScript:void(0);" onclick=location.href=${base}'/webPhone/help_center_1homePage.do'>登录注册<span></span></a></li>
        <li><a href="javaScript:void(0);" onclick=location.href=${base}'/webPhone/help_center_2homePage.do'>绑定银行卡<span></span></a></li>
        <li><a href="javaScript:void(0);" onclick=location.href=${base}'/webPhone/help_center_3homePage.do'>充值提现<span></span></a></li>
        <li><a href="javaScript:void(0);" onclick=location.href=${base}'/webPhone/help_center_4homePage.do'>标的类<span></span></a></li>
        <li><a href="javaScript:void(0);" onclick=location.href=${base}'/webPhone/help_center_5homePage.do'>资金存管<span></span></a></li>
    </ul>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
</body>
</html>