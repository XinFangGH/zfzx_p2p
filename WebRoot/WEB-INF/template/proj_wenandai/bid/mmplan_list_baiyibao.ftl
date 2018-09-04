<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 理财计划详情</title>

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>

<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
<script type="text/javascript">
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200 });		
	});
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
 <!-- start：百翼宝页列表面 -->
 
  <div class="bidplan1">
	  	    <div class="baiyibao">
	    		<div class="titlediv titlediv1">    			
		    		<div class="title">百翼宝</div>
		    		
	    		</div>
	    		<div class="iconlist">
		    	<div class="blockdiv">
		    		<div class="picdiv">
		    			<div class="circlewhole130" style="margin:15px 0 0 20px;">
			    			<br/>
			    			<br/>
			    			<span class="large black"><#if bybMoneyPlan??>${bybMoneyPlan.investlimit!'0'}<#else>0</#if>个月</span>
			    			<span class="num" style="display:none;">100%</span>
			    			<br/>
			    			<span class="normal">期限</span>
		    			</div>	
	    		</div>
		    		<div class="bigtext">
		    			<#if bybMoneyPlan??>${bybMoneyPlan.persionNum!'0'}<#else>0</#if>人已加入 
		    		</div>
		    	</div>
		    	<div class="blockdiv">
		    		<div class="picdiv">
		    			<div class="circlewhole130" style="margin:15px 0 0 20px; ">
			    			<br/>
			    			<br/>
			    			<span class="num large black"><#if bybMoneyPlan??>${bybMoneyPlan.yeaRate}<#else>0</#if>%</span>
			    			<br/>
			    			<span class="normal">年化收益率</span>
		    			</div>	    			
		    		</div>
		    		<div class="bigtext">
		    			<span class="greenshield">100%本息保障</span>
		    		</div>
		    	</div>
		    	<div class="blockdiv">
		    		<div class="picdiv">
		    			<div class="circleprogress130" style="margin:15px 0 0 20px;">
			    			<br/>
			    			<br/>
			    			<span class="num large black"><#if bybMoneyPlan??>${bybMoneyPlan.progress}<#else>0</#if>%</span>
			    			<br/>
			    			<span class="normal">当前进度</span>
		    			</div>
		    		</div>
		    		<div class="bigtext">
		    		<#if bybMoneyPlan??>
		    			
		    		<#if  bybMoneyPlan.state==-1>
						<a class="buttongray btn" href="#">敬请期待</a>
					<#elseif bybMoneyPlan.state==0>
						<a class="buttongray btn" href="#">敬请期待</a>
					<#elseif  bybMoneyPlan.state==1>
						<a class="buttonorange btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=<#if bybMoneyPlan??>${bybMoneyPlan.mmplanId}<#else>0</#if>" target="_self">投标</a>
					<#elseif  bybMoneyPlan.state==2>
						<a class="buttongray btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=<#if bybMoneyPlan??>${bybMoneyPlan.mmplanId}<#else>0</#if>" target="_self">已满标</a>
					<#elseif  bybMoneyPlan.state==3>
						<a class="buttongray btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=<#if bybMoneyPlan??>${bybMoneyPlan.mmplanId}<#else>0</#if>" target="_self">已流标</a>	
					<#elseif  bybMoneyPlan.state==5>
						<a class="buttongray btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=<#if bybMoneyPlan??>${bybMoneyPlan.mmplanId}<#else>0</#if>" target="_self">还款中</a>	
					<#else>
						<a class="buttongray btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=<#if bybMoneyPlan??>${bybMoneyPlan.mmplanId}<#else>0</#if>" target="_self">已关闭</a>	
					</#if>
					<#else>
					<a class="buttongray btn" href="#">敬请期待</a>
					</#if>
		    		</div>
		    	</div>
    		</div>
    	</div>
    	</div>
	 <div class="introduce1">
	 	<div class="title">百翼宝介绍</div>
	 	<br/>
	 	<p style="font-size:16px; color:#666;">&nbsp;&nbsp;“百翼宝”产品，封闭期限一年及以上，出借人在封闭期间结束后获得本息收益回报，出借人可以通过百翼宝平台的审核及推荐，将手中的富余资金出借给信用良好、有资金需求的大学生、工薪阶层、微小企业主、农民等，帮助他们实现教育培训、家电购买、装修、兼职创业等梦想。同时，根据选择封闭期限的不同时长，出借人的年化收益率不断递增，在实现经济价值的同时还实现了巨大的社会价值。百翼宝预期年化收益率12%-14%。</p>
	 </div>
	 <div class="introduce1">
	 	<div class="title">百翼宝投资原理</div>
	 	<p class="picture"><img src="${base}/theme/${systemConfig.theme}/images/investment.jpg" alt=""  /></p>
	 </div>
	
 
 <!-- end：百翼宝页列表面-->
 
 
  
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
