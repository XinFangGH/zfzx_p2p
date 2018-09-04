<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 我要出借</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript">var m1="我要出借",m2="",m3="";</script>
<script type="text/javascript">
	$(function(){
		$('a[rel*=leanModal]').leanModal({ top : 200 });		
	});
	$(document).ready(
		//进度条触发
		function() {
	    <#list pager.list as list0 >
            $('#plan${list0.mmplanId}').css("background-position","${(list0.progress)*-54}px 0");
        </#list>
			var $bidForm = $("#queryForm");
		    var $attribute = $("#filter li a.attribute");
		    var $pageNumber=$("#pageNumber");
		    var $pageSize=$("#pageSize");
		    var $filter = $("#filter .filter-content");
		    
		    // 清空cookie
		    if($("#typeId").val()==""){//计划类型
		     	$.cookie("businessFrom",null);
		    }
		    // 清空cookie
		    if($("#investlimitInput").val()==""){//计划类型
		     	$.cookie("investlimit",null);
		    }
		    // 清空cookie
		    if($("#yearRateInput").val()==""){//计划类型
		     	$.cookie("yearRate",null);
		    }
		    // 清空cookie
		    if($("#sumMoneyInput").val()==""){//计划类型
		     	$.cookie("sumMoney",null);
		    }
		    
		    if($("#yearRateInput").val()!=""){
		    	$('.filter-condition.more').toggle('slideDown');
		     	$("#filterother").addClass('up');
	         	$("#filterother").attr('data-status', 'open');
		    }else if($("#sumMoneyInput").val() !=""){
		    	$('.filter-condition.more').toggle('slideDown');
		     	$("#filterother").addClass('up');
	         	$("#filterother").attr('data-status', 'open');
		    }
		    
		    //通过cookie 设置样式
		  	$filter.each(function(){
				var $this = $(this);
				var $curr=$this.find("li a");
				$curr.each(function(){
					var $thisCurr=$(this);
					if ($thisCurr.attr('lang')==  $.cookie($this.attr('id'))) {
						$this.find('li a').removeClass("selected");
						$thisCurr.addClass("selected");
					}
				});
			});
		    $attribute.click(function() {
				var $this = $(this);
				if ($this.hasClass("selected")) {
					$this.closest(".filter-condition").find("input").prop("disabled", true);
				} else {
					$this.closest(".filter-condition").find("input").prop("disabled", false).val($this.attr('lang'));
				    $.cookie($this.closest(".filter-content").attr('id'),$this.attr('lang'));
				    var $curr0=$this.closest(".filter-condition").find("li a");
				    if($curr0.html()=="全部"){
				    	$curr0.attr("class","attribute");
				    }
				}
				$pageNumber.val(1);
				$bidForm.submit();
				return false;
			});
	});
	
	$(function() {
		// 合作伙伴图标水平循环滚动
		scrollPartner();
		//图标下拉框  提示框
		var content = $(".txts1").html();	
		$('.newhand .one').pt({
			position: 'b',
			width:160,
			content: content
		});
		//图标下拉框  提示框
		var content = $(".txts2").html();	
		$('.newhand .two').pt({
			position: 'b',
			width:160,
			content: content
		});
		//图标下拉框  提示框
		var content = $(".txts3").html();	
		$('.newhand .three').pt({
			position: 'b',
			width:160,
			content: content
		});	  
	});
	
	function buttonClick(){
		if($("#businessFrom").val()==""){
	     	$.cookie("businessFrom",null);
	    }
	}
</script>
<script type="text/javascript">
	$(function(){
		 $('.filter-other').click(function() {
            $('.filter-condition.more').toggle('slideDown');
            if($(this).attr('data-status') === 'open') {
                $(this).removeClass('up');
                $(this).attr('data-status', 'close');
            } else {
                $(this).addClass('up');
                $(this).attr('data-status', 'open');
            }
        });
	})
</script>
</head>
<body>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div style="background:#EFF3F4; width:100%;">
	 <div class="main">
	 	<div class="per-box">
	 		<div class="per">
				<!--判断背景色-->
				<a href="${base}/creditFlow/financingAgency/allPlmanagemoneyPlBidSale.do" onclick="buttonClick();" class="<#if planType=="plmanage">bg1<#else>bg2</#if>">D债权转让</a>
		    	<a href="${base}/creditFlow/financingAgency/listPlBidPlan.do" onclick="buttonClick();" class="<#if planType=="Dir">bg1<#else>bg2</#if>">散标投资</a>
		    	<a href="${base}/creditFlow/financingAgency/getUPlanPlManageMoneyPlan.do" onclick="buttonClick();" class="<#if planType=="UPlan">bg1<#else>bg2</#if>">U债权转让</a>
		    	<a href="${base}/creditFlow/financingAgency/alltransferinglistPlBidSale.do" onclick="buttonClick();" class="<#if planType=="ortransfer">bg1<#else>bg2</#if>">债权交易</a>
			</div>
			<span id="spanText" style="display:none"></span>
			<form id="queryForm" name="queryForm" method="get" action="${base}/creditFlow/financingAgency/getUPlanPlManageMoneyPlan.do">
				<input type="hidden" id="pageNumber" name="pager.pageNumber" value="${pager.pageNumber}"/>
				<input type="hidden" id="pageSize" name="pager.pageSize" value="${pager.pageSize}"/>
				<div id="filter" class="bidsearch">
					<div class="filter-condition">
					      <label>计划类型：<input type="hidden" id="typeId" name="typeId" value="${typeId!}" /></label>
					      <ul class="filter-content"  id="businessFrom"> 
							  <li><a href="javascript:void(0)" lang="" id="attra" class="selected attribute">不限</a></li> 
							 <#if listMoneyType??>
					      	<#list listMoneyType as list>
					      		<li><a href="javascript:void(0)"   lang="${list.manageMoneyTypeId}" class="attribute">${list.name}</a></li>
					      	</#list>
					      </#if>						     
						  </ul>
				    </div>
				    
				   	<div class="filter-condition">
		                <label>计划期限：<input type="hidden" id="investlimitInput" name="investlimit" value="${investlimit!}"/></label>
		                <ul class="filter-content" id="investlimit">
		                	<li><a href="javascript:void(0)" lang="" class="selected attribute">不限</a></li> 
					        <li><a href="javascript:void(0);" class="attribute" lang="0-3">3个月以下</a></li>  
					        <li><a href="javascript:void(0);" class="attribute" lang="4-6">4-6个月</a></li>  
					        <li><a href="javascript:void(0);" class="attribute" lang="7-11">7-11个月</a></li> 
					        <li><a href="javascript:void(0);" class="attribute" lang="12-1000">12个月以上</a></li> 
		                </ul>      
                	</div>
                	
				    <div class="filter-condition more">
		                <label>年化利率：<input type="hidden" id="yearRateInput" name="yearRate" value="${yearRate!}"/></label>
		                <ul class="filter-content" id="yearRate">
		                	<li><a href="javascript:void(0)" lang="" class="selected attribute">不限</a></li> 
					        <li><a href="javascript:void(0);" class="attribute" lang="0-9">9%以下</a></li>  
					        <li><a href="javascript:void(0);" class="attribute" lang="9-10">9-10%</a></li>  
					        <li><a href="javascript:void(0);" class="attribute" lang="11-11">10-11%</a></li> 
					        <li><a href="javascript:void(0);" class="attribute" lang="11-12">11-12%</a></li> 
					        <li><a href="javascript:void(0);" class="attribute" lang="12-100">12%以上</a></li> 
		                </ul>      
                    </div>
                    
                    <div class="filter-condition more">
					      <label>金额范围： <input type="hidden" id="sumMoneyInput" name="sumMoney" value="${sumMoney!}"/></label>
					      <ul class="filter-content" id="sumMoney"> 
					          <li><a href="javascript:void(0);" lang="" class="selected attribute">全部</a></li>
					          <li><a href="javascript:void(0);" class="attribute"  lang="1-5000">5千元以下</a></li>
					          <li><a href="javascript:void(0);" class="attribute"  lang="5000-10000">5000-1万元</a></li>
					          <li><a href="javascript:void(0);" class="attribute"  lang="10000-50000">1万元-5万元</a></li> 
					          <li><a href="javascript:void(0);" class="attribute"  lang="50000-100000">5万元-10万元</a></li> 
					          <li><a href="javascript:void(0);" class="attribute"  lang="100000-500000">10万元-50万元</a></li>
					          <li><a href="javascript:void(0);" class="attribute"  lang="500000-1000000">50万元-100万元</a></li>
					          <li><span class="hidden-city"> <a href="javascript:void(0);" class="attribute"  lang="1000000-100000000">100万元以上</a></span></li>  
					       </ul>
			    	</div>
                    
				    <div class="filter-other" data-status="close">
				         	  更多筛选条件
				    	<div class="sign"></div>
				    </div>					
				</div>
			</form>
	 	</div>
<div class="tenderlist1"style="padding-bottom:30px;">
  <!--U计划开始-->
<div class="tenderlist">
	<div class="title">
         U计划
    </div>
 		<!--U计划循环列表--> 
 		<#list pager.list as list>
        <div class="investment">
        	<h3> 
	        	 <em><img src="${base}/theme/${systemConfig.theme}/images/fu.png" width="19"/></em>
	        	 <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.mmplanId}" title="${list.mmName}"><#if list.mmName?length gt 20>${list.mmName?substring(0,20)}<#else>${list.mmName}</#if></a>
	        	<i style="margin-top:6px;">
				   <#if list.novice==1><font class="mm_font-one">新</font></#if>
	               <#if list.addRate!=0&&list.addRate!=""><font class="mm_font-two">息</font></#if>
	               <#if list.coupon==1><font class="mm_font-three">券</font></#if>
	            </i>
        	</h3>
        	<div class="investment-list">
        		<dl class="investment-dl" style="padding: 10px 0 10px 55px;">       			
        			<#if list.logoUrl==0>
        			<dt><a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.mmplanId}"><img src="${base}/theme/${systemConfig.theme}/images/dl-list-pic.png" width="140" height="100"></a></dt>
        			<#else>
        			<dt><a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.mmplanId}"><img src="${base}/${list.logoUrl}" width="140" height="100"></a></dt>
        			</#if>
        			<dd>
        				<h5><a href="#"><#if list.manageMoneyTypeName?length gt 10>${list.manageMoneyTypeName?substring(0,10)}<#else>${list.manageMoneyTypeName}</#if></a></h5>
        				<p>借款金额：<em>${list.sumMoney?string(',##0')}</em>元</p>
        				<p>计划期限： <span>${list.investlimit}个月</span></p>
        				<p>锁定期： <span>${list.lockingLimit}个月</span></p>
        			</dd>
        		</dl>
        		<div class="investment-div">
        			<p>预期年化收益率：</p>
 					<p>
 					<em >
 					${list.yeaRate}
 					</em>		
 					<i>%</i>
 					</p>
        		</div>
        		<div class="investment-right">
        			 <span class="wad_spec5">
	        			 <strong id="plan${list.mmplanId}">
	        			 	<em>${list.progress}</em>%
        			     </strong>
        			 </span>
        			 <span class="wad_spec6">
		               <#if list.state=='1'>
		                 <#if list.startTime?date("yyyy-MM-dd HH:mm:ss") lt list.nowDate?date("yyyy-MM-dd HH:mm:ss") >
		    				<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.mmplanId}&keystr=UPlan" style="color:#fff;">购买中</a>
		                 <#else>
		                                                   预售中
		                 </#if>
		               <#elseif list.state=='2'>已售罄
		               <#elseif list.state=='7'>回款中
		               <#elseif list.state=='10'>已完成
		               <#elseif list.state=='-1'||list.state=='-2'>
		              	已关闭
		               </#if>
        			 </span>
        		</div>
        	</div>
        </div> 
        </#list>
        <#import "/WEB-INF/template/common/pager.ftl" as p>
        <#assign parameterMap = {"typeId":(typeId)!,"investlimit":(investlimit)!,"yearRate":(yearRate)!,"sumMoney":(sumMoney)!} />
     	<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/getUPlanPlManageMoneyPlan.do" parameterMap = parameterMap />      	
</div>
   <!--end:U计划结束-->
	 </div>
</div>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">	
</body>
</html>