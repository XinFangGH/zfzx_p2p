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
    <title>升升投 - 首页</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/common.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/swiper.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/index.css"/>
    <style>
        .container {
            width: 100%;
            background: #FFFFFF;
        }

        .swiper-container {
            height: 4rem;
            width: 7.5rem;
            overflow: hidden;
            margin: 0 auto;
            position: relative;
        }

        .swiper-container .swiper-slide {
            height: 100%;
        }

        .swiper-container .swiper-slide img {
            width: 7.5rem;
            height: 4rem;
        }

    </style>
</head>
<body>
<div class="container">
    <!--轮播图-->
    <div class="swiper-container swiperOne" style="width: 100%;" id="swiperMsg">
        <div class="swiper-wrapper">
            <c:forEach items="${bannerList}" var="banner">
            <div class="swiper-slide"><img  style="display:block;width:100%;" src="${base}${banner.url}"></div>
             </c:forEach>
        </div>
        <c:choose>
            <c:when test="${personUnreadMsg > 0}"><a href="${base}/webPhone/message_centerhomePage.do" class="swiperMsg"></a></c:when>
            <c:otherwise><a href="${base}/webPhone/message_centerhomePage.do" class="swiperMsg1"></a></c:otherwise>
        </c:choose>
        <%--<span class="swiperMsg"></span>--%>
    </div>

    <!--跑马灯-->
    <div class="my_ammount">
        <img class="ammount-ico" src="${base}/mobileNew/img/homepage/horn.png">
        <div class="my_content"  id="my_content_liu">
            <ul>
                <c:forEach items="${newsReport}" var="news">
                    <li id="my_content_li" class="my_content_li">${news.title}</li>
                </c:forEach>
            </ul>
        </div>
        <img class="ammount-ico1" src="${base}/mobileNew/img/homepage/combinedShape.png">
    </div>
    <!--累计注册用户-->
    <div class="cumulativeUsers">
        <ul>
            <li>
                <span></span>
                <div>
                    <p>累计注册用户</p>
                    <p>${showList[0]}<em>人</em></p>
                </div>
            </li>
            <li>
                <span></span>
                <div>
                    <p>用户累计投资</p>
                    <p><fmt:formatNumber value="${showList[1]/10000}" pattern="##.##" minFractionDigits="2"/><em>万元</em></p>
                </div>
            </li>
        </ul>
    </div>
    <!--新手专享-->
    <div class="novice">
        <div>
            <span></span>
            <p class="newer">新手<b>专享</b></p>
            <p class="describe">打开你的财富大门</p>
        </div>
        <div></div>
       <%-- <a href="/webPhone/mobilePlanDetailWebBidPlanAction.do?bidId=${newlist.bidId}">--%>
        <c:forEach items="${newcomerList}" var="newlist">
        <div>
            <p>预期年化利率</p>
            <p><fmt:formatNumber value="${newlist.yearInterestRate}" pattern="##.#" minFractionDigits="1" ></fmt:formatNumber><span>%</span></p>
        </div>
        <div>
            <p>${newlist.startMoney}元起投</p>
            <p>项目期限${newlist.loanLife}</p>
            <p>${newlist.theWayBack}</p>
        </div>
      <%--  </a>--%>
        <div id="nowLend" onclick=window.location.href="${base}/webPhone/mobilePlanDetailWebBidPlanAction.do?bidId=${newlist.bidId}">立即出借</div>
        </c:forEach>
    </div>
   <div class="selectedBox">
        <div>
            <span></span>
            <p class="newer">理财<b>精选</b></p>
            <p class="describe">高收益 放心投</p>
            <a href="/webPhone/planListhomePage.do"><p class="more">更多<i></i></p></a>
        </div>
        <div></div>
        <div>
            <c:forEach items="${indexPlanList}" var="index">
            <a   class="lease_ul"  href="${base}/webPhone/mobilePlanDetailWebBidPlanAction.do?bidId=${index.bidId}">
               <ul class="active_one">
                   <li>
                       <p class="standard">${fn:substring(index.proKeepType,0,2)}</p>
                       <p class="finance ">${index.bidProName}</p>
                       <c:if test="${index.showRate != null  && index.showRate != ''}">
                           <p class="increase">加息</p>
                       </c:if>
                   </li>
                   <li>
                       <ul>
                           <li>
                               <p>
                                   <span class="f56">
                                       <c:choose>
                                           <c:when test="${index.showRate != null}">
                                               <fmt:formatNumber value="${index.yearInterestRate-index.showRate}" pattern="##.#" minFractionDigits="1" >
                                               </fmt:formatNumber>
                                           </c:when>
                                           <c:otherwise>
                                               <fmt:formatNumber value="${index.yearInterestRate}" pattern="##.#" minFractionDigits="1" >
                                               </fmt:formatNumber>
                                           </c:otherwise>
                                       </c:choose>
                                   </span>
                                   <c:if test="${index.showRate != null}">
                                       <span class="f38">+<fmt:formatNumber value="${index.showRate}" pattern="##.#" minFractionDigits="1" ></fmt:formatNumber></span>
                                   </c:if><span class="f24">%</span>
                               </p>

                               <p>预期年化利率</p>
                           </li>
                           <li></li>
                           <li>
                               <p>${index.loanLife} | ${index.theWayBack}</p>
                               <p>剩余可投
                                   <c:choose>
                                   <c:when test="${index.afterMoney > 10000}"><fmt:formatNumber value="${index.afterMoney/10000}" pattern="##.##" minFractionDigits="2"/>万元</c:when>
                                   <c:otherwise><fmt:formatNumber value="${index.afterMoney}" pattern="##" minFractionDigits="0"/>元</c:otherwise>
                                   </c:choose>
                           </li>
                       </ul>
                   </li>
                </ul>
            </a>
            </c:forEach>
        </div>
   </div>
    <!--媒体报道-->
    <div class="media_news">
        <div class="dl" id="media_list"><p>媒体</p>报道</div>
        <div class="dr swiper4">
            <ul class="swiper-wrapper">
            <c:forEach items="${articleList}" var="article">
                <li class="swiper-slide"><a href=${base}"/webPhone/getWebMediahomePage.do?mediaId=${article.id}"><span class="sl">展信快讯 </span><span class="sr">| ${fn:substring(article.title,0,22)}... </span></a></li>
                <%--<li class="swiper-slide"><span class="sl">展信快讯2 </span><span class="sr">| 合规备案大考将至，你应该你知道的那些事儿！</span></li>
                <li class="swiper-slide"><span class="sl">展信快讯3 </span><span class="sr">| 合规备案大考将至，你应该你知道的那些事儿！</span></li>--%>
            </c:forEach>
            </ul>
        </div>
    </div>
    <!--安全保障 信息披露-->
    <div class="bottomPic">
        <a href=${base}"/webPhone/safety_guaranteehomePage.do">
             <div class="pic_one"></div>
        </a>
        <a href=${base}"/webPhone/informationhomePage.do">
            <div  class="pic_two"></div>
        </a>

    </div>
    <%@include file="foot.jsp"%>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/swiper.min.js"></script>
<script>
    var mySwiper = new Swiper(".swiperOne",{
        direction:"horizontal",/*横向滑动*/
        loop:true,/*形成环路（即：可以从最后一张图跳转到第一张图*/
        pagination:".swiper-pagination",/*分页器*/
        prevButton:".swiper-button-prev",/*前进按钮*/
        nextButton:".swiper-button-next",/*后退按钮*/
    /*    observer:true,
        observeParents:true,*/
        autoplay:3000,/*每隔3秒自动播放*/
        autoplayDisableOnInteraction : false
    });

    function scroll(){
        $("#my_content_liu ul").animate({"margin-top":"-28px"},function(){
            $("#my_content_liu ul li:eq(0)").appendTo($("#my_content_liu ul"));
            $("#my_content_liu ul").css({"margin-top":0});
        })
    }
    setInterval(scroll,2000);

    var prwidth = $(window).width()-72;
    $('.media_news .dr').css("width",prwidth);
    var swiper4 = new Swiper('.swiper4', {
        //freeMode: true,
        slidesPerView: '1.375',
        spaceBetween:10,
        setWrapperSize :true,
        freeModeSticky: true,
        slidesOffsetBefore:10,
        slidesOffsetAfter:10
    });


    $('.container .footerNav a').removeClass('selected').eq(0).addClass('selected');
    $('.homePageBox .homePage').css({"background-image":"url(${base}/mobileNew/img/footer_icon/home.png)","background-size":" 0.4rem 0.44rem"});

    $('#my_content_liu .my_content_li').each(function(i,li){
        var str=$(li).html();
        if(str.length>22){
            $(li).html(str.substring(0,22)+'...');
        }
    });

    $('#media_list').click(function () {
        window.location.href=${base}"/webPhone/htmlMediaListhomePage.do";
    });


    $('#my_content_liu ul li').click(function () {
        window.location.href=${base}"/webPhone/getWebArticleListhomePage.do";
    });

</script>
</body>
</html>
