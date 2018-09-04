<div class="bidinfo"> 
 <!-- start：总投资列表页 -->
	 <div class="bidplan1">
	    <div class="baiyibao">
			<div class="titlediv">    			
	    		<div class="title">固定期限类</div>
	    		<div class="morelink"><a href="#"><span class="white small">了解详情></span></a></div>
			</div>
		</div>
		<div class="financeplan">
	    	<div class="planshow">
	    	<br/> 
	    		<p class="regular gray">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"固定期限出借方式"是百翼为你推出具有较高资金流动式的理财模式，根据资金封闭期不同，分为3个月、6个月、9个月、12个月四档模式，理财客户在资金封闭期内，可以按月获取投资收益，封闭期结束后，本机返还至理财客户账户，预期年化收益率9%-12%。</p>            
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
	                      <span>当前进度&nbsp;&nbsp;</span><span></span><span>&nbsp;&nbsp;${moneyPlan.progress}%</span><br/>
	                      <span>剩余金额&nbsp;&nbsp;</span><span>${moneyPlan.afterMoney}元</span><br/>
	                      <a class="buttonorange btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${moneyPlan.mmplanId}" target="_self">投标</a>
	                  </div>    			    			
	                 </div>		
	              </#list>
	              		
	    	</div>
	    </div>
	</div>
<!-- end：总投资列表页-->

 <!-- start：百翼宝页列表面 -->
 
  <div class="bidplan1">
	  	    <div class="baiyibao">
	    		<div class="titlediv titlediv1">    			
		    		<div class="title">百翼宝</div>
		    		
	    		</div>
	    		<div class="iconlist">
		    	<div class="blockdiv">
		    		<div class="picdiv">
		    			
		    			<div class="circleprogress130" style="margin:15px 0 0 20px;">
			    			<br/>
			    			<span class="large black">12个月</span>
			    			<span class="num" style="display:none;">100%</span>
			    			<br/>
			    			<span class="normal">期限</span>
		    			</div>	
	    		</div>
		    		<div class="bigtext">
		    			15192人已加入 
		    		</div>
		    	</div>
		    	<div class="blockdiv">
		    		<div class="picdiv">
		    			<div class="circleprogress130" style="margin:15px 0 0 20px;">
			    			<br/>
			    			<span class="num large black">12-14%</span>
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
			    			<span class="num large black">78%</span>
			    			<br/>
			    			<span class="normal">当前进度</span>
		    			</div>
		    		</div>
		    		<div class="bigtext">
		    			<a class="buttonorange btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${bybMoneyPlan.mmplanId}" target="_self">投标</a>
		    		</div>
		    	</div>
    		</div>
    	</div>
    	</div>
	 <div class="introduce">
	 	<div class="title">百翼宝介绍</div>
	 	<br/>
	 	<p style="font-size:16px; color:#666;">&nbsp;&nbsp;“百翼宝”产品，封闭期限一年及以上，出借人在封闭期间结束后获得本息收益回报，出借人可以通过百翼宝平台的审核及推荐，将手中的富余资金出借给信用良好、有资金需求的大学生、工薪阶层、微小企业主、农民等，帮助他们实现教育培训、家电购买、装修、兼职创业等梦想。同时，根据选择封闭期限的不同时长，出借人的年化收益率不断递增，在实现经济价值的同时还实现了巨大的社会价值。百翼宝预期年化收益率12%-14%。</p>
	 </div>
	 <div class="introduce">
	 	<div class="title">百翼宝投资原理</div>
	 	<p class="picture"><img src="${base}/theme/${systemConfig.theme}/images/investment.jpg" alt=""  /></p>
	 </div>
	
 
 <!-- end：百翼宝页列表面-->
 
 
  <!-- start：散标理财列表面-->
  <div class="bidplan1">
	    <div class="baiyibao">
			<div class="titlediv titlediv1">    			
	    		<div class="title">散标理财列表</div>
			</div>
<table class="tab_css_3" width="1001" border="0 cellspacing="0" cellpadding="0"" style="text-align:center; background:#fff;">
 
  <tr>
    <th>借款标题</th>
    <th>信用等级</th>
    <th>年利率</th>
    <th>金额	</th>
    <th>期限</th>
    <th>进度</th>
    <th><h4 class="computer">理财计算器</h4></th>
  </tr>
  <tr>
    <td>资金周转</td>
    <td class="bidplan-bg"><h3>A</h3></td>
    <td>12%</td>
    <td>390 000</td>
    <td>12个月</td>
    <td>
    	<div class="blockdiv1">
		    		<div class="picdiv1">
		    			
		    			<div class="circleprogress45" style="text-align:center">
			    			
			    			<span class="num">100%</span>
			    			
		    			</div>	
		    			
		    		</div>
		    		
		    	</div>
	</td>
    <td><span class="buttonorange">还款中</span></td>
    
  </tr>
  <tr>
    <td>资金周转</td>
    <td class="bidplan-bg"><h3>A</h3></td>
    <td>12%</td>
    <td>390 000</td>
    <td>12个月</td>
    <td>
    	<div class="blockdiv1">
		    		<div class="picdiv1">
		    			
		    			<div class="circleprogress45" style="text-align:center">
			    			
			    			<span class="num">100%</span>
			    			
		    			</div>	
		    			
		    		</div>
		    		
		    	</div>
	</td>
    <td><span class="buttonorange">还款中</span></td>
    
  </tr>
  <tr>
    <td>资金周转</td>
    <td class="bidplan-bg"><h3>A</h3></td>
    <td>12%</td>
    <td>390 000</td>
    <td>12个月</td>
    <td>
    	<div class="blockdiv1">
		    		<div class="picdiv1">
		    			
		    			<div class="circleprogress45" style="text-align:center">
			    			
			    			<span class="num">100%</span>
			    			
		    			</div>	
		    			
		    		</div>
		    		
		    	</div>
	</td>
    <td><span class="buttonorange">还款中</span></td>
    
  </tr>
  <tr>
    <td>资金周转</td>
    <td class="bidplan-bg"><h3>A</h3></td>
    <td>12%</td>
    <td>390 000</td>
    <td>12个月</td>
    <td>
    	<div class="blockdiv1">
		    		<div class="picdiv1">
		    			
		    			<div class="circleprogress45" style="text-align:center">
			    			
			    			<span class="num">100%</span>
			    			
		    			</div>	
		    			
		    		</div>
		    		
		    	</div>
	</td>
    <td><span class="buttonorange">还款中</span></td>
    
  </tr>
 
</table>

	</div>
	
  <!-- end：散标理财列表面-->
  
  
  
  <!-- start：我要出借列表面-->
  	<!-- start：百翼宝-->
   <div class="bidplan1" style="margin-bottom:20px;">
	  	    <div class="baiyibao">
	    		<div class="titlediv titlediv1">    			
		    		<div class="title">百翼宝</div>
		    		
	    		</div>
	    		<div class="iconlist">
		    	<div class="blockdiv">
		    		<div class="picdiv">
		    			
		    			<div class="circleprogress130" style="margin:3px 0 0 20px;">
			    			<br/>
			    			<span class="large black" >12个月</span>
			    			<span class="num" style="display:none;">100%</span>
			    			<br/>
			    			<span class="normal">期限</span>
		    			</div>	
	    		</div>
		    		<div class="bigtext">
		    			15192人已加入 
		    		</div>
		    	</div>
		    	<div class="blockdiv">
		    		<div class="picdiv">
		    			<div class="circleprogress130" style="margin:3px 0 0 20px;">
			    			<br/>
			    			<span class="num large black">12-14%</span>
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
		    			<div class="circleprogress130" style="margin:3px 0 0 20px;">
			    			<br/>
			    			<span class="num large black">78%</span>
			    			<br/>
			    			<span class="normal">当前进度</span>
		    			</div>
		    		</div>
		    		<div class="bigtext">
		    			<a class="buttonorange btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${bybMoneyPlan.mmplanId}" target="_self">投标</a>
		    		</div>
		    	</div>
    		</div>
    	</div>
    	</div>
    	<!-- end：百翼宝-->
    	<!-- start：固定期限类-->
    		 <div class="bidplan1">
	    <div class="baiyibao">
			<div class="titlediv titlediv1">    			
	    		<div class="title">固定期限类</div>
	    		<div class="morelink"><a href="#"><span class="white small">了解详情></span></a></div>
			</div>
		</div>
		<div class="financeplan">
	    	<div class="planshow"> 
	    	<br/>
	    		<p class="regular gray">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"固定期限出借方式"是百翼为你推出具有较高资金流动式的理财模式，根据资金封闭期不同，分为3个月、6个月、9个月、12个月四档模式，理财客户在资金封闭期内，可以按月获取投资收益，封闭期结束后，本机返还至理财客户账户，预期年化收益率9%-12%。</p>            
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
	                  <div class="detail">
	                      <span>当前进度&nbsp;&nbsp;</span><span></span><span>&nbsp;&nbsp;${moneyPlan.progress}%</span><br/>
	                      <span>剩余金额&nbsp;&nbsp;</span><span>${moneyPlan.afterMoney}元</span>&nbsp;&nbsp;<a class="buttonorange btn" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${moneyPlan.mmplanId}" target="_self">投标</a>
	                  </div>    			    			
	                 </div>		
	              </#list>
	              		
	    	</div>
	    </div>
	</div>
<!-- end：固定期限类--

 <!-- start：散标理财-->
   <div class="bidplan1">
	    <div class="baiyibao">
			<div class="titlediv titlediv1">    			
	    		<div class="title">散标理财列表</div>
			</div>
<table class="tab_css_3" width="1001" border="0 cellspacing="0" cellpadding="0"" style="text-align:center; background:#fff;">
 
  <tr>
    <th>借款标题</th>
    <th>信用等级</th>
    <th>年利率</th>
    <th>金额	</th>
    <th>期限</th>
    <th>进度</th>
    <th><h4 class="computer">理财计算器</h4></th>
  </tr>
  <tr>
    <td>资金周转</td>
    <td class="bidplan-bg"><h3>A</h3></td>
    <td>12%</td>
    <td>390 000</td>
    <td>12个月</td>
    <td>
    	<div class="blockdiv1">
		    		<div class="picdiv1">
		    			
		    			<div class="circleprogress45" style="text-align:center">
			    			
			    			<span class="num">100%</span>
			    			
		    			</div>	
		    			
		    		</div>
		    		
		    	</div>
	</td>
    <td><span class="buttonorange">还款中</span></td>
    
  </tr>
  <tr>
    <td>资金周转</td>
    <td class="bidplan-bg"><h3>A</h3></td>
    <td>12%</td>
    <td>390 000</td>
    <td>12个月</td>
    <td>
    	<div class="blockdiv1">
		    		<div class="picdiv1">
		    			
		    			<div class="circleprogress45" style="text-align:center">
			    			
			    			<span class="num">100%</span>
			    			
		    			</div>	
		    			
		    		</div>
		    		
		    	</div>
	</td>
    <td><span class="buttonorange">还款中</span></td>
    
  </tr>
  <tr>
    <td>资金周转</td>
    <td class="bidplan-bg"><h3>A</h3></td>
    <td>12%</td>
    <td>390 000</td>
    <td>12个月</td>
    <td>
    	<div class="blockdiv1">
		    		<div class="picdiv1">
		    			
		    			<div class="circleprogress45" style="text-align:center">
			    			
			    			<span class="num">100%</span>
			    			
		    			</div>	
		    			
		    		</div>
		    		
		    	</div>
	</td>
    <td><span class="buttonorange">还款中</span></td>
    
  </tr>
  <tr>
    <td>资金周转</td>
    <td class="bidplan-bg"><h3>A</h3></td>
    <td>12%</td>
    <td>390 000</td>
    <td>12个月</td>
    <td>
    	<div class="blockdiv1">
		    		<div class="picdiv1">
		    			
		    			<div class="circleprogress45" style="text-align:center">
			    			
			    			<span class="num">100%</span>
			    			
		    			</div>	
		    			
		    		</div>
		    		
		    	</div>
	</td>
    <td><span class="buttonorange">还款中</span></td>
    
  </tr>
 
</table>

	</div>
  <!-- end：散标理财-->
  
  <!-- end：我要出借列表面-->
  
  
</div>