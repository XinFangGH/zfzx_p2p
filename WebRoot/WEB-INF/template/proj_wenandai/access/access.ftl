<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${systemConfig.metaTitle} - 用户评估</title>
        <meta name="description" content="${systemConfig.metaTitle} - 用户评估,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
        <meta name="keywords" content="${systemConfig.metaTitle} - 用户评估,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

		<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
  		<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
		<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/funds.css?subversion='1.0'" />
		<script type="text/javascript" src="${base}/js/access/access.js"></script>
	</head>
	<body>
		<input type="hidden" id="bidId" value=${bidId}>
		<input type="hidden" id="bidType" value=${bidType}>
			<div style="width:100%;background:#eff3f3;overflow:hidden;min-height:427px; ">
				<!--one-->
				<div class="wrap mywrap">
				    <div class="module05" id="page-succ01">
				        <div class="modcon">
				            <div class="submit-main">
				                <s class="icon-warn01"></s>
				                <h3 class="ftx-warn">推荐您完成投资风险等级评估</h3>
				                <div class="mt10 middle">为了帮助您控制投资风险，根据国家相关法律要求，交易前需要对您的风险承受能力进行评价</div>
				                <div class="pt30">
				                    <a onClick="toAccess()" class="btn-c1 btn-h2 mr10">立即去评测</a>
				                    <a href="${base}/user/saveAccessBpCustMember.do?bidId=${bidId}&bidType=${bidType}" class="btn-c1 btn-h2 ml30">默认稳健型</a>
				                </div>
				            </div>
				        </div>
				    </div>
				</div>
			</div>
		<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	</body>
</html>