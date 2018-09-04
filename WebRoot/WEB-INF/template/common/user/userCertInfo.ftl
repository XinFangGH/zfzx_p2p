<div class="bd">
	<h3 style="background-image: url(${base}/theme/${systemConfig.theme}/images/icon_form_title.png);background-repeat: no-repeat;color: #d30c13;font-size: 14px;height: 36px;line-height: 36px;padding-left: 36px;">
		<b class="f-l">温馨提示</b>
		<div class="degree f-l">
			<span <#if 
			(bpCustMember.isCheckEmail==null&&bpCustMember.paymentCode==null&&bpCustMember.cardcode==null&&bpCustMember.telphone==null)||
			(bpCustMember.isCheckEmail!=null&&bpCustMember.paymentCode==null&&bpCustMember.cardcode==null&&bpCustMember.telphone==null)||
			(bpCustMember.isCheckEmail==null&&bpCustMember.paymentCode!=null&&bpCustMember.cardcode==null&&bpCustMember.telphone==null)||
			(bpCustMember.isCheckEmail==null&&bpCustMember.paymentCode==null&&bpCustMember.cardcode!=null&&bpCustMember.telphone==null)||
			(bpCustMember.isCheckEmail==null&&bpCustMember.paymentCode==null&&bpCustMember.cardcode==null&&bpCustMember.telphone!=null)> 
			class="high" </#if> >低</span>
			<span <#if 
			((bpCustMember.isCheckEmail!=null&&bpCustMember.paymentCode!=null)&&(bpCustMember.cardcode==null||bpCustMember.telphone==null))||
			((bpCustMember.isCheckEmail!=null&&bpCustMember.cardcode!=null)&&(bpCustMember.paymentCode==null||bpCustMember.telphone==null))||
			((bpCustMember.isCheckEmail!=null&&bpCustMember.telphone!=null)&&(bpCustMember.paymentCode==null||bpCustMember.cardcode==null))||
			((bpCustMember.paymentCode!=null&&bpCustMember.cardcode!=null)&&(bpCustMember.isCheckEmail==null||bpCustMember.telphone==null))||
			((bpCustMember.paymentCode!=null&&bpCustMember.telphone!=null)&&(bpCustMember.isCheckEmail==null||bpCustMember.cardcode==null))||
			((bpCustMember.cardcode!=null&&bpCustMember.telphone!=null)&&(bpCustMember.password==null||bpCustMember.paymentCode==null))>
			class="high" </#if>  >中</span>
			<span <#if bpCustMember.password!=null&&bpCustMember.paymentCode!=null&&bpCustMember.cardcode!=null&&bpCustMember.telphone!=null> class="high"</#if> >高</span>
		</div>
	</h3>
	<p class="m-b_20">建议您启动全部安全装置，以保障账户和资金安全。</p>
	<ul class="set-list">
		<li>
			<a href="${base}/user/getBpCustMember.do?safe=all&typ=2" class="set-btn f-r">修改</a>
			<label <#if bpCustMember.password!=null>class="success"<#else> class="failed"</#if> >登录密码：</label>最好使用一个包含数字和字母，并且长度为6-16个字符的密码。
		</li>
		
		<li>
			<a href="${base}/user/getBpCustMember.do?safe=all&typ=4" class="set-btn f-r">修改</a>
			<label <#if bpCustMember.isCheckPhone!=null> class="success"  <#else>  class="failed" </#if> >手机绑定：</label>当修改密码、银行账号、提现等操作时需通过手机验证码，以确保账户资金安全。
		</li>
		
		<#if systemConfig.isOpenPayMentCode=="1">
		<li>
		
			<#if bpCustMember.paymentCode==null>
			<a href="${base}/user/getBpCustMember.do?safe=all&typ=5" class="set-btn f-r">
			
			设置
			</a>
			<#else>
			<a href="${base}/user/getBpCustMember.do?safe=all&typ=5" class="set-btn f-r">
			
			修改
			</a>
			</#if>
			<label <#if bpCustMember.paymentCode!=null>class="success"<#else> class="failed"</#if> >交易密码：</label>最好使用一个包含数字和字母，并且长度为6-16个字符的密码，请不要和登录密码相同
		</li>
		</#if>
		<li>
		
			<#if bpCustMember.isCheckEmail==null>
			<a href="${base}/emailreg.do" class="set-btn f-r">
			
			设置
			</a>
			<#else>
			<a href="${base}/emailreg.do" class="set-btn f-r">
			
			修改
			</a>
			</#if>
			<label <#if bpCustMember.isCheckEmail!=null&&bpCustMember.isCheckEmail==1>class="success"<#else> class="failed"</#if> >邮箱：</label>当进行某些交易时，会进行邮箱提醒!<#if isSendMail>已发验证邮件请验证!</#if>
		</li>
		<#if systemConfig.thirdPay!="huifuConfig">
		<li>
			<#if bpCustMember.isCheckCard==null>
			<a href="${base}/user/getBpCustMember.do?safe=all&typ=3" class="set-btn f-r">认证</a>
			<#else>
			</#if>
			<label <#if bpCustMember.isCheckCard!=null> class="success"  <#else>  class="failed" </#if> id="authenticateStatus">实名认证：</label>
			成为实名认证用户后，即使您发送的信息在网上被他人截获，甚至您的个人账户、密码等<br>
			信息被盗取，仍可以保证您的账户资金安全。
			</li>
		</#if>
		
		<#if systemConfig.thirdPay=="huifuConfig">
		<li>
			<#if bpCustMember.thirdPayFlagId!=null >
				<a href="${base}/pay/loginToHuiFuPay.do" class="set-btn f-r" style="background-color:#999">
				登录到汇付天下
				</a>
			<#else>
				<a href="${base}/thirdreg.do" class="set-btn f-r">
			
				开通
				</a>
			</#if>
			<label <#if bpCustMember.thirdPayFlagId!=null>class="success" <#else> class="failed"</#if> >第三方支付绑定：</label> <#if bpCustMember.thirdPayFlagId!=null>绑定成功后可直接登录到汇付账户。<#else>当完成邮箱、手机验证后才可进行绑定!</#if>
		</li>
		
		<li>
			<#if bpCustMember.thirdPayFlagId!=null >
				<a href="${base}/pay/bindCardPay.do" class="set-btn f-r" style="background-color:#999">
				绑定银行卡
				</a>
			
			<label  >银行卡绑定：</label>取现之前必须先绑定银行卡!可绑定多张银行卡。
			</#if>
		</li>
		</#if>
		<#if systemConfig.thirdPay=="moneyMoreMoreConfig">
		<li>
			<#if bpCustMember.thirdPayFlagId!=null >
				<a href="javascript:(0)" class="set-btn f-r">
			
				已开通
				</a>
			<#elseif bpCustMember.isCheckPhone==1>
				<a href="${base}/thirdreg.do" class="set-btn f-r">
			
				开通
				</a>
			</#if>
			<label <#if bpCustMember.thirdPayFlagId!=null>class="success" <#else> class="failed"</#if> >第三方支付绑定：</label>当完成邮箱、手机验证后才可进行绑定!
		</li>
		</#if>
	</ul>
</div>
