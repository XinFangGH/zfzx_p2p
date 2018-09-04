<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>${systemConfig.metaTitle} - 银行卡列表</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/detailsFunds.js"></script>
<script type="text/javascript" src="${base}/js/user/withdraw.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript">var m1="个人中心", m2="账户总览", m3="银行卡列表";</script>
<script type="text/javascript">
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200 });		
	});
</script>
<style type="text/css">
    .tab-cont .bd table th {background:#e6f4fd}
    .account-info .set-btn {background:#55b14a}
    .account-info .wad_btnhover:hover {background:#55b14a}
</style>
</head>
<body >
<div>
 <!-- header --><!-- navbar -->
 	<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">


<div class="main">
	<div class="user-cont">
		 <div class="user-name-nav fl">
			 <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
		</div>
    	<div  class="user-cont-fr fr">
	   		<div class="user-cont-right">
	   		<h2 class="big-tit">银行卡列表</h2>
            <div class="account-info">
            	<!--此处放置内容-->
            	
            	<#include "/WEB-INF/template/common/loan/bankList.ftl">
            	
				<!--放置内容结束-->		
			</div>
                <p style="display: inline-block;height:28px;line-height:28px;text-align:center;font-size: 12px;color:666;margin-left: 25px;margin-top: 20px;">
                    <img style="display: inline-block;width: 18px;height:18px;vertical-align: text-bottom;" src="${base}/theme/${systemConfig.theme}/images/reminder.png" alt="">&nbsp;温馨提示：绑定的银行卡必须是您本人银行卡，并且注册时使用的手机号码必须为该卡的银行预留手机号码。</p>
		</div>
	</div>
</div>
</div>

			
<!--end: main -->


      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">


	
	
	</body>
</html>