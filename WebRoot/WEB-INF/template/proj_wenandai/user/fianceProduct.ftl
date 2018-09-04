<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 100%本息保障</title>

    <meta name="description" content="${systemConfig.metaTitle} - 100%本息保障,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 100%本息保障,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/style.css" />           
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <div class="wrap_body">
 	<a href="${base}/financeProduct/buyProductPlFinanceProduct.do">
      <div class="banner_2"></div>
     </a>
      <div class="box1_1">
       		<div class="main">
                <dl class="ar_center">
                    <dt>	
                    	<h2>稳健收益</h2>
                    	<#if plFinanceProductRate??>
                    		<p class="big">七日年化收益率(${plFinanceProductRate.intentDate?string("yyyy-MM-dd ")}):${plFinanceProductRate.yearRate}%</p>
                    		<p class="middle">注：货币基金不等同于银行存款，过往业绩不预示其未来表现。市场有风险，投资需谨慎。</p>
                    	<#else>
                    		<p class="big">七日年化收益率(2015-07-26):3.3390%</p>
                    		<p class="middle">注：货币基金不等同于银行存款，过往业绩不预示其未来表现。市场有风险，投资需谨慎。</p>
                    	</#if>
                    	
					</dt>
                    <dd>
                    	<div class="bg_pic">
                    		<div class="sata">
                    		<#if plFinanceProductRate??>
                    			<span><a>10000</a>元</span>
	                    		<span>一天收益：<em>${plFinanceProductRate.thousandsIncome?string(',##0.####')}元</em></span>
                    		<#else>
                    			<span><a>10000</a>元</span>
	                    		<span>一天收益：<em>0.88元</em></span>
                    		</#if>
	                    		
                    		</div>
                    		
                    	</div>
                    	<#if plFinanceProduct??>
                    			<p class="text">现在转入，${plFinanceProduct.accountDay?string("yyyy-MM-dd ")}可显示收益。</p>
                    		<#else>
                    			<p class="text">现在转入，周四可显示收益。</p>
                    		</#if>
					</dd>
                </dl>
            </div>
            <div class="hurong_arrow"></div>
       </div>
       <div class="box1_2">
       		<div class="main">
                <dl class="ar_center">
                    <dt>	
                    	  <img src="${base}/theme/${systemConfig.theme}/images/hurong_bao2.png">
					</dt>
                    <dd style="width:268px;padding-top:30px;">
                    	<h2>随时存取</h2>
                    	<p class="big">建立产品保障专户，可随时存取立即到账，不耽误理财商机。</p>                   	
					</dd>
                </dl>
            </div>
            <div class="hurong_arrow"></div>
       </div>
       <div class="box1_1">
       		<div class="main">
                <dl class="ar_center">
                    <dt style="padding-top:50px;">	
                    	<h2>闲置收益</h2>
                    	<p class="big" style="color:#333;">闲置资金也可以有收益。</p>
					</dt>
                    <dd>
                    	<img src="${base}/theme/${systemConfig.theme}/images/hurong_bao3.png">
					</dd>
                </dl>
            </div>
       </div>
           
 </div>
	
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>