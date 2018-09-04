<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 登录</title>
<meta name="description" content="${systemConfig.metaTitle} - 登录,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
<meta name="keywords" content="${systemConfig.metaTitle} - 登录,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/login.js?version=20180521"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/fingerprint.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/jquery.md5.js"></script>
<script type="text/javascript" src="${base}/js/user/encode64.js"></script>
<script type="text/javascript">var m1="",m2="",m3="";</script>
<script type="text/javascript">	
$(function(){
	var fingerprint = new Fingerprint().get();
	$("#fingerprint").val(fingerprint);
	});
</script>
    <script type="text/javascript">
        $(function () {
            $("body").css("width", $(document).width() + "px");
            $(window).resize(function () {
                if ($(window).width() > 1200) {
                    $("body").css("width", $(window).width() + "px");
                } else {
                    $("body").css("width", $(document).width() + "px");
                }
            });
        })
    </script>
</head>
<body onkeydown="enterSumbit()">
<!--整体布局-->
<div>
<!-- header --><!-- topbar -->
<#--<#include "/WEB-INF/template/${systemConfig.theme}/layout/topbar.ftl">-->
	<#include "/WEB-INF/template/${systemConfig.theme}/layout/newlogobar.ftl">


<input type="hidden" id="lockNum" value="${lockNum}">
<div class="login_register-con login-page" style="overflow: hidden;">
	<div class="login_register-inner">
        <form id="register-form" action="${base}/tologin.do" method="post">
			<div class="login-inner">
				<h2>用户登录</h2>
					<@s.token/>
					<input type="hidden"  id="fingerprint" name="fingerprint"  value=""  />
	                <div id="errorContainer" class="login-form-error clearfix" style="display:none">
	                </div>
	                <div class="common-tip" style="display:none;margin-left:30px;"></div>
					<ul class="login-form clearfix">
						<li>
	                        <span class="login-form-username icon-login_register icon-ren"></span>
							<input  id="loginname" name="loginname"  type="text"  value="${(exitName)!}" autocomplete="off"  class="input-text-style-3" placeholder="邮箱/手机号/用户名" value="">
						</li>
						<li>
	                        <span class="login-form-password icon-login_register icon-suo"></span>
							<input id="password" name="password" type="password" autocomplete="off" class="input-text-style-3" placeholder="密码">
						</li>
						<li id="vcode" class="rcf-valicode" style="display:none">
							<input id="valicode" name="valicode" type="text" autocomplete="off" class="input-text-style-3" style="width:110px;" placeholder="验证码">
							<span class="vcodeWrapper fl"><img id="loginCode" class="verify-code" title="点击获取验证码" alt="点击获取验证码" width="80" height="46" onclick="javascript:refresh(document.getElementById('loginCode'));" src="${base}/getCode.do" style="cursor:pointer;" ></span>
							<span class="icon-refresh fl" style="width:78px;padding:8px 0 0 10px;">
								<span class="normal fl" style="cursor:pointer;color:#0096c4;" onclick="javascript:refresh(document.getElementById('loginCode'));">看不清，<br>换一张</span>
								<span class="fr" style="padding-top:10px;">
									<img id="successCode" style="display:none" src='${base}/theme/${systemConfig.theme}/images/icon.JPG'>
									<img style="display:none" id="errorCode" src='${base}/theme/${systemConfig.theme}/images/erricon.jpg'>
								</span>
							</span>
						</li>
						
						<li class="login-form-checkbox">
							<span class="fl">
								<label>
									<input name="remember_me" type="checkbox" checked="checked" value="1" class="input-checkbox-style-3 fl">
									<span class="fl middle">记住用户名</span>
								</label>
							</span>
							<span class="fr">
								<a href="${base}/forgotreg.do" class="middle" style="color:#2871b8;">忘记密码？</a>
							</span>
						</li>
						<li style="margin-bottom:25px;">
							<input id="loginBtn" name="loginBtn" type="button"  class="input-submit-style-3" value="登 录"> <#--onkeydown="enterSumbit()" -->
	                        <input type="hidden" name="ret_url" value="">
						</li>
						<li class="login-form-checkbox">
							<span class="fl">
								<#--<a href="/" class="middle">没有账号？</a>-->
							没有账号？
									<a href="${base}/htmlreg.do " style="color:#2871b8;text-decoration: underline;" class="setRegLog middle" _reg_val="header">免费注册</a>
							</span>
						</li>
					</ul>
				</form>
			
		</div>
	</div>
</div>
 <!-- end main --> 
  	<!-- 结束 只需要拿这个说明标签之内的部分 -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/newsitemap.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/log_reg_footer.ftl">
	
	</body>
</html>