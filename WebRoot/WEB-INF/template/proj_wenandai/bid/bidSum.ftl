<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>${systemConfig.metaTitle} - 我要投资</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
<script type="text/javascript">
	$(document).ready(
		//进度条触发
		function() {
		  <#list pager.list as list0 >
			$("#pb_${list0.bidId}").progressBar();
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
	    //通过cookie 设置样式
	   $filter.each(function() {
			var $this = $(this);
			var $curr=$this.find("a");
			
			$curr.each(function(){
			var $thisCurr=$(this);
				if ($thisCurr.attr('lang')==  $.cookie($this.attr('id'))) {
					$thisCurr.addClass("selected");
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
			}
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
      <dt> 还款方式：<input id="Q_payIntersetWay_S_EQ" type="hidden" name="Q_payIntersetWay_S_EQ" value="${plBidPlanFilter.payIntersetWay!}"  /> </dt>
      <dd id="paytype" > <a href="javascript:;" lang="" class="selected attribute" style="margin-right: 25px;">全部</a> 
      <a href="javascript:;" class="attribute" lang="1" >等额本息</a> 
      <a href="javascript:;" class="attribute" lang="2" >等额本金</a> 
      <a href="javascript:;" class="attribute" lang="3" >等本等息</a> 
      <!--  <a href="javascript:;" class="attribute" lang="4" >按日计息到期还本</a>  -->
      <a href="javascript:;" class="attribute" lang="5" >期末一次性支付本息</a> 
      </dd>
    </dl>
    <dl>
      <dt> 还款时间： <input type="hidden" id="payTimeInput" name="payTime" value="${plBidPlanFilter.payTime!}"  /> </dt>
      <dd id="payTime"> <a href="javascript:void(0);" lang="" class="selected attribute" style="margin-right: 25px;">全部</a> <a href="javascript:void(0);" class="attribute" lang="1-30-D">30天内</a> <a href="javascript:void(0);"  class="attribute" lang="1-1-M">1个月</a> <a href="javascript:void(0);" class="attribute" lang="2-2-M">2个月</a> <a href="javascript:void(0);" class="attribute" lang="3-3-M">3个月</a> <a href="javascript:void(0);" class="attribute" lang="6-6-M">6个月</a> <a href="javascript:void(0);" class="attribute" lang="12-12-M">12个月</a> <a href="javascript:void(0);" class="attribute" lang="1-3-M">1-3个月</a> <a href="javascript:void(0);" class="attribute" lang="1-6-M">1-6个月</a> <a href="javascript:void(0);" class="attribute" lang="6-12-M">6-12个月</a> </dd>
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

		<div class="tenderlist">
    	<div class="title">
        	<div style="width:200px; height:30px; float:left; margin:10px 0px 0px 10px;"><span class="large blue">投资项目列表</span></div>
            <div style="width:100px; height:20px; float:right; margin:20px 10px 0px 0px;"></div>
        </div>
        
        <#list pager.list as list>
       <#if (list_index + 1) % 2 == 1 > 
		  
		  <!-- 项目开始 -->
        <div class="content">
        	<div class="title">
        		<div style="height:30px; float:left; margin:12px 0px 0px 50px;"><img src="${base}/theme/${systemConfig.theme}/images/bid_<#if (list.proType=="B_Dir"||list.proType=="B_Or")>company</#if><#if (list.proType=="P_Dir"||list.proType=="P_Or")>person</#if>.png" width="24" height="21" /></div>
                <div style="height:30px; float:left; margin:12px 0px 0px 10px;"><a href="${base}${list.htmlPath}" target="_self"><span class="blue big">${list.bidProName}</span></a></div>
                <div style="width:400px; height:20px; float:left; margin:13px 10px 0px 30px;"><span class="gray normal">[${list.proKeepType}]</span>&nbsp;&nbsp;<span class="gray normal">项目编号：${list.bidProNumber}</span></div>
            </div>
      		<div style="width:950px; height:0px; border-bottom:1px #e0e0e0 dashed; margin:15px auto 10px auto;" title="虚线"></div>
            <div style="float:left; width:200px; height:80px; margin:0px 10px 0px 40px;">
            	<ul>
                	<li><span class="black middle">年化利率：&nbsp;&nbsp;</span><span class="orange middle"><#if (list.proType=="B_Dir")>
										${list.bpBusinessDirPro.yearInterestRate}%
									</#if><#if (list.proType=="B_Or")>
										${list.bpBusinessOrPro.yearInterestRate}%
									</#if><#if (list.proType=="P_Dir")>
										${list.bpPersionDirPro.yearInterestRate}%
									</#if><#if (list.proType=="P_Or")>
										${list.bpPersionOrPro.yearInterestRate}%
									</#if></span></li>
                    <li><span class="black middle">融资金额：&nbsp;&nbsp;</span><span class="black middle">${list.bidMoney?string(',###.00')}元</span></li>
                    <li><span class="black middle">融资期限：&nbsp;&nbsp;</span><span class="black middle"><#if (list.proType=="B_Dir")>
										${list.bpBusinessDirPro.loanLife}个月
									</#if><#if (list.proType=="B_Or")>
										${list.bpBusinessOrPro.loanLife}个月
									</#if><#if (list.proType=="P_Dir")>
										${list.bpPersionDirPro.loanLife}个月
									</#if><#if (list.proType=="P_Or")>
										${list.bpPersionOrPro.loanLife}个月
									</#if></span></li>
                </ul>
            </div>
            <div style="float:left; width:200px; height:80px; margin:0px 10px 0px 20px;">
            	<ul>
                	<li><span class="black middle">还款方式：&nbsp;&nbsp;</span><span class="black middle">
                	
										<#if list.payIntersetWay==1>等额本息</#if>
										<#if list.payIntersetWay==2>等额本金</#if>
										<#if list.payIntersetWay==3>等本等息</#if>
										<#if list.payIntersetWay==4>按日计息到期还本</#if>
										<#if list.payIntersetWay==5>期末一次性支付本息</#if>
									</span></li>
                    <li><span class="black middle">信用等级：&nbsp;&nbsp;</span><span style="color:#F90; font-size:20px;">${list.creditLevel}</span></li>
                    <li><span class="black middle">招标类型：&nbsp;&nbsp;</span><span class="black middle">${list.plBiddingType.name}</span></li>
                </ul>
            </div>
            <div style="float:left; width:250px; height:80px; margin:0px 10px 0px 20px;">
            	<ul>
                	<li><span class="black middle">投标进度：&nbsp;&nbsp;</span><span class="progressBar" id="pb_${list.bidId}">${list.progress!}%</span></li>
                    <li><span class="black middle">剩余时间：&nbsp;&nbsp;</span><span class="black middle">2天4小时</span></li>
                    <li><span class="black middle"></span></li>
                </ul>
            </div>
            <div style="float:right;margin:20px 60px 0px 20px">
            	<a href="${base}${list.htmlPath}" target="_self"><span class="buttonblue" style="font-size:16px; padding:6px 18px;">查看详情</span></a>
            </div>
        </div>
        <!-- 项目结束 -->
							
			</#if>			
			 <#if (list_index + 1) % 2 == 0 > 
			 <!-- 项目开始 -->
        <div class="content2">
        	<div class="title">
        		<div style="height:30px; float:left; margin:12px 0px 0px 50px;"><img src="${base}/theme/${systemConfig.theme}/images/bid_<#if (list.proType=="B_Dir"||list.proType=="B_Or")>company</#if><#if (list.proType=="P_Dir"||list.proType=="P_Or")>person</#if>.png" width="24" height="21" /></div>
                <div style="height:30px; float:left; margin:12px 0px 0px 10px;"><a href="${base}${list.htmlPath}" target="_self"><span class="blue big">${list.bidProName}</span></a></div>
                <div style="width:400px; height:20px; float:left; margin:13px 10px 0px 30px;"><span class="gray normal">[${list.proKeepType}]</span>&nbsp;&nbsp;<span class="gray normal">项目编号：${list.bidProNumber}</span></div>
            </div>
      		<div style="width:950px; height:0px; border-bottom:1px #e0e0e0 dashed; margin:15px auto 10px auto;" title="虚线"></div>
            <div style="float:left; width:200px; height:80px; margin:0px 10px 0px 40px;">
            	<ul>
                	<li><span class="black middle">年化利率：&nbsp;&nbsp;</span><span class="orange middle"><#if (list.proType=="B_Dir")>
										${list.bpBusinessDirPro.yearInterestRate}%
									</#if><#if (list.proType=="B_Or")>
										${list.bpBusinessOrPro.yearInterestRate}%
									</#if><#if (list.proType=="P_Dir")>
										${list.bpPersionDirPro.yearInterestRate}%
									</#if><#if (list.proType=="P_Or")>
										${list.bpPersionOrPro.yearInterestRate}%
									</#if></span></li>
                    <li><span class="black middle">融资金额：&nbsp;&nbsp;</span><span class="black middle">${list.bidMoney?string(',###.00')}元</span></li>
                    <li><span class="black middle">融资期限：&nbsp;&nbsp;</span><span class="black middle"><#if (list.proType=="B_Dir")>
										${list.bpBusinessDirPro.loanLife}个月
									</#if><#if (list.proType=="B_Or")>
										${list.bpBusinessOrPro.loanLife}个月
									</#if><#if (list.proType=="P_Dir")>
										${list.bpPersionDirPro.loanLife}个月
									</#if><#if (list.proType=="P_Or")>
										${list.bpPersionOrPro.loanLife}个月
									</#if></span></li>
                </ul>
            </div>
            <div style="float:left; width:200px; height:80px; margin:0px 10px 0px 20px;">
            	<ul>
                	<li><span class="black middle">还款方式：&nbsp;&nbsp;</span><span class="black middle">
                	                    <#if list.payIntersetWay==1>等额本息</#if>
										<#if list.payIntersetWay==2>等额本金</#if>
										<#if list.payIntersetWay==3>等本等息</#if>
										<#if list.payIntersetWay==4>按日计息到期还本</#if>
										<#if list.payIntersetWay==5>期末一次性支付本息</#if>
										</span></li>
                    <li><span class="black middle">信用等级：&nbsp;&nbsp;</span><span style="color:#F90; font-size:20px;">${list.creditLevel}</span></li>
                    <li><span class="black middle">招标类型：&nbsp;&nbsp;</span><span class="black middle">${list.plBiddingType.name}</span></li>
                </ul>
            </div>
            <div style="float:left; width:250px; height:80px; margin:0px 10px 0px 20px;">
            	<ul>
                	<li><span class="black middle">投标进度：&nbsp;&nbsp;</span><span class="progressBar" id="pb_${list.bidId}">${list.progress}%</span></li>
                    <li><span class="black middle">剩余时间：&nbsp;&nbsp;</span><span class="black middle">2天4小时</span></li>
                    <li><span class="black middle"></span></li>
                </ul>
            </div>
            <div style="float:right;margin:20px 60px 0px 20px">
            	
            </div>        	
        </div>
        <!-- 项目结束 -->
						
             </#if>   			
        </#list>
                		
        
        <#import "/WEB-INF/template/common/pager.ftl" as p>
         			<#assign parameterMap = {"Q_plBiddingType.biddingTypeId_L_EQ":(plBidPlanFilter.bidType)! ,"Q_payIntersetWay_S_EQ":(plBidPlanFilter.payway)!,"payTime":(plBidPlanFilter.payTime)!,"bidMoney":(plBidPlanFilter.bidMoney)!} />
         			<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/listPlBidPlan.do" parameterMap = parameterMap />
    </div>
  </div>
  </div>
  </form> 
 <!-- partner-pic --><!-- partner-text --><!-- sitemap --><!-- copyright --><!-- permit -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</div>
	</body>
</html>