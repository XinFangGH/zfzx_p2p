


<#--已逾期的项目-->
<script type="text/x-jsrender" id="page_tmp1">
   <tr>
      <td align="center" class="td-top">
      <span class="tit">
      	<a title="{{:proName}}" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidId}}">{{:proName}}</a>
      	</span>
      <td align="center">{{:receiverName}}</td>
      <td align="center">{{:payintentPeriod}}</td>
      <td align="center">￥{{:principal}}</td>
      <td align="center">￥{{:loanInterest}}</td>
      <td align="center">￥{{:serviceMoney}}</td>
      <td align="center">{{formartDate:intentDate}}</td>
      <td align="center">{{:punishDays}}天</td>
      <td align="center">{{:accMoney}}</td>
       <td align="center" >
      <span style="display:inline-block;float:left;padding-left:30px;"><a href="javascript:void(0);"  onClick="clickCompensatory({{:bidId}},'{{:intentDate}}') " class="btn1">代偿</a>
       </span> </td>
      <td align="center" >
      <span><a href="javascript:void(0);"  onClick="clickPlan({{:bidId}},'guaranteeFin') " class="btn1 fl" style="line-height:18px;padding: 0 2px;">回款计划</a>
      <a  {{if url!=null&&url.length>2}}href="${base}/financePurchase/downLoadFinancePurchase.do?contractUrl={{:url}}"{{else}}href="javascript:void(0);" onclick="ht()"{{/if}} class="btn2 fr" style="line-height:18px;padding: 0 2px;">合同</a></span> </td>
    </tr>
</script>


<#--代偿项目-->
<script type="text/x-jsrender" id="page_tmp2">
   <tr>
      	<td align="center" class="td-top">
      	<span class="tit"><a style="color:#0096c4;font-size:14px;" title="{{:bidName}}" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:planId}}">{{:bidName}}</a></span>
       	<td align="center">{{:receiverName}}</td>
       	<td align="center">{{:payintentPeriod}}</td>
       	<td align="center">{{:compensatoryDate}}</td>
      	<td align="center">￥{{:compensatoryMoney}}</td>
      	<td align="center">￥{{:punishMoney}}</td>
      	<td align="center">￥{{:backAllMoney}}</td>
      	<td align="center">{{:backDate}}</td>
      	<td align="center" >
       	<span>
        	<a class="btn1" href="javascript:void(0)" class="btn2"  onClick="clickOfflineRegist({{:id}})">线下追偿登记</a>
      	</span></td>
    </tr>
</script>

<#--在保项目-->
<script type="text/x-jsrender" id="page_tmp3">
   <tr>
      <td align="center" class="td-top">
      <span class="tit"><a style="color:#0096c4;font-size:14px;" title="{{:bidProName}}" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidId}}">{{:bidProName}}</a></span>
      </td>
      <td align="center" >{{:receiverName}}</td>
      <td align="center">￥{{:bidMoney}}</td>
      <td align="center">{{:loanLife}}</td>
      <td align="center">{{:interestRate}}%</td>
      <!--<td align="center">{{:publishSingeTime}}</td>-->
      <td align="center" >
       <a>合同</a>
      </td>
  	  <!--<td align="center">{{:afterMoney}}</td>-->
   </tr>
</script>

<#--已结清项目-->
<script type="text/x-jsrender" id="page_tmp4">
   <tr>
      <td align="center" class="td-top">
      <span class="tit"><a style="color:#0096c4;font-size:14px;" title="{{:bidProName}}" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidId}}">{{:bidProName}}</a></span>
      <td align="center">{{:receiverName}}</td>
      <td align="center">￥{{:bidMoney}}</td>
      <td align="center">{{:loanLife}}</td>
      <td align="center">{{:interestRate}}%</td>
      <td align="center" >
       <a>合同</a>
      </td>
    </tr>
</script>