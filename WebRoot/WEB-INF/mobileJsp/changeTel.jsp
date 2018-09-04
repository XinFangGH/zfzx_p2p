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
<div class="forget_pwd changeTel">
    <div class="dt"><span>${telNummber}</span></div>
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
        <a href="javascript:;" class="code_a" id ="send" onclick="createCode()">立即发送</a>
        <span style="display: none;color:#9b9b9b;margin-right:0.72rem;" class="code_a" id="time">60S</span>

        <input id="code" type="hidden"  value=""/>
    </div>
    <p class="tit">发送失败！</p>
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


    var flag_text=true;
    function createCode() {
        //弹框提示可能发送失败的原因
        if(flag_text&&$('#send').html()=="重新发送"){
            $('.modal-box_text').show();
            flag_text=false;
        }


        $('.code_box .code').val("");
        $('.code_box .code').eq(0).focus();
        var telPhone=$(".dt span").text();
        var codeType=3;
        var url=${base}"/appcodecreate.do";
        $.ajax({
            type: "get",
            url: url,
            data: {"telPhone":telPhone, "codeType":codeType},
            dataType: "json",
            success: function(data){
                //$(".changeTel .tit").html(data,msg).show();
                var t =setTimeout(function () {
                    $(".forget_pwd .tit").hide();
                },3000);
                $("#code").attr("value",data.data.code);
            },
            error: function () {
                $(".changeTel .tit").html("发送失败！").show();
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
</script>
<script type="text/javascript">
    $(function(){
        $('.code_box .code').val("");
        //————短信验证
        $('.code_box .code').focus(function(){
            $('.plceh').hide();
        });
        $('.code_box').click(function(){
            var isNull=true;
            $('.code_box .code').each(function(i,input){
                if($(input).val()){
                    isNull=false;
                }
            });
            if(isNull){
                $('.code_box .code').eq(0).focus();
            }
        });
        $(document).click(function(e){
            //(!$(e.target).closest('.code_box').hasClass('code'))&&(!$(e.target).hasClass('plceh'))
            if($(e.target).closest('.code_box').length==0){
                var isNull=true;
                $('.code_box .code').each(function(i,input){
                    if($(input).val()){
                        isNull=false;
                    }
                });
                if(isNull){
                    $('.plceh').show();
                }
            }
        });


        $('.code_box .code').keyup(function(e){
            var keycode = e.keyCode;
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
                //获取验证码
                var input1= $(" .lf input:nth-child(1) ").val();
                var input2= $(" .lf input:nth-child(2) ").val();
                var input3= $(" .lf input:nth-child(3) ").val();
                var input4= $(" .lf input:nth-child(4) ").val();
                var input5= $(" .lf input:nth-child(5) ").val();
                var input6= $(" .lf input:nth-child(6) ").val();
                var checkCode=input1+input2+input3+input4+input5+input6;
                //alert(checkCode)
                //短信验证码
                var code = $("#code").val();
                //alert(code)
                //验证验证码
                if(checkCode==code){
                    //alert("11111")
                    $.ajax({
                        type: "get",
                        url: "/webPhone/verifPhoneMobileWebChangePhoneAction.do",
                        data: {"verify_sms":checkCode,"isApp":1},
                        dataType: "json",
                        success: function(data){
                            if(data.code==200){
                                window.location.href="/webPhone/returnChangeTelWebChangePhoneAction.do";
                            }
                            //$(".changeTel .tit").html(data,msg).show();

                        },
                        error: function () {
                           /* $(".changeTel .tit").html("发送失败！").show();
                            var t =setTimeout(function () {
                                $(".forget_pwd .tit").hide();
                            },3000);*/
                        }
                    })
                    //window.location.href="/webPhone/verifPhoneMobileWebChangePhoneAction.do?verify_sms="+checkCode;
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

        $('.plceh').click(function(){
            $('.code_box .code:first-child').focus();
        });
    });
    //点击确定弹框隐藏
    $('#cancel').click(function(){
        $('.modal-box_text').hide();
    });
</script>
</body>
</html>