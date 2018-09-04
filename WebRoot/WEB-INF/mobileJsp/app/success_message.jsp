<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>消息提示</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
    <script src="${base}/mobileNew/js/AppJsInterface.js"></script>
</head>
<body>
<%--<div class="nav_box">--%>
<%--<div class="nav">--%>
<%--<a href="javascript:history.go(-1);" class="back"></a>--%>
<%--消息提示--%>
<%--</div>--%>
<%--</div>--%>
<div class="back_msg">
    <%--根据不同class替换不同的图片succ:成功，fail:失败，tit:提示--%>
    <div class="img_icon succ"></div>
    <div style="width: 100%;height:114px;text-align:center;margin-top: 44px;margin-bottom: 30px">
        <%--<p style="font-family:PingFang-SC-Medium;font-size:16px;color:#4a4a4a;text-align:center;">${messageModel.getMess}</p>--%>
            <p style="font-family:PingFang-SC-Medium;font-size:25px;color:#000000;text-align:center;">${mes}</p>
            <%--<p style="font-family:PingFang-SC-Medium;font-size:25px;color:#ff0000;text-align:center;">${code}</p>--%>
    </div>
</div>
</body>

</html>
