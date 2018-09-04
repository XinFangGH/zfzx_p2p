<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>报道详情</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
</head>
<body>
<%--<div class="nav_box">--%>
<%--<div class="nav">--%>
<%--<a href="javascript:history.go(-1);" class="back"></a>--%>
<%--报道详情--%>
<%--</div>--%>
<%--</div>--%>
<div class="media_detail" style="margin-top:0rem;">
    <h5>${article.title}</h5>
    <div class="con">
        ${article.content}
    </div>
    <div class="db clearfix">
        <p>${article.modifyDate}</p>
    </div>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/commen.js"></script>
</body>
</html>