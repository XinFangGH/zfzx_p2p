<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>${systemConfig.metaTitle} - 绑定银行卡</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/detailsFunds.js"></script>
<script type="text/javascript" src="${base}/js/user/withdraw.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript">var m1="个人中心", m2="账户总览", m3="银行卡列表";</script>
<script type="text/javascript">
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200 });		
	});
</script>
<style type="text/css">
    .tab-cont .bd table th {background:#e6f4fd}
    .account-info .set-btn {background:#55b14a}
    .account-info .wad_btnhover:hover {background:#55b14a}
</style>
</head>
<body >
<div>
 <!-- header --><!-- navbar -->
 	<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">


<div class="main-box">

	<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
    	<!-- 以下内容为左侧三级导航条 -->
		 	<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
        <!--  左侧三级导航结束 end-->
    <!-- 左侧页面 end -->
    <!--<div class="ucvline"></div>-->
    <!-- 右侧主体内容 start  右侧内容宽740px -->
    	<div class="main"   style="wiadth:1001px; margin:10px auto; overflow:hidden; background:#fff; border:1px solid #dcdcdc;">
    	<!-- 我的个人信息 start -->
        	<div style="width:980px; height:35px; padding:20px 0px 0px 20px;">
            	<span style="font-size:22px;color: #0096c4;">银行卡列表</span>
            </div>
            <div class="dashedline2"></div>
            <div style="width:900px; padding:20px 0px; overflow:hidden;margin:0 auto;" class="account-info">
            	<!--此处放置内容-->
            	
            	<#include "/WEB-INF/template/common/loan/bankCardBind.ftl">
            	
				<!--放置内容结束-->		
			</div>	
		</div>
	</div>
</div>
</div>

			
<!--end: main -->


      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</div>

	
	
	</body>
</html>