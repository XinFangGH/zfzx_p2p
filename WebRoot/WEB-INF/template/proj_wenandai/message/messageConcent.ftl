<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 站内信</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript">var m1="个人中心", m2="我的消息", m3="";</script>
</head>
<body >
 <!-- header --><!-- navbar -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
	<div class="main">
	<div class="user-cont">
	<!--start: 左侧页面-->
	 	<div class="user-name-nav fl">
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
	 		<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">  
        <!-- 左侧三级导航结束 end-->
 		</div>
    <!-- 左侧页面 end -->
    	<!-- 我的个人信息 start -->
     <div class="user-cont-fr fr">
	   	<div class="user-cont-right"  style="min-height:500px;">
            <div class="tab-list">
            	<h3 class="zqqd-tit">
					<a href="${base}/message/getUserAllOaNewsMessage.do" style="float:left;font-size:18px;">消息</a>
					<a href="${base}/message/getUserAllOaNewsMessage.do"><em class="back"></em>返回</a>
				</h3>
            	<div class="hurong-list" style="padding:0;">
            		<#if OaNewsMessagerinfo!=null>
					<div class="gray-box">
						<p class="fa-tit">标题：<#if OaNewsMessagerinfo.title!=null>${OaNewsMessagerinfo.title}<#else></#if></p>
						<p class="fa-time">
							<span>发送人：<#if OaNewsMessagerinfo.addresser!=null>${OaNewsMessagerinfo.addresser}<#else></#if></span>
							<span>发送时间：<#if OaNewsMessagerinfo.sendTime!=null>${OaNewsMessagerinfo.sendTime}<#else></#if></span>
							<span>类型：<#if OaNewsMessagerinfo.typename!=null>${OaNewsMessagerinfo.typename}<#else></#if></span>
						</p>
					</div>
					<div class="messg-cont">
						<p><#if OaNewsMessagerinfo.content!=null>${OaNewsMessagerinfo.content}<#else></#if></p>
					</div> 
					<#else>
					<div class="gray-box">
						<p class="fa-tit">标题：</p>
						<p class="fa-time">
							<span>发送人：</span>
							<span>发送时间：</span>
							<span>类型：</span>
						</p>
					</div>
					<div class="messg-cont">
						<p></p>
					</div>
					</#if>	          	
		     </div>
            <!--站内信内容结束-->
		    	<!--放置内容结束-->
			</div>
		</div>
	</div>	
	
		</div>
		</div>
	</div>		
<!--end: main -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>