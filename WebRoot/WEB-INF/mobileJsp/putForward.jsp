<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 提现</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/swiper.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/recharge.css"/>
    <style>
        html {
            font-size: 10px;
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
        提现
    </div>
</div>
<div class="container">
    <ul class="put_Forward">
        <li>
            <p>可提现金额（元）</p>
            <p><input id="trueMoney" type="hidden" value="<fmt:formatNumber value='${fn:replace(trueMoney,"," ,"" )}' pattern="##0" maxFractionDigits="2"></fmt:formatNumber>"/>${trueMoney}
            </p>
        </li>
        <li>
            <p>到账银行</p>
            <p>${bankCard.bankName}（尾号${bankCard.cardNum}）</p>
        </li>
        <li>
            <p><span>提现金额</span><input type="number" id="amount"
                      name="amount"  <%--type="number" step="0.01" name="amount" placeholder="提现金额"  onkeyup="clearNoNum(this)"--%>
                      placeholder="" onkeyup="clearNoNum()" onblur="clearNoNum()"></p>
            <p id="full_presentation" style="cursor: pointer">全部提现</p>
        </li>
    </ul>
    <div class="rightCorner">
        <p class="rightCorner_1">*每笔收取提现手续费1元</p>
        <p class="rightCorner_2" style="display: none">*实际到账&nbsp;<span id="drawing_out"></span>（手续费1元）</p>
        <p class="rightCorner_3" style="display: none">余额不足&nbsp;,无法提现</p>
        <p class="rightCorner_4" style="display: none">提现金额必须大于1元</p>
    </div>
    <div onclick="putForWard()"  class="recharge">
        <input type="hidden" id="cardId" value="${bankCard.cardId}">
        提&nbsp;现
    </div>
    <input id="fee" value="1" type="hidden">
</div>
<%--弹框--%>
<div id="outerdiv_2" class="outerdiv_2" style="display:none;z-index: 10;position:absolute;">
    <div id="forOne" class="forOne"  style="display: none">
        <div>温馨提示</div>
        <div>由于银行限制，最小提现金额需大于1元</div>
        <%--<ul>
            <li  onclick="inspectRechargeMoney()">去充值</li>
            <li id="cancel" >取消</li>
        </ul>--%>
        <div id="cancel">确&nbsp;&nbsp;定</div>
    </div>
    <div class="threeYuan" id="threeYuan" style="display: none">
        <div>温馨提示</div>
        <div>您当前提现金额为<i id="cash"></i>元，剩余金额不大于1元，请全部提出。</div>
        <div id="confirmThree">确&nbsp;&nbsp;定</div>
    </div>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script>
    /*给输入框添加函数*/
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

        var trueMoney = parseFloat($("#trueMoney").val());
        var fee = parseFloat($("#fee").val());
        var amount = parseFloat($("input[name='amount']").val());
        if (amount <= 0 && trueMoney>0) {
            $(".rightCorner_4").show().siblings("p").hide();
            return
        }

      /* if(amount <= 0){
            $(".rightCorner_3").show().siblings("p").hide();
            return
        }*/

        if(amount == 1){
           /* $("#drawing_out").html(0 + "元");*/
            $(".rightCorner_4").show().siblings("p").hide();
        }

        if (amount > trueMoney) {
            $("input[name='amount']").val(trueMoney);
            var money= parseFloat($("input[name='amount']").val());
            if(trueMoney == 0){
                $(".rightCorner_3").show().siblings("p").hide();
                return
            }else if(amount == 1){
                $("#drawing_out").html(1 + "元");
                $(".rightCorner_2").show().siblings("p").hide();
                return
            } else{
                var drawing_out = money - fee;
                drawing_out = drawing_out.toFixed(2);
                $("#drawing_out").html(drawing_out + "元");
                $(".rightCorner_2").show().siblings("p").hide();
                return
            }
        }

        if (amount > 0) {
            if (trueMoney < amount) {
                $(".rightCorner_3").show().siblings("p").hide();
            } else if (amount > fee && trueMoney >= amount) {
                var drawing_out = amount - fee;
                drawing_out = drawing_out.toFixed(2);
                $("#drawing_out").html(drawing_out + "元");
                $(".rightCorner_2").show().siblings("p").hide();
            }
        } else {
            $(".rightCorner_1").show().siblings("p").hide();
        }
    }


    $("#full_presentation").click(function () {
        //全部提现
        $("input[name='amount']").val($("#trueMoney").val());
        clearNoNum();
    });

    function putForWard() {
        var trueMoney = parseFloat($("#trueMoney").val());
        var cardId = $("#cardId").val();
        var amount = parseFloat($("#amount").val());
        $("#cash").html(amount);

        if(parseFloat($("#amount").val()) <=  1 ){
            $("#outerdiv_2").css({"display":"flex"});
            $("#forOne").css({"display":"block"});
            $("#cancel").click(function(){
                $("#outerdiv_2").css({"display":"none"});
                $("#forOne").css({"display":"none"});
            });
        }else if((trueMoney - amount) <=  "1" && (trueMoney - amount)!= "0"){
            $("#outerdiv_2").css({"display":"flex"});
            $("#threeYuan").css({"display":"block"});
            $("#confirmThree").click(function(){
                $("#outerdiv_2").css({"display":"none"});
                $("#threeYuan").css({"display":"none"});
                $("#amount").val(parseFloat($("#trueMoney").val()));
                $("#drawing_out").html(($("#amount").val() - 1).toFixed(2));
            });
        } else  if($("#amount").val() == ""){
            $(".rightCorner_4").show().siblings("p").hide();
        }else  if($("#amount").val() > "1"){
            window.location.href = "/webPhone/appWithdrawsWebPhoneThirdAction.do?amount=" + amount + "&cardId=" + cardId+"&isApp="+1;
        }


       /*  if (amount > 0 && trueMoney >= amount) {
                window.location.href = "/webPhone/appWithdrawsWebPhoneThirdAction.do?amount=" + amount + "&cardId=" + cardId+"&isApp="+1;
            } else if (trueMoney < amount) {
                $(".rightCorner_4").show().siblings("p").hide();
         }*/
       }

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
</script>
</body>
</html>