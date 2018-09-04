function commaFormatted(amount) {
			var delimiter = ","; // replace comma if desired
			amount = new String(amount);
			amount = (amount.indexOf(".", 0)==0)?("0"+amount):amount;
			var a = amount.split('.',2);
			//alert(a.length);
			 var d = a.length > 1 ?  "."+a[1] : '';
			var i = parseInt(a[0]);
			if(isNaN(i)) { return ''; }
			var minus = '';
			if(i < 0) { minus = '-'; }
			i = Math.abs(i);
			var n = new String(i);
			var a = [];
			while(n.length > 3)
			{
			    var nn = n.substr(n.length-3);
			    a.unshift(nn);
			    n = n.substr(0,n.length-3);
			}
			if(n.length > 0) { a.unshift(n); }
				n = a.join(delimiter);
			amount = n + d;
			amount = minus + amount;
			return amount;
		}
	
		function formatMoney(str)
		{
			return commaFormatted(str);
		}
		
		
	
        function recharge_radio(){
             var chkObjs = document.getElementsByName("recharge");
             for(var i=0;i<chkObjs.length;i++)
             {
                 if(chkObjs[i].checked)
                 {
                     if($("#recharge_status").val() == chkObjs[i].value)
                     {
                         return;
                     }
                     else
                     {
                     	$("#recharge_status").val(chkObjs[i].value);
                     }
                 }
             }
        }
        function withdraw_radio(){
             var chkObjs = document.getElementsByName("withdraw");
             for(var i=0;i<chkObjs.length;i++)
             {
                 if(chkObjs[i].checked)
                 {
                     if($("#withdraw_status").val()==chkObjs[i].value)
                     {
                         return;
                     }
                     else
                     {
                     	$("#withdraw_status").val(chkObjs[i].value);
                     }
                 }
             }
        }
		function query(submitForm,type,redio){	
			if(""==type||undefined==type)
			{
				type = $("#fund_type").val();
			}
			$("#fund_type").val(type);
			var log_time_start = $("#from").val();
			var log_time_end = $("#to").val();
			var financ_name = $("#financ_name").val();
			if(""!=log_time_start&&""!=log_time_end&&log_time_start>log_time_end)
			{
 				msgAlert("信息提示","交易开始时间不能大于结束时间","warning",null);
    			return;
			}
			if(undefined!=redio && "rec"==redio)
			{
				recharge_radio();
				$("#currPage").val("");
				$("#minShowFlag").val("");
				$("#maxShowFlag").val("");
			}
			if(undefined!=redio && "draw"==redio)
			{
				withdraw_radio();
				$("#currPage").val("");
				$("#minShowFlag").val("");
				$("#maxShowFlag").val("");
			}
 			//document.getElementById('queryForm').submit();
    		$("#"+submitForm).ajaxSubmit({
				beforeSubmit:showStart,
				clearForm:false,
				dataType:"JSON",
				async : false,
				success:function(responseText, statusText){
					if(type=='fund')
					{
						var table="";
						$.each(responseText.content,function(i,n){
							table +="<tr>";
 							table += "<td>"+responseText.content[i].log_date+"</td>";
							table += "<td>"+getTradeType(responseText.content[i].account_from)+"</td>";
 							table += "<td>"+getFontFlag(responseText.content[i].account_from)
 								+formatMoney(
 									new Number(responseText.content[i].account_money)
 									+new Number(responseText.content[i].account_charge_money)
 								)
 								+"</td>";
							table += "<td>"+responseText.content[i].modify_desc+"</td>";
 							table +="</tr>";
						});
						$(".funds").find('table').find('tr').siblings().has('td').remove();
						$(".funds").find('table').append(table);
						$("#fund_pager").html('').append(responseText.pager);
					}
					else if(type=='recharge')
					{
						var table="";
						$.each(responseText.content,function(i,n){
							table +="<tr>";
							table += "<td>"+responseText.content[i].provider_id+"</td>";
							table += "<td>"+formatMoney(responseText.content[i].recharge_money)+"</td>";
							table += "<td>"+responseText.content[i].recharge_date+"</td>";
							table += "<td>"+getRechargeStatus(responseText.content[i].recharge_status)+"</td>";
							table +="</tr>";
						});
						$(".recharge").find('table').find('tr').siblings().has('td').remove();
						$(".recharge").find('table').append(table);
						$("#recharge_pager").html('').append(responseText.pager);
					}
					else if(type=='withdraw')
					{
						var table="";
						$.each(responseText.content,function(i,n){
							table +="<tr>";
							table += "<td>"+responseText.content[i].user_recharge_account+"</td>";
							table += "<td>"+formatMoney(responseText.content[i].withdraw_money)+"</td>";
 							table += "<td>"+formatMoney(responseText.content[i].withdraw_charge_money)+"</td>";
							table += "<td>"+responseText.content[i].withdraw_date+"</td>";
							table += "<td>"+getWithdrawStatus(responseText.content[i].withdraw_status)+"</td>";
							table +="</tr>";
						});
						$(".withdraw").find('table').find('tr').siblings().has('td').remove();
						$(".withdraw").find('table').append(table);
						$("#withdraw_pager").html('').append(responseText.pager);
					}
				}
			});
		}
 
		function getTradeType(tradeId){
			var tradeTyepStr="";
			if(tradeId=="01"){
				tradeTyepStr='资金充值';
			}
			if(tradeId=="02"){
				tradeTyepStr='投资申购';
			}
			if(tradeId=="03"){
				tradeTyepStr='年费扣除';
			}
			if(tradeId=="04"){
				tradeTyepStr='收益返还';
			}
			if(tradeId=="05"){
				tradeTyepStr='冻结资金';
			}
			if(tradeId=="06"){
				tradeTyepStr='解冻资金';
			}
			if(tradeId=="07"){
				tradeTyepStr='资金提现';
			}
			if(tradeId=="08"){
				tradeTyepStr='融资资金';
			}
			if(tradeId=="09"){
				tradeTyepStr='融资提成';
			}
			if(tradeId=="10"){
				tradeTyepStr='融资利息';
			}
			if(tradeId=="11"){
				tradeTyepStr='逾期还款';
			}
			if(tradeId=="12"){
				tradeTyepStr='融资本金';
			}
  		  return tradeTyepStr;
    	}
    	
    	function getFontFlag(tradeId){
    		var fontStr="";
    		if(tradeId  =='01'||tradeId == '04'||tradeId == '06'||tradeId == '08'){
    		 	fontStr="<font color='red' size='2'>+</font>";
    		}
    		if(tradeId  =='02'||tradeId == '03'||tradeId == '05'||tradeId == '07'||tradeId == '09'||tradeId == '10'||tradeId == '11'||tradeId == '12'){
    		 	fontStr="<font color='red'  size='4'>-</font>";
    		}
    		return fontStr;
    	}
 
 
 		function getRechargeStatus(status){
    		var statusStr="";
    		if(status  =='Y'){
    		 	statusStr="充值成功";
    		}
    		if(status  =='N'){
    		 	statusStr="充值失败";
    		}
    		if(status  =='U'){
    		 	statusStr="原因不明";
    		}
    		return statusStr;
    	}
    	
   		function getWithdrawStatus(status){
    		var statusStr="";
    		if(status  =='0'){
    		 	statusStr="未处理提现";
    		}
    		if(status  =='1'){
    		 	statusStr="已处理提现";
    		}
    		if(status  =='-1'){
    		 	statusStr="无法转到银行卡";
    		}
    	     if(status  =='2'){
    		 	statusStr="已转到银行卡";
    		}
    		if(status  =='-2'){
    		 	statusStr="已经退单";
    		}
    		if(status  =='3'){
    		 	statusStr="提款ID不存在";
    		}
    		if(status  =='4'){
    		 	statusStr="银行结算";
    		}
    		return statusStr;
    	}
    	
    	function queryForm(){
     		var tab=$('.controller').find('li.current').index();
			$(this).addClass('current').siblings('li').removeClass('current'); 
 			if(0==tab)
			{
				$("#tradeId").val("front_MyFinanceDetail");
				query('queryForm','fund');	
				$("#fund_type").val("fund");
			}
			else if(1==tab)
			{
				$("#tradeId").val("front_MyRechargeDetail");
				query('queryForm','recharge');	
				$("#fund_type").val("recharge");
			}
			else if(2==tab)
			{
				$("#tradeId").val("front_MyWithdrawDetail");
 				query('queryForm','withdraw');	
				$("#fund_type").val("withdraw");
			}
    	}
    	
      	function queryFinanceDetailList(){
		var transferType=$("#transferType").val();
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();
		var isAccountSuccess=$("#isAccountSuccess").val();
		location.href =basepath+"/financePurchase/detailFinancePurchase.do?transferType="+transferType+"&startDate="+startDate+"&endDate="+endDate+"&isAccountSuccess="+isAccountSuccess;
    	}
