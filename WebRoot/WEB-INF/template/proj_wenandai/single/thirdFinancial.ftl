<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${systemConfig.metaTitle} - ${articleCategory.name}</title>
    <meta name="description" content="${articleCategory.metaDescription}">
    <meta name="keywords" content="${articleCategory.metaKeywords}">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="${parent.name}";</script>
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" />
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_common.css" />
</head>
<body >
<!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<style>


</style>

<div class="main">
    <!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
    <div class="abouts-nav fl">
        <!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_aboutus.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->
    <div class="abouts-cont fr finacialS" >
        <img class="banner" src="${base}/theme/proj_wenandai/images/mypic/info_banner.jpg" alt="">
        <div class="abouts-border" style="padding:30px 40px;min-height: 930px;">
            <div class="tab">
                <#list listThird as a >

                <#if a.id == articleCategory.id >
                <a href="${base}/gonglue/fl/${a.id}/"><span style="width:${100/listThird?size}%; color: #6299E7;font-size: 22px;">${a.name}</span></a>
                <#else >
                <a href="${base}/gonglue/fl/${a.id}/"><span style="width:${100/listThird?size}%">${a.name}</span></a>
                    
                </#if>
                </#list>
                <#--<span class="pr">互金理财攻略</span>-->
            </div>

                <ul class="lists">
                       <#list pager.list as c >

                    <li>
                        <a href="${base}/gl/${c.id}.html">
                            <span class="pl">${c.title}</span>
                            <span class="pr">${c.createDate}</span>
                        </a>
                    </li>
                       </#list>
            <#import "/WEB-INF/template/common/pager.ftl" as p>
		    <#assign parameterMap = {} />
	    	<@p.pager pager = pager baseUrl = "/html/mutualFinancialSingle.do?id=${lid}" parameterMap = parameterMap />
                </ul>

        </div>
    </div>
</div>

    <!--end: Container -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
    <script type="text/javascript" src="${base}/js/jquery.fancybox-1.3.1.pack.js"></script>
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" />
    <script type="text/javascript" src="${base}/js/page/jquery.page.js"></script>
    <!--<script type="text/javascript" src="${base}/js/wad_common.js"></script>-->
<script>
    $(function(){
        $('.finacialS .tab span').eq(0).addClass('on');
        $('.con').eq(0).show();
        $('.finacialS .tab').on('click','span',function(){
            if(!$(this).hasClass('on')){
                var index=$(this).index();
                $(this).addClass('on').siblings().removeClass('on');
                $('.con').eq(index).show().siblings('.con').hide();
            }
        });

        $('.my-about-navbar li').each(function(){
            if($(this).hasClass('current')){
                $(this).find('a').css({"color":"#6299E7"});
                $(this).removeClass('current');
            }
            if($(this).attr('data-i')=="hj"){
                $(this).show();
            }
            if($(this).find('a').text()=="理财攻略"){
                $(this).addClass('current');
                $('.current .user-arrow').css("transform","rotate(90deg)");
            }
        });
    });
</script>
</body>
</html>