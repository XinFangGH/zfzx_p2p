<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${systemConfig.metaTitle} - 用户评估结果</title>
        <meta name="description" content="${systemConfig.metaTitle} - 用户评估结果,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
        <meta name="keywords" content="${systemConfig.metaTitle} - 用户评估结果,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

		<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
		<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
		<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/funds.css?subversion='1.0'" />
		<script type="text/javascript" src="${base}/js/jQuery/jquery-1.8.2-min.js"></script>
		<script type="text/javascript" src="${base}/js/access/access.js"></script>
	</head>
	<body>
		<input value="${bidId}" type="hidden" id="bidId">
		<input value="${bidType}" type="hidden" id="bidType">
		<div style="width:100%;background:#eff3f3;overflow:hidden;min-height:427px;">
			<div class="wrap mywrap">
			    <div class="wanebox">
			        <div class="wanecon">
			            <dl class="hazardbox">
			                <dt>您的投资风险等级为：</dt>
			                <dd>
			                    <h3 class="ftx-03">${bpCustMember.grade}</h3>
			                    <#if from == "1">
				                    <div class="btns">
				                        <a href="${base}/user/safeBpCustMember.do?safe=all" class="btn-c1 btn-h1">返回个人中心</a>
				                    </div>
				                 <#else>   
				                    <div class="btns">
				                        <a href="#" onclick="toBuy()" class="btn-c1 btn-h1">继续购买</a>
				                    </div>
				                 </#if>  
			                    <p class="ftx-04">
			                       	 您可以去：<a onClick="toAccess()" href="#">重新评估风险等级</a>
			                    </p>
			                </dd>
			            </dl>
			        </div>
			        <div class="wanetl"></div>
			        <div class="wanetr"></div>
			        <div class="wanebl"></div>
			        <div class="wanebr"></div>
			    </div>
			</div>
		</div>
		<!-- copyright -->
		<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
		</div>
	</body>
</html>