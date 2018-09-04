<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 绑定第三方支付</title>
    <meta name="description" content="${systemConfig.metaTitle} - 绑定第三方支付,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 绑定第三方支付,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/thirdPay.js"></script>
<script type="text/javascript">var m1="个人中心", m2="个人中心", m3="个人资料";</script>



</head>
<body >

<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">





 <div class="main">
  
	<input type="hidden" id="path" name="path" value="${base}"/>
    <div style="width:999px; height:550px; border:1px solid #e0e0e0; margin:10px auto;">
      <div style="float:left; width:999px; height:400px; margin:0px 0px 0px 0px;">
        <div style="width:999px; height:55px; padding:0px 0px 0px 0px;">
          <div style="width:899px; float:left; height:35px; padding:20px 0px 0px 100px;"><span class="blue big">注册绑定结果页面</span></div>
        </div>
        <div style="width:910px; height:1px; padding:0px 40px;">
          <hr class="splitline" />
        </div>
        
        <div style="width:919px; height:1px; padding:30px 0px 0px 80px;">
        	<div class="common-tip" style="display:none;"></div>
         </div>
        <div style="width:919px;  padding:30px 0px 0px 80px;">
	        <form id="registerForm" name="registerForm" action="${base}/pay/bindPay.do" method="post">
		        <div style="width:919px;  padding:30px 0px 0px 80px;">
		 				<#if ResultCode=="88">
							绑定成功！返回<a href="${base}/user/safeBpCustMember.do?safe=all"><span class="blue big">账户安全</span></a>，或直接进入<a href="${base}/user/getBpCustMember.do"><span class="blue big">个人资料</span></a>
						 </div>
						<#else>
						绑定失败：${message}，返回<a href="${base}/user/safeBpCustMember.do?safe=all"><span class="blue big">账户安全</span></a>
						<!--	<#if bandMessage==null>
								${CodeMsg}<#if ResultCode=="09"||ResultCode=="11"||ResultCode=="10">您可以选择下面的方式绑定第三方<#else>，返回<a href="${base}/thirdreg.do"><span class="blue big">绑定第三方页面</span></a></#if>
								 </div><br/><br/>
								<#if ResultCode=="09"||ResultCode=="11">
								<div style="width:919px; height:30px; padding:30px 0px 0px 80px;">
									<input type="hidden" id="telphone" name="telphone" value="${bpCustMember.telphone}"/>
									<div tabindex="4" style="width:120px; height:30px; float: left; margin:8px 0px 0px 65px; padding:5px 0px 0px 0px; font-size:16px;"
						 			   >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${bpCustMember.telphone}</div>
									<div tabindex="4" style="width:120px; height:30px; float: left; margin:8px 0px 0px 65px; padding:5px 0px 0px 0px; font-size:16px;"
						 			 class="buttonorange"    onClick="paybindTelphone()" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机绑定</div>
						 		</div>
					 	 		</#if>
					 	 		<#if ResultCode=="09"||ResultCode=="10">
					 	 		<div style="width:919px; height:30px; padding:30px 0px 0px 80px;">
					 	 			<input type="hidden" id="email" name="email" value="${bpCustMember.email}"/>
					 	 			<div tabindex="4" style="width:120px; height:30px; float: left; margin:8px 0px 0px 65px; padding:5px 0px 0px 0px; font-size:16px;"
						 			   >${bpCustMember.email}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						 	 		<div tabindex="4" style="width:120px; height:30px; float:left; margin:8px 0px 0px 65px; padding:5px 0px 0px 0px; font-size:16px;"
						 			 class="buttonorange"    onClick="paybindEmail()" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱绑定</div>
					 			 </div>
					 			 </#if>
				 			 <#else>
				 			 	${message}，返回<a href="${base}/user/safeBpCustMember.do?safe=all"><span class="blue big">账户安全</span></a>
				 			 </#if> -->
						</#if>
		
		     
	      </form>
	    </div>
				
    </div>
  </div>
</div>

<!--end: main -->

	</body>
</html>