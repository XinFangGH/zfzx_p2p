<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 工具箱</title>
    <meta name="description" content="${systemConfig.metaTitle} - 工具箱,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 工具箱,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="",m2="",m3="工具箱";m4="";</script>

</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/registerCenter/layout/topall.ftl">

<div class="main">
	<div class="djsafe_hide">
		<ul class="govbox">
			<li>
	           <div class="govpart">
	             <a href="${base}/html/incomeSingle.do"><p>收益计算器</p></a>
	           </div>
	        </li>
	        <li>
	           <div class="govpart">
	             <a href="${base}/html/cpiSingle.do"><p>CPI 跟踪器</p></a>
	           </div>
	        </li>
	        <li>
	           <div class="govpart">
	             <a href="${base}/html/loanSingle.do"><p>车贷计算器</p></a>
	           </div>
	        </li>
	        <li>
	           <div class="govpart">
	             <a href="${base}/html/housLoanSingle.do"><p>房贷计算器</p></a>
	           </div>
	        </li>
		</ul>
	</div>
	
</div>
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>