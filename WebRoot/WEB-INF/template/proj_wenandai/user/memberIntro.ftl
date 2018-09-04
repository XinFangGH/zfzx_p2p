<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 特权介绍</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/message.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>

<script type="text/javascript">var m1="个人中心", m2="VIP会员", m3="";</script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
	<div class="main-box">
<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
    	<!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
        <!-- 左侧三级导航结束 end-->
 
    <!-- 左侧页面 end -->
       <!-- <div style="float:left; height:700px; padding:10px 0px; width:1px; border:0px; background-color:#e0e0e0;"></div>-->
    <!-- 右侧主体内容 start  右侧内容宽740px -->
    <div class="main-cont">
    	<!-- 我的个人信息 start -->
        	<div class="content">
        		<div style=" height:40px; line-height:40px;margin-bottom:10px; ">
            		<span style="font-size:22px;color: #0096c4;">特权介绍</span><hr width="100%" align="center">
            	</div>
            <div class="tab-list1">
            	<span>会员等级：${levelMark}  &nbsp;&nbsp;&nbsp;   到期时间：${bpCustMember.memberDuedate}</br></span></br>
            	<table border="0" width="100%">
            		<tr>
            			<th height="40" bgcolor="#47B2D6">特权名称</th>
            			<th height="40" bgcolor="#47B2D6">非会员</th>
            			<th height="40" bgcolor="#47B2D6">黄金会员</th>
            			<th height="40" bgcolor="#47B2D6">白金会员</th>
            		</tr>
            		<tr align="center">
            			<td>特权一</td>
            			<td>无</td>
            			<td><font color="red">尊享</font></td>
            			<td><font color="red">尊享</font></td>
            		</tr>
            		<tr align="center">
            			<td>特权二</td>
            			<td>无</td>
            			<td><font color="red">尊享</font></td>
            			<td><font color="red">尊享</font></td>
            		</tr>
            		<tr align="center">
            			<td>特权三</td>
            			<td>无</td>
            			<td><font color="red">尊享</font></td>
            			<td><font color="red">尊享</font></td>
            		</tr>
            		<tr align="center">
            			<td>特权四</td>
            			<td>无</td>
            			<td>无</td>
            			<td><font color="red">尊享</font></td>
            		</tr>
            		<tr align="center">
            			<td>特权五</td>
            			<td>无</td>
            			<td>无</td>
            			<td><font color="red">尊享</font></td>
            		</tr>
            		<tr align="center">
            			<td>年费</td>
            			<td>无</td>
            			<td>每年300元</td>
            			<td>每年600元</td>
            		</tr>
            	</table>
            	<br> <div style="width:699px; height:60px; padding:26px 0 0 380px; ">
						<a href="${base}/user/mbPurchaseBpCustMember.do"><span class="buttonoblue1" style="font-size:18px; padding:6px 18px;cursor: pointer;"   tabindex="8">立刻开通/续费</span></a>
					</div>
            </div>
            
            </div>
           
				
			</div>
<!--end: main -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>