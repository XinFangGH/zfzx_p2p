<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 债权交易</title>
    <meta name="description" content="${systemConfig.metaTitle} - 债权交易,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 债权交易,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="个人中心",m2="bbbb",m3="我的投资";</script>
<script type="text/javascript">
$(function(){
	$('.cont-list li').click(function(){ 
	$(this).addClass("active").siblings().removeClass('active');
	$(".cont-list .finlist_hide ol").hide().eq($('.cont-list li').index(this)).show(); 
	});
}) 	
</script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
		<div class="main">
			<input id="str" type="hidden" value="${str}">
			<div class="user-cont">
				<!--左侧三级导航条 -->
				<div class="user-name-nav fl">
					<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
				</div>
				<!--end:左侧三级导航条 -->
				<div class="user-cont-fr fr">
					<div class="user-cont-right">
						<div class="head-num" id="dataShow">
							<div class="num-icon fl">
								<div class="icon-img icon-img-one fl"></div>
								<div class="num-data fl">
									<p class="normal">可交易债权总额(元)</p>
									<p class="middle"><em>0.00</em>元</p>
								</div>
							</div>
							<div class="num-icon fl">
								<div class="icon-img icon-img-two fl"></div>
								<div class="num-data fl">
									<p class="normal">已交易债权总额(元)</p>
									<p class="middle"><em>0.00</em>元</p>
								</div>
							</div>
							<div class="num-icon fl">
								<div class="icon-img icon-img-three fl"></div>
								<div class="num-data fl">
									<p class="normal">成功交易笔数(笔)</p>
									<p class="middle"><em>0</em>笔</p>
								</div>
							</div>
						</div>
						<h2 class="big-tit">债权交易</h2>
						<div class="cont-list">
							<ul class="finlist_title">
								<li class="active">可交易债权</li>
								<li>交易中债权</li>
								<li>已卖出债权</li>
								<li>已购买债权</li>
								<li>已关闭交易</li>
							</ul>
							<div class="finlist_hide">
								<ol class="rent" id="7">
									<div class="tab-list">
										<div class="worm-tips">
											<span class="icon1">可交易债权</span>
										</div>
										<table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s7">
											<tr>
												<th align="center"> 债权名称 </th>
												<th align="center">出让本金</th>
												<th align="center">操作</th>
											</tr>
			              				</table>
			              			<ul id="pager7" class="pager1">
									</ul>
		            		</div>
		       		</ol>
		       		<ol id="1">
				       <div class="tab-list">
				           <div class="worm-tips">
				               <span class="icon1">交易中债权</span>
									</div>
									<table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s1">
										<tr>
											<th align="center">债权名称 </th>
											<th align="center">出让本金</th>
											<th align="center"> 折让金 </th>
											<th align="center">折让率 </th>
											<th align="center">挂牌时间 </th>
											<th align="center">操作 </th>
										</tr>
									</table>
									<ul id="pager1" class="pager1"></ul>
							</div>
							</ol>
							<ol id="10">
								<div class="tab-list">
									<div class="worm-tips">
										<span class="icon1">已卖出债权</span>
									</div>
									<table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s10">
										<tr>
											<th align="center">债权名称 </th>
											<th align="center">出让本金</th>
											<th align="center"> 折让金 </th>
											<th align="center">折让率 </th>
											<th align="center">结算金额 </th>
											<th align="center">交易时间 </th>
											<th align="center">操作 </th>
										</tr>
									</table>
									<ul id="pager10" class="pager1">
									</ul>
								</div>
							</ol>
							<ol id="2">
								<div class="tab-list">
									<div class="worm-tips">
										<span class="icon1">已购买债权</span>
									</div>
									<table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s2">
										<tr>
											<th align="center">债权名称 </th>
											<th align="center">出让本金</th>
											<th align="center"> 折让金 </th>
											<th align="center">折让率 </th>
											<th align="center">结算金额 </th>
											<th align="center">交易时间 </th>
											<th align="center">操作 </th>
										</tr>
									</table>
									<ul id="pager2" class="pager1">
									</ul>
								</div>
							</ol>
							<ol id="3">
								<div class="tab-list">
									<div class="worm-tips">
										<span class="icon1">已关闭交易</span>
									</div>
									<table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s3">
										<tr>
											<th align="center">债权名称 </th>
											<th align="center">出让本金</th>
											<th align="center"> 折让金 </th>
											<th align="center">折让率 </th>
											<th align="center">关闭时间 </th>
										</tr>
									</table>
									<ul id="pager3" class="pager1"></ul>
								</div>
							</ol>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
</body>
</html>
<!--end: Container -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/bid/sale/saleTempContainer.ftl">

<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript" src="${base}/js/common.js"></script>
<#--引入jsrender -->
<script type="text/javascript" src="${base}/js/page/jquery.pagination-1.2.3.js"></script>
<script type="text/javascript" src="${base}/js/jsrender/jsrender.min.js"></script>
<script src='${base}/js/sale_jsrender.js'></script>
<script type="text/javascript" >

	$.views.converters("formartDate", function(val) {  
	    if(val==""||val=='undefined'||val==null){debugger
	        return "";
	    }else{
		    var date = new Date(val);
		    var date1 = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	    	return date1;
	    }
	});


	$(function () {
		showStatistic();
		addPage(7);
	});

	   //查询统计数据	
	function showStatistic(){
	    var new_map0={age:2,start:0,limit:10};
	    ajaxFunction("${base}/creditFlow/financingAgency/loadSaleDataPlBidSale.do",new_map0,callback0_0);
	}	
	
    //获取债权交易的统计数据
	 function callback0_0(data){debugger;
	 	var result=data.result;
	 	if(result==null || result==''||result=='undefined'){
	 	 
		 }else{
	 		$("#dataShow").empty().html($("#temp").render(result));//累计赚取收益
	 	}
	 }

	$.views.converters("substr", function(v) { debugger;
	 	var ret="";
	 	if(v==null || v==''||v=='undefined'){
	 		return  ret=0.00; 
	 	}else{
			return  ret=v;  
	 	}
	 	
	});
	function addPage(Nums){debugger;
		var url="${base}/creditFlow/financingAgency/showSaleRecordPlBidSale.do";
		var param={};
		if(Nums==7){//可交易债权
			params = {type:"CanTransfering",start:"0",limit:"10"};
		}else if(Nums==1){//交易中的债权
			params = {type:"transferingList",start:"0",limit:"10"};
		}else if(Nums==10){//已卖出的债权
			params = {type:"transfered",start:"0",limit:"10"};		
		}else if(Nums==2){//已购买的债权
			params = {type:"buyed",start:"0",limit:"10"};
		}else if(Nums==3){//已关闭的债权
			params = {type:"closed",start:"0",limit:"10"};
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
		           console.log(data);
		        }
		    }
		});
}
</script>

