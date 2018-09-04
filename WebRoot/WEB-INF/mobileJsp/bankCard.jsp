<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 -  银行卡</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        银行卡
    </div>
</div>
<div class="bankCard">
    <div class="dt"><span>添加银行卡</span></div>
    <div class="dc">
        <span class="sl">支持银行</span>
        <a href="/webPhone/bankCard1WebFinancePurchaseAction.do" class="all">全部</a>
    </div>
    <table class="db">
        <thead>
        <tr>
            <th>银行名称</th>
            <th>单笔限额（元）</th>
            <th>单日限额（元）</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><span class="bname gongshang"></span><span>工商银行</span></td>
            <td>50000</td>
            <td>50000</td>
        </tr>
        <tr>
            <td><span class="bname jianshe"></span><span>建设银行</span></td>
            <td>200,000</td>
            <td>200,000</td>
        </tr>
        <tr>
            <td><span class="bname guangfa"></span><span>广发银行</span></td>
            <td>200,000</td>
            <td>500,000</td>
        </tr>
        <tr>
            <td><span class="bname pingan"></span><span>平安银行</span></td>
            <td>50000</td>
            <td>50000</td>
        </tr>
        <tr>
            <td><span class="bname guangda"></span><span>光大银行</span></td>
            <td>50000</td>
            <td>50000</td>
        </tr>
        </tbody>
    </table>
</div>
<script>
        function tiedCard() {
            window.location.href='/webPhone/appBindCardWebPhoneThirdAction.do?isApp='+1;
        }
        $(".dt").bind("click",tiedCard);

</script>
</body>
</html>