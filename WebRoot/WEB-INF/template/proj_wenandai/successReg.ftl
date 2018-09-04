<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 手机验证</title>
    <meta name="description" content="${systemConfig.metaTitle} - 手机验证,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 手机验证,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/bindPhone.js"></script>
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/uploadImage.js"></script>
<script type="text/javascript">var m1="";var m2="";var m3="";</script>
<script type="text/javascript">	
$(function(){
		//图标下拉框  提示框
	var content = $(".sidnext").html();	
	$('.icon-one').pt({
		position: 'b',
		width:300,
		content: content
	});	  
});
</script>
</head>
<body >
  <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <div style="background:#EFF3F4;width:100%;">
  <div class="main">
  
	<form id="registerForm" action="${base}/signreg.do" method="post">
	<input type="hidden" id="token" value="${token}"/> 
    <div style="width:990px; height:700px; background:#fff; margin:10px auto;">
      <div style="float:left; width:999px; height:400px; margin:0px 0px 0px 0px;">
        <!--<div style="width:970px; height:100x; margin:10px auto 0px auto;"><img src="${base}/theme/${systemConfig.theme}/images/loginstep1.jpg" /></div>-->
        <div style="width:999px; height:100px; padding:0px 0px 0px 0px;">
          <div style="width:784px; float:left; height:160px; padding:40px 0px 0px 215px;">
          	<img class="hidden" src="${base}/theme/${systemConfig.theme}/images/reg_pic3.jpg" />
          </div>
         
        </div>
        <div style="width:650px; height:35px; line-height:35px;margin:150px 0 0 180px; text-align:center; ">
		<span class="black" style="font-size:34px;">您已成功注册<span style="color:#72af43;font-size:34px;"> 升升投 </span>。</a>
		</div>
		<div style="width:990px; height:30px; padding:60px 0px 0px 158px;" class="reg-div">	
			<p class="middle"style=" padding:0 0 0 10px;float:left;">在进行投资理财或申请借款之前，需要 <span class="middle"style="color:#73b9d7;">去开通资金账户<span style="padding-top:10px;width:15px; height:15px;display:inline;" class="icon-one"><img class="hidden" src="${base}/theme/${systemConfig.theme}/images/wh.png" /></span>， </span>您也可以 <span class="middle"style="color:#73b9d7;">完善用户资料 </span>以提升认证等级。</p>
         <div class="sidnext">资金托管账户是理财人和借款人分别在资金托管机构（${payType}支付）开立的独立账户，资金由理财人和借款人直接对接，避免了资金被平台挪用的风险。</div>
        </div>
         <div style="width:740px; height:90px; padding:60px 0px 0px 240px">
          <div style="float:left; margin-right:20px;"> <a href="${base}/thirdreg.do?retUrl=/thirdreg.do"><span class="buttonblue3" style="font-size:16px; padding:5px 18px;cursor: pointer;border:1px solid #78c7e0;color:#0096c4;" name="tabSwitch">去开通资金托管账户</span></a></div>
          <div style="float:left; margin-right:20px;"> <a href="${base}/user/getBpCustMember.do?typ=1&retUrl=/user/getBpCustMember.do?typ=1"><span class="buttonblue3" style="font-size:16px; padding:5px 18px;cursor: pointer;border:1px solid #78c7e0;color:#0096c4;" name="tabSwitch">去后台完善资料</span></a></div>
          <div style="float:left; "> <a href="${base}/creditFlow/financingAgency/listPlBidPlan.do?Q_proType_S_LK=Dir&retUrl=/creditFlow/financingAgency/listPlBidPlan.do?Q_proType_S_LK=Dir"><span class="buttonblue3" style="font-size:16px; padding:5px 18px;cursor: pointer;border:1px solid #78c7e0;color:#0096c4;" name="tabSwitch">去浏览理财产品</span></a></div>
          </div>
        
        
      </div>
    </div>
  </div>
</form>
<div style="height:4px; border-bottom:1px #f0f0f0 solid; margin:15px auto 0px auto;" title="横线"></div>
<!-- end main --> 
    </div>
    <!--login form end -->
<!--end: Container -->


</div>
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	</body>
</html>