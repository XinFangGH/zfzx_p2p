<form id="bindMobileForm" action="${base}/user/bindPhoneBpCustMember.do" method="post">
	<div class="common-tip" style="display:none;"></div>
	<ul>
		<li  style="height:40px">
			<label  style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">手机号码</label>
			<input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
			<input type="text" class="colorful"  id="telphone" name="telphone" placeholder="请输入手机号码" maxlength="11"/>
			<a id="getVerifySms" class="getcode m-l_20 disabled" href="javascript:void(0);">获取验证码</a>
			<span   id ="spanPhone"></span>
		</li>
		<li   style="height:40px">
			<label  style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">短信验证码</label>
			<input type="text"  id="verify_sms" class="colorful"  name="verify_sms" placeholder="请输入短信验证码"/>
		</li>
		<!--<li   style="height:40px">
			<label  style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">交易密码</label>
			<input type="password" class="colorful"  id="paymentCode" name="paymentCode" placeholder="请输入交易密码" />
		</li>-->
		<li  style="height:40px">
			<div tabindex="4" style="width:80px; height:30px;line-height:30px; float:left; margin:8px 0px 0px 137px; font-size:16px;"
			 id="bindMobile" class="buttonorange">确认</div>
		</li>
	</ul>
</form>