<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 邮箱认证</title>
    <meta name="description" content="${systemConfig.metaTitle} - 邮箱认证,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 邮箱认证,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/bindEmail.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript">var m1="个人账户", m2="账户设置", m3="账户安全";</script>
</head>
<style type="text/css">
	a.getcode.disabled {
		color: #d5d5d5;
		cursor: default;
		background-color: #ededed;
	}
	a.getcode {
		background-color: #f3f3f3;
		border: 1px solid #d5d5d5;
		border-radius: 5px;
		color: #000;
		padding: 4px 19px;
		text-decoration: none;
	}
	
	a.getcode.disabled {
		color: #d5d5d5;
		cursor: default;
		background-color: #ededed;
	}
	.common-tip {
		border: 1px solid #ff8080;
		padding: 5px 5px 5px 25px;
		font-size: 12px;
		margin-bottom: 10px;
		max-width: 230px;
		min-height: 20px;
		line-height: 20px;
		background-color: #fff2f2;
		background-repeat: no-repeat;
		background-position: 5px 8px;
	}
</style>

<body >
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div class="main">
	<div class="user-cont">
	<!--start: 左侧页面-->
	 	<div class="user-name-nav fl">
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
			 <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">  
		</div>
    	 <div  class="user-cont-fr fr">
		   <div class="user-cont-right">
            	<h2 class="big-tit">邮箱认证</h2>
            	<div class="cont-list" style="padding:20px 0;">
            	<!--此处放置内容-->
            	<form id="bindemailForm" action="${base}/user/bindEmailBpCustMember.do" method="post">
				<div class="common-tip" style="display:none;"></div>
				<ul>
					<li style="height:40px">
						<label  style="float: left;margin-top:8px;width: 80px;text-align: right;margin-right: 15px; font-size:14px;">邮箱</label>
						<input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
						<input type="text" class="colorful1"  id="email" name="email" placeholder="请输入邮箱" value="${bpCustMember.email}"/>
					</li>
					<li  style="height:40px">
						<div tabindex="4" style="width:254px; height:30px; float:left; margin:8px 0px 0px 95px; padding:5px 0px 0px 0px; font-size:16px;"
						 id="bindemail" class="buttonoblue1">确认</div>
					</li>
				</ul>
			</form>
				<!--放置内容结束-->	
			</div>
		</div>
	</div>
</div>
</div>

<!--end: main -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>