<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 邀请好友</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
</head>
<body>
<div class="nav_box nav_bgf">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        邀请好友
    </div>
</div>
<div class="invite">
    <img src="${base}/mobileNew/img/mypic/invite_bg1.jpg" alt="" />
    <img src="${base}/mobileNew/img/mypic/invite_bg2.jpg" alt="" />
    <div class="bg3_con">
        <img src="${base}/mobileNew/img/mypic/invite_bg3.jpg" alt="" />
        <div class="pt">我的邀请码：<span>${plainCode}</span></div>
        <a href="javascript:;" class="a_btn" id="yq_btn"></a>
        <div class="pb">
            <span>我的邀请</span>
            <p  id="invite">查看详情</p>
        </div>
    </div>
    <div class="bg4_con">
        <img src="${base}/mobileNew/img/mypic/invite_bg4.jpg" alt="" />
        <div class="invite_list">
            <div class="pt">
                <span>受邀请人</span>
                <span>注册时间</span>
            </div>
            <c:choose>
                <c:when test="${empty bpCustMember}">
                    <ul></ul>
                    <div class="pc" style="display:block;">还没有好友和您一起赚钱，赶快邀请吧！</div>
                </c:when>
                <c:otherwise>
                    <ul >
                        <c:forEach items="${bpCustMember}" var="bpCustMember">
                            <li>
                                <span>${fn:substring(bpCustMember.telphone,0,3)}****${fn:substring(bpCustMember.telphone,7,11)}</span>
                                <span>${bpCustMember.registrationDate}</span>
                            </li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
    <div class="modal-box" style="display:none;">
        <div class="modal"></div>
        <div class="dialog">
            <p class="clearfix">
                <img class="pic" src="${base}/mobileNew/img/mypic/invite_share.png" alt="">
            </p>
            <p>点击右上角按钮，然后在弹出菜单栏中点击“发送给好友”或“分享到朋友圈”，即可分享。</p>
        </div>
    </div>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/commen.js"></script>
<script>
    $(function(){
        $('#yq_btn').click(function(){
            $('.invite .modal-box').show();
        });
        $('.invite .modal-box').click(function(){
            $('.invite .modal-box').hide();
        });
    });
</script>

<script>
    $(function () {
        $('#invite').click(function () {
            window.location.href=${base}"/webPhone/selectInviteListWebPhoneCustMember.do?plainCode=${plainCode}";
        })
    })
</script>
</body>
</html>