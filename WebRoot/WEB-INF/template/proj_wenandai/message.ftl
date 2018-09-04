<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle}</title>
    <meta name="description" content="${systemConfig.metaTitle} ,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} ,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/thirdPay.js"></script>
<script type="text/javascript">var m1="个人中心", m2="个人中心", m3="个人资料";</script>

<script>

$(document).ready(
	//N秒后返回页面
	function(){
    timeBank("retrunTime",10,"${webMsg.url}");
	}
);

</script>

</head>
<body >

<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">

<#if (webMsg.type=="0")>
 <div class="main">
    <div style="height:250px; border:1px solid #e0e0e0; margin:50px auto; background:#fff;">
      <ul style="width:760px; height:250px; margin:50px auto;font-size:16px;">
         <li style="float:left;"><img src="${base}/theme/${systemConfig.theme}/images/<#if (webMsg.code =="8888")>success1.jpg<#else>notsuccess1.jpg</#if>" width="100"/></li><!--未修改成功的图片名称为notsuccess.jpg-->
         <li  style="float:left;padding:10px 0 0 20px;">
           <p class="bold bigger">${webMsg.desc}</p>
           <p>该页面将在<span id="retrunTime">10</span>秒后返回，<a style="text-decoration:underline;" href="${webMsg.url}"><span class="blue">点此立即返回</span></a></p>
         </li>
      </ul>
    </div>
    </#if>
    <#if (webMsg.type=="1")>
    <div style="width:800px; height:400px; border:1px solid #e0e0e0; margin:50px auto;">
      <ul style="width:500px; height:400px; margin:50px auto;font-size:16px;">
         <li style="float:left;"><img src="${base}/theme/${systemConfig.theme}/images/user/<#if (webMsg.code =="8888")>success.jpg<#else>notsuccess.jpg</#if>" /></li><!--未修改成功的图片名称为notsuccess.jpg-->
         <li style="float:left;">
           <p class="bold bigger">${webMsg.desc}</p>
           <#if (webMsg.remark1==null)><#else><p>项目名称：<span>${webMsg.remark1}</span></p></#if>
           <#if (webMsg.remark2==null)><#else><p>项目编号：<span>${webMsg.remark2}</span></p></#if>
           <#if (webMsg.remark3==null)><#else><p>年化利率：<span>${webMsg.remark3}</span></p></#if>
           <#if (webMsg.remark4==null)><#else><p>投标金额：<span>${webMsg.remark4?string(',###.00')}</span></p></#if>
           <#if (webMsg.remark5==null)><#else><p>订单编号：<span>${webMsg.remark5}</span></p></#if>
           <p>该页面将在<span id="retrunTime">10</span>秒后关闭，<a style="text-decoration:underline;  href="${webMsg.url}"><span class="blue">点此立即返回</span></a></p>
         </li>
      </ul>
    </div>
    </#if>
<#--<#include "/WEB-INF/template/${systemConfig.theme}/layout/partner-pic.ftl">    -->
<#--<#include "/WEB-INF/template/${systemConfig.theme}/layout/partner-text.ftl">-->	
</div>
<!--end: main -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl"> 
</body>
	
	    

</html>