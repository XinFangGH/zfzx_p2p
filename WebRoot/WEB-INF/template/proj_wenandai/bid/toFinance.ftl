<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 我要出借</title>

    <meta name="description" content="${systemConfig.metaTitle} - 我要出借,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 我要出借,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>

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
<div > 
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
 <!-- start：我要出借列表面-->
 <!-- start：百翼宝页列表面 -->
 <div class="main">
  <div class="bidplan1" style="float:initial;">
	  	    <div class="baiyibao" style="background:#00ff00">
	    		<div class="titlediv titlediv1" >    			
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
	 
 
 <!-- end：百翼宝页列表面-->
    	<div class="bidinfo"> 
 <!-- start：总投资列表页 -->
	 <div class="bidplan1">
	    <div class="baiyibao">
			<div class="titlediv">    			
	    		<div class="title">固定期限类</div>
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
	                      <span>年化收益率&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期限&nbsp;&nbsp;&nbsp;&nbsp;</span><br />
	                      <span class="greenshield">100%本息保障</span>
	                  </div>
    				  <div class="splitline"></div>
	                  <div class="detail">
	                      <span>当前进度</span><#if moneyPlan.investedMoney??><span class="progressBar" id="pb${moneyPlan.mmplanId}">${moneyPlan.progress}%</span></#if><br/>
	                      <span>剩余金额&nbsp;&nbsp;</span><span>${moneyPlan.afterMoney}元</span><br/>
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
					<#elseif  moneyPlan.state==5>
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
<!-- end：总投资列表页-->

        <!-- start：散标理财列表面-->
  <div class="bidplan1">
	    <div class="baiyibao">
			<div class="titlediv1">    			
	    		<div class="title">散标理财列表</div>
			</div>
<table class="tab_css_3" width="1001" border="0 cellspacing="0" cellpadding="0"" style="text-align:center; background:#fff;">
 
  <tr>
    <th>借款标题</th>
    <th>项目类型</th>
    <th>年利率</th>
    <th>金额	</th>
    <th>期限</th>
    <th>进度</th>
    <th><h4 class="computer">理财计算器</h4></th>
  </tr>
  <#if (pager.list)??>
 	 <#list pager.list as list>
 	 	  <tr>
		    <td><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}"  title="${list.bidProName}"><#if list.bidProName?length &gt; 6>${list.bidProName?substring(0,6)}<#else>${list.bidProName}</#if></a></td>
		    <td class="bidplan-bg"><h3>${list.proKeeyRemark}</h3></td>
		    <td>
		    	<#if (list.proType=="B_Dir")>
										${list.bpBusinessDirPro.yearInterestRate}%
									</#if><#if (list.proType=="B_Or")>
										${list.bpBusinessOrPro.yearInterestRate}%
									</#if><#if (list.proType=="P_Dir")>
										${list.bpPersionDirPro.yearInterestRate}%
									</#if><#if (list.proType=="P_Or")>
										${list.bpPersionOrPro.yearInterestRate}%
				</#if>
		    </td>
		    <td>${list.bidMoney?string(',###.00')}元</td>
		    <td>
					<#if (list.proType=="B_Dir")>
										${list.bpBusinessDirPro.loanLife}个月
									</#if><#if (list.proType=="B_Or")>
										${list.bpBusinessOrPro.loanLife}个月
									</#if><#if (list.proType=="P_Dir")>
										${list.bpPersionDirPro.loanLife}个月
									</#if><#if (list.proType=="P_Or")>
										${list.bpPersionOrPro.loanLife}个月
					</#if>		    
		    </td>
		     <td>
		    	<div class="blockdiv1">
				    		<div class="picdiv1">
				    			<div class="circleprogress45" style="text-align:center">
					    			<span class="num">${list.progress}%</span>
				    			</div>	
				    		</div>
				    	</div>
			</td>
		    <td>
		    	<#if (list.state)??>
				    <#if list.state!=5>
				    		<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_self">
				    		<#if (list.laveTime)??>
				    			<#if list.laveTime?index_of('结束')!=-1>
				    				<span class="buttongray" style="font-size:14px; padding:4px 6px;">招标结束</span>
				    			<#else>
				    				<#if list.state==0>
				    					<span class="buttonblue" style="font-size:14px; padding:4px 18px;">未发标</span>
				    				<#elseif list.state==1>
				    					<span class="buttonblue" style="font-size:14px; padding:4px 18px;">招标中</span>
				    				<#elseif list.state==2>
				    					<span class="buttonblue" style="font-size:14px; padding:4px 18px;">已满标</span>
				    				<#elseif list.state==3>
				    					<span class="buttonblue" style="font-size:14px; padding:4px 18px;">已流标</span>
				    				<#elseif list.state==4>
				    					<span class="buttonblue" style="font-size:14px; padding:4px 18px;">已关闭</span>
				    				<#elseif list.state==5>
				    					<span class="buttonblue" style="font-size:14px; padding:4px 18px;">还款中</span>
				    				<#elseif list.state==6>
				    					<span class="buttonblue" style="font-size:14px; padding:4px 14px;">等待放款</span>
				    				<#else>
				    					<span class="buttonblue" style="font-size:14px; padding:4px 18px;">${list.state}</span>
				    				</#if>
				    			</#if>
				    		</#if>
				    		</a>
				   		<#else>
				   		<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_self"><span class="buttonblue" style="font-size:14px; padding:4px 18px;">还款中</span></a>
				    </#if>
			    </#if>

            </td> 
		  </tr>
 	 
 	 
 	 </#list>
  </#if> 
</table>

</div>
	
  <!-- end：散标理财列表面-->
  
  <!-- end：我要出借列表面-->
  
  
</div>
</div>
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
