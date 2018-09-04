$(function(){
			
			var smsTelphone = $("#Smstelphone").val();
			if(smsTelphone=="error"){
				showCommonTip(".common-tip","输入的手机验证码有误，请重新输入", "info");
			}
			//修改按钮
			$("#beforeBindMobile").click(function(){
				var beforeVerify_sms = $("#beforeVerify_sms").val();
				if(($.trim(beforeVerify_sms)).length<1){
					showCommonTip(".common-tip","手机验证码不能为空，请重新输入", "info");
					return false;
				}
				$("#beforeBindMobileForm").submit();
			});
			//获取短信验证码
			//$("#getBeforeVerifySms").click(getVerifySms("getBeforeVerifySms", "beforeTelphone", "beforeVerify_sms", 60 , "bind_telphone"));
			$("#getBeforeVerifySms").click(function(){
				verify();
			});
			
		});
function verify(){
		var seconds=120;
		var temp =120 ;
		var mobileId = "telphone";
		var getVerifySmsObj = $("#getBeforeVerifySms");
		var type = "bind_telphone";
		var text = getVerifySmsObj.html();
		//每次进入先清空
		getVerifySmsObj.html(text);
		getVerifySmsObj.addClass("disabled");
		//getVerifySmsObj.html(text + "(" + seconds + ")");
		var url = basepath + "codecreate.do?sms_code_type=" + type
			+ "&telphone=" + $("#" + mobileId).val() + '&randomCode='
			+ (Math.random())+"&token=updatePhone";
		$.ajax({
			url : url,
			dataType : "json",
			success : function(result) {
				if (result.status == "200") {
					showCommonTip(".common-tip", "短信已发送至您手机，请输入短信中的验证码，30分钟内输入有效。");
					var interval = window.setInterval(function() {
						seconds--;
						if (seconds == 0) {
							seconds = temp;
							window.clearInterval(interval);
						//	showCommonTip(".common-tip", "请重新获取验证码");
							$("#timeMsg").css("display","none");
							$("#getBeforeVerify_Sms").css("display","block");
							getVerifySmsObj.removeClass("disabled");
							getVerifySmsObj.html(text.indexOf("重新") == -1 ? "重新" + text : text);
						} else {
							$("#timeMsg").css("display","block");
							$("#getBeforeVerify_Sms").css("display","none");
							$("#timeMsg").html("重新获取验证码"+"("+seconds+")");
							getVerifySmsObj.html(text + "(" + seconds + ")");
						}

					}, 1000);
				} else {
					showCommonTip(".common-tip", result.remark);
					getVerifySmsObj.html(result.remark);
				}
			},
			error : function() {
			}
		});
	
				
}
function updategetBeforeVerifySms(){
	var spancheckCode_img = $("#spancheckCode_img").text();
	if(spancheckCode_img=="0"){
		verify();
	}else{
		showCommonTip(".common-tip", "请输入正确的验证码");
	}
}


//校验表单手机验证
function checkForm(){
	if (isEmpty($("#beforeVerify_sms").val())){
		showTip("verify_sms","短信验证码不能为空");
		return false;
	}

	
	return true;
}			


function validatTel2CheckCode(){
	var tel = $("#telphone").val();
	if (tel=="") {
		showCommonTip(".common-tip", "手机号码不能为空");
		return false;
	} else if (isMobile(tel)==0) {
		showCommonTip(".common-tip", "手机号码格式错误");
		return false;
	}else{
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : basepath + "/user/isTelphoneBpCustMember.do?telphone="+tel,
			cache : false,
			success : function(responseText, statusText) {
				if (responseText.success) {
					if(responseText.result=="1"){
						showCommonTip(".common-tip", responseText.errMsg);
					}
				} else {
					if(responseText.result=="1"){
					    showCommonTip(".common-tip", responseText.errMsg);
					}else{
						showCommonTip(".common-tip", responseText.errMsg);
					    $("#true_telphone").val("1");
					}
				}
			},
			error : function(request) {
				return false;
			}
		});
	}
}
function updateTe(){
	var tel = $("#telphone").val();
	var true_telphone =$("#true_telphone").text();
	var spancheckCode_img = $("#spancheckCode_img").text();
	if (tel=="") {
		showCommonTip(".common-tip", "手机号码不能为空");
		return false;
	} else if (isMobile(tel)==0) {
		showCommonTip(".common-tip", "手机号码格式错误");
		return false;
	}
	var true_telphone = $("#true_telphone").val();
	if(true_telphone!="1"){
		showCommonTip(".common-tip", "请正确填写手机号码或者手机号已经存在");
		return false;
	}
	if(spancheckCode_img=="0"){
		verify();
	}else{
		showCommonTip(".common-tip", "验证码输入错误");
	}
}


function upDateTellPhone(){
	var tel = $("#telphone").val();
	var true_telphone =$("#true_telphone").text();
	if (tel=="") {
		showCommonTip(".common-tip", "手机号码不能为空");
		return false;
	} else if (isMobile(tel)==0) {
		showCommonTip(".common-tip", "手机号码格式错误");
		return false;
	}
	var true_telphone = $("#true_telphone").val();
	if(true_telphone!="1"){
		showCommonTip(".common-tip", "请正确填写手机号码或者手机号已经存在");
		return false;
	}
	var spancheckCode_img = $("#spancheckCode_img").text();
	if(spancheckCode_img=="0"){
	}else{
		showCommonTip(".common-tip", "图形验证码输入错误");
		return false;
	}
	
	var tellnews=$("#tellnews").val();
	if(tellnews==""){
		showCommonTip(".common-tip", "短信验证码不能为空");
		return false;
	}
	/*//修改按钮
	$("#bindMobile8").click(function(){*/
		$("#bindMobileForm").submit();
	/*});*/
	
}