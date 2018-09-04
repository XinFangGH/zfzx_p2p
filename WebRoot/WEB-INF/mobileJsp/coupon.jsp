<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 -  我的优惠券</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/swiper.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/swiper.min.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        我的优惠券
    </div>
</div>
<div class="coupon">
    <div class="swiper-container swiper1">
        <div class="swiper-wrapper">
            <div class="swiper-slide selected">未使用<i></i></div>
            <div class="swiper-slide">已使用<i></i></div>
            <div class="swiper-slide">已过期<i></i></div>
        </div>
    </div>
    <div class="swiper-container swiper2">
        <div class="swiper-wrapper">
            <div class="swiper-slide">
                <div class="default">
                    <img class="pic" src="${base}/mobileNew/img/mypic/coupon.png" alt="" />
                    <p>暂时没有优惠券哦~</p>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="default">
                    <img class="pic" src="${base}/mobileNew/img/mypic/coupon.png" alt="" />
                    <p>暂时没有优惠券哦~</p>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="default">
                    <img class="pic" src="${base}/mobileNew/img/mypic/coupon.png" alt="" />
                    <p>暂时没有优惠券哦~</p>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function(){
        function setCurrentSlide(ele, index) {
            $(".swiper1 .swiper-slide").removeClass("selected");
            ele.addClass("selected");
            //swiper1.initialSlide=index;
        }

        var swiper1 = new Swiper('.swiper1', {
            //					设置slider容器能够同时显示的slides数量(carousel模式)。
            //					可以设置为number或者 'auto'则自动根据slides的宽度来设定数量。
            //					loop模式下如果设置为'auto'还需要设置另外一个参数loopedSlides。
            slidesPerView: 3,
            paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
            spaceBetween: 10,//slide之间的距离（单位px）。
            freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
            loop: false,//是否可循环
            observer: true,//修改swiper自己或子元素时，自动初始化swiper
            observeParents: true,//修改swiper的父元素时，自动初始化swiper
            onTab: function (swiper) {
                var n = swiper1.clickedIndex;
            }
        });
        swiper1.slides.each(function (index, val) {
            var ele = $(this);
            ele.on("click", function () {
                setCurrentSlide(ele, index);
                swiper2.slideTo(index, 500, false);
                //mySwiper.initialSlide=index;
            });
        });

        var swiper2 = new Swiper('.swiper2', {
            //freeModeSticky  设置为true 滑动会自动贴合
            direction: 'horizontal',//Slides的滑动方向，可设置水平(horizontal)或垂直(vertical)。
            loop: false,
            //					effect : 'fade',//淡入
            //effect : 'cube',//方块
            //effect : 'coverflow',//3D流
            //					effect : 'flip',//3D翻转
            autoHeight: true,//自动高度。设置为true时，wrapper和container会随着当前slide的高度而发生变化。
            observer: true,//修改swiper自己或子元素时，自动初始化swiper
            observeParents: true,//修改swiper的父元素时，自动初始化swiper
            onSlideChangeEnd: function (swiper) {  //回调函数，swiper从一个slide过渡到另一个slide结束时执行。
                var n = swiper.activeIndex;
                setCurrentSlide($(".swiper1 .swiper-slide").eq(n), n);
                swiper1.slideTo(n, 500, false);
            }
        });
    });

</script>
</body>
</html>