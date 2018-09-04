$(function() {
	$("#queryFinReturnList").click(function(){
		var active=$("#mmfShow").val();
		var mmName=$("#mmName_"+active).val();
		location.href =basepath+"creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?mmName="+mmName+"&state=7&show=returnList";
	})
		$("#queryFinFullList").click(function(){
		var active=$("#mmfShow").val();
		var mmName=$("#mmName_"+active).val();
		location.href =basepath+"creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?mmName="+mmName+"&state=2&show=fullList";
	})
		$("#queryFinBidingList").click(function(){
		var active=$("#mmfShow").val();
		var mmName=$("#mmName_"+active).val();
		location.href =basepath+"creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?mmName="+mmName+"&state=1&show=bidingList";
	})
		$("#queryFinPresaleList").click(function(){
		var active=$("#mmfShow").val();
		var mmName=$("#mmName_"+active).val();
		location.href =basepath+"creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?mmName="+mmName+"&state=1&isPresale=ysz&show=presaleList";
	})
		$("#queryFinSuccessList").click(function(){
		var active=$("#mmfShow").val();
		var mmName=$("#mmName_"+active).val();
		location.href =basepath+"creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?mmName="+mmName+"&state=10&show=successList";
	})
		$("#queryFinEarlyList").click(function(){
		var active=$("#mmfShow").val();
		var mmName=$("#mmName_"+active).val();
		var checkStatus=$("#checkStatus").val();
		location.href =basepath+"creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?mmName="+mmName+"&show=earlyList&checkStatus="+checkStatus;
	})
/*	var active=$("#mmfShow").val();
	var t1 = document.getElementById("selectValue_"+active);
	if(t1!=null){
		for(i=0;i<t1.length;i++){
	        if($("#mmSelectValue").val()==t1.options[i].value){  
	            t1.options[i].selected=true  
	        }  
	    }  
	}*/
    
    
});