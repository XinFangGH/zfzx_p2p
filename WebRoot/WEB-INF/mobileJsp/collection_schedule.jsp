<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 回款计划</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        回款计划
    </div>
</div>
<div class="colle_sche scat_lend">
    <c:choose>
        <c:when test="${fundIntentpay[0].payintentPeriod == 0}"><c:set var="aaa" scope="session" value="${fundIntentpay.size()-1}"/> </c:when>
        <c:otherwise><c:set var="aaa" value="${fundIntentpay.size()}"/></c:otherwise>
    </c:choose>
    <c:forEach items="${fundIntentpay}" var="fi">
        <div class="lend_con">
            <div class="dt clearfix">
                <p class="pl">期数:<span>${fi.payintentPeriod}/<c:out value="${aaa}" /></span>
                <p>
                    <c:choose>
                        <c:when test="${fi.factPaymentsMoney.unscaledValue()==0}"><span class="pr cor_org">未回款</span> </c:when>
                        <c:otherwise><span class="pr">已回款</span> </c:otherwise>
                    </c:choose>
                </p>
            </div>
            <ul class="dc clearfix">
                <li>
                    <p>${fi.payIntestPrinMoney}元</p>
                    <span>应回金额（元）</span>
                </li>
                <li>
                    <p>${fi.factPaymentsMoney}</p>
                    <span>实回金额（元）</span>
                </li>
                <li>
                    <p>${fi.intentDate}</p>
                    <span>应还日期</span>
                </li>
            </ul>
         </div>
    </c:forEach>
  <%--  <div class="lend_con">
        <div class="dt clearfix">
            <p class="pl">期数：<span>4/4</span></p>
            <p class="pr cor_org">未还款</p>
        </div>
        <ul class="dc clearfix">
            <li>
                <p> 1000元</p>
                <span>应回金额（元）</span>
            </li>
            <li>
                <p>1000元</p>
                <span>实回金额（元）</span>
            </li>
            <li>
                <p>2017-4-26</p>
                <span>应还日期</span>
            </li>
        </ul>
    </div>--%>
</div>

<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/commen.js"></script>

</body>
</html>