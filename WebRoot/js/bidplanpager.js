$(function(){
	$(".cont-list .finlist_title li").click(function(){
		$(this).addClass("active").siblings().removeClass();
		$(".cont-list .finlist_hide ol").eq($(this).index()).addClass("rent").siblings().removeClass();
		$("#pager"+$(".cont-list .finlist_hide ol").eq($(this).index())[0].id).empty();
		addPage($(".cont-list .finlist_hide ol").eq($(this).index())[0].id);
	});  	
});
$(function(){
	$(".content .finlist_titles li").click(function(){
		$(this).addClass("active").siblings().removeClass();
		$(".content .finlist_hides dl").eq($(this).index()).addClass("rents").siblings().removeClass();
	});	
});
$(function(){
	$(".content .finlist_title0 li").click(function(){
		$(this).addClass("active").siblings().removeClass();
		$(".content .finlist_hide0 em").eq($(this).index()).addClass("rent1").siblings().removeClass();
	});	
});
function backnext(a,b,c,d,e,f){
	
	var html="";
	//var pns = $("#pns").text();
	var pns = $("#"+b).text();
	$.ajax({
		type : "POST",
		url : basepath + "/financePurchase/ajaxplanFinancePurchase.do",
		dataType : 'json',
		data:{pageNumber:pns,state:a},
		success : function(responseText, statusText) {
		//$("#pns").text(responseText.pageNumber);
			$("#"+b).text(responseText.pageNumber);
		var list = JSON.parse(responseText.result);
		//$(".ff").remove();
		$("."+c).remove();
		for(var i=0;i<list.length;i++){
		var date = new Date(list[i].publishSingeTime);
		
		var strdate = date.getFullYear();
			strdate += '-';
			strdate += date.getMonth()+1;
			strdate += '-';
			strdate += date.getDate()<10?'0'+date.getDate():date.getDate();
		html += '<tr class="'+c+'"><td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">'+
                  '<a href="../creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId='+list[i].bidId+' target="_blank" title="'+list[i].bidProName+'"><span class="tit">'+list[i].bidProName+'</span></a>'+
                  '<span class="small">利率'+list[i].interestRate+'%期限'+list[i].loanLife+'</span></td>'+
                  '<td align="center">'+strdate+'</td>'+
                  '<td align="center">'+list[i].afterMoneyTotal+'</td>'+
                  '<td align="center">'+list[i].loanLife+'</td>'+
                  '<td align="center">'+list[i].interestRate+'% </td>'+
                  '<td align="center" ><span style="display:inline-block;float:left;padding-left:20px;">'+list[i].notMoneyTotal+'</span>'+
                  '<span style="display:inline-block;float:right;"><a href="javascript:void(0);" onClick=clickFinancing('+list[i].bidId+',\''+list[i].investOrderNo+'\',\'Financing\') class="btn1">回款计划</a>'+
                  '<a href="../financePurchase/downLoadFinancePurchase.do?contractUrl='+list[i].url+'" class="btn2">合同</a><span class="last"></span></span></td></tr>';
		}
			//$("#suc").append(html);
		$("#"+d).append(html);
			//if($("#pns").text()=='1'){
			if($("#"+b).text()=='1'){
		   		//$("#pali").html('<span>上一页</span>');
				$("#"+e).html('<span>上一页</span>');
		   	}else{
		   		//$("#pali").html('<a href="javascript:void(0);" id="backup" onclick="backup()">上一页</a>');
		   		$("#"+e).html('<a href="javascript:void(0);" id="backup" onclick=backup(\''+a+'\',\''+b+'\',\''+c+'\',\''+d+'\',\''+e+'\',\''+f+'\')>上一页</a>');
		   	}
		   	var sumcount = responseText.count%10==0?responseText.count:Math.ceil(responseText.count/10);
		   	if(parseInt(sumcount-responseText.pageNumber)<=0){//控制最后一页把下一页失效
		   		//$("#padow").html('<span>下一页</span>');
		   		$("#"+f).html('<span>下一页</span>');
		   	}
		},
		error : function() {
			alert('出错了！！！');
		}
	});
}

function backup(a,b,c,d,e,f){
	var html="";
	//var pns = $("#pns").text();
	var pns = $("#"+b).text();
	pns = parseInt(pns)-2;
	$.ajax({
		type : "POST",
		url : basepath + "/financePurchase/ajaxplanFinancePurchase.do",
		dataType : 'json',
		data:{pageNumber:pns,state:a},
		success : function(responseText, statusText) {
		//$("#pns").text(responseText.pageNumber);
		$("#"+b).text(responseText.pageNumber);
		var list = JSON.parse(responseText.result);
		//$(".ff").remove();
		$("."+c).remove();
		for(var i=0;i<list.length;i++){
		var date = new Date(list[i].publishSingeTime);
		
		var strdate = date.getFullYear();
			strdate += '-';
			strdate += date.getMonth()+1;
			strdate += '-';
			strdate += date.getDate()<10?'0'+date.getDate():date.getDate();
		html += '<tr class="'+c+'"><td align="left" class="td-top"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">'+
                  '<a href="../creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId='+list[i].bidId+' target="_blank" title="'+list[i].bidProName+'"><span class="tit">'+list[i].bidProName+'</span></a>'+
                  '<span class="small">利率'+list[i].interestRate+'%期限'+list[i].loanLife+'</span></td>'+
                  '<td align="center">'+strdate+'</td>'+
                  '<td align="center">'+list[i].afterMoneyTotal+'</td>'+
                  '<td align="center">'+list[i].loanLife+'</td>'+
                  '<td align="center">'+list[i].interestRate+'% </td>'+
                  '<td align="center" ><span style="display:inline-block;float:left;padding-left:20px;">'+list[i].notMoneyTotal+'</span>'+
                  '<span style="display:inline-block;float:right;"><a href="javascript:void(0);" onClick=clickFinancing('+list[i].bidId+',\''+list[i].investOrderNo+'\',\'Financing\') class="btn1">回款计划</a>'+
                  '<a href="../financePurchase/downLoadFinancePurchase.do?contractUrl='+list[i].url+'" class="btn2">合同</a><span class="last"></span></span></td></tr>';
		}
			//$("#suc").append(html);
			$("#"+d).append(html);
			//if($("#pns").text()=='1'){
			if($("#"+b).text()=='1'){
		   		//$("#pali").html('<span>上一页</span>');
				$("#"+e).html('<span>上一页</span>');
		   	}else{
		   		//$("#pali").html('<a href="javascript:void(0);" id="backup" onclick="backup()">上一页</a>');
		   		$("#"+e).html('<a href="javascript:void(0);" id="backup" onclick=backup(\''+a+'\',\''+b+'\',\''+c+'\',\''+d+'\',\''+e+'\',\''+f+'\')>上一页</a>');
		   	}
		   	var sumcount = responseText.count%10==0?responseText.count:Math.ceil(responseText.count/10);
		   	if(parseInt(sumcount-responseText.pageNumber)==0){//控制最后一页把下一页失效
		   		//$("#padow").html('<span>下一页</span>');
		   		$("#"+f).html('<span>下一页</span>');
		   	}else{
		   		//$("#padow").html('<a href="javascript:void(0);" id="backnext" onclick="backnext()">下一页</a>');
		   		$("#"+f).html('<a href="javascript:void(0);" id="backnext" onclick=backnext(\''+a+'\',\''+b+'\',\''+c+'\',\''+d+'\',\''+e+'\',\''+f+'\')>下一页</a>');
		   	}
		},
		error : function() {
			alert('出错了！！！');
		}
	});
};