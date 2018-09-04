$(function() {


	$("#applyEarlyOutBtn").click(function(){
				if (isEmpty($("#earlierOutDate").val())) {
					showTip("telphone", "提取支取日期不能为空");
					return false;
				}

		var earlierOutDate=$("#earlierOutDate").val();
		var orderId=$("#orderId").val();
		parent.window.location.href=basepath+"creditFlow/financingAgency/submitEarlyOutApplyPlManageMoneyPlanBuyinfo.do?orderId="+orderId
			+"&earlierOutDate="+earlierOutDate;
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	});
	


	
});


function clickorMatchingDetail(orderId){
	var url = basepath+"creditFlow/financingAgency/orMacthingDetaillistPlManageMoneyPlanBuyinfo.do?orderId="
	+orderId
	$.layer({
            type : 2,
            title: '匹配债权详情',
            shadeClose: true,
            shade : [0.5 , '#000' , true],
            move : ['.xubox_title' , true],
            zIndex : 19891014,
            maxmin: true,
            fix : false,  
            area: ['1024px', 600],                     
            iframe: {
                src : url
            } 
        });	
        document.body.style.overflow = "hidden";
        
}

//回款计划
function clickAssigninterest(orderId){
	var url = basepath+"creditFlow/financingAgency/assigninterestlistPlManageMoneyPlanBuyinfo.do?orderId="+orderId
	$.layer({
            type : 2,
            title: '回款计划',
            shadeClose: true,
            shade : [0.5 , '#000' , true],
            move : ['.xubox_title' , true],
            zIndex : 19891014,
            maxmin: true,
            fix : false,  
            area: ['1024px', 600],                     
            iframe: {
                src : url
            } 
        });	
        document.body.style.overflow = "hidden";
}

//债权清单
function clickClaimsList(orderId){
	parent.window.location.href=basepath+"creditFlow/financingAgency/getClaimsListPlManageMoneyPlanBuyinfo.do?orderId="+orderId
}

function clickearlyout(orderId,lockingEndDate){
	var ret=compartDate(lockingEndDate,getNow());
	if(lockingEndDate !=""&&ret=="1"){
		alert("还在锁定期，不能提前支取！");
	}else{
		var url = basepath+"creditFlow/financingAgency/earlyOutDetailPlManageMoneyPlanBuyinfo.do?orderId="+orderId
		$.layer({
            type : 2,
            title: '提前支取',
            shadeClose: false,//是否点击遮罩关闭
            shade : [0.5 , '#000' , true],//遮罩
            move : ['.xubox_title' , true],
            zIndex : 19891014,
            maxmin: true,
            fix : false,
            area: ['540px',500],                     
            iframe: {
                src : url
            } 
        });	
        document.body.style.overflow = "hidden";
	} 
}

function compartDate(date1,date2){
if(dateStr(date1).getTime() >  dateStr(date2).getTime()){
   return 1;
}
return 2;
}
dateStr= function(s){
return new Date(s.replace(/-/g, "/"))
}
function getNow (){
var now=new Date();
y=now.getFullYear();
m=now.getMonth()+1;
d=now.getDate();
/*m=m<10?"0"+m:m
d=d<10?"0"+d:d*/
return y+"-"+m+"-"+d
}