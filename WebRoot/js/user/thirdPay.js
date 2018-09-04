$(function(){
    //三证合一
    $(".myThreeCard").hide();
    $("#threeType :radio").click(function(){
        var value =  $(this).val();
        if(value==1){
            $("#unifiedCode").show();
            $(".myThreeCard").hide();
        }else{
            $("#unifiedCode").hide();
            $(".myThreeCard").show();
        }
    });
})

function paybind(){
    //校验用户名
    if (isEmpty($("#loginname").val())) {
        showTip("loginname", "用户名不能为空");
        return false;
    }

    /*    //校验邮箱
        if (isEmpty($("#email").val())) {
            showTip("email", "邮箱不能为空");
            return false;
        }
        if (!$("#email").val().match( /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
            showTip("email", "邮箱格式不正确");
            return false;
        }*/
    //当输入的邮箱不为空时，效验邮箱的格式
    if($("#email").length>0){
        if (!isEmpty($("#email").val())) {
            if (!$("#email").val().match( /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
                showTip("email", "邮箱格式不正确");
                return false;
            }
        }
    }


    //校验手机号码
    if (isEmpty($("#telphone").val())) {
        showTip("telphone", "手机号码不能为空");
        return false;
    }

    if (!isMobile($("#telphone").val())){
        //alert("手机号码格式不正确");
        showTip("telphone","手机号码格式不正确");
        return false;
    }

    //校验真实姓名
    if (isEmpty($("#truename").val())) {
        showTip("truename", "真实姓名不能为空");
        return false;
    }

    //校验身份证号
    if (isEmpty($("#cardcode").val())) {
        showTip("cardcode", "身份证号不能为空");
        return false;
    }


    // $("#bandPayBtnDiv").remove();

    $("#registerPayForm").submit();

}

function enterpriseThirdPaybind(){
    //校验用户名
    if (isEmpty($("#loginname").val())) {
        showTip("loginname", "用户名不能为空");
        return false;
    }

    //校验邮箱
    /*  if (isEmpty($("#email").val())) {
          showTip("email", "邮箱不能为空");
          return false;
      }
      if (!$("#email").val().match( /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
          showTip("email", "邮箱格式不正确");
          return false;
      }
      //当输入的邮箱不为空时，效验邮箱的格式
      if($("#email").length>0){
           if (!isEmpty($("#email").val())) {
              if (!$("#email").val().match( /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
                  showTip("email", "邮箱格式不正确");
                  return false;
              }
          }
      }*/


    //校验手机号码
    if (isEmpty($("#telphone").val())) {
        showTip("telphone", "手机号码不能为空");
        return false;
    }

    if (!isMobile($("#telphone").val())){
        //alert("手机号码格式不正确");
        showTip("telphone","手机号码格式不正确");
        return false;
    }

    //校验真实姓名
    if (isEmpty($("#truename").val())) {
        showTip("truename", "企业名称不能为空");
        return false;
    }
    //校验开户银行许可证
    if (isEmpty($("#bankLicense").val())) {
        showTip("bankLicense", "开户银行许可证不能为空");
        return false;
    }
    //判断是否选择的三证合一
    var threeCard = $('input:radio:checked').val();
    if(threeCard==1){//是三证合一
        if (isEmpty($("#threeCardCode").val())) {
            showTip("threeCardCode", "社会信用代码不能为空");
            return false;
        }
    }else{//非三证合一
        //校验组织机构代码
        if (isEmpty($("#cardcode").val())) {
            showTip("cardcode", "组织机构号码不能为空");
            return false;
        }
        //校验税务登记号
        if (isEmpty($("#taxNo").val())) {
            showTip("taxNo", "税务登记号不能为空");
            return false;
        }
        //校验营业执照编号
        if (isEmpty($("#businessLicense").val())) {
            showTip("businessLicense", "营业执照编号不能为空");
            return false;
        }
    }
    //校验法人姓名
    if (isEmpty($("#legalPerson").val())) {
        showTip("legalPerson", "法人姓名不能为空");
        return false;
    }

    //校验法人身份证
    if (isEmpty($("#legalNo").val())){

        showTip("legalNo","法人身份证不能为空");
        return false;
    }

    //校验企业联系人
    if (isEmpty($("#contactPerson").val())) {
        showTip("contactPerson", "企业联系人不能为空");
        return false;
    }
    $("#registerEnterprisePayForm").submit();
}

function fuioupaybind(){
    $("#registerPayForm").submit();
}

//手机绑定
function paybindTelphone(){

    var telphone = $("#telphone").val();

    location.href = basepath+"pay/bindCheckPay.do?loanMoneymoremore="+telphone;



    /*$.ajax({
        type: "POST",
        url: basepath+"pay/bindCheckPay.do",
        dataType: 'json',
         data: {
                 loanMoneymoremore:telphone
               },
        beforeSend:function(){
        },
       success: function(responseText, statusText) {
                if (statusText == "success") {
                    showCommonTip(".common-tip",responseText.CodeMsg);
                    //location.href = basepath+"pay/registerAndBindSuccessPay.do?ResultCode="+responseText.ResultCode+"&CodeMsg="+responseText.CodeMsg+"&MoneymoremoreId="+responseText.MoneymoremoreId;
                //alert(responseText.CodeMsg);
                $("#bandPayBtnDiv").show();
                } else{}
            },
        error:function() {
                showCommonTip(".common-tip", "网络异常，绑定失败");
                $("#bandPayBtnDiv").show();
            }
    })*/
}

//邮箱绑定
function paybindEmail(){
    var email = $("#email").val();
//	if (email=="") {
//		$("#bindPayBtn").attr("onclick","");
//		showTip("email", "邮箱不能为空");
//        return false;
//	}else{
    /*		if(email.match( /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
                    $.ajax({
                    type : "POST",
                    dataType : "JSON",
                    url : basepath + "/user/isExistEmailBpCustMember.do?email="+email,
                    cache : false,
                    success : function(responseText, statusText) {
                        if (responseText.success) {
                            $("#bindPayBtn").attr("onclick","");
                            showTip("email", "邮箱已存在");
                            return false;
                        }else {
                            $("#bindPayBtn").attr("onclick","paybind()");
                        }
                    },
                    error : function(request) {
                        return false;
                    }
                });
            }else{
                showTip("email", "邮箱格式不正确");
            }*/
//	}
}
//验证真实姓名
function checkTruename(){

    var truename = $("#truename").val();
    //验证中文
    var reg=/^[\u4e00-\u9fa5]+$/;
    if(!reg.test(truename))
    {
        //$("#bindPayBtn").attr("onclick","");
        showTip("truename", "真实姓名不合法！");
        return false;
    }else{
        // 	$("#bindPayBtn").attr("onclick","paybind()");
    }
}
//验证身份证号码
function checkCardcode(){
    var cardcode = $("#cardcode").val();
    if(cardcode==""){
        //	$("#bindPayBtn").attr("onclick","");
        showTip("cardcode", "身份证号码不能为空！");
        return false;
    }else{
        $.ajax({
            type : "POST",
            dataType : "JSON",
            url : basepath + "/user/checkCardBpCustMember.do?cardcode="+cardcode,
            cache : false,
            success : function(responseText, statusText) {
                if (responseText.success) {
                    //	$("#bindPayBtn").attr("onclick","paybind()");
                }else {
                    //$("#bindPayBtn").attr("onclick","");
                    showTip("cardcode", "身份证号码格式不正确");
                    return false;
                }
            },
            error : function(request) {
                return false;
            }
        });
    }
}

function goHome(path){

    location.href=path+"/user/getBpCustMember.do";
}

