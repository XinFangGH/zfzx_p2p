<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script>
        function go() {
            location.href="/WEB-INF/mobileJsp/immediate_test.jsp"
        }
    </script>

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
    <div class="risk_assessment_remind">为了帮助您控制投资风险，根据国家相关法律要求，交易前需要对您的风险承受能力进行评估，为了给您提供更好的资产配置建议，请认真作答，感谢您的配合</div>

    <a href="/webPhone/testRiskWebPhoneCustMember.do?flag=${flag}&bidId=${bidId}&isApp=${isApp}" class="risk_assessment_test" >立即测试</a>
    <a href="/webPhone/mobileSaveWebPhoneCustMember.do?flag=${flag}&bidId=${bidId}" class="risk_assessment_default">默认为稳健型</a>

</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
</body>
</html>
