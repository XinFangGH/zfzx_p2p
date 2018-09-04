<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

 
<title>${systemConfig.metaTitle} - 第三方支付</title>
    <meta name="description" content="${systemConfig.metaTitle} - 第三方支付,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 第三方支付,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="个人中心",m2="账户设置",m3="个人资料";</script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript" src="${base}/js/user/editUserInfo.js?version=1638120"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/authentication.js"></script>
</head>
<body>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">

<div class="main">
	<div class="user-cont">
	<!--start: 左侧页面-->
	 	<div class="user-name-nav fl">
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
		</div>
    <!-- 左侧页面 end -->
    <!-- 右侧主体内容 start  右侧内容宽740px -->   	
	   <div  class="user-cont-fr fr">
	   		<div class="user-cont-right">
	   			<h2 class="big-tit">第三方支付</h2>
			   	<div class="cont-list contents" >
			        <div class="user-infor" style="margin:0 auto;padding:0;border-top:2px solid #ef665e;" >
	            	<!--此处放置内容-->
	            		<ul style="padding:0;">
		            		<li style="background:#f6f6f6;margin:0; padding:20px 10px;">
		            			<span style="width:150px;">支付账户：</span>
		            			<span style="color:#fc4c4e;">${bpCustMember.getThirdPayFlagId()!}</span>
		            		</li>
		            		<li>
		            			<span style="width:150px;">账户总额：</span>
		            			<span>${commonResponse.getBalance()!}</span>
		            		</li>
				    		<li>
					    		<span style="width:150px;">账户可用余额：</span>
					    		<span>${commonResponse.getAvailableAmount()!}</span></li>
				    		<li>
				    			<span style="width:150px;">账户冻结金额：</span>
				    			<span>${commonResponse.getFreezeAmount()!}</span>
				    		</li>
				    		<li>
				    			<span style="width:150px;">支付银行卡号：</span>
				    			<span>${commonResponse.getBankCode()!}</span>
				    		</li>
				    		<li>
				    			<span style="width:150px;">支付银行：</span>
				    			<span>${commonResponse.getBankName()!}</span>
				    		</li>
						</ul>					
					<!--此处放置内容 结束-->	
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
