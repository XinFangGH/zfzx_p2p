<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 安全保障</title>
    <meta name="description" content="${systemConfig.metaTitle} - 安全保障,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 安全保障,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript">var m1="安全保障",m2="",m3="";</script>
<script type="text/javascript">
$(function(){
	var type = $("#type").text();
	var top = "0px";
	if(type==1){
		top = "400px";
	}else if(type==2){
		top = "1000px";
	}else if(type==3){
		top = "1300px";
	}else if(type==4){
		top = "1500px";
	}
	$("html,body").animate({"scrollTop":top},1000); 
})
</script>
    <script type="text/javascript">
        $(function () {
            $("body").css("width", $(document).width() + "px");
            $(window).resize(function () {
                if ($(window).width() > 1200) {
                    $("body").css("width", $(window).width() + "px");
                } else {
                    $("body").css("width", $(document).width() + "px");
                }
            });
        })
    </script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <span id="type" hidden="true">${type}</span>
<div class="secure-banner"></div>
    <div class="secure-cont">
	<div class="secure-main" style="width:100%;">
		<img src="${base}/theme/proj_wenandai/images/safe/safe_01_1.jpg" style="width: 100%;" alt="">
		<img src="${base}/theme/proj_wenandai/images/safe/safe_02.jpg" style="width: 100%;" alt="">
		<img src="${base}/theme/proj_wenandai/images/safe/safe_03.jpg" style="width: 100%;" alt="">
		<img src="${base}/theme/proj_wenandai/images/safe/safe_04_1.jpg" style="width: 100%;" alt="">
		<img src="${base}/theme/proj_wenandai/images/safe/safe_05.jpg" style="width: 100%;" alt="">

   <#--    <div class="cecure-01"></div>
       <div class="cecure-02"></div>
       <div class="cecure-03"></div>
       <div class="cecure-04"></div>
       <div class="cecure-05"></div>
       <div class="cecure-06"></div>
       <div class="cecure-07"></div>
       <div class="cecure-08"></div>	-->
	</div>
</div>


<!--end: Container -->

     <#-- <#include "/WEB-INF/template/${systemConfig.theme}/layout/footer.ftl">-->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</body>
</html>