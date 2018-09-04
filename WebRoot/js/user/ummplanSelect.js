$(function() {  //该js没有用了，之前个人中心理财计划分为普通计划和U计划上下两个表格，现在合在一起了。
	//购买中
	$("#queryUBuyingList").click(function(){
		var active=$("#ummShow").val();
		var selectValue=$("#selectValue_"+active).val();
		var startTime="undefined"==$("#startTime_"+active).val()?"":$("#startTime_"+active).val();
		var endTime="undefined"==$("#endTime_"+active).val()?"":$("#endTime_"+active).val();
		location.href =basepath+"creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?selectValue="+selectValue+"&startTime="+startTime+"&endTime="+endTime+"&state1=1&ushow=ubuyingList&keystr=UPlan";
	})
	//持有中
	$("#queryUOurList").click(function(){
		var active=$("#ummShow").val();
		var selectValue=$("#selectValue_"+active).val();
		var startTime="undefined"==$("#startTime_"+active).val()?"":$("#startTime_"+active).val();
		var endTime="undefined"==$("#endTime_"+active).val()?"":$("#endTime_"+active).val();
		location.href =basepath+"creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?selectValue="+selectValue+"&startTime="+startTime+"&endTime="+endTime+"&state2=2&state7=7&ushow=uowningList&keystr=UPlan";
	})
	//已退出
	$("#queryUOutList").click(function(){
		var active=$("#ummShow").val();
		var selectValue=$("#selectValue_"+active).val();
		var startTime="undefined"==$("#startTime_"+active).val()?"":$("#startTime_"+active).val();
		var endTime="undefined"==$("#endTime_"+active).val()?"":$("#endTime_"+active).val();
		location.href =basepath+"creditFlow/financingAgency/uPlanManagelistPlManageMoneyPlanBuyinfo.do?selectValue="+selectValue+"&startTime="+startTime+"&endTime="+endTime+"&state8=8&ushow=uoutList&keystr=UPlan";
	})
	//已失败
	$("#queryUFailureList").click(function(){
		var active=$("#ummShow").val();
		var selectValue=$("#selectValue_"+active).val();
		var startTime="undefined"==$("#startTime_"+active).val()?"":$("#startTime_"+active).val();
		var endTime="undefined"==$("#endTime_"+active).val()?"":$("#endTime_"+active).val();
		location.href =basepath+"creditFlow/financingAgency/uPlanManagelistPlManageMoneyPlanBuyinfo.do?selectValue="+selectValue+"&startTime="+startTime+"&endTime="+endTime+"&state_2=-2&state3=3&ushow=ufailureList&keystr=UPlan";
	})
	var active=$("#ummShow").val();
	var t1 = document.getElementById("selectValue_"+active);
	if(t1!=null){
		for(i=0;i<t1.length;i++){
	        if($("#ummSelectValue").val()==t1.options[i].value){  
	            t1.options[i].selected=true  
	        }  
	    }  
	}
});