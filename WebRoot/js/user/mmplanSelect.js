$(function() {
/*	$("#queryBtn").click(function(){
		var active=$("#mmShow").val();
		var selectValue=$("#selectValue_"+active).val();
		var startTime=$("#startTime_"+active).val();
		var endTime=$("#endTime_"+active).val();
		location.href =basepath+"creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?selectValue="+selectValue+"&startTime="+startTime+"&endTime="+endTime+"&state2=2&state7=7&show=owningList";
	})*/
	$("#queryBuyingList").click(function(){debugger;
	addPage1(2);
	})
	$("#querySuccessList").click(function(){
		var active=$("#mmShow").val();
		var selectValue=$("#selectValue_"+active).val();
		var startTime=$("#startTime_"+active).val();
		var endTime=$("#endTime_"+active).val();
		location.href =basepath+"creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?selectValue="+selectValue+"&startTime="+startTime+"&endTime="+endTime+"&state10=10&show=successList";
	})
	$("#queryOutList").click(function(){
		var active=$("#mmShow").val();
		var selectValue=$("#selectValue_"+active).val();
		var startTime=$("#startTime_"+active).val();
		var endTime=$("#endTime_"+active).val();
		location.href =basepath+"creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?selectValue="+selectValue+"&startTime="+startTime+"&endTime="+endTime+"&state8=8&show=outList";
	})
	$("#queryFailureList").click(function(){
		var active=$("#mmShow").val();
		var selectValue=$("#selectValue_"+active).val();
		var startTime=$("#startTime_"+active).val();
		var endTime=$("#endTime_"+active).val();
		location.href =basepath+"creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?selectValue="+selectValue+"&startTime="+startTime+"&endTime="+endTime+"&state_2=-2&state3=3&show=failureList";
	})
	var active=$("#mmShow").val();
	var t1 = document.getElementById("selectValue_"+active);
	if(t1!=null){
		for(i=0;i<t1.length;i++){
	        if($("#mmSelectValue").val()==t1.options[i].value){  
	            t1.options[i].selected=true  
	        }  
	    }  
	}
});