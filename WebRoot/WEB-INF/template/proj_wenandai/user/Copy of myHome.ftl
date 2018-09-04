<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title>${systemConfig.metaTitle} - 个人信息</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/uploadImage.js"></script>
<script type="text/javascript">var m1="个人中心",m2="个人主页",m3="个人主页";</script>
</head>
<body>
<!-- header --><!-- navbar -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 

<div class="main">
	<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
	<div class="ucleftdiv">
    	<!-- 以下内容为左侧三级导航条 -->
		<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->
    <div class="ucvline"></div>
    <!-- 右侧主体内容 start  右侧内容宽740px -->
    <div class="ucrightdiv">
    	<!-- 我的个人信息 start -->
        	<div style="width:640px; height:40px; padding:10px 0px 0px 40px;">
            	<img src="${base}/theme/${systemConfig.theme}/images/spicon1.jpg" style="vertical-align:middle; margin:0px 20px 7px 0px;" /><span class="blue large">我的个人信息</span>
            </div>
            <div class="dashedline"></div>
        	<div style="width:680px; padding:20px 40px; overflow:hidden;" class="account-info">
            	<!--此处放置内容-->
            	<#include "/WEB-INF/template/common/user/userInfo.ftl">
            </div>
        	<!-- 我的个人信息 end -->
            <!-- 资金信息 start -->
        	<div style="width:640px; height:40px; padding:10px 0px 0px 40px;">
            	<img src="${base}/theme/${systemConfig.theme}/images/spicon2.jpg" style="vertical-align:middle; margin:0px 20px 7px 0px;" /><span class="blue large">资金信息</span>
            </div>
            <div class="dashedline"></div>
        	<div style="width:680px; height:100px; padding:20px 40px;">
            	<!--此处放置内容-->					
            	<#include "/WEB-INF/template/common/user/userAccountTotalInfo.ftl">
            </div>
            <!-- 资金信息 end -->
    </div>
    <!-- 右侧主体内容结束 -->
</div>


<!--end: Container -->
       <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">	
	</body>
</html>