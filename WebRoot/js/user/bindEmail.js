//校验表单邮箱验证
function checkEmailForm(){
    if (isEmpty($("#email").val())) {
        showTip("email", "邮箱不能为空");
        return false;
    }
    if (!$("#email").val().match( /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
        showTip("email", "邮箱格式不正确");
        return false;
    }
    return true;
}

function num(obj){
    obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
    obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个, 清除多余的
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
}

$(function(){
			//邮箱验证
			$("#bindemail").click(function(){
                if (isEmpty($("#email").val())) {
                    showTip("email", "邮箱不能为空");
                }else if (!$("#email").val().match( /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
                    showTip("email", "邮箱格式不正确");
                }else {
                    $("#bindemailForm").submit();
                }
			});
});

