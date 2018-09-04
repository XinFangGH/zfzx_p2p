/**
 * 注册表单
 * update: 2013.11.05
 */
$(function() {
	var lockNum = $("#lockNum").val();
	if(lockNum>=3){
        $("#mycode").css("display","block");
    }
    
    //登录按钮
    $("#loginBtn").click(function() {
        // var password = $.md5($("#password").val());//md5加密
   		// $("#password").val(password);
        loginAction();
    });
    $('#register-form').on('keyup', 'input.txt', function(e) {
        if (e.keyCode == 13) {
            $("#loginBtn").trigger('click');
        };
    });

   // getAdCodeUrl();
});
// 回车键 登录
 function enterSumbit(){  
      var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异  
     if (event.keyCode == 13){  
     	/* var password = $.md5($("#password").val());//md5加密
         $("#password").val(password);*/
       loginAction();  
     }  
 } 

function loginAction(){
        $("#register-form").ajaxSubmit({
            beforeSubmit: checkForm,
            clearForm: false,
            dataType: "JSON",
            success: function(responseText, statusText) {
                if (statusText == "success") {
                    if (responseText.result == 1) {
                        location.href = responseText.retUrl;
                        $("#register-form").remove();
                    } else {
                    	var lockNum = responseText.lockNum;
                    	if(lockNum>=3){
                    		$("#vcode").css("display","block");
                    	}
                    	//showTip("loginname", responseText.errMsg);
                    	showCommonTip(".common-tip", responseText.errMsg);
                        removeGray("loginBtn");
                        resetLoginForm();
                    }
                } else {
                    showCommonTip(".common-tip", "网络异常，登录失败");
                    removeGray("loginBtn");
                    resetLoginForm();
                }
            },
            error: function() {
                showCommonTip(".common-tip", "网络异常，登录失败");
                removeGray("loginBtn");
                resetLoginForm();
            }
        });
    
}

function resetLoginForm(){
	//$("#loginname").val("");
   // $("#password").val("");
    $("#checkCode").val("");
    refresh(document.getElementById('loginCode'))
}

//校验表单

function checkForm() {
    //校验用户名
    if (isEmpty($("#loginname").val())) {
    	showTip("loginname", "用户名不能为空");
        return false;
    }

    //校验密码
    if (isEmpty($("#password").val())) {
    	showTip("password", "密码不能为空");
        return false;
    }

   if(!$("#vcode").is(":hidden")){
		 //校验验证码
    if (isEmpty($("#valicode").val())) {
    	showTip("valicode", "请填写验证码");
        return false;
    }

  	if ($("#valicode").val().length !=4 ) {
    	showTip("valicode", "验证码必须为4位");
        return false;
    }
   }
    activeGray("loginBtn");
 
    
    return true;
}
