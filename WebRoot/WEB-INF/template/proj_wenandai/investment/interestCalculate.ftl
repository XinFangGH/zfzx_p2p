<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 利息计算器</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/investment.js"></script>
<script type="text/javascript" src="${base}/js/slider-min.js"></script>
<script type="text/javascript">var m1="",m2="",m3="";</script>
</head>
<body >
<!--整体布局
<div class="docment docment-711-234">-->
<div class="container-wrap">
	

<div class="container clearfix">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<!-- 头部结束 -->
<!--start: Container -->

<div class="calculate-wrap">
			<h3>利息计算器</h3>
			<p></p>
			<h3>月标</h3>
			<div class="calculate">
				<form name="monthForm" id="monthForm" method="post" action="/common/interestCalculate.html">
					<input type="hidden" name="tradeId" value="monthCal" />
					<a href="javascript:;" class="submit-btn f-r" onclick="calculate('M')"><span>开始计算</span></a>
					<div class="digital clearfix">
						<span>投资金额：</span><input type="text" class="txt f-l" id="month_money" name="month_money"/><span class="m-l_5">元/人民币</span>
						<span class="m-l_25">预期年化利率：</span><input type="text" class="txt f-l" style="width: 60px;" id="month_rate" name="month_rate"/><span class="m-l_5">%</span>
						<span class="m-l_25">借款期限：</span><input type="text" class="txt f-l" style="width: 60px;" id="month_limited" name="month_limited"/><span class="m-l_5">月</span>
						<span class="m-l_25">还款方式：</span>
						<select  id="repayment_type"  class="f-l"  name="repayment_type" > <option class="combox-default" value="">请选择</option><option value="1" >先息后本</option></select>
						<br/>
						<div id="result" style="text-align: left;"></div>
					</div>
					
				</form>
			</div>
			<h3>日标</h3>
			<div class="calculate">
				<form name="dayForm" id="dayForm" method="post" action="/common/interestCalculate.html">
				<input type="hidden" name="tradeId" value="dayCal" />
				<a href="javascript:;" class="submit-btn f-r" onclick="calculate('D')"><span>开始计算</span></a>
				<div class="digital clearfix">
					<span>投资金额：</span><input type="text" class="txt f-l" id="day_money" name="day_money"/><span class="m-l_5">元/人民币</span>
					<span class="m-l_25">预期年化利率：</span><input type="text" class="txt f-l" style="width: 60px;" id="day_rate" name="day_rate"><span class="m-l_5">%</span>
					<span class="m-l_25">借款期限：</span><input type="text" class="txt f-l" style="width: 60px;" id="day_limited" name="day_limited"/><span class="m-l_5">天</span>
				</div>
				<div id="day_result" style="text-align: left;"></div>
				</form>
			</div>
		</div>

	</div>
</div>

<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>