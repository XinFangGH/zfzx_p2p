<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 我的融资</title>
    <meta name="description" content="${systemConfig.metaTitle} - 我的融资,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 我的融资,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>

<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript">var m1="个人中心",m2="个人主页",m3="我的投资";</script>
<style type="text/css">
dd {
display: block;
-webkit-margin-start: 40px;
}


dl {
display: block;
-webkit-margin-before: 1em;
-webkit-margin-after: 1em;
-webkit-margin-start: 0px;
-webkit-margin-end: 0px;
}

.query-btn {
margin-left: 10px;
color: #fff!important;
padding: 6px;
cursor: pointer;
border-radius: 3px;
text-decoration: none;
background: #e74649;
background: -moz-linear-gradient(top, #e74649 0%, #e03336 100%);
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#e74649), color-stop(100%,#e03336));
background: -webkit-linear-gradient(top, #e74649 0%,#e03336 100%);
background: -o-linear-gradient(top, #e74649 0%,#e03336 100%);
background: -ms-linear-gradient(top, #e74649 0%,#e03336 100%);
background: linear-gradient(to bottom, #e74649 0%,#e03336 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e74649', endColorstr='#e03336',GradientType=0 );
}


</style>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">


<div class="main">

<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
	<div style="float:left; padding:0px 10px;">
    	<!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar-user.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->
       <!-- <div style="float:left; height:700px; padding:10px 0px; width:1px; border:0px; background-color:#e0e0e0;"></div>-->
    <!-- 右侧主体内容 start  右侧内容宽740px -->
    <div style="float:left; width:790px; margin:10px 0px 0px 0px; overflow:hidden;">
    	<!-- 我的个人信息 start -->
        	<div style="width:780px; height:40px; padding:10px 0px 0px 18px;">
            	<span class="blue large">我的融资</span>
            </div>
            <div class="dashedline"></div>
        	<div style="width:780px; padding:20px 2px; overflow:hidden;" class="account-info">
            	<!--此处放置内容-->
            	<ul width="100%">
					<form name="queryForm" id="queryForm" method="post" action="${base}/user/toFinancingBpCustMember.do">
						<li style="margin-top: 20px;height:40px;">
							<label class="f-l m-t_5"><span class="black normal">投资时间：</span></label>
							<input type="text" id="bidtime" style="width:80px" name="bidtime" class="colorful" readonly="readonly" value="" onclick="new Calendar().show(this);"/>
							<label class="f-l m-t_5"><span class="black normal">到</span></label>
							<input type="text" id="bidtime2" style="width:80px" name="bidtime2" class="colorful" readonly="readonly" value="" onclick="new Calendar().show(this);"/>
							<label class="m-l_10"><span class="black normal">投资名称：</span></label>
							<input type="text" class="colorful" style="width:100px"  name="bidName" id="bidName" value=""/>
								<label class="m-l_10"><span class="black normal">投资金额：</span></label>
							<input type="text" class="colorful" style="width:100px"  name="userMoney" id="userMoney" value=""/>
							<!--<a href="javascript:query();" class="query-btn"><span class="black white">查询</span></a> -->
								<input type="submit" id="btn" name="btn" class="query-btn white" value="查询" />
						</li>
						<li style="margin-top: 20px;">
							
						</li>
					
						<!--<li style="margin-top: 20px;float:left;height:40px;">
						<label><span class="black normal">  结果统计：借出总额￥<em class="font-red">0</em>元
						  应收本息总额￥<em class="font-red">0</em>元
 						  已收本金总额￥<em class="font-red">0</em>元
 						  收益利息总额￥<em class="font-red">0</em>元</span></label>
						</li>	-->
						<li>
							<table width="100%" class="tab_css_3" border="0"cellpadding="0"  cellspacing="0" style="text-align:left;table-layout:fixed">
								<tr>
									<th width="15%">投资名称</th>
									
									<th width="10%">金额(元)</th>
									<th width="7%">期限</th>
									<th width="11%">年化利率(%)</th>
									<th width="10%">借款时间</th>
								</tr>
								
								
					<#list pager.list as list>
        	          <tr> 
        	          	<td style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
        	          	<a href="javascript:;" title="${list.projectName}">${list.projectName}</a></td> 
        	          	<td>${list.projectMoney}</td> 
        	          	<td>${list.yearManagementConsultingOfRate}</td> 
        	          	<td>${list.yearAccrualRate}</td> 
        	           	<td>${list.startDate}</td>  
						</tr>
        			</#list>
	 						</table>
						</li>
						<li>
						
                   <#import "/WEB-INF/template/common/pager.ftl" as p>
         			<#assign parameterMap = {} />
         			<@p.pager pager = pager baseUrl = "/financePurchase/myFinancePurchase.do"  parameterMap = parameterMap />
         			
						</li>
					</ul>
				</div>
			</div>
		</div>						

</form>	
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>