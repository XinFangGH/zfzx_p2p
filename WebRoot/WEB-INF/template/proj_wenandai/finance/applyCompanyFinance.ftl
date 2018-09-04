<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${systemConfig.metaTitle} - 企业借款</title>
    <meta name="description" content="${systemConfig.metaTitle} - 企业借款,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 企业借款,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript" src="${base}/js/user/financeCheck.js"></script>
<script type="text/javascript">var m1="",m2="",m3="";</script>
<script type="text/javascript">
$(function(){
	$.divselect(".divselect");
});
</script>

</head>
<body >
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<!--整体布局
<div class="docment docment-711-234">-->
<div class="container-wrap">
	

<div class="container clearfix">

<!-- 头部结束 -->
<div style="width:100%; background:#eff3f3;">
 <div class="main">
	<div style="width:100px; height:30px; line-height:30px;background:#595757; color:#fff;text-align:center;">企业借款</div>
	<form id="applyForm" action="${base}/financePurchase/saveApplyFinancePurchase.do" method="post" onsubmit="return saveCom()" accept-charset="utf-8">
 	<input type="hidden" name="financeApply.productName" value="非注册企业借款">
	 <input type="hidden" name="financeApply.type" value="1">
	  <input type="hidden" name="financeApply.state" value="0">
    <div style="width:999px; height:900px;  border:1px solid #e5e5e5;margin:10px auto;background:#fff;">
      <div style="float:left; width:999px; height:auto; margin:0px 0px 0px 0px;">
      	<div style="height:102px; background:#def3d4; margin:50px 100px 20px 100px; padding:10px 54px;">
      		<div style="line-height:26px; color:#006d30;font-size:15px;">◆ 我们为您提供专业的贷款代理、贷款咨询服务，根据公司的借款需求、信用情况以及资产情况，为您从
我们的合作贷款机构中挑选出最适合您的机构及产品，并为您代办各种复杂手续。</div>
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
            <input id="businessName" name="financeApply.businessName" type="text" tabindex="1" maxlength="20" size="30" class="colorful1"  onblur="chkBusiness(this)"/>
            <input id="" name="financeApply.area" type="hidden" value="北京"/>
          </div>
          <div id="tip-businessName"  style="width:400px; margin-left:40px;  height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span id="businessName_span" class="gray middle"></span></div>
        </div>
		<div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
          <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">所在地区：</span></div>
          <div style="width:220px; height:30px; float:left;"><span style="display:inline-block;margin:7px 0 0 2px; color:#807a62; font-size:14px;">北京</span></div>
          <div id="tip-area" style="width:400px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span class="red middle" id ="area_x"></span><span id="area_span" class="gray middle"></span></div>
        </div>
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
          <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="money_x">*</span><span class="black middle">期望贷款金额（元）：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="loanMoney" name="financeApply.loanMoney" type="text"  tabindex="3" maxlength="20" size="30" class="colorful1"  onblur="chkLoanMoney(this);funFormat(this);"/>
          </div>
          <div id="tip-loanMoney" style="width:390px;margin-left:40px; height:24px; float:left; padding-top:6px">&nbsp;<span></span>&nbsp;<span id="loanMoney_span" class="gray middle"></span></div>
        </div>
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
          <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="timeLen_x">*</span><span class="black middle"> 期望贷款期限（月）：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="loanTimeLen" name="financeApply.loanTimeLen" type="text"  tabindex="4" class="colorful1"  size="30" onblur="chkLoanTime(this)"/>
          </div>
          <div id="tip-loanTimeLen" style="width:390px;margin-left:40px; height:24px; float:left; padding-top:6px">&nbsp;<span></span>&nbsp;<span id="loanTimeLen_span" class="gray middle"></span></div>
        </div>
        <#--<div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
          <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="money_x">*</span><span class="black middle">期望放款日期：</span></div>
          <div style="width:220px; height:30px; float:left;">
           <div class="divselect" >
			      <cite><span>一周以内</span><!--已经在js里面写好了，只需给name赋值就可以
  				<input name="" type="hidden" value="" /></cite>
			      <ul>
			      	<li><a class="selt" href="javascript:;" selectid="1">一周以内</a></li>
					 <li><a class="selt" href="javascript:;" selectid="1">两周以内</a></li>
			         <li><a class="selt" href="javascript:;" selectid="2">一个月以内</a></li>
			         <li><a class="selt" href="javascript:;" selectid="3">两个月以内</a></li>
			         <li><a class="selt" href="javascript:;" selectid="4">其他</a></li>
			        
			      </ul>
			  </div>
			  
          </div>
          <div id="tip-area" style="width:400px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span class="red middle" id ="area_x"></span><span id="area_span" class="gray middle"></span></div>
        </div>-->
        <div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
          <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="link_x">*</span><span class="black middle">联系人：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="linkPersion" name="financeApply.linkPersion" type="text" tabindex="1" maxlength="20" size="30" class="colorful1"  onblur="chkLoanPerson(this)"/>
          </div>
          <div id="tip-linkPersion"  style="width:400px; margin-left:40px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span id="linkPersion_span" class="gray middle"></span></div>
        </div>
        <div style="width:919px; height:30px; padding:20px 0px 0px 80px;">
          <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="red middle" id ="phone_x">*</span><span class="black middle">手机号码：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="phone" name="financeApply.phone" type="text" tabindex="2" maxlength="20" size="30" class="colorful1"  onblur="chkLoanPersonPhone(this)"/>
          </div>
          <div id="tip-phone" style="width:400px; margin-left:40px;  height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span id="phone_span" class="gray middle"></span></div>
        </div>
        <div style="width:919px; height:70px; padding:20px 0px 0px 80px;">
          <div style="width:170px; height:27px; float:left; text-align:right; padding-top:3px"><span class="black middle">其他说明：</span></div>
          <div style="width:400px; height:30px; float:left;">
            <textarea id="remark" name="financeApply.remark"  class="colorful1" rows="3" cols="40" style="resize: none; height:100px;padding:10px;"></textarea>
          </div>
          <!--<div id="tip-remark" style="width:400px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;</div>-->
        </div>
        
        <div style="width:919px; height:30px; padding:70px 0px 0px 80px;">
          	  <div style="width:170px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">验证码：</span></div>
               <div style="width:100px; height:30px; float:left;">
              	<input type="text" id="checkCode" name="checkCode" tabindex="3" size="8"style="width:80px;" maxlength="4" class="colorful1" placeholder="输入验证码" onkeyup="validatCheckCode(this)" />
              </div>
               
              <div style="width:60px; height:29px; float:left; padding-top:1px ;margin-left:10px;"><img width="60" height="35" id="loginCode" class="verify-code" alt="点击更换" title="点击更换" onclick="javascript:refresh(document.getElementById('loginCode'));"  src="${base}/getCode.do" style="cursor:pointer;" /></div>
              <div style="width:200px; height:24px; float:left; padding-top:6px">&nbsp;<span class="normal" style="cursor:pointer;color:#0096c4;width:100px;" onclick="javascript:refresh(document.getElementById('loginCode'));">看不清，换一张</span>&nbsp;&nbsp;<span class="orange middle"style="width:100px;" id ="spancheckCode"></span></div>
        </div>
        
        
        
        <div style="width:699px; height:60px; padding:30px 0 0 420px; "> <input type="submit" class="buttonoblue1" style="font-size:18px; padding:6px 18px;cursor: pointer;" id="addAplyBtn" value="立&nbsp;即&nbsp;申&nbsp;请 "  tabindex="7" /> </div>
      </div>
    </div>
  </div>
</form>
</div>

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>