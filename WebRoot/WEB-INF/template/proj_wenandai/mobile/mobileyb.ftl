<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <meta  http-equiv="x-rim-auto-match" content="none">
    <title>升升投 - 易宝绑定</title>
        <link rel="stylesheet"  media="all" type="text/css" href="${base}/theme/${systemConfig.theme}/css/mobile.css" />
        <#--<script type="text/javascript" src="${base}/js/user/thirdPay.js"></script>-->
		<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript">
	function paybind(){
	
	var meg=document.getElementById('errMeg');
	//校验手机号码
    if (isEmpty($("#telphone").val())) {
    	meg.innerHTML='错误提示信息：手机号码不能为空！';
        return false;
    }
    if (!isMobile($("#telphone").val())){
		meg.innerHTML='错误提示信息：手机号码格式不正确！';
		return false;
	}
    //校验邮箱
    if (isEmpty($("#email").val())) {
    	meg.innerHTML='错误提示信息：邮箱不能为空！';
        return false;
    }
	if (!$("#email").val().match( /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
    	meg.innerHTML='错误提示信息：邮箱格式不正确！';
        return false;
    }
    //当输入的邮箱不为空时，效验邮箱的格式
    if($("#email").length>0){
	     if (!isEmpty($("#email").val())) {
	    	if (!$("#email").val().match( /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
	    		meg.innerHTML='错误提示信息：邮箱格式不正确！';
	        	return false;
	    	}
	    }
    }
    
     //校验真实姓名
    if (isEmpty($("#truename").val())) {
    	meg.innerHTML='错误提示信息：真实姓名不能为空！';
        return false;
    }
     //校验身份证号
    if (isEmpty($("#cardcode").val())) {
   		meg.innerHTML='错误提示信息：身份证号不能为空！';
        return false;
    }
    //校验证件号码
	if (isEmpty($("#cardcode").val())){
		meg.innerHTML='错误提示信息：证件号码不能为空！';
		return false;
	}else if(!isIdCardNo($("#cardcode").val())){
		meg.innerHTML='错误提示信息：无效的身份证号码！';
		return false;
	}
    $("#registerPayForm").submit();
}

</script>
</head>
<body>
    <div id="wrap" class="wad_loginbg">
        <div class="wad_mobile">
            <div class="login_header">
                <a class="btn_back" href="javascript:history.back(-1)"><i class="icon ico_back">返回</i></a>
                <h1 class="yibaologo"><i class="logo_ico"></i></h1>
            </div>
            <div class="wad_login">
            	<p class="wad_cuowutishi"><span id="errMeg"></span></p> 
                <p class="wad_cuowutishi" style="font-size: 14px;color: #38b54a;margin: -28px 0 30px;text-align: center;"><a>易宝支付第三方帐号绑定</a></p>
                <form id="registerPayForm" name="registerForm" action="${base}/pay/registerAndBindPay.do?mobile=mobile" method="post">
                    <input id="loginId" type="hidden" value="${bpCustMember.id}" />
                    <ul>
                        <li><input id="loginname" readonly name="loginname" type="text" placeholder="输入用户名" value="${bpCustMember.loginname}"></li>
                        <li><input id="telphone" name="telphone" type="text" placeholder="输入注册的手机号" value="${bpCustMember.telphone}"></li>
                        <li><input id="email" name="email" type="text" placeholder="输入常用邮箱" value="${bpCustMember.email}"></li>
                        <li><input id="truename" name="truename" type="text" placeholder="输入您的真实姓名" value="${bpCustMember.truename}"></li>
                        <li><input id="cardcode" name="cardcode" type="text" placeholder="输入您的真实身份证号" id="idCard" value="${bpCustMember.cardcode}"><em id="emIdcard"></em></li>
                    </ul>
                    <div class="wad_shoujili"><div class="wad_voice">
                      输入完整正确的信息后， <a class="wad_shouji" href="">页面将调转到易宝官网进行最后绑定</a></div></div>
                    <a href="javascript:;" class="wad_btn_login" onClick="paybind()" id="bandPayBtn">绑定</a>
                    <p class="wad_link_reg">已经有帐号了！<a>立即登录</a></p>
                </form>
            </div>
        </div>
        <div class="wad_footer_banquan">升升投携手易宝支付，<br>共同为客户打造诚心、可靠、安全的投资理财平台</div>
        <div class="wad_mobile wad_mobilelogin"><h1><i class="logo_ico"></i><i class="logo_ico"></i></h1></div> 
    </div>
    
    <script src='${base}/js/mobile/zepto.min.js'></script>
    <script src='${base}/js/mobile/common.js'></script>
    <script type="text/javascript">
        $(".wad_allnav").click(function(){
            $(".wad_slidernav").toggle();
        });
        $(function () {
            $('#idCard').on('change', function () {
                if (checkIdcard($(this).val())) {
                    $('#emIdcard').addClass('wad_right').removeClass('wad_wrong');
                } else {
                    $('#emIdcard').addClass('wad_wrong').removeClass('wad_right');
                }
            })
        });
    </script>
</body>
</html>