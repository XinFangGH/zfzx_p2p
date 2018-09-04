<#--模板文件开始-->
<#--数据统计模板-->
<script id="temp" type="text/x-jsrender">
	<div class="num-icon fl">
		<div class="icon-img icon-img-one fl"></div>
		<div class="num-data fl">
			<p class="normal">可交易债权总额(元)</p>
			<p class="middle">
			<em>
			{{if canTransferSale == null}}
			    0
			{{else}}
			    {{:canTransferSale}}
			{{/if}}
			</em>元</p>
		</div>
	</div>
	<div class="num-icon fl">
		<div class="icon-img icon-img-two fl"></div>
		<div class="num-data fl">
			<p class="normal">已交易债权总额(元)</p>
			<p class="middle"><em>{{substr:transferedMoney}}</em>元</p>
		</div>
	</div>
	<div class="num-icon fl">
		<div class="icon-img icon-img-three fl"></div>
		<div class="num-data fl">
			<p class="normal">成功交易笔数(笔)</p>
			<p class="middle"><em>{{:successDeals}}</em>笔</p>
		</div>
	</div>
</script>

<#--已购买债权-->
<script id="page_tmp2" type="text/x-jsrender">
	<tr>
		<td align="center" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidPlanID}}" target="_blank" title="{{:bidProName}}(到期日:{{:intentDate}};年化利率 :{{:yearAccrualRate}}%)">
				<span class="tit">
					{{:bidProName}}
				</span>
			<div class="normal gray" style="text-indent:20px;">(到期日:{{formartDate:intentDate}};年化利率 :{{:yearAccrualRate}}%)</div></a>
		</td>
		<td align="center">
			{{if saleMoney == 0}}
			    0.00
			{{else}}
			    {{if saleMoney < 1000}}
			    	{{:saleMoney}}
			    {{else}}
			    	{{:saleMoney}}
			    {{/if}}
			{{/if}}元
		</td>
		<td align="center">
		    {{if changeMoney==0}}
		    	{{:changeMoney}}
		    {{else}}
		    	{{if  changeMoneyType==0}}
		    		+
		    	{{else}}
		    		-
		    	{{/if}}
		    	{{if changeMoney==0}}
		    		0.00
		    	{{else}}
		    		{{if changeMoney < 1000}}	
		    			{{:changeMoney}}
		    		{{else}}
		    		    {{:changeMoney}}
		    		{{/if}}
		    	{{/if}}
		    {{/if}}元
		</td>
		<td align="center">
			{{if changeMoneyRate==0}}
				{{:changeMoneyRate}}
			{{else}}
				{{if changeMoneyType==0}}
					+
				{{else}}
					-
				{{/if}}
				{{:changeMoneyRate}}
			{{/if}}‰
		</td>
		<td align="center" style="padding-right:5px">
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
		<td align="center">
			{{:saleSuccessTime}}
		</td>
		<td align="center">
		<span style="display:inline-block;float:right;"><a href="javascript:void(0);" onClick="clickOrStartTransferFormsee({{:bidInfoID}},{{:yearAccrualRate}},'{{:intentDate}}','{{:startDate}}',{{:id}});" class="btn1">结算清单</a>
		<a href="${base}/creditFlow/financingAgency/contractPlBidSale.do?saleId={{:id}}" class="btn2">合同</a><span class="last"></span></span>
		</td>
	</tr>
</script>


<#--已卖出的债权-->
<script id="page_tmp10" type="text/x-jsrender">
	<tr>
		<td align="center" class="td-top" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidPlanID}}" target="_blank" title="{{:bidProName}}(到期日:{{:intentDate}};年化利率 :{{:yearAccrualRate}}%)">
				<span class="tit">
					{{:bidProName}}
				</span>
				<div class="normal gray" style="text-indent:20px;">
					(到期日:{{formartDate:intentDate}};年化利率 :{{:yearAccrualRate}}%)
				</div>
			</a>
		</td>
		<td align="center">
			{{if saleMoney==0}}
				0.00
			{{else}}
				{{if saleMoney < 1000}}
					{{:saleMoney}}
				{{else}}
					{{:saleMoney}}
				{{/if}}
			{{/if}}元
		</td>
		<td align="center">
			{{if changeMoney==0}}
				{{:changeMoney}}
			{{else}}
				{{if changeMoneyType==0}}+
				{{else}}-
				{{/if}}
				{{if changeMoney==0}}
					0.00
				{{else}}
					{{if changeMoney < 1000}}
					    {{:changeMoney}}
					{{else}}
						{{:changeMoney}}
					{{/if}}
				{{/if}}
			{{/if}}元			    	
		</td>
		<td align="center">
			{{if changeMoneyRate==0}}
				{{:changeMoneyRate}}
			{{else}}
				{{if changeMoneyType==0}}
					+
				{{else}}
					-
				{{/if}}
				{{:changeMoneyRate}}
			{{/if}}‰
		</td>
		<td align="center">
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
		<td align="center">
			{{:saleSuccessTime}}
		</td>
		<td align="center">
			<span>
				<a href="javascript:void(0);" onClick="clickOrStartTransferFormsee({{:bidInfoID}},{{:yearAccrualRate}},'{{:intentDate}}','{{:startDate}}','{{:id}}')" class="btn1">结算清单</a>
				  <#-- <a href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=invest&contractUrl=${list.url}" class="btn2">转让合同</a></span></td>-->
				<a href="${base}/creditFlow/financingAgency/contractPlBidSale.do?saleId={{:id}}" class="btn2">合同</a><span class="last"></span>
			</span>
		</td>
	</tr>
</script>

<#--交易中的债权-->
<script id="page_tmp1" type="text/x-jsrender">
	<tr>
		<td align="center" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidPlanID}}" target="_blank" title="{{:bidProName}}(到期日:{{:intentDate}};年化利率:{{:yearAccrualRate}}%)"><span class="tit">{{:bidProName}}</span><div class="normal gray" style="text-indent:20px;">(到期日:{{formartDate:intentDate}};年化利率 :{{:yearAccrualRate}}%)</div></a>
		</td>
		<td align="center">
			{{if saleMoney == 0}}
				0.00
			{{else}}
				{{if saleMoney < 1000}}
					{{:saleMoney}}
				{{else}}
					{{:saleMoney}}
				{{/if}}
			{{/if}}元
		</td>
		<td align="center">
			{{if changeMoney==0}}
				{{:changeMoney}}
			{{else}}
				{{if changeMoneyType==0}} +
				{{else}}
				-
				{{/if}}
			{{if changeMoney==0}}
				0.00
			{{else}}
				{{if changeMoney<1000}}
					{{:changeMoney}}
				{{else}}
					{{:changeMoney}}{{/if}}
				{{/if}}
			{{/if}}元
		</td>
		<td align="center">
			{{if changeMoneyRate==0}}
				{{:changeMoneyRate}}
			{{else}}
				{{if changeMoneyType==0}} +
				{{else}}
				-
				{{/if}}
				{{:changeMoneyRate}}
			{{/if}}‰
		</td>
		<td align="center">
			{{:saleStartTime}}
		</td>
		<td align="center">
			<span>
				{{if saleStatus==3}}
				    <a href="javascript:void(0);"   class="btn1">正在交易</a>
				{{else}}
				 	<a  href="${base}/creditFlow/financingAgency/updatePlBidSale.do?id={{:id}}" class="btn1">取消交易</a>
				{{/if}}
				    <a  href="javascript:void(0);" onClick="clickOrStartTransferFormsee({{:bidInfoID}},{{:yearAccrualRate}},'{{:intentDate}}','{{:startDate}}','{{:id}}')" class="btn2">结算清单</a>
			</span>
		 </td>
	</tr>
</script>

<#--可交易债权-->
<script id="page_tmp7" type="text/x-jsrender">
    <tr>
		<td align="center">
			<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidPlanID}}" target="_blank" title="{{:bidProName}}(到期日:{{:intentDate}};年化利率 :{{:yearAccrualRate}}%)"><span class="tit">{{:bidProName}}</span><div class="normal gray" style="text-indent:20px;">(到期日:{{formartDate:intentDate}};年化利率 :{{:yearAccrualRate:}}%)</div></a>
		</td>
		<td align="center" style="padding-right:5px">
			{{if saleMoney == 0}}
			    0.00
				{{else}}
					{{if saleMoney < 1000}}
					    {{:saleMoney}}
					{{else}}
						{{:saleMoney}}
					{{/if}}
			{{/if}}元
		</td>
		<td align="center">
			<span style="display:inline-block;">
	      	{{if id == null || id == 0}}
	      		<a href="javascript:void(0);"  onClick="clickOrStartTransferForm({{:bidInfoID}},{{:yearAccrualRate}},'{{:intentDate}}','{{:startDate}}')" class="btn1">挂牌交易</a>
	      	{{else}}
	      		<a href="javascript:void(0);"   class="btn1">正在挂牌</a>
	      	{{/if}}  
	       		<a href="javascript:void(0);" onClick="clickFinancing({{:bidPlanID}},'{{:orderNo}}','Financing')" class="btn1">回款计划</a>
	      </td>
	  </tr>
</script>

<#--已关闭的债权-->
<script id="page_tmp3" type="text/x-jsrender">
	<tr class="ff">
		<td align="center" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidPlanID}}" target="_blank" title="{{:bidProName}}(到期日:{{:intentDate}};年化利率 :{{:yearAccrualRate}}%)">
				<span class="tit">
					{{:bidProName}}
				</span>
				<div class="normal gray" style="text-indent:20px;">
					(到期日:{{formartDate:intentDate}};年化利率 :{{:yearAccrualRate}}%)
				</div>
			</a>
		</td>
		<td align="center">
			{{if saleMoney==0}}
				0.00
			{{else}}
				{{if saleMoney < 1000}}
					{{:saleMoney}}
				{{else}}
					{{:saleMoney}}
				{{/if}}
			{{/if}}元
		</td>
		<td align="center">
			{{if changeMoney==0}}
				{{:changeMoney}}
			{{else}}
			    {{if changeMoneyType==0}}
			    	+
			    {{else}}
			    	-
			    {{/if}}
			    {{if changeMoney==0}}
			    	0.00
			    {{else}}
			    	{{if changeMoney < 1000}}
			    		{{:changeMoney}}
			    	{{else}}
			    		{{:changeMoney}}
			    	{{/if}}
			    {{/if}}
			{{/if}}元
		</td>
		<td align="center">
			{{if changeMoneyRate==0}}
				{{:changeMoneyRate}}
			{{else}}
				{{if changeMoneyType==0}}
					+
				{{else}}
					-
				{{/if}}
				{{:changeMoneyRate}}
			{{/if}}‰				
		</td>
		<td align="center">
		    {{:saleCloseTime}}
		</td>
	</tr>
</script>
