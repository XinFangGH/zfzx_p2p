<%@ page import="java.util.Map" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>消息提示</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
    <script src="${base}/mobileNew/js/AppJsInterface.js"></script>
</head>
<body>
<%--<div class="nav_box">--%>
<%--<div class="nav">--%>
<%--<a href="javascript:history.go(-1);" class="back"></a>--%>
<%--消息提示--%>
<%--</div>--%>
<%--</div>--%>
<div class="back_msg" style="margin-top: 120px;">
    <%--根据不同class替换不同的图片succ:成功，fail:失败，tit:提示--%>
        <div class="img_icon" id="img_icon"></div>
    <div style="width: 100%;height:114px;text-align:center;margin-top: 50px;margin-bottom: 30px">
        <%--<img src="${base}/theme/${systemConfig.theme}/images/mobile_msg_icon.png" alt=""--%>
             <%--style="display:block;width: 114px;height:114px;margin:0 auto">--%>
    </div>
    <%--<p style="font-family:PingFang-SC-Medium;font-size:16px;color:#4a4a4a;text-align:center;">${webMsg.desc}</p>--%>
    <p style="font-family:PingFang-SC-Medium;font-size:0.28rem;color:#9B9B9B;margin-top:-150px;text-align:center;">${webMsg.desc}</p>
    <%--<c:forEach items="${attr}" var="map">--%>
    <%--<p style="font-family:PingFang-SC-Medium;font-size:16px;color:#4a4a4a;text-align:center;">--%>
    <%--<c:out value="${map.value.retCode}"></c:out>--%>
    <%--</p>--%>
    <%--</c:forEach>--%>
    <%--<p style="font-family:PingFang-SC-Medium;font-size:16px;color:#4a4a4a;text-align:center;">--%>
    <%--${attr.reqData}--%>

    <%--${attr.reqData}--%>

</div>
</body>
<script>
    if('${webMsg.code}'=='0000'||'${webMsg.code}'=='responsecode_faild'){
        $("#img_icon").addClass("fail");
    }
    if('${webMsg.code}'=='8888'){
        $("#img_icon").addClass("succ");
    }
    var map = eval(${attr.reqData})
    var retCode = map["retCode"];
    var retMsg = map["retMsg"];
    var result = ("0000" === retCode);
    var resultJson = {}
    resultJson.errorCode = retCode
    resultJson.errorMsg = retMsg
    resultJson.status = result
    if (result) {
        resultJson.extMark = map["content"]["extMark"]
        AppInterface.thridPartCallBck(JSON.stringify(resultJson))
    } else {
        resultJson.extMark = ""
        AppInterface.thridPartCallBck(JSON.stringify(resultJson))
    }
</script>
</html>