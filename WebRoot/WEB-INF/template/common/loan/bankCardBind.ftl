
<script type="text/javascript" >

	$(function(){
	
		$("#bankId").change(function(){
			//得到下拉列表的相应的值
			var id = document.getElementById("bankId").value;
			var select = document.getElementById("bankId").selectedIndex;
			var selectName = document.getElementById("bankId").options[select].text;
			//给隐藏文本框赋值
			document.getElementById("bankname").value=selectName;
		});
	});
</script>
<form action="${base}/pay/bindCardPay.do?retUrl=<#if bType??><#if bType=='bindBankList'>financePurchase/getBindBankListFinancePurchase.do<#else>financePurchase/withdrawFinancePurchase.do</#if><#else>financePurchase/withdrawFinancePurchase.do</#if>" method="post" name="bindBankCardForm" id="bindBankCardForm">
	<input type="hidden"  name="path" id="path" maxlength="10" value="${base}">
	<div class="hdlh">
    <div class="hdlh1">
	<ul class="bd myinfo" style="width:400px;background:#f8f6f6;padding:25px 20px 55px 80px;">
		
		<li style="height:20px"></li>
		
				<div class="common-tip" style="display: none; margin-left: 95px;"></div>
				
				<li style="height:40px;display: none;">
					<label  style="margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">银行卡类型：</span></label>
					<input type="radio" name="moneyMoreMore.CardType" id="CardType" value="0" checked ><span class="middle gray">借记卡</span></label>
					<input type="radio" disabled name="moneyMoreMore.CardType" id="CardType" value="1" />
					<label style="margin-top: 0;"  for="female"><span class="middle gray">信用卡</span></label>
				</li>
				<li style="height:20px;">
					<label  style="margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">开户名称：<#if bpCustMember??>${bpCustMember.truename}<#else></#if></span>										
					</label>
					
				</li>
				<li style="height:25px;">											
					<span class="normal" style="color:#9B0D01;">银行卡必须1为"<#if bpCustMember??>${bpCustMember.truename}<#else></#if>"的借记卡，否则提现失败</span>		
				</li>
					<li style="height:30px;">
					<label  style="margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">开户银行</span></label>
				</li>
				<li style="height:30px;">	
					<input type="hidden" id="bankname" name="webBankcard.bankname" />
					<select class="colorful" id="bankId" name="webBankcard.bankId" style="width:310px;">
						<option value="">请选择</option>
						<#list listCsbank as list>
						<option value="${list.bankid}">${list.bankname}</option>
						</#list>
						
					</select>
				</li>
				
				
				<li style="height:30px;">
					<label  style="margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">银行卡号</span></label>
				</li>
				<li style="height:30px;">
					<input type="type" class="colorful" name="cardNo" id="CardNum" style="width:300px;"/>
				</li>
				
				<!-- 
				<#if listCsbank!=null>
				<li style="height:30px">
					<label  style="margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">开户地区</span></label>
				</li>
				<li style="height:30px">
					<input type="hidden" id="provinceName" name="webBankcard.provinceName" />
					<select class="colorful" name="webBankcard.provinceId" id="Province" style="width:207px;">
						<option value="">请选择</option>
					</select>
					
					<input type="hidden" id="cityName" name="webBankcard.cityName" />
					<select class="colorful" name="webBankcard.cityId" id="City" style="width:100px;">
						<option value="">请选择</option>
					</select>
					 <select name="district" id="district"  style="width: auto; display: none;"></select> 
				</li>
				<li style="height:30px;">
					<label  style="margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">分行名称</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="normal" style="color:red;">(非必填，填写后可加快提现)</span></label>
				</li>
				<li style="height:30px;">
					<input type="type" class="colorful" name="webBankcard.branchbank" id="branchbank" style="width:300px;"/>
				</li>
				 <li style="height:30px;">
					<label  style="margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"><span class="middle gray">网点名称</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="normal" style="color:red;">(非必填，填写后可加快提现)</span></label>
				</li>
				  
				<li style="height:30px;">
					<input type="type" class="colorful" name="webBankcard.subbranchbank" id="subbranchbank" style="width:300px;"/>
				</li>
				</#if>
				-->
				<li style="height:30px;">
					<label style="margin-top: 0;width: 50px;text-align: right;margin-right: 15px;">&nbsp;&nbsp;</label>
					<label style="blackground-color:red;">
					<div tabindex="4" style="width:120px; height:30px; float:left; margin:20px 0px 0px 112px; padding:5px 0px 0px 0px; font-size:16px;"
		  class="buttonorange" id="submitbindBankCardFormDiv"  onclick="submitbindBankCardForm();">绑定银行卡</div>
				
					</lable>
					
				</li>
	</ul>
	</div>
	</div>
</form>