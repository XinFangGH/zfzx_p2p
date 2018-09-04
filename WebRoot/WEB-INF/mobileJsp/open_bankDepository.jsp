<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 开通银行存管</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        开通银行存管
    </div>
</div>
<form id="classForm" method="post" action="/webPhone/appRegAndBindWebPhoneThirdAction.do" >
<div class="changeTel yhcg">
    <div class="in_box">

        <label for="yhcg_name">真实姓名</label>
        <input id="yhcg_name" name="truename" type="text" placeholder="请输入你的真实姓名">
    </div>
    <div class="in_box">
        <label for="yhcg_code">证件号码</label>
        <input id="yhcg_code" name="cardcode" type="text" placeholder="请输入你的身份证号码">
    </div>
    <a href="javascript:;" class="btn" id="btn_chTel1">下一步</a>
</div>
</form>
<%--<div class="modal-box" style="display:none;">
    <div class="modal"></div>
    <div class="dialog">
        <p class="pt succ">成功</p>
        <p class="pt fail">失败</p>
        <p class="pb"><span id="fpwd_time">3</span>s</p>
    </div>
</div>--%>
<script>
    $('input').val("");
    $(function(){
        $("#btn_chTel1").click(function(){
            if($(this).hasClass('btn_1')) {
                $('#yhcg_name').val(trim($('#yhcg_name').val()));
                $("#classForm").submit();
            }
        });
    });

    function trim(str) {
        return str.replace(/\s+/g,"");
    }
    
    $(function(){
        $('#yhcg_code').keyup(function(){
            if($(this).val().length==18){
                $("#btn_chTel1").addClass('btn_l');
            }else{
                $("#btn_chTel1").removeClass('btn_l');
            }
        });
        $("#btn_chTel1").click(function(){
            if($(this).hasClass('btn_l')){
                $("#classForm").submit();
            }
        });
    });
</script>
<%--<script>
    $('#btn_chTel1').click(function () {
        var yhcg_name = $('#yhcg_name').val();
        var yhcg_code = $('#yhcg_code').val();
        var url = ${base}"/webPhone/appRegAndBindWebPhoneThirdAction.do";
        $.ajax({
            type:"post",
            data:{"cardcode":yhcg_code,"truename":yhcg_name},
            url:url,
            dataType:"json",
            success:function (data) {
                alert("1111111");
                if(data.code==200){
                    $(".modal-box .succ").html(data.msg).show().siblings('.fail').hide();
                    $('.modal-box').show();
                    popTime();
                    var t2=setTimeout(function(){
                        window.location.href="/webPhone/userValidWebPhoneCustMember.do";
                        clearTimeout(t2);
                    },3000);
                }
                if(data.code==500){
                    $(".modal-box .fail").html(data.msg).show().siblings('.succ').hide();
                    $('.modal-box').show();
                    popTime();
                    var t2=setTimeout(function(){
                        window.location.href="/webPhone/userValidWebPhoneCustMember.do";
                        clearTimeout(t2);
                    },3000);
                }
                if(data.code==501){
                    $(".modal-box .fail").html(data.msg).show().siblings('.succ').hide();
                    $('.modal-box').show();
                    popTime();
                    var t2=setTimeout(function(){
                        window.location.href="/webPhone/userValidWebPhoneCustMember.do";
                        clearTimeout(t2);
                    },3000);
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

</script>--%>

</body>
</html>