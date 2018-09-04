<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <title>升升投 - 投资</title>
       <link rel="stylesheet"  media="all" type="text/css" href="${base}/theme/${systemConfig.theme}/css/mobile.css" />
</head>
<body>
    <div id="wrap">
        <!-- 顶部   -->
        <div class="wad_header">
            <div class="wad_topbar">
                <a href="#" class="wad_bak"></a>
                <em>投资</em>
                <span class="wad_allnav"></span>
            </div>

           <#include "/WEB-INF/template/${systemConfig.theme}/mobilelayout/navbar.ftl">
        </div>
        <!-- 内容 -->
         <div class="wad_content">
            <ul>
            <#if (pager.list)??>
            	<#list pager.list as list>
            	<#if list.state=='1'||list.state=='2'||list.state=='5'||list.state=='6'>
                <li>
                    <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?mobile=mobile&bidId=${list.bidId}" class="wad_wcont">
                        <div class="wad_fcon" style="position:relative;">
                            <img src="${base}/theme/${systemConfig.theme}/images/mobile/xin.png">
                            <h2>${list.bidProName}</h2>
                           <#if list.state=='1'>
                           	 <div class="wad_toubiaoye"></div>
                           <#else>
                           	<div class="wad_toubiaobg"></div>
                           </#if>
                            <p class="wad_xxmes wad_tbxxms">
                                <span class="wad_jine" style="4"><i>${list.bidMoney?string(',###.00')}元</i><i class="wad_jines">借款金额</i></span>
                                <span class="wad_lilv"><i>
									<#if (list.proType=="B_Dir")>
									${list.bpBusinessDirPro.yearInterestRate}%
								</#if><#if (list.proType=="B_Or")>
									${list.bpBusinessOrPro.yearInterestRate}%
								</#if><#if (list.proType=="P_Dir")>
									${list.bpPersionDirPro.yearInterestRate}%
								</#if><#if (list.proType=="P_Or")>
									${list.bpPersionOrPro.yearInterestRate}%
								</#if>
								</i><i class="wad_jines">年化利率</i></span>
                                <span class="wad_qixian"><i>${list.loanLife}</i><i class="wad_jines">借款期限</i></span>
                            </p>
                            <p class="wad_graph wad_tbgraph"><span class="wad_yanse" id="plan${list.bidId}">${((list.bidMoney-list.afterMoney)/list.bidMoney*100)?string(',##0.##')}%</span></p>
                           <#-- <p class="wad_jiekr">${(list.persionNum)!}出借人已投${(list.bidMoney-list.afterMoney)?string(',##0.##')}元</p>-->
                        </div>
                    </a>
                </li>
               
                </#if>
                 </#list>
            </#if>
            </ul> 
        </div>
        <!-- 分页 
        <div class="f_page"> 
            <div class="sectionone">上一页</div> 
            <div class="sectionone">第<span>1</span>页</div> 
            <div class="sectionone">下一页</div> 
        </div>-->
        <!-- star footer  -->
          <#include "/WEB-INF/template/${systemConfig.theme}/mobilelayout/footerbar.ftl">
        <!-- end footer  -->
    </div>
    <script src='${base}/js/mobile/zepto.min.js'></script>
    <script type="text/javascript">
        $(".wad_allnav").click(function(){
            $(".wad_slidernav").toggle();
        });
         $(function(){
      		<#list pager.list as list>
         $("#plan${list.bidId}").width($("#plan${list.bidId}").text());
         </#list>
       });
    </script>
</body>
</html>
