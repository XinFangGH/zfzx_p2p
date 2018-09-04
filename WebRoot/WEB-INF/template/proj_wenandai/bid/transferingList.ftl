<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 债权交易</title>
    <meta name="description" content="${systemConfig.metaTitle} - 债权交易,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 债权交易,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

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
		          <li ><a href="${base}/creditFlow/financingAgency/canTransferingListPlBidSale.do" >可交易债权</a></li>
		          <li class="active"><a href="${base}/creditFlow/financingAgency/transferingListPlBidSale.do" >交易中债权</a></li>
		          <li ><a href="${base}/creditFlow/financingAgency/transferedListPlBidSale.do" >已卖出债权</a></li>
		          <li ><a href="${base}/creditFlow/financingAgency/buyedListPlBidSale.do" >已购买债权</a></li>
		          <li ><a href="${base}/creditFlow/financingAgency/closeedListPlBidSale.do" >已关闭交易</a></li>
		       </ul>
		       <div class="finlist_hide">
		       	<div class="tab-list">
		       	 <div class="worm-tips">
		            	 <span class="icon1">交易中债权</span>
		    	   </div>
		                <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                    <tr>
		                      <th class="top-line left-line" align="center" width="30%">债权名称   </th>
		                      <th class="top-line" align="center"  width="12%">出让本金</th>
		                      <th class="top-line" align="center"  width="10%"> 折让金  </th>
		                       <th class="top-line" align="center"  width="10%">折让率 </th>
		                        <th class="top-line" align="center"  width="16%">挂牌时间  </th>
		                         <th class="top-line" align="center"  >操作 </th>
		                    </tr>
		                    <#list transferingListpager.list as list>
		                    <tr >
		                      <td align="center" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
		                     <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidPlanID}" target="_blank" title="${list.bidProName}(到期日:${list.intentDate};年化利率:${list.yearAccrualRate}%)"><span class="tit">${list.bidProName}</span><div class="normal gray" style="text-indent:20px;">(到期日:${list.intentDate};年化利率 :${list.yearAccrualRate}%)</div></a>
		                      </td>
		                      
		                      <td align="center" ><#if list.saleMoney==0>0.00<#else><#if list.saleMoney lt 1000>${list.saleMoney}<#else>${list.saleMoney?string(',###.00')}</#if></#if>元</td>
		                      <td align="center" ><#if list.changeMoneyType=0> +<#else>-</#if><#if list.changeMoney==0>0.00<#else><#if list.changeMoney lt 1000>${list.changeMoney}<#else>${list.changeMoney?string(',###.00')}</#if></#if>元</td>
		                      <td  align="center"><#if list.changeMoneyType=0> +<#else>-</#if>${list.changeMoneyRate}‰</td>
		                     <td align="center">${list.saleStartTime?string("yyyy-MM-dd HH:mm:ss")}</td>
		                     <td align="center" >
		                      <span >
		                       <#if list.saleStatus==3>
		                           <a href="javascript:void(0);"   class="btn1">正在交易</a>
		                        <#else>
		                         <a  href="${base}/creditFlow/financingAgency/updatePlBidSale.do?id=${list.id}" class="btn1">取消交易</a>
		                        </#if>  
		                   
		                      <a  href="javascript:void(0);" onClick="clickOrStartTransferFormsee(${list.bidInfoID},${list.yearAccrualRate},'${list.intentDate}','${list.startDate}','${list.id}')" class="btn2">交易结算清单</a></span></td>
		
		                    </tr>
		                    </#list>
		              	</table>
		              	<#import "/WEB-INF/template/common/pager.ftl" as p>
		         		<@p.pager pager = transferingListpager baseUrl = "/creditFlow/financingAgency/transferingListPlBidSale.do?show=transferingList"  />
		            </div>
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