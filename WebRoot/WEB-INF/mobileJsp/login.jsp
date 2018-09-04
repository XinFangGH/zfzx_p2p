<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 登录</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>

    <script type="text/javascript">
        function login() {
            //获取手机号
            var loginname=${telPhone};
            //获取密码
            var password = $("#log_pwd").val();
            var url = ${base}"/webPhone/mobileEntryhomePage.do?isAjax=1";
            $.ajax({
                type:"post",
                url:url,
                data:{"loginname":loginname,"password":password},
                dataType:"json",
                success : function (data) {
                    if(data.code==200){
                        $(".tit").text(data.msg);
                        window.location.href="/webPhone/userValidWebPhoneCustMember.do";
                    }else{
                        $(".log_reg .tit").html(data.msg).show();
                        var t=setTimeout(function(){
                            $(".log_reg .tit").hide();
                            clearTimeout(t);
                        },3000);
                    }
                }

            })
        }
        /*$(function(){
            $("#btn_log").click(function(){
                if($(this).hasClass('btn_l')){

                    $("#classForm").submit();
                }
            });
        });*/
    </script>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        登录
    </div>
</div>
<form action="" method="post" id="classForm">
<div class="log_reg">
    <div class="dt"><span>${telPhone}</span></div>
    <div class="login">
        <input type="password" name="password" class="in" id="log_pwd" autocomplete="off" placeholder="请输入6位以上密码" value=""  style="box-shadow:0 0 0 60px #FFF inset;"/>
        <div class="clearfix">
            <a href="javascript:; onclick=forgetPassword()" class="to_forpwd">忘记密码</a>
        </div>
        <a href="javascript:;" class="btn" id="btn_log"  onclick="login()">登录</a>
    </div>
    <p class="tit">密码错误，请重新输入！</p>
</div>
</form>
<script>

    function forgetPassword() {
        var telPhone=${telPhone};
        window.location.href="/webPhone/forget_pwdWebPhoneCustMember.do?telPhone="+telPhone;
    }
</script>
</body>
</html>