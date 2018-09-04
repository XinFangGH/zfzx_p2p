<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 绑定邮箱</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        绑定邮箱
    </div>
</div>
<div class="changeTel bindEmail1">
    <div class="dt1">系统已向您的邮箱<span>*****</span>发送了一封邮件</div>
    <div class="in_box">
        <label for="bEmail1_code">邮箱验证码</label>
        <input id="bEmail1_code" name="email" type="text" placeholder="请输入邮箱验证码">
    </div>
    <a href="javascript:;" class="btn" id="btn_bEmail1">绑定</a>
    <div class="modal-box" style="display: none">
        <div class="modal"></div>
        <div class="dialog">
            <p class="pt succ">邮箱绑定成功</p>
            <p class="pt fail">邮箱绑定失败</p>
            <p class="pb"><span id="fpwd_time">3</span>s</p>
        </div>
    </div>
</div>

<script>
    $('#btn_bEmail1').click(function () {
        var emailcode = $("#bEmail1_code").val();
        var url=${base}"/webPhone/appUpdateEmailTypeWebPhoneEmailAction.do";
        $.ajax({
            type:"post",
            data:{"emailcode":emailcode},
            url:url,
            dataType: "json",
            success:function (data) {
                if(data.data.status==1){
                    $(".modal-box .fail").html(data.msg).show().siblings('.succ').hide();
                    $('.modal-box').show();
                    popTime();
                }
                if(data.data.status==2){
                    $(".modal-box .fail").html(data.msg).show().siblings('.succ').hide();
                    $('.modal-box').show();
                    popTime();
                }
                if(data.data.status==3){
                    $(".modal-box .fail").html(data.msg).show().siblings('.succ').hide();
                    $('.modal-box').show();
                    popTime();
                }
                if(data.data.status==4){
                    $(".modal-box .fail").html(data.msg).show().siblings('.succ').hide();
                    $('.modal-box').show();
                    popTime();
                }
                if(data.data.status==5){
                    $(".modal-box .fail").html(data.msg).show().siblings('.succ').hide();
                    $('.modal-box').show();
                    popTime();
                }
                if(data.data.status==6){
                    $(".modal-box .fail").html(data.msg).show().siblings('.succ').hide();
                    $('.modal-box').show();
                    popTime();
                }
                if(data.data.status==7){
                    $(".modal-box .fail").html(data.msg).show().siblings('.succ').hide();
                    $('.modal-box').show();
                    popTime();
                }
                function popTime(){
                    var t=parseInt($('#fpwd_time').html());
                    var t1=setInterval(function(){
                        if(t){
                            t--;
                            $('#fpwd_time').html(t);
                        }else{
                            $('.modal-box').hide();
                            clearInterval(t1);
                            if(data.code==500){
                                $("#fpwd_time").html(3);
                            }
                        }
                    },1000);
                }
            },
            error: function () {
                alert("失败");
            }
        })
    })
</script>
</body>
</html>