<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 认证</title>

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="个人中心", m2="个人主页", m3="个人资料";</script>



</head>
<body >

<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">





 <div class="main">
  
	<input type="hidden" id="path" name="path" value="${base}"/>
    <div style="width:999px; height:550px; border:1px solid #e0e0e0; margin:10px auto;">
      <div style="float:left; width:999px; height:400px; margin:0px 0px 0px 0px;">
        <div style="width:999px; height:55px; padding:0px 0px 0px 0px;">
          <div style="width:899px; float:left; height:35px; padding:20px 0px 0px 100px;"><span class="blue big">身份证认证结果页面</span></div>
        </div>
       
        
        <div style="width:919px; height:30px; padding:30px 0px 0px 80px;">
	        
	        <div style="width:919px; height:30px; padding:30px 0px 0px 80px;">
	          <span class="black big" id="authentication" style="vertical-align: middle; cursor:pointer;" onclick="intostep3('${base}')">
				${message}，返回<a href="${base}/user/safeBpCustMember.do?safe=all"><span class="blue big">账户安全</span></a>
				</span>
	      </div>
	    </div>
				
    </div>
  </div>
</div>

<!--end: main -->

	</body>
</html>