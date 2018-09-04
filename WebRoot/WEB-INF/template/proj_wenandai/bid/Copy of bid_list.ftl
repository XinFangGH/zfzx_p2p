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
        $('#plan${list0.bidId}').css("background-position","${(list0.progress)*-54}px 0");
        </#list>
        
        <#list listPlan as list0 >
        	$('#plan${list0.mmplanId}').css("background-position","${(list0.progress)*-54}px 0");
        </#list>
		
		
		  <#list pager.list as list0 >
			$("#pb${list0.bidId}").progressBar();
			</#list>
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
<body >
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
	    	<a href="${base}/creditFlow/financingAgency/listPlBidPlan.do" class="<#if planType=="Dir">bg1<#else>bg2</#if>">散标投资</a>
	    	<a href="${base}/creditFlow/financingAgency/getUPlanPlManageMoneyPlan.do" class="<#if planType=="UPlan">bg1<#else>bg2</#if>">U债权转让</a>
	    	<#--<a href="${base}/creditFlow/financingAgency/listPlBidPlan.do?Q_proType_S_LK=Or" class="<#if planType=="Or">bg1<#else>bg2</#if>">债权转让</a>-->
	    	<a href="${base}/creditFlow/financingAgency/alltransferinglistPlBidSale.do" class="<#if planType=="ortransfer">bg1<#else>bg2</#if>">债权交易</a>
		</div>
		<form id="queryForm" name="queryForm" method="get" action="${base}/creditFlow/financingAgency/listPlBidPlan.do">					
			<input type="hidden" id="pageNumber" name="pager.pageNumber" value="${pager.pageNumber}"/>
			<input type="hidden" id="pageSize" name="pager.pageSize" value="${pager.pageSize}"/>
			<div id="filter" class="bidsearch">
			
				 <div class="filter-condition">
	                <label>借款期限：<input type="hidden" id="businessTypeInput" name="payTime" value="${plBidPlanFilter.payTime!}" /></label>
	                <ul class="filter-content" id="proKeepType">
	                	<li><a href="javascript:void(0)" lang="" class="selected attribute">不限</a></li> 
				        <li><a href="javascript:void(0);" class="attribute" lang="0-1">1个月以下</a></li>  
				        <li><a href="javascript:void(0);" class="attribute" lang="1-3">1-3个月</a></li>  
				        <li><a href="javascript:void(0);" class="attribute" lang="4-6">4-6个月</a></li>  
				        <li><a href="javascript:void(0);" class="attribute" lang="7-11">7-11个月</a></li> 
				        <li><a href="javascript:void(0);" class="attribute" lang="12-1000">12个月以上</a></li> 
	                </ul>
                </div>
                
                <div class="filter-condition">
			      <label> 金额范围：<input type="hidden" id="bidMoneyInput" name="bidMoney" value="${plBidPlanFilter.bidMoney!}"  /> </label>
			      <ul class="filter-content"  id="bidMoney"> 
				      <li><a href="javascript:void(0);" lang="" class="selected attribute">不限</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="1-5000">5千元以下</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="5000-10000">5000-1万元</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="10000-50000">1万元-5万元</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="50000-100000">5万元-10万元</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="100000-500000">10万元-50万元</a></li>
				      <li><a href="javascript:void(0);" class="attribute"  lang="500000-1000000">50万元-100万元</a></li> 
				     <li><span class="hidden-city"> <a href="javascript:void(0);" class="attribute"  lang="1000000-100000000">100万元以上</a></span></li> 
			      </ul>
			    </div>    
			    
			    
			     <div class="filter-condition  more">
			      <label> 年收益率：<input type="hidden" id="yearInterestRateInput" name="yearInterestRate" value="${plBidPlanFilter.yearInterestRate!}"  /> </label>
			      <ul class="filter-content"  id="yearInterestRate"> 
				      <li><a href="javascript:void(0);" lang="" class="selected attribute">不限</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="0-5">5%以下</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="5-8">5%-8%</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="8-10">8%-10%</a></li> 
				      <li><a href="javascript:void(0);" class="attribute"  lang="10-100">10%以上</a></li> 
			      </ul>
			    </div>    
			      
                
				<div class="filter-condition  more">
	                <label>标的类型：<input type="hidden" id="bidtype" name="plBidPlanFilter.keepProtypeName" value="${plBidPlanFilter.proKeepType!}" /></label>
	                <ul class="filter-content" id="businessFrom">
	                  <li><a href="javascript:void(0)"  lang="" class="selected attribute">不限</a></li>
				      <li><a href="javascript:void(0)"  lang="credit" class="attribute">信用审核标</a></li>
				      <li><a href="javascript:void(0)"  lang="indeed" class="attribute">实地核查标</a></li>
				      <li><a href="javascript:void(0)"  lang="assure" class="attribute">机构担保标</a></li>
				      <li><a href="javascript:void(0)"  lang="walfare" class="attribute">福利标</a></li>
	                </ul>
                </div>
               
                <div class="filter-condition  more">
	                <label>信用等级： <input type="hidden" id="payTimeInput" name="plKeepCreditlevel_name" value="${plBidPlanFilter.proType!}"  /></label>
	                <ul class="filter-content" id="payTime">
	                	<li><a href="javascript:void(0);" lang="" class="selected attribute">不限</a></li>   
				      	<li><a href="javascript:void(0);" class="attribute" lang="AA">AA</a></li>  
				      	<li><a href="javascript:void(0);" class="attribute" lang="A">A</a></li> 
				     	<li><a href="javascript:void(0);" class="attribute" lang="B">B</a></li> 
					    <li><a href="javascript:void(0);" class="attribute" lang="C">C</a></li> 
					    <li><a href="javascript:void(0);" class="attribute" lang="D">D</a></li> 
					    <li><a href="javascript:void(0);" class="attribute" lang="E">E</a></li> 
					    <li><a href="javascript:void(0);" class="attribute" lang="HR">HR</a></li>  	                	
	                </ul>
                </div>
                			
			    <div class="filter-other" data-status="close" id="filterother">
                 		   更多筛选条件
                    <div class="sign"></div>
               </div>
		</div>
 	</form> 
</div>
<div class="worm-tips2">
   <span class="icon">温馨提示：工作日固定发标时间在12:00、17:00，其余时间与周末随机发标</span>
</div>
<div class="tenderlist">
	<div class="title">
       <!--<div style="float:left; width:35px; height:25px; padding:16px 0px 0px 5px;"><img src="${base}/theme/${systemConfig.theme}/images/arrow-1.jpg" width="25" height="25" /></div>-->
                   投22资理财
       <#--<a rel="leanModalIncome" href="#signupInterest"><span  onClick="Caculator()"><span class="computer">理财计算器</span></a>-->
    </div>
    
    <!--start体验标循环列表--> 
    <#if listPlan??>
       <#list listPlan as plman>
          <div class="investment yellow">
        	<h3 class="yellow">
	        	<a href="#" title="">新手体验-模拟投资</a>
	        	<em><img src="${base}/theme/${systemConfig.theme}/images/hot.jpg"></em>
        	</h3>
        	<div class="investment-list">
        		<dl class="investment-dl">
        			<dt><a href="${base}/creditFlow/financingAgency/getExperienceDetailPlManageMoneyPlan.do?mmplanId=${plman.mmplanId}"><img src="${base}/theme/${systemConfig.theme}/images/tiyanb.png" width="140" height="100"></a></dt>
        			<dd>
	    				<h5><a href="${base}/creditFlow/financingAgency/getExperienceDetailPlManageMoneyPlan.do?mmplanId=${plman.mmplanId}" title=""><#if plman.mmName?length gt 12>${plman.mmName?substring(0,12)}<#else>${plman.mmName}</#if></a></h5>
	    			 	<p>借款金额：<#if (plman.sumMoney>=10000)><em>${(plman.sumMoney/10000)?string(",##0.00#")}</em>万元<#else><em>${plman.sumMoney?string(',##0')}</em>元</#if></p>
	    				<p>借款期限： <span>${plman.investlimit}天</span></p>
	    				<p>信用等级： <a class="aa">AA</a></p>
        			</dd>
        		</dl>
        		<div class="investment-div">
        			<p>年化收益率：</p>
 					<p>
 					<em >${plman.yeaRate}</em>		
 					<i>%</i>
 					</p>
        		</div>
        		<div class="investment-right">
        			 <span class="w110 wad_spec5">
	        			 <strong id="plan${plman.mmplanId}">
	        			 	<em>${plman.progress}</em>%
        			     </strong>
        			 </span>
        			 <span class="wad_spec6">
        			 <!--<em>进度：${plman.progress}</em>-->
        			  <#if plman.state==2 >
        			     <a class="yellow" href="#">已满标</a>
                      <#elseif plman.state==-2 || plman.state==-1>
                         <a class="yellow" href="#">已流标</a>
                      <#elseif plman.state==10>
                         <a class="yellow" href="#">已完成</a>
                      <#elseif plman.state==7>
                         <a class="yellow" href="#">还款中</a>
                      <#else>
                           <#if plman.startTime?date("yyyy-MM-dd HH:mm:ss") lt plman.nowDate?date("yyyy-MM-dd HH:mm:ss") >
        			 	   		<a class="yellow" href="${base}/creditFlow/financingAgency/getExperienceDetailPlManageMoneyPlan.do?mmplanId=${plman.mmplanId}">投标中</a>
                           <#else>
                                <a class="yellow" href="#">预售中</a>
                           </#if>
                        </#if>
        			 	
        			 </span>
        		</div>
        	</div>             	
        </div>
     </#list>   
  </#if>  
<!--end体验标循环列表--> 



 <!--start散标循环列表--> 
        <#if (pager.list)??>
        <#list pager.list as list>
        <div class="investment">
        	<h3> 
	        	 <em><img src="${base}/theme/${systemConfig.theme}/images/<#if (list.proKeepType)??><#if list.proKeepType=="信用审核标">xin.png<#elseif list.proKeepType=="实地核查标">shi.png<#elseif  list.proKeepType="机构担保标">bao.png<#elseif list.proKeepType="福利标">fu.png<#else>xin.png</#if><#else>xin.png</#if>" width="19"/></em>
	        	 <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" title="${list.bidProName}"><#if list.bidProName?length gt 20>${list.bidProName?substring(0,20)}<#else>${list.bidProName}</#if></a>
	        	<i>
				   <#if list.novice==1><font class="font-one">新</font></#if>
		           <#if list.addRate!=0&&list.addRate!=""><font class="font-two">息</font></#if>
		           <#if list.coupon==1><font class="font-three">券</font></#if>
	            </i>
        	</h3>
        	<div class="investment-list">
        		<dl class="investment-dl">
        			<#if list.logoURL==0>
        			<dt><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}"><img src="${base}/theme/${systemConfig.theme}/images/dl-list-pic.png" width="140" height="100"></a></dt>
        			<#else>
        			<dt><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}"><img src="${base}/${list.logoURL}" width="140" height="100"></a></dt>
        			</#if>
        			<dd>
        				<h5><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" title="${list.bidProName}"><#if list.bidProName?length gt 10>${list.bidProName?substring(0,10)}<#else>${list.bidProName}</#if></a></h5>
        				<p>借款金额：<#if (list.bidMoney >=10000) ><em>${(list.bidMoney/10000)?string(",##0.00#")}</em>万元<#else><em>${list.bidMoney?string(',##0')}</em>元</#if></p>
        				<p><#if list.proType=='P_Or' || list.proType=='B_Or'>剩余期限：<span></span><#else>借款期限：<span>${list.loanLife}</span></#if> </p>
        				<p>信用等级：<a class="
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
        				</p>
        			</dd>
        		</dl>
        		<div class="investment-div">
        			<p>年化收益率：</p>
 					<p>
 					<em >
 					<#if (list.proType=="B_Dir")>${list.bpBusinessDirPro.yearInterestRate}</#if>
            		<#if (list.proType=="B_Or")>${list.bpBusinessOrPro.yearInterestRate}</#if>
            		<#if (list.proType=="P_Dir")>${list.bpPersionDirPro.yearInterestRate}</#if>
            		<#if (list.proType=="P_Or")>${list.bpPersionOrPro.yearInterestRate}</#if>
 					</em>		
 					<i>%</i>
 					</p>
        		</div>
        		<div class="investment-right">
        			 <span class="wad_spec5">
	        			 <strong id="plan${list.bidId}">
	        			 	<em>${list.progress}</em>%
        			     </strong>
        			 </span>
        			 <span class="wad_spec6">
        			    <#if list.state==2 || list.state==5|| list.state==6>已满标
                        <#elseif list.state==3>已流标
                        <#elseif list.state==4>已过期
                        <#elseif list.state==10>已还清
                        <#elseif list.state==7>还款中
                        <#else>
                            <#if list.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt list.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") >
                            <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" style="color:#fff;">投标中</a>
                            <#else>预售中</#if>
                        </#if>
        			 </span>
        		</div>
        	</div>             	
        </div>
        </#list>
        </#if>
        
        <#import "/WEB-INF/template/common/pager.ftl" as p>
        <#assign parameterMap = {"payTime":(plBidPlanFilter.payTime)!,"plBidPlanFilter.keepProtypeName":(plBidPlanFilter.proKeepType)!,"plKeepCreditlevel_name":(plBidPlanFilter.proType)!,"bidMoney":(plBidPlanFilter.bidMoney)!} />
        <@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/listPlBidPlan.do" parameterMap = parameterMap />
    <!--end散标循环列表-->   

  <!--end:新版项目列表-->  			
  </div>      
</div>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<#-- <#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl"> -->
</body>
</html>