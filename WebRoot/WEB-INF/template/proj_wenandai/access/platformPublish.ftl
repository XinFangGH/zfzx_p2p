<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 统计数据</title>
<meta property="qc:admins" content="1451334700445346756164116375" />
<meta property="wb:webmaster" content="2516fb302e734849" />
    <meta name="description" content="${systemConfig.metaTitle} - 统计数据,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 统计数据,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>


<body>
<!-- header -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div class="Data_banner"></div>
<!--data数据-->
<#if platDataPublish!=null >
<div class="Data_box">
    <div class="Data_box_cont">
        <h6>统计数据汇总<span>（截止至昨日）</span></h6>
        <div class="Data_cont fl">
        	<div class="data-icon"><img src="${base}/theme/${systemConfig.theme}/images/data-icon1.png" ></div>
            <p class="Digital">累计投资金额</p>
            <p class="font_16"><#if (platDataPublish.loneMoney>=10000)><em>${(platDataPublish.loneMoney/10000)?string(",##0.00#")}</em>万元<#else><em>${platDataPublish.loneMoney?string(',##0')}</em>元</#if></p>

        </div>
        <div class="Data_cont fl">
        	<div class="data-icon"><img src="${base}/theme/${systemConfig.theme}/images/data-icon2.png" ></div>
            <p class="Digital">平台交易笔数</p>
            <p class=" font_16"><#if (platDataPublish.loneCount>=10000)><em>${(platDataPublish.loneCount/10000)}</em>万元<#else><em>${platDataPublish.loneCount}</em>元</#if></p>
        </div>
         <div class="Data_cont fl">
         	<div class="data-icon"><img src="${base}/theme/${systemConfig.theme}/images/data-icon3.png" ></div>
            <p class="Digital">投资人数量</p>
            <p class=" font_16"><#if (platDataPublish.investorCount>=10000)><em>${(platDataPublish.investorCount/10000)}</em>万人<#else><em>${platDataPublish.investorCount}</em>人</#if></p>
        </div>        
         <div class="Data_cont fl">
         	<div class="data-icon"><img src="${base}/theme/${systemConfig.theme}/images/data-icon4.png" ></div>
            <p class="Digital">安全运营天数</p>
            <p class="font_16"><em>130</em> 天</p>
        </div> 
    </div>
</div>
<!--Data_list-->
<div class="Data_list-box">
	<div class="Data_box_cont"> 
		<h6>平台累计项目信息</h6>
		 <div class="Sum_data fl"><img src="${base}/theme/${systemConfig.theme}/images/data-pic01.png" ></div>
		 <div class="Sum_data fl"><img src="${base}/theme/${systemConfig.theme}/images/data-pic02.png" ></div>
		    <!--需要重新做数据-->
		    <#-- <div class="Sum_data lf">
		        <h4>投资统计</h4>
		        <ul>
		            <li><span>投资人数量：</span><span><#if (platDataPublish.investorCount>=10000)>${(platDataPublish.investorCount/10000)}万人<#else>${platDataPublish.investorCount}人</#if></span></li>
		            <li><span>成功投资金额：</span><span><#if (platDataPublish.investMoney>=10000)>${(platDataPublish.investMoney/10000)?string(",##0.00#")}万元<#else>${platDataPublish.investMoney?string(',##0')}元</#if></span></li>
		            <li><span>已为投资人赚取：</span><span><#if (platDataPublish.haveEarnedMoney>=10000)>${(platDataPublish.haveEarnedMoney/10000)?string(",##0.00#")}万元<#else>${platDataPublish.haveEarnedMoney?string(',##0')}元</#if></span></li>
		            <li><span>投资待赚取：</span><span><#if (platDataPublish.toEarnCount>=10000)>${(platDataPublish.toEarnCount/10000)}万笔<#else>${platDataPublish.toEarnCount}笔</#if></span></li>
		        </ul>
		    </div>
		    <div class="Yesterday_data lf">
		        <h4>昨日投资</h4>
		        <ul>
		            <li><span>投资人数量：</span><span><#if (platDataPublish.investorCount>=10000)>${(platDataPublish.investorCount/10000)}万人<#else>${platDataPublish.investorCount}人</#if></span></li>
		            <li><span>成功投资金额：</span><span><#if (platDataPublish.investMoney>=10000)>${(platDataPublish.investMoney/10000)?string(",##0.00#")}万元<#else>${platDataPublish.investMoney?string(',##0')}元</#if></span></li>
		            <li><span>已为投资人赚取：</span><span><#if (platDataPublish.haveEarnedMoney>=10000)>${(platDataPublish.haveEarnedMoney/10000)?string(",##0.00#")}万元<#else>${platDataPublish.haveEarnedMoney?string(',##0')}元</#if></span></li>
		            <li><span>投资待赚取：</span><span><#if (platDataPublish.toEarnCount>=10000)>${(platDataPublish.toEarnCount/10000)}万笔<#else>${platDataPublish.toEarnCount}笔</#if></span></li>
		        </ul>
		    </div>-->
	</div>
</div>
<div class="Data_box">
	<div class="Data_box_cont">
		<h6>投标本息数据</h6>
		<div class="Story_Data">
		    <img src="${base}/theme/${systemConfig.theme}/images/data_pic.png" >
		</div>
	</div>
</div>
<div class="Data_list-box">
	<div class="Data_box_cont"> 
		<h6>借贷款统计</h6>
	    <div class="Data_one">
	    	<div class="Yesterday_data fl bg-blue">
		 		<p class="big-txt">近3月的借款统计</p>
		        <ul>
		            <li>借款人数量：<#if (platDataPublish.investorCount>=10000)><span>${(platDataPublish.investorCount/10000)}</span>万人<#else><span>${platDataPublish.investorCount}</span>人</#if></li>
		            <li>贷款余额：<#if (platDataPublish.onLoneMoney>=10000)><span>${(platDataPublish.onLoneMoney/10000)?string(",##0.00#")}</span>万元<#else><span>${platDataPublish.onLoneMoney?string(',##0')}</span>元</#if></li>
		            <li>最大单户借款余额占比：<span>${platDataPublish.oneOnloanProportion}</span>%</li>
		            <li>最大10户借款余额占比：<span>${platDataPublish.tenOnloanProportion}</span>%</li>
		        </ul>
		    </div>
		    <div class="Yesterday_data fl bg-blue">
		 		<p class="big-txt">借款统计</p>
		        <ul>
		            <li>借款人数量：<#if (platDataPublish.investorCount>=10000)><span>${(platDataPublish.investorCount/10000)}</span>万人<#else><span>${platDataPublish.investorCount}</span>人</#if></li>
		            <li>贷款余额：<#if (platDataPublish.onLoneMoney>=10000)><span>${(platDataPublish.onLoneMoney/10000)?string(",##0.00#")}</span>万元<#else><span>${platDataPublish.onLoneMoney?string(',##0')}</span>元</#if></li>
		            <li>最大单户借款余额占比：<span>${platDataPublish.oneOnloanProportion}</span>%</li>
		            <li>最大10户借款余额占比：<span>${platDataPublish.tenOnloanProportion}</span>%</li>
		        </ul>
		    </div>
		     <div class="Yesterday_data fl bg-green">
		    	<p class="big-txt">代偿统计</p>
		        <ul>
		            <li>借贷逾期金额：<#if (platDataPublish.overdueMoney>=10000)><span>${(platDataPublish.overdueMoney/10000)?string(",##0.00#")}</span>万元<#else><span>${platDataPublish.overdueMoney?string(',##0')}</span>元</#if></li>
		            <li>代偿金额：<#if (platDataPublish.compensatoryMoney>=10000)><span>${(platDataPublish.compensatoryMoney/10000)?string(",##0.00#")}</span>万元<#else><span>${platDataPublish.compensatoryMoney?string(',##0')}</span>元</#if></li>
		            <li>借贷逾期率：<span>${platDataPublish.overdueProportion}</span>%</li>
		            <li>代偿划转率：<span>${platDataPublish.badDebtProportion}</span>%</li>
		        </ul>
		    </div>
	    </div>
	</div>
</div>
<#else>
<div class="Data_box">
    <div class="Data_box_cont">

        <h6>统计数据汇总<span>（截止至昨日）</span></h6>
        <div class="Data_cont fl">
        	<div class="data-icon"><img src="${base}/theme/${systemConfig.theme}/images/data-icon1.png" ></div>
            <p class="Digital">平台累计投资总额 </p>
            <p class="font_16"><em>1,202,342,200.00</em> 元</p>
        </div>

        <div class="Data_cont fl">
        	<div class="data-icon"><img src="${base}/theme/${systemConfig.theme}/images/data-icon2.png" ></div>
            <p class="Digital">累计为投资人赚取</p>
            <p class="font_16"><em>130</em> 元</p>

        </div>

         <div class="Data_cont fl">
         	<div class="data-icon"><img src="${base}/theme/${systemConfig.theme}/images/data-icon3.png" ></div>
            <p class="Digital">投资会员人数</p>
            <p class="font_16"><em>130</em> 人</p>
        </div>
         <div class="Data_cont fl">
         	<div class="data-icon"><img src="${base}/theme/${systemConfig.theme}/images/data-icon4.png" ></div>
            <p class="Digital">安全运营天数</p>
            <p class="font_16"><em>130</em> 天</p>

        </div>        
    </div>
</div>
<!--Data_list-->
<div class="Data_list-box">
	<div class="Data_box_cont"> 
		<h6>平台累计项目信息</h6>
		 <div class="Sum_data fl"><img src="${base}/theme/${systemConfig.theme}/images/data-pic01.png" ></div>
		 <div class="Sum_data fl"><img src="${base}/theme/${systemConfig.theme}/images/data-pic02.png" ></div>
		    <!--需要重新做数据-->
		    <!--<div class="Sum_data lf">
		        <h4>投资统计</h4>
		        <ul>
		            <li><span>投资人数量：</span><span>1114亿</span></li>
		            <li><span>成功投资金额：</span><span>27亿3117万元</span></li>
		            <li><span>已为投资人赚取：</span><span>23亿4035万元</span></li>
		            <li><span>投资待赚取：</span><span>272万笔</span></li>
		        </ul>
		    </div>
		    <div class="Yesterday_data lf">
		        <h4>昨日投资</h4>
		        <ul>
		            <li><span>投资人数量：</span><span>2亿2776万元</span></li>
		            <li><span>成功投资金额：</span><span>977万元</span></li>
		            <li><span>已为投资人赚取：</span><span>244万元</span></li>
		            <li><span>投资待赚取：</span><span>6490笔</span></li>
		        </ul>
		    </div>-->
	</div>
</div>
<div class="Data_box">
	<div class="Data_box_cont">
		<h6>投标本息数据</h6>
		<div class="Story_Data">
			<img src="${base}/theme/${systemConfig.theme}/images/data_pic.png" >
		</div>
	</div>
</div>

<div class="Data_list-box">
	<div class="Data_box_cont"> 
		<h6>借贷款统计</h6>
		<div class="Data_one">
		 	<div class="Yesterday_data fl bg-blue">
		 		<p class="big-txt">近3月的借款统计</p>
		        <ul>
		            <li>借款人数量：<span>1114</span>亿</li>
		            <li>贷款余额：<span>1000</span>亿元</li>
		            <li>最大单户借款余额占比：<span>1</span>%</li>
		            <li>最大10户借款余额占比：<span>10</span>%</li>
		        </ul>
		    </div>
		    <div class="Yesterday_data fl bg-green">
		    	<p class="big-txt">借款统计</p>
		        <ul>
		            <li>借贷逾期金额：<span>0</span>元</li>
		            <li>代偿金额：<span>0</span>元</li>
		            <li>借贷逾期率：<span>0</span>%</li>
		            <li>代偿划转率：<span>0</span>%</li>
		        </ul>
		    </div>
		    <div class="Yesterday_data fl bg-oragen">
		    	<p class="big-txt">代偿统计</p>
		        <ul>
		            <li>借贷逾期金额：<span>0</span>元</li>
		            <li>代偿金额：<span>0</span>元</li>
		            <li>借贷逾期率：<span>0</span>%</li>
		            <li>代偿划转率：<span>0</span>%</li>
		        </ul>
		    </div>
	    </div>
	</div>
</div>
</#if>
<!--end:Data_list-->	
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl"> 

</body>
</html>
