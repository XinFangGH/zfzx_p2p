<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 出借明细</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/invitations_list.css"/>
    <style>
        html {
            font-size: 100px;
        }

        .container {
            width: 100%;
            background: #FFFFFF;
            /* margin-top: 0.88rem;*/
        }

    </style>
</head>
<body>
<div class="nav_box" style="display: none">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        出借明细
    </div>
</div>
<div class="container ">
    <div class="loan_details">
        <div class="nav">
            <a href="javascript:history.go(-1);" class="back"></a>
            出借明细
        </div>
        <ul class="loan_details_topbar">
            <li><span  class="userPic"></span><span>${personDetail.truename}</span><span>注册时间</span><span><fmt:formatDate value="${personDetail.registrationDate}" pattern="yyyy-MM-dd"></fmt:formatDate></span></li>
            <li><span  class="phonePic"></span><span>${fn:substring(personDetail.telphone,0,3)}****${fn:substring(personDetail.telphone,7,11)}</span><span>累计出借</span><span>${totalCounts}</span></li>
        </ul>
    </div>
    <c:forEach items="${invitePersonList}" var="list">
    <ul class="loan_details_lists">
        <li><span>${list.bidName}</span><span>出借时间：<fmt:formatDate value="${list.bidtime}" pattern="yyyy-MM-dd"></fmt:formatDate></span></li>
        <li><span class="listsColor">出借期限</span><span>${list.payMoneyTime}个月</span></li>
        <li><span class="listsColor">出借金额</span><span>
            <c:choose>
            <c:when test="${list.userMoney > 10000}"><fmt:formatNumber value="${list.userMoney/10000}" pattern=",##0.00#"/>万元</c:when>
            <c:otherwise><fmt:formatNumber value="${list.userMoney}" pattern=",##0"/>元</c:otherwise>
        </c:choose></span></li>
        <li><span class="listsColor">放款日期</span><span>${list.startIntentDate}</span></li>
        <li><span class="listsColor">到期日期</span><span>${list.endIntentDate}</span></li>
        <li><span class="listsColor">当前状态</span><span class="Repayments">
            <c:choose><c:when test="${list.state == 1}">招标中</c:when>
                <c:when test="${list.state == 2}">已满标</c:when>
                <c:when test="${list.state == 3}">已流标</c:when>
                <c:when test="${list.state == 4}">已过期</c:when>
                <c:when test="${list.state == 7}">还款中</c:when>
                <c:when test="${list.state == 10}">已还款</c:when>
            </c:choose></span></li>
    </ul>
    </c:forEach>


</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
</body>
</html>