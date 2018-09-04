<h3 style="background-image: url(${base}/theme/${systemConfig.theme}/images/icon_form_title.png);background-repeat: no-repeat;color: #d30c13;font-size: 14px;height: 36px;line-height: 36px;padding-left: 36px;">
	<em class="f-l">温馨提示</em>
</h3>
<ul>
	<li>1、密码必须为6至16个字母或数字！</li>
	<li>2、新密码前后输入要一致，密码中不要包含空格！</li>
	<li>3、交易密码不能与登录密码一致！</li>
</ul>
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<h3 class="title">请填写一下交易密码</h3>
<form id="setTradePasswordForm" action="${base}/user/updatePayPasswordBpCustMember.do" method="post" class="m-t_20">
	<div class="common-tip" style="display:none; margin-left: 20px;"></div>
	<input type="hidden" id="path" name="path" value="${base}"/>
	<input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
	<ul  class="certname">
		<li   style="height:40px">
			<label>交易密码</label>
			<input type="password" class="colorful" id="paymentCode" name="paymentCode" placeholder="交易密码" maxlength="16" onblur="chkPass(this)"/><span id="password_span" class="gray normal">请输入6-16个字母或数字</span>
		</li>
		<li   style="height:40px">
			<label>确认密码</label>
			<input type="password" class="colorful" id="repeat_paymentCode" placeholder="确认密码" onblur="chkRepPasss(this)" maxlength="16"/><span id="repeat_password_span" class="gray middle">请重复输入上面的密码</span> <span id="tip-repeat-password"></span>
		</li>
		<li>
			<div tabindex="4" style="width:80px; height:30px; line-height:30px; float:left; margin:8px 0px 0px 65px; font-size:16px;"
			 id="setTradePassword"  class="buttonorange">确认修改</div>
		</li>

	</ul>
</form>