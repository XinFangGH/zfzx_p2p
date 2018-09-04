<form name="queryForm" id="queryForm" method="post" action="${base}/pay/balanceQueryPay.do">
	<input type="hidden" id="tradeId" name="tradeId" value="front_MyFinanceDetail">
	<input type="hidden" id="submitForm" name="submitForm" value="queryForm">
	<input type="hidden" name="currPage" id="currPage" value=""/>
	<input type="hidden" name="minShowFlag" id="minShowFlag" value=""/>
	<input type="hidden" name="maxShowFlag" id="maxShowFlag" value=""/>
	<input type="hidden" id="fund_type" name="fund_type" value="">
	<input type="hidden" name="recharge_status" id="recharge_status" value="-2"/>
	<input type="hidden" name="withdraw_status" id="withdraw_status" value="-7"/>
	<dl class="bd finance">
	 	<dd>
			<label>交易时间：</label>
			<input type="text" id="from" name="from"  />
			<span >到</span>
			<input type="text" id="to" name="to"  />
			<!--<input type="submit" id="btn" name="btn" class="query-btn" value="查询" />-->
			<a href="javascript:queryForm();" >查询</a>
		</dd>
	</dl>	
</form>
<div >
	<ul >
		<li >
			资金明细
		</li>
		<li>
			充值记录
		</li>
		<li>
			提现记录
		</li>
	</ul>
	<div  style="display: block;">
		<div >
			<table>
				<tr>
						<th>交易时间</th>
					<th>交易类型</th>
					<th>交易金额</th>
					<th>交易描述</th>
					
					</tr>
			</table>
			<div  id="fund_pager"></div>
		</div>
	</div>
	
	<div >
		<div   style="height:500px">
			<div >
				<label for="rechargeAll"><input type="radio" name="recharge" id="rechargeAll" checked="true" value="-2" onclick="query('queryForm','recharge','rec')"/>所有</label>
				<label for="rechargeSuccess"><input type="radio" name="recharge" id="rechargeSuccess" value="Y" onclick="query('queryForm','recharge','rec')"/>充值成功</label>
				<label for="rechargeFailed"><input type="radio" name="recharge" id="rechargeFailed" value="N" onclick="query('queryForm','recharge','rec')"/>充值失败</label>
			</div>
			<table>
				<tr>
						<th>充值银行</th>
					<th>充值金额</th>
					<th>充值时间</th>
					<th>充值状态</th>
				</tr>									
			</table>
			<div  id="recharge_pager"></div>
		</div>
	</div>
	
	
	<div >
		<div  style="height:500px">
			<div >
				<label for="widthdrawAll"><input type="radio" name="withdraw" id="widthdrawAll" checked="true" value="-7" onclick="query('queryForm','withdraw','draw')"/>所有</label>
				<label for="withdrawSuccess"><input type="radio" name="withdraw" id="withdrawSuccess" value="1" onclick="query('queryForm','withdraw','draw')"/>提现成功</label>
				<label for="withdrawFailed"><input type="radio" name="withdraw" id="withdrawFailed" value="0" onclick="query('queryForm','withdraw','draw')"/>提现失败</label>
				<label for="withdrawWaiting"><input type="radio" name="withdraw" id="withdrawWaiting" value="-1" onclick="query('queryForm','withdraw','draw')"/>提现中</label>
			</div>
			<table>
				<tr>
					<th>提现账户</th>
					<th>提现金额</th>
						<th>手续费用</th>
					<th>提现时间</th>
					<th>提现状态</th>
				</tr>									
			</table>
			<div id="withdraw_pager" ></div>
		</div>
	</div>
	


</div>