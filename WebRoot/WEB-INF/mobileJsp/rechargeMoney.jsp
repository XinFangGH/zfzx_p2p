<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 充值</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/swiper.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/recharge.css"/>
    <style>
        html {
            font-size: 100px;
        }

        .container {
            width: 100%;
            background: #FFFFFF;
            margin-top: 0.88rem;
        }

    </style>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        充值
    </div>
</div>
<div class="container">
    <ul class="rechargeMoney">
        <li>
            <c:choose>
                <c:when test="${fn:contains(bankCard.bankLogo,'null')}">
                    <div class="blankAtl" style="margin-left: 0.3rem;">
                        <p>${bankCard.bankName}（尾号${bankCard.cardNum}）<span class="quota_list">支持银行限额</span></p>
                        <p>单笔限额 <span><fmt:formatNumber value="${bankCard.signDealQuota/10000}"  minFractionDigits="0"/>万元</span></p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="blank" style="background: url(${bankCard.bankLogo})no-repeat center; background-size:100%"></div>
                    <div class="blankAtl">
                        <p>${bankCard.bankName}（尾号${bankCard.cardNum}）<span class="quota_list">支持银行限额</span></p>
                        <p>单笔限额 <span><fmt:formatNumber value="${bankCard.signDealQuota/10000}"  minFractionDigits="0"/>万元</span></p>
                    </div>
                </c:otherwise>
            </c:choose>
        </li>
        <li>
            <p>充值金额</p>
            <input type="number" name = "amount" id ="amount" class="money" onkeyup="clearNoNum()" onblur="this.placeholder='';clearNoNum()" placeholder="" onfocus="this.placeholder=''">
        </li>
        <li>*大额充值请登录官方网站选择网关充值</li>
    </ul>
    <a href="javascript:void(0);recharge()" class="recharge">充&nbsp;值</a>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script>
    var u = navigator.userAgent;
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    if(isiOS){
        //alert('iOS');
        $("#amount").attr("type","number");
    }

    if(isAndroid){
        //alert('isAndroid');
        $("#amount").attr("type","tel");
    }

    function clearNoNum() {
        //先把非数字的都替换掉，除了数字和.
        $("input[name='amount']").val($("input[name='amount']").val().replace(/[^\d.]/g, ""));
        //保证只有出现一个.而没有多个.
        $("input[name='amount']").val($("input[name='amount']").val().replace(/\.{2,}/g, "."));
        //必须保证第一个为数字而不是.
        $("input[name='amount']").val($("input[name='amount']").val().replace(/^\./g, ""));
        //保证.只出现一次，而不能出现两次以上
        $("input[name='amount']").val($("input[name='amount']").val().replace(".", "$#$").replace(/\./g, "").replace("$#$", "."));
        //只能输入两个小数
        $("input[name='amount']").val($("input[name='amount']").val().replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'));
    }

    function recharge () {
        var amount =$("#amount").val();
            if(amount>10000000){
                $(".rechargeMoney li:nth-child(3)").html("*最大充值金额为1000万").css({color:"red"});
                $("#amount").val("");
            }else{
                window.location.href="/webPhone/appRechargeWebPhoneThirdAction.do?payType="+1+"&amount="+amount+"&isApp="+1;
            }

    }
//跳限额表
    $(".quota_list").click(function () {
        window.location.href='/webPhone/bankCard1WebFinancePurchaseAction.do'
    })
</script>
</body>
</html>