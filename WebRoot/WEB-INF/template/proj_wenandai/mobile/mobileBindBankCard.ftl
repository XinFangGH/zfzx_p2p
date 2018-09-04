<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <title>升升投 - 银行卡信息</title>
    <link href="${base}/theme/${systemConfig.theme}/css/mobile.css" rel="stylesheet" media="all">
</head>
<style>
.bank_list {
    -webkit-box-shadow: 0 1px 0 #ccc;
    -moz-box-shadow: 0 1px 0 #ccc;
    box-shadow: 0 1px 0 #ccc;
}
</style>
<body onload="${onloadurl}">
    <div id="wrap">
        <!-- 顶部   -->
        <div class="wad_header">
            <div class="wad_topbar">
                <a href="#" class="wad_bak"></a>
                <em>银行卡信息</em>
                <span class="wad_allnav"></span>
            </div>

            <#include "/WEB-INF/template/${systemConfig.theme}/mobilelayout/navbar.ftl">
        </div>
<#list listBankCard as list>
        <div class="bank_list">
            <!-- 银行卡信息 -->
            <div class="wad_xinyongxinxi">
                <ul>
                    <li class="wad_bottomtop"><div class="wad_item">银行卡信息</div></li>
                    <li>
                        <div class="wad_item">
                            <label>姓名：</label>
                            <span>${list.username}</span>
                        </div>
                    </li>
                    <li>
                        <div class="wad_item">
                            <label>银行：</label>
                            <span>${list.bankname}</span>
                        </div>
                    </li>
                    <li>
                        <div class="wad_item">
                            <label>卡号：</label>
                            <span><#if list.cardNum!=null><#if list.cardNum!="">${list.cardNum?substring(0,4)}****${list.cardNum?substring(12)}<#else>***********</#if><#else>***********</#if></span>
                        </div>
                    </li>
                    <li>
                        <div class="wad_item">
                            <label>绑卡状态：</label>
                            <span>${list.bindCardStatusmsg}</span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
</#list>
        <!-- footer 
        <div class="footer">
            <div class="version"><a href="#">Phone版</a><a href="#">PAD版</a><a href="javascript:;">电脑版</a></div>
            <div class="footer_bottom">
                <p class="wad_lianxi"><a><img src="images/qq.png">4006918987</a><a href="tel:4006918987"><img src="images/phone1.png">4006-918-987</a></p>
            </div>
            <div class="footer_banquan">版权所有 升升投商务顾问（北京）有限公司<br>© 2014 </div>
        </div> -->
          <!--star footer  -->
          <#include "/WEB-INF/template/${systemConfig.theme}/mobilelayout/footerbar.ftl">
        <!-- end footer  -->
    </div>
    <script src='${base}/js/mobile/zepto.min.js'></script>
    <script type="text/javascript">
        $(".wad_allnav").click(function () {
            $(".wad_slidernav").toggle();
        });

            $('.leftsidebar li').click(function () {
        $('.leftsidebar li').removeClass('current');
        $(this).addClass('current');
    });

    </script>
</body>
</html>
