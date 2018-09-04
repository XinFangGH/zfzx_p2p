var userId = "U000022626";
	$(function() {
		$('#navBar li:nth-child(5)').children('span').show();
		$("#innerNav li.prepaid").addClass("current").append("<em class=\"curr\">&nbsp;</em>");

		
		

		
		
		/*  个人中心已经是动态 获取值了为什么还要多此一步 再请求一次呢？
		 *  动态加载的时候一次性赋值不久可以吗？
		 *  而且没有 用户的钱多多标识 怎么查呢？
		 *  没有参数吗？
		var ue=$.ajax({
        async:false,
        url: basepath+"/pay/balanceQueryPay.do",
        beforeSend:function(){
        }
       }).responseText;//账户余额
       
       var yue = "100.00|200.00|300.00";//乾多多平台返回的账户余额  网贷平台账户余额|可用余额(网贷平台账户余额+公共账户余额)|冻结余额
	var strs = new Array();
	strs = ue.split("|");
	$("#moneyaccount").val(strs[1]);*/
 		
		$('#rechargeNext').click( function() {
		    var amount = $.trim($('#amountShow').val());
		    
			
	   		if(chkRegMoney('amountShow')){
	   			if($('#amountShow').val()<1){
	   				$("#money_span").html("<span style='font-size:12px;'>最小金额不能小于1元。</span>");
					return false;
				}

	   		if($("#thirdPay").length>0&&"moneyMoreMoreConfig"==$("#thirdPay").val()){
	   		
	   		}else{
	   		//判断是否选中的银行
	   		if($("input:radio").length!=0){
	   		if($("input:radio:checked").length==0){
				showTip('amountShow', '请选择进行充值的账户银行');
				return false;
			}}
	   		

	   		}
	   		$("#recharge").html("充值金额:"+amount+"元");
	   		$('a[rel*=leanModal]').leanModal({ top : 200 });	
			$("#submitrecharge").click(function(){
			  var rechargeNextForm = $('#rechargeNextForm');//location.href =basepath+"/financePurchase/detailFinancePurchase.do"
			  $("#submitrecharge").unbind("click").attr("value","   充值完成  »   ").click(function(){window.open(basepath+"/financePurchase/detailFinancePurchase.do");  });
			  $("#updatemoney").unbind("click").attr("value","   充值失败  »   ").click(function(){location.href =basepath+"/financePurchase/detailFinancePurchase.do"});
			  $("#lean_overlay_close").unbind("click").click(function(){location.href =basepath+"/financePurchase/detailFinancePurchase.do"});
			  rechargeNextForm.submit();
			});	
			     //return true;
	   		}else{
	   			return false;
	   		}
		});
		/*
		$('#recharge').click( function() {
		    
			$.ajax({
        type: "POST",
        url: basepath+"/pay/rechargePay.do",
        dataType: 'json',
         data: { 
               "loginname":$("#loginname").val(),
               "user_account_money":$("#user_account_money").val(),
              "checkbank":$("#checkbank").val()
               },
        beforeSend:function(){
        },
       success: function(responseText, statusText) {
                if (statusText == "success") {
                	if(responseText.ResultCode == 88){
                		
                		alert("提现成功");
                	}else{
                		var message='';
                		var ResultCode=responseText.ResultCode;
                		 if(ResultCode==89){
				        	 message="提现资金退回";
				         }else if(ResultCode==01){
				        	 message="用户乾多多标识错误";
				         }else if(ResultCode==02){
				        	 message="平台乾多多标识错误";
				         }else if(ResultCode==03){
				        	 message="提现订单号错误";
				         }else if(ResultCode==04){
				        	 message="金额错误";
				         }else if(ResultCode==05){
				        	 message="卡信息错误";
				         }else if(ResultCode==06){
				        	 message="加密验证失败";
				         }else if(ResultCode==07){
				        	 message="余额不足";
				         }else if(ResultCode==08){
				        	 message="提现失败";
				         }else if(ResultCode==09){
				        	 message="支付密码输入错误";
				         }else if(ResultCode==10){
				        	 message="短信验证码输入错误";
				         }else if(ResultCode==11){
				        	 message="安保问题输入错误";
				         }else if(ResultCode==12){
				        	 message="账户正在被别的线程操作";
				         }else if(ResultCode==13){
				        	 message="手续费比例错误";
				         }else if(ResultCode==14){
				        	 message="平台自有账户余额不足";
				         }
                		alert(message);
                	}
                } 
            }
    })
		*/	
			
			
		//充值成功
		$('#successPay').click(function(){
			self.location=basepath+'/financePurchase/detailFinancePurchase.do';
		});
		
		//关闭弹出层
		$('.js_close').click(function(){
			$('#backShow').hide();
			$('.mask-layer').remove();
		    self.location=basepath+'/financePurchase/rechargeFinancePurchase.do';
		});
	});

	
	
	//校验数字长度，限制9位
	function checkLength(obj)
	{
		var src = obj.value;
		var target = src.replace(new RegExp(",","gm"),""); 
		if(target.length>9)
		{
			document.getElementById("amountShow").value=target.substr(0,9);
		}
	}

function chkRegMoney(idval) {
 	var object=$("#"+idval);
     var reg = /(?!^0\d*$)^\d{1,16}(\.\d{1,2})?$/;
     flag = reg.test(object.val());
     if (object.val() == ""){
     	$("#money_span").html("<span style='font-size:12px;'>金额不能为空。</span>");
     return false;
     } else if (!flag) {
        $("#money_span").html("<span style='font-size:12px;'>金额必须大于0且为整数或小数。</span>");
         object.focus();
         return false;
     } else {
     	if($('#payType').val()=="1" && $("#isSupportShortcut").val()=="1") {
     		var signDealQuota = $("#signDealQuota").val();
     		if(parseFloat(object.val()) > parseFloat(signDealQuota)) {
     			 $("#money_span").html("<span style='font-size:12px;'>金额超过单笔充值上限</span>");
     			  return false;
     		}else {
     			  $("#money_span").html("");
	        	 return true;
     		}
     	}else {
	         $("#money_span").html("");
	         return true;
     	}
     }
 }