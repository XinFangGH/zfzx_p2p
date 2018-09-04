<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>${systemConfig.metaTitle} -<#if planType=='Dir'> 我要投资<#else> 债权转让</#if></title>
    <meta name="description" content="${systemConfig.metaTitle} - <#if planType=='Dir'> 我要投资<#else> 债权转让</#if>,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - <#if planType=='Dir'> 我要投资<#else> 债权转让</#if>,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript">var m1="<#if planType=='Dir'>我要投资<#else>债权转让</#if>",m2="",m3="";</script>
<script type="text/javascript">
$(function(){

		$('a[rel*=leanModal]').leanModal({ top : 200 });		
});

$(document).ready(
	
		//进度条触发
		function() {
		  <#list pager.list as list0 >
			$("#pb${list0.bidId}").progressBar();
		  </#list>
		
		var $bidForm = $("#queryForm");
	    var $attribute = $("#filter a.attribute");
	    var $pageNumber=$("#pageNumber");
	    var $pageSize=$("#pageSize");
	    var $filter = $("#filter dd");
	    // 清空cookie
	    if($("#Q_payIntersetWay_S_EQ").val()==""){
	     $.cookie("paytype",null);
	    }
	    if($("#payTimeInput").val()==""){
	     $.cookie("payTime",null);
	    }
	    if($("#bidMoneyInput").val()==""){	   
	     $.cookie("bidMoney",null);
	    }
	     if($("#bidtype").val()==""){
	     $.cookie("bidtype",null);
	    }
	     if($("#businessFromInput").val()==""){
	     $.cookie("businessFrom",null);
	    }
	     if($("#businessTypeInput").val()==""){
	     $.cookie("proKeepType",null);
	    }
	     if($("#bidStateInput").val()==""){
	     $.cookie("bidState",null);
	    }
	    
	    //通过cookie 设置样式
	   $filter.each(function() {
			var $this = $(this);
			var $curr=$this.find("a");
			
			$curr.each(function(){
			var $thisCurr=$(this);
				if ($thisCurr.attr('lang') ==  $.cookie($this.attr('id'))) {
					$thisCurr.addClass("selected");
					$this.find("a:first").removeClass("selected");
				}
			});
			
		});
		
	    $attribute.click(function() {
			var $this = $(this);
			if ($this.hasClass("selected")) {
				$this.closest("dl").find("input").prop("disabled", true);
				
			} else {
				$this.closest("dl").find("input").prop("disabled", false).val($this.attr('lang'));
			    $.cookie($this.closest("dd").attr('id'),$this.attr('lang'));
			    
			     var $curr0=$this.closest("dl").find("a");
			    if($curr0.html()=="全部"){
			     $curr0.attr("class","attribute");
			    }
			}
			
			$this.attr("class","attribute selected");
			$pageNumber.val(1);
			$bidForm.submit();
			return false;
		});
}
	);
	$(function() {
	// 合作伙伴图标水平循环滚动
	scrollPartner();

});
</script>
</head>
<body >
<!--整体布局
<div> 
  <!-- topbar -->
  <!-- header --><!-- navbar -->
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
  <div class="main">
  
<!-- 头部结束 -->
<!--start: Container -->


			<form id="queryForm" name="queryForm" method="get" action="${base}/creditFlow/financingAgency/listPlBidPlan.do">					
				<input type="hidden" id="pageNumber" name="pager.pageNumber" value="${pager.pageNumber}"/>
				<input type="hidden" id="pageSize" name="pager.pageSize" value="${pager.pageSize}"/>
				
				
<div id="filter" class="bidsearch">
    <dl>
      <dt>还款方式：<input id="Q_payIntersetWay_S_EQ" type="hidden" name="Q_payIntersetWay_S_EQ" value="${plBidPlanFilter.payIntersetWay!}" /> </dt>
      <dd id="paytype" > <a href="javascript:;" lang="" class="selected attribute" style="margin-right: 25px;">全部</a>
      <a href="javascript:;" class="attribute" lang="1" >等额本息</a> 
      <a href="javascript:;" class="attribute" lang="2" >等额本金</a> 
      <a href="javascript:;" class="attribute" lang="3" >等本等息</a> 
      <!--  <a href="javascript:;" class="attribute" lang="4" >按日计息到期还本</a>  -->
      <a href="javascript:;" class="attribute" lang="5" >期末一次性支付本息</a>
      </dd>
    </dl>
    <!--<dl>
      <dt> 业务来源：<input type="hidden" id="businessFromInput" name="Q_businessFrom_S_EQ" value="${plBidPlanFilter.businessFrom!}" /></dt>
      <dd id="businessFrom"> <a href="javascript:void(0)" lang="" class="selected attribute" style="margin-right: 25px;">全部</a> <a href="javascript:void(0)"  lang="Pawn" class="attribute"  style="margin-right: 25px;">典当行</a><a href="javascript:void(0)"  lang="SmallLoan" class="attribute"  style="margin-right: 25px;">小贷公司</a><a href="javascript:void(0)"  lang="guarantee" class="attribute"  style="margin-right: 25px;">担保公司</a></dd>
    </dl>-->
    <dl>
      <dt> 业务类别：<input type="hidden" id="businessTypeInput" name="Q_proKeepType_S_EQ" value="${plBidPlanFilter.proKeepType!}" />
       </dt>
      <dd id="proKeepType"> <a href="javascript:void(0)" lang="" class="selected attribute" style="margin-right: 25px;">全部</a> <#list businessTypeList as list><a href="javascript:void(0)"  lang="${list.keyStr}" class="attribute"  style="margin-right: 25px;">${list.name}</a></#list></dd>
    </dl>
    <dl>
      <dt> 项目期限： <input type="hidden" id="payTimeInput" name="payTime" value="${plBidPlanFilter.payTime!}"  /></dt>
      <dd id="payTime"> <a href="javascript:void(0);" lang="" class="selected attribute" style="margin-right: 25px;">全部</a>  <a href="javascript:void(0);" class="attribute" lang="1-3-M">三个月以下</a> <a href="javascript:void(0);" class="attribute" lang="3-6-M">3-6个月</a> <a href="javascript:void(0);" class="attribute" lang="6-12-M">6个月以上</a> </dd>
    </dl>
    
    <dl>
      <dt> 招标状态： <input type="hidden" id="bidStateInput" name="Q_state_N_EQ" value="${plBidPlanFilter.bidState!}"  /> </dt>
      <dd id="bidState"> <a href="javascript:void(0);" lang="" class="selected attribute" style="margin-right: 25px;">全部</a>  <a href="javascript:void(0);" class="attribute" lang="1">投标中</a> <a href="javascript:void(0);" class="attribute" lang="2">已齐标</a> <a href="javascript:void(0);" class="attribute" lang="5">还款中</a><a href="javascript:void(0);" class="attribute" lang="6">已完成</a> <a href="javascript:void(0);" class="attribute" lang="3">已流标</a> </dd>
    </dl>
   <dl>
      <dt> 金额范围：<input type="hidden" id="bidMoneyInput" name="bidMoney" value="${plBidPlanFilter.bidMoney!}"  /> </dt>
      <dd id="bidMoney"> <a href="javascript:void(0);" lang="" class="selected attribute" style="margin-right: 25px;">全部</a> <a href="javascript:void(0);" class="attribute"  lang="1-5000">1-5千元</a> <a href="javascript:void(0);" class="attribute"  lang="5001-10000">5001-1万元</a> <a href="javascript:void(0);" class="attribute"  lang="10000-50000">1万元-5万元</a> <a href="javascript:void(0);" class="attribute"  lang="50000-100000">5万元-10万元</a> <a href="javascript:void(0);" class="attribute"  lang="100000-500000">10万元-50万元</a> <a href="javascript:void(0);" class="attribute"  lang="500000-1000000">50万元-100万元</a> <span class="hidden-city"> <a href="javascript:void(0);" class="attribute"  lang="1000001-100000000">100万元以上</a> </span>  </dd>
    </dl>
    <dl>
      <dt> 标的类型：<input type="hidden" id="bidtype" name="Q_plBiddingType.biddingTypeId_L_EQ" value="${plBidPlanFilter.bidType!}" />
       </dt>
      <dd id="bidtype"> <a href="javascript:void(0)" lang="" class="selected attribute" style="margin-right: 25px;">全部</a> <#list bidtypeList as list><a href="javascript:void(0)"  lang="${list.biddingTypeId}-T" class="attribute"  style="margin-right: 25px;">${list.name}</a></#list></dd>
    </dl>
</div>

		<div class="tenderlist" >
    	<div class="title">
        	<div style="float:left; padding:10px 0px 0px 30px;"><img src="${base}/theme/${systemConfig.theme}/images/icon1.jpg" /></div>
        	<div style="width:200px; height:30px; float:left; margin:10px 0px 0px 10px;"><span class="large3 black"><#if planType=='Dir'> 我要投资<#else> 债权转让</#if>项目列表</span></div>
            <div style="width:100px; height:20px; float:right; margin:20px 10px 0px 0px;"></div>
        </div>
        
        <#list pager.list as list>
       
		  <!-- start:通过奇 偶 判断 样式 -->
        <div <#if (list_index + 1) % 2 == 1 >  class="content" <#else> class="content2"</#if>>
        
        
        <!--start:新版项目列表-->
     
        	<div class="title">
        		<div style="height:30px; float:left; margin:12px 0px 0px 20px; display:inline;"><img src="${base}/theme/${systemConfig.theme}/images/bid_<#if (list.proType=="B_Dir"||list.proType=="B_Or")>company</#if><#if (list.proType=="P_Dir"||list.proType=="P_Or")>person</#if>.gif" width="24" height="24" /></div>
                <div style="height:30px; float:left; margin:12px 0px 0px 10px; display:inline;"><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_self"><span class="black big">${list.bidProName}</span></a></div>
                <div style="width:400px; height:20px; float:left; margin:17px 10px 0px 30px; display:inline;"><span class="gray normal">项目编号：${list.bidProNumber}</span></div></div>
      		<div style="width:950px; height:1px; border-bottom:1px #e0e0e0 dashed; margin:15px auto 5px auto; line-height:1px;" title="虚线"></div>
            <div style="float:left; width:260px; height:80px; margin:5px 10px 10px 40px;">
            	<ul><li style="float:left; width:55px "><div class="buttonorange1 labbtn0"><#if list.progress==100>还款中<#else> 投标中</#if></div></li>
            	    <li style="float:left; width:105px"><div class="buttongray1 labbtn1" >${list.proKeepType}</div></li>
                    <li style="float:left; width:70px"><div class="buttongray1 labbtn2" >${list.plBiddingType.name}</div></li>
                    <li style="float:left; width:250px;padding:10px 10px 10px 0px; display:inline; "><div style=" width:200px; text-align:left;" class="black middle" >担保机构：${list.orgName}</div></li>
                </ul>
            </div>
            <div style="float:left; width:250px; height:80px;margin:0px 0px 0px 5px; display:inline;">
            	<ul>
                	<li style="height:25px"><span class="black middle">融资金额：&nbsp;&nbsp;</span><span class="black middle">${list.bidMoney?string(',##0.00')}元</span></li>
                    <li style="height:25px"><span class="black middle">年化收益率：&nbsp;&nbsp;</span><span  class="orange middle"><#if (list.proType=="B_Dir")>
										${list.bpBusinessDirPro.yearInterestRate}%
									</#if><#if (list.proType=="B_Or")>
										${list.bpBusinessOrPro.yearInterestRate}%
									</#if><#if (list.proType=="P_Dir")>
										${list.bpPersionDirPro.yearInterestRate}%
									</#if><#if (list.proType=="P_Or")>
										${list.bpPersionOrPro.yearInterestRate}%
									</#if></span></li>
                    <li style="height:25px"><span class="black middle">还款期限：&nbsp;&nbsp;</span><span class="black middle"><#if (list.proType=="B_Dir")>
										${list.bpBusinessDirPro.loanLife}个月
									</#if><#if (list.proType=="B_Or")>
										${list.bpBusinessOrPro.loanLife}个月
									</#if><#if (list.proType=="P_Dir")>
										${list.bpPersionDirPro.loanLife}个月
									</#if><#if (list.proType=="P_Or")>
										${list.bpPersionOrPro.loanLife}个月
									</#if></span></li>
                    <li style="height:25px"><span class="black middle">还款方式：&nbsp;&nbsp;</span><span class="black middle"> <#if list.payIntersetWay==1>等额本息</#if>
										<#if list.payIntersetWay==2>等额本金</#if>
										<#if list.payIntersetWay==3>等本等息</#if>
										<#if list.payIntersetWay==4>按日计息到期还本</#if>
										<#if list.payIntersetWay==5>期末一次性支付本息</#if></span></li>
                  <!--  <li><span class="black middle">所&nbsp;在&nbsp;地：&nbsp;&nbsp;</span><span class="black middle">北京市</span></li> -->
                </ul>
            </div>
            <div style="float:left; width:250px; height:80px;">
            	<ul>
                	<li style="height:25px"><span class="black middle">投标进度&nbsp;</span><span class="progressBar" id="pb${list.bidId}">${list.progress}%</span></li>
                    <li style="height:25px"><span class="black middle">剩余投资额&nbsp;</span><span class="black middle">${list.afterMoney?string(',##0.00')}元</span></li>
                     <!--<li style="height:25px"><span class="black middle">剩余时间&nbsp;</span> <span style="color:#F90; font-size:20px;"></span></li> -->
                    <li style="height:25px"><span class="black middle">100%本息保障</span> <span style="color:#F90; font-size:20px;"></span></li>
                    <li><span class="black middle"></span></li>
                </ul>
            </div>
            <div style="float:right;margin:20px 60px 0px 20px;">
            <#if (list.laveTime)??>
            		<#if list.laveTime?index_of('结束')!=-1>
            			<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_self"><span class="buttonnotcolor" style="font-size:16px; padding:6px 18px;">招标到期</span></a>
            		<#else>
            			 <#if list.state==1>
			            	 <a id="go" rel="leanModal" name="signup" onClick="check('${list.bidId}','${list.afterMoney}',
			            	 <#if (list.proType=="B_Dir")>${list.bpBusinessDirPro.yearInterestRate}</#if>
							 <#if (list.proType=="B_Or")>${list.bpBusinessOrPro.yearInterestRate}</#if>
							 <#if (list.proType=="P_Dir")>${list.bpPersionDirPro.yearInterestRate}</#if>
							 <#if (list.proType=="P_Or")>${list.bpPersionOrPro.yearInterestRate}</#if>)" href="#signup">
							<span class="buttonorange" style="font-size:16px; padding:6px 18px;">马上投标</span></a>
		            	 <#else>
		            		<a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_self">
		            		<span class="buttonnotcolor" style="font-size:16px; padding:6px 26px;">已满标</span></a>
						 </#if>
            		</#if>
            	<#else>
            		<span class="buttonorange" style="font-size:16px; padding:6px 26px;">还款中</span>
            </#if>
            </div>
        </div>	
			

     <!--end:新版项目列表-->  			
        </#list>
                		
        
        <#import "/WEB-INF/template/common/pager.ftl" as p>
         			<#assign parameterMap = {"Q_plBiddingType.biddingTypeId_L_EQ":(plBidPlanFilter.bidType)! ,"Q_payIntersetWay_S_EQ":(plBidPlanFilter.payway)!,"payTime":(plBidPlanFilter.payTime)!,"bidMoney":(plBidPlanFilter.bidMoney)!,"Q_proKeepType_S_EQ":(plBidPlanFilter.proKeepType)!,"Q_businessFrom_S_EQ":(plBidPlanFilter.businessFrom)!,"Q_state_N_EQ":(plBidPlanFilter.bidState)!} />
         			<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/listPlBidPlan.do" parameterMap = parameterMap />
    </div>
  </div>
  </div>
  </form> 
 <!-- partner-pic --><!-- partner-text --><!-- sitemap --><!-- copyright --><!-- permit -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</div>
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl">
	</body>
</html>