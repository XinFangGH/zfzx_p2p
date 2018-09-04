<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 充值</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/swiper.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/recharge.css"/>
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
        充值
    </div>
</div>
<div class="container">
    <ul class="rechargeMoney">
        <li>
            <div class="blank" style="background: url('${base}/mobileNew/img/recharge/blank.png')no-repeat center; background-size: 0.68rem 0.68rem;"></div>
            <div class="blankAtl">
                <p>工商银行（尾号1458）<span class="quota_list">支持银行限额</span></p>
                <p>单笔限额 <span>5万元</span>今日剩余快捷充值额度 <span>5万元</span></p>
            </div>
        </li>
        <li>
            <p>充值金额</p>
            <input type="text" class="money" placeholder="100元起充">
            <%--<p>100元起充</p>--%>
        </li>
        <li>*大额充值请登录官方网站选择网关充值</li>
    </ul>
    <a href="" class="recharge">充&nbsp;值</a>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>


</body>
</html>
