<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 账户</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/index.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/common.css"/>
    <style>
        .container {
            width: 100%;
            background: #FFFFFF;
        }

        .outerdiv{
            position:fixed;
            width:100%;
            height:100%;
            top:0;
            left:0;
            background:rgba(16,44,81,0.3);
            z-index:4;
            /*display:none;*/
            display:flex;
            justify-content:center;
            align-items:center;
        }
        .modal-box{display:none;}
        .modal-box .modal{position:fixed;left:0px;top:0px;width:100%;height:100%;background:rgba(16,44,81,0.3);z-index:100;}
        .modal-box .dialog{
            width:5.72rem;height:3.28rem;position:fixed;left:50%;top:120%;margin-left:-2.86rem;margin-top:-1.64rem;z-index:101;background:#fff;border-radius:0.2rem;
        }
        .modal-box .dialog .pt{
            margin-top:0.5rem;
            font-size:0.32rem;
            color:#1A1A1A;
            text-align:center;
            line-height:0.44rem;
            font-weight:bold;
        }
        .modal-box .dialog .pc{
            margin:0.2rem 0.3rem 0;
            font-size: 0.28rem;
            color: #1A1A1A;
            text-align: center;
            line-height:0.4rem;
        }
        .modal-box .dialog .pb{
            margin-top:0.5rem;
            border-top:2px solid #EDEDED;
            overflow:hidden;
        }
        .modal-box .dialog .pb .cancel{
            float:left;
            width:50%;
            box-sizing:border-box;
            border-right:1px solid #EDEDED;
            font-size: 0.28rem;
            color: #9B9B9B;
            line-height:1.2rem;
            text-align: center;
        }
        .modal-box .dialog .pb .widthdr{
            float:right;
            width:50%;
            box-sizing:border-box;
            border-left:1px solid #EDEDED;
            font-size: 0.28rem;
            color: #6BA9FC;
            line-height:1.2rem;
            text-align: center;
        }





    </style>

    <script type="text/javascript">
        function reglog() {
            window.location.href=${base}'/webPhone/regLoghomePage.do'
        }
    </script>
</head>
<body>
<div class="container">
    <!--账号头部-->
    <c:if test="${bpCustMembers==''}">
        <div  class="accounttopbar_login"  >
            <div class="accounttopbar_login_user"></div>
            <p>欢迎使用升升投，请先登录</p>
            <div class="accounttopbar_login_btn" onclick="reglog()" id="reg_log">登录/注册</div>
        </div>
    </c:if>
    <c:if test="${bpCustMembers!=''}">
        <div class="accounttopbar">
            <div class="userMsg">
                <b id="account" style="font-weight:normal;">
                     <span style="background:url(${bpCustMembers.imgHeader})no-repeat center;background-size: 0.72rem 0.72rem"></span>
                <c:if test="${fn:length(bpCustMembers.telphone)=='11'}">
                    <span>${fn:substring(bpCustMembers.telphone,0,3)}****${fn:substring(bpCustMembers.telphone,7,11)}</span>
                </c:if>
                </b>
                <c:choose>
                    <c:when test="${bpCustMembers.personUnreadMsg > 0}"><span id="message" class="msg"></span></c:when>
                    <c:otherwise><span id="message1" class="msg1"></span></c:otherwise>
                </c:choose>
            </div>
            <ul class="userList">
                <li>
                    <span><fmt:formatNumber value="${bpCustMembers.availableInvestMoney}" pattern=",##0" minFractionDigits="2"/></span>
                    <p>账户可用余额（元）</p>
                    <input id= "trueMoney" type="hidden" value="<fmt:formatNumber value="${bpCustMembers.trueMoney}" pattern=",##0" minFractionDigits="2"/>">
                </li>
                <li>
                    <a href="javaScript:void(0);onclick=recharge()">
                    <span>充值／提现</span>
                    </a>
                </li>
                <li>
                    <span><fmt:formatNumber value="${bpCustMembers.totalMoney}" pattern=",##0" minFractionDigits="2"/></span>
                    <p>账户总资产(元)</p>
                </li>
                <li>
                    <span><fmt:formatNumber value="${bpCustMembers.allInterest}" pattern=",##0" minFractionDigits="2"/></span>
                    <p>累计收益(元)</p>
                </li>
            </ul>
        </div>

    </c:if>

    <!--中部内容 content-->
    <div class="assetManagement">
        <div class="managementTitle">资产管理</div>
        <ul class="managementList">
            <li>
                <div class="Whole  line">
                    <a href="javaScript:; onclick=window.location.href=${base}'/webPhone/newmobileaccountWebPhoneCustMember.do'">
                        <span class="icon_1"></span>
                        <div>
                            <p>账户总览</p>
                            <p>我的资金账户</p>
                        </div>
                    </a>
                </div>
                <div  class="Whole changePic">
                    <a onclick="accountDetail()">
                        <span class="icon_2"></span>
                        <div id="account_detail">
                            <p>资金明细</p>
                            <p>交易资金明细</p>
                        </div>
                    </a>
                </div>
            </li>
            <li>
                <div class="Whole  line">
                    <a href=${base}"/webPhone/mobileMyManageMoneyWebFinancePurchaseAction.do?planstate=7&start=1&limit=8">
                        <span class="icon_3"></span>
                        <div>
                            <p>散标出借</p>
                            <p>高收益&nbsp;&nbsp;看得见</p>
                        </div>
                    </a>
                </div>
                <div  class="Whole changePic">
                    <a href=${base}"/webPhone/appLoadMyInvestWebPhoneCustMember.do?plainCode=<c:if test="${bpCustMembers!=''}">${bpCustMembers.plainpassword}</c:if>">
                        <span class="icon_4"></span>
                        <div>
                            <p>邀请好友</p>
                            <p>邀请好友来赚钱</p>
                        </div>
                    </a>
                </div>
            </li>
            <li  class="no_line">
                <div class="Whole  line">
                    <a href="javaScript:void(0);">
                        <span class="icon_5"></span>
                        <div id="coupon">
                            <p>我的优惠券</p>
                            <p>红包优惠券</p>
                        </div>
                    </a>
                </div>
                <div  class="Whole changePic">
                    <%-- <span class="icon_6"></span>
                     <div>
                         <p>自动投标</p>
                         <p>机会就在你手上</p>
                     </div>--%>
                </div>
            </li>
        </ul>


        <div class="managementTitle">账户管理</div>
        <ul class="managementList newList">
            <li class="newStyle">
                <div class="Whole  line changeTwo">
                    <a href="javaScript:void(0); onclick=window.location.href=${base}'/webPhone/account_setWebPhoneCustMember.do?telNummber='<c:if test="${bpCustMembers!=''}">+${bpCustMembers.telphone}</c:if>">
                        <span class="icon_7"></span>
                        <div>
                            <p class="alignment">账户设置</p>
                        </div>
                    </a>
                </div>
                <div  class="Whole changePic">
                    <a href="javaScript:void(0);" id="messInfo">
                        <span class="icon_8"></span>
                        <div>
                            <p class="alignment" >消息中心</p>
                        </div>
                    </a>
                </div>
            </li>
            <li   class="newStyle">
                <div class="Whole  line  changeTwo">
                    <a href="javaScript:void(0);onclck=location.href=${base}'/webPhone/helpCenterhomePage.do'">
                        <span class="icon_9"></span>
                        <div>
                            <p class="alignment">帮助中心</p>
                        </div>
                    </a>
                </div>
                <div  class="Whole changePic">
                    <a href="javaScript:;" class="connect_kf">
                        <span class="icon_10"></span>
                        <div>
                            <p class="alignment">联系客服</p>
                        </div>
                    </a>
                </div>
            </li>
            <li   class="no_line newStyle">
                <div class="Whole  line changeTwo">
                    <a href="javaScript:void(0);onclck=location.href=${base}'/webPhone/feedBackJspWebPhoneCustMember.do'">
                        <span class="icon_11"></span>
                        <div>
                            <p class="alignment">意见反馈</p>
                        </div>
                    </a>
                </div>
                <div  class="Whole changePic">
                    <%-- <span class="icon_10"></span>
                     <div>
                         <p class="alignment">联系客服</p>
                     </div>--%>
                </div>
            </li>
        </ul>
        </div>
    </div>
    <!--footer-->
    <%@include file="foot.jsp"%>
    </div>
    <%--充值/提现弹框--%>
    <div id="outerdiv" class="outerdiv  outerdiv_1" style="display: none">
        <div class="prompt_tube" style="display: none">
            <div class="BG"></div>
            <div>您需要先开通银行存管账户，才可以出借，充值，提现，转让</div>
            <div id="immediatelyOpen">立即开通</div>
            <div id="withholdOpen">暂不开通</div>
        </div>
        <%--<div class="prompt_tube" style="display: none" id="realName">--%>
            <%--<div class="BG"></div>--%>
            <%--<div>您需要先开通银行存管账户，才可以出借，充值，提现，转让</div>--%>
            <%--<div><a href="/webPhone/appRegAndBindWebPhoneThirdAction.do">立即开通</a></div>--%>
            <%--<div>暂不开通</div>--%>
            <%--<div><a href="javaScript:void(0);" style="width:100%;height:100%;color:#FFF;">立即开通</a></div>--%>
            <%--<div id="temporarily">暂不开通</div>--%>
        <%--</div>--%>
        <div class="alertBindCard" id="alertBindCard" style="display: none">
            <div>请先绑卡</div>
            <div>您还没有绑定银行卡，请先绑定银行卡</div>
            <ul>
                <li id="later">稍后操作</li>
                <li id="immediatelyBind">立即绑卡</li>
            </ul>
        </div>
        <div class="immediateTest" id="immediateTest"  style="display: none">
            <span id="close" class="close"></span>
            <p>根据监管要求，出借之前需要先对您进行风险测评。</p>
            <a href="/webPhone/evaluateWebPhoneCustMember.do?flag=1&bidId=${plBidPlan.bidId}">立即测评</a>
        </div>
    </div>
    <div class="modal-box" style="display: none">
    <div class="modal"></div>
    <div class="dialog">
        <p class="pt succ">更换手机号成功</p>
        <p class="pt fail">更换手机号失败</p>
        <p class="pb"><span id="fpwd_time">3</span>s</p>
    </div>
</div>
<div class="modal-box">
    <div class="modal"></div>
    <div class="dialog">
        <p class="pt">400-9266-114</p>
        <p class="pc">工作时间:周一到周日 9:00-18:00</p>
        <p class="pb">
            <a href="javascript:;" class="cancel">取消</a>
            <a href="tel:400-9266-114" class="widthdr">拨打</a>
        </p>
    </div>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script>
    $('.container .footerNav a').removeClass('selected').eq(2).addClass('selected');
    $('.accountBox .account').css({"background-image":"url(${base}/mobileNew/img/footer_icon/me.png)"});
    function recharge(){
        //可用余额
        var availableInvestMoney =$(".userList li:nth-child(1) span").text();
        var trueMoney = $("#trueMoney").val();
//        window.location.href=
//            '/webPhone/rechargeWebFinancePurchaseAction.do?availableInvestMoney='+availableInvestMoney+'&trueMoney='+trueMoney;

        var url = ${base}'/webPhone/inspectWebFinancePurchaseAction.do';
        $.ajax({
            url:url,
            type:"post",
            dataType:"json",
            success:function (data) {
                if(data.code==6666){
                    $("#outerdiv").show();
                    $(".prompt_tube").show();
                }
                if (data.code == 8888) {
                    $("#outerdiv").show();
                    $("#alertBindCard").show();
                }
                if (data.code == 9999) {
                    window.location.href = ${base}'/webPhone/regLoghomePage.do';
                }
                if (data.code == 200) {
                    window.location.href =
                        ${base}'/webPhone/rechargeWebFinancePurchaseAction.do?availableInvestMoney='
                        + availableInvestMoney + '&trueMoney=' + trueMoney;
                }
            }

        })

    }


    //未实名提示框

    $(function(){
        $('.footerNav a').removeClass('selected').eq(2).addClass('selected');
        $('.accountBox .account').css({"background-image":"url(${base}/mobileNew/img/footer_icon/me.png)"});

        //去开通
        $("#immediatelyOpen").click(function () {
            var url =${base}'/webPhone/appThirdWebPhoneRegAction.do';
            $.ajax({
                url:url,
                type:"post",
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        window.location.href = ${base}'/webPhone/thirdPayBindWebPhoneRegAction.do';
                    }else{

                    }
                }
            })
        })
        //暂不开通
        $("#withholdOpen").click(function () {
            $("#outerdiv").hide();
            $(".alertBindCard").hide();
        })
        //绑卡稍后操作
        $("#later").click(function () {
            $("#outerdiv").hide();
            $(".prompt_tube").hide();
        })

        //立即绑卡
        $("#immediatelyBind").click(function () {
            window.location.href=${base}'/webPhone/bankCardWebPhoneCustMember.do';
        })

        $('.connect_kf').click(function(){
            $('.modal-box').show();
            $('.modal-box .dialog').animate({top:"50%"},200);
        });
        $('.cancel').click(function(){
            $('.modal-box').hide();
            $('.modal-box .dialog').css({top:"120%"});
        });
        $('#message').click(function () {
            window.location.href=${base}'/webPhone/message_centerhomePage.do';
        })
        $('#message1').click(function () {
            window.location.href=${base}'/webPhone/message_centerhomePage.do';
        })
        $('#messInfo').click(function () {
            window.location.href=${base}'/webPhone/message_centerhomePage.do';
        })
        $('#account').click(function () {
            window.location.href=${base}'/webPhone/account_setWebPhoneCustMember.do?telNummber='<c:if test="${bpCustMembers!=''}">+${bpCustMembers.telphone}</c:if>;
        })
        $('#coupon').click(function () {
            window.location.href=${base}'/webPhone/couponhomePage.do';
        })

    });



    function recharge(){
        //可用余额
        var availableInvestMoney =$(".userList li:nth-child(1) span").text();
        var trueMoney = $("#trueMoney").val();
//        window.location.href=
//            '/webPhone/rechargeWebFinancePurchaseAction.do?availableInvestMoney='+availableInvestMoney+'&trueMoney='+trueMoney;

        var url = '/webPhone/inspectWebFinancePurchaseAction.do';
        $.ajax({
            url:url,
            type:"post",
            dataType:"json",
            success:function (data) {
                if(data.code==6666){
                    $("#outerdiv").show();
                    $(".prompt_tube").show();
                }
                if (data.code == 8888) {
                    $("#outerdiv").show();
                    $("#alertBindCard").show();
                }
                if (data.code == 9999) {
                    window.location.href = '/webPhone/regLoghomePage.do';
                }
                if (data.code == 200) {
                    window.location.href =
                        '/webPhone/rechargeWebFinancePurchaseAction.do?availableInvestMoney='
                        + availableInvestMoney + '&trueMoney=' + trueMoney;
                }
            }
        })
    }


    function accountDetail() {
        window.location.href=${base}'/financePurchase/moneyDetailFinancePurchase.do?page=1&limit=1';
        <%--var url=${base}"/financePurchase/moneyDetailFinancePurchase.do";--%>
        <%--$.ajax({--%>
            <%--type:"get",--%>
            <%--data:{"page":1,"limit":3},--%>
            <%--url:url,--%>
            <%--dataType:"json",--%>
            <%--success:function (data) {--%>
                <%--if(data.code==200){--%>
                    <%--window.location.href="/webPhone/account_detailhomePage.do";--%>
                <%--}--%>
                <%--if(data.code==500){--%>
                    <%--$(".modal-box .fail").html(data.msg).show().siblings('.succ').hide();--%>
                    <%--$('.modal-box').show();--%>
                    <%--popTime();--%>
                <%--}--%>
                <%--if(data.code==400){--%>
                    <%--//window.location.href="/webPhone/regLoghomePage.do"--%>
                    <%--$(".modal-box .fail").html(data.msg).show().siblings('.succ').hide();--%>
                    <%--$('.modal-box').show();--%>
                    <%--popTime();--%>
                <%--}--%>
                <%--function popTime(){--%>
                    <%--var t=parseInt($('#fpwd_time').html());--%>
                    <%--var t1=setInterval(function(){--%>
                        <%--if(t){--%>
                            <%--t--;--%>
                            <%--$('#fpwd_time').html(t);--%>
                        <%--}else{--%>
                            <%--$('.modal-box').hide();--%>
                            <%--clearInterval(t1);--%>
                            <%--if(data.code==500 || data.code==400){--%>
                                <%--$("#fpwd_time").html(3);--%>
                            <%--}--%>
                        <%--}--%>
                    <%--},1000);--%>
                <%--}--%>
            <%--},--%>
            <%--error:function () {--%>
                <%--$(".modal-box .fail").html("查询失败，请查看是否已登录").show().siblings('.succ').hide();--%>
                <%--$('.modal-box').show();--%>
                <%--popTime();--%>
            <%--}--%>
        <%--})--%>
    }

</script>

</body>
</html>