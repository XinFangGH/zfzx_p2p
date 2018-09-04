<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>消息提示</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
    <script src="/mobileNew/js/AppJsInterface.js"></script>
</head>
<body>
<#--<div class="nav_box">-->
    <#--<div class="nav">-->
        <#--<a class="btn_back"  id="btn_back" href="${base}/indexp2pmobile.html"  style="position:absolute;display: inline-block;height:0px;left:13px;top: 16px;">-->
            <#--<img src="${base}/theme/${systemConfig.theme}/images/massage_back.png" alt="" style="display:inline-block;width:11px;height:20px">-->
        <#--</a>-->
        <#--消息提示-->
    <#--</div>-->
<#--</div>-->
<div class="nav_box">
    <div class="nav">
        <#--<a href="javascript:history.go(-1);" class="back"></a>-->
        <a href="/webPhone/mobileIndexhomePage.do" class="back"></a>
        消息提示
    </div>
</div>

<div class="back_msg">
    <div style="width: 100%;height:114px;text-align:center;margin-top: 44px;margin-bottom: 30px">
        <img src="${base}/theme/${systemConfig.theme}/images/mobile_msg_icon.png" alt="" style="display:block;width: 114px;height:114px;margin:0 auto">
    </div>
    <p style="font-family:PingFang-SC-Medium;font-size:16px;color:#4a4a4a;text-align:center;">${webMsg.desc}</p>
    <p id="jsStr" style="font-family:PingFang-SC-Medium;font-size:16px;color:#4a4a4a;text-align:center;">

    <#assign myMap=stack.findValue("attr")/>
    <#list myMap?keys as key >
        ${myMap[key]}
    </#list>
    </p>
    <#--根据不同class替换不同的图片succ:成功，fail:失败，tit:提示-->
    <#--<div class="img_icon succ"></div>
    <p class="pt">消息提醒</p>
    <p class="pb">关于消息提醒的具体内容文字样式</p>-->
</div>
<script>
    $(function(){
        console.dir($('#jsStr').html());
    })
</script>
</body>
</html>