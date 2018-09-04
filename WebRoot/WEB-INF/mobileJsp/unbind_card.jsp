<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 -  解绑银行卡</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        解绑银行卡
    </div>
</div>
<div class="unbind_card">
    <div class="dt">验证码已发送到 <span class="tel">176****5660</span></div>
    <div class="clearfix code_box">
        <span class="plceh">短信验证码</span>
        <div class="lf">
            <input type="text" class="in code" placeholder=""/>
            <input type="text" class="in code" placeholder=""/>
            <input type="text" class="in code" placeholder=""/>
            <input type="text" class="in code" placeholder=""/>
            <input type="text" class="in code" placeholder=""/>
            <input type="text" class="in code" placeholder=""/>
        </div>
        <a href="javascript:;" class="code_a">重新发送</a>
    </div>
    <div class="modal-box">
        <div class="modal"></div>
        <div class="dialog">
            <p class="pt succ">解绑成功，即将跳转</p>
            <p class="pt fail">解绑失败，请重试。</p>
            <p class="pb"><span id="fpwd_time">3</span>s</p>
        </div>
    </div>
</div>
</body>
</html>