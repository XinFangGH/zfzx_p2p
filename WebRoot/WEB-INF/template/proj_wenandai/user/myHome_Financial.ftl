<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title>${systemConfig.metaTitle} - 个人信息</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/uploadImage.js"></script>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript" src="${base}/js/user/mmplan.js"></script>
<script type="text/javascript">var m1="个人中心",m2="我的债权",m3="";</script>
<script type="text/javascript">
	$(function(){
		$(".cont-list .finlist_title li").click(function(){
			$(this).addClass("active").siblings().removeClass();
			$(".cont-list .finlist_hide ol").eq($(this).index()).addClass("rent").siblings().removeClass();
		});
	})
</script>

</head>
<body>
<!-- header --><!-- navbar -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<!--<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">-->
<input value="${(bpCustMember.id)??}" type="hidden" id="memberId">
 <div class="main">
 	 <div class="user-cont">
   		<!--左侧三级导航条 -->
	   <div class="user-name-nav fl">
	       <#if bpCustMember.entCompanyType==1>
    	  <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_guarantee.ftl">
    	  <#elseif bpCustMember.perCompanyType==1 >
    	  <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_financial.ftl"> 
    	  <#else>
    	  <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">  
    	  </#if>
	   </div>
    
    
    
    
     <div class="user-cont-fr fr">   
      <div class="user-cont-right">
      	<div class="data-list">
    			<div class="date-left fl" style="padding-top: 25px;">
    				<div class="icon-img">
    					<span class="data-icon"></span>
    				</div>
    				<div class="title" style="padding-top: 15px;">
    					<p class="f20">理财数据</p>
    				</div>
				</div>
    			<div class="data-right fl" style="width:440px;">
    				<div class="data-invent">
    					<p class="normal"><i class="icon-num icon-01"></i>预售中（个）</p>
    					<p class="middle pd-30"><em>0</em>个</p>
    				</div>
    				<div class="data-invent">
    					<p class="normal"><i class="icon-num icon-02"></i>招标中（个）</p>
    					<p class="middle pd-30"><em>0</em>个</p>
    				</div>
    				<div class="data-invent">
    					<p class="normal"><i class="icon-num icon-03"></i>回款中（个）</p>
    					<p class="middle pd-30"><em>1</em>个</p>
    				</div>
    				<div class="data-invent">
    					<p class="normal"><i class="icon-num icon-04"></i>已完成（个）</p>
    					<p class="middle pd-30"><em>1</em>个</p>
    				</div>
    			</div>
    			<div class="data-right fr" style="width:200px;padding:50px 0 0 0;">
    				<div class="data-invent">
    					<p class="normal"><i class="icon-num icon-01"></i>已齐标（个）</p>
    					<p class="middle pd-30"><em>0</em>个</p>
    				</div>
    			</div>
    		</div>
    		<div class="dashedline"></div>
      		<div class="data-list" style="padding-top:20px;">
    			<div class="date-left fl" >
    				<div class="icon-img">
    					<span class="data-icon"></span>
    				</div>
    				<div class="title" style="padding-top: 15px;">
    					<p class="f20">资金数据</p>
    				</div>
				</div>
    			<div class="data-right fl" >
    				<div class="data-invent">
    					<p class="normal"><i class="icon-num icon-01"></i>应派本金（元）</p>
    					<p class="middle pd-30"><em>0.00</em>元</p>
    				</div>
    				<div class="data-invent">
    					<p class="normal"><i class="icon-num icon-02"></i>应派利息（元）</p>
    					<p class="middle pd-30"><em>0.00</em>元</p>
    				</div>
    			</div>
    		</div>
    		<div class="cont-list">
	        	<ul class="finlist_title">
	                  <li class="active">回款中</li>
	                  <li>已齐标</li>
	                  <li>招标中</li>
	                  <li>预售中</li>
	                  <li>已完成</li>
	       		</ul>
        		<div class="finlist_hide">
        			<ol  class="rent">
				    	<div class="tab-list">
				    		<div class="worm-tips fl">
				            	 <span class="icon1">回款中</span>
				    	   </div>
				          	<p style="padding:5px 30px 10px 0; float:left;">
								理财计划名称: 
								<input type="text" style="width:120px" name="bidtime" class="colorful" readonly="readonly" value="${startTime_dealDate}" onclick="new Calendar().show(this);"/>
								<span  style="font-size:14px; padding:4px 15px;color:#fd7754;" id="selectUseraccountinfo" name="selectUseraccountinfo">查&nbsp;询</span> 
							</p>
							<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
								 <tr>
									 <th align="center">计划名称</th>
									 <th align="center">招标金额</th>
									 <th align="center">逾期收益率</th>
									 <th align="center">投资期限</th>
									 <th align="center">锁定期至</th>
									 <th align="center">计划派息日</th>
									 <th align="center">派息金额</th>
									 <th align="center">查看</th>
								     <th align="center">操作</th>
								 </tr>
					             <tr>
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>	
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>	
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>				 	
								</tr>     
						    </table>
				       </div>
				     </ol>
				     <ol>
				    	<div class="tab-list">
				    		<div class="worm-tips fl">
				            	 <span class="icon1">已齐标</span>
				    	   </div>
				          	<p style="padding:5px 30px 10px 0; float:left;">
								理财计划名称: 
								<input type="text" style="width:120px" name="bidtime" class="colorful" readonly="readonly" value="${startTime_dealDate}" onclick="new Calendar().show(this);"/>
								<span  style="font-size:14px; padding:4px 15px;color:#fd7754;" id="selectUseraccountinfo" name="selectUseraccountinfo">查&nbsp;询</span> 
							</p>
							<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
								 <tr>
									 <th align="center">计划名称</th>
									 <th align="center">招标金额</th>
									 <th align="center">逾期收益率</th>
									 <th align="center">投资期限</th>
									 <th align="center">锁定期至</th>
									 <th align="center">计划派息日</th>
									 <th align="center">派息金额</th>
									 <th align="center">查看</th>
								     <th align="center">操作</th>
								 </tr>
					             <tr>
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>	
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>	
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>				 	
								</tr>     
						    </table>
				       </div>
				     </ol>
				     <ol>
				    	<div class="tab-list">
				    		<div class="worm-tips fl">
				            	 <span class="icon1">招标中</span>
				    	   </div>
				          	<p style="padding:5px 30px 10px 0; float:left;">
								理财计划名称: 
								<input type="text" style="width:120px" name="bidtime" class="colorful" readonly="readonly" value="${startTime_dealDate}" onclick="new Calendar().show(this);"/>
								<span  style="font-size:14px; padding:4px 15px;color:#fd7754;" id="selectUseraccountinfo" name="selectUseraccountinfo">查&nbsp;询</span> 
							</p>
							<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
								 <tr>
									 <th align="center">计划名称</th>
									 <th align="center">招标金额</th>
									 <th align="center">逾期收益率</th>
									 <th align="center">投资期限</th>
									 <th align="center">锁定期至</th>
									 <th align="center">计划派息日</th>
									 <th align="center">派息金额</th>
									 <th align="center">查看</th>
								     <th align="center">操作</th>
								 </tr>
					             <tr>
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>	
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>	
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>				 	
								</tr>     
						    </table>
				       </div>
				     </ol>
				     <ol>
				    	<div class="tab-list">
				    		<div class="worm-tips fl">
				            	 <span class="icon1">预售中</span>
				    	   </div>
				          	<p style="padding:5px 30px 10px 0; float:left;">
								理财计划名称: 
								<input type="text" style="width:120px" name="bidtime" class="colorful" readonly="readonly" value="${startTime_dealDate}" onclick="new Calendar().show(this);"/>
								<span  style="font-size:14px; padding:4px 15px;color:#fd7754;" id="selectUseraccountinfo" name="selectUseraccountinfo">查&nbsp;询</span> 
							</p>
							<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
								 <tr>
									 <th align="center">计划名称</th>
									 <th align="center">招标金额</th>
									 <th align="center">逾期收益率</th>
									 <th align="center">投资期限</th>
									 <th align="center">锁定期至</th>
									 <th align="center">计划派息日</th>
									 <th align="center">派息金额</th>
									 <th align="center">查看</th>
								     <th align="center">操作</th>
								 </tr>
					             <tr>
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>	
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>	
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>				 	
								</tr>     
						    </table>
				       </div>
				     </ol>
				     <ol>
				    	<div class="tab-list">
				    		<div class="worm-tips fl">
				            	 <span class="icon1">已完成</span>
				    	   </div>
				          	<p style="padding:5px 30px 10px 0; float:left;">
								理财计划名称: 
								<input type="text" style="width:120px" name="bidtime" class="colorful" readonly="readonly" value="${startTime_dealDate}" onclick="new Calendar().show(this);"/>
								<span  style="font-size:14px; padding:4px 15px;color:#fd7754;" id="selectUseraccountinfo" name="selectUseraccountinfo">查&nbsp;询</span> 
							</p>
							<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
								 <tr>
									 <th align="center">计划名称</th>
									 <th align="center">招标金额</th>
									 <th align="center">逾期收益率</th>
									 <th align="center">投资期限</th>
									 <th align="center">锁定期至</th>
									 <th align="center">计划派息日</th>
									 <th align="center">派息金额</th>
									 <th align="center">查看</th>
								     <th align="center">操作</th>
								 </tr>
					             <tr>
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>	
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>	
									 <td align="center">11</td>
									 <td align="center">11</td>
									 <td align="center">11</td>				 	
								</tr>     
						    </table>
				       </div>
				     </ol>
        		</div>
      	<!--<div class="counter">
      	<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_financial.ftl">
        	<ul class="ui-list">
            	<li>
                	<span class="icon"></span>
                    <span class="date"><#if InvestLoanMoneytotal==null>0.00<#else><#if InvestLoanMoneytotal lt 1000>${InvestLoanMoneytotal+bpCustMember.freezeMoney}<#else>${InvestLoanMoneytotal+bpCustMember.freezeMoney}</#if></#if><em>资产总额（元）</em></span>
                    <span class="icon1"></span>
                </li>
                <li><span class="sign">=</span></li>
                <li>
                    <span class="date1">
                    <#if bpCustMember.availableInvestMoney==0>0.00<#else><#if bpCustMember.availableInvestMoney lt 1000>${bpCustMember.availableInvestMoney+bpCustMember.freezeMoney}<#else>${bpCustMember.availableInvestMoney+bpCustMember.freezeMoney}</#if></#if><em>资金余额（元）</em></span>
                </li>
                <li><span class="sign1">+</span></li>
                 <li>
                    <span class="date1"><#if investMoney==0>0.00<#else><#if investMoney lt 1000>${investMoney}<#else>${investMoney?string(',###.00')}</#if></#if><em>投资金额（元）</em></span>
                </li>
                <li><span class="sign1">-</span></li>
                 <li>
                    <span class="date1"><#if loanTotal??><#if loanTotal==0>0.00<#else><#if loanTotal lt 1000>${loanTotal}<#else>${loanTotal?string(',###.00')}</#if></#if><#else>0.00</#if><em>借款金额（元）</em></span>
                </li>
                
            </ul>
        </div>-->
        
      <!--<div class="">
          <div class="guarantee">
			  <div class="gat-list">
		    		<h2><span>理财数据</span></h2>
			        <div class="li-list">
			            <ul>
			                <li>预售中：<span id="lc01">0</span>个</li>
			                <li>招标中：<span id="lc02">0</span>个</li>
			                <li>已齐标：<span id="lc03">0</span>个</li>
			            </ul>
			            <ul>
			                
			                <li>回款中：<span id="lc04">0</span>个</li>
			                <li>已完成:<span id="lc05">0</span>个</li>
			            </ul>
			        </div>
		    </div>
		    <div class="gat-list">
		    	<h2 class="bg"><span>资金数据</span></h2>
		        <div class="li-list">
		            <ul style="padding-top:23px;">
		                <li>应派本金：<span id="zj01">0.00</span>元</li>
		                <li>应派利息：<span id="zj02">0.00</span>元</li>
		            </ul>
		         
		        </div>
		    </div>
   </div>-->
      <!--<div class="tab-list" style="font-size:14px; color:red;"></div>  
      <div class="frame-tab">
          <div class="tab-title">
            <ul>
               <li   class="selected" onclick="plan_more('plan_one')">理财计划招标中</li>
              <span class="plan_one"> 
              <a href="${base}/creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?state=7&show=returnList"><i class="more"></i>更多</a></span>
              </ul>
          </div>
          <div id="tab-content">	
              <div class="tb-c" >
                   <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
		                 <tr>
		                      <th align="center" >名称</th>
		                      <th align="center">招标金额</th>
		                      <th align="center">预期收益率</th>
		                      <th align="center">投资期限</th>
		                      <th align="center">购买结束时间</th>
		                  </tr>                  
		                <#list   mflist1 as list>
		         			
		                <tr class="ff">
		                      <td align="center" class="td-top"  style="padding-left:15px;white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
		                    <a style="color:#0096c4;font-size:14px;" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.mmplanId}" title="${list.mmName}">${list.mmName}</a>
		                    </td>
		                      <td align="center">￥${list.sumMoney?string(',##0.00')}元</td>
		                      <td align="center">${list.yeaRate}%</td>
		                      <td align="center">${list.investlimit}月</td>
		                      <td align="center">${list.buyEndTime?string("yyyy-MM-dd")}</td>
		                     
		                  </tr>
		        		</#list>
		                
		          </table>                     
              </div>
	  	</div> 
		
      </div>-->
    </div>
</div>
</div>
</div> 
</div>    	
<!--end: Container -->
       <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
       	
	</body>
</html>



<script type="text/javascript">
	$(function () {
	var map1 = {};
	//理财信息
	
	 ajaxFunction("${base}/user/getBidCountFinancialBpCustMember.do?state=1&isPresale=ysz", map1, lc01);
	
	ajaxFunction("${base}/user/getBidCountFinancialBpCustMember.do?state=1", map1, lc02);
	
	ajaxFunction("${base}/user/getBidCountFinancialBpCustMember.do?state=2", map1, lc03);
	
	ajaxFunction("${base}/user/getBidCountFinancialBpCustMember.do?state=7", map1, lc04);
	
	ajaxFunction("${base}/user/getBidCountFinancialBpCustMember.do?state=10", map1, lc05);
	
	//资金信息

	ajaxFunction("${base}/user/getBidMoneyFinancialBpCustMember.do?fundType=principalRepayment", map1, zj01);
	
	ajaxFunction("${base}/user/getBidMoneyFinancialBpCustMember.do?fundType=loanInterest", map1, zj02);
	
	 });
	 
	 function lc01(data){
		 var result=data.result;
		 $("#lc01").empty().html(result);
	 }
	 function lc02(data){
		 var result=data.result;
		 $("#lc02").empty().html(result);
	 }
	 function lc03(data){
		 var result=data.result;
		 $("#lc03").empty().html(result);
	 }
	 function lc04(data){
		 var result=data.result;
		 $("#lc04").empty().html(result);
	 }
	 function lc05(data){
		 var result=data.result;
		 $("#lc05").empty().html(result);
	 }
	 function zj01(data){
		 var result=data.result;
		 $("#zj01").empty().html(result);
	 }
	 function zj02(data){
		 var result=data.result;
		 $("#zj02").empty().html(result);
	 }
</script>