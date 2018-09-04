<h3 style="background-image: url(${base}/theme/${systemConfig.theme}/images/icon_form_title.png);background-repeat: no-repeat;color: #d30c13;font-size: 14px;height: 36px;line-height: 36px;padding-left: 36px;">
	<em class="f-l">温馨提示</em>
</h3>
<ul>
	<li>1、密码必须为6至16个字母或数字！</li>
	<li>2、新密码前后输入要一致，密码中不要包含空格！</li>
	<li>3、交易密码不能与登录密码一致！</li>
</ul>
<h3 class="title">请填写获取的短信验证码并点击下一步</h3>
<form id="changeTradePasswordForm1"  action="${base}/user/updatePayPasswordPhoneCheckBpCustMember.do" method="post" class="m-t_20">
	<div class="common-tip" style="display:none; margin-left: 20px;max-width: 270px;"></div>
	<input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
	<ul>
		<li  style="height:40px">
			<label   style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">手机号码</label>
			<input type="hidden" class="txt" id="mobileId" name="mobileId" value="${bpCustMember.telphone}"/>
			<span id="mobile">${bpCustMember.telphone}</span>
			<a class="getcode m-l_20" id="getVerifySms" href="javascript:void(0);">获取验证码</a>
		</li>
		<li  style="height:40px">
			<label   style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">短信验证码</label>
			<input type="text" class="colorful" id="verify_sms" name="verify_sms" maxlength="6" placeholder="请输入短信验证码" onkeydown="if(event.keyCode==13){return false;}"/>
			<!--  -->
		</li>
		<li style="height:40px">
			<div tabindex="4" style="width:80px; height:30px; line-height:30px; float:left; margin:8px 0px 0px 65px;  font-size:16px;"
			 id="changeTradePassword1"  class="buttonorange">下一步</div>
		</li>
	</ul>
</form>