<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 出借</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/mobileNew/css/scrollLoad.css" />
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/index.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/common.css"/>

    <style>
        .container {
            width: 100%;
            background: #FFFFFF;
        }
        .tit {
            display: none;
            position: fixed;
            transform: translate(-50%,-50%);
            left: 50%;
            top: 50%;
            padding: 0.1rem 0.3rem;
            font-size: 14px;
            color: #FFFFFF;
            line-height: 0.4rem;
            background: rgba(0,0,0,0.60);
            border-radius: 3px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="Lent">
        <p>散标出借</p>
        <span class="mouse"></span>
        <form action="javascript:return true;">
            <input  class="slide_into" id="slide_into" type="search" placeholder="请搜索标的名称" value="">
        </form>

    </div>
    <div class=" list-wrapper list-wrapper-hook">
        <div>
            <!-- 顶部提示信息 -->
            <div class="top-tip">
                <span class="refresh-hook">下拉刷新</span>
            </div>
            <div class="lentContent list-content list-content-hook"></div>
            <div class="bottom-tip">
                <span class="loading-hook">查看更多</span>
            </div>
        </div>
    </div>
    <p class="tit"></p>
    <!-- footer -->
    <%@include file="foot.jsp"%>

</div>
<%--<div class="alert alert-hook">刷新成功</div>--%>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/bscroll.js"></script>
<script src="${base}/mobileNew/js/scrollLoad.js"></script>
<script>
    $(function(){
        $('.container .footerNav a').removeClass('selected').eq(1).addClass('selected');
        $('.lendBox .lend').css({"background":"url(${base}/mobileNew/img/footer_icon/list.png)no-repeat center","background-size":" 0.4rem 0.44rem"});
    });

</script>
<script>
    $(document).ready(function(){
        $(".mouse").click(function(){
            $(".mouse").toggleClass("mouseChange");

            if($(".slide_into").css("width") == "0px"){
                $(".slide_into").animate({width:'5.9rem'},"slow");
                $(".mouseChange").click(function(){
                    $("#slide_into").val("");
                });
            }else{
                $(".slide_into").animate({width:'0px'},"slow");
            }
        });
    });

</script>
</body>
</html>