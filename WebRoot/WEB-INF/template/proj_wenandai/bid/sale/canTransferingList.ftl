<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 债权交易</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript">var m1="个人中心",m2="债权交易",m3="我的投资";</script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<div class="main">
<input id="str" type="hidden" value="${str}">
	<div class="user-cont">
   		<!--左侧三级导航条 -->
	   <div class="user-name-nav fl">
	       <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
	   </div>
	<!--end:左侧三级导航条 -->
	<div class="user-cont-fr fr">
    	<div class="user-cont-right">
    		<div class="head-num">
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-one fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">可交易债权总额(元)</p>
	   						<p class="middle"><em>30,000</em>元</p>
	   					</div>
	   				</div>
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-two fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">已交易债权总额(元)</p>
	   						<p class="middle"><em>300000</em>笔</p>
	   					</div>
	   				</div>
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-three fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">成功交易笔数(笔)</p>
	   						<p class="middle"><em>3</em>笔</p>
	   					</div>
	   				</div>
	   			</div>
	   		<h2 class="big-tit">债权交易</h2>	
	    	<div class="cont-list">
		       <ul class="finlist_title">
		          <li class="active"><a href="${base}/creditFlow/financingAgency/canTransferingListPlBidSale.do" >可交易债权</a></li>
		          <li ><a href="${base}/creditFlow/financingAgency/transferingListPlBidSale.do" >交易中债权</a></li>
		          <li ><a href="${base}/creditFlow/financingAgency/transferedListPlBidSale.do" >已卖出债权</a></li>
		          <li ><a href="${base}/creditFlow/financingAgency/buyedListPlBidSale.do" >已购买债权</a></li>
		          <li ><a href="${base}/creditFlow/financingAgency/closeedListPlBidSale.do" >已关闭交易</a></li>
		       </ul>
		       <div class="finlist_hide">
			       <ol  class="rent">
				       <div class="tab-list">
				           <div class="worm-tips">
				               <span class="icon1">可转让交易</span>
				    	   </div>
			                <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
			                    <tr>
			                      <th align="center"> 债权名称 </th>
			                     
			                      <th align="center" >出让本金</th>
			                      <th align="center" >操作</th>
			                    </tr>
			                    <#list canTransferingListpager.list as list>
			                    <tr>
			                      <td align="center">
			                      <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidPlanID}" target="_blank" title="${list.bidProName}(到期日:${list.intentDate};年化利率 :${list.yearAccrualRate}%)"><span class="tit">${list.bidProName}</span><div class="normal gray" style="text-indent:20px;">(到期日:${list.intentDate};年化利率 :${list.yearAccrualRate}%)</div></a>
			                      </td>
			                        <td align="center" style="padding-right:5px"><#if list.saleMoney==0>0.00<#else><#if list.saleMoney lt 1000>${list.saleMoney}<#else>${list.saleMoney?string(',###.00')}</#if></#if>元</td>
			                      <!--  <td align="center"><#if list.accrualtype=="sameprincipalandInterest">等额本息
			                      <#elseif list.accrualtype=="singleInterest">按月付息，到期还本
			                      <#elseif list.accrualtype=="sameprincipal">等额本金
			                      <#elseif list.accrualtype=="otherMothod">其他方式
			                      <#elseif list.accrualtype=="ontTimeAccrual">一次性还本付息</#if></td>
			                      <td align="center">${(list.nextPayDate)}</td>
			                      <td align="center" >
			                      -->
			                      <td align="center" >
			                      <span style="display:inline-block;">
			                      <#if list.id==null ||list.id ==0>
			                      <a href="javascript:void(0);"  onClick="clickOrStartTransferForm(${list.bidInfoID},${list.yearAccrualRate},'${list.intentDate}','${list.startDate}')" class="btn1">挂牌交易</a>
			                      <#else>
			                      <a href="javascript:void(0);"   class="btn1">正在挂牌</a>
			                      </#if>  
			                       <a href="javascript:void(0);" onClick="clickFinancing(${list.bidPlanID},'${list.orderNo}','Financing')" class="btn1">回款计划</a>
			                      </td>
			                    </#list>
			                    <tr>
			              	</table>
			              	<#import "/WEB-INF/template/common/pager.ftl" as p>
			         		<@p.pager pager = canTransferingListpager baseUrl = "/creditFlow/financingAgency/canTransferingListPlBidSale.do?show=canTransferingList"  />
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