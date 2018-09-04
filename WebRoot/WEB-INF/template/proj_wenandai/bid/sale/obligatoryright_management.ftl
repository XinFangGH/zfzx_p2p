<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 债权交易4</title>
    <meta name="description" content="${systemConfig.metaTitle} - 债权交易4,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 债权交易4,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript">var m1="个人中心",m2="债权交易",m3="我的投资";</script>
<script type="text/javascript">
$(function(){
	$(".content .finlist_title li").click(function(){
		$(this).addClass("active").siblings().removeClass();
		$(".content .finlist_hide ol").eq($(this).index()).addClass("rent").siblings().removeClass();
	});
	
	
	
	if($("#str").val()=='pass'){
		
		
 	}
	
});
$(document).ready(function(){
var active="${show}";
var obj=$(".content .finlist_title li");
if(active=='canTransferingList'){

$(obj.get(0)).addClass("active").siblings().removeClass();
$($(".content .finlist_hide ol").get(0)).addClass("rent").siblings().removeClass();


}else if(active=='transferingList'){
$(obj.get(1)).addClass("active").siblings().removeClass();
$($(".content .finlist_hide ol").get(1)).addClass("rent").siblings().removeClass();

}else if(active=='transferedList'){
$(obj.get(2)).addClass("active").siblings().removeClass();
$($(".content .finlist_hide ol").get(2)).addClass("rent").siblings().removeClass();

}else if(active=='buyedList'){
$(obj.get(3)).addClass("active").siblings().removeClass();
$($(".content .finlist_hide ol").get(3)).addClass("rent").siblings().removeClass();

}else if(active=='closeedList'){
$(obj.get(4)).addClass("active").siblings().removeClass();
$($(".content .finlist_hide ol").get(4)).addClass("rent").siblings().removeClass();

}else{
$(obj.get(0)).addClass("active").siblings().removeClass();
$($(".content .finlist_hide ol").get(0)).addClass("rent").siblings().removeClass();

}



});
</script>	
<style type="text/css">
dd {
display: block;
-webkit-margin-start: 40px;
}


dl {
display: block;
-webkit-margin-before: 1em;
-webkit-margin-after: 1em;
-webkit-margin-start: 0px;
-webkit-margin-end: 0px;
}

.query-btn {
margin-left: 10px;
color: #fff!important;
padding: 6px;
cursor: pointer;
border-radius: 3px;
text-decoration: none;
background: #e74649;
background: -moz-linear-gradient(top, #e74649 0%, #e03336 100%);
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#e74649), color-stop(100%,#e03336));
background: -webkit-linear-gradient(top, #e74649 0%,#e03336 100%);
background: -o-linear-gradient(top, #e74649 0%,#e03336 100%);
background: -ms-linear-gradient(top, #e74649 0%,#e03336 100%);
background: linear-gradient(to bottom, #e74649 0%,#e03336 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e74649', endColorstr='#e03336',GradientType=0 );
}


</style>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">


<div class="main-box">
<input id="str" type="hidden" value="${str}">
<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
    	<!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
        <!-- 左侧三级导航结束 end-->

    <!-- 左侧页面 end -->
       <!-- <div style="float:left; height:700px; padding:10px 0px; width:1px; border:0px; background-color:#e0e0e0;"></div>-->
    <!-- 右侧主体内容 start  右侧内容宽740px -->
    <div class="main-cont">
    	<div class="content" style="padding:0px;">
       <ul class="finlist_title">
          <li >可交易债权1</li>
          <li >交易中债权</li>
          <li >已卖出债权</li>
          <li >已购买债权</li>
          <li >已关闭交易</li>
       </ul>
       <div class="finlist_hide">
         <ol  class="rent">
           <div class="tab-list">
            <div class="worm-tips2">
            	 <span class="icon">可转让交易</span>
    	   </div>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <th align="center" > 债权名称 </th>
                     
                      <th align="center">出让本金</th>
                      <th align="center" >操作</th>
                    </tr>
                    <#list canTransferingListpager.list as list>
                    <tr >
                      <td align="center" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
                      <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidPlanID}" target="_blank" title="${list.bidProName}(到期日:${list.intentDate};年化利率 :${list.yearAccrualRate}%)"><span class="tit">${list.bidProName}</span><div class="normal gray" style="text-indent:20px;">(${list.orderNo}--到期日:${list.intentDate};年化利率 :${list.yearAccrualRate}%)</div></a>
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
                      <span ><a href="javascript:void(0);"  onClick="clickOrStartTransferForm(${list.bidInfoID},${list.yearAccrualRate},'${list.intentDate}','${list.startDate}')" class="btn1">挂牌交易</a>
                       <a href="javascript:void(0);" onClick="clickFinancing(${list.bidPlanID},'${list.orderNo}','Financial')" class="btn1">回款计划</a>
                      </td>
         
                    </#list>
                    <tr>
              	</table>
              	<#import "/WEB-INF/template/common/pager.ftl" as p>
         		<@p.pager pager = canTransferingListpager baseUrl = "/creditFlow/financingAgency/obligatoryrightTransferPlBidSale.do?show=canTransferingList"  />
            </div>
       </ol>
       	
       	<ol>
       	<div class="tab-list">
       	 <div class="worm-tips2">
            	 <span class="icon">交易中债权</span>
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
                    <tr class="ff">
                      <td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
                     <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidPlanID}" target="_blank" title="${list.bidProName}(到期日:${list.intentDate};年化利率:${list.yearAccrualRate}%)"><span class="tit">${list.bidProName}</span><div class="normal gray" style="text-indent:20px;">(到期日:${list.intentDate};年化利率 :${list.yearAccrualRate}%)</div></a>
                      </td>
                      
                        <td align="right" style="padding-right:5px"><#if list.saleMoney==0>0.00<#else><#if list.saleMoney lt 1000>${list.saleMoney}<#else>${list.saleMoney?string(',###.00')}</#if></#if>元</td>
                      <td align="right" style="padding-right:5px"><#if list.changeMoneyType=0> +<#else>-</#if><#if list.changeMoney==0>0.00<#else><#if list.changeMoney lt 1000>${list.changeMoney}<#else>${list.changeMoney?string(',###.00')}</#if></#if>元</td>
                      <td  align="center"><#if list.changeMoneyType=0> +<#else>-</#if>${list.changeMoneyRate}‰</td>
                     <td align="center">${list.saleStartTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                     <td align="center" >
                      <span style="display:inline-block;float:right;"><a  href="${base}/creditFlow/financingAgency/updatePlBidSale.do?id=${list.id}&saleStatus=3" class="btn1">取消交易</a>
                      <a  href="javascript:void(0);" onClick="clickOrStartTransferFormsee(${list.bidInfoID},${list.yearAccrualRate},'${list.intentDate}','${list.startDate}','${list.id}')" class="btn2">交易结算清单</a><span class="last"></span></span></td>

                    </tr>
                    </#list>
              	</table>
              	<#import "/WEB-INF/template/common/pager.ftl" as p>
         		<@p.pager pager = transferingListpager baseUrl = "/creditFlow/financingAgency/obligatoryrightTransferPlBidSale.do?show=transferingList"  />
            </div>
       </ol>
	<ol>
     <div class="tab-list">
      <div class="worm-tips2">
            	 <span class="icon">已卖出交易</span>
    	   </div>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <th class="top-line left-line" align="center" width="24%">债权名称   </th>
                      <th class="top-line" align="center"  width="12%">出让本金</th>
                      <th class="top-line" align="center"  width="10%"> 折让金  </th>
                       <th class="top-line" align="center"  width="8%">折让率 </th>
                         <th class="top-line" align="center"  width="12%">结算金额 </th>
                        <th class="top-line" align="center"  width="16%">交易时间  </th>
                         <th class="top-line" align="center"  >操作 </th>
                    </tr>
                     <#list transferedListpager.list as list>
                    <tr class="ff">
                      <td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
                     <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidPlanID}" target="_blank" title="${list.bidProName}(到期日:${list.intentDate};年化利率 :${list.yearAccrualRate}%)"><span class="tit">${list.bidProName}</span><div class="normal gray" style="text-indent:20px;">(到期日:${list.intentDate};年化利率 :${list.yearAccrualRate}%)</div></a>
                      </td>
                        <td align="right" style="padding-right:5px"><#if list.saleMoney==0>0.00<#else><#if list.saleMoney lt 1000>${list.saleMoney}<#else>${list.saleMoney?string(',###.00')}</#if></#if>元</td>
                      <td align="right" style="padding-right:5px"><#if list.changeMoneyType=0> +<#else>-</#if><#if list.changeMoney==0>0.00<#else><#if list.changeMoney lt 1000>${list.changeMoney}<#else>${list.changeMoney?string(',###.00')}</#if></#if>元</td>
                       <td align="center"><#if list.changeMoneyType=0> +<#else>-</#if>${list.changeMoneyRate}‰</td>
                      <td align="right" style="padding-right:5px"><#if list.sumMoney==0>0.00<#else><#if list.sumMoney lt 1000>${list.sumMoney}<#else>${list.sumMoney?string(',###.00')}</#if></#if>元</td>
                     <td align="center">${list.saleSuccessTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                     <td align="center" >
                      <span style="display:inline-block;float:right;"><a href="javascript:void(0);" onClick="clickOrStartTransferFormsee(${list.bidInfoID},${list.yearAccrualRate},'${list.intentDate}','${list.startDate}','${list.id}')" class="btn1">交易结算清单</a>
                      <a href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=invest&contractUrl=${list.url}" class="btn2">转让合同</a><span class="last"></span></span></td>

                    </tr>
                    </#list>
              	</table>
              		<#import "/WEB-INF/template/common/pager.ftl" as p>
         		<@p.pager pager = transferedListpager baseUrl = "/creditFlow/financingAgency/obligatoryrightTransferPlBidSale.do?show=transferedList"  />
            </div>
       </ol>
       <ol>
     <div class="tab-list">
      <div class="worm-tips2">
            	 <span class="icon">已购买债权</span>
    	   </div>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                       <th class="top-line left-line" align="center" width="24%">债权名称   </th>
                      <th class="top-line" align="center"  width="12%">出让本金</th>
                      <th class="top-line" align="center"  width="10%"> 折让金  </th>
                       <th class="top-line" align="center"  width="8%">折让率 </th>
                         <th class="top-line" align="center"  width="12%">结算金额 </th>
                        <th class="top-line" align="center"  width="16%">交易时间  </th>
                         <th class="top-line" align="center" >操作 </th>
                    </tr>
                     <#list buyedListpager.list as list>
                    <tr class="ff">
                      <td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
                     <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidPlanID}" target="_blank" title="${list.bidProName}(到期日:${list.intentDate};年化利率 :${list.yearAccrualRate}%)"><span class="tit">${list.bidProName}</span><div class="normal gray" style="text-indent:20px;">(到期日:${list.intentDate};年化利率 :${list.yearAccrualRate}%)</div></a>
                      </td>
                        <td align="right" style="padding-right:5px"><#if list.saleMoney==0>0.00<#else><#if list.saleMoney lt 1000>${list.saleMoney}<#else>${list.saleMoney?string(',###.00')}</#if></#if>元</td>
                      <td align="right" style="padding-right:5px"><#if list.changeMoneyType=0> +<#else>-</#if><#if list.changeMoney==0>0.00<#else><#if list.changeMoney lt 1000>${list.changeMoney}<#else>${list.changeMoney?string(',###.00')}</#if></#if>元</td>
                        <td align="center"><#if list.changeMoneyType=0> +<#else>-</#if>${list.changeMoneyRate}‰</td>
                      <td align="right" style="padding-right:5px"><#if list.sumMoney==0>0.00<#else><#if list.sumMoney lt 1000>${list.sumMoney}<#else>${list.sumMoney?string(',###.00')}</#if></#if>元</td>
                     <td align="center">${list.saleSuccessTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                     <td align="center" >
                      <span style="display:inline-block;float:right;"><a href="javascript:void(0);" onClick="clickOrStartTransferFormsee(${list.bidInfoID},${list.yearAccrualRate},'${list.intentDate}','${list.startDate}','${list.id}')" class="btn1">交易结算清单</a>
                      <a href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=invest&contractUrl=${list.url}" class="btn2">转让合同</a><span class="last"></span></span></td>

                    </tr>
                    </#list>
              	</table>
              	<#import "/WEB-INF/template/common/pager.ftl" as p>
         		<@p.pager pager = buyedListpager baseUrl = "/creditFlow/financingAgency/obligatoryrightTransferPlBidSale.do?show=buyedList"  />
            </div>
       </ol>
	<ol>
       	<div class="tab-list">
       	 <div class="worm-tips2">
            	 <span class="icon">已关闭的交易</span>
    	   </div>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <th class="top-line left-line" align="center" width="30%">债权名称   </th>
                      <th class="top-line" align="center"  width="13%">出让本金</th>
                      <th class="top-line" align="center"  width="10%"> 折让金  </th>
                       <th class="top-line" align="center"  width="10%">折让率 </th>
                        <th class="top-line" align="center"  >关闭时间  </th>
                    </tr>
                    <#list closeedListpager.list as list>
                    <tr class="ff">
                       <td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
                     <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidPlanID}" target="_blank" title="${list.bidProName}(到期日:${list.intentDate};年化利率 :${list.yearAccrualRate}%)"><span class="tit">${list.bidProName}</span><div class="normal gray" style="text-indent:20px;">(到期日:${list.intentDate};年化利率 :${list.yearAccrualRate}%)</div></a>
                      </td>
                      <td align="right" style="padding-right:5px"><#if list.saleMoney==0>0.00<#else><#if list.saleMoney lt 1000>${list.saleMoney}<#else>${list.saleMoney?string(',###.00')}</#if></#if>元</td>
                      <td align="right" style="padding-right:5px"><#if list.changeMoneyType=0> +<#else>-</#if><#if list.changeMoney==0>0.00<#else><#if list.changeMoney lt 1000>${list.changeMoney}<#else>${list.changeMoney?string(',###.00')}</#if></#if>元</td>
                     <td align="center"><#if list.changeMoneyType=0> +<#else>-</#if>${list.changeMoneyRate}‰</td>
                     <td align="center">${list.saleCloseTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                    </tr>
                    </#list>
                    
              	</table>
              		<#import "/WEB-INF/template/common/pager.ftl" as p>
         		<@p.pager pager = closeedListpager baseUrl = "/creditFlow/financingAgency/obligatoryrightTransferPlBidSale.do?show=closeedList"  />
            </div>
       </ol>
     </div>  
  </div>
</div>
</div>						
<!--end: Container -->



      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>