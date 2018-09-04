
//每月还本金及利息
function interMoney(rate){/*
		var money=$("#loanMoney").val(); //借款本金
		var loanTimeLen=''//还款月数
		if(rate=='1'){
			loanTimeLen=$("#selectid").val();
		}else{
			loanTimeLen=rate;
		}
	if(money.match(/^[0-9]*$/) && loanTimeLen!=''){
		var yearRate='';
		$("input[name='loan_Radio']").each(function(){
			if(loanTimeLen==$(this).val()){
				var loanText = $(this).next().text();
				var str = loanText.split(",");
			}
		})
		var monthRate=str[0]*0.01/12;//月利率
		var comNum=Math.pow((1+monthRate),loanTimeLen);//(1+月化利率)^月数
		var fenmu=monthRate*comNum;
		var fenzi=comNum-1;
		var totalMoney=fenmu/fenzi*money+"";
		var point=totalMoney.indexOf('.');
		var newMoney;
		if(point==-1){
			newMoney=totalMoney;
		}else{
			newMoney=totalMoney.substr(0,point+3);
		}
			if(str.length>0){
		   	 	$("#autoRate").html(str[0]+"%");
			}
			if(str.length>1){
		   	 	$("#moneyManger").html(str[1]+"%");
			}
			if(str.length>2){
		   	 	$("#yearFinanceServiceOfRate").html(str[2]+"%");
			}
	}
*/}
//每月交借款管理费
function manageMoney(){
	var loanStartMoney = $("#loanStartMoney").val();
	var loanEndMoney = $("#loanEndMoney").val();
	var wad_dkname = $("#wad_dkname").text();
	var money=$("#loanMoney");
		if(!money.val().match(/^[0-9]*$/)){
			showTip("loanMoney", "借款金额必须有数字组成");
			return false;
		}else if(parseInt(money.val())<parseInt(loanStartMoney)){
			showTip("loanMoney", "借款金额为:"+loanStartMoney+"至"+loanEndMoney+"元");
			return false;
		}else if(parseInt(money.val())>parseInt(loanEndMoney)){
			showTip("loanMoney", "借款金额为:"+loanStartMoney+"至"+loanEndMoney+"元");
			return false;
		}else{
			serviceFree();//期初服务费
			var manageMon=money.val()*0.003+"";
			var point=manageMon.indexOf('.');
			var newManageMon;
			if(point==-1){
				newManageMon=manageMon;
			}else{
				newManageMon=manageMon.substr(0,point+3);
			}
			$("#monthlyCharge").val(newManageMon);
		}
	}
//期初服务费
function serviceFree(){
	var money=$("#loanMoney").val();
	$("#serCharge1").html(0);
	$("#serCharge2").html(checkPoint(money*0.01));
	$("#serCharge3").html(checkPoint(money*0.015));
	$("#serCharge4").html(checkPoint(money*0.02));
	$("#serCharge5").html(checkPoint(money*0.025));
	$("#serCharge6").html(checkPoint(money*0.03));
	$("#serCharge7").html(checkPoint(money*0.05));
}
function checkPoint(tm){
		var totalMoney=tm+"";
		var point=totalMoney.indexOf('.');
		var newMoney;
		if(point=='-1'){
			newMoney=totalMoney;
		}else{
			newMoney=totalMoney.substr(0,point+3);
		}
		return newMoney;
}
function saveApply(){
	var loanStartMoney = $("#loanStartMoney").val();
	var loanEndMoney = $("#loanEndMoney").val();
	var loanTitle=$("#loanTitle");
		if(loanTitle.val()==''){
			loanTitle.focus();
			showTip("loanTitle", "借款标题不能为空");
			return false;
		}
		if($('#seleUser').val()==''){
			alert("请选择借款用途");
			return false;
		}
		var loanMoney=$('#loanMoney');
		if(loanMoney.val()==''){
			loanMoney.focus();
			showTip("loanMoney", "借款金额不能为空");
			return false;
		}else if(parseInt(loanMoney.val())<parseInt(loanStartMoney)){
			loanMoney.focus();
			showTip("loanMoney", "借款金额为:"+loanStartMoney+"至"+loanEndMoney+"元");
			return false;
		}else if(parseInt(loanMoney.val())>parseInt(loanEndMoney)){
			loanMoney.focus();
			showTip("loanMoney", "借款金额为:"+loanStartMoney+"至"+loanEndMoney+"元");
			return false;
		}else if(!loanMoney.val().match(/^[0-9]*$/)){
			loanMoney.focus();
			showTip("loanMoney", "借款金额必须有数字组成哦");
			return false;
		}
		if($('#selectid').val()==''){
			alert("请选择借款期限");
			return false;
		}
		var remark=$('#remark');
		if(remark.val()==''){
			remark.focus();
			showTip("remark", "借款描述不能为空");
			return false;
		}
		var chk = document.getElementById('readAgreement');
		if(!chk.checked){
			showTip("readAgreement", "您未选中升升投的服务协议");
		    return false;
		}
	}
	//每月还本金及利息
function setMoney(){
	var loanTime = $("#my_loanTime").text();
	var money=$("#loanMoney").val(); //借款本金
	var fundType=$("#payIntersetWay").val();
	if(money.match(/^[0-9]*$/)&&loanTime!=""){
		$("input[name='loan_Radio']").each(function(){
		if(loanTime==$(this).val()){
			var loanText = $(this).next().text();
			var str = loanText.split(",");
			if(str.length>0){
		   	 	$("#autoRate").html(str[0]+"%");
			}
			if(str.length>1){
		   	 	$("#moneyManger").html(str[1]+"%");
			}
			if(str.length>2){
		   	 	$("#yearFinanceServiceOfRate").html(str[2]+"%");
			}
			var monthRate=str[0]*0.01/12;//月利率
			sumEvarMonthIntent(money,monthRate,fundType,loanTime,"#moneyAndInt");
	   	 	return false;
		}
   	 })
	}else{ 
	}

}	
//每月还本金及利息
function loanRadio(loanTime){
	$("#my_loanTime").text(loanTime);
	var money=$("#loanMoney").val(); //借款本金
	var fundType=$("#payIntersetWay").val();
	$("input[name='loan_Radio']").each(function(){
		if(loanTime==$(this).val()){
			var loanText = $(this).next().text();
			var str = loanText.split(",");
			if(str.length>0){
		   	 	$("#autoRate").html(str[0]+"%");
			}
			if(str.length>1){
		   	 	$("#moneyManger").html(str[1]+"%");
			}
			if(str.length>2){
		   	 	$("#yearFinanceServiceOfRate").html(str[2]+"%");
			}
			var monthRate=str[0]*0.01/12;//月利率	
			sumEvarMonthIntent(money,monthRate,fundType,loanTime,"#moneyAndInt");
	   	 	return false;
		}
   	 })
}
/*用来计算不同款项类型下的每月应还本金和利息
*/
function sumEvarMonthIntent(money,mothRate,fundtype,loanTime,htmlId){
     if(fundtype=="sameprincipalandInterest"){//等额本息
     	var t1=Math.pow(1+mothRate,loanTime);
     	var totalMoney=mothRate*money*((t1)/(t1-1));
     	$(htmlId).html(totalMoney.toFixed(2));
     }else if(fundtype=="sameprincipal"){//等额本金                                                                                                                                                                         
        var totalMoney=mothRate*money+(money/6);
     	$(htmlId).html(totalMoney.toFixed(2)+"(第一个月,随后逐月递减)");
     }else if(fundtype=="sameprincipalsameInterest"){//等本等息
     	  var totalMoney=mothRate*money+(money/6);
     	$(htmlId).html(totalMoney.toFixed(2)+"(第一个月,随后逐月递减)");  
     }else if(fundtype=="singleInterest"){//按期付息到期还本
			var totalMoney=mothRate*money;
		    $(htmlId).html(totalMoney.toFixed(2));
     }else {
     	$(htmlId).html("计算模型不明确,无法计算");
     }
}