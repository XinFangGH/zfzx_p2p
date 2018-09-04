<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript">var m1="债权转让",m2="",m3="";</script>
<script type="text/javascript" src="${base}/js/user/mmplan.js"></script>
</head>
<script type="text/javascript">
$(function(){


		$("#get_VerifySms").click(function(){
			var verify_num =$("#verify_num").text();
			if(verify_num=="0"){
				get_ValidateSms("get_VerifySms", "telphone", "verify_sms", 120 , "bind_telphone");	
			}
		});
		function get_ValidateSms(getVerifySmsId, mobileId, verifySmsId, seconds, type) {
			var codeValue=$("#typeCode").html();
			if(codeValue===''){
				$("#spancheckCode").html('请输入验证码');
				return;
			}else if(codeValue!='success'){
				$("#spancheckCode").html('验证码错误');
				return;
			}
			var temp = seconds;
			var getVerifySmsObj = $("#"+getVerifySmsId);
			var mobile = $("#"+mobileId);
			var token = null;
			// 禁用获取短信验证码按钮
			debugger
			getVerifySmsObj.addClass("disabled");
			$("#"+verifySmsId).removeAttr("readonly");
			var text = getVerifySmsObj.html();
			//每次进入先清空
			getVerifySmsObj.html(text);
			var url = basepath + "user/getTelphoneCodeBpCustMember.do?telphone="+mobile.val();
			$.ajax({
				url : url,
				dataType : "json",
				success : function(result) {
				debugger
					if (result.success == true) {
						$("#showMsg_p").parent().css("display","block");
						$("#showMsg_p").html("短信已发送至您手机，请输入短信中的验证码，30分钟内输入有效。");
						var interval = window.setInterval(function() {
							seconds--;
							if (seconds == 0) {
							    $("#verify_num").text(seconds);
								seconds = temp;
								window.clearInterval(interval);
								getVerifySmsObj.removeClass("disabled");
								getVerifySmsObj.html(text.indexOf("重新") == -1? "重新" + text: text);
							} else {
								$("#verify_num").text(seconds);
								getVerifySmsObj.html(text + "(" + seconds + ")");
							}
						}, 1000);
					} else {
						$("#showMsg_p").parent().css("display","block");
						$("#showMsg_p").html(result.remark);
						getVerifySmsObj.removeClass("disabled");
						getVerifySmsObj.html(text.indexOf("重新") == -1? "重新" + text: text);
					};
				},
				error : function() {
					$("#showMsg_p").parent().css("display","block");
					$("#showMsg_p").html("短信验证码获取失败，请重新获取");
				}
			});
		};
		
	$("#btnExit").click(function(){
	    if($("#btnExit_value").text()=="1"){
		    $("#transfer_Form_out").submit();
		    layer.msg('提前赎回申请成功!',1,1);
		    var seconds = 5;
			var interval = window.setInterval(function() {
				seconds--;
				if (seconds == 0) {
				  parent.location.reload();
				}
			})
	    }
	})

})
	function btn_out(){
		$("#transfer_Form_out").submit();
		var seconds = 2;
		var interval = window.setInterval(function() {
			seconds--;
			if (seconds == 0) {
			  parent.location.reload();
			}
		})
		
		
	}
		function aa(){
			 setTimeout(function(){
				var index = parent.layer.getFrameIndex(window.name);
			 	parent.layer.close(index);
			 },1000);
		}
		
		function submit(){
		alert("99")
			var codeValue=$("#typeCode2").html();
			if(codeValue==='success'){
				$("#transfer_Form_out").submit();
				aa();
			}
		};
		/**
		 * 验证手机短信验证码
		 * @param {}  obj
		 * @return {Boolean}
		 */
		function validatTelCheckCode(obj) {
		    $("#showMsg_p").html("");
			$("#showMsg").html("");
			document.getElementById("showMsg").style.background = "";
			if ($("#verify_sms").val().length == 6) {
				var checkCode = $("#verify_sms").val();
				$.ajax({
					type : "POST",
					dataType : "JSON",
					url : basepath + "/user/isRightTelCheckCodeBpCustMember.do?checkCode="+ checkCode,
					cache : false,
					success : function(responseText, statusText) {
						if (responseText.success) {
							$("#typeCode2").html("success");
							document.getElementById("showMsg").style.color = "#999";
							$("#showMsg").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
							document.getElementById("showMsg").style.background = "url("+ themepath + "images/icon.JPG) no-repeat";
							$("#btnExit").removeClass("buttonogray1");
							$("#btnExit").addClass("buttonoblue1");
							$("#btnExit_value").text(1);
							$("#btnExit").css('cursor','pointer');
						} else {
							$("#typeCode2").html("faile");
							document.getElementById("showMsg").style.color = "red";
							$("#showMsg").html(responseText.errMsg);
							$("#btnExit").removeClass("buttonoblue1");
							$("#btnExit").addClass("buttonogray1");
							$("#btnExit_value").text(0);
							$("#btnExit").css('cursor','default');
						}
					},
					error : function(request) {
					}
				});
			}
		}
	</script>
<body >
<form id="transfer_Form_out"  method="post" action="${base}/creditFlow/financingAgency/submitEarlyOutApplyPlManageMoneyPlanBuyinfo.do">
	<input name="orderId" type="hidden" id="orderId" value=${orderId} />
	<!-- 
	<div>提前支取日期:
		<input type="text" id="earlierOutDate" style="width:80px" name="earlierOutDate" class="colorful1" readonly="readonly" onclick="new Calendar().show(this);"/>
	</div>
	-->
	<div class="quit-box-all">
			<dl class="quit-box">
			<dt>剩余本金：</dt><dd>${buyinfo.buyMoney}元</dd>
			<dt>结算利息：</dt><dd>${loanInterestMoney?string("0.##")}元 <em>（截止到${now}号欠派利息，退出当天无收益）</em></dd>
			<dt>退出费用：</dt><dd>${liquidatedDamagesMoney}元 <em>（退出费率:  ${buyinfo.uearlierOutRate}%）</em></dd>
			<dt>预计回收金额：</dt><dd>${sumMoney}元</dd>
			<dt>绑定手机：</dt><dd>${telphone?substring(0,3)}***${telphone?substring(10)}</dd>
			<dt>图形验证码 ：</dt>
			<dd> 
			<input type="text" id="checkCode" name="checkCode" tabindex="3" size="8"style="width:80px;" maxlength="4" class="colorful1" placeholder="输入验证码" onkeyup="validatCheckCode(this)" />
			<img width="60" height="35" id="loginCode" class="verify-code" alt="点击更换" title="点击更换" onclick="javascript:refresh(document.getElementById('loginCode'));"  src="${base}/getCode.do" style="cursor:pointer;" />
			<span class="normal" style="cursor:pointer;color:#0096c4;width:100px;" onclick="javascript:refresh(document.getElementById('loginCode'));">看不清，换一张</span>&nbsp;&nbsp;
			<span class="orange middle"style="width:100px;" id ="spancheckCode"></span>
			<span  id ="typeCode" style="display:none"></span>
			</dd>
			<dt>手机验证码：</dt>
			<dd>
				<input type="text" id="verify_sms" name="verify_sms" tabindex="6" size="10"style="width:80px;" maxlength="6"  class="colorful1" placeholder="输入验证码" onkeyup="validatTelCheckCode(this)" />
				<a class="buttonoblue1" id="get_VerifySms">发送验证码</a>
				<span  id ="typeCode2" style="display:none"></span>
				<span id="verify_num" style="display:none">0</span>
			    <span class="black middle" id="showMsg" style="clear:both; padding-left:20px; color: red;"></span>
			</dd>
			<p class="black middle" id="showMsg_p" style="clear:both; padding-left:20px; color: red;"></p>
		</dl>
		<input type="hidden" id="telphone" value="${telphone}"/>
		<p class="btn-all">
			<a class="buttonoblue1" id="btnExit" style="cursor:default; margin:0;">确定退出</a>
			<span id="btnExit_value" style="display:none">0</span> 
			<a onclick="aa()" class="buttonoblue1">取消</a>
		</p>
	</div>
	</form>
</body>
</html>