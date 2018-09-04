<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>${systemConfig.metaTitle} - 借款申请</title>
    <meta name="description" content="${systemConfig.metaTitle} - 借款申请,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 借款申请,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/detailsFunds.js"></script>

<script type="text/javascript" src="${base}/js/Calendar3.js"></script>

<script type="text/javascript">var m1="个人中心",m2="个人主页",m3="借款申请";</script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 


<div class="main">
<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
	<div class="ucleftdiv2">
    	<!-- 以下内容为左侧三级导航条 -->
		<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->
    <!--<div class="ucvline"></div>-->
    <!-- 右侧主体内容 start  右侧内容宽740px -->
    <div class="ucrightdiv2">
    	<!-- 我的个人信息 start -->
        	<div style="width:780px; height:40px; padding:10px 0px 0px 0px;">
            	<span class="blue large">借款申请</span>
            </div>
            <div class="dashedline2"></div>
        	<div style="width:780px; padding:20px 0px; overflow:hidden;" class="account-info">
            	<!--此处放置内容-->
					
					<table width="100%" class="tab_css_3" border="0" cellpadding="0"  cellspacing="0">
						<tr>
							<th>序号11</td>
							<th>借款产品</td>
							<th>借款标题</td>
							<th>借款金额</td>
							<th>年化利率</td>
							<th>借款期限</td>
							<th>申请时间</td>
							<th>申请/审核状态</td>
						</tr>
						<#list listApplyUser as loan>
						<tr>
							<td><span class="normal">${loan.loanId}</span></td>
							<td><span class="normal">${loan.productName}</span></td>
							<td><span class="normal">${loan.loanTitle}</span></td>
							<td><span class="normal">${loan.loanMoney}</span></td>
							<td><span class="normal">${loan.expectAccrual}</span></td>
							<td><span class="normal">${loan.loanTimeLen}</span></td>
							<td><span class="normal">${loan.createTime}</span></td>
							<td><span class="normal">
								<#if loan.state=='0'>
									<#if loan.finishStatus==''>
										<a href="${base}/financePurchase/getNodeapplyUser.do?id=${loan.loanId}">|${loan.finishStatus}|资料填写中,请继续</a>
										<a href="javascript:if(confirm('提示：确定要终止审请吗?')){window.location.href='${base}/financePurchase/endApplyapplyUser.do?id=${loan.loanId}'}">终止申请</a>
									<#else>
										手机端申请，请到手机端完成<br/>
										<a href="javascript:if(confirm('提示：确定要终止审请吗?')){window.location.href='${base}/financePurchase/endApplyapplyUser.do?id=${loan.loanId}'}">终止申请</a>
									</#if>
								</#if>
								<#if loan.state=='1'>
									已提交申请，正在进行材料审核
									<#--<a href="${base}/financePurchase/getNodeapplyUser.do?id=${loan.loanId}">修改资料</a>-->
								</#if>
								<#if loan.state=='2'||loan.state=='4'||loan.state=='5'>
									材料审核通过，信用审核中
								</#if>
								<#if loan.state=='3'>
									您的申请未通过
								</#if>
								<#if loan.state=='6'>
									审核失败
								</#if>
								<#if loan.state=='7'>发标前审核，会有人员联系您
									<#--<a href="${base}/financePurchase/getNodeapplyUser.do?id=${loan.loanId}&state=7">点击上传</a>-->
								</#if>
								<#if loan.state=='8'>
									发标审核中，请耐心等待
								</#if>
								</span>
							</td>
							</tr>
						</#list>
				</table>
					
					
				</div>
			</div>
		</div>	
					
			
			<!--end: main -->


<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>
