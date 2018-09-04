    $.views.converters("formartDate", function(val,str) {  
		return  formartDate(val,str);  
	});

	$.views.converters("sub", function(var0,var1) {  
		var val=Number(var0)-Number(var1);
		var Dight   =   Math.round   (val*Math.pow(10,2))/Math.pow(10,2); 
		return  Dight;  
	});
	
	$.views.converters("substring", function(v,l) {  
		return  subString(v,l);  
	});

	$.views.converters("isurl", function(v) {  
		if(v==""||v==undefined){
			return "javascript:void(0)";
		}else{
			return "${base}/financePurchase/downLoadFinancePurchase.do?customerType=invest&contractUrl="+v;
		}
	});


	function addPage(params){debugger;
		var url = "${base}/creditFlow/financingAgency/manageListShowPlManageMoneyPlanBuyinfo.do";
		var param={};
		if(params==30){
			url= "${base}/financePurchase/myExperienceFinancePurchase.do";
			param.planstate=params;
		}else if(params==2){//购买中的理财计划
			param={state1:'1',show:'buyingList',start:'0',limit:'10'};
		}else if(params==3){//持有中的理财计划
		    param = {state2:'3',show:'owningList',start:'0',limit:'10'};
		}else if(params==4){//已完成的理财计划
		    param = {state10:'10',show:'successList',start:'0',limit:'10'};
		}else if(params==5){//已退出的理财计划
			param = {state8:'8',show:'outList',start:'0',limit:'10'}
		}else if(params==6){//以失败的理财计划
			param = {state_2:'-2',show:'failureList',start:'0',limit:'10'}
		}else{
			url= "${base}/financePurchase/myManageMoneyFinancePurchase.do";
			param.planstate=params;
		}
		
		$("#pager"+params).page({
		    pageSize: 10,
		    remote: {
		        url:url,  //请求地址
		        params:param,       //自定义请求参数
		        callback: function (result) {
		            //回调函数
		            //result 为 请求返回的数据，呈现数据
		            var data=result.result;
		            //使用模板绑定数据
		            var tr=$("#s"+params).find('tr')[0];
		           	$("#s"+params).empty().append(tr).append($("#page_tmp"+params).render(data));
		        }
		    }
		});
	}

	function getManageData(){
	    var new_map0={age:2,start:0,limit:10};
	    ajaxFunction("${base}/creditFlow/financingAgency/myFinancePlManageMoneyPlanBuyinfo.do",new_map0,callback0_0);
	}
    //获取标的的统计数据
	 function callback0_0(data){
	 	var result=data.result;
	 	$(".head-num").empty().html($("#temp").render(result));//数据渲染
	 }
