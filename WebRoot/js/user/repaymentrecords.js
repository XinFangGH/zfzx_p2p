function query(submitForm,type)
		{	
			if(""==type||undefined==type)
			{
				type = $("#payment_type").val();
			}
			$("#payment_type").val(type);
			var repayment_date_start = $("#from").val();
			var repayment_date_end = $("#to").val();
			var financ_name = $("#financ_name").val();
			if(""!=repayment_date_start&&""!=repayment_date_end&&repayment_date_start>repayment_date_end)
			{
				alert("交易开始时间不能大于结束时间！");
    			return;
			}
			if(chkLength(financ_name,3)>100)
			{
				alert("输入标题的字数太多！");
    			return;
			}
			//document.getElementById('queryForm').submit();
    		$("#"+submitForm).ajaxSubmit({
				beforeSubmit:showStart,
				clearForm:false,
				dataType:"JSON",
				success:function(responseText, statusText){
					if("norepayment"==type)
					{
						$(".pending").find("dl").html('').append(responseText.content);
						$("#norepayment_pager").html('').append(responseText.pager);
					}
					else if("repaymented"==type)
					{
						$(".repaid").find("dl").html('').append(responseText.content);
						$("#repaymented_pager").html('').append(responseText.pager);
					}
					else if("overdue"==type)
					{
						$(".overdue").find("dl").html('').append(responseText.content);
						$("#overdue_pager").html('').append(responseText.pager);
					}
				}
			});
		}
		
function ht(){
	alert("此项目合同未上传");
};
function show(id,surplusMoney){
	$("#satory").text(id);
	$("#surplusMoney").text(surplusMoney);
	document.getElementById("xy-lc-box").style.display="";
}
function hid(){
	document.getElementById("xy-lc-box").style.display="none";
}
function checkMoney(){
	var surplusMoney = $("#surplusMoney").text();
	var checkMoney = $("#checkMoney").val();
	if(checkMoney.match(/[^\d\.]/g)){
		$(".xy-ui-form-item-explain").text("X 金额必须为数字");
		$(".xy-ui-form-item-explain").show();
			return false;
	}else if(Number(checkMoney)>Number(surplusMoney)){
		$(".xy-ui-form-item-explain").text("X 还款金额不能大于剩余还款金额");
		$(".xy-ui-form-item-explain").show();
			return false;
	}else{
		$(".xy-ui-form-item-explain").text("");
		$(".xy-ui-form-item-explain").hide();
		   return true;
	}
}
function trueCheck(){
	var surplusMoney = $("#surplusMoney").text();
	var atoryId = $("#satory").text();
	var checkMoney = $("#checkMoney").val();
	if(checkMoney==""){
		$(".xy-ui-form-item-explain").text("X 请输入还款金额");
		$(".xy-ui-form-item-explain").show();
			return false;
	}else if(Number(checkMoney)>Number(surplusMoney)){
		$(".xy-ui-form-item-explain").text("X 还款金额不能大于剩余还款金额");
		$(".xy-ui-form-item-explain").show();
			return false;
	}else if(checkMoney.match(/[^\d\.]/g)){
		$(".xy-ui-form-item-explain").text("X 金额必须为数字");
		$(".xy-ui-form-item-explain").show();
			return false;
	}else{
		location.href=basepath+"pay/repayCompensatoryPay.do?atoryId="+atoryId+"&checkMoney="+checkMoney;
	}
}
