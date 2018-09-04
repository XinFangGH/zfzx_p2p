<!--回款中模版-->
<script id="page_tmp7" type="text/x-jsrender">
	<tr > 
		  <td style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
		  	  <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidId}}" target="_blank" title="{{:proName}}"><span class="normal">{{:proName}}</span></a>
		  </td>
		  <td align="center"><span class="normal">{{:payintentPeriod}}</span></td> 
		  <td align="center"><span class="normal">{{:intentDate}}</span></td> 
		  <td align="center"><span class="normal">{{:intenttotal}}元</span></td>
		  <td align="center"><span class="normal">{{:accMoney}}元</span></td> 
		  <td align="center"><span class="normal">{{:repaymentTotal}}元</span></td> 
		  <td align="center">
		  	  <a href="javascript:void(0);"  onClick="clickPlan({{:bidId}},'Loan')"><span class="normal">回款计划	&nbsp;|</a></span></a>
		  	  <a href="{{isurl:url}}"><span class="normal"><span class="normal">&nbsp;合同</span></span></a>
		  </td> 
		  <td align="center" style="text-align:left;"> 
		 	  <a class="btn1" id="repayMentbtn"   onClick="repayMentPay()" href="${base}/pay/repayMentByLoanerPay.do?planId={{:bidId}}&ids=&peridId={{:payintentPeriod}}&notMoney={{:repaymentTotal}}" >立即还款</a>
		 <a class="btn2" href="javascript:void(0);" id="advanceLoanBtn" onClick="advanceLoan({{:bidId}})" >提前还款</a> 
		 <#--<a class="btn1" target="_Blank"  href="${base}/pay/autoRepaymentAuthorizationPay.do?planId={{:bidId}}&&actionStatus={{:authorizationStatus}}">{{ationStatus:authorizationStatus}}</a></td>-->
	</tr>
</script>


<!--代偿列表模版-->
<script id="page_tmp5" type="text/x-jsrender">
	<tr > 
		<td style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:planId}}" target="_blank" title="{{:bidName}}"><span class="normal">{{:bidName}}</span></a>
		</td>
		<td align="center"><span>{{:payintentPeriod}}</span></td> 
		<td align="center"><span>{{:compensatoryDate}}</span></td>
		<td align="center"><span class="normal">{{:compensatoryMoney}}元</span></td> 
		<td align="center"><span class="normal">{{:punishMoney}}元</span></td> 
		<td align="center"><span class="normal">{{:backPunishMoney}}元</span></td> 
		<td align="center"><span class="normal">{{:backCompensatoryMoney}}元</span></td> 
		<td align="center"><span class="normal">{{:plateMoney}}元</span></td> 
		<td align="center"><span class="normal">{{:compensatoryDays}}天</span></td> 
		<td align="center"><span class="normal">{{:surplusMoney}}</span>元</td> 
		 <td align="center"> 
		 <a href="javascript:void(0);"  onClick="show({{:id}},{{:surplusMoney}});" class="btn1">立即还款</a>
		</td>
	</tr>
					        	

</script>

<!--已完成-->
<script id="page_tmp10" type="text/x-jsrender">
	<tr>
	 	<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
	 	<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidId}}" target="_blank" title="{{:proName}}"><span class="normal">{{:proName}}</span></a>
	 	</td>
	 	<td align="center"><span class="normal">{{:payMoney}}元</span></td>
	 	<td align="center"><span class="normal">{{:bidTime}}</span></td>
	 	<td align="center"><span class="normal">{{:loanLife}}</span></td>
	 	<td align="center"><span class="normal">{{:interestRate}}%</span></td>
	 	<td align="center"><span class="normal">{{:repaymentTotal}}元</span></td>
	 	<td align="center"><span class="normal">{{:repaymentDate}}</span></td>
	 	<td align="center">
	 		<a href="javascript:void(0);"  onClick="clickPlan({{:bidId}},'Repaymented')" class="btn1">回款计划</span></a>
	 		<a href="{{isurl:url}}" class="btn2" >合同</a>
	 	</td>
	</tr>
</script>


<!--招标中-->
<script id="page_tmp1" type="text/x-jsrender">
	<tr>
		<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidId}}" target="_blank" title="{{:proName}}"><span class="normal">{{:proName}}</span></a>
		</td>
		<td align="center"><span class="normal">{{:payMoney}}元</span></td>
		<td align="center"><span class="normal">{{:bidTime}}</span></td>
		<td align="center"><span class="normal">{{:loanLife}}</span></td>
		<td align="center"><span class="normal">{{:interestRate}}%</span></td>
	</tr>
</script>

<!--申请借款中-->
<script id="page_tmp-1" type="text/x-jsrender">
		<tr>
			<td width="10%" align="center">{{:loanTitle}}</td>
			<td width="10%" align="center">{{:productName}}</td>
			<td width="15%" align="center">{{:loanMoney}}元</td>
			<td width="10%" align="center">{{:loanTimeLen}}个月</td>
			<td width="10%" align="center">{{:createTime}}</td>
			<td width="15%" align="center">
				{{if state=='0'}}
					<a href="${base}/loan/getNodeP2pLoanProduct.do?productId={{:productId}}">未填写完整继续填写</a> | <a href="${base}/financePurchase/endApplyapplyUser.do?id={{:loanId}}">终止</a><br/>
				{{/if}}
				{{if state=='1'}}
					已提交审核<br/>
				{{/if}}
				{{if state=='2'}}
					已受理<br/>
				{{/if}}
				{{if state=='3'}}
					<a href="${base}/loan/getNodeP2pLoanProduct.do?productId={{:productId}}">打回,请重新填写</a> | <a href="${base}/financePurchase/endApplyapplyUser.do?id={{:loanId}}">终止</a><br/>
				{{/if}}
				{{if state=='4'}}
					已通过审批<br/>
				{{/if}}
				{{if state=='5'}}
					立项中<br/>
				{{/if}}
				{{if state=='6'}}
					已发标<br/>
				{{/if}}
				{{if state=='7'}}
					未通过审核已终止<br/>
				{{/if}}
			</td>
		</tr>
</script>
	<!--统计数据查询的模板-->
	<script id="temp" type="text/x-jsrender">
		<div class="data-invent">
			<p class="normal"><i class="icon-num icon-01"></i>累计借款笔数</p>
			<p class="middle pd-30"><em>{{:allLoanNums}}</em>笔</p>
		</div>
		<div class="data-invent">
			<p class="normal"><i class="icon-num icon-02"></i>招标中笔数（笔）</p>
			<p class="middle pd-30"><em>{{:numsOnBiding}}</em>笔</p>
		</div>
		<div class="data-invent">
			<p class="normal"><i class="icon-num icon-03"></i>待还笔数（笔）</p>
			<p class="middle pd-30"><em>{{:unBackNums}}</em>笔</p>
		</div>
		<div class="data-invent">
			<p class="normal"><i class="icon-num icon-04"></i>累计借款金额（元）</p>
			<p class="middle pd-30"><em>{{selectX:allLoanMoney}}</em>元</p>
		</div>
		<div class="data-invent">
			<p class="normal"><i class="icon-num icon-05"></i>招标中金额（元）</p>
			<p class="middle pd-30"><em>{{:moneyOnBiding}}</em>元</p>
		</div>
		<div class="data-invent">
			<p class="normal"><i class="icon-num icon-06"></i>待还金额（元）</p>
			<p class="middle pd-30"><em>{{selectX:unBackMoney}}</em>元</p>
		</div>
	</script>
