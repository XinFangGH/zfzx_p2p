$(function() {
$('a[rel*=leanModal]').leanModal({ top : 200 });
	// 注册按钮
	$("#registerBtnWen").click(function() {
		checkForm();
		/*var recommandPerson = $("#recommandPerson").val();
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
				});*/
	});
	$("#readAgreement").click(function(){
		if($("#readAgreement").attr("checked")=="checked"){
			$("#readAgreement_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/successicon.jpg"/>');
			$("#readAgreement_msg").html("已阅读协议");
		}else{
			document.getElementById("readAgreement_msg").style.color = "red";
			$("#readAgreement_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
			$("#readAgreement_msg").html("请选中协议");
		}
	})
});
//最后提交时再次校验
function checkForm(){
	//校验用户名
	var true_loginname = $("#true_loginname").val();
	if(true_loginname!="1"){
		document.getElementById("spanUserId").style.color = "red";
		$("#spanUserId").html("请正确填写用户名");
		$("#loginname").focus();
		$("#spanUserId_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		return false;
	}
	//检验密码
	var true_password = $("#true_password").val();
	if(true_password!="1"){
		document.getElementById("password_span").style.color = "red";
		$("#password_span").html("请正确填写密码");
		$("#password").focus();
		$("#password_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		return false;
	}
	//检验重复密码
	var true_repeat_password = $("#true_repeat_password").val();
	if(true_repeat_password!="1"){
		document.getElementById("repeat_password_span").style.color = "red";
		$("#repeat_password_span").html("请正确填写确认密码");
		$("#repeat_password").focus();
		$("#repeat_password_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		return false;
	}
	//检验手机号码
	var true_telphone = $("#true_telphone").val();
	if(true_telphone!="1"){
		document.getElementById("telphone_span").style.color = "red";
		$("#telphone_span").html("请正确填写手机号码");
		$("#telphone").focus();
		$("#telphone_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		return false;
	}
	//检验图形验证码
	var true_spancheckCode = $("#true_spancheckCode").val();
	if(true_spancheckCode!="1"){
		document.getElementById("spancheckCode").style.color = "red";
		$("#spancheckCode").html("请正确填写图形验证码");
		$("#checkCode").focus();
		$("#spancheckCode_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		return false;
	}
	//检验手机验证码
	var true_phone_msg = $("#true_phone_msg").val();
	if(true_phone_msg!="1"){
		document.getElementById("phone_msg").style.color = "red";
		$("#phone_msg").html("请正确填写手机验证码");
		$("#tel_checkCode").focus();
		$("#phone_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		return false;
	}
	//检验邀请码
	var true_recommandPerson_msg = $("#true_recommandPerson_msg").val();
	if(true_recommandPerson_msg=="0"){
		document.getElementById("recommandPerson_msg").style.color = "red";
		$("#recommandPerson_msg").html("请正确填写邀请码");
		$("#recommandPerson").focus();
		$("#recommandPerson_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		return false;
	}
	//检验部门邀请码
	var true_departrecommandPerson_msg = $("#true_departrecommandPerson_msg").val();
	if(true_departrecommandPerson_msg=="0"){
		document.getElementById("recommandPerson_msg2").style.color = "red";
		$("#recommandPerson_msg2").html("请正确填写邀请码");
		$("#departmentRecommend").focus();
		$("#departrecommandPerson_msg_img2").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		return false;
	}
	//组织机构代码
	var custType = $("#custType").val();
	//判断是企业注册才验证
	if(custType=="1"){
		var truename = $("#truename").val();
		if(truename==""){
			document.getElementById("truename_msg").style.color = "red";
			$("#truename_msg").html("企业名称不能为空");
			$("#truename").focus();
			$("#truename_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
			return false;
		}
		var comRelationPerText = $("#comRelationPerText").val();
		if(comRelationPerText==""){
			document.getElementById("comRelationPerText_msg").style.color = "red";
			$("#comRelationPerText_msg").html("联系人不能为空");
			$("#comRelationPerText").focus();
			$("#comRelationPerText_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
			return false;
		}
		var IDcard = $("#IDcard").val();
		if(IDcard==""){
			document.getElementById("IDcard_msg").style.color = "red";
			$("#IDcard_msg").html("组织机构代码不能为空");
			$("#IDcard").focus();
			$("#IDcard_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
			return false;
		}
	}
	//是否选中服务协议
	if($("#readAgreement").attr("checked")=="checked"){
	}else{
		document.getElementById("readAgreement_msg").style.color = "red";
		$("#readAgreement_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		$("#readAgreement_msg").html("请选中协议");
		return false;
	}
	//第一次提交注册申请后，就禁用提交按钮、防止用户多次点击提交申请
	$("#registerBtnWen").attr("disabled",true);
	$("#registerBtnWen").val("注册申请中...");
	$("#registerFormWen").submit();
}

/**
 * 个人和企业注册用户切换
 */
function sethidden(obj){
		if(obj==0){
		 $("#custType").val(0);
         //企业联系人
		 $("#comRelationPer").attr("hidden",true);
		 
		  //联系电话和联系人电话
		 $("#personRelationTelephone").css("display","block");
		 $("#enterpriseRelationTelehone").css("display","none");
		 $("#telphone").attr("placeholder","手机号码");
		 
		 //公司名称
		  $("#companyName").attr("hidden",true);
		 
		  //组织机构代码
		  $("#companyCardID").attr("hidden",true);
		    //企业客户类型
		  $("#entCompanyType").attr("hidden",true);
		}else if(obj==1){
			$("#custType").val(1);
	        $("#yqm").hide();
	         //企业联系人
			 $("#comRelationPer").attr("hidden",false);
			 
			  //联系电话和联系人电话
			 $("#personRelationTelephone").css("display","none");
			 $("#enterpriseRelationTelehone").css("display","block");
			 $("#telphone").attr("placeholder","企业联系人手机号码");
			 //公司名称
			 $("#companyName").attr("hidden",false);
	 		  //组织机构代码
			 $("#companyCardID").attr("hidden",false);
			 	 //企业客户类型
		    $("#entCompanyType").attr("hidden",false);
		}
}

// 检查用户名
function chkUserName(obj) {
	$("#spanUserId").html("");
	document.getElementById("spanUserId").style.background = "";
	if (isEmpty(obj.value)) {
		document.getElementById("spanUserId").style.color = "red";
		$("#spanUserId").html("用户名不能为空");
		$("#spanUserId_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
	} else if (obj.value.length < 6 || obj.value.length > 16) {
		document.getElementById("spanUserId").style.color = "red";
		$("#spanUserId").html("6-16个字符、可由字母、数字及下划线组成 ");
		$("#spanUserId_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		$("#spanUserId_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
	} else if (!obj.value.match(/^[a-zA-Z0-9_]+$/)) {
		document.getElementById("spanUserId").style.color = "red";
		$("#spanUserId").html("6-16个字符、可由字母、数字及下划线组成 ");
		$("#spanUserId_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		$("#spanUserId_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		return false;
	} else if (obj.value.match(/^\d+$/)) {
		document.getElementById("spanUserId").style.color = "red";
		$("#spanUserId").html("6-16个字符、可由字母、数字及下划线组成 ");
		$("#spanUserId_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		$("#spanUserId_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
	} else {
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : basepath + "/user/isExistBpCustMember.do?loginname="+ obj.value,
			cache : false,
			success : function(responseText, statusText) {
				if (responseText.success) {
					$("#true_loginname").val("");
					document.getElementById("spanUserId").style.color = "red";
					$("#spanUserId").html(responseText.errMsg);
					$("#spanUserId_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
				} else {
					$("#true_loginname").val("1");
					document.getElementById("spanUserId").style.color = "#999";
					$("#spanUserId").html(responseText.errMsg);
					$("#spanUserId_img").empty().append('<img src="'+themepath+'images/successicon.jpg"/>');
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
		$("#password_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
	} else if (obj.value.length < 6 || obj.value.length > 16) {
		$("#password_span").html("密码长度为6~16个字符");
		$("#password_span").css('color', 'red');
		$("#tip-password").css("background", "");
		$("#password_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
	} else if (!obj.value.match(/^[a-zA-Z0-9]+$/)) {
		$("#password_span").html("密码只能由字母和数字组成");
		$("#password_span").css('color', 'red');
		$("#tip-password").css("background", "");
		$("#password_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
	} else {
		$("#password_span").html("此密码可用");
		$("#password_span").css('color', '#999');
		$("#true_password").val("1");
		$("#password_span_img").empty().append('<img src="'+themepath+'images/successicon.jpg"/>');
	}
}
// 检查密码一致性
function chkRepPass(obj) {
	// 校验确认密码
	if (isEmpty(obj.value)) {
		$("#repeat_password_span").html("请确认密码");
		$("#repeat_password_span").css('color', 'red');
		$("#tip-repeat-password").css("background", "");
		$("#repeat_password_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
	} else if (obj.value != $("#password").val()) {
		$("#repeat_password_span").html("两次输入密码不一致");
		$("#repeat_password_span").css('color', 'red');
		$("#tip-repeat-password").css("background", "");
		$("#repeat_password_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
	} else {
		$("#repeat_password_span").html("密码已确认");
		$("#repeat_password_span").css('color', '#999');
		$("#true_repeat_password").val("1");
		$("#repeat_password_span_img").empty().append('<img src="'+themepath+'images/successicon.jpg"/>');
	}

}

function checkTelphone(obj){
	var telphone = $("#telphone").val();
	if(telphone==""){
		telphone = $("#telphone_qy").val();
	}
    var boo = true;
	$("#telphone_span").html("");
	if (isEmpty(telphone)) {
		var boo = false;
		document.getElementById("telphone_span").style.color = "red";
		$("#telphone_span").html("手机号码不能为空");
		$("#telphone_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
	}else{
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : basepath + "/user/isTelphoneBpCustMember.do?telphone="+telphone,
			cache : false,
			success : function(responseText, statusText) {
				if (responseText.success) {
					if(responseText.result=="1"){
						document.getElementById("telphone_span").style.color = "red";
					   $("#telphone_span").html(responseText.errMsg);
					   $("#telphone_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
					   boo = false;
					}
				} else {
					if(responseText.result=="1"){
						$("#true_telphone").val("");
						document.getElementById("telphone_span").style.color = "red";
					    $("#telphone_span").html(responseText.errMsg);
					    $("#telphone_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
						var boo = false;
					}else{
						document.getElementById("telphone_span").style.color = "#999";
					    $("#telphone_span").html(responseText.errMsg);
					    $("#true_telphone").val("1");
					    $("#telphone_span_img").empty().append('<img width="18px"  src="'+themepath+'images/successicon.jpg"/>');
						boo = true;
					}
				}
			},
			error : function(request) {
				return false;
			}
		});
	}
		return boo;
}


function checkTelphone_qy(obj){
	var telphone_qy = $("#telphone_qy").val();
    var boo = true;
	$("#telphone_span").html("");
	if (isEmpty(telphone_qy)) {
		var boo = false;
		document.getElementById("telphone_span").style.color = "red";
		$("#telphone_span_qy").html("手机号码不能为空");
		$("#telphone_span_img_qy").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
	}else{
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : basepath + "/user/isTelphoneBpCustMember.do?telphone="+telphone_qy,
			cache : false,
			success : function(responseText, statusText) {
				if (responseText.success) {
					if(responseText.result=="1"){
						document.getElementById("telphone_span").style.color = "red";
					   $("#telphone_span_qy").html(responseText.errMsg);
					   $("#telphone_span_img_qy").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
					   boo = false;
					}
				} else {
					if(responseText.result=="1"){
						document.getElementById("telphone_span").style.color = "red";
					    $("#telphone_span_qy").html(responseText.errMsg);
					    $("#telphone_span_img_qy").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
						var boo = false;
					}else{
						document.getElementById("telphone_span").style.color = "#999";
					    $("#telphone_span_qy").html(responseText.errMsg);
                        $("#telphone_span_qy").css("color","rgb(153, 153, 153)");

					    $("#true_telphone").val("1");
					    $("#telphone_span_img_qy").empty().append('<img width="18px"  src="'+themepath+'images/successicon.jpg"/>');
						boo = true;
					}
				}
			},
			error : function(request) {
				return false;
			}
		});
	}
		return boo;
}

/**
 * 验证验证码
 * 
 * @param {}
 *            obj
 * @return {Boolean}
 */
function validatCheckCode(obj) {
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
					$("#spancheckCode").html("输入正确");
					$("#true_spancheckCode").val("1");
					$("#spancheckCode_img").empty().append('<img width="18px"  src="'+themepath+'images/successicon.jpg"/>');
					
				} else {
					$("#typeCode").html("faile");
					$("#true_spancheckCode").val("");
					document.getElementById("spancheckCode").style.color = "red";
					$("#spancheckCode").html(responseText.errMsg);
					$("#spancheckCode_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
				}
			},
			error : function(request) {
			}
		});
	}
}

function validatCheckCode2() {
	var checkecode=false;
	if ($("#checkCode").val().length == 4) {
		var checkCode = $("#checkCode").val();
		$.ajax({
			type : "POST",
			dataType : "JSON",
			async:false,
			url : basepath + "/user/isRightCheckCodeBpCustMember.do?checkCode="
					+ checkCode,
			cache : false,
			success : function(responseText, statusText) {
				if (responseText.success) {
					checkecode=true;
				} else {
					$("#typeCode").html("faile");
					$("#spancheckCode").html(responseText.errMsg);
				}
			},
			error : function(request) {
			}
		});
	}
	
	return checkecode;
}

/**
 * 校验手机验证码
 * @param {} obj
 */
function validatTel2CheckCode(obj){
	var tel = $("#telphone").val();
	var tel_qy = $("#telphone_qy").val();
	var teypPhone = $("#typeTelphone").text();
	if (isEmpty(tel)) {
		$("#telphone_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		document.getElementById("telphone_span").style.color = "red";
		$("#telphone_span").html("手机号码不能为空");
		return false;
	} else if (isMobile(tel)==0) {
		$("#telphone_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		document.getElementById("telphone_span").style.color = "red";
		$("#telphone_span").html("手机号码格式不正确");
		return false;
	}else if(!validatCheckCode2()){
		document.getElementById("spancheckCode").style.color = "red";
		$("#spancheckCode_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		$("#spancheckCode").html("图形验证码不正确");
		return false;
	}else if (!checkTelphone(tel)) {
		$("#telphone_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
			$("#telphone_span").html("手机号码已存在");
			document.getElementById("telphone_span").style.color = "red";
			return false;
	}else {
		getValidateSms("getVerifySms", "telphone", "verify_sms", 120 , "bind_telphone");	
	}
}

function validatTel2CheckCode_qy(){
	var tel = $("#telphone_qy").val();
	var teypPhone = $("#typeTelphone").text();
	if (isEmpty(tel)||tel==undefined) {
		$("#telphone_span_img_qy").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		document.getElementById("telphone_span_qy").style.color = "red";
		$("#telphone_span_qy").html("手机号码不能为空");
		return false;
	} else if (isMobile(tel)==0) {
		$("#telphone_span_img_qy").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		document.getElementById("telphone_span_qy").style.color = "red";
		$("#telphone_span_qy").html("手机号码格式不正确");
		return false;
	}else if(!validatCheckCode2()){
		document.getElementById("spancheckCode").style.color = "red";
		$("#telphone_span_img_qy").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		$("#spancheckCode").html("图形验证码不正确");
		return false;
	}else if (!checkTelphone(tel)) {
		$("#telphone_span_img_qy").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
			$("#telphone_span_qy").html("手机号码已存在");
			document.getElementById("telphone_span").style.color = "red";
			return false;
	}else {
		getValidateSms("getVerifySms", "telphone_qy", "verify_sms", 120 , "bind_telphone");	
	}
}







function getValidateSms(getVerifySmsId, mobileId, verifySmsId, seconds, type) {debugger
	var temp = seconds;
		var getVerifySmsObj = $("#" + getVerifySmsId);
		var token = null;
		// 该按钮没有禁用
		if (!getVerifySmsObj.is(".disabled")) {
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
								   document.getElementById("phone_msg").style.color = "red";
								   	$("#phone_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/successicon.jpg"/>');
									$("#phone_msg").html("短信已发送至您手机，30分钟内输入有效。");
									//showTip("phone_msg", "短信已发送至您手机，30分钟内输入有效。");
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
												getVerifySmsObj.html("重新获取" + "(" + seconds + ")");
											}
										}, 1000);
								} else {
									document.getElementById("phone_msg").style.color = "red";
									$("#phone_msg").html(result.remark);
									getVerifySmsObj.removeClass("disabled");
								//	getVerifySmsObj.html(text.indexOf("重新") == -1? "重新" + text: text);
								};
						},
						error : function() {
							$("#showMsg").parent().css("display","block");
							$("#showMsg").html("短信验证码获取失败，请重新获取");
						}
					});

		}else{
			alert("操作次数过多，请稍后在试。。。");
			return false;
		}
}
/**
 * 手机验证码是否正确
 * @param {} obj
 */
function checkTelphoneCode(obj){
	var tel = $("#telphone").val();
	var tel_qy = $("#telphone_qy").val();
	var getVerifySmsObj = $("#getVerifySms");
	var url;
	if(tel_qy!=null && tel_qy!='undefined' && tel_qy!=''){
		url = basepath + "/user/checkPhoneCodereg.do?telphone="+tel_qy+"&checkCode="+obj.value
	}else{
		url = basepath + "/user/checkPhoneCodereg.do?telphone="+tel+"&checkCode="+obj.value
	}
	$("#phone_msg").empty()
	if (isEmpty(tel) && isEmpty(tel_qy)) {
		document.getElementById("phone_msg").style.color = "red";
		$("#telphone_span").html("手机号码不能为空");
	}else if(isEmpty(obj.value)){
		document.getElementById("phone_msg").style.color = "red";
		$("#phone_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		$("#phone_msg").html("请填写手机验证码");
	}else{
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : url,
			cache : false,
			success : function(responseText, statusText) {
				if (responseText.success) {
					if(responseText.result=="2"){
						document.getElementById("phone_msg").style.color = "red";
						 $("#true_phone_msg").val("");
					   $("#phone_msg").html(responseText.Msg);
						$("#phone_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
					}else if(responseText.result=="1"){
						document.getElementById("phone_msg").style.color = "#999";
					   $("#phone_msg").html(responseText.Msg);
					   $("#true_phone_msg").val("1");
					   $("#phone_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/successicon.jpg"/>');
					}else{
						$("#true_telphone").val("");
						document.getElementById("telphone_span").style.color = "red";
					    $("#telphone_span").html(responseText.Msg);
					    $("#telphone_span_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
					      $("#phone_msg_img").empty();
					}
				} else {
					if(responseText.result=="1"){
						document.getElementById("phone_msg").style.color = "red";
					    $("#phone_msg").html(responseText.Msg);
					}
				}
			},
			error : function(request) {
				return false;
			}
		});
	}
}

function checkisExistRecommand(obj){
	var recommandPerson = $("#recommandPerson").val();
	if (recommandPerson=="") {
		$("#true_recommandPerson_msg").val("");
	}else{
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : basepath + "/user/isExistRecommandBpCustMember.do?recommandPerson="+recommandPerson,
			cache : false,
			success : function(responseText, statusText) {
				if (responseText.success) {
					document.getElementById("recommandPerson_msg").style.color = "#999";
					 $("#recommandPerson_msg").html("邀请码有效");
					 $("#true_recommandPerson_msg").val("");
					 $("#recommandPerson_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/successicon.jpg"/>');
				}else {
					document.getElementById("recommandPerson_msg").style.color = "red";
					$("#recommandPerson_msg").html("不存在此邀请码");
					 $("#true_recommandPerson_msg").val("0");
					 $("#recommandPerson_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
				}
			},
			error : function(request) {
				return false;
			}
		});
	}
}

/**
 * 部门推荐码
 * @param {Object} obj
 * @return {TypeName} 
 */
function checkisExistdepartRecommand(obj){debugger;
	var departmentRecommend = $("#departmentRecommend").val();
	if (departmentRecommend=="") {
		$("#true_departrecommandPerson_msg").val("");
	}else{
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : basepath + "/user/isExistdepartRecommandBpCustMember.do?departmentRecommend="+departmentRecommend,
			cache : false,
			success : function(responseText, statusText) {
				if (responseText.success) {
					document.getElementById("recommandPerson_msg2").style.color = "#999";
					 $("#recommandPerson_msg2").html("推荐码有效");
					 $("#true_departrecommandPerson_msg").val("");
					 $("#departrecommandPerson_msg_img2").empty().append('<img width="18px"  src="'+themepath+'images/successicon.jpg"/>');
				}else {
					document.getElementById("recommandPerson_msg2").style.color = "red";
					$("#recommandPerson_msg2").html("不存在此推荐码");
					 $("#true_departrecommandPerson_msg").val("0");
					 $("#departrecommandPerson_msg_img2").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
				}
			},
			error : function(request) {
				return false;
			}
		});
	}
}

function isExistEmail(obj){
	var email = $("#email").val();
	if (email=="") {
		showTip("email", "邮箱不能为空");
        return false;
	}else{
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : basepath + "/user/isExistEmailBpCustMember.do?email="+email,
			cache : false,
			success : function(responseText, statusText) {
				if (responseText.success) {
				}else {
					showTip("email", "邮箱已存在");
				}
			},
			error : function(request) {
				return false;
			}
		});
	}
}
