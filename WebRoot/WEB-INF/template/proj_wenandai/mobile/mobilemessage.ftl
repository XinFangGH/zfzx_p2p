<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <meta  http-equiv="x-rim-auto-match" content="none">
    <!--<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">-->
    <title>升升投 - 消息提示</title>
    <link rel="stylesheet"  media="all" type="text/css" href="${base}/theme/${systemConfig.theme}/css/mobile.css" />
</head>
<body>
<div id="wrap" class="wad_loginbg">
    <div class="wad_mobile">

        <div class="login_header  my_header"  style="position:relative;width:100%;height:50px;background-color: #e84518;border-bottom:10px solid #f3f3f3;padding: 0">
            <a class="btn_back"  id="btn_back" href="${base}/indexp2pmobile.html"  style="position:absolute;display: inline-block;height:0px;left:13px;top: 16px;">
                <img src="${base}/theme/${systemConfig.theme}/images/massage_back.png" alt="" style="display:inline-block;width:11px;height:20px">
            </a>
            <h3 style="line-height: 50px;font-size: 18px;color:#FFF">消息提示</h3>
        </div>

        <div class="wad_chenggong" id="wad_chenggong">
            <div style="width: 100%;height:114px;text-align:center;margin-top: 44px;margin-bottom: 30px">
                <img src="${base}/theme/${systemConfig.theme}/images/mobile_msg_icon.png" alt="" style="display:block;width: 114px;height:114px;margin:0 auto">
            </div>
            <p style="font-family:PingFang-SC-Medium;font-size:16px;color:#4a4a4a;text-align:center;">${webMsg.desc}</p>
           <#-- <p style="font-family:PingFang-SC-Regular;margin-top:14px;font-size:14px;color:#9b9b9b;text-align:center;"></p>-->
            <!--<p><a href="${base}/thirdreg.do?mobile=mobile">开通易宝账户</a></p>-->
            <!--<div class="wad_shoujili" style="width: 90%;margin: 0 5%;"><div class="wad_voice" style="text-align:center;">如果您不开通易宝账户，将 <a class="wad_shouji" href="">无法在易贷金融平台上投标或融资</a></div></div>-->
        </div>

    </div>
    <!-- <div class="wad_footer_banquan">升升投携手富滇银行，<br>共同为客户打造诚心、可靠、安全的投资理财平台</div>-->
    <!--<div class="wad_mobile wad_mobilelogin"><h1><i class="logo_ico"></i><i class="logo_ico"></i></h1></div>-->
</div>
<script src='${base}/js/mobile/zepto.min.js'></script>
</body>
<script>
    window.setTimeout(function(){
        $("#btn_back").click();
    },5000);
</script>

</html>