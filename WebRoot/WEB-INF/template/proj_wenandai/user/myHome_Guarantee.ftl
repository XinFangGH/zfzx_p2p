<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title>${systemConfig.metaTitle} - 企业担保户信息</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/uploadImage.js"></script>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript" src="${base}/js/user/mmplan.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript">var m1="个人中心",m2="总览",m3="";</script>
<script type="text/javascript">
$(function(){
	$('.tab-title ul li').click(function(){ 
	$(this).addClass("selected").siblings().removeClass('selected');
	$("#tab-content .tb-c").hide().eq($('.tab-title ul li').index(this)).show(); 
	});

})
$(function(){
	$('.tab-titles ul li').click(function(){ 
	$(this).addClass("selected").siblings().removeClass('selected');
	$("#tab-contents .tb-c").hide().eq($('.tab-titles ul li').index(this)).show(); 
	});

})
	$(function(){
	  var t1 = document.getElementById("overDays");
	  debugger
	   if(t1!=null){
		for(i=0;i<t1.length;i++){
	        if($("#yqtsSelectValue").val()==t1.options[i].value){  
	            t1.options[i].selected=true  
	        }  
	    }  
	}
})
function plan_more(plan){
	if(plan=="plan_one"){
		$(".plan_two").css("display","none");
		$(".plan_one").css("display","block");
		$(".plan_three").css("display","none");
		$(".plan_four").css("display","none");
	}
	if(plan=="plan_two"){
		$(".plan_two").css("display","block");
		$(".plan_one").css("display","none");
		$(".plan_three").css("display","none");
		$(".plan_four").css("display","none");
	}
		if(plan=="plan_three"){
		$(".plan_three").css("display","block");
		$(".plan_one").css("display","none");
		$(".plan_two").css("display","none");
		$(".plan_four").css("display","none");
	}
		if(plan=="plan_four"){
		$(".plan_four").css("display","block");
		$(".plan_one").css("display","none");
		$(".plan_three").css("display","none");
		$(".plan_two").css("display","none");
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
<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
<input id="yqtsSelectValue" type="hidden" value="${overDays1}">
<input value="${(bpCustMember.id)??}" type="hidden" id="memberId">
<div class="main-box">
    <!-- 以下内容为左侧三级导航条 -->
		
        <!-- 左侧三级导航结束 end-->
   <div class="main">
    <#if bpCustMember.thirdPayFlagId!=null>
    	<#if bpCustMember.thirdPayStatus==1 && bpCustMember.customerType==1>
    		<div class="inform">
       您的账户尚未激活资金托管账户<span class="icon-ww"> <img src="${base}/theme/${systemConfig.theme}/images/uc/icon-bg1.png" width="15" height="15" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;，目前不能进行理财或借款，<a href="${base}/user/offerMaterialBpCustMember.do">查看激活材料</a>
       <div class="sidnext">资金托管账户是企业客户和担保客户类型，在开通后需向易宝支付相关材料进行审核以此来激活资金托管账户。</div>
    </div>
    	<#else>
    	</#if>
    <#else>
   	  <div class="inform">
       您的账户尚未开通资金托管账户<span class="icon-ww"> <img src="${base}/theme/${systemConfig.theme}/images/uc/icon-bg1.png" width="15" height="15" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;，目前不能进行理财或借款，<a href="${base}/thirdreg.do">点击立即开通</a>
       <div class="sidnext">资金托管账户是理财人和借款人分别在资金托管机构（${payType}支付）开立的独立账户，资金由理财人和借款人直接对接，避免了资金被平台挪用的风险。</div>
    </div>
    
    
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
    </#list>    
      <div class="content01">
      	<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_guarantee.ftl">

        <div class="div-list">
        	<div class="guarantee">
	  <div class="gat-list">
    	<h2><span>担保数据</span></h2>
        <div class="li-list">
            <ul>
                <li>在保项目：<span id="db01">0</span>个</li>
                <li>结清项目：<span id="db02">0</span>个</li>
            </ul>
            <ul>
                <li><a href="#" title="不以逾期项目款项为准， 单个项目多笔款项逾期多笔款项也记为一个项目。">逾期项目：</a><span id="db03">0</span>个</li>
                <li><a href="#" title="不以逾期项目数量为准， 单个项目多笔款项逾期也记为一个项目。">逾期项目：</a><span id="db03">0</span>个</li>
                <li>代偿笔数：<span id="db04">0</span>笔</li>
                <li>已追回笔数:<span id="db05">0</span>笔</li>
            </ul>
        </div>
    </div>
    <div class="gat-list">
    	<h2 class="bg"><span>资金数据</span></h2>
        <div class="li-list">
            <ul>
                <li>在保金额：<span id="zj01">0.00</span>元</li>
                <li>已收担保费：<span id="zj02">0.00</span>元</li>
                <li>待收担保费：<span id="zj03">0.00</span>元</li>
            </ul>
            <ul>
                <li>待代偿金额：<span id="zj06">0.00</span>元</li>
                <li>代偿金额：<span id="zj04">0.00</span>元</li>
                <li>应收罚息：<span id="zj05">0.00</span>元</li>
            </ul>
        </div>
    </div>
</div>
        </div>
       <#--<div class="tab-list" style="font-size:14px; color:red;"></div> -->  
        <!-- start 列表字段不正确，需重新做-->
   <div class="frame-tabs">
          <div class="tab-titles">
            <ul>
               <li class="selected" onclick="plan_more('plan_one')">待代偿款项</li>
               <li onclick="plan_more('plan_two')">已代偿款项</li>
               <li onclick="plan_more('plan_three')">在保项目</li>
               <li onclick="plan_more('plan_four')">结清项目</li>
              </ul>
          </div>
		<div id="tab-contents">
	       <div class="tb-c">
	             <div class="worm-tip2" style="border:0;margin:20px 0;">
            	 	<span>项目名称：</span> 
            	 <span>
            	 <input type="text" id="oprojectName" class="colorful" value="${oprojectName}"/></span>
                  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	  	<span>逾期天数：</span> 
            	 <span>
            	  <select style="width:80px;height:28px;" id="overDays"> 
            	   <option value=""></option>
            	 	<option value="1">1天内</option>
            	 	<option value="3">3天内</option>	
            	 	<option value="5">5天内</option>	
            	 	<option value="7">7天内</option>	
            	 	<option value="15">15天内</option>		
            	  </select></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	   <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="f-l m-t_5"><span class="black normal">应还日期：</span></label>
					<input type="text" id="ostartDate" style="width:120px" name="bidtime" class="colorful" readonly="readonly" value="${ostartDate}" onclick="new Calendar().show(this);"/>
					<label class="f-l m-t_5"><span class="black normal">-</span></label>
					<input type="text" id="oendDate" style="width:120px" name="bidtime2" class="colorful" readonly="readonly" value="${oendDate}" onclick="new Calendar().show(this);"/>
            	 </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	 <span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" onclick="queryOverdueList()"  name="queryBtn">查&nbsp;询</span> 
	              </div> 
	            <table width="100%" class="tab_css_one"  border="0" cellspacing="0" cellpadding="0">
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
	                <#list   guaranteeList as list>
	               <tr>
	                  <td align="center" class="td-top">
	                  <span class="tit"><a style="color:#0096c4;font-size:14px;" title="${list.proName}" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}">${list.proName}</a></span>
	                  <td align="center">${(list.receiverName)}</td>
	                  <td align="center">${(list.payintentPeriod)}</td>
	                  <td align="center">￥${list.principal?string(',##0.00')}</td>
	                  <td align="center">￥${list.loanInterest?string(',##0.00')}</td>
	                  <td align="center">￥${list.serviceMoney?string(',##0.00')}</td>
	                  <td align="center">${list.intentDate?string("yyyy-MM-dd")}</td>
	                  <td align="center">${(list.punishDays)!}天</td>
	                  <td align="center">${list.accMoney?string(',##0.00')}</td>
	                   <td align="center" >
	                  <span style="display:inline-block;float:left;padding-left:30px;"><a href="javascript:void(0);"  onClick="clickCompensatory(${list.bidId},'${list.intentDate}') " class="btn1">代偿</a>
	                   <span class="last"></span></span> </td>
	                  <td align="center" >
	                  <span style="display:inline-block;float:right;"><a href="javascript:void(0);"  onClick="clickPlan(${list.bidId},'guaranteeFin') " class="btn1">回款计划</a>
	                  <a  <#if list.url?length gt 2>href="${base}/financePurchase/downLoadFinancePurchase.do?contractUrl=${list.url!}"<#else>href="javascript:void(0);" onclick="ht()"</#if> class="btn2">合同</a><span class="last"></span></span> </td>
	                </tr>
	        		</#list>
	          </table>
	        </div>
	        <div class="tb-c none">	 
	               <div class="worm-tip2" style="border:0;margin:20px 0;">
            	 	<span>项目名称：</span> 
            	 <span>
            	 <input type="text" id="cprojectName" class="colorful" value="${cprojectName}"/></span>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	   <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="f-l m-t_5"><span class="black normal">代偿日期：</span></label>
					<input type="text" id="cstartDate" style="width:120px" name="bidtime" class="colorful" readonly="readonly" value="${cstartDate}" onclick="new Calendar().show(this);"/>
					<label class="f-l m-t_5"><span class="black normal">-</span></label>
					<input type="text" id="cendDate" style="width:120px" name="bidtime2" class="colorful" readonly="readonly" value="${cendDate}" onclick="new Calendar().show(this);"/>
            	 </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	 <span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" onclick="queryCompensatoryList()"  name="queryBtn">查&nbsp;询</span> 
	              </div>        	
	            <table width="100%" class="tab_css_one"  border="0" cellspacing="0" cellpadding="0">
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
	                <#list   compensatoryList as list>
	         			
	         			
	               <tr>
	                  <td align="center" class="td-top">
	                  <span class="tit"><a style="color:#0096c4;font-size:14px;" title="${list.bidName}" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.planId}">${list.bidName}</a></span>
	                   <td align="center">${list.receiverName}</td>
	                   <td align="center">${list.payintentPeriod}</td>
	                   <td align="center">${list.compensatoryDate?string("yyyy-MM-dd")}</td>
	                  <td align="center">￥${list.compensatoryMoney?string(',##0.00')}</td>
	                  <td align="center">￥${list.punishMoney?string(',##0.00')}</td>
	                  <td align="center">￥${list.backAllMoney?string(',##0.00')}</td>
	                  <td align="center">${list.backDate?string("yyyy-MM-dd")}</td>
	                 
	                  <td align="center" >
                       <span>
                        <a class="btn1" href="javascript:void(0)" class="btn2"  onClick="clickOfflineRegist(${list.id})">线下追偿登记</a>
                      </span></td>
	                </tr>
	        		</#list>
	          </table>
	         <#import "/WEB-INF/template/common/pager.ftl" as p>
         	<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do?Q_state_SN_EQ=1&show=buyingList&startTime=${startTime_buyingList}&endTime=${endTime_buyingList}&selectValue=${selectValue_buyingList}"  />
	        </div>
	            <div class="tb-c none">
	             <div class="worm-tip2" style="border:0;margin:20px 0;">
            	 	<span>项目名称：</span> 
            	 <span>
            	 <input type="text" id="pprojectName" class="colorful" value="${pprojectName}"/></span>
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	 <span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" onclick="queryOnBidList()"  name="queryBtn">查&nbsp;询</span> 
	              </div> 	        	
	            <table width="100%" class="tab_css_one"  border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <th align="center">项目名称</th>
	                  <th align="center">借款人</th>
	                  <th align="center">金额</th>
	                  <th align="center">期限</th>
	                  <th align="center">年化利率</th>
	                  <th align="center">查看</th>
	
	                  
	                </tr>
	                <#list   zlist as list>
	         			
	         			
	               <tr>
	                  <td align="center" class="td-top">
	                  <span class="tit"><a style="color:#0096c4;font-size:14px;" title="${list.bidProName}" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}">${list.bidProName}</a></span>
	                  <td align="center">${list.receiverName}</td>
	                  <td align="center">￥${list.bidMoney?string(',##0.00')}</td>
	                  <td align="center">${list.loanLife}</td>
	                  <td align="center">${list.interestRate}%</td>
	                 
	                  <td align="center" >
	                   <a>合同</a>
                   </td>
	                </tr>
	                	
	        		</#list>
	                
	          </table>
	        </div>
	            <div class="tb-c none">	
	             <div class="worm-tip2" style="border:0;margin:20px 0;">
            	 	<span>项目名称：</span> 
            	 <span>
            	 <input type="text" id="uprojectName" class="colorful" value="${uprojectName}"/></span>
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	 <span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" onclick="queryUnBidList()"  name="queryBtn">查&nbsp;询</span> 
	              </div> 	        	
	            <table width="100%" class="tab_css_one"  border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <th align="center">项目名称</th>
	                  <th align="center">借款人</th>
	                  <th align="center">金额</th>
	                  <th align="center">期限</th>
	                  <th align="center">年化利率</th>
	                  <th align="center">查看</th>
	
	                  
	                </tr>
	                <#list   jlist2 as list>
	         			
	         			
	               <tr>
	                  <td align="center" class="td-top">
	          	      <span class="tit"><a style="color:#0096c4;font-size:14px;" title="${list.bidProName}" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}">${list.bidProName}</a></span>
	                  <td align="center">${list.receiverName}</td>
	                  <td align="center">￥${list.bidMoney?string(',##0.00')}</td>
	                  <td align="center">${list.loanLife}</td>
	                  <td align="center">${list.interestRate}%</td>
	               
	                 
	                  <td align="center" >
	                   <a>合同</a>
                    </td>
	                </tr>
	                	
	        		</#list>
	                
	          </table>
	        </div>
  		</div>
 </div>
            <!-- end 列表字段不正确，需重新做-->
      </div>
    </div>
</div>
<!--end: Container -->
       <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
       	
	</body>
</html>



<script type="text/javascript">
	$(function () {
	var map1 = {};
	//担保数据
	
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
	
   	 });
	 
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
</script>