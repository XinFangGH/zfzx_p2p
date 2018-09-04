
 <form action="${base}/financeProduct/transferFromPlFinanceProduct.do"
		method="post" name="personTransferProductForm" id="personTransferProductForm"   target="_blank">
			<div class="instant_recharge_two" style="display: block; overflow: hidden;">
				<div class="_div">转出金额 </div>
				<div style="font-size: 12px; color: #999; text-align: center;">
					<!--可用免费充值流量：  ¥0.00 元，超出部分1%充值手续费，财付通9折。-->
                	<input type="hidden" id="txtFreeAmount" value="0" />
                 </div>
                 <br/>
                
                 <div class="account-info banks" style="border:0;">
						<ul class="certname">
							<li style="height:60px">
								<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">产品名称：</label> 
								<input type="hidden" id="productId" name="productId" value='${plFinanceProduct.id}'/>
								<label>${plFinanceProduct.productName}</label>
								<!--<select class="colorful" id="productId" name="plFinanceProduct.productId" style="width:210px;">
									<option value="">请选择</option>
									<#list plFinanceProductlist as list>
									<option value="${list.productName}">${list.productName}</option>
									</#list>
									
								</select>-->
							  </li>
							<li style="height:60px">
								<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">转出金额：</label> 
								<input type="text" class="colorful" name="amount" id="amountFromShow" onblur="chk_Money('amountFromShow');"  maxlength="10">&nbsp;元
								<span id="money_span" style="color:red;"></span>
							  </li>
							
							<li  style="height:40px">
								<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">可转出金额：</label> 
								<label>${plFinanceProductUseraccount.currentMoney?string(',##0.00')}<span class="m-l_10">元</span>
								</label>
							</li>
							<li  style="height:40px">
								 <div style="width:919px; height:30px; padding:10px 0px 0px 100px;">
									<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"></label> 
									<label><span class="m-l_10">转出金额当日无收益,立即到账</span>
									</label>
								</div>
							</li>
							<li  style="height:80px">
								<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">&nbsp;</label> 
								<lable >
								<div  tabindex="4" style="width:120px;text-align: middle; height:35px; line-height:35px; float:left; margin:8px 0px 0px 65px; font-size:16px;"
			 						  class="buttonoblue1 white">
				 						  <a href="#signup" rel="leanModal"  name="signup">
				 						  	<span class="big white" style="width:120px;text-align: middle; height:35px; line-height:35px; float:left; margin: 0px 0px 0px 0px; font-size:16px;" id="" onClick="checkForm(${plFinanceProductUseraccount.currentMoney})">确定</span>
				 						  </a>
			 						  </div>
								</lable>
								
							</li>
						</ul>
                 </div>
                 
                 
			</div>
			
	
</form>



