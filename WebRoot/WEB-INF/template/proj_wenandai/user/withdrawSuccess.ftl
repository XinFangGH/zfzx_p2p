<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 提现页面</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="个人中心", m2="个人主页", m3="";</script>



</head>
<body >

<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">





 <div class="main">
  
	<input type="hidden" id="path" name="path" value="${base}"/>
    <div style="width:999px; height:550px; border:1px solid #e0e0e0; margin:10px auto;">
      <div style="float:left; width:999px; height:400px; margin:0px 0px 0px 0px;">
        <div style="width:999px; height:55px; padding:0px 0px 0px 0px;">
          <div style="width:899px; float:left; height:35px; padding:20px 0px 0px 100px;"><span class="blue big">提现结果页面</span></div>
        </div>
        <div style="width:910px; height:1px; padding:0px 40px;">
          <hr class="splitline" />
        </div>
        
        
        <div style="width:919px; height:30px; padding:30px 0px 0px 80px;">
	        
	        <div style="width:919px; height:30px; padding:30px 0px 0px 80px;">
	          <span class="black big" id="authentication" style="vertical-align: middle; cursor:pointer;" onclick="intostep3('${base}')">
	 				<#if ResultCode!="88">
						${message}，返回<a href="${base}/financePurchase/withdrawFinancePurchase.do"><span class="blue big">提现页面</span></a>，或直接进入<a href="${base}/user/getBpCustMember.do"><span class="blue big">个人资料</span></a>
					<#else>
						${message}，进入<a href="${base}/user/getBpCustMember.do"><span class="blue big">个人资料</span></a><br/>
						<span class="blue big">成功提现：${Amount}元</span> 
					</#if>
	
				</span>
	      </div>
	    </div>
				
    </div>
  </div>
</div>

<!--end: main -->

	</body>
</html>