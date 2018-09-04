<script type="text/javascript" >
	$(function(){
		$("#withdrawForm").submit(function(){
			$("#disabledDiv").show();
			$("#submitNextFormDiv").hide();
		})
		//默认第一个单选框选中
		 $(".card:eq(0)").attr("checked",true);
	});
</script>
<form action="${base}/pay/withdrawsPay.do?tradeId=front_Withdraw" onkeydown="if(event.keyCode==13){return false;}" method="post" name="withdrawForm" id="withdrawForm" style=" padding:0 20px;">
<a href="${base}/financePurchase/getBindBankListFinancePurchase.do" class="buttonoblue4 f-r" style="color:#fff;padding:3px 8px; float:right;">银行卡管理</a><br>

    <p class="my_tit">*提现失败时请先确认绑定银行卡是否为二类卡，是否超出转账限额，详情咨询相关开卡银行</p>
	<div class="tab-cont"  style="margin-top:15px;">
						<div class=" bd">
							<table class="tab_css_three" border="0" cellpadding="0"  cellspacing="0">
								<tr>
									<th class="black normal">姓名</th>
									<th class="black normal">银行</th>
									<th class="black normal">卡号</th>
									<th class="black normal">选择</th>
								</tr>
									
								<#list listBankCard as list>
        	                    <tr>
        	          				<td>${list.username}</td> 
        	         				<td>${list.bankname}</td> 
        	          				<td><#if list.cardNum!=null><#if list.cardNum!="">${list.cardNum?substring(0,4)}****${list.cardNum?substring(12)}<#else>***********</#if><#else>***********</#if></td>
									<td><input class="card" name="cardNo" type="radio" value="${list.cardId}" /></td>
        	           			</tr>
        						</#list>
        	
							</table>
							
						</div>
					</div>
		
	<ul class="bd myinfo" style="margin-top:20px; font-size:12px;">
		<li style="height:20px"></li>
        <li style="height:50px;">
            <label style="float: left;margin-top: 0;width: 132px;text-align: center;margin-right:0px;">
                <span class="middle gray">可提现金额</span></label>
            <label style="width:220px;font-size:18px;color: #f65540;">
                <input type="hidden" name="moneyaccount" id="moneyaccount" value="${withdrawBalance}"/>${withdrawBalance}<span class="m-l_10">元</span>
			</label>
			<p style="display: inline-block;width: 475px;height:28px;line-height28px;text-align:center;background-color: #f4f4f4;font-size: 14px;color:666;margin-left: 10px">
				<img style="display: inline-block;width: 18px;height:18px;vertical-align: text-bottom;" src="${base}/theme/${systemConfig.theme}/images/reminder.png" alt="">&nbsp;温馨提示：当日充值及回款金额请于1-2个工作日后提取，节假日顺延。</p>
        </li>
		<li style="height:50px;">
			<label style="float: left;margin-top: 0;width: 120px;text-align: center;margin-right: 0px;" >
				<span class="middle gray">提现金额</span>
			</label>
			<label style="width:220px;">
				<input style="float:left" class="colorful txt"  name="amount" id="amountShow"  maxlength="9" onblur="chk_Money('amountShow');">&nbsp;<span id="money_span" style="color: red"></span>
			</label>
		</li>
        <hr style="height:18px;margin-top:20px;border:none;border-top:2px dashed #f4f4f4;">
		<li style="height:50px;">
			<label style="float: left;margin-top: 0;width: 120px;text-align: center;margin-right: 0px;">
			 <span class="middle gray"> 可用余额</span> </label>
			 <label style="width:220px;">
			 <input type="hidden" name="moneyaccount" id="moneyaccount" value="${bpCustMember.availableInvestMoney}"/>${bpCustMember.availableInvestMoney?string(',##0.00')}<span class="m-l_10">元
			</label>
		</li>
				<#if systemConfig.isOpenPayMentCode=="1">
				<!--<li style="height:40px;">
					<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">交易密码：</span></label>
					<input type="password" class="colorful" name="paymentCode" id="paymentCode" maxlength="16"/>
					<span id="paymentCodeSpan"  class="m-l_10"></span>
					<a href="javascript:;"  target="_self" class="m-l_20"><span class="middle gray">忘记交易密码?</span></a>
				</li>--> </#if>
				
				
				<li style="height:40px;">
					<label style="float: left;margin-top: 0;width: 50px;text-align: right;margin-right: 15px;">&nbsp;&nbsp;</label>
					<label style="float:left;blackground-color:red;">
					<div tabindex="4" style=" margin:8px 0px 0px 155px;" class="buttonorange" id="submitNextFormDiv"  onclick="submitNextForm();">立即提现</div>
		  
			  <div tabindex="4" style="width:120px; height:35px; line-height:35px; float:left; margin:8px 0px 0px 155px; font-size:16px;display:none;"
				  class="buttonoblue4" id="disabledDiv" >提现申请中....</div>
				
					</lable>
					
				</li>
				 <#--<li>
					  <p style="margin-top: 20px; color: #c40000;">取现提示：<br>
					1、取现前需先绑定银行卡。绑定银行卡请到“银行卡管理”中进行操作；<br>
					2、每次取现，易宝支付会收取2元手续费，请根据自己所需，进行提现，避免多次支付手续费。</p>
				</li>-->
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