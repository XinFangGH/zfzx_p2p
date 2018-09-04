<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <meta  http-equiv="x-rim-auto-match" content="none">
    <title>升升投 - 登录</title>
    <script type="text/javascript" src="${base}/js/jQuery/jquery-1.8.2-min.js"></script>
    <script type="text/javascript" src="${base}/js/user/login.js"></script>
    <script type="text/javascript" src="${base}/js/fingerprint.js"></script>
    <link rel="stylesheet"  media="all" type="text/css" href="${base}/theme/${systemConfig.theme}/css/mobile.css" />
</head>
<body>

    <div id="wrap" class="wad_loginbg">
        <div class="wad_mobile">
            <div class="login_header">
                <a class="btn_back" href="javascript:history.back(-1)"><i class="icon ico_back">返回</i></a>
                <h1><i class="logo_ico"></i></h1>
            </div>
            <div class="wad_login">
            	<#if err!=null>
               	 <p class="wad_cuowutishi">错误提示信息：<a>${err}</a></p> 
                </#if>
                 <form id="register-form" action="mtologin.do" method="post">
                  <input type="hidden" id="fingerprint" name="fingerprint" value=""/>
                  	 <ul>
                        <li><input type="text"  placeholder="请输入升升投用户名" name="loginname" value="${loginname}"><em></em></li>
                        <li><input type="password" placeholder="请输入密码" name="password"><em></em></li>
                    </ul>
                    <input type="hidden" value="${refererurl}" name="refererurl">
                    <div class="wad_bar"><a>忘记密码?</a></div>
                    <!--<a href="${base}/user/getBpCustMember.do?mobile=mobile" class="wad_btn_login" id="loginBtn" >登录</a>-->
                     <a href="javascript:void(0)" class="wad_btn_login" id="loginBtn" >登录</a>
                    <p class="wad_link_reg">还没有帐号？<a href="${base}/mobileReglogin.do">立即注册</a></p>
                </form>
            </div>
        </div>
    </div>
    <script src='src="${base}/js/mobile/zepto.min.js'></script>
    <script type="text/javascript">
        $(".wad_allnav").click(function(){
            $(".wad_slidernav").toggle();
        });
        $("#loginBtn").click(function(){
        var name = $("#loginname").val();
        var word = $("#password").val();
        $("#register-form").submit();
        });
    $(function(){
		var fingerprint = new Fingerprint().get();
		$("#fingerprint").val(fingerprint);
	});
    </script>
</body>
</html>