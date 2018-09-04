<form name="queryForm" id="queryForm" method="post" action="">
	<dd>
		<label class="f-l m-t_5">交易时间：</label>
		<input type="text" id="purchase_time_start" name="purchase_time_start" class="wdate txt f-l" value=""/>
		<span class="m-l_10 m-t_5 f-l m-r_10">到</span>
		<input type="text" id="purchase_time_end" name="purchase_time_end" class="wdate txt f-l"  value=""/>
		<label class="m-l_10">项目名称：</label>
		<input type="text" class="txt" name="keyWord" id="keyWord" value=""/>
		<a href="javascript:query();" class="query-btn">查询</a>
	</dd>
	<dd class="m-t_20">
		<label>状态：</label>
		<select name="financ_status" id="financ_status"  onchange="query();">
			<option value="">请选择</option>
			<option value="-1" >全部</option>
			<option value="P" >投标中</option>
			<option value="F" >已结标</option>
			<option value="B" >还款中</option>
			<option value="E" >已还完</option>
		</select>
	</dd>
</form>	
<dl class="bd finance">
	<dd>
		  结果统计：借出总额￥<em class="font-red">0</em>元
		  应收本息总额￥<em class="font-red">0</em>元
		  已收本金总额￥<em class="font-red">0</em>元
		  收益利息总额￥<em class="font-red">0</em>元
	</dd>	
	<dd> 
		<table>
			<tr>
				<th width="135">项目名称</th>
				<th width="70">借款人</th>
				<th width="60">金额</th>
				<th width="56">年利率</th>
				<th width="52">期限</th>
				<th width="100">投标时间</th>
				<th width="120">应收本息</th>
				<th width="73">借出金额</th>
				<th width="57">状态</th>
				<th width="50">协议</th>
			</tr>
			
		</table>
	</dd>
	<dd>
		<div class="pagination m-t_15">
			<form id="paginationForm" name="paginationForm" action="/userAccount/myFinancePurchase.html" method="post">
				 <input type="hidden" name="financ_status" value=""/>
				 <input type="hidden" name="keyWord" value=""/>
				 <input type="hidden" name="purchase_time_end" value=""/>
				 <input type="hidden" name="purchase_time_start" value=""/>
				 <input type="hidden" name="tradeId" value="front_myFinancePurchase"/>
				 <input type="hidden" name="user_id" value="U000022626"/>
				 <input type="hidden" name="currPage" id="currPage" value=""/>
				<input type="hidden" name="minShowFlag" id="minShowFlag" value=""/>
				<input type="hidden" name="maxShowFlag" id="maxShowFlag" value=""/>
				<div class="flickr">
					<span class="disabled">首页</span>
					<span class="disabled">上一页</span>
					<span class="disabled">下一页</span>
					<span class="disabled">末页</span>
					 共0页 
				</div>
			</form>
		</div>
	</dd>
</dl>
	
		