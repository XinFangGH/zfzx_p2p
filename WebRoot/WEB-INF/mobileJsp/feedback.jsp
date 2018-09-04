<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 意见反馈</title>
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
<div class="nav_box" style="position:absolute;">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        意见反馈
    </div>
</div>
<div class="container ">
    <div class="feedback_box">
        <form action="" method="post" id ="formClass">
            <textarea name="feedback_textarea" id="feedback_textarea" class="feedback_textarea"  <%--cols="30" rows="10"--%>  style="resize:none" placeholder="请详细描述您的问题或建议，我们将及时跟进解决" onfocus="this.placeholder=''"   onblur="changeText(this)" onkeyup="changeText(this)"></textarea>
            <input  name="contact" id ="feedback_input" type="text" class="feedback_input" placeholder="手机号/邮箱（方便我们联系您）" value="" onfocus="this.placeholder=''" onblur="change(this)" onkeyup="change(this)">
            <div class="button">提  交</div>
        </form>
        <div class="modal-box" style="display:none;">
            <div class="modal"></div>
            <div class="dialog">
                <p class="pt succ">修改密码成功，请重新登录。</p>
                <p class="pt fail">修改密码失败，请重试。</p>
                <p class="pb"><span id="fpwd_time">3</span>s</p>
            </div>
        </div>
    </div>
    <p class="tit">密码错误，请重新输入！</p>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script>


    $(".button").click(function () {
        //反馈内容
        var content = $("#feedback_textarea").val();
        //联系方式
        var contact = $("#feedback_input").val();
        var url = ${base}"/webPhone/feedBackWebPhoneCustMember.do";
        //邮箱正则
        var  re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        //手机号正则
        var re1 = /^1\d{10}$/;

        if(content){
            if(contact){//反馈内容和联系方式是否为空
                //判断联系方式是否符合手机号和邮箱格式
                if(re.test(contact)||re1.test(contact)){
                    $.ajax({
                        type:"post",
                        url:url,
                        data:{"content":content,"contact":contact},
                        dataType:"json",
                        success:function (data) {
                            $(".modal-box").show();
                            var t = parseInt($("#fpwd_time").html());
                            if(data.code==200){
                                $(".modal-box .succ").show().html(data.msg).siblings(".fail").hide();
                            }

                            if(data.code==500){
                                $(".modal-box .fail").show().html(data.msg).siblings(".succ").hide();
                            }
                            var t1=setInterval(function () {
                                t--;
                                if(t) {
                                    $("#fpwd_time").html(t);
                                }else{
                                    $(".modal-box").hide();
                                    clearInterval(t1);
                                    if(data.code==500){
                                        $("#fpwd_time").html(3);
                                    }
                                    if(data.code==200){
                                        window.location.href=${base}'/webPhone/userValidWebPhoneCustMember.do';
                                    }

                                }
                            },1000)
                        }
                    })

                }else{
                    $(".tit").html("手机号或邮箱格式不对").show();
                    var t = setTimeout(function () {
                        $(".tit").hide();
                        clearTimeout(t);
                    },3000)
                }
            }else{
                $(".tit").html("联系方式不能为空").show();
                var t = setTimeout(function () {
                    $(".tit").hide();
                    clearTimeout(t);
                },3000)
            }

        }else {
            $(".tit").html("反馈内容不能为空").show();
            var t = setTimeout(function () {
                $(".tit").hide();
                clearTimeout(t);
            },3000)
        }
    });

    function change(){
        if(!$("#feedback_input").val()){
            $("#feedback_input").attr("placeholder","手机号/邮箱（方便我们联系您）");
        }
    }
    function changeText(){
        if(!$("#feedback_textarea").text()){
            $("#feedback_textarea").attr("placeholder","请详细描述您的问题或建议，我们将及时跟进解决");
        }
    }

</script>
</body>
</html>