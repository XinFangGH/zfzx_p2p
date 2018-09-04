<%--<%@ taglib prefix="c" uri="/jodd" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 账户设置</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/help_center.css"/>
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
        <a href="/webPhone/userValidWebPhoneCustMember.do" class="back"></a>
        账户设置
    </div>
</div>
<div class="container ">
    <ul class="account_set">
        <li><c:choose>
            <c:when test="${empty bpCustMember.cardcode}"><a id="third_pay" href="javaScript:void(0);">实名认证
                <span></span>
                <p>去认证</p></a></c:when>
            <c:when test="${'2' eq bpCustMember.isCheckCard and not empty bpCustMember.cardcode}"><a id="third_payNex" href="javaScript:void(0);">实名认证
                <span></span>
                <p>认证失败，点击重新认证</p></a></c:when>
            <c:otherwise><a id="thirdPpay">实名认证
                <p class="cor_h">${fn:replace(bpCustMember.cardcode,fn:substring(bpCustMember.cardcode, 6,14 ),"********")}</p>
            </a></c:otherwise>
        </c:choose></li>
        <li><c:if test="${isBankCard==0}">
            <a href="javaScript:; onclick=bankCard()" id = "bankCard">我的银行卡 <span></span>
            <p>去绑定</p></a>
        </c:if>
            <c:if test="${isBankCard==1}">
                <a href="javaScript:; onclick=bankCard() " id = "bankCard">我的银行卡 <span></span>
                    <p class="cor_h">已绑定</p></a></c:if></li>
        <li><a href="javaScript:; onclick=bindPhone()">绑定手机 <span></span>
            <p id="tel" class="cor_h">${fn:substring(bpCustMember.telphone,0,3)}****${fn:substring(bpCustMember.telphone,7,11)}</p></a></li>
        <%--<li><a href="javaScript:void(0);">绑定邮箱 <span></span><p>去绑定</p></a></li>--%>
        <li><a href="javaScript:void(0);">银行认证
            <c:if test="${thirdPayFlagId!=''}">
                <p class="cor_h">${thirdPayFlagId}</p>
            </c:if>
            <c:if test="${empty thirdPayFlagId}">
                <p>未认证</p>
            </c:if>
            </a></li>
        <li><a href="${base}/webPhone/evaluateWebPhoneCustMember.do?flag=2">风险承受能力评估 <span></span>
            <c:choose>
                <c:when test="${bpCustMember.grade == null}"><p  style="color: #6BA9FC;">立即评估</p></c:when>
                <c:otherwise><p class="cor_h">${bpCustMember.grade}</p></c:otherwise>
            </c:choose>
            </a></li>
        <li><a href="javaScript:void(0);" onclick=alterPassword()>密码管理 <span></span>
            <p></p></a></li>
    </ul>
    <p class="tit">密码错误，请重新输入！</p>
    <div class="exit_logon" id="exit_login"><a>退出登录</a></div>
</div>

<div class="modal-box" style="display:none;">
    <div class="modal"></div>
    <div class="dialog">
        <p class="pt fail">邮箱绑定失败</p>
        <p class="pb"><span id="fpwd_time">3</span>s</p>
    </div>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script>
    window.addEventListener('pageshow', function(e) {
        // 通过persisted属性判断是否存在 BF Cache
        if (e.persisted) {
            location.reload();
        }
    });

    /*正则  3-7 为*号*/
    $(function () {
        var tel = $('#tel').html();
        var mtel = tel.substr(0, 3) + '****' + tel.substr(7);
        $('#tel').text(mtel);
    });
//    $("#bankCard").click(function() {
//        var url = '/webPhone/getBindBankListWebPhoneCustMember.do';
//        $.ajax({
//            url: url,
//            type: "post",
//            dataType: "json",
//            success: function (data) {
//                if (data.code == 200) {
//                    window.location.href = '/webPhone/my_banckWebPhoneCustMember.do';
//                }
//                //没有银行卡
//                if (data.code == 9999) {
//                    window.location.href = '/webPhone/bankCardWebPhoneCustMember.do';
//                }
//                //未实名
//                if (data.code == 500) {
//                    $(".tit").html(data.msg).show();
//                    var t = setTimeout(function () {
//                        $(".tit").hide();
//                        clearTimeout(t);
//                    }, 3000)
//
//                }
//
//            }
//        })
//    })

    function bankCard() {
     var url = '/webPhone/getBindBankListWebPhoneCustMember.do';
        $.ajax({
            url:url,
            type:"post",
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    window.location.href='/webPhone/my_banckWebPhoneCustMember.do';
                }
                //没有银行卡
                if(data.code==9999){
                    window.location.href='/webPhone/bankCardWebPhoneCustMember.do';
                }
                //未实名
                if(data.code==500){
                    $(".tit").html(data.msg).show();
                    var t=setTimeout(function () {
                        $(".tit").hide();
                        clearTimeout(t);
                    },3000)

                }

            }
        })




    }

    function bindPhone() {
        window.location.href = '/webPhone/changeTelhomePage.do?telNummber=' +${bpCustMember.telphone};
    }

    function alterPassword() {
        window.location.href = '/webPhone/changePwdWebPhoneCustMember.do';
    }

    $('#exit_login').click(function () {
        var url = ${base}"/user/isSessinoValueBpCustMember.do";
        $.ajax({
            type: "post",
            url: url,
            data: {"isApp": 1},
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    window.location.href = '/webPhone/userValidWebPhoneCustMember.do';
                }
            }
        })
    })
    $('#third_payNex').click(function () {
        var url = ${base}"/webPhone/appThirdRemWebPhoneRegAction.do";
        $.ajax({
            type:"post",
            url:url,
            data:{},
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    $.ajax({
                    type:"post",
                    url: ${base}"/webPhone/appThirdWebPhoneRegAction.do",
                    data:{"mobile": 1},
                    dataType: "json",
                    success:function (data) {
                        if (data.code == 200) {
                            window.location.href = '/webPhone/thirdPayBindWebPhoneRegAction.do';
                        }else{
                            $(".modal-box .fail").html(data.msg).show();
                            $('.modal-box').show();
                            popTime();
                        }
                    }
                    })
                    //window.location.href = '/webPhone/thirdPayBindWebPhoneRegAction.do';
                }else{
                    $(".modal-box .fail").html(data.msg).show();
                    $('.modal-box').show();
                    popTime();
                }
            }
        })

    })

    $('#third_pay').click(function () {
        var url = ${base}"/webPhone/appThirdWebPhoneRegAction.do";
        $.ajax({
            type: "post",
            url: url,
            data: {"mobile": 1},
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    window.location.href = '/webPhone/thirdPayBindWebPhoneRegAction.do';
                } else {
                    $(".modal-box .fail").html(data.msg).show();
                    $('.modal-box').show();
                    popTime();

                    function popTime() {
                        var t = parseInt($('#fpwd_time').html());
                        var t1 = setInterval(function () {
                            if (t) {
                                t--;
                                $('#fpwd_time').html(t);
                            } else {
                                $('.modal-box').hide();
                                clearInterval(t1);
                                if (data.code == 500) {
                                    $("#fpwd_time").html(3);
                                }
                            }
                        }, 1000);
                    }
                }
            }
        })
    })
</script>
</body>
</html>