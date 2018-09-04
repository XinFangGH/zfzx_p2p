<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 融资租赁</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/swiper.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/financeLeaseDetail.css"/>
    <style>
        html {
            font-size: 100px;
        }
        .container {
            width: 100%;
            background: #FFFFFF;
            position: fixed;
            top: 0.88rem;
            left:0;
        }

        .swiper1 {
            width: 100%;
            border-top:1px solid #ccc;
            box-shadow: 0 4px 4px 0 rgba(155,166,173,0.15);
            margin-bottom:0.3rem;
        }
        .swiper1 .selected {
            color:  #409af6;
        }
        .swiper1 .swiper-slide {
            text-align: center;
            font-size: 0.28rem;
            height:0.88rem!important;
            /*  !* Center slide text vertically *!*/
            display: -webkit-box;
            display: -ms-flexbox;
            display: -webkit-flex;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            -webkit-justify-content: center;
            justify-content: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            -webkit-align-items: center;
            align-items: center;
            cursor: pointer;
        }
        .swiper2 {
            width: 100%;
        }
        .swiper2 .swiper-slide {
            height:518px;
            background-color: #fff;
            color: #1A1A1A;
            box-sizing: border-box;
            /*overflow-x: hidden;*/
            -webkit-overflow-scrolling: touch;
            /*box-shadow: 0 0.04rem 0.2rem 0 rgba(146,173,189,0.35);*/
            border-radius: 0.08rem;
            padding:0.2rem;
            padding-bottom:0.3rem;
        }

    </style>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        融资租赁
    </div>
</div>
<div class="container">
    <div class="swiper-container swiper1">
        <div class="swiper-wrapper">
            <div class="swiper-slide selected">标的详情</div>
            <div class="swiper-slide">资料公示</div>
            <div class="swiper-slide">出借记录</div>
        </div>
    </div>
    <!-- swiper2 -->
    <div class="swiper-container swiper2">
        <div class="swiper-wrapper">
            <div class="swiper-slide  slidePage" style="overflow-x: hidden;" id="enterprise">
                <div class="titleNav">
                    <span class="icon"></span>
                    <span class="title">企业资料</span>
                </div>
                <ul class="enterpriseContent">
                    <li><span>企业名称</span>
                        <span><c:if test="${enterprise.enterprisename} == ''">未填</c:if>
                            ${enterprise.enterprisename}</span></li>
                    <li><span>所有制性质</span>
                        <span><c:if test="${enterprise.ownership} == ''">未填</c:if>
                            ${enterprise.ownership}</span></li>
                    <li><span>注册资金</span>
                        <span><c:if test="${enterprise.registermoney} == ''">未填</c:if>
                            ${enterprise.registermoney}万元</span></li>
                    <li><span>注册时间</span>
                        <span><c:if test="${enterprise.opendate} == ''">未填</c:if>
                            ${enterprise.opendate}</span></li>
                    <li><span>经营所在地</span>
                        <span><c:if test="${enterprise.managecityName} == ''">未填</c:if>
                            ${enterprise.managecityName}</span></li>
                    <li><span>所属行业</span>
                        <span><c:if test="${enterprise.hangyeName} == ''">未填</c:if>
                            ${enterprise.hangyeName}</span></li>
                    <li><span>营业范围</span>
                        <span><c:if test="${pl.proBusinessScope} == ''">未填</c:if>
                            ${pl.proBusinessScope}</span></li>
                    <li><span>主要债务</span>
                        <span><c:if test="${pl.mainDebt} == ''">未填</c:if>
                            ${pl.mainDebt}</span></li>
                </ul>
                <div class="segmentingLine"></div>
                <div class="titleNav">
                    <span class="icon"></span>
                    <span class="title">项目信息</span>
                </div>
                <ul class="enterpriseContent" style="height:8.5rem;">
                    <li><span style="width: 100%; line-height:0.6rem;">项目描述</span><span  style="width: 100%">
                        <c:if test="${pl.proDes} == ''">未填</c:if>
                        ${pl.proDes}
                    </span></li>
                    <li><span>资金用途</span><span>
                        <c:if test="${pl.proUseWay} == ''">未填</c:if>
                        ${pl.proUseWay}
                    </span></li>
                    <li><span>还款来源</span><span>
                        <c:if test="${pl.proPayMoneyWay} == ''">未填</c:if>
                        ${pl.proPayMoneyWay}
                    </span></li>
                </ul>
                <p class="remind">＊市场有风险，出借须谨慎。</p>
            </div>
            <div class="swiper-slide  slidePage" style="overflow-x: hidden;" id="publicity">
                <div class="titleNav">
                    <span class="icon"></span>
                    <span class="title">经营公示</span>
                </div>
                <p class="paging"><span id="paging_page">1</span>/${fileLIst.size()}</p>
                <div class="License swiper5">
                    <div class="swiper-wrapper publicity_pic">
                        <c:forEach items="${fileLIst}" var="list">
                            <div class="swiper-slide">
                                <div class="Picture_area">
                                    <img src="${base}/${list.webPath}">
                                </div>
                                <p>${list.setname}</p>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="segmentingLine" style="  margin:0.4rem auto 0.4rem;"></div>
                    <div class="titleNav">
                        <span class="icon"></span>
                        <span class="title">设备公示</span>
                    </div>
                    <p class="paging"><span id="paging_page_2">1</span>/${materials.size()}</p>
                    <div class="License swiper6" style="margin-bottom: 1rem;">
                        <div class="swiper-wrapper publicity_pic" >
                            <c:forEach items="${materials}" var="mas">
                                <div class="swiper-slide">
                                    <div class="Picture_area">
                                        <img src="${base}/${mas.imgUrl}" data-src="${base}/${mas.imgUrl}" alt="">
                                    </div>
                                    <p>${mas.materialsName}</p>
                                </div>
                            </c:forEach><%--<div class="swiper-slide">
                        <div class="Picture_area">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/01.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/01.jpg" alt="">
                        </div>
                        <p>烟台彩悦货运代理有限公司营业执照</p>
                    </div>--%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="swiper-slide  slidePage" style="overflow-x: hidden;" id="Loan_record">
                <ul>
                    <c:forEach items="${listPlBid}" var="record">
                        <li>
                            <span class="times">${record.bidtime}</span>
                            <p><span>${record.userName}</span><span>${record.userMoney}</span></p>
                        </li>
                    </c:forEach>
                    <li>
                        <span class="times">2018-4-26 06:41:14</span>
                        <p><span>136****2959</span><span>65,000.00</span></p>
                    </li>
                    <li>
                        <span class="times">2018-4-26 06:41:14</span>
                        <p><span>136****2959</span><span>65,000.00</span></p>
                    </li>
                    <li>
                        <span class="times">2018-4-26 06:41:14</span>
                        <p><span>136****2959</span><span>65,000.00</span></p>
                    </li>
                    <li>
                        <span class="times">2018-4-26 06:41:14</span>
                        <p><span>136****2959</span><span>65,000.00</span></p>
                    </li>
                </ul>

            </div>

        </div>
    </div>
</div>
<div class="footerButton">
    <div class="button">立即出借</div>
</div>

<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(100, 100, 100, 0.5);z-index:4;width:100%;height:100%;display:none;">
    <div id="innerdiv" style="position:absolute;">
        <i id="fork"></i>
        <img id="bigimg" style="border:1px solid #fff;" src="" />
    </div>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/swiper.min.js"></script>
<script>
    $(function() {
        setTimeout(changeTab,100);
        function changeTab(){
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

            var swiper5 = new Swiper('.swiper5', {
                slidesPerView: 1.329,
                freeModeSticky: true,
                paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
                spaceBetween: 30,//slide之间的距离（单位px）。
                // freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
                loop: false,//是否可循环
                slidesOffsetBefore: 40,
                slidesOffsetAfter: 80,
                observer: true,//修改swiper自己或子元素时，自动初始化swiper
                observeParents: true,//修改swiper的父元素时，自动初始化swiper
                onSlideChangeEnd: function (swiper) {  //回调函数，swiper从一个slide过渡到另一个slide结束时执行。
                    var n = swiper.activeIndex;
                    $("#paging_page").html(n + 1);

                    /*setCurrentSlide($(".swiper1 .swiper-slide").eq(n), n);
                    swiper1.slideTo(n, 500, false);*/
                }
            });

            var swiper6 = new Swiper('.swiper6', {
                slidesPerView: 1.329,
                freeModeSticky: true,
                paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
                spaceBetween: 30,//slide之间的距离（单位px）。
                // freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
                loop: false,//是否可循环
                slidesOffsetBefore: 40,
                slidesOffsetAfter: 80,
                observer: true,//修改swiper自己或子元素时，自动初始化swiper
                observeParents: true,//修改swiper的父元素时，自动初始化swiper
                onSlideChangeEnd: function (swiper) {  //回调函数，swiper从一个slide过渡到另一个slide结束时执行。
                    var n = swiper.activeIndex;
                    $("#paging_page_2").html(n + 1);

                    /*setCurrentSlide($(".swiper1 .swiper-slide").eq(n), n);
                    swiper1.slideTo(n, 500, false);*/
                }
            });
        }
    });
</script>
<script>
    $(function(){
        $(".License .swiper-wrapper  .swiper-slide").click(function(){
            var _this = $(this).find("img");
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
        });

        $("#bigimg").click(function(){//再次点击淡出消失弹出层
            $(outerdiv).fadeOut("slow");
        });

        function imgShow(outerdiv, innerdiv, bigimg, _this){
            var src = _this.attr("data-src");
            $(bigimg).attr("src", src);

            $("<img/>").attr("src", src).load(function(){
                var windowW = $(window).width();
                var windowH = $(window).height();
                var realWidth = this.width;
                var realHeight = this.height;
                var imgWidth, imgHeight;
                var scale = 0.8;

                if(realHeight>windowH*scale) {
                    imgHeight = windowH*scale;
                    imgWidth = imgHeight/realHeight*realWidth;
                    if(imgWidth>windowW*scale) {
                        imgWidth = windowW*scale;
                    }
                } else if(realWidth>windowW*scale) {
                    imgWidth = windowW*scale;
                    imgHeight = imgWidth/realWidth*realHeight;
                } else {
                    imgWidth = realWidth;
                    imgHeight = realHeight;
                }
                $(bigimg).css("width",imgWidth);

                var w = (windowW-imgWidth)/2;
                var h = (windowH-imgHeight)/2 + 44;
                $(innerdiv).css({"top":h, "left":w});
                $(outerdiv).fadeIn("slow");//淡入显示#outerdiv及.pimg
            });
        }
    });

</script>

<script type="text/javascript">
    var u = navigator.userAgent;
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    /* alert('是否是Android：'+isAndroid);
     alert('是否是iOS：'+isiOS);*/

    if(isAndroid){
        $(".swiper2 .slidePage").css("height","calc(85vh - 50px)");
    }

    /*  if(isiOS){
          $(".swiper2 .slidePage").css("height","10.6rem");
      }*/
</script>

<script>
    /*下拉刷新，上拉加载*/

    /*    $(".swiper2 .swiper-slide").scroll(function(){
           var scroll =  $(this).scrollTop();
           if(scroll == "0" ){
             /!*  window.history.back();*!/
               window.location.href='https://www.baidu.com/';
           }else{
               return;
           }
        });*/





    /*    $(document).ready(function(){
            $(window).scroll(function(){
                var scrollTop = $(this).scrollTop();               //滚动条距离顶部的高度
                var scrollHeight = $(document).height();                   //当前页面的总高度
                var windowHeight = $(this).height();                   //当前可视的页面高度
                if(scrollTop + windowHeight >= scrollHeight){        //距离顶部+当前高度 >=文档总高度 即代表滑动到底部
                    alert("上拉加载，要在这调用啥方法？");
                }else if(scrollTop<=0){         //滚动条距离顶部的高度小于等于0
                    alert("下拉刷新，要在这调用啥方法？");
                }
            });
        });*/
</script>
</body>
</html>