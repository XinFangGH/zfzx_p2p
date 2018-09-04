<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 -  登录&nbsp;/&nbsp;注册</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
<%--    <style>
        input:-webkit-autofill,
        input:-webkit-autofill:hover,
        input:-webkit-autofill:focus {
            box-shadow:0 0 0 60px #eee inset;
            -webkit-text-fill-color: #878787;
        }
    </style>--%>
    <script type="text/javascript">
        $(function(){
            $("#btn1").click(function(){
                if($(this).hasClass('btn_l')) {
                    $("#classForm").submit();
                }
            });
        });
    </script>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        登录&nbsp;/&nbsp;注册
    </div>
</div>
<form action="/webPhone/checkPhonehomePage.do" method="get" id="classForm">
<div class="log_reg log_reg_m">
    <input type="tel" name="telPhone"  autocomplete="off"  class="in tel" placeholder="请输入您的手机号" />
    <div class="msg">
        <p class="sl">*注册时请使用与银行卡绑定的手机号</p>
        <span class="sr" id="msg_down">了解详情</span>
    </div>
    <div class="msg_con">
        为了保证您的资金安全，应资金监管银行要求，注册时手机号必须与账户需要绑定的银行卡预留手机号保持一致，为了避免您更换手机号操作，请尽量选择您要绑卡的银行预留手机号进行注册。
    </div>
    <a href="javascript:;" class="btn" id="btn1">下一步</a>
    <p class="tit">密码错误，请重新输入！</p>
</div>
</form>
<script>
    $(function(){
        $('#msg_down').click(function(){
            $('.msg_con').toggle();
        });
    });
</script>
</body>
</html>