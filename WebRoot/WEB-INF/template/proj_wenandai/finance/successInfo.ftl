<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${systemConfig.metaTitle} - 我要融资</title>
    <meta name="description" content="${systemConfig.metaTitle} - 我要融资,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 我要融资,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript">var m1="",m2="",m3="";</script>
<style >
	div{ padding:2px;}
</style>
</head>
<body >
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div style="width:100%; background:#eff3f3; overflow:hidden;">
<div class="main">
    <div class="img"><img src="${base}/theme/${systemConfig.theme}/images/process-bg2.png" /></div>
    <div class="content">
        <div class="rightlist rightlist1" >
            <div class="loanappinfo1" style="text-align:center;">
            	<p style="width:100px;height:80px;float:left; margin-left:140px;"><img class="hidden" src="${base}/theme/${systemConfig.theme}/images/success1.png" width="100"/></p>
            	<div style="float:left; margin-left:20px;padding:10px 0;">
               	 <p style="font-size:22px; font-weight:bold;">信息已提交，请等待审核。。。</p>
               	 <p style="padding-top:10px; font-size:16px; text-align:left;">该页面将在<span id="retrunTime">10</span>秒后返回，<a style="text-decoration:underline;" href="${base}/loan/listP2pLoanProduct.do"><span class="blue">点此立即返回</span></a></p>
            	</div>
            </div>       
        </div>
    </div>
</div>
</div>

<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
	<script type="text/javascript">
	$(document).ready(
    function(){
      $('.regdiv').html("${Session.successHtml}");
    }
);
	</script>
	<script>
		$(document).ready(
		//N秒后返回页面
		function(){
	    timeBank("retrunTime",10,"${base}/loan/listP2pLoanProduct.do");
		}
	);

</script>
</html>