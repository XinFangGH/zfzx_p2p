<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 主页</title>
    <meta name="description" content="${systemConfig.metaTitle} - 主页,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 主页,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

    <meta property="qc:admins" content="1451334700445346756164116375" />
<meta property="wb:webmaster" content="2516fb302e734849" />
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>


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
		}
		
		
);




</script>

</head>
<body>
  <!-- topbar -->
  <!-- header --><!-- navbar -->
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
   
  <!-- start:banner -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/banner.ftl">
 <!--公告预告-->
<div class="mAnnouncement"> 
    <div class="renav">
    	<span class="icon-laba"></span>
		<p>网站公告  <span>|</span></p>
		<ol class="mAnnouncementList">
			<#list listA as lista>
	        	<li>
					<a href="${base}/article/newscontentArticle.do?catId=${lista.id}"  target='_blank'>${lista.title!}！</a>
				    <span>${lista.createDate!}</span>
			 	</li>
	        </#list>
	        <!--<a href="${base}/article/newslistArticle.do?lid=30" class="more"  target='_blank'>更多公告&nbsp;&gt;</a>-->
		</ol>
		<div class="big-data">
			<span>平台累计交易额  3,034,945,490  元</span>
			<a href="${base}/user/toPublishBpCustMember.do">平台数据</a>
		</div>
	</div>
</div>
  <!-- end:banner -->
    <!--start:官方公告-->
     <#--<div class="DataShow">
    	<div class="qn_wrapper">
    		<ul>
    		 <#if platDataPublish!=null >
    			<li>
    				<i class="tb_one"></i>
    				<div class="fl_data">
	    				<span class="fs-30"><#if (platDataPublish.loneMoney>=10000)>${(platDataPublish.loneMoney/10000)?string(",##0.00#")}万<#else>${platDataPublish.loneMoney?string(',##0')}</#if></span>
	    				<span class="fs-16">交易金额</span>
    				</div>
    				
    			</li>
    			<li>
    				<i class="tb_two"></i>
    				<div class="fl_data">
	    				<span class="fs-30"><#if (platDataPublish.loneCount>=10000)>${(platDataPublish.loneCount/10000)}万<#else>${platDataPublish.loneCount}</#if></span>
	    				<span class="fs-16">交易笔数</span>
    				</div>
    			</li>
    			<li>
    				<i class="tb_three"></i>
    				<div class="fl_data">
	    				<span class="fs-30"><#if (platDataPublish.lenderCount>=10000)>${(platDataPublish.lenderCount/10000)}万<#else>${platDataPublish.lenderCount}</#if></span>
	    				<span class="fs-16">出借人数量</span>
    				</div>
    			</li>
    			<li>
    				<i class="tb_four"></i>
    				<div class="fl_data">
	    				<span class="fs-30"><#if (platDataPublish.borrowerCount>=10000)>${(platDataPublish.borrowerCount/10000)}万<#else>${platDataPublish.borrowerCount}</#if></span>
	    				<span class="fs-16">借款人数量</span>
    				</div>
    			</li>
    		 <#else>
    				<li>
    				<i class="tb_one"></i>
    				<div class="fl_data">
	    				<span class="fs-30">4,367万</span>
	    				<span class="fs-16">交易金额</span>
    				</div>
    				
    			</li>
    			<li>
    				<i class="tb_two"></i>
    				<div class="fl_data">
	    				<span class="fs-30">4,367万</span>
	    				<span class="fs-16">交易笔数</span>
    				</div>
    			</li>
    			<li>
    				<i class="tb_three"></i>
    				<div class="fl_data">
	    				<span class="fs-30">4,367万</span>
	    				<span class="fs-16">出借人数量</span>
    				</div>
    			</li>
    			<li>
    				<i class="tb_four"></i>
    				<div class="fl_data">
	    				<span class="fs-30">4,367万</span>
	    				<span class="fs-16">借款人数量</span>
    				</div>
    			</li>
    			</#if>
    			<a href="${base}/user/toPublishBpCustMember.do" class="btn">查看更多</a>
    		</ul>  
    		
    		 		
    	</div>  	
    </div>-->
    <!--end:数据报告--> 
  
  <!-- main -->
<div class="main">
	<!--宣传图-->
	<div class="pucrit">
            <div class="valuepic">
                  <div class="blockdiv">
                    <div class="picdiv">
                        <img src="${base}/theme/${systemConfig.theme}/images/valuepic1.png">
                    </div>
                   <div class="bigtext">回报丰厚</div>
                   <div class="smalltext">12%-15%的稳定</div>
                   <div class="smalltext">年化收益悦享理财尊贵服务</div>
                </div>
                <div class="blockdiv">
                    <div class="picdiv">
                        <img src="${base}/theme/${systemConfig.theme}/images/valuepic2.png" >
                    </div>
                   <div class="bigtext">安全保障</div>
                   <div class="smalltext">5重保障9大关卡39道</div>
                   <div class="smalltext">流程为您安心理财保驾护航</div>
                </div>
                <div class="blockdiv">
                    <div class="picdiv">
                        <img src="${base}/theme/${systemConfig.theme}/images/valuepic3.png" >
                    </div>
                    <div class="bigtext">新手指南<!--<a href="${base}/creditFlow/financingAgency/experienceBidContentPlBidPlan.do"></a>--></div>
                    <div class="smalltext">1分钟了解升升投  </div>
                    <div class="smalltext">3步注册认证开启互联网理财新里程</div>
                </div>
                 <div class="blockdiv">
                    <div class="picdiv">
                        <img src="${base}/theme/${systemConfig.theme}/images/valuepic4.png">
                    </div>
                    <div class="bigtext">百元起投</div>
                    <div class="smalltext">百元即可起投</div>
                    <div class="smalltext">无门槛限制打造大众理财新概念</div>
                </div>
                
            </div>
        </div>
   <!--end:宣传图-->
<!-- 理财计划 -->
 <div style=" width:1198px;  overflow:hidden; background:#fff; border-top:0; border-bottom:0;border:1px solid #ddd;margin:20px 0;">
  	<div class="xy-lc-recommend-bar">
    	<h2>债权批量转让</h2>
        <span>升升投提供的安全便捷理财工具，分散投资于升升投的债权。有四种投资期限可供选择，提供本息保障。</span>
        <a class="xy-lc-recommend-bar-more" href="${base}/creditFlow/financingAgency/allPlmanagemoneyPlBidSale.do">查看往期历史计划>></a>
    </div>
	<div class="xy-lc-ul">
        <ul>
         <#if bybMoneyPlanone!=null >
           <li class="xy-lc-ul-limargin">
            	<i class="licai-bg"></i>
                <div class="xy-lc-ul-tag">
                    <p class="xy-lc-ul-trait">
                    <#if bybMoneyPlanone.mmName!=null>
                    <a title="${bybMoneyPlanone.mmName}" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${bybMoneyPlanone.mmplanId}"> 
                    <#if bybMoneyPlanone.mmName?length gt 7>${bybMoneyPlanone.mmName?substring(0,7)}..<#else>
                    ${bybMoneyPlanone.mmName}</#if>
                    </a>
                    <#else>
                    </#if>
                    </p>
	                <#if bybMoneyPlanone.mmName!=null>
	                <h3>
	               	<#if bybMoneyPlanone.manageMoneyTypeName!=null>
	                <a title="${bybMoneyPlanone.manageMoneyTypeName}" href="">
	                <#if bybMoneyPlanone.manageMoneyTypeName?length gt 7>${bybMoneyPlanone.manageMoneyTypeName?substring(0,7)}..
	                <#else>${bybMoneyPlanone.manageMoneyTypeName}
	                </#if>
	                </a>
	                <#else>
	                </#if>
	                </h3>
	                <#else>
	                <p class="xy-lc-ul-trait">
	                <#if bybMoneyPlanone.manageMoneyTypeName!=null>
		                  <a title="${bybMoneyPlanone.manageMoneyTypeName}" href="javascript:void(0);"> <#if bybMoneyPlanone.manageMoneyTypeName?length gt 7>
		                   ${bybMoneyPlanone.manageMoneyTypeName?substring(0,7)}..
		                   <#else>
		                   ${bybMoneyPlanone.manageMoneyTypeName}
		                   </#if>
		                   </a>
		                   <#else>
		                   </#if>	
	                  </p>
	                    </#if>
                </div>
                <#if bybMoneyPlanone.mmplanId!=null>
                	<p class="xy-lc-ul-money-text">预期年化收益率</p>
	                <p class="xy-lc-ul-money">${bybMoneyPlanone.yeaRate}%</span></p>
	                <p class="xy-lc-ul-state"><em class="licai_pic"></em>资金本息保障<br>投资小额分散 ，项目严格风控</p>
	                <p class="xy-lc-ul-mesg">
	                    <span>计划金额：<em>${bybMoneyPlanone.sumMoney?string(',##0')}元</em></span>
	                    <#--<span>结束日期：<em>${bybMoneyPlanone.buyEndTime}起</em></span>-->
	                    <span>投资期限：<em>${bybMoneyPlanone.investlimit}个月</em></span>
	                     <#--<span>起购金额：<em>${bybMoneyPlantwo.buyEndTime}元</em></span>-->
	                    <span>截止时间：<em>${bybMoneyPlanone.buyEndTime?string("yyyy-MM-dd HH:mm")}</em></span>
	                </p>
	                <p class="xy-lc-ul-nextdate">
	                    <a>投资进度：${bybMoneyPlanone.progress}%</a>
	                </p>
	                <#if bybMoneyPlanone.state!=null >
		                <#if bybMoneyPlanone.state=='-2'>
		                	<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#ed5564;" >即将开始</a>
		                <#elseif bybMoneyPlanone.state=='1'>
		                	<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${bybMoneyPlanone.mmplanId}" class="xy-lc-ul-btn">立即购买</a>
		                <#elseif bybMoneyPlanone.state=='7'>
		                	<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#2770b8;color:#fff;" >回款中</a>
		                <#else>
		                	<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#2770b8;color:#fff;" >已售罄</a>
		                </#if>
	                <#else>
	               		<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#2770b8;color:#fff;" >敬请期待</a>
	            	</#if>
                <#else>
                	 <div class="qidai-pic" style="padding:120px 0;"><img src="${base}/theme/${systemConfig.theme}/images/qidai.png"></div>
                </#if>
			</li>
            <#else>
              <li class="xy-lc-ul-limargin" style="margin-right:0; border-top:0;border: solid 1px #d5d4d4;">
             	<div class="qidai-pic"><img src="${base}/theme/${systemConfig.theme}/images/qidai.png"></div>
            </li>
            </#if>
            <#if bybMoneyPlantwo!=null >
            <li class="xy-lc-ul-limargin">
            	<i class="licai-bg"></i>
                <div class="xy-lc-ul-tag">
                    <p class="xy-lc-ul-trait">
                    <#if bybMoneyPlantwo.mmName!=null>
                    <a title="${bybMoneyPlantwo.mmName}" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${bybMoneyPlantwo.mmplanId}">
                    <#if bybMoneyPlantwo.mmName?length gt 7>${bybMoneyPlantwo.mmName?substring(0,7)}..<#else>${bybMoneyPlantwo.mmName}</#if></a>
                    <#else></#if></p>
                    <#if bybMoneyPlantwo.mmName!=null>
                    <h3>
                    <#if bybMoneyPlantwo.manageMoneyTypeName!=null>
                    	<a title="${bybMoneyPlantwo.manageMoneyTypeName}" href="">
	                    <#if bybMoneyPlantwo.manageMoneyTypeName?length gt 7>${bybMoneyPlantwo.manageMoneyTypeName?substring(0,7)}..
	                    <#else>
	                    ${bybMoneyPlantwo.manageMoneyTypeName}</#if>
	                    </a>
	                    <#else>
	                   </#if>
                     
                    </h3>
                    <#else>
                    <p class="xy-lc-ul-trait"><#if bybMoneyPlantwo.manageMoneyTypeName!=null><a title="${bybMoneyPlantwo.manageMoneyTypeName}" href="javascript:void(0);"><#if bybMoneyPlantwo.manageMoneyTypeName?length gt 7>${bybMoneyPlantwo.manageMoneyTypeName?substring(0,7)}..<#else>${bybMoneyPlantwo.manageMoneyTypeName}</#if></a><#else></#if></p></#if>
                </div>
                <#if bybMoneyPlantwo.mmplanId!=null>
                	<p class="xy-lc-ul-money-text">预期年化收益率</p>
	                <p class="xy-lc-ul-money">${bybMoneyPlantwo.yeaRate}%</p><!--${bybMoneyPlantwo.yeaRate}-->
	                <p class="xy-lc-ul-state"><em class="licai_pic"></em>资金本息保障<br>投资小额分散 ，项目严格风控</p>             
	                <p class="xy-lc-ul-mesg">
	                    <span>计划金额：<em>${bybMoneyPlantwo.sumMoney?string(',##0')}元</em></span>                   
	                    <span>投资期限：<em>${bybMoneyPlantwo.investlimit}个月</em></span>
	                     <#--<span>起购金额：<em>${bybMoneyPlantwo.buyEndTime}元</em></span>-->
	                    <span>截止时间：<em>${bybMoneyPlantwo.buyEndTime?string("yyyy-MM-dd HH:mm")}</em></span>
	                    
	                </p>
	                <p class="xy-lc-ul-nextdate">
	                    <a>投资进度：${bybMoneyPlantwo.progress}%</a>
	                </p>
	               <#if bybMoneyPlantwo.state!=null >
		                <#if bybMoneyPlantwo.state=='-2'>
		                	<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#ed5564;" >即将开始</a>
		                <#elseif bybMoneyPlantwo.state=='1'>
		                	<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${bybMoneyPlantwo.mmplanId}" class="xy-lc-ul-btn">立即购买</a>
		                <#elseif bybMoneyPlantwo.state=='7'>
		                	<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#2770b8;color:#fff;" >回款中</a>
		                <#else>
		                	<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#2770b8;color:#fff;" >已售罄</a>
		                </#if>
	                <#else>
	               		<a href="javascript:void(0);" class="xy-lc-ul-btn" >敬请期待</a>
	            	</#if>
                <#else>
                	 <div class="qidai-pic" style="padding:120px 0;"><img src="${base}/theme/${systemConfig.theme}/images/qidai.png"></div>
                </#if>       
            </li>
            <#else>
              <li class="xy-lc-ul-limargin" style="margin-right:0; border-top:0;border: solid 1px #d5d4d4;">
             	<div class="qidai-pic"><img src="${base}/theme/${systemConfig.theme}/images/qidai.png"></div>
            </li>
            </#if>
            <#if bybMoneyPlantree!=null >
            <li class="xy-lc-ul-limargin">
            	<i class="licai-bg"></i>
                <div class="xy-lc-ul-tag">
                    <p class="xy-lc-ul-trait">
                    <#if bybMoneyPlantree.mmName!=null>
                    <a title="${bybMoneyPlantree.mmName}" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${bybMoneyPlantree.mmplanId}">
                    <#if bybMoneyPlantree.mmName?length gt 7>${bybMoneyPlantree.mmName?substring(0,7)}..<#else>${bybMoneyPlantree.mmName}</#if>
                    </a>
                    <#else></#if></p>
                    <#if bybMoneyPlantree.mmName!=null>
                    <h3>
                    <#if bybMoneyPlantree.manageMoneyTypeName!=null>
                    <a title="${bybMoneyPlantree.manageMoneyTypeName}" href="">
                    <#if bybMoneyPlantree.manageMoneyTypeName?length gt 7>${bybMoneyPlantree.manageMoneyTypeName?substring(0,7)}..
                    <#else>${bybMoneyPlantree.manageMoneyTypeName}</#if>
                    </a>
                    <#else>
                    </#if>
                    </h3>
                    <#else>
                    <p class="xy-lc-ul-trait"><#if bybMoneyPlantree.manageMoneyTypeName!=null><a title="${bybMoneyPlantree.manageMoneyTypeName}" href="javascript:void(0);"><#if bybMoneyPlantree.manageMoneyTypeName?length gt 7>${bybMoneyPlantree.manageMoneyTypeName?substring(0,7)}..<#else>${bybMoneyPlantree.manageMoneyTypeName}</#if></a><#else></#if></p></#if>
                </div>
                <#if bybMoneyPlantree.mmplanId!=null>
                	   <p class="xy-lc-ul-money-text">预期年化收益率</p>
		               <p class="xy-lc-ul-money">${bybMoneyPlantree.yeaRate}%</p>
		               <p class="xy-lc-ul-state"><em class="licai_pic"></em>资金本息保障<br>投资小额分散 ，项目严格风控</p>
		               <p class="xy-lc-ul-mesg">
		                    <span>计划金额：<em>${bybMoneyPlantree.sumMoney?string(',##0')}元</em></span>
		                   <#-- <span>结束日期：<em>${bybMoneyPlantree.buyEndTime}起</em></span>-->
		                    <span>投资期限：<em>${bybMoneyPlantree.investlimit}个月</em></span>
		                     <#--<span>起购金额：<em>${bybMoneyPlantwo.buyEndTime}元</em></span>-->
		                    <span>截止时间：<em>${bybMoneyPlantree.buyEndTime?string("yyyy-MM-dd HH:mm")}</em></span>
		                </p>
		                 <p class="xy-lc-ul-nextdate">
		                    <a>投资进度：${bybMoneyPlantree.progress}%</a>
		                </p>
		                 <#if bybMoneyPlantree.state!=null >
			                <#if bybMoneyPlantree.state=='-2'>
			                	<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#ed5564;" >即将开始</a>
			                <#elseif bybMoneyPlantree.state=='1'>
			                	<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${bybMoneyPlantree.mmplanId}" class="xy-lc-ul-btn">立即购买</a>
			                <#elseif bybMoneyPlantree.state=='7'>
		                		<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#2770b8;color:#fff;" >回款中</a>
			                <#else>
			                	<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#2770b8;color:#fff;" >已售罄</a>
			                </#if>
		                <#else>
		               		<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#2770b8;color:#fff;" >敬请期待</a>
		            	</#if>
                <#else>
                	 <div class="qidai-pic" style="padding:120px 0;"><img src="${base}/theme/${systemConfig.theme}/images/qidai.png"></div>
                </#if>            
            </li>
             <#else>
	             <li class="xy-lc-ul-limargin" style="border-top:0;border: solid 1px #d5d4d4;">
	             	<div class="qidai-pic"><img src="${base}/theme/${systemConfig.theme}/images/qidai.png"></div>
	            </li>
            </#if>
             <#if bybMoneyPlanfour!=null >
            	<li class="xy-lc-ul-limargin">
	            	<i class="licai-bg"></i>
	                <div class="xy-lc-ul-tag">
                    <p class="xy-lc-ul-trait">
                    <#if bybMoneyPlanfour.mmName!=null>
                    <a title="${bybMoneyPlanfour.mmName}" href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${bybMoneyPlanfour.mmplanId}">
                    <#if bybMoneyPlanfour.mmName?length gt 7>${bybMoneyPlanfour.mmName?substring(0,7)}..<#else>${bybMoneyPlanfour.mmName}</#if>
                    </a>
                    <#else></#if></p>
                    <#if bybMoneyPlanfour.mmName!=null><h3><#if bybMoneyPlanfour.manageMoneyTypeName!=null><a title="${bybMoneyPlanfour.manageMoneyTypeName}" href="javascript:void(0);"><#if bybMoneyPlanfour.manageMoneyTypeName?length gt 7>${bybMoneyPlanfour.manageMoneyTypeName?substring(0,7)}..<#else>${bybMoneyPlanfour.manageMoneyTypeName}</#if></a><#else></#if></h3><#else><p class="xy-lc-ul-trait"><#if bybMoneyPlanfour.manageMoneyTypeName!=null><a title="${bybMoneyPlanfour.manageMoneyTypeName}" href="javascript:void(0);"><#if bybMoneyPlanfour.manageMoneyTypeName?length gt 7>${bybMoneyPlanfour.manageMoneyTypeName?substring(0,7)}..<#else>${bybMoneyPlanfour.manageMoneyTypeName}</#if><#else></#if></p></#if>
                	</div>
	                <#if bybMoneyPlanfour.mmplanId!=null>
		                <p class="xy-lc-ul-money-text">预期年化收益率</p>
		                <p class="xy-lc-ul-money">${bybMoneyPlanfour.yeaRate}%</p>
		               	<p class="xy-lc-ul-state"><em class="licai_pic"></em>资金本息保障<br>投资小额分散 ，项目严格风控</p>
		                <p class="xy-lc-ul-mesg">
		                    <span>计划金额：<em>${bybMoneyPlanfour.sumMoney?string(',##0')}元</em></span>
		                    <span>投资期限：<em>${bybMoneyPlanfour.investlimit}个月</em></span>
		                    <#--<span>起购金额：<em>${bybMoneyPlantwo.buyEndTime}元</em></span>-->
		                    <span>截止时间：<em>${bybMoneyPlanfour.buyEndTime?string("yyyy-MM-dd HH:mm")}</em></span>
		                </p>
		                <p class="xy-lc-ul-nextdate">
		                    <a>投资进度：${bybMoneyPlanfour.progress}%</a>
		                </p>
		                <#if bybMoneyPlanfour.state!=null >
			                <#if bybMoneyPlanfour.state=='-2'>
			                	<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#ed5564;" >即将开始</a>
			                <#elseif bybMoneyPlanfour.state=='1'>
			                	<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${bybMoneyPlanfour.mmplanId}" class="xy-lc-ul-btn">立即购买</a>
			                <#elseif bybMoneyPlanfour.state=='7'>
		                		<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#2770b8;color:#fff;" >回款中</a>
			                <#else>
			                	<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#2770b8;color:#fff;" >已售罄</a>
			                </#if>
		                <#else>
		               		<a href="javascript:void(0);" class="xy-lc-ul-btn" style="background:#2770b8;color:#fff;" >敬请期待</a>
		            	</#if>
	                <#else>
	                	 <div class="qidai-pic" style="padding:120px 0;"><img src="${base}/theme/${systemConfig.theme}/images/qidai.png"></div>
	                </#if>                            
	           	 </li>
             <#else>
	             <li class="xy-lc-ul-limargin" style="margin-right:0; border-top:0;border: solid 1px #d5d4d4;">
	             	<div class="qidai-pic"><img src="${base}/theme/${systemConfig.theme}/images/qidai.png"></div>
	            </li>
            </#if>
        </ul>
    </div>
</div>
<!-- end 理财计划 -->
 <div style=" width:999px;  overflow:hidden; background:#fff; border-top:0; border-bottom:0;border:1px solid #ddd;margin:20px 0;">
<div class="xy-lc-recommend-bar">
            <h2>散标投资</h2>
            <span>投资于特定债权，等额本息回款方式，投资起点低至50元。</span>
            <a class="xy-lc-recommend-bar-more" href="${base}/creditFlow/financingAgency/listPlBidPlan.do">更多</a>
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
                    <#if list_index<4>
                    <li class="wad_li_spec2">
                        <span class="w260 wad_spec1">
	                        <em><img src="${base}/theme/${systemConfig.theme}/images/<#if (list.proKeepType)??><#if list.proKeepType=="信用审核标">xin.png<#elseif list.proKeepType=="实地核查标">shi.png<#elseif  list.proKeepType="机构担保标">bao.png<#elseif list.proKeepType="福利标">fu.png<#else>xin.png</#if><#else>xin.png</#if>" width="19"/></em>
	                        <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" title="${list.bidProName}">                        <#if list.bidProName?length gt 10>${list.bidProName?substring(0,10)}<#else>${list.bidProName}</#if></a>
                        	<i>
		                        <#if list.novice==1><font class="font-one">新</font></#if>
		                        <#if list.addRate!=0&&list.addRate!=""><font class="font-two">息</font></#if>
		                        <#if list.coupon==1><font class="font-three">券</font></#if>
                        	</i>
                        </span>
                        <span class="wad_xinyong w80 wad_spec2">
                        <a class="
	                        <#if (list.keepCreditlevelName)??>
		                        <#if list.keepCreditlevelName=="AA">aa
		                        <#elseif list.keepCreditlevelName=="HR">hr
		                        <#elseif list.keepCreditlevelName=="A">a
		                        <#elseif list.keepCreditlevelName=="B">b
		                        <#elseif list.keepCreditlevelName=="C">c
		                        <#elseif list.keepCreditlevelName=="D">d
		                        <#else>e
		                        </#if>
		                    <#else>
		                        a
	                        </#if>
                        ">${(list.keepCreditlevelName)!}</a>
                        </span>
                        <span class="w120"><em class="value">
                        <#if (list.proType=="B_Dir")>${list.bpBusinessDirPro.yearInterestRate}</#if>
                        <#if (list.proType=="B_Or")>${list.bpBusinessOrPro.yearInterestRate}</#if>
                        <#if (list.proType=="P_Dir")>${list.bpPersionDirPro.yearInterestRate}</#if>
                        <#if (list.proType=="P_Or")>${list.bpPersionOrPro.yearInterestRate}</#if>
                        </em>%</span>
                        <span class="w160"><em class="value">${list.bidMoney?string(',##0')}</em>元</span>
                        <span class="w100"><em class="value"> ${list.loanLife}</em></span>
                        <span class="w110 wad_spec5"><strong id="plan${list.bidId}"><em>${list.progress}%</em></strong></span>
                        <span class="w80 wad_spec6">
							<span class="wad_open">
	                        <#if list.state==2 || list.state==5|| list.state==6>已满标
	                        <#elseif list.state==3>已流标
	                        <#elseif list.state==4>已过期
	                        <#elseif list.state==10>已还清
	                        <#elseif list.state==7>还款中
	                        <#else>
	                             <#if list.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt list.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") >
	                                    <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" style="color:#fff;">投标中</a>
	                             <#else>
	                                                                                               预售中
	                             </#if>
	                        </#if>
                        	</span>
                        </span>
                    </li>
                    </#if>
                    </#list>
                    </#if>
                    
                </ul>
            </div>
    </div>
</div>
</div>

<div class="xy-footerbar">
            <div class="xy-phoneaside">
                <h3><a href="#">客服热线</a></h3>
                <span class="xy-phone">${servicePhone}</span>
                <#if systemConfig.workTime>
            		<span class="xy-servicetime">${systemConfig.workTime}</span>
            	<#else>
                	<span class="xy-servicetime">周一至周五 9:00 - 18:30</span>
				</#if>
                <p>
                    <em>升升投客户服务</em>
                    <a href="#" alt="新浪微博" class="xy-weibo weibotips" onmouseover="myshow('xy-arale-tip xy-arale-tip-01');" onmouseout="myhidden('xy-arale-tip xy-arale-tip-01');">新浪微博</a>
                    <a href="#" alt="腾讯微博" class="xy-tengxunweibo" onmouseover="myshow('xy-arale-tip xy-arale-tip-02');" onmouseout="myhidden('xy-arale-tip xy-arale-tip-02');">腾讯微博</a>
                    <a href="#" alt="微信" class="xy-weixin weixintips" onmouseover="myshow('xy-arale-tip xy-arale-tip-03');" onmouseout="myhidden('xy-arale-tip xy-arale-tip-03');">微信</a>
                    <a href="#" alt="qq" class="xy-qq" onmouseover="myshow('xy-arale-tip xy-arale-tip-04');" onmouseout="myhidden('xy-arale-tip xy-arale-tip-04');">qq</a>
                </p>
            </div>
            <div class="xy-newsaside">
                <h3><a href="#">最新动态</a></h3>
                <ul>
             <#list listArticle25 as list1>
                    <li>
                    	<a href="${base}/article/newscontentArticle.do?catId=${list1.id}">
                    	<span>${list1.createDate!}</span>
                    	${list1.title!}
                    	</a>
                    </li>
             </#list>
                </ul>
            </div>
            <div class="xy-useraside">
                <h3><a href="#">新手指南</a></h3>
                <ul>
                    <#list listArticle35 as list2>
	                    <li>
	                    	<a href="${base}/article/helpcontentArticle.do?catId=${list2.id}">
	                    	<span>${list2.createDate!}</span>
	                    	${list2.title!}
	                    	</a>
	                    </li>
             		</#list>  
             </ul>
            </div>
    </div>

<#include "/WEB-INF/template/${systemConfig.theme}/layout/partner-pic.ftl"> 
</div>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl"> 
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
