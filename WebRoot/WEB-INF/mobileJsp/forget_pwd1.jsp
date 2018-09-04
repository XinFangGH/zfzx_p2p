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

</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-2);" class="back"></a>
        重置密码
    </div>
</div>
<div class="forget_pwd">
    <%--<input type="password" id="f_pwd" class="in" placeholder="请输入密码（大小写字母及数字混合组成）"/>--%>
    <input type="password" id="f_pwd" class="in" placeholder="请输入6位以上的密码"/>
    <input type="password" id="f_pwd1" class="in" placeholder="请再次输入密码"/>
    <a href="javascript:;" class="btn" id="fpwd_btn">确认</a>
    <p class="tit">密码错误，请重新输入！</p>
    <div class="modal-box" style="display:none;">
        <div class="modal"></div>
        <div class="dialog">
            <p class="pt succ">修改密码成功，请重新登录。</p>
            <p class="pt fail">修改密码失败，请重试。</p>
            <p class="pb"><span id="fpwd_time">3</span>s</p>
        </div>
    </div>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/commen.js"></script>
<script>
    //密码修改成功弹框
    function popTime(){
        var t=parseInt($('#fpwd_time').html());
        var t1=setInterval(function(){
            t--;
            if(t){
                $('#fpwd_time').html(t);
            }else{
                $('.modal-box').hide();
                clearInterval(t1);
                window.location.href="/webPhone/regLoghomePage.do";
            }
        },1000);
    }

    function checkPwd(str){
//        var reg=/(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6,20}/;
        var reg=/[A-Za-z0-9]{6,20}/;
        return reg.test(str);
    }
    $("#fpwd_btn").click(function () {
        if($('#fpwd_btn').hasClass("btn_l")){
            var val  =$('#f_pwd').val();
            if(checkPwd($("#f_pwd1").val())){
                if($("#f_pwd1").val()==val){
                    affirm();
                }else{
                    $(".tit").html("两次输入密码不一致").show();
                    var t = setTimeout(function () {
                        $(".tit").hide();
                    },3000)
                }
            }else {
                $(".tit").html("密码格式不正确").show();
                var t = setTimeout(function () {
                    $(".tit").hide();
                },3000)
            }




        }
    })
    function affirm() {
        //密码
        var passWord = $("#f_pwd1").val();
        var telPhone = ${telPhone};
        var checkCode = ${checkCode};
        $.ajax({
            type:"post",
            url:${base}"/webPhone/appResetPasswordWebPhoneCustMember.do",
            data:{"passWord":passWord,"telPhone":telPhone,"checkCode":checkCode},
            dataType:"json",
            success:function (data) {
                //alert(data.msg);
                if(data.code==200){
                    $('.modal-box .succ').show().siblings('.fail').hide();
                    $('.modal-box').show();
                    popTime();
                }
                if(data.code==500){
                    $('.modal-box .fail').show().siblings('.succ').hide();
                    $('.modal-box').show();
                    popTime();
                }
            },
            error:function(){
                $('.modal-box .fail').show().siblings('.succ').hide();
                $('.modal-box').show();
                popTime();
            }


        })

    }
</script>
</body>
</html>