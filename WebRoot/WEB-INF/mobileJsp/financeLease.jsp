<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 融资租赁</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/leaseDetails.css"/>
    <style>
        html {
            font-size: 100px;
        }

    </style>
</head>
<body>
<div class="fixed_nav_box" style="display: none">
    <div class="nav" style="background-image: linear-gradient(48deg, #69B7FF 0%, #5E9EFE 100%);">
        <a href="javascript:history.go(-1);" class="back"></a>
        融资租赁
    </div>
</div>
<div class="leaseContainer">
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
    <!--上拉加载内容   start-->
    <div class="upLoad">

    </div>
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
<script src="${base}/mobileNew/js/fontSize.js"></script>
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
            $(".fixed_nav_box").fadeIn(1000); // 开始淡入
        } else{
            $(".fixed_nav_box").stop(true,true).fadeOut(1000); // 如果小于等于 200 淡出
        }
    });

    /*https://www.jb51.net/article/46437.htm*/
    var stop=true;
    $(window).scroll(function(){
        totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());
        if($(document).height() <= totalheight){
            if(stop==true){
                stop=false;
                /*window.location.href = "financeLeaseDetial.jsp";*/
                /*window.location.href = "financeLeaseDetial.jsp";*/
                stop=true;
                /*  $.post("ajax.php", {start:1, n:50},function(txt){
                      $("#Loading").before(txt);
                      stop=true;
                  },"text");*/
            }
        }
    });

</script>
</body>
</html>