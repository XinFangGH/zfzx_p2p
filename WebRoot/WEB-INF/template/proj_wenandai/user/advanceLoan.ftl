<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 提前还款</title>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript">var m1="个人中心",m2="借款管理",m3="借款管理";</script>
<script type="text/javascript">
	window.onload=function() {
		var fag = $("#fag").val();
		if(fag!=""){
			if(fag=="success"){
				alert("提前还款成功");
			}else{
				alert("提前还款失败");
			}
		}
	}
	function refesh(){
	var href1  = document.getElementById("prepayment").getAttribute('href',2);
		document.getElementById("prepayment").setAttribute("href","javascript:return false;"); 
		window.open(href1);  
  
		 parent.location.reload();
	}
</script>
<style type="text/css"> 
/*提前还款页面*/
*{margin:0; padding:0; list-style:none;font-family: "Microsoft YaHei","微软雅黑","STXihei","华文细黑";}
.repayment{padding:0px 20px; margin:0px; overflow:hidden; }
.repayment p{display:block; padding:20px 0px;font-size:16px; color:#0096c4;}
.repayment ul{ overflow:hidden;border-bottom:1px dashed #cecece;}
.repayment ul li{height:35px; line-height:30px; }
.repayment ul li .txt{width:150px;display:inline-block;font-size:14px; text-align:right;}
.repayment ul li .red{color:red;display:inline-block;padding:10px;font-size:14px; text-align:right;}
.repayment table.tab_css_4{width:100%; margin:0 auto; color:#666;border:1px solid #cecece;}
.repayment table.tab_css_4 th{color:#333; background-color:#e6f4fd; height:40px; font-weight:normal;font-size:14px;text-align:center;}
.repayment table.tab_css_4 td{color:#333; background-color:#fff;height:40px; font-weight:normal;font-size:16px; text-align: center}
.repayment .btn{padding:40px 0; overflow:hidden; text-align:center;}
.repayment .btn .buttongray{padding:10px 20px;margin-right:20px; border-radius: 8px; background:#cacaca; color:#333; font-size:14px; text-decoration:none; cursor:pointer;}
.repayment .btn .buttonorange{padding:10px 20px; border-radius: 8px; background:#fd7754; color:#fff; font-size:14px; text-decoration:none;cursor:pointer;}
</style>	
</head>
<body >
<div class="repayment"><!-- prepaymentStr-->
	<input type="hidden" id="fag" value="${fagStr}">
	<#assign prepayment=prepaymentStr?eval ><!--声明一个变量来接收返回过来的json字符串 -->
	<p>截止到${nowTime}，提前还款明细如下：</p>
	<div style="padding:0px 0 20px 70px;border-bottom:1px dashed #cecece; font-size:16px; ">
	<span class="txt">剩余本金：</span>
	<span class="red"><#if prepayment??><#if prepayment.principalMoney??>${prepayment.principalMoney}<#else>0.00</#if><#else>0.00</#if>元</span>
	<span class="txt" style="margin-left:20px;">账户余额：</span>
	<span class="red"><#if prepayment??><#if prepayment.availableInvestMoney??>${prepayment.availableInvestMoney}<#else>0.00</#if><#else>0.00</#if>元</span>
	<br><span style="color:red;font-size:14px;"><#if prepayment.overdueMoney?number gt 0>（提示：逾期金额${prepayment.overdueMoney}元不能提前还款）</#if></span>
	</div>
	<ul>		
		<li><span class="txt">提前还款本金：</span><span class="red"><#if prepayment??><#if prepayment.principalMoney??>${prepayment.principalMoney}<#else>0.00</#if><#else>0.00</#if>元</span></li>
		<li><span class="txt">截止利息总额：</span><span class="red"><#if prepayment??><#if prepayment.interestMoney??>${prepayment.interestMoney}<#else>0.00</#if><#else>0.00</#if>元</span></li>
		<li><span class="txt">截止咨询费总额：</span><span class="red"><#if prepayment??><#if prepayment.managementConsultingMoney??>${prepayment.managementConsultingMoney}<#else>0.00</#if><#else>0.00</#if>元</span></li>
		<li><span class="txt">截止服务费总额：</span><span class="red"><#if prepayment??><#if prepayment.financeServiceMoney??>${prepayment.financeServiceMoney}<#else>0.00</#if><#else>0.00</#if>元</span></li>
		<li><span class="txt">补偿息天数：</span><span class="red"><#if prepayment??><#if prepayment.penaltyDays??>${prepayment.penaltyDays}<#else>0.00</#if><#else>0.00</#if>天</span></li>
		<li><span class="txt">罚息总额：</span><span class="red"><#if prepayment??><#if prepayment.interestPenalty??>${prepayment.interestPenalty}<#else>0.00</#if><#else>0.00</#if>元</span></li>
		<li><span class="txt">提前还款支付总额：</span><span class="red"><#if prepayment??><#if prepayment.allMoney??>${prepayment.allMoney}<#else>0.00</#if><#else>0.00</#if>元</span></li>
	</ul>
	  <div class="btn">

		<#if prepayment.platSize==1>
		<a class="buttongray" > 确定提前还款(请先偿还代偿借款)</a>
	  	<#elseif prepayment.overdueMoney?number gt 0>
	 	<a class="buttongray" > 确定提前还款(逾期)</a>
	 	<#elseif ((prepayment.availableInvestMoney?number)<(prepayment.allMoney?number))>
	 	<a class="buttongray" > 确定提前还款(余额不足)</a>
	 	<#else>
	 	<a class="buttonorange" target="_blank" id="prepayment" href="${base}/pay/toEarlyRepaymentByLoanerPay.do?planId=${prepayment.bidPlanId}&notMoney=${prepayment.allMoney}" onclick="refesh();"> 确定提前还款</a>
	 	</#if>
	 </div> 
</div>	
</body>
</html>