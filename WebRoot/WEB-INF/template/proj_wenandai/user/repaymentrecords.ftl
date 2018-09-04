<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  
<title>${systemConfig.metaTitle} - 还款记录</title>
    <meta name="description" content="${systemConfig.metaTitle} - 还款记录,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 还款记录,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
<script type="text/javascript">var m1="个人中心", m2="个人主页",m3="还款管理";</script></head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">



<div class="main">
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
    <div class="ucrightdiv">
    	<!-- 我的个人信息 start -->
        	<div style="width:640px; height:40px; padding:10px 0px 0px 40px;">
            	<img src="${base}/theme/${systemConfig.theme}/images/spicon1.jpg" style="vertical-align:middle; margin:0px 20px 7px 0px;" /><span class="blue large">我的贷款</span>
            </div>
            <div class="dashedline"></div>
        	<div style="width:680px; padding:20px 40px; overflow:hidden;" class="account-info">
            	<!--此处放置内容-->
					<form name="queryForm" id="queryForm" method="post" action="/userAccount/repaymentrecords.html">
						<input type="hidden" id="tradeId" name="tradeId" value="pRepaymentRecordList">
						<input type="hidden" id="submitForm" name="submitForm" value="queryForm">
						<input type="hidden" name="currPage" id="currPage" value=""/>
						<input type="hidden" name="minShowFlag" id="minShowFlag" value=""/>
						<input type="hidden" name="maxShowFlag" id="maxShowFlag" value=""/>
						<div class="bd" id="pageForm">
							<dd>
								<label>交易时间：</label>
								<input type="text" id="from" name="from" class="wdate txt f-l">
								<span class="m-l_10 f-l m-r_10">到</span>
								<input type="text" id="to" name="to" class="wdate txt f-l">
								<label>关键字：</label><input type="text" id="financ_name" name="financ_name" class="txt f-l" maxlength="100">
								<input type="hidden" id="payment_type" name="payment_type" value="">
								<a href="javascript:;" id="serch_financ" class="query-btn" onclick="query('queryForm','')">查询</a>
							</dd>
						</div>
					</form>
					<div class="tab-controller">
						<ul class="controller clearfix">
							<li id="li-norepayment" class="current">待还款</li>
							<li id="li-repaymented" class="">已还款</li>
							<li id="li-overdue" class="">逾期</li>
						</ul>
						<div class="tab-cont" style="display: block;">
							<div class="pending">
								<dl class="outer-wrap">
									<dt>
										<ul class="thead clearfix">
											<!-- <li class="a">订单号</li> -->
											<li class="b">标题</li>
											<!-- <li class="c">协议</li> -->
											<li class="d">借款金额</li>
											<li class="e">利率</li>
											<li class="f">期限</li>
											<li class="g">每期本息</li>
											<li class="h">已还本息</li>
											<li class="i">未还本息</li>
											<li class="j">明细</li>
										</ul>
									</dt>
								</dl>
								<div id="norepayment_pager" class="pagination m-t_15">
								</div>
							</div>
						</div>
						<div class="tab-cont">
							<div class="repaid">
								<dl class="outer-wrap">
									<dt>
										<ul class="thead clearfix">
											<!-- <li class="a">订单号</li> -->
											<li class="b">标题</li>
											<!-- <li class="c">协议</li> -->
											<li class="d">借款金额</li>
											<li class="e">利率</li>
											<li class="f">期限</li>
											<li class="g">每期本息</li>
											<li class="h">已还本息</li>
											<li class="i">未还本息</li>
											<li class="j">明细</li>
										</ul>
									</dt>
								</dl>
								<div id="repaymented_pager" class="pagination m-t_15">
								</div>
							</div>
						</div>
						<div class="tab-cont">
							<div class="overdue">
								<dl class="outer-wrap">
									<dt>
										<ul class="thead clearfix">
											<!-- <li class="a">订单号</li> -->
											<li class="b">标题</li>
											<!-- <li class="c">协议</li> -->
											<li class="d">借款金额</li>
											<li class="e">利率</li>
											<li class="f">期限</li>
											<li class="g">每期本息</li>
											<li class="h">已还本息</li>
											<li class="i">未还本息</li>
											<li class="j">明细</li>
										</ul>
									</dt>
								</dl>
								<div id="overdue_pager" class="pagination m-t_15">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
</div></div>
			
			<!--end: main -->


<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>