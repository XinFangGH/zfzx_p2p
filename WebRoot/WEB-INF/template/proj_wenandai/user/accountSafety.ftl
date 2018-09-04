<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 账户安全</title>
    <meta name="description" content="${systemConfig.metaTitle} - 账户安全,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 账户安全,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

<script type="text/javascript">var m1="个人中心", m2="个人主页", m3="账户安全";</script>
<style type="text/css">
.f-l {  float: left; display: inline; }
.f-r { float: right; display: inline; }
.pos-r {  position: relative; }
.pos-a {  position: absolute; }
.hide { display: none; }
.last { border: none; }
.dis_b { display: block; }
.dis_i { display: inline; }
.dis_ib { display: inline-block; }


.m-t_5 { margin-top: 5px; }
.m-r_5 { margin-right: 5px; }
.m-b_5 { margin-bottom: 5px; }
.m-l_5 { margin-left: 5px; }
.m-t_10 { margin-top: 10px; }
.m-r_10 { margin-right: 10px; }
.m-b_10 { margin-bottom: 10px; }
.m-l_10 { margin-left: 10px; }
.m-t_15 { margin-top: 15px; }
.m-r_15 { margin-right: 15px; }
.m-b_15 { margin-bottom: 15px; }
.m-l_15 { margin-left: 15px; }
.m-t_20 { margin-top: 20px; }
.m-r_20 { margin-right: 20px; }
.m-b_20 { margin-bottom: 20px; }
.m-l_20 { margin-left: 20px; }
.m-t_25 { margin-top: 25px; }
.m-r_25 { margin-right: 25px; }
.m-b_25 { margin-bottom: 25px; }
.m-l_25 { margin-left: 25px; }
.m-t_30 { margin-top: 30px; }
.m-r_30 { margin-right: 30px; }
.m-b_30 { margin-bottom: 30px; }
.m-l_30 { margin-left: 30px; }
.m-t_35 { margin-top: 35px; }
.m-r_35 { margin-right: 35px; }
.m-b_35 { margin-bottom: 35px; }
.m-l_35 { margin-left: 35px; }
.m-t_40 { margin-top: 40px; }
.m-r_40 { margin-right: 40px; }
.m-b_40 { margin-bottom: 40px; }
.m-l_40 { margin-left: 40px; }
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
    <!--<div class="ucvline"></div>-->
    <!-- 右侧主体内容 start  右侧内容宽740px -->
    <div class="ucrightdiv">
			<!-- 我的个人信息 start -->
			<div style="width:640px; height:40px; padding:10px 0px 0px 2px;">
		    	<img src="${base}/theme/${systemConfig.theme}/images/spicon3.jpg" style="vertical-align:middle; margin:0px 20px 7px 0px;" /><span class="blue large">账户安全</span>
		    </div>
		    <div class="dashedline"></div>
			<div style="width:680px; padding:20px 4px; overflow:hidden;" class="account-info">
		    	<!--此处放置内容-->
		    	 <#include "/WEB-INF/template/common/user/userCertInfo.ftl">
		    	<!--放置内容结束-->
			</div>
		</div>
	</div>			
<!--end: main -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>