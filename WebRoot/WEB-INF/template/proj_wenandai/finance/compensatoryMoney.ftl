<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${systemConfig.metaTitle} - 个人借款</title>
    <meta name="description" content="${systemConfig.metaTitle} - 个人借款,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 个人借款,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
<script type="text/javascript">
  function repayByFinance(){
		var planId=$("#planId").val();
		var intentDate=$("#intentDate").val();
        var notMoney=$("#notMoney").val();
		var orderNo=$("#orderNo").val();
		parent.window.location.href=basepath+"/pay/compensatoryPay.do?planId="+planId
			+"&intentDate="+intentDate+"&notMoney="+notMoney+"&orderNo="+orderNo
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	}
</script>
</head>
<body>
<form id="compensatory_Form"  method="post" action="${base}/pay/compensatoryPay.do" >
<input type="hidden" id="planId" name="pid" value="${showManageMoney.bidId}"/> 
<input type="hidden" id="intentDate" name="idate" value="${showManageMoney.intentDate}"/> 
<input type="hidden" id="notMoney" name="nmoney" value="${comtotalMoney}"/> 
<input type="hidden" id="orderNo" name="ono" value="${showManageMoney.orderNo}"/> 
<div class="tc-box">
<#if showManageMoney!=null >
	<div class="ul-one">
		<div class="tit-list">
	    	<p>项目名称：</p>
	    	<p><a href="#">${showManageMoney.proName}</a></p>
	    </div>
	    <div class="tit-list">
	    	<p>借款人</p>
	        <p>${showManageMoney.receiverName}</p>
	    </div>
	    <div class="tit-list">
	    	<p>还款期数</p>
	    	<p>第${showManageMoney.payintentPeriod}期</p>
	    </div>
    </div>
    <p class="tit-nema">预期款项明细</p>
    <table class="ul-two" border="0" cellspacing="0" cellpadding="0">
    	<tr>
    		<th>应还本金</th>
    		<th>利息金额</th>
    		<th>费用总额</th>
    		<th>应还日期</th>
    		<th>逾期天数</th>
    		<th>逾期罚息金额</th>
    	</tr>
    	<tr>
    		<td>${showManageMoney.principal?string(',##0.00')}</td>
    		<td>${showManageMoney.loanInterest?string(',##0.00')}</td>
    		<td>${showManageMoney.serviceMoney?string(',##0.00')}</td>
    		<td>${showManageMoney.intentDate}</td>
    		<td>${showManageMoney.punishDays}</td>
    		<td>${showManageMoney.accMoney?string(',##0.00')}</td>
    	</tr>
    </table>
    <p class="tit-nema">代偿结算清单</p>
    <table class="ul-two bdr">
    	<tr>
    		<th>代偿本金</th>
    		<th>代偿利息</th>
    		<th>代偿罚息</th>
    		<th>代偿费用</th>
    		<th>代偿合计</th>
    	</tr>
    	<tr>
    		<td>${comRepaymentMoney?string(',##0.00')}</td>
    		<td>${comInterestMoney?string(',##0.00')}</td>
    		<td>${comPublishlMoney?string(',##0.00')}</td>
    		<td>${comServiceMoney?string(',##0.00')}</td>
    		<td>${comtotalMoney?string(',##0.00')}</td>
    	</tr>
    </table>
    <#else>
    <div class="ul-one">
		<div class="tit-list">
	    	<p>项目名称：</p>
	    	<p><a href="#">11111</a></p>
	    </div>
	    <div class="tit-list">
	    	<p>借款人</p>
	        <p>张三</p>
	    </div>
	    <div class="tit-list">
	    	<p>还款期数</p>
	    	<p>第四期</p>
	    </div>
    </div>
     <p class="tit-nema">预期款项明细</p>
     <table class="ul-two" border="0" cellspacing="0" cellpadding="0">
    	<tr>
    		<th>应还本金</th>
    		<th>利息金额</th>
    		<th>费用总额</th>
    		<th>应还日期</th>
    		<th>逾期天数</th>
    		<th>逾期罚息金额</th>
    	</tr>
    	<tr>
    		<td>￥200,00</td>
    		<td>￥200,00</td>
    		<td>￥200,00</td>
    		<td>￥200,00</td>
    		<td>2016-03-03</td>
    		<td>5天</td>
    	</tr>
    </table>
    <p class="tit-nema">代偿结算清单</p>
    <table class="ul-two bdr">
    	<tr>
    		<th>代偿本金</th>
    		<th>代偿利息</th>
    		<th>代偿罚息</th>
    		<th>代偿费用</th>
    		<th>代偿合计</th>
    	</tr>
    	<tr>
    		<td>￥200,00</td>
    		<td>￥200,00</td>
    		<td>￥200,00</td>
    		<td>￥200,00</td>
    		<td>￥200,00</td>
    	</tr>
    </table>
   </#if> 
    <div class="btn-box">
    	<a href="javascript:;" onclick="repayByFinance();" id="compensatoryForm">确认代偿</a>
</div>
</form>
</body>
</html>