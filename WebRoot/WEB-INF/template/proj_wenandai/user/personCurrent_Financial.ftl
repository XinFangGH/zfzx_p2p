<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 活期理财</title>
    <meta name="description" content="${systemConfig.metaTitle} - 活期理财,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 活期理财,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<!--引入highchart主题           <script src="${base}/js/highchart/grid.js" type="text/javascript"></script> -->
<script type="text/javascript">var m1="个人中心", m2="活期理财", m3="活期理财";</script>
<style type="text/css">

	.span8 {
		width: 770px;
		margin-bottom: 20px;
		display: block;
		overflow: hidden;
	}
	
	[class*="span"] {
		float: left;
		margin-left: 10px;
	}
	
	.next_tab_content {
		margin-bottom: 20px;
		display: block;
		overflow: hidden;
		width: 770px;
	}
	
	.instant_recharge_one {
		border: 1px solid #ededed;
		background: #fff;
		margin-bottom: 10px;
		margin-top: 15px;
	}
	
	.instant_recharge_one div{
		background: #f8f8f8;
		font-size: 15px;
		color: #333;
		padding: 7px 7px 7px 15px;
	}
	
	
	.instant_recharge_one table {
		width: 597px;
		margin: 0 auto;
		margin-top: 30px;
		margin-bottom: 30px;
		max-width: 100%;
		border-collapse: collapse;
		border-spacing: 0;
		background-color: transparent;
		border-spacing: 2px;
		border-color: gray;
	}
	tr {
		display: table-row;
		vertical-align: inherit;
		border-color: inherit;
	}
	.instant_recharge_one table td {
		height: 30px;
	}
	
	tbody {
		display: table-row-group;
		vertical-align: middle;
		border-color: inherit;
	}
	
	
	.instant_recharge_one .huilv {
		color: #666;
		font-size: 12px;
		margin: 0 0 0 45px;
	}
	i {
		font-style: normal;
	}
	
	.instant_recharge_one table {
		width: 597px;
		margin: 0 auto;
		margin-top: 30px;
		margin-bottom: 30px;
	}
	
	tr {
		display: table-row;
		vertical-align: inherit;
		border-color: inherit;
	}

	.instant_recharge_one table span {
		width: 218px;
		height: 86px;
		display: block;
		background: url(../theme/${systemConfig.theme}/images/zhifu.jpg) no-repeat;
		border: 1px solid #CCC;
		-moz-border-radius: 6px;
		border-radius: 6px;
	}


	.instant_recharge_two {
		border: 1px solid #ededed;
		background: #fff;
		margin-bottom: 10px;
		margin-top: 15px;
	}
	
	.instant_recharge_two ._div {
		background: #f8f8f8;
		font-size: 15px;
		color: #333;
		padding: 7px 7px 7px 15px;
	}
	
	.instant_recharge_two table {
		width: 507px;
		margin: 0 auto;
		margin-bottom: 10px;
		margin-top: 10px;
	}
	
	
	
	.instant_recharge_three {
		border: 1px solid #ededed;
		background: #fff;
		margin-bottom: 10px;
		margin-top: 15px;
	}
	.instant_recharge_three li {
		line-height: 30px;
		padding-left: 25px;
	}

	.mask-layer {
		position: absolute;
		top: 0;
		background-color: #000;
		opacity: 0.5;
		filter: alpha(opacity=50);
	}
	
	
	strong, b {
font-weight: bold;
}
	
</style>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<div class="main">
<input id="str" type="hidden" value="${str}">
<#--储存数据(个人账户总览的点击更多)-->
<input type="hidden" id="type" value="${type}" />
<div class="user-cont">
	<!--start: 左侧页面-->
	 	<div class="user-name-nav fl">
			<#--<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">-->
		    <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
		</div>
    <!-- 左侧页面 end -->
    <!-- 右侧主体内容 start  右侧内容宽740px -->   	
	   <div  class="user-cont-fr fr">
	   		<div class="user-cont-right">
			<!--升升投宝-->
			<div class="head-num">
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-four fl"></div>
	   					<div class="num-data">
	   						<p class="normal">昨日收益(元)</p>
	   						<p class="middle"><em>0</em>元</p>
							<p class="normal">账户余额(元)<span>0</span><i class="eyes"></i></p>
							
	   					</div>
	   				</div>
	   				<div class="num-icon fl">
	   					<div class="num-data fl">
	   						<div class="datd-list">
	   							<span class="w12"><em class="user-icon icon1"></em>累计收益(元)</span>
	   							<span class="f16">0</span>
	   						</div>
	   						<div class="datd-list">
		   						<span class="w12"><em class="user-icon icon2"></em>尚未计息金额(元)</span>
	   							<span class="f16">0</span>
	   						</div>
	   					</div>
	   				</div>
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-two fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">近一月赚取(元)</p>
	   						<p class="middle"><em>0</em>元</p>
	   						<p class="btn">
	   							<a href="${base}/financeProduct/buyProductPlFinanceProduct.do" class="btn_bg1" style="color:red;">转入</a>
								<a href="${base}/financeProduct/persontransferFromProductPlFinanceProduct.do" class="btn_bg1"  style="color:red;">转出</a>
	   						</p>
	   					</div>
	   				</div>
	   			</div>
				<div class="hurong-list">
					<div border="1" id="container" style="width: 100%; height:300px"></div>
				</div>
				 <div class="cont-list" style="margin-top:25px;">
				       <ul class="finlist_title">
				          <li class="active" >收益</li>
				          <li >转入</li>
				          <li >转出</li>
				       </ul>
				        <div class="finlist_hide">
				        	<ol  class="rent" id="0">
				           		<div class="tab-list">
				           			<p  style="padding:10px 30px 10px 0; float:right;">
										日期：
										<input type="text" id="startTime_dealDate0" style="width:120px"  class="colorful" readonly="readonly" onclick="new Calendar().show(this);"/>
										<label class="f-l m-t_5"><span class="black normal">-</span></label>
										<input type="text" id="endTime_dealDate0" style="width:120px"  class="colorful" readonly="readonly"  onclick="new Calendar().show(this);"/>
										<#--<span  style="font-size:14px; padding:4px 15px;color:#fd7754;" id="selectUseraccountinfo" name="selectUseraccountinfo" onclick="searchByDate();">查&nbsp;询</span>--> 
										<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;color:#fff;" id="queryBtn" name="queryBtn" onclick="searchByDate(0);">查&nbsp;询</span>
									</p>
									<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s0">
										 <tr>
										 	<th align="center">日期</th>
										 	<th align="center">金额</th>
										 	<th align="center">类型</th>
										 </tr>
										  <#--<#list pager.list as list>
					                    	<tr>
											 	<td align="center"></td>
											 	<td align="center"></td>
											 	<td align="center"></td>				 	
										 	</tr>
					                    </#list>-->
									</table>
									<ul id="pager0" class="pager"></ul>
				           		</div>
				           	</ol>
				           	<ol id="1">
				           		<div class="tab-list">
				           			<p style="padding:10px 30px 10px 0; float:right;">
										日期：
										<input type="text" id="startTime_dealDate1" style="width:120px"  class="colorful" readonly="readonly" onclick="new Calendar().show(this);"/>
										<label class="f-l m-t_5"><span class="black normal">-</span></label>
										<input type="text" id="endTime_dealDate1" style="width:120px"  class="colorful" readonly="readonly"  onclick="new Calendar().show(this);"/>
										<#--<span style="font-size:14px; padding:4px 15px;color:#fd7754;" id="selectUseraccountinfo" name="selectUseraccountinfo">查&nbsp;询</span> -->
										<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;color:#fff;" id="queryBtn1" name="queryBtn1" onclick="searchByDate(1);">查&nbsp;询</span>
									</p>
									<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s1">
										 <tr>
										 	<th align="center">日期</th>
										 	<th align="center">金额</th>
										 	<th align="center">类型</th>
										 </tr>
									</table>
									<ul id="pager1" class="pager"></ul>
				           		</div>
				           	</ol>
				           	<ol id="2">
				           		<div class="tab-list">
				           			<p style="padding:10px 30px 10px 0; float:right;">
										日期：
										<input type="text" id="startTime_dealDate2" style="width:120px"  class="colorful" readonly="readonly"  onclick="new Calendar().show(this);"/>
										<label class="f-l m-t_5"><span class="black normal">-</span></label>
										<input type="text" id="endTime_dealDate2" style="width:120px"  class="colorful" readonly="readonly"  onclick="new Calendar().show(this);"/>
										<#--<span style="font-size:14px; padding:4px 15px;color:#fd7754;" id="selectUseraccountinfo" name="selectUseraccountinfo">查&nbsp;询</span>-->
										<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;color:#fff;" id="queryBtn2" name="queryBtn2" onclick="searchByDate(2);">查&nbsp;询</span>
									</p>
									<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s2">
										 <tr>
										 	<th align="center">日期</th>
										 	<th align="center">金额</th>
										 	<th align="center">类型</th>
										 </tr>
									</table>
									<ul id="pager2" class="pager"></ul>
				           		</div>
				           	</ol>
				        </div>
				   </div>
				
				<#--<div class="pagination m-t_15" id="fund_pager" style="height:30px; position:relative;width:100%;text-align:center;">
						 <#import "/WEB-INF/template/common/pager.ftl" as p>
							<@p.pager pager = pager baseUrl = "/financeProduct/personFianceAccountPlFinanceProduct.do?startTime=${startTime_dealDate}&endTime=${endTime_dealDate}"/>
				</div>-->
		</div>
		<!--end升升投宝-->
		</div>
	</div>
</div>
<!--end: main -->
      <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<#--存放jsrender模板的容器-->
	<#include "/WEB-INF/template/${systemConfig.theme}/user/personTempContainer.ftl">
	<!--充值弹框-->
	 <#include "/WEB-INF/template/common/user/rechargeBox.ftl">
<!--充值弹框End-->
	</body>
</html>

<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/recharge.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript" src="${base}/js/common.js"></script>


<#--引入jsrender -->
<script type="text/javascript" src="${base}/js/page/jquery.pagination-1.2.3.js"></script>
<script type="text/javascript" src="${base}/js/jsrender/jsrender.min.js"></script>

<!--引入highchart插件-->
<script src="${base}/js/highchar/highcharts.js" type="text/javascript"></script> 
<script type="text/javascript" src="${base}/js/user/FianceProduct.js"></script>
<script type="text/javascript" src="${base}/js/highchar/fianceProductRate.js"></script>
<script type="text/javascript">
$(function(){
	$(".cont-list .finlist_title li").click(function(){
		$(this).addClass("active").siblings().removeClass();
		$(".cont-list .finlist_hide ol").eq($(this).index()).addClass("rent").siblings().removeClass();
		addPage($(".cont-list .finlist_hide ol").eq($(this).index())[0].id,'');
	});
})
</script>

<script type="text/javascript">
$(function () {
	showStatistic();
	addPage(0,'');
	function searchByDate(Nums){debugger
		var type=$("#type").val();
		var start_date = $("#startTime_dealDate"+type).val();
		var end_date = $("#endTime_dealDate"+type).val();
		addPage(Nums,{type:Nums,start:0,limit:10,startTime:start_date,endTime:end_date});
	}
});
	function searchByDate(Nums){debugger
		var type=$("#type").val();
		var start_date = $("#startTime_dealDate"+Nums).val();
		var end_date = $("#endTime_dealDate"+Nums).val();
		addPage(Nums,{type:Nums,start:0,limit:10,startTime:start_date,endTime:end_date});
	}
	



	function showStatistic(){debugger
	    var new_map0={age:2,start:0,limit:10};
	    ajaxFunction("${base}/financeProduct/showCurrentFinancialPlFinanceProduct.do",new_map0,callback0_0);
		
	}	
	function callback0_0(data){
	 	var result=data.result;
	 	if(result==null||result==''||result=='undefined'){
	 	 
		}else{
		 	$(".head-num").empty().html($("#temp").render(result));//累计赚取收益
		}
	 }
	

	
	
	function addPage(Nums,params){
		//debugger;
		var url="${base}/financeProduct/showSaleRecordPlFinanceProduct.do";
		var param={};
		if(Nums==0){//收益
			if(params!=null&&params!=''&&params!='undefined'){
				params = params;
			}else{
				params = {type:"0", start:"0",limit:"10"};
			}
		}else if(Nums==1){//转入
			if(params!=null&&params!=''&&params!='undefined'){
				params = params;
			}else{
				params = {type:"1", start:"0",limit:"10"};
			}
		}else if(Nums==2){//转出
			if(params!=null&&params!=''&&params!='undefined'){
				params = params;
			}else{
				params = {type:"2", start:"0",limit:"10"};		
			}
		}
		$("#pager"+Nums).page({
		    pageSize: 10,
		    remote: {
		        url: url,  //请求地址
		        params: params,       //自定义请求参数
		        callback: function (result) {
		            //回调函数
		            //result 为 请求返回的数据，呈现数据
		            var data=result.result;
		            //使用模板绑定数据
		             var tr=$("#s"+Nums).find('tr')[0];
		           $("#s"+Nums).empty().append(tr).append($("#page_tmp"+Nums).render(data));
		           $("#type").val(params.type);
		           console.log(Nums);
		           console.log(params.type);
		        }
		    }
		});
}
</script>


