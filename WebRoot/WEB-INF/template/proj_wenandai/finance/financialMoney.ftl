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
	$(function(){
	   $("#financialForm").click(function(){
				var mmplanId=$("#mmplanId").val();
				var intentDate=$("#intentDate").val();
		        var notMoney=$("#notMoney").val();
				parent.window.location.href=basepath+"/pay/repayMentByFinancialPay.do?mmplanId="+mmplanId
					+"&intentDate="+intentDate+"&notMoney="+notMoney
		        var index = parent.layer.getFrameIndex(window.name);
		        parent.layer.close(index);
		});
		
		$("#back").click(function(){debugger
		        var index = parent.layer.getFrameIndex(window.name);
		        parent.layer.close(index);
		});
	});
	
	function repayFinance(){
		var mmplanId=$("#mmplanId").val();
		var intentDate=$("#intentDate").val();
        var notMoney=$("#notMoney").val();
		parent.window.location.href=basepath+"/pay/repayMentByFinancialPay.do?mmplanId="+mmplanId
			+"&intentDate="+intentDate+"&notMoney="+notMoney
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	}
	
	function back(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	}
</script>
</head>
<body>
<form id="compensatory_Form"  method="post" action="${base}/pay/repayMentByFinancialPay.do" >
<input type="hidden" id="mmplanId" name="mid" value="${showManageMoney.mmplanId}"/> 
<input type="hidden" id="intentDate" name="idate" value="${showManageMoney.intentDate}"/> 
<input type="hidden" id="notMoney" name="nmoney" value="${showManageMoney.repaymentTotal}"/> 
<div class="tc-box" style="width:590px; border:0;">
<#if showManageMoney!=null >
    <ul class="ul-two bdr">
    	<p>派息明细</p>
    	<li style="width:155px;">派息金额：￥${showManageMoney.repaymentTotal?string(',##0.00')}</li>
        <li style="width:155px;">本金：￥${showManageMoney.principal?string(',##0.00')}</li>
        <li style="width:155px;" class="mr-0">利息：￥${showManageMoney.loanInterest?string(',##0.00')}</li>
        <li style="width:155px;" >应还日期：${showManageMoney.intentDate}</li>
    </ul>
    <#else>
     <ul class="ul-two bdr">
    	<p>派息明细</p>
    	<li>派息金额：￥8000.00</li>
        <li>本金：￥6000.00</li>
        <li class="mr-0">利息：￥2000.00</li>
        <li>应还日期2016-03-29</li>
    </ul>
   </#if> 
    <div class="btn-box">
    	<a href="javascript:void(0);" onclick="repayFinance();"  id="financialForm">确认派息</a>
        <a href="javascript:void(0);"    onclick="back();" id="back">返回</a>
    </div>
</div>
</form>
</body>
</html>