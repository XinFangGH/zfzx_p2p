<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 -  标的类</title>
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
        标的类
    </div>
</div>
<div class="container ">
    <ul class="help_center_list">
        <li>
            <div class="slideToggle_lists"><span>标的发布时间</span><span></span></div>
            <div class="slideToggle_show">
                <p>工作日固定发标时间在12:00、17:00，其余时间与周末随机发标;</p>
            </div>
        </li> <li>
            <div class="slideToggle_lists"><span>投资成功后起息时间</span><span></span></div>
            <div class="slideToggle_show">
                <p>A、若投资日标的未满标，则投资当日按照募集期利率开始计息；</p>
                <p>B、若投资日为满标日，则投资当日按照年化利率开始计息；</p>
            </div>
        </li> <li>
            <div class="slideToggle_lists"><span>募集期利息</span><span></span></div>
            <div class="slideToggle_show">
                <p>升升投为保证用户资金利益最大化，从用户投资日到满标日期间按照募集期利率开始计息，直到满标放款后，开始按照年华收益率计息</p>
            </div>
        </li> <li>
            <div class="slideToggle_lists"><span>募集期时间</span><span></span></div>
            <div class="slideToggle_show">
                <p>成功投资日到满标放款日期间为募集期时间</p>
            </div>
        </li> <li>
            <div class="slideToggle_lists"><span>如何查看收益</span><span></span></div>
            <div class="slideToggle_show">
                <p>A、网页端：登录升升投官网，点击【我的账户】-【我的散标】-【回款中】的标的查看收益 ；</p>
                <P>B、微信端：登录升升投账户，点击【账户】-【散标出借】-【回款中】的标的查看收益；</P>
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