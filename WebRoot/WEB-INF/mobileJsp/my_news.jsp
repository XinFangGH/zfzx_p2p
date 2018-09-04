<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 我的消息</title>
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
           /* margin-top: 0.88rem;*/
        }
        .list-wrapper{
            bottom:0;
        }
        .h_text{font-weight:normal;}
    </style>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.back(-1);" class="back"></a>
        我的消息
    </div>
</div>
<div class=" list-wrapper list-wrapper-hook">
    <div>
        <div class="container">
            <ul class="platform list-content-hook">
                <%--<c:forEach items="${list}" var="list">
                <li>
                    <a href="javaScript:void(0);">
                        <h3 class="h_text">${list.title}<span ></span></h3>
                        <span>${list.sendTime}</span>
                        <p>${list.content}</p>
                    </a>
                </li>
                </c:forEach>
                <li>
                    <a href="javaScript:void(0);">
                        <h3  class="h_text">开通银行存管fff<span></span></h3>
                        <span>2018-4-26</span>
                        <p>尊敬的客户，您好，祝贺您注册成功！请登录您的账户，开始您的投资。</p>
                    </a>
                </li>--%>
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
<script src="${base}/mobileNew/js/myNews_list.js"></script>
<script>
    window.addEventListener('pageshow', function(e) {
        // 通过persisted属性判断是否存在 BF Cache
        if (e.persisted) {
            location.reload();
        }
    });
   /* $(".h_text").each(function(){
        var maxwidth=12;
        if($(this).text().length > maxwidth){
            $(this).text($(this).text().substring(0,maxwidth));
            $(this).html($(this).text()+'...'+ '<span ></span>');
        }
    });*/
</script>
</body>
</html>