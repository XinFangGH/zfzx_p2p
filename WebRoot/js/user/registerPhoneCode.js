$(function(){
	$("#telphone").on("keyup blur", function(){
				if(!isMobile($("#telphone").val())){
					$("#getVerifySms").addClass("disabled");
				}else{
					if ($("#getVerifySms").text().indexOf("(") == -1){
						//让发送 验证码的按钮可用
						$("#getVerifySms").removeClass("disabled");
					}
				}
	});
/*	$("#getVerifySms").click(function(){
				if (isEmpty($("#telphone").val())) {
					showTip("telphone", "手机号码不能为空");
					return false;
				}

				if (!isMobile($("#telphone").val())) {
					showTip("telphone", "手机号码格式不正确");
					return false;
				}
				
				getValidateSms("getVerifySms", "telphone", "verify_sms", 60 , "bind_telphone");	
	});*/
	
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
				
				/*if (isEmpty($("#" + mobileId).val())) {
					showTip(mobileId, "手机号码不能为空");
					return false;
				}

				if (!isMobile($("#" + mobileId).val())) {
					showTip(mobileId, "手机号码格式不正确");
					return false;
				}
*/
				
			}

			// 禁用获取短信验证码按钮
			getVerifySmsObj.addClass("disabled");
			$("#" + verifySmsId).removeAttr("readonly");

			var text = getVerifySmsObj.html();
			//每次进入先清空
			getVerifySmsObj.html(text);
			
			var url = basepath + "codecreate.do?sms_code_type=" + type
					+ "&telphone=" + $("#" + mobileId).val() + '&randomCode='
					+ (Math.random())+"&token="+token;
			
			$.ajax({
						url : url,
						dataType : "json",
						success : function(result) {
							if (result.status == "200") {
									$("#showMsg").parent().css("display","block");
									$("#showMsg").html("短信已发送至您手机，请输入短信中的验证码，30分钟内输入有效。");
									var interval = window.setInterval(function() {
											seconds--;
											if (seconds == 0) {
											
												seconds = temp;
												window.clearInterval(interval);
												getVerifySmsObj.removeClass("disabled");
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
								
								getVerifySmsObj.removeClass("disabled");
								getVerifySmsObj.html(text.indexOf("重新") == -1? "重新" + text: text);
								};
						},
						error : function() {
							$("#showMsg").parent().css("display","block");
							$("#showMsg").html("短信验证码获取失败，请重新获取");
						}
					});

	};
}
	
	
	
	$("#registerBtnPhone").click(function(){
		var daojishi=10;			
		if (isEmpty($("#verify_sms").val())) {
				showTip("verify_sms", "请输入短信验证码");
				return false;
			}
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : basepath + "/user/bindPhoneBpCustMember.do?telphone="+$("#telphone").val()+"&verify_sms="+$("#verify_sms").val(),
			cache : false,
					success : function(responseText, statusText) {
						if (statusText == "success") {
							if(responseText.errMsg=="0")
							{
								showTip("verify_sms", "手机验证码不正确");
							}
							if(responseText.errMsg=="1")
							{
								location.href = basepath+ "user/successPhoneBpCustMember.do";
							}
							if(responseText.errMsg=="2")
							{
								showTip("telphone", "手机绑定失败");
							}if(responseText.errMsg=="3")
							{
								$("#djs").css("display","block");
								var interval = window.setInterval(function() {
									daojishi--;
									if (daojishi == 0) {
										location.href = basepath+ "htmlreg.do";
									}else {
										$("#djs").text("注册失败，"+daojishi+"秒钟后跳转到注册页面！");
									}
								}, 1000);
								
							}
						}
					}
				});
				
	
		//$("#registerFormPhone").submit();
	});
	
	$("#nextButton").click(function(){
		var typeCode = $("#typeCode").text();
		var teypPhone = $("#typeTelphone").text();
		if (isEmpty($("#telphone").val())) {
				showTip("telphone", "手机号码不能为空");
				return false;
			}
		if (!isMobile($("#telphone").val())) {
			showTip("telphone", "手机号码格式不正确");
			return false;
		}
		if (teypPhone=="1") {
			showTip("telphone", "手机号码已存在");
			return false;
		}
	
		if(typeCode=="faile"||typeCode==""){
			showTip("checkCode", "验证码输入错误");
			return false;
		}
		$("#myTelphone").text($("#telphone").val());
		
		getValidateSms("getVerifySms", "telphone", "verify_sms", 60 , "bind_telphone");	
		
	})
});
