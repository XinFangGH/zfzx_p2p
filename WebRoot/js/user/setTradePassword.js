$(function(){
			//修改按钮
			$("#setTradePassword").click(function(){
				$("#setTradePasswordForm").ajaxSubmit({
					beforeSubmit:checkPaymentForm,
					clearForm:false,
					dataType:"JSON",
					success:function(responseText, statusText){
						if (statusText == "success"){
							if (responseText.result == 1){
								$("#password_span").html("交易密码设置成功");
								location.href = basepath+"/user/safeBpCustMember.do?safe=all";
							}else{
								$("#password_span").html("responseText.errMsg");
							}
						}else{
							$("#password_span").html("交易密码设置失败");
						}
					},
					error:function(){
						$("#password_span").html("交易密码设置失败");
					}
				});
			});
			
			if ("" != ""){
				showCommonTip(".common-tip","");
			}
		});

		//校验表单
		function checkPaymentForm(){
			//校验密码
			if (isEmpty($("#paymentCode").val())){
				$("#password_span").html("交易密码不能为空");
				return false;
			}

			if ($("#paymentCode").val().length < 6 || $("#paymentCode").val().length > 16){
				$("#password_span").html("交易密码长度为6~16个字符");
				return false;
			}

			/*if (!$("#paymentCode").val().match(/^[a-zA-Z0-9]+$/)){
				showTip("paymentCode","交易密码只能由字母和数字组成");
				return false;
			}*/

			//校验确认密码
			if (isEmpty($("#repeat_paymentCode").val())){
				$("#password_span").html("请确认交易密码");
				return false;
			}
			if ($("#repeat_paymentCode").val() != $("#paymentCode").val()){
				$("#password_span").html("两次输入密码不一致");
				return false;
			}

			return true;
		}
