$(function(){
			$("#innerNav li.accountSafety").addClass("current").append("<em class=\"curr\">&nbsp;</em>");

			$("#verify_sms").attr("readonly", "readonly");
			
			//修改按钮
			$("#changeTradePassword1").click(function(){
				$("#changeTradePasswordForm1").ajaxSubmit({
					beforeSubmit:checkForm,
					clearForm:false,
					dataType:"JSON",
					success:function(responseText, statusText){
						if (statusText == "success"){
							if (responseText.result == 1){
								location.href = basepath+"/user/getBpCustMember.do?id="+responseText.data.id+"&typ=6";
							}else{
								showCommonTip(".common-tip",responseText.errMsg);
							}
						}else{
							showCommonTip(".common-tip","修改交易密码失败");
						}
					},
					error:function(){
						showCommonTip(".common-tip","修改交易密码失败");
					}
				});
			});
			
			//获取短信验证码
			$("#getVerifySms").click(getVerifySms("getVerifySms", "mobileId", "verify_sms", 120 , "update_trade_password"));

			//错误提示
			if ("" != ""){
				showCommonTip(".common-tip","");
			}
		});

		//校验表单
		function checkForm(){
			if (isEmpty($("#verify_sms").val())){
				showTip("verify_sms","短信验证码不能为空");
				return false;
			}

			return true;
		}
