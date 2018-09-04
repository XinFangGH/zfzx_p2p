
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 邀请好友</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
</head>
<body>
<div class="nav_box nav_bgf">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        邀请好友
    </div>
</div>
<div class="invite_reg">
    <img src="${base}/mobileNew/img/mypic/invite_reg1.jpg" alt="" />
    <img src="${base}/mobileNew/img/mypic/invite_reg2.jpg" alt="" />
    <div class="reg3_con">
        <img src="${base}/mobileNew/img/mypic/invite_reg3.jpg" alt="" />
        <p>嗨，我是<span>${fn:substring(telPhone,0,3)}****${fn:substring(telPhone,7,11)}</span>。我在使用升升投平台，现在邀请你，一起来理财吧！</p>
    </div>
    <div class="reg4_con">
        <div class="pt">
            <input class="in tel" id="tel" type="tel" placeholder="输入手机号" />
            <input  id="plainCode" type="hidden" value="${plainCode}" />
            <p class="tit1" style="display:none;">*用户已存在</p>
        </div>
        <div class="pc js_reg" style="display:none;">
            <input type="text" class="in" id="reg_pwd" placeholder="设置登录密码，最少六位"/>
            <div class="clearfix">
                <input type="text" id="checkCode" class="in lf code_in" placeholder="短信验证码"/>
                <a href="javascript:;onclick=createCode()" id ="send" class="in rt code">获取验证码</a>
                <span class="in rt code" style="display:none;color:#9b9b9b;text-align:center;padding-left:0;" id="time">60S</span>
            </div>
            <div class="xy">
                <span class="select">注册即视为同意</span><a href=${base}"/webPhone/service_agreementWebPhoneRegAction.do">《升升投注册协议》</a>
            </div>
        </div>
        <a href="javascript:" id="reg_btn" class="a_btn">立即注册</a>
    </div>
    <div class="modal-box" style="display:none;">
        <div class="modal"></div>
        <div class="dialog">
            <p class="pt succ">修改密码成功，请重新登录。</p>
            <p class="pt fail">修改密码失败，请重试。</p>
            <p class="pb"><span id="fpwd_time">3</span>s</p>
        </div>
    </div>
</div>
<p class="tit"></p>

<script>
    $(function () {
        function checkPwd(str){
            //var reg=/(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6,20}/;
            var reg=/[A-Za-z0-9]{6,20}/;
            return reg.test(str);
        }
        $('#tel').focus(function(){
            $('.tit1').hide();
        });
        $('#reg_btn').click(function () {
                if($('.js_reg').css('display')=="none"){
                    var url = ${base}"/webPhone/checkPhonehomePage.do";
                    var tel = $('#tel').val();
                    $.ajax({
                        url:url,
                        type:"post",
                        data:{"telPhone":tel,"isApp":1},
                        dataType:"json",
                        success:function (data) {
                            if(data.data.isRegister==0){
                                $('.js_reg').show();
                                //window.location.href=${base}"/webPhone/appInviteRegWebPhoneCustMember.do?plainCode="+${plainCode};
                            }else {
                                $(".invite_reg .tit1").html("*用户已存在").show();
                            }
                        }
                    });
                }else{
                    var passWord= $("#reg_pwd").val();
                    var telPhone = $("#tel").val();
                    var checkCode = $("#checkCode").val();
                    var recommand = $("#plainCode").val();
                    var url = ${base}"/webPhone/newsignWebPhoneRegAction.do";
                    if(checkPwd($('#reg_pwd').val())){
                        $.ajax({
                            type:"post",
                            url:url,
                            data:{"telPhone":telPhone,"passWord":passWord,"checkCode":checkCode,"recommand":recommand},
                            dataType:"json",
                            success:function (data) {
                                if(data.code==200){

                                    $(".modal-box .succ").show().html(data.msg).siblings(".fail").hide();
                                    $('.modal-box').show();
                                    var time = 3;
                                    var t=setInterval(function () {
                                        time--;
                                        if(time){
                                            $("#fpwd_time").html(time);
                                        }else {
                                            $(".modal-box").hide();
                                            clearInterval(t);
                                            $("#fpwd_time").html(3);
                                            //进入个人中心
                                            window.location.href=${bsae}"/webPhone/userValidWebPhoneCustMember.do";
                                        }
                                    },1000)
                                }else{
                                    $(".modal-box .fail").show().html(data.msg).siblings(".succ").hide();
                                    $('.modal-box').show();
                                    var time = 3;
                                    var t=setInterval(function () {
                                        time--;
                                        if(time){
                                            $("#fpwd_time").html(time);
                                        }else {
                                            $(".modal-box").hide();
                                            clearInterval(t);
                                            $("#fpwd_time").html(3);
                                        }
                                    },1000)
                                }
                            },
                            error:function (data) {
                                $(".invite_reg .tit1").html(data.msg).hide();
                                var t =setTimeout(function () {
                                    $(".invite_reg .tit1").hide();
                                },3000);
                            }
                        });
                    }else {
                        $(".tit").html("密码格式错误").show();
                        var t = setTimeout(function () {
                            $(".tit").hide();
                        },3000)
                    }
                }
        })
    })

    //发送验证码
    function createCode() {
        $('.code_box .code').val("");
        $('.code_box .code').eq(0).focus();
        //获取手机号
        var telPhone=$('#tel').val();
        //验证码类型
        var codeType=0;
        var url=${base}"/appcodecreate.do";
        $.ajax({
            type: "get",
            url: url,
            data: {"telPhone":telPhone, "codeType":codeType},
            dataType: "json",
            success: function(data){
                $(".invite_reg .tit1").html(data.msg).show();
                var t =setTimeout(function () {
                    $(".invite_reg .tit1").hide();
                },3000);
            },
            error: function () {
                $(".invite_reg .tit1").html("发送失败").show();
                var t =setTimeout(function () {
                    $(".invite_reg .tit1").hide();
                },3000);
            }
        })
        $("#send").hide();
        $("#time").show();
        var time =60;
        var t =setInterval(function () {
            time--;
            if(time){
                $("#time").html(time+"S").show();
            }else{
                $("#time").html("60s").hide();
                $("#send").text("重新发送").show();
                clearInterval(t);
            }
        },1000)
    }

</script>
</body>
</html>