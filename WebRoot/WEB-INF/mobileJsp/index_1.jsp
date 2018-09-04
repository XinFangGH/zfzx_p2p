<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 安全保障</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/swiper.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/newStyle.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/leaseDetails.css?version=180823"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/financeLeaseDetail.css"/>
    <style>
        html {
            font-size: 100px;
        }
    </style>
</head>
<body>
<div class="nav_box" style="display:none" >
    <div class="nav"  style="background-image:linear-gradient(48deg, #69B7FF 0%, #5E9EFE 100%);">
        <a href="javascript:history.go(-1);" class="back"></a>
        安全保障
    </div>
</div>
<div class="leaseContainer">
    <section class="swiper-container  swiper-container-one" style="height:100%;">
        <div class="swiper-wrapper" style="height:100%;">
            <!--第一屏幕-->
            <div class="swiper-slide page1" id="page1" style="height:100%;margin: 0 auto;/*padding-bottom: 1.5rem;*/" >
                <div class="BG">
                    <div class="BG_nav_box">
                        <div class="nav">
                            <a href="javascript:history.go(-1);" class="back"></a>
                            融资租赁
                        </div>
                    </div>
                    <div class="leaseDetails">
                        <ul class="leaseUl">
                            <li>
                                <p>
                                    <c:choose>
                                        <c:when test="${plBidPlan.showRate != null && plBidPlan.showRate != ''}"><fmt:formatNumber value="${plBidPlan.yearInterestRate - plBidPlan.showRate}" pattern="##0.0#"/><span>%</span>+<fmt:formatNumber value="${plBidPlan.showRate}" pattern="##0.0#"/><span>%</span></c:when>
                                        <c:otherwise><fmt:formatNumber value="${plBidPlan.yearInterestRate}" pattern="##0.0#"/> <span>%</span></c:otherwise>
                                    </c:choose>
                                </p>
                                <p>预期年化利率</p>
                            </li>
                            <li  class="verticalLine"></li>
                            <li>
                                <p>
                                    <c:choose>
                                        <c:when test="${plBidPlan.raiseRate != null}"><fmt:formatNumber value="${plBidPlan.raiseRate}" pattern="##0.0#"/><span>%</span></c:when>
                                        <c:otherwise>0.0<span>%</span></c:otherwise>
                                    </c:choose>
                                </p>
                                <p>募集期利率</p>
                            </li>
                        </ul>
                    </div>
                    <div class="progressBar">
                        <div class="progressBar_pending"></div>
                        <span class="progressBar_button"></span>
                        <div class="progressBar_title">
                            <span id="progressVal"><fmt:formatNumber value="${plBidPlan.progress}" pattern="##0.#"/>%</span>
                        </div>
                    </div>
                    <ul class="leaseBottom  leaseBottom_numberOne">
                        <li>
                            <c:choose>
                                <c:when test="${plBidPlan.state == 7}"><span>剩余募集时间</span><p>已结束</p></c:when>
                                <c:otherwise><span>剩余募集时间</span><p id="time"></p></c:otherwise>
                            </c:choose></li>
                        <li><span>剩余</span>
                            <p>
                                <c:choose>
                                    <c:when test="${plBidPlan.afterMoney >= 10000}"><fmt:formatNumber value="${plBidPlan.afterMoney/10000}" pattern=",##0.00#"/>万元</c:when>
                                    <c:otherwise><fmt:formatNumber value="${plBidPlan.afterMoney}" pattern=",##0"/>元</c:otherwise>
                                </c:choose>
                            </p></li>
                    </ul>

                    <ul  class="leaseBottom">
                        <li><span>期限</span><p>${plBidPlan.loanLife}</p></li>
                        <li><span>总额</span>
                            <p>
                                <c:choose>
                                    <c:when test="${plBidPlan.bidMoney > 10000}"><fmt:formatNumber value="${plBidPlan.bidMoney/10000}" pattern=",##0.00#"/>万元</c:when>
                                    <c:otherwise><fmt:formatNumber value="${plBidPlan.bidMoney}" pattern=",##0"/>元</c:otherwise>
                                </c:choose>
                            </p></li>
                    </ul>
                </div>
                <div class="warmHints">
                    <p>温馨提示：</p>募集期利息为投资日到满标当日所记利息，为平台单独发放的福利，与实际标的利息无关，募集期利息发放日期为满标当日。
                </div>
                <ul class="course">
                    <li>
                        <span></span>
                        <p>选标出借</p>
                    </li>
                    <li></li>
                    <li>
                        <span></span>
                        <p>开始计息</p>
                    </li>
                    <li></li>
                    <li>
                        <span></span>
                        <p>到期回款</p>
                    </li>
                    <li></li>
                    <li>
                        <span></span>
                        <p>提现到账</p>
                    </li>
                </ul>
                <div class="segmentingLine"></div>
                <ul class="information">
                    <li><span>产品名称</span><p>${plBidPlan.bidProName}</p></li>
                    <li><span>产品类型</span><p>${plBidPlan.proKeepType}</p></li>
                    <li><span>起息时间</span>
                        <p>
                            <c:choose>
                                <c:when test="${plBidPlan.startIntentDate != null}">${plBidPlan.startIntentDate}</c:when>
                                <c:otherwise>满标日期</c:otherwise>
                            </c:choose>
                        </p></li>
                    <li><span>截止日期</span><p>${plBidPlan.bidEndTime}</p></li>
                    <li><span>还款方式</span><p>${plBidPlan.theWayBack}</p></li>
                    <li><span>信用等级</span><p>${plBidPlan.keepCreditlevelName}</p></li>
                    <li><span>协议范本</span><p><a href="${base}/js/user/rongzixieyi.pdf" target="_blank" style="color: #6BA9FC;">《融资租赁协议》范本</a></p></li>
                </ul>
                <div class="leasePull">
                    <p>向上滑动查看更多信息 <span></span></p>
                </div>
            </div>
            <!--第二屏幕-->
            <div class="swiper-slide page1" id="page2" style="height:100%;">
                <div class="swiper-container swiper1">
                    <div class="swiper-wrapper" style="height:0.88rem!important;">
                        <div class="swiper-slide selected">标的详情</div>
                        <div class="swiper-slide">资料公示</div>
                        <div class="swiper-slide">出借记录</div>
                    </div>
                </div>
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
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide  slidePage" style="overflow-x: hidden;" id="Loan_record">
                            <ul>
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
                                <li>
                                    <span class="times">2018-4-26 06:41:14</span>
                                    <p><span>136****2959</span><span>65,000.00</span></p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<div class="footerButton">
    <div class="button">立即出借</div>
</div>
<div id="outerdiv_1" class="outerdiv_1" style="display: none">
    <div class="prompt_tube" style="display: none">
        <div class="BG"></div>
        <div>您需要先开通银行存管账户，才可以出借，充值，提现，转让</div>
        <div>立即开通</div>
        <div>暂不开通</div>
    </div>
    <div class="alertBindCard" style="display: none">
        <div>请先绑卡</div>
        <div>您还没有绑定银行卡，请先绑定银行卡</div>
        <ul>
            <li>稍后操作</li>
            <li>立即绑卡</li>
        </ul>
    </div>
    <div class="immediateTest"  style="display: none">
        <p>根据监管要求，出借之前需要先对您进行风险测评。</p>
        <a href="javaScript:(0);">立即测评</a>
    </div>
    <ul class="confirm" style="display: none">
        <li><span>剩余可投（元）</span><p>20000</p></li>
        <li><span>可用余额（元）</span><p>0.00</p></li>
        <li>
            <div>出借金额（元）<p>100元起投</p></div>
            <div>余额全投</div>
        </li>
        <li>*金额必须大于0且为整数</li>
        <li>立即出借</li>
        <li><input type="checkbox" name="layout" id="" checked="">请您在出借前仔细阅读 <p>《出借风险说明和禁止性行为说明书》</p></li>
    </ul>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fastclick.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/swiper.min.js"></script>
<script>
    //->解决click的300ms延迟
    FastClick.attach(document.body);

    //->初始化Swiper
    var swiperOne=new Swiper(".swiper-container-one", {
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
<script>
    $(function(){
        //剩余募集时间倒计时
        /*var time=parseInt($('#time').html())*1000;*/
        var time=parseInt(${plBidPlan.remainingTime})*1000;
        var t=setInterval(function () {
            if(time>0){
                var day = parseInt(time / 1000 / 60 / 60 / 24);
                var hour = parseInt(time / 1000 / 60 / 60 % 24);
                var minute = parseInt(time / 1000 / 60 % 60);
                var seconds = parseInt(time / 1000 % 60);
                $('#time').html(day + "天" + hour + "时" + minute + "分" );/*+ seconds + "秒"*/
                time-=1000;
            }

        }, 1000);
    });

    $(document).ready(function(){
        var Progress_bar  = $("#progressVal").html();
        $(".progressBar_pending").animate({"width":Progress_bar},1200);
        $(".progressBar_button").animate({"left":Progress_bar},1200);
        /* console.log(parseInt(Progress_bar));*/
        if(parseInt(Progress_bar) <= "3" ){
            Progress_bar = "3%"
        }else if(parseInt(Progress_bar) >= "94" ){
            Progress_bar = "94%"
        }
        $(".progressBar_title").animate({"left":Progress_bar},1200);

    });


    // 滑动滚动条
    $(window).scroll(function(){
// 滚动条距离顶部的距离 大于 44px时
        if($(window).scrollTop() >= 44){
            $(".nav_box").fadeIn(1000); // 开始淡入
        } else{
            $(".nav_box").stop(true,true).fadeOut(1000); // 如果小于等于 200 淡出
        }
    });

</script>
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

</body>
</html>