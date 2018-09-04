<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <meta  http-equiv="x-rim-auto-match" content="none">
    <title>升升投 - 注册</title>
    <link rel="stylesheet"  media="all" type="text/css" href="${base}/theme/${systemConfig.theme}/css/mobile.css" />
</head>
<body>
    <div id="wrap" class="wad_loginbg">
        <div class="wad_mobile">
            <div class="login_header">
                <a class="btn_back" href="javascript:history.back(-1)"><i class="icon ico_back">返回</i></a>
                <h1><i class="logo_ico"></i></h1>
            </div>

            
          
            <div class="wad_chenggong" id="wad_chenggong" >
                <p>借款申请成功！</p>
                <p><a href="${base}/thirdreg.do?mobile=mobile">开通易宝账户</a></p>
                <div class="wad_shoujili" style="width: 90%;margin: 0 5%;"><div class="wad_voice" style="text-align:center;">如果您不开通易宝账户，将 <a class="wad_shouji" href="">无法在升升投平台上投标或融资</a></div></div>
            </div> 

           
            
           
           
        </div>
        <div class="wad_footer_banquan">升升投携手易宝支付，<br>共同为客户打造诚心、可靠、安全的投资理财平台</div>
        <div class="wad_mobile wad_mobilelogin"><h1><i class="logo_ico"></i><i class="logo_ico"></i></h1></div> 
    </div>
    <script src='${base}/js/mobile/zepto.min.js'></script>
    <script type="text/javascript">
    var i = 60;
    function a(){
    	$("#meg").html(i+"秒后可以重新获取");
    	$("#meg").css("color","gray");
    	$("#meg").attr("onclick","");
    	
    	i = i-1;
    	if(i<=0){
    		$("#meg").html("获取手机验证码");
    		$("#meg").css("color","#fff");
    		$("#meg").attr("onclick","tomsg()");
    	}else{
    		setTimeout(a, 1000);
    	}
    	
    }
        $(".wad_allnav").click(function(){
            $(".wad_slidernav").toggle();
        });
        
        function tomsg (){
        $("#errorMsg").html("");
        var telphone = $("#telphone_reg_random").val();
        if(telphone==''||telphone==null){
        	$("#errorMsg").html("提示：请填写手机号码！");
        	return false;
        }
        var url = 'codecreate.do'
        i = 60
        $.ajax({
				url:url,
				dataType:"json",
				data:{telphone:telphone},
				success:function(data){
					if(data.status!='200'){
						$("#errorMsg").html("提示："+data.remark);
					}else{
						a();
					}
				}
			});
        };
        
        $("#mreg").click(function(){
        if(!document.getElementById("agree").checked){
        	$("#errorMsg").html("错误提示：请阅读并接受《服务协议》");
        	return false;
        }
        $("#errorMsg").html("");
      	var uss = $("#username").val();
        var pa = $("#pass").val();
        var wo =$("#word").val();
        var trr = $("#telphone_reg_random").val();
        var cc = $("#checkCode").val();
        $.ajax({
        	url:'../mobilegather/reg_BGAction.do',
				dataType:"json",
				data:{username:uss,pass:pa,word:wo,telphone_reg_random:trr,checkCode:cc},
				success:function(data){
					if(data.success){
					$("#errorMsg").html("错误提示："+data.errMsg);
					
					}else{
						window.location.href="../mobilegather/productlist_BGAction.do";
						
					}
				}
        
        });
        
        });
        
    </script>
</body>
</html>