<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 -出借服务协议 </title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <style>

    </style>
</head>
<body>
<div class="fina_agree swiper-container">
    <div class="swiper-wrapper">
        <div class="swiper-slide" id="page1">
            <img src="${base}/mobileNew/img/mypic/0_1.jpg" alt="">
        </div>
        <div class="swiper-slide" id="page2">
            <img src="${base}/mobileNew/img/mypic/0_2.jpg" alt="">
        </div>
        <div class="swiper-slide" id="page3">
            <img src="${base}/mobileNew/img/mypic/0_3.jpg" alt="">
        </div>
        <div class="swiper-slide" id="page4">
            <img src="${base}/mobileNew/img/mypic/0_4.jpg" alt="">
        </div>
        <div class="swiper-slide" id="page5">
            <img src="${base}/mobileNew/img/mypic/0_5.jpg" alt="">
        </div>
        <div class="swiper-slide" id="page6">
            <img src="${base}/mobileNew/img/mypic/0_6.jpg" alt="">
        </div>
        <div class="swiper-slide" id="page7">
            <img src="${base}/mobileNew/img/mypic/0_7.jpg" alt="">
        </div>
    </div>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/swiper.min.js"></script>
<script>
    //->初始化Swiper
    var swiper=new Swiper(".swiper-container", {
            direction: "vertical",
            loop: false,
            autoHeight:true,
            //->当屏幕切换结束后执行 swipe:当前Swiper的一个实例
            onSlideChangeEnd: function (swiper) {
                var ind = swiper.activeIndex;
                var slides = swiper.slides;
                var len = slides.length;
                var trueLen = len - 2;
                [].forEach.call(slides, function (item, index) {
                    if (index == ind) {
                        item.id = 'page' + (ind % trueLen == 0 ? (trueLen) : ind % trueLen);
                        return;
                    }
                    item.id = null;
                })

            }
        }
    );
</script>
</body>
</html>
