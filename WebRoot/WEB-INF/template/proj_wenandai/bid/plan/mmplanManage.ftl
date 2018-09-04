<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 理财管理</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
</head>
<body >
<!-- header --><!-- navbar -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<div class="main">
<input id="str" type="hidden" value="${str}">
<input id="mmSelectValue" type="hidden" value="${selectValue}">
<input id="mmShow" type="hidden" value="${show}">
<div class="user-cont">
	<!--start: 左侧页面-->
	 	<div class="user-name-nav fl">
		    <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
		</div>
		<input type="hidden" id="tagId" />
		<#--储存数据(个人账户总览的点击更多)-->
        <input type="hidden" id="type" value="${type}" />
    <!-- 左侧页面 end -->
    <!-- 右侧主体内容 start  右侧内容宽740px -->   	
	   <div  class="user-cont-fr fr">
	   		<div class="user-cont-right">
	   			<div class="head-num">
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-one fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">累计赚取收益(元)</p>
	   						<p class="middle"><em>0</em>元</p>
	   					</div>
	   				</div>
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-two fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">累计投资(笔)</p>
	   						<p class="middle"><em>0</em>笔</p>
	   					</div>
	   				</div>
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-three fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">累计投资金额(元)</p>
	   						<p class="middle"><em>0</em>元</p>
	   					</div>
	   				</div>
	   			</div>
	   			<h2 class="big-tit">理财管理</h2>	
				<div class="cont-list">
					<ul class="finlist_title">
				          <li id="buying" class="active">购买中</li>
				          <li id="holding">持有中</li>
				          <li >已完成</li>
				          <li ><#--<a href="${base}/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?state8=8&show=outList" class="<#if show=="outList">bg1<#else>bg2</#if>">已退出</a>-->已退出</li>
				          <li ><#--<a href="${base}/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?state_2=-2&state3=3&show=failureList" class="<#if show=="failureList">bg1<#else>bg2</#if>">已失败</a>-->已失败</li>    
				      </ul>
				       <div class="finlist_hide">
				         <ol  class="rent" id="1">
				           <div class="tab-list">
				              <div class="worm-tips fl">
				            	 <span class="icon1">购买中</span>
				              </div>
				            <div class="fl">
				            	 	<span>理财计划类型：</span> 
				            	 <span>
				            	 <select style="width:150px;height:28px;" id="selectValue1"> 
				        	 		<option value="">全部</option>
				        	 		{{if state == 0}}
				        	 			<option value="{{:manageMoneyTypeId}}">{{:name}}</option>
				        	 		{{/if}}
				            	 </select></span>
				            	 <input type="hidden" id="tab" value="${tab}" />
				            	 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="f-l m-t_5"><span class="black normal">加入日期：</span></label>
									<input type="text" id="startTime1" style="width:120px" name="bidtime" class="colorful" readonly="readonly"  onclick="new Calendar().show(this);"/><#--value="${startTime_buyingList}"-->
									<label class="f-l m-t_5"><span class="black normal">-</span></label>
									<input type="text" id="endTime1" style="width:120px" name="bidtime2" class="colorful" readonly="readonly" onclick="new Calendar().show(this);"/>
				            	 </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				            	<#--<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;"><a href="javascript:void(0);" onclick="searchData();">查&nbsp;询</a></span>-->  
				            	 <span class="buttonorange" style="font-size:14px; padding:4px 15px;"><a href="javascript:void(0);" style="color:#fff" onclick="searchData();">查&nbsp;询</a></span> 
				    	      <#--id="queryBuyingList" name="queryBtn"-->
				    	      </div>
				              <table width="100%"  class="tab_css_one clearfix" border="0" cellspacing="0" cellpadding="0" id="s1">
				                 <tr>
				                   <th align="center">计划名称   </th>
				                   <th align="center"> 加入金额  </th>
				                   <th align="center"> 预期收益率 </th>
				                   <th align="center"> 加入时间 </th>
				                   <th align="center"> 计划截止时间 </th>
				                   <th align="center"> 计划期限 </th>
				                   <th align="center"> 承诺收益 </th>
				                 </tr>
				               </table>
				               <#--分页区-->
				          	   <ul id="pager1" class="pager1"></ul> 
				          </div>
				       </ol>
				       <ol id='2'>
					   		<div class="tab-list">
					   	 		<div class="worm-tips fl">
					        	 	<span class="icon1">持有中</span>
					        	 </div>
					        	 <div class="fl">
					            	 	<span>理财计划类型：</span> 
					            	 	<span>
					            	 		<select style="width:150px;height:28px;" id="selectValue2"> 
							        	 		<option value="">全部</option>
							        	 		{{if state == 0}}
							        	 			<option value="{{:manageMoneyTypeId}}">{{:name}}</option>
							        	 		{{/if}}
					            	 		</select>
					            	 	</span>
					        	 	<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="f-l m-t_5"><span class="black normal">加入日期：</span></label>
										<input type="text" id="startTime2" style="width:120px" name="bidtime" class="colorful" readonly="readonly"  onclick="new Calendar().show(this);"/>
										<label class="f-l m-t_5"><span class="black normal">-</span></label>
										<input type="text" id="endTime2" style="width:120px" name="bidtime2" class="colorful" readonly="readonly"  onclick="new Calendar().show(this);"/>
					            	</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					            	<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="queryBtn" name="queryBtn" onclick="searchData();">查&nbsp;询</span> 
						   	 </div>
				             <table width="100%"  class="tab_css_one clearfix" border="0" cellspacing="0" cellpadding="0" id='s2'>
				                <tr>
				                  <th align="center">计划名称   </th>
				                  <th align="center"> 加入金额  </th>
				                  <th align="center"> 预期收益率 </th>
				                  <th align="center">承诺收益 </th>
				                  <!--<th align="center">当前已实现收益 </th>-->
				                  <th align="center">加入日期 </th>
				                  <th align="center">计划到期日 </th>
				                  <th align="center" width="20%">操作 </th>
				                </tr>
					        </table>
				          	 <#--分页区-->
				          	   <ul id="pager2" class="pager" style="margin: 20px auto;"></ul> 
					    </div>
				      </ol>
				      <ol id='10'>
				     	<div class="tab-list">
				      	<div class="worm-tips fl">
				            <span class="icon1">已完成</span>
				        </div>
				        <div class="fl">
				        <span>理财计划类型：</span> 
				            <span>
				        	 	<select style="width:150px;height:28px;" id="selectValue10"> 
				        	 		<option value="">全部</option>
				        	 		{{if state == 0}}
				        	 			<option value="{{:manageMoneyTypeId}}">{{:name}}</option>
				        	 		{{/if}}
				            	<#--<#list plManageMoneyType as list>
				            	 		<#if  list.state=0> 
				            	 	    <option value="${list.manageMoneyTypeId}">${list.name}</option>	
				            	 		</#if>
				            	 	</#list>-->
				            	 </select>
				            </span>
				            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="f-l m-t_5"><span class="black normal">加入日期：</span></label>
								<input type="text" id="startTime10" style="width:120px" name="bidtime" class="colorful" readonly="readonly" value="${startTime_successList}" onclick="new Calendar().show(this);"/>
								<label class="f-l m-t_5"><span class="black normal">-</span></label>
								<input type="text" id="endTime10" style="width:120px" name="bidtime2" class="colorful" readonly="readonly" value="${endTime_successList}" onclick="new Calendar().show(this);"/>
				        	</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        	<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="querySuccessList" name="queryBtn" onclick="searchData();">查&nbsp;询</span> 
				    	 </div>
				         <table width="100%"  class="tab_css_one clearfix" border="0" cellspacing="0" cellpadding="0" id="s10">
				            <tr>
				              <th align="center">计划名称   </th>
				              <th align="center"> 加入金额  </th>
				              <th align="center"> 预期收益率 </th>
				              <th align="center">承诺收益 </th>
				              <!--<th align="center">当前已实现收益 </th>-->
				              <!--<th align="center">退出日期</th>-->
				              <th align="center">计划到期日 </th>
				              <th align="center">操作 </th>
				            </tr>
				         </table>
				      	<ul id="pager10" class="pager1"></ul>
				       </div>
				     </ol>
					 <ol id='8'>
				     	<div class="tab-list">
					      	<div class="worm-tips fl">
					    	 	<span class="icon1">已退出</span>
					    	 </div>
					    	 <div class="fl">
					    	 	<span>理财计划类型：</span> 
					    	 	<span>
						            <select style="width:150px;height:28px;" id="selectValue8"> 
						            	<option value="">全部</option>
						        	 		{{if state == 0}}
						        	 			<option value="{{:manageMoneyTypeId}}">{{:name}}</option>
						        	 		{{/if}}
						             </select>
					             </span>
					             <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="f-l m-t_5"><span class="black normal">加入日期：</span></label>
									<input type="text" id="startTime8" style="width:120px" name="bidtime" class="colorful" readonly="readonly" value="${startTime_outList}" onclick="new Calendar().show(this);"/>
									<label class="f-l m-t_5"><span class="black normal">-</span></label>
									<input type="text" id="endTime8" style="width:120px" name="bidtime2" class="colorful" readonly="readonly" value="${endTime_outList}" onclick="new Calendar().show(this);"/>
					        	 </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        	 <span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="queryOutList" name="queryBtn" onclick="searchData();">查&nbsp;询</span> 
				    	  	</div>
				          <table width="100%"  class="tab_css_one clearfix" border="0" cellspacing="0" cellpadding="0" id="s8">
				             <tr>
				               <th align="center">计划名称   </th>
				               <th align="center"> 加入金额  </th>
				               <th align="center"> 预期收益率 </th>
				               <th align="center">承诺收益 </th>
				               <!--<th align="center">当前已实现收益 </th>-->
				               <th align="center">退出日期</th>
				               <th  align="center">计划到期日 </th>
				               <th  align="center"  >操作 </th>
				             </tr>
				           </table>
				           <ul id="pager8" class="pager1">
				        </div>
				     </ol>
				     <ol id='_2'>
				     	<div class="tab-list">
				      		<div class="worm-tips fl">
				            	<span class="icon1">已失败</span>
				            </div>
				            <div class="fl">
								<span>理财计划类型：</span> 
				            	<span>
				            	 	<select style="width:150px;height:28px;" id="selectValue_2"> 
				            	 		<option value="">全部</option>
						        	 		{{if state == 0}}
						        	 			<option value="{{:manageMoneyTypeId}}">{{:name}}</option>
						        	 		{{/if}}
				            	 	</select>
				            	 </span>
				            	 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="f-l m-t_5"><span class="black normal">加入日期：</span></label>
									<input type="text" id="startTime_2" style="width:120px" name="bidtime" class="colorful" readonly="readonly"  onclick="new Calendar().show(this);"/>
									<label class="f-l m-t_5"><span class="black normal">-</span></label>
									<input type="text" id="endTime_2" style="width:120px" name="bidtime2" class="colorful" readonly="readonly"  onclick="new Calendar().show(this);"/>
				            	 </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				            	 <span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="queryFailureList" name="queryFailureList" onclick="searchData();">查&nbsp;询</span> 
				    	     </div>
				             <table width="100%"  class="tab_css_one clearfix" border="0" cellspacing="0" cellpadding="0" id="s_2">
				                <tr>
				                  <th align="center">计划名称   </th>
				                  <th align="center"> 加入金额  </th>
				                  <th align="center"> 预期收益率 </th>
				                  <th align="center"> 加入时间 </th>
				                  <th align="center"> 计划截止时间 </th>
				                  <th align="center"> 计划期限 </th>
				                  <th align="center"> 承诺收益 </th>
				                </tr>
				              </table>
				         	<ul id="pager-2" class="pager1">
				          </div>
				       </ol>
     				</div>  
  				</div>
			</div>
		</div>
	</div>
</div>
</div>						
<!--end: Container -->
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<#--存放jsrender模板的容器-->
<#include "/WEB-INF/template/${systemConfig.theme}/temp_container.ftl">
</body>
</html>
<#--js文件放到页面底部-->
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript" src="${base}/js/user/mmplan.js"></script>
<script type="text/javascript" src="${base}/js/user/mmplanSelect.js"></script>
<script type="text/javascript" src="${base}/js/user/ummplanSelect.js"></script>

<#-- 引入jsrender相关的文件-->
<script type="text/javascript" src="${base}/js/page/jquery.pagination-1.2.3.js"></script>
<script type="text/javascript" src="${base}/js/jsrender/jsrender.min.js"></script>
<script type="text/javascript" src="${base}/js/common.js"></script>
<script type="text/javascript" src="${base}/js/mmPlanRender.js"></script>


<script type="text/javascript">var m1="个人中心",m2="我的理财",m3="我的投资";</script>	
<!--产品列表模版-->
<script id="page_tmp_type" type="text/x-jsrender">
		<option value="{{:manageMoneyTypeId}}">{{:name}}</option>
</script>

<script type="text/javascript">
	$(function(){debugger;
	    var tab = $("#tab").val();
	    if(tab!=null && tab!="undefined" && tab!=""){debugger;//从债权详情页返回
	    	$("#1").removeClass("rent");
		    	$("#2").addClass("rent");
		    	$("#buying").removeClass("active");
		    	$("#holding").addClass("active");
				addPage(2,'');
	    }else{
			if($("#type").val()!=null && $("#type").val()!='undefined' && $("#type").val()!=''){debugger
		    	alert("2");
		    	$("#1").removeClass("rent");
		    	$("#2").addClass("rent");
		    	$("#buying").removeClass("active");
		    	$("#holding").addClass("active");
				addPage(2,'');
		    }else{
		    	addPage(1,'');
		    }
	    }
		//获取理财计划的类型
		getManageData();//获取页面上的统计数据
		getMyType();
	});
	
	 function formatNum(num,n){debugger//参数说明：num 要格式化的数字 n 保留小数位
	    //num = String(num.toFixed(n));
	    var re = /(-?\d+)(\d{3})/;
	    while(re.test(num)) num = num.toString().replace(re,"$1,$2");
	    return num;
	}

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
	
	$.views.converters("isNull", function(v) {
	     if(v==null||v==''||v=='undefined'){
	    	return  0; 	
	     }else{
	     	return  v;
	     } 
	});

	$.views.converters("isurl", function(v) {  
		if(v==""||v==undefined){
			return "javascript:void(0)";
		}else{
			return "${base}/financePurchase/downLoadFinancePurchase.do?customerType=invest&contractUrl="+v;
		}
	});

	//查询方法
	function searchData(){debugger
		var Num = $("#tagId").val();//获取当前页签的id
		var startTime=$("#startTime"+Num).val();
		var endTime=$("#endTime"+Num).val();
		var selectVal=$("#selectValue"+Num).val();//查询理财计划的类型
		var template; 
		if(Num!=null && Num!='' && Num!='undefined'){
		    var str = "state"+Num;
			template = {start:'0',limit:'10',startTime:startTime,endTime:endTime,selectVal:selectVal};
			template[str] = 1;
		}
		addPage($("#tagId").val(),template);
	 }
		
	function addPage(params,template){debugger
		var url = "${base}/creditFlow/financingAgency/manageListShowPlManageMoneyPlanBuyinfo.do";
		var param={};
		if(params==30){
			url= "${base}/financePurchase/myExperienceFinancePurchase.do";
			param.planstate=params;
		}else {//购买中的理财计划
			if(template!=null&&template!='undefined'&&template!=''){
				param = template;
			}else{
				param={start:'0',limit:'10'};
				var str = "state"+params;
				param[str] = 1;
			}
		}
		
		$("#pager"+params).empty();
		
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
		           	$("#tagId").val(params);
		        }
		    }
		});
	}
  
   function getMyType(){
	   var new_map1={age:2,start:0,limit:10};
	   ajaxFunction("${base}/creditFlow/financingAgency/getMyFinanceTypePlManageMoneyPlanBuyinfo.do",new_map1,callback0_1);
   }

   //获取理财计划的类型
	function callback0_1(data){debugger;
	   	var result=data.result;
	   	//对象转数组
	    var array = $.map($(".cont-list .finlist_hide ol"), function(value, index) {
   			return [value];
		});
	    array.forEach(function(item){
	   		$("#selectValue"+item.id).empty().html($("#page_tmp7").render(result));
	   });
	}
	
	//查询理财计划类型
	function getManageData(){
	    var new_map0={age:2,start:0,limit:10};
	    ajaxFunction("${base}/creditFlow/financingAgency/myFinancePlManageMoneyPlanBuyinfo.do",new_map0,callback0_0);
	}
	
    //获取标的的统计数据
	function callback0_0(data){
	 	var result=data.result;
	 	$(".head-num").empty().html($("#temp").render({allBenefit:formatNum(result.allBenefit,2),allBidMoney:formatNum(result.allBidMoney,2),allBidTimes:result.allBidTimes}));//数据渲染
	}
</script>


