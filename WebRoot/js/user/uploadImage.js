
$(function() {
  
    var path = $("#path").val();

    //注册按钮
    $("#imageBtn").click(function() {
        $("#imageForm").ajaxSubmit({
            beforeSubmit: checkForm,
            clearForm: false,
            dataType: "JSON",
            success: function(responseText, statusText) {
                if (statusText == "success") {
                    if (responseText.result == 1) {
                    	location.reload(); 
                       //location.href = path+"/user/getBpCustMember.do?id="+responseText.data.id;
                    } else {
                        showCommonTip(".common-tip", responseText.errMsg);
                        //removeGray("registerBtn");
                    }
                } else {
                    showCommonTip(".common-tip", "网络异常，上传图片失败");
                    //removeGray("registerBtn");
                }
            },
            error: function() {
                showCommonTip(".common-tip", "网络异常，上传图片失败");
                //removeGray("registerBtn");
            }
        });
    });


});

//校验表单

function checkForm() {
    
    if (isEmpty($("#file").val())) {
    	showTip("file", "请选择图片");
        return false;
    }

   // activeGray("registerBtn");
  
    
    return true;
}



