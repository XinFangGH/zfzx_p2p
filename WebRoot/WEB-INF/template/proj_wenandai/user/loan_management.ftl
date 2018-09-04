<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 借款管理</title>
    <meta name="description" content="${systemConfig.metaTitle} - 借款管理,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 借款管理,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/loading.css" />
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/xy-lc.css">
<script type="text/javascript">var m1="我的账户",m2="我的借款",m3="借款管理";</script>
<script type="text/javascript">
$(function(){
	$.divselect(".divselectall");
});
</script>
<script type="text/javascript">
window.onload=function() {
	
	$(".contents .finlist_title li").each(function(index) {debugger //带参数遍历各个选项卡
		var className = $(this).attr("class");
		var show = $("#show").val();
		
		if(show=="Repayment"){
			$(".contents .finlist_hide ol:eq(0)").show()
			.siblings().hide();
		}
		if(show=="loan"){
			$(".contents .finlist_hide ol:eq(1)").show()
			.siblings().hide();
		}
		$(this).click(function() {      //注册每个选卡的单击事件
		    console.log($(this));
		    $("#pager5").empty();
		    $("#pager7").empty();
		    $("#pager1").empty();
		    $("#pager10").empty();
		    $("#pager-1").empty();
		    addPage(5);
			$(".contents .finlist_title li.active").removeClass("active"); //移除已选中的样式
			$(this).addClass("active"); //增加当前选中项的样式
			//显示选项卡对应的内容并隐藏未被选中的内容
			if(index==0){
				$(".contents .finlist_hide ol:eq(0)").show().show().siblings().hide();
				addPage($(".contents .finlist_hide ol:eq(0)")[0].id);
				$(".contents .finlist_hide ol:eq(1)").show();
				addPage($(".contents .finlist_hide ol:eq(1)")[0].id);
			}else{
				var indexeq = index+1;
				$(".contents .finlist_hide ol:eq("+indexeq+")").show().siblings().hide();
				$("#pager"+$(".contents .finlist_hide ol:eq(" + indexeq + ")")[0].id).empty();
				addPage($(".contents .finlist_hide ol:eq(" + indexeq + ")")[0].id);
			}
		});
	});
}
</script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">

		<input value="${(bpCustMember.id)??}" type="hidden" id="memberId">
		<div class="main">
			<div class="user-cont">
				<!--左侧三级导航条 -->
				<div class="user-name-nav fl">
                    <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
					<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
				</div>
				<!--end:左侧三级导航条 -->
				<div class="user-cont-fr fr">
					<div class="user-cont-right">
						<div class="data-list">
							<div class="date-left fl">
								<div class="icon-img">
									<span class="data-icon"></span>
								</div>
								<div class="title">
									<p class="f20">截止近一年：</p>
									<p class="middle org">借款统计</p>
								</div>
							</div>
							<div class="data-right fr" id="dataShow">
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-01"></i>累计借款笔数</p>
									<p class="middle pd-30"><em>10</em>笔</p>
								</div>
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-02"></i>招标中笔数（笔）</p>
									<p class="middle pd-30"><em>10</em>笔</p>
								</div>
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-03"></i>待还笔数（笔）</p>
									<p class="middle pd-30"><em>10</em>笔</p>
								</div>
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-04"></i>累计借款金额（元）</p>
									<p class="middle pd-30"><em>1000,000.00</em>元</p>
								</div>
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-05"></i>招标中金额（元）</p>
									<p class="middle pd-30"><em>1000,000.00</em>元</p>
								</div>
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-06"></i>待还金额（元）</p>
									<p class="middle pd-30"><em>1000,000.00</em>元</p>
								</div>
							</div>
						</div>
						<div class="cont-list contents">
							<ul class="finlist_title">
								<li id="backing" class="active">还款中借款</li>
								<li>招标中借款</li>
								<li>已结清借款</li>
								<li>借款申请查询</li>
							</ul>
							<div class="finlist_hide">
								<ol id="5">
		<div class="tab-list" id="mydiv">
    		<div class="worm-tips">
    			<span class="icon1">代偿列表（请先偿还代偿借款）</span>
        	 </div>
        	 		<ul>
						<li style="margin-top: 20px;">
						</li>
						<li>
							<table width="100%" class="tab_css_one"  border="0" cellpadding="0"  cellspacing="0" id="s5">
								<tr>
									<th width="15%" align="center">代偿名称</th>
									<th width="10%" align="center">代偿期数</th>
									<th width="10%" align="center">代偿日期</th>
									<th width="10%" align="center">代偿金额</th>
									<th width="10%" align="center">罚息</th>
									<th width="10%" align="center">已偿还罚息</th>
									<th width="10%" align="center">已偿还金额</th>
									<th width="10%" align="center">平账金额</th>
									<th width="10%" align="center">罚息天数</th>
									<th width="10%" align="center">剩余金额</th>
									<th width="10%" align="center">操作</th>
								</tr>
	 						</table>
						</li>
					<li>
						<ul id="pager5" class="pager">
						</ul> 
					</li>
				</ul>
    </div>
    </ol>
								<ol class="rent" id="7">
									<div class="tab-list">
										<div class="worm-tips">
											<span class="icon1">还款中借款</span>
										</div>
										<ul>
											<li style="margin-top: 20px;">
											</li>

											<li>
												<table width="100%" class="tab_css_one" border="0" cellpadding="0" cellspacing="0" id="s7">
													<tr>
														<th width="15%" align="center">名称</th>
														<th width="10%" align="center">还款期号</th>
														<th width="10%" align="center">计划还款日</th>
														<th width="15%" align="center">应还本息合计</th>
														<th width="10%" align="center">当前罚息</th>
														<th width="10%" align="center">当前应还总额</th>
														<th width="15%" align="center">查看</th>
														<th width="15%" align="center">操作</th>
													</tr>
												</table>
											</li>
											<li>
												<ul id="pager7" class="pager">
												</ul>
											</li>
										</ul>
									</div>
								</ol>

								<ol id="1">
									<div class="tab-list">
										<div class="worm-tips">
											<span class="icon1">招标中借款</span>
										</div>
										<ul>
											<li style="margin-top: 20px;">
											</li>
											<li>
												<table width="100%" class="tab_css_one" border="0" cellpadding="0" cellspacing="0" id="s1">
													<tr>
														<th width="15%" align="center">借款名称</th>
														<th width="15%" align="center">招标金额</th>
														<th width="10%" align="center">发标时间</th>
														<th width="10%" align="center">期限</th>
														<th width="10%" align="center">年化利率</th>
													</tr>
												</table>

											</li>
											<li>
												<ul id="pager1" class="paginationjs-theme-blue paginationjs-small">
												</ul>
											</li>
										</ul>
									</div>
								</ol>

								<!-- 已完成开始-->
								<ol id="10">
									<div class="tab-list">
										<div class="worm-tips">
											<span class="icon1">已结清借款</span>
										</div>
										<ul>
											<li style="margin-top: 20px;"></li>
											<li>
												<table width="100%" class="tab_css_one" border="0" cellpadding="0" cellspacing="0" id="s10">
													<tr>
														<th width="15%" align="center">借款名称</th>
														<th width="15%" align="center">招标金额</th>
														<th width="10%" align="center">发标时间</th>
														<th width="10%" align="center">期限</th>
														<th width="10%" align="center">年化利率</th>
														<th width="15%" align="center">还款总额</th>
														<th width="10%" align="center">还清日期</th>
														<th width="15%" align="center">查看</th>
													</tr>

												</table>
											</li>
											<li>
												<ul id="pager10" class="pager">
												</ul>
											</li>
										</ul>
									</div>
								</ol>
								<!-- 已完成结束-->

								<!--借款申请查询开始 -->
								<ol id="-1">
									<div class="tab-list">
										<div class="worm-tips">
											<span class="icon1">借款申请查询</span>
										</div>
										<!--此处放置内容-->

										<table width="100%" class="tab_css_one" id="s-1" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<th width="10%" align="center">借款标题</th>
												<th width="10%" align="center">借款产品</th>
												<th width="15%" align="center">借款金额</th>
												<th width="10%" align="center">借款期限</th>
												<th width="10%" align="center">申请时间</th>
												<th width="15%" align="center">申请/审核状态</th>
											</tr>
											<#list listApplyUser as loan>
												<tr>
													<td width="10%" align="center">${loan.loanTitle}</td>
													<td width="10%" align="center">${loan.productName}</td>
													<td width="15%" align="center">${loan.loanMoney}元</td>
													<td width="10%" align="center">${loan.loanTimeLen}个月</td>
													<td width="10%" align="center">${loan.createTime}</td>
													<td width="15%" align="center">
														<#if loan.state=='0'>
															<a href="${base}/loan/getNodeP2pLoanProduct.do?productId=${loan.productId}">未填写完整继续填写</a> | <a href="${base}/financePurchase/endApplyapplyUser.do?id=${loan.loanId}}">终止</a><br/>
														</#if>
														<#if loan.state=='1'>
															已提交审核<br/>
														</#if>
														<#if loan.state=='2'>
															已受理<br/>
														</#if>
														<#if loan.state=='3'>
															<a href="${base}/loan/getNodeP2pLoanProduct.do?productId=${loan.productId}">打回,请重新填写</a> | <a href="${base}/financePurchase/endApplyapplyUser.do?id=${loan.loanId}}">终止</a><br/>
														</#if>
														<#if loan.state=='4'>
															已通过审批<br/>
														</#if>
														<#if loan.state=='5'>
															立项中<br/>
														</#if>
														<#if loan.state=='6'>
															已发标<br/>
														</#if>
														<#if loan.state=='7'>
															未通过审核已终止<br/>
														</#if>
													</td>
												</tr>
											</#list>
										</table>
										<ul id="pager-1" class="pager"></ul>
									</div>
								</ol>
								<!--借款申请查询结束 -->
								<!-- 还款授权-->
								<ol>
									<div class="tab-list">
										<ul>
											<li style="margin-top: 20px;"></li>
											<li>
												<table width="100%" class="tab_css_one" border="0" cellpadding="0" cellspacing="0">
													<tr>
														<th width="10%" align="center">借款名称</th>
														<th width="7%" align="center">金额</th>
														<th width="13%" align="center">起始日期</th>
														<th width="13%" align="center">到期日期</th>
														<th width="7%" align="center">期限</th>
														<th width="10%" align="center">年化利率</th>
														<th width="10%" align="center">还款授权</th>
														<th width="10%" align="center">查看</th>
													</tr>
													<#list authorizationPlan as list>
														<tr>
															<td align="center" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
																<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" title="${list.bidProName}"><span class="normal">${list.bidProName}</span></a>
															</td>
															<td align="center"><span class="normal"><#if list.bidMoney==0>0.00<#else>${list.bidMoney}</#if></span></td>
															<td align="center"><span class="normal">${list.publishSingeTime}</span></td>
															<td align="center"><span class="normal">${list.bidEndTime}</span></td>
															<td align="center"><span class="normal">${list.loanLife}</span></td>
															<td align="center"><span class="normal">${list.interestRate}%</span></td>
															<td align="center"><a target="_Blank" href="${base}/pay/autoRepaymentAuthorizationPay.do?planId=${list.bidId}&actionStatus=<#if list.authorizationStatus==1>cancel<#else>authorization</#if>" style="text-decoration:underline;"><span class="normal"><#if list.authorizationStatus==1>取消授权<#else>还款授权</#if></span></a></td>
															<td align="center">
																<a href="javascript:void(0);" onClick="clickPlan(${list.bidId},'Financial')"><span class="normal">回款计划&nbsp;&nbsp;|</span></a>
																<a <#if list.url>href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=loan&contractUrl=${list.url}"<#else>href="javascript:void(0);" onclick="ht()"</#if>><span class="normal">&nbsp;&nbsp;合同</span></a>
															</td>
														</tr>
													</#list>
												</table>
											</li>
										</ul>
									</div>
								</ol>
							</div>
						</div>
					</div>

					<div class="xy-lc-box" id="xy-lc-box" style="display: none">
						<div class="xy-ui-box-mask"></div>
						<div class="xy-lc-box-show" style="width:450px;height:275px;position: fixed!important;margin:-135px 0 -225px 0;left:35%;top:50%; ">
							<a class="xy-xbox-close" title="Close" href="javascript:void(0);" onClick="hid();" style="display: inline;">×</a>
							<div class="xy-lc-box-content" style="height:275px;">
								<h1>代偿还款</h1>
								<div class="xy-confirm-hd" style="padding-left:65px;">
									注：本次还款是先还代偿罚息，再还代偿金额
								</div>
								<span id="satory" hidden="true"></span>
								<span id="surplusMoney" hidden="true"></span>
								<div class="xy-ui-form-item-explain" style="display: none; background:none;padding-left:100px;"></div>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 还款金额：
								<input id="checkMoney" onkeyup="checkMoney();" class="xy-lc-input"><span class="xy-ft-unit">元</span>
								<br>
								<div class="xy-form-btn-box_mm">
									<a href="#" class="xy-form-btn-link"><input id="btn1" type="button" onclick="trueCheck()" value="确认还款" class="xy-form-btn-box-input"></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
<!--end: Container -->
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/user/loanTempContainer.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/CommonPerson.js"></script>



<#--js-render引用js文件-->
<script type="text/javascript" src="${base}/js/page/jquery.pagination-1.2.3.js"></script>
<script type="text/javascript" src="${base}/js/jsrender/jsrender.min.js"></script>

<!--回款中模版-->
<script id="page_tmp7" type="text/x-jsrender">
 <tr > 
					        	          <td style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
					        	          	<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidId}}" target="_blank" title="{{:proName}}"><span class="normal">{{:proName}}</span></a>
					        	          </td>
					        	          <td align="center"><span class="normal">{{:payintentPeriod}}</span></td> 
					        	          <td align="center"><span class="normal">{{:intentDate}}</span></td> 
					        	          <td align="center"><span class="normal">{{:intenttotal}}元</span></td>
					                   	  <td align="center"><span class="normal">{{:accMoney}}元</span></td> 
					                      <td align="center"><span class="normal">{{:repaymentTotal}}元</span></td> 
					                      <td align="center">
					                      	<a href="javascript:void(0);"  onClick="clickPlan({{:bidId}},'Loan')"><span class="normal">回款计划&nbsp;&nbsp;|</a></span></a>
					                      	<a href="{{isurl:url}}"><span class="normal"><span class="normal">&nbsp;&nbsp;合同</span></span></a>
					                      </td> 
					        	     	  <td align="center"> 
						        	     	  <span class="normal">
						        	     	  <a onClick="repayMentPay()" id="repayMentbtn"   href="${base}/pay/repayMentByLoanerPay.do?planId={{:bidId}}&ids=&peridId={{:payintentPeriod}}&notMoney={{:repaymentTotal}}" >立即还款</a>
					        	     	 <a href="javascript:void(0);" id="advanceLoanBtn"  onClick="advanceLoan({{:bidId}})" >&nbsp;|&nbsp;提前还款</a> &nbsp;|&nbsp;
					        	     	 <br><a  target="_Blank"  href="${base}/pay/autoRepaymentAuthorizationPay.do?planId={{:bidId}}&&actionStatus={{:authorizationStatus}}"><span class="normal">{{ationStatus:authorizationStatus}}</span></a> </span></td>
					        	     </tr>
					        	

</script>
<!--代偿列表模版-->
<script id="page_tmp5" type="text/x-jsrender">
	<tr > 
		<td style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:planId}}" target="_blank" title="{{:bidName}}"><span class="normal">{{:bidName}}</span></a>
		</td>
		<td align="center"><span>{{:payintentPeriod}}</span></td> 
		<td align="center"><span>{{:compensatoryDate}}</span></td>
		<td align="center"><span class="normal">{{:compensatoryMoney}}元</span></td> 
		<td align="center"><span class="normal">{{:punishMoney}}元</span></td> 
		<td align="center"><span class="normal">{{:backPunishMoney}}元</span></td> 
		<td align="center"><span class="normal">{{:backCompensatoryMoney}}元</span></td> 
		<td align="center"><span class="normal">{{:plateMoney}}元</span></td> 
		<td align="center"><span class="normal">{{:compensatoryDays}}天</span></td> 
		<td align="center"><span class="normal">{{:surplusMoney}}</span>元</td> 
		 <td align="center"> 
		 <span class="normal"> <a href="javascript:void(0);"  onClick="show({{:id}},{{:surplusMoney}});" >立即还款</a></span>
		</td>
	</tr>
					        	

</script>

<!--已完成-->
<script id="page_tmp10" type="text/x-jsrender">
 <tr bgcolor="#F3F3F3" >
			        	 	<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			        	 	<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidId}}" target="_blank" title="{{:proName}}"><span class="normal">{{:proName}}</span></a>
			        	 	</td>
			        	 	<td align="center"><span class="normal">{{:payMoney}}元</span></td>
			        	 	<td align="center"><span class="normal">{{:bidTime}}</span></td>
			        	 	<td align="center"><span class="normal">{{:loanLife}}</span></td>
			        	 	<td align="center"><span class="normal">{{:interestRate}}%</span></td>
			        	 	<td align="center"><span class="normal">{{:repaymentTotal}}元</span></td>
			        	 	<td align="center"><span class="normal">{{:repaymentDate}}</span></td>
			        	 	<td align="center">
			        	 		<a href="javascript:void(0);"  onClick="clickPlan({{:bidId}},'Repaymented')"><span class="normal">回款计划&nbsp;&nbsp;|</span></a>
			        	 		<a href="{{isurl:url}}" ><span class="normal">&nbsp;&nbsp;合同</span></a>
			        	 	</td>
			        	 	
			        	 	
			        	 </tr>

</script>


<!--招标中-->
<script id="page_tmp1" type="text/x-jsrender">
 <tr bgcolor="#F3F3F3" >
			        	 	<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			        	 	<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:bidId}}" target="_blank" title="{{:proName}}"><span class="normal">{{:proName}}</span></a>
			        	 	</td>
			        	 	<td align="center"><span class="normal">{{:payMoney}}元</span></td>
			        	 	<td align="center"><span class="normal">{{:bidTime}}</span></td>
			        	 	<td align="center"><span class="normal">{{:loanLife}}</span></td>
			        	 	<td align="center"><span class="normal">{{:interestRate}}%</span></td>
			        	 	
			        	 	
			        	 	
			        	 </tr>

</script>

<script type="text/javascript">
	function formatNum(num,n){debugger//参数说明：num 要格式化的数字 n 保留小数位
	    //num = String(num.toFixed(n));
	    var re = /(-?\d+)(\d{3})/;
	    while(re.test(num)) num = num.toString().replace(re,"$1,$2");
	    return num;
	}
	
	$.views.converters("formartDate", function(val,str) {  
	    return  formartDate(val,str);  
	});
	
	//还款授权 转换
	$.views.converters("ationStatus", function(var0) { 
		var ret="";
		if(var0==""||var0==null) ret="还款授权";
		if(var0=="1") ret="取消授权";
		  return  ret;  
	});
	
	$.views.converters("add", function(var0,var1) {  
	    return  var0+var1;  
	});
	
	$.views.converters("substring", function(v,l) {  
	    return  subString(v,l);  
	});
	
	$.views.converters("isurl", function(v) {  
		if(v==""||v==undefined){
			return "javascript:void(0)";
		}else{
			return "${base}/financePurchase/downLoadFinancePurchase.do?customerType=loan&contractUrl="+v;
		}
	});
	
	$(function () {
	    $("#pager7").empty();
		addPage(7);
		$("#5").show();
		$("#pager5").empty();
		addPage(5);
		showStatistic();
	});
    //查询统计数据	
	function showStatistic(){
	    var new_map0={age:2,start:0,limit:10};
	    ajaxFunction("${base}/user/loadAllLoanDataBpCustMember.do",new_map0,callback0_0);
	}	
    //获取我的借款的统计数据
	 function callback0_0(data){
	 	var result=data.result;
	 	if(result==null||result==''||result=='undefined'){
	 		
	 	}else{
		 	$("#dataShow").empty().html($("#temp").render({allLoanNums:result.allLoanNums,numsOnBiding:result.numsOnBiding,
		 													unBackNums:result.unBackNums,allLoanMoney:formatNum(result.allLoanMoney,2),
		 													moneyOnBiding:formatNum(result.moneyOnBiding,2),unBackMoney:formatNum(result.unBackMoney,2)}));//累计赚取收益
	 	}
	 }
	$.views.converters("selectX", function(val) { 
		var  vals="";
		if(val==null||val==''||val=='undefined'){
			return  vals=0.00;
		}else{
		    return  vals=val;  
		}  
	});
     function addPage(params){debugger;
       $("#pager"+params).empty();
       var url = '${base}/user/newLoanmanagementBpCustMember.do';
        if(params == 5){
       	  url = '${base}/compensatory/getCompensatoryListPlBidCompensatory.do';
       }else{
       	 url = url;
       }
		$("#pager"+params).page({
		    pageSize: 10,
		    showPrevious: true,
		    showPageNumbers: true,
    		showNext: true,
		    remote: {
		        url: url,  //请求地址
		        params: {"planstate":params},       //自定义请求参数
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
		if(($("#pager5").val()=='')){
		 //   document.getElementById('5').style.display="";
		}
	}
</script>

