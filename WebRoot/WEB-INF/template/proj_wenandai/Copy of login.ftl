<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 登录</title>
    <meta name="description" content="${systemConfig.metaTitle} - 登录,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 登录,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/fingerprint.js"></script>
<script type="text/javascript">var m1="",m2="",m3="";</script>
<script type="text/javascript">	
$(function(){
	var fingerprint = new Fingerprint().get();
	$("#fingerprint").val(fingerprint);
	});
</script>
</head>
<body onkeydown="enterSumbit()">
<!--整体布局-->
<div>
<!-- header --><!-- topbar -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topbar.ftl">
<div class="main">
<div style="height:428px; _height:400px;margin:10px auto; background-color:#fff; border:1px solid #ddd;">
	<div style="float:left; width:640px;_width:490px; _height:400px;height:428px; ">
    		<img src="${base}/theme/${systemConfig.theme}/images/login-pic.jpg"/>
    </div>
	<div style="float:right; width:359px; _width:326px; height:428px; margin:0px 0px 0px 0px;">
		  
           <form id="register-form" action="${base}/tologin.do" method="post">
            <@s.token/>
           <input type="hidden"  id="fingerprint" name="fingerprint"  value=""  />
           <div style=" height:15px; padding:30px 0px 0px 50px;">
        		<div class="common-tip" style="display:none;"></div>
         	</div>
          <div style=" height:30px; padding:30px 0px 0px 20px;">
              <div style="width:60px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">账号：</span></div>
              <div style="width:220px; height:30px; float:left;">
              <input type="text"  id="loginname" name="loginname"  tabindex="1"  maxlength="30" size="30" class="colorful1" value="${(exitName)!}" placeholder="请输入账号或者手机号" /></div>
          </div>
          <div style="height:30px; padding:20px 0px 0px 20px;">
              <div style="width:60px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">密码：</span></div>
              <div style="width:220px; height:30px; float:left;">
              <input type="password" id="password" name="password" tabindex="2" maxlength="20" size="30" class="colorful1" placeholder="请输入密码" /></div>

             

          </div>
          <div style=" height:30px; padding:20px 0px 0px 20px;">
          	  <div style="width:60px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">验证码：</span></div>
              <div style="width:60px; height:30px; float:left; margin-right:10px;">
              <input type="text" id="checkCode" name="checkCode" tabindex="3" size="6"style="width:40px;" maxlength="4" class="colorful1" placeholder="输入验证码" onkeyup="validatCheckCode(this)" />
              </div>
               
              <div style="width:60px; height:29px; float:left; padding-top:1px"><img width="60" height="35" id="loginCode" class="verify-code" alt="点击更换" title="点击更换" onclick="javascript:refresh(document.getElementById('loginCode'));"  src="${base}/getCode.do" style="cursor:pointer;" /></div>
              <div style="width:130px; height:30px; float:left; padding-top:6px">&nbsp;<span class="normal" style="cursor:pointer;color:#0096c4;width:110px;" onclick="javascript:refresh(document.getElementById('loginCode'));">看不清,换一张</span><span class="orange middle" style="width:80px;" id ="spancheckCode"></span></div>
          </div> 
          <div style=" height:30px; padding:20px 0px 0px 20px;"> 
           <!--<div  style="width:170px; height:24px; float:left; padding:6px 0 0 60px; font-size:14px;"><span style="padding-top:2px;*+padding-top:0;float:left;">记住用户名</span></div>-->
           <div style="width:170px; height:24px; float:left; padding:6px 0 0 60px; font-size:14px;>
           		<span class="middle" style="color:#0096c4;">&nbsp;&nbsp;<a href="${base}/forgotreg.do" target="_blank">忘记密码？</a></span></div>
          </div>         
          <div style=" height:60px; padding:40px 0 0 130px;"  >
          	<span class="buttonoblue1" style="font-size:18px; padding:6px 18px;" onkeydown="enterSumbit()" id="loginBtn" name="loginBtn">立&nbsp;刻&nbsp;登&nbsp;录</span> 
          	<span style="padding:10px 0; display:block; font-size:14px;">没有账号？<a href="${base}/htmlreg.do " target="_self"><span style="color:#0096c4;">免费注册</a></span></span>
          </div>
           <#--- <div style=" height:60px; padding:10px 0 0 20px; " class="login_partne" >
           <div style="width:430px; height:2px;border-top:1px solid #e2e2e2;margin:0 auto; "></div>      
          	<span style="padding:15px 0 10px 100px;_padding:0px 0 10px 100px; display:block; font-size:14px;float:left;display:inline-block;">您还可以使用合作账号登录</span>
          	<a href="${base}/qqLoginreg.do" class="qq_partne" ></a>
          	<a href="${base}/autoSinareg.do"  class="qq_partne1"></a>
          </div>---->
          </form>
    </div>

</div>  
</div>
<div style="height:4px; border-bottom:1px #f0f0f0 solid; margin:15px auto 0px auto;" title="横线"></div>
<!-- end main --> 
  	<!-- 结束 只需要拿这个说明标签之内的部分 -->
  
      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	
	</body>
</html>