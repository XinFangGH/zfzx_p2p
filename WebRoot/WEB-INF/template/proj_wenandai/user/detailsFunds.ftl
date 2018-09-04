<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>${systemConfig.metaTitle} - 资金明细</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/detailsFunds.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>

<script type="text/javascript">var m1="个人中心",m2="资金明细",m3="资金明细";</script>
<script type="text/javascript">
	$(function(){
	  var t1 = document.getElementById("transferType");
	   if(t1!=null){
		for(i=0;i<t1.length;i++){
	        if($("#jylxSelectValue").val()==t1.options[i].value){  
	            t1.options[i].selected=true  
	        }  
	    }  
	}
	  var t2 = document.getElementById("isAccountSuccess");
	   if(t2!=null){
		for(i=0;i<t2.length;i++){
	        if($("#sfcgSelectValue").val()==t2.options[i].value){  
	            t2.options[i].selected=true  
	        }  
	    }  
	}
	});
</script>	
<style type="text/css">
.tab-controller {
color: #666;
}
.tab-controller .controller {
border-bottom: 1px solid #d5d5d5;
height: 39px;
}

.tab-controller .controller li {
background: url(../../../images/tab_page_bg.jpg) repeat-x 0 0;
border: 1px solid #d5d5d5;
border-bottom: none;
border-radius: 5px 5px 0 0;
cursor: pointer;
float: left;
font-size: 14px;
height: 38px;
line-height: 38px;
margin-right: 3px;
text-align: center;
width: 133px;
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

.tab-controller .tab-cont {
border: 1px solid #d5d5d5;
border-top: none;
border-radius: 0 0 5px 5px;
color: #666;
display: none;
line-height: 24px;
padding: 15px;
}

.account-info .funds{
margin: 0;
height: 460px;
}

.pagination {
padding:0!important;
text-align: center;
margin: 20px auto;
}

.m-t_15 {
margin-top: 15px;
}
.my_tit{
	font-size:12px;
	margin-bottom:6px;
}
</style>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">

<input id="jylxSelectValue" type="hidden" value="${transferType1}">
<input id="sfcgSelectValue" type="hidden" value="${isAccountSuccess1}">
<div class="main">
   <div class="user-cont">
   		<!--左侧三级导航条 -->
	   <div class="user-name-nav fl">
	       <#--<#if bpCustMember.entCompanyType==1>
    	  <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_guarantee.ftl">
    	  <#elseif bpCustMember.perCompanyType==1 >
    	  <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_financial.ftl"> 
    	  <#else>
    	  <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">  
    	  </#if>-->
		  <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
    	  <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl"> 
	   </div>
       <div class="user-cont-fr fr">
           <div class="user-cont-right">
           <h2 class="big-tit">资金明细</h2>	
    	<!-- 我的个人信息 start -->
	             <div class="worm-tip" style="margin:20px 0 10px;">
            	 <span>交易类型：</span> 
            	 <span>
            	  <select id="transferType"> 
            	   <option value="">全部</option>
            	   <#list obSystemaccountSetting as list>
            	 	<option value="${list.typeKey}">${list.typeName}</option>	
            	   </#list>
            	  </select>
            	  </span>
            	 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="f-l m-t_5"><span class="black normal">交易日期：</span></label>
					<input type="text" id="startDate" style="width:120px" name="bidtime" class="colorful" readonly="readonly" value="${startDate}" onclick="new Calendar().show(this);"/>
					<label class="f-l m-t_5"><span class="black normal">-</span></label>
					<input type="text" id="endDate" style="width:120px" name="bidtime2" class="colorful" readonly="readonly" value="${endDate}" onclick="new Calendar().show(this);"/>
            	 </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	  	<span>是否支付成功：</span> 
            	 <span>
            	  <select style="width:80px;height:28px;" id="isAccountSuccess"> 
            	   <option value="">全部</option>
            	 	<option value="1">是</option>
            	 	<option value="2">否</option>		
            	  </select></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	 <span class="buttonorange" style="font-size:14px; padding:4px 15px;" onclick="queryFinanceDetailList()"  name="queryBtn">查&nbsp;询</span>
	              </div>
			   		<p class="my_tit">*提现失败时请先确认绑定银行卡是否为二类卡，是否超出转账限额，详情咨询相关开卡银行</p>
	              	<div class="cont-list " >
	                <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
	                    <tr>
	                      <th  align="center" width="25%">交易流水号</th>
	                      <th align="center" width="15%" >交易时间</th>
	                      <th align="center" width="10%">交易类型</th>
	                      <th align="center"  width="15%">收入金额（元）</th>
	                      <th align="center"  width="15%">支出金额（元）</th>
	                      <th align="center" width="10%">交易状态</th>
	                      <th align="center" width="10%">操作</th>
	                    </tr>
	                     <#list pager.list as list>
	                    <tr>
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
	                    </#list>
	              	</table>
	                </div>
	              	<div class="pagination m-t_15" id="fund_pager" >
					 <#import "/WEB-INF/template/common/pager.ftl" as p>
						<@p.pager pager = pager baseUrl = "/financePurchase/detailFinancePurchase.do?transferType=${transferType1}&startDate=${startDate}&endDate=${endDate}&isAccountSuccess=${isAccountSuccess1}" />
					</div>
	          
            
            </div>
   	</div>        
	</div>				
</div>

			<!--end: main -->


<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>