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
<script type="text/javascript">

$(document).ready(
	//进度条触发
	function(){
	<#list pager.list as list0 >
		$("#pb${list0.bidId}").progressBar();
		</#list>
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
</head>
<body>
  <!-- topbar -->
  <!-- header --><!-- navbar -->
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
  <!-- start:banner -->
  <div class="banner">
	<div class="slide">
	    <a href="/" target="_blank"><img class="hidden" src="${base}/theme/${systemConfig.theme}/images/banner2.jpg" /></a>
	   	<a href="${base}/html/wad_zhuanti_js.html" target="_blank"><img class="hidden" src="${base}/theme/${systemConfig.theme}/images/banner@js.png" /></a>
	</div>
    <div class="clickbtn">
    	<a href="javascript:void(0)"></a>
    	<a href="javascript:void(0)"></a>
    	<!--<a href="javascript:void(0)"></a> -->
    </div>
  </div>
  <!-- end:banner -->
    <!--start:官方公告-->
    
    <!--end:数据报告-->	
  
  <!-- main -->
  <div class="main">
  <!--start:宣传图和数据报告 -->
 	<div style=" width:1001px;  overflow:hidden;">
		<!--start:宣传图-->
    <div class="pucrit">
	    <div class="valuepic">
	    	<div class="blockdiv">
	    		<div class="picdiv">
	    			<img src="${base}/theme/${systemConfig.theme}/images/valuepic1.jpg" height="102" />
	    		</div>
	    		<div class="bigtext">
	    			安全保障
	    			<i class="small-icon1"></i>
	    		</div>
	    		<div class="smalltext">
	    			设立风险保证金客户投标资金100%本金保障；真正第三方托管，有效保障理财人资金安全。
	    		</div>
	    	</div>
	    	<div class="splitline2"><img src="${base}/theme/${systemConfig.theme}/images/splitline2.jpg" height="50" /></div>
	    	<div class="blockdiv">
	    		<div class="picdiv">
	    			<img src="${base}/theme/${systemConfig.theme}/images/valuepic2.jpg" height="102" />
	    		</div>
	    		<div class="bigtext">
	    			理财收益
	    			<i class="small-icon1"></i>
	    		</div>
	    		<div class="smalltext">
	    			获得预期12%-15%的稳定年华收益，理财人15秒注册，快速简单。
	    		</div>
	    	</div>
	    	<div class="splitline2"><img src="${base}/theme/${systemConfig.theme}/images/splitline2.jpg" height="50" /></div>
	    	<div class="blockdiv">
	    		<div class="picdiv">
	    			<img src="${base}/theme/${systemConfig.theme}/images/valuepic3.jpg" height="102" />	    			
	    		</div>
	    		
	    		<div class="bigtext">
	    			投资起点
	    			<i class="small-icon2"></i>
	    		</div>
	    		<div class="smalltext">
	    			最低50元即可进行理财，支持智能投标，让您理财更省心。
	    		</div>
	    	</div>
	    	
	    </div>
    <!--end:宣传图-->
		
	<!--start:官方公告-->  
      	
	<!--end:官方公告-->	
	</div>
     <div class="apply">
     	<div class="images">
     		<img src="${base}/theme/${systemConfig.theme}/images/index-img.jpg" width="219" />
     	</div>		      	
		<div class="txt">
			<ul>
				<li>个人、企业借款一站式服务</li>
				<li>繁琐的工作我们做</li>
				<li>精准匹配合作机构</li>
				<li>100%解决您的借款需求</li>
			</ul>    		
		</div>
		<div class="btn">
			<a href="${base}/financePurchase/applyComFinancePurchase.do" class="btn1"><span class="middle">企业借款</span></a>
			<a href="${base}/financePurchase/toFinancePurchase.do" class="btn2"><span  class="middle">个人借款</span></a>
		</div>
      </div>
    </div>
    <!--end:宣传图和官方公告-->
    <!-- start:项目列表 -->
    <div class="tenderlist" > 	
    	<div class="title">
    		<div style="float:left; width:35px; height:25px; padding:16px 0px 0px 5px;"><img src="${base}/theme/${systemConfig.theme}/images/arrow-1.jpg" width="25" height="24" /></div>
        	<div style="width:200px; height:30px; line-height:30px; float:left;font-weight:bold; margin:12px 0px 0px 0px;color:#595757;"><span class="big">投资理财</span></div>
            <div style="width:130px; height:20px; float:right; margin:16px 10px 0px 0px; "><a href="${base}/creditFlow/financingAgency/listPlBidPlan.do" target="_blank"><span style="color:#b0b0b0; font-size:15px;">查看更多投资项目</span></a></div>
        </div>
          <div class="bidplan1">
	    <div class="baiyibao">
			
<table class="tab_css_3-1" width="1001" border="0 cellspacing="0" cellpadding="0"" style="text-align:center; background:#fff;">
 
  <tr>
 	<th  width="10%"></th>
    <th width="10%" align="left">借款标题</th>
    <th width="10%">年化收益率</th>
    <th width="10%">信用等级</th>
    <th width="10%">剩余金额</th>
    <th width="10%">期限</th>
    <th width="15%" align="center">进度</th>
  </tr>
  <#if (pager.list)??>
 	 <#list pager.list as list>
  <tr><!-- small-icon-4.png-->
  	<td class="none" width="10%"><span style="display:inline-block;float:left;width:63px; height:28px;"><img src="${base}/theme/${systemConfig.theme}/images/<#if list.proKeepType=="信用审核标">xin.png<#elseif list.proKeepType=="实地核查标">shi.png<#elseif  list.proKeepType="机构担保标">bao.png</#if>" height="28px"/></span></td>
    <td  align="left"><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}"  title="${list.bidProName}"><#if list.bidProName?length &gt; 6>${list.bidProName?substring(0,6)}...<#else>${list.bidProName}</#if></a></td>
    <td>
		<#if (list.proType=="B_Dir")>
			${list.bpBusinessDirPro.yearInterestRate}%
		</#if><#if (list.proType=="B_Or")>
			${list.bpBusinessOrPro.yearInterestRate}%
		</#if><#if (list.proType=="P_Dir")>
			${list.bpPersionDirPro.yearInterestRate}%
		</#if><#if (list.proType=="P_Or")>
			${list.bpPersionOrPro.yearInterestRate}%
		</#if>
	</td>
    <td class="bidplan-bg">
    <#if (list.proType=="B_Dir")>
		<h3 class="<#if (list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name)??>
		<#if list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name=="AA">icon-bg1
		<#elseif list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name=="HR">icon-bg7
		<#elseif list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name=="A">icon-bg2
		<#elseif list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name=="B">icon-bg3
		<#elseif list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name=="C">icon-bg4
		<#elseif list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name=="D">icon-bg5
		<#else>icon-bg6</#if><#else>icon-bg2</#if>">
		${(list.bpBusinessDirPro.plBusinessDirProKeep.plKeepCreditlevel.name)!}
		</h3>
	</#if><#if (list.proType=="P_Dir")>
		<h3 class="<#if (list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name)??>
		<#if list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name=="AA">icon-bg1
		<#elseif list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name=="HR">icon-bg7
		<#elseif list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name=="A">icon-bg2
		<#elseif list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name=="B">icon-bg3
		<#elseif list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name=="C">icon-bg4
		<#elseif list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name=="D">icon-bg5
		<#else>icon-bg6</#if><#else>icon-bg2</#if>">
		${(list.bpPersionDirPro.plPersionDirProKeep.plKeepCreditlevel.name)!}
		</h3>
	</#if>
    </td>
    <td>${list.bidMoney?string(',###.00')}元</td>
    <td>
   		 ${list.loanLife}
    </td>
    <td align="center"> 
    	<div class="blockdiv1" style="width:60px;_width:40px;float:left;display:inline-block;">
		    		<div class="picdiv1">
		    			
		    			<div class="circleprogress45" style="text-align:center;">
			    			
			    			<span class="num">${list.progress}%</span>			    		
		    			</div>	
		    			
		    		</div>	
		    						
	   	  </div>
		  <div style="float:left;  width:60px; padding-top:16px;display:inline-block; float:left;padding-left:30px;_padding-left:20px;"><span class="big black">
		  	
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
		  	
		  </span>
		  </div>  	
	</td>
    
  </tr>
  </#list>
  </#if>
</table>

	</div>
  </div>	
    <!--end:项目列表-->
    <div class="title">
    		<div style="float:left; width:35px; height:25px; padding:16px 0px 0px 5px;"><img src="${base}/theme/${systemConfig.theme}/images/arrow-1.jpg" width="25" height="24" /></div>
        	<div style="width:200px; height:30px; line-height:30px; float:left; font-weight:bold; margin:12px 0px 0px 0px;color:#595757;"><span class="big">动态及公告</span></div>
            <div style="width:150px; height:20px; float:right; margin:16px 10px 0px 0px;text-align:right; "><a href="${base}/article/newslistArticle.do?lid=33" target="_blank"><span style="color:#b0b0b0; font-size:15px;padding:0px 10px;">更多</span></a></div>
     </div>
     <ul class="ui-list">
    
     	
     	
     	<#list listArticle25 as list1>
     	<#if list1_index<6>
	     		<li><span class="list-left regular"><a href="${base}/article/newscontentArticle.do?catId=${list1.id}">${list1.title!}</a></span><span class="time" >${list1.createDate!}</span></li>
	     </#if>	
     	</#list>
     	
     	
     </ul>
      
  </div>
  </div>

 <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl"> 
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl">
</body>
</html>
