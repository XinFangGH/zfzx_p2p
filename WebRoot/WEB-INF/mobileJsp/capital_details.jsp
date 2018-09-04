<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 资金明细</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/recharge.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/mobileNew/css/scrollLoad.css" />
    <style>
        html {
            font-size: 100px;
        }

        .container {
            width: 100%;
            background: #FFFFFF;
            /*margin-top: 0.88rem;*/
        }
        .list-wrapper{
            bottom:0;
        }
        .tit {
            display: none;
            position: fixed;
            transform: translate(-50%,-50%);
            left: 50%;
            top: 50%;
            padding: 0.1rem 0.3rem;
            font-size: 14px;
            color: #FFFFFF;
            line-height: 0.4rem;
            background: rgba(0,0,0,0.60);
            border-radius: 3px;
            z-index:9;
        }
    </style>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        资金明细
        <span class="screen" id="screen"></span>
    </div>
</div>
<div class=" list-wrapper list-wrapper-hook">
    <div>
        <div class="container" >
            <ul class="capital_details_list list-content-hook">
               <%-- <c:forEach items="${List}" var="list">
                <li>
                    <div>
                        <span>${list.transferTypeName}</span><span class="success">${list.msg}</span>
                    </div>
                    <div>
                        <p><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd"></fmt:formatDate></p>
                        <p><c:choose><c:when test="${list.transferType==1}">+</c:when>
                            <c:when test="${list.transferType==2}">-</c:when>
                        <c:otherwise > </c:otherwise></c:choose><span><fmt:formatNumber value="${list.incomMoney}"  minFractionDigits="2"/></span>元</p>
                    </div>
                </li>
                </c:forEach>--%>
               <%-- <li>
                    <div>
                        <span>提现</span><span  class="success">提现成功</span>
                    </div>
                    <div>
                        <p>2018-04-16 13:18:08</p>
                        <p>-<span>1000.00</span>元</p>
                    </div>
                </li>
                <li>
                    <div>
                        <span>充值</span><span class="fail">充值失败</span>
                    </div>
                    <div>
                        <p>2018-04-16 13:18:08</p>
                        <p>+<span>1000.00</span>元</p>
                    </div>
                </li>--%>
            </ul>
        </div>
        <div class="bottom-tip">
            <span class="loading-hook">查看更多</span>
        </div>
    </div>
</div>

<%--筛选 DOM--%>
<div id="outerdiv" class="outerdiv" style="display: none">
    <div class="screen_box">
        <div class="screen_title">
            筛选条件
        </div>
        <ul class="screen_list clearfloat">
            <%--<li class="active" id="screen_1">全部</li>
            <li id="screen_2">取现</li>
            <li id="screen_3">出借收益</li>
            <li id="screen_4">出借</li>
            <li id="screen_5">本金收回</li>
            <li id="screen_6">取现手续费</li>
            <li id="screen_7">借款人借款入账</li>
            <li id="screen_8">借款人还本付息</li>
            <li id="screen_9">剩余未还本息</li>
            <li id="screen_10">系统红包</li>
            <li id="screen_11">平台转账</li>
            <li id="screen_12">账户转账</li>--%>
        </ul>
        <div class="screen_title">
            时间筛选
        </div>
        <ul class="screen_list_1">
            <%--<li class="active_1" id="acreen_1">全部</li>--%>
            <%--<li>1个月内</li>--%>
            <%--<li>3个月内</li>--%>
        </ul>
        <ul class="bottom_btn">
            <li>重&nbsp;置</li>
            <li class="activeBtn" id="checkBtn">确&nbsp;认</li>
        </ul>
    </div>
</div>
<p class="tit">密码错误，请重新输入！</p>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/bscroll.js"></script>
<script src="${base}/mobileNew/js/capital_details.js"></script>
<script>
    /* $("#screen").click(function(){
         $("#outerdiv").css("display","block");
         $('body').css("overflow", "hidden")
     });*/

    $("#screen").click(function(){
        $("#outerdiv").fadeIn("slow",function(){
            $('body').css("overflow", "hidden");
        });
    });

    $(".screen_list").on('click','li',function(){
        if($(this).index()!=0){
            if($(this).hasClass("active")){
                $(this).removeClass("active");
            }else{
                $(this).addClass("active");
                $(".screen_list li").eq(0).removeClass("active");
            }
        }else{
            $(this).addClass("active").siblings('li').removeClass("active");
        }
    });

    $(".screen_list_1").on('click','li',function(){
        if (!$(this).hasClass("active_1")) {
            $(this).addClass("active_1").siblings('li').removeClass("active_1");
        }
    });

    /*$(".screen_list_1 li").click(function(){
        $(this).addClass("active_1").siblings("li").removeClass("active_1");
    });*/

    $(".bottom_btn li").click(function(){
        if($(this).index() == "0"){
            $(".screen_list li").eq(0).addClass("active").siblings("li").removeClass("active");
            $(".screen_list_1 li").eq(0).addClass("active_1").siblings("li").removeClass("active_1");
        }else if($(this).index() == "1"){
            $(this).addClass("activeBtn").siblings("li").removeClass("activeBtn");
            $(".screen_list li.active").html();
            $(".screen_list_1 li.active_1").html();

            $("#outerdiv").fadeOut("slow",function(){
                $('body').css("overflow", "auto")
            });

            /* $("#outerdiv").css("display","none");*/

        }

    });

    $('#screen').click(function () {
        var  url = ${base}"/webPhone/screeningWebConditionAction.do";
        $.ajax({
            url:url,
            data:{isApp:1},
            dataType:"json",
            success:function (data) {
                //$("#screen_1").html(data.screeningList.get(0)).show();
//                var llis = data.data.screeningList[0].name;
//                alert(llis);   i代表集合的索引
                var listo = data.data.screeningList[0].list;
                var listt = data.data.screeningList[1].list;
                var flagment="";
                var flagment2="";
                $.each(listo,function (i,list) {
                    if(i==0){
                        flagment+=`<li class="active" id="screen_1" data-i="`+(list.type?list.type:"")+`">`+list.name+`</li>`;
                    }else{
                        flagment+=`<li id="screen_1" data-i="`+list.type+`">`+list.name+`</li>`;
                    }

                    //console.log(list.name+":"+list.type);
                })
                $.each(listt,function (i,list) {
                    if(i==0){
                        flagment2+=`<li class="active_1" id="acreen_1" data-i="`+list.type+`">`+list.name+`</li>`;
                    }else{
                        flagment2+=`<li id="acreen_1" data-i="`+list.type+`">`+list.name+`</li>`;
                    }

                    //console.log(list.name+":"+list.type);
                })
                $('.screen_list').html(flagment);
                $('.screen_list_1').html(flagment2);
            }
        })
        //window.location.href="http://localhost:8081/webPhone/screeningWebConditionAction.do";
    })

</script>
</body>
</html>