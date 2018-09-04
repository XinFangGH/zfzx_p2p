<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <title>升升投 - 关于</title>
    <link rel="stylesheet"  media="all" type="text/css" href="${base}/theme/${systemConfig.theme}/css/mobile.css" />
</head>
<body>
    <div id="wrap">
        <!-- 顶部   -->
        <div class="wad_header">
            <div class="wad_topbar">
                <a href="#" class="wad_bak"></a>
                <em>关于</em>
                <span class="wad_allnav"></span>
            </div>
            <#include "/WEB-INF/template/${systemConfig.theme}/mobilelayout/navbar.ftl">
        </div>

        <!-- 内容 -->
        <div class="wad_gywenandai">
            <h1>升升投</h1>
            <span class="wad_gywanspec">"P2P网络借贷"指借助网络进行信息沟通，在个人与个人间发生的小额借贷交易。</span>
            <a href="#">升升投：${base}</a>
            <span class="wad_gywanspec1">升升投向您承若</span>
            <p>①&nbsp;便捷高效，理财不分贵贱，借钱无需出门。</p>
            <p>②&nbsp;诚信透明，信息均经过专业的实际审核，并均有记录。</p>
            <p>③&nbsp;可靠安全，资金流转环境封闭，资金流向可查。</p>
            <span class="wad_gywanspec1">升升投重磅推出，四大主力贷款</span>
            <p>1、园丁贷 - 少有的教师专属服务</p>
            <p>2、薪资贷 - 万万没想到，足不出户能办到</p>
            <p>3、经营贷 - 私营企业主的临时输血站</p>
            <p>4、网商贷 - 淘宝掌柜终于全场大促销了，亲，包邮哦</p>
            <span class="wad_gywanspec1">升升投愿景</span>
            <p class="wad_gywanspec2">稳健经营、严格风控、<br />安全理财、极致体验，<br />成为每个人身边，<br />最便捷、最稳健、最安全的，<br />互联网融资、理财服务平台，<br />升升投携手易宝支付，<br />共同打造您信可的理财安全服务平台。</p>
            <h1 class="wad_gywanspec3">稳健安全&nbsp;&nbsp;便捷理财</h1>
            <span class="wad_gywanspec4">升升投商务顾问（北京）有限公司，是一家由北京中关村科技园区（昌平园）管委会核准成立的，科技网络金融信息服务公司。通过资深的团队管理运营与先进的网络互动平台相结合，为投资理财人与资金需求人搭建一个稳健、安全、便捷、透明的网络互动平台。依托升升投的网络平台，借款人足不出户即可实现高效、便捷、无抵押的借款需求，并且随着自己信用等级的提升，享受不同程度的专有权力；投资人在升升投平台上，可以轻松选择借款项目及投资金额，享受低起点、高收益、有保障、自动返还的理财回报。</span>
        </div>


        <!-- footer  -->
        <div class="footer">
            <div class="version"><a href="#">Phone版</a><a href="#">PAD版</a><a href="javascript:;">电脑版</a></div>
            <div class="footer_bottom">
                <p class="wad_lianxi"><a><img src="${base}/theme/${systemConfig.theme}/images/mobile/qq.png">4006918987</a><a href="tel:4006918987"><img src="${base}/theme/${systemConfig.theme}/images/mobile/phone1.png">4006-918-987</a></p>
            </div>
            <div class="footer_banquan">版权所有 升升投商务顾问（北京）有限公司<br>© 2014 </div>
        </div>
    </div>
    <script src='${base}/js/mobile/zepto.min.js'></script>
    <script type="text/javascript">
        $(".wad_allnav").click(function(){
            $(".wad_slidernav").toggle();
        });
    </script>
</body>
</html>
