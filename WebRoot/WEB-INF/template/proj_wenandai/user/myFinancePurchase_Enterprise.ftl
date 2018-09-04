<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 企业理财管理</title>
    <meta name="description" content="${systemConfig.metaTitle} - 企业理财管理,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 企业理财管理,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript">var m1="个人中心",m2="理财管理",m3="我的投资";</script>
<script type="text/javascript" src="${base}/js/bidplanpager.js"></script>	
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
          <li class="active">回款中</li>
          <li>投标中</li>
          <li>已结束</li>
          <li>投标失败</li>
       </ul>
       <div class="finlist_hide">
         <ol  class="rent">
           <div class="tab-list">
            <div class="worm-tips2">
            	 <span class="icon">处于还款期中的产品</span>
    	   </div>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="suc">
                    <tr>
                      <th class="top-line left-line" align="center" width="25%">产品</th>
                      <th class="top-line" align="center" width="15%">时间</th>
                      <th class="top-line" align="center"  width="15%">金额</th>
                      <th class="top-line" align="center"  width="10%">期限</th>
                      <th class="top-line" align="center"  width="13%">年化利率</th>
                      <th class="top-line right-line" align="center">待回款</th>
                    </tr>
                    <#list listPlanbacking as list>
                    <tr class="ff">
                      <td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
                      <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" title="${list.bidProName}"><span class="tit">${list.bidProName}</span></a>
                      <span class="small">利率${list.interestRate}%期限${list.loanLife}</span></td>
                      <td align="center">${list.publishSingeTime!}</td>
                      <td align="center"><#if list.afterMoneyTotal==null>0.00<#else><#if list.afterMoneyTotal lt 1000>${list.afterMoneyTotal}<#else>${list.afterMoneyTotal?string(',###.00')}</#if></#if></td>
                      <td align="center">${(list.loanLife)!}</td>
                      <td align="center">${(list.interestRate)!}% </td>
                      <td align="center" ><span style="display:inline-block;float:left;padding-left:20px;"><#if list.notMoneyTotal==0>0.00<#else><#if list.notMoneyTotal lt 1000>${list.notMoneyTotal}<#else>${list.notMoneyTotal?string(',###.00')}</#if></#if></span>
                      <#--clickPlan(${list.bidId},'PlanbackingOne') -->
                      <span style="display:inline-block;float:right;"><a href="javascript:void(0);" onClick="clickFinancing(${list.bidId},'${list.investOrderNo}','Financing')" class="btn1">回款计划</a>
                      <a href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=invest&contractUrl=${list.url}" class="btn2">合同</a><span class="last"></span></span></td>
                    </tr> 
                    </#list>
                    <tr>
              	</table>
            <ul class="pager">
				<li class="prePage prePage1" id="pali">
				</li>
		    	<li class="pagNum">
					<span id="pns">1</span>
				</li>
				<li class="nextPage nextPage1" id="padow">
				<#if Planbackingcount<=10>
					<span>下一页</span>
				<#else>
					<a href="javascript:void(0);" onclick="backnext('Planbacking','pns','ff','suc','pali','padow')">下一页</a>
				</#if>	
				</li>
			</ul>
            </div>
       </ol>
       	
       	<ol>
       	<div class="tab-list">
       	 <div class="worm-tips2">
            	 <span class="icon">投标成功，还未进入还款期的产品</span>
    	   </div>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="sucs">
                    <tr>
                      <th class="top-line left-line" align="center" >产品</th>
                      <th class="top-line" align="center" width="15%">时间</th>
                      <th class="top-line" align="center"  width="15%">金额</th>
                      <th class="top-line" align="center"  width="10%">期限</th>
                      <th class="top-line" align="center"  width="15%">年化利率</th>
                      <th class="top-line right-line" align="center">待回款</th>
                    </tr>
                    <#list listPlanSuccess as list>
                    <tr class="fff">
                      <td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
                     <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" title="${list.bidProName}"><span class="tit">${list.bidProName}</span></a>
                      <span class="small">利率${list.interestRate}%   期限${list.loanLife}</span></td>
                      <td align="center">${list.publishSingeTime!}</td>
                      <td align="center"><#if list.afterMoneyTotal==null>0.00<#else><#if list.afterMoneyTotal lt 1000>${list.afterMoneyTotal}<#else>${list.afterMoneyTotal?string(',###.00')}</#if></#if></td>
                      <td align="center">${(list.loanLife)!}</td>
                     <td align="center">${(list.interestRate)!}% </td>
                     <td align="center" ><span style="display:inline-block;float:left;padding-left:20px;"><#if list.notMoneyTotal==0>0.00<#else><#if list.notMoneyTotal lt 1000>${list.notMoneyTotal}<#else>${list.notMoneyTotal?string(',###.00')}</#if></#if></span>
                      <span style="display:inline-block;float:right;"><a href="javascript:void(0);" onClick="clickFinancing(${list.bidId},'${list.investOrderNo}','Financing')" class="btn1">回款计划</a>
                      <a href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=invest&contractUrl=${list.url}" class="btn2">合同</a><span class="last"></span></span></td>
                    </tr>
                    </#list>
              	</table>
              	<ul class="pager">
				<li class="prePage prePage1" id="palis">
				</li>
		    	<li class="pagNum">
					<span id="pnss">1</span>
				</li>
				<li class="nextPage nextPage1" id="padows">
				<#if successcount<=10>
					<span>下一页</span>
				<#else>
					<a href="javascript:void(0);" onclick="backnext('success','pnss','fff','sucs','palis','padows')">下一页</a>
				</#if>
				</li>
			</ul>
            </div>
       </ol>
	<ol>
     <div class="tab-list">
      <div class="worm-tips2">
            	 <span class="icon">已经完成全部还款的产品</span>
    	   </div>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="sucss">
                    <tr>
                      <th class="top-line left-line" align="center" >产品</th>
                      <th class="top-line" align="center" width="15%">时间</th>
                      <th class="top-line" align="center"  width="15%">金额</th>
                      <th class="top-line" align="center"  width="10%">期限</th>
                      <th class="top-line" align="center"  width="15%">年化利率</th>
                      <th class="top-line right-line" align="center">待回款</th>
                    </tr>
                     <#list listPlanback as list>
                    <tr class="ffff">
                      <td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
                      <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" title="${list.bidProName}"><span class="tit">${list.bidProName}</span></a>
                      <span class="small">利率${(list.interestRate)!}%   期限 ${(list.loanLife)!}</span></td>
                      <td align="center">${list.publishSingeTime!}</td>
                      <td align="center"><#if list.afterMoneyTotal==null>0.00<#else><#if list.afterMoneyTotal lt 1000>${list.afterMoneyTotal}<#else>${list.afterMoneyTotal?string(',###.00')}</#if></#if></td>
                      <td align="center">${(list.loanLife)!}</td>
                      <td align="center">${(list.interestRate)!}% </td>
                     <td align="center" ><span style="display:inline-block;float:left;padding-left:20px;"><#if list.notMoneyTotal==0>0.00<#else><#if list.notMoneyTotal lt 1000>${list.notMoneyTotal}<#else>${list.notMoneyTotal?string(',###.00')}</#if></#if></span>
                      <span style="display:inline-block;float:right;"><a href="javascript:void(0);" onClick="clickFinancing(${list.bidId},'${list.investOrderNo}','Financing')" class="btn1">回款计划</a>
                      <a href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=invest&contractUrl=${list.url}" class="btn2">合同</a><span class="last"></span></span></td>
                    </tr>
                    </#list>
              	</table>
              <ul class="pager">
				<li class="prePage prePage1" id="paliss">
				</li>
		    	<li class="pagNum">
					<span id="pnsss">1</span>
				</li>
				<li class="nextPage nextPage1" id="padowss">
				<#if Planbackcount<=10>
					<span>下一页</span>
				<#else>
					<a href="javascript:void(0);"  onclick="backnext('Planback','pnsss','ffff','sucss','paliss','padowss')">下一页</a>
				</#if>
				</li>
			</ul>
            </div>
       </ol>
       
	<ol>
       	<div class="tab-list">
       	 <div class="worm-tips2">
            	 <span class="icon">投标失败的产品</span>
    	   </div>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="sucsss">
                    <tr>
                      <th class="top-line left-line" align="center" >产品</th>
                      <th class="top-line" align="center" width="15%">时间</th>
                      <th class="top-line" align="center"  width="15%">金额</th>
                      <th class="top-line" align="center"  width="10%">期限</th>
                      <th class="top-line" align="center"  width="15%">年化利率</th>
                      <th class="top-line right-line" align="center">待回款</th>
                    </tr>
                    <#list listPlanFila as list>
                    <tr class="fffff">
                      <td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
                     <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" title="${list.bidProName}"><span class="tit">${list.bidProName}</span></a>
                      <span class="small">利率${(list.interestRate)!}%   期限${(list.loanLife)!}</span></td>
                      <td align="center">${list.publishSingeTime!}</td>
                      <td align="center"><#if list.afterMoneyTotal==null>0.00<#else><#if list.afterMoneyTotal lt 1000>${list.afterMoneyTotal}<#else>${list.afterMoneyTotal?string(',###.00')}</#if></#if></td>
                      <td align="center">${(list.loanLife)!}</td>
                     <td align="center">${(list.interestRate)!}% </td>
                      <td align="center" ><span style="display:inline-block;float:left;padding-left:20px;"><#if list.notMoneyTotal==0>0.00<#else><#if list.notMoneyTotal lt 1000>${list.notMoneyTotal}<#else>${list.notMoneyTotal?string(',###.00')}</#if></#if></span>
                      <span style="display:inline-block;float:right;"><a href="javascript:void(0);" onClick="clickPlan(${list.bidId},'PlanbackingOne')" class="btn1">回款计划</a>
                      <a href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=invest&contractUrl=${list.url}" class="btn2">合同</a><span class="last"></span></span></td>
                    </tr>
                    </#list>
                    
              	</table>
              <!--	<ul class="pager">
				<li class="prePage prePage1" id="palisss">
				</li>
		    	<li class="pagNum">
					<span id="pnssss">1</span>
				</li>
				<li class="nextPage nextPage1" id="padowsss">
					<a href="javascript:void(0);"  onclick="backnext('error','pnssss','fffff','sucsss','palisss','padowsss')">下一页</a>
				</li>
			</ul>-->
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