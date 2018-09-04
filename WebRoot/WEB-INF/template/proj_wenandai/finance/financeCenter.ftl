<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 我要借款</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/financeCenter.js"></script>
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript" src="${base}/js/user/financeCheck.js"></script>
<script type="text/javascript" src="${base}/js/user/company.js"></script>
<script type="text/javascript">var m1="我要借款",m2="",m3="";</script>
<script type="text/javascript"> 
	//function myFinance(text){
		//$(".per-intention .prod-inner").css("display","none");
		//$("#"+text).css("display","block");
	//}
$(function(){
	$('.my-borrow a').click(function(){ 
		$(this).addClass("on").siblings().removeClass('on');
		$(".borrow-list").hide().eq($('.my-borrow a').index(this)).show(); 
	});

	$('.per-tab li').click(function(){ 
		$(this).addClass("selected").siblings().removeClass('selected');
		$(".prod-inner").hide().eq($('.per-tab li').index(this)).show(); 
	});
})
</script>

</head>
<body >
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div class="main">
	<div class="my-borrow">
		<a  class="on"  href="javascript:0;">借款意向登记</a>
		<!--<a href="javascript:0;">个人信用借款</a>-->
	</div>
<h2>${str}</h2>
	<div class="list-way">
		<div class="borrow-list">
			<h1 class="way-tit">借款意向登记</h1>
			<div class="way-content">
				<p class="big">温馨提示：</p>
	      		<p class="way-txt middle">◆ 我们为您提供专业的贷款代理、贷款咨询服务，根据公司的借款需求、信用情况以及资产情况，为您从我们的合作贷款机构中挑选出最适合您的机构及产品，并代理您 办理各种复杂手续。</p>
				<p class="way-txt middle">◆ 请您如实填写以下信息，我们会尽快与您取得联系。</p>
				<p class="way-txt middle">◆ 由于业务原因，升升投目前只开放云南地区企业的贷款业务。</p>
	      	</div>
	      	<div class="prod-title">
	          <span class="regular">请选择借款意向</span>
	        </div>
	        <div class="per-intention">
	        	<!--<div class="per-tab">
			    	<a class="regular selected" id="person1" onclick="myFinance('myPerson')"><em></em>个人借款意向登记</a>
			    	<a class="regular" id="person2" onclick="myFinance('myEnterprise')"><em></em>企业借款意向登记</a>
			    </div>-->
		        <ul class="per-tab">
			    	<li class="selected"><a class="regular">个人借款意向登记</a><em class="cked-bg"></em></li>
			    	<!--<li><a class="regular">企业借款意向登记</a><em class="cked-bg"></em></li>-->
			    </ul>
			    <div class="prod-title">
		          <span class="regular">请填写您的信息</span>
		        </div>
		        <!-- 个人借款-->
		        <div class="prod-inner" id="myPerson">
		          <form action="${base}/financePurchase/saveApplyFinancePurchase.do" onsubmit="return savePer()" method="post" accept-charset="utf-8">
										   <div class="reg_group">
				         <div class="group_lable w165"><span class="red middle" id ="businessName_x">*</span><span class="middle">借款人姓名：</span></div>
				         <div class="group_input">
				            <input  id="linkPersion" name="financeApply.linkPersion" type="text" tabindex="1" maxlength="20" size="30" class="input-text-style-3 input-wrap" onblur="chkLoanPerson(this)"  />
				         </div>
				         <div class="tip-linkPersion" id="tip-linkPersion">
				         	<span id="linkPersion_span" class="gray middle"></span>
				         </div>
			     	</div>
			     	<div class="reg_group">
				         <div class="group_lable w165"><span class="red middle" id ="phone_x">*</span><span class="middle">手机号码：</span></div>
				         <div class="group_input">
				            <input id="phone" name="financeApply.phone" type="text" tabindex="2" maxlength="20" size="30" class="input-text-style-3 input-wrap"  onblur="chkLoanPersonPhone(this)"/>
				         </div>
				         <div class="tip-linkPersion" id="tip-phone" >
				         	<span id="phone_span" class="gray middle"></span>
				         </div>
			     	</div>
			     	<div class="reg_group">
				         <div class="group_lable w165"><span class="middle">所在地区：</span></div>
				         <div class="group_input">
				            <input id="area" name="financeApply.area" type="text"  tabindex="3" maxlength="20" size="30"  class="input-text-style-3 input-wrap" />
				         </div>
				         <div class="tip-linkPersion" id="tip-area">
				         	<span class="red middle" id ="area_x"></span>
				         	<span id="area_span" class="gray middle"></span>
				         </div>
			     	</div>
			     	<div class="reg_group">
				         <div class="group_lable w165"><span class="red middle" id ="money_x">*</span><span class="black middle">期望贷款金额（元）：</span></div>
				         <div class="group_input">
				            <input  id="loanMoney" name="financeApply.loanMoney" type="text"  tabindex="4" maxlength="20" size="30" onblur="chkLoanMoney(this);funFormat(this);" onkeyup="value=value.replace(/[^\d]/g,'')" <#--placeholder="请输入数字"--> class="input-text-style-3 input-wrap" />
				         </div>
				         <div class="tip-linkPersion" id="tip-loanMoney">
				         	<span id="loanMoney_span" class="gray middle">请输入数字</span>
				         </div>
			     	</div>
			     	<div class="reg_group">
				         <div class="group_lable w165"><span class="red middle" id ="timeLen_x">*</span><span class="black middle"> 期望贷款期限（月）：</span></div>
				         <div class="group_input">
				            <input id="loanTimeLen" name="financeApply.loanTimeLen" type="text"  tabindex="5" size="30" onblur="chkLoanTime(this)" class="input-text-style-3 input-wrap" />
				         </div>
				         <div class="tip-linkPersion" id="tip-loanTimeLen" >
				         	<span id="loanTimeLen_span" class="gray middle"></span>
				         </div>
			     	</div>
			     	<div class="reg_group" style="height:122px">
				         <div class="group_lable w165"><span class="black middle">借款用途：</span></div>
				         <div class="group_input" style="height:122px">
				            <textarea id="remark" name="financeApply.remark"  tabindex="6" class="input-text-style-3 input-wrap" rows="3" cols="31" style="resize: none; height:100px;padding:10px;"></textarea>
				         </div>
				         <div class="tip-linkPersion" id="tip-remark" ></div>
			     	</div>
			     	<div class="reg_group">
				         <div class="group_lable w165"><span class="red middle" id ="timeLen_x">*</span><span class="black middle">验证码：</span></div>
				         <div class="group_input" style="width:90px;">
				            <input type="text" id="checkCode" name="checkCode" tabindex="7" size="8"  maxlength="4" placeholder="输入验证码" onkeyup="validatCheckCode(this)" class="input-text-style-3 input-wrap" style="width:68px;"/>
				         </div>
				          <div class="reCaptcha">
				          	<span><img id="loginCode" class="verify-code" alt="点击更换" title="点击更换" onclick="javascript:refresh(document.getElementById('loginCode'));"  src="${base}/getCode.do" /></span>
			              	<span onclick="javascript:refresh(document.getElementById('loginCode'));">看不清，换一张</span>
							<span class="orange middle" id ="spancheckCode"></span>
						 </div>
				         <div class="tip-linkPersion" id="tip-loanTimeLen" >
				         	<span id="loanTimeLen_span" class="gray middle"></span>
				         </div>
			     	</div>
			     	<div class="reg_btn" style="margin-left:220px;"> 
			     		<input type="submit"  onclick="validateApply(this)" class="buttonorange" style="padding:6px 18px;cursor: pointer;" id="addAplyBtn" value="立&nbsp;即&nbsp;申&nbsp;请 "  tabindex="7" />
			     	</div>
			     	</form>
		        </div>
		        <!-- 个人借款结束-->
		        <!-- 企业借款-->
		        <div class="prod-inner none" id="myEnterprise" style="display:none;">
		        <form id="applyForm" action="${base}/financePurchase/saveApplyFinancePurchase.do" onsubmit="return saveBusiness()" method="post" accept-charset="utf-8">
		        	<div class="reg_group">
				         <div class="group_lable w165"><span class="red middle" id ="businessName_x">*</span><span class="black middle">企业名称：</span></div>
				         <div class="group_input">
				            <input  id="businessNameB" name="financeApply.businessName" type="text" tabindex="1" maxlength="20" size="30" class="input-text-style-3 input-wrap" onblur="chkBusiness(this)" />
				         </div>
				         <div class="tip-linkPersion" id="tip-businessName" >
				         	<span id="businessName_span"  class="gray middle"></span>
				         </div>
			     	</div>
			     	<div class="reg_group">
				         <div class="group_lable w165"><span class="red middle" id ="businessName_x">*</span><span class="black middle">借款人姓名：</span></div>
				         <div class="group_input">
				            <input id="linkPersionB" name="financeApply.linkPersion" type="text" tabindex="2" maxlength="20" size="30" class="input-text-style-3 input-wrap" onblur="chkLoanBussiness(this)"/>
				         </div>
				         <div class="tip-linkPersion" id="tip-linkBussiness" >
				         	<span id="linkBussiness_span" class="gray middle"></span>
				         </div>
			     	</div>
			     	<div class="reg_group">
				         <div class="group_lable w165"><span class="red middle" id ="phone_x">*</span><span class="middle">手机号码：</span></div>
				         <div class="group_input">
				            <input id="phoneB" name="financeApply.phone" type="text" tabindex="3" maxlength="20" size="30" class="input-text-style-3 input-wrap"  onblur="chkLoanBPhone(this)"/>
				         </div>
				         <div class="tip-linkPersion" id="tip-phoneB" >
				         	<span id="phoneB_span"  class="gray middle"></span>
				         </div>
			     	</div>
			     	<div class="reg_group">
				         <div class="group_lable w165"><span class="middle">所在地区：</span></div>
				         <div class="group_input">
				            <input id="areaB" name="financeApply.area" type="text"  tabindex="4" maxlength="20" size="30"  class="input-text-style-3 input-wrap" />
				         </div>
				         <div class="tip-linkPersion" id="tip-area">
				         	<span class="red middle" id ="area_x"></span>
				         	<span id="area_span" class="gray middle"></span>
				         </div>
			     	</div>
			     	<div class="reg_group">
				         <div class="group_lable w165"><span class="red middle" id ="money_x">*</span><span class="black middle">期望贷款金额（元）：</span></div>
				         <div class="group_input">
				            <input  id="loanMoneyB" name="financeApply.loanMoney" type="text"  tabindex="5" maxlength="20" size="30" class="input-text-style-3 input-wrap"  onblur="chkLoanMoneyB(this);funFormat(this);"/>
				         </div>
				         <div class="tip-linkPersion" id="tip-loanMoneyB">
				         	<span id="loanMoneyB_span" class="gray middle"></span>
				         </div>
			     	</div>
			     	<div class="reg_group">
				         <div class="group_lable w165"><span class="red middle" id ="timeLen_x">*</span><span class="black middle"> 期望贷款期限（月）：</span></div>
				         <div class="group_input">
				            <input id="loanTimeLenB" name="financeApply.loanTimeLen" type="text"  tabindex="6" class="input-text-style-3 input-wrap" size="30" onblur="chkLoanTimeB(this)"  />
				         </div>
				         <div class="tip-linkPersion"  id="tip-loanTimeLenB" >
				         	<span id="loanTimeLenB_span" class="gray middle"></span>
				         </div>
			     	</div>
			     	<div class="reg_group" style="height:122px">
				         <div class="group_lable w165"><span class="black middle">借款用途：</span></div>
				         <div class="group_input" style="height:122px">
				            <textarea id="remarkB" name="financeApply.remark"  tabindex="7" class="input-text-style-3 input-wrap" rows="3" cols="31" style="resize: none; height:100px;padding:10px;"></textarea>
				         </div>
				         <div class="tip-linkPersion" id="tip-remark"></div>
			     	</div>
			     	<div class="reg_group">
				         <div class="group_lable w165"><span class="red middle" id ="timeLen_x">*</span><span class="black middle">验证码：</span></div>
				         <div class="group_input" style="width:90px;">
				            <input id="checkCodeB" name="checkCodeB" tabindex="8" size="8" maxlength="4" placeholder="输入验证码" onkeyup="business_validatCheckCode(this)"class="input-text-style-3 input-wrap" style="width:68px;"/>
				         </div>
				          <div class="reCaptcha">
				          	<span><img id="loginCode1" class="verify-code" alt="点击更换" title="点击更换" onclick="javascript:refresh(document.getElementById('loginCode1'));"  src="${base}/getCode.do" /></span>
			              	<span onclick="javascript:refresh(document.getElementById('loginCode1'));">看不清，换一张</span>
							<span class="orange middle" id ="business_spancheckCode"></span>
						 </div>
				         <div class="tip-linkPersion">
				         	<span id="typeCode"  style="display:none"></span>
				         </div>
			     	</div>
			     	<div class="reg_btn" style="margin-left:220px;"> 
			     		<input type="submit" class="buttonorange" id="addAplyBtn" value="立&nbsp;即&nbsp;申&nbsp;请 "  tabindex="7" /> 
			     	</div>
		        </div>
		        </form>
		        <!-- 企业借款结束-->
	        </div>
        </div>
        <!--个人信用贷-->
        <div class="borrow-list none">
	        <div class="list-way-ul">
	        	<h1 class="way-tit">个人信用贷</h1>
		        <div id="personDiv">
			        <#list productPersonList as list>
			        		<div class="porduct wad_porspec${list_index}">
				        		
				            	<h4>${list.productName}<span class="small">(${list.userScope})</span></h4>
					                <ul>
					                	<li>申请条件：</li>
					                	 <#list list.conditionList as conditionList>
					                	 	<li class="normal black">${conditionList.conditionContent}</li>
					                	 </#list>
					                </ul>
				                <p><a href="${base}/loan/showProductP2pLoanProduct.do?productId=${list.productId}">立即申请</a></p>
			            	</div>
			        </#list>
		        </div>
		        <div id="enterpriseDiv" hidden="true">
		        <#list productEnterpriseList as list>
		        		<div class="porduct wad_porspec${list_index}">
			        		
			            	<h4>${list.productName}<span class="small">(${list.userScope})</span></h4>
			                <ul>
					                <li>申请条件：</li>
						               	 <#list list.conditionList as conditionList>
						               	 	<li class="normal black">${conditionList.conditionContent}</li>
						               	 </#list>
									
			                </ul>
			                <p><a href="${base}/loan/showProductP2pLoanProduct.do?productId=${list.productId}">立即申请</a></p>
		            	</div>
		        </#list>
		    </div>
	    </div>
    </div>
     <!--end:个人信用贷-->   
	</div>

</div>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">	
</body>
</html>