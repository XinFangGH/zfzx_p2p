<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 手机验证</title>
    <meta name="description" content="${systemConfig.metaTitle} - 手机验证,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 手机验证,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/registerPhoneCode.js"></script>
<script type="text/javascript">var m1="";var m2="";var m3="";</script>
<script type="text/javascript">
$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200 });
});
</script>
</head>
<body >
  <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <div style="background:#EFF3F4;width:100%;">
  <div class="main">
  <!-- ${base}/succRegreg.do-->
  <!--// 修改按钮-->
  <!-- ${base}/succRegreg.do-->
			
	<form id="registerFormPhone" action="${base}/user/bindPhoneBpCustMember.do?toType=success" method="post">
	<input type="hidden" id="token" value="${token}"/> 
	 <input type="hidden" id="id" name="id" value="${id}"/>
    <div style="width:990px; height:700px; background:#fff; margin:10px auto;">
      <div style="float:left; width:999px; height:400px; margin:0px 0px 0px 0px;">
        <!--<div style="width:970px; height:100x; margin:10px auto 0px auto;"><img src="${base}/theme/${systemConfig.theme}/images/loginstep1.jpg" /></div>-->
        <div style="width:999px; height:100px; padding:0px 0px 0px 0px;">
          <div style="width:784px; float:left; height:160px; padding:40px 0px 0px 215px;">
          	<img class="hidden" src="${base}/theme/${systemConfig.theme}/images/reg_pic2.jpg" />
          </div>
         
        </div>
        <span  id ="typeCode" style="display:none"></span>
        <span  id ="typeTelphone" style="display:none"></span>
        
        <div  style="width:650px; height:35px; line-height:35px;margin:130px 0 0 180px; border:1px solid #a8bdc7; background:#d0f0ff; text-align:center;display:none;" >

		</div>
        <div style="width:919px; height:15px; padding:40px 0px 0px 80px;"  style="text-align:center;">
			<div class="common-tip"  style="display:none; text-align:center;margin-left:300px;"></div>
	 	</div>

        <div style="width:731px; height:30px; padding:20px 0px 0px 268px;">
          <!--<div style="width:100px; height:27px; float:left; text-align:right; padding-top:3px"><span class="black middle"><span style="color:red;">*</span>&nbsp;验证码：</span></div>
          <div style="width:80px; height:30px; float:left;">
            <input id="checkCode" name="checkCode" type="text" tabindex="5" size="4" maxlength="4" class="colorful" placeholder="输入验证码" onkeyup="validatCheckCode(this)"/>
            
          </div>
          <span class="orange middle" id ="spancheckCode"></span>
          
          <div style="width:60px; height:29px; float:left; padding-top:1px"><img id="loginCode" class="verify-code"  style="vertical-align: middle; margin:0;height:25px;width:60px; cursor:pointer;" onclick="javascript:refresh(document.getElementById('loginCode'));"  src="${base}/getCode.do"></div>
          <div style="width:100px; height:24px; float:left; padding-top:6px">&nbsp;<span class="blue normal" style="cursor:pointer;" onclick="javascript:refresh(document.getElementById('loginCode'));">看不清，换一张</span></div>
	     -->
	     <br>
			<div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle"><span style="color:red;">*</span>&nbsp;手机号码：</span></div>
				<div style="width:220px; height:30px; float:left;">
				<input type="text" class="colorful1"tabindex="5"  id="telphone" name="telphone" onblur="checkTelphone()" placeholder="请输入手机号码" maxlength="11" size="30"/>
				</div>
		</div>
		
		
		<div style="width:731px; height:30px; padding:30px 0px 0px 268px;margin-top:30px;">
		<div style="width:100px; height:27px; float:left; text-align:right; padding-top:3px"><span class="black middle"><span style="color:red;">*</span>&nbsp;验证码：</span></div>
        <div style="width:100px; height:30px; float:left;">
          <input id="checkCode" name="checkCode" type="text" tabindex="5" size="8" maxlength="4" class="colorful" placeholder="输入验证码" onkeyup="validatCheckCode(this)"/>
          
        </div>
        <span class="orange middle" id ="spancheckCode"></span>
        <div style="width:60px; height:29px; float:left; padding-top:1px"><img id="loginCode" class="verify-code"  style="vertical-align: middle; margin:0;height:25px;width:60px; cursor:pointer;" onclick="javascript:refresh(document.getElementById('loginCode'));"  src="${base}/getCode.do"></div>
        <div style="width:100px; height:24px; float:left; padding-top:6px">&nbsp;<span class="blue normal" style="cursor:pointer;" onclick="javascript:refresh(document.getElementById('loginCode'));">看不清，换一张</span></div>
	     
		</div>
		
		<div style="width:699px; height:60px; padding:50px 0 0 430px; ">
		<a id="go" href="#signup" rel="leanModal" name="signup" class="font-blue"><span class="buttonoblue1" style="font-size:18px; padding:6px 33px;cursor: pointer;" id="nextButton"  tabindex="8">&nbsp;下&nbsp;一&nbsp;步&nbsp;</span></a>
	</div>

	<!-- 
     <div style="width:731px; height:30px; padding:30px 0px 0px 268px;margin-top:30px;">
				<div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle"><span style="color:red;">*</span>&nbsp;短信验证码：</span></div>
				<span style=" float:left;"><input type="text"  id="verify_sms" tabindex="6" class="colorful1"  name="verify_sms" placeholder="请输入短信验证码"  size="30" /></span>
				<p class="normal"style="width:200px; padding:0 0 0 10px;float:left;">如果您在1分钟之内没有收到验证码，请 <span class="normal"style="color:#73b9d7;">返回修改手机号</span> 或 <spanclass="normal" style="color:#73b9d7;"> 获取验证码 </span></p>
        </div>
        <div style="width:699px; height:60px; padding:50px 0 0 430px; ">
			<a href="javascript:void(0)"><span class="buttonoblue1" style="font-size:18px; padding:6px 33px;cursor: pointer;" id="registerBtnPhone"  tabindex="8">验&nbsp;证</span></a>
		</div>
		-->
      </div>
    </div>
  </div>
</form>
<div style="height:4px; border-bottom:1px #f0f0f0 solid; margin:15px auto 0px auto;" title="横线"></div>
<!-- end main --> 
    </div>
    <!--login form end -->
<!--end: Container -->


</div>
<div id="signup" style="height:400px!important;">
<div id="signup-ct">
	<div id="signup-header">
    	<div style="float:left; width:330px; height:40px; padding:10px 0px 0px 30px"><span  class="large blue">填写手机验证码</span></div>
        <div id="lean_overlay_close" style="float:right;  padding:20px 0px 0px 0px;width:60px; height:20px; text-decoration:underline; cursor:pointer;"  class="normal blue">关闭</div>
	</div>
    <div style="float:left; width:650px; height:300px; margin:10px 0px 0px 30px; overflow:auto; line-height:30px;">
    	<span  class="black middle" id="showMsg"></span><br>
    	<br><div>手机号：<span id="myTelphone"></span> <br> </div>
    	<br><span style=" float:left;">验证码：<input type="text"  id="verify_sms" tabindex="6" class="colorful1"  name="verify_sms" placeholder="请输入短信验证码"  size="10" /></span>
    	  
			&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"><span class="buttonoblue1" style="font-size:18px; padding:5px 33px;cursor: pointer;" id="getVerifySms"  tabindex="8">重新获取验证码</span></a>
		
		
		<div style="width:199px; height:60px; padding:50px 0 0 60px; ">
		<a href="javascript:void(0)"><span class="buttonoblue1" style="font-size:18px; padding:6px 33px;cursor: pointer;" id="registerBtnPhone"  tabindex="8">&nbsp;确&nbsp;定&nbsp;</span></a>
		<span style="display:none" id="djs"></span>
	</div>
    	</div>
</div>
</div>
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	</body>
</html>