<!--产品列表模版-->
<script id="temp" type="text/x-jsrender">
	<div class="num-icon fl">
		<div class="icon-img icon-img-one fl"></div>
			<div class="num-data fl">
				<p class="normal">累计赚取收益(元)</p>
				<p class="middle"><em>{{isNull:allBenefit}}</em>元</p>
			</div>
		</div>
		<div class="num-icon fl">
			<div class="icon-img icon-img-two fl"></div>
			<div class="num-data fl">
				<p class="normal">累计投资(笔)</p>
				<p class="middle"><em>{{isNull:allBidTimes}}</em>笔</p>
			</div>
		</div>
		<div class="num-icon fl">
			<div class="icon-img icon-img-three fl"></div>
			<div class="num-data fl">
				<p class="normal">累计投资金额(元)</p>
				<p class="middle"><em>{{isNull:allBidMoney}}</em>元</p>
			</div>
	   	</div>	
</script>

<!--购买中模版-->
<script id="page_tmp1" type="text/x-jsrender">
	 <tr>
          <td align="center" class="td-top"><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:planId}}" title="{{:projectName}}">{{:mmName}}</a></td>
          <td align="center">{{:buyMoney}}元</td>
          <td align="center">{{:promisYearRate}}%</td>
          <td align="center">{{:buyDatetime}}</td>
          <td align="center">{{:endinInterestTime}}</td>
          <td align="center">{{:orderlimit}}天</td>
          <td align="center">{{:promisIncomeSum}}元</td>
          <#--<td align="center" >
      		<span>
          		<a href="javascript:void(0);" onClick="clickFinancing({{:planId}},'{{:orderNo}}','Financing')"  class="btn1">回款计划</a>
              	<a  href="{{isurl:url}} " class="btn2">合同</a>
          	</span>
          </td>-->
     <tr>
</script>


<!--持有中-->
<script id="page_tmp2" type="text/x-jsrender">
	<tr>
      <td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
	  	  <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr={{:keystr}}&mmplanId={{:mmplanId}}" target="_blank" title="{{:mmName}}">
				<span class="tit">{{:mmName}}</span><br/>
		  </a>
	  </td>
      <td align="center">{{:buyMoney}}元</td>
      <td align="center">{{:promisYearRate}}%</td>
      <td align="center">{{:promisIncomeSum}}元</td>
      <td align="center">{{:buyDatetime}}</td>
      <td align="center">{{:endinInterestTime}}</td>
      <td align="center">
      		<a class="btn1" href="javascript:void(0)" class="btn2"  onClick="clickAssigninterest({{:orderId}})">回款</a>
			{{if keystr == 'UPlan'}}
				<a class="btn1" href="${base}/creditFlow/financingAgency/getClaimsListPlManageMoneyPlanBuyinfo.do?orderId={{:orderId}}&type = '1'" class="btn2">债权</a>
			{{else}}
				<a class="btn1" target="_blank" href="${base}/creditFlow/financingAgency/getClaimsListPlManageMoneyPlanBuyinfo.do?orderId={{:orderId}}" class="btn2">债权</a>
			{{/if}}
				<a class="btn1" target="_blank" href="${base}/creditFlow/financingAgency/buyInfoContractPlManageMoneyPlan.do?orderId={{:orderId}}" class="btn2"  >合同</a>
			{{if state == 7}}
				<a  href="javascript:void(0)" class="btn2">退审中</a> 
			{{else}}
				<a  href="javascript:void(0)" class="btn2"  onClick="clickearlyout({{:orderId}},'{{:lockingEndDate}}')">退出</a>
			{{/if}}
	  </td>
    </tr>
</script>


<!--已完成-->
<script id="page_tmp10" type="text/x-jsrender">
	<tr>
	    <td align="center" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr={{:keystr}}&mmplanId={{:mmplanId}}" target="_blank" title="{{:mmName}}"><span class="tit">{{:mmName}}</span></a>
		</td>
	    <td align="center">{{:buyMoney}}</td>
	    <td align="center">{{:promisYearRate}}元</td>
	    <td align="center">{{:promisIncomeSum}}</td>
	    <td align="center">{{:endinInterestTime}}%</td>
	    <td align="center" >
	    	<#--<a class="btn1" href="javascript:void(0)" class="btn2"  onClick="clickAssigninterest({{:list.orderId}})">回款</a>
			<a href="javascript:void(0)" class="btn1" {{if keystr=='UPlan'}} onClick="clickClaimsList({{:orderId}}) {{else}} onClick="clickorMatchingDetail({{:orderId}})" {{/#if}}债权</a>
			<a class="btn1" target="_blank" href="${base}/creditFlow/financingAgency/buyInfoContractPlManageMoneyPlan.do?orderId={{:orderId}}" class="btn2"  >合同</a>-->
	    </td>
    </tr>
</script>


<!--已退出-->
<script id="page_tmp8" type="text/x-jsrender">
	<tr>
		<td align="center" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr={{:keystr}}&mmplanId={{:mmplanId}}" target="_blank" title="{{:mmName}}"><span class="tit">{{:mmName}}</span></a>
		</td>
	    <td align="center">{{:buyMoney}}</td>
	    <td align="center">{{:promisYearRate}}元</td>
	    <td align="center">{{:promisIncomeSum}}</td>
	    <td align="center">{{:earlierOutDate}}%</td>  
	    <td align="center">{{:endinInterestTime}}%</td>  
	    <td align="center">
			<a href="javascript:void(0)" class="btn1"  onClick="{{if keystr == 'UPlan'}}clickClaimsList({{:orderId}}){{else}}clickorMatchingDetail({{:orderId}}){{/if}}">债权</a>
			<a class="btn1" target="_blank" href="${base}/creditFlow/financingAgency/buyInfoContractPlManageMoneyPlan.do?orderId={{:orderId}}" class="btn2"  >合同</a>
		</td>                
    </tr>
</script>



<!--已失败-->
<script id="page_tmp_2" type="text/x-jsrender">
	<tr >
          <td align="center" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			  <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr={{:keystr}}&mmplanId={{:mmplanId}}" target="_blank" title="{{:mmName}"><span class="tit">{{:mmName}}</span></a>
		  </td>
          <td align="center" style="padding-right:5px">{{:buyMoney}}元</td>
          <td align="center" style="padding-right:5px">{{:promisYearRate}}%</td>
          <td align="center" style="padding-right:5px">{{:buyDatetime}}</td>
          <td align="center" style="padding-right:5px">{{:buyEndTime}}</td>                
          <td align="center" style="padding-right:5px">{{:orderlimit}}天</td>
          <td align="center" style="padding-right:5px">{{:promisIncomeSum}}元</td>
    </tr>
</script>

<!--理财产品种类-->
<script id="page_tmp7" type="text/x-jsrender">
    <option value="{{:manageMoneyTypeId}}">{{:name}}</option>	
</script>
