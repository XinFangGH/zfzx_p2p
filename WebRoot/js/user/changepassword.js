$(function(){
			$("#innerNav li.accountSafety").addClass("current").append("<em class=\"curr\">&nbsp;</em>");
			var path = $("#path").val();
    		//修改按钮
    		$("#changeLoginPassword").click(function(){
    			checkForm();
    		});
    	});

    	//校验表单
    	function checkForm(){
    		//校验旧密码
    		if ($("#oldpassword").val()==""){
    			showTip("oldpassword","旧密码不能为空");
   	          	return false;
    		}

    		//校验新密码
    		if ($("#password").val()==""){
    			showTip("password","新密码不能为空");
   	          	return false;
    		}

    		if ($("#password").val().length < 6 || $("#password").val().length > 16){
    			showTip("password","新密码长度为6~16个字符");
   	          	return false;
    		}
    		if (!$("#password").val().match(/^[a-zA-Z0-9]+$/)){
    			showTip("password","新密码只能由字母和数字组成");
   	          	return false;
    		}

    		//校验确认新密码
    		if ($("#repeat_password").val()==""){
    			showTip("repeat_password","请确认新密码");
   	          	return false;
    		}

    		if ($("#repeat_password").val() != $("#password").val()){
    			showTip("repeat_password","两次输入密码不一致");
   	          	return false;
    		}
    		$("#changeLoginPasswordForm").submit();
    	}

