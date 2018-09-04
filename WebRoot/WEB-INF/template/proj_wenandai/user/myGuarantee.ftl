<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title>${systemConfig.metaTitle} - 我的担保</title>
    <meta name="description" content="${systemConfig.metaTitle} - 我的担保,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 我的担保,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="个人中心",m2="我的担保",m3="";</script>
<script type="text/javascript">
	$(function(){
		$(".cont-list .finlist_title li").click(function(){
			$(this).addClass("active").siblings().removeClass();
			$(".cont-list .finlist_hide ol").eq($(this).index()).addClass("rent").siblings().removeClass();
		});
	})
</script>
</head>
<body>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
		<div class="main">
			<div class="user-cont">
				<!--左侧三级导航条 -->
				<div class="user-name-nav fl">
					<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
					<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
				</div>
				<div class="user-cont-fr fr">
				<input type="hidden" id="type" />
					<div class="user-cont-right">
						<div class="data-list">
							<div class="date-left fl" style="padding-top: 25px; width:330px;">
								<div class="user-money-all fl">
						   		<div class="icon-bg fl">
						   			<span class="user-money-icon"></span>
						   		</div>
						   		<div class="user-data-all fr" style="width:230px;">
						   			<p class="normal">账户可用余额(元)</p>
						   			<p class="num middle"><em><#if bpCustMember!=null><#if bpCustMember.availableInvestMoney==0>0.00<#else><#if bpCustMember.availableInvestMoney lt 1000>${bpCustMember.availableInvestMoney}<#else>${bpCustMember.availableInvestMoney?string(',###.00')}</#if></#if><#else>0.00</#if></em>元</p>
						   			<p class="btn">
							        	<a href="${base}/financePurchase/rechargeFinancePurchase.do?retUrl=user/getBpCustMember.do" >充 值</a>
							            <a href="${base}/financePurchase/withdrawFinancePurchase.do?retUrl=user/getBpCustMember.do" >提 现</a>
							            <a href="${base}/financePurchase/getBindBankListFinancePurchase.do">银行卡</a>
							        </p>
						   		</div>
						   	</div>
							</div>
							<div class="data-right fl" style="width:560px;" id="po3">
								<div class="data-invent" style="width:185px;">
									<p class="normal"><i class="icon-num icon-01"></i>在保项目(个)</p>
									<p class="middle pd-30"  id="db01"><em>0</em>个</p>
								</div>
								<div class="data-invent" style="width:185px;">
									<p class="normal"><i class="icon-num icon-02"></i>结清项目（个）</p>
									<p class="middle pd-30" id="db02"><em>0</em>个</p>
								</div>
								<div class="data-invent" style="width:185px;">
									<p class="normal"><i class="icon-num icon-03"></i>逾期项目（个）</p>
									<p class="middle pd-30" id="db03"><em>1</em>个</p>
								</div>
								<div class="data-invent" style="width:185px;">
									<p class="normal"><i class="icon-num icon-04"></i>代偿笔数（笔）</p>
									<p class="middle pd-30" id="db04"><em>1</em>个</p>
								</div>
								<div class="data-invent" style="width:185px;">
									<p class="normal"><i class="icon-num icon-01"></i>已追回笔数（笔）</p>
									<p class="middle pd-30" id="db05"><em>0</em>笔</p>
								</div>
								<div class="data-invent" style="width:185px;">
									<p class="normal"><i class="icon-num my-icon03" style="background-position: -70px -153px;"></i>未读消息(条)</p>
									<p class="middle pd-30" ><em><#if notRead!=null><#if notRead!=0>${notRead}<#else>0</#if></#if></em></p>
								</div>
							</div>
						</div>
						<div class="dashedline"></div>
						<div class="data-list">
							<div class="date-left fl">
								<div class="icon-img" style="float: none;">
									<span class="data-icon icon-two"></span>
								</div>
								<div class="title" style="padding-top: 15px; padding-left:20px;">
									<p class="f20">资金数据</p>
								</div>
							</div>
							<div class="data-right fl" id="po3" style="padding-top:15px;">
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-01"></i>在保金额（元）</p>
									<p class="middle pd-30" id="zj01"><em>0</em>元</p>
								</div>
								<!--<div class="data-invent">
									<p class="normal"><i class="icon-num icon-02"></i>已收担保费（元）</p>
									<p class="middle pd-30" id="zj02"><em>0</em>元</p>
								</div>-->
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-03"></i>代偿金额（元）</p>
									<p class="middle pd-30" id="zj04"><em>1</em>元</p>
								</div>
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-04"></i>应收罚息（元）</p>
									<p class="middle pd-30" id="zj05"><em>1</em>元</p>
								</div>
							</div>
							<!--<div class="data-right fr" style="width:200px;padding:50px 0 0 0;" id="po2">
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-01"></i>待收担保费（元）</p>
									<p class="middle pd-30" id="zj03"><em>0</em>元</p>
								</div>
							</div>-->
						</div>
						<div class="gray-bg">提示：以上统计结果不包含“债权交易“相关数据（具有特殊性），如需查阅，请进入相关频道查看具体明细。</div>
						<div class="cont-list">
							<ul class="finlist_title">
								<li class="active">逾期项目</li>
								<li>代偿项目</li>
								<li>在保项目</li>
								<li>结清项目</li>
							</ul>
							<div class="finlist_hide">
								<ol class="rent" id="1">
									<div class="tab-list">
										<p style="padding:5px 30px 10px 0; float:left;">
											<span style="padding:0 10px;">
											项目名称:
											<input type="text" style="width:120px" id="bidname1" name="bidtime" class="colorful"/>
											</span>
											<span style="padding:0 10px;">
											逾期天数: 
											<select class="colorful" style="width:120px" id="overDays">
												<option>--请选择--</option>
												<option>1天</option>
												<option>3天</option>
												<option>5天</option>
												<option>7天</option>
												<option>15天</option>
												<option>15天以上</option>
											</select>
											</span>
											<span style="padding:0 10px;">
											应还日期: 
											<input type="text" style="width:120px" id="start_time"  name="bidtime" class="colorful" readonly="readonly"  onclick="new Calendar().show(this);" />
											-
											<input type="text" style="width:120px" id="end_time" name="bidtime" class="colorful" readonly="readonly" onclick="new Calendar().show(this);" />
											</span>
											 <span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="queryOutList" name="queryBtn" onclick="searchData();">查&nbsp;询</span><#--style="font-size:14px; padding:4px 15px;color:#fd7754;" -->
										</p>
										<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s1" >
											<tr>
												<th align="center">项目名称</th>
												<th align="center">借款人</th>
												<th align="center">还款期数</th>
												<th align="center">应还本金</th>
												<th align="center">利息金额</th>
												<th align="center">费用总额</th>
												<th align="center">应还日期</th>
												<th align="center">逾期天数</th>
												<th align="center">当前罚息</th>
												<th align="center">操作</th>
												<th align="center">查看</th>
											</tr>
										</table>
										<ul id="pager1" class="pager"></ul>
									</div>
								</ol>
								<ol id="2">
									<div class="tab-list">
										<!--<div class="worm-tips fl">
											<span class="icon1">已齐标</span>
										</div>-->
										<p style="padding:5px 30px 10px 0; float:left;">
											项目名称:
											<input type="text" style="width:120px" id="bidname2" name="bidtime" class="colorful" />
											<span>&nbsp;&nbsp;&nbsp;&nbsp;<label class="f-l m-t_5"><span class="black normal">代偿日期：</span></label>
												<input type="text" id="cstartDate" style="width:120px" name="bidtime" class="colorful" readonly="readonly" value="" onclick="new Calendar().show(this);"/>
												<label class="f-l m-t_5"><span class="black normal">-</span></label>
												<input type="text" id="cendDate" style="width:120px" name="bidtime2" class="colorful" readonly="readonly" value="" onclick="new Calendar().show(this);"/>
							            	 </span>&nbsp;&nbsp;
											
											<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="queryOutList" name="queryBtn" onclick="searchData();">查&nbsp;询</span>
										</p>
										<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s2">
											<tr>
											  <th align="center">代偿名称</th>
							                  <th align="center">借款人</th>
							                  <th align="center">代偿期数</th>
							                  <th align="center">代偿日期</th>
							                  <th align="center">代偿金额</th>
							                  <th align="center">代偿罚息</th>
							                  <th align="center">已追回金额</th>
							                  <th align="center">实际到账日</th>
							                  <th align="center">操作</th>
											</tr>
										</table>
										<ul id="pager2" class="pager"></ul>
									</div>
								</ol>
								<ol id="3">
									<div class="tab-list">
										<div class="worm-tips fl">
											<span class="icon1">在保项目</span>
										</div>
										<p style="padding:5px 30px 10px 0; float:left;">
											项目名称:
											<input type="text" style="width:120px" id="bidname3" name="bidname3" class="colorful"  />
											<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="queryOutList" name="queryBtn" onclick="searchData();">查&nbsp;询</span>
										</p>
										<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s3">
											<tr>
												<th align="center">项目名称</th>
								                  <th align="center">借款人</th>
								                  <th align="center">金额</th>
								                  <th align="center">期限</th>
								                  <th align="center">年化利率</th>
								                  <th align="center">查看</th>
												<!--<th align="center">剩余可投金额</th>-->
											</tr>
										</table>
										<ul id="pager3" class="pager"></ul>
									</div>
								</ol>
								<ol id="4">
									<div class="tab-list">
										<div class="worm-tips fl">
											<span class="icon1">已结清项目</span>
										</div>
										<p style="padding:5px 30px 10px 0; float:left;">
											项目名称:
											<input type="text" style="width:120px" id="bidname4" class="colorful" />
											<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="queryOutList" name="queryBtn" onclick="searchData();">查&nbsp;询</span>
										</p>
										<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s4">
											<tr>
												  <th align="center">项目名称</th>
								                  <th align="center">借款人</th>
								                  <th align="center">金额</th>
								                  <th align="center">期限</th>
								                  <th align="center">年化利率</th>
								                  <th align="center">查看</th>

											</tr>
										</table>
										<input type="hidden" id="memberId" value="${memberId}" />
										<ul id="pager4" class="pager"></ul>
									</div>
								</ol>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
	<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
		</body>
	</html>
	<#include "/WEB-INF/template/${systemConfig.theme}/user/myGuaranteeTemp.ftl">
	<script type="text/javascript" src="${base}/js/common.js"></script>
	<#--引入jsrender -->
	<script type="text/javascript" src="${base}/js/page/jquery.pagination-1.2.3.js"></script>
	<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
	<script type="text/javascript" src="${base}/js/jsrender/jsrender.min.js"></script>
	<script type="text/javascript" src="${base}/js/user/payment.js"></script>
	<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
	<script src='${base}/js/sale_jsrender.js'></script>
	<script type="text/x-jsrender" id="temp2">
	<option>{{:punishDays}}</option>



</script>
<script type="text/javascript">
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
		  function repayByFinance(){
				var planId=$("#planId").val();
				var intentDate=$("#intentDate").val();
		        var notMoney=$("#notMoney").val();
				var orderNo=$("#orderNo").val();
				parent.window.location.href=basepath+"/pay/compensatoryPay.do?planId="+planId
					+"&intentDate="+intentDate+"&notMoney="+notMoney+"&orderNo="+orderNo
		        var index = parent.layer.getFrameIndex(window.name);
		        parent.layer.close(index);
		   }
	});
	$(function () {
	    new Calendar().hide();
		var map1 = {};
		//担保数据
		$("#pager1").empty();
		ajaxFunction("${base}/user/getBidCountGuaranteeBpCustMember.do?state=7", map1,db01);
		
		ajaxFunction("${base}/user/getBidCountGuaranteeBpCustMember.do?state=10", map1, db02);
		
		ajaxFunction("${base}/user/getOverdueBidCountGuaranteeBpCustMember.do", map1, db03);
		
		ajaxFunction("${base}/user/getBidCountCompensatoryBpCustMember.do?backStatus=0", map1, db04);
		
		ajaxFunction("${base}/user/getBidCountCompensatoryBpCustMember.do?backStatus=2", map1, db05);
		
		
		//资金数据
		ajaxFunction("${base}/user/getBidMoneyGuaranteeBpCustMember.do?state=7", map1, zj01);
		
		ajaxFunction("${base}/user/getCompensatoryMoneyBpCustMember.do", map1, zj04);
		
		ajaxFunction("${base}/user/getPunishMoneyBpCustMember.do", map1, zj05);
		
		ajaxFunction("${base}/user/getCountBidLoanBpCustMember.do", map1, zj02);
		
		ajaxFunction("${base}/user/getCountRepaymentLoanBpCustMember.do", map1, zj03);
		
		ajaxFunction("${base}/user/getWaitCompensatoryMoneyBpCustMember.do", map1, zj06);
		$("#pager1").empty();
		addPage(1,'');
		function searchData(){
		   var type = $("#type").val();
		   var name = $("#bidname"+type).val();
		   var overDays = $("#overDays").val();
		   var startDate = $("#start_time").val();
		   var endDate = $("#end_time").val();
		   $("#pager"+type).empty();
		   addPage(type,{type:type,name:name,overDays:overDays,startDate:startDate,endDate:endDate,start:0,limit:10});
		}	 
   	 });
	 
	function searchData(){
	   var type = $("#type").val();
	   var name = $("#bidname"+type).val();
	   var overDays = $("#overDays").val();
	   if(overDays.indexOf("--")==0){
	   	  overDays = "";
	   }else{
		   if(overDays.length>4){
		   	  overDays = 16;
		   }else{
		   	  overDays = overDays.substring(0,overDays.length-1);
		   };
	   }
	   var startDate = $("#start_time").val();
	   var endDate = $("#end_time").val();
	    $("#pager"+type).empty();
	   addPage(type,{type:type,name:name,overDays:overDays,startDate:startDate,endDate:endDate,start:0,limit:10});
	}	 
	 function db01(data){
		 var result=data.result;
		 $("#db01").empty().html(result);
	 }
	 function db02(data){
		 var result=data.result;
		 $("#db02").empty().html(result);
	 }
	  function db03(data){
		 var result=data.result;
		 $("#db03").empty().html(result);
	 }
	 function db04(data){
		 var result=data.result;
		 $("#db04").empty().html(result);
	 }
	 function db05(data){
		 var result=data.result;
		 $("#db05").empty().html(result);
	 }
	 function zj01(data){
		 var result=data.result;
		 $("#zj01").empty().html(result);
	 }
	 function zj02(data){
		 var result=data.result;
		 $("#zj02").empty().html(result);
	 }
	 function zj03(data){
		 var result=data.result;
		 $("#zj03").empty().html(result);
	 }
	 function zj04(data){
		 var result=data.result;
		 $("#zj04").empty().html(result);
	 }
	 function zj05(data){
		 var result=data.result;
		 $("#zj05").empty().html(result);
	 }
	 function zj06(data){
		 var result=data.result;
		 $("#zj06").empty().html(result);
	 }
	 
     function addPage(Nums,param){debugger;
		$("#pager"+Nums).empty();
		var url="${base}/user/loadMyGuauateeBpCustMember.do";
		if(Nums==1){debugger//逾期项目
			if(param!=null&&param!=''&&param!='undefined'){
				params = param;
			}else{
				params = {type:Nums,start:"0",limit:"10"};
			}
		}else if(Nums==2){//交易中的债权
			if(param!=null&&param!=''&&param!='undefined'){
				params = param;
			}else{
				params = {type:Nums,start:"0",limit:"10"};
			}
		}else if(Nums==3){//在保项目
			if(param!=null&&param!=''&&param!='undefined'){
				params = param;
			}else{
				params = {type:Nums,start:"0",limit:"10"};
			}
		}else if(Nums==4){//已购买的债权
			if(param!=null&&param!=''&&param!='undefined'){
				params = param;
			}else{
				params = {type:Nums,start:"0",limit:"10"};
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
		        //   $("#pager"+Nums).empty();
		           $("#s"+Nums).empty().append(tr).append($("#page_tmp"+Nums).render(data));
		      //     $("#overDays").empty().append(tr).append($("#temp2").render(data));
		           $("#type").val(Nums);
		           console.log(data);
		        }
		    }
		});
	}
</script>