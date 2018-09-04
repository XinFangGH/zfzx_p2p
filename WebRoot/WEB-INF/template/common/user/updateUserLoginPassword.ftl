<form id="changeLoginPasswordForm" action="${base}/user/updatePasswordBpCustMember.do" method="post" class="m-t_20">
	<div class="common-tip" style="display:none;"></div>
	<ul>
		<li   style="height:40px">
			<label  style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">旧密码</label>
		<input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
		<input type="password" class="colorful" id="oldpassword" name="oldpassword"  placeholder="请输入旧密码"/>
		</li>
		<li   style="height:40px">
			<label  style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">新密码</label>
			<input type="password"  class="colorful"  id="password" name="password" placeholder="新密码" maxlength="16"/>
		</li>
		<li   style="height:40px">
			<label  style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">确认新密码</label>
			<input type="password" class="colorful"   id="repeat_password" placeholder="确认新密码" maxlength="16"/>
		</li>
		<li  style="height:40px">
			<div tabindex="4" style="width:80px; height:30px; line-height:30px; float:left; margin:8px 0px 0px 138px; font-size:16px;"
			 id="changeLoginPassword"  class="buttonorange">确认</div>
		</li>
	</ul>
</form>
