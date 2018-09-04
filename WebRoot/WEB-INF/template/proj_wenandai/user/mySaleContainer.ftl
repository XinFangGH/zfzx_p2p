<#--顶层的统计数据-->
<script id="temp" type="text/x-jsrender">
	<div class="data-invent">
		<p class="normal"><i class="icon-num icon-01"></i>预售中（个）</p>
		<p class="middle pd-30"><em>{{:unPublic}}</em>个</p>
	</div>
	<div class="data-invent">
		<p class="normal"><i class="icon-num icon-02"></i>招标中（个）</p>
		<p class="middle pd-30"><em>{{:bidingCount}}</em>个</p>
	</div>
	<div class="data-invent">
		<p class="normal"><i class="icon-num icon-03"></i>回款中（个）</p>
		<p class="middle pd-30"><em>{{:backingCount}}</em>个</p>
	</div>
	<div class="data-invent">
		<p class="normal"><i class="icon-num icon-04"></i>已完成（个）</p>
		<p class="middle pd-30"><em>{{:complateCount}}</em>个</p>
	</div>
</script>
<script id="temp-1" type="text/x-jsrender">
	<div class="data-invent">
    	<p class="normal"><i class="icon-num icon-01"></i>已齐标（个）</p>
    	<p class="middle pd-30"><em>{{:bidFullCount}}</em>个</p>
    </div>
</script>

<script id="temp-2" type="text/x-jsrender">
	<div class="data-invent">
		<p class="normal"><i class="icon-num icon-01"></i>应派本金（元）</p>
		<p class="middle pd-30"><em>{{formatNum:unbackRepayment}}</em>元</p>
	</div>
	<div class="data-invent">
		<p class="normal"><i class="icon-num icon-02"></i>应派利息（元）</p>
		<p class="middle pd-30"><em>{{formatNum:unbackInterest}}</em>元</p>
	</div>
</script>


<script type="text/x-jsrender" id="page_tmp0">
	<tr class="ff">
	  	<td align="center" class="td-top"  style="padding-left:15px;white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
	<a style="color:#0096c4;font-size:14px;" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId={{:mmplanId}}" title="{{:mmName}}">{{:mmName}}</a>
	</td>
	  	<td align="center">￥{{:sumMoney}}元</td>
	  	<td align="center">{{:yeaRate}}%</td>
	  	<td align="center">{{:investlimit}}月</td>
	  	<td align="center">{{:buyEndTime}}</td>
	</tr>
</script>

<#--回款中-->
<script type="text/x-jsrender" id="page_tmp7">
	<tr>
		<td align="center" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr={{:keystr}}&mmplanId={{:mmplanId}}" target="_blank" title="{{:mmName}}"><span class="tit">{{:mmName}}</span></a>
		</td>
		<td  align="center" style="padding-right:5px">
			{{if sumMoney==0}}
				0.00
			{{else}}
				{{if sumMoney < 1000}}
					{{:sumMoney}}
				{{else}}
					{{:sumMoney}}
				{{/if}}
			{{/if}}元
		</td>
		<td  align="center" style="padding-right:5px">
			{{:interestRate}}%
		</td>
		<td  align="center" style="padding-right:5px">
			{{:investlimit}}月
		</td>
		<td  align="center" style="padding-right:5px">
			{{formartDate:lockingEndDate}}
		</td>
		<td  align="center" style="padding-right:5px">
			{{formartDate:intentDate}}
		</td>
		<td  align="center" style="padding-right:5px">
			{{if repaymentTotal==0}}
				0.00
			{{else}}
				{{if repaymentTotal < 1000}}
					{{:repaymentTotal}}
				{{else}}
					{{:repaymentTotal}}
				{{/if}}
			{{/if}}元
		</td>
		<td align="center">
			<a href="javascript:void(0);"  onClick="clickPlan({{:mmplanId}},'Finperson')">
				<span class="normal">回款计划</span>
			</a>
		</td>  
		<td align="center" >
			<span style="display:inline-block;">
				<a href="javascript:void(0);"  onClick="clickFinancial({{:mmplanId}},'{{:intentDate}}') " class="btn1">
					派息
				</a>
				<span class="last">
				</span>
			</span> 
		</td>
	<tr>
</script>

<#--已齐标-->
<script type="text/x-jsrender" id="page_tmp2">
	<tr>
		<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr={{:keystr}}&mmplanId={{:mmplanId}}" target="_blank" title="{{:mmName}}">
				<span class="tit">
					{{:mmName}}
				</span><br/>
			</a>
		</td>
		<td align="center" >
			{{if sumMoney==0}}
				0.00
			{{else}}
				{{if sumMoney < 1000}}
					{{:sumMoney}}
				{{else}}	
					{{:sumMoney}}
				{{/if}}
			{{/if}}元
		</td>
		<td  align="center" style="padding-right:5px">
			{{:interestRate}}%
		</td>
		<td  align="center" style="padding-right:5px">
			{{:investlimit}}月
		</td>
		<td align="center">
			{{formartDate:buyStartTime}}
		</td>
		<td align="center">
			{{:joinCount}}
		</td>
	</tr>
</script>

<#--招标中-->
<script type="text/x-jsrender" id="page_tmp1">
	<tr>
		<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr={{:keystr}}&mmplanId={{:mmplanId}}" target="_blank" title="{{:mmName}}">
				<span class="tit">{{:mmName}}</span><br/>
			</a>
		</td>
		<td align="center" >
		    {{if sumMoney==0}}
		    	0.00
			{{else}}
				{{if sumMoney < 1000}}
					{{:sumMoney}}
				{{else}}
					{{:sumMoney}}
				{{/if}}	
			{{/if}}元
		</td>
		<td  align="center" style="padding-right:5px">
			{{:interestRate}}%
		</td>
		<td  align="center" style="padding-right:5px">
			{{:investlimit}}月
		</td>
		<td align="center">
			{{formartDate:buyStartTime}}
		</td>
		<td align="center">
			{{:joinCount}}
		</td>
		<td align="center" >
			{{if remainingMoney==0}}
				0.00
			{{else}}
				{{if remainingMoney < 1000}}
					{{:remainingMoney}}
				{{else}}
					{{:remainingMoney}}
				{{/if}}
			{{/if}}元
		</td>
	</tr>
</script>
<#--预售中-->
<script type="text/x-jsrender" id="page_tmp5">
	<tr>
		<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr={{:keystr}}&mmplanId={{:mmplanId}}" target="_blank" title="{{:mmName}}">
				<span class="tit">{{:mmName}}</span><br/>
			</a>
		</td>
		<td align="center" >
			{{if sumMoney==0}}
				0.00
			{{else}}
			    {{if sumMoney < 1000}}
					{{:sumMoney}}
				{{else}}	
					{{:sumMoney}}
				{{/if}}
			{{/if}}元
		</td>
		<td  align="center" style="padding-right:5px">
			{{:interestRate}}%
		</td>
		<td  align="center" style="padding-right:5px">
			{{:investlimit}}月
		</td>
		<td align="center">
			{{formartDate:preSaleTime}}
		</td>
		<td align="center">
			{{formartDate:buyStartTime}}
		</td>
	</tr>
</script>

<#--已完成-->
<script type="text/x-jsrender" id="page_tmp10">
	<tr>
		<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr={{:keystr}}&mmplanId={{:mmplanId}}" target="_blank" title="{{:mmName}}">
				<span class="tit">{{:mmName}}</span><br/>
			</a>
		</td>
		<td align="center" >
			{{if sumMoney==0}}
				0.00
			{{else}}
				{{if sumMoney < 1000}}
					{{:sumMoney}}
				{{else}}	
					{{:sumMoney}}
				{{/if}}
			{{/if}}元
		</td>
		<td  align="center" style="padding-right:5px">
			{{:interestRate}}%
		</td>
		<td  align="center" style="padding-right:5px">
			{{:investlimit}}月
		</td>
		<td align="center" >
			{{if allMoney==0}}
				0.00
			{{else}}
				{{if allMoney < 1000}}
					{{:allMoney}}
				{{else}}	
					{{:allMoney}}
				{{/if}}
			{{/if}}元
		</td>
		<td align="center">正常完成/提前退出</td>
		<td align="center">
			<a href="javascript:void(0);"  onClick="clickPlan1({{:mmplanId}},'Finperson')">
				<span class="normal">回款计划</span>
			</a></td>
		<td align="center">合同</td>
	</tr>
</script>

<#--退出审核-->
<script type="text/x-jsrender" id="page_tmp8">
	<tr>
		<td align="center" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr={{:keystr}}&mmplanId={{:mmplanId}}" target="_blank" title="{{:mmName}}"><span class="tit">{{:mmName}}</span></a>
		</td>
		<td  align="center" style="padding-right:5px">
			{{:investPersonName}}
		</td>
		<td  align="center" style="padding-right:5px">
			{{if buyMoney==0}}
				0.00
			{{else}}
				{{if buyMoney < 1000}}
					{{:buyMoney}}
				{{else}}
					{{:buyMoney}}
				{{/if}}	
			{{/if}}元
		</td>
		<td  align="center" style="padding-right:5px">
			{{formartDate:startinInterestTime}}
		</td>
		<td  align="center" style="padding-right:5px">
			{{formartDate:endinInterestTime}}
		</td>
		<td  align="center" style="padding-right:5px">
			{{formartDate:earlyDate}}
		</td>
		<td  align="center" style="padding-right:5px">
			{{if checkStatus==0}}
				未审核
			{{else}}
				{{if checkStatus==1}}
					已通过
				{{else}}
					已驳回
				{{/if}}
			{{/if}}
		</td>
		<td align="center" >
			<span style="display:inline-block;">
				{{if checkStatus==0}}
					<a href="javascript:void(0);"  onClick="clickEarly('{{:earlyRedemptionId}}') " class="btn1">
						审核
				{{else}}		
					审核
				{{/if}}
				</a>
				<span class="last"></span>
			</span> 
			{{if keystr=='UPlan'}}
				<a class="btn1" target="_blank" href="${base}/creditFlow/financingAgency/getClaimsListPlManageMoneyPlanBuyinfo.do?orderId={{:orderId}}" class="btn2">债权</a>
			{{else}}
				<a class="btn1" target="_blank" href="${base}/creditFlow/financingAgency/getClaimsListPlManageMoneyPlanBuyinfo.do?orderId={{:orderId}}" class="btn2">债权</a>
			{{/if}}
		</td>
	<tr>
</script>
