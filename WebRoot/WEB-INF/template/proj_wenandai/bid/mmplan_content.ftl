<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 理财计划详情</title>

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/flb.css" />
<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
<script type="text/javascript">
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200 });		
	});
	window.onload=function() {
	$(".finlist .finlist_title li").each(function(index) { //带参数遍历各个选项卡
		$(this).click(function() { //注册每个选卡的单击事件
			$(".finlist .finlist_title li.active").removeClass("active"); //移除已选中的样式
			$(this).addClass("active"); //增加当前选中项的样式
			//显示选项卡对应的内容并隐藏未被选中的内容
			$(".finlist .finlist_hide ol:eq(" + index + ")").show()
			.siblings().hide();
		});
	});

}

</script>
</head>

 <input type="hidden" id="bidId" value="${plan.mmplanId}">
 <input type="hidden" id="bidName" value="${plan.mmName}">
 <input type="hidden" id="hiddenMoney" value="${plan.afterMoney}"/>
 <input type="hidden" id="bidType" value="plmm">
  <input type="hidden" id="planprogress" value="${plan.progress}"/>
    <input type="hidden" id="planafterTime" value="${plan.afterTime}"/>
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
<!-- 理财计划详情页 -->

<div style="width:100%;background:#eff3f3;overflow:hidden; ">
<div class="main">
	<div class="porduct">
    	<a href="#">产品详情</a>
    </div>
    <div class="inner">
    	<div class="inner-left" style="height:320px;">
        	<h2 class="title"><span class="pic"></span><span class="txt">${plan.manageMoneyTypeName}</span></h2>
            <div class="condition">
            	<dl class="didt">
                	<dt style="color:#555;">${plan.yeaRate}%-${plan.maxYearRate}%</dt>
                    <dd>预期年化收益率</dd>
                </dl>
                <dl class="didt1">
                	<dt><#if plan.sumMoney==0>0.00<#else><#if plan.sumMoney lt 1000>${plan.sumMoney}<#else>${plan.sumMoney?string(',###.00')}</#if></#if>元</dt>
                    <dd style="width:299px;">计划金额（元）</dd>
                </dl>
                <dl class="didt1 didt-none">
                	<dt>${plan.investlimit}</dt>
                    <dd style="line-height:26px;width:203px;">
                    <span>期限（月）</span>
              		</dd>

                </dl>
            </div>
            <div class="stat">
                <div class="stat-list1">
	           	    <span class="noat">起投金额：</span>
	            	<span class="nums" ><#if plan.startMoney==0>0.00<#else><#if plan.startMoneyy lt 1000>${plan.startMoney}<#else>${plan.startMoney?string(',###.00')}</#if></#if>元</span>
               	 </div>
               	  <div class="stat-list" >
	                <span class="noat">收益方式：</span>
		            <span class="nums" ><#if  plan.isOne==0>按期收息,到期还本<#else>一次性支付利息 </#if></span>
				</div>
               	 <div class="stat-list ">
	           	    <span class="noat">锁定截止日期：</span>
	            	<span class="nums">${plan.lockingEndDate}</span>
               	 </div>
               	  <div class="stat-list1" >
	           	    <span class="noat">投资上限：</span>
	            	<span class="nums" >${plan.limitMoney}元</span>
               	 </div>
               	 <div class="stat-list">
	           	    <span class="noat">计划到期日：</span>
	            	<span class="nums">${plan.endinInterestTime}</span>
               	 </div>
             	
              
                <div class="stat-list"> 
                	<span class="noat" style="width:65px;"><i class="img-bg"></i>还剩：</span>
                	<span class="nums" style="color:red;"><span id="day_show">0天</span>
							<span id="hour_show">0时</span>
							<span id="minute_show">0分</span>
							<span id="second_show">0秒</span></span>
                </div>
            </div>
             <div class="times">                  
	             <span>投资进度：</span>
	            <span  id='addProgressBar' class='progressBar'>${plan.progress}%</span>
             </div>
        </div>
          <div class="inner-right1" style="height:340px;">
        	<h3>投标中</h3>
            <div class="sumb"  >
            	<form id="bid_Form" style="height:218px;"action="${base}/creditFlow/financingAgency/bidingPlBidInfo.do" method="post">
            		<div style="width:230px; overflow:hidden; position: relative;">
					<input name="plBidInfo.bidId" type="hidden" id="plBidInfobidId">
					<input name="plBidInfo.bidName" type="hidden" id="plBidInfobidName">
					<input name="plBidInfo.userMoney" type="hidden" id="plBidInfouserMoney">
					<input name="plBidInfo.userName" type="hidden" id="plBidInfouserName">
					<input name="plBidInfo.userId" type="hidden" id="plBidInfouserId">
					<input name="formtoken" type="hidden" id="formtoken" value="">
					<input  type="hidden" id="upperMoney" value="">

                	<input type="text" style="width:219px;" id="signMoney" onblur="chkMoney('signMoney','currM');" class="good_input" placeholder="输入金额需为10元的整数倍"/>

                   <span style="color:#a0a0a0;display:inline-block; float:right; position: absolute; top:7px; right:8px;">元</span>
                	</div>
                    <ul>

                    <li style="height:14px; line-height:10px;"><span id="money_span"></span></li>
                    	<li>剩余金额：<input type="hidden" id="afterM" value="${plan.afterMoney}"/><span >${plan.afterMoney?string(',##0.00')}元</span></li>                
                    <#if Session.successHtml=='undefined'||Session.successHtml==null> <li>账户余额：登录可见</li><#else>
                        <li>账户余额：<input type="hidden" id="currM" value="${currM}">${currM}<span id="myMoney"></span></li> 
                     </#if>                      
                    	
                      </ul>
					     <div class="btn">
                     	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
                     	 	<a href="${base}/htmllogin.do?retUrl=" target="_self" id="bidLogin"> <input  type="button"   value="&nbsp;&nbsp;&nbsp;立即登录 &nbsp;&nbsp;" style="height:30px;"></input></a>
                     	  <#else>		
						        <#if plan.state==0>
				    					<input  type="button" value="&nbsp;&nbsp;未发布&nbsp;&nbsp;">
				    				<#elseif plan.state==1>
				    					<input  type="button" onClick="bidHandler()"  value="&nbsp;&nbsp;确定投资&nbsp;&nbsp;" >
				    				<#elseif plan.state==2 ||plan.state==5||plan.state==6>
				    					<input  type="button" value="&nbsp;&nbsp;已满标&nbsp;&nbsp;">
				    				<#elseif plan.state==3>
				    					<input  type="button" value="&nbsp;&nbsp;已流标&nbsp;&nbsp;">
				    				<#elseif plan.state==4>
				    					<input  type="button" value="&nbsp;&nbsp;已过期&nbsp;&nbsp;">
				    			
				    				<#elseif plan.state==7>
				    					<input  type="button" value="&nbsp;&nbsp;还款中&nbsp;&nbsp;">
				    				<#elseif plan.state==10>
				    					<input  type="button" value="&nbsp;&nbsp;已完成&nbsp;&nbsp;">
				    				<#else>
				    					
				    			</#if>
				         
					    <input type="hidden" value=${plan.limitMoney} id="maxMoney" />
					    	 	<a href="javascript:void(0)" onClick="bidAll(${plan.afterMoney},${plan.startMoney},${plan.riseMoney})"  style=" padding:5px 10px; border:1px solid #a0a0a0; background:#fff;" >全投</a>
					    	 	<a href="${base}/financePurchase/rechargeFinancePurchase.do">充值</a>
                   		</div>
                
                       </#if>
                </form>
            </div>
             <div class="inner-right4"  style="display:none;">
	        	<h3>收益中</h3>
		            <div class="sumb">
		            		
		                    <ul>
		                    	<li>已获收益：<span >1000元</span></li>                
                        		<li>到期日：<span >1111</span></li>   
		                    </ul>
		                
		            </div>
	            </div>
            <div class="inner-right1" style="height:340px;display:none;">
	        	<h3>已退出</h3>
	            <div class="sumb">
	            	<ul>
	                    	<li>退出时间：<span >1111</span></li>  
	                    	<li>累积收益：<span >1000元</span></li>                
                        	 
	                  </ul>
	            </div>
           </div>
            
            
            
            </div>
    </div>   
    <div class="finlist">
       <ul class="finlist_title">
          <li class="active">计划介绍</li>          
          <li>加入记录</li>
       </ul>
       <div class="finlist_hide">
         <ol style="display:block">     
       	 <div class="content">
       	 	<div class="cont-list">
	       	 	<span class="name">名称：</span>
	       	 	<span class="txts">${plan.mmName}</span>
       	 	</div> 
       	 	<div class="cont-list">
	       	 	<span class="name">介绍：</span>
	       	 	<span class="txts">${plan.mmName}</span>
       	 	</div> 
       	 	<div class="cont-list">
	       	 	<span class="name">投资范围：</span>
	       	 	<span class="txts">${plan.investScope}</span>
       	 	</div> 
       	 	<div class="cont-list">
	       	 	<span class="name">收益方式：</span>
	       	 	<span class="txts"><#if  plan.isOne==0>按期收息,到期还本<#else>一次性支付利息 </#if></span>
       	 	</div> 
       	 	<div class="cont-list">
	       	 	<span class="name">锁定期：</span>
	       	 	<span class="txts">${plan.lockingLimit}月</span>
       	 	</div>
       	 	<div class="cont-list">
	       	 	<span class="name">锁定截止日：</span>
	       	 	<span class="txts">${plan.lockingEndDate}</span>
       	 	</div>
       	 	<div class="cont-list">
	       	 	<span class="name">计划到期日：</span>
	       	 	<span class="txts">${plan.endinInterestTime}</span>
       	 	</div>
       	 	<div class="cont-list">
	       	 	<span class="name">投资起点金额：</span>
	       	 	<span class="txts">${plan.startMoney}元</span>
       	 	</div>
       	 	
       	 	<div class="cont-list">
	       	 	<span class="name">递增金额：</span>
	       	 	<span class="txts">${plan.riseMoney}元</span>
       	 	</div>
       	 	<div class="cont-list">
	       	 	<span class="name">投资上限：</span>
	       	 	<span class="txts">${plan.limitMoney}</span>
       	 	</div>
       	 	<div class="cont-list">
	       	 	<span class="name">购买开放时间：</span>
	       	 	<span class="txts">${plan.buyStartTime}</span>
       	 	</div>
       	 	<div class="cont-list">
	       	 	<span class="name">购买结束时间：</span>
	       	 	<span class="txts">${plan.buyEndTime}</span>
       	 	</div>
       	 	<div class="cont-list">
	       	 	<span class="name">到期退出方式：</span>
	       	 	<span class="txts">系统将通过债权转让自动完成退出，您所持债权提出的具体时间，视债权到期时间而定。</span>
       	 	</div>
       	 	<div class="cont-list">
	       	 	<span class="name">提前退出方式：</span>
	       	 	<span class="txts">锁定期内不支持提前退出，锁定期后计划到期日前可提前退出，需支付提前赎回费用，收取投资总金额的2%。</span>
       	 	</div>
			<div class="cont-list">
	       	 	<span class="name">保障方式 ：</span>
	       	 	<span class="txts">100%本金保障计划</span>
       	 	</div>
	 	      
         </div> 
       </ol>
   <ol>
       	 <div class="content">    
            <div class="tab">
            	<table width="900" border="0" align="center" cellpadding="0" cellspacing="0">
                       <tr>
                        <th width="150" height="40" align="center" bgcolor="#47B2D6">序号</th>
                        <th width="150" align="center" bgcolor="#47B2D6">投资人</th>
                        <th width="150" align="center" bgcolor="#47B2D6">加入金额</th>
                        <th width="150" align="center" bgcolor="#47B2D6">加入时间</th>
                      </tr>
                       <#list buyInfoList as list>
                        <tr>
                        <td height="40" align="center" bgcolor="#FAFBFD">1</td>
                        <td align="center" bgcolor="#FAFBFD">${list.investPersonName}</td>
                        <td align="center" bgcolor="#FAFBFD"> ${list.buyMoney}元</td>
                        <td align="center" bgcolor="#FAFBFD"> ${list.buyDatetime}</td>
                        </tr>
                       </#list>
                     
                    
                </table>
            </div> 
         </div> 
       </ol>
        
	</div>
</div>
</div>
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

</body>
</html>
