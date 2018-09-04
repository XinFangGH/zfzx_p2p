<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 账户授权</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>

<script type="text/javascript" src="${base}/js/user/recharge.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript">var m1="个人中心", m2="个人主页", m3="账户授权";
function submitLoanAuthorize(){
		$("#loanAuthorizeForm").submit();
	}
</script>
<style type="text/css">

</style>

</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">

	<div class="main" >
		<!-- 页面分为左右两个部分   -->
	    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
	<div class="ucleftdiv">
    	<!-- 以下内容为左侧三级导航条 -->
		<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar-user.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->
    <div class="ucvline"></div>
    <!-- 右侧主体内容 start  右侧内容宽740px -->
    <div class="ucrightdiv">
	    	<!-- 我的个人信息 start -->
        	<div style="width:640px; height:40px; padding:10px 0px 0px 40px;">            	
		<img src="${base}/theme/${systemConfig.theme}/images/spicon1.jpg" style="vertical-align:middle; margin:0px 20px 7px 0px;" /><span class="blue large">账户授权</span>
            </div>
            <div class="dashedline"></div>
        	<div style="width:680px; padding:20px 40px; overflow:hidden;" class="account-info">
        	<form action = "${base}/pay/loanAuthorizePay.do" id="loanAuthorizeForm" method = "post">
            	<!--此处放置内容-->
			<div> 投标授权： <input type="radio" name="bid" <#if bpCustMember.tender==1 &&bpCustMember.tender!=null >checked</#if> value = "1"> 开启 <input type="radio" name="bid"  <#if bpCustMember.tender!=1 ||bpCustMember.tender==null  >checked </#if> value = "0">关闭</div>
			<div> 还款授权： <input type="radio" name="payMoney" <#if bpCustMember.refund==1 && bpCustMember.refund!=null>checked</#if> value = "1"> 开启<input type="radio" name="payMoney" <#if bpCustMember.refund!=1||bpCustMember.refund==null>checked </#if> value = "0">关闭</div>
			<div> 分配授权： <input type="radio" name="secondAllow" <#if bpCustMember.secondAudit==1 && bpCustMember.secondAudit!=null >checked</#if> value = "1"> 开启<input type="radio" name="secondAllow" <#if bpCustMember.secondAudit!=1 ||bpCustMember.secondAudit==null>checked </#if> value = "0">关闭</div>
				<input type="hidden" value="${bpCustMember.moneymoremoreId}" name="rechargeMoneymoremore"/>
				<div tabindex="4" style="width:120px; height:30px; float:left; margin:8px 0px 0px 65px; padding:5px 0px 0px 0px; font-size:16px;"
			  class="buttonorange"    onClick="submitLoanAuthorize()" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提交授权</div>
				<!--放置内容结束-->
			</form>
			</div>
		</div>
	</div>
<!--end: main -->
      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	
	</body>
</html>