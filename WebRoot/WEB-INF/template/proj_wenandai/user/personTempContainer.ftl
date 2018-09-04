<!--产品列表模版-->
<script id="temp" type="text/x-jsrender">
		<div class="num-icon fl" style="width:310px; margin-right:30px;">
			<div class="icon-img icon-img-four fl"></div>
			<div class="num-data fl">
				<p class="normal">昨日收益(元)</p>
				<p class="middle"><em>{{:earningsYesterday}}</em>元</p>
				<p class="normal">账户余额(元)<span><em>{{:accountBalance}}</em>元</span><i class="eyes"></i></p>
			</div>
		</div>
		<div class="num-icon fl">
			<div class="num-data fl">
				<div class="datd-list">
					<span class="w12"><em class="user-icon icon1"></em>累计收益(元)</span>
					<span class="f16">{{:accumulatedEarnings}}<em class="f12"> 元</em></span>
				</div>
				<div class="datd-list">
					<span class="w12"><em class="user-icon icon2"></em>尚未记息金额(元)</span>
					<span class="f16">{{:leviticusInterestMoney}}<em class="f12"> 元</em></span>
				</div>
			</div>
		</div>
		<div class="num-icon fl">
			<div class="icon-img icon-img-two fl"></div>
			<div class="num-data fl">
				<p class="normal">近一月赚取(元)</p>
				<p class="middle"><em>{{:nearlyMoney}}</em>元</p>
				<p class="btn">
					<a href="${base}/financeProduct/buyProductPlFinanceProduct.do" class="btn_bg1">转入</a>
					<a href="${base}/financeProduct/persontransferFromProductPlFinanceProduct.do" class="btn_bg1">转出</a>
				</p>
			</div>
		</div>
</script>

<!--收益模版-->
<script id="page_tmp0" type="text/x-jsrender">
	 <tr>
         <#--<td align="center" class="td-top"  style="padding-left:15px;white-space:nowrap;overflow: hidden;text-overflow: ellipsis;"><a style="color:#0096c4;font-size:14px;" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:planId}}" title="{{:projectName}}">{{:mmName}}</a></td>--> 
          <td align="center">{{:dealDate}}</td>
          <td align="center">{{:amont}}元</td>
          <td align="center">{{:dealtypeName}}</td>
          <#--<td align="center" >
      		<span>
          		<a href="javascript:void(0);" onClick="clickFinancing({{:planId}},'{{:orderNo}}','Financing')"  class="btn1">回款计划</a>
              	<a  href="{{isurl:url}} " class="btn2">合同</a>
          	</span>
          </td>-->
     <tr>
</script>
<!--转入模板-->
<script id="page_tmp1" type="text/x-jsrender">
	<tr>
      <#--<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
	  	  <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr={{:keystr}}&mmplanId={{:mmplanId}}" target="_blank" title="{{:mmName}}">
				<span class="tit">{{:mmName}}</span><br/>
		  </a>
	  </td>-->
      <td align="center">{{:dealDate}}</td>
      <td align="center">{{:amont}}元</td>
      <td align="center">{{:dealtypeName}}</td>
      
    </tr>
</script>


<!--转出模板-->
<script id="page_tmp2" type="text/x-jsrender">
	<tr>
	    <#--<td align="center" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr={{:keystr}}&mmplanId={{:mmplanId}}" target="_blank" title="{{:mmName}}"><span class="tit">{{:mmName}}</span></a>
		</td>-->
	    <td align="center">{{:dealDate}}</td>
      	<td align="center">{{:amont}}元</td>
      	<td align="center">{{:dealtypeName}}</td>
    </tr>
</script>



<!--理财产品种类-->
<#--<script id="page_tmp7" type="text/x-jsrender">
    <option value="{{:manageMoneyTypeId}}">{{:name}}</option>	
</script>-->
