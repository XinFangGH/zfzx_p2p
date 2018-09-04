<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>${systemConfig.metaTitle} - 账户提现</title>
    <meta name="description" content="${systemConfig.metaTitle} - 账户提现,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 账户提现,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/detailsFunds.js"></script>
<script type="text/javascript" src="${base}/js/user/withdraw.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript">var m1="个人中心", m2="账户总览", m3="账户提现";</script>
<script type="text/javascript">
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200 });		
	});
</script>

</head>
<body >
<div>
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">

<div class="main">
	<div class="user-cont">
		 <div class="user-name-nav fl">
			 <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
		</div>
        <!--  左侧三级导航结束 end-->
    <!-- 左侧页面 end -->
    <!--<div class="ucvline"></div>-->
    	<div  class="user-cont-fr fr">
	   		<div class="user-cont-right">
	        	<h2 class="big-tit">账户提现</h2>
	        	<div class="account-info">
	            	<!--此处放置内容-->
	            	
	            	<#include "/WEB-INF/template/common/loan/userGetCashBySelect.ftl">
	            	
					<!--放置内容结束-->		
				</div>		
			</div>
		</div>

</div>		
			<!--end: main -->
</div>

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	
	<div id="signup">
	<div id="signup-ct">
		<div id="signup-header">
        	<div style="float:left; width:330px; height:40px; padding:10px 0px 0px 30px"><span  class="large blue">我要投标</span></div>
            <div id="lean_overlay_close" style="float:right;  padding:20px 0px 0px 0px;width:60px; height:20px; text-decoration:underline; cursor:pointer;"  class="normal blue">关闭</div>
		</div>
		<div style="overflow:hidden;">
        <div style="float:left; width:300px; height:150px; margin:30px 0px 0px 30px;">
        <span id="bidLoad"></span>
        <!--提示信息开始-->
        	<span id="msgWin" style="display:none">
        	<ul>
				  <li>
				    	<span class="big black">提示信息：</span>
				  </li>
				  <li>
				    <span id="bidMsg"></span>
				  </li>
			 	  <li>
			 	  	<input class="buttonorange button" type="submit" onClick="javascript:window.location.reload()"  value="&nbsp;&nbsp;&nbsp;关闭窗口 &raquo;&nbsp;&nbsp;" style="height:30px;"></input>
			 	  </li>
			 	  </ul>
        	</span>
        	<!--提示信息结束-->
				<span id="bidForm">
				<ul>
				  <li>
				    	<span class="big black">请输入投标金额：</span>
				  </li>
				  <li>
				    <input class="colorful" id="signMoney" class="good_input" name="" type="text" />元
				  </li>
			 	  <li>
			 	  	<input class="buttonorange button" type="submit" onClick="bidHandler()"  value="&nbsp;&nbsp;&nbsp;确定投资 &raquo;&nbsp;&nbsp;" style="height:30px;"></input>
			 	  </li>
			 	  </ul>
			 	  </span>
					
        </div>
    	<div style="float:left; height:200px; margin:10px 0px 0px 0px; width:1px; border:0px; background-color:#e0e0e0;"></div>
    	
        <div style="float:left; width:300px; margin:20px 0px 0px 20px;">
        	<ul>
        		<li>剩余投标金额：<span id="afterMoney"></span></li>
        		<li>年化利率：20%</li>
        		<li>账户可用金额：<span id="myMoney"></span>&nbsp;&nbsp;<a href="/hurong_p2p_v3.5.1/financePurchase/rechargeFinancePurchase.do" target="_self"><span class="blue">立刻充值</span></a></li>
        		<li>投标说明：我们的风控体系。。。。。</li>
        	</ul>        
        </div>
        </div>
	</div>
</div> 
	
	
	</body>
</html>