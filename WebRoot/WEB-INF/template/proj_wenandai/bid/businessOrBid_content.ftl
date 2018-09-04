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
<link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" /> 
<script type="text/javascript" src="${base}/js/user/fancybox.js"></script>

<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
<style type="text/css">
	body,h1,ul{margin:0;}
	ul li{list-style-type:none;}
	#header{width:100%;border-top:solid 1px #ccc;border-bottom:solid 1px #ccc;text-align:center;}
	.nav_scroll{position:fixed;width:100%;margin-left:98px;left:0;top:0;background-color:white;}
</style>

<script type="text/javascript">
$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200 });		
	});
	window.onload=function() {
	$(".finlist .finlist_title li").each(function(index) { //带参数遍历各个选项卡
		$(this).click(function() { //注册每个选卡的单击事件
			$(".finlist .finlist_title li.active").removeClass("active"); //移除已选中的样式
			$(this).addClass("active"); //增加当前选中项的样式
			//显示选项卡对应的内容并隐藏未被选中的内容
			$(".finlist .finlist_hide ol:eq(" + index + ")").show()
			.siblings().hide();
		});
	});
	
	$(function(){
		check('${plan.bidId}','${plan.afterMoney}','${planPro.yearInterestRate}',false);
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
<script type="text/javascript">	
$(function(){
		//图标下拉框  提示框
	var content = $(".sidnext").html();	
	$('.icon-one').pt({
		position: 'b',
		width:300,
		content: content
	});	 
	var content = $(".sidnext1").html();	
	$('.icon-two').pt({
		position: 'b',
		width:300,
		content: content
	});	 
	var content = $(".sidnext2").html();	
	$('.icon-three').pt({
		position: 'b',
		width:300,
		content: content
	});	  
	
});
</script>
<script type="text/javascript">
	$(document).ready(function(){
	 var topMain=$("#header").height()+500//是头部的高度加头部与nav导航之间的距离
	 var nav=$(".finlist_title");
	 $(window).scroll(function(){
	  if ($(window).scrollTop()>topMain){//如果滚动条顶部的距离大于topMain则就nav导航就添加类.nav_scroll，否则就移除
	//   	Array.prototype.slice.call($(".finlist_title li")).forEach(function(item){
	//		item.className = "active";
	//	});
	   nav.addClass("nav_scroll");
	  }else{
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
<!--企业债权标-->
<input value="${(bpCustMember.grade)??}" type="hidden" id="grade">
<input value="${(bpCustMember.id)??}" type="hidden" id="memberId">
<input value="${plan.maxCouponMoney}" type="hidden" id="planMaxCouponMoney">
<div style="background:#eff3f3;width:100%;overflow:hidden;">
<div class="main">
	<!--<div class="porduct">
    	<a href="#">产品详情</a>
    </div>-->
    <div class="inner">
    	<h2 class="title"><span class="pic"></span><span class="txt">${(plan.bidProName)![0..10]}</span>&nbsp;&nbsp;<#if plan.novice==1><font class="one">新</font></#if>&nbsp;&nbsp;<a href='${base}/creditFlow/financingAgency/planContentPlBidPlan.do'>协议预览</a></h2>
    	<div class="inner-left">       	
            <div class="condition">
            	<dl class="didt">
	      			<dt>预期年利率</dt>
	      			<dd><i>${(planPro.yearInterestRate)!}</i>%  <#if plan.addRate!=0&&plan.addRate!=""><font color="#EA0000" style="font-size:20px;">+${plan.addRate}%</font>&nbsp;<font class="font-two">息</font></#if></dd>
                </dl>
                <dl class="didt">
                	<dt>总额</dt>
                	<dd><#if (plan.bidMoney>=10000)><i>${(plan.bidMoney/10000)?string(",##0.00#")}</i>万元<#else><i>${plan.bidMoney?string(',##0')}</i>元</#if></p></dd> 
                    
                </dl>
                <dl class="didt didt-none">
                	<dt>剩余期限</dt>
                	<dd><i>${plan.acctulData!}</i>天</dd>
                   
                </dl>
            </div>
            <ul>
            
            	<li><em><img src="${base}/theme/proj_wenandai/images/ty-icon1.png"  alt="" /></em>原始债权金额：<span>1000</span>
                <a href="${base}/html/secureSingle.do?toWhere=projectDesc#projectDesc"><!--<span style="display:inline-block;" >[?]</span>--></a>
                </li>
                 <li><em><img src="${base}/theme/proj_wenandai/images/ty-icon2.png"  alt="" /></em>年化收益率：<span>12%</span>
                <a href="${base}/html/secureSingle.do?toWhere=projectDesc#projectDesc"><!--<span style="display:inline-block;" >[?]</span>--></a>
                </li>
                 <li><em><img src="${base}/theme/proj_wenandai/images/ty-icon3.png"  alt="" /></em>债权到期日：<span>2016-08-24</span>
                <a href="${base}/html/secureSingle.do?toWhere=projectDesc#projectDesc"><!--<span style="display:inline-block;" >[?]</span>--></a>
                </li>
            <#if plan.coupon==1>
        	<li>
	               <font class="font-three">券</font>
	               返现比例：<b class="red" style="font-weight:normal;">${plan.returnRatio}%</b>
        	<span style="display:inline-block;" class="icon-two">[?]</span>
        	<div class="sidnext1" style="display:none;">
        	<p align="left"><b>有效面值：</b>面值*折现比例</p>
        	<p align="left"><b>返利方式：</b>
        	<#if plan.rebateWay==1>
        	立返(放款时一次性返利)，
        	</p>	
        		<p align="left">
        		<b>返利类型：</b>
            	<#if plan.rebateType==1>
            		返现。立返=面值*折现比例</#if>
            	<#if plan.rebateType==2>
            	返息。立返=以面值*折现比例 按标的计息方式生成收息表(没有本金)进行加合利息</#if>
            	<#if plan.rebateType==3>
            	返息现。立返=以面值*折现比例 按标的计息方式生成收息表(有本金)进行加合本息</#if>
            	<#if plan.rebateType==4>
            	加息。利率为加息利率,投资金额按照利息计算方式（只生成利息）
            	</#if>
            	</p>
        	</#if>
        	<#if plan.rebateWay==2>
        	随期(随期进行返利)
        	</p><p align="left">	
        	<b>返利类型：</b>
            	<#if plan.rebateType==1>
            		返现。随期=当期本金*【面值*折现比例/实际投资额】</#if>
            	<#if plan.rebateType==2>
            	返息。随期=以面值*折现比例按标的计息方式生成收息表(没有本金)</#if>
            	<#if plan.rebateType==3>
            	返息现。随期=以面值*折现比例按标的计息方式生成收息表(有本金) 随息发放本息</#if>
            	<#if plan.rebateType==4>
            	加息。利率为加息利率,投资金额按照利息计算方式（只生成利息）
            	</#if>
            	</p>
        	</#if>
        	<#if plan.rebateWay==3>
        	到期(贷款结束时返利)，
        	</p>	
        		<p align="left">
        		<b>返利类型：</b>
            	<#if plan.rebateType==1>
            		返现。立返=面值*折现比例</#if>
            	<#if plan.rebateType==2>
            	返息。立返=以面值*折现比例 按标的计息方式生成收息表(没有本金)进行加合利息</#if>
            	<#if plan.rebateType==3>
            	返息现。立返=以面值*折现比例 按标的计息方式生成收息表(有本金)进行加合本息</#if>
            	<#if plan.rebateType==4>
            	加息。利率为加息利率,投资金额按照利息计算方式（只生成利息）进行加合利息
            	</#if>
            	</p>
        	</#if>
        	</div>
        	</li>
        </#if>
            	<!--<li><em class="icon-one"><img src="${base}/theme/proj_wenandai/images/ty-icon5.png"  alt=""/></em>还款方式：<span>${plan.theWayBack!}</span>
            	<a href="javascript:void(0)"><span style="display:inline-block;" class="icon-one">[?]</span></a>
                 <#if plan.payIntersetWay=='1'><div class="sidnext" style="display:none;">等额本息还款法是在还款期内，每月偿还同等数额的贷款(包括本金和利息)。借款人每月还款额中的本金比重逐月递增、利息比重逐月递减。</div>
                 </#if>
                 <#if plan.payIntersetWay=='2'>
                 <div class="sidnext" style="display:none;">等额本金是在还款期内把贷款数总额等分，每月偿还同等数额的本金和剩余贷款在该月所产生的利息，每月的还款本金额固定，而利息越来越少</div>
                 </#if>
                 
                 <#if plan.payIntersetWay=='3'>
                 <div class="sidnext" style="display:none;">等本等息还款法是在还款期内，每个月平均偿还本金和利息，利息一直按总本金计算</div>
                 </#if>
                  <#if plan.payIntersetWay=='4'>
                  <div class="sidnext" style="display:none;">先息后本还款法是在还款期内，每月只偿还利息，在最后一期偿还利息的同时偿还全部本金。</div>
                </#if>
                </li>-->
                 <!--<li><em><img src="${base}/theme/proj_wenandai/images/ty-icon4.png" />保障方式：<span>本金保障</span>-->
                <a href="${base}/html/secureSingle.do?toWhere=projectDesc#projectDesc"><!--<span style="display:inline-block;" >[?]</span>--></a>
                 <#--${(planKeep.plKeepGtorz.name)!}--></li>
                <#--<li class="none">月还款额（元）：${planPro.monthInterestRate?string(',###.00')}</li>-->
               <#if plan.raiseRate gt 0>
               <li>
               <em><img src="${base}/theme/proj_wenandai/images/baoz_icon.jpg" /></em>
               		募集期利率：${plan.raiseRate}%/年<span style="display:inline-block;" class="icon-three">[?]</span>
                <div class="sidnext2" style="display:none;">
                    <p align="left"><b>募集天数：</b>起息日-投资日（投资当天起息不计算）</p>
                    <p align="left"><b>计算公式：</b>投资金额*日化募集期利率*募集天数</p>
                </div>
               </li>
               </#if>
              </ul>
        </div>
        <!-- start 已满标-->
        <#if plan.state==2 || plan.state==5|| plan.state==6>
         <div class="inner-right1">
         	<i class="bg"></i>
        	<h3>已满标</h3>
            <div class="sumb">
            	   <ul>
                    	<li>满标用时：</li>
                    	<li style="font-size:20px;">${plan.bidFullTime}</li>
                    	 
                        <li>加入人次：<span class="bigger">${plan.persionNum}人</span></li>                       
                    	
                    </ul>
               
            </div>
        </div>
        <!--end 已满标-->
        <!-- start还款中-->
        
        <#elseif plan.state==7> 
        <div class="inner-right1" >
        	<i class="bg"></i>
        	<h3>还款中</h3>
            <div class="sumb">
            		
                    <ul>
                    	 <li>待还本息：<span class="big">${plan.notRepaymentMoney}元</span></li> 
                    	 <li>还款进度：<span class="big">${plan.repayMentLength}</span></li> 
                    	
                        <li>下次还款日：<span class="big">${plan.nextRepaymentDate}</span></li>                       
                    	
                    </ul>
                
            </div>
        </div>
        <!-- end还款中-->
        <!-- start已还清-->  
        <#elseif plan.state==10>  
        <div class="inner-right1" >
        	<i class="bg"></i>
        	<h3>已还清</h3>
            <div class="sumb">
            		
                    <ul>
                    	<li>还清日期：${plan.repaymentFullDate}</li>
                    	
                        <li>加入人次：<span class="bigger">${plan.persionNum}人</span></li>                      </ul>
                
            </div>
        </div>
        <!-- end 已还清 -->
        <!-- start 已过期 -->
        <#elseif plan.state==3>
		 <div class="inner-right1" >
		 	<i class="bg"></i>
        	<h3>已流标</h3>
            <div class="sumb">
            	<ul>
                    	<p style="padding:20px 0;font-size:16px;text-align:center;">已流标！</p>
                  </ul>
            </div>
        </div>
         <#elseif plan.state==4>
		 <div class="inner-right1" >
		 	<i class="bg"></i>
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
        	<i class="bg"></i>
        	<h3>投标中</h3>
            <div class="sumb">
            	<form id="bid_Form" action="${base}/creditFlow/financingAgency/bidingPlBidInfo.do" method="post">
            		<div style="width:230px; overflow:hidden; position: relative;">
					<input name="plBidInfo.bidId" type="hidden" id="plBidInfobidId">
					<input name="plBidInfo.bidName" type="hidden" id="plBidInfobidName">
					<input name="plBidInfo.userMoney" type="hidden" id="plBidInfouserMoney">
					<input name="plBidInfo.userName" type="hidden" id="plBidInfouserName">
					<input name="plBidInfo.userId" type="hidden" id="plBidInfouserId">
					<input name="formtoken" type="hidden" id="formtoken" value="${formtoken}">
					<input  type="hidden" id="upperMoney" value="${plan.maxMoney}">
					<input name="couponId" type="hidden" id="couponId" value="">
                	<input type="text" style="width:249px;" id="sign_Money" onblur="chkMoney('sign_Money',${plan.afterMoney},'currM');" class="good_input" placeholder="输入金额需为${plan.startMoney}元的整数倍"/>

                	</div>
                    <ul>

                        <li style="height:14px; line-height:10px;"><span id="moneyspan"></span></li>
                    	<li>剩余金额：<input type="hidden" id="afterM" value="${plan.afterMoney}"/><span >${plan.afterMoney?string(',##0.00')}元</span></li>

                    <#if Session.successHtml=='undefined'||Session.successHtml==null> 
                        <li>账户余额：<span style="color:#fd7754;text-decoration: underline; ">登录可见</span></li>
                    <#else>
                        <li>账户余额：<span style="color:#fd7754;" tid="currM">${bpCustMember.availableInvestMoney?string(',##0.00')}</span></li>                       
                    </#if>
                    </ul>
                    <#if Session.successHtml=='undefined'||Session.successHtml==null>
                  		<a href="${base}/htmllogin.do?retUrl=" target="_self" id="bidLogin"> <input  type="button"   value="&nbsp;&nbsp;&nbsp;立即登录 &nbsp;&nbsp;" style="height:30px;"></input></a>
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
						    					<#if bpCustMember.grade??>
								    				<a id="to" rel="leanModal" style="padding:0; border:0;"name="signup" onClick="check('${bidId}','${afterMoney}','${yearInterestRate}',true)" href="#signup">
							    						<input  type="button"  value="&nbsp;&nbsp;确定投资&nbsp;&nbsp;" >
							    					</a>
						    					<#else>
							    					<a id="to" style="padding:0; border:0;"name="signup" onClick="check('${bidId}','${afterMoney}','${yearInterestRate}',true)" href="#signup">
							    						<input  type="button"  value="&nbsp;&nbsp;确定投资&nbsp;&nbsp;" >
							    					</a>
							    				</#if>
						    				<#elseif plan.state==2 ||plan.state==5||plan.state==6>
						    					<input  type="button" value="&nbsp;&nbsp;已满标&nbsp;&nbsp;">
						    				<#elseif plan.state==3>
						    					<input  type="button" value="&nbsp;&nbsp;已流标&nbsp;&nbsp;">
						    				<#elseif plan.state==4>
						    					<input  type="button" value="&nbsp;&nbsp;已过期&nbsp;&nbsp;">
						    			
						    				<#elseif plan.state==7>
						    					<input  type="button" value="&nbsp;&nbsp;还款中&nbsp;&nbsp;">
						    						<#elseif plan.state==10>
						    					<input  type="button" value="&nbsp;&nbsp;已完成&nbsp;&nbsp;">
						    				<#else>
						    					
						    				</#if>
						    			</#if>
						    		</#if>
						   		<#else>
						   		<input  type="button" value="&nbsp;&nbsp;还款中&nbsp;&nbsp;">
						    </#if>
					    </#if>
					    	 	<input type="hidden" value="${plan.maxMoney}" id="maxMoney" />
					    	 	<a href="javascript:void(0)" onClick="bidAll(${plan.afterMoney},${plan.startMoney},${plan.riseMoney})" >全投</a>
					    	 	<a href="${base}/financePurchase/rechargeFinancePurchase.do">充值</a>
                             		</div>
                   </#if>
                   
                </form>
            </div>
        </div>
        <#else>
        	<div class="inner-right1" >
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
    <div class="finlist">
       <ul class="finlist_title">
          <li class="active">标的详情</li> 
          <span class="sept">|</span>
          <li>相关材料</li> 
          <span class="sept">|</span>        
          <li>投标记录</li>
          <span class="sept">|</span>
          <li>还款表现</li>
          <span class="sept">|</span>
          <li>债权信息</li>
          <span class="sept">|</span>
          <li>转让记录</li>
          <span class="sept">|</span>
          <li>贷后信息</li>
       </ul>
       <div class="finlist_hide">
         <ol style="display:block">
          <div class="content">    
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                <h5>企业基本信息</h5>
               </div> 
             
               <dl class="contentleft2">
                <dt>企业名称:</dt>
                  <dd><#if enterPrise.enterprisename=="">未填<#else>${enterPrise.enterprisename?substring(0,2)}******${enterPrise.enterprisename?substring(enterPrise.enterprisename?length-2)}</#if></dd>
               <#--<dd><#if enterPrise.enterprisename=="">未填<#else>${enterPrise.enterprisename!}</#if></dd>-->
               </dl>
               
                <dl class="contentleft2">
                <dt>所有制性质:</dt>
                <dd><#if enterPrise.ownership=="">未填<#else>${enterPrise.ownership!}</#if></dd>
                </dl>
               
                 <dl class="contentleft2">
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
              	</dl>               
            </div>  
        </div> 
        <div class="content">    
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                <h5>项目信息</h5>
               </div> 
              
               <dl class="contentleft2">
               	<dt>项目说明:</dt>
                <dd>${planKeep.proDes}</dd>
                </dl>
                <dl class="contentleft2">
               	<dt>资金用途:</dt>
                <dd>${planKeep.proUseWay!}</dd>
                </dl>
                <dl class="contentleft2">
                <dt>还款来源:</dt>
                <dd>${planKeep.proPayMoneyWay}</dd>
              </dl> 
            </div>  
        </div>
        <div class="content">    
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                <h5>担保信息</h5>
               </div> 
              
               <dl class="contentleft2">
               	<dt>担保公司名称:</dt>
                <dd>${planKeep.guarantorsName}</dd>
                </dl>
                <dl class="contentleft2">
               	<dt>担保机构简介:</dt>
                <dd>${planKeep.guarantorsDes}</dd>
                </dl>
                <dl class="contentleft2">
                <dt>担保意见:</dt>
                <dd>${planKeep.guarantorsAdvise}</dd>
              </dl>               
            </div>  
        </div> 
        <div class="content">    
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                <h5>风险控制</h5>
               </div> 
              
               <dl class="contentleft2">
               	<dt>${planKeep.proRiskCtr!}</dt>
   
               
              </dl>               
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
                       <tr>                        
                        <th width="150" height="40"align="center" bgcolor="#47B2D6">审核项目</th>
                        <th width="100" align="center" bgcolor="#47B2D6">状态</th>
                        <th width="100" align="center" bgcolor="#47B2D6">通过日期</th>
                        <th width="100" align="center" bgcolor="#47B2D6">详细</th>
                      </tr>
                      <#list listMaterials as list>
	                      <tr>
	                        <td align="center" height="40" bgcolor="#FAFBFD">${list.materialsName}</td>
	                        <td align="center" bgcolor="#FAFBFD">通过</td>
	                        <td align="center" bgcolor="#FAFBFD"><#if list.createTime==null>-- --<#else>${list.createTime!}</#if></td>
	                        <td align="center" bgcolor="#FAFBFD"><a>
		                        <#if list.imgUrl !="">
			                	<a rel="group" class="spec_img" href="${base}/${list.imgUrl}" title="${list.materialsName}"><i class=""></i>
			                	<img src="${base}/theme/proj_wenandai/images/chakan.png" title="查看"></a>
			                	<#else>
			                	未上传材料
			                	</#if>
						        
		                    	<#list list.fileFormList as filelist>
		                    	<#if filelist_index !=0>
			                	<a rel="group" class="spec_img" href="${base}/${filelist.filepath!}" title="${list.materialsName}"><i class=""></i>
						        <img alt="${list.materialsName}" style="display:none" src="${base}/${filelist.filepath!}" width="100" height="80"/></a>
			                	</#if>
			                	</#list>	
	                        	</a>
	                        </td>
	                      </tr>
                      </#list>
                      
                </table>
            </div>                
            </div>  
    	<!--借款材料 end -->
    	    </#if>
         </div> 
       </ol>
       	
      <ol>
       	 <div class="content">    
       	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
             <#else> 
          <h2 class="title"><span>加入人次${plan.persionNum}人</span> 投标总额${(plan.bidMoney-plan.afterMoney)?string(',##0.##')}元</h2>
            <div class="tab">
            	<table width="100%" class="tab_css_one" border="0"  align="center" cellpadding="0" cellspacing="0">
                       <tr>
                        <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                        <th width="150" align="center" bgcolor="#47B2D6">投标人</th>
                        <th width="50"  align="center" bgcolor="#47B2D6">年利率</th>
                        <th width="150" align="center" bgcolor="#47B2D6">投标金额</th>
                        <th width="150" align="center" bgcolor="#47B2D6">投标时间</th>
                      </tr>
                       <#if (listPlBid!=null)>
                        <#list listPlBid as list>
                        <#if (list_index<8)>
                        <tr>
                           <td height="40" align="center" bgcolor="#FAFBFD">${list_index+1}</td>
                           <td align="center" bgcolor="#FAFBFD">${list.userName?substring(0,1)}****</td>
                           <td align="center" bgcolor="#FAFBFD">${(planPro.yearInterestRate)!}%</td>
                           <td align="center" bgcolor="#FAFBFD">${list.userMoney?string(',###.00')}</td>
                           <td align="center" bgcolor="#FAFBFD"><#if (list.bidtime==null)><#else>${list.bidtime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                        </tr>
                        </#if>
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
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
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
         
            <h2 class="title"><span>已还本息<#if sum1??>${sum1?string(",##0.00")}<#else>--</#if> 元</span> 待还本息<#if sum1?? && sum2??>${(sum2-sum1)?string(",##0.00")}<#else>--</#if>元</h2>
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
                         <td align="center" bgcolor="#FAFBFD">${fundlist.incomeMoney?string(',##0.00')}<#if fundlist.fundType=='principalRepayment'> (本)<#else>  (息)</#if></td>
                        <td align="center" bgcolor="#FAFBFD">${fundlist.accrualMoney?string(',##0.00')}</td>
                        <td align="center" bgcolor="#FAFBFD"><#if (fundlist.factDate==null)>--<#else>${fundlist.factDate}</#if></td>
                      </tr>
                      </#list>
                </table>
            </div>
            </#if>  
         </div> 
       </ol>
       
      <ol>
       	 <div class="content">    
       	  <#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
             <#else>     
          		 <h2 class="title">持有债权人数<#if (bondListBid!=null)> ${bondListBid?size}<#else>0</#if>人</h2>
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
	            </div>  
             </#if>
         </div> 
       </ol>
       <#--
       <ol>
       	 <div class="content">    
            <h2 class="title">持有债权人数313人</h2>
            <div class="tab">
            	<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
                       <tr>
                        <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                        <th width="150" align="center" bgcolor="#47B2D6">债权人</th>
                        <th width="150" align="center" bgcolor="#47B2D6">持有金额</th>
                      </tr>
                      <tr>
                        <td height="40" align="center" bgcolor="#FAFBFD">1</td>
                        <td align="center" bgcolor="#FAFBFD">投标人</td>
                        <td align="center" bgcolor="#FAFBFD">200.00元</td>
                      </tr>
                      <tr>
                        <td height="40" align="center" bgcolor="#E1E4E9">2</td>
                        <td align="center" bgcolor="#E1E4E9">投标人投标人</td>
                        <td align="center" bgcolor="#E1E4E9">500.00元</td>
                      </tr>
                </table>
            </div>  
            <p>无债权转让记录</p>
         </div> 
       </ol>-->
       
       <ol>
       	 <div class="content">    
            <#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
             <#else>  
           <#-- <h2 class="title"><span>以交易总额0.00元</span> 待交易总额0.00元</h2>-->
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
                        <td align="center" bgcolor="#FAFBFD">${sale.inCustName?substring(0,2)}****${sale.inCustName?substring(sale.inCustName?length-2)}</td>
                        <td align="center" bgcolor="#FAFBFD">${sale.outCustName?substring(0,2)}****${sale.outCustName?substring(sale.outCustName?length-2)}</td>
                        <td align="center" bgcolor="#FAFBFD">${sale.sumMoney?string(',###.00')}</td>
                        <td align="center" bgcolor="#FAFBFD">${sale.saleSuccessTime?string('yyyy-MM-dd HH:mm:ss')}</td>
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
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
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
       </ol>
       
	</div>
</div>
</div>
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

 <input type="hidden" id="bidId" value="${plan.bidId}">
 <input type="hidden" id="bidName" value="${plan.bidProName}">
 <input type="hidden" id="hiddenMoney" value="${plan.afterMoney}"/>
 <input type="hidden" id="bidType" value="bOr">
</body>
</html>
