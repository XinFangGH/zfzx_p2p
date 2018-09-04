<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 忘记密码</title>
    <meta name="description" content="${systemConfig.metaTitle} - 忘记密码,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 忘记密码,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript">var m1="";var m2="";var m3="";</script>
<script type="text/javascript">
	$(document).ready(function(){
	    		$("#changeLoginPassword").click(function(){
    			
				$("#changeLoginPasswordForm").ajaxSubmit({
					beforeSubmit:checkForm,
					clearForm:true,
					dataType:"JSON",
					success:function(responseText, statusText){
						
						if (statusText == "success"){
							if (responseText.result == 1){
								location.href = basepath + "htmllogin.do";
							}else{
								showCommonTip(".common-tip",responseText.errMsg);
							}
						}else{
							showCommonTip(".common-tip","登录密码修改失败");
						}
					},
					error:function(){
						showCommonTip(".common-tip","登录密码修改失败");
					}
				});
    		});
	});
	  	function checkForm(){

    		//校验新密码
    		if (isEmpty($("#password").val())){
    			showTip("password","新密码不能为空");
   	          	return false;
    		}

    		if ($("#password").val().length < 6 || $("#password").val().length > 16){
    			showTip("password","新密码长度为6~16个字符");
   	          	return false;
    		}

    		if (!$("#password").val().match(/^[a-zA-Z0-9]+$/)){
    			showTip("password","新密码只能由字母和数字组成");
   	          	return false;
    		}

    		//校验确认新密码
    		if (isEmpty($("#repeat_password").val())){
    			showTip("repeat_password","请确认新密码");
   	          	return false;
    		}

    		if ($("#repeat_password").val() != $("#password").val()){
    			showTip("repeat_password","两次输入密码不一致");
   	          	return false;
    		}
    	

    		return true;
    	}
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

<div class="main" style="height:400px; width:60%;">
  
<form id="changeLoginPasswordForm" action="${base}/user/resetPasswordBpCustMember.do" method="post" class="m-t_20">
	
	
	
    <div style="width:800px; height:380px; border:1px solid #e0e0e0; margin:10px auto;">
      <div style="float:left; width:999px; height:400px; margin:0px 0px 0px 0px;">
        <!--<div style="width:970px; height:55px; margin:10px auto 0px auto;"><img src="${base}/theme/${systemConfig.theme}/images/loginstep1.jpg" /></div>-->
        <div style="width:800px; height:55px; padding:0px 0px 0px 0px;">
          <div style="width:899px; float:left; height:35px; padding:20px 0px 0px 100px;"><span class="blue big">找回密码</span></div>
        </div>
        <div style="width:710px; height:1px; padding:0px 40px;">
          <hr class="splitline" />
        </div>
        <div style="width:919px; height:15px; padding:30px 0px 0px 80px;">
			<div class="common-tip" style="display:none;"></div>
	 	</div>
       
        <div style="width:719px; height:30px; padding:30px 0px 0px 200px;">
	<ul>
		<li style="height:40px">
			<label  style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">新密码</label>
			<input type="password"  class="colorful"  id="password" name="password" onblur="chkPass(this)" placeholder="新密码" maxlength="16"/>
		</li>
		<li   style="height:40px">
			<label  style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">确认密码</label>
			<input type="password" class="colorful"   id="repeat_password"  onblur="chkRepPass(this)" placeholder="确认新密码" maxlength="16"/>
			<input type="hidden"   name="random" value=${findpwd_random_session} ></input>
		</li>
		<li  style="height:40px">
			<div tabindex="4" style="width:80px; height:30px; float:left; margin:8px 0px 0px 92px; padding:5px 0px 0px 0px; font-size:16px;"
			 id="changeLoginPassword"  class="buttonorange">确认</div>
		</li>
	</ul>
</div>
</div>
</form>
       
    </div>
  </div>
  	<#include "/WEB-INF/template/${systemConfig.theme}/layout/copyright.ftl">
</body>
</html>
