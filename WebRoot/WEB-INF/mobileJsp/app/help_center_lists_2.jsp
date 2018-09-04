<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>绑定银行卡</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/help_center.css"/>
    <style>
        html {
            font-size: 100px;
        }

        .container {
            width: 100%;
            background: #FFFFFF;
            margin-top: 0rem;
        }

    </style>
</head>
<body>
<%--<div class="nav_box">--%>
    <%--<div class="nav">--%>
        <%--<a href="javascript:history.go(-1);" class="back"></a>--%>
        <%--绑定银行卡--%>
    <%--</div>--%>
<%--</div>--%>
<div class="container ">
    <ul class="help_center_list">
        <li>
            <div class="slideToggle_lists"><span>如何绑卡</span><span></span></div>
            <div class="slideToggle_show">
                <p>新用户只需在注册过程中填写身份证号码、银行卡号、密码等信息即可快速绑定银行卡。银行卡需要是本人名下的有效期内的借记卡。</p>
            </div>
        </li>
        <li>
            <div class="slideToggle_lists"><span>绑卡支持哪些银行</span><span></span></div>
            <div class="slideToggle_show">
                <p>绑定银行卡页面有支持银行卡的列表</p>
            </div>
        </li><li>
            <div class="slideToggle_lists"><span>可以绑卡数量</span><span></span></div>
            <div class="slideToggle_show">
                <p>升投目前只能绑定一张提现银行卡暂不支持多张银行卡绑定。</p>
            </div>
        </li><li>
            <div class="slideToggle_lists"><span>如何设置交易密码</span><span></span></div>
            <div class="slideToggle_show">
                <p>在开通富滇银行存管账户时，会要求投资人设置交易密码，此密码将做为投资人充值、提现、投资等操作时的交易密码。</p>
            </div>
        </li><li>
            <div class="slideToggle_lists"><span>绑卡失败</span><span></span></div>
            <div class="slideToggle_show">
                <p>A、根据页面提示的报错信息，对自己填写的姓名、身份证号码、银行卡号码、手机号（与银行卡预留手机号保持一致）进行校对，确认填写无误。</p>
                <p>B、暂不支持您输入的银行卡，建议更换银行卡。</p>
                <p>C、注册时手机号与银行卡预留手机号不一致（受富滇银行政策影响，升升投注册手机号必须与银行卡预留手机号保持一致），必须先修改手机号后再进行绑卡操作；</p>
                <p>D、联系客服，寻求帮助（服务热线：400-9266-114）。</p>
            </div>
        </li><li>
            <div class="slideToggle_lists"><span>交易密码如何找回</span><span></span></div>
            <div class="slideToggle_show">
                <p>若投资人需要修改或找回交易密码，需要进入富滇银行管理页面进行操作。</p>
            </div>
        </li><li>
            <div class="slideToggle_lists"><span>如何解绑</span><span></span></div>
            <div class="slideToggle_show">
                <p>A、网页端：点击【我的账户】-【账户总览】-【银行卡】-【解绑】，跳转到富滇银行解绑银行卡界面，输入【图像验证码】，输入【短信验证码】，【交易密码】，点击【确认】，完成解绑操作</p>
            </div>
        </li><li>
            <div class="slideToggle_lists"><span>绑卡解绑是否收取手续费</span><span></span></div>
            <div class="slideToggle_show">
                <p>绑卡解绑操作升升投平台不收取任何费用</p>
            </div>
        </li><li>
            <div class="slideToggle_lists"><span>什么情况下会出现解绑失败</span><span></span></div>
            <div class="slideToggle_show">
                <p>A、银行卡出现挂失、注销或信息变更等状况。</p>
                <p>B、账户内存在未提现的余额；</p>
                <p>C、账户内余额提现未到账时；</p>
            </div>
        </li><li>
            <div class="slideToggle_lists"><span>账户有余额能否解绑银行卡</span><span></span></div>
            <div class="slideToggle_show">
                <p>账户内有余额存在时不允许进行解绑银行卡操作，必须先将账户余额全部提取后才可进行解绑操作</p>
            </div>
        </li>
    </ul>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script>
    $(document).ready(function(){
        $(".slideToggle_lists").click(function(){/*slideToggle();*/
            $(this).next(".slideToggle_show").slideDown("slow");
            $(this).parent("li").siblings("li").find(".slideToggle_show").slideUp()

        });
    });
</script>
</body>
</html>