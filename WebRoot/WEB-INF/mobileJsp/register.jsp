<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 注册</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
</head>
<body>
<div class="nav_box" style="position:absolute;">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        注册
    </div>
</div>
<div class="log_reg">
    <%--<form  id="fromClass" method="post">--%>
    <div class="dt"><span>${telPhone}</span></div>
    <div class="register">
        <div class="clearfix code_box">
            <span class="plceh">短信验证码</span>
            <div class="lf">
                <input type="tel" class="in code" maxlength="1" placeholder=""/>
                <input type="tel" class="in code"  maxlength="1"  placeholder=""/>
                <input type="tel" class="in code" maxlength="1"  placeholder=""/>
                <input type="tel" class="in code" maxlength="1"  placeholder=""/>
                <input type="tel" class="in code" maxlength="1"  placeholder=""/>
                <input type="tel" class="in code" maxlength="1"  placeholder=""/>
            </div>
            <a href="javascript:;onclick=createCode()" id ="send" class="code_a">立即发送</a>
            <span class="code_a" style="display:none;color:#9b9b9b;margin-right:0.9rem;" id="time">60S</span>
        </div>
       <%-- <input type="password" class="in" id="reg_pwd" placeholder="登录密码，大小写字母及数字混合组成"/>--%>
        <input type="password" class="in" id="reg_pwd" placeholder="请输入6位以上的密码"/>
        <input type="text" class="in" id="reg_rcm"placeholder="邀请码（选填）"/>

        <div class="xy">
            <span id="check" class="checked">注册即视为同意</span><a href="javascript:; onclick=service_agreement()">《升升投注册协议》</a>
        </div>
        <a href="javascript:;onclick=register()" class="btn" id="btn_reg">注册</a>
    </div>
    <%--</form>--%>
    <p class="tit">密码错误，请重新输入！</p>
        <div class="modal-box" style="display:none;">
            <div class="modal"></div>
            <div class="dialog">
                <p class="pt succ">修改密码成功，请重新登录。</p>
                <p class="pt fail">修改密码失败，请重试。</p>
                <p class="pb"><span id="fpwd_time">3</span>s</p>
            </div>
        </div>
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
    //验证码输入跳下一个
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


    //注册密码格式验证
    function checkPwd(str){
        //var reg=/(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6,20}/;
        var reg=/[A-Za-z0-9]{6,20}/;
        return reg.test(str);
    }
    function register(){
        var val= $("#reg_pwd").val();
        if($('#btn_reg').hasClass("btn_l")){
                reg();
        }else if(!checkPwd(val)) {
            $(".tit").html("密码格式不正确").show();
            var t = setTimeout(function () {
                $(".tit").hide();
            },3000)
        }else{
            $(".tit").html("请选中协议").show();
            var t = setTimeout(function () {
                $(".tit").hide();
            },3000)
        };

    }
    function reg() {
        //获取手机号
        var telPhone=$(".dt span").text();
        //获取验证码
        var input1= $(".log_reg .lf input:nth-child(1) ").val();
        var input2= $(".log_reg .lf input:nth-child(2) ").val();
        var input3= $(".log_reg .lf input:nth-child(3) ").val();
        var input4= $(".log_reg .lf input:nth-child(4) ").val();
        var input5= $(".log_reg .lf input:nth-child(5) ").val();
        var input6= $(".log_reg .lf input:nth-child(6) ").val();
        var checkCode=input1+input2+input3+input4+input5+input6;
        //获取密码
        var passWord = $("#reg_pwd").val();
        //获取邀请码
        var recommand = $("#reg_rcm").val();
        var url = ${base}"/webPhone/newsignWebPhoneRegAction.do";
        $.ajax({
            type:"post",
            url:url,
            data:{"telPhone":telPhone,"passWord":passWord,"checkCode":checkCode,"recommand":recommand},
            dataType:"json",
            success : function (data) {
                if(data.code==200){
//                    $(".tit").html(data.msg).show();
//                    var t =setTimeout(function () {
//                        $(".log_reg .tit").hide();
//                    },3000);
                    $(".modal-box").show();
                    $(".modal-box .succ").show().html(data.msg).siblings(".fail").hide();
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
//                    $(".tit").html(data.msg).show();
//                    var t =setTimeout(function () {
//                        $(".log_reg .tit").hide();
//                    },3000);
                    $(".modal-box").show();
                    $(".modal-box .fail").show().html(data.msg).siblings(".succ").hide();
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
            },error:function (data) {
                $(".log_reg .tit").html(data.msg).hide();
                var t =setTimeout(function () {
                    $(".log_reg .tit").hide();
                },3000);
            }

        })


    }
    //发送验证码
    var flag_text=true;
    function createCode() {
        //弹框提示可能发送失败的原因
        if(flag_text&&$('#send').html()=="重新发送"){
            $('.modal-box_text').show();
            flag_text=false;
        }


        $('.code_box .code').val("");
        $('.code_box .code').eq(0).focus();
        //获取手机号
        var telPhone=$(".dt span").text();
        //验证码类型
        var codeType=0;
        var url=${base}"/appcodecreate.do";
        $.ajax({
            type: "get",
            url: url,
            data: {"telPhone":telPhone, "codeType":codeType},
            dataType: "json",
            success: function(data){
                $(".log_reg .tit").html(data.msg).show();
                var t =setTimeout(function () {
                    $(".log_reg .tit").hide();
                },3000);
            },
            error: function () {
                $(".log_reg .tit").html("发送失败").show();
                var t =setTimeout(function () {
                    $(".log_reg .tit").hide();
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

    function service_agreement() {
        window.location.href='/webPhone/service_agreementWebPhoneRegAction.do'
    }



    //点击确定弹框隐藏
    $('#cancel').click(function(){
        $('.modal-box_text').hide();
    });
</script>
</body>
</html>