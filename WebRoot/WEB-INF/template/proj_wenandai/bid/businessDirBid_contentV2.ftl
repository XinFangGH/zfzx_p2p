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

                <a href="${base}/js/user/chujiehetong.pdf" target="_blank">
                    <span style="font-size:14px;float:right;color:#409af6;">《出借服务协议》范本</span>
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
                    <span>${plan.theWayBack!}</span>
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
          <li>投标记录</li>

       </ul>
<#--测试专用-->
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
                        <td class="td1">企业法人</td>
                        <td class="td2"><#if enterPrise.linkman=="">未填<#else>${enterPrise.linkman!}</#if></td>
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
                    <tr>
                        <td class="td1">资金用途</td>
                        <td class="td3" colspan="3">融资款主要用于工程机械整机采购</td>
                    </tr>
                    <tr>
                        <td class="td1">还款来源</td>
                        <td class="td3" colspan="3">${planKeep.proPayMoneyWay!}</td>
                    </tr>
                </table>
            </div>
          </div>
             <div class="content">
            <div class="contentleft">
              <div class="contentleft1">
                 <span class="bg"></span>
                 <h5 class="td4">审核信息</h5>
               </div>
                <table class="my-table" style="width: 100%;">
                    <tr>
                        <td class="td1 td4">营业执照</td>
                        <td class="td2 td4 td5">已审核</td>
                        <td class="td1 td4">法人身份证</td>
                        <td class="td2 td4 td5">已审核</td>
                    </tr>
                    <tr>
                        <td class="td1 td4">借款用途</td>
                        <td class="td2 td4 td5">已审核</td>
                        <td class="td1 td4">还款来源</td>
                        <td class="td2 td4 td5">已审核</td>
                    </tr>
                    <tr>
                        <td class="td1 td4">信用记录</td>
                        <td class="td2 td4 td5">已审核</td>
                        <td class="td1 td4">银行账户</td>
                        <td class="td2 td4 td5">已审核</td>
                    </tr>
                </table>
            </div>
            <#--  <div style="line-height: 30px;font-size: 14px;margin-top: 25px;padding-left:15px">担保措施 : 烟台山腾建筑工程机械有限公司承诺逾期债权回购；设备残余价值保障</div>-->
          </div>
             <div class="content">
                 <div class="contentleft">
                     <div class="contentleft1">
                         <span class="bg"></span>
                         <h5 class="td6">担保措施</h5>
                     </div>
                     <table class="my-table">
                         <tr>
                             <td class="td1">担保措施</td>
                             <td class="td3" colspan="3">担保措施 : 烟台山腾建筑工程机械有限公司承诺逾期债权回购；设备残余价值保障</td>
                         </tr>
                     </table>
                 </div>
             </div>


            <#--经营公示图片-->
            <#-- <p style="font-size: 18px;color:#333;text-align: left;margin-top: 30px">
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

                     </ul>
                 </#if>
                 </div>
                 <div id="outerdiv"  style="position:fixed;top:0;left:0;background:rgba(100, 100, 100, 0.5);z-index:35;width:100%;height:100%;display:none;">
                     <div id="innerdiv" style="position:absolute;">
                         <i id="fork"></i>
                         <img id="bigimg" style="border:5px solid #fff;" src="" alt="">
                     </div>
                 </div>
             </div>-->
             
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
            </div>
            </div>
    	<!--借款材料 end -->
             </#if>
                        </div>
                    </ol>
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

                </table>
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
            </div>
        </div>

    </ol>

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
            $(outerdiv).fadeIn("slow");//淡入显示#outerdiv及.pimg
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
