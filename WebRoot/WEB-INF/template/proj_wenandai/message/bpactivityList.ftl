<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 积分兑换</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/message.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/editUserInfo.js?version=1638120"></script>
<script type="text/javascript">var m1="个人中心", m2="我的积分", m3="";</script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<div class="main">
	<div class="user-cont">
		<div class="user-name-nav fl">
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
        <!-- 左侧三级导航结束 end-->
        </div>
 
    <!-- 左侧页面 end -->
       <!-- <div style="float:left; height:700px; padding:10px 0px; width:1px; border:0px; background-color:#e0e0e0;"></div>-->
    <!-- 右侧主体内容 start  右侧内容宽740px -->
	<div  class="user-cont-fr fr">
	    <div class="user-cont-right">
    		<h2 class="big-tit">积分兑换</h2>
    	<!-- 我的个人信息 start -->
        	<div class="cont-list contents">
        		<div class="head-num" style="padding:30px 20px;">
	   				<div class="num-icon fl" style="width:220px;">
	   					<div class="icon-img icon-img-four fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">可用积分（分）</p>
	   						<p class="middle"><em  id="usableRecordNumber">${usableRecordNumber}</em>分</p>
	   					</div>
	   				</div>
	   				<div class="num-icon fl" style="width:220px;">
	   					<div class="icon-img icon-img-three fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">累计积分（分）</p>
	   						<p class="middle"><em id="totalRecordNumber">${totalRecordNumber}</em>分</p>
	   					</div>
	   				</div>
	   				<div class="num-icon fl" style="width:220px;">
	   					<div class="icon-img icon-img-two fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">已用积分（分）</p>
	   						<p class="middle"><em id="useRecordNumber">${useRecordNumber}</em>分</p>
	   					</div>
	   				</div>
	   			</div>
	   			<div class="solidline"></div>	
        			<!--新样式-->
	        	<div class="trade-credits">
                <ul>
                    <#list pager.list as list>
	                    <li>
	                    <#if list.couponType==1>
	                    	<!--返现券-->
	                    	<h5 class='kee_bg'></h5>
	                    </#if>
	                    <#if list.couponType==2>
	                    	<!--体验券-->
	                    <h5 class='kee_bg1'></h5>
	                    </#if>
	                    <#if list.couponType==3>
	                    	<!--加息券-->
	                    <h5 class='kee_bg2'></h5>
	                    </#if>
						</h5>
	                    <p class='large'><#if list.couponType==3>${list.parValue}%<#else><em>￥</em>${list.parValue}</#if></p>
						<p class='day'>有限期：<span>${list.validNumber}天</span></p>
						<p class='jifen'>需要：${list.needIntegral} 积分</p>
						<p class='btn'><a href='#' onClick="activitybutton(${list.activityId},${list.needIntegral})">立即兑换</a></p></li>
                    </#list>
                    </ul>
             </div>
         </div>
       <div>	 
            <#import "/WEB-INF/template/common/pager.ftl" as p>
  			<#assign parameterMap = {} />
  			<@p.pager pager = pager baseUrl = "/activity/activityAllListBpActivityManage.do"  parameterMap = parameterMap />
	</div>	
</div>
</div>
</div>
</div>
<!--end: main -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>