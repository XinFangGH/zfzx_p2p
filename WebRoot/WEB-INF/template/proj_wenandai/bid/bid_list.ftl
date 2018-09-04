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
        <#--<#list pager.list as list0 >-->
        <#--$('#plan${list0.bidId}').css("background-position","${(list0.progress)*-54}px 0");-->
        <#--</#list>-->
        <#---->
        <#--<#list listPlan as list0 >-->
        	<#--$('#plan${list0.mmplanId}').css("background-position","${(list0.progress)*-54}px 0");-->
        <#--</#list>-->
        <#---->
        <#---->
		  <#--<#list pager.list as list0 >-->
			<#--$("#pb${list0.bidId}").progressBar();-->
			<#--</#list>-->
		var $bidForm = $("#queryForm");
	    var $attribute = $("#filter li a.attribute");
	    var $pageNumber=$("#pageNumber");
	    var $pageSize=$("#pageSize");
	    var $filter = $("#filter .filter-content");
	    // 清空cookie

	    if($("#payTimeInput").val()==""){
	     $.cookie("payTime",null);
	    }
	    
	    if($("#bidMoneyInput").val()==""){
	     $.cookie("bidMoney",null);
	    }
	    
	    if($("#bidtype").val()==""){
	     $.cookie("businessFrom",null);
	    }
	    
	    if($("#businessTypeInput").val()==""){
	     $.cookie("proKeepType",null);
	    }
	    
	     if($("#yearInterestRateInput").val()==""){
	     $.cookie("yearInterestRate",null);
	    }
	    
	    //控制收起的查询条件选中后页面刷新打开


	    if($("#bidtype").val() !=""){
	    	$('.filter-condition.more').toggle('slideDown');
	     	$("#filterother").addClass('up');
         	$("#filterother").attr('data-status', 'open');
	    }else if($("#payTimeInput").val()!=""){
	    	$('.filter-condition.more').toggle('slideDown');
	     	$("#filterother").addClass('up');
         	$("#filterother").attr('data-status', 'open');
	    }else if($("#yearInterestRateInput").val()!=""){
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
       }
	);
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
<body>
<!--整体布局
<div> 
  <!-- topbar -->
  <!-- header --><!-- navbar -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div class="main" style="margin-bottom:30px;"  >
	<!-- 头部结束 -->
	<!--start: Container -->
	<div class="per-box my-per-box">
		<div class="per">
			<!--判断背景色-->
			<!--<a href="${base}/creditFlow/financingAgency/allPlmanagemoneyPlBidSale.do" class="<#if planType=="plmanage">bg1<#else>bg2</#if>">D债权转让</a>
			<span class="setp">|</span>-->
	    	<a href="${base}/creditFlow/financingAgency/listPlBidPlan.do" class="<#if planType=="Dir">bg1<#else>bg2</#if>">散标投资</a>
	    	<#--<span class="setp">|</span>-->
	    	<!--<a href="${base}/creditFlow/financingAgency/getUPlanPlManageMoneyPlan.do" class="<#if planType=="UPlan">bg1<#else>bg2</#if>">U债权转让</a>
	    	<span class="setp">|</span>-->
	    	<a href="${base}/creditFlow/financingAgency/alltransferinglistPlBidSale.do" class="<#if planType=="ortransfer">bg1<#else>bg2</#if>">债权交易</a>
	    	<#--<span class="setp">|</span>-->
	    	<#--<a href="${base}/creditFlow/financingAgency/listVipPlBidPlan.do" class="<#if planType=="Dir">bg2<#else>bg1</#if>">VIP专享</a>-->
	    	<#--<span class="setp">|</span>-->
	    	<#--<a href="${base}/creditFlow/financingAgency/alltransferinglistVipPlBidSale.do" class="<#if planType=="ortransfer">bg1<#else>bg2</#if>">VIP债权交易</a>-->
	    	<#--<a href="${base}/creditFlow/financingAgency/listPlBidPlan.do?Q_proType_S_LK=Or" class="<#if planType=="Or">bg1<#else>bg2</#if>">债权转让</a>-->

            <div id="search_box" class="search_box">
                <form id="search_form" method="post" action="${base}/creditFlow/financingAgency/newPlanListPlBidPlan.do"  >
                    <input type="text" id="swap_value"  name="bidProName" autocomplete="off" value="${bidProName!}" onkeydown="KeyDown()" placeholder="搜索" />
                    <input type="button"  id="goSearch" onclick = "checkUser();"/>
                </form>
            </div>
		</div>
		<form id="queryForm" name="queryForm" method="get" action="${base}/creditFlow/financingAgency/newPlanListPlBidPlan.do">

			<input type="hidden" id="pageNumber" name="pager.pageNumber" value="${pager.pageNumber}"/>
			<input type="hidden" id="pageSize" name="pager.pageSize" value="${pager.pageSize}"/>
			<div id="filter" class="bidsearch">


				 <div class="filter-condition">
	                <label>借款期限：<input type="hidden" id="businessTypeInput" name="payTime" value="${plBidPlanFilter.payTime!}" /></label>
	                <ul class="filter-content" id="proKeepType">
	                	<li><a href="javascript:void(0)" lang="" class="selected attribute">不限</a></li> 
				        <li><a href="javascript:void(0);" class="attribute" lang="0-1">1个月以内</a></li>
				        <li><a href="javascript:void(0);" class="attribute" lang="1-3">1-3个月</a></li>  
				        <li><a href="javascript:void(0);" class="attribute" lang="4-6">4-6个月</a></li>  
				        <li><a href="javascript:void(0);" class="attribute" lang="7-11">7-11个月</a></li> 
				        <li><a href="javascript:void(0);" class="attribute" lang="12-1000">12个月以上</a></li> 
	                </ul>
                </div>
                
                <div class="filter-condition">
			      <label>金额范围：<input type="hidden" id="bidMoneyInput" name="bidMoney" value="${plBidPlanFilter.bidMoney!}"/> </label>
			      <ul class="filter-content"  id="bidMoney"> 
				      <li><a href="javascript:void(0);" lang="" class="selected attribute">不限</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="1-5000">5千元以下</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="5000-10000">5千元-1万元</a></li>
				      <li><a href="javascript:void(0);" class="attribute"  lang="10000-50000">1-5万元</a></li>
				      <li><a href="javascript:void(0);" class="attribute"  lang="50000-100000">5-10万元</a></li>
				      <li><a href="javascript:void(0);" class="attribute"  lang="100000-500000">10-50万元</a></li>
				      <li><a href="javascript:void(0);" class="attribute"  lang="500000-1000000">50-100万元</a></li>
				     <li><span class="hidden-city"> <a href="javascript:void(0);" class="attribute"  lang="1000000-100000000">100万元以上</a></span></li> 
			      </ul>
			    </div>    
			    
			    
			     <div class="filter-condition  more">
			      <label>年收益率：<input type="hidden" id="yearInterestRateInput" name="yearInterestRate" value="${plBidPlanFilter.yearInterestRate!}"/></label>
			      <ul class="filter-content"  id="yearInterestRate"> 
				      <li><a href="javascript:void(0);" lang="" class="selected attribute">不限</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="0-5">5%以下</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="5-8">5%-8%</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="8-10">8%-10%</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="10-100">10%以上</a></li> 
			      </ul>
			    </div>    
			      
                
				<div class="filter-condition  more">
	                <label>标的类型：<input type="hidden" id="bidtype" name="keepProtypeName" value="${plBidPlanFilter.proKeepType!}" /></label>
	                <ul class="filter-content" id="businessFrom">
	                  <li><a href="javascript:void(0)"  lang="" class="selected attribute">不限</a></li>
				      <li><a href="javascript:void(0)"  lang="信用审核标" class="attribute">信用审核标</a></li>
				      <li><a href="javascript:void(0)"  lang="实地核查标" class="attribute">实地核查标</a></li>
				      <li><a href="javascript:void(0)"  lang="机构担保标" class="attribute">机构担保标</a></li>
				      <li><a href="javascript:void(0)"  lang="福利标" class="attribute">福利标</a></li>
	                </ul>
                </div>
               
                <div class="filter-condition  more">
	              <label>信用等级： <input type="hidden" id="payTimeInput" name="keepCreditlevelName" value="${plBidPlanFilter.keepCreditlevelName!}"/></label>
	                <ul class="filter-content" id="payTime">
	                	<li><a href="javascript:void(0);" lang="" class="selected attribute">不限</a></li>
                        <li><a href="javascript:void(0);" lang="AAA" class="attribute">AAA</a></li>
				      	<li><a href="javascript:void(0);" lang="AA" class="attribute">AA</a></li>
				      	<li><a href="javascript:void(0);" lang="A" class="attribute">A</a></li>
				     	<li><a href="javascript:void(0);" lang="B" class="attribute">B</a></li>
					    <li><a href="javascript:void(0);" lang="C" class="attribute">C</a></li>
					    <li><a href="javascript:void(0);" lang="D" class="attribute">D</a></li>
					    <li><a href="javascript:void(0);" lang="E" class="attribute">E</a></li>
					    <li><a href="javascript:void(0);" lang="HR" class="attribute">HR</a></li>
	                </ul>
                </div>

				<div class="clearfix">
                    <span class="icon">温馨提示：工作日固定发标时间在12:00、17:00，其余时间与周末随机发标。</span>
                    <div class="filter-other" data-status="close" id="filterother">
                        更多筛选条件
                        <span class="sign"></span>
                    </div>
				</div>

		</div>
 	</form> 
</div>
<#--<div class="worm-tips2">
   <span class="icon">温馨提示：工作日固定发标时间在12:00、17:00，其余时间与周末随机发标</span>
</div>-->
<div class="tenderlist my-tenderlist">
    <!--start体验标循环列表--> 
    <#if listPlan??>
       <#list listPlan as list>
          <div class="investment">
          	<b class="Novice-new"></b>
        	<h3>
	        	<a href="#" title="">新手体验-模拟投资</a>
	        	<em><img src="${base}/theme/${systemConfig.theme}/images/hot.jpg"></em>
        	</h3>
        	<div class="investment-list">
        		<div class="fl w1015">
	        		<div class="investment-dl">
	        			<div class="f16" style="padding-bottom:5px;">预期年化收益： 
		        			<em>
	        				${list.yeaRate}%
		        			</em>
	        			</div>
	        			<!--<div class="f16">还款方式：${list.theWayBack}</div>-->
	        		</div>
	        		<div class="investment-dl">
	        			<div class="f16">
	        			投资期限：${list.investlimit}天</div>
	        			<div class="f16 credit-rating">信用等级：
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
                        "><#if (list.keepCreditlevelName)??>${(list.keepCreditlevelName)!}<#else>A</#if></a>
	        			</div>
	        		</div>
	        		<div class="investment-dl">
	        			<div class="f16">计划金额：
	        				<#if (list.sumMoney >=10000) >${(list.sumMoney/10000)?string(",##0.00#")}万元<#else>${list.sumMoney?string(',##0')}元</#if>
						</div>
	        			<div class="f16">
	        				<span>投资进度：</span>
		        			<span class="progress project-progress w160" style="margin-top:9px;">
							    <span class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="11.00" aria-valuemin="0" aria-valuemax="100" style="width: ${list.progress}%"></span>
							</span>
							<span class="progress-num" style="color:#262626;"><#if (list.state == 2)>100%<#elseif (list.state == 7)>100% <#elseif (list.state == 10)>100%<#else>${list.progress}%</#if></span>
	        			</div>
        			</div>
        		</div>
        		<div class="bid_list_btn fr">
        	    	<#if list.state==2 || list.state==5|| list.state==6>
        				<span class="gray-btn buttongray">已满标</span>
                    <#elseif list.state==3>
                    	<span class="buttongray gray-btn">已流标</span>
                    <#elseif list.state==4>
                    	<span class="buttongray gray-btn">已过期</span>
                    <#elseif list.state==10>
                    	<span class="buttongray gray-btn">已还清</span>
                    <#elseif list.state==7>
                    	<span class="buttongray gray-btn">还款中</span>
                    <#else>
                    <a href="${base}/creditFlow/financingAgency/getExperienceDetailPlManageMoneyPlan.do?mmplanId=${list.mmplanId}" class="buttonorange orange-btn">立即出借</a>
                    </#if>
        		</div>
			</div>             	
		</div>
     </#list>   
  </#if> 
<!--end体验标循环列表--> 



 <!--start散标循环列表--> 
        <#if (pager.list)??>
        <#list pager.list?sort_by("bidId")?reverse  as list>
        <div class="investment">
        	<h3> 
	        	 <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" title="${list.bidProName}"><#if list.bidProName?length gt 30>${list.bidProName?substring(0,30)}<#else>${list.bidProName}</#if></a>
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
                <span class="myspan">
								<a class="
	                        <#if (list.keepCreditlevelName)??>
								<#if list.keepCreditlevelName=="AAA">aaa
		                        <#elseif list.keepCreditlevelName=="HR">hr
		                         <#elseif list.keepCreditlevelName=="AA">aa
		                        <#elseif list.keepCreditlevelName=="A">a
		                        <#elseif list.keepCreditlevelName=="B">b
		                        <#elseif list.keepCreditlevelName=="C">c
		                        <#elseif list.keepCreditlevelName=="D">d
		                        <#else>e
		                        </#if>
		                    <#else>
		                        a
	                        </#if>
                        ">
								<#if (list.keepCreditlevelName)??>${(list.keepCreditlevelName)!}<#else>A</#if></a></span>
				<i>
				   <#if list.novice==1><font class="font-one">新</font></#if>

		           <#--<#if list.addRate!=0&&list.addRate!=""><font class="font-two">息</font></#if>-->
		           <#--<#if list.coupon==1><font class="font-three">券</font></#if>-->
	            </i>
            <#if list.showRate!=0&&list.showRate!="">
                <#--<em class="jx">
	        	 	限时加息
                </em>-->
				<img src="${base}/theme/${systemConfig.theme}/images/mypic/jx.png" class="jx" alt="">
            </#if>
        	</h3>
        	<div class="investment-list">
        		<div class="fl w1015">
	        		<div class="investment-dl">
	        			<div class="f16">
		        			<p class="my-color">
		        				<em><#if list.showRate != null>${list.yearInterestRate - list.showRate}</em>%
                                <span class="nhlv"><i>+</i><em>${list.showRate}</em>%<b class="addinterest"></b>
								<#else >
									${list.yearInterestRate}</em>%
								</#if></span>
		        			</p>
                            预期年化收益率
	        			</div>
	        			<!--<div class="f16">还款方式2：${list.theWayBack}</div>-->
	        		</div>
	        		<div class="investment-dl">
	        			<div class="f16">
                        <#if list.proType=='P_Or' || list.proType=='B_Or'><p>${list.loanLifeQuery}天</p>剩余期限
							<#else>
							<p>${list.loanLife}</p>项目期限</#if>
						</div>
                    </div>
                    <div class="investment-dl">
                        <div class="f16">
                            <p><#if (list.bidMoney >=10000) >${(list.bidMoney/10000)?string(",##0.00#")}万元<#else>${list.bidMoney?string(',##0')}元</#if></p>
							借款金额
                        </div>
                    </div><div class="investment-dl">
                        <div class="f16">
							  <#if list.theWayBack == '按期计息到期还本' >
									<p>按期计息</p><#else >
									<p>${list.theWayBack!}</p>
							  </#if>
							还款方式
                        </div>
                    </div>
                    <#--<div class="investment-dl">
	        			<div class="f16 credit-rating">
                            <p><span class="myspan">
								<a class="
	                        <#if (list.keepCreditlevelName)??>
		                        <#if list.keepCreditlevelName=="AAA">aaa
		                        <#elseif list.keepCreditlevelName=="HR">hr
		                         <#elseif list.keepCreditlevelName=="AA">aa
		                        <#elseif list.keepCreditlevelName=="A">a
		                        <#elseif list.keepCreditlevelName=="B">b
		                        <#elseif list.keepCreditlevelName=="C">c
		                        <#elseif list.keepCreditlevelName=="D">d
		                        <#else>e
		                        </#if>
		                    <#else>
		                        a
	                        </#if>
                        ">
								<#if (list.keepCreditlevelName)??>${(list.keepCreditlevelName)!}<#else>A</#if></a></span></p>
							信用等级
	        			</div>
	        		</div>-->
                    <div class="investment-dl mar_l">
	        			<#--<div class="f16">
	        				&lt;#&ndash;<span>投资进度：</span>&ndash;&gt;
		        			<span class="progress project-progress w160" style="margin-top:9px;">
							    <span class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="11.00" aria-valuemin="0" aria-valuemax="100" style="width: <#if (list.state == 2)>100%<#elseif (list.state == 7)>100% <#elseif (list.state == 10)>100%<#else>${list.progress}%</#if>"></span>
							</span>
							<span class="progress-num" style="color:#262626;"><#if (list.state == 2)>100%<#elseif (list.state == 7)>100% <#elseif (list.state == 10)>100%<#else>${list.progress}%</#if></span>
	        			</div>-->
                        <div class="progressBar">
                            <div class="progressBar_pending" style="overflow: hidden;"></div>
                            <span class="progressBar_button"></span>
                            <div class="progressBar_title">
                                <span><#if (list.state == 2)>100%<#elseif (list.state == 7)>100%<#elseif (list.state == 10)>100%<#else>${list.progress}%</#if></span>
                            </div>
                        </div>
        			</div>
                   <div class="bid_list_btn fr">
        	    	<#if list.state==2 || list.state==5|| list.state==6>
                        <span class="gray-btn buttongray">已满标</span>
					<#elseif list.state==3>
                    	<span class="buttongray gray-btn">已流标</span>
					<#elseif list.state==4>
                    	<span class="buttongray gray-btn">已过期</span>
					<#elseif list.state==10>
                    	<span class="buttongray gray-btn">已还清</span>
					<#elseif list.state==7>
                    	<span class="buttongray gray-btn">还款中</span>
					<#else>
						<#if list.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt list.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") >
                    <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" class="buttonorange orange-btn">立即出借</a>
						<#else>
                    	<span class="buttongray gray-btn">预售中</span>
						</#if>
					</#if>
                        </div>

        		</div>
			</div>
            <div class="line"></div>
		</div>
        </#list>
        </#if>
        
        <#import "/WEB-INF/template/common/pager.ftl" as p>
        <#assign parameterMap = {"payTime":(plBidPlanFilter.payTime)!,"keepProtypeName":(plBidPlanFilter.proKeepType)!,"keepCreditlevelName":(plBidPlanFilter.keepCreditlevelName)!,"bidMoney":(plBidPlanFilter.bidMoney)!,"yearInterestRate":(plBidPlanFilter.yearInterestRate)!,"bidProName":'${bidProName!}'} />
        <@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/newPlanListPlBidPlan.do" parameterMap = parameterMap />
    <!--end散标循环列表-->   
  <!--end:新版项目列表-->  			
	</div>
</div> 
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<#-- <#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl"> -->
<script>
    var P2pProductList = {
        progressRoll:function(){
            var scrollTop = $(document).scrollTop();
            $(".progressBar").each(function () {
                var _this = $(this);
                var width = _this.children('.progressBar_title').find('span').html();
                var dataw={},datal={};
                dataw["width"]=width;
                datal["left"]=width;


                if (_this.offset().top <= (scrollTop + $(window).height()) && width != '0%') {
                  /*  _this.children('.progressBar_pending').animate({"width":data},1200);
                    _this.children('.progressBar_button').animate({"left": data},1200);
                    _this.children('.progressBar_title').animate({"left":data},1200);*/
                    _this.children('.progressBar_pending').animate(dataw,1200);
                    _this.children('.progressBar_button').animate(datal,1200);
                    _this.children('.progressBar_title').animate(datal,1200);
                }
            });
            return arguments.callee;
        }
    }
    $(function () {
        P2pProductList.progressRoll();
        $(document).scroll(function (e) {
            P2pProductList.progressRoll();
        });
    });
</script>

<script>
    function checkUser(){
		/*var swap_value = $("#swap_value").val();
        localStorage.setItem("swap_value",swap_value);*/
        document.getElementById("search_form").submit();
    }

    function KeyDown(){
        if (event.keyCode==13)   //回车键的键值为13
            /*/* var password = $.md5($("#password").val());//md5加密
         $("#password").val(password);*/
            checkUser();
    }

 /*   var local_value = localStorage.getItem("swap_value");
    $("#swap_value").val(local_value)*/

</script>



<#--加息活动start-->
<style>
    .jx_active{
        position:fixed;
        width:161px;
        height:148px;
        right:0px;
        top:69%;
        z-index:500;
    }
    .jx_active .jx_pic{
        width:100%;
        height:100%;
    }
    .time_con span{
        position:absolute;
        left:26px;
        top:118px;
        color:#ff5c49;
        font-size:18px;
        font-weight:bold;
    }
    .time_con span.jx_h{
        left:63px;
    }
    .time_con span.jx_m{
        left:101px;
    }
</style>
<div class="jx_active">
    <input type="hidden" id="jx_time" value=" ${time!}">
    <img src="${base}/theme/proj_wenandai/images/mypic/jx_time0.png" class="jx_pic" alt="">
    <div class="time_con">
        <span class="jx_d">00</span>
        <span class="jx_h">00</span>
        <span class="jx_m">00</span>
    </div>
</div>
<script>
    $(function(){
        //加息时间倒计时
        var time=parseInt($('#jx_time').val());
        var timeFn=function () {
            if(time>0){
                var day = parseInt(time / 1000 / 60 / 60 / 24);
                var hour = parseInt(time / 1000 / 60 / 60 % 24);
                var minute = parseInt(time / 1000 / 60 % 60);
                //var seconds = parseInt(time / 1000 % 60);
                if(day<10){
                    day='0'+day;
                }
                if(hour<10){
                    hour='0'+hour;
                }
                if(minute<10){
                    minute='0'+minute;
                }
                $('.jx_active .jx_d').html(day);
                $('.jx_active .jx_h').html(hour);
                $('.jx_active .jx_m').html(minute);
                //$('#time').html(day + "天" + hour + "时" + minute + "分" + seconds + "秒");
                time-=60000;
            }
        }
        timeFn();
        var t=setInterval(timeFn, 60000);
    });
</script>
<#--加息活动end-->
</body>
</html>