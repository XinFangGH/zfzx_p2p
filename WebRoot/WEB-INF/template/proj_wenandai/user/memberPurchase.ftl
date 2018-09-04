<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 开通/续费</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/message.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/member.js"></script>
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
        		<div style=" height:40px; line-height:40px;margin-bottom:20px; ">
            		<span style="font-size:22px;color: #0096c4;">VIP会员升级/续费</span><hr width="90%" align="center">
            	</div>

            <div class="tab-list">
            	<br>
            	<table style="border-collapse:separate; border-spacing:10px;"  width="90%">
            		<tr>
            			<td>升级/续费用户：${bpCustMember.loginname}</td>
            		</tr>
            		<tr>
            			<td>当前会员等级：${member_grade}<span id="dengji" style="display:none;">${bpCustMember.memberGrade}

</span></td>
            		</tr>
            		<tr>
            			<td>当前会员等级到期日：<span id="nowDuedate">${bpCustMember.memberDuedate}</span></td>
            		</tr>
            		<tr>
            			<td>当前会员等级剩余天数：<span id="day">${quot}</span></td>
            		</tr>
            		<tr>
            			<td>会员等级：<span><input type="radio" value="2"   name="grade" checked="checked">钻石会员(年费<span id="money_2">600</span>元)</span>
            						 <span id="hydj"><input type="radio" value="1"  name="grade">黄金会员(年费<span id="money_1">300</span>元)</span>
            			</td>
            		</tr>
            		<tr>
            			<td>缴费周期：<input type="radio" value="1" name="year" checked="checked">1年
            						<input type="radio" value="2" name="year">2年
            						<input type="radio" value="3" name="year">3年
            						<input type="radio" value="4" name="year">4年
            						<input type="radio" value="5" name="year">5年
            			</td>
            		</tr>
            		<tr>
            			<td>缴费后会员等级到期日：<span id="oldDuedate"></span></td>
            		</tr>
            		<tr>
            			<td>会员等级补齐费用：<span id="buqi"></span>元(结算至当前会员等级到期日)</td>
            		</tr>
            		<tr>
            			<td>会员等级年费费用：<span id="yearMoney"></span>元(年费 X 周期)</td>
            		</tr>
            		<tr>
            			<td>合计费用：<span id="countMoney"></span>元(年费 X 周期)</td>
            		</tr>
            	</table>
            	<br>
            	<br> <div style="width:699px; height:60px; padding:5px 0 0 80px; ">
						<a href="${base}/user/mbPurchaseBpCustMember.do"><span class="buttonoblue1" style="font-size:18px; padding:6px 18px;cursor: pointer;"   tabindex="8">立刻支付,立刻尊享特权</span></a>
					</div>
            </div>
            
            </div>
           
				
			</div>
<!--end: main -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>