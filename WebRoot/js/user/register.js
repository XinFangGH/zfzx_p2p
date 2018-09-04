/**
 * 注册表单 update: 2013.11.05
 */
$(function() {
	

	// 注册按钮
	$("#registerBtnWen").click(function() {
		var recommandPerson = $("#recommandPerson").val();
		$("#registerFormWen").ajaxSubmit({
					beforeSubmit : checkForm,
					clearForm : false,
					dataType : "JSON",
					data:{"recommandPerson":recommandPerson},
					success : function(responseText, statusText) {
						if (statusText == "success") {
							if (responseText.result == 1||responseText.result == 2) {
								if(responseText.result == 2)
								{
									location.href = basepath+ "user/getBpCustMember.do";
								}else
								{
									location.href = basepath+ "telreg.do";
								}
							} else {
								if(responseText.errMsg=="用户名已经存在")
								{
								 	showTip("loginname", responseText.errMsg);
									showCommonTip(".common-tip",responseText.errMsg);
								}
								if(responseText.errMsg=="验证码错误")
								{
								 	showTip("checkCode", responseText.errMsg);
									showCommonTip(".common-tip",responseText.errMsg);
								}
								if(responseText.errMsg=="邀请码不存在")
								{
								 	showTip("recommandPerson", responseText.errMsg);
									showCommonTip(".common-tip",responseText.errMsg);
								}
								removeGray("registerBtn");
							}
						} else {
							showCommonTip(".common-tip", "网络异常，注册失败");
							removeGray("registerBtn");
						}
					},
					error : function() {
						showCommonTip(".common-tip", "网络异常，注册失败");
						removeGray("registerBtn");
					}
				});
	});

	$('#registerForm').on('keyup', 'input.txt', function(e) {
				if (e.keyCode == 13) {
					$("#registerBtn").trigger('click');
				};
			});

		// 获取短信验证码
		// $("#getVerifySms").click(getVerifySms("getVerifySms", "telphone",
		// "verify_sms", 60, "register"));

	});

// 校验表单

function checkForm() {
	// 校验用户名
	if($("#loginname").length>0){
		if (isEmpty($("#loginname").val())) {
			showTip("loginname", "用户名不能为空");
			return false;
		}
	
		if ($("#loginname").val().length < 6 || $("#loginname").val().length > 16) {
			showTip("loginname", "用户名长度为6~16个字符");
			return false;
		}
	
		if (!$("#loginname").val().match(/^[a-zA-Z0-9_]+$/)) {
			showTip("loginname", "用户名只能由字母、数字 和下划线组成");
			return false;
		}
	
		if ($("#loginname").val().match(/^\d+$/)) {
			showTip("loginname", "用户名不能使用纯数字");
			return false;
		}
	}


	

	// 校验密码
	if($("#password").length>0){
		if (isEmpty($("#password").val())) {
			showTip("password", "密码不能为空");
			return false;
		}
	
		if ($("#password").val().length < 6 || $("#password").val().length > 16) {
			showTip("password", "密码长度为6~16个字符");
			return false;
		}
	
		if (!$("#password").val().match(/^[a-zA-Z0-9]+$/)) {
			showTip("password", "密码只能由字母和数字组成");
			return false;
		}
	
		// 校验确认密码
		if (isEmpty($("#repeat_password").val())) {
			showTip("repeat_password", "请确认密码");
			return false;
		}
	
		if ($("#repeat_password").val() != $("#password").val()) {
			showTip("repeat_password", "两次输入密码不一致");
			return false;
		}
	}
	

	/***************************************************************************
	 * //校验手机号 if (isEmpty($("#telphone").val())) { showTip("telphone",
	 * "手机号码不能为空"); return false; }
	 * 
	 * if (!isMobile($("#telphone").val())) { showTip("telphone", "手机号码格式不正确");
	 * return false; }
	 **************************************************************************/

	
	// 校验邮箱
	if($("#email").length>0){
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
	if($("#verify_sms").length>0){
		if (isEmpty($("#verify_sms").val())) {
			showTip("verify_sms", "验证短信不能为空");
			return false;
		}
	}
	
	

	// 用户协议
	if($("#readAgreement").length>0){
		if (!$("#readAgreement").is(":checked")) {
			showTip("readAgreement", "请阅读并接受《服务协议》");
			return false;
		}
	}
	

	activeGray("registerBtn");

	return true;
}

function intostep3(path) {

	var email = $("#email").val();

	var url = basepath + "/thirdreg.do?email=" + email;// 举例根据a标签某个值传过去,返回跳转地址

	location.href = url;// 当前标签地址跳转
}

function refresh(obj) {
	obj.src = basepath + "/getCode.do?" + Math.random();
}
//检查借款人
function chkLoanPerson(obj) {
	if (isEmpty(obj.value)) {
		$("#linkPersion_span").html("请填写联系人");
		$("#tip-linkPersion").css("background","");
	} else if (!isChina(obj.value)) {
		$("#linkPersion_span").html("联系人必须为中文");
			$("#tip-linkPersion").css("background","");
	} else if(obj.value.length>4){
		$("#linkPersion_span").html("联系人不能超过4个字");
		$("#tip-linkPersion").css("background","");
	}else {
		$("#linkPersion_span").html("");
		$("#link_x").html("");
		//$("#tip-linkPersion").css("background","url(" + themepath + "images/icon.JPG) no-repeat 20px");
	}
}
function chkLoanBussiness(obj) {
	if (isEmpty(obj.value)) {
		$("#linkBussiness_span").html("请填写联系人");
		$("#tip-linkBussiness").css("background","");
	} else if (!isChina(obj.value)) {
		$("#linkBussiness_span").html("联系人必须为中文");
			$("#tip-linkBussiness").css("background","");
	} else if(obj.value.length>4){
		$("#linkBussiness_span").html("联系人不能超过4个字");
		$("#tip-linkBussiness").css("background","");
	}else {
		$("#linkBussiness_span").html("");
		$("#link_x").html("");
		//$("#tip-linkPersion").css("background","url(" + themepath + "images/icon.JPG) no-repeat 20px");
	}
}
function chkLoanPersonPhone(obj) {
	if (isEmpty(obj.value)) {
		$("#phone_span").html("请填写手机号");
			$("#tip-phone").css("background","");
	} else if (!isMobile(obj.value)) {
		$("#phone_span").html("手机号码格式不正确");
			$("#tip-phone").css("background","");
	} else {
		$("#phone_span").html("");
	//	$("#phone_x").html("");
		//$("#tip-phone").css("background","url(" + themepath + "images/icon.JPG) no-repeat 20px");
	}
}
function chkLoanBPhone(obj) {
	if (isEmpty(obj.value)) {
		$("#phoneB_span").html("请填写手机号");
			$("#tip-phoneB").css("background","");
	} else if (!isMobile(obj.value)) {
		$("#phoneB_span").html("手机号码格式不正确");
			$("#tip-phoneB").css("background","");
	} else {
		$("#phoneB_span").html("");
	//	$("#phoneB_x").html("");
		//$("#tip-phone").css("background","url(" + themepath + "images/icon.JPG) no-repeat 20px");
	}
}

function chkLoanMoney(obj){
	if (isEmpty(obj.value)) {
		$("#loanMoney_span").html("请填写融资金额");
		$("#tip-loanMoney").css("background","");
	}else if(obj.value.toString().lastIndexOf('.') != -1){
	   var obj1 = obj.value.toString().substring(0,obj.value.toString().length-3);
	   //console.log(typeof(obj1));
		if(!obj1.match(/^[0-9]*$/)){
			$("#loanMoney_span").html("请填写数字");
			$("#tip-loanMoney").css("background","");
		}	  
	} else if(!obj.value.match(/^[0-9]*$/)){
		$("#loanMoney_span").html("请填写数字");
		$("#tip-loanMoney").css("background","");
	}else {
		$("#loanMoney_span").html("");
		//$("#money_x").html("");
		//$("#tip-loanMoney").css("background","url(" + themepath + "images/icon.JPG) no-repeat 35px");
	}
}
function chkLoanMoneyB(obj){debugger;
	if (isEmpty(obj.value)) {
		$("#loanMoneyB_span").html("请填写融资金额");
		$("#tip-loanMoneyB").css("background","");
	}else if(obj.value.toString().lastIndexOf('.') != -1){
	   var obj1 = obj.value.toString().substring(0,obj.value.toString().length-3);
	   console.log(typeof(obj1));
		if(!obj1.match(/^[0-9]*$/)){
			$("#loanMoneyB_span").html("请填写数字");
			$("#tip-loanMoneyB").css("background","");
		}	  
	}else if(!obj.value.toString().match(/^[0-9]*$/)){
		$("#loanMoneyB_span").html("请填写数字");
		$("#tip-loanMoneyB").css("background","");
	}else {
		$("#loanMoneyB_span").html("");
	//	$("#moneyB_x").html("");
		//$("#tip-loanMoney").css("background","url(" + themepath + "images/icon.JPG) no-repeat 35px");
	}
}
function chkBusiness(obj){
	
	if (isEmpty(obj.value)) {
		$("#businessName_span").html("请填写企业名称");
		$("#tip-businessName").css("background","");
	} else{
		$("#businessName_span").html("");
		$("#businessName_x").html("");
		//$("#tip-businessName").css("background","url(" + themepath + "images/icon.JPG) no-repeat 20px");
	}

}

function chkCompanyNo(obj){
	
	if (isEmpty(obj.value)) {
		$("#companyNo_span").html("请填写企业的工商注册号");
		$("#tip-linkCompanyNo").css("background","");
	} else{
		$("#companyNo_span").html("");
		$("#companyNo_x").html("");
	}

}

function chkCardnumber(obj){
	
	var num = checkNUM(document.getElementById("cardnumber").value);
	if(num==0){
		$("#cardnumber_span").html("输入的身份证号必须是数字");
		$("#tip-cardnumber").css("background","");
	}else{
		$("#cardnumber_span").html("");
		$("#cardnumber_x").html("");
	}
}

function chkregisteredcapital(obj){
	var num = checkNUM(document.getElementById("registeredcapital").value);
	if(num==0){
		$("#registeredcapital_span").html("输入的注册资金必须是数字");
		$("#tip-registeredcapital").css("background","");
	}else{
		$("#registeredcapital_span").html("");
		$("#registeredcapital_x").html("");
	}
}

function chkLoanTimeLen(obj){
	var num = checkNUM(document.getElementById("loanTimeLen").value);
	if(num==0){
		$("#loanTimeLen_span").html("输入的周期必须是数字");
		$("#tip-loanTimeLen").css("background","");
	}else{
		$("#loanTimeLen_span").html("");
	//	$("#loanTimeLen_x").html("");
	}
}

function chkExpectAccrual(obj){
	var num = checkNUM(document.getElementById("expectAccrual").value);
	if(num==0){
		$("#expectAccrual_span").html("输入的期望费率必须是数字");
		$("#tip-expectAccrual").css("background","");
	}else{
		$("#expectAccrual_span").html("");
		$("#expectAccrual_x").html("");
	}
}

function chkLoanUse(){
	if(isEmpty(document.getElementById("loanUse").value)){
		$("#loanUse_span").html("借款用途不能为空");
		$("#tip-loanUse").css("background","");
	}else{
		$("#loanUse_span").html("");
		$("#loanUse_x").html("");
	}
}

function chkRebatesUse(){
	if(isEmpty(document.getElementById("rebatesUse").value)){
		$("#rebatesUse_span").html("还款来源不能为空");
		$("#tip-rebatesUse").css("background","");
	}else{
		$("#rebatesUse_span").html("");
		$("#rebatesUse_x").html("");
	}
}

/*function chkLegalEmail(obj){
	
		if (isEmpty(obj)) {
			$("#legalEmail_span").html("邮箱不能为空");
			$("#tip-legalEmail").css("background","");
		}
		if (!$("#legalEmail")
				.val()
				.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
			$("#legalEmail_span").html("邮箱格式不正确");
			$("#legalEmail_x").css("background","");
		}
	
}*/
// 校验融资期限
function chkLoanTime(obj) {
	if (isEmpty(obj.value)) {
		$("#loanTimeLen_span").html("请填写融资期限");
		$("#tip-loanTimeLen").css("background","");
	} else if (obj.value.length > 5) {
		$("#loanTimeLen_span").html("请填写正确的融资期限");
		$("#tip-loanTimeLen").css("background","");
	} else if(!obj.value.match(/^[0-9]*$/)){
		$("#loanTimeLen_span").html("请填写数字");
		$("#tip-loanTimeLen").css("background","");
	}else {
		$("#loanTimeLen_span").html("");
		//$("#timeLen_x").html("");
		//$("#tip-loanTimeLen").css("background","url(" + themepath + "images/icon.JPG) no-repeat 35px");
	}

}
function chkLoanTimeB(obj) {
	if (isEmpty(obj.value)) {
		$("#loanTimeLenB_span").html("请填写融资期限");
		$("#tip-loanTimeLenB").css("background","");
	} else if (obj.value.length > 5) {
		$("#loanTimeLenB_span").html("请填写正确的融资期限");
		$("#tip-loanTimeLenB").css("background","");
	} else if(!obj.value.match(/^[0-9]*$/)){
		$("#loanTimeLenB_span").html("请填写数字");
		$("#tip-loanTimeLenB").css("background","");
	}else {
		$("#loanTimeLenB_span").html("");
		$("#timeLen_x").html("");
		//$("#tip-loanTimeLen").css("background","url(" + themepath + "images/icon.JPG) no-repeat 35px");
	}

}
// 检查用户名
function chkUserName(obj) {
	$("#spanUserId").html("");
	$("#spanUserId2").html("");
	document.getElementById("spanUserId").style.background = "";
	if (isEmpty(obj.value)) {
		document.getElementById("spanUserId").style.color = "red";
		$("#spanUserId").html("用户名不能为空");
		$("#tip-username").css("background", "");
	} else if (obj.value.length < 6 || obj.value.length > 16) {
		document.getElementById("spanUserId").style.color = "red";
		$("#spanUserId").html("用户名只允许字母、数字、下划线、横线组成、不支持纯数字， 需要 6-16个字符");
		$("#tip-username").css("background", "");
	} else if (!obj.value.match(/^[a-zA-Z0-9_]+$/)) {
		document.getElementById("spanUserId").style.color = "red";
		$("#spanUserId").html("用户名只允许字母、数字、下划线、横线组成，不支持纯数字， 需要 6-16个字符");
		$("#tip-username").css("background", "");
		return false;
	} else if (obj.value.match(/^\d+$/)) {
		document.getElementById("spanUserId").style.color = "red";
		$("#spanUserId").html("用户名只允许字母、数字、下划线、横线组成，不支持纯数字， 需要 6-16个字符");
	} else {
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : basepath + "/user/isExistBpCustMember.do?loginname="
					+ obj.value,
			cache : false,
			success : function(responseText, statusText) {
				if (responseText.success) {
					document.getElementById("spanUserId").style.color = "red";
					// $("#spanUserId").classname = "orange middle";
					$("#spanUserId").html(responseText.errMsg);// alert(responseText.errMsg);
				} else {
					document.getElementById("spanUserId").style.color = "#999";
					$("#spanUserId").html(responseText.errMsg);
					//document.getElementById("tip-username").style.background = "url("+ themepath + "images/icon.JPG) no-repeat 20px ";// 120px

				}
			},
			error : function(request) {
			}
		});
	}
}
// 检查 密码
function chkPass(obj) {

	// 校验密码
	if (isEmpty(obj.value)) {
		$("#password_span").html("密码不能为空");
		$("#password_span").css('color', 'red');
		$("#tip-password").css("background", "");
	} else if (obj.value.length < 6 || obj.value.length > 16) {
		$("#password_span").html("密码长度为6~16个字符");
		$("#password_span").css('color', 'red');
		$("#tip-password").css("background", "");
	} else if (!obj.value.match(/^[a-zA-Z0-9]+$/)) {
		$("#password_span").html("密码只能由字母和数字组成");
		$("#password_span").css('color', 'red');
		$("#tip-password").css("background", "");
	} else {
		$("#password_span").html("此密码可用");
		$("#password_span").css('color', '#999');
		//$("#tip-password").css("background","url(" + themepath + "images/icon.JPG) no-repeat 20px");
	}
}
// 检查密码一致性
function chkRepPass(obj) {
	// 校验确认密码
	if (isEmpty(obj.value)) {
		$("#repeat_password_span").html("请确认密码");
		$("#repeat_password_span").css('color', 'red');
		$("#tip-repeat-password").css("background", "");
	} else if (obj.value != $("#password").val()) {
		$("#repeat_password_span").html("两次输入密码不一致");
		$("#repeat_password_span").css('color', 'red');
		$("#tip-repeat-password").css("background", "");
	} else {
		$("#repeat_password_span").html("密码已确认");
		$("#repeat_password_span").css('color', '#999');
		//$("#tip-repeat-password").css("background","url(" + themepath + "images/icon.JPG) no-repeat 20px");
	}

}

// 检查交易密码一致性
function chkRepPasss(obj) {
	// 校验确认密码
	if (isEmpty(obj.value)) {
		$("#repeat_password_span").html("请确认密码");
		$("#repeat_password_span").css('color', 'red');
		$("#tip-repeat-password").css("background", "");
	} else if (obj.value != $("#paymentCode").val()) {
		$("#repeat_password_span").html("两次输入密码不一致");
		$("#repeat_password_span").css('color', 'red');
		$("#tip-repeat-password").css("background", "");
	} else {
		$("#repeat_password_span").html("");
		//$("#tip-repeat-password").css("background","url(" + themepath + "images/icon.JPG) no-repeat 20px");
	}

}

// 校验邮箱
function validatEmail(obj) {
	$("#spanEmailId").html("");
	$("#spanEmailId2").html("");
	document.getElementById("spanEmailId").style.background = "";
	document.getElementById("tip-useremail").style.background = "";
	var email = $("#email").val();

	// 校验邮箱
	if (isEmpty($("#email").val())) {
		document.getElementById("spanEmailId").style.color = "red";
		$("#spanEmailId").html("邮箱不能为空");
		return false;
	}
	if (!$("#email")
			.val()
			.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
		document.getElementById("spanEmailId").style.color = "red";
		$("#spanEmailId").html("邮箱格式不正确");
		return false;
	}

	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : basepath + "/user/isExistEmailBpCustMember.do?email=" + email,
		cache : false,
		success : function(responseText, statusText) {
			if (responseText.success) {
				document.getElementById("spanEmailId").style.color = "red";
				$("#spanEmailId").html(responseText.errMsg);// alert(responseText.errMsg);
			} else {
				document.getElementById("spanEmailId").style.color = "#999";
				$("#spanEmailId").html("邮箱可以使用");
				//document.getElementById("tip-useremail").style.background = "url("+ themepath + "images/icon.JPG) no-repeat 20px";

			}
		},
		error : function(request) {
		}
	});
}


/**
 * 验证验证码
 * 
 * @param {}
 *            obj
 * @return {Boolean}
 */
function validatCheckCode(obj) {//个人
	$("#spancheckCode").html("");
	document.getElementById("spancheckCode").style.background = "";
	if ($("#checkCode").val().length == 4) {
		var checkCode = $("#checkCode").val();
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : basepath + "/user/isRightCheckCodeBpCustMember.do?checkCode="
					+ checkCode,
			cache : false,
			success : function(responseText, statusText) {
				if (responseText.success) {
					$("#typeCode").html("success");
					document.getElementById("spancheckCode").style.color = "#999";
					$("#spancheckCode").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					document.getElementById("spancheckCode").style.background = "url("+ themepath + "images/icon.JPG) no-repeat";
				} else {
					$("#typeCode").html("faile");
					document.getElementById("spancheckCode").style.color = "red";
					$("#spancheckCode").html(responseText.errMsg);
				}
			},
			error : function(request) {
			}
		});
	}
}
function business_validatCheckCode(obj) {//企业
	$("#business_spancheckCode").html("");
	document.getElementById("business_spancheckCode").style.background = "";
	if ($("#checkCodeB").val().length == 4) {
		var checkCode = $("#checkCodeB").val();
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : basepath + "/user/isRightCheckCodeBpCustMember.do?checkCode="
					+ checkCode,
			cache : false,
			success : function(responseText, statusText) {
				if (responseText.success) {
					$("#typeCode").html("success");
					document.getElementById("business_spancheckCode").style.color = "#999";
					$("#business_spancheckCode").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					document.getElementById("business_spancheckCode").style.background = "url("+ themepath + "images/icon.JPG) no-repeat";
				} else {
					$("#typeCode").html("faile");
					document.getElementById("business_spancheckCode").style.color = "red";
					$("#business_spancheckCode").html(responseText.errMsg);
				}
			},
			error : function(request) {
			}
		});
	}
}
function checkTelphone(){
	$("#typeTelphone").text("");
	var telphone= $("#telphone").val();
	if (isEmpty($("#telphone").val())) {
				showTip("telphone", "手机号码不能为空");
				return false;
			}
		if (!isMobile($("#telphone").val())) {
			showTip("telphone", "手机号码格式不正确");
			return false;
		}
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : basepath + "/user/isTelphoneBpCustMember.do?telphone="
					+ telphone,
			cache : false,
			success : function(responseText, statusText) {
				if (responseText.success) {
					if(responseText.result=="1"){
						showTip("telphone", responseText.errMsg);
						$("#typeTelphone").text("1");
					}
				} else {
					if(responseText.result=="1"){
						showTip("telphone", responseText.errMsg);
						$("#typeTelphone").text("1");
					}
				}
			},
			error : function(request) {
				return false;
			}
		});
}
$(function() {
	$("#checkUpload").click(function() {
		var lid=$("#loadid").val();
		$.ajax({
			url: basepath + "/loan/savefileUploadP2pLoanProduct.do",
			dataType:"json",
			data:{loadid:lid},
			success:function(data){
				if(data.result=='0'){
						ymPrompt.alert({title:'借款提示信息',message:data.message,width:280,height:150});
				}else{
					window.location.href = basepath + "user/successUploadBpCustMember.do";
				}
			}
		});
	});
});


