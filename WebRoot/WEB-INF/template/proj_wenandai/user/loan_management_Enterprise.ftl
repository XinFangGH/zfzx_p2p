<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 企业借款管理</title>
    <meta name="description" content="${systemConfig.metaTitle} - 企业借款管理,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 企业借款管理,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript">var m1="个人中心",m2="借款管理",m3="借款管理";</script>
<script type="text/javascript">
$(function(){
	$.divselect(".divselectall");
});
	
</script>
<script type="text/javascript">
window.onload=function() {
	
	$(".contents .finlist_title li").each(function(index) { //带参数遍历各个选项卡
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
		$(this).click(function() { //注册每个选卡的单击事件
			$(".contents .finlist_title li.active").removeClass("active"); //移除已选中的样式
			$(this).addClass("active"); //增加当前选中项的样式
			//显示选项卡对应的内容并隐藏未被选中的内容
			$(".contents .finlist_hide ol:eq(" + index + ")").show()
			.siblings().hide();
		});
	});
	
}
</script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">


<div class="main-box">

<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
    	<!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
        <!-- 左侧三级导航结束 end-->
    <!-- 左侧页面 end -->
        <!--<div style="float:left; height:700px; padding:10px 0px; width:1px; border:0px; background-color:#e0e0e0;"></div>-->
    <!-- 右侧主体内容 start  右侧内容宽740px -->
     <div class="main-list"style="background:none;">
 		<div class="date-list">
        	<div class="date-left">
            	<dl>
                	<dt>借款总金额（元）</dt>
                    <dd><#if loanTotal==0>0<#else><#if loanTotal lt 1000>${loanTotal}<#else>${loanTotal}</#if></#if></dd>
                </dl>
            </div>
            <div class="date-right">
            	<dl>
                	<dt>逾期金额（元）</dt>
                    <dd><#if money1==0>0.00<#else><#if money1 lt 1000>${money1}<#else>${money1?string(',###.00')}</#if></#if></dd>
                </dl>
                <div class="line"></div>
                <dl>
                	<dt>待还金额（元）</dt>
                    <dd><#if money2==0>0.00<#else><#if money2 lt 1000>${money2}<#else>${money2?string(',###.00')}</#if></#if></dd>
                </dl>
                <div class="line"></div>
                <dl>
                	<dt>最近10天应还金额</dt>
                    <dd><#if money3==0>0.00<#else><#if money3 lt 1000>${money3}<#else>${money3?string(',###.00')}</#if></#if></dd>
                </dl>
                <!-- <div class="txts">您最近10天内有0笔借款需归还，总额<#if loanTotalTen==0>0.00<#else><#if loanTotalTen lt 1000>${loanTotalTen}<#else>${loanTotalTen?string(',###.00')}</#if></#if>元</div>-->
            </div>
        </div>
      <div class="contents">
      	<div class="con-ui-list">
        	<ul class="finlist_title">
                <!--  <li class="active">还款中借款</li>
                  <li>已还清借款</li>-->
                  <li class="<#if show=="Repayment">active<#else></#if>">未还款</li>
                  <li class="<#if show=="loan">active<#else></#if>">借款申请查询</li>
                  <li>已完成</li>
                  <li>还款授权</li>
                   <input type="hidden" id="show" value="${show}">
       		</ul>
        </div>
        <div class="finlist_hide">
        <ol class="curr">
        <div class="tab-list1">
           <ul>
					<form name="queryForm" id="queryForm" method="post" action="${base}/user/loanmanagementBpCustMember.do?toAction=Repayment">
						
								<li style="margin-top: 20px;height:40px;">
						<#-- <label><span class="black normal"style="float:left;padding-top:10px;">状态：</span></label>
							<div class="divselectall" style="float:left;">
						      <cite><span>全部</span>
			  				<input name="bpCustMember.collegeDegree" type="hidden" value="" /></cite>
						      <ul>
								 <li><a class="selt" href="javascript:;" selectid="1"><#if searchMap.get('notMoney')??><#if searchMap.get('notMoney')=='1'>selected</#if></#if>待还款</a></li>
						         <li><a class="selt" href="javascript:;" selectid="2"><#if searchMap.get('notMoney')??><#if searchMap.get('notMoney')=='2'>selected</#if></#if>已还款</a></li>

						      </ul>
						  </div>-->
							
							<label class="m-l_10"><span class="black normal">项目名称：</span></label>
							<input type="text" class="colorful1" style="width:100px"  name="Q_projectName_S_LK" id="keyWord" value="<#if searchMap??><#if searchMap.get('Q_projectName_S_LK')??>${searchMap.get('Q_projectName_S_LK')}</#if></#if>"/>
							<label class="f-l m-t_5"><span class="black normal">还款日：</span></label>
							<input type="text" id="purchase_time_start" style="width:80px" name="selectTime" class="colorful1" readonly="readonly" value="<#if searchMap??><#if searchMap.get('selectTime')??>${searchMap.get('selectTime')}</#if></#if>" onclick="new Calendar().show(this);"/>
							<label class="f-l m-t_5"><span class="black normal">到</span></label>
							<input type="text" id="purchase_time_start" style="width:80px" name="selectTime2" class="colorful1" readonly="readonly" value="<#if searchMap??><#if searchMap.get('selectTime2')??>${searchMap.get('selectTime2')}</#if></#if>" onclick="new Calendar().show(this);"/>
							
							<!--<a href="javascript:query();" class="query-btn"><span class="black white">查询</span></a> -->
								<input type="submit" style="padding:3px 5px;"id="btn" name="btn" class="buttonoblue4 white" value="查询" />
						</li>
						<li style="margin-top: 20px;">
							
						</li>
					
						<!--<li style="margin-top: 20px;float:left;height:40px;">
						<label><span class="black normal">  结果统计：借出总额￥<em class="font-red">0</em>元
						  应收本息总额￥<em class="font-red">0</em>元
 						  已收本金总额￥<em class="font-red">0</em>元
 						  收益利息总额￥<em class="font-red">0</em>元</span></label>
						</li>	-->
						<li>

							<table width="100%" class="tab_css_4"  border="0" cellpadding="0"  cellspacing="0">
								<tr>
									<th width="10%">借款名称</th>
									<th width="7%">还款期号</th>
									<th width="10%">应还款日期</th>
									<th width="7%">当期本金</th>
									<th width="7%">当期利息</th>
									<th width="6%">综合费用</th>
									<th width="10%">当前罚息</th>
									<th width="7%">已代偿金额</th>
									<!-- <th width="10%">已还金额</th>-->
									<th width="10%">当前应还总额</th>
									<th width="15%">查看</th>
									<th width="15%">操作</th>
								</tr>
								 <#if pager!=null>
								 <#list pager.list as list>
					        	     <tr bgcolor=<#if (list.fundType=='loanInterest')> "#F3F3F3"</#if> > 
					        	          <td style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
					        	          	<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.planId}" target="_blank" title="${list.projectName}"><span class="normal">${list.projectName}</span></a>
					        	          </td>
					        	          <td><span class="normal">第${list.payintentPeriod}期 </span></td> 
					        	          <td><span class="normal">${list.intentDate?string('yyyy-MM-dd')}</span></td> 
					        	          <td align="right"><span class="normal">¥${list.principalRepaymentMoney}</span></td>
					                   	  <td align="right"><span class="normal">¥${list.loanInterestMoney}</span></td> 
					                      <td align="right"><span class="normal">¥${list.comprehensiveMoney}</span></td> 
					                      <td align="right"><span class="normal">¥${list.punishInterestMoney}</span></td> 
					                      <td align="right"><span class="normal">¥<#if list.compensateMoney??>${list.compensateMoney}<#else>0</#if></span></td>
					                      <#-- <td align="right"><span class="normal">¥<#if list.afterMoney??><#if list.compensateMoney??>${list.afterMoney}-${list.compensateMoney}<#else>${list.afterMoney}</#if><#else>0</#if></span></td> -->
					                      <td align="right"><span class="normal">¥${list.notMoney}</span></td> 
					                      <td>
					                      	<a href="javascript:void(0);"  onClick="clickPlan(${list.planId},'Loan')"><span class="normal">回款计划&nbsp;&nbsp;|</a></span></a>
					                      	<a href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=loan&contractUrl=${list.url!}"><span class="normal"><span class="normal">&nbsp;&nbsp;合同</span></span></a>
					                      </td> 
					        	     	  <td  align="right"> 
						        	     	  <span class="normal"><a href="${base}/pay/repayMentByLoanerPay.do?planId=${list.planId}&ids=${list.ids}&peridId=${list.payintentPeriod}&notMoney=${list.notMoney}" target="_Blank" >立即还款</a>&nbsp;&nbsp;|&nbsp;&nbsp;
						        	     	<#-- <a href="javascript:void(0);" onClick="advanceLoan(${list.planId})" >提前还款</a>-->
					        	     	  </span></td>
					        	     	  <#--<a href="${base}/pay/autoRepaymentAuthorizationPay.do?planId=${list.planId}&actionStatus=<#if list.authorizationStatus==1>cancel<#else>authorization</#if>"  ><#if list.authorizationStatus==1>取消还款授权<#else>还款授权</#if></a> -->
					        	     </tr>
					        	</#list>
					        	</#if>
					        	
					        	<#-- <#if pager!=null>
								 <#list pager.list as list>
					        	     <tr bgcolor=<#if (list.fundType=='loanInterest')> "#F3F3F3"</#if> > 
					        	          <td style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
					        	          	<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.planId}" target="_blank" title="${list.bidPlanName}"><span class="normal">${list.bidPlanName}</span></a>
					        	          </td>
					        	          <td><span class="normal">第${list.payintentPeriod}期 </span></td> 
					        	          <td><span class="normal">${list.intentDate}</span></td> 
					        	          <td align="right"><span class="normal">¥${list.principalRepayment.incomeMoney}</span></td>
					                   	  <td align="right"><span class="normal">¥${list.loanInterest.incomeMoney}</span></td> 
					                      <td align="right"><span class="normal">¥${list.comprehensiveMoney}</span></td> 
					                      <td align="right"><span class="normal">¥${list.punishInterestMoney}</span></td> 
					                      <td align="right"><span class="normal">¥<#if list.compensateMoney??>${list.compensateMoney}<#else>0</#if></span></td> -->
					                      <#-- <td align="right"><span class="normal">¥<#if list.afterMoney??><#if list.compensateMoney??>${list.afterMoney}-${list.compensateMoney}<#else>${list.afterMoney}</#if><#else>0</#if></span></td> -->
					                      <#--<td align="right"><span class="normal">¥${list.allLoanMoney}</span></td> 
					                      <td>
					                      	<a href="javascript:void(0);"  onClick="clickPlan(${list.planId},'Loan')"><span class="normal">回款计划&nbsp;&nbsp;</a></span></a>
					                      	<a href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=loan&contractUrl=${list.url!}"><span class="normal"><span class="normal">&nbsp;&nbsp;合同</span></span></a>
					                      </td> 
					        	     	  <td  align="right"> 
						        	     	  <span class="normal"><a href="${base}/pay/repayMentByLoanerPay.do?planId=${list.planId}&ids=${list.ids}&peridId=${list.payintentPeriod}&notMoney=${list.notMoney}" target="_Blank" >立即还款</a>&nbsp;&nbsp;|&nbsp;&nbsp;
						        	     	 <a href="javascript:void(0);" onClick="advanceLoan(${list.planId})" >提前还款</a>
					        	     	  </span></td>-->
					        	     	  <#--<a href="${base}/pay/autoRepaymentAuthorizationPay.do?planId=${list.planId}&actionStatus=<#if list.authorizationStatus==1>cancel<#else>authorization</#if>"  ><#if list.authorizationStatus==1>取消还款授权<#else>还款授权</#if></a> -->
					        	     <#-- </tr>
					        	</#list>
					        	</#if>-->
	 						</table>
						<#import "/WEB-INF/template/common/pager.ftl" as p>
         			<#assign parameterMap = {"Q_plBiddingType.biddingTypeId_L_EQ":"" ,"Q_payIntersetWay_S_EQ":"" ,"payTime":"" ,"bidMoney":""} />
         			<@p.pager pager = pager baseUrl = "/user/loanmanagementBpCustMember.do?toAction=Repayment" parameterMap = parameterMap />
						</li>
						<li></li>
					</ul>
        </div>
        </ol>
        
        <!--借款申请查询开始 -->
         <ol>
        <div class="tab-list1">
            	<!--此处放置内容-->
					
					<table class="tab_css_4" id="tab_css_3" border="0" cellpadding="0"  cellspacing="0">
						<tr>
							<th  width="15%">序号</th>
							<th width="10%">借款产品</th>
							<th width="10%">借款标题</th>
							<th width="10%">借款金额</th>
							<th width="10%">年化利率</th>
							<th width="10%">借款期限</th>
							<th width="15%">申请时间</th>
							<th width="18%">申请/审核状态</th>
						</tr>
						<#list listApplyUser as loan>
						<tr>
							<td>${loan.loanId}</td>
							<td>${loan.productName}</td>
							<td>${loan.loanTitle}</td>
							<td>${loan.loanMoney}元</td>
							<td>${loan.expectAccrual}%</td>
							<td>${loan.loanTimeLen}个月</td>
							<td>${loan.createTime}</td>
							<td>
								<#if loan.state=='0'>
									<#if loan.finishStatus!=''>
										<a href="${base}/financePurchase/getNodeapplyUser.do?id=${loan.loanId}">资料填写中,请继续</a>
										<a href="javascript:if(confirm('提示：确定要终止审请吗?')){window.location.href='${base}/financePurchase/endApplyapplyUser.do?id=${loan.loanId}'}">终止申请</a>
									<#else>
										手机端申请，请到手机端完成<br/>
										<a href="javascript:if(confirm('提示：确定要终止审请吗?')){window.location.href='${base}/financePurchase/endApplyapplyUser.do?id=${loan.loanId}'}">终止申请</a>
									</#if>
								</#if>
								<#if loan.state=='1'>
									已提交申请，正在进行材料审核<br/>
									<a href="${base}/financePurchase/getNodeapplyUser.do?id=${loan.loanId}">修改资料</a>
								</#if>
								<#if loan.state=='2'||loan.state=='4'||loan.state=='5'>
									材料审核通过，信用审核中
								</#if>
								<#if loan.state=='3'>
									您的申请未通过
								</#if>
								<#if loan.state=='6'>
									已结束
								</#if>
								<#if loan.state=='7'><#--信用审核已通过，请上传最后材料进入发标流程,
									<a href="${base}/financePurchase/getNodeapplyUser.do?id=${loan.loanId}&state=7">点击上传</a>-->
									发标前审核，会有人员联系您
								</#if>
								<#if loan.state=='8'>
									发标审核中，请耐心等待
								</#if>
							</td>
							</tr>
						</#list>
				</table>
        </div>
        </ol>
        <!--借款申请查询结束 -->
        
        <!-- 已完成开始-->
         <ol>
        <div class="tab-list1">
           <ul>
				<li style="margin-top: 20px;"></li>
				<li>
					<table width="100%" class="tab_css_4"  border="0" cellpadding="0"  cellspacing="0">
						<tr>
							<th width="10%">借款名称</th>
							<th width="7%">金额</th>
							<th width="13%">起始日期</th>
							<th width="13%">到期日期</th>
							<th width="7%">期限</th>
							<th width="10%">年化利率</th>
							<th width="10%">完成时间</th>
							<th width="10%">查看</th>
						</tr>
			        	 <#list PlanLoan as list>
			        	 <tr bgcolor="#F3F3F3" >
			        	 	<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			        	 	<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" title="${list.bidProName}"><span class="normal">${list.bidProName}</span></a>
			        	 	</td>
			        	 	<td align="center"><span class="normal"><#if list.bidMoney==0>0.00<#else>${list.bidMoney!}</#if></span></td>
			        	 	<td align="center"><span class="normal">${list.publishSingeTime}</span></td>
			        	 	<td align="center"><span class="normal">${list.bidEndTime}</span></td>
			        	 	<td align="center"><span class="normal">${list.loanLife}</span></td>
			        	 	<td align="center"><span class="normal">${list.interestRate}%</span></td>
			        	 	<td align="center"><span class="normal">${list.nextTime }</span></td>
			        	 	<td align="center">
			        	 		<a href="javascript:void(0);"  onClick="clickPlan(${list.bidId},'Repaymented')"><span class="normal">回款计划&nbsp;&nbsp;|</span></a>
			        	 		<a href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=loan&contractUrl=${list.url!}"><span class="normal">&nbsp;&nbsp;合同</span></a>
			        	 	</td>
			        	 </tr>
			        	 </#list>
					</table>
				</li>
				<li></li>
			</ul>
        </div>
        </ol>
        <!-- 已完成结束-->
        
        <!-- 还款授权-->
         <ol>
        <div class="tab-list1">
           <ul>
				<li style="margin-top: 20px;"></li>
				<li>
					<table width="100%" class="tab_css_4"  border="0" cellpadding="0"  cellspacing="0">
						<tr>
							<th width="10%">借款名称</th>
							<th width="7%">金额</th>
							<th width="13%">起始日期</th>
							<th width="13%">到期日期</th>
							<th width="7%">期限</th>
							<th width="10%">年化利率</th>
							<th width="10%">还款授权</th>
							<th width="10%">查看</th>
						</tr>
			        	 <#list authorizationPlan as list>
			        	 <tr bgcolor="#F3F3F3" >
			        	 	<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
			        	 	<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" title="${list.bidProName}"><span class="normal">${list.bidProName}</span></a>
			        		</td>
			        	 	<td align="center"><span class="normal"><#if list.bidMoney==0>0.00<#else>${list.bidMoney!}</#if></span></td>
			        	 	<td align="center"><span class="normal">${list.publishSingeTime}</span></td>
			        	 	<td align="center"><span class="normal">${list.bidEndTime}</span></td>
			        	 	<td align="center"><span class="normal">${list.loanLife}</span></td>
			        	 	<td align="center"><span class="normal">${list.interestRate}%</span></td>
			        	 	<td align="center"><a  target="_Blank"  href="${base}/pay/autoRepaymentAuthorizationPay.do?planId=${list.bidId}&actionStatus=<#if list.authorizationStatus==1>cancel<#else>authorization</#if>" style="text-decoration:underline;"><span class="normal"><#if list.authorizationStatus==1>取消授权<#else>还款授权</#if></span></a></td>
			        	 	<td align="center">
			        	 		<a href="javascript:void(0);"  onClick="clickPlan(${list.bidId},'Financial')"><span class="normal">回款计划&nbsp;&nbsp;|</span></a>
			        	 		<a href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=loan&contractUrl=${list.url!}"><span class="normal">&nbsp;&nbsp;合同</span></a>
			        	 	</td>
			        	 </tr>
			        	 </#list>
					</table>
				</li>
				<li></li>
			</ul>
        </div>
        </ol>
        <!-- 还款授权完-->
        </div>
      </div>

    </div>					
</div>
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>