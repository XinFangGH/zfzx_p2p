<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 更换手机号</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        更换手机号
    </div>
</div>
<form id="classForm" type="get" action="/webPhone/updatePhoneAppWebChangePhoneAction.do">
<div class="changeTel changeTel1">
    <div class="in_box">
        <label for="chTel1_tel">新手机号</label>
        <input id="chTel1_tel" name="telPhone" type="tel" placeholder="请输入您的新手机号">
    </div>
    <div class="in_box">
        <label for="chTel1_code">验证码</label>
        <input id="chTel1_code" name="verify_sms" type="tel" placeholder="请输入短信验证码">
        <a href="javascript:;" onclick="createCode()" id="send">立即发送</a>
        <span style="display: none;color:#9b9b9b;margin-right:0.72rem;" class="code_a" id="time">60S</span>
    </div>
    <input id="code" type="hidden"  value="${code}"/>
    <p class="tit"></p>
    <a href="javascript:;" class="btn" id="btn_chTel1" >确定</a>
    <div class="modal-box" style="display: none">
        <div class="modal"></div>
        <div class="dialog">
            <p class="pt succ">更换手机号成功</p>
            <p class="pt fail">更换手机号失败</p>
            <p class="pb"><span id="fpwd_time">3</span>s</p>
        </div>
    </div>
</div>
</form>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/commen.js"></script>
<script type="text/javascript">
    window.addEventListener('pageshow', function(e) {
        // 通过persisted属性判断是否存在 BF Cache
        if (e.persisted) {
            location.reload();
        }
    });
    $('#chTel1_code').val("");
    function createCode() {
        var telPhone=$("#chTel1_tel").val();
        //alert(telPhone)
        var codeType=3;
        var url=${base}"/webPhone/isMobilePhoneWebChangePhoneAction.do";
        $.ajax({
            type: "get",
            url: url,
            data: {"telPhone":telPhone,"codeType":codeType},
            dataType: "json",
            success: function(data){
                //alert("000000")
                //$(".changeTel .tit").html(data,msg).show();
                if(data.code == 200){
                    //alert(data.data.code);
                    $(".changeTel1 .tit").html(data.msg).show();
                    var t1 =setTimeout(function () {
                        $(".changeTel1 .tit").hide();
                        clearTimeout(t1);
                    },3000);
                    //倒计时秒数
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
                    $("#code").attr("value",data.data.code);
                }
                if(data.code == 500){
                    $(".changeTel1 .tit").html(data.msg).show();
                    var t =setTimeout(function () {
                        $(".changeTel1 .tit").hide();
                    },3000);
                }
                if(data.code == 501){
                    $(".changeTel1 .tit").html(data.msg).show();
                    var t =setTimeout(function () {
                        $(".changeTel1 .tit").hide();
                    },3000);
                }
            },
            error: function () {
                $(".changeTel .tit").html("发送失败！").show();
                var t =setTimeout(function () {
                    $(".changeTel1 .tit").hide();
                },3000);
            }
        })
    }
</script>
<script type="text/javascript">
    $(function(){
        $("#btn_chTel1").click(function(){
            var checkCode = $("#chTel1_code").val();
            var telPhone = $("#chTel1_tel").val();
            var code = $("#code").val();
            if(checkCode==code){
                if($(this).hasClass('btn_l')) {
                    $("#classForm").submit();
                }
            }else {
                $(".changeTel .tit").html("验证码不正确！").show();
//                    var t =setTimeout(function () {
//                        $(".changeTel1 .tit").hide();
//                    },3000);
            }
        });
    });
//    $(function(){
//        $('#btn_chTel1').click(function(){
//
//                var checkCode = $("#chTel1_code").val();
//                //alert(checkCode);
//                var code = $("#code").val();
//                var telPhone = $("#chTel1_tel").val();
//                //alert(code);
//                if(checkCode==code){
//                   // window.open("/webPhone/updatePhoneAppWebChangePhoneAction.do?isApp=1&verify_sms="+checkCode+"&telPhone="+telPhone);
//                    if($(this).hasClass('btn_l')){
//                        $.ajax({
//                            type: "get",
//                            data: {"verify_sms":checkCode,"telPhone":telPhone},
//                            url: "/webPhone/updatePhoneAppWebChangePhoneAction.do?",
//                            dataType: "json",
//                            success:function (data) {
//
//                            },
//                            error: function () {
//                               /* $(".changeTel .tit").html("系统错误！").show();
//                                var t =setTimeout(function () {
//                                    $(".changeTel1 .tit").hide();
//                                },3000);*/
//                            }
//                        })
//                    }
//                }else {
//                    $(".changeTel .tit").html("验证码不正确！").show();
//                    var t =setTimeout(function () {
//                        $(".changeTel1 .tit").hide();
//                    },3000);
//                }
//
//        });
//
//    });
</script>
</body>
</html>