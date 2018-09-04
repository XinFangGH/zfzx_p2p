
function clickPlan(bidId,type){
	var memberId=$("#memberId").val();
	if(memberId){
		if(checkSession()){
			var str = bidId+","+type;//拼接字符串
			var url = basepath+"user/loanRepayMentPlanBpCustMember.do?str="+str;
			$.layer({
	            type : 2,
	            title: '还款计划',
	            shadeClose: true,
	            shade : [0.5 , '#000' , true],
	            move : ['.xubox_title' , true],
	            zIndex : 19891014,
	            maxmin: true,
	            fix : false,  
	            area: ['1024px', 500],                     
	            iframe: {
	                src : url
	            } 
	        });	
	        document.body.style.overflow = "hidden";
		}
	}else{
		location.href=basepath+"htmllogin.do";
	}
}


function clickPlan1(bidId,type){
	var memberId=$("#memberId").val();
	if(memberId){
		if(checkSession()){
			var str = bidId+","+type+","+1;//拼接字符串
			var url = basepath+"user/loanRepayMentPlanBpCustMember.do?str="+str;
			$.layer({
	            type : 2,
	            title: '还款计划',
	            shadeClose: true,
	            shade : [0.5 , '#000' , true],
	            move : ['.xubox_title' , true],
	            zIndex : 19891014,
	            maxmin: true,
	            fix : false,  
	            area: ['1024px', 500],                     
	            iframe: {
	                src : url
	            } 
	        });	
	        document.body.style.overflow = "hidden";
		}
	}else{
		location.href=basepath+"htmllogin.do";
	}
}



function advanceLoan(planId){
 $('#advanceLoanBtn').removeAttr('onclick');
	var url = basepath+"user/advanceLoanBpCustMember.do?bidPlanId="+planId;
	if(checkSession()){
		$.layer({
	            type : 2,
	            title: '提前还款',
	            shadeClose: true,
	            shade : [0.5 , '#000' , true],
	            move : ['.xubox_title' , true],
	            zIndex : 19891014,
	            maxmin: true,
	            fix : false,  
	            area: ['900px', 500],                     
	            iframe: {
	                src : url
	            } 
	    });	
	    document.body.style.overflow = "hidden";
	}
}

function clickFinancing(bidId,orderNo,type){
	var str = bidId+","+type+","+orderNo;//拼接字符串
	var url = basepath+"user/paymentplanBpCustMember.do?str="+str;
//	if(checkSession()){
		$.layer({
	            type : 2,
	            title: '回款计划',
	            shadeClose: true,
	            shade : [0.5 , '#000' , true],
	            move : ['.xubox_title' , true],
	            zIndex : 19891014,
	            maxmin: true,
	            fix : false,  
	            area: ['1024px', 500],                     
	            iframe: {
	                src : url
	            } 
	    });	
    	document.body.style.overflow = "hidden";
	//}
}
/**
 * 还款请求
 */
function repayMentPay(){
	
	var href1  = document.getElementById("repayMentbtn").getAttribute('href',2);
	document.getElementById("repayMentbtn").setAttribute("href","javascript:return false;"); 
	location.href = href1;
	CommonPerson.Base.LoadingPic.FullScreenShow();
	//setTimeout("fullHide()", 10000);
		/*设置setTimeout是为了演示效果 
		在实际应用中可以在ajax请求前调用：CommonPerson.Base.LoadingPic.FullScreenShow(); 来全屏显示“数据正在加载中...”的loading图 
		当ajax请求成功返回数据，在把数据填充到页面之前调用：CommonPerson.Base.LoadingPic.FullScreenHide(); 来隐藏全屏loading图 
		*/
		/*$.ajax({
    		type:"POST",
    		dataType:"JSON",
    		async:false,
    		url:basepath+"pay/repayMentByLoanerPay.do",
    		 data: { 
		           	"planId":planId,
		           	"peridId":peridId,
		           	"notMoney":notMoney
		           },
    		cache:false,
    		success:function(responseText,statusText){
    			var resultMsg = responseText.success;
    			if(resultMsg == 0){
    				location.href =basepath+"/pay/mentMessagePay.do?resultCode=0000&resultMsg="+responseText.msg;
    			}else{
    				CommonPerson.Base.LoadingPic.FullScreenHide();
    			}
    		},error:function(responseText,statusText){
    			location.href =basepath+"/pay/mentMessagePay.do?resultCode=0000&resultMsg=请求错误";
    		}
		})*/
}
function fullHide() {
	CommonPerson.Base.LoadingPic.FullScreenHide();
}
function clickCompensatory(bidId,intentDate){
	if(checkSession()){
		var url = basepath+"creditFlow/financingAgency/compensatoryMoneyPlBidPlan.do?bidId="+bidId+"&intentDate="+intentDate;
		$.layer({
	            type : 2,
	            title: '担保代偿',
	            shadeClose: true,
	            shade : [0.5 , '#000' , true],
	            move : ['.xubox_title' , true],
	            zIndex : 19891014,
	            maxmin: true,
	            fix : false,  
	            area: ['1024px', 500],                     
	            iframe: {
	                src : url
	            } 
	    });	
	    document.body.style.overflow = "hidden";
	}
        
}
function clickOfflineRegist(cId){
	if(checkSession()){
		var url = basepath+"compensatory/getCompensatoryInfoPlBidCompensatory.do?cId="+cId;
		$.layer({
	            type : 2,
	            title: '线下追偿登记',
	            shadeClose: true,
	            shade : [0.5 , '#000' , true],
	            move : ['.xubox_title' , true],
	            zIndex : 19891014,
	            maxmin: true,
	            fix : false,  
	            area: ['1024px', 500],                     
	            iframe: {
	                src : url
	            } 
	    });	
	    document.body.style.overflow = "hidden";
	}
}
function clickFinancial(mmplanId,intentDate){
	if(checkSession()){
		var url = basepath+"creditFlow/financingAgency/financialMoneyPlManageMoneyPlanBuyinfo.do?mmplanId="+mmplanId+"&intentDate="+intentDate;
		$.layer({
	            type : 2,
	            title: '派息',
	            shadeClose: true,
	            shade : [0.5 , '#000' , true],
	            move : ['.xubox_title' , true],
	            zIndex : 19891014,
	            maxmin: true,
	            fix : false,  
	            area: ['680px', 280],                     
	            iframe: {
	                src : url
	            } 
	    });	
	    document.body.style.overflow = "hidden";
	}
        
}
function clickEarly(earlyRedemptionId){
	if(checkSession()){
		var url = basepath+"creditFlow/financingAgency/gcalculateEarlyOutPlManageMoneyPlanBuyinfo.do?earlyRedemptionId="+earlyRedemptionId;
		$.layer({
	            type : 2,
	            title: '提前退出结算清单',
	            shadeClose: true,
	            shade : [0.5 , '#000' , true],
	            move : ['.xubox_title' , true],
	            zIndex : 19891014,
	            maxmin: true,
	            fix : false,  
	            area: ['680px', 340],                     
	            iframe: {
	                src : url
	            } 
	    });	
	    document.body.style.overflow = "hidden";
	}
}
function trueSave(){
	var backPunishMoney = $("#backPunishMoney").val();
	var compensatoryId = $("#compensatoryId").val();
	var flateMoney = $("#flateMoney").val();
	var backDate = $("#backDate").val();
	var backCompensatoryMoney = $("#backCompensatoryMoney").val();
	var surplusMoney = $("#surplusMoney").val();
	
	if(backCompensatoryMoney==""){
		$("#pBackCompensatoryMoney").text("请输入代偿回款金额");
		$("#pBackCompensatoryMoney").show();
			return false;
	}else if(backPunishMoney.match(/[^\d\.]/g)){
		$("#pBackCompensatoryMoney").text("代偿回款金额必须为数字");
		$("#pBackCompensatoryMoney").show();
			return false;
	}
	if(backPunishMoney==""){
		$("#pBackPunishMoney").text("请输入罚息回款金额");
		$("#pBackPunishMoney").show();
			return false;
	}else if(backCompensatoryMoney.match(/[^\d\.]/g)){
		$("#pBackPunishMoney").text("罚息金额必须为数字");
		$("#pBackPunishMoney").show();
			return false;
	}
	if(flateMoney==""){
		$("#pFlateMoney").text("请输入平账金额");
		$("#pFlateMoney").show();
			return false;
	}else if(flateMoney.match(/[^\d\.]/g)){
		$("#pFlateMoney").text("平账金额必须为数字");
		$("#pFlateMoney").show();
			return false;
	}
	if(backDate==""){
	    $("#pBackDate").text("请选择追偿日期");
		$("#pBackDate").show();
			return false;
	}
	else if(Number(backCompensatoryMoney)+Number(backPunishMoney)+Number(flateMoney)>Number(surplusMoney)){
		$("#pBackCompensatoryMoney").text("X 还款金额不能大于剩余还款金额");
		$("#pBackCompensatoryMoney").show();
			return false;
	}else{
	location.href=basepath+"compensatory/saveCompensatoryInfoPlBidCompensatory.do?backPunishMoney="+backPunishMoney+"&compensatoryId="+compensatoryId+"&flateMoney="+flateMoney+
		"&backDate="+backDate+"&backCompensatoryMoney="+backCompensatoryMoney;
		
	}
}
    function queryOverdueList(){
		var oprojectName=$("#oprojectName").val();
		var overDays=$("#overDays").val();
		var ostartDate=$("#ostartDate").val();
		var oendDate=$("#oendDate").val();
		location.href =basepath+"/user/getBpCustMember.do?oprojectName="+oprojectName+"&overDays="+overDays+"&ostartDate="+ostartDate+"&oendDate="+oendDate;
    	}
    function queryCompensatoryList(){
		var cprojectName=$("#cprojectName").val();
		var cstartDate=$("#cstartDate").val();
		var cendDate=$("#cendDate").val();
		location.href =basepath+"/user/getBpCustMember.do?cprojectName="+cprojectName+"&cstartDate="+cstartDate+"&cendDate="+cendDate;
    	}
    function queryOnBidList(){
		var pprojectName=$("#pprojectName").val();
		location.href =basepath+"/user/getBpCustMember.do?pprojectName="+pprojectName;
    	}
    	
     function queryUnBidList(){
		var uprojectName=$("#uprojectName").val();
		location.href =basepath+"/user/getBpCustMember.do?uprojectName="+uprojectName;
    	}


//检查session是否超时
function checkSession(){
	var checkecode=true;
	$.ajax({
		async: true,
		type : "POST",
		dataType : "JSON",
		url : basepath + "/user/checkSessionBpCustMember.do",
		cache : false,
		success : function(responseText, statusText) {
			if(responseText.flag=="yes"){
				checkecode = true
			}else{
				checkecode = false
				location.href=basepath+"htmllogin.do";
			}
		},
		error : function(request) {
		}
	});
	return checkecode;
	
}
    	
