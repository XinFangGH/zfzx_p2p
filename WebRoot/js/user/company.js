/*
 * 企业融资
 */
$(function(){
	$("#addAplyBtn").click(function(){
		var businessName = $("#businessName").val();
		if (isEmpty(businessName)) {
			//$("#businessName_span").html("请填写企业名称");
			//$("#tip-businessName").css("background","");
			//return false;
			ymPrompt.errorInfo({title:'提示信息',message:'请填写企业名称！',width:280,height:150});
			return false;
		}
		
		/*var companyNo = $("#companyNo").val();
		if (isEmpty(companyNo)) {
			$("#companyNo_span").html("请填写企业的工商注册号");
			$("#tip-linkCompanyNo").css("background","");
			return false;
			ymPrompt.errorInfo({title:'提示信息',message:'请填写企业的工商注册号！',width:280,height:150});
			return false;
		} */
		
		var linkPersion = $("#linkPersion").val();
		if (isEmpty(linkPersion)) {
			/*$("#linkPersion_span").html("请填写联系人");
			$("#tip-linkPersion").css("background","");
			return false;*/
			showTip("linkPersion", "请填写借款人");
			//ymPrompt.errorInfo({title:'提示信息',message:'请填写联系人！',width:280,height:150});
			return false;
		} else if (!isChina(linkPersion)) {
			/*$("#linkPersion_span").html("联系人必须为中文");
			$("#tip-linkPersion").css("background","");
			return false;*/
			showTip("linkPersion", "借款人必须为中文");
			//ymPrompt.errorInfo({title:'提示信息',message:'联系人必须为中文！',width:280,height:150});
			return false;
		} else if(linkPersion.length>4){
			/*$("#linkPersion_span").html("联系人不能超过4个字");
			$("#tip-linkPersion").css("background","");
			return false;*/
			showTip("linkPersion", "借款人不能超过4个字");
		//	ymPrompt.errorInfo({title:'提示信息',message:'联系人不能超过4个字！',width:280,height:150});
			return false;
		}
		/*var cardnumber = $("#cardnumber").val();
		if(checkNUM(cardnumber)==0){
			$("#cardnumber_span").html("输入的身份证号必须是数字");
			$("#tip-cardnumber").css("background","");
			return false;
			ymPrompt.errorInfo({title:'提示信息',message:'输入的身份证号必须是数字！',width:280,height:150});
			return false;
		}*/
		var phone = $("#phone").val();
		if (isEmpty(phone)) {
			/*$("#phone_span").html("请填写手机号");
			$("#tip-phone").css("background","");
			return false;*/
			showTip("phone", "请填写手机号");
			//ymPrompt.errorInfo({title:'提示信息',message:'请填写手机号！',width:280,height:150});
			return false;
		} else if (!isMobile(phone)) {
			/*$("#phone_span").html("手机号码格式不正确");
			$("#tip-phone").css("background","");
			return false;*/
			showTip("phone", "手机号码格式不正确");
			//ymPrompt.errorInfo({title:'提示信息',message:'手机号码格式不正确！',width:280,height:150});
			return false;
		}
		
		/*var legalEmail= $("#legalEmail").val();
		if (isEmpty(legalEmail)) {
			$("#legalEmail_span").html("邮箱不能为空");
			$("#tip-legalEmail").css("background","");
			return false;
			ymPrompt.errorInfo({title:'提示信息',message:'邮箱不能为空！',width:280,height:150});
			return false;
		}
		if (!$("#legalEmail").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
			$("#legalEmail_span").html("邮箱格式不正确");
			$("#legalEmail_x").css("background","");
			return false;
			ymPrompt.errorInfo({title:'提示信息',message:'邮箱格式不正确！',width:280,height:150});
			return false;
		}*/
		/*var registeredcapital = $("#registeredcapital").val();
		if(checkNUM(registeredcapital)==0){
			$("#registeredcapital_span").html("输入的注册资金必须是数字");
			$("#tip-registeredcapital").css("background","");
			return false;
			ymPrompt.errorInfo({title:'提示信息',message:'输入的注册资金必须是数字！',width:280,height:150});
			return false;
		}*/
		var purchase_time_start = $("#purchase_time_start").val();
		if(isEmpty(purchase_time_start)){
			/*$("#phone_span").html("公司成立时间不能为空");
			$("#tip-phone").css("background","");
			return false;*/
			showTip("phone", "公司成立时间不能为空");
		//	ymPrompt.errorInfo({title:'提示信息',message:'公司成立时间不能为空！',width:280,height:150});
			return false;
		}
		/*var area = $("#area").val();
		if(isEmpty(area)){
			$("#area_span").html("公司所在地不能为空");
			$("#tip-area").css("background","");
			return false;
			ymPrompt.errorInfo({title:'提示信息',message:'公司所在地不能为空！',width:280,height:150});
			return false;
		}*/
		
		var loanMoney = $("#loanMoney").val();
		if(isEmpty(loanMoney)){
			/*$("#loanMoney_span").html("融资金额不能为空");
			$("#tip-loanMoney").css("background","");
			return false;*/
			showTip("loanMoney", "期望贷款金额不能为空");
		//	ymPrompt.errorInfo({title:'提示信息',message:'融资金额不能为空！',width:280,height:150});
			return false;
		}
		
		/*var loanTimeLen = $("#loanTimeLen").val();
		if(isEmpty(loanTimeLen)){
			$("#loanTimeLen_span").html("输入的周期必须是数字");
			$("#tip-loanTimeLen").css("background","");
			return false;
			ymPrompt.errorInfo({title:'提示信息',message:'输入的周期必须是数字！',width:280,height:150});
			return false;
		}*/
		/*var expectAccrual = $("#expectAccrual").val();
		if(isEmpty(expectAccrual)){
			$("#expectAccrual_span").html("期望费率不能为空");
			$("#tip-expectAccrual").css("background","");
			return false;
			ymPrompt.errorInfo({title:'提示信息',message:'期望费率不能为空！',width:280,height:150});
			return false;
		}*/
		/*var loanUse = $("#loanUse").val();
		if(isEmpty(loanUse)){
			$("#loanUse_span").html("借款用途不能为空");
			$("#tip-loanUse").css("background","");
			return false;
			ymPrompt.errorInfo({title:'提示信息',message:'借款用途不能为空！',width:280,height:150});
			return false;
		}*/
		/*var rebatesUse = $("#rebatesUse").val();
		if(isEmpty(rebatesUse)){
			$("#rebatesUse_span").html("还款来源不能为空");
			$("#tip-rebatesUse").css("background","");
			return false;
			ymPrompt.errorInfo({title:'提示信息',message:'还款来源不能为空！',width:280,height:150});
			return false;
		}*/
		var checkCode = $("#checkCode").val();
		if(isEmpty(checkCode)){
			//alert("验证码不能为空");
			showTip("checkCode", "验证码不能为空");
			//ymPrompt.succeedInfo({title:'提示信息',message:'验证码不能为空！',width:280,height:150});
			return false;
		}
		//模拟提交表单
		$("#applyForm").submit();
	});
});
 
