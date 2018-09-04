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
  <%--  <meta name="apple-mobile-web-app-capable" content="yes"/>--%>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 散标出借</title>
    <link rel="stylesheet" type="text/css" href="${base}/mobileNew/css/scrollLoad.css" />
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css?version=1.0"/>
    <style>
        .list-wrapper{
            top: 1.9rem;
            bottom:0;
            overflow:auto;
        }
    </style>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        散标出借
    </div>
</div>
<div class="scat_lend">
    <div class="swiper-container swiper1">
        <div class="swiper-wrapper">
            <div class="swiper-slide selected">出借中<i></i></div>
            <div class="swiper-slide">回款中<i></i></div>
            <div class="swiper-slide">已结清<i></i></div>
            <div class="swiper-slide">失败标的<i></i></div>
        </div>
    </div>
    <div class=" list-wrapper list-wrapper-hook">
        <div>
            <div class="list-content list-content-hook"></div>
            <div class="bottom-tip">
                <span class="loading-hook">查看更多</span>
            </div>
        </div>
    </div>
</div>
<%@include file="common.jsp"%>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/commen.js"></script>
<script src="${base}/mobileNew/js/bscroll.js"></script>
<script src="${base}/mobileNew/js/scattered_lend.js?version=1.2"></script>
</body>
</html>