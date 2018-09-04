<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 主页</title>
<meta property="qc:admins" content="1451334700445346756164116375" />
<meta property="wb:webmaster" content="2516fb302e734849" />
    <meta name="description" content="${systemConfig.metaTitle} - 主页,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 主页,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
<link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_common.css">
<!--<script language="javascript" src="${base}/js/wad_common.js"></script>  -->
<script type="text/javascript">

function wad_bgimg() {
        $('.wad_product_li').mouseenter(function(){
        $(this).find('.product_bgimg').animate({
            top : '150px'
        })
    }).mouseleave(function(){
         $(this).find('.product_bgimg').stop().animate({
            top : '0px'
        })
    })
}


$(document).ready(
    //进度条触发
    function(){
    
        <#list pager.list as list0 >
        //$("#pb${list0.bidId}").progressBar();
        $('#plan${list0.bidId}').css("background-position","${(list0.progress)*-54}px 0");
        </#list>
             wad_bgimg(); 
       
       
         $("#bybMoneyPlanoneprogress").progressBar();  
          $("#bybMoneyPlantwoprogress").progressBar();    
           $("#bybMoneyPlantreeprogress").progressBar();  
           debugger
        <#if bybMoneyPlanone !=null>   
         var afterTime=$("#bybMoneyPlanoneafterTime").val();
		  intDiff = parseInt(afterTime);//倒计时总秒数量	
		  timer(intDiff);  
		 </#if>
		   <#if bybMoneyPlantwo !=null>   
         var afterTime1=$("#bybMoneyPlantwoafterTime").val();
		  intDiff1 = parseInt(afterTime1);//倒计时总秒数量	
		  timer1(intDiff1);  
		 </#if>
		   <#if bybMoneyPlantree !=null>   
         var afterTime2=$("#bybMoneyPlantreeafterTime").val();
		  intDiff2 = parseInt(afterTime2);//倒计时总秒数量	
		  timer2(intDiff2);  
		 </#if>
         
    }

);
$(function(){

    $('a[rel*=leanModal]').leanModal({ top : 200 });        
    });
    $(function() {
    // 首页广告图切换
    changeBannerSimple();
        
    // 合作伙伴图标水平循环滚动
    scrollPartner();    
    

});
function scroll(){
    $("#announcment").append($("#announcment>a:first"));
    $("#announcment>a").css("display","none");
    $("#announcment>a:first").css("display","block");
}
setInterval("scroll()",5000)
</script>
<script type="text/javascript">
$(document).ready(
		//进度条触发
		function() {
		<#if pager??>
		<#if pager.list??>
		<#list pager.list as list0 >
			$("#pb${list0.bidId}").progressBar();
		</#list>
		<#list listMoneyPlan as list1 >
			$("#pb${list1.mmplanId}").progressBar();
		</#list>
		</#if>
		</#if>
		},
		
		
);




</script>

</head>
<body>
  <!-- topbar -->
  <!-- header --><!-- navbar -->
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
  <!-- start:banner -->
  <div class="banner">
    <div class="slide">
        <a href="${base}/html/secureSingle.do" target="_blank"><img class="hidden" src="${base}/theme/${systemConfig.theme}/images/wad_banner@anquan.jpg" /></a>
        <a href="${base}/html/wad_zhuanti_js.html" target="_blank"><img class="hidden" src="${base}/theme/${systemConfig.theme}/images/banner@js.png" /></a>
        <a href="${base}/html/wad_zhuanti_sq.html" target="_blank"><img class="hidden" src="${base}/theme/${systemConfig.theme}/images/wad_bannersq@1118.jpg" /></a>
    	<a href="${base}/html/wad_zhuanti_yeb.html" target="_blank"><img class="hidden" src="${base}/theme/${systemConfig.theme}/images/wad_banner@yeb.jpg" /></a>
    </div>
    <div class="clickbtn">
        <a href="javascript:void(0)"></a>
        <a href="javascript:void(0)"></a>
        <a href="javascript:void(0)"></a> 
        <a href="javascript:void(0)"></a> 
    </div>
  </div>
  <!-- end:banner -->
    <!--start:官方公告-->
    
    <!--end:数据报告--> 
  
  <!-- main -->
  <div class="main">

    <div style=" width:1001px;  overflow:hidden;">
        <div class="pucrit">
            <div class="valuepic">
                <div class="blockdiv">
                    <div class="picdiv">
                        <img src="${base}/theme/proj_wenandai/images/valuepic1.jpg" height="102">
                    </div>
                    <div class="bigtext">安全保障<i class="small-icon1"></i></div>
                    <div class="smalltext">设立风险保证金客户投标资金100%本金保障；真正第三方托管，有效保障理财人资金安全。</div>
                </div>
                <div class="blockdiv">
                    <div class="picdiv">
                        <img src="${base}/theme/proj_wenandai/images/valuepic2.jpg" height="102">
                    </div>
                    <div class="bigtext">理财收益<i class="small-icon1"></i></div>
                    <div class="smalltext">获得预期12%-15%的稳定年华收益，理财人15秒注册，快速简单，最低50元即可进行理财。</div>
                </div>
                <div class="blockdiv">
                    <div class="picdiv">
                        <img src="${base}/theme/proj_wenandai/images/valuepic3.jpg" height="102">
                    </div>
                    <div class="bigtext">信用借款<i class="small-icon3"></i></div>
                    <div class="smalltext">真正的互联网信用贷款，5分钟申请，24小时审核，最快5小时完成资金募集。</div>
                </div>
                
                
            </div>
        </div>
    </div>

    <!-- 产品列表 -->
    <div class="wad_title">
        <div class="wad_title_left"><span class="big">稳安借款</span><em>真正的网上信用贷款，火热开启中...</em></div>
        <div class="wad_title_right"><a href="${base}/creditFlow/financingAgency/listPlBidPlan.do">更多</a></span></div>
    </div>
    <div class="wad_product">
        <ul>
            <li class="wad_product_li">
                <a href="${base}/financePurchase/showProductFinancePurchase.do?id=15" class="wad_product_link">
                    <div class="product_bgimg"></div>
                    <div class="product_news">
                        <h3>园丁贷</h3>
                        <p>教师专属网上信用贷款服务</p>
                        <p>活动期内：手续费减免20%</p>
                        <p><a href="${base}/financePurchase/showProductFinancePurchase.do?id=15">立即申请</a></p>
                    </div>
                </a>
            </li>
            <li class="wad_product_li">
                <a href="${base}/financePurchase/showProductFinancePurchase.do?id=16" class="wad_product_link">
                    <div class="product_bgimg product_bgimg_jingying"></div>
                    <div class="product_news">
                        <h3>青春贷</h3>
                        <p>助力青春、放飞梦想</p>
                        <p>面向大专高等院校在校生</p>
                        <p><a href="${base}/financePurchase/showProductFinancePurchase.do?id=16">立即申请</a></p>
                    </div>
                </a>
            </li>                
            <li class="wad_product_li wad_product_lastli">
                <a href="javascript:void(0);" class="wad_product_link">
                    <div class="product_bgimg product_bgimg_yihu"></div>
                    <div class="product_news">
                        <h3>医护贷</h3>
                        <p>致敬辛劳的白衣天使</p>
                        <p>借款专项费用优惠</p>
                        <p><a>敬请期待</a></p>
                    </div>
                </a>
            </li>
        </ul>
    </div>
    <!-- 借款项目以及公告 -->
    <div class="tenderlist">
    
     <div class="wad_title">
            <div class="wad_title_left"><span class="big">升升投“福”到计划</span><em>福利大放送，收益13%以上</em></div>
            <div class="wad_title_right"><a href="${base}/creditFlow/financingAgency/listPlBidPlan.do" target="_blank">更多</a></span></div>
        </div>
    
    
        <div class="bidplan1">
            <div class="baiyibao">
                <ul class="tab_css_3-1 wad_product_list_tab">
    				<li class="wad_li_spec1">
                        <span class="w260">借款标题</span>
                        <span class="text-center w80" title="借款人在升升投的个人信用评级">信用等级</span>
                        <span class="w120">年化收益率</span>
                        <span class="w160">金额</span>
                        <span class="w100">期限</span>
                        <span class="w110">进度</span>
                        <span class="w80"></span>
                    </li>
                    
                    <#if (pager.list)??>
                    <#list pager.list as list>
                     <#if list.proKeepType=="福利标" >
                    <li class="wad_li_spec2">
                        <span class="w260 wad_spec1"><em><img src="${base}/theme/${systemConfig.theme}/images/fu.png"></em><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" title="${list.bidProName}">${list.bidProName}</a></span>
                    <span class="wad_xinyong w80 wad_spec2">
                        <a class="aa">AA</a>
                        </span>
                    <span class="w120"><em class="value">
                        <#if (list.proType=="B_Dir")>${list.bpBusinessDirPro.yearInterestRate}</#if><#if (list.proType=="B_Or")>${list.bpBusinessOrPro.yearInterestRate}</#if><#if (list.proType=="P_Dir")>${list.bpPersionDirPro.yearInterestRate}</#if><#if (list.proType=="P_Or")>${list.bpPersionOrPro.yearInterestRate}</#if></em>%</span><span class="w160"><em class="value">${list.bidMoney?string(',##0')}</em>元</span>
                        <span class="w100"><em class="value">1天</em></span>
                        <span class="w110 wad_spec5"><strong id="plan${list.bidId}"><em>${list.progress}</em>%</strong></span>
                        <span class="w80 wad_spec6"><a href="javascript:void(0)"><span class="wad_open">
                        <#if list.state==2 || list.state==5|| list.state==6>已满标
                                <#elseif list.state==3>已流标
                                <#elseif list.state==4>已过期
                                <#elseif list.state==10>已还清
                                <#elseif list.state==7>还款中
                                <#else>
                                    <#if list.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt list.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") >
                                    <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" style=" text-decoration: underline;">投标中</a>
                                    <#else>预售中</#if>
                                </#if>
                        </span><!-- <span class="wad_ready">还款中</span><span class="wad_full">已满标</span> --></a></span>
                    </li>
                   </#if>
                    </#list>
                    </#if>
       			 </ul>
            </div>
    	</div>
        <div class="wad_title">
            <div class="wad_title_left"><span class="big">零钱理财</span><em>全部产品参与100%本息保障计划</em></div>
            <div class="wad_title_right"><a href="${base}/creditFlow/financingAgency/listPlBidPlan.do" target="_blank">更多</a></span></div>
        </div>

        <div class="bidplan1">
            <div class="baiyibao">
                <ul class="tab_css_3-1 wad_product_list_tab">
                    <li class="wad_li_spec1">
                        <span class="w260">借款标题</span>
                        <span class="text-center w80" title="借款人在升升投的个人信用评级">信用等级</span>
                        <span class="w120">年化收益率</span>
                        <span class="w160">金额</span>
                        <span class="w100">期限</span>
                        <span class="w110">进度</span>
                        <span class="w80"></span>
                    </li>
                     <#if (pager.list)??>
                    <#list pager.list as list>
                    <#if list.proKeepType=="福利标" >
                    
                   <#else>
                    
                   
                    <li class="wad_li_spec2">
                        <span class="w260 wad_spec1"><em><img src="${base}/theme/${systemConfig.theme}/images/<#if list.proKeepType=="信用审核标">xin.png<#elseif list.proKeepType=="实地核查标">shi.png<#elseif  list.proKeepType="机构担保标">bao.png</#if>"></em><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" title="${list.bidProName}">${list.bidProName}</a></span>
                    <#if (list.proType=="B_Dir")>
                        <span class="wad_xinyong w80 wad_spec2">
                        <a class="<#if (list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name)??>
                        <#if list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name=="AA">aa
                        <#elseif list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name=="HR">hr
                        <#elseif list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name=="A">a
                        <#elseif list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name=="B">b
                        <#elseif list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name=="C">c
                        <#elseif list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name=="D">d
                        <#else>e</#if><#else>a</#if>
                        
                        ">${(list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name)!}</a>
                        </span>
                    </#if><#if (list.proType=="P_Dir")>
                        <span class="wad_xinyong w80 wad_spec2 ">
                        <a class="<#if (list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name)??>
                        <#if list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name=="AA">aa
                        <#elseif list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name=="HR">hr
                        <#elseif list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name=="A">a
                        <#elseif list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name=="B">b
                        <#elseif list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name=="C">c
                        <#elseif list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name=="D">d
                        <#else>e</#if><#else>a</#if>">${(list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name)!}</a>
                        </span>
                    </#if>
                        
                        <span class="w120"><em class="value">
                        <#if (list.proType=="B_Dir")>${list.bpBusinessDirPro.yearInterestRate}</#if><#if (list.proType=="B_Or")>${list.bpBusinessOrPro.yearInterestRate}</#if><#if (list.proType=="P_Dir")>${list.bpPersionDirPro.yearInterestRate}</#if><#if (list.proType=="P_Or")>${list.bpPersionOrPro.yearInterestRate}</#if></em>%</span><span class="w160"><em class="value">${list.bidMoney?string(',##0')}</em>元</span>
                        <span class="w100"><em class="value"> ${list.loanLife}</em></span>
                        <span class="w110 wad_spec5"><strong id="plan${list.bidId}"><em>${list.progress}</em>%</strong></span>
                        <span class="w80 wad_spec6"><a href="javascript:void(0)"><span class="wad_open">
                        <#if list.state==2 || list.state==5|| list.state==6>已满标
                                <#elseif list.state==3>已流标
                                <#elseif list.state==4>已过期
                                <#elseif list.state==10>已还清
                                <#elseif list.state==7>还款中
                                <#else>
                                    <#if list.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt list.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") >
                                    <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" style=" text-decoration: underline;">投标中</a>
                                    <#else>预售中</#if>
                                </#if>
                        </span><!-- <span class="wad_ready">还款中</span><span class="wad_full">已满标</span> --></a></span>
                    </li>
                     </#if>
                     </#list>
                  </#if>
                    


                </ul>
            </div>

        <!-- 理财计划 -->
        <div class="wad_title">
            <div class="wad_title_left"><span class="big">理财计划</span></div>
        </div>
        <div class="wad_product wad_product_spec">
            <ul>
                <li class="wad_product_li" style="height:210px;">
                 <#if bybMoneyPlanone==null>敬请期待<#else>
                    <div class="product_news product_newss">
                        <input id="bybMoneyPlanoneafterTime" type="hidden" value="${bybMoneyPlanone.afterTime}">
                        <h3>${bybMoneyPlanone.manageMoneyTypeName}</h3>
                        <div class="product_news_stat">
                         	<div class="stat_list">
                         		<p class="middle">≥ ${bybMoneyPlanone.yeaRate}%</p>
                         		<p>预期年化</p>
                         	</div>
                         	<div class="stat_list">
                         		<p class="middle">${bybMoneyPlanone.sumMoney}<em>元</em></p>
                         		<p>计划总额</p>
                         	</div>
                         	<div class="stat_list" style="width:70px;">
                         		<p class="middle">${bybMoneyPlanone.lockingLimit}<em>个月</em></p>
                         		<p>锁定期</p>
                         	</div>
                         	<div class="stat_list stat_last">
                         		<p class="middle">${bybMoneyPlanone.startMoney}<em>元</em></p>
                         		<p>起投金额</p>
                         	</div>
                        </div>
                        <div class="times">                  
	                        <span style="line-height:35px;">还剩：<i class="img-bg"></i><span class="red" id="bybMoneyPlanonedjs"><span id="day_show">0天</span>
							<span id="hour_show">0时</span>
							<span id="minute_show">0分</span>
							<span id="second_show">0秒</span>
                             </span></span>
	                        <span>投资进度： <span  id='bybMoneyPlanoneprogress' class='progressBar'>${bybMoneyPlanone.progress}</span></span>
                        </div>
                        
                        <#--<p><#if bybMoneyPlanone==null>敬请期待<#else>预期年化:${bybMoneyPlanone.yeaRate}--${bybMoneyPlanone.maxYearRate}，计划总额:${bybMoneyPlanone.sumMoney}
                        </br>计划总额:${bybMoneyPlanone.investlimit} ，起投金额:${bybMoneyPlanone.startMoney}</p>-->
                        <p ><a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${bybMoneyPlanone.mmplanId}">立即加入</a></p>
                    </div>
                    </#if>
                </li>                
                <li class="wad_product_li" style="height:210px;">
                 <#if bybMoneyPlantwo==null>敬请期待<#else>
                    <div class="product_news">
                        <h3>${bybMoneyPlantwo.manageMoneyTypeName}</h3>
                         <input id="bybMoneyPlantwoafterTime" type="hidden" value="${bybMoneyPlantwo.afterTime}">
                       <div class="product_news_stat">
                         	<div class="stat_list">
                         		<p class="middle">≥ ${bybMoneyPlantwo.yeaRate}%</p>
                         		<p>预期年化</p>
                         	</div>
                         	<div class="stat_list">
                         		<p class="middle">${bybMoneyPlantwo.sumMoney}<em>元</em></p>
                         		<p>计划总额</p>
                         	</div>
                         	<div class="stat_list" style="width:70px;">
                         		<p class="middle">${bybMoneyPlantwo.lockingLimit}<em>个月</em></p>
                         		<p>锁定期</p>
                         	</div>
                         	<div class="stat_list stat_last">
                         		<p class="middle">${bybMoneyPlantwo.startMoney}<em>元</em></p>
                         		<p>起投金额</p>
                         	</div>
                        </div>
                        <div class="times">                  
	                        <span style="line-height:35px;">还剩：<i class="img-bg"></i><span class="red"><span id="day_show1">0天</span>
							<span id="hour_show1">0时</span>
							<span id="minute_show1">0分</span>
							<span id="second_show1">0秒</span>
                             </span></span>
	                          <span>投资进度： <span  id='bybMoneyPlantwoprogress' class='progressBar'>${bybMoneyPlantwo.progress}</span></span>
                        </div>
                        <#-- <p><#if bybMoneyPlantwo==null>敬请期待<#else>预期年化:${bybMoneyPlanone.yeaRate}--${bybMoneyPlanone.maxYearRate}，计划总额:${bybMoneyPlanone.sumMoney}
                        </br>计划总额:${bybMoneyPlanone.investlimit} ，起投金额:${bybMoneyPlanone.startMoney}</#if></p>-->
                        <p><a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${bybMoneyPlantwo.mmplanId}">立即加入</a></p>
                    </div>
                    </#if>
                </li>                
                <li class="wad_product_li wad_product_lastli" style="height:210px;">
                    <div class="product_news">
                     <#if bybMoneyPlantree==null>敬请期待<#else>
                        <h3>${bybMoneyPlantree.manageMoneyTypeName}</h3>
                         <input id="bybMoneyPlantreeafterTime" type="hidden" value="${bybMoneyPlantree.afterTime}">
                       <div class="product_news_stat">
                         	<div class="stat_list">
                         		<p class="middle">≥ ${bybMoneyPlantree.yeaRate}%</p>
                         		<p>预期年化</p>
                         	</div>
                         	<div class="stat_list">
                         		<p class="middle">${bybMoneyPlantree.sumMoney}<em>元</em></p>
                         		<p>计划总额</p>
                         	</div>
                         	<div class="stat_list" style="width:70px;">
                         		<p class="middle">${bybMoneyPlantree.lockingLimit}<em>个月</em></p>
                         		<p>锁定期</p>
                         	</div>
                         	<div class="stat_list stat_last">
                         		<p class="middle">${bybMoneyPlantree.startMoney}<em>元</em></p>
                         		<p>起投金额</p>
                         	</div>
                        </div>
                        <div class="times">                  
	                        <span style="line-height:35px;">还剩：<i class="img-bg"></i><span class="red" id="bybMoneyPlantreedjs"><span id="day_show2">0天</span>
							<span id="hour_show2">0时</span>
							<span id="minute_show2">0分</span>
							<span id="second_show2">0秒</span>
                             </span></span>
	                           <span>投资进度： <span  id='bybMoneyPlantreeprogress' class='progressBar'>${bybMoneyPlantree.progress}</span></span>
	                        <span>100%</span>
                        </div>
                       <#--><p><#if bybMoneyPlantree==null>敬请期待<#else>预期年化:${bybMoneyPlanone.yeaRate}--${bybMoneyPlanone.maxYearRate}，计划总额:${bybMoneyPlanone.sumMoney}
                        </br>计划总额:${bybMoneyPlanone.investlimit} ，起投金额:${bybMoneyPlanone.startMoney}</#if></p>-->
                        <p><a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${bybMoneyPlantree.mmplanId}">立即加入</a></p>
                    </div>
                    </#if>
                </li>
            </ul>
            <p class="wad_product_tishi"><a href="${base}/html/wad_qq.html">重磅提示：余额保计划S，充值5万元以上客户想获得更高余额收益保障，请直接联系客服，开通专项通道服务</a></p>
        </div>  

        <!--动态及公告-->
        <div class="wad_title">
            <div class="wad_title_left"><span class="big">最新动态</span></div>
            <div class="wad_title_right"><a href="${base}/article/newslistArticle.do?lid=33" target="_blank">更多</a></span></div>
        </div>
        <ul class="ui-list wad_ui_list">
            <#list listArticle25 as list1>
                <#if list1_index<6>
                <li><span class="list-left regular"><a href="${base}/article/newscontentArticle.do?catId=${list1.id}">${list1.title!}</a></span><span class="time" >${list1.createDate!}</span></li>
                </#if> 
            </#list>
        </ul>
    </div>
</div>
</div>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl"> 
<#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl">
<script type="text/javascript">
$(function () {
    function wad_bgimg() {
        $('.wad_product_li').hover(function () {
            $(this).find('.product_bgimg').animate({
                top : '150px'
            });
        }, function () {
            $(this).find('.product_bgimg').stop().animate({
                top : '0px'
            });
        });
    }
    wad_bgimg();

    function wad_li_spec2_hover(){
        $('.wad_li_spec2').hover(function () {
            $(this).css('background','#eee');
        },function () {
            $(this).css('background','none');
        });
    }
    wad_li_spec2_hover();
});
</script>
</body>
</html>
