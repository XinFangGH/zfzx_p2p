<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 修改登录密码</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        修改登录密码
    </div>
</div>
<div class="changeTel changePwd">
    <div class="in_box">
        <label for="chPwd_pwd">原密码</label>
        <input id="chPwd_pwd" type="password" placeholder="请输入原登录密码">
    </div>
    <div class="in_box">
        <label for="chPwd_pwd1">新密码</label>
        <input id="chPwd_pwd1" type="password" placeholder="请输入6位以上密码">
    </div>
    <div class="in_box">
        <label for="chPwd_pwd2">确认密码</label>
        <input id="chPwd_pwd2" type="password" placeholder="请再次输入新登录密码">
    </div>
    <a href="javascript:;onclick=check()" class="btn" id="btn_chPwd">确认</a>
    <div class="modal-box" style="display: none">
        <div class="modal"></div>
        <div class="dialog">
            <p class="pt succ">密码修改成功</p>
            <p class="pt fail">密码修改失败</p>
            <p class="pb"><span id="fpwd_time">3</span>s</p>
        </div>
    </div>
    <p class="tit"></p>
</div>
<script>
    $("#chPwd_pwd2").keyup(function () {
        var val = $("#chPwd_pwd2").val();
        if(val){
            $('#btn_chPwd').addClass('btn_l');
        }else{
            $('#btn_chPwd').removeClass('btn_l');
        }
    })
    function checkPwd(str){
//        var reg=/(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6,20}/;
        var reg=/[A-Za-z0-9]{6,20}/;
        return reg.test(str);
    }
    function check() {
        if( $('#btn_chPwd').hasClass('btn_l')){
            document.activeElement.blur();
            var val  =$('#chPwd_pwd2').val();
            if(checkPwd($("#chPwd_pwd2").val())){
                if($("#chPwd_pwd1").val()==val){
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


    }
    function affirm() {
        var chPwd_pwd = $("#chPwd_pwd").val();
        var chPwd_pwd1 = $("#chPwd_pwd1").val();
        var chPwd_pwd2 = $("#chPwd_pwd2").val();
        var url =${base}"/webPhone/alterPassWordWebPhoneCustMember.do"
        if(chPwd_pwd1==chPwd_pwd2){
            $.ajax({
                type:"post",
                url:url,
                data:{"passWord":chPwd_pwd,"newPassWord":chPwd_pwd2},
                dataType:"json",
                success: function (data) {
                    $(".modal-box").show();
                    if(data.code==500){
                        $(".modal-box .fail").html(data.msg).show().siblings(".succ").hide();
                    }
                    if(data.code==200){
                        $(".modal-box .succ").show().siblings(".fail").hide();
                    }
                    if(data.code)
                    var t = parseInt($("#fpwd_time").html());
                    var t1=setInterval(function () {
                        t--;
                        if(t){
                            $("#fpwd_time").html(t);
                        }else{
                            clearInterval(t1);
                            $(".modal-box").hide();
                            if(data.code==200){
                                window.location.href=${base}'/webPhone/account_setWebPhoneCustMember.do';
                            }
                            if(data.code==500){
                                $("#fpwd_time").html(3);
                            }
                        }
                    },1000)
                }

            })
        }else{
            $(".tit").html("两次输入密码不一致").show();
            var t = setTimeout(function () {
                    clearTimeout(t);
                    $(".tit").hide();
            },3000)

        }


    }
    
</script>
</body>
</html>