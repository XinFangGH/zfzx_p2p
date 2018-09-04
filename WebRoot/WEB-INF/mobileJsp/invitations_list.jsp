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
    <title>升升投 -  邀请列表</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/swiper.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/invitations_list.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/swiper.min.js"></script>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        <%-- 邀请列表--%>
        <input  class="slide_into" id="slide_into" type="search" placeholder="搜索" value="">
        <span class="mouse" onclick="findDate()"></span>
    </div>
</div>
<div class="scat_lend">
    <div class="swiper-container swiper1">
        <div class="swiper-wrapper">
            <div class="swiper-slide selected">已出借<i></i></div>
            <div class="swiper-slide">未出借<i></i></div>
        </div>
    </div>
    <div class="swiper-container swiper2">
        <div class="swiper-wrapper">
            <div class="swiper-slide">
                <div class="already_borrowed">
                    <c:forEach items="${inviteList}" var="list">
                        <c:if test="${list.bidId != null}">
                            <a onclick="toDetail(${list.id})">
                                    <%--href="${base}/checkInviteWebPhoneCustMember.do?id=${list.id}"--%>
                               <%--onclick="function inv() {--%>
                                    <%--window.location.href=${base}'/checkInviteWebPhoneCustMember.do?id=${list.id}';--%>
                                    <%--}"--%>
                            <%-->--%>
                            <ul>
                                <li><span class="userPic"></span><span>${list.truename}</span><span class="more" id="selectInv"></span><span>${fn:substring(list.telphone,0,3)}****${fn:substring(list.telphone,7,11)}</span><span class="phonePic"></span></li>
                                <li><span class="bidname">${list.bidName}</span><span>${list.userMoney}</span><span>${list.bidtime}</span></li>
                                <li><span class="bidname">项目名称</span><span>出借金额（元）</span><span>出借时间</span></li>
                            </ul>
                            </a>
                        </c:if>
                    </c:forEach>
                    <%--<ul>
                        <li><span class="userPic"></span><span>王小明</span><span class="more"></span><span>186****9227</span><span class="phonePic"></span></li>
                        <li><span>融资租赁001</span><span>2000</span><span>2018-4-26</span></li>
                        <li><span>项目名称</span><span>出借金额（元）</span><span>出借时间</span></li>
                    </ul>--%>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="no_loan">
                    <c:forEach items="${inviteList}" var="list">
                        <c:if test="${list.bidId == null}">
                            <c:if test="${list.isCheckCard != 1}">
                            <ul>
                                <li><span class="no_real_name"><i></i>未实名</span><span>${fn:substring(list.telphone,0,3)}****${fn:substring(list.telphone,7,11)}</span><span>${list.registrationDate}</span></li>
                                <li><span>姓名</span><span>手机号</span><span>注册时间</span></li>
                            </ul>
                        </c:if>
                        <c:if test="${list.isCheckCard == 1}">
                        <ul>
                            <li><span class="already_real_name"><i></i>${list.truename}</span><span>${fn:substring(list.telphone,0,3)}****${fn:substring(list.telphone,7,11)}</span><span>${list.registrationDate}</span></li>
                            <li><span>姓名</span><span>手机号</span><span>注册时间</span></li>
                        </ul>
                        </c:if>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <div class="con" style="display:none;">
    </div>
    <p class="tit"></p>
</div>
<script>
//    $(function () {
//        $('#selectInv').click(function () {
//
//        })
//    })
    function toDetail(id) {
        window.location.href=${base}"/webPhone/checkInviteWebPhoneCustMember.do?id="+id
    }

    function findDate() {
        window.location.href=${base}"/webPhone/invite3WebPhoneCustMember.do";
    }

    $(function(){
        function setCurrentSlide(ele, index) {
            $(".swiper1 .swiper-slide").removeClass("selected");
            ele.addClass("selected");
            //swiper1.initialSlide=index;
        }
        var sl_swiper1 = new Swiper('.scat_lend .swiper1', {
            //					设置slider容器能够同时显示的slides数量(carousel模式)。
            //					可以设置为number或者 'auto'则自动根据slides的宽度来设定数量。
            //					loop模式下如果设置为'auto'还需要设置另外一个参数loopedSlides。
            slidesPerView: 2,
            paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
            spaceBetween: 10,//slide之间的距离（单位px）。
            freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
            loop: false,//是否可循环
            observer: true,//修改swiper自己或子元素时，自动初始化swiper
            observeParents: true,//修改swiper的父元素时，自动初始化swiper
            onTab: function (swiper) {
                var n = sl_swiper1.clickedIndex;
            }
        });
        sl_swiper1.slides.each(function (index, val) {
            var ele = $(this);
            ele.on("click", function () {
                setCurrentSlide(ele, index);
                sl_swiper2.slideTo(index, 500, false);
                //mySwiper.initialSlide=index;
            });
        });

        var sl_swiper2 = new Swiper('.scat_lend .swiper2', {
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
                setCurrentSlide($(".scat_lend .swiper1 .swiper-slide").eq(n), n);
                sl_swiper1.slideTo(n, 500, false);
            }
        });



        //搜索
        $("#slide_into").on('keypress',function(e) {
            var keycode = e.keyCode;
            var searchKey = $(this).val();
            if(keycode=='13') {
                e.preventDefault();
                document.activeElement.blur();
                //请求搜索接口
                $.ajax({
                    type:'GET',
                    url:"/webPhone/selectInviteWebPhoneCustMember.do",
                    data:{"searchKey":searchKey,"isApp":1},
                    dataType:'json',
                    success:function(data){
                        var lists=data.data.list;
                        if(lists.length){
                            $('.swiper1').hide();
                            $('.swiper2').hide();
                            var flagment="";

                            $.each(lists,function(i,list){
                                if(list.bidName){
                                    flagment+=`<div class="already_borrowed">
                                    <ul>
                                        <li><span class="userPic"></span><span>`+list.truename+`</span><span class="more"></span><span>`+(list.telphone.substr(0,3)+'****'+list.telphone.substr(7))+`</span><span class="phonePic"></span></li>
                                        <li><span class="bidname">`+list.bidName+`</span><span>`+list.userMoney+`</span><span>`+list.bidtime+`</span></li>
                                        <li><span>项目名称</span><span>出借金额（元）</span><span>出借时间</span></li>
                                    </ul>
                                </div>`;
                                }else{
                                    flagment+=`<div class="no_loan">
                                    <ul>
                                        <li><span class="`+(list.isCheckCard==1?"already_real_name":"no_real_name")+`"><i></i>`+(list.isCheckCard==1?list.truename:"未实名")+`</span><span>`+(list.telphone.substr(0,3)+'****'+list.telphone.substr(7))+`</span><span>`+list.registrationDate+`</span></li>
                                        <li><span>姓名</span><span>手机号</span><span>注册时间</span></li>
                                    </ul>
                                </div>`;
                                }
                            });
                            $('.con').html(flagment).show();
                        }else{
                            $('.tit').html("无相关搜索结果").show();
                            var t =setTimeout(function () {
                                $(".tit").hide();
                                clearTimeout(t);
                            },3000);
                        }
                    }
                });
            }
        });
    });
</script>
</body>
</html>