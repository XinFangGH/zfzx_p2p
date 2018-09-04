
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 充值提现</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/swiper.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/recharge.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
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
        充值提现
    </div>
</div>
<div class="container">
    <div class="money_pic"></div>
    <input type="hidden"  id="presented"  value="<fmt:formatNumber value='${fn:replace(trueMoney,"," ,"" )}' pattern="##0" maxFractionDigits="2"></fmt:formatNumber>"/>
    <ul  class="money_imf">
        <li>
            <p>可用余额（元）</p>
            <p>${availableInvestMoney}</p>
        </li>
        <li>
            <p>可提现金额（元）</p>
            <p>${trueMoney}</p>
        </li>
    </ul>
    <div  onclick="inspectRechargeMoney()" class="recharge">充&nbsp;值</div>
    <div  onclick="inspectPutForwar()" class="putForward">提&nbsp;现</div>
    <p class="prompt">*温馨提示：当日充值及回款金额请于1-2个工作日<br>后提取（节假日顺延）。</p>
    <p class="tit">密码错误，请重新输入！</p>
</div>
<%--弹框--%>
<div id="outerdiv_2" class="outerdiv_2" style="display:none;z-index: 10;position:absolute;">
    <div class="zero" id="zero" style="display: none">
        <div>温馨提示</div>
        <div id="hintContent">您当前可提现金额为<i style="color:red;">0</i>元，当日充值或回款金额，可在1-2个工作日后进行提现（节假日顺延）。</div>
        <div id="confirm">确&nbsp;&nbsp;定</div>
    </div>
    <div id="forOne" class="forOne"  style="display: none">
        <div>温馨提示</div>
        <div>由于银行限制，最小提现金额需大于1元请您先进行充值后再进行提现。</div>
        <ul>
            <li onclick="inspectRechargeMoney()">去充值</li>
            <li id="cancel">取消</li>
        </ul>
    </div>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script>
    function inspectRechargeMoney() {
        var url = '/webPhone/inspectWebFinancePurchaseAction.do';
        $.ajax({
            url:url,
            type:"post",
            dataType:"json",
            success:function (data) {
                if(data.code==500){
                    $(".tit").html(data.msg).show();
                    var time=setTimeout(function () {
                        $(".tit").hide();
                    },3000)
                }else if(data.code==9999){
                    window.location.href ='/webPhone/regLoghomePage.do';
                }else{
                  window.location.href ='/webPhone/rechargeMoneyWebFinancePurchaseAction.do';
                }
            }
        })
    }

    function inspectPutForwar() {
        if(parseFloat($("#presented").val()) == "0"){
            $("#outerdiv_2").css({"display":"flex"});
            $("#zero").css({"display":"block"});
            $("#confirm").click(function(){
                $("#outerdiv_2").css({"display":"none"});
                $("#zero").css({"display":"none"});
            });
        }else if(parseFloat($("#presented").val()) <=  1 ){
            $("#outerdiv_2").css({"display":"flex"});
            $("#forOne").css({"display":"block"});
            $("#cancel").click(function(){
                $("#outerdiv_2").css({"display":"none"});
                $("#forOne").css({"display":"none"});
            });
        }else{
            var url = '/webPhone/inspectWebFinancePurchaseAction.do';
            var trueMoney = $(".money_imf li:nth-child(2) p:nth-child(2)").text();
            $.ajax({
                url:url,
                type:"post",
                dataType:"json",
                success:function (data) {
                    if(data.code==500){
                        $(".tit").html(data.msg).show();
                        var time=setTimeout(function () {
                            $(".tit").hide();
                        },3000);
                    }else if(data.code==9999) {
                        window.location.href = '/webPhone/regLoghomePage.do';
                    }else{
                        window.location.href='/webPhone/putForWardWebFinancePurchaseAction.do?trueMoney='+trueMoney;
                    }
                }
            })
        }
    }
</script>
</body>
</html>