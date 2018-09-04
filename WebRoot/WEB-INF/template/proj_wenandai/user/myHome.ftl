		<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title>${systemConfig.metaTitle} - 账户总览</title>
    <meta name="description" content="${systemConfig.metaTitle} - 账户总览,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 账户总览,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/detailsFunds.js"></script>
<script type="text/javascript" src="${base}/js/user/uploadImage.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript" src="${base}/js/user/mmplan.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/CommonPerson.js"></script>
<script type="text/javascript">var m1="个人中心",m2="账户总览",m3="";</script>
<script type="text/javascript">
$(function(){
	$('.tab-list-all li').click(function(){ 
	$(this).addClass("selected").siblings().removeClass('selected');
	$(".div-list .div-list-one").hide().eq($('.tab-list-all li').index(this)).show(); 
	});
	
	$('.tab-title ul li').click(function(){ 
	$(this).addClass("selected").siblings().removeClass('selected');
	$("#tab-content .tb-c").hide().eq($('.tab-title ul li').index(this)).show(); 
	});

	$('.tab-titles ul li').click(function(){ 
	$(this).addClass("selected").siblings().removeClass('selected');
	$("#tab-contents .tb-c").hide().eq($('.tab-titles ul li').index(this)).show(); 
	});
})
function plan_more(plan){
	if(plan=="plan_one"){
		$(".plan_two").css("display","none");
		$(".plan_one").css("display","block");
	}
	if(plan=="plan_two"){
		$(".plan_two").css("display","block");
		$(".plan_one").css("display","none");
	}
}
</script>
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
        <div class="user-money">
		   	<div class="user-money-all fl">
		   		<div class="icon-bg fl">
		   			<span class="user-money-icon"></span>
		   		</div>
		   		<div class="user-data-all fr">
		   			<p class="normal">账户可用余额(元)</p>
		   			<p class="num middle"><em><#if bpCustMember!=null><#if bpCustMember.availableInvestMoney==0>0.00<#else><#if bpCustMember.availableInvestMoney lt 1000>${bpCustMember.availableInvestMoney}<#else>${bpCustMember.availableInvestMoney?string(',###.00')}</#if></#if><#else>0.00</#if></em>元</p>
		   			<p class="btn">
			        	<a href="${base}/financePurchase/rechargeFinancePurchase.do?retUrl=user/getBpCustMember.do" >充 值</a>
			            <a href="${base}/financePurchase/withdrawFinancePurchase.do?retUrl=user/getBpCustMember.do" >提 现</a>
			            <#--<a href="#" onclick="checkSession()" >富滇账户余额同步</a>  -->
			            <a href="${base}/financePurchase/getBindBankListFinancePurchase.do">银行卡</a>
			        </p>
		   		</div>
		   	</div>
		   	<div class="user-money-fr fr">
		   		<ul>
		   			<li><span><i class="my-icon01"></i>优惠券(张)</span> <em><#if coupons!=null><#if coupons!=0>${coupons}<#else>0</#if><#else>0</#if></em></li>
		   			<li><span><i class="my-icon02"></i>当前积分</span> <em><#if bpCustMember!=null><#if bpCustMember.score==null||bpCustMember.score==0 >0<#else><#if bpCustMember.score lt 1000>${bpCustMember.score}<#else>${bpCustMember.score?string(',###.00')}</#if></#if><#else>0</#if></em></li>
		   			<li><span><i class="my-icon03"></i>未读消息(条)</span><em><#if notRead!=null><#if notRead!=0>${notRead}<#else>0</#if><#else>0</#if></em></li>
		   		</ul>
		   		<#if bpCustMember.thirdPayFlagId!=null>
    	<#if bpCustMember.thirdPayStatus==1 && bpCustMember.customerType==1>
    		<!--<div class="inform">
       您的账户尚未激活资金托管账户<span class="icon-ww">  </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;，目前不能进行理财或借款，<a href="${base}/user/offerMaterialBpCustMember.do">查看激活材料</a>
       <div class="sidnext" style="display:none;">资金托管账户是企业客户和担保客户类型，在开通后需向易宝支付相关材料进行审核以此来激活资金托管账户。</div>
    </div>-->
    	<#else>
    	</#if>
    <#else>
   	 <!-- <div class="inform">
       您的账户尚未开通资金托管账户<span class="icon-ww"> <img src="${base}/theme/${systemConfig.theme}/images/uc/icon-bg1.png" width="15" height="15" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;，目前不能进行理财或借款，<a href="${base}/thirdreg.do">点击立即开通</a>
       <div class="sidnext" style="display:none;">资金托管账户是理财人和借款人分别在资金托管机构（${payType}支付）开立的独立账户，资金由理财人和借款人直接对接，避免了资金被平台挪用的风险。</div>
    </div>-->
    	
    
    </#if>
    <#assign sign=0>
    <#list listApplyUser as loan>
    
    <#if loan.state=='0'>
    	<div class="informs wad_inform"><span class="dians"></span><span>您还有一笔借款正在申请中，<a href="${base}/loan/getNodeP2pLoanProduct.do?productId=${loan.productId}">点击完成申请。</a></span></div>
    	<#assign sign=1>
    </#if>
    <#if loan.state=='3'>
	<div class="informs wad_inform"><span class="dians"></span><span>您有一笔借款已打回，<a href="${base}/loan/getNodeP2pLoanProduct.do?productId=${loan.productId}">点击完善信息。</a></span></div>
	<#assign sign=1>
</#if>
    <#--
	<#if loan.state=='7'>
		<div class="inform wad_inform" style="background: #e6f4fd;border: 1px solid #eee;"><a href="${base}/financePurchase/getNodeapplyUser.do?id=${loan.loanId}&state=7">上传补充材料</a></div>
	</#if>
	-->
    </#list> 
		   	</div>
		</div>
        <div class="div-list">
        	<ul class="tab-list-all">
	        	<li class="selected">资金信息</li>
	            <li>交易信息</li>
	            <li>收益信息</li>
	            <li>资金明细</li>
            </ul>
        	<div class="div-list-one">
        		<div class="user-list-all fl">
			   		<div class="icon-bg-one fl">
			   			<span class="user-money-icon"></span>
			   		</div>
			   		<div class="user-data-list fl">
			   			<p class="normal"><span class="my_span">账户可用余额(元)</span><!--<em class="icon-w"></em>--></p>
			   			<p class="num middle"  ><em><#if bpCustMember!=null><#if bpCustMember.availableInvestMoney==0>0.00<#else><#if bpCustMember.availableInvestMoney lt 1000>${bpCustMember.availableInvestMoney}<#else>${bpCustMember.availableInvestMoney?string(',###.00')}</#if></#if><#else>0.00</#if></em>元</p>
			   		</div>
		   		</div>
               <ul class="ul-box fr">
                    <li>
                    	<p class="normal"><span>投标冻结金额(元)</span><!--<em></em>--></p>
                    	<p class="f18" id="zj02">0.00</p>
                    </li> 
                    <li>
                    	<p><span>待收投资总额(元)</span><!--<em></em>--></p>
                    	<p class="f18" id="zj03">0.00</p>
                    </li>
                    <li>
                    	<p><span>待还借款总额(元)</span><!--<em></em>--></p>
                    	<p class="f18" id="zj04">0.00</p>
                    </li>
                    <li>
	                    <p><span>账户净资产(元)</span><!--<em></em>--></p>
	                    <p class="f18" id="zj05">0.00</p>
                    </li>
                </ul>
            </div>
            <div class="div-list-one none">
            	<div class="user-list-all fl">
			   		<div class="icon-bg-one fl">
			   			<span class="user-money-icon"></span>
			   		</div>
			   		<div class="user-data-list fl">
			   			<p class="normal"><span>累计投资总额(元)</span><!--<em class="icon-w"></em>--></p>
			   			<p class="num middle" id="jy01"><em>0.00</em>元</p>
			   		</div>
		   		</div>
                <ul class="ul-box fr">
                    <li>
	                    <p><span>投标冻结中笔数(笔)</span><!--<em></em>--></p>
	                    <p class="f18" id="jy02">0</p>
                    </li>
                    <li>
	                    <p><span>待回款投资笔数(笔)</span><!--<em></em>--></p>
	                    <p class="f18" id="jy03">0</p>
                    </li>
                    <li>
                    	<p><span>招标中借款笔数(笔)</span><!--<em></em>--></p>
                    	<p class="f18" id="jy04">0</p>
                    </li>
                    <li>
                    	<p><span>还款中借款笔数(笔)</span><!--<em></em>--></p>
                    	<p class="f18" id="jy05">0</p>
                    </li>
                </ul>
           </div>
           <div class="div-list-one none">
               <div class="user-list-all fl">
			   		<div class="icon-bg-one fl">
			   			<span class="user-money-icon"></span>
			   		</div>
			   		<div class="user-data-list fl">
			   			<p class="normal"><span>累计到账收益(元)</span><!--<em class="icon-w"></em>--></p>
			   			<p class="num middle" id="sy01""><em>0.00</em>元</p>
			   		</div>
		   		</div>
               <ul class="ul-box fr">
                    <li><p><span>累计回收本金(元)</span><!--<em></em>--></p><p class="f18"id="sy02">0.00</p></li>
                    <li><p><span>30天内收益(元)</span><!--<em></em>--></p><p class="f18"id="sy03">0.00</p></li>
                    <li><p><span>预期待收收益(元)</span><!--<em></em>--></p><p class="f18"id="sy04">0.00</p></li>
                    <li><p><span>累计收益率(%)</span><!--<em></em>--></p><p class="f18"id="sy05">0.00</p></li>
                </ul>
            </div>
            <div class="div-list-one none">
               <div class="worm-tip" style="margin:10px 0; overflow:hidden;">
               <a class="more fr" href="${base}/financePurchase/detailFinancePurchase.do" style="color:#fd7754">更多>></a>            	<#-- <span>交易类型：</span> 
            	 <span>
            	  <select id="transferType"> 
            	   <option value="">全部</option>
            	   <#list obSystemaccountSetting as list>
            	 	<option value="${list.typeKey}">${list.typeName}</option>	
            	   </#list>
            	  </select>
            	  </span>-->
            	<#-- <span>&nbsp;&nbsp;<label class="f-l m-t_5"><span class="black normal">交易日期：</span></label>
					<input type="text" id="startDate" style="width:110px" name="bidtime" class="colorful" readonly="readonly" value="${startDate}" onclick="new Calendar().show(this);"/>
					<label class="f-l m-t_5"><span class="black normal">-</span></label>
					<input type="text" id="endDate" style="width:110px" name="bidtime2" class="colorful" readonly="readonly" value="${endDate}" onclick="new Calendar().show(this);"/>
            	 </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	  	<span>是否支付成功：</span> 
            	 <span>
            	  <select style="width:80px;height:28px;" id="isAccountSuccess"> 
            	   <option value="">全部</option>
            	 	<option value="1">是</option>
            	 	<option value="2">否</option>		
            	  </select></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	 <span class="buttonorange" style="font-size:14px; padding:4px 15px;" onclick="queryFinanceDetailList()"  name="queryBtn">查&nbsp;询</span>--> 
	              </div> 
	              	<div class="cont-list " >
	                <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
	                    <tr>
	                      <th align="center" width="25%">交易流水号</th>
	                      <th align="center" width="15%" >交易时间</th>
	                      <th align="center" width="10%">交易类型</th>
	                      <th align="center"  width="15%">收入金额（元）</th>
	                      <th align="center"  width="15%">支出金额（元）</th>
	                      <th align="center" width="10%">交易状态</th>
	                      <th align="center" width="10%">操作</th>
	                    </tr>
	                     <#list pager.list as list>
	                     <#if list_index<5 >
	                    <tr class="ff">
	                      <td align="center">
		                      <span class="tit">
		                      <#if list.recordNumber??>${list.recordNumber}<#else>----</#if>
		                      </span>
	                      </td>
	                     <td align="center">
	                    <#--  <#if list.transferDate??>
		                      	${list.transferDate?string("yyyy-MM-dd HH:mm:ss")}--><!-- 这条记录交易时间（交易成功）-->
		                <#--  <#else>
		                      	${list.createDate?string("yyyy-MM-dd HH:mm:ss")}--><!-- 这条记录创建时间（交易失败）-->
		                <#--  </#if>-->
		                       ${list.orderDate?string("yyyy-MM-dd HH:mm:ss")}
		                 </td>
						<td align="center"><#if list.transferTypeName??>${list.transferTypeName}<#else>----</#if></td>
	                      <td align="center"><#if list.incomMoney??>${list.incomMoney?string(',##0.00')}<#else>0.00</#if></td>
	                      <td align="center"><#if list.payMoney??>${list.payMoney?string(',##0.00')}<#else>0.00</#if></td>
	                      <td align="center" >
	                       ${list.msg} 
	                      </td>
	                      <td align="center" >
	                       <#if (list.dealRecordStatus==1&&list.transferType<3)>
	                       	<#--<a style='' href='${base}/pay/reRechargePay.do?loginId=${list.id}&retUrl=/financePurchase/detailFinancePurchase.do'>重新支付</a>-->
	                       	<a href='${base}/pay/cancelRechargePay.do?loginId=${list.id}&retUrl=/financePurchase/detailFinancePurchase.do'>取消交易</a>
	                       	<#elseif list.dealRecordStatus==7&&list.thirdPayRecordNumber==""&&list.transferType=="2">
	                   		<a href='${base}/pay/cancelRechargePay.do?loginId=${list.id}&retUrl=/financePurchase/detailFinancePurchase.do'>取消交易</a>
	                       </#if>
	                      </td>
	                    </tr>
	                    </#if>
	                    </#list>
	              	</table>
	                </div>
            </div>
        </div> 
      <div class="frame-tab">
          <div class="tab-title">
            <ul>
               <li class="selected" onclick="plan_more('plan_one')">散标投标中</li>
               <!--<span class="sept">|</span>
               <li onclick="plan_more('plan_two')">理财计划投标中</li>-->
              <span class="plan_one"> <a href="${base}/financePurchase/myFinancePurchase.do?type='1'"><i class="more"></i>更多</a></span>
              <!--<span class="plan_two" style="display:none"> <a href="${base}/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?Q_state_SN_EQ=1&show=buyingList"><i class="more"></i>更多</a></span>-->
              </ul>
          </div>
          <div id="tab-content">	
              <div class="tb-c" >
              	 <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
		                 <tr>
		                      <th align="center">名称</th>
		                      <th align="center">投资时间</th>
		                      <th align="center">投资金额</th>
		                      <th align="center">投资期限</th>
		                      <th align="center">年化利率</th>
		                  </tr>                  
		
		                <#list   newManageMoney as list>
		         			<#if (list_index<5)>
		         			
		                <tr class="ff">
		                      <td align="center" class="td-top"  style="padding-left:15px;white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
		                    <a class="middle" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.planId}" title="${list.proName}">${list.projectName}</a>
		                    </td>
		                      <td align="center">${list.bidtime}</td>
		                      <td align="center">${list.userMoney}元</td>
		                      <td align="center">${list.loanLife}</td>
		                      <td align="center">${list.interestRate}%</td>
		                     
		                  </tr>
		                	</#if>
		        		</#list>
		                
		          </table>                   
          	  </div>
              <div class="tb-c none" >
                   <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
		                 <tr>
		                      <th align="center" >名称</th>
		                      <th align="center">投资时间</th>
		                      <th align="center">投资金额</th>
		                      <th align="center">投资期限</th>
		                      <th align="center">年化利率</th>
		                  </tr>                  
		
		                <#list   mmlist1 as list>
		         			
		                <tr class="ff">
		                      <td align="center" class="td-top"  style="padding-left:15px;white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
		                    <a class="middle" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.plManageMoneyPlan.mmplanId}" title="${list.mmName}">${list.mmName}</a>
		                    </td>
		                      <td align="center">${list.buyDatetime}</td>
		                      <td align="center">${list.buyMoney}元</td>
		                      <td align="center">${list.orderlimit}天</td>
		                      <td align="center">${list.promisYearRate}%</td>
		                     
		                  </tr>
		        		</#list>
		                
		          </table>                     
              </div>
		</div>     
	</div>

  <!-- start 列表字段不正确，需重新做-->
   <div class="frame-tabs">
          <div class="tab-titles">
            <ul>
               <li class="selected" onclick="plan_more('plan_one')">散标近期待收投资</li>
               <!--<span class="sept">|</span>
               <li onclick="plan_more('plan_two')">理财近期待收投资</li>-->
               <a class="plan_one" href="${base}/financePurchase/myFinancePurchase.do"><i class="more"></i>更多</a>
               <!--<span class="plan_two" style="display:none"> <a href="${base}/creditFlow/financingAgency/managelist1PlManageMoneyPlanBuyinfo.do?type='1'"><i class="more"></i>更多</a></span>-->
              </ul>
          </div>
		<div id="tab-contents">
	       <div class="tb-c">
	            <table width="100%" class="tab_css_one"  border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <th align="center">名称</th>
	                  <th align="center">投标时间</th>
	                  <th align="center">投资金额</th>
	                  <th align="center">投资期限</th>
	                  <th align="center">年化利率</th>
	                  <th align="center">计划还款日</th>
	                  <th align="center">还款日应收金额</th>
	                  <th align="center">查看</th>
	
	                  
	                </tr>
	                <#list   ShowManageMoney as list>
	         			<#if (list_index<5)>
	               <tr>
	                  <td align="center" class="td-top">
	                  <span class="tit"><a class="middle" title="${list.projectName}" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.planId}">${list.projectName}</a></span>
	                  <td align="center">${(list.bidtime)!}</td>
	                  <td align="center">￥${list.userMoney?string(',##0.00')}</td>
	                  <td align="center">${(list.loanLife)!}</td>
	                  <td align="center">${(list.interestRate)!}</td>
	                  <td align="center">${list.intentDate?string("yyyy-MM-dd")}</td>
	                  <td align="center">${list.planPaymentsMoney?string(',##0.00')}</td>
	                  
	                 
	                  <td align="center" >
	                  <span style="display:inline-block;float:right;"><a href="javascript:void(0);"  onClick="clickFinancing(${list.planId},'${list.orderNo}','Financing') " class="btn1">回款计划</a>
	                  <a  <#if list.url?length gt 2>href="${base}/financePurchase/downLoadFinancePurchase.do?customerType=invest&contractUrl=${list.url!}"<#else>href="javascript:void(0);" onclick="ht()"</#if> class="btn2">合同</a><span class="last"></span></span> </td>
	                </tr>
	                	</#if>
	        		</#list>
	                
	          </table>
	        </div>
	        <div class="tb-c none">	        	
	            <table width="100%" class="tab_css_one"  border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <th align="center">名称</th>
	                  <th align="center">投资金额</th>
	                  <th align="center">投资期限</th>
	                  <th align="center">年化利率</th>
	                  <th align="center">加入日期</th>
	                  <th align="center">计划到期日</th>
	                  <th align="center">查看</th>
	                </tr>
	                <#list   mmlist2 as list>
	               <tr>
	                  <td align="center" class="td-top">
	                  <span class="tit"> <a class="middle" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.plManageMoneyPlan.mmplanId}" title="${list.mmName}">${list.mmName}</a></span>
	                  <td align="center">￥${list.buyMoney?string(',##0.00')}</td>
	                  <td align="center">${(list.orderlimit)!}天</td>
	                  <td align="center">${(list.promisYearRate)!}%</td>
	               <td align="center">${list.buyDatetime?string("yyyy-MM-dd")}</td>
	                  <td align="center">${list.endinInterestTime?string("yyyy-MM-dd")}</td>
	                  <td align="center" >
                       <span>
                        <a class="btn1" href="javascript:void(0)" class="btn2"  onClick="clickAssigninterest(${list.orderId})">回款</a>
                        <a class="btn1" target="_blank" href="${base}/creditFlow/financingAgency/buyInfoContractPlManageMoneyPlan.do?orderId=${list.orderId}" class="btn2"  >合同</a>
                      <#if list.state==7> <a  href="javascript:void(0)" class="btn1"> 退审中</a> <#else><a  href="javascript:void(0)" class="btn1"  onClick="clickearlyout(${list.orderId},'${list.plManageMoneyPlan.lockingEndDate}')">退出</a></#if>
                      </span></td>
	                </tr>
	        		</#list>
	                
	          </table>
	        </div>
  		</div>
 </div>
          <div class="tab-list">
        	<h3>
        		<span>近期待还借款</span>
        		<a href="${base}/user/loanmanagementBpCustMember.do?toAction=Repayment"><i class="more"></i>更多</a>
        	</h3>
        	<div class="tb-c">
            <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <th align="center">名称</th>
                 <th align="center">还款期号</th>
                  <th align="center">计划还款日</th>
                  <th align="center">应还本息合计</th>
                  <th align="center">当前罚息</th>
                  <th align="center">当前应还总额</th>
                  <!--<th class="top-line right-line" align="center" width="25%">查看</th>-->
                  <th align="center">操作</th>
                 
                </tr>
                <#list LoanList as list>
         			<#if (list_index<5)>
         			
                <tr>
                  <td align="center">
                   <span class="tit1"><a class="middle" title="${list.proName}" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}">${list.proName}</a></span></td>
                 <td align="center">${list.payintentPeriod}</td>
                  <td align="center">${list.intentDate?string("yyyy-MM-dd")}</td>
                  <td align="center">￥${list.intenttotal?string(',##0.00')}</td>

                  <td align="center">￥${list.accMoney?string(',##0.00')}</td>
                  <td align="center">￥${list.repaymentTotal?string(',##0.00')}</td>
                  
                 
 
                  <td>&nbsp;&nbsp;&nbsp;&nbsp;
                  <span class="last">
                  <a class="btn1" id="repayMentbtn" onClick="repayMentPay()" href="${base}/pay/repayMentByLoanerPay.do?planId=${list.bidId}&ids=&peridId=${list.payintentPeriod}&notMoney=${list.repaymentTotal}" >立即还款</a></span></td><#--<a href="${base}/user/loanmanagementBpCustMember.do?toAction=Repayment" class="btn1 btn3">立即还款</a>-->
                </tr>
                
                	</#if>
        		</#list>
          </table>
          </div>
        </div>
            <!-- end 列表字段不正确，需重新做-->
      </div>
    </div>
    </div>
</div>
</div>
</div>
<!--end: Container -->
       <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
       	
	</body>
</html>



<script type="text/javascript">


function checkSession(){
	$.ajax({
		async: false,
		type : "POST",
		dataType : "JSON",
		url : basepath + "user/checkThirdPartyMoneyBpCustMember.do",
		success : function(request) {
		var request1= JSON.stringify(request);
		console.debug(request1);
		location.reload() 
		},
		error : function(request) {
		var request1= JSON.stringify(request);
		console.debug(request1);
		}
	});
	
}

	function formatNum(num,n){//参数说明：num 要格式化的数字 n 保留小数位
	    //num = String(num.toFixed(n));
	    var re = /(-?\d+)(\d{3})/;
	    while(re.test(num)) num = num.replace(re,"$1,$2")
	    return num;
	}
	$(function () {
	var map1 = {};
	//资金信息
	
	 ajaxFunction("${base}/user/getMoneyBidFrozenBpCustMember.do", map1, zj02);
	
	ajaxFunction("${base}/user/getMoneyInvestAllBpCustMember.do", map1, zj03);
	
	ajaxFunction("${base}/user/getMoneyDueinAllBpCustMember.do", map1, zj04);
	
	ajaxFunction("${base}/user/getMoneyAccountNetassetBpCustMember.do", map1, zj05);
	
	//交易信息
	ajaxFunction("${base}/user/getMoneyAccumulativeInvestBpCustMember.do", map1, jy01);
	
	ajaxFunction("${base}/user/getCountBidFrozenBpCustMember.do", map1, jy02);
	
	ajaxFunction("${base}/user/getCountInvestBackBpCustMember.do", map1, jy03);
	
	ajaxFunction("${base}/user/getCountBidLoanBpCustMember.do", map1, jy04);
	
	ajaxFunction("${base}/user/getCountRepaymentLoanBpCustMember.do", map1, jy05);
	
	//收益信息
	ajaxFunction("${base}/user/getMoneyAccumulativeIncomeBpCustMember.do", map1, sy01);
	
	ajaxFunction("${base}/user/getMoneyPreweekIncomeBpCustMember.do", map1, sy02);
	
	ajaxFunction("${base}/user/getMoneyPremonthIncomeBpCustMember.do", map1, sy03);
	
	ajaxFunction("${base}/user/getMoneyExpectIncomeBpCustMember.do", map1, sy04);
	
	ajaxFunction("${base}/user/getMoneyAccumulativeIncomeRateBpCustMember.do", map1, sy05); 
	
	 });
	 
	 function zj01(data){
		 var result=data.result;
		 $("#zj01").empty().html(formatNum(result,2));
	 }
	 function zj02(data){
		 var result=data.result;
		 $("#zj02").empty().html(formatNum(result,2));
	 }
	 function zj03(data){
		 var result=data.result;
		 $("#zj03").empty().html(formatNum(result,2));
	 }
	 function zj04(data){
		 var result=data.result;
		 $("#zj04").empty().html(formatNum(result,2));
	 }
	 function zj05(data){
		 var result=data.result;
		 $("#zj05").empty().html(formatNum(result,2));
	 }
	 
	 
	 function jy01(data){
		 var result=data.result;
		 $("#jy01").empty().html(formatNum(result,2));
	 }
	 function jy02(data){
		 var result=data.result;
		 $("#jy02").empty().html(formatNum(result,2));
	 }
	 function jy03(data){
		 var result=data.result;
		 $("#jy03").empty().html(formatNum(result,2));
	 }
	 function jy04(data){
		 var result=data.result;
		 $("#jy04").empty().html(formatNum(result,2));
	 }
	 function jy05(data){
		 var result=data.result;
		 $("#jy05").empty().html(formatNum(result,2));
	 }
	 
	 
	 
	 function sy01(data){
		 var result=data.result;
		 $("#sy01").empty().html(formatNum(result,2));
	 }
	 function sy02(data){
		 var result=data.result;
		 $("#sy02").empty().html(formatNum(result,2));
	 }
	 function sy03(data){
		 var result=data.result;
		 $("#sy03").empty().html(formatNum(result,2));
	 }
	 function sy04(data){
		 var result=data.result;
		 $("#sy04").empty().html(formatNum(result,2));
	 }
	 function sy05(data){
		 var result=data.result;
		 $("#sy05").empty().html(formatNum(result,2));
	 }
	 
 
</script>