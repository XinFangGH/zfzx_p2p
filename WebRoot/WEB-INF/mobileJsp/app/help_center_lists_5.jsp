<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>资金存管</title>
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
        <%--资金存管--%>
    <%--</div>--%>
<%--</div>--%>
<div class="container ">
    <ul class="help_center_list">
        <li>
            <div class="slideToggle_lists"><span>什么是资金存管</span><span></span></div>
            <div class="slideToggle_show">
                <p>根据《网络借贷信息中介机构业务活动管理暂行办法》第二十八条“网络借贷信息中介机构应当实行自身资金与出借人和借款人资金的隔离管理，并选择符合条件的银行业金融机构作为出借人与借款人的资金存管机构。”和第三十五条“资金存管机构对出借人与借款人开立和使用资金账户进行管理和监督，并根据合同约定，对出借人与借款人的资金进行存管、划付、核算和监督。”“资金存管”是指这样一种安排，即网络借贷信息中介平台在银行业金融机构单独设立独立账户，将客户资金存入该等独立账户，并由银行业金融机构根据出借人、借款人发出的指令和合同约定，对该等资金的使用、划付进行管理和监督。</p>
            </div>
        </li>   <li>
            <div class="slideToggle_lists"><span>实现银行存管之后，是如何保障用户资金安全</span><span></span></div>
            <div class="slideToggle_show">
                <p>平台用户涉及资金的操作将直接对接至富滇银行存管账户，包括存管账户激活、绑卡、充值、提现等；平台无法直接操作用户的存管账户，确保用户存管账户的独立性。</p>
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