<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 风险评估</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/help_center.css"/>
    <style>
        body{
            background:#fff;
        }
        html {
            font-size: 100px;
        }

        .container {
            width: 100%;
            background: #FFFFFF;
            margin-top: 0.88rem;
            overflow: hidden;
        }

    </style>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        风险评估
    </div>
</div>
<div class="container">
    <div class="immediate_test_confirm">
        <h3>您的风险评估结果为</h3>
        <div class="immediate_test_text">
            <c:if test="${bp.grade == '谨慎型'}">谨慎型</c:if>
            <c:if test="${bp.grade == '进取型'}">进取型</c:if>
            <c:if test="${bp.grade == '稳健型'}">稳健型</c:if>
        </div>
        <div class="immediate_test_describe">您首先考虑的是资金的稳定性，为了获得一定的收益可以承受投资产品价格的波动，对风险有清晰的认识，适合购买中高收益的</div>
    </div>
    <a href="confirmWebPhoneCustMember.do?flag=${flag}&bidId=${bidId}" class="risk_assessment_test">确认</a>
    <a href="evaluateWebPhoneCustMember.do?flag=${flag}&bidId=${bidId}" class="risk_assessment_default">重新测试</a>

</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>

</body>
</html>