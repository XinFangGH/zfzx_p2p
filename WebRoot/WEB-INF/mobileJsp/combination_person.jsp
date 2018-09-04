<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 标的详情</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/swiper.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/newStyle.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/leaseDetails.css?version=180823"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/financeLeaseDetail.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/mobileNew/css/scrollLoad.css" />
    <style>
        html {
            font-size: 10px;
        }
        .list-wrapper{
            top:0;
        }
        #page2{
            position:relative;
        }
        #page2 .tit{
            position:fixed;
            top:0;
            left:0;
            z-index:99;
            background:#fff;
        }
        .list-wrapper-hook{
            top:0.9rem;
        }
        .swiper2{
            overflow:auto;
            margin-top:0;
        }
        .swiper2 .myslide{
            min-height:12rem;
        }
        .swiper1{
            margin-top:0.9rem;
            box-shadow:none;
            margin-bottom:0;
        }
        .swiper1 .swiper-wrapper{
            box-shadow: 0 4px 4px 0 rgba(155,166,173,0.15);
            margin-bottom: 0.3rem;
        }
        .swiper1 .swiper-slide i {
            display: block;
            width: 0.54rem;
            height: 0.04rem;
            background: #6BA9FC;
            border-radius: 0.28rem;
            margin: -0.04rem auto 0;
            visibility: hidden;
        }
        .swiper1 .selected i {
            visibility: visible;
        }

        #changeChecked span{
            padding-left:0.4rem;
            background:url(${base}/mobileNew/img/mypic/xy.png) left center no-repeat;
            background-size:0.28rem 0.28rem;
        }
        #changeChecked  span.checked{
            background-image:url(${base}/mobileNew/img/mypic/xy_checked.png);
        }
        .nav_box .nav{
            height:0.9rem;
            line-height:0.9rem;
            background:url(${base}/mobileNew/img/financeLease/leaseDetailsBgs.png) center no-repeat;
            background-size:100% 100%;
        }
        .top-tip .pic{
            width:0.3rem;
            height:0.8rem;
            vertical-align: middle;
            margin-right:0.2rem;
        }
        .top-tip .ani_rote{
            transform:rotateZ(180deg);
            transition: all 0.2s linear;
        }
    </style>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        ${plBidPlan.bidProName}
    </div>
</div>
<div class="swiper-container swiper1 tit" style="display:none;">
    <div class="swiper-wrapper" style="height:0.9rem!important;">
        <div class="swiper-slide selected">标的详情 <i></i></div>
        <div class="swiper-slide">相关材料<i></i></div>
        <div class="swiper-slide">出借记录<i></i></div>
    </div>
</div>
<div class="leaseContainer">
    <div class=" list-wrapper list-wrapper-hook">
        <div>
            <div class="list-content-hook">
                <!--第一屏幕-->
                <div class="page1" id="page1">
                    <div class="BG">
                        <%--<div class="BG_nav_box">
                            <div class="nav">
                                <a href="javascript:history.go(-1);" class="back"></a>
                                ${plBidPlan.bidProName}
                            </div>
                        </div>--%>
                        <div class="leaseDetails">
                            <ul class="leaseUl">
                                <li>
                                    <p >
                                        <c:choose>
                                            <c:when test="${plBidPlan.showRate != null && plBidPlan.showRate != ''}"><fmt:formatNumber value="${plBidPlan.yearInterestRate - plBidPlan.showRate}" pattern="##0.0#"/><span>%</span>+<fmt:formatNumber value="${plBidPlan.showRate}" pattern="##0.0#"/><span>%</span></c:when>
                                            <c:otherwise><fmt:formatNumber value="${plBidPlan.yearInterestRate}" pattern="##0.0#"/> <span>%</span></c:otherwise>
                                        </c:choose>
                                    </p>
                                    <p>预期年化利率</p>
                                </li>
                                <li  class="verticalLine"></li>
                                <li>
                                    <p>
                                        <c:choose>
                                            <c:when test="${plBidPlan.raiseRate != null}"><fmt:formatNumber value="${plBidPlan.raiseRate}" pattern="##0.0#"/><span>%</span></c:when>
                                            <c:otherwise>0.0<span>%</span></c:otherwise>
                                          </c:choose>
                                    </p>
                                    <p>募集期利率</p>
                                </li>
                            </ul>
                        </div>
                        <div class="progressBar">
                            <input type="hidden" id="hiddenProgress" value="${plBidPlan.progress}">
                            <div class="progressBar_pending"></div>
                            <span class="progressBar_button"></span>
                            <div class="progressBar_title">
                                <span id="progressVal"><fmt:formatNumber value="${plBidPlan.progress}" pattern="##0.#"/>%</span>
                            </div>
                        </div>
                        <ul class="leaseBottom  leaseBottom_numberOne">
                            <li>
                                <c:choose>
                                    <c:when test="${plBidPlan.state == 7}"><span>剩余募集时间</span><p>已结束</p></c:when>
                                    <c:otherwise><span>剩余募集时间</span><p id="time"></p></c:otherwise>
                                </c:choose></li>
                            <li><span>剩余</span>
                                <p>
                                    <c:choose>
                                        <c:when test="${plBidPlan.afterMoney >= 10000}"><fmt:formatNumber value="${plBidPlan.afterMoney/10000}" pattern=",##0.00#"/>万元</c:when>
                                        <c:otherwise><fmt:formatNumber value="${plBidPlan.afterMoney}" pattern=",##0"/>元</c:otherwise>
                                    </c:choose>
                                </p></li>
                        </ul>

                        <ul  class="leaseBottom">
                            <li><span>期限</span><p><input id="totalDay" type="hidden" value="">${plBidPlan.loanLife}</p></li>
                            <li><span>总额</span>
                                <p>
                                    <c:choose>
                                        <c:when test="${plBidPlan.bidMoney >= 10000}"><fmt:formatNumber value="${plBidPlan.bidMoney/10000}" pattern=",##0.00#"/>万元</c:when>
                                        <c:otherwise><fmt:formatNumber value="${plBidPlan.bidMoney}" pattern=",##0"/>元</c:otherwise>
                                    </c:choose>
                                </p></li>
                        </ul>
                    </div>
                    <div class="warmHints">
                        <p>温馨提示：</p>募集期利息为投资日到满标当日所记利息，为平台单独发放的福利，与实际标的利息无关，募集期利息发放日期为满标当日。
                    </div>
                    <ul class="course">
                        <li>
                            <span></span>
                            <p>选标出借</p>
                        </li>
                        <li></li>
                        <li>
                            <span></span>
                            <p>开始计息</p>
                        </li>
                        <li></li>
                        <li>
                            <span></span>
                            <p>到期回款</p>
                        </li>
                        <li></li>
                        <li>
                            <span></span>
                            <p>提现到账</p>
                        </li>
                    </ul>
                    <div class="segmentingLine" style="margin: 0 auto 0.2rem;"></div>
                    <ul class="information">
                        <li><span>产品名称</span><p>${plBidPlan.bidProName}</p></li>
                        <li><span>产品类型</span><p>${plBidPlan.proKeepType}</p></li>
                        <li><span>起息时间</span><p><c:choose><c:when test="${plBidPlan.startIntentDate != null}">${plBidPlan.startIntentDate}</c:when><c:otherwise>满标日期</c:otherwise></c:choose></p></li>
                        <li><span>截止日期</span>
                            <p><fmt:formatDate value="${plBidPlan.bidEndTime}" pattern="yyy-MM-dd"/></p></li>
                        <li><span>还款方式</span><p>${plBidPlan.theWayBack}</p></li>
                        <li><span>信用等级</span><p>${plBidPlan.keepCreditlevelName}</p></li>
                        <c:choose>
                        <c:when test="${plBidPlan.versionType  == 2 }">
                            <li>
                                <span>协议范本</span>
                                <p><a href="${base}/js/user/chujiehetong.pdf" target="_blank" style="color: #6BA9FC;">《出借服务协议》范本</a></p>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <span>协议范本</span>
                                <p><a href="${base}/js/user/rongzixieyi.pdf" target="_blank" style="color: #6BA9FC;">《融资租赁协议》范本</a></p>
                            </li>
                        </c:otherwise>
                        </c:choose>
                        <%-- <li>
                             <span>服务协议</span>
                             <p><a href="/webPhone/serviceWebBidPlanAction.do" target="_blank" style="color: #6BA9FC;">《 升升投服务协议》</a></p>
                         </li>--%>
                    </ul>
                    <div class="leasePull">
                        <p>向上滑动查看更多信息 <span></span></p>
                    </div>
                </div>
                <!--第二屏幕-->
                <div class="page1" id="page2" style="display:none;">
                    <div class="top-tip">
                        <img class="pic" src="${base}/mobileNew/img/mypic/dload.png" alt=""><span class="refresh-hook">下拉返回</span>
                    </div>
                    <div class="swiper2">
                        <div class="mySlide" style="overflow-x: hidden;" id="enterprise">
                            <div class="titleNav">
                                <%--<span class="icon"></span>--%>
                                <span class="title">个人基本信息</span>
                            </div>
                            <ul class="enterpriseContent">
                                <li><span>用户名</span>
                                    <span><c:if test="${persionDirPro.loginName==null}">未填</c:if>
                                        ${fn:substring(persionDirPro.loginName,0,1)}****${fn:substring(persionDirPro.loginName,persionDirPro.loginName.length()-1,persionDirPro.loginName.length())}</span></li>
                                <li><span>年&nbsp;&nbsp;龄</span>
                                    <span><c:if test="${persionDirPro.age==null}">未填</c:if>
                                        ${persionDirPro.age}</span></li>
                                <li><span>学&nbsp;&nbsp;历</span>
                                    <span><c:if test="${persionDirPro.education==null}">未填</c:if>
                                    ${persionDirPro.education}</span></li>
                                <li><span>学&nbsp;&nbsp;校</span>
                                    <span><c:if test="${persionDirPro.educationSchool==null}">未填</c:if>
                                        ${persionDirPro.educationSchool}</span></li>
                                <li><span>月收入</span>
                                    <span>
                                        <%--<c:if test="${plBidPlan.monthIncome==0}">未提供</c:if>--%>
                                        <%--${plBidPlan.monthIncome}--%>
                                    <c:choose>
                                        <c:when test="${plBidPlan.monthIncome==0}">未提供</c:when>
                                        <c:otherwise>${plBidPlan.monthIncome}</c:otherwise>
                                    </c:choose>
                                    </span></li>
                                <li><span>主要财务</span>
                                    <span><c:if test="${proKeep.mainFinance==''}">未填</c:if>
                                        ${proKeep.mainFinance}</span></li>
                                <li><span>公司行业</span>
                                    <span><c:choose>
                                            <c:when test="${persionDirPro.companyIndustry=='null'}">未填</c:when>
                                            <c:otherwise>${persionDirPro.companyIndustry}</c:otherwise>
                                          </c:choose>
                                            <%--<c:if test="${persionDirPro.companyIndustry==null}">未填</c:if>--%>
                                            <%--${persionDirPro.companyIndustry}--%></span></li>
                                <li><span>公司规模</span>
                                    <span>
                                        <c:choose>
                                            <c:when test="${persionDirPro.companyScale==null}">未填</c:when>
                                            <c:otherwise>${persionDirPro.companyScale}</c:otherwise>
                                        </c:choose>
                                        <%--<c:if test="${persionDirPro.companyScale==null}">未填</c:if>--%>
                                        <%--${persionDirPro.companyScale}</span></li>--%>
                                <li><span>岗位职位</span>
                                    <span><c:if test="${persionDirPro.position==null}">未填</c:if>
                                        ${persionDirPro.position}</span></li>
                                <li><span>工作城市</span>
                                    <span><c:if test="${persionDirPro.workCity==null}">未填</c:if>
                                        ${persionDirPro.workCity}</span></li>
                                <li><span>工作时间</span>
                                    <span><c:if test="${persionDirPro.workTime==null}">未填</c:if>
                                        ${persionDirPro.workTime}</span></li>
                                <li><span>主要债务</span>
                                    <span><c:if test="${proKeep.mainDebt==''}">未填</c:if>
                                        ${proKeep.mainDebt}</span></li>
                            </ul>
                            <div class="segmentingLine"></div>
                            <div class="titleNav">
                                 <%--<span class="icon"></span>--%>
                                 <span class="title">项目信息</span>
                             </div>
                            <ul class="enterpriseContent" style="/*height:8.5rem;*/margin-bottom:0.4rem;">
                                <li><span style="width: 100%; line-height:0.6rem;">项目描述</span><span  style="width: 100%">
                                    <%--<c:if test="${proKeep.proDes==''}">未填</c:if>--%>
                                    ${proKeep.proDes}
                                </span></li>
                                <li><span>资金用途</span><span>
                                    <%--<c:if test="${proKeep.proUseWay==''}">未填</c:if>--%>
                                    ${proKeep.proUseWay}
                                </span></li>
                                 <li><span>还款来源</span><span>
                                    <%--<c:if test="${proKeepv.proPayMoneyWay==''}">未填</c:if>--%>
                                    ${proKeep.proPayMoneyWay}
                                </span></li>
                            </ul>
                            <p class="remind">＊网贷有风险，出借需谨慎。</p>
                        </div><%--swiper-slide slidePage--%>
                        <div class="mySlide" style="overflow-x: hidden;padding:0.2rem 0 0.3rem" id="publicity">
                            <div class="License swiper7">
                                <div class="swiper-wrapper publicity_pic copy_publicity_pic" >
                                    <c:forEach items="${materials}" var="mas">
                                        <div class="swiper-slide">
                                            <div class="Picture_area">
                                                <img src="${mas.imgUrl}" data-src="${mas.imgUrl}" alt="">
                                            </div>
                                            <p>${mas.materialsName}</p>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <p class="paging copyPaging"><span id="paging_page_3">1</span>/${materials.size()}</p>
                        </div>
                        <div class="mySlide" style="overflow-x: hidden;width: 7.5rem;" id="Loan_record">
                            <ul>
                                <c:forEach items="${listPlBid}" var="record">
                                    <li>
                                        <span class="times">${record.bidtime}</span>
                                        <p><span class="telp">${record.telphone}</span><span><fmt:formatNumber value="${record.userMoney}" pattern=",##0.00#"/></span></p>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footerButton">    <%--webPhone/lendWebBidPlanAction--%>
    <div class="button" id="clickButton">
        立即出借
    </div>
</div>
<%--弹框--%>
<div id="outerdiv" style="position:fixed;/*top:0.88rem;*/top:0rem;left:0;background:rgba(100, 100, 100, 0.5);z-index:10;width:100%;height:100%;display:none;">
    <div id="innerdiv" style="position:absolute;">
        <i id="fork"></i>
        <img id="bigimg" style="border:1px solid #fff;" src="" />
    </div>
</div>
<input id="stateValue" type="hidden" value="${cust}" >
<input id="changeState" type="hidden" value="${cust.isCheckCard}" >
<input id="carkState" type="hidden" value="${cust.isBankCard}" >
<input id="gradeState" type="hidden" value="${cust.grade}" >
<input id="nonClickable" type="hidden" value="${plBidPlan.state}" >
<div id="outerdiv_1" class="outerdiv_1" style="display:none;z-index: 10;position:absolute;">
    <div class="prompt_tube" style="display: none" id="realName">
        <div class="BG"></div>
        <div>您需要先开通银行存管账户，才可以出借，充值，提现，转让</div>
        <div><a href="/webPhone/thirdPayBindWebPhoneRegAction.do"  style="width:100%;height:100%;color:#FFF;">立即开通</a></div>
        <div  id="temporarily">暂不开通</div>
    </div>
    <div class="alertBindCard" id="alertBindCard" style="display: none">
        <div>请先绑卡</div>
        <div>您还没有绑定银行卡，请先绑定银行卡</div>
        <ul>
            <li id="later">稍后操作</li>
            <li><a href="/webPhone/appBindCardWebPhoneThirdAction.do" style="color: #6BA9FC;">立即绑卡</a></li>
        </ul>
    </div>
    <div class="immediateTest" id="immediateTest"  style="display: none">
        <span id="close" class="close"></span>
        <p>根据监管要求，出借之前需要先对您进行风险测评。</p>
        <a href="/webPhone/evaluateWebPhoneCustMember.do?flag=1&bidId=${plBidPlan.bidId}">立即测评</a>
    </div>


    <div id="confirmBox"  style="display: none;width:100%;height:100%;">
        <div  id="confirmDiv"   style="width:100%;height:100%;"></div>
        <ul class="confirm" id="confirm">
            <li><span>剩余可投（元）</span><p><input id="remainder" type="hidden" value="${plBidPlan.afterMoney}"><fmt:formatNumber value="${plBidPlan.afterMoney}" pattern="###,###"/></p><input id="profitRate" type="hidden" value="${plBidPlan.yearInterestRate}"><input
                  id="profitDay"  type="hidden" value="${plBidPlan.cycle}"></li>
            <li>
                <span>可用余额（元）</span>
                <p>
                    <input type="hidden"  id="balance" value="${cust.availableInvestMoney}">
                    <fmt:formatNumber value="${cust.availableInvestMoney}" pattern=",##0.00#"/>
                </p>
                <a href="/webPhone/rechargeMoneyWebFinancePurchaseAction.do"  class="detailRecharge" style="display:inline-block;float:right;height:0.5rem;line-height: 0.5rem; margin-right:0.36rem;width:1.48rem;text-align: center;margin-top: 0.25rem;background-image: linear-gradient(44deg, #FF7740 0%, #FF5540 100%);border-radius: 100px;color:#FFF;">立即充值</a>
            </li>
            <li>
                <div>出借金额（元）<input type="number" id="Loan" style="outline:none;border:none;display:inline-block;width:40%;height:0.9rem "  value="" placeholder="${plBidPlan.startMoney}元起投"  onfocus="this.placeholder=''"  <%--onblur="this.placeholder='${plBidPlan.startMoney}元起投';"--%> onblur="change(this)" onkeyup="change(this)"></div>
                <div id="fullCast">全&nbsp;&nbsp;投</div>
            </li>
            <li>
                <p id="loanHints_1"  style="display: none">*100元起投，且为
                    <c:choose>
                         <c:when test="${plbidPlan.riseMoney !='' &&plbidPlan.riseMoney !=null}">${plbidPlan.riseMoney}</c:when>
                        <c:otherwise>100</c:otherwise>
                    </c:choose>
                    元整数倍</p>
                <p id="loanHints_2" style="display: none">*可用余额不足，请及时充值</p>
                <p id="loanHints_3" style="display: none">*请阅读《出借风险说明和禁止性行为说明书》</p>
                <p id="loanHints_4" style="display: none">*投标金额大于剩余可投金额,不能进行投标</p>
                <p id="loanHints_5" style="display: none">预期收益：<i id="profit"></i>元（不含募集期利息，收益以实际为准）</p>
            </li>
            <li  id="Immediate">
                <form id="lastClick" class="form1" action="/webPhone/bidingWebBidInfoAction.do?bidId=${plBidPlan.bidId}" method="post">
                    <input id="userMoney" name="userMoney" type="hidden" value="">
                    <input name="formtoken" type="hidden" id="formtoken" value="${formtoken}">
                    <input id="nowLend" type="button" style="width:100%;height:100%;border:none;background:none;cursor:pointer;color:#FFF;" value="立即出借">
                </form>
            </li>
            <li id="changeChecked">
                <%--<input type="checkbox" name="layout" id="riskRead" checked="">--%>
                <span id="check" class="checked">请您在出借前仔细阅读</span>
                <p><a style=" color: #719eff" href="/webPhone/termSheetWebBidPlanAction.do">《出借风险说明和禁止性行为说明书》</a></p>
            </li>
        </ul>
    </div>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fastclick.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/swiper.min.js"></script>
<script src="${base}/mobileNew/js/bscroll.js"></script>
<script src="${base}/mobileNew/js/combination.js"></script>
<script>
    //->解决click的300ms延迟
    //FastClick.attach(document.body);

    $(function(){
        //手机号保密显示
        $('.telp').each(function(){
            var telp=$(this).html();
            if(telp){
                var mtel = telp.substr(0, 3) + '****' + telp.substr(7);
                $(this).text(mtel);
            }
        });


        //剩余募集时间倒计时
        /*var time=parseInt($('#time').html())*1000;*/
        var time=parseInt(${plBidPlan.remainingTime})*1000;
        var t=setInterval(function () {
            if(time>0){
                var day = parseInt(time / 1000 / 60 / 60 / 24);
                var hour = parseInt(time / 1000 / 60 / 60 % 24);
                var minute = parseInt(time / 1000 / 60 % 60);
                var seconds = parseInt(time / 1000 % 60);
                $('#time').html(day + "天" + hour + "时" + minute + "分" );/*+ seconds + "秒"*/
                time-=1000;
            }

        }, 1000);



        var Progress_bar  = $("#progressVal").html();
        var hiddenProgress = parseInt($("#hiddenProgress").val());
        $(".progressBar_pending").animate({"width":Progress_bar},1200);

        if(parseInt(Progress_bar) <= "3" ){
            Progress_bar = "0%"
        }else if(parseInt(Progress_bar) >= "94" ){
            $(".progressBar_title").css({"margin-left":"-0.25rem"});
            Progress_bar = "94%";
        }else{
            $(".progressBar_title").css({"margin-left":"-0.25rem"});
        }
        $(".progressBar_title").animate({"left":Progress_bar},1200);


        if(hiddenProgress > "2.6"){
            $(".progressBar_button").css({"margin-left":"-0.1rem"});
        }else{
            $(".progressBar_pending").css({"border-radius":"0"});
        }
        if(hiddenProgress >= "97.4" ){
            $(".progressBar_button").css({"margin-left":"0"});
            hiddenProgress = "97.4";
        }
        $(".progressBar_button").animate({"left":hiddenProgress+"%"},1200);



        //点击图片放大效果
        $(".publicity_pic").on('click','.swiper-slide',function(){
            var _this = $(this).find("img");
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
        });

        function imgShow(outerdiv, innerdiv, bigimg, _this){
            var src = _this.attr("src");
            $(bigimg).attr("src", src);

            $("<img/>").attr("src", src).load(function(){
                /*var windowW = $(window).width();*/
                var windowW =screen.availWidth;
                /* var windowH = $(window).height();*/
                var windowH = screen.availHeight;
                var realWidth = this.width;
                var realHeight = this.height;
                var imgWidth, imgHeight;
                var scale = 0.8;

                if(realHeight>windowH*scale) {
                    imgHeight = windowH*scale;
                    imgWidth = imgHeight/realHeight*realWidth;
                    if(imgWidth>windowW*scale) {
                        imgWidth = windowW*scale;
                    }
                } else if(realWidth>windowW*scale) {
                    imgWidth = windowW*scale;
                    imgHeight = imgWidth/realWidth*realHeight;
                } else {
                    imgWidth = realWidth;
                    imgHeight = realHeight;
                }
                $(bigimg).css("width",imgWidth);
                var imgHeight1=$(bigimg).width()/realWidth*realHeight;
                var w = (windowW-imgWidth)/2;
                var h = (windowH-imgHeight1)/2;
                $(innerdiv).css({"top":h, "left":w});
                $(outerdiv).fadeIn("slow");//淡入显示#outerdiv及.pimg
            });
        }
        $("#bigimg").on('touchstart',function(){//再次点击淡出消失弹出层
            $(outerdiv).fadeOut("slow");
        });

        $('#outerdiv').on('touchstart',function(e){
            e.stopPropagation();
            e.preventDefault();
        },false);

        setTimeout(changeTab,100);
        function changeTab(){
            function setCurrentSlide(ele, index) {
                $(".swiper1 .swiper-slide").removeClass("selected");
                ele.addClass("selected");
                //swiper1.initialSlide=index;
            }

            var swiper5 = new Swiper('.swiper5', {
                slidesPerView: 1.329,
                freeModeSticky: true,
                paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
                spaceBetween: 30,//slide之间的距离（单位px）。
                // freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
                loop: false,//是否可循环
                slidesOffsetBefore: 40,
                slidesOffsetAfter: 40,
                observer: true,//修改swiper自己或子元素时，自动初始化swiper
                observeParents: true,//修改swiper的父元素时，自动初始化swiper
                onSlideChangeEnd: function (swiper) {  //回调函数，swiper从一个slide过渡到另一个slide结束时执行。
                    var n = swiper.activeIndex;
                    $("#paging_page").html(n + 1);

                    /*setCurrentSlide($(".swiper1 .swiper-slide").eq(n), n);
                    swiper1.slideTo(n, 500, false);*/
                }
            });

            var swiper6 = new Swiper('.swiper6', {
                slidesPerView: 1.329,
                freeModeSticky: true,
                paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
                spaceBetween: 30,//slide之间的距离（单位px）。
                // freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
                loop: false,//是否可循环
                slidesOffsetBefore: 40,
                slidesOffsetAfter: 40,
                observer: true,//修改swiper自己或子元素时，自动初始化swiper
                observeParents: true,//修改swiper的父元素时，自动初始化swiper
                onSlideChangeEnd: function (swiper) {  //回调函数，swiper从一个slide过渡到另一个slide结束时执行。
                    var n = swiper.activeIndex;
                    $("#paging_page_2").html(n + 1);
                    /*setCurrentSlide($(".swiper1 .swiper-slide").eq(n), n);
                    swiper1.slideTo(n, 500, false);*/
                }
            });



            var swiper7 = new Swiper('.swiper7', {
                width: window.innerWidth,
                slidesPerView: 1.18,
                freeModeSticky: true,
                paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
                spaceBetween: 30,//slide之间的距离（单位px）。
                // freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
                loop: false,//是否可循环
                slidesOffsetBefore: 0,
                slidesOffsetAfter: 0,
                //observer: true,//修改swiper自己或子元素时，自动初始化swiper
               // observeParents: true,//修改swiper的父元素时，自动初始化swiper
               centeredSlides: true,
                onSlideChangeEnd: function (swiper) {  //回调函数，swiper从一个slide过渡到另一个slide结束时执行。
                    var n = swiper.activeIndex;
                    $("#paging_page_3").html(n + 1);
                    /*setCurrentSlide($(".swiper1 .swiper-slide").eq(n), n);
                    swiper1.slideTo(n, 500, false);*/
                }
            });




        }
    });



    // 滑动滚动条
  /*  $(window).scroll(function(){
// 滚动条距离顶部的距离 大于 44px时
        if($(window).scrollTop() >= 44){
            $(".nav_box").fadeIn(1000); // 开始淡入
        } else{
            $(".nav_box").stop(true,true).fadeOut(1000); // 如果小于等于 200 淡出
        }
    });*/

</script>
<script>
    /*linear-gradient(-130deg, #DBDBDB 0%, #DBDBDB 100%)*/
    /*判断当前状态是否为可点击状态*/
    if($("#nonClickable").val() == "2" || $("#nonClickable").val() == "5" || $("#nonClickable").val() == "6" ){
        $("#clickButton").html("已满标");
        $(".footerButton .button").css({"background-image":"linear-gradient(-130deg, #DBDBDB 0%, #DBDBDB 100%)"});
    }else if($("#nonClickable").val() == "7"){
        $("#clickButton").html("还款中");
        $(".footerButton .button").css({"background-image":"linear-gradient(-130deg, #DBDBDB 0%, #DBDBDB 100%)"});
    }else if($("#nonClickable").val() == "10"){
        $("#clickButton").html("已还清");
        $(".footerButton .button").css({"background-image":"linear-gradient(-130deg, #DBDBDB 0%, #DBDBDB 100%)"});
    }else if($("#nonClickable").val() == "3"){
        $("#clickButton").html("已流标");
        $(".footerButton .button").css({"background-image":"linear-gradient(-130deg, #DBDBDB 0%, #DBDBDB 100%)"});
    }else if($("#nonClickable").val() == "4"){
        $("#clickButton").html("已过期");
        $(".footerButton .button").css({"background-image":"linear-gradient(-130deg, #DBDBDB 0%, #DBDBDB 100%)"});
    }else{
        /*状态值  判断弹框显示*/
        $("#clickButton").click(function(){
            if($("#stateValue").val() == ""){
                /*登陆*/
                window.location.href='/webPhone/regLoghomePage.do';
            }else if($("#changeState").val() != "1"){
                /*实名*/
                $("#outerdiv_1").css("display","flex");
                $("#realName").css("display","block");
                /*实名*/
                $("#temporarily").click(function(){
                    $("#outerdiv_1").css("display","none");
                    $("#realName").css("display","none");
                });
            }else if($("#carkState").val() == "0"){
                /*绑卡*/
                $("#outerdiv_1").css("display","flex");
                $("#alertBindCard").css("display","block");
                /*绑卡*/
                $("#later").click(function(){
                    $("#outerdiv_1").css("display","none");
                    $("#alertBindCard").css("display","none");
                });

            }else if($("#gradeState").val() == ''){
                /*测评*/
                $("#outerdiv_1").css("display","flex");
                $("#immediateTest").css("display","block");
                /*测评*/
                $("#close").click(function(){
                    $("#outerdiv_1").css("display","none");
                    $("#immediateTest").css("display","none");
                });
            }else{
                $("#outerdiv_1").css("display","flex");
                $("#confirmBox").css("display","block");

                $("#confirmDiv").click(function () {
                    $("#outerdiv_1").css("display","none");
                    $("#confirmBx").css("display","none");
                });
            }
        });
    }

    /*余额全投  */
    $("#fullCast").click(function(event){
        /*可用余额*/
        var balance =  parseFloat($("#balance").val());
        /*剩余可投*/
       var remainder =  parseFloat($("#remainder").val());
        if(balance <=  remainder && balance >= 100){
            $("#Loan").val(Math.floor(balance/100)*100);
        }else if(balance <=  remainder && balance < 100){
            $("#Loan").val(balance);
            $("#loanHints_1").show().siblings().hide();
        }else  if(balance >  remainder && remainder >= 100){
            $("#Loan").val(Math.floor(remainder/100)*100);
        }else if(balance >  remainder && remainder < 100){
            $("#Loan").val(remainder);
            $("#loanHints_1").show().siblings().hide();
        }
    });

    //注册协议是否选中
    $('#check').click(function(){
        $(this).toggleClass('checked');
    });

    function change(){
        //先把非数字的都替换掉，除了数字和.
        $("#Loan").val($("#Loan").val().replace(/[^\d.]/g, ""));
        //保证只有出现一个.而没有多个.
        $("#Loan").val($("#Loan").val().replace(/\.{2,}/g, "."));
        //必须保证第一个为数字而不是.
        $("#Loan").val($("#Loan").val().replace(/^\./g, ""));
        //保证.只出现一次，而不能出现两次以上
        $("#Loan").val($("#Loan").val().replace(".", "$#$").replace(/\./g, "").replace("$#$", "."));
        //只能输入两个小数
        $("#Loan").val($("#Loan").val().replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'));

        var str =parseFloat( $("#Loan").val());
        /*可投金额*/
        var rem = parseInt($("#balance").val());
        /*剩余可投*/
        var remainder =  parseFloat($("#remainder").val());

        //投资天数
        var profitDay = $("#profitDay").val();
        //收益率
        var profitRate = $("#profitRate").val();
        var profitRateDay = profitRate/100/365;

        var profit = (str*profitDay*profitRateDay).toFixed(2);
        $("#profit").html(profit);

        /*if(str%100 != '0' && str != ""){
            $("#loanHints_1").show().siblings().hide();
            if(str > remainder){
                $("#loanHints_4").show().siblings().hide();
            }else{
                $("#loanHints_4").hide();
            }
        }else{
            $("#loanHints_1").hide();
        }*/

        if(str){
            if(str == ""){
                $("#loanHints_5").hide();
            }else if(str%100 != '0'){
                $("#loanHints_1").show().siblings().hide();
            }else if(str > remainder){
                $("#loanHints_4").show().siblings().hide();
            }else{
                $("#loanHints_5").show().siblings().hide();
            }

        }else if(!str){
            $("#Loan").attr("placeholder","${plBidPlan.startMoney}元起投");
            $("#loanHints_1").hide();
        }

        /* if(!str){
            $("#Loan").attr("placeholder","${plBidPlan.startMoney}元起投");
            $("#loanHints_1").hide();
        }*/
    }

    /*输入的金额必须大于0，且为整数*/
  $("#nowLend").click(function(event){
        $("#userMoney").val($("#Loan").val());
        var str =parseFloat( $("#Loan").val());
        /*可投金额*/
        var rem = parseFloat($("#balance").val());
        if(str%100 == '0' && str != ""){
            if(str <= rem){
                if($('#check').attr('class') == "checked") {
                  /* $("#lastClick").attr("active","/webPhone/bidingWebBidInfoAction.do?bidId=${plBidPlan.bidId}");*/
                    $("#lastClick").submit();
                    /*$("#Loan").val("");*/
                }else{
                    $("#loanHints_3").show().siblings().hide();
                    return;
                }
            }else{
                $("#loanHints_2").show().siblings().hide();
                return;
            }
        }else{
            $("#loanHints_1").show().siblings().hide();
            return;
        }
      return false;
    });
</script>
</body>
</html>