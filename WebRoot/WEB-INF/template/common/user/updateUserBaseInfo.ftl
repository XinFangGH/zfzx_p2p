<div class="user-infor">
	<h4>账户信息</h4>
	<ul>
		<li>
			<span class="accredi"><input type="radio"/>未认证</span>
			<span class="accredi-tit">手机号码</span>
			<span class="accredi-con">手机号是您在升升投重要的身份凭证</span>
			<span class="accredi-open"><a href="#" style="color:#0096c4">开通</a></span>
		</li>
		<li class="none">
			<span class="accredi1"><input type="radio"/>已认证</span>
			<span class="accredi-tit">手机号码</span>
			<span class="accredi-con">您绑定的手机：<em>153****2065</em></span>
			<span class="accredi-open"><a href="#" style="color:#0096c4">修改</a></span>
		</li>
		<li>
			<span class="accredi"><input type="radio"/>未认证</span>
			<span class="accredi-tit">实名认证</span>
			<span class="accredi-con">是账户所有权归属的最主要判断依据，实名认证会在开通资金托管账户过程中同时完成</span>
			<span class="accredi-open"><a href="#" style="color:#0096c4">开通</a></span>
		</li>
		<li class="none">
			<span class="accredi1"><input type="radio"/>已认证</span>
			<span class="accredi-tit">实名认证</span>
			<span class="accredi-con">**童      1102************3x</span>
			<span class="accredi-open"><a href="#" style="color:#0096c4">修改</a></span>
		</li>
		<li>
			<span class="accredi"><input type="radio"/>未认证</span>
			<span class="accredi-tit">资金托管账户</span>
			<span class="accredi-con">由富滇银行提供资金托管服务，保障资金独立与安全</span>
			<span class="accredi-open"><a href="#" style="color:#0096c4">开通</a></span>
		</li>
		<li class="none">
			<span class="accredi1"><input type="radio"/>已认证</span>
			<span class="accredi-tit">资金托管账户</span>
			<span class="accredi-con">1868519284-24</span>
			<span class="accredi-open"><a href="#" style="color:#0096c4">修改</a></span>
		</li>
		<li>
			<span class="accredi"><input type="radio"/>未认证</span>
			<span class="accredi-tit">电子邮箱</span>
			<span class="accredi-con">绑定后账户与产品变动会随时通知到邮箱</span>
			<span class="accredi-open"><a href="#" style="color:#0096c4">开通</a></span>
		</li>
		<li class="none">
			<span class="accredi1"><input type="radio"/>已认证</span>
			<span class="accredi-tit">电子邮箱</span>
			<span class="accredi-con">319***@qq.com</span>
			<span class="accredi-open"><a href="#" style="color:#0096c4">修改</a></span>
		</li>
		<li>
			<span class="accredi"><input type="radio"/>未认证</span>
			<span class="accredi-tit">登录密码</span>
			<span class="accredi-con">上次登录时间：2014-08-01 11:30:25</span>
			<span class="accredi-open"><a href="#" style="color:#0096c4">开通</a></span>
		</li>
		<li class="none">
			<span class="accredi1"><input type="radio"/>已认证</span>
			<span class="accredi-tit">登录密码</span>
			<span class="accredi-con">上次登录时间：2014-08-01 11:30:25</span>
			<span class="accredi-open"><a href="#" style="color:#0096c4">修改</a></span>
		</li>
	</ul>
</div>
		     <#-- <ul style="float:left;margin-bottom:20px;" class="personal_data">
				 <li><h3>注册信息</h3></li>
				 <li><label>用&nbsp;户&nbsp;名 ：</label><span> ${bpCustMember.loginname}</span></li>
				 <li><label>登陆密码 ：</label>
				 <a href="${base}/user/getBpCustMember.do?typ=2&action=updatePwd&retUrl=user/getBpCustMember.do?typ=1"><div tabindex="4" style="text-align:center;height:20px;line-height:20px;font-size:14px;margin-top:10px;padding:0px 5px;"  
				 id="updateUserInfo" class="buttonorange">修改</div></a>
				 </li>
			    <li><label style="padding-left:3px;">注册时间：</label><span><#if (bpCustMember.registrationDate==null)><#else>${bpCustMember.registrationDate?string('yyyy-MM-dd HH:mm:ss')}</#if></span></li>
			  </ul>
				
			  <ul style="float:right;margin-bottom:20px;" class="personal_data">
				 <li><h3>联系方式</h3></li>
				 <li><label>手机号码：</label><span>
				  <#if bpCustMember.telphone!=null>${bpCustMember.telphone?substring(0,4)}****${bpCustMember.telphone?substring(7)}

		          <#else>***********</#if></span><span></span>
		          <a href="${base}/user/getBpCustMember.do?typ=2&action=updateTelphone&retUrl=user/getBpCustMember.do?typ=1"><div tabindex="4" style="margin-left:4px;height:20px;text-align:center;line-height:20px;font-size:14px;margin-top:10px;padding:0px 5px;"  
				 id="updateUserInfo" class="buttonorange">修改</div></a>

		         </li>
		         <li><label>电子邮箱：</label>
		         <span> <#if bpCustMember.isCheckEmail==1>${emailName[0..3]}****@${emailNameAfter}<#else> ${bpCustMember.email}</#if></span><span></span>
		         <#if bpCustMember.isCheckEmail==1>
		         	<a href="${base}/emailreg.do?type=2&action=email&retUrl=user/getBpCustMember.do?typ=1" ><div tabindex="4" style="margin-left:4px;text-align:center;line-height:20px;font-size:14px;margin-top:10px;height:20px;padding:0px 5px;"  
				 	id="updateUserInfo1" class="buttonorange">修改</div></a>
		         <#else>
		         	<a href="${base}/emailreg.do?type=2&action=email&retUrl=user/getBpCustMember.do?typ=1" ><div tabindex="4" style="margin-left:4px;text-align:center;line-height:20px;font-size:14px;margin-top:10px;height:20px;padding:0px 5px;"  
				 	id="updateUserInfo2" class="buttonorange">验证</div></a>
		         </#if>
		         
		         </li>
		      </ul>


		     
				<#if systemConfig.thirdPayType=="0">

			  <ul style="float:right;margin-bottom:20px;" class="personal_data">

				 <li><h3><#if bpCustMember.thirdPayFlagId!=null>第三方支付已开通<#else>第三方支付未开通</#if></h3></li>

                 <#if bpCustMember.thirdPayFlagId!=null> <li><label>支付账号：</label><span>${bpCustMember.thirdPayFlag0}</span></li>
				 <li>
					<#if systemConfig.thirdPay!="yeepayConfig"> <a href="${base}/pay/loginToHuiFuPay.do"><div tabindex="4" style="margin-left:10px;text-align:center;height:20px;line-height:20px;font-size:14px;margin-top:10px;padding:0px 5px;"  
				 id="updateUserInfo" class="buttonorange">登录第三方支付</div></a> </#if>
				 </li>
                 <#else>
                  <li>
		         <a href="${base}/thirdreg.do"><div tabindex="4" style="margin-left:10px;text-align:center;height:20px;line-height:20px;font-size:14px;margin-top:10px;padding:0px 5px;"  
				 id="updateUserInfo" class="buttonorange">开通第三方支付</div></a>
		         </li>
                 
                 </#if>
		      </ul>
		     
		       </#if>
				<#if systemConfig.system_authentication_type!="thirdPayAuthentication">
		        <ul style="float:left;margin-bottom:20px;" class="personal_data">

				 <li><h3><#if bpCustMember.isCheckCard=="1">已实名认证<#else>未实名认证</#if></h3></li>
				 <li><label>真实姓名：</label><span> ${bpCustMember.truename}</span></li>
				 <li><label>身份证号：</label><span>
				 <#if bpCustMember==null><#else>
				 	<#if bpCustMember.cardcode?length lt 15>${bpCustMember.cardcode}<#else>${bpCustMember.cardcode?substring(0,4)}****${bpCustMember.cardcode?substring(14)}</#if>
				 </#if></span></li>
				 
				<#if bpCustMember.isCheckCard!="1">
					<#if systemConfig.thirdPay!="huifuConfig" >
				  <li>
				 <a href="${base}/user/getBpCustMember.do?typ=2&action=updateName&retUrl=user/getBpCustMember.do?typ=1"><div tabindex="4"  style="margin-left:10px;text-align:center;line-height:20px;font-size:14px;margin-top:10px;height:20px;padding:0px 5px;"  
				 id="updateUserInfo" class="buttonorange">实名认证</div></a></li>
				 <#elseif bpCustMember.isCheckCard!="1">
				 <span class="small" style="float:right">*注：开通第三方支付账号自动实名认证</span>
				 </#if>
				<#else></#if>
				 
			  </ul>
			  <#else> 
				  <#if systemConfig.system_authentication_show!="yes">
				  <#else>
				  	<ul style="float:left;margin-bottom:20px;" class="personal_data">
						 <li><h3><#if bpCustMember.isCheckCard=="1">已实名认证<#else>未实名认证</#if></h3></li>
						 <li><label>真实姓名：</label><span> ${bpCustMember.truename}</span></li>
						 <li><label>身份证号：</label><span>
						 <#if bpCustMember==null><#else>
						 	<#if bpCustMember.cardcode?length lt 15>${bpCustMember.cardcode}<#else>${bpCustMember.cardcode?substring(0,4)}****${bpCustMember.cardcode?substring(14)}</#if>
						 </#if></span></li>
						<#if bpCustMember.isCheckCard!="1">
						  <li><span class="small" style="float:right">*注：开通第三方支付账号自动实名认证</span>
						<#else></#if>
				  	</ul>
				  </#if>
			  </#if>
		

		      <ul style="float:left;<#if systemConfig.thirdPayType=="0"><#else>margin:0 0 20px 30px;</#if>" class="personal_data">

				 <li><h3>已绑定的提现银行卡</h3></li>
				  <#list listBankCard as list>
				  		<#if list_index lt 2>
				  			<li ><label title="${list.bankname}"><a href="javascript:void(0)" title="${list.bankname}"><#if (list.bankname)??><#if list.bankname?length lt 6 >${list.bankname}<#else>${list.bankname?substring(0,6)}</#if></#if></a>：</label><span>${list.cardNum?substring(0,4)}****${list.cardNum?substring(12)}</span></li>
				  		</#if>
				  </#list>
				  <li>
				 <a href="${base}/financePurchase/getBindBankListFinancePurchase.do"><div tabindex="4"  style="margin-left:4px;text-align:center;line-height:20px;font-size:14px;margin-top:6px;height:20px;padding:0px 5px;"  
				 id="updateUserInfo" class="buttonorange">设置其它提现银行卡</div></a>
				 </li>
			  </ul> -->

		      
		    