<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title>${systemConfig.metaTitle} - 企业个人信息</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/uploadImage.js"></script>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript">var m1="个人中心",m2="稳安总览",m3="";</script>
<script type="text/javascript">	
$(function(){

	//手机，邮箱等认证提示	  	
	var content = '  <#if bpCustMember.isCheckPhone==1>手机已验证<br/><span>${bpCustMember.telphone}</span><#else>未手机验证</#if> ';	
	$('.phone1').pt({
		position: 'b',
		width:120,
		content: content
	});	
		  	
	var content = '<#if bpCustMember.isCheckEmail==1>邮箱已验证<br/><span>${bpCustMember.email}</span><#else>未邮箱验证</#if> ';	
	$('.phone2').pt({
		position: 'b',
		width:150,
		content: content
	});
	var content = '<#if bpCustMember.isCheckCard==1>实名已验证<br/><span>${bpCustMember.truename}</span><#else>未实名验证</#if>';	
	$('.phone3').pt({
		position: 'b',
		width:120,
		content: content
	});
	
	var content = '<#if bpCustMember.thirdPayFlag0!=null>第三方支付账号<br/><span>${bpCustMember.thirdPayFlag0}</span><#else>未第三方验证</#if>';	
	$('.phone4').pt({
		position: 'b',
		width:150,
		content: content
	});
		//图标下拉框  提示框
	var content = $(".sidnext").html();	
	$('.icon-ww').pt({
		position: 'b',
		width:300,
		content: content
	});	  
});

</script>
</head>
<body>
<!-- header --><!-- navbar -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<!--<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">-->

<div class="main-box">
    <!-- 以下内容为左侧三级导航条 -->
		<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
        <!-- 左侧三级导航结束 end-->
    <div class="main" style="wiadth:1001px; margin:10px auto; overflow:hidden;">
    <#if bpCustMember.thirdPayFlag0!=null><#else>
   	  <div class="inform">
       您的账户尚未开通资金托管账户<span class="icon-ww"> <img src="${base}/theme/${systemConfig.theme}/images/uc/icon-bg1.png" width="15" height="15" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;，目前不能进行理财或借款，<a href="${base}/thirdreg.do">点击立即开通</a>
       <div class="sidnext">资金托管账户是理财人和借款人分别在资金托管机构（${payType}支付）开立的独立账户，资金由理财人和借款人直接对接，避免了资金被平台挪用的风险。</div>
    </div>
    
    
    </#if>
    <#assign sign=0>
    <#list listApplyUser as loan>
    
    <#if loan.state=='0'||loan.state=='1'>
    	<div class="inform wad_inform" style="background: #e6f4fd;border: 1px solid #eee;">您还有一笔借款正在申请中，<a href="${base}/financePurchase/getNodeapplyUser.do?id=${loan.loanId}">点击完成申请</a></div>
    	<#assign sign=1>
    </#if>
    <#--
	<#if loan.state=='7'>
		<div class="inform wad_inform" style="background: #e6f4fd;border: 1px solid #eee;"><a href="${base}/financePurchase/getNodeapplyUser.do?id=${loan.loanId}&state=7">上传补充材料</a></div>
	</#if>
	-->
    </#list>
    
      <div class="content">
      	<!--<div class="counter">
        	<ul class="ui-list">
            	<li>
                	<span class="icon"></span>
                    <span class="date"><#if InvestLoanMoneytotal==null>0.00<#else><#if InvestLoanMoneytotal lt 1000>${InvestLoanMoneytotal+bpCustMember.freezeMoney}<#else>${InvestLoanMoneytotal+bpCustMember.freezeMoney}</#if></#if><em>资产总额（元）</em></span>
                    <span class="icon1"></span>
                </li>
                <li><span class="sign">=</span></li>
                <li>
                    <span class="date1">
                    <#if bpCustMember.availableInvestMoney==0>0.00<#else><#if bpCustMember.availableInvestMoney lt 1000>${bpCustMember.availableInvestMoney+bpCustMember.freezeMoney}<#else>${bpCustMember.availableInvestMoney+bpCustMember.freezeMoney}</#if></#if><em>资金余额（元）</em></span>
                </li>
                <li><span class="sign1">+</span></li>
                 <li>
                    <span class="date1"><#if investMoney==0>0.00<#else><#if investMoney lt 1000>${investMoney}<#else>${investMoney?string(',###.00')}</#if></#if><em>投资金额（元）</em></span>
                </li>
                <li><span class="sign1">-</span></li>
                 <li>
                    <span class="date1"><#if loanTotal??><#if loanTotal==0>0.00<#else><#if loanTotal lt 1000>${loanTotal}<#else>${loanTotal?string(',###.00')}</#if></#if><#else>0.00</#if><em>借款金额（元）</em></span>
                </li>
                
            </ul>
        </div>-->
        <div class="div-list">
        	<div class="div-list-one">
            	<h2>资金信息</h2>
                <ul class="ui-box">
                	<li>可用金额：<#if bpCustMember.availableInvestMoney==0>0.00<#else><#if bpCustMember.availableInvestMoney lt 1000>${bpCustMember.availableInvestMoney}<#else>${bpCustMember.availableInvestMoney?string(',###.00')}</#if></#if>元</li>
                    <li>冻结金额：<#if bpCustMember.freezeMoney==0>0.00<#else>${bpCustMember.freezeMoney?string(',###.00')}</#if>元</li>
                    <li>待收总额：<#if bpCustMember.principal=null>0.00<#else><#if bpCustMember.principal==0>0.00<#else><#if bpCustMember.principal lt 1000>${bpCustMember.principal?number+bpCustMember.accrual?number}<#else>${(bpCustMember.principal?number+bpCustMember.accrual?number)?string(',###.00')}</#if></#if></#if>元</li>
                    <li>预期收益：<#if bpCustMember.accrual=null>0.00<#else><#if bpCustMember.accrual==0>0.00<#else><#if bpCustMember.accrual lt 1000>${bpCustMember.accrual}<#else>${bpCustMember.accrual?string(',###.00')}</#if></#if></#if>元</li>
                </ul>
            </div>
          <div class="div-list-one div-list-two">
            	<h2>收益信息</h2>
                <ul class="ui-box">
                	<li>累计收益：<#if bpCustMember.allInterest==0>0.00<#else><#if bpCustMember.allInterest lt 1000>${bpCustMember.allInterest}<#else>${bpCustMember.allInterest?string(',###.00')}</#if></#if>元</li>
                    <li>回款计划：<#if bpCustMember.paymentPlanNum??>${(bpCustMember.paymentPlanNum)!}<#else>0</#if>笔</li>
                    <li>上月收益：<#if bpCustMember.monthIncome=null>0.00<#else><#if bpCustMember.monthIncome==0>0.00<#else><#if bpCustMember.monthIncome lt 1000>${bpCustMember.monthIncome}<#else>${bpCustMember.monthIncome?string(',###.00')}</#if></#if></#if>元</li>
                    <li>当年收益：<#if bpCustMember.yearIncome=null>0.00<#else><#if bpCustMember.yearIncome==0>0.00<#else><#if bpCustMember.yearIncome lt 1000>${bpCustMember.yearIncome}<#else>${bpCustMember.yearIncome?string(',###.00')}</#if></#if></#if>元</li>
                </ul>
            </div>
          <div class="div-list-one div-list-three">
            	<h2>交易信息</h2>
                <ul class="ui-box">
                	<li>累计投资额：<#if bpCustMember.totalInvestMoney==0>0.00<#else><#if bpCustMember.totalInvestMoney lt 1000>${bpCustMember.totalInvestMoney}<#else>${bpCustMember.totalInvestMoney?string(',###.00')}</#if></#if>元</li>
                    <li>累计投资笔数：<#if bpCustMember.investmentNumber??>${(bpCustMember.investmentNumber)!}<#else>0</#if>笔</li>
                    <li>回款中：<#if bpCustMember.investmentBackNum??>${(bpCustMember.investmentBackNum)!}<#else>0</#if>笔</li>
                    <li>投标中：<#if bpCustMember.investmentBidNum??>${(bpCustMember.investmentBidNum)!}<#else>0</#if>笔</li>
                </ul>
          </div>
        </div>
        <!-- start 列表字段不正确，需重新做-->
        <div class="tab-list">
        	<h3> <a href="${base}/financePurchase/myFinancePurchase.do">更多...</a>理财</h3>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <th class="top-line left-line" align="center" width="33%" >产品</th>
                  <th class="top-line" align="center" width="15%">时间</th>
                  <th class="top-line" align="center"  width="15%">金额</th>
                  <th class="top-line" align="center"  width="10%">期限</th>
                  <th class="top-line" align="center"  width="15%">年化利率</th>
                  <th class="top-line right-line" align="center" width="35%">待回款</th>
                </tr>
                <#list   bidPlanFinancial as list>
         			<#if (list_index<5)>
                <tr class="ff">
                  <td align="left" class="td-top">
                  <span class="tit"><a style="color:#0096c4;font-size:16px;"href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}">${(list.bidProName)!}</span>
                  <span class="small">利率${(list.interestRate)!}%   期限 ${(list.loanLife)!}</span></a></td>
                  <td align="center">${(list.investmentTime)!}</td>
                  <td align="center"><#if list.afterMoneyTotal==null>0.00<#else><#if list.afterMoneyTotal==0>0.00<#else>${list.afterMoneyTotal?string(',##0.00')}</#if></#if></td>
                  <td align="center">${(list.loanLife)!}</td>
                  <td align="center">${(list.interestRate)!}% </td>
                  <td align="center" ><span style="display:inline-block;float:left;padding-left:20px;"><#if list.notMoneyTotal==null>0.00<#else><#if list.notMoneyTotal==0>0.00<#else>${list.notMoneyTotal?string(',##0.00')}</#if></#if></span>
                  <span style="display:inline-block;float:right;"><a href="javascript:void(0);"  onClick="clickPlan(${list.bidId},'PlanbackingOne')" class="btn1">回款计划</a>
                  <a href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=invest&contractUrl=${list.url!}" class="btn2">合同</a><span class="last"></span></span> </td>
                </tr>
                	</#if>
        		</#list>
                
          </table>
        </div>
          <div class="tab-list">
        	<h3> <a href="${base}/user/loanmanagementBpCustMember.do?toAction=Repayment">更多...</a>借款</h3>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <th class="top-line left-line" align="center" >名称</th>
                  <th class="top-line" align="center" width="15%">总额</th>
                  <th class="top-line" align="center"  width="10%">还款进度</th>
                  <th class="top-line" align="center"  width="15%">下期时间</th>
                  <th class="top-line" align="center"  width="10%">下期应还</th>
                  <th class="top-line right-line" align="center" width="25%">状态</th>
                </tr>
                <#list bidPlanLoan as list>
         			<#if (list_index<5)>
         			<#if list.loanMoney!=0>
                <tr class="ff">
                  <td align="left" class="td-top">
                   <span class="tit1"><a style="color:#0096c4;font-size:16px;"href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}">${list.bidProName!}</a></span></td>
                  <td align="center"><#if list.loanMoney==0>0.00<#else>${list.loanMoney}</#if></td>
                  <td align="center">${(list.repayMentLength)!}</td>
                  <td align="center"><#if list.nextRepaymentDate!=null>${list.nextRepaymentDate}<#else>-- --</#if></td>
                  <td align="center"><#if list.nextMoney!=null><#if list.nextMoney==0>0.00<#else>${list.nextMoney?string(',##0.00')}</#if><#else>0.00</#if></td>
                  <td>&nbsp;&nbsp;&nbsp;&nbsp;<#if list.isDate==0>未逾期<#else>已逾期</#if>
                  <span class="last"></span><a href="${base}/user/loanmanagementBpCustMember.do?toAction=Repayment" class="btn1 btn3">去还款</a></td>
                </tr>
                </#if>
                	</#if>
        		</#list>
                
          </table>
        </div>
            <!-- end 列表字段不正确，需重新做-->
      </div>
    </div>
</div>
<!--end: Container -->
       <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
       	
	</body>
</html>