<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 手机绑定</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/bindPhone.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>


<script type="text/javascript">var m1="个人账户", m2="账号设置, m3="账户安全";</script>
</head>
<style type="text/css">
	a.getcode.disabled {
		color: #d5d5d5;
		cursor: default;
		background-color: #ededed;
	}
	a.getcode {
		background-color: #f3f3f3;
		border: 1px solid #d5d5d5;
		border-radius: 5px;
		color: #000;
		padding: 4px 19px;
		text-decoration: none;
	}
	
	a.getcode.disabled {
		color: #d5d5d5;
		cursor: default;
		background-color: #ededed;
	}
	
	
</style>

<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">


	<div class="main">
	<!-- 页面分为左右两个部分   -->
	    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
		<div style="float:left;width:229px; padding:0px 10px;">
	    	<!-- 以下内容为左侧三级导航条 -->
				<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar-user.ftl">
	        <!-- 左侧三级导航结束 end-->
	    </div>
	    <!-- 左侧页面 end -->	    
        <div style="float:left; height:700px; padding:10px 0px; width:1px; border:0px; background-color:#e0e0e0;"></div>
	    <!-- 右侧主体内容 start  右侧内容宽740px -->
	    <div style="float:left; width:740px; margin:10px 0px 0px 0px; overflow:hidden;">
    	<!-- 我的个人信息 start -->
        	<div style="width:640px; height:40px; padding:10px 0px 0px 100px;">
            	<span class="blue large">手机绑定</span>
            </div>
            <div class="dashedline"></div>
        	<div style="width:680px; padding:20px 40px; overflow:hidden;" class="account-info">
            	<!--此处放置内容-->
            	<#include "/WEB-INF/template/common/user/bindPhone.ftl">
				<!--放置内容结束-->	
			</div>
		</div>
	</div>



<!--end: main -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>