<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 理财计划详情</title>

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>

<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
<script type="text/javascript">
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200 });		
	});
	$(document).ready(
	function() {
		<#list listMoneyPlan as list1 >
			$("#pb${list1.mmplanId}").progressBar();
		</#list>
		}
	);
	
	function drawCircle(){  
		var canvas = document.getElementById("circle");  
		var ctx = canvas.getContext("2d");  
		ctx.fillStyle = '#F39801';  
		ctx.beginPath();  
		ctx.moveTo(64,53);  
		ctx.arc(64,53,46,Math.PI*1.5,Math.PI*2*1,false);  
		ctx.fill();  
	} 
		$(function() {
	// 合作伙伴图标水平循环滚动
	scrollPartner();
}); 

</script>
</head>
<body>
<div> 
  <!-- topbar -->
  <!-- header --><!-- navbar -->
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<!-- main --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 

 <!-- start：总投资列表页 -->
 <div class="main">
<div class="bidinfo"> 
	 <div class="bidplan1">
	    <div class="baiyibao">
			<div class="titlediv">    			
	    		<div class="title">固定期限类</div>
			</div>
		</div>
		<div class="financeplan">
	    	<div class="planshow">
	    	<br/> 
	    		<p class="regular gray" style="padding-right:20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"固定期限出借方式"是百翼为你推出具有较高资金流动式的理财模式，根据资金封闭期不同，分为3个月、6个月、9个月、12个月四档模式，理财客户在资金封闭期内，可以按月获取投资收益，封闭期结束后，本机返还至理财客户账户，预期年化收益率9%-12%。</p>            
	              <#list listMoneyPlan as moneyPlan>
	                  <#if (moneyPlan_index + 1) % 2 == 1 >
	                      <div class="blockdiv bg1">
	                  <#else>
	                      <div class="blockdiv bg2">
	                  </#if>
	                  <div class="title">${moneyPlan.manageMoneyTypeName}</div>
	                  <div class="content">
	                      <span class="small gray">${moneyPlan.persionNum}11111人次已加入</span><br />
	                      <span class="large bold black" style="font-size:32px;">${moneyPlan.yeaRate}</span><span class="large bold black">%</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="large">${moneyPlan.investlimit}个月</span><br />
	                      <span>年化收益率&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期限&nbsp;&nbsp;&nbsp;&nbsp;</span><br />
	                      <span class="greenshield">100%本息保障</span>
	                  </div>
    				  <div class="splitline"></div>
	                  <div class="detail">
	                       <span>当前进度</span><#if moneyPlan.investedMoney??><span class="progressBar" id="pb${moneyPlan.mmplanId}">${moneyPlan.progress}%</span></#if><br/>
	                      <span>剩余金额：&nbsp;&nbsp;</span><span>${moneyPlan.afterMoney}元</span><br/>
	               <#if  moneyPlan.state==-1>
						<a class="buttongray btn" href="#">敬请期待</a>
					<#elseif moneyPlan.state==0>
						<a class="buttongray btn" href="#">敬请期待</a>
					<#elseif  moneyPlan.state==1>
						<a class="buttonorange btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${moneyPlan.mmplanId}" target="_self">投标</a>
					<#elseif  moneyPlan.state==2>
						<a class="buttongray btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${moneyPlan.mmplanId}" target="_self">已满标</a>
					<#elseif  moneyPlan.state==3>
						<a class="buttongray btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${moneyPlan.mmplanId}" target="_self">已流标</a>	
					<#elseif moneyPlan.state==5>
						<a class="buttongray btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${moneyPlan.mmplanId}" target="_self">还款中</a>	
					<#else>
						<a class="buttongray btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${moneyPlan.mmplanId}" target="_self">已关闭</a>	
					</#if>
	                  </div>    			    			
	                 </div>		
	              </#list>
	              		
	    	</div>
	    </div>
	</div>
	<div class="bidinfo-list">
		<dl>
			<dt><img src="${base}/theme/${systemConfig.theme}/images/bidinfo-list_01.png"  /></dt>
			<dd class="txt">
				<b class="tit middle">cello&nbsp;&nbsp;<span>都市白领</span></b>
				<div class="normal gray">作为都市白领的我，虽然理财金额不多但是也没空每天操作，三季丰简直贴心了，一个点击就搞定了，比银行、基金什么的强太多了吧！</div>
			</dd>
			<dt><img src="${base}/theme/${systemConfig.theme}/images/bidinfo-list_02.png"  /></dt>
			<dd class="txt">
				<b class="tit middle">鸟叔&nbsp;&nbsp;<span>打工一族</span></b>
				<div class="normal gray">这是完爆银行理财的结构吗？？？3个月比银行存款多2%，6个月多4%，而且1000块就能加入，支持双季盈！</div>
			</dd>
			<dt><img src="${base}/theme/${systemConfig.theme}/images/bidinfo-list_03.png"  /></dt>
			<dd class="txt">
				<b class="tit middle">果果&nbsp;&nbsp;<span>都市白领</span></b>
				<div class="normal gray">之前一直觉得投资期限太长，有了季度红酒好多了。3个月、6个月，非常适合我的需要，哈哈哈！</div>
			</dd>
			<dt><img src="${base}/theme/${systemConfig.theme}/images/bidinfo-list_04.png"  /></dt>
			<dd class="txt">
				<b class="tit middle">黑白&nbsp;&nbsp;<span>在校大学生</span></b>
				<div class="normal gray">3个月、6个月，不用复投，这完全是懒人标配理财产品！</div>
			</dd>
		</dl>
	</div>
</div>
</div>
<!-- end：总投资列表页-->
<div style="height:4px; border-bottom:1px #f0f0f0 solid; margin:15px auto 0px auto;" title="横线"></div>
<!-- end main --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 


<!-- copyright -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</div>
</body>
</html>
