<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 个人借款</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/financeCenter.js"></script>
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript" src="${base}/js/user/financeCheck.js"></script>
<script type="text/javascript" src="${base}/js/user/company.js"></script>
<script type="text/javascript">var m1="个人借款",m2="",m3="";</script>
<script type="text/javascript"> 
$(function(){



	//图标下拉框  提示框
	var content = $(".sidnext").html();	
	$('.icon').pt({
		position: 'b',
		width:120,
		content: content
	});	  
//图标下拉框  提示框
	var content = $(".sidnext1").html();	
	$('.icon1').pt({
		position: 'b',
		width:120,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext2").html();	
	$('.icon2').pt({
		position: 'b',
		width:200,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext3").html();	
	$('.icon3').pt({
		position: 'b',
		width:150,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext4").html();	
	$('.icon4').pt({
		position: 'b',
		width:80,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext5").html();	
	$('.icon5').pt({
		position: 'b',
		width:200,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext6").html();	
	$('.icon6').pt({
		position: 'b',
		width:200,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext7").html();	
	$('.icon7').pt({
		position: 'b',
		width:120,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext8").html();	
	$('.icon8').pt({
		position: 'b',
		width:120,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext9").html();	
	$('.icon9').pt({
		position: 'b',
		width:80,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext10").html();	
	$('.icon10').pt({
		position: 'b',
		width:200,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext11").html();	
	$('.icon11').pt({
		position: 'b',
		width:200,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext12").html();	
	$('.icon12').pt({
		position: 'b',
		width:120,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext13").html();	
	$('.icon13').pt({
		position: 'b',
		width:120,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext14").html();	
	$('.icon14').pt({
		position: 'b',
		width:120,
		content: content
	});	  

//图标下拉框  提示框
	var content = $(".sidnext15").html();	
	$('.icon15').pt({
		position: 'b',
		width:80,
		content: content
	});	  
});
function myFinance(text){
	$(".list-way").css("display","none");
	$("#"+text).css("display","block");
}

</script>
</head>
<body >
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<h2>${str}</h2>
<div style="width:100%; background:#eff3f3;">
<div class="main">
	<!-- start:个人信用贷款产品 -->
	<div class="per">
    	<a href="#" style="background:#36aef4;color:#fff; padding:8px 15px;font-size:16px;" id="person1" onclick="myFinance('xingdai')">个人信用借款</a>
    	<a href="#" style="background:#5CADAD;color:#fff; padding:8px 15px; font-size:16px;" id="person2" onclick="myFinance('myPerson')">个人借款意向登记</a>
    	<a href="#" style="background:#9999CC;color:#fff; padding:8px 15px; font-size:16px;" id="person3" onclick="myFinance('myEnterprise')">企业借款意向登记</a>
    </div>
    
    <!--个人信贷借款开始 -->
    <div class="list-way" id="xingdai">
    	<div class="list-way-top">
        	<div class="img">
            	<div class="pic"></div>
            </div>
            <div class="cont" id="personContDiv">
            	<div class="tit">
                	<h3 class="title">个人信用贷</h3>
                    <div class="iconall">
                    	<ul class="sidenav">
                        	<span>用途：</span>
                         	<li><a href="#" class="icon"></a><div class="sidnext">购房：购买新房或二手房</div></li>
                            <li><a href="#" class="icon1"></a><div class="sidnext1">购车：购车或车位</div></li>
                            <li><a href="#" class="icon2"></a><div class="sidnext2">消费：旅游、装修、结婚、教育进修、出国留学等较大金额个人消费</div></li>
                            <li><a href="#" class="icon3"></a><div class="sidnext3">经营：用于公司经营周转</div></li>
                            <li><a href="#" class="icon4"></a><div class="sidnext4">其他</div></li>
                        </ul>
                    </div>
                </div>
                <div class="txt text1">
                   <p><span class="juh"></span><span class="con">快</span>&nbsp;&nbsp;贷款最快一天到账，额度最高50万元</p>
                   <p><span class="juh"></span><span class="con">简</span>&nbsp;&nbsp;申请简单无需任何担保，纯信用，仅需网上提交信息、资料</p>
                </div>
            </div>
            
            
           <div class="cont" id="enterpriseContDiv" hidden="true">
	        	<div class="tit">
	            	<h3 class="title">企业信用贷</h3>
	                <div class="iconall">
	                	<ul class="sidenav">
	                    	<span>用途：</span>
	                     	<li><a href="#" class="icon"></a><div class="sidnext">购房：购买新房或二手房</div></li>
	                        <li><a href="#" class="icon1"></a><div class="sidnext1">购车：购车或车位</div></li>
	                        <li><a href="#" class="icon2"></a><div class="sidnext2">消费：旅游、装修、结婚、教育进修、出国留学等较大金额个人消费</div></li>
	                        <li><a href="#" class="icon3"></a><div class="sidnext3">经营：用于公司经营周转</div></li>
	                        <li><a href="#" class="icon4"></a><div class="sidnext4">其他</div></li>
	                    </ul>
	                </div>
	            </div>
	            <div class="txt text1">
	               <p><span class="juh"></span><span class="con">快</span>&nbsp;&nbsp;贷款最快一天到账，额度最高50万元</p>
	               <p><span class="juh"></span><span class="con">简</span>&nbsp;&nbsp;申请简单无需任何担保，纯信用，仅需网上提交信息、资料</p>
	            </div>
        </div>
        </div>
  
        <div class="list-way-ul" id="personDiv">
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
        
        <div class="list-way-ul" id="enterpriseDiv" hidden="true">
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
    	 <!--个人信贷借款开始 -->
    	
    	<!-- 个人借款开始-->
    	 <div class="list-way" id="myPerson" style="display:none">
    	<div class="list-way-top">
    	<form action="${base}/financePurchase/saveApplyFinancePurchase.do" onsubmit="return savePer()" method="post" accept-charset="utf-8">
	 <input type="hidden" name="financeApply.productName" value="${loanProduct.productName}">
	  <input type="hidden" name="financeApply.type" value="0">
	  <input type="hidden" name="financeApply.state" value="0">
	  <input type="hidden" name="financeApply.productId" value="${loanProduct.productId}"/>
    <div>
      <div style="float:left; width:999px; height:auto; margin:0px 0px 0px 0px;">
      	<div style="height:102px; background:#def3d4; margin:50px 100px 20px 100px; padding:10px 54px;">
      		<div style="line-height:26px; color:#006d30;font-size:15px;">◆ 我们为您提供专业的贷款代理、贷款咨询服务，根据公司的借款需求、信用情况以及资产情况，为您从
我们的合作贷款机构中挑选出最适合您的机构及产品，并代理您办理各种复杂手续。</div>
			<div style="line-height:24px; color:#006d30;font-size:15px;">◆ 请您如实填写以下信息，我们会尽快与您取得联系</div>
			<div style="line-height:24px; color:#006d30; font-size:15px;">◆  由于业务原因，升升投目前只开放云南地区企业的贷款业务。</div>
      	</div>
        <div style="width:999px; height:55px; padding:0px 0px 0px 0px;">
          <div style="width:899px; float:left; height:35px; padding:20px 0px 0px 45px;"><span class="black regular">请填写您的信息</span></div>
        </div>
        <div style="width:910px; height:1px; padding:0px 40px;">
      <hr class="splitline" />
    </div>
        <div style="width:919px; height:30px; padding:30px 0px 0px 80px;">
          <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="businessName_x">*</span><span class="black middle">借款人姓名：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="linkPersion" name="financeApply.linkPersion" type="text" tabindex="1" maxlength="20" size="30" class="colorful1"  onblur="chkLoanPerson(this)"/>
          </div>
        <div id="tip-linkPersion"  style="width:400px; margin-left:40px;  height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span id="linkPersion_span" class="gray middle"></span></div>
        </div>
        
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
          <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="phone_x">*</span><span class="black middle">手机号码：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="phone" name="financeApply.phone" type="text" tabindex="2" maxlength="20" size="30" class="colorful1"  onblur="chkLoanPersonPhone(this)"/>
          </div>
          <div id="tip-phone" style="width:400px; margin-left:40px;  height:24px; float:left; padding-top:6px;">&nbsp;&nbsp;<span id="phone_span" class="gray middle"></span></div>
        </div>
		<div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
          <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">所在地区：</span></div>
          <div style="width:220px; height:30px; float:left;">
          <input id="area" name="financeApply.area" type="text"  tabindex="3" maxlength="20" size="30" class="colorful1" />
			
          </div>
         <div id="tip-area" style="width:400px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span class="red middle" id ="area_x"></span><span id="area_span" class="gray middle"></span></div>
        </div>
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
          <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="money_x">*</span><span class="black middle">期望贷款金额（元）：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="loanMoney" name="financeApply.loanMoney" type="text"  tabindex="4" maxlength="20" size="30" class="colorful1"  onblur="chkLoanMoney(this);funFormat(this);"/>
          </div>
          <div id="tip-loanMoney" style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;<span></span>&nbsp;<span id="loanMoney_span" class="gray middle"></span></div>
        </div>
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
          <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="timeLen_x">*</span><span class="black middle"> 期望贷款期限（月）：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="loanTimeLen" name="financeApply.loanTimeLen" type="text"  tabindex="5" class="colorful1"  size="30" onblur="chkLoanTime(this)"/>
          </div>
         <div id="tip-loanTimeLen" style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;<span></span>&nbsp;<span id="loanTimeLen_span" class="gray middle"></span></div>
        </div>
        
        <div style="width:919px; height:70px; padding:20px 0px 0px 80px;">
          <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">借款用途：</span></div>
          <div style="width:400px; height:30px; float:left;">
            <textarea id="remark" name="financeApply.remark"  tabindex="6" class="colorful1" rows="3" cols="31" style="resize: none; height:100px;padding:10px;"></textarea>
          </div>
          <div id="tip-remark" style="width:400px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;</div>
        </div>
        
        
         <div style="width:919px; height:30px; padding:70px 0px 0px 80px;">
          	  <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">验证码：</span></div>
               <div style="width:100px; height:30px; float:left;">
              	<input type="text" id="checkCode" name="checkCode" tabindex="7" size="8"style="width:80px;" maxlength="4" class="colorful1" placeholder="输入验证码" onkeyup="validatCheckCode(this)" />
              </div>
               
              <div style="width:60px; height:29px; float:left; padding-top:1px ;margin-left:10px;"><img width="60" height="35" id="loginCode" class="verify-code" alt="点击更换" title="点击更换" onclick="javascript:refresh(document.getElementById('loginCode'));"  src="${base}/getCode.do" style="cursor:pointer;" /></div>
              <div style="width:200px; height:24px; float:left; padding-top:6px">&nbsp;<span class="normal" style="cursor:pointer;color:#0096c4;width:100px;" onclick="javascript:refresh(document.getElementById('loginCode'));">看不清，换一张</span>&nbsp;&nbsp;<span class="orange middle"style="width:100px;" id ="spancheckCode"></span></div>
        </div> 
        
        
        
        <div style="width:799px; height:30px; padding:20px 0px 0px 200px;">
         
           </div>
        <div style="width:699px; height:60px; padding:20px 0 0 420px; "> <input type="submit" onclick="validateApply(this)" class="buttonoblue1" style="font-size:18px; padding:6px 18px;cursor: pointer;" id="addAplyBtn" value="立&nbsp;即&nbsp;申&nbsp;请 "  tabindex="7" /> </div>
      </div>
    </div>
  </div>
</form>
 </div>
    	<!-- 个人借款结束-->
    	
    	
    	<!-- 企业借款开始-->
    	 <div class="list-way" id="myEnterprise" style="display:none">
    	<div class="list-way-top">return savePer()return saveBusiness()
    	<form  action="${base}/financePurchase/saveApplyFinancePurchase.do" onsubmit="return saveBusiness()" method="post" accept-charset="utf-8">
  <input type="hidden" name="financeApply.productName" value="${loanProduct.productName}">
  <input type="hidden" name="financeApply.type" value="1">
  <input type="hidden" name="financeApply.state" value="0">
  <input type="hidden" name="financeApply.productId" value="${loanProduct.productId}"/>
<div>
  <div style="float:left; width:999px; height:auto; margin:0px 0px 0px 0px;">
  	<div style="height:102px; background:#def3d4; margin:50px 100px 20px 100px; padding:10px 54px;">
  		<div style="line-height:26px; color:#006d30;font-size:15px;">◆ 我们为您提供专业的贷款代理、贷款咨询服务，根据公司的借款需求、信用情况以及资产情况，为您从
我们的合作贷款机构中挑选出最适合您的机构及产品，并代理您办理各种复杂手续。</div>
		<div style="line-height:24px; color:#006d30;font-size:15px;">◆ 请您如实填写以下信息，我们会尽快与您取得联系</div>
		<div style="line-height:24px; color:#006d30; font-size:15px;">◆  由于业务原因，升升投目前只开放云南地区企业的贷款业务。</div>
  	</div>
    <div style="width:999px; height:55px; padding:0px 0px 0px 0px;">
      <div style="width:899px; float:left; height:35px; padding:20px 0px 0px 45px;"><span class="black regular">请填写您的信息</span></div>
    </div>
    <div style="width:910px; height:1px; padding:0px 40px;">
      <hr class="splitline" />
    </div>

    <div style="width:919px; height:30px; padding:30px 0px 0px 80px;">
    <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="businessName_x">*</span><span class="black middle">企业名称：</span></div>
    <div style="width:220px; height:30px; float:left;">
      <input id="businessNameB" name="financeApply.businessName" type="text" tabindex="1" maxlength="20" size="30" class="colorful1"  onblur="chkBusiness(this)"/>
    </div>
  <div id="tip-businessName"  style="width:400px; margin-left:40px;  height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span id="businessName_span" class="gray middle"></span></div>
  </div>
  
    <div style="width:919px; height:30px; padding:30px 0px 0px 80px;">
      <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="businessName_x">*</span><span class="black middle">借款人姓名：</span></div>
      <div style="width:220px; height:30px; float:left;">
        <input id="linkPersionB" name="financeApply.linkPersion" type="text" tabindex="2" maxlength="20" size="30" class="colorful1"  onblur="chkLoanBussiness(this)"/>
      </div>
    <div id="tip-linkBussiness"  style="width:400px; margin-left:40px;  height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span id="linkBussiness_span" class="gray middle"></span></div>
    </div>
    
    
    <div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
      <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="phone_x">*</span><span class="black middle">手机号码：</span></div>
      <div style="width:220px; height:30px; float:left;">
        <input id="phoneB" name="financeApply.phone" type="text" tabindex="3" maxlength="20" size="30" class="colorful1"  onblur="chkLoanBPhone(this)"/>
      </div>
      <div id="tip-phoneB" style="width:400px; margin-left:40px;  height:24px; float:left; padding-top:6px;">&nbsp;&nbsp;<span id="phoneB_span" class="gray middle"></span></div>
    </div>
	<div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
      <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">所在地区：</span></div>
      <div style="width:220px; height:30px; float:left;">
      <input id="areaB" name="financeApply.area" type="text"  tabindex="4" maxlength="20" size="30" class="colorful1" />
		
      </div>
     <div id="tip-area" style="width:400px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span class="red middle" id ="area_x"></span><span id="area_span" class="gray middle"></span></div>
    </div>
    
    <div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
      <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="money_x">*</span><span class="black middle">期望贷款金额（元）：</span></div>
      <div style="width:220px; height:30px; float:left;">
        <input id="loanMoneyB" name="financeApply.loanMoney" type="text"  tabindex="5" maxlength="20" size="30" class="colorful1"  onblur="chkLoanMoneyB(this);funFormat(this);"/>
      </div>
      <div id="tip-loanMoneyB" style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;<span></span>&nbsp;<span id="loanMoneyB_span" class="gray middle"></span></div>
    </div>
    
    <div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
      <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="timeLen_x">*</span><span class="black middle"> 期望贷款期限（月）：</span></div>
      <div style="width:220px; height:30px; float:left;">
        <input id="loanTimeLenB" name="financeApply.loanTimeLen" type="text"  tabindex="6" class="colorful1"  size="30" onblur="chkLoanTimeB(this)"/>
      </div>
     <div id="tip-loanTimeLenB" style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;<span></span>&nbsp;<span id="loanTimeLenB_span" class="gray middle"></span></div>
    </div>
    
    <div style="width:919px; height:70px; padding:20px 0px 0px 80px;">
      <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">借款用途：</span></div>
      <div style="width:400px; height:30px; float:left;">
        <textarea id="remarkB" name="financeApply.remark"  class="colorful1"  tabindex="7" rows="3" cols="31" style="resize: none; height:100px;padding:10px;"></textarea>
      </div>
      <div id="tip-remark" style="width:400px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;</div>
    </div>
    
    
     <div style="width:919px; height:30px; padding:70px 0px 0px 80px;">
      	  <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">验证码：</span></div>
           <div style="width:100px; height:30px; float:left;">
          	<input type="text" id="checkCodeB" name="checkCodeB" tabindex="8" size="8"style="width:80px;" maxlength="4" class="colorful1" placeholder="输入验证码" onkeyup="business_validatCheckCode(this)" />
          </div>
           
          <div style="width:60px; height:29px; float:left; padding-top:1px ;margin-left:10px;"><img width="60" height="35" id="loginCode1" class="verify-code" alt="点击更换" title="点击更换" onclick="javascript:refresh(document.getElementById('loginCode1'));"  src="${base}/getCode.do" style="cursor:pointer;" /></div>
          <div style="width:200px; height:24px; float:left; padding-top:6px">&nbsp;<span class="normal" style="cursor:pointer;color:#0096c4;width:100px;" onclick="javascript:refresh(document.getElementById('loginCode1'));">看不清，换一张</span>&nbsp;&nbsp;<span class="orange middle"style="width:100px;" id ="business_spancheckCode"></span></div>
    </div> 
    
     
    <span id="typeCode" style="display:none"></span>
    <div style="width:799px; height:30px; padding:20px 0px 0px 200px;">
     
       </div>
    <div style="width:699px; height:60px; padding:20px 0 0 420px; "> <input type="submit"  class="buttonoblue1" style="font-size:18px; padding:6px 18px;cursor: pointer;" id="addAplyBtn" value="立&nbsp;即&nbsp;申&nbsp;请 "  tabindex="7" /> </div>
  </div>
</div>
</div>
</form>
    	</div>
    	</div>
 </div>   	
    	<!-- 企业借款结束-->
    	
    	
    	
    	
    	
    	
    	
    	
    	<!-- start:个人担保贷(---------已作废---------)-->
    <!--<div class="list-way1">
    	<div class="list-way-top">
        	<div class="img">
            	<div class="pic"></div>
            </div>
            <div class="cont">
            	<div class="tit">
                	<h3 class="title">个人担保贷</h3>
                    <div class="iconall">
                    	<ul class="sidenav">
                        	<span>用途：</span>
                         	<li><a href="#" class="icon5"></a><div class="sidnext5">紧急：金额较大且要求借款资金很短时间内到账的情况</div></li>
                            <li><a href="#" class="icon6"></a><div class="sidnext6">定制：有些特殊的担保方式或特殊的借款需求可与贷款机构协商</div></li>
                            <li><a href="#" class="icon7"></a><div class="sidnext7">购房：购买新房或二手房</div></li>
                            <li><a href="#" class="icon8"></a><div class="sidnext8">购车：购车或车位</div></li>
                            <li><a href="#" class="icon9"></a><div class="sidnext9">其他</div></li>
                        </ul>
                    </div>
                </div>
                <div class="txt wad_txtspec">
                   <p><span class="con">优势：</span><em class="wad_conem">担保方式灵活多样，需求可定制，适合借款需求复杂的借款者；承担利率一般8%左右</em><em>客户受理面最广，很多向银行申请不到贷款的客户能在这些机构申请成功</em><em>贷款额度较高，一般都能满足借款者需求</em></p>
                   <p class="wad_conp">我们为您优选合作机构，替您完成琐碎的手续，让您以最快速度获得贷款</p>
                </div>
            </div>
        </div>
        <div class="butt">
        	<div class="ljsq">
            	<a href="${base}/financePurchase/applyPerFinancePurchase.do?personal_loan=4" class="btn1">立即申请</a>
            </div>
        </div>
        
    </div>-->
    	<!-- end:个人担保贷-->
    	<!-- start:个人银行贷-->
   <!--  <div class="list-way1 list-way-three">
    	<div class="list-way-top">
        	<div class="img">
            	<div class="pic pic1"></div>
            </div>
            <div class="cont">
            	<div class="tit">
                	<h3 class="title title1">个人银行贷</h3>
                    <div class="iconall">
                    	<ul class="sidenav">
                        	<span>用途：</span>
                         	<li><a href="#" class="icon10"></a><div class="sidnext10">授信：即借款人目前无用款需求，提前获得银行贷款额度，待有用款需求时，一般1-2个工作日就能得到资金。额度有效期最长可达25年，一劳永逸</div></li>
                            <li><a href="#" class="icon11"></a><div class="sidnext11">信用：银行机构也提供信用贷款，但对个人资产情况及信用情况要求极高</div></li>
                            <li><a href="#" class="icon12"></a><div class="sidnext12">购房：购车</div></li>
                            <li><a href="#" class="icon13"></a><div class="sidnext13">购车：购买新房或二手房</div></li>
                            <li><a href="#" class="icon14"></a><div class="sidnext14">经营：公司需要资金周转</div></li>
                            <li><a href="#" class="icon15"></a><div class="sidnext15">其他</div></li>
                        </ul>
                    </div>
                </div>
                <div class="txt txtspec wad_txtspec">
                   <p><span class="con">优势：</span><em class="wad_conem">贷款利率最低。您承担利率一般6%左右</em><em>产品便捷，借款者按需提款，提供一次申请、多年循环使用的产品</em></p>
                   <p class="wad_conp">由我们为您选择最适合您情况的银行，帮您完成繁琐的手续，让您以最低成本获得贷款。</p>
                </div>
            </div>
        </div>
        <div class="butt">
        	<div class="ljsq">
            	<a href="${base}/financePurchase/applyPerFinancePurchase.do?personal_loan=5" class="btn2">立即申请</a>
            </div>
        </div>
        
    </div>-->
    <!-- start:个人银行贷-->
	
</div>
</div>


       <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">	
	</body>
</html>