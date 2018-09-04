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
</script>
</head>
<body >
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
<div class="bidinfo"> 
 <!-- start：总投资列表页 -->
	  <div class="bidplan">
	  	    <div class="baiyibao">
	    		<div class="titlediv">    			
		    		<div class="title">百翼宝</div>
		    		<div class="morelink"><a href="#"><span class="white small">了解详情></span></a></div>
	    		</div>
	    		<div class="iconlist">
			    	<div class="blockdiv">
			    		<div class="picdiv">
			    			<div style="width:100%;height:33px;"></div>
			    			<span class="large black"><#if bybMoneyPlan?exists>${bybMoneyPlan.investlimit}<#else>0</#if>个月</span><br />
			    			<span class="normal">期限</span>
			    		</div>
			    		<div class="bigtext">
			    			<#if bybMoneyPlan?exists>${bybMoneyPlan.persionNum}<#else>0</#if>人已加入
			    		</div>
			    	</div>
			    	<div class="blockdiv">
			    		<div class="picdiv">
			    			<div style="width:100%;height:33px;"></div>
			    			<span class="large black"><#if bybMoneyPlan?exists>${bybMoneyPlan.yeaRate}<#else>0</#if></span><br />
			    			<span class="normal">年化收益</span>		    			
			    		</div>
			    		<div class="bigtext">
			    			<span class="greenshield">100%本息保障</span>
			    		</div>
			    	</div>
			    	<div class="blockdiv">
			    		<div class="picdiv">
			    			<div style="width:100%;height:33px;"></div>
			    			<span class="large black"><#if bybMoneyPlan?exists>${bybMoneyPlan.progress}<#else>0</#if>%</span><br />
			    			<span class="normal">当前进度</span>
			    		</div>
			    		<div class="bigtext">
			    			<a class="buttonorange btn">投标</a>
			    		</div>
			    	</div>
	    		</div>
	    	</div> 
	 </div>
	 <div style="width:100%; height:15px;"></div>
	 <div class="bidplan">
	    <div class="baiyibao">
			<div class="titlediv">    			
	    		<div class="title">固定期限类</div>
	    		<div class="morelink"><a href="#"><span class="white small">了解详情></span></a></div>
			</div>
		</div>
		<div class="financeplan">
	    	<div class="planshow">             
	              <#list listMoneyPlan as moneyPlan>
	                  <#if (moneyPlan_index + 1) % 2 == 1 >
	                      <div class="blockdiv bg1">
	                  <#else>
	                      <div class="blockdiv bg2">
	                  </#if>
	                  <div class="title">${moneyPlan.manageMoneyTypeName}</div>
	                  <div class="content">
	  
	                      <span class="small gray">${moneyPlan.persionNum}人次已加入</span><br />
	                      <span class="large bold black" style="font-size:32px;">${moneyPlan.yeaRate}</span><span class="large bold black">%</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="large">${moneyPlan.investlimit}个月</span><br />
	                      <span>年化收益&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期限&nbsp;&nbsp;&nbsp;&nbsp;</span><br />
	                      <span class="greenshield">100%本息保障</span>
	                  </div>
	                  <div class="detail">
	                      <span>当前进度&nbsp;&nbsp;</span><span></span><span>&nbsp;&nbsp;${moneyPlan.progress}%</span><br/>
	                      <span>剩余金额&nbsp;&nbsp;</span><span>${moneyPlan.afterMoney}元</span>&nbsp;&nbsp;<a class="buttonorange btn" href="${base}${moneyPlan.htmlPath}" target="_blank">投标</a>
	                  </div>    			    			
	                 </div>		
	              </#list>	    		
	    	</div>
	    </div>
	</div>
<!-- end：总投资列表页-->
</div>
<div style="height:4px; border-bottom:1px #f0f0f0 solid; margin:15px auto 0px auto;" title="横线"></div>
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
</div>
</body>
</html>
