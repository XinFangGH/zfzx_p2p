<div class="topnav">
	<ul>
		<#--<li <#if highlight==1>class="current"</#if>><a href="${base}/user/getBpCustMember.do">账户总览</a><em class="user-arrow"></em></li>-->
		<#--<li <#if highlight==2>class="current"</#if>><a href="${base}/financePurchase/myFinancePurchase.do">我的散标</a><em class="user-arrow"></em></li>-->
		<#--<li <#if highlight==3>class="current"</#if>><a href="${base}/creditFlow/financingAgency/canTransferingListPlBidSale.do">债权交易</a><em class="user-arrow"></em></li>-->
		<#--<li <#if highlight==4>class="current"</#if>><a href="${base}/creditFlow/financingAgency/managelist1PlManageMoneyPlanBuyinfo.do?Q_state_SN_EQ=1&show=buyingList">我的理财</a><em class="user-arrow"></em></li>-->
		<#--<li <#if highlight==5>class="current"</#if>><a href="${base}/financeProduct/personFianceAccountPlFinanceProduct.do">活期理财</a><em class="user-arrow"></em></li>-->
		<#--<li <#if highlight==6>class="current"</#if>><a href="${base}/user/loadTemplateBpCustMember.do?toAction=Repayment">我的借款</a><em class="user-arrow"></em></li>-->
		<#--<li><a href="${base}/user/getFinancialBpCustMember.do">我的债权</a><em class="user-arrow"></em></li>-->
		<#if isGarantee ==null || isGarantee =="">
		<li><a href="${base}/user/getBpCustMember.do">账户总览</a><em class="user-arrow"></em></li>
	    <li><a href="${base}/financePurchase/myFinancePurchase.do">我的散标</a><em class="user-arrow"></em></li>
	    <#--<li><a href="${base}/creditFlow/financingAgency/loadTemplatePlBidSale.do">债权交易</a><em class="user-arrow"></em></li>-->
	    <li <#if highlight==10>class="current"</#if>><a href="${base}/creditFlow/financingAgency/loadTemplatePlBidSale.do">债权交易</a><em class="user-arrow"></em></li>
	   <#-- <li <#if highlight==11>class="current"</#if>><a href="${base}/creditFlow/financingAgency/loadTemplateVipPlBidSale.do">Vip债权交易</a><em class="user-arrow"></em></li>-->
	    <!--<li><a href="${base}/creditFlow/financingAgency/managelist1PlManageMoneyPlanBuyinfo.do?Q_state_SN_EQ=1&show=buyingList">我的理财</a><em class="user-arrow"></em></li>-->
	    <!--<li><a href="${base}/financeProduct/personFianceAccount1PlFinanceProduct.do">活期理财</a><em class="user-arrow"></em></li>-->
	    <li><a href="${base}/user/loadTemplateBpCustMember.do?toAction=Repayment">我的借款</a><em class="user-arrow"></em></li>
	    <!--<li><a href="${base}/user/loadMySaleTemplateBpCustMember.do">我的债权</a><em class="user-arrow"></em></li>-->
	    <li><a href="${base}/user/myCouponsBpCustMember.do?safe=all">我的优惠券</a><em class="user-arrow"></em></li>
	   <#-- <li><a href="${base}/user/myIntegralBpCustMember.do">我的积分</a><em class="user-arrow"></em></li>-->
	    <li><a href="${base}/user/myInviteBpCustMember.do">我的邀请</a><em class="user-arrow"></em></li>
	    <#else>
	    <li><a href="${base}/user/myGuaranteeBpCustMember.do">我的担保</a><em class="user-arrow"></em></li>
	    </#if>
	    <#-- <li><a href="${base}/user/financialreturnBpCustMember.do">理财回款</a></li>-->
	    <li><a href="${base}/message/getUserAllOaNewsMessage.do">我的消息</a><em class="user-arrow"></em></li>
	    <li><a href="${base}/user/safeBpCustMember.do?safe=all">账户设置</a><em class="user-arrow"></em></li>
	    <li><a href="${base}/financePurchase/detailFinancePurchase.do">资金明细</a><em class="user-arrow"></em></li>
	    <!--<li><a href="${base}/user/checkMaterialBpCustMember.do">认证资料</a><em class="user-arrow"></em></li>-->
	    <#-- <li><a href="${base}/user/mbIntroBpCustMember.do">VIP会员</a></li>-->
	    <!--<li><a href="${base}/html/vipSingle.do">债权计划</a></li>-->
	</ul>
</div>