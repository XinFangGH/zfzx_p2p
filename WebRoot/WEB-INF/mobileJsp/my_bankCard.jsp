<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 -  我的银行卡</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
    <style>
        /*弹框*/
        .outerdiv_2{
            position:fixed;
            width:100%;
            height:100%;
            top:0;
            left:0;
            background:rgba(16,44,81,0.3);
            z-index:4;
            /*display:none;*/
            display:flex;
            justify-content:center;
            align-items:center;
        }

        .outerdiv_2 .zero{
            position: relative;
            width: 5.72rem;
            /* height:3.28rem;*/
            background: #FFFFFF;
            border-radius: 10px;
        }

        .outerdiv_2 .zero div:nth-child(1){
            width: 100%;
            height: 1.2rem;
            line-height: 1.5rem;
            text-align: center;
            font-size: 0.32rem;
            color: #1A1A1A;
        }
        .outerdiv_2 .zero div:nth-child(2){
            width: 5.12rem;
            height: 2rem;
            box-sizing: border-box;
            padding: 0.2rem 0;
            margin: 0 auto;
            line-height:0.4rem;
            /* text-align: center;*/
            font-size: 0.28rem;
            color: #1A1A1A;
        }

        .outerdiv_2 .zero  div:nth-child(3){
            width: 100%;
            height:1.2rem;
            line-height: 1.2rem;
            border-top: 1px solid #EDEDED;
            text-align: center;
            font-size:0.28rem;
            color: #6BA9FC;
        }

        .outerdiv_2 .forOne{
            position: relative;
            width: 5.72rem;
            /* height:3.28rem;*/
            background: #FFFFFF;
            border-radius: 10px;
        }

        .outerdiv_2 .forOne  div:nth-child(1){
            width: 100%;
            height: 1.2rem;
            line-height: 1.5rem;
            text-align: center;
            font-size: 0.32rem;
            color: #1A1A1A;
        }

        .outerdiv_2 .forOne div:nth-child(2){
            width: 5.12rem;
            height: 1.5rem;
            box-sizing: border-box;
            padding: 0.2rem 0;
            margin: 0 auto;
            line-height:0.4rem;
            /* text-align: center;*/
            font-size: 0.28rem;
            color: #1A1A1A;

        }

        .outerdiv_2 .forOne  ul{

            width: 100%;
            height: 1.2rem;
            border-top: 1px solid #EDEDED;
        }
        .outerdiv_2 .forOne ul li{
            display: inline-block;
            float: left;
            width: 49.5%;
            height: 1.2rem;
            line-height: 1.2rem;
            text-align: center;
            font-size: 0.28rem;
            color: #9B9B9B;
        }

        .outerdiv_2 .forOne ul li:nth-child(1) {
            color: #6BA9FC;
            border-right: 1px solid #EDEDED;
        }

        .outerdiv_2 .forOne ul li:nth-child(1) a{
            display: inline-block;
            width: 100%;
            height: 100%;
            color: #6BA9FC;
        }


        .outerdiv_2 .threetan{
            position: relative;
            width: 5.72rem;
            /* height:3.28rem;*/
            background: #FFFFFF;
            border-radius: 10px;
        }

        .outerdiv_2 .threetan  div:nth-child(1){
            width: 100%;
            height: 1.2rem;
            line-height: 1.5rem;
            text-align: center;
            font-size: 0.32rem;
            color: #1A1A1A;
        }

        .outerdiv_2 .threetan div:nth-child(2){
            width: 5.12rem;
            height: 1.5rem;
            box-sizing: border-box;
            padding: 0.2rem 0;
            margin: 0 auto;
            line-height:0.4rem;
            /* text-align: center;*/
            font-size: 0.28rem;
            color: #1A1A1A;

        }

        .outerdiv_2 .threetan  ul{

            width: 100%;
            height: 1.2rem;
            border-top: 1px solid #EDEDED;
        }
        .outerdiv_2 .threetan ul li{
            display: inline-block;
            float: left;
            width: 49.5%;
            height: 1.2rem;
            line-height: 1.2rem;
            text-align: center;
            font-size: 0.28rem;
            color: #9B9B9B;
        }

        .outerdiv_2 .threetan ul li:nth-child(1) {
            color: #6BA9FC;
            border-right: 1px solid #EDEDED;
        }

        .outerdiv_2 .threetan ul li:nth-child(1) a{
            display: inline-block;
            width: 100%;
            height: 100%;
            color: #6BA9FC;
        }


        .outerdiv_2 .fourtan{
            position: relative;
            width: 5.72rem;
            /* height:3.28rem;*/
            background: #FFFFFF;
            border-radius: 10px;
        }

        .outerdiv_2 .fourtan div:nth-child(1){
            width: 100%;
            height: 1.2rem;
            line-height: 1.5rem;
            text-align: center;
            font-size: 0.32rem;
            color: #1A1A1A;
        }
        .outerdiv_2 .fourtan div:nth-child(2){
            width: 5.12rem;
            height: 2rem;
            box-sizing: border-box;
            padding: 0.2rem 0;
            margin: 0 auto;
            line-height:0.4rem;
            /* text-align: center;*/
            font-size: 0.28rem;
            color: #1A1A1A;

        }

        .outerdiv_2 .fourtan  div:nth-child(3){
            width: 100%;
            height:1.2rem;
            line-height: 1.2rem;
            border-top: 1px solid #EDEDED;
            text-align: center;
            font-size:0.28rem;
            color: #6BA9FC;
        }

        .outerdiv_2 .fivetan{
            position: relative;
            width: 5.72rem;
            /* height:3.28rem;*/
            background: #FFFFFF;
            border-radius: 10px;
        }

        .outerdiv_2 .fivetan div:nth-child(1){
            width: 100%;
            height: 1.2rem;
            line-height: 1.5rem;
            text-align: center;
            font-size: 0.32rem;
            color: #1A1A1A;
        }
        .outerdiv_2 .fivetan div:nth-child(2){
            width: 5.12rem;
            height: 1.5rem;
            box-sizing: border-box;
            padding: 0.2rem 0;
            margin: 0 auto;
            line-height:0.4rem;
            /* text-align: center;*/
            font-size: 0.28rem;
            color: #1A1A1A;

        }

        .outerdiv_2 .fivetan  div:nth-child(3){
            width: 100%;
            height:1.2rem;
            line-height: 1.2rem;
            border-top: 1px solid #EDEDED;
            text-align: center;
            font-size:0.28rem;
            color: #6BA9FC;
        }
    </style>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        我的银行卡
    </div>
    <span onclick="relieveCard()" class="jb" id="jiebang">解绑</span>
</div>
<div class="my_bankCard">
    <!--不同银行不同class为银行拼音-->
    <div class="con_bg  ${bankCard.spell}">
        
        <div class="con">
            <span class="bank_pic" ></span>
            <div class="dt">
                <p class="pl">${bankCard.bankName}</p>
                <p class="pr">储蓄卡</p>
                <input id ="cardId" type="hidden" value="${bankCard.cardId}">
                <input type="hidden"  id="money"  value="${money}"/>
                <input type="hidden"  id="trueMoney"  value="${trueMoney}"/>
            </div>
            <div class="dc">${fn:substring(bankCard.cardNum,0,4 )}&nbsp;${fn:substring(bankCard.cardNum,4 ,8 )}&nbsp;${fn:substring(bankCard.cardNum,8,12 )}&nbsp;${fn:substring(bankCard.cardNum,12 ,16 )}&nbsp;${fn:substring(bankCard.cardNum,16 ,19 )}</div>
            <div class="db">
                <p class="lf">单笔限额（元）<span><fmt:formatNumber value="${bankCard.signDealQuota/10000}"  minFractionDigits="0"/></span>万</p>
                <p class="rt">单日限额（元）<span><fmt:formatNumber value="${bankCard.dayDealQuota/10000}"  minFractionDigits="0"/></span>万</p>
            </div>
        </div>
        <div class="pic_bg"></div>
    </div>
<%--    <div class="modal-box">
        <div class="modal"></div>
        <div class="dialog">
            <p class="pt">解绑提示</p>
            <p class="pc">您的账户中还有余额未提现，不能进行解绑操作，请全部提现后再进行解绑操作！</p>
            <p class="pb">
                <a href="javascript:void(0);" class="cancel">稍后操作</a>
                <a href="javascript:;inspectPutForwar()" class="widthdr">立即提现</a>
            </p>
        </div>
    </div>--%>
</div>
<%--弹框--%>
<div id="outerdiv_2" class="outerdiv_2" style="display:none;z-index: 10;position:absolute;">
    <div class="zero" id="zero" style="display: none">
        <div>解绑提示</div>
        <div id="hintContent">
            您的账户中还有余额未提现，您当前账户可提现金额为<i style="color:red;">0</i>元,当日充值或回款金额可在1-2工作日进行提现(节假日顺延)
        </div>
        <div id="confirm">确&nbsp;&nbsp;定</div>
    </div>
    <div id="forOne" class="forOne"  style="display: none">
        <div>解绑提示</div>
        <div>您的账户中还有余额未提现，由于银行限制,最小提现金大于1元,请您先进行充值后再进行提现</div>
        <ul>
            <li onclick="inspectRechargeMoney()">去充值</li>
            <li id="cancel">取消</li>
        </ul>
    </div>
    <div class="threetan" id="threetan" style="display: none">
        <div>解绑提示</div>
        <div>您当前账户有未提现金额，由于银行监管限制，请全部提现后再进行解绑</div>
        <ul>
            <li onclick="inspectPutForwar()">去提现</li>
            <li id="cancel_1">取消</li>
        </ul>
    </div>
    <div class="fourtan" id="fourtan" style="display: none">
        <div>解绑提示</div>
        <div>
            当日帐户内有不可提现金额,需全部提现后才能解绑银行卡，充值或回款金额可在1-2工作日进行提现(节假日顺延)
        </div>
        <div id="confirm_1">确&nbsp;&nbsp;定</div>
    </div>
    <div class="fivetan" id="fivetan" style="display: none">
        <div>解绑提示</div>
        <div>
            您当前账户还有资金冻结中，请等待标满放款后再进行解绑操作。
        </div>
        <div id="confirm_2">确&nbsp;&nbsp;定</div>
    </div>
</div>
<script>
    /*可用金额*/
    var money = parseFloat($("#money").val());
    /*可提现金额*/
    var trueMoney = parseFloat($("#trueMoney").val());
    /*卡号*/
    var cardId = $("#cardId").val();

   /* console.log(money);
    console.log(trueMoney);
    console.log(cardId);
    console.log(money == 0);
    console.log(money > "0" && trueMoney == "0");
    console.log(trueMoney > 0 &&  trueMoney <= "1");
    console.log(trueMoney > 0 &&  trueMoney <= "1");
    console.log(trueMoney > 1 && money == trueMoney);*/

    function relieveCard(){
        var url ='/webPhone/relieveCardWebFinancePurchaseAction.do';
        $.ajax({
            type:"post",
            url:url,
            dataType:"json",
            success:function (data) {
                if(data.data.code == 0){
                    /*解绑*/
                    window.location.href="/webPhone/appCancelBindCardWebPhoneThirdAction.do?cardNoId="+cardId+"&isApp="+1;
                }else if(data.data.code == 1){
                    /*1-2提取*/
                    $("#outerdiv_2").css({"display":"flex"});
                    $("#zero").css({"display":"block"});
                    $("#confirm").click(function(){
                        $("#outerdiv_2").css({"display":"none"});
                        $("#zero").css({"display":"none"});
                    });
                }else if(data.data.code == 2){
                    /*提示充值*/
                    $("#outerdiv_2").css({"display":"flex"});
                    $("#forOne").css({"display":"block"});
                    $("#cancel").click(function(){
                        $("#outerdiv_2").css({"display":"none"});
                        $("#forOne").css({"display":"none"});
                    });
                }else if(data.data.code == 3){
                    $("#outerdiv_2").css({"display":"flex"});
                    $("#fourtan").css({"display":"block"});
                    $("#confirm_1").click(function(){
                        $("#outerdiv_2").css({"display":"none"});
                        $("#fourtan").css({"display":"none"});
                    });
                }else if(data.data.code == 4){
                    /*直接提现*/
                    $("#outerdiv_2").css({"display":"flex"});
                    $("#threetan").css({"display":"block"});
                    $("#cancel_1").click(function(){
                        $("#outerdiv_2").css({"display":"none"});
                        $("#threetan").css({"display":"none"});
                    });
                }else if(data.data.code == 5){
                    /*冻结金额*/
                    $("#outerdiv_2").css({"display":"flex"});
                    $("#fivetan").css({"display":"block"});
                    $("#confirm_2").click(function(){
                        $("#outerdiv_2").css({"display":"none"});
                        $("#fivetan").css({"display":"none"});
                    });
                }
            }
            })






    }

    function inspectRechargeMoney() {
        var url = '/webPhone/inspectWebFinancePurchaseAction.do';
        $.ajax({
            url:url,
            type:"post",
            dataType:"json",
            success:function (data) {
                if(data.code==500){
                  /*  $(".tit").html(data.msg).show();
                    var time=setTimeout(function () {
                        $(".tit").hide();
                    },3000)*/
                }else if(data.code==9999){
                    window.location.href ='/webPhone/regLoghomePage.do';
                }else{
                    window.location.href ='/webPhone/rechargeMoneyWebFinancePurchaseAction.do';
                }
            }
        })
    }

    function inspectPutForwar(){
        var url = '/webPhone/inspectWebFinancePurchaseAction.do';
        $.ajax({
            url:url,
            type:"post",
            dataType:"json",
            success:function (data) {
                if(data.code==500){
                   /* $(".tit").html(data.msg).show();
                    var time=setTimeout(function () {
                        $(".tit").hide();
                    },3000);*/
                }else if(data.code==9999) {
                    window.location.href = '/webPhone/regLoghomePage.do';
                }else{
                    window.location.href='/webPhone/putForWardWebFinancePurchaseAction.do?trueMoney='+trueMoney;
                }
            }
        })
    }





//    if(data.code== 0){
//        /*解绑*/
//        window.location.href="/webPhone/appCancelBindCardWebPhoneThirdAction.do?cardNoId="+cardId+"&isApp="+1;
//
//    }else if(money > "0" && trueMoney == "0"){
//        /*1-2提取*/
//        $("#outerdiv_2").css({"display":"flex"});
//        $("#zero").css({"display":"block"});
//        $("#confirm").click(function(){
//            $("#outerdiv_2").css({"display":"none"});
//            $("#zero").css({"display":"none"});
//        });
//    }else if(trueMoney > 0 &&  trueMoney <= "1"){
//        /*提示充值*/
//        $("#outerdiv_2").css({"display":"flex"});
//        $("#forOne").css({"display":"block"});
//        $("#cancel").click(function(){
//            $("#outerdiv_2").css({"display":"none"});
//            $("#forOne").css({"display":"none"});
//        });
//    }else if(trueMoney > 1 && money > trueMoney){
//        $("#outerdiv_2").css({"display":"flex"});
//        $("#fourtan").css({"display":"block"});
//        $("#confirm_1").click(function(){
//            $("#outerdiv_2").css({"display":"none"});
//            $("#fourtan").css({"display":"none"});
//        });
//    }else if(trueMoney > 1 && money == trueMoney){
//        /*直接提现*/
//        $("#outerdiv_2").css({"display":"flex"});
//        $("#threetan").css({"display":"block"});
//        $("#cancel_1").click(function(){
//            $("#outerdiv_2").css({"display":"none"});
//            $("#threetan").css({"display":"none"});
//        });
//    }

</script>
</body>
</html>