<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 我要出借</title>
    <meta name="description" content="${systemConfig.metaTitle} - 我要出借,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 我要出借,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
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
	 		<#if (list0.progress)??>	
            	$('#plan${list0.mmplanId}').css("background-position","${(list0.progress)*-54}px 0");
            <#else>
            </#if>
        </#list>
	    
		var $bidForm = $("#queryForm");
	    var $attribute = $("#filter li a.attribute");
	    var $pageNumber=$("#pageNumber");
	    var $pageSize=$("#pageSize");
	    var $filter = $("#filter .filter-content");
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
	    
        // 清空cookie
	    if($("#bidtype").val()==""){//计划类型
	     	$.cookie("businessFrom",null);
	    }
	    
	    // 清空cookie
	    if($("#investlimitInput").val()==""){//计划期限
	     	$.cookie("investlimit",null);
	    }
	    
	    // 清空cookie
	    if($("#yearRateInput").val()==""){//利率
	     	$.cookie("yearRate",null);
	    }
	    
	    // 清空cookie
	    if($("#sumMoneyInput").val()==""){//金额范围
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
	    $filter.each(function() {
			var $this = $(this);
			var $curr=$this.find("li a");
			$curr.each(function(){
				var $thisCurr=$(this);
				if ($thisCurr.attr('lang')==$.cookie($this.attr('id'))) {
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
<body >
<input value="${(bpCustMember.grade)??}" type="hidden" id="grade">
<input value="${(bpCustMember.id)??}" type="hidden" id="memberId">
<input type="hidden" id="bidType" value="claim">
<!--整体布局
<div> 
  <!-- topbar -->
  <!-- header --><!-- navbar -->
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div style="background:#EFF3F4; width:100%;">
  <div class="main"  >
<!-- 头部结束 -->
<!--start: Container -->
    <div class="per-box">	
      <div class="per">
		<!--判断背景色-->
		<a href="${base}/creditFlow/financingAgency/allPlmanagemoneyPlBidSale.do" class="<#if planType=="plmanage">bg1<#else>bg2</#if>">D债权转让</a>
		<span class="setp">|</span>
    	<a href="${base}/creditFlow/financingAgency/listPlBidPlan.do" class="<#if planType=="Dir">bg1<#else>bg2</#if>">散标投资</a>
    	<span class="setp">|</span>
    	<a href="${base}/creditFlow/financingAgency/getUPlanPlManageMoneyPlan.do" class="<#if planType=="UPlan">bg1<#else>bg2</#if>">U债权转让</a>
    	<span class="setp">|</span>
    	<#--<a href="${base}/creditFlow/financingAgency/alltransferinglistPlBidSale.do" class="<#if planType=="ortransfer">bg1<#else>bg2</#if>">债权交易</a>-->
   	  </div>
   	  <div id="filter" class="bidsearch">
		 <#if planType=="ortransfer">	
		 	<form id="queryForm" name="queryForm" method="get" action="${base}/creditFlow/financingAgency/alltransferinglistPlBidSale.do">					
				<input type="hidden" id="pageNumber" name="pager.pageNumber" value="${pager.pageNumber}"/>
				<input type="hidden" id="pageSize" name="pager.pageSize" value="${pager.pageSize}"/>
				    <div class="filter-condition">
					      <label>剩余期限：<input type="hidden" id="businessTypeInput" name="payTime" value="${payTime}" /></label>
					      <ul class="filter-content"  id="proKeepType"> 
							  <li><a href="javascript:void(0)" lang="" class="selected attribute">不限</a></li> 
						      <li><a href="javascript:void(0);" class="attribute" lang="0-3">3个月下</a><li> 
						      <li><a href="javascript:void(0);" class="attribute" lang="3-6">3-6个月</a></li> 
						      <li><a href="javascript:void(0);" class="attribute" lang="9-15">9-15个月</a></li> 
						      <li><a href="javascript:void(0);" class="attribute" lang="18-24">18-24个月</a></li>
						      <li><a href="javascript:void(0);" class="attribute" lang="24-1000">24个月以上</a></li> 
						  </ul>
				    </div>
				    <div class="filter-condition">
					      <label>债权金额： <input type="hidden" id="payTimeInput" name="plKeepCreditlevel_name" value="${plKeepCreditlevel_name}"/></label>
					      <ul class="filter-content"   id="payTime"> 
							  <li><a href="javascript:void(0);" lang="" class="selected attribute">不限</a></li>  
						      <li><a href="javascript:void(0);" class="attribute" lang="0-500">500元以下</a> </li>
						      <li><a href="javascript:void(0);" class="attribute" lang="500-1000">500-1000元</a> </li>
						      <li><a href="javascript:void(0);" class="attribute" lang="1000-5000">1000-5000元</a></li>
						      <li><a href="javascript:void(0);" class="attribute" lang="5000-100000000">5000元以上</a></li> 
						  </ul>
				    </div>
 		</form> 
 	<#else>
  		<form id="queryForm" name="queryForm" method="get" action="${base}/creditFlow/financingAgency/allPlmanagemoneyPlBidSale.do">					
			<input type="hidden" id="pageNumber" name="pager.pageNumber" value="${pager.pageNumber}"/>
			<input type="hidden" id="pageSize" name="pager.pageSize" value="${pager.pageSize}"/>			
			    
			    <div class="filter-condition">
	                <label>计划类型：<input type="hidden" id="bidtype" name="plBidPlanFilter.keepProtypeName" value="${my_plan!}"/></label>
	                <ul class="filter-content" id="businessFrom">
	                  <li><a href="javascript:void(0)"  lang="" class="selected attribute">不限</a></li>
	                  <#if plTypeList!=null><!-- 查询条件循环typeList-->
				      	 <#list plTypeList as typeList>
				      		<#if typeList.state=0>
				      			<li><a href="javascript:void(0)"  lang="${typeList.manageMoneyTypeId}" class="attribute" >${typeList.name}</a></li>
				      		</#if>
	      			    </#list>
				      <#else>
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
 	</form> 
 </#if>
 	<div class="filter-other" data-status="close">
         	  更多筛选条件
    	<div class="sign"></div>
    </div>
 </div>
</div>

<!--列表-->
<div class="tenderlist">
  <#if planType=="ortransfer">
   <div class="bidplan1">
                <table class="tab_css_0">
                    <tr>
                        <th align="center">债权名称</th>
                        <th align="center">出让本金</th>
                        <th align="center">折让金</th>
                        <th align="center">折让率</th>
                        <th align="center">挂牌时间</th>
                        <th align="center">操作</th>
                    </tr>
                    <#list pager.list as list>
                    
                    <tr>
                        <td align="left">
	                       <em><img src="${base}/theme/${systemConfig.theme}/images/<#if (list.proKeepType)??><#if list.proKeepType=="信用审核标">xin.png<#elseif list.proKeepType=="实地核查标">shi.png<#elseif  list.proKeepType="机构担保标">bao.png<#elseif list.proKeepType="福利标">fu.png<#else>xin.png</#if><#else>xin.png</#if>" width="19"/></em>
	                        <a href="${base}/creditFlow/financingAgency/saleInfoPlBidPlan.do?salseId=${list.id}" title="${list.bidProName}(到期日:${list.intentDate};年化利率:${list.yearAccrualRate}%)">
	                       <#if  list.bidProName?length gt 10> ${list.bidProName?substring(0,10)}<#else> ${list.bidProName}</#if>  </a>
                        </td>
                        <td align="center" ><#if list.saleMoney==0>0.00<#else><#if list.saleMoney lt 1000>${list.saleMoney}<#else>${list.saleMoney?string(',###.00')}</#if></#if>元</td>
                        <td align="center"><#if list.changeMoney=0> ${list.changeMoney}<#else> <#if list.changeMoneyType=0> +<#else>-</#if><#if list.changeMoney==0>0.00<#else><#if list.changeMoney lt 1000>${list.changeMoney}<#else>${list.changeMoney?string(',###.00')}</#if></#if></#if>元</td>
                        <td align="center"><#if list.changeMoneyRate=0> ${list.changeMoneyRate} <#else> <#if list.changeMoneyType=0> +<#else>-</#if>${list.changeMoneyRate}</#if>‰</td>
                        <td align="center">${list.saleStartTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                       <td align="center">
	                        <#if list.saleStatus==3>
	                          <a href="javascript:void(0);" class="wad_open btn1">正在交易</a>
	                        <#else>
	                          <a href="${base}/creditFlow/financingAgency/saleInfoPlBidPlan.do?salseId=${list.id}" class="wad_open">购买中</a> 
	                        </#if>  
                       </td>
                     </#list>
                    </tr>
                </table>
                <#import "/WEB-INF/template/common/pager.ftl" as p>
                <#assign parameterMap = {"payTime":(payTime)!,"plKeepCreditlevel_name":(plKeepCreditlevel_name)!} />
         		<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/alltransferinglistPlBidSale.do" parameterMap = parameterMap/>
   </div>   
   <#else>
	<!--<div class="title">
           D债权转让
    </div>-->
 		<!--start理财计划循环列表--> 
 		<#list pager.list as list>
        <div class="investment">
        	<h3> 
	        	<em>
	        	 	<#if (list.proKeepType)??>
	        	 		<#if list.proKeepType=="信用审核标">信用
		        	 		<#elseif list.proKeepType=="实地核查标">实地
		        	 		<#elseif  list.proKeepType="机构担保标">担保
		        	 		<#elseif list.proKeepType="福利标">福利
		        	 		<#else>信用
	        	 		</#if>
	        	 	<#else>信用
	        	 	</#if>
	        	 </em>
	        	 <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.mmplanId}" title="${list.mmName}"><#if list.mmName?length gt 20>${list.mmName?substring(0,20)}<#else>${list.mmName}</#if></a>
	        	<i>
				   <#if list.novice==1><font class="mm_font-one">新</font></#if>
	               <#if list.addRate!=0&&list.addRate!=""><font class="mm_font-two">息</font></#if>
	               <#if list.coupon==1><font class="mm_font-three">券</font></#if>
	            </i>
        	</h3>
        	<div class="investment-list">
        		<div class="fl w1015">
	        		<div class="investment-dl" >       			
	        			<#--<#if list.logoUrl==0>
	        			<dt><a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.mmplanId}"><img src="${base}/theme/${systemConfig.theme}/images/dl-list-pic.png" width="140" height="100"></a></dt>
	        			<#else>
	        			<dt><a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.mmplanId}"><img src="${base}/${list.logoUrl}" width="140" height="100"></a></dt>
	        			</#if>-->
	        			<div class="f16" style="padding-bottom:5px;">年化收益： 
		        			<em>${list.yeaRate}%</em>
	        			</div>
	        			<div class="f16">还款方式：到期还本付息</div>
	        		</div>
	        		<div class="investment-dl">
	        			<div class="f16"><#if list.proType=='P_Or' || list.proType=='B_Or'>剩余期限：
	        			<#else>
	        			投资期限：${list.investlimit}个月</#if></div>
	        			<div class="f16">锁定期：${list.lockingLimit}个月</div>
	        		</div>
	        		<div class="investment-dl">
	        			<div class="f16">计划金额：${list.sumMoney?string(',##0')}元</div>
	        			<div class="f16">
	        				<span>投资进度：</span>
		        			<span class="progress project-progress w160">
							    <span class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="11.00" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></span>
							</span>
							<span class="progress-num" style="color:#262626;">100%</span>
	        			</div>
        			</div>
	        	</div>
	        	<div class="bid_list_btn fr">
		               <#if list.state=='1'>
		                 <#if list.startTime?date("yyyy-MM-dd HH:mm:ss") lt list.nowDate?date("yyyy-MM-dd HH:mm:ss") >
		    				<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.mmplanId}" class="buttonorange orange-btn">购买中</a>
		                 <#else>
		                  <span class="gray-btn buttongray"> 预售中</span>
		                 </#if>
		               <#elseif list.state=='2'> <span class="gray-btn buttongray">已售罄</span>
		               <#elseif list.state=='7'> <span class="gray-btn buttongray">回款中</span>
		               <#elseif list.state=='10'> <span class="gray-btn buttongray">已完成</span>
		               <#elseif list.state=='-1'||list.state=='-2'>
		              	 <span class="gray-btn buttongray">已关闭</span>
		               </#if>
        		</div>
        		
        	</div>
        </div> 
        </#list>
        <#import "/WEB-INF/template/common/pager.ftl" as p>
        <#assign parameterMap = {"plBidPlanFilter.keepProtypeName":(my_plan)!,"investlimit":(investlimit)!,"yearRate":(yearRate)!,"sumMoney":(sumMoney)!} />
        <@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/allPlmanagemoneyPlBidSale.do"  parameterMap = parameterMap/>            	
</div>
  <!--end理财计划列表结束-->
   </#if>
 
 </div>      
	 
</div>

<#--进度条
<div class="investment-right">
        			 <span class="wad_spec5">
	        			 <strong id="plan${list.mmplanId}">
	        			 	<em>${list.progress}</em>%
        			     </strong>
        			 </span>
        			 <span class="wad_spec6">
		               <#if list.state=='1'>
		                 <#if list.startTime?date("yyyy-MM-dd HH:mm:ss") lt list.nowDate?date("yyyy-MM-dd HH:mm:ss") >
		    				<a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId=${list.mmplanId}" style="color:#fff;">购买中</a>
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
-->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</body>
</html>