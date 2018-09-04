<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${systemConfig.metaTitle} - 个人借款</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
<script type="text/javascript" >
	$(function(){
		 $("#advanceFinancialForm").click(function(){
			var earlyRedemptionId=$("#earlyRedemptionId").val();
	        var sumMoney=$("#sumMoney").val();
			parent.window.location.href=basepath+"/pay/repayMentAdvanceByFinancialPay.do?earlyRedemptionId="+earlyRedemptionId
				+"&notMoney="+sumMoney
	        var index = parent.layer.getFrameIndex(window.name);
	        parent.layer.close(index);
		});
	});
	function early(){
		var earlyRedemptionId=$("#earlyRedemptionId").val();
	    var sumMoney=$("#sumMoney").val();
	     var liquid = $("#liquid").val();
		parent.window.location.href=basepath+"/pay/repayMentAdvanceByFinancialPay.do?earlyRedemptionId="+earlyRedemptionId
			+"&notMoney="+sumMoney+"&liquid="+liquid;
	    var index = parent.layer.getFrameIndex(window.name);
	    parent.layer.close(index);
	}
	
	function reject(){
		var earlyRedemptionId=$("#earlyRedemptionId").val();
	    var sumMoney=$("#sumMoney").val();
	   
		parent.window.location.href=basepath+"/creditFlow/financingAgency/rejectedByFinancialPlManageMoneyPlanBuyinfo.do?earlyRedemptionId="+earlyRedemptionId
			+"&notMoney="+sumMoney
	    var index = parent.layer.getFrameIndex(window.name);
	    parent.layer.close(index);
	}
</script>
</head>
<body>
<form id="compensatory_Form"  method="post" action="${base}/pay/repayMentByFinancialPay.do" >
<input type="hidden" id="earlyRedemptionId" name="eid" value="${earlyRedemptionId}"/> 
<input type="hidden" id="sumMoney" name="smoney" value="${sumMoney}"/> 
<input type="hidden" id="principal" name="principal" value="${principalRepaymenMoney}"/>
<input type="hidden" id="interest" name="interest" value="${loanInterestMoney}" />
<input type="hidden" id="liquid" name="liquidatedDamagesMoney" value="${liquidatedDamagesMoney}" />
<div class="tc-box" style="width:590px; border:0;">
    <ul class="ul-two bdr">
    	<p>提前支取审核</p>
    	<li style="width:155px;">投资人：${investPersonName}</li>
        <li style="width:155px;">本金：${principalRepaymenMoney?string(',##0.00')}元</li>
        <li style="width:155px;" class="mr-0">欠派利息：${loanInterestMoney?string(',##0.00')}元</li>
        <li style="width:220px;" >提前退出违约金：${liquidatedDamagesMoney?string(',##0.00')}元</li>
        <li style="width:155px;" >结算金额：${sumMoney?string(',##0.00')}元</li>
    </ul>

    <div class="btn-box">
    	<a href="javascript:void(0);" onclick="early();" id="advanceFinancialForm">同意</a>
        <a href="javascript:void(0);" onclick="reject();" id="rejectedFinancialForm">驳回</a>
    </div>
</div>
</form>
</body>
</html>