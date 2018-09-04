<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 忘记密码</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

<script type="text/javascript" src="${base}/js/user/forgotPassword.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>


<script type="text/javascript">var m1="";var m2="";var m3="";</script>

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
</head>
<body >
  <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">

  <div class="main">
  
	<form id="registerForm2" action="${base}/user/checkCodeBpCustMember.do" method="post">
    <div style="width:999px; height:400px; border:1px solid #e0e0e0; margin:10px auto;">
      <div style="float:left; width:999px; height:400px; margin:0px 0px 0px 0px;">
        <!--<div style="width:970px; height:55px; margin:10px auto 0px auto;"><img src="${base}/theme/${systemConfig.theme}/images/loginstep1.jpg" /></div>-->
       <div class="find_password1" style="width:500px;margin:0 auto;">
       <ul style="padding:40px 0 10px 0;">
       <div class="common-tip" style="display:none;"></div>
       <#if regType=="telphone"><div><font color='red'>手机验证码已成功发送,请填写手机验证码</font></div></#if><br>
	   <#if regType=="email"><div><font color='red'>邮箱验证码已成功发送,请填写邮箱验证码</font></div></#if><br>
			<li  style="height:50px;" name="mail" >
				<div style="width:100px; height:27px; float:left; text-align:right; padding-top:3px; float:left; "><span class="black middle"><#if regType??>
					<#if regType=="email">验证邮箱： <input id="email" name="email" type="hidden" tabindex="5" size="4" maxlength="6" class="colorful" value=${typeValue} /></#if>
					<#if regType=="telphone">验证手机号： <input id="telphone" name="telphone" type="hidden" tabindex="5" size="4" maxlength="6" class="colorful" value=${typeValue} /></#if>
					
					</#if></span></div>	
					<div style="width:150px; height:20px; border:1px solid #e0e0e0; background:#f4f4f4; padding:5px 10px; float:left; border-radius:4px;">
					${typeValue}
					</div> 
			</li>
			<li style="height:40px">
				<div style="width:100px; height:27px; float:left; text-align:right; padding-top:3px"><span class="black middle">验证码：</span></div>
		<div style="width:400px; height:30px; float:left;">
            <input id="checkCode" name="checkCode" type="text" tabindex="5" size="8" maxlength="6" class="colorful" placeholder="输入验证码"/>
			<input id="checkCode1" name="checkCode1" type="text" style="display:none"/>
          </div>
          <span class="orange middle" id ="spancheckCode"></span>
			</li>
			<li style="height:40px;padding:10px 0 0 100px;">
				<span class="buttonorange" style="font-size:16px; padding:5px 20px;cursor: pointer;" id="registerBtn2"  tabindex="8">确&nbsp;认</span>
			</li>
		</ul>
        </div>
    </div>
  </div>
</form>

  	<#include "/WEB-INF/template/${systemConfig.theme}/layout/copyright.ftl">
	</body>
</html>