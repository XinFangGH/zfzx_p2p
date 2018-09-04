<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 招标信息详情</title>

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>

<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
<script type="text/javascript">
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200 });		
	});
</script>
</head>
<body>
<div> 
  <!-- topbar -->
  <!-- header --><!-- navbar -->
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<!-- main --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 -->
<div class="bidinfo"> 
  <!-- 详情页中部内容开始 --> 
  	<!-- 招标信息 start -->
  	<#assign plan=plan?eval />
  	<#assign planPro=planPro?eval />
  	<#assign planKeep=planKeep?eval />
  	
  	
  	<div style="border:1px solid #e0e0e0; width:999px;">
    	<div style="background-color:#F0F5F8; width:969px; height:50px; padding:20px 0px 0px 30px;">
        	<span class="big black bold">${plan.bidProName}</span>
        </div>
        <div style="width:999px; height:200px;">        	
            <div style="float:left; width:690px; height:190px; margin:10px 0px 0px 0px;">
            		<ul style="overflow:hidden;">
                        <li style="width:100px; text-align:right;">招标金额：</li>
                        <li style="width:100px; text-align:left;">${plan.bidMoney?string(',###.00')}元</li>
                        <li style="width:100px; text-align:right;">年化利率：</li>
                        <li style="width:100px; text-align:left;">${planPro.yearInterestRate}%</li>
                        <li style="width:100px; text-align:right;">借款期限：</li>
                        <li style="width:100px; text-align:left;">${planPro.loanLife}个月</li>
                        <li style="width:100px; text-align:right;">项目编号：</li>
                        <li style="width:100px; text-align:left;">${plan.bidProNumber}</li>
                        <li style="width:100px; text-align:right;">信用等级：</li>
                        <li style="width:100px; text-align:left;">${planKeep.plKeepCreditlevel.name}</li>
                        <li style="width:100px; text-align:right;">招标类型：</li>
                        <li style="width:100px; text-align:left;">${plan.plBiddingType.name}</li>
                    </ul>
                    <div style="width:90%; margin:20px auto; height:0px; border-bottom:1px dashed #e0e0e0;"></div>
            		<ul style="overflow:hidden;">
                           <li style="width:100px; text-align:right;">还款方式：</li>
                        <li style="width:100px; text-align:left;">                     
										<#if plan.payIntersetWay==1>等额本息</#if>
										<#if plan.payIntersetWay==2>等额本金</#if>
										<#if plan.payIntersetWay==3>等本等息</#if>
										<#if plan.payIntersetWay==4>按日计息到期还本</#if>
										<#if plan.payIntersetWay==5>期末一次性支付本息</#if>
                        </li>
                        <li style="width:100px; text-align:right;">还需资金：</li>
                        <li style="width:100px; text-align:left;" id="money">${plan.afterMoney?string(',###.00')}元</li>
                         <input type="hidden" id="hiddenMoney" value="${plan.afterMoney}"/>
                        <li style="width:100px; text-align:right;">投标进度：</li>
                                             <li style="width:100px; text-align:left;" ><span class="progressBar" id="progress">${plan.progress}%</span></li>
                         <li style="width:100px; text-align:right;">剩余时间：</li>
                        <li style="width:100px; text-align:left;"></li>
                        <li style="width:100px; text-align:right;" >投标人数：</li>
                        <li style="width:100px; text-align:left;" id="persionNum">${plan.persionNum}</li>
                        <li style="width:100px; text-align:right;">借款类型：</li>
                        <li style="width:100px; text-align:left;">${planKeep.plKeepProtype.name}</li>
                    </ul>
            </div>
            <div style="float:left; width:1px; height:180px; background-color:#e0e0e0; margin:10px 0px;"></div>
            <div style="float:left; width:248px; height:180px; margin:0px 0px 0px 0px; padding:20px 30px 0px 30px; ">
            	<span class="black middle" style="line-height:200%;">
            	担保机构：${planKeep.plKeepGtorz.name}<br />
                 ${planKeep.plKeepGtorz.remark}
                </span>
                   <div class="buttonblue"   style="width:96px; height:32px; font-size:18px; padding:8px 0px 0px 24px; margin:15px auto;"><a id="go" rel="leanModal" name="signup" onClick="check('${plan.bidId}','${plan.bidProName}')" href="#signup"><span class="big white" >马上投标</span></a></div>
               <div class="buttonblue" id="full" style="width:96px; height:32px; font-size:18px; padding:8px 0px 0px 24px; margin:15px auto; display:none"><span class="big white">已满标</span></div>
                        </div>
        </div>
    </div>
  	<!-- 招标信息 end -->
    <!-- 左侧布局 start -->    	
    <div style="float:left; width:690px; margin:10px 5px 10px 0px;">
    	<!--项目基本信息 start -->
        <div style="float:left;border:1px solid #e0e0e0; width:688px; margin:10px 5px 10px 0px;">
    		<div style="background-color:#F0F5F8; width:658px; height:50px; padding:20px 0px 0px 30px;">
        	<span class="big black bold">项目基本信息</span></div>
        	<div style="width:648px; height:auto; padding:30px 20px;" class="text">
            	${planKeep.proDes}
            </div>
        	<div style="width:648px; padding: 0px 20px 10px 20px;">
            	<span class="black middle" style="line-height:150%;">${planKeep.proUseWay}</span>
            </div>
        </div>   
    	<!-- 项目基本信息 end -->    	
        <!-- 企业信息 start -->
        <div style="float:left;border:1px solid #e0e0e0; width:688px; margin:10px 5px 10px 0px;">
    		<div style="background-color:#F0F5F8; width:658px;  height:50px; padding:20px 0px 0px 30px;">
        	<span class="big black bold">风险控制</span></div>
        	<div class="ultable text" style="width:648px; height:auto; padding:30px 20px;">
            	${planKeep.proRiskCtr}
            </div>
        	
        </div>   
    	<!-- 企业信息 end -->
    </div>
    <!-- 左侧布局 end -->
    <!-- 右侧布局 start -->    
    <div style="float:left; width:305px; margin:10px 0px 10px 0px;">
    	<!--投资人列表 start -->
        	<div style="width:303px; border:1px solid #e0e0e0; overflow:hidden; margin:10px 0px 0px 0px;">
            	<div style="background-color:#F0F5F8; width:273px; height:50px; padding:20px 0px 0px 30px;">
        	<span class="big black bold">投标情况</span></div>
                <div class="investorlist" style="width:263px; margin:10px 20px 10px 20px; background-color:#fbfbfb; overflow:hidden;">
                	<ul style="width:263px;" id="userBidList">
                    	<div id="load"></div>
                    </ul>
                </div>
            </div>
    	<!--投资人列表 end -->
    </div>
    <!-- 右侧布局 end -->
  <!-- 详情页中部内容结束 --> 
</div>
<div style="height:4px; border-bottom:1px #f0f0f0 solid; margin:15px auto 0px auto;" title="横线"></div>
<!-- end main --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 

<!-- copyright -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</div>

 <#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl">
 <input type="hidden" id="bidId" value="${plan.bidId}">
 <input type="hidden" id="bidName" value="${plan.bidProName}">
</body>
</html>
