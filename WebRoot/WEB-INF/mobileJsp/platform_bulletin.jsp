<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 平台公告</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/message_center.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/mobileNew/css/scrollLoad.css" />
    <style>
        html {
            font-size: 100px;
        }

        .container {
            width: 100%;
            background: #FFFFFF;
        }
        .list-wrapper{
            bottom:0;
        }
    </style>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        平台公告
    </div>
</div>
<div class=" list-wrapper list-wrapper-hook">
    <div>
        <div class="container">
            <ul class="platform list-content-hook">
                <%--<c:forEach items="${webList}" var="webList">--%>
                   <%-- <li>
                        <a href="javaScript:void(0);">
                            <h3 class="h_text">${webList.title} <span></span></h3>
                            <span>${webList.createDate}</span>
                            <div  class="h_con">${webList.content}</div>
                        </a>
                    </li>--%>
                <%--</c:forEach>--%>
            </ul>
        </div>
        <div class="bottom-tip">
            <span class="loading-hook">查看更多</span>
        </div>
    </div>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/bscroll.js"></script>
<script src="${base}/mobileNew/js/platformBullt_list.js"></script>
</body>
</html>