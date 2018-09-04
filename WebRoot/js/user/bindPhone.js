$(function(){
			$("#innerNav li.accountSafety").addClass("current").append("<em class=\"curr\">&nbsp;</em>");
			
			$("#telphone").on("keyup blur", function(){
				if(!isMobile($("#telphone").val())){
					$("#getVerifySms").addClass("disabled");
				}else{
					if ($("#getVerifySms").text().indexOf("(") == -1){
						//让发送 验证码的按钮可用
						$("#getVerifySms").removeClass("disabled");/*
						$.ajax({
							type : "POST",
							dataType : "JSON",
							url : basepath + "/user/isTelphoneBpCustMember.do?telphone=" + $("#telphone").val(),
							cache : false,
							success : function(responseText, statusText) {
								if (responseText.success) {
									if(document.getElementById("spanPhone")){
										document.getElementById("spanPhone").style.color = "red";
										$("#spanPhone").html(responseText.errMsg);// alert(responseText.errMsg);
									}
									
								} else {
									//让发送 验证码的按钮可用
									$("#getVerifySms").removeClass("disabled");
									if(document.getElementById("spanPhone")){
										//document.getElementById("spanPhone").style.color = "red";
										$("#spanPhone").html('');// alert(responseText.errMsg);
									}
									//document.getElementById("spanPhone").style.color = "#999";
									//$("#spanPhone").html("号码可以使用");
									//document.getElementById("tip-useremail").style.background = "url("+ themepath + "images/icon.JPG) no-repeat 20px";
					
								}
							},
							error : function(request) {
							}
						});
						
					*/}
				}
			});
			
			if(!isMobile($("#telphone").val())){
				$("#getVerifySms").addClass("disabled");
				$("#verify_sms").attr("readonly", "readonly");
			}
					
			//修改按钮
			$("#bindMobile").click(function(){
				$("#bindMobileForm").submit();
			});
			
			//获取短信验证码
			//$("#getVerifySms").click(getVerifySms("getVerifySms", "telphone", "verify_sms", 60 , "bind_telphone"));			
			$("#getVerifySms").click(function(){
				getVerifySms("getVerifySms", "telphone", "verify_sms", 120 , "bind_telphone");	

			});
		});

		//校验表单手机验证
		function checkForm(){
			//校验手机号
			if (isEmpty($("#telphone").val())){
				showTip("telphone","手机号码不能为空");
				return false;
			}

			if (!isMobile($("#telphone").val())){
				showTip("telphone","手机号码格式不正确");
				return false;
			}

			if (isEmpty($("#verify_sms").val())){
				showTip("verify_sms","短信验证码不能为空");
				return false;
			}

			/*if (isEmpty($("#paymentCode").val())){
				showTip("paymentCode","交易密码不能为空");
				return false;
			}*/
			
			return true;
		}			