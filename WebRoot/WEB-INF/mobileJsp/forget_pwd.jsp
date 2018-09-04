<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 -  忘记密码</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        重置密码
    </div>
</div>
<div class="forget_pwd">
    <div class="clearfix code_box">
        <span class="plceh">短信验证码</span>
        <div class="lf">
            <input type="tel" class="in code" placeholder="" maxlength="1"/>
            <input type="tel" class="in code" placeholder="" maxlength="1"/>
            <input type="tel" class="in code" placeholder="" maxlength="1"/>
            <input type="tel" class="in code" placeholder="" maxlength="1"/>
            <input type="tel" class="in code" placeholder="" maxlength="1"/>
            <input type="tel" class="in code" placeholder="" maxlength="1"/>
        </div>
        <a href="javascript:;onclick=sendCode()" id="send" class="code_a">立即发送</a>
        <span style="display: none;color:#9b9b9b;margin-right:0.72rem;" class="code_a" id="time">60S</span>

        <input  id= "code" type="hidden"  value=""/>
        <input  id= "telPhone" type="hidden"  value="${telPhone}"/>
    </div>
    <p class="tit">密码错误，请重新输入！</p>
    <div class="modal-box_text" style="display:none;">
        <div class="modal"></div>
        <div class="dialog">
            <p class="pt">温馨提示</p>
            <p class="pc">A、请确认手机是否安装短信拦截或过滤软件；<br>
                B、请确认手机是否能够正常接收短信（信号问题、欠费、停机等）；<br>
                C、短信收发过程中可能会存在延迟，请耐心等待；<br>
                如果只有这个手机号收不到验证码，说明此手机号码被运营商屏蔽<br>
                原因：用户曾经将此类通知短信向运营商投诉过为垃圾短信<br>
                解决方法：联系运营商取消拦截营销短信。</p>
            <p class="pb" id="cancel">确定</p>
        </div>
    </div>
</div>
<script>
    $('.code_box .code').keyup(function(e){
        var keycode = e.keyCode;
        /*alert(keycode);*/
        var isNull=true;
        var isVal=false;
        $('.code_box .code').each(function(i,input){
            if($(input).val()){
                isNull=false;
            }else{
                isVal=true;
            }
        });
        if(!isVal){
            //获取手机号
            var telPhone= $("#telPhone").val();
            //获取验证码
            var input1= $(" .lf input:nth-child(1) ").val();
            var input2= $(" .lf input:nth-child(2) ").val();
            var input3= $(" .lf input:nth-child(3) ").val();
            var input4= $(" .lf input:nth-child(4) ").val();
            var input5= $(" .lf input:nth-child(5) ").val();
            var input6= $(" .lf input:nth-child(6) ").val();
            var checkCode=input1+input2+input3+input4+input5+input6;
            //短信验证码
            var code = $("#code").val();
            //验证验证码
            if(checkCode==code){
                window.location.href="/webPhone/forget_pwd1WebPhoneCustMember.do?checkCode="+checkCode+"&telPhone="+telPhone;
            }else{
                //alert("验证码不正确")
                $('.forget_pwd .tit').html("验证码不正确").show();
                var t = setTimeout(function () {
                    $('.forget_pwd .tit').hide();
                },3000);
            }
        }
        if(keycode=="8"){
            $(this).val("");
            if(navigator.userAgent.indexOf('UCBrowser') > -1||navigator.userAgent.indexOf('UCWEB')>-1){
                $(this).prev().trigger("click");
            }else{
                $(this).prev().focus();
            }

        }else if($(this).val()){
            if(navigator.userAgent.indexOf('UCBrowser') > -1||navigator.userAgent.indexOf('UCWEB')>-1){
                $(this).next().trigger("click");
            }else{
                $(this).next().focus();
            }
        }
    });

    var flag_text=true;
    function sendCode() {
        //弹框提示可能发送失败的原因
        if(flag_text&&$('#send').html()=="重新发送"){
            $('.modal-box_text').show();
            flag_text=false;
        }


        $('.code_box .code').val("");
        $('.code_box .code').eq(0).focus();
        var telPhone= $("#telPhone").val();
        var codeType=1;
        var url=${base}"/appcodecreate.do";
        $.ajax({
            type: "get",
            url: url,
            data: {"telPhone":telPhone, "codeType":codeType},
            dataType: "json",
            success: function(data){
                $(".forget_pwd .tit").html(data.msg).show();
                var t =setTimeout(function () {
                    $(".forget_pwd .tit").hide();
                },3000);
                $("#code").attr("value",data.data.code);
            },
            error: function () {
                $(".forget_pwd .tit").html("发送失败").show();
                var t =setTimeout(function () {
                    $(".forget_pwd .tit").hide();
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
    //点击确定弹框隐藏
    $('#cancel').click(function(){
        $('.modal-box_text').hide();
    });
</script>
</body>
</html>