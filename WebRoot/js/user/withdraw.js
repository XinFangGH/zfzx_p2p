$(function(){
 		
		//省份列表
    		var provinceList = $.ajax({async:false,url:basepath+"pay/getListBankCode.do"}).responseText;
    		$("#Province").append(provinceList);
    		//城市
    		$("#Province").change(function(){
    			$("#City option").remove();
    			$("#City").append("<option value=''>请选择</option>");
    			if (!isEmpty($(this).val())){
    				$("#provinceName").val(this.options[this.selectedIndex].text);
					$.ajax({
						url:basepath+"pay/getListBankCode.do",
						dataType:"text",
						data: {ParentCode:$(this).val()},
						async:false,
						success:function(data){
							
							if (data){
								$("#City").append(data);
								
					    			$("#City").val();

							}
						}
					});
    			}
    		});
    		
    		//城市
    		$("#Province").trigger("change");
		
		
      	//错误提示
	 	if ("" != ""){
	 		showCommonTip(".common-tip","");
	 	}

	 		//获取银行名称
    		$("#BankCode").change(function(){
    			if (!isEmpty($(this).val())){
    				$("#bankname").val(this.options[this.selectedIndex].text);
    			}
    		});
    		//获取城市
    		$("#City").change(function(){
    			if (!isEmpty($(this).val())){
    				$("#cityName").val(this.options[this.selectedIndex].text);
    			}
    		});
	 
	/*var ue=$.ajax({ 
		data: { 
	        "rechargeMoneymoremore":$("#rechargeMoneymoremore").val()
	        },
        async:false,
        url: basepath+"/pay/balanceQueryPay.do",
        beforeSend:function(){
        }
       }).responseText;
	var yue = "100.00|200.00|300.00";//乾多多平台返回的账户余额  网贷平台账户余额|可用余额(网贷平台账户余额+公共账户余额)|冻结余额
	var strs = new Array();
	strs = ue.split("|");
	$("#moneyaccount").val(strs[1]);*/

	 	
});
	//校验表单
	function submitForm(){
		/**var money = $.trim($("#user_account_money").val());
		var paswd = $.trim($("#user_account_password").val());
 		if (isEmpty(money)){
 			showTip("amountShow","请输入提现金额");
	        return false;
		}
		if (isEmpty(paswd)) {
			showTip("user_account_password", "请输入您的提现密码")
		}
		if(isNaN(money)){
			showTip("amountShow","提现金额必须为数字");
			return false;
		}
		if(parseInt(money)>parseInt("0") ||parseInt("0")==0){
			showTip("amountShow","提现金额不可以大于可提现金额");
			return false;
		}
		
		var val=$('input:radio[name="account_order"]:checked').val();
        if(val==null){
          showTip("bankList", "请选择提现行");
          return false;
        }*/
        activeGray("submit-btn");
  		$("#withdrawForm").submit();
	}
	
	
	
	//校验表单
	function submitNextForm(){
		var isPayMentCode=1;
		var paswd = $.trim($("#paymentCode").val());
		if(chk_Money('amountShow')){
	 	//校验银行
		if (isEmpty($("#BankCode").val())){
			showTip("BankCode	","请选择银行");
          	return false;
		}
		//校验银行卡号
		if (isEmpty($("#CardNo").val())){
			showTip("CardNo","请输入银行卡号");
          	return false;
		}
		
		//校验所在省份
		if (isEmpty($("#Provice").val())){
			showTip("Provice","请选择省份");
          	return false;
		}

		//校验所在城市
		if (isEmpty($("#City").val())){
			showTip("City","请选择城市");
          	return false;
		}
 		
		var money1=$("#user_account_money").val();
		var money2 = $("#moneyaccount").val();
		var num = money1 -money2;
		
		if (num>0) {
			showTip("amountShow", "提现金额不能大于可提现金额");
			return false;
		}
		
			$.ajax({	                                             
			type: "POST", 
			dataType: "JSON",
			url: basepath+"system/sysConfigGlobal.do",
			  async : false,
			cache: false,
			success: function(responseText, statusText) {
				if(statusText=="success"){
					isPayMentCode=responseText.isOpenPayMentCode;
				}else{
				
				}
			},
			error: function(request) {
			}
		});
		$("#withdrawForm").submit();//进入第三方进行处理
		}else{
			return false;
		}
		
	}
	
	//校验绑定银行卡表单
	function submitbindBankCardForm(){
		
	 	//校验银行
		/*if (isEmpty($("#bankId").val())){
			showTip("bankId	","请选择银行");
          	return false;
		}*/
		//校验银行卡号
		if (isEmpty($("#CardNum").val())){
			showTip("CardNum","请输入银行卡号");
          	return false;
		}
		if(isNaN($("#CardNum").val())){
			showTip("CardNum","提现金额必须为数字");
			return false;
		}
		//判断银行卡的位数
		if($("#CardNum").val().length<12||$("#CardNum").val().length>19){
			showTip("CardNum","银行卡号必须是12-19个数字");
          	return false;
		}
		//校验所在省份
		if (isEmpty($("#Provice").val())){
			showTip("Provice","请选择省份");
          	return false;
		}

		//校验所在城市
		if (isEmpty($("#City").val())){
			showTip("City","请选择城市");
          	return false;
		}
		$("#bindBankCardForm").submit();//进入第三方进行处理
		
		
		
         
	}
	
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
	
	
	
function check(){
	//第一检查是否登录
	checkUserIsLogin();
}


//检查用户是否登录
function checkUserIsLogin(){
	$.ajax({
        type: "POST",
        url: basepath+"checkUserIsLoginlogin.do",
        dataType: 'json',
        beforeSend:function(){
             $("#bidForm").css('display','none');
           $("#bidLoad").append('<img src="'+themepath+'images/loading.gif"/><span>账户检查中请等待...</span>');
        },
       success: function(responseText, statusText) {
                if (statusText == "success") {
                	//用户未登录
                	if(!responseText.success){
                		$("#bidLoad").empty();
                		 $("#bidMsg").append(responseText.msg);
                		  $("#msgWin").css('display','block');
                		  // 隐藏关闭按钮
                		  $("#lean_overlay_close").remove();
                		   $("#afterMoney").empty();
                		 $("#myMoney").empty();
                		  $("#afterMoney").append($("#hiddenMoney").val()+"元");
                		  $("#myMoney").append("登录后显示");
                	}else{
                		
                		 $("#bidLoad").empty();
                		$("#bidForm").css('display','block');
                		 $("#afterMoney").empty();
                		 $("#myMoney").empty();
                		  $("#afterMoney").append($("#hiddenMoney").val()+"元");
                		  $("#myMoney").append(responseText.myPmoney+"元"); // myPmoney 平台账户 myTmoney 总金额  myDmoney冻结金额
                	}
                }else{
                }
            },
         error:function(){
         	alert("出错了");
         }
    })
}
