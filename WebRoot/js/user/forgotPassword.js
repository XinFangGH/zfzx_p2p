/**
 * 忘记密码
 * 
 * 
 * **/

$(function(){
	//邮箱验证
	$("#registerBtn1").click(function(){
		$("#registerForm1").ajaxSubmit({
					beforeSubmit : checkForm1,
					clearForm : false,
					dataType : "JSON",
					success : function(responseText, statusText) {
						if (statusText == "success") {
							if (responseText.result == 1) {
								location.href = basepath+ "user/jumpBpCustMember.do?regType="+responseText.data;

							} else {
                                if(responseText.errMsg=="验证码错误!"){
                                    $(".common-tip").hide();
                                }else{
                                    showCommonTip(".common-tip",responseText.errMsg);
								}
								removeGray("registerBtn1");
							}
						} else {
							showCommonTip(".common-tip", "系统操作错误!请联系管理员");
							removeGray("registerBtn1");
						}
					},
					error : function() {
						showCommonTip(".common-tip", "网络异常!");
						removeGray("registerBtn1");
					}
				});
	});
	//邮箱验证
	$("#registerBtn2").click(function(){
		$("#registerForm2").ajaxSubmit({
					beforeSubmit : checkForm1,
					clearForm : false,
					dataType : "JSON",
					success : function(responseText, statusText) {
						if (statusText == "success") {
							if (responseText.result == 1) {
								//location.href = basepath+ "user/jumpBpCustMember.do?regType="+responseText.data;
								location.href = basepath
										+ "gotoUpdatePasswordreg.do?uuid="+responseText.data;
							} else {
								showCommonTip(".common-tip",
										responseText.errMsg);
								removeGray("registerBtn1");
							}
						} else {
							showCommonTip(".common-tip", "网络异常，注册失败");
							removeGray("registerBtn1");
						}
					},
					error : function() {
						showCommonTip(".common-tip", "网络异常，注册失败");
						removeGray("registerBtn1");
					}
				});
	});
	$("#emailClick").click(function(){
		$("#myemail").addClass("buttonoblue2");
		$("#mytelphone").removeClass("buttonoblue2");
	})
	$("#telphoneClick").click(function(){
		$("#mytelphone").addClass("buttonoblue2");
		$("#myemail").removeClass("buttonoblue2");
	})
})

// 校验表单

function checkForm1() {
	// 校验邮箱
	if($("#email").length>0){
		if("none"!=$("#email").parent().css("display")){
				if (isEmpty($("#email").val())) {
					showTip("email", "邮箱不能为空");
					return false;
				}
				if (!$("#email")
						.val()
						.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
					showTip("email", "邮箱格式不正确");
					return false;
				}
		}

	}	
	if($("#telphone").length>0){
		if("none"!=$("#telphone").parent().css("display")){
				if (isEmpty($("#telphone").val())){
					showTip("telphone","手机号码不能为空");
					return false;
				}
		
				if (!isMobile($("#telphone").val())){
					showTip("telphone","手机号码格式不正确");
					return false;
				}
		}
	}
	
	// 校验验证码
	if(typeof($("#checkCode"))){
		if (isEmpty($("#checkCode").val())) {
			showTip("checkCode", "请填写验证码");
			return false;
		}
	
		if ($("#checkCode").val().length == 4||$("#checkCode").val().length ==6) {
			
		}else{
			showTip("checkCode", "请输入正确的验证码");
			return false;
		}
	}

	

	activeGray("registerBtn1");

	return true;
}
 function isEmpty(str) {
     if (str != null) {
         var tmp = ATrim(str)
         if (tmp.length == 0) {
             return true;
         }
     }
     return false;
 }
  function RTrim(str) {
     var i = 0;
     var len = str.length;
     while (str.charAt(len - i - 1) == ' ') i++;
     return str.substring(0, len - i);
 }

 function ATrim(str) {
     return RTrim(LTrim(str));
 }
  //----去空格----

 function LTrim(str) {
     var i = 0;
     while (str.charAt(i) == ' ') i++;
     return str.substring(i);
 }