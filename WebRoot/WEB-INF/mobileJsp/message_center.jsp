<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 消息中心</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
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
        消息中心
    </div>
</div>
<div class="container">
    <ul class="message_center">
        <li class="platform">
            <a href="javascript:void(0); onclck=location.href=${base}'/webPhone/getWebArticleListhomePage.do'" ><span></span><h3>平台公告 </h3><span class="more"></span></a>
        </li>
        <li class="news"><a href="javascript:void(0); onclck=location.href=${base}'/message/getUserMesAllOaNewsMessage.do?page=1&limit=10'"><span></span><h3>我的消息
            <c:choose>
                <c:when test="${OaNewsMessagerinfo.readStatus==0}"><i></i></c:when>
                <c:otherwise> </c:otherwise>
            </c:choose></h3><span  class="more">
        </span></a></li>
    </ul>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script>
    window.addEventListener('pageshow', function(e) {
        // 通过persisted属性判断是否存在 BF Cache
        if (e.persisted) {
            location.reload();
        }
    });
</script>
</body>
</html>