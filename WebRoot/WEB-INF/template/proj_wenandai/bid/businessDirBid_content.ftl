<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>${systemConfig.metaTitle} - 招标信息详情</title>

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/check.js"></script>
    <script type="text/javascript" src="${base}/js/user/login.js"></script>
    <script type="text/javascript" src="${base}/js/sign.js"></script>
    <script type="text/javascript" src="${base}/js/jquery.fancybox-1.3.1.pack.js"></script>
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css"/>
    <script type="text/javascript" src="${base}/js/user/fancybox.js"></script>

    <link rel="stylesheet" href="${base}/theme/proj_wenandai/css/jquery.vm-carousel.css">
    <script src="${base}/js/modernizr.js"></script>

    <script type="text/javascript">var m1 = "我要投资", m2 = "", m3 = "";</script>
    <style type="text/css">
        body, h1, ul {
            margin: 0;
        }

        ul li {
            list-style-type: none;
        }

        #header {
            width: 100%;
            border-top: solid 1px #ccc;
            border-bottom: solid 1px #ccc;
            text-align: center;
        }

        .nav_scroll {
            position: fixed;
            width: 100%;
            margin-left: 98px;
            left: 0;
            top: 0;
            background-color: white;
            z-index: 25;
        }

     /*   h2 {
            margin-bottom: 40px;
            margin-top: 40px;
        }*/
        .vmc-centered{ padding: 10px 0; }
        .vmc-centered img { transition: all 0.3s ease; }
        /* .vmc-centered .vmc_active img { transform: scale(1.2); }*/

        .swiper-container {
            width: 100%;
            height: 100%;
        }

        .swiper-slide {
            width: 262px;
            text-align: center;
            font-size: 18px;
            margin-right: 30px;
            margin-top: 10px;
            background-color: #f5f7fa;
        }

        .swiper-slide img {
            display: block;
            width: 260px;
            height: 190px;
            border: 1px solid #cccccc;
            border-radius: 8px;
            cursor: pointer;

        }

        .swiper-slide p {
            display: block;
            font-size: 14px;
            color: #333;
        }

        .cover{
            position: absolute;
            left: 0;
            top: 0;
            width: 250px;
            height: 185.5px;
            background-color: #a0a0a0d6;
            filter: alpha(opacity=50);
            -moz-opacity: 0.4;
            opacity: 0.4;
            /* z-index: 99; */
            display: none;
            border-radius: 7px;
            cursor: pointer;
        }

        .swiper-slide:hover  .cover{
            display: block;
            cursor: pointer;
        }

        .span_img {
            position: absolute;
            top: 32%;
            left: 45%;
            display: none;
            width: 44px;
            height: 44px;
            background-image: url(${base}/theme/${systemConfig.theme}/images/Qualification/fangda_icon.png);
        }

        .swiper-slide:hover .span_img {
            display: block;
            cursor: pointer;
        }

      .vmc-arrow-left{
            background-image: url(${base}/theme/proj_wenandai/css/left_right.png);
            background-position: 0 0;
            position: absolute;
            top: 37%;
            left: -55px;
            width: 20px;
            height: 84px;
           margin-top: -25px;
            text-align: center;
        }

        .vmc-arrow-right{
            width: 20px;
            height: 84px;
            background-image: url(${base}/theme/proj_wenandai/css/left_right.png);
            background-position: 20px 0;
            right: -52px;
            left: auto;
            top: 89px;
        }

        .vmc-centered li img{
            width: 250px;
        }

        .vmc-centered li p{
            display: block; font-size: 14px; color: #333;height: 30px;
            line-height: 30px;
            text-align: center;
           /* margin-top: 10px;*/
            padding: 10px 0;
        }

        .vmc-centered li{
            position: relative;
            height:230px
        }

        .vmcarousel > li:hover .cover {
            position: absolute;
            left: 16px;
            top: 0;
            display: block;
            cursor: pointer;
        }

        .vmcarousel > li:hover .span_img {
            display: block;
            cursor: pointer;
        }

        .vmc-centered li img{
            width: 250px;
            height:184.5px;
            border-radius: 7px;
            border: 1px solid #cccccc;
        }

      /*  .vmc-viewport{
            background-color: #f5f7fa;
        }*/

        #page_navigation{
            margin-top: 45px;
            margin-bottom: 20px;
        }

        #page_navigation a{
           /* padding:3px;
            border:1px solid gray;
            margin:2px;
            color:black;
            text-decoration:none*/
            display: inline-block;
            width: 26px;
            height: 26px;
            line-height: 26px;
            border:1px solid gray;
            margin: 1px 18px 0 0;
            color: #464646;;
        }

        #page_navigation a:hover {
            color: #4788ca;
        }

        #page_navigation  a.previous_link , #page_navigation  a.next_link{
            font-family: "\5B8B\4F53", sans-serif;
            font-weight: bold;
            font-size: 14px;
            color: #ccc;
            width: 60px;
            background: #fff;
            display: inline-block;
            margin: 1px 18px 0 0;
            height: 26px;
            line-height: 26px;
            border: 1px solid #e5e5e5;
            overflow: hidden;
            vertical-align: top;
        }

        #page_navigation  a.previous_link:hover , #page_navigation  a.next_link:hover{
            color: #4788ca;
        }


        .active_page{
            background:#2584e0;
            color:white !important;
        }

    </style>
    <script type="text/javascript">
        $(function () {
            $('a[rel*=leanModal]').leanModal({top: 200});
        });
        window.onload = function () {

            $(".finlist .finlist_title li").each(function (index) { //带参数遍历各个选项卡
                $(this).click(function () { //注册每个选卡的单击事件
                    $(".finlist .finlist_title li.active").removeClass("active"); //移除已选中的样式
                    $(this).addClass("active"); //增加当前选中项的样式
                    //显示选项卡对应的内容并隐藏未被选中的内容
                    $(".finlist .finlist_hide ol:eq(" + index + ")").show()
                            .siblings().hide();
                });
            });


            $(function () {
                check('${plan.bidId}', '${plan.afterMoney}', '${planPro.yearInterestRate}', false);
                $("#to").click(function () {
                    var myMoney = $("#myMoney").text();
                    var afterMoney = $("#afterMoney").text();
                    var subMyMoney = myMoney.substring(0, myMoney.length - 1);
                    var subAfterMoney = afterMoney.substring(0, afterMoney.length - 1);
                    var strAfterMoney = "";
                    var strMyMoney = "";
                    for (var i = 0; i < subAfterMoney.length; i++) {
                        if ("," != subAfterMoney[i]) {
                            strAfterMoney += subAfterMoney[i];
                        }
                    }
                    for (var i = 0; i < subMyMoney.length; i++) {
                        if ("," != subMyMoney[i]) {
                            strMyMoney += subMyMoney[i];
                        }
                    }

                    if (strMyMoney > strAfterMoney) {
                        $("#signMoney").val(strAfterMoney);
                    } else if (strMyMoney <= strAfterMoney) {
                        $("#signMoney").val(strMyMoney);
                    } else {
                        $("#signMoney").val(0);
                    }

                });
            });
        }
    </script>
    <script type="text/javascript">
        $(function () {
            //图标下拉框  提示框
            var content = $(".sidnext").html();
            $('.icon-one').pt({
                position: 'b',
                width: 300,
                content: content
            });
            var content = $(".sidnext1").html();
            $('.icon-two').pt({
                position: 'b',
                width: 300,
                content: content
            });
            var content = $(".sidnext2").html();
            $('.icon-three').pt({
                position: 'b',
                width: 300,
                content: content
            });

        });
    </script>
    <script type="text/javascript">
        $(function () {
            var a = $("#showZQ").val();
            if (a != null && a != '' && a != 'undefined') {
                if (a == '1') {//当不是直投标时 债权信息页签隐藏
                    $("#showZP").show();
                    $("#ppxx").show();
                    $("#showZX").hide();
                    $("#zqxx").hide();
                } else {
                    $("#showZP").hide();
                    $("#ppxx").hide();
                    $("#showZX").show();
                    $("#zqxx").show();
                }
            } else {
                $("#showZP").hide();
                $("#ppxx").hide();
                $("#showZX").show();
                $("#zqxx").show();
            }
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            var topMain = $("#header").height() + 500//是头部的高度加头部与nav导航之间的距离
            var nav = $(".finlist_title");
            $(window).scroll(function () {
                if ($(window).scrollTop() > topMain) {//如果滚动条顶部的距离大于topMain则就nav导航就添加类.nav_scroll，否则就移除
//   	Array.prototype.slice.call($(".finlist_title li")).forEach(function(item){
//		item.className = "active";
//	});
                    nav.addClass("nav_scroll");
                } else {
                    nav.removeClass("nav_scroll");
                }
            });
        })
    </script>
</head>
<body>
<div>
    <!-- topbar -->
    <!-- header --><!-- navbar -->
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
    <!-- main -->
    <!-- 开始 只需要拿这个说明标签之内的部分 -->
    <!-- 开始 只需要拿这个说明标签之内的部分 -->
    <!-- 开始 只需要拿这个说明标签之内的部分 -->
    <!-- 开始 只需要拿这个说明标签之内的部分 -->
    <!-- 开始 只需要拿这个说明标签之内的部分 -->
    <!-- 开始 只需要拿这个说明标签之内的部分 -->
    <!-- 开始 只需要拿这个说明标签之内的部分 -->
    <!-- 开始 只需要拿这个说明标签之内的部分 -->
    <!-- 开始 只需要拿这个说明标签之内的部分 -->
    <!-- 开始 只需要拿这个说明标签之内的部分 -->
    <!-- 开始 只需要拿这个说明标签之内的部分 -->
    <!--企业直投标-->
    <input value="${(bpCustMember.grade)??}" type="hidden" id="grade">
    <input value="${(bpCustMember.id)??}" type="hidden" id="memberId">
    <input value="${plan.maxCouponMoney}" type="hidden" id="planMaxCouponMoney">
<div style="background:#eff3f3;width:100%;overflow:hidden;">
<div class="main  my-main">
    <!--<div class="porduct">
        <a href="#">产品详情</a>
    </div>-->
    <div class="inner">

        <div class="inner-left">
            <h2 class="title"><span class="pic"></span><span
                    class="txt">${(plan.bidProName)![0..10]}</span>
                <span class="sd"><em>实地</em></span>
                <span class="xydj">
                    <i>${plan.keepCreditlevelName!}</i>
                </span>
                <#if plan.novice==1><font
                        class="font-one">新</font></#if>

                <a href="${base}/js/user/rongzixieyi.pdf" target="_blank">
                    <span style="font-size:14px;float:right;color:#409af6;">《融资租赁协议》范本</span>
                </a>

            </h2>
            <div class="condition">
                <dl class="didt text-l">
                    <dd class="my-color" style=" width: auto;text-align: center;font-size: 14px;"><i style="font-size: 28px">
               <#if plan.showRate!=0&&plan.showRate!="">     ${(planPro.yearInterestRate-plan.showRate)!}</i>%   <i style="font-size: 20px">+</i><font color="#EA0000" size="5px;"><i style="font-size: 28px;">${plan.showRate}</i><i style="font-size: 14px">%</i></font>&nbsp;<#--<font class="font-two">息</font>-->
               <#else >
                   ${(planPro.yearInterestRate)}</i>%
               </#if>
                    </dd>

                    <dt style="text-align: center">预期年化收益率</dt>
                </dl>
                <dl class="didt didt-none">
                    <dd><i>${plan.loanLife}</i></dd>
                    <dt>
                    <#--借款期限（${plan.repaymentPeriod}）<br>-->
                        项目期限
                    </dt>
                </dl>
                <dl class="didt">
                    <dd><#if (plan.bidMoney>=10000)><i>${(plan.bidMoney/10000)?string(",##0.00#")}</i>万元<#else><i>${plan.bidMoney?string(',##0')}</i>元</#if></p></dd>
                    <dt>借款金额</dt>
                </dl>
            </div>
            <div class="progressBar">
                <i><b style="width: ${plan.progress}%;" class="progressBar_pending"></b></i><em>${plan.progress}%</em>
                <span></span>
            </div>
            <ul>

                <li>
                    还款方式
                   <#-- <#if plan.theWayBack == '按期计息到期还本' >
                    <span>按期计息</span><#else >-->
                    <span>${plan.theWayBack!}</span>
                   <#-- </#if>-->
                </li>
                <li>
                    起息日期
                    <span>${plan.startIntentDate!'满标日期'}</span>
                </li>
                <li class="bg_li">
                    剩余募集时间
                    <#if plan.state = 7 >
                    <span id="time">募集期结束</span>
                    <#else>
                    <input id="timestart" type="hidden" value="${plan.remainingTime!}">
                    <span id="time"></span>
                    </#if>

                </li>
                <li class="bg_li">
                    募集期利率
                    <span>${plan.raiseRate!}%/年</span>
                </li>
                <li style="width: 100%">
                    <p style="display: inline-block;height:28px;line-height:28px;font-size: 12px;color:666;margin-top: 20px;">
                        <img style="display: inline-block;width: 18px;height:18px;vertical-align: text-bottom;" src="${base}/theme/${systemConfig.theme}/images/reminder.png" alt="">&nbsp;温馨提示：募集期利息为投资日到满标当日所记利息，为平台单独发放的福利，与实际标的利息无关，募集期利息发放日期为满标当日。</p>
                </li>

            	<#--<li><em><img src="${base}/theme/proj_wenandai/images/ty-icon1.png"  alt="" /></em>原始债权金额：<span>1000</span>
                <a href="${base}/html/secureSingle.do?toWhere=projectDesc#projectDesc"><span style="display:inline-block;" >[?]</span></a>
                </li>
                 <li><em><img src="${base}/theme/proj_wenandai/images/ty-icon2.png"  alt="" /></em>年化收益率：<span>12%</span>
                <a href="${base}/html/secureSingle.do?toWhere=projectDesc#projectDesc"><span style="display:inline-block;" >[?]</span></a>
                </li>
                 <li><em><img src="${base}/theme/proj_wenandai/images/ty-icon3.png"  alt="" /></em>债权到期日：<span>2016-08-24</span>
               <a href="${base}/html/secureSingle.do?toWhere=projectDesc#projectDesc"><span style="display:inline-block;" >[?]</span></a></li>-->
                
            <#--<#if plan.coupon==1>-->
        	<#--<li>-->
                <#--<font class="font-three">券</font>-->
                <#--返现比例：<b class="red" style="font-weight:normal;">${plan.returnRatio}%</b>-->
                <#--<span style="display:inline-block;" class="icon-two">[?]</span>-->
                <#--<div class="sidnext1" style="display:none;">-->
                    <#--<p align="left"><b>有效面值：</b>面值*折现比例</p>-->
                <#--<p align="left"><b>返利方式：</b>-->
        	<#--<#if plan.rebateWay==1>-->
        	<#--立返(放款时一次性返利)，-->
        	<#--</p>	-->
        		<#--<p align="left">-->
                    <#--<b>返利类型：</b>-->
            	<#--<#if plan.rebateType==1>-->
            		<#--返现。立返=面值*折现比例</#if>-->
            	<#--<#if plan.rebateType==2>-->
            	<#--返息。立返=以面值*折现比例 按标的计息方式生成收息表(没有本金)进行加合利息</#if>-->
            	<#--<#if plan.rebateType==3>-->
            	<#--返息现。立返=以面值*折现比例 按标的计息方式生成收息表(有本金)进行加合本息</#if>-->
            	<#--<#if plan.rebateType==4>-->
            	<#--加息。利率为加息利率,投资金额按照利息计算方式（只生成利息）-->
                <#--</#if>-->
                <#--</p>-->
            <#--</#if>-->
        	<#--<#if plan.rebateWay==2>-->
        	<#--随期(随期进行返利)-->
        	<#--</p><p align="left">-->
                <#--<b>返利类型：</b>-->
            	<#--<#if plan.rebateType==1>-->
            		<#--返现。随期=当期本金*【面值*折现比例/实际投资额】</#if>-->
            	<#--<#if plan.rebateType==2>-->
            	<#--返息。随期=以面值*折现比例按标的计息方式生成收息表(没有本金)</#if>-->
            	<#--<#if plan.rebateType==3>-->
            	<#--返息现。随期=以面值*折现比例按标的计息方式生成收息表(有本金) 随息发放本息</#if>-->
            	<#--<#if plan.rebateType==4>-->
            	<#--加息。利率为加息利率,投资金额按照利息计算方式（只生成利息）-->
                <#--</#if>-->
            <#--</p>-->
            <#--</#if>-->
        	<#--<#if plan.rebateWay==3>-->
        	<#--到期(贷款结束时返利)，-->
        	<#--</p>	-->
        		<#--<p align="left">-->
                    <#--<b>返利类型：</b>-->
            	<#--<#if plan.rebateType==1>-->
            		<#--返现。立返=面值*折现比例</#if>-->
            	<#--<#if plan.rebateType==2>-->
            	<#--返息。立返=以面值*折现比例 按标的计息方式生成收息表(没有本金)进行加合利息</#if>-->
            	<#--<#if plan.rebateType==3>-->
            	<#--返息现。立返=以面值*折现比例 按标的计息方式生成收息表(有本金)进行加合本息</#if>-->
            	<#--<#if plan.rebateType==4>-->
            	<#--加息。利率为加息利率,投资金额按照利息计算方式（只生成利息）进行加合利息-->
                <#--</#if>-->
                <#--</p>-->
            <#--</#if>-->

                <#--</div>-->
            <#--</li>-->
        <#--</#if>-->
            <#--<!--<li><em class="icon-one"><img src="${base}/theme/proj_wenandai/images/ty-icon5.png"  alt=""/></em>还款方式：<span>${plan.theWayBack!}</span>-->
                 <#--<#if plan.payIntersetWay=='1'><div class="sidnext" style="display:none;">等额本息还款法是在还款期内，每月偿还同等数额的贷款(包括本金和利息)。借款人每月还款额中的本金比重逐月递增、利息比重逐月递减。</div>-->
                 <#--</#if>-->
                 <#--<#if plan.payIntersetWay=='2'>-->
                 <#--<div class="sidnext" style="display:none;">等额本金是在还款期内把贷款数总额等分，每月偿还同等数额的本金和剩余贷款在该月所产生的利息，每月的还款本金额固定，而利息越来越少</div>-->
                 <#--</#if>-->
                 <#---->
                 <#--<#if plan.payIntersetWay=='3'>-->
                 <#--<div class="sidnext" style="display:none;">等本等息还款法是在还款期内，每个月平均偿还本金和利息，利息一直按总本金计算</div>-->
                 <#--</#if>-->
                  <#--<#if plan.payIntersetWay=='4'>-->
                  <#--<div class="sidnext" style="display:none;">先息后本还款法是在还款期内，每月只偿还利息，在最后一期偿还利息的同时偿还全部本金。</div>-->
                <#--</#if>-->
                <#--</li>&ndash;&gt;-->
            <#--<!--<li><em><img src="${base}/theme/proj_wenandai/images/ty-icon4.png" />保障方式：<span>本金保障</span>&ndash;&gt;-->
            <#--<a href="${base}/html/secureSingle.do?toWhere=projectDesc#projectDesc"><!--<span style="display:inline-block;" >[?]</span>&ndash;&gt;</a>-->
        <#--&lt;#&ndash; <#if planKeep.plKeepGtorz.name??><#if (planKeep.plKeepGtorz.name)?length gt 6>${(planKeep.plKeepGtorz.name)?substring(0,6)}...<#else>${planKeep.plKeepGtorz.name}</#if></#if>&ndash;&gt;-->
            <#--</li>-->
        <#--&lt;#&ndash; <li class="none">月还款额（元）：${planPro.monthInterestRate?string(',###.00')}</li>&ndash;&gt;-->
               <#--<#if plan.raiseRate gt 0>-->
               <#--<li>-->
                   <#--<em><img src="${base}/theme/proj_wenandai/images/baoz_icon.jpg" /></em>-->
                   <#--募集期利率：${plan.raiseRate}%/年<span style="display:inline-block;" class="icon-three">[?]</span>-->
                   <#--<div class="sidnext2" style="display:none;">-->
                       <#--<p align="left"><b>募集天数：</b>起息日-投资日（投资当天起息不计算）</p>-->
                       <#--<p align="left"><b>计算公式：</b>投资金额*日化募集期利率*募集天数</p>-->
                   <#--</div>-->
               <#--</li>-->
               <#--</#if>-->
            </ul>
        </div>
         
        <!-- start 已满标-->
        <#if plan.state==2 || plan.state==5|| plan.state==6> 
        <div class="inner-right1">
        	<#--<i class="bg"></i>-->
        	<h3>已满标</h3>
            <div class="sumb">
            	   <ul>
                    	<li>满标用时：</li>
                    	<li style="font-size:20px;">${plan.bidFullTime}</li>
                    	 
                        <li>加入人次：<span class="bigger">${plan.persionNum}人</span></li>                       
                    	
                    </ul>
                <div class="btn btn_mygray">
                    <input  type="button" value="&nbsp;&nbsp;已满标&nbsp;&nbsp;">
                </div>
            </div>
        </div>
        <!--end 已满标-->
        <!-- start还款中-->
        
        <#elseif plan.state==7> 
        <div class="inner-right1" >
        	<#--<i class="bg"></i>-->
        	<h3>还款中</h3>
            <div class="sumb">
            		
                    <ul>
                    	 <li>待还本息：<span class="big">${plan.notRepaymentMoney}元</span><span class="myicon"><img src="${base}/theme/${systemConfig.theme}/images/mypic/tixing.png" alt=""><i class="ptit">待还本息=(借款金额*预期年化收益率)/365*(最后一期还款日期-起息日期)-已还金额<br><b>具体情况请以合同为准。</b></i></span></li>
                    	 <li>还款进度：<span class="big">${plan.repayMentLength}</span></li> 
                    	
                        <li>下次还款日：<span class="big">${plan.nextRepaymentDate}</span></li>                       
                    	
                    </ul>
                    <div class="btn btn_mygray">
                        <input  type="button" value="&nbsp;&nbsp;还款中&nbsp;&nbsp;">
                    </div>
            </div>
        </div>
        <!-- end还款中-->
        <!-- start已还清-->  
        <#elseif plan.state==10>  
        <div class="inner-right1" >
        	<#--<i class="bg"></i>-->
        	<h3>已还清</h3>
            <div class="sumb">
            		
                    <ul>
                    	<li>还清日期：${plan.repaymentFullDate}</li>
                    	
                        <li>加入人次：<span class="bigger">${plan.persionNum}人</span></li>
                    </ul>
                    <div class="btn btn_mygray">
                       <input  type="button" value="&nbsp;&nbsp;已还清&nbsp;&nbsp;">
                   </div>
            </div>
        </div>
        <!-- end 已还清 -->
        <!-- start 已过期 -->
        <#elseif plan.state==3>
		 <div class="inner-right1" >
		 	<#--<i class="bg"></i>-->
        	<h3>已流标</h3>
            <div class="sumb">
            	<ul>
                    	<p style="padding:20px 0;font-size:16px;text-align:center;">已流标！</p>
                  </ul>
            </div>
        </div>
         <#elseif plan.state==4>
		 <div class="inner-right1" >
		 	<#--<i class="bg"></i>-->
        	<h3>已流标</h3>
            <div class="sumb">
            	<ul>
                    	<p style="padding:20px 0;font-size:16px;text-align:center;">已过期！</p>
                  </ul>
            </div>
        </div>
        <#else>
        <#if plan.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt plan.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") ><!-- 是否还在预售-->
        <div class="inner-right1">
        	<#--<i class="bg"></i>-->
                <#if Session.successHtml=='undefined'||Session.successHtml==null>
        	<h3>未登录</h3>
                <#else>
                	<h3>可出借</h3>
                </#if>
            <div class="sumb">
            	<form id="bid_Form" action="${base}/creditFlow/financingAgency/bidingPlBidInfo.do" method="post">
            		<div style="width:300px; overflow:hidden; position: relative;margin-bottom:5px;">
					<input name="plBidInfo.bidId" type="hidden" id="plBidInfobidId">
					<input name="plBidInfo.bidName" type="hidden" id="plBidInfobidName">
					<input name="plBidInfo.userMoney" type="hidden" id="plBidInfouserMoney">
					<input name="plBidInfo.userName" type="hidden" id="plBidInfouserName">
					<input name="plBidInfo.userId" type="hidden" id="plBidInfouserId">
					<input name="formtoken" type="hidden" id="formtoken" value="${formtoken}">
					<input  type="hidden" id="upperMoney" value="${plan.maxMoney}">
					<input name="couponId" type="hidden" id="couponId" value="">
                	<input type="text" style="width:249px;" id="sign_Money"  onblur="chkMoney1('sign_Money',${plan.afterMoney},'currM',${plan.startMoney},${(planPro.yearInterestRate)},'${plan.loanLife}');"  class="good_input" placeholder="${plan.startMoney}元起投，且为${plan.riseMoney}元整数倍"/>

                	</div>
                    <ul>

                        <li style="width:260px;height:auto;/*height:14px; line-height:10px;*/line-height:16px;margin-bottom:5px;"><span id="moneyspan"></span></li>


                    	 <#if Session.successHtml=='undefined'||Session.successHtml==null> 
                    	   <li>账户余额：<span style="color:#f65540;">登录可见</span></li>
                    	 <#else>
                           <li class="clearfix">
                               <div class="lf">
                                   账户余额：<span style="color:#f65540;" id="currM">${bpCustMember.availableInvestMoney?string(',##0.00')}元</span>
                               </div>
                               <a class="rt" href="javascript:void(0)" onClick="bidAll1(${plan.afterMoney},${plan.startMoney},${plan.riseMoney})" >全投</a>
                           </li>
                    	</#if>
                        <li>剩余可投：<input type="hidden" id="afterM" value="${plan.afterMoney}"/><span >${plan.afterMoney?string(',##0.00')}元</span></li>
                    </ul>
                    <#if Session.successHtml=='undefined'||Session.successHtml==null>
                  		<a href="${base}/htmllogin.do?retUrl=" target="_self" id="bidLogin"> <input  type="button"   value="&nbsp;&nbsp;&nbsp;立即登录 &nbsp;&nbsp;" style="height:30px; cursor:pointer;"></input></a>
                   <#else>
                   		<div class="btn">
                   		 <#if (plan.state)??>
						    <#if plan.state!=5>
						    		<#if (plan.laveTime)??>
						    			<#if plan.laveTime?index_of('结束')!=-1>
						    				<input  type="button" value="&nbsp;&nbsp;招标结束&nbsp;&nbsp;">
						    			<#else>
						    				<#if plan.state==0>
						    					<input  type="button" value="&nbsp;&nbsp;未发布&nbsp;&nbsp;">
						    				<#elseif plan.state==1>
						    					<#if bpCustMember.grade?? >
                                                    <#if bpCustMember.isCheckCard != 1 >
                                                           	<a id="to" style="padding:0; border:0;" href="${base}/thirdreg.do">
                                                                <input class="cj"  type="button"  value="&nbsp;&nbsp;请先实名认证&nbsp;&nbsp;" >
                                                            </a>
                                                    <#else>
								    				<a id="to" rel="leanModal" style="padding:0; border:0;"name="signup" onClick="check('${bidId}','${afterMoney}','${yearInterestRate}',true)" href="#signup">
							    						<input class="cj"  type="button"  value="&nbsp;&nbsp;立即出借&nbsp;&nbsp;" >
							    					</a>
                                                    </#if>
						    					<#else>
                                                       <a id="to" style="padding:0; border:0;"name="signup" onClick="check('${bidId}','${afterMoney}','${yearInterestRate}',true)" href="#signup">
                                                           <input class="cj"  type="button"  value="&nbsp;&nbsp;立即出借&nbsp;&nbsp;" >
                                                       </a>
							    				</#if>
						    				<#--<#elseif plan.state==2 ||plan.state==5||plan.state==6>-->
						    					<#--<input  type="button" value="&nbsp;&nbsp;已满标&nbsp;&nbsp;">-->
						    				<#--<#elseif plan.state==3>-->
						    					<#--<input  type="button" value="&nbsp;&nbsp;已流标&nbsp;&nbsp;">-->
						    				<#--<#elseif plan.state==4>-->
						    					<#--<input  type="button" value="&nbsp;&nbsp;已过期&nbsp;&nbsp;">-->

						    				<#--<#elseif plan.state==7>-->
						    					<#--<input  type="button" value="&nbsp;&nbsp;还款中&nbsp;&nbsp;">-->
						    						<#--<#elseif plan.state==10>-->
						    					<#--<input  type="button" value="&nbsp;&nbsp;已完成&nbsp;&nbsp;">-->
						    				<#--<#else>-->

						    				</#if>
						    			</#if>
						    		</#if>
						   		<#else>
						   		<input  type="button" value="&nbsp;&nbsp;还款中&nbsp;&nbsp;">
						    </#if>
					    </#if>
					    	 	<input type="hidden" value="${plan.maxMoney}" id="maxMoney" />

					    	 	<a class="cz" href="${base}/financePurchase/rechargeFinancePurchase.do">充值</a>
                             		</div>
            </#if>
                   
                </form>
                <p style="font-size:15px;"><i style=" font-style: normal;margin-left:20px;color:red;vertical-align: middle;">*</i> 网贷有风险，出借需谨慎。</p>
            </div>
        </div>
        <#else>
        	<div class="inner-right1" >
        		<#--<i class="bg"></i>-->
        	<h3>预售中</h3>
            <div class="sumb">
            		
                    <ul>
                    	<li>开始投标时间：</li>
                    	<li><#if plan.prepareSellTime??>${plan.publishSingeTime?string("yyyy-MM-dd HH:mm:ss")}</#if></li>
                    </ul>
                
            </div>
        </div>
        </#if>
        </#if>
        <!--end 申请失败-->
        
      
    </div>
	<div class="my-lc">
		<ul>
			<li class="con">选标出借</li>
			<li class="line"></li>
            <li class="con con2">开始计息</li>
            <li class="line"></li>
            <li class="con con3">到期回款</li>
            <li class="line"></li>
            <li class="con con4">提现到账</li>
		</ul>
	</div>
    <div class="finlist my-finlist" style="margin:20px 0 0;">
       <ul class="finlist_title">
         <li class="active">标的详情</li>
          <#--<span class="sept">|</span>-->
         <#-- <li>相关材料</li>
          <span class="sept">|</span>   -->
          <li>投标记录</li>
         <#-- <span class="sept">|</span>
          <li>还款表现</li>
          <span class="sept">|</span>
         	 <li id="zqxx">债权信息</li>
             <li id="ppxx">债权匹配信息</li>
          <span class="sept">|</span>
          <li>转让记录</li>
          <span class="sept">|</span>
          <li>贷后信息</li>-->
          <!--<span class="sept">|</span>
          <li>债权匹配信息</li>-->

       </ul>
<#--测试专用-->
<#--<#if plan.bidId gt 100>-->
<#--线上-->
<#if plan.bidId gt 6842 >
       <div class="finlist_hide my-finlist_hide">
         <ol style="display:block">
          <div class="content">
            <div class="contentleft">
              <div class="contentleft1">
              <span class="bg"></span>
                <h5>企业基本信息</h5>
               </div>
                <table class="my-table">
                    <tr>
                        <td class="td1">企业名称</td>
                        <td class="td2">
                            <#if enterPrise.enterprisename=="">未填<#else>${enterPrise.enterprisename}</#if>
                        </td>
                        <td class="td1">所有制性质</td>
                        <td class="td2"><#if enterPrise.ownership=="">未填<#else>${enterPrise.ownership!}</#if></td>
                    </tr>
                    <tr>
                        <td class="td1">注册资金</td>
                        <td class="td2"><#if enterPrise.registermoney=="">未填<#else>${enterPrise.registermoney!}万元</#if></td>
                        <td class="td1">统一信用代码</td>
                        <td class="td2"><#if enterPrise.organizecode=="">未填<#else>${enterPrise.organizecode!}</#if></td>
                    </tr>
                    <tr>
                        <td class="td1">经营所在地</td>
                        <td class="td2"><#if enterPrise.managecityName=="">未填<#else>${enterPrise.managecityName!}</#if></td>
                        <td class="td1">所属行业</td>
                        <td class="td2"><#if enterPrise.hangyeName=="">未填<#else>${enterPrise.hangyeName!}</#if></td>
                    </tr>
                    <tr>
                        <td class="td1">营业范围</td>
                        <td class="td2"><#if planKeep.proBusinessScope=="">未填<#else>${planKeep.proBusinessScope!}</#if></td>
                        <td class="td1">主要债务</td>
                        <td class="td2"><#if planKeep.mainDebt=="">未填<#else>${planKeep.mainDebt!}</#if></td>
                    </tr>

                <#-- <tr>
                     <td class="td1">营业范围</td>
                     <td class="td2"><#if planKeep.proBusinessScope=="">未填<#else>${planKeep.proBusinessScope!}</#if></td>
                     <td class="td1">经营描述</td>
                     <td class="td2"><#if planKeep.proBusinessStatus=="">未填<#else>${planKeep.proBusinessStatus!}</#if></td>
                 </tr>
                 <tr>
                     <td class="td1">主要债务</td>
                     <td class="td2"><#if planKeep.mainDebt=="">未填<#else>${planKeep.mainDebt!}</#if></td>
                     <td class="td1">主要财务</td>
                     <td class="td2"><#if planKeep.mainFinance=="">未填<#else>${planKeep.mainFinance!}</#if></td>
                 </tr>-->
                </table>
              <#--<dl class="contentleft2">
                <dt>企业名称:</dt>
                <dd><#if enterPrise.enterprisename=="">未填<#else>${enterPrise.enterprisename?substring(0,2)}******${enterPrise.enterprisename?substring(enterPrise.enterprisename?length-2)}</#if></dd>
               </dl>
              <dl class="contentleft2">
                <dt>所有制性质:</dt>
                <dd><#if enterPrise.ownership=="">未填<#else>${enterPrise.ownership!}</#if></dd>
                </dl>-->
                <#-- <dl class="contentleft2">
                <dt>注册资金:</dt>
                 <dd><#if enterPrise.registermoney=="">未填<#else>${enterPrise.registermoney!}万元</#if></dd>
                </dl>
                <dl class="contentleft2">
                <dt>注册时间:</dt>
                <dd><#if enterPrise.opendate=="">未填<#else>${enterPrise.opendate!}</#if></dd>
                </dl>
                 <dl class="contentleft2">
                <dt>经营所在地:</dt>
                <dd><#if enterPrise.managecityName=="">未填<#else>${enterPrise.managecityName!}</#if></dd>
                </dl>
                 <dl class="contentleft2">
                <dt>行业:</dt>
                <dd><#if enterPrise.hangyeName=="">未填<#else>${enterPrise.hangyeName!}</#if></dd>
                </dl>
                <dl class="contentleft2">
               	<dt>营业范围:</dt>
                <dd><#if planKeep.proBusinessScope=="">未填<#else>${planKeep.proBusinessScope!}</#if></dd>
              </dl>
              <dl class="contentleft2">
               	<dt>经营描述:</dt>
                <dd><#if planKeep.proBusinessStatus=="">未填<#else>${planKeep.proBusinessStatus!}</#if></dd>
              </dl>
              <dl class="contentleft2">
           		<dt>主要财务:</dt>
            	<dd style="line-height:15px;padding-top:9px;"><#if planKeep.mainFinance=="">未填<#else>${planKeep.mainFinance!}</#if></dd>
          	  </dl>
          	  <dl class="contentleft2">
            	<dt>主要债务:</dt>
            	<dd style="line-height:15px;padding-top:9px;"><#if planKeep.mainDebt=="">未填<#else>${planKeep.mainDebt!}</#if></dd>
          	  </dl>                -->
            </div>
        </div>
          <div class="content">
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                <h5 class="icon2">项目信息</h5>
               </div>


                <table class="my-table">
                    <tr>
                        <td class="td1">项目描述</td>
                        <td class="td3">
                            ${planKeep.proDes}
                        </td>
                    </tr>
                    <tr>
                        <td class="td1">资金用途</td>
                        <td class="td3">融资款主要用于工程机械整机采购</td>
                    </tr>
                    <tr>
                        <td class="td1">还款来源</td>
                        <td class="td3">1）租赁工程机械租金保障 2）设备残余价值保障 3）融资租赁公司其他待回款保障 4）相关产业链企业提供还款保障！</td>
                    </tr>
                </table>
               	<#-- <dl class="contentleft2" style="width:84%;">
               	<dt>项目描述:</dt>
                <dd style="width:100%;">${planKeep.proDes}</dd>
                </dl>
                <dl class="contentleft2"  style="width:84%;">
               	<dt>资金用途:</dt>
                <dd  style="width:100%;">${planKeep.proUseWay!}</dd>
                </dl>

                <dl class="contentleft2"  style="width:84%;">
                <dt>还款来源:</dt>
                <dd  style="width:100%;">${planKeep.proPayMoneyWay!}</dd>
              </dl> -->
            </div>
        </div>
        <#--<div class="content">
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                <h5>担保信息</h5>
               </div>

               <dl class="contentleft2">
               	<dt>担保公司名称:</dt>
                <dd>${(planKeep.guarantorsName)!}</dd>
                </dl>
                <dl class="contentleft2">
               	<dt>担保机构简介:</dt>
                <dd>${planKeep.guarantorsDes}</dd>
                </dl>
                <dl class="contedntleft2">
                <dt>担保说明:</dt>
                <dd>${planKeep.guarantorsAdvise!}</dd>
              </dl>
            </div>
        </div> -->
        <#--<div class="content">
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                <h5>风险控制</h5>
               </div>
               <dl class="contentleft2" style="width:84%;">
               	<dt style="width:100%;">${planKeep.proRiskCtr!}</dt>
              </dl>
            </div>
        </div>  -->

            <#--经营公示图片-->
             <p style="font-size: 18px;color:#333;text-align: left;margin-top: 30px">
            <#if fileLIst?? && fileLIst?size gt 0  >
                 <i style="display:inline-block;    margin-right: 10px;width:24px;height:20px;vertical-align:sub;background:url(${base}/theme/${systemConfig.theme}/images/03.png)no-repeat;"></i>经营公示
             <#else>  </p>
             </#if>
             <div class="htmleaf-container" style="width:100%;margin-top: 20px;">
                 <div style="width: 845px;    margin: 0 auto;">
                 <#if fileLIst?? && fileLIst?size gt 0 >
                     <ul class="vmcarousel-centered-infitine vmc-centered" style="width: 1824px">

                         <#list fileLIst?sort_by("sort") as fList >
                         <li>
                             <img src="${base}/${fList.webPath!}" alt=""   data_src="${base}/${fList.webPath!}" width="260">
                             <div class="cover"></div>
                             <span class="span_img"></span>
                             <p>${fList.setname!}</p>
                         </li>
                         </#list>

                         <#--<li>
                             <img src="${base}/theme/${fileLIst.webPath}" alt=""   data_src="${base}/theme/${systemConfig.theme}/images/shebei/33.jpg" width="260">
                             <div class="cover"></div>
                             <span class="span_img"></span>
                             <p style="">开户许可证</p>
                         </li>
                         <li>
                             <img src="${base}/theme/${systemConfig.theme}/images/shebei/8.jpg" alt=""   data_src="${base}/theme/${systemConfig.theme}/images/shebei/88.jpg" width="260">
                             <div class="cover"></div>
                             <span class="span_img"></span>
                             <p style="">华文融资租赁有限公司营业执照</p>
                         </li>
                         <li>
                             <img src="${base}/theme/${systemConfig.theme}/images/shebei/4.jpg" alt=""   data_src="${base}/theme/${systemConfig.theme}/images/shebei/44.jpg" width="260">
                             <div class="cover"></div>
                             <span class="span_img"></span>
                             <p style="">外商投资企业设立备案</p>
                         </li>-->
                     </ul>
                 </#if>
                 </div>
                 <div id="outerdiv"  style="position:fixed;top:0;left:0;background:rgba(100, 100, 100, 0.5);z-index:35;width:100%;height:100%;display:none;">
                     <div id="innerdiv" style="position:absolute;">
                         <i id="fork"></i>
                         <img id="bigimg" style="border:5px solid #fff;" src="" alt="">
                     </div>
                 </div>
             </div>
             
             <#--设备公示-->
             <p style="font-size: 18px;color:#333;text-align: left;margin-top: 30px">
                 <#if listMaterials?? && listMaterials?size gt 0  >
                 <i style="display:inline-block;    margin-right: 10px;width:24px;height:20px;vertical-align:sub;background:url(${base}/theme/${systemConfig.theme}/images/04.png)no-repeat;"></i>设备公示</p>
                 <#else ></p>
                 </#if>
             <div class="htmleaf-container" style="width:100%x;margin-top: 20px;">
                 <div style="width: 845px;    margin: 0 auto;">
             <#if listMaterials?? && listMaterials?size gt 0 >
                     <ul class="vmcarousel-centered-infitine vmc-centered" style="width: 1692px">
                 <#list listMaterials as list>
                         <li>
                             <img src="${base}/${list.imgUrl!}" alt=""   data_src="${base}/${list.imgUrl!}" width="260">
                             <div class="cover"></div>
                             <span class="span_img"></span>
                             <p>${list.materialsName!}</p>
                         </li>
             </#list>
                     </ul>
             </#if>
                 </div>
                 <div id="outerdiv"  style="position:fixed;top:0;left:0;background:rgba(100, 100, 100, 0.5);z-index:35;width:100%;height:100%;display:none;">
                     <div id="innerdiv" style="position:absolute;">
                         <i id="fork"></i>
                         <img id="bigimg" style="border:5px solid #fff;" src="" alt="">
                     </div>
                 </div>
             </div>
                           <#--
                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/A1.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/A3.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S01T021外观</p>
                               </div>
                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/A2.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/A2.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S01T021合格证</p>
                               </div>
                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/A3.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/A1.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S01T021标牌</p>
                               </div>


                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/B1.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/B3.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S02T022外观</p>
                               </div>
                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/B2.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/B1.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S02T022合格证</p>
                               </div>
                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/B3.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/B2.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S02T022标牌</p>
                               </div>


                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/C1.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/C3.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S02T023外观</p>
                               </div>
                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/C2.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/C1.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S02T023合格证</p>
                               </div>
                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/C3.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/C2.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S02T023标牌</p>
                               </div>



                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/D1.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/D3.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S02T024外观</p>
                               </div>
                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/D2.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/D1.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S02T024合格证</p>
                               </div>
                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/D3.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/D2.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S02T024标牌</p>
                               </div>


                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/E1.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/E3.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S02T027外观</p>
                               </div>
                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/E2.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/E1.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S02T027合格证</p>
                               </div>
                               <div class="swiper-slide  slide_box">
                                   <img src="${base}/theme/${systemConfig.theme}/images/shebei/E3.png"
                                        data_src="${base}/theme/${systemConfig.theme}/images/shebei/E2.jpg" alt="">
                                   <div class="cover"></div>
                                   <span class="span_img"></span>
                                   <p>2018S02T027标牌</p>
                               </div>-->
                           <#--</div>-->
                           <!-- Add Pagination -->
                       <#-- <div class="swiper-pagination"></div>
                        <!-- Add Arrows &ndash;&gt;-->
            <#--           </div>
                       <div class="swiper-button-next"></div>
                       <div class="swiper-button-prev"></div>
                   </div>
               </div>-->

       </ol>


       <#-- <div class="contentleft">
                                       <div class="contentleft1">
                                           <span class="bg"></span>
                                           <h5>经营公示</h5>
                                       </div>
                                       <dl class="contentleft2" style="width:84%;">
                                           <dt style="width:100%;">${planKeep.proRiskCtr!}</dt>
                                       </dl>
                                   </div>-->
       	<ol>
       	 <div class="content">
       	 	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
             <#else>
    	<!--借款材料 start -->
             <div class="contentleft">
               <div class="tab tab1">
                   <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                       <thead>
                       <tr>
                           <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                           <th width="150" align="center" bgcolor="#47B2D6">投标人</th>
                           <th width="50" align="center" bgcolor="#47B2D6">年利率</th>
                           <th width="150" align="center" bgcolor="#47B2D6">投标金额</th>
                           <th width="150" align="center" bgcolor="#47B2D6">投标时间</th>
                       </tr>
                       </thead>
                       <tbody id="content">
                           <#if (listPlBid!=null)>
                           <#list listPlBid as list>
                        <tr>
                            <td height="40" align="center" bgcolor="#FAFBFD">${list_index+1}</td>
                            <td align="center" bgcolor="#FAFBFD">${list.userName?substring(0,1)}****</td>
                            <td align="center" bgcolor="#FAFBFD">${planPro.yearInterestRate}%</td>
                            <td align="center" bgcolor="#FAFBFD">${list.userMoney?string(',###.00')}</td>
                            <td align="center"
                                bgcolor="#FAFBFD"><#if (list.bidtime==null)><#else>${list.bidtime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                        </tr>
                           </#list>
                           </#if>
                       </tbody>
                   </table>
                   <input type='hidden' id='current_page' />
                   <input type='hidden' id='show_per_page' />
                   <div id='page_navigation'></div>
            	<#--<table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">-->
                       <#--<tr>                        -->
                        <#--<th width="150" height="40"align="center" bgcolor="#47B2D6">审核项目</th>-->
                        <#--<th width="100" align="center" bgcolor="#47B2D6">状态</th>-->
                        <#--<th width="100" align="center" bgcolor="#47B2D6">通过日期</th>-->
                        <#--<th width="100" align="center" bgcolor="#47B2D6">详细</th>-->
                      <#--</tr>-->
                      <#--<#list listMaterials as list>-->
	                      <#--<tr>-->
                              <#--<td align="center" height="40" bgcolor="#FAFBFD">${list.materialsName}</td>-->
                              <#--<td align="center" bgcolor="#FAFBFD">通过</td>-->
                              <#--<td align="center" bgcolor="#FAFBFD"><#if list.createTime==null>---->
                                  <#----<#else>${list.createTime!}</#if></td>-->
                              <#--<td align="center" bgcolor="#FAFBFD"><a>-->
		                        <#--<#if list.imgUrl !="">-->
			                	<#--<a rel="group" class="spec_img" href="${base}/${list.imgUrl}"-->
                                   <#--title="${list.materialsName}"><i class=""></i>-->
                                    <#--<img src="${base}/theme/proj_wenandai/images/chakan.png" title="查看"></a>-->
                                <#--<#else>-->
			                	<#--未上传材料-->
                                <#--</#if>-->
						        <#---->
		                    	<#--<#list list.fileFormList as filelist>-->
                                    <#--<#if filelist_index !=0>-->
			                	<#--<a rel="group" class="spec_img" href="${base}/${filelist.filepath!}"-->
                                   <#--title="${list.materialsName}"><i class=""></i>-->
                                    <#--<img alt="${list.materialsName}" style="display:none"-->
                                         <#--src="${base}/${filelist.filepath!}" width="100" height="80"/></a>-->
                                    <#--</#if>-->
                                <#--</#list>-->
                              <#--</a>-->
                              <#--</td>-->
                          <#--</tr>-->
                      <#--</#list>-->
                      <#---->
                <#--</table>-->
            </div>                
            </div>
    	<!--借款材料 end -->
             </#if>
                        </div>
                    </ol>

                        <#--标的其他信息-->
          <#--          <ol>
                        <div class="content">
       	                    <#if Session.successHtml=='undefined'||Session.successHtml==null>
                                <div style="text-align:center;"><span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
                            <#else>
                               <h2 class="title"><span>加入人次${plan.persionNum}人</span>
                                   投标总额${(plan.bidMoney-plan.afterMoney)?string(',##0.##')}元</h2>
                                <div class="tab">
                                    <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                                            <th width="150" align="center" bgcolor="#47B2D6">投标人</th>
                                            <th width="50" align="center" bgcolor="#47B2D6">年利率</th>
                                            <th width="150" align="center" bgcolor="#47B2D6">投标金额</th>
                                            <th width="150" align="center" bgcolor="#47B2D6">投标时间</th>
                                        </tr>
                                           <#if (listPlBid!=null)>
                                               <#list listPlBid as list>
                                            <tr>
                                                <td height="40" align="center" bgcolor="#FAFBFD">${list_index+1}</td>
                                                <td align="center" bgcolor="#FAFBFD">${list.userName?substring(0,1)}****</td>
                                                <td align="center" bgcolor="#FAFBFD">${planPro.yearInterestRate}%</td>
                                                <td align="center" bgcolor="#FAFBFD">${list.userMoney?string(',###.00')}</td>
                                                <td align="center"
                                                    bgcolor="#FAFBFD"><#if (list.bidtime==null)><#else>${list.bidtime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                                            </tr>
                                               </#list>
                                           </#if>
                                    </table>
                                </div>
                            </#if>
                        </div>
                    </ol>

                    <ol>
                        <div class="content">
                             <#if Session.successHtml=='undefined'||Session.successHtml==null>
                                 <div style="text-align:center;"><span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
                             <#else>
                                 <#assign sum1=0>
                                 <#assign sum2=0>
                                 <#list  slFundList as list>
                                     <#if list.afterMoney??>
                                         <#assign sum1=sum1 +list.afterMoney>
                                     </#if>
                                     <#if list.incomeMoney??>
                                         <#assign sum2=sum2 +list.incomeMoney>
                                     </#if>
                                 </#list>

                                <h2 class="title"><span>已还本息<#if sum1??>${sum1?string(",##0.00")}<#else>--</#if> 元</span>
                                    待还本息<#if sum1?? && sum2??>${(sum2-sum1)?string(",##0.00")}<#else>--</#if>元</h2>
                                <div class="tab">
                                    <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <th width="150" height="40" align="center" bgcolor="#47B2D6">约定还款日期</th>
                                            <th width="150" align="center" bgcolor="#47B2D6">状态</th>
                                            <th width="150" align="center" bgcolor="#47B2D6">应还本息</th>
                                            <th width="150" align="center" bgcolor="#47B2D6">应还罚息</th>
                                            <th width="150" align="center" bgcolor="#47B2D6">实际还款日期</th>
                                        </tr>
                                          <#list slFundList as fundlist>
                                          <tr>
                                              <td height="40" align="center" bgcolor="#FAFBFD">${fundlist.intentDate}</td>
                                              <td align="center" bgcolor="#FAFBFD">
                                                <#if (fundlist.factDate==null)>
                                                    未偿还
                                                <#else>
                                                    已偿还
                                                </#if>
                                              </td>
                                              <td align="center" bgcolor="#FAFBFD">${fundlist.incomeMoney?string(',##0.00')}</td>
                                              <td align="center" bgcolor="#FAFBFD">${fundlist.accrualMoney?string(',##0.00')}</td>
                                              <td align="center" bgcolor="#FAFBFD"><#if (fundlist.factDate==null)>
                                                  --<#else>${fundlist.factDate}</#if></td>
                                          </tr>
                                          </#list>
                                    </table>
                                </div>
                             </#if>
                        </div>
                    </ol>
                    <ol id="showZX" style="margin-top:30px;">
                        <div class="content">
                            <#if Session.successHtml=='undefined'||Session.successHtml==null>
                                <div style="text-align:center;"><span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
                            <#else>
          		&lt;#&ndash; <h2 class="title">持有债权人数<#if (bondListBid!=null)> ${bondListBid?size}<#else>0</#if>人</h2>
	            <div class="tab">
                    <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                            <th width="150" align="center" bgcolor="#47B2D6">债权人</th>
                            <th width="150" align="center" bgcolor="#47B2D6">持有金额</th>
                        </tr>
	                      <#if (bondListBid!=null)>
                              <#list bondListBid as list>
                                  <#if (list_index<8)>
	                        <tr>
                                <td height="40" align="center" bgcolor="#FAFBFD">${list_index+1}</td>
                                <td align="center" bgcolor="#FAFBFD">${list.userName?substring(0,1)}****</td>
                                <td align="center" bgcolor="#FAFBFD">${list.bondTotelMoney?string(',###.00')}</td>
                            </tr>
                                  </#if>
                              </#list>
                          </#if>
                    </table>
                </div>&ndash;&gt;
            </#if>
                        </div>
                    </ol>
                    <!--债权匹配信息&ndash;&gt;
                    <ol id="showZP">
                        <div class="content">
       	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
             <div style="text-align:center;"><span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
         <#else>
          		 <h2 class="title">债权信息</h2>
	            <div class="tab">
                    <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                            <th width="150" align="center" bgcolor="#47B2D6">转让人</th>
                            <th width="150" align="center" bgcolor="#47B2D6">受让人</th>
                            <th width="150" align="center" bgcolor="#47B2D6">本次转让金额</th>
                            <th width="150" align="center" bgcolor="#47B2D6">转让时间</th>
                        </tr>
                 		<#if (pager.list !=null)>
                            <#list pager.list as list>
	                          <tr>
                                  <td height="40" align="center">${list_index+1}</td>
                                  <td align="center">${list.outCustName?substring(0,2)}
                                      ****${list.outCustName?substring(list.outCustName?length-2)}</td>
                                  <td align="center">${list.inCustName?substring(0,2)}
                                      ****${list.inCustName?substring(list.inCustName?length-2)}</td>
                                  <td align="center">${list.sumMoney?string(',###.00')}</td>
                                  <td align="center">${list.saleSuccessTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                              </tr>
                            </#list>
                        </#if>
	                    <#import "/WEB-INF/template/common/pager.ftl" as p>
        				<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do"/>

                    </table>
                </div>
         </#if>
                        </div>
                    </ol>
                    <!--债权匹配信息&ndash;&gt;
                    <ol>
                        <div class="content">
                        &lt;#&ndash;  <h2 class="title"><span>以交易总额0.00元</span> 待交易总额0.00元</h2>&ndash;&gt;
            <#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;"><span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
            <#else>
            &lt;#&ndash; <h2 class="title"><span>以交易总额0.00元</span> 待交易总额0.00元</h2>&ndash;&gt;
                <#if (saleList!=null)>
            <div class="tab">
                <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                        <th width="140" height="40" align="center" bgcolor="#47B2D6">债权买入者</th>
                        <th width="140" align="center" bgcolor="#47B2D6">债权卖出者</th>
                        <th width="140" align="center" bgcolor="#47B2D6">交易金额</th>
                        <th width="140" align="center" bgcolor="#47B2D6">交易时间</th>
                    </tr>
                      <#list saleList as sale>
                          <#if (sale_index<8)>
                      <tr>
                          <td height="40" align="center" bgcolor="#FAFBFD">${sale_index+1}</td>
                          <td align="center" bgcolor="#FAFBFD">${sale.inCustName?substring(0,2)}
                              ****${sale.inCustName?substring(sale.inCustName?length-2)}</td>
                          <td align="center" bgcolor="#FAFBFD">${sale.outCustName?substring(0,2)}
                              ****${sale.outCustName?substring(sale.outCustName?length-2)}</td>
                          <td align="center" bgcolor="#FAFBFD">${sale.sumMoney?string(',###.00')}</td>
                          <td align="center"
                              bgcolor="#FAFBFD">${sale.saleSuccessTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                      </tr>
                          </#if>
                      </#list>
                </table>
            </div>
                <#else>
            <p>无债权转让记录</p>
                </#if>
            </#if>
                        </div>
                    </ol>

                   <ol>
                        <div class="content">
       	 	<#if Session.successHtml=='undefined'||Session.successHtml==null>
                <div style="text-align:center;"><span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
            <#else>
                <#if plan.state==7 || plan.state==10>
	          	<div class="contentleft">
                    <div class="contentleft1">
                        <span class="bg"></span>
                        <h5>融资资金运用情况</h5>
                    </div>
                    <div class="txt">${planPro.moneyUseSituation!}</div>
                </div>
		       	<div class="contentleft">
                    <div class="contentleft1">
                        <span class="bg"></span>
                        <h5>借款人经营状况及财务状况</h5>
                    </div>
                    <div class="txt">${planPro.managementSituation!}</div>
                </div>
		       	<div class="contentleft">
                    <div class="contentleft1">
                        <span class="bg"></span>
                        <h5>借款人还款能力变化情况</h5>
                    </div>
                    <div class="txt">${planPro.repaymentChangeSituation!}</div>
                </div>
                <#else>
		       		<p><font size="4">抱歉，尚无可查看信息</font></p>
                </#if>
            </#if>
                        </div>
                    </ol>-->
                </div>
            </div>
        </div><#else >
<div class="finlist_hide my-finlist_hide">
    <ol style="display:block">
        <div class="content">
            <div class="contentleft">
                <div class="contentleft1">
                    <span class="bg"></span>
                    <h5>企业基本信息</h5>
                </div>
                <table class="my-table">
                    <tr>
                        <td class="td1">企业名称</td>
                        <td class="td2">
						<#if enterPrise.enterprisename=="">未填<#else>${enterPrise.enterprisename}</#if>
                        </td>
                        <td class="td1">所有制性质</td>
                        <td class="td2"><#if enterPrise.ownership=="">未填<#else>${enterPrise.ownership!}</#if></td>
                    </tr>
                    <tr>
                        <td class="td1">注册资金</td>
                        <td class="td2"><#if enterPrise.registermoney=="">未填<#else>${enterPrise.registermoney!}万元</#if></td>
                        <td class="td1">注册时间</td>
                        <td class="td2"><#if enterPrise.opendate=="">未填<#else>${enterPrise.opendate!}</#if></td>
                    </tr>
                    <tr>
                        <td class="td1">经营所在地</td>
                        <td class="td2"><#if enterPrise.managecityName=="">未填<#else>${enterPrise.managecityName!}</#if></td>
                        <td class="td1">所属行业</td>
                        <td class="td2"><#if enterPrise.hangyeName=="">未填<#else>${enterPrise.hangyeName!}</#if></td>
                    </tr>
                    <tr>
                        <td class="td1">营业范围</td>
                        <td class="td2"><#if planKeep.proBusinessScope=="">未填<#else>${planKeep.proBusinessScope!}</#if></td>
                        <td class="td1">主要债务</td>
                        <td class="td2"><#if planKeep.mainDebt=="">未填<#else>${planKeep.mainDebt!}</#if></td>
                    </tr>

                <#-- <tr>
                     <td class="td1">营业范围</td>
                     <td class="td2"><#if planKeep.proBusinessScope=="">未填<#else>${planKeep.proBusinessScope!}</#if></td>
                     <td class="td1">经营描述</td>
                     <td class="td2"><#if planKeep.proBusinessStatus=="">未填<#else>${planKeep.proBusinessStatus!}</#if></td>
                 </tr>
                 <tr>
                     <td class="td1">主要债务</td>
                     <td class="td2"><#if planKeep.mainDebt=="">未填<#else>${planKeep.mainDebt!}</#if></td>
                     <td class="td1">主要财务</td>
                     <td class="td2"><#if planKeep.mainFinance=="">未填<#else>${planKeep.mainFinance!}</#if></td>
                 </tr>-->
                </table>
            <#--<dl class="contentleft2">
              <dt>企业名称:</dt>
              <dd><#if enterPrise.enterprisename=="">未填<#else>${enterPrise.enterprisename?substring(0,2)}******${enterPrise.enterprisename?substring(enterPrise.enterprisename?length-2)}</#if></dd>
             </dl>
            <dl class="contentleft2">
              <dt>所有制性质:</dt>
              <dd><#if enterPrise.ownership=="">未填<#else>${enterPrise.ownership!}</#if></dd>
              </dl>-->
            <#-- <dl class="contentleft2">
            <dt>注册资金:</dt>
             <dd><#if enterPrise.registermoney=="">未填<#else>${enterPrise.registermoney!}万元</#if></dd>
            </dl>
            <dl class="contentleft2">
            <dt>注册时间:</dt>
            <dd><#if enterPrise.opendate=="">未填<#else>${enterPrise.opendate!}</#if></dd>
            </dl>
             <dl class="contentleft2">
            <dt>经营所在地:</dt>
            <dd><#if enterPrise.managecityName=="">未填<#else>${enterPrise.managecityName!}</#if></dd>
            </dl>
             <dl class="contentleft2">
            <dt>行业:</dt>
            <dd><#if enterPrise.hangyeName=="">未填<#else>${enterPrise.hangyeName!}</#if></dd>
            </dl>
            <dl class="contentleft2">
               <dt>营业范围:</dt>
            <dd><#if planKeep.proBusinessScope=="">未填<#else>${planKeep.proBusinessScope!}</#if></dd>
          </dl>
          <dl class="contentleft2">
               <dt>经营描述:</dt>
            <dd><#if planKeep.proBusinessStatus=="">未填<#else>${planKeep.proBusinessStatus!}</#if></dd>
          </dl>
          <dl class="contentleft2">
               <dt>主要财务:</dt>
            <dd style="line-height:15px;padding-top:9px;"><#if planKeep.mainFinance=="">未填<#else>${planKeep.mainFinance!}</#if></dd>
            </dl>
            <dl class="contentleft2">
            <dt>主要债务:</dt>
            <dd style="line-height:15px;padding-top:9px;"><#if planKeep.mainDebt=="">未填<#else>${planKeep.mainDebt!}</#if></dd>
            </dl>                -->
            </div>
        </div>
        <div class="content">
            <div class="contentleft">
                <div class="contentleft1">
                    <span class="bg"></span>
                    <h5 class="icon2">项目信息</h5>
                </div>


                <table class="my-table">
                    <tr>
                        <td class="td1">项目描述</td>
                        <td class="td3">${planKeep.proDes}</td>
                    </tr>
                    <tr>
                        <td class="td1">资金用途</td>
                        <td class="td3">${planKeep.proUseWay!}</td>
                    </tr>
                    <tr>
                        <td class="td1">还款来源</td>
                        <td class="td3">${planKeep.proPayMoneyWay!}</td>
                    </tr>
                </table>
            <#-- <dl class="contentleft2" style="width:84%;">
            <dt>项目描述:</dt>
         <dd style="width:100%;">${planKeep.proDes}</dd>
         </dl>
         <dl class="contentleft2"  style="width:84%;">
            <dt>资金用途:</dt>
         <dd  style="width:100%;">${planKeep.proUseWay!}</dd>
         </dl>

         <dl class="contentleft2"  style="width:84%;">
         <dt>还款来源:</dt>
         <dd  style="width:100%;">${planKeep.proPayMoneyWay!}</dd>
       </dl> -->
            </div>
        </div>
    <#--<div class="content">
        <div class="contentleft">
          <div class="contentleft1">
              <span class="bg"></span>
            <h5>担保信息</h5>
           </div>

           <dl class="contentleft2">
               <dt>担保公司名称:</dt>
            <dd>${(planKeep.guarantorsName)!}</dd>
            </dl>
            <dl class="contentleft2">
               <dt>担保机构简介:</dt>
            <dd>${planKeep.guarantorsDes}</dd>
            </dl>
            <dl class="contentleft2">
            <dt>担保说明:</dt>
            <dd>${planKeep.guarantorsAdvise!}</dd>
          </dl>
        </div>
    </div> -->
    <#--<div class="content">
        <div class="contentleft">
          <div class="contentleft1">
              <span class="bg"></span>
            <h5>风险控制</h5>
           </div>
           <dl class="contentleft2" style="width:84%;">
               <dt style="width:100%;">${planKeep.proRiskCtr!}</dt>
          </dl>
        </div>
    </div>  -->

    </ol>

<#-- <div class="contentleft">
                                <div class="contentleft1">
                                    <span class="bg"></span>
                                    <h5>经营公示</h5>
                                </div>
                                <dl class="contentleft2" style="width:84%;">
                                    <dt style="width:100%;">${planKeep.proRiskCtr!}</dt>
                                </dl>
                            </div>-->
    <ol>
        <div class="content">
       	 	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
                 <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
             <#else>
    	<!--借款材料 start -->
            	<div class="contentleft">
                    <div class="tab tab1">
                        <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                            <thead>
                            <tr>
                                <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                                <th width="150" align="center" bgcolor="#47B2D6">投标人</th>
                                <th width="50" align="center" bgcolor="#47B2D6">年利率</th>
                                <th width="150" align="center" bgcolor="#47B2D6">投标金额</th>
                                <th width="150" align="center" bgcolor="#47B2D6">投标时间</th>
                            </tr>
                            </thead>
                            <tbody  id="content">
                            <#if (listPlBid!=null)>
                           <#list listPlBid as list>
                        <tr class="data_list">
                            <td height="40" align="center" bgcolor="#FAFBFD">${list_index+1}</td>
                            <td align="center" bgcolor="#FAFBFD">${list.userName?substring(0,1)}****</td>
                            <td align="center" bgcolor="#FAFBFD">${planPro.yearInterestRate}%</td>
                            <td align="center" bgcolor="#FAFBFD">${list.userMoney?string(',###.00')}</td>
                            <td align="center"
                                bgcolor="#FAFBFD"><#if (list.bidtime==null)><#else>${list.bidtime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                        </tr>
                           </#list>
                            </#if>
                            </tbody>
                        </table>
                        <input type='hidden' id='current_page' />
                        <input type='hidden' id='show_per_page' />
                    <div id='page_navigation' class="3333333333"></div>



                    <#--<table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">-->
                    <#--<tr>                        -->
                    <#--<th width="150" height="40"align="center" bgcolor="#47B2D6">审核项目</th>-->
                    <#--<th width="100" align="center" bgcolor="#47B2D6">状态</th>-->
                    <#--<th width="100" align="center" bgcolor="#47B2D6">通过日期</th>-->
                    <#--<th width="100" align="center" bgcolor="#47B2D6">详细</th>-->
                    <#--</tr>-->
                    <#--<#list listMaterials as list>-->
                    <#--<tr>-->
                    <#--<td align="center" height="40" bgcolor="#FAFBFD">${list.materialsName}</td>-->
                    <#--<td align="center" bgcolor="#FAFBFD">通过</td>-->
                    <#--<td align="center" bgcolor="#FAFBFD"><#if list.createTime==null>---->
                    <#----<#else>${list.createTime!}</#if></td>-->
                    <#--<td align="center" bgcolor="#FAFBFD"><a>-->
                    <#--<#if list.imgUrl !="">-->
                    <#--<a rel="group" class="spec_img" href="${base}/${list.imgUrl}"-->
                    <#--title="${list.materialsName}"><i class=""></i>-->
                    <#--<img src="${base}/theme/proj_wenandai/images/chakan.png" title="查看"></a>-->
                    <#--<#else>-->
                    <#--未上传材料-->
                    <#--</#if>-->
                    <#---->
                    <#--<#list list.fileFormList as filelist>-->
                    <#--<#if filelist_index !=0>-->
                    <#--<a rel="group" class="spec_img" href="${base}/${filelist.filepath!}"-->
                    <#--title="${list.materialsName}"><i class=""></i>-->
                    <#--<img alt="${list.materialsName}" style="display:none"-->
                    <#--src="${base}/${filelist.filepath!}" width="100" height="80"/></a>-->
                    <#--</#if>-->
                    <#--</#list>-->
                    <#--</a>-->
                    <#--</td>-->
                    <#--</tr>-->
                    <#--</#list>-->
                    <#---->
                    <#--</table>-->
                    </div>


                </div>
    	<!--借款材料 end -->
             </#if>
        </div>
    </ol>

    <ol>
        <div class="content">
       	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
             <div style="text-align:center;"><span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
         <#else>
           <h2 class="title"><span>加入人次${plan.persionNum}人</span>
               投标总额${(plan.bidMoney-plan.afterMoney)?string(',##0.##')}元</h2>
            <div class="tab">
                <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                        <th width="150" align="center" bgcolor="#47B2D6">投标人</th>
                        <th width="50" align="center" bgcolor="#47B2D6">年利率</th>
                        <th width="150" align="center" bgcolor="#47B2D6">投标金额</th>
                        <th width="150" align="center" bgcolor="#47B2D6">投标时间</th>
                    </tr>
                       <#if (listPlBid!=null)>
                           <#list listPlBid as list>
                        <tr>
                            <td height="40" align="center" bgcolor="#FAFBFD">${list_index+1}</td>
                            <td align="center" bgcolor="#FAFBFD">${list.userName?substring(0,1)}****</td>
                            <td align="center" bgcolor="#FAFBFD">${planPro.yearInterestRate}%</td>
                            <td align="center" bgcolor="#FAFBFD">${list.userMoney?string(',###.00')}</td>
                            <td align="center"
                                bgcolor="#FAFBFD"><#if (list.bidtime==null)><#else>${list.bidtime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                        </tr>
                           </#list>
                       </#if>
                </table>
            </div>
         </#if>
        </div>
    </ol>

    <#--标的其他信息-->
    <#--<ol>
        <div class="content">
       	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
             <div style="text-align:center;"><span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
         <#else>
             <#assign sum1=0>
             <#assign sum2=0>
             <#list  slFundList as list>
                 <#if list.afterMoney??>
                     <#assign sum1=sum1 +list.afterMoney>
                 </#if>
                 <#if list.incomeMoney??>
                     <#assign sum2=sum2 +list.incomeMoney>
                 </#if>
             </#list>

            <h2 class="title"><span>已还本息<#if sum1??>${sum1?string(",##0.00")}<#else>--</#if> 元</span>
                待还本息<#if sum1?? && sum2??>${(sum2-sum1)?string(",##0.00")}<#else>--</#if>元</h2>
            <div class="tab">
                <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <th width="150" height="40" align="center" bgcolor="#47B2D6">约定还款日期</th>
                        <th width="150" align="center" bgcolor="#47B2D6">状态</th>
                        <th width="150" align="center" bgcolor="#47B2D6">应还本息</th>
                        <th width="150" align="center" bgcolor="#47B2D6">应还罚息</th>
                        <th width="150" align="center" bgcolor="#47B2D6">实际还款日期</th>
                    </tr>
                      <#list slFundList as fundlist>
                      <tr>
                          <td height="40" align="center" bgcolor="#FAFBFD">${fundlist.intentDate}</td>
                          <td align="center" bgcolor="#FAFBFD">
                        	<#if (fundlist.factDate==null)>
                                未偿还
                            <#else>
                        		已偿还
                            </#if>
                          </td>
                          <td align="center" bgcolor="#FAFBFD">${fundlist.incomeMoney?string(',##0.00')}</td>
                          <td align="center" bgcolor="#FAFBFD">${fundlist.accrualMoney?string(',##0.00')}</td>
                          <td align="center" bgcolor="#FAFBFD"><#if (fundlist.factDate==null)>
                              --<#else>${fundlist.factDate}</#if></td>
                      </tr>
                      </#list>
                </table>
            </div>
         </#if>
        </div>
    </ol>

    <ol id="showZX" style="    margin-top: 30px;">
        <div class="content">
            <#if Session.successHtml=='undefined'||Session.successHtml==null>
                <div style="text-align:center;"><span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
            <#else>
            &lt;#&ndash; <h2 class="title">持有债权人数<#if (bondListBid!=null)> ${bondListBid?size}<#else>0</#if>人</h2>
          <div class="tab">
              <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                      <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                      <th width="150" align="center" bgcolor="#47B2D6">债权人</th>
                      <th width="150" align="center" bgcolor="#47B2D6">持有金额</th>
                  </tr>
                    <#if (bondListBid!=null)>
                        <#list bondListBid as list>
                            <#if (list_index<8)>
                      <tr>
                          <td height="40" align="center" bgcolor="#FAFBFD">${list_index+1}</td>
                          <td align="center" bgcolor="#FAFBFD">${list.userName?substring(0,1)}****</td>
                          <td align="center" bgcolor="#FAFBFD">${list.bondTotelMoney?string(',###.00')}</td>
                      </tr>
                            </#if>
                        </#list>
                    </#if>
              </table>
          </div>&ndash;&gt;
            </#if>
        </div>
    </ol>
    <!--债权匹配信息&ndash;&gt;
    <ol id="showZP">
        <div class="content">
       	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
             <div style="text-align:center;"><span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
         <#else>
          		 <h2 class="title">债权信息</h2>
	            <div class="tab">
                    <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                            <th width="150" align="center" bgcolor="#47B2D6">转让人</th>
                            <th width="150" align="center" bgcolor="#47B2D6">受让人</th>
                            <th width="150" align="center" bgcolor="#47B2D6">本次转让金额</th>
                            <th width="150" align="center" bgcolor="#47B2D6">转让时间</th>
                        </tr>
                 		<#if (pager.list !=null)>
                            <#list pager.list as list>
	                          <tr>
                                  <td height="40" align="center">${list_index+1}</td>
                                  <td align="center">${list.outCustName?substring(0,2)}
                                      ****${list.outCustName?substring(list.outCustName?length-2)}</td>
                                  <td align="center">${list.inCustName?substring(0,2)}
                                      ****${list.inCustName?substring(list.inCustName?length-2)}</td>
                                  <td align="center">${list.sumMoney?string(',###.00')}</td>
                                  <td align="center">${list.saleSuccessTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                              </tr>
                            </#list>
                        </#if>
	                    <#import "/WEB-INF/template/common/pager.ftl" as p>
        				<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do"/>

                    </table>
                </div>
         </#if>
        </div>
    </ol>
    <!--债权匹配信息&ndash;&gt;
    <ol>
        <div class="content">
        &lt;#&ndash;  <h2 class="title"><span>以交易总额0.00元</span> 待交易总额0.00元</h2>&ndash;&gt;
            <#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;"><span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
            <#else>
            &lt;#&ndash; <h2 class="title"><span>以交易总额0.00元</span> 待交易总额0.00元</h2>&ndash;&gt;
                <#if (saleList!=null)>
            <div class="tab">
                <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                        <th width="140" height="40" align="center" bgcolor="#47B2D6">债权买入者</th>
                        <th width="140" align="center" bgcolor="#47B2D6">债权卖出者</th>
                        <th width="140" align="center" bgcolor="#47B2D6">交易金额</th>
                        <th width="140" align="center" bgcolor="#47B2D6">交易时间</th>
                    </tr>
                      <#list saleList as sale>
                          <#if (sale_index<8)>
                      <tr>
                          <td height="40" align="center" bgcolor="#FAFBFD">${sale_index+1}</td>
                          <td align="center" bgcolor="#FAFBFD">${sale.inCustName?substring(0,2)}
                              ****${sale.inCustName?substring(sale.inCustName?length-2)}</td>
                          <td align="center" bgcolor="#FAFBFD">${sale.outCustName?substring(0,2)}
                              ****${sale.outCustName?substring(sale.outCustName?length-2)}</td>
                          <td align="center" bgcolor="#FAFBFD">${sale.sumMoney?string(',###.00')}</td>
                          <td align="center"
                              bgcolor="#FAFBFD">${sale.saleSuccessTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                      </tr>
                          </#if>
                      </#list>
                </table>
            </div>
                <#else>
            <p>无债权转让记录</p>
                </#if>
            </#if>
        </div>
    </ol>

    <ol>
        <div class="content">
       	 	<#if Session.successHtml=='undefined'||Session.successHtml==null>
                <div style="text-align:center;"><span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
            <#else>
                <#if plan.state==7 || plan.state==10>
	          	<div class="contentleft">
                    <div class="contentleft1">
                        <span class="bg"></span>
                        <h5>融资资金运用情况</h5>
                    </div>
                    <div class="txt">${planPro.moneyUseSituation!}</div>
                </div>
		       	<div class="contentleft">
                    <div class="contentleft1">
                        <span class="bg"></span>
                        <h5>借款人经营状况及财务状况</h5>
                    </div>
                    <div class="txt">${planPro.managementSituation!}</div>
                </div>
		       	<div class="contentleft">
                    <div class="contentleft1">
                        <span class="bg"></span>
                        <h5>借款人还款能力变化情况</h5>
                    </div>
                    <div class="txt">${planPro.repaymentChangeSituation!}</div>
                </div>
                <#else>
		       		<p><font size="4">抱歉，尚无可查看信息</font></p>
                </#if>
            </#if>
        </div>
    </ol>-->
</div>
            </div>
        </div>
</#if>
<p style="font-size:15px;text-align:center;"><i style=" font-style: normal;margin-left:20px;color:red;vertical-align: middle;">*</i> 网贷有风险，出借需谨慎。</p>
        <!-- end main -->
        <!-- 结束 只需要拿这个说明标签之内的部分 -->
        <!-- 结束 只需要拿这个说明标签之内的部分 -->
        <!-- 结束 只需要拿这个说明标签之内的部分 -->
        <!-- 结束 只需要拿这个说明标签之内的部分 -->
        <!-- 结束 只需要拿这个说明标签之内的部分 -->
        <!-- 结束 只需要拿这个说明标签之内的部分 -->
        <!-- 结束 只需要拿这个说明标签之内的部分 -->
        <!-- 结束 只需要拿这个说明标签之内的部分 -->
        <!-- 结束 只需要拿这个说明标签之内的部分 -->
        <!-- 结束 只需要拿这个说明标签之内的部分 -->
        <!-- 结束 只需要拿这个说明标签之内的部分 -->

        <!-- copyright -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl">

<input type="hidden" id="showZQ" value="${SignZQ}">
<input type="hidden" id="bidId" value="${plan.bidId}">
<input type="hidden" id="bidName" value="${plan.bidProName}">
<input type="hidden" id="hiddenMoney" value="${plan.afterMoney}"/>
<input type="hidden" id="bidType" value="bDir">



<script>
    $(".finlist").css("z-index", "1000");
</script>


<script src="${base}/js/user/jquery.vm-carousel.js"></script>
<script type="text/javascript">
    jQuery(function($){
        $('.vmcarousel-centered').vmcarousel({
            centered: true,
            start_item: 2,
            autoplay: false,
            infinite: false
        });

        $('.vmcarousel-centered-infitine').vmcarousel({
            centered: true,
            start_item: 1,
            autoplay: false,
            infinite: true
        });

        $('.vmcarousel-normal').vmcarousel({
            centered: false,
            start_item: 0,
            autoplay: false,
            infinite: false
        });
    });
</script>
<script>
    /* $(function () {
         $(".slide_box ").click(function () {
             var _this = $(this).find("img");
             imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
         });
     });*/

    $(function () {
        $(".vmc-centered>li").click(function () {
            var _this = $(this).find("img");
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
        });
    });

    function imgShow(outerdiv, innerdiv, bigimg, _this) {
        var src = _this.attr("data_src");
        $(bigimg).attr("src", src);

        $("<img/>").attr("src", src).load(function () {
            var windowW = $(window).width();
            var windowH = $(window).height();
            var realWidth = this.width;
            var realHeight = this.height;
            var imgWidth, imgHeight;
            var scale = 0.8;

            if (realHeight > windowH * scale) {
                imgHeight = windowH * scale;
                imgWidth = imgHeight / realHeight * realWidth;
                if (imgWidth > windowW * scale) {
                    imgWidth = windowW * scale;
                }
            } else if (realWidth > windowW * scale) {
                imgWidth = windowW * scale;
                imgHeight = imgWidth / realWidth * realHeight;
            } else {
                imgWidth = realWidth;
                imgHeight = realHeight;
            }
            $(bigimg).css("width", imgWidth);

            var w = (windowW - imgWidth) / 2;
            var h = (windowH - imgHeight) / 2;
            $(innerdiv).css({"top": h, "left": w});
            $(outerdiv).fadeIn("slow");
            //淡入显示#outerdiv及.pimg
        });

        $("#fork").click(function () {//再次点击淡出消失弹出层
            $(outerdiv).fadeOut("slow");
        });
    }
</script>
<#--投标记录-->
<script type="text/javascript">
    $(document).ready(function(){
        var show_per_page = 5;
        var number_of_items = $('#content').children().size();
        var number_of_pages = Math.ceil(number_of_items/show_per_page);
        $('#current_page').val(0);
        $('#show_per_page').val(show_per_page);
        var navigation_html = '<a class="previous_link" href="javascript:previous();" >上一页</a>';
        var current_link = 0;
        while(number_of_pages > current_link){
            navigation_html += '<a class="page_link" href="javascript:go_to_page(' + current_link +')" longdesc="' + current_link +'">'+ (current_link + 1) +'</a>';
            current_link++;
        }
        navigation_html += '<a class="next_link" href="javascript:next();">下一页</a>';


        if(${plan.progress} > "0"){
            $('#page_navigation').html(navigation_html);
            $('#page_navigation .page_link:first').addClass('active_page');
            $('#content').children().css('display', 'none');
            /*  $('#content').children().slice(0, show_per_page).css('display', 'block');*/
            $('#content').children().slice(0, show_per_page).css('display', 'table-row');
        }





    });

    function previous(){
        new_page = parseInt($('#current_page').val()) - 1;
        if($('.active_page').prev('.page_link').length==true){
            go_to_page(new_page);
        }
    }

    function next(){
        new_page = parseInt($('#current_page').val()) + 1;
        //if there is an item after the current active link run the function
        if($('.active_page').next('.page_link').length==true){
            go_to_page(new_page);
        }
    }
    function go_to_page(page_num){
        var show_per_page = parseInt($('#show_per_page').val());
        start_from = page_num * show_per_page;
        end_on = start_from + show_per_page;
       /* $('#content').children().css('display', 'none').slice(start_from, end_on).css('display', 'block');*/
        $('#content').children().css('display', 'none').slice(start_from, end_on).css('display', 'table-row');
        $('.page_link[longdesc=' + page_num +']').addClass('active_page').siblings('.active_page').removeClass('active_page');
        $('#current_page').val(page_num);
    }

</script>
<script>
    $(function(){
        //剩余募集时间倒计时
        var time=parseInt($('#timestart').val())*1000;
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
    });
</script>

<script type="text/javascript">
    //注册键盘事件
    document.onkeydown = function(e) {
        //捕捉回车事件
        var ev = (typeof event!= 'undefined') ? window.event : e;
        if(ev.keyCode == 13 && document.activeElement.id == "sign_Money") {
            return false;//禁用回车事件
        }
    }
</script>
</body>
</html>
