<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 还款管理</title>
    <meta name="description" content="${systemConfig.metaTitle} - 还款管理,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 还款管理,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>

<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript">var m1="个人中心",m2="个人主页",m3="还款列表";</script>
<style type="text/css">
dd {
display: block;
-webkit-margin-start: 40px;
}


dl {
display: block;
-webkit-margin-before: 1em;
-webkit-margin-after: 1em;
-webkit-margin-start: 0px;
-webkit-margin-end: 0px;
}

.query-btn {
margin-left: 10px;
color: #fff!important;
padding: 6px;
cursor: pointer;
border-radius: 3px;
text-decoration: none;
background: #e74649;
background: -moz-linear-gradient(top, #e74649 0%, #e03336 100%);
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#e74649), color-stop(100%,#e03336));
background: -webkit-linear-gradient(top, #e74649 0%,#e03336 100%);
background: -o-linear-gradient(top, #e74649 0%,#e03336 100%);
background: -ms-linear-gradient(top, #e74649 0%,#e03336 100%);
background: linear-gradient(to bottom, #e74649 0%,#e03336 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e74649', endColorstr='#e03336',GradientType=0 );
}


</style>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">


<div class="main-box">

<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
		<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">

    <!-- 右侧主体内容 start  右侧内容宽740px -->
    <div class="main0" style="width:1001px;margin:20px auto; ">
    <div class="ucrightdiv" style="width:981px;background:#fff;padding:10px; border:1px solid #dcdcdc;overflow:hidden; min-height:260px;margin-bottom:20px;">
    	<!-- 我的个人信息 start -->
        	<div style=" height:30px; line-height:30px;padding:20px 0px 0px 10px;">
            	<span class="blue large">还款管理</span>
            </div>
	         <div class="dashedline2"></div>
        	<div style=" padding:20px 10px; overflow:hidden;" class="account-info">
            	<!--此处放置内容-->
            	<ul>
					<form name="queryForm" id="queryForm" method="post" action="${base}/financePurchase/returnmoneyFinancePurchase.do">
						
								<li style="margin-top: 20px;height:40px;">
						<label><span class="black normal">状态：</span></label>
							<select name="notMoney" id="financ_status" onchange="query();" class="quanbu" style="height:26px; width:100px;">
								<option value="" >全部</option>
								 
								<option value="1" <#if searchMap.get('notMoney')??><#if searchMap.get('notMoney')=='1'>selected</#if></#if>>待还款</option>
								<option value="2" <#if searchMap.get('notMoney')??><#if searchMap.get('notMoney')=='2'>selected</#if></#if>>已还款</option>
								
							</select>
							<label class="m-l_10"><span class="black normal">项目名称：</span></label>
							<input type="text" class="colorful" style="width:100px"  name="Q_projectName_S_LK" id="keyWord" value="<#if searchMap??><#if searchMap.get('Q_projectName_S_LK')??>${searchMap.get('Q_projectName_S_LK')}</#if></#if>"/>
							<label class="f-l m-t_5"><span class="black normal">还款日：</span></label>
							<input type="text" id="purchase_time_start" style="width:80px" name="selectTime" class="colorful" readonly="readonly" value="<#if searchMap??><#if searchMap.get('selectTime')??>${searchMap.get('selectTime')}</#if></#if>" onclick="new Calendar().show(this);"/>
							<label class="f-l m-t_5"><span class="black normal">到</span></label>
							<input type="text" id="purchase_time_start" style="width:80px" name="selectTime2" class="colorful" readonly="readonly" value="<#if searchMap??><#if searchMap.get('selectTime2')??>${searchMap.get('selectTime2')}</#if></#if>" onclick="new Calendar().show(this);"/>
							
							<!--<a href="javascript:query();" class="query-btn"><span class="black white">查询</span></a> -->
								<input type="submit" id="btn" name="btn" class="query-btn white" style="background:#0096c4;"value="查询" />
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

							<table class="tab_css_3" border="0" cellpadding="0"  cellspacing="0">
								<tr>
									<th width="20%">借款名称</th>
									<th width="10%">还款期号</th>
									<th width="10%">应还款日期</th>
									<th width="10%">当期本金(元)</th>
									<th width="10%">当期利息(元)</th>
									<th width="10%">综合费用</th>
									<th width="10%">当前罚息</th>
									<th width="10%">当前应还总额</th>
									<th width="10%">还款</th>
								</tr>
								
								 <#list pager.list as list>
					        	     <tr bgcolor=<#if (list.fundType=='loanInterest')> "#F3F3F3"</#if> > 
					        	          <td style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
					        	          	<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.planId}" target="_blank" title="${list.projectName}"><span class="normal">${list.projectName}</span></a>
					        	          </td>
					        	          <td><span class="normal">第${list.payintentPeriod}期 </span></td> 
					        	          <td><span class="normal">${list.intentDate}</span></td> 
					        	          <td align="right"><span class="normal">¥${list.principalRepaymentMoney}</span></td>
					                   	  <td align="right"><span class="normal">¥${list.loanInterestMoney}</span></td> 
					                      <td align="right"><span class="normal">¥${list.comprehensiveMoney}</span></td> 
					                      <td align="right"><span class="normal">¥${list.punishInterestMoney}</span></td> 
					                      <td align="right"><span class="normal">¥${list.notMoney}</span></td> 
					                      <td><a href="${base}/pay/repayMentByLoanerPay.do?planId=${list.planId}&ids=${list.ids}&peridId=${list.payintentPeriod}&notMoney=${list.notMoney}"><span class="normal">立即还款</span></a></td> 
					        	     </tr>
					        	</#list>
	 						</table>

						</li>
						<li>
						
                   <#import "/WEB-INF/template/common/pager.ftl" as p>
         			<#assign parameterMap = {} />
         			<@p.pager pager = pager baseUrl = "/financePurchase/returnmoneyFinancePurchase.do"  parameterMap = parameterMap />
         			
						</li>
					</ul>

				</div>
			</div>
		</div>
		</div>
</div>
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>