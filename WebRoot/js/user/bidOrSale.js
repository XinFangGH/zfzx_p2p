$(function() {
		$("#compensatoryForm").click(function(){
			var planId=$("#planId").val();
			var intentDate=$("#intentDate").val();
	        var notMoney=$("#notMoney").val();
			var orderNo=$("#orderNo").val();
			parent.window.location.href=basepath+"/pay/compensatoryPay.do?planId="+planId
				+"&intentDate="+intentDate+"&notMoney="+notMoney+"&orderNo="+orderNo
	        var index = parent.layer.getFrameIndex(window.name);
	        parent.layer.close(index);
	});	
	    $("#advanceFinancialForm").click(function(){
			var earlyRedemptionId=$("#earlyRedemptionId").val();
	        var sumMoney=$("#sumMoney").val();
			parent.window.location.href=basepath+"/pay/repayMentAdvanceByFinancialPay.do?earlyRedemptionId="+earlyRedemptionId
				+"&notMoney="+sumMoney
	        var index = parent.layer.getFrameIndex(window.name);
	        parent.layer.close(index);
		});
		    $("#rejectedFinancialForm").click(function(){
			var earlyRedemptionId=$("#earlyRedemptionId").val();
	        var sumMoney=$("#sumMoney").val();
			parent.window.location.href=basepath+"/creditFlow/financingAgency/rejectedByFinancialPlManageMoneyPlanBuyinfo.do?earlyRedemptionId="+earlyRedemptionId
				+"&notMoney="+sumMoney
	        var index = parent.layer.getFrameIndex(window.name);
	        parent.layer.close(index);
	});


	$("#getVerifySms").click(function(){
				if (isEmpty($("#telphone").val())) {
					showTip("telphone", "手机号码不能为空");
					return false;
				}

				if (!isMobile($("#telphone").val())) {
					showTip("telphone", "手机号码格式不正确");
					return false;
				}
				
				getValidateSms("getVerifySms", "telphone", "verify_sms", 60 , "bind_telphone");	
	});
	
	function getValidateSms(getVerifySmsId, mobileId, verifySmsId, seconds, type) {
	var temp = seconds;
		var getVerifySmsObj = $("#" + getVerifySmsId);
		var token = null;
		// 该按钮没有禁用
		if (!getVerifySmsObj.is(".disabled")) {
			if (type == "update_trade_password"
					|| type == "forget_trade_password") {
				var mobile = $("#" + mobileId).text();
				
			} else {
				// 校验手机号
				if($("#token").length>0){
					token = $("#token").val();
				}
				
				if (isEmpty($("#" + mobileId).val())) {
					showTip(mobileId, "手机号码不能为空");
					return false;
				}

				if (!isMobile($("#" + mobileId).val())) {
					showTip(mobileId, "手机号码格式不正确");
					return false;
				}

				
			}

			// 禁用获取短信验证码按钮
			getVerifySmsObj.addClass("disabled");
			$("#" + verifySmsId).removeAttr("readonly");

			var text = getVerifySmsObj.html();
			//每次进入先清空
			getVerifySmsObj.html(text);
			
			
			var url = basepath + "codecreate.do?sms_code_type=" + type
					+ "&telphone=" + $("#" + mobileId).val() + '&randomCode='
					+ (Math.random())+"&token="+token+"&notRegCode=1";
			
		
			$.ajax({
						url : url,
						dataType : "json",
						success : function(result) {
							if (result.status == "200") {
									$("#showMsg").parent().css("display","block");
									$("#showMsg").html("短信已发送至您手机，请输入短信中的验证码，确保您的手机号真实有效。");
									var interval = window.setInterval(function() {
											seconds--;
					
											if (seconds == 0) {
												seconds = temp;
												window.clearInterval(interval);
												getVerifySmsObj.removeClass("disabled");
												// $("#" + verifySmsId).attr("readonly",
												// "readonly");
												getVerifySmsObj.html(text.indexOf("重新") == -1
														? "重新" + text
														: text);
											} else {
												getVerifySmsObj.html(text + "(" + seconds + ")");
											}
					
										}, 1000);
								} else {
								$("#showMsg").parent().css("display","block");
								$("#showMsg").html(result.remark);
								//showTip(mobileId, result.remark);
								
								getVerifySmsObj.removeClass("disabled");
								getVerifySmsObj.html(text.indexOf("重新") == -1? "重新" + text: text);
								};
						},
						error : function() {
							//$("#showMessage").html("短信验证码获取失败，请重新获取");
							$("#showMsg").parent().css("display","block");
							$("#showMsg").html("短信验证码获取失败，请重新获取");
						}
					});

	};
}
	
	
	$("#close_btn").click(function(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	});
	$("#transferFormPhone").click(function(){
		var bidInfoId=$("#bidInfoId").val();
		var startDate=$("#startDate").val();
		var changeMoneyRate=$("#changeMoneyRate").val();
		var changeMoneyType=$('input[name="changeMoneyType"]:checked').val();
		parent.window.location.href=basepath+"creditFlow/financingAgency/saveorStartTransferPlBidSale.do?startDate="+startDate
			+"&bidInfoId="+bidInfoId+"&changeMoneyRate="+changeMoneyRate+"&changeMoneyType="+changeMoneyType
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
		/*
		if (isEmpty($("#changeMoneyRate").val())) {
				showTip("changeMoneyRate", "折价率不能为空");
				return false;
			}

		if (isEmpty($("#verify_sms").val())) {
				showTip("verify_sms", "短信验证码不能为空");
				return false;
			}
				
		$("#transfer_Form").ajaxSubmit({
					clearForm : false,
					dataType : "JSON",
					success : function(responseText, statusText) {
						if (statusText == "success") {
							if(responseText.errMsg=="0")
							{
								showTip("verify_sms", "手机验证码不正确");
							}
							
							if(responseText.errMsg=="1")
							{
								parent.window.location.href=basepath+"creditFlow/financingAgency/canTransferingListPlBidSale.do"
 		                        var index = parent.layer.getFrameIndex(window.name);
		                        parent.layer.close(index);
								//location.href = basepath+ "creditFlow/financingAgency/obligatoryrightTransferPlBidSale.do";
							}
						}
					}
				});
				
	
		//$("#transferFormPhone").submit();
	*/});
		
	$("#transferFormbuy").click(function(){
			var bidInfoId=$("#bidInfoId").val();
		    var startDate=$("#startDate").val();
		     var saleId=$("#saleId").val();
			parent.window.location.href=basepath+"creditFlow/financingAgency/buyorStartTransferPlBidSale.do?startDate="+startDate
			+"&bidInfoId="+bidInfoId	+"&saleId="+saleId
 		    var index = parent.layer.getFrameIndex(window.name);
		    parent.layer.close(index);	

				
	
		//$("#transferFormPhone").submit();
	});
	
	
	/*$("#changeMoneyRate").on("keyup blur", function(){
		    var changeMoneyRate=0;
		    var changeMoney=0;
		    var saleMoney=$("#saleMoney").val();
			if($("#changeMoneyRate").val()){
			   changeMoneyRate=$("#changeMoneyRate").val();
			}
			changeMoney=parseFloat(changeMoneyRate*saleMoney/1000);
			
			var changeMoneyType=$("input[name='changeMoneyType']:checked").val();
			if(changeMoneyType=="1"){
				changeMoney=0-changeMoney;
			}
			
			$("#changeMoney").val(changeMoney);
			$("#changeMoneytext").text(changeMoney);
			
			
		
			
					
	});*/
});


	function signMoney(){
		var changeMoneyRate=0;
		var changeMoney=0;
		var saleMoney=$("#saleMoney").val();
		if($("#changeMoneyRate").val()){
			changeMoneyRate=$("#changeMoneyRate").val();
		}
		changeMoney=Math.round(parseFloat(changeMoneyRate*saleMoney/1000)*100)/100;
			
		var changeMoneyType=$("input[name='changeMoneyType']:checked").val();
		if(changeMoneyType=="1"){
			changeMoney=0-changeMoney;
		}
		$("#changeMoney").val(changeMoney);
		$("#changeMoneytext").text(changeMoney);
		
		var saleMoney=parseFloat($("#saleMoney").val());
		var changeMoney=parseFloat($("#changeMoney").val());
		var sumInteresteMoney=parseFloat($("#sumInteresteMoney").val());
		var accrualMoney=parseFloat($("#accrualMoney").val());
		var sumMoney=saleMoney+changeMoney+sumInteresteMoney+accrualMoney;
		
		$("#sumMoney").val(sumMoney);
		$("#sumMoneytext").text(Math.round(sumMoney*100)/100);
		$("#transferFee").val(sumMoney*5/100);
		$("#transferFeetext").text(Math.round(sumMoney*10/1000*100)/100);
	};
	
function clickOrStartTransferForm(bidInfoId,yearAccrualRate,intentDate,startDate){
	var url = basepath+"creditFlow/financingAgency/orStartTransferPlBidSale.do?bidInfoId="
	+bidInfoId+"&yearAccrualRate="+yearAccrualRate+"&intentDate="+intentDate+"&startDate="+startDate+"&type=transfer";
	$.layer({
            type : 2,
            title: '挂牌交易',
            shadeClose: true,
            shade : [0.5 , '#000' , true],
            move : ['.xubox_title' , true],
            zIndex : 19891014,
            maxmin: true,
            fix : false,  
            area: ['1024px', 650],                     
            iframe: {
                src : url
            } 
        });	
        document.body.style.overflow = "hidden";
        
}
function clickOrStartTransferFormbuy(bidInfoId,yearAccrualRate,intentDate,startDate,saleId){
	var bidType=$("#bidType").val();
	var grade=$("#grade").val();
	var login = $(".loginname").text();
	if(login==""||login==null||login==undefined){
		window.location.href=basepath+"/htmllogin.do";
	}else if(grade){
		var url = basepath+"creditFlow/financingAgency/orStartTransferPlBidSale.do?bidInfoId="
		+bidInfoId+"&yearAccrualRate="+yearAccrualRate+"&intentDate="+intentDate+"&startDate="+startDate+"&type=buy"+"&saleId="+saleId;
		$.layer({
	        type : 2,
	        title: '购买债权',
	        shadeClose: true,
	        shade : [0.5 , '#000' , true],
	        move : ['.xubox_title' , true],
	        zIndex : 19891014,
	        maxmin: true,
	        fix : false,  
	        area: ['1024px', 550],                     
	        iframe: {
	            src : url
	        } 
	    });	
	    document.body.style.overflow = "hidden";
	}else{
		location.href=basepath+"user/toAccessBpCustMember.do?bidType="+bidType;
	}
}
function clickOrStartTransferFormsee(bidInfoId,yearAccrualRate,intentDate,startDate,saleId){
	var url = basepath+"creditFlow/financingAgency/orStartTransferPlBidSale.do?bidInfoId="
	+bidInfoId+"&yearAccrualRate="+yearAccrualRate+"&intentDate="+intentDate+"&startDate="+startDate+"&type=see"+"&saleId="+saleId;
	$.layer({
            type : 2,
            title: '交易信息',
            shadeClose: true,
            shade : [0.5 , '#000' , true],
            move : ['.xubox_title' , true],
            zIndex : 19891014,
            maxmin: true,
            fix : false,  
            area: ['1024px', 600],                     
            iframe: {
                src : url
            } 
        });	
        document.body.style.overflow = "hidden";
        
}
function colsedTransfer(saleId){
                  $.ajax({
					url : basepath+"creditFlow/financingAgency/updatePlBidSale.do?id="+ saleId+"&saleStatus=3",
					dataType:"text",
					success:function(data){
					}
				});

}