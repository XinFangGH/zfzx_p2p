<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 -  充值提现</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/help_center.css"/>
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
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        充值提现
    </div>
</div>
<div class="container ">
    <ul class="help_center_list">
        <li>
            <div class="slideToggle_lists"><span>如何充值</span><span></span></div>
            <div class="slideToggle_show">
                <p>为保证充值的正常进行，需开通富滇银行存管账户。</p>
                <p>A、网页端：登录升升投网官网，点击【我的账户】-【账户总览】-【充值】选择充值方式和充值金额后，跳转到富滇银行资金存管页面，填写交易密码，按照页面提示输入信息即可完成充值。</p>
                <p>B、微信端：登录升升投账户，点击【账户】-【充值/提现】-【充值】输入充值金额后，跳转到厦门银行资金存管页面，填写交易密码即可完成充值。</p>
            </div>
        </li> <li>
            <div class="slideToggle_lists"><span>充值限额</span><span></span></div>
            <div class="slideToggle_show">
                <p>升升投没有设置充值限额，充值时的单笔及当日转账上限为各个银行限制；</p>
            </div>
        </li> <li>
            <div class="slideToggle_lists"><span>充值到账时间</span><span></span></div>
            <div class="slideToggle_show">
                <p>A、正常情况下，充值即时到账。如果银行提示扣款之后账户余额没有相应增加，可能是存管系统延迟或者银行掉单导致：系统延迟会在10-30分钟后到账，到账后不影响正常使用；</p>
            </div>
        </li> <li>
            <div class="slideToggle_lists"><span>充值失败</span><span></span></div>
            <div class="slideToggle_show">
                <p>A、银行卡余额不足以支付充值金额;</p>
                <p>B、交易密码输入有误;</p>
                <p>C、银行卡出现挂失、注销或信息变更等状况;</p>
                <p>D、联系客服，寻求帮助（服务热线：400-9266-114）。</p>
            </div>
        </li><li>
            <div class="slideToggle_lists"><span>如何提现</span><span></span></div>
            <div class="slideToggle_show">
                <p>A、网页端：登录升升投官网，在【我的账户】-【提现】中输入您要提现的金额，在跳转的富滇银行资金存管页面中输入交易密码即可提现成功;</p>
                <p>B、微信端：登录升升投账户，点击【账户】-【充值/提现】-【提现】中输入您要提现的金额，在跳转的富滇银行资金存管页面中输入交易密码即可提现成功；</p>
            </div>
        </li> <li>
            <div class="slideToggle_lists"><span>提现手续费</span><span></span></div>
            <div class="slideToggle_show">
                <p>提现时富滇银行收取单笔一元的提现手续费，升升投不收取任何提现费用</p>
            </div>
        </li> <li>
            <div class="slideToggle_lists"><span>提现限额</span><span></span></div>
            <div class="slideToggle_show">
                <p>单笔最低提现2元，没有最高额度限制</p>
            </div>
        </li> <li>
            <div class="slideToggle_lists"><span>提现到账时间</span><span></span></div>
            <div class="slideToggle_show">
                <p>成功提交提现申请后，存管银行进行受理，T+1个工作日到账；遇双休日、国家法定节假日，到账日期顺延;</p>
            </div>
        </li> <li>
            <div class="slideToggle_lists"><span>提现未到账前是否能解绑银行卡</span><span></span></div>
            <div class="slideToggle_show">
                <p>成功提交提现申请后，未到账之前都不可进行解卡操作;</p>
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