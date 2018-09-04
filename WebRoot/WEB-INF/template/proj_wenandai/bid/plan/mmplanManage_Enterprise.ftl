<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 企业理财计划</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript" src="${base}/js/user/mmplan.js"></script>
<script type="text/javascript">var m1="个人中心",m2="理财计划",m3="我的投资";</script>
<script type="text/javascript">
$(function(){
	$(".cont-list .finlist_title li").click(function(){
		$(this).addClass("active").siblings().removeClass();
		$(".cont-list .finlist_hide ol").eq($(this).index()).addClass("rent").siblings().removeClass();
	});
	if($("#str").val()=='pass'){
 	}
});
	$(document).ready(function(){
		var active="${show}";
		var obj=$(".cont-list .finlist_title li");
		if(active=='buyingList'){
		
		$(obj.get(0)).addClass("active").siblings().removeClass();
		$($(".cont-list .finlist_hide ol").get(0)).addClass("rent").siblings().removeClass();
		
		
		}else if(active=='owningList'){
		$(obj.get(1)).addClass("active").siblings().removeClass();
		$($(".cont-list .finlist_hide ol").get(1)).addClass("rent").siblings().removeClass();
		
		}else if(active=='outList'){
		$(obj.get(3)).addClass("active").siblings().removeClass();
		$($(".cont-list .finlist_hide ol").get(3)).addClass("rent").siblings().removeClass();
		
		}else if(active=='successList'){
		$(obj.get(2)).addClass("active").siblings().removeClass();
		$($(".cont-list .finlist_hide ol").get(2)).addClass("rent").siblings().removeClass();
		
		}else if(active=='failureList'){
		$(obj.get(4)).addClass("active").siblings().removeClass();
		$($(".cont-list .finlist_hide ol").get(4)).addClass("rent").siblings().removeClass();
		
		}else{
		$(obj.get(0)).addClass("active").siblings().removeClass();
		$($(".cont-list .finlist_hide ol").get(0)).addClass("rent").siblings().removeClass();
		
		}

	});
</script>	
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">


<div class="main">
<input id="str" type="hidden" value="${str}">
<div class="user-cont">
	<!--start: 左侧页面-->
	 	<div class="user-name-nav fl">
			<#--<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">-->
		    <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
		</div>
    <!-- 左侧页面 end -->
    <!-- 右侧主体内容 start  右侧内容宽740px -->   	
	   <div  class="user-cont-fr fr">
	   		<div class="user-cont-right">
	   			<div class="head-num">
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-one fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">累计赚取收益(元)</p>
	   						<p class="middle"><em>30,000</em>元</p>
	   					</div>
	   				</div>
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-two fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">累计投资(笔)</p>
	   						<p class="middle"><em>10</em>笔</p>
	   					</div>
	   				</div>
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-three fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">累计投资金额(元)</p>
	   						<p class="middle"><em>20,000.000</em>元</p>
	   					</div>
	   				</div>
	   			</div>
	   			<h2 class="big-tit">我的还款查询</h2>
		    	<div class="cont-list">
			       <ul class="finlist_title">
			          <li ><a href="${base}/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?Q_state_SN_EQ=1&show=buyingList" class="<#if show=="buyingList">bg1<#else>bg2</#if>">购买中</a></li>
			          <li ><a href="${base}/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?Q_state_SN_EG=2&Q_state_SN_LE=7&show=owningList" class="<#if show=="owningList">bg1<#else>bg2</#if>">持有中</a></li>
			          <li ><a href="${base}/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?Q_state_SN_GE=10&show=successList" class="<#if show=="successList">bg1<#else>bg2</#if>">已完成</a></li>
			          <li ><a href="${base}/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?Q_state_SN_GE=8&show=outList" class="<#if show=="outList">bg1<#else>bg2</#if>">已退出</a></li>
			          <li ><a href="${base}/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?Q_state_SN_EQ=-2&show=failureList" class="<#if show=="failureList">bg1<#else>bg2</#if>">已失败</a></li>
			          
			       </ul>
			       <div class="finlist_hide">
			         <ol  class="rent">
			           <div class="tab-list">
			            <div class="worm-tips">
			            	 <span class="icon1">购买中</span>
			    	   </div>
			                <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                    <tr>
			                      <th class="top-line left-line" align="center" width="15%">计划名称   </th>
			                      <th class="top-line" align="center"  width="10%"> 加入金额  </th>
			                       <th class="top-line" align="center"  width="10%">承诺最低收益 </th>
			                    </tr>
			                    <#list pager.list as list>
			                    <tr class="ff">
			                      <td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			                        <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.plManageMoneyPlan.mmplanId}" target="_blank" title="${list.bidProName}(年化收益 :${list.plManageMoneyPlan.yeaRate}%--${list.plManageMoneyPlan.maxYearRate}"><span class="tit">${list.plManageMoneyPlan.mmName}</span><div class="normal gray" style="text-indent:20px;">(年化收益 :${list.plManageMoneyPlan.yeaRate}%)--${list.plManageMoneyPlan.maxYearRate})</div></a>
			                      </td>
			                        <td align="right" style="padding-right:5px"><#if list.buyMoney==0>0.00<#else><#if list.buyMoney lt 1000>${list.buyMoney}<#else>${list.buyMoney?string(',###.00')}</#if></#if>元</td>
			                          <td align="right" style="padding-right:5px"><#if list.promisIncomeSum==0>0.00<#else><#if list.promisIncomeSum lt 1000>${list.promisIncomeSum}<#else>${list.promisIncomeSum?string(',###.00')}</#if></#if>元</td>
			         
			                    </#list>
			                    <tr>
			              	</table>
			              	<#import "/WEB-INF/template/common/pager.ftl" as p>
			         		<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?Q_state_SN_EQ=1&show=buyingList"  />
			            </div>
			       </ol>
			       	
			       	<ol>
			       	<div class="tab-list">
			       	 <div class="worm-tips">
			            	 <span class="icon1">持有中</span>
			    	   </div>
			                <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                    <tr>
			                      <th class="top-line left-line" align="center" width="25%">计划名称   </th>
			                      <th class="top-line" align="center"  width="10%"> 加入金额  </th>
			                       <th class="top-line" align="center"  width="10%">承诺最低收益 </th>
			                        <th class="top-line" align="center"  width="12%">当前已实现收益 </th>
			                         <th class="top-line" align="center"  width="10%">锁定截至日期 </th>
			                         <th class="top-line" align="center"  width="10%">到期日 </th>
			                         <th class="top-line" align="center"  >操作 </th>
			                    </tr>
			                    <#list pager.list as list>
			                    <tr class="ff">
			                       <td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			                        <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.plManageMoneyPlan.mmplanId}" target="_blank" title="${list.bidProName}(年化收益 :${list.plManageMoneyPlan.yeaRate}%--${list.plManageMoneyPlan.maxYearRate}"><span class="tit">${list.plManageMoneyPlan.mmName}</span><div class="normal gray" style="text-indent:20px;">(年化收益 :${list.plManageMoneyPlan.yeaRate}%--${list.plManageMoneyPlan.maxYearRate}%)</div></a>
			                      </td>
			                       <td align="right" style="padding-right:5px"><#if list.buyMoney==0>0.00<#else><#if list.buyMoney lt 1000>${list.buyMoney}<#else>${list.buyMoney?string(',###.00')}</#if></#if>元</td>
			                       <td align="right" style="padding-right:5px"><#if list.promisIncomeSum==0>0.00<#else><#if list.promisIncomeSum lt 1000>${list.promisIncomeSum}<#else>${list.promisIncomeSum?string(',###.00')}</#if></#if>元</td>
			                       <td align="right" style="padding-right:5px"><#if list.currentGetedMoney==0>0.00<#else><#if list.currentGetedMoney lt 1000>${list.currentGetedMoney}<#else>${list.currentGetedMoney?string(',###.00')}</#if></#if>元</td>
			                        <td align="center">${list.plManageMoneyPlan.lockingEndDate}</td>
			                        <td align="center">${list.endinInterestTime}</td>
			                     <td align="center" >
			                       <span style="display:inline-block;float:right;">
			                       <a class="btn1" href="javascript:void(0)" class="btn2"  onClick="clickAssigninterest(${list.orderId})">回款计划</a>
			                        <a class="btn1" href="javascript:void(0)" class="btn2"  onClick="clickorMatchingDetail(${list.orderId})">债权</a>
			                        <a class="btn1" target="_blank" href="${base}/creditFlow/financingAgency/buyInfoContractPlManageMoneyPlan.do?orderId=${list.orderId}" class="btn2"  >合同</a>
			                      <#if list.state==7> <a  href="javascript:void(0)" class="btn1"> 提取支取审核中</a> <#else><#--<a  href="javascript:void(0)" class="btn1"  onClick="clickearlyout(${list.orderId},'${list.plManageMoneyPlan.lockingEndDate}')">提前退出</a>--></#if>
			                      <span class="last"></span></span></td>
			                       <span class="last"></span></span></td>
			
			                    </tr>
			                    </#list>
			              	</table>
			              	<#import "/WEB-INF/template/common/pager.ftl" as p>
			         		<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?Q_state_SN_GE=2&Q_state_SN_LE=7&show=owningList"  />
			            </div>
			       </ol>
			       
			       
			       <ol>
			     <div class="tab-list">
			      <div class="worm-tips">
			            	 <span class="icon1">已完成</span>
			    	   </div>
			                <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                    <tr>
			                      <th class="top-line left-line" align="center" width="25%">计划名称   </th>
			                      <th class="top-line" align="center"  width="10%"> 加入金额  </th>
			                       <th class="top-line" align="center"  width="10%">承诺最低收益 </th>
			                        <th class="top-line" align="center"  width="12%">当前已实现收益 </th>
			                         <th class="top-line" align="center"  width="10%">退出日期</th>
			                         <th class="top-line" align="center"  width="10%">到期日 </th>
			                         <th class="top-line" align="center"  >操作 </th>
			                    </tr>
			                     <#list pager.list as list>
			                    <tr class="ff">
			                          <td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			                      <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.plManageMoneyPlan.mmplanId}" target="_blank" title="${list.plManageMoneyPlan.mmName}(年化收益 :${list.plManageMoneyPlan.yeaRate}%--${list.plManageMoneyPlan.maxYearRate}"><span class="tit">${list.plManageMoneyPlan.mmName}</span><div class="normal gray" style="text-indent:20px;">(年化收益 :${list.plManageMoneyPlan.yeaRate}%--${list.plManageMoneyPlan.maxYearRate}%)</div></a>
			                      </td>
			                       <td align="right" style="padding-right:5px"><#if list.buyMoney==0>0.00<#else><#if list.buyMoney lt 1000>${list.buyMoney}<#else>${list.buyMoney?string(',###.00')}</#if></#if>元</td>
			                       <td align="right" style="padding-right:5px"><#if list.promisIncomeSum==0>0.00<#else><#if list.promisIncomeSum lt 1000>${list.promisIncomeSum}<#else>${list.promisIncomeSum?string(',###.00')}</#if></#if>元</td>
			                       <td align="right" style="padding-right:5px"><#if list.currentGetedMoney==0>0.00<#else><#if list.currentGetedMoney lt 1000>${list.currentGetedMoney}<#else>${list.currentGetedMoney?string(',###.00')}</#if></#if>元</td>
			                         <td align="center">${list.earlierOutDate}</td>
			                        <td align="center">${list.endinInterestTime}</td>
			                     <td align="center" >
			                       <a class="btn1" href="javascript:void(0)" class="btn2"  onClick="clickAssigninterest(${list.orderId})">回款计划</a>
			                      <a href="javascript:void(0)" class="btn2"  onClick="clickorMatchingDetail(${list.orderId})">债权</a>
			                         <a class="btn1" target="_blank" href="${base}/creditFlow/financingAgency/buyInfoContractPlManageMoneyPlan.do?orderId=${list.orderId}" class="btn2"  >合同</a>
			                      </td>
			
			                    </tr>
			                    </#list>
			              	</table>
			              		<#import "/WEB-INF/template/common/pager.ftl" as p>
			         		<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?Q_state_SN_GE=10&show=successList"  />
			            </div>
			       </ol>
			       
			       
			       
			       
				<ol>
			     <div class="tab-list">
			      <div class="worm-tips">
			            	 <span class="icon1">已退出</span>
			    	   </div>
			                <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                    <tr>
			                      <th class="top-line left-line" align="center" width="25%">计划名称   </th>
			                      <th class="top-line" align="center"  width="10%"> 加入金额  </th>
			                       <th class="top-line" align="center"  width="10%">承诺最低收益 </th>
			                        <th class="top-line" align="center"  width="12%">当前已实现收益 </th>
			                         <th class="top-line" align="center"  width="10%">退出日期</th>
			                         <th class="top-line" align="center"  width="10%">到期日 </th>
			                         <th class="top-line" align="center"  >操作 </th>
			                    </tr>
			                     <#list pager.list as list>
			                    <tr class="ff">
			                          <td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			                      <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.plManageMoneyPlan.mmplanId}" target="_blank" title="${list.plManageMoneyPlan.mmName}(年化收益 :${list.plManageMoneyPlan.yeaRate}%--${list.plManageMoneyPlan.maxYearRate}"><span class="tit">${list.plManageMoneyPlan.mmName}</span><div class="normal gray" style="text-indent:20px;">(年化收益 :${list.plManageMoneyPlan.yeaRate}%--${list.plManageMoneyPlan.maxYearRate}%)</div></a>
			                      </td>
			                       <td align="right" style="padding-right:5px"><#if list.buyMoney==0>0.00<#else><#if list.buyMoney lt 1000>${list.buyMoney}<#else>${list.buyMoney?string(',###.00')}</#if></#if>元</td>
			                       <td align="right" style="padding-right:5px"><#if list.promisIncomeSum==0>0.00<#else><#if list.promisIncomeSum lt 1000>${list.promisIncomeSum}<#else>${list.promisIncomeSum?string(',###.00')}</#if></#if>元</td>
			                       <td align="right" style="padding-right:5px"><#if list.currentGetedMoney==0>0.00<#else><#if list.currentGetedMoney lt 1000>${list.currentGetedMoney}<#else>${list.currentGetedMoney?string(',###.00')}</#if></#if>元</td>
			                         <td align="center">${list.earlierOutDate}</td>
			                        <td align="center">${list.endinInterestTime}</td>
			                     <td align="center" >
			                      <a href="javascript:void(0)" class="btn2"  onClick="clickorMatchingDetail(${list.orderId})">债权</a>
			                         <a class="btn1" target="_blank" href="${base}/creditFlow/financingAgency/buyInfoContractPlManageMoneyPlan.do?orderId=${list.orderId}" class="btn2"  >合同</a>
			                      </td>
			
			                    </tr>
			                    </#list>
			              	</table>
			              		<#import "/WEB-INF/template/common/pager.ftl" as p>
			         		<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?Q_state_SN_GE=8&show=outList"  />
			            </div>
			       </ol>
			     
			     
			     
			     
			     <ol>
			     <div class="tab-list">
			      <div class="worm-tips">
			            	 <span class="icon1">已失败</span>
			    	   </div>
			                <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                    <tr>
			                      <th class="top-line left-line" align="center" width="25%">计划名称   </th>
			                      <th class="top-line" align="center"  width="10%"> 加入金额  </th>
			                       <th class="top-line" align="center"  width="10%">承诺最低收益 </th>
			                        <th class="top-line" align="center"  width="12%">当前已实现收益 </th>
			                         <th class="top-line" align="center"  width="10%">退出日期</th>
			                         <th class="top-line" align="center"  width="10%">到期日 </th>
			                         <th class="top-line" align="center"  >操作 </th>
			                    </tr>
			                     <#list pager.list as list>
			                    <tr class="ff">
			                          <td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			                      <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.plManageMoneyPlan.mmplanId}" target="_blank" title="${list.plManageMoneyPlan.mmName}(年化收益 :${list.plManageMoneyPlan.yeaRate}%--${list.plManageMoneyPlan.maxYearRate}"><span class="tit">${list.plManageMoneyPlan.mmName}</span><div class="normal gray" style="text-indent:20px;">(年化收益 :${list.plManageMoneyPlan.yeaRate}%--${list.plManageMoneyPlan.maxYearRate}%)</div></a>
			                      </td>
			                       <td align="right" style="padding-right:5px"><#if list.buyMoney==0>0.00<#else><#if list.buyMoney lt 1000>${list.buyMoney}<#else>${list.buyMoney?string(',###.00')}</#if></#if>元</td>
			                       <td align="right" style="padding-right:5px"><#if list.promisIncomeSum==0>0.00<#else><#if list.promisIncomeSum lt 1000>${list.promisIncomeSum}<#else>${list.promisIncomeSum?string(',###.00')}</#if></#if>元</td>
			                       <td align="right" style="padding-right:5px"><#if list.currentGetedMoney==0>0.00<#else><#if list.currentGetedMoney lt 1000>${list.currentGetedMoney}<#else>${list.currentGetedMoney?string(',###.00')}</#if></#if>元</td>
			                         <td align="center">${list.earlierOutDate}</td>
			                        <td align="center">${list.endinInterestTime}</td>
			                     <td align="center" >
			                      <a href="javascript:void(0)" class="btn2"  onClick="clickorMatchingDetail(${list.orderId})">债权</a>
			                      </td>
			
			                    </tr>
			                    </#list>
			              	</table>
			              		<#import "/WEB-INF/template/common/pager.ftl" as p>
			         		<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?Q_state_SN_GE=3&show=failureList"  />
			            </div>
			       </ol>
			     </div>  
  			</div>
		</div>
	</div>
</div>
</div>						
<!--end: Container -->



      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>