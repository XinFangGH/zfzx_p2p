<table class="tab_css_3">
	<tr>
		<th width="132">账户总额</th>
		<th width="132">可用余额</th>
		<th width="132">冻结金额</th>
		<th width="132">可提现额</th>
		
	</tr>
	<tr>
		<td style="text-align:center;"><span>￥<#if bpCustMember.totalMoney==0>0.00<#else>${bpCustMember.totalMoney?string(',###.00')}</#if>元</span></td>
		<td style="text-align:center;"><span>￥<#if bpCustMember.availableInvestMoney==0>0.00<#else>${bpCustMember.availableInvestMoney?string(',###.00')}</#if>元</span></td>
		<td style="text-align:center;"><span>￥<#if bpCustMember.freezeMoney==0>0.00<#else>${bpCustMember.freezeMoney?string(',###.00')}</#if>元</span></td>
		<td style="text-align:center;"><span>￥<#if bpCustMember.availableInvestMoney==0>0.00<#else>${bpCustMember.availableInvestMoney?string(',###.00')}</#if>元</span></td>
		
		
	</tr>
</table>