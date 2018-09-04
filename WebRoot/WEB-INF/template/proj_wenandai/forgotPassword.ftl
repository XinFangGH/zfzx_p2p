<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 忘记密码</title>
    <meta name="description" content="${systemConfig.metaTitle} - 忘记密码,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 忘记密码,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/bindPhone.js"></script>
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/user/forgotPassword.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>

<script type="text/javascript">var m1="";var m2="";var m3="";</script>
<script type="text/javascript">		
window.onload=function() {
	$(".find_password div").each(function(index) { //带参数遍历各个选项卡
		$(this).click(function() { //注册每个选卡的单击事件
			//显示选项卡对应的内容并隐藏未被选中的内容
			$(".find_password1 ul").show();
			if(index==0){
				$("#regType").val("email");
				$(".find_password1 li:eq(0)").show();
				$(".find_password1 li:eq(1)").hide();
			}else{
				$("#regType").val("telphone");
				$(".find_password1 li:eq(1)").show();
				$(".find_password1 li:eq(0)").hide();
			}
		});
	});
};
</script>
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
	/*	padding: 5px 5px 5px 25px;*/
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
<div style="background:#EFF3F4;width:100%;">
  <div class="main"  >
  
	<form id="registerForm1" action="${base}/user/checkUerBpCustMember.do" method="post">
	 <@s.token/>
	<input type="hidden" name="regType" id="regType" value="telphone"/>
    <div style="width:999px; height:600px; margin:10px auto;background:#fff;">
      <div style="float:left; width:999px; height:400px; margin:0px 0px 0px 0px;">
        <!--<div style="width:970px; height:55px; margin:10px auto 0px auto;"><img src="${base}/theme/${systemConfig.theme}/images/loginstep1.jpg" /></div>-->
        <div style="width:999px; height:55px; padding:0px 0px 0px 0px;">
          <!--<div style="width:899px; float:left; height:35px; padding:20px 0px 0px 40px;"><span class="blue big">忘记密码</span></div>-->
        </div>
        <!-- <div style="width:910px; height:1px; padding:0px 40px;">
          <hr class="splitline" />
        </div> -->
        
      <div class="find_password" style="width:600px; height:50px;margin:0 auto;padding-top:50px;">
         <div id="emailClick" class="visited" style="float:left;margin-right:130px;"> <span id="myemail"  style="font-size:18px; padding:6px 18px;cursor: pointer;  border:1px solid #0097C4;" name="tabSwitch">使用绑定邮箱找回密码</span></div>
        <div id="telphoneClick" style="float:left; "><span id="mytelphone" class="buttonoblue2"  style="font-size:18px; padding:6px 18px;cursor: pointer;border:1px solid #22AD38" name="tabSwitch">使用绑定手机找回密码</span></div>
        
     </div>
        <div style="clear:both;"></div>
       <div class="find_password1" style="width:500px;margin:0 auto;">
        <ul style="padding:40px 0 10px 0;">
			<li  style="height:40px;display:none;" name="mail" >
				<div style="width:100px; height:27px; float:left; text-align:right; padding-top:3px"><span class="black middle">电子邮箱：</span></div>
				<input id="email" name="email" type="text"  tabindex="4" class="colorful" placeholder="请输入邮箱"  size="30"  />

                <div class="common-tip" style="display:none;"></div>
			</li>
			<li  style="height:40px;" name="phone">
				<div style="width:100px; height:27px; float:left; text-align:right; padding-top:3px"><span class="black middle">手机号码：</span></div>
				<input type="text" class="colorful" tabindex="5" id="telphone" name="telphone" placeholder="请输入手机号码" maxlength="11" size="30"/>
                <div class="common-tip" style="display:none;"></div>
			</li>
			<li  style="height:40px">
				<div style="width:100px; height:27px; float:left; text-align:right; padding-top:3px"><span class="black middle">验证码：</span></div>
          <div style="width:80px; height:30px; float:left;">
            <input id="checkCode" name="checkCode" type="text" style="width:60px;" tabindex="5" size="4" maxlength="4" class="colorful" placeholder="输入验证码" onkeyup="validatCheckCode(this)"/>
            
          </div>      
          <div style="width:60px; height:29px; float:left; padding-top:1px"><img id="loginCode" class="verify-code"  style="vertical-align: middle; margin:0;height:28px;width:60px; cursor:pointer;" onclick="javascript:refresh(document.getElementById('loginCode'));"  src="${base}/getCode.do"></div>
          <div style="width:100px; height:24px; float:left; padding-top:3px">&nbsp;<span class="blue normal" style="cursor:pointer;" onclick="javascript:refresh(document.getElementById('loginCode'));">看不清，换一张</span></div>
            <span class="orange middle" id ="spancheckCode" style="display:inline-block;float:left;padding-top:3px;"></span>
			</li>
			<li  style="height:40px;padding:10px 0 0 100px;">
				 <span class="buttonoblue1" style="font-size:16px; padding:5px 20px;cursor: pointer;" id="registerBtn1"  tabindex="8">提交</span>
			</li>
		</ul>

        </div>
        <div style="width:500px; height:30px;margin:0 auto;padding:60px 0 0 120px;"><span>若您无法使用上述方法找回，请联系客服${servicePhone}</span></div>
    </div>
  </div>
</form>
</div>
</div>
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	</body>
</html>

