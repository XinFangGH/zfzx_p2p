<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 理财计划</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript" src="${base}/js/user/mmplan.js"></script>
<script type="text/javascript" src="${base}/js/user/mmplanFinancialSelect.js"></script>
<script type="text/javascript" src="${base}/js/user/ummplanSelect.js"></script>
<script type="text/javascript">var m1="个人中心",m2="理财计划",m3="我的投资";</script>
<script type="text/javascript">
	$(function(){
		$(".content01 .finlist_title01 li").click(function(){
			$(this).addClass("active").siblings().removeClass();
			$(".content01 .finlist_hide01 ol").eq($(this).index()).addClass("rent").siblings().removeClass();
		});
		if($("#str").val()=='pass'){}
		var mmfShow=$("#mmfShow").val();
		if(mmfShow=='earlyList'){
		  var t2 = document.getElementById("checkStatus");
	   if(t2!=null){
		for(i=0;i<t2.length;i++){
	        if($("#ztSelectValue").val()==t2.options[i].value){  
	            t2.options[i].selected=true  
	        }  
	     } 
	    }
	   }
	});
	$(document).ready(function(){
		var active="${show}";
		var obj=$(".content01 .finlist_title01 li");
		if(active=='returnList'){
			$(obj.get(0)).addClass("active").siblings().removeClass();
			$($(".content01 .finlist_hide01 ol").get(0)).addClass("rent").siblings().removeClass();
		}else if(active=='fullList'){
			$(obj.get(1)).addClass("active").siblings().removeClass();
			$($(".content01 .finlist_hide01 ol").get(1)).addClass("rent").siblings().removeClass();
		}else if(active=='presaleList'){
			$(obj.get(3)).addClass("active").siblings().removeClass();
			$($(".content01 .finlist_hide01 ol").get(3)).addClass("rent").siblings().removeClass();
		}else if(active=='bidingList'){
			$(obj.get(2)).addClass("active").siblings().removeClass();
			$($(".content01 .finlist_hide01 ol").get(2)).addClass("rent").siblings().removeClass();
		}else if(active=='successList'){
			$(obj.get(4)).addClass("active").siblings().removeClass();
			$($(".content01 .finlist_hide01 ol").get(4)).addClass("rent").siblings().removeClass();
		}else if(active=='earlyList'){
			$(obj.get(5)).addClass("active").siblings().removeClass();
			$($(".content01 .finlist_hide01 ol").get(5)).addClass("rent").siblings().removeClass();
		}else{
			$(obj.get(0)).addClass("active").siblings().removeClass();
			$($(".content01 .finlist_hide01 ol").get(0)).addClass("rent").siblings().removeClass();
		}
	});
	$(function(){
		$(".content02 .finlist_title02 li").click(function(){
			$(this).addClass("active").siblings().removeClass();
			$(".content02 .finlist_hide02 ol").eq($(this).index()).addClass("rent").siblings().removeClass();
		});
		if($("#str").val()=='pass'){}
	});
</script>	
<style type="text/css">
	dd {
		display: block;
		-webkit-margin-start: 40px;
	};
	dl {
		display: block;
		-webkit-margin-before: 1em;
		-webkit-margin-after: 1em;
		-webkit-margin-start: 0px;
		-webkit-margin-end: 0px;
	};
	.query-btn {
		margin-left: 10px;
		color: #fff!important;
		padding: 6px;
		cursor: pointer;
		border-radius: 3px;
		text-decoration: none;
		background: #e74649;
		background: -moz-linear-gradient(top, #e74649 0%, #e03336 100%);
		background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#e74649), color-stop(100%,#e03336));
		background: -webkit-linear-gradient(top, #e74649 0%,#e03336 100%);
		background: -o-linear-gradient(top, #e74649 0%,#e03336 100%);
		background: -ms-linear-gradient(top, #e74649 0%,#e03336 100%);
		background: linear-gradient(to bottom, #e74649 0%,#e03336 100%);
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e74649', endColorstr='#e03336',GradientType=0 );
	};
</style>
</head>
<body >
<!-- header --><!-- navbar -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<div class="main-box">
<input id="str" type="hidden" value="${str}">
<input id="mmfShow" type="hidden" value="${show}">
<input id="ztSelectValue" type="hidden" value="${checkStatus1}">
<input value="${(bpCustMember.id)??}" type="hidden" id="memberId">
	<!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
	<!-- 以下内容为左侧三级导航条 -->
	<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
    <!-- 左侧三级导航结束 end-->
    <!-- 左侧页面 end -->
    <!-- <div style="float:left; height:700px; padding:10px 0px; width:1px; border:0px; background-color:#e0e0e0;"></div>-->
    <!-- 右侧主体内容 start  右侧内容宽740px -->
    <div class="main-cont">
    <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_financial.ftl">
    <div class="content01">
       <ul class="finlist_title01">
          <li class="active"><a href="${base}/creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?state=7&show=returnList" class="<#if show=="returnList">bg1<#else>bg2</#if>">回款中</a></li>
          <li ><a href="${base}/creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?state=2&show=fullList" class="<#if show=="fullList">bg1<#else>bg2</#if>">已齐标</a></li>
          <li ><a href="${base}/creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?state=1&show=bidingList" class="<#if show=="bidingList">bg1<#else>bg2</#if>">招标中</a></li>
          <li ><a href="${base}/creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?state=1&isPresale=ysz&show=presaleList" class="<#if show=="presaleList">bg1<#else>bg2</#if>">预售中</a></li>
          <li ><a href="${base}/creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?state=10&show=successList" class="<#if show=="successList">bg1<#else>bg2</#if>">已完成</a></li>
             <li ><a href="${base}/creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?show=earlyList" class="<#if show=="earlyList">bg1<#else>bg2</#if>">退出审核</a></li>     
       </ul>
       <div class="finlist_hide01">
         <ol  class="rent">
           <div class="tab-list">
              <div class="worm-tips2">
            	 <span class="icon3">回款中</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	 	<span>理财计划名称：</span> 
                    <span>
            	   <input type="text" id="mmName_returnList" class="colorful" value="${mmName_returnList}"/></span>
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	 <span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="queryFinReturnList" name="queryBtn">查&nbsp;询</span> 
    	      </div>
              <table width="100%"  class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
                 <tr>
                   <th align="center">计划名称   </th>
                   <th align="center"> 招标金额  </th>
                   <th align="center"> 预期收益率 </th>
                   <th align="center"> 投资期限</th>
                   <th align="center"> 锁定期至 </th>
                   <th align="center"> 计划派息日 </th>
                   <th align="center"> 派息金额 </th>
                   <th align="center"> 查看 </th>
                   <th align="center"> 操作 </th>
                 </tr>
                 <#list pager.list as list>
                 <tr>
                   <td align="center" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
                     <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr=${list.keystr}&mmplanId=${list.mmplanId}" target="_blank" title="${list.mmName}"><span class="tit">${list.mmName}</span></a>
                   </td>
                   <td  align="center" style="padding-right:5px"><#if list.sumMoney==0>0.00<#else><#if list.sumMoney lt 1000>${list.sumMoney}<#else>${list.sumMoney?string(',###.00')}</#if></#if>元</td>
                   <td  align="center" style="padding-right:5px">${list.interestRate}%</td>
                   <td  align="center" style="padding-right:5px">${list.investlimit}月</td>
                   <td  align="center" style="padding-right:5px">${list.lockingEndDate?string("yyyy-MM-dd")}</td>
                   <td  align="center" style="padding-right:5px">${list.intentDate?string("yyyy-MM-dd")}</td>
                   <td  align="center" style="padding-right:5px"><#if list.repaymentTotal==0>0.00<#else><#if list.repaymentTotal lt 1000>${list.repaymentTotal}<#else>${list.repaymentTotal?string(',###.00')}</#if></#if>元</td>
                   <td align="center">
					  	<a href="javascript:void(0);"  onClick="clickPlan(${list.mmplanId},'Finperson')"><span class="normal">回款计划&nbsp;&nbsp;|</a></span></a>
				  </td>  
                   <td align="center" >
	                  <span style="display:inline-block;"><a href="javascript:void(0);"  onClick="clickFinancial(${list.mmplanId},'${list.intentDate}') " class="btn1">派息</a>
	                   <span class="last"></span></span> </td>
                
                 <tr>
                 </#list>
               </table>
            <#import "/WEB-INF/template/common/pager.ftl" as p>
         	<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?state=7&show=returnList"  />
          </div>
       </ol>
       <ol>
	   		<div class="tab-list">
	   	 		<div class="worm-tips2">
	        	 	<span class="icon3">已齐标</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	<span>理财计划名称：</span> 
                    <span>
            	   <input type="text" id="mmName_fullList" class="colorful" value="${mmName_fullList}"/></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="queryFinFullList" name="queryBtn">查&nbsp;询</span> 
		   	 </div>
             <table width="100%"  class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <th align="center">计划名称   </th>
                  <th align="center"> 招标金额  </th>
                  <th align="center"> 预期收益率 </th>
                  <th align="center">投资期限</th>
                  <th align="center">投标开始日</th>
                  <th align="center">加入人次 </th>
                </tr>
                <#list pager.list as list>
            	<tr>
               		<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
	                    <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr=${list.keystr}&mmplanId=${list.mmplanId}" target="_blank" title="${list.mmName}">
	                        <span class="tit">${list.mmName}</span><br/>
	                    </a>
             		</td>
                    <td align="center" ><#if list.sumMoney==0>0.00<#else><#if list.sumMoney lt 1000>${list.sumMoney}<#else>${list.sumMoney?string(',###.00')}</#if></#if>元</td>
                    <td  align="center" style="padding-right:5px">${list.interestRate}%</td>
                     <td  align="center" style="padding-right:5px">${list.investlimit}月</td>
                     <td align="center">${list.buyStartTime}</td>
                     <td align="center">${list.joinCount}</td>
            	</tr>
                </#list>
	        </table>
          	<#import "/WEB-INF/template/common/pager.ftl" as p>
     		<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?state=2&show=fullList"  />
	    </div>
      </ol>
         <ol>
	   		<div class="tab-list">
	   	 		<div class="worm-tips2">
	        	 	<span class="icon3">招标中</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	  	<span>理财计划名称：</span> 
                    <span>
            	   <input type="text" id="mmName_bidingList" class="colorful" value="${mmName_bidingList}"/></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="queryFinBidingList" name="queryBtn">查&nbsp;询</span> 
		   	 </div>
             <table width="100%"  class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <th align="center">计划名称   </th>
                  <th align="center"> 招标金额  </th>
                  <th align="center"> 预期收益率 </th>
                  <th align="center">投资期限</th>
                  <th align="center">投标开始日</th>
                  <th align="center">加入人次 </th>
                  <th align="center">剩余可投金额 </th>
                </tr>
                <#list pager.list as list>
            	<tr>
               		<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
	                    <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr=${list.keystr}&mmplanId=${list.mmplanId}" target="_blank" title="${list.mmName}">
	                        <span class="tit">${list.mmName}</span><br/>
	                    </a>
             		</td>
                    <td align="center" ><#if list.sumMoney==0>0.00<#else><#if list.sumMoney lt 1000>${list.sumMoney}<#else>${list.sumMoney?string(',###.00')}</#if></#if>元</td>
                    <td  align="center" style="padding-right:5px">${list.interestRate}%</td>
                     <td  align="center" style="padding-right:5px">${list.investlimit}月</td>
                     <td align="center">${list.buyStartTime}</td>
                     <td align="center">${list.joinCount}</td>
                      <td align="center" ><#if list.remainingMoney==0>0.00<#else><#if list.remainingMoney lt 1000>${list.remainingMoney}<#else>${list.remainingMoney?string(',###.00')}</#if></#if>元</td>
            	</tr>
                </#list>
	        </table>
          	<#import "/WEB-INF/template/common/pager.ftl" as p>
     		<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?state=1&show=bidingList"  />
	    </div>
      </ol>
      
        <ol>
	   		<div class="tab-list">
	   	 		<div class="worm-tips2">
	        	 	<span class="icon3">预售中</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	  	<span>理财计划名称：</span> 
                    <span>
            	   <input type="text" id="mmName_presaleList" class="colorful" value="${mmName_presaleList}"/></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="queryFinPresaleList" name="queryBtn">查&nbsp;询</span> 
		   	 </div>
             <table width="100%"  class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <th align="center">计划名称   </th>
                  <th align="center"> 招标金额  </th>
                  <th align="center"> 预期收益率 </th>
                  <th align="center">投资期限</th>
                  <th align="center">预售时间</th>
                  <th align="center">投标开始时间</th>
                 
                </tr>
                <#list pager.list as list>
            	<tr>
               		<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
	                    <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr=${list.keystr}&mmplanId=${list.mmplanId}" target="_blank" title="${list.mmName}">
	                        <span class="tit">${list.mmName}</span><br/>
	                    </a>
             		</td>
                    <td align="center" ><#if list.sumMoney==0>0.00<#else><#if list.sumMoney lt 1000>${list.sumMoney}<#else>${list.sumMoney?string(',###.00')}</#if></#if>元</td>
                    <td  align="center" style="padding-right:5px">${list.interestRate}%</td>
                     <td  align="center" style="padding-right:5px">${list.investlimit}月</td>
                     <td align="center">${list.preSaleTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                     <td align="center">${list.buyStartTime?string("yyyy-MM-dd HH:mm:ss")}</td>
            	</tr>
                </#list>
	        </table>
          	<#import "/WEB-INF/template/common/pager.ftl" as p>
     		<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?state=1&isPresale=ysz&show=presaleList"  />
	    </div>
      </ol>
         <ol>
	   		<div class="tab-list">
	   	 		<div class="worm-tips2">
	        	 	<span class="icon3">已完成</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	 	<span>理财计划名称：</span> 
                    <span>
            	   <input type="text" id="mmName_successList" class="colorful" value="${mmName_successList}"/></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="queryFinSuccessList" name="queryBtn">查&nbsp;询</span> 
		   	 </div>
             <table width="100%"  class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <th align="center">计划名称   </th>
                  <th align="center"> 招标金额  </th>
                  <th align="center"> 预期收益率 </th>
                  <th align="center">投资期限</th>
                  <th align="center"> 派息总金额  </th>
                  <th align="center"> 操作 </th>
                
                </tr>
                <#list pager.list as list>
            	<tr>
               		<td align="center"  style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
	                    <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr=${list.keystr}&mmplanId=${list.mmplanId}" target="_blank" title="${list.mmName}">
	                        <span class="tit">${list.mmName}</span><br/>
	                    </a>
             		</td>
                    <td align="center" ><#if list.sumMoney==0>0.00<#else><#if list.sumMoney lt 1000>${list.sumMoney}<#else>${list.sumMoney?string(',###.00')}</#if></#if>元</td>
                    <td  align="center" style="padding-right:5px">${list.interestRate}%</td>
                     <td  align="center" style="padding-right:5px">${list.investlimit}月</td>
                    <td align="center" ><#if list.allMoney==0>0.00<#else><#if list.allMoney lt 1000>${list.allMoney}<#else>${list.allMoney?string(',###.00')}</#if></#if>元</td>

                     <td align="center">合同</td>
            	</tr>
                </#list>
	        </table>
          	<#import "/WEB-INF/template/common/pager.ftl" as p>
     		<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?state=10&show=successList"  />
	    </div>
      </ol>
        <ol>
           <div class="tab-list">
              <div class="worm-tips2">
            	 <span class="icon3">退出审核</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	 	<span>理财计划名称：</span> 
                    <span>
            	   <input type="text" id="mmName_earlyList" class="colorful" value="${mmName_earlyList}"/></span>
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	  	<span>状态：</span> 
            	 <span>
            	  <select style="width:80px;height:28px;" id="checkStatus"> 
            	 	<option value="0">未审核</option>
            	 	<option value="1">已通过</option>
            	 	<option value="3">已驳回</option>		
            	  </select></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	 <span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" id="queryFinEarlyList" name="queryBtn">查&nbsp;询</span> 
    	      </div>
              <table width="100%"  class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
                 <tr>
                   <th align="center">计划名称   </th>
                   <th align="center"> 投资人  </th>
                   <th align="center"> 购买金额 </th>
                   <th align="center"> 计息起日</th>
                   <th align="center"> 计息止日 </th>
                   <th align="center"> 申请日期 </th>
                   <th align="center"> 状态 </th>
                   <th align="center"> 操作 </th>
                 </tr>
                 <#list pager.list as list>
                 <tr>
                   <td align="center" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
                     <a href="${base}/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?keystr=${list.keystr}&mmplanId=${list.mmplanId}" target="_blank" title="${list.mmName}"><span class="tit">${list.mmName}</span></a>
                   </td>
                   <td  align="center" style="padding-right:5px">${list.investPersonName}</td>
                   <td  align="center" style="padding-right:5px"><#if list.buyMoney==0>0.00<#else><#if list.buyMoney lt 1000>${list.buyMoney}<#else>${list.buyMoney?string(',###.00')}</#if></#if>元</td>
                   <td  align="center" style="padding-right:5px">${list.startinInterestTime?string("yyyy-MM-dd")}</td>
                   <td  align="center" style="padding-right:5px">${list.endinInterestTime?string("yyyy-MM-dd")}</td>
                   <td  align="center" style="padding-right:5px">${list.earlyDate?string("yyyy-MM-dd")}</td>
                    <td  align="center" style="padding-right:5px"><#if list.checkStatus==0>未审核<#else><#if list.checkStatus==1>已通过<#else>已驳回</#if></#if></td>
                   <td align="center" >
	                  <span style="display:inline-block;"><#if list.checkStatus==0><a href="javascript:void(0);"  onClick="clickEarly('${list.earlyRedemptionId}') " class="btn1">审核<#else>审核</#if></a>
	                   <span class="last"></span></span> 
	                   	<#if list.keystr=='UPlan'>
                			<a class="btn1" target="_blank" href="${base}/creditFlow/financingAgency/getClaimsListPlManageMoneyPlanBuyinfo.do?orderId=${list.orderId}" class="btn2">债权</a>
                			<#else>
                			<a class="btn1" target="_blank" href="${base}/creditFlow/financingAgency/getClaimsListPlManageMoneyPlanBuyinfo.do?orderId=${list.orderId}" class="btn2">债权</a>
                			</#if>
	                </td>
                
                 <tr>
                 </#list>
               </table>
            <#import "/WEB-INF/template/common/pager.ftl" as p>
         	<@p.pager pager = pager baseUrl = "/creditFlow/financingAgency/manageFinanciallistPlManageMoneyPlanBuyinfo.do?show=earlyList"  />
          </div>
       </ol>
     </div>  
  </div>
</div>
</div>						
<!--end: Container -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</body>
</html>