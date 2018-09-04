<form action="${base}/pay/withdrawsPay.do?tradeId=front_Withdraw" method="post" name="withdrawForm" id="withdrawForm">
	<input type="hidden"  name="path" id="path" maxlength="10" value="${base}">
	<ul class="bd myinfo">
		<li style="border: 1px solid #ededed;background:#f8f8f8;margin-bottom: 10px;margin-top: 15px;">
			<label></label>
			<span style="font-size: 15px;color: #333;padding: 7px 7px 7px 15px;">
				第一步：申请提现
				
				</span>
		</li>
		
		
		
		<li style="height:20px"></li>
		
				<div class="common-tip" style="display: none; margin-left: 95px;"></div>
				
				<li style="height:40px;display: none;" class="account">
					<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">银行卡类型：</span></label>
					<input type="radio" name="moneyMoreMore.CardType" id="CardType" value="0" checked ><span class="middle gray">借记卡</span></label>
					<input type="radio" disabled name="moneyMoreMore.CardType" id="CardType" value="1" />
					<label style="margin-top: 0;"  for="female"><span class="middle gray">信用卡</span></label>
				</li>
				
				<li style="height:40px;" class="account">
					<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">开户银行：</span></label>
					<input type="hidden" id="bankname" name="bankname" />
					<select class="colorful" id="BankCode" name="bankCode"">
						<option value="">请选择</option>
						<#list listCsbank as list>
						<option value="${list.bankid}">${list.bankname}</option>
						</#list>
						
					</select>
				</li>
				
				<li style="height:40px;">
					<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">银行卡号：</span></label>
					<input type="type" class="colorful" name="cardNo" id="CardNo"/>
				</li>
				
				<li style="height:40px;" class="account">
					<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">开户名称：</span></label>
					<input type="type" class="colorful" name="branchBankName" id="BranchBankName"/>
				</li>
				
				
				
				<li style="height:40px" class="account">
					<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">开户地区：</span></label>
					<input type="hidden" id="provinceName" name="province" />
					<select class="colorful" name="provinceId" id="Province">
						<option value="">请选择</option>
					</select>
					
					<input type="hidden" id="cityName" name="city" />
					<select class="colorful" name="cityId" id="City">
						<option value="">请选择</option>
					</select>
					 <select name="district" id="district"  style="width: auto; display: none;"></select> 
				</li>
				<li style="height:40px;">
					<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">网点名称：</span></label>
					<input type="type" class="colorful" name="outBankName" id="outBankName"/>
				</li>
				
				<li style="height:50px;" class="account">
					<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;" ><span class="middle gray">提现金额：</span></label>
					<label style="width:220px;">
					<input  type="hidden" class="txt" name="user_account_money" id="user_account_money" maxlength="10">
					<input style="float:left" class="colorful" class="txt" name="amount" id="amountShow" maxlength="11" onkeyup='checkLength(this);changeAmt(this,"user_account_money");' onfocusout='changeAmt(this,"user_account_money");'>
					<span class="m-l_10">元</span>
					</label>
					<label style="line-height: 20px;font-size:12px;float:right;width:180px;background-color:#F6F6F6">提现手续费收费标准：<#if systemConfig.theme=="proj_yirandai">3元/笔<#else>${systemConfig.poundage}%/笔</#if></label>
				</li>
				<#if systemConfig.isOpenPayMentCode=="1">
				<li style="height:40px;" class="account">
					<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">交易密码：</span></label>
					<input type="password" class="colorful" name="paymentCode" id="paymentCode" maxlength="16"/>
					<span id="paymentCodeSpan"  class="m-l_10"></span>
					<a href="javascript:;"  target="_self" class="m-l_20"><span class="middle gray">忘记交易密码?</span></a>
				</li> </#if>
				
				<li style="height:40px;" class="account">
					<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">可提现金额：</span></label>
					<label><input type="hidden" name="moneyaccount" id="moneyaccount" value="${bpCustMember.availableInvestMoney}"/>${bpCustMember.availableInvestMoney}<span class="m-l_10">元</span>
					</label>
				</li>
				
				
				<li style="height:40px;" class="account">
					<label style="float: left;margin-top: 0;width: 50px;text-align: right;margin-right: 15px;">&nbsp;&nbsp;</label>
					<label style="float:left;blackground-color:red;">
					<div tabindex="4" style="width:120px; height:30px; float:left; margin:8px 0px 0px 65px; padding:5px 0px 0px 0px; font-size:16px;"
		  class="buttonorange" id="submitNextFormDiv"  onclick="submitNextForm();">立即提现</div>
				
					</lable>
					
				</li>
	</ul>
	<!--<div class="account_for_bank" style="border: 1px solid #ededed;background: #fff;margin-bottom: 10px;margin-top: 15px;">
        <div style="background: #f8f8f8;font-size: 15px;color: #333;padding: 7px 7px 7px 15px;">  第二步：第三方银行处理</div>
        <h4 style="margin-left: 155px;padding: 5px 0 5px 0;color: #333;">待处理提现记录</h4>
        <table cellpadding="0" cellspacing="0" class="tab_css_2">
            <tbody><tr>
                <th>提现金额
                </th>
                <th>提交时间
                </th>
                <th>预计到账时间
                </th>
            </tr>
            <tr>
                <td>100.00
                </td>
                <td>2014-03-01 11:11:11
                </td>
                <td>2014-03-25 11:11:11
                </td>
            </tr>
        </tbody></table>
        <br/>
    </div>-->
</form>