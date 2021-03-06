<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 账户充值</title>
    <meta name="description" content="${systemConfig.metaTitle} - 账户充值,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 账户充值,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>


<script type="text/javascript" src="${base}/js/user/recharge.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript">var m1="个人中心", m2="账户总览", m3="账户充值";</script>
<style type="text/css">

	.span8 {
		width: 770px;
		margin-bottom: 20px;
		display: block;
		overflow: hidden;
	}
	
	[class*="span"] {
		float: left;
		margin-left: 10px;
	}
	
	.next_tab_content {
		margin-bottom: 20px;
		display: block;
		overflow: hidden;
		width: 770px;
	}
	
	.instant_recharge_one {
		border: 1px solid #ededed;
		background: #fff;
		margin-bottom: 10px;
		margin-top: 15px;
	}
	
	.instant_recharge_one div{
		background: #f8f8f8;
		font-size: 15px;
		color: #333;
		padding: 7px 7px 7px 15px;
	}
	
	
	.instant_recharge_one table {
		width: 597px;
		margin: 0 auto;
		margin-top: 30px;
		margin-bottom: 30px;
		max-width: 100%;
		border-collapse: collapse;
		border-spacing: 0;
		background-color: transparent;
		border-spacing: 2px;
		border-color: gray;
	}
	tr {
		display: table-row;
		vertical-align: inherit;
		border-color: inherit;
	}
	.instant_recharge_one table td {
		height: 30px;
	}
	
	tbody {
		display: table-row-group;
		vertical-align: middle;
		border-color: inherit;
	}
	
	
	.instant_recharge_one .huilv {
		color: #666;
		font-size: 12px;
		margin: 0 0 0 45px;
	}
	i {
		font-style: normal;
	}
	
	.instant_recharge_one table {
		width: 597px;
		margin: 0 auto;
		margin-top: 30px;
		margin-bottom: 30px;
	}
	
	tr {
		display: table-row;
		vertical-align: inherit;
		border-color: inherit;
	}

	.instant_recharge_one table span {
		width: 218px;
		height: 86px;
		display: block;
		background: url(../theme/${systemConfig.theme}/images/zhifu.jpg) no-repeat;
		border: 1px solid #CCC;
		-moz-border-radius: 6px;
		border-radius: 6px;
	}


	
	
</style>

</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<div class="main">
	<div class="user-cont">
	<!--start: 左侧页面-->
	 	<div class="user-name-nav fl">
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
		</div>
        <!-- 左侧三级导航结束 end-->
	 	<div  class="user-cont-fr fr">
	   		<div class="user-cont-right">
	        	<h2 class="big-tit">账户充值</h2>
		        <div class="account-info">
		            <!--此处放置内容-->
					<#include "/WEB-INF/template/common/loan/userRecharge.ftl">
					<!--放置内容结束-->
				</div>	
			</div>	
		</div>
	</div>
</div>
<!--end: main -->
      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">


	
	<!--充值弹框-->
	 <#include "/WEB-INF/template/common/user/rechargeBox.ftl">
	
	

<!--充值弹框End-->
	</body>
</html>