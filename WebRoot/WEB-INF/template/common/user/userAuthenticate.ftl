<form id="authenticateForm" action="${base}/user/updateAuthenticateBpCustMember.do" method="post" class="authenticate personal bd">
	<h3 style="background-image: url(${base}/theme/${systemConfig.theme}/images/icon_form_title.png);background-repeat: no-repeat;color: #d30c13;font-size: 14px;height: 36px;line-height: 36px;padding-left: 36px;">
		<em class="f-l">温馨提示</em>
	</h3>
	<p>请认证填写以下内容，一旦通过实名认证将不能修改。</p>
	<p>注意：为保护您的隐私安全，下面的部分栏位的信息在认证通过后会做部分隐藏</p>
	<div class="common-tip" style="display:none; max-width: 280px;"></div>
	<ul  class="certname">
		<li  style="height:40px"><label>用 户 名：</label>${bpCustMember.loginname}</li>
		<input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
		<input type="hidden" id="path" name="path" value="${base}"/>
		<li  style="height:40px">
			<label>真实姓名：</label>
			<input type="text" class="colorful"  id="truename" name="truename"
					value='${bpCustMember.truename}' placeholder="真实姓名" maxlength="25"/>
			<input type="hidden" id="user_real_name" name="user_real_name" value="" maxlength="25"/>
		</li>
		<li  style="height:40px">
			<label>证件类型：</label>
			<select id="cardtype" name="cardtype">
				
				<option value="0">身份证</option>
			</select>
		</li>
		<li  style="height:40px">
			<label>证件号码：</label>
			<input type="text" class="colorful"  id="cardcode" name="cardcode" value="${bpCustMember.cardcode}" placeholder="证件号码" maxlength="100"/>
			<span >为保护您的隐私安全，认证通过后您的身份证号码只显示最后4位</span>
		</li>
		
		
		
		<li  style="height:40px">
			<div tabindex="4" style="width:80px; height:30px; line-height:30px; float:left; margin:8px 0px 0px 110px; font-size:16px;"
			 id="authenticate" class="buttonorange">确认</div>
		</li>
	</ul>
</form>