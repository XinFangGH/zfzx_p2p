
<form action="${base}/financeProduct/transferToPlFinanceProduct.do" method="post" id="personBuyProductForm"   target="_blank">
			<div class="instant_recharge_two" style="display: block; overflow: hidden;border:0;">
				<div class="_div">转入金额 </div>
				<div style="font-size: 12px; color: #999; text-align: center;">
					<!--可用免费充值流量：  ¥0.00 元，超出部分1%充值手续费，财付通9折。-->
                	<input type="hidden" id="txtFreeAmount" value="0" />
                 </div>
                 <br/>
                
                 <div class="account-info banks" style="border:0;">
						<ul class="certname">
							<li style="height:50px">
								<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">产品名称：</label> 
								<input type="hidden" id="productId" name="productId" value='${plFinanceProduct.id}'/>
								<label>${plFinanceProduct.productName}</label>
							  </li>
							<li style="height:50px">
								<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">转入费率：</label> 
								<input type="hidden" id="plateRate" name="plateRate" value='${plFinanceProduct.plateRate}'/>
								<label>${plFinanceProduct.plateRate}&nbsp;&nbsp;%</label>
							</li>
							<li style="height:50px">
								<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">起投金额：</label> 
								<input type="hidden" id="minBidMoney" name="minBidMoney" value='${plFinanceProduct.minBidMoney}'/>
								<label>${plFinanceProduct.minBidMoney}&nbsp;&nbsp;元</label>
							</li>
							<li style="height:50px">
								<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">转入金额：</label> 
								<input type="text" class="colorful" name="amount" id="ptoductamountShow" onblur="checkTransferFromUser()"  maxlength="10">&nbsp;元<span id="money_span"></span>
							 
							 </li>
							 <li style="height:50px">
								<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">手续费：</label> 
								<input type="text" class="colorful" name="buyProductFee" id="buyProductFee"  readOnly maxlength="10" value='0'>&nbsp;元
							 </li>
							 <li style="height:50px">
								<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">实际扣款金额：</label> 
								<input type="text" class="colorful" name="buyFactMoney" id="buyFactMoney"  readOnly maxlength="10" value='0'>&nbsp;元
							 </li>
							
							<li  style="height:40px">
								<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">账户余额：</label> 
								<input type="hidden" id="availableInvestMoney" name="availableInvestMoney" value='${bpCustMember.availableInvestMoney}'/>
								<label>${bpCustMember.availableInvestMoney?string(',##0.00')}<span class="m-l_10">元</span>
								</label>
							</li>
							<li  style="height:40px">
								 <div style="width:919px; height:30px; padding:10px 0px 0px 100px;">
									<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;"></label> 
									<label><span class="m-l_10">预计${plFinanceProduct.intentDay?string("yyyy-MM-dd ")}产生收益，预计收益${plFinanceProduct.accountDay?string("yyyy-MM-dd ")}到账</span>
									</label>
								</div>
							</li>
							<li  style="height:40px">
								 <div style="width:919px; height:30px; padding:10px 0px 0px 100px;">
						          <label>
						            <input type="checkbox" id="readbuyAgreement" tabindex="7" class="colorful1" style="height:16px;width:15px; float:left;padding:0px 5px 0 0\9\0;margin:4px 10px 0 0;vertacal-align:middle; border:0;" />
						            <span class="black middle">我已阅读并且同意</span></label>
						          <a id="go" href="#signup" rel="leanModal" name="signup" class="font-blue"><span class="blue middle">《服务协议及风险提示》</span></a> </div>
							</li>
							<li  style="height:80px">
								<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">&nbsp;</label> 
								<lable >
								<div  tabindex="4" style="width:120px;text-align:center; height:35px; line-height:35px; float:left; margin:20px 0px 0px 65px; font-size:16px;"
			 						  class="buttonoblue1 white">
				 						  <span class="big white" id="" onClick="checkForm2()">确认</span>
			 						  </div>
								</lable>
								
							</li>
						</ul>
                 </div>
                 
                 
			</div>	
</form>

<div id="signup" style="height:400px!important;">
	<div id="signup-ct">
		<div id="signup-header">
        	<div style="float:left; width:330px; height:40px; padding:10px 0px 0px 30px"><span  class="large blue">服务协议及风险提示</span></div>
            <div id="lean_overlay_close" style="float:right;  padding:20px 0px 0px 0px;width:60px; height:20px; text-decoration:underline; cursor:pointer;"  class="normal blue">关闭</div>
		</div>
        <div style="float:left; width:650px; height:300px; margin:10px 0px 0px 30px; overflow:auto; line-height:30px;">
        <span id="bidLoad"></span>
        <!--提示信息开始-->
    <h4 style="font-size:14px; color:#555;margin:0; text-indent:2em;">本网站由升升投商务顾问（北京）有限公司负责运营（以下“升升投”均指本网站及升升投商务顾问（北京）有限公司）,并依据本协议的规定为升升投用户（以下简称“用户”）提供服务。本协议在用户和升升投间具有法律效力。在您注册成为升升投用户前请务必必认真、仔细阅读并充分理解本协议全部内容。若您一旦注册，则表示您已经充分理解和同意本协议全部内容，同意接受升升投的服务并受以下条款的约束。若您不接受以下条款，请您立即停止注册或主动停止使用升升投的服务。</h4>
    <h4 style="font-size:14px; color:#555;margin:0;text-indent:2em;">在协议履行过程中，升升投可根据实际情况对协议的相关条款进行修改变化。一旦协议内容发生变动，升升投将公布最新的服务协议，一切变更以升升投最新公布条款为准，不再向用户作个别通知。经修订的协议、规则一经公布，立即自动生效，如果用户不同意升升投对协议所做的修改，有权停止使用升升投服务。如果用户继续使用升升投服务，则视为接受升升投对协议所做的修改，并应遵照修改后的协议履行应尽义务。本网站有权不经任何告知终止、中止本协议或者限制你进入本网站的全部或者部分板块且不承担任何法律责任。但该终止、中止或限制行为并不能豁免你在本网站已经进行的交易下所应承担的任何法律责任。本公司不承担任何因此导致的法律责任。</h4>
    <h4 style="font-size:14px; color:#555;margin:0;text-indent:2em;">本服务协议包括以下所有条款，同时也包括升升投已经发布的或者将来可能发布的各类规则。所有规则均为本服务协议不可分割的一部分，与本服务协议具有同等法律效力。</h4>
${systemConfig.regDeal}

	</div>
</div>
</div>


