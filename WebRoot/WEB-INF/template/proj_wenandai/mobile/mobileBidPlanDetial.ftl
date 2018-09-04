<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <meta  http-equiv="x-rim-auto-match" content="none">
    <title>升升投 - 投标信息</title>
       <link rel="stylesheet"  media="all" type="text/css" href="${base}/theme/${systemConfig.theme}/css/mobile.css" />
    <meta name="description" content="${systemConfig.metaTitle} - 投标信息,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 投标信息,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

		<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
		<script type="text/javascript" src="${base}/js/user/check.js"></script>
		<script type="text/javascript" src="${base}/js/user/login.js"></script>
		<script type="text/javascript" src="${base}/js/sign.js"></script>
		<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
	<script type="text/javascript">
	window.onload=function() {
	$(function(){
	
		check('${(plan.bidId)!}','${(plan.afterMoney)!}','${(planPro.yearInterestRate)!}');
		$("#to").click(function(){
			var myMoney = $("#myMoney").text();
			var afterMoney = $("#afterMoney").text();
			var subMyMoney = myMoney.substring(0,myMoney.length-1);
			var subAfterMoney = afterMoney.substring(0,afterMoney.length-1);
			var strAfterMoney = "";
			var strMyMoney ="";
			for(var i=0;i<subAfterMoney.length;i++){
				if(","!=subAfterMoney[i]){
					strAfterMoney+= subAfterMoney[i];
				}
			}
			for(var i=0;i<subMyMoney.length;i++){
				if(","!=subMyMoney[i]){
					strMyMoney+= subMyMoney[i];
				}
			}
			
			if(strMyMoney > strAfterMoney){
				$("#signMoney").val(strAfterMoney);
			}else if(strMyMoney <= strAfterMoney){
				$("#signMoney").val(strMyMoney);
			}else{
				$("#signMoney").val(0);
			}
			
		});
	});
}

</script>
</head>
<body>
    <div id="wrap">
        <!-- 顶部   -->
        <div class="wad_header">
            <div class="wad_topbar">
                <a href="#" class="wad_bak"></a>
                <em>投标信息</em>
                <span class="wad_allnav"></span>
            </div>
            <#include "/WEB-INF/template/${systemConfig.theme}/mobilelayout/navbar.ftl">
        </div>
        <!-- 内容 -->
        <!-- 标题 -->
        <div class="wad_tbxgheader">
            <img src="${base}/theme/${systemConfig.theme}/images/mobile/xin.png">
            <span>${(plan.bidProName)!}</span>
        </div>
        <div class="tawrap">
            <!-- 投标详情 -->
            <div class="wad_tb">
                <p class="wad_tblilv"><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?mobile=userInfo&bidId=${(plan.bidId)!}" class="wad_yonghuxinxi"><img src="${base}/theme/${systemConfig.theme}/images/mobile/icon-tips.png">查看用户信息情况</a><i>${(planPro.yearInterestRate)!}</i><i class="wad_jines"><em class="wad_baifenbi">%</em><em>年化利率</em></i></p>
                <div class="wad_fcon">
                    <p class="wad_xxmes">
                        <span class="wad_jine"><i>${(plan.bidMoney?string(',###.00'))!}</i><i class="wad_jines">融资金额(元)</i></span>
                        <span class="wad_qixian"><i>${(planPro.loanLife)!}</i><i class="wad_jines">借款期限(月)</i></span>
                    </p>
                    <p class="wad_graph"><span class="wad_yanse">${((plan.bidMoney-plan.afterMoney)/plan.bidMoney*100)?string(',##0.##')}%</span></p>
                    <p class="wad_jiekr"  style="height:auto;line-height:24px;margin-top:10px;"><span style="display:block;font-size:16px;">✓&nbsp;${(plan.persionNum)!}出借人已投${(plan.bidMoney-plan.afterMoney)?string(',##0.##')}元</span><span style="display:block;font-size:16px;">✓&nbsp;剩余可投金额：${(plan.afterMoney)?string(',##0.##')}元</span></p>
                </div>
            </div>
            
            <div class="wad_goumai">
            <#if plan.state=='1'>
                <form id="bid_Form" action="${base}/creditFlow/financingAgency/bidingPlBidInfo.do" method="post">
                <input name="plBidInfo.bidId" type="hidden" id="plBidInfobidId">
					<input name="plBidInfo.bidName" type="hidden" id="plBidInfobidName">
					<input name="plBidInfo.userMoney" type="hidden" id="plBidInfouserMoney">
					<input name="plBidInfo.userName" type="hidden" id="plBidInfouserName">
					<input name="plBidInfo.userId" type="hidden" id="plBidInfouserId">
					<input name="formtoken" type="hidden" id="formtoken" value="${formtoken}">
					<input  type="hidden" id="upperMoney" value="${plan.maxMoney}">
					<input  type="hidden" id="mobile" value="mobile">
                    <input type="text" name="points" id="signMoney"  placeholder="请输入投标金额为${plan.riseMoney}元的整数倍" onblur="chkMoney('signMoney',${plan.afterMoney},'currM');">
                    <label class="wad_qian">元</label>
                    <#if Session.successHtml=='undefined'||Session.successHtml==null> <label class="wad_wdl">账户余额：登录可见</label><#else>
                    <label class="wad_syqian">您的账户余额为：<input type="hidden" id="currM"/><span id="myMoney"></span>&nbsp;&nbsp;<span id="money_span"></span>
                    <input type="hidden" id="afterM" value="${plan.afterMoney}"/>
                    </label>
                    </#if>
                    <label class="tb_button"><input type="button" value="确定投资" onclick="bidHandler()"></label>
                   <#--<a href="bidHandler()"> <input class="button"   value="&nbsp;&nbsp;确定投资&nbsp;&nbsp;" ></a>-->
                    <p class="wad_tbzhifutishi">温馨提示：提交订单后，页面将调转到易宝平台进行支付且成功扣款后，该投标订单才能生效。</p>
                </form>
               <#else>
               <h3>已满标</h3>
            	<#--<div>
                    <ul>
                    	<li>满标用时：</li>
                    	<li style="font-size:20px;">${plan.bidFullTime}</li>
                        <li>加入人次：<span class="bigger">${plan.persionNum}人</span></li>                       
                    </ul>
               
            	</div>-->
              </#if>
            </div>
            <p class="wad_tbxuzhi"><a href="#"><img src="${base}/theme/${systemConfig.theme}/images/mobile/icon-tips.png">用户须知</a><span><em>✓&nbsp;投标最低金额为50元</em><em>✓&nbsp;单笔投标最高金额为：标的总金额的20%</em><em>✓&nbsp;平台为投资人的本金提供全额保障，本金0风险</em><em>✓&nbsp;涉及资金支付流程均在易宝平台操作，扣款成功后，该投标订单立即生效</em></span></p>
        </div>
        <!-- star footer  -->
          <#include "/WEB-INF/template/${systemConfig.theme}/mobilelayout/footerbar.ftl">
        <!-- end footer  -->
    </div>
    <script src='${base}/js/mobile/zepto.min.js'></script>
    <script type="text/javascript">
        $(".wad_allnav").click(function(){
            $(".wad_slidernav").toggle();
        });
        $(function(){$(".wad_yanse").width($(".wad_yanse").text());});
    </script>
</body>
</html>