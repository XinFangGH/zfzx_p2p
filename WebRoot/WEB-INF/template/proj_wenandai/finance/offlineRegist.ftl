<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${systemConfig.metaTitle} - 个人借款</title>
    <meta name="description" content="${systemConfig.metaTitle} - 个人借款,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 个人借款,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
<script type="text/javascript" >

   function  saveTo(){debugger
	var backPunishMoney = $("#backPunishMoney").val();
	var compensatoryId = $("#compensatoryId").val();
	var flateMoney = $("#flateMoney").val();
	var backDate = $("#backDate").val();
	var backCompensatoryMoney = $("#backCompensatoryMoney").val();
	var surplusMoney = $("#surplusMoney").val();
	
	$("#pBackCompensatoryMoney").empty();
	$("#pBackPunishMoney").empty();
	$("#pFlateMoney").empty();
	$("#pBackDate").empty();
	
	
	
	if(backCompensatoryMoney==""){
		$("#pBackCompensatoryMoney").text("请输入代偿回款金额");
		$("#pBackCompensatoryMoney").show();
			return false;
	}else if(backPunishMoney.match(/[^\d\.]/g)){
		$("#pBackCompensatoryMoney").text("代偿回款金额必须为数字");
		$("#pBackCompensatoryMoney").show();
			return false;
	}
	if(backPunishMoney==""){
		$("#pBackPunishMoney").text("请输入罚息回款金额");
		$("#pBackPunishMoney").show();
			return false;
	}else if(backCompensatoryMoney.match(/[^\d\.]/g)){
		$("#pBackPunishMoney").text("罚息金额必须为数字");
		$("#pBackPunishMoney").show();
			return false;
	}
	if(flateMoney==""){
		$("#pFlateMoney").text("请输入平账金额");
		$("#pFlateMoney").show();
			return false;
	}else if(flateMoney.match(/[^\d\.]/g)){
		$("#pFlateMoney").text("平账金额必须为数字");
		$("#pFlateMoney").show();
			return false;
	}
	if(backDate==""){
	    $("#pBackDate").text("请选择追偿日期");
		$("#pBackDate").show();
			return false;
	}
	else if(Number(backCompensatoryMoney)+Number(backPunishMoney)+Number(flateMoney)>Number(surplusMoney)){
		$("#pBackCompensatoryMoney").text("X 还款金额不能大于剩余还款金额");
		$("#pBackCompensatoryMoney").show();
			return false;
	}else{
		parent.window.location.href=basepath+"compensatory/saveCompensatoryInfoPlBidCompensatory.do?backPunishMoney="+backPunishMoney+"&compensatoryId="+compensatoryId+"&flateMoney="+flateMoney+
			"&backDate="+backDate+"&backCompensatoryMoney="+backCompensatoryMoney;
	     var index = parent.layer.getFrameIndex(window.name);
		 parent.layer.close(index);
		}
	};
</script>
</head>
<body>
<form id="offlineRegist_Form"  method="post" action="${base}/pay/compensatoryPay.do" >
<input type="hidden" id="compensatoryId" name="ono" value="${plBidCompensatory.id}"/> 
<input type="hidden" id="surplusMoney" name="sno" value="${plBidCompensatory.surplusMoney}"/> 
<div class="tc-box">
<#if plBidCompensatory!=null >
 <table class="ul-two" border="0" cellspacing="0" cellpadding="0">
    	<tr>
    		<th>代偿金额</th>
    		<th>代偿罚息</th>
    		<th>累计应收</th>
    		<th>累计追偿</th>
    		<th>未还代偿金额</th>
    		<th>未还代偿罚息</th>
    		<th>平账金额</th>
    		<th>累计未还金额</th>
    	</tr>
    	<tr>
    		<td>￥${plBidCompensatory.compensatoryMoney?string(',##0.00')}</td>
    		<td>￥${plBidCompensatory.punishMoney?string(',##0.00')}</td>
    		<td>￥${plBidCompensatory.allMoney?string(',##0.00')}</td>
    		<td>￥${plBidCompensatory.backAllMoney?string(',##0.00')}</td>
    		<td>￥${plBidCompensatory.unBackCompensatoryMoney?string(',##0.00')}</td>
    		<td>￥${plBidCompensatory.unBackPunishMoney?string(',##0.00')}</td>
    		<td>￥${plBidCompensatory.plateMoney?string(',##0.00')}</td>
        	<td>￥${plBidCompensatory.surplusMoney?string(',##0.00')}</td>
    	</tr>
    </table>
    <p class="tit-nema">代偿回款记录</p>
    <table width="100%"  class="ul-two" border="0" cellspacing="0" cellpadding="0">
    	<tr class="ul-two">
    		<th>回款流水号</th>
    		<th>罚息金额</th>
    		<th>代偿回款金额</th>
    		<th>平账金额</th>
    		<th>追偿日期</th>
    	</tr>
    	<#list  plist as list>
    	<tr>
    		<td>${list.requestNo}</td>
    		<td>￥${list.backPunishMoney?string(',##0.00')}</td>
    		<td>￥${list.backCompensatoryMoney?string(',##0.00')}</td>
    		<td>￥${list.flateMoney?string(',##0.00')}</td>
    		<td>${list.backDate?string("yyyy-MM-dd")}</td>
    	</tr>
    	</#list>
    </table>
     <ul style="padding:30px 0; margin:20px 0; overflow:hidden; border-top:1px solid #ddd;">
     	<li style="padding-left:5px;">
     		罚息金额：<input id='backPunishMoney' type="text" class="colorful" style="width:60px;"/> 元
     		<p style="display:none; color:red; line-height:0; text-align:center;" id="pBackPunishMoney"><span></span></p>
     	</li>
        <li style="padding-left:15px;">
        	代偿回款金额：<input id='backCompensatoryMoney'   type="text" class="colorful" style="width:60px;" /> 元
        	<p style="display:none; color:red; line-height:0; text-align:center;" id="pBackCompensatoryMoney"><span></span></p>
        </li>
        <li style="padding-left:25px;">平账金额：<input type="text" id='flateMoney' style="width:60px;" class="colorful" value="0" onfocus="if (value =='0'){value =''}" onblur="if (value ==''){value='0'}"  /> 元
           <p style="display:none; color:red; line-height:0; text-align:center;" id="pFlateMoney"><span></span></p>
        </li>
        <li style="padding-left:25px;" class="mr-0">追偿日期：<input id='backDate' type="text" id="startDate" style="width:120px" name="bidtime" class="colorful" readonly="readonly" onclick="new Calendar().show(this);"/>
        <p style="display:none; color:red; line-height:0; text-align:center;" id="pBackDate"><span></span></p>
         </li>
    </ul>
    <#else>
    <table class="ul-two" border="0" cellspacing="0" cellpadding="0">
    	<tr>
    		<th>代偿金额</th>
    		<th>代偿罚息</th>
    		<th>累计应收</th>
    		<th>累计追偿</th>
    		<th>未还代偿金额</th>
    		<th>未还代偿罚息</th>
    		<th>平账金额</th>
    		<th>累计未还金额</th>
    	</tr>
    	<tr>
    		<td>￥200,00</td>
    		<td>￥200,00</td>
    		<td>￥200,00</td>
    		<td>￥200,00</td>
    		<td>￥200,00</td>
    		<td>￥200,00</td>
    		<td>￥200,00</td>
        	<td>￥200,00</td>
    	</tr>
    </table>
     <p class="tit-nema">代偿回款记录</p>
    <table width="100%"  class="ul-two" border="0" cellspacing="0" cellpadding="0">
    	<tr class="ul-two">
    		<th>回款流水号</th>
    		<th>罚息金额</th>
    		<th>代偿回款金额</th>
    		<th>平账金额</th>
    		<th>追偿日期</th>
    	</tr>
    	<#list  plist as list>
    	<tr>
    		<td>DCHK7692862848053667	</td>
    		<td>￥200.00</td>
    		<td>￥200.00</td>
    		<td>￥200.00</td>
    		<td>2016-07-29</td>
    	</tr>
    	</#list>
    </table>
   </#if> 
   <br>
    <div class="btn-box" style="float:left;padding-left:350px;">
    	<a href="javascript:;" onclick="saveTo();" id="trueSave">保存</a>
        <a href="#">取消</a>
    </div>
</div>
</form>
</body>
</html>