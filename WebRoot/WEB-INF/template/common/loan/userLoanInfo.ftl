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
						<li class="b">项目名称</li>
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
						<li class="b">项目名称</li>
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
						<li class="b">项目名称</li>
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