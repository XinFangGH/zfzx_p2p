<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#if stack.context["session"]?exists> 
<#assign s = stack.context["session"]>  
<title>${s['metaTitle']}--我要投资</title>
</#if>
    <meta name="description" content="${systemConfig.metaTitle} - 我要投资,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 我要投资,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/detailsFunds.js"></script>
<script type="text/javascript" src="${base}/js/slider-min.js"></script>
</head>
<body >
<!--整体布局
<div class="docment docment-711-234">-->
<div class="container-wrap">
	

<div class="container clearfix">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<!-- 头部结束 -->
<!--start: Container -->
<div class="contents clearfix">

<div class="selector">
			<form id="queryForm" name="queryForm" method="post" action="/investment/investment.html">					
				<input type="hidden" name="tradeId" value="front_pUserAccountFinancList"/>
				<input type="hidden" name="productStatus" id="productStatus" value="progress"/>
				<input type="hidden" name="formArea" id="formArea"  value=""/>
				<input type="hidden" name="formRepayType" id="formRepayType"  value=""/>
				<input type="hidden" name="formRepayTime" id="formRepayTime" value=""/>
				<input type="hidden" name="formMoneyRange" id="formMoneyRange" value=""/>
				<input type="hidden" name="formProductType" id="formProductType" value=""/>
				
				<input type="hidden" name="orderType" id="orderType" value=""/>
				<input type="hidden" name="order" id="order" value=""/>
				
				<input type="hidden" name="showMoreArea" id="showMoreArea" value=""/>
				<input type="hidden" name="showMoreMoneyRange" id="showMoreMoneyRange" value="0"/>
				<input type="hidden" name="showMoreProductType" id="showMoreProductType" value=""/>
				
			</form> 
			<dl>
				<dt>
					项目地区：
				</dt>
				<dd class="clearfix" id="Area">
					<a href="javascript:void(0);" lang="" class="curr">全国</a>
				</dd>
			</dl>
			<dl>
				<dt>
					还款方式：
				</dt>
				<dd id="RepayType">
					<a href="javascript:void(0);" lang="" class="curr">全部</a>
					<a href="javascript:void(0);" lang="1">先息后本</a>
					
				</dd>
			</dl>
			<dl>
				<dt>
					还款时间：
				</dt>
				<dd id="RepayTime">
					<a href="javascript:void(0);" lang="" class="curr">全部</a>
					<a href="javascript:void(0);" lang="1-30-D">30天内</a>
					<a href="javascript:void(0);" lang="1-1-M">1个月</a>
					<a href="javascript:void(0);" lang="2-2-M">2个月</a>
					<a href="javascript:void(0);" lang="3-3-M">3个月</a>
					<a href="javascript:void(0);" lang="6-6-M">6个月</a>
					<a href="javascript:void(0);" lang="12-12-M">12个月</a>
					<a href="javascript:void(0);" lang="1-3-M">1-3个月</a>
					<a href="javascript:void(0);" lang="1-6-M">1-6个月</a>
					<a href="javascript:void(0);" lang="6-12-M">6-12个月</a>
				</dd>
			</dl>
			<dl>
				<dt>
					金额范围：
				</dt>
				<dd id="MoneyRange">
					<a href="javascript:void(0);" lang="" class="curr">全部</a>
					<a href="javascript:void(0);" lang="1-5000">1-5000元</a>
					<a href="javascript:void(0);" lang="5001-10000">5001-1万元</a>
					<a href="javascript:void(0);" lang="10000-50000">1万元-5万元</a>
					<a href="javascript:void(0);" lang="50000-100000">5万元-10万元</a>
					<a href="javascript:void(0);" lang="100000-500000">10万元-50万元</a>
					<a href="javascript:void(0);" lang="500000-1000000">50万元-100万元</a>
					<span class="hidden-city">
						<a href="javascript:void(0);" lang="1000001-100000000">100万元以上</a>
					</span>
					<span class="show-more"><em>&lt;&lt;</em> 更多</span>
				</dd>
			</dl>
			<dl class="last">
				<dt>
					标的类型：
				</dt>
				<dd class="wrap" id="ProductType">
					<a href="javascript:void(0)" lang="" class="curr" style="margin-right: 25px;">全部</a>
				</dd>
			</dl>
		</div>
		<div class="item-list m-t_15">
			<div class="hd">
				<h3>
					正在投标中
				</h3>
				<div class="filter f-r">
					<a href="javascript:void(0);" lang="" class="checked">默认</a>
					<a href="javascript:void(0);" lang="pf.financ_payback">利率</a>
					<a href="javascript:void(0);" lang="fa.financ_publish_time">发标时间</a>
					<a href="javascript:void(0);" lang="um.user_grade">用户等级</a>
				</div>
			</div>
			<div class="bd wrap">
				<table class="items m-t_5">
					<tr>
						<th width="95">&nbsp;</th>
						<th width="238"><strong>标题/借款者</strong></th>
						<th width="160"><strong>金额/利率</strong></th>
						<th width="160"><strong>进度/剩余时间</strong></th>
						<th width="130"><strong>期限/还款方式</strong></th>
						<th width="180"><strong>等级</strong></th>
					</tr>
					
					<tr>
						<td>
							<span class="img-bg m-r_15">
							<img src='/upload/pic/member/head/20140212115908811.bmp' alt="" />
							</span>
						</td>
						<td>
							<ul class="column-1">
								<li class="first name" lang="63">
									<a href="/investment/productDetail.html?financ_create_user=U000022491&amp;financ_id=F000000689&amp;interval_order=517" target="_blank" title="【实体经营】增加康复中心的检验耗材">
									【实体经营】增加康复中心的检验耗材</a>
								</li>
								<li>所在地：广州市</li>
							</ul>
						</td>
						<td>
							<ul class="column-2">
								<li class="first">融资金额：<em class="font-red">￥10万</em></li>
								<li>年化率：9%/年</li>
							</ul>
						</td>
						<td>
							<ul class="column-3">
								<li>
									<span class="progress-wrap">
										<span class="progress"
											style="width: 36%;">
											36%
										</span>
									</span> 
								</li>
								<li>已完成
								9笔投标</li>
							</ul>
						</td>
						<td>
							<ul class="column-4">
								<li>期限：1
									个月
									
								</li>
								<li>
									
										先息后本
										
									
								</li>
							</ul>
						</td>
						<td>
							<ul class="column-5">

								<li class="grade" title="当前等级为2级">
											<em class='y-star'>&nbsp;</em><em class='y-star'>&nbsp;</em>
								</li>
							</ul>
						</td>
					</tr>
					
				</table>
			</div>
		</div>
		<div class="pagination m-t_15">
			<form id="paginationForm" name="paginationForm" action="/investment/investment.html" method="post">
				 <input type="hidden" name="financ_city" value=""/>
				 <input type="hidden" name="financ_method" value=""/>
				 <input type="hidden" name="financ_repayment_type" value=""/>
				 <input type="hidden" name="formArea" value=""/>
				 <input type="hidden" name="formMoneyRange" value=""/>
				 <input type="hidden" name="formProductType" value=""/>
				 <input type="hidden" name="formRepayTime" value=""/>
				 <input type="hidden" name="formRepayType" value=""/>
				 <input type="hidden" name="order" value=""/>
				 <input type="hidden" name="orderType" value=""/>
				 <input type="hidden" name="productStatus" value="progress"/>
				 <input type="hidden" name="showMoreArea" value=""/>
				 <input type="hidden" name="showMoreMoneyRange" value="0"/>
				 <input type="hidden" name="showMoreProductType" value=""/>
				 <input type="hidden" name="tradeId" value="front_pUserAccountFinancList"/>
				 <input type="hidden" name="currPage" id="currPage" value=""/>
				<input type="hidden" name="minShowFlag" id="minShowFlag" value=""/>
				<input type="hidden" name="maxShowFlag" id="maxShowFlag" value=""/>
				<div class="flickr">
				<span class="disabled">首页</span>
				<span class="disabled">上一页</span>
				<a href="javascript:;" class="current" onclick="gotoStagePage('1','1','1');return false;">1</a><span class="disabled">下一页</span>
				<span class="disabled">末页</span>
				 共1页 </div>
			</form>
		</div>
	</div>
</div>

<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>