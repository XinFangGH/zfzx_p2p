/**
 * 注册表单 update: 2013.11.05
 */

// 校验转入表单

function checkForm2() {
	// 校验用户名
	if($("#ptoductamountShow").length>0){
		if (isEmpty($("#ptoductamountShow").val())) {
			showTip("ptoductamountShow", "转入金额不能为空");
			return false;
		}else{
			if(eval($("#ptoductamountShow").val())<eval($("#minBidMoney").val())){
		    showTip("ptoductamountShow", "转入金额不能小于起投金额，请重新输入");
			return false;
			}
			var factMoney=$("#buyFactMoney").val();
			var availbMoney=$("#availableInvestMoney").val();
			if(availbMoney!=""){
				if(eval(factMoney)<=eval(availbMoney)){
					// 用户协议
					if($("#readbuyAgreement").length>0){
						if (!$("#readbuyAgreement").is(":checked")) {
							showTip("readbuyAgreement", "请阅读并接受《服务协议》");
							return false;
						}else{
							$("#personBuyProductForm").submit();
						}
					}else{
						$("#personBuyProductForm").submit();
					}
				}else{
					showTip("buyFactMoney", "转入金额应小于账户可用金额");
					return false;
				}
			}else{
				showTip("availableInvestMoney", "账户可用余额不能为空");
				return false;
			}
			
		}
		
		
		
	
	}


	
	


}

// 校验转出表单
function checkForm(val) {
	// 校验用户名
	if($("#amountFromShow").length>0){
		if (isEmpty($("#amountFromShow").val())) {
			showTip("amountFromShow", "转入金额不能为空");
			return false;
		}
		/*var reg = /(?!^0\d*$)^\d{1,16}(\.\d{1,2})?$/;
		if (reg.test($("#amountFromShow").val())) {
			showTip("amountFromShow", "金额必须大于0且为整数或小数");
			return false;
		}*/
		
		if($("#amountFromShow").val()>val){
			showTip("amountFromShow", "转出金额应小于等于可转出金额");
			return false;
		}
		
	
	}



	
$("#personTransferProductForm").submit();

}
/**
 * 升升投宝转入检测方法
 */
function checkTransferFromUser(){
	var object=$("#ptoductamountShow");
     
     if (object.val()== ""){
     	$("#money_span").html("<span style='font-size:12px;'>金额不能为空。</span>");
     return false;
     } else{ 
     	var reg = /(?!^0\d*$)^\d{1,16}(\.\d{1,2})?$/;
     	flag = reg.test(object.val());
	     if (!flag) {
	        $("#money_span").html("<span style='font-size:12px;'>金额必须大于0且为整数或小数。</span>");
	         object.focus();
	         return false;
	     } else { 
	         var  fee=0;
	         var factMoney=0;
	         var feeRate=$("#plateRate").val();
	         fee=eval(object.val()*feeRate)/100;
	         fee=Math.round(parseFloat(fee) *100)/100
	         factMoney=eval(object.val())+eval(fee);
	         $("#buyProductFee").val(fee);
	         $("#buyFactMoney").val(factMoney);
	         return true;
	     }
     }
}




