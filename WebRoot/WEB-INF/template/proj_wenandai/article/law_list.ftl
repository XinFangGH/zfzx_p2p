<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<#--<title>${systemConfig.metaTitle} - ${helpTitle}</title>-->
    <title>${systemConfig.metaTitle} - 出借人教育</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
<#--<script type="text/javascript">var m1="关于我们",m2="",m3="${helpTitle}";</script>-->
    <script type="text/javascript">var m1="关于我们",m2="",m3="出借人教育";</script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_common.css">

<#--<link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/law_List.css">-->

</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">

<div class="main">
	<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
    <div class="abouts-nav fl">
        <!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_aboutus.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>

    <!-- 左侧页面 end -->
	 <div class="abouts-cont fr">
	 	<div class="abouts-border" style="padding:30px 40px; min-height:633px;"><#-- id="law_List"-->
			<#--<div class="law_Title">
				<div class="law_Pic">
					<span class="left_PIC"></span>
					<h1>${helpTitle}</h1>
					<span class="left_PIC"></span>
				</div>
			</div>-->
            <div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">风险提示</span>
            </div>
			<div  class="riskHints">
                <p>您正在进行的是升升投平台提供网络借贷信息中介服务的网络借贷活动。升升投在此就网络借贷活动的风险及禁止性行为向您提示如下：</p>
                <p>1、网络借贷是个体和个体之间通过互联网平台实现的直接借贷，您与借款人约定的且通过升升投平台展示的借款利率或参考年回报率不代表您最终实际取得的利息或回报，您出借的本金以及相应的利息存在不能够按期收回的风险；升升投不对您本金的收回、可获利息或回报金额作出任何承诺、保证。</p>
                <p>2、您作为出借人，不得从事以下行为或存在以下情形：</p>
                <p>（1）向网络借贷信息中介机构提供不真实、不准确、不完整的信息；</p>
                <p>（2）使用非法资金或非自有资金进行出借；</p>
                <p>（3）不具备与进行网络借贷活动相适应的风险认知和承受能力，投资于与自身风险承受能力不匹配的融资项目；</p>
                <p>（4）其他借贷合同及有关协议约定的禁止性行为。</p>
                <p>3、您确认已经知悉网络借贷活动的风险，保证不存在从事网络借贷活动的禁止性行为，承诺具备与参与网络借贷活动相适应的投资风险意识、风险识别能力、拥有非保本类金融产品投资的经历并熟悉互联网，承诺自行承担借贷产生的本息损失。</p>
			</div>
            <div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">法律法规</span>
            </div>
            <ul class="media-cont news_list">
			<#list pager.list as list>
				<li class="media-list" >
					<span class="new-list fl" >
						<#--<a href="&lt;#&ndash;${base}/article/newscontentArticle.do?catId=${list.id}&ndash;&gt;">&lt;#&ndash;${list.title}&ndash;&gt;</a>-->
						<a href="${list.content}" target="_blank">${list.title}</a>
					</span>
                    <span class="fr"><#--${list.createDate}--></span>
                </li>
			</#list>
		<#import "/WEB-INF/template/common/pager.ftl" as p>
      			<#assign parameterMap = {} />
      			<@p.pager pager = pager baseUrl = "/article/lawListArticle.do?lid=32" parameterMap = parameterMap />
			</ul>



			<#--<#list pager.list as list>-->
			<#--<div class="company wad_company" >-->
				<#--<p class="company_l">-->
					<#--<span class="wad_omimg1"><img src="${base}/theme/${systemConfig.theme}/images/company_pic.png" /></span>-->
					<#--<span class="wad_omimg2"><a href="${base}/article/lawListDetailArticle.do?catId=${list.id}">${list.title}</a></span>-->
				<#--</p>-->
				<#--<p class="company_r">${list.createDate}</p>-->
			<#--</div>-->
			<#--</#list>-->
			<#--<#import "/WEB-INF/template/common/pager.ftl" as p>-->
         			<#--<#assign parameterMap = {} />-->
         			<#--<@p.pager pager = pager baseUrl = "/article/lawListDetailArticle.do?lid=${articleCategorysign}" parameterMap = parameterMap />-->
   		</div>
		</div>
</div>
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	</body>
</html>