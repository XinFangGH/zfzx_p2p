<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 我的资料</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="个人中心", m2="个人中心", m3="个人资料";</script>
</head>
<body >

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
			<!-- 右侧主体内容 start  右侧内容宽740px -->
    	<div style="float:left; width:740px; margin:10px 0px 0px 0px; overflow:hidden;">
    	<!-- 提示信息 start -->
        	<div style="width:640px; height:40px; padding:10px 0px 0px 40px;">
            	
<img src="${base}/theme/${systemConfig.theme}/images/spicon2.jpg" style="vertical-align:middle; margin:0px 20px 7px 0px;" /><span class="blue large">提示信息</span>
            </div>
            <div class="dashedline"></div>
			<div style="width:680px; padding:20px 40px; overflow:hidden;" class="account-info">
				<ul class="certname">
						<span  class="big black">您的个人资料已经修改成功</span>
						
						<span  class="big black">您可以前往</span>
						<a href="${base}/user/getBpCustMember.do?typ=1"><span  class=" big middle blue">[个人资料]</span></a>
						<span class="big black">查看详情。</span>
				</ul>
			</div>
		</div>
	</div>
<!-- 提示信息 end -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>