<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 招标信息详情</title>

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>

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
	
	$(function(){
		check('${plan.bidId}','${plan.afterMoney}','${planPro.yearInterestRate}');
		$("#to").click(function(){
			var myMoney = $("#myMoney").text();
			var afterMoney = $("#afterMoney").text();
			var subMyMoney = myMoney.substring(0,myMoney.length-1);
			var subAfterMoney = afterMoney.substring(0,afterMoney.length-1);
			var strAfterMoney = "";
			var strMyMoney ="";
			for(var i=0;i<subAfterMoney.length;i++){
				if(","!=subAfterMoney[i]){
					strAfterMoney+= subAfterMoney[i];
				}
			}
			for(var i=0;i<subMyMoney.length;i++){
				if(","!=subMyMoney[i]){
					strMyMoney+= subMyMoney[i];
				}
			}
			
			if(strMyMoney > strAfterMoney){
				$("#signMoney").val(strAfterMoney);
			}else if(strMyMoney <= strAfterMoney){
				$("#signMoney").val(strMyMoney);
			}else{
				$("#signMoney").val(0);
			}
			
		});
	});
}

</script><script type="text/javascript">	
$(function(){
		//图标下拉框  提示框
	var content = $(".sidnext").html();	
	$('.icon-one').pt({
		position: 'b',
		width:300,
		content: content
	});	 
	var content = $(".sidnext1").html();	
	$('.icon-two').pt({
		position: 'b',
		width:300,
		content: content
	});	  
	
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
<!-- 个人债权标 -->

<div style="width:100%;background:#eff3f3; overflow:hidden;">
<div class="main2">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/share.ftl">
	<!--<div class="porduct">
    	<a href="#">产品详情</a>
    </div>-->
    <div class="inner">
    	<h2 class="title"><span class="pic"></span><span class="txt">${(plan.bidProName)![0..10]}</span>&nbsp;&nbsp;<#if plan.novice==1><font class="font-one">新</font></#if></h2>
    	<div class="inner-left">        	
            <div class="condition">
            	<dl class="didt">
            	<dt>年利率</dt>
                	<dd><i>${(planPro.yearInterestRate)!}</i>% <#if plan.addRate!=0&&plan.addRate!=""><font color="#EA0000" size="5px;">+${plan.addRate}%</font>&nbsp;<font class="font-two">息</font></#if></dd>
                </dl>
                <dl class="didt">
                	<dt>总额</dt>
                	<dd><i>${plan.bidMoney?string(',###.00')}</i>元</dd>
                   
                </dl>
                <dl class="didt didt-none">
                	<dt>还款期限</dt>
                	<dd><i>${planPro.loanLife!}</i>个${plan.repaymentPeriod}</dd>                    
                </dl>
            </div>
            <ul>
            	<li><em><img src="${base}/theme/proj_wenandai/images/huank_iocn.jpg" />还款方式：${plan.theWayBack!}
            	<a href="javascript:void(0)"><span style="display:inline-block;" class="icon-one">[?]</span></a>
                 <#if plan.payIntersetWay=='1'><div class="sidnext" style="display:none;">等额本息还款法是在还款期内，每月偿还同等数额的贷款(包括本金和利息)。借款人每月还款额中的本金比重逐月递增、利息比重逐月递减。</div>
                 </#if>
                 <#if plan.payIntersetWay=='2'>
                 <div class="sidnext" style="display:none;">等额本金是在还款期内把贷款数总额等分，每月偿还同等数额的本金和剩余贷款在该月所产生的利息，每月的还款本金额固定，而利息越来越少</div>
                 </#if>
                 
                 <#if plan.payIntersetWay=='3'>
                 <div class="sidnext" style="display:none;">等本等息还款法是在还款期内，每个月平均偿还本金和利息，利息一直按总本金计算</div>
                 </#if>
                  <#if plan.payIntersetWay=='4'>
                  <div class="sidnext" style="display:none;">先息后本还款法是在还款期内，每月只偿还利息，在最后一期偿还利息的同时偿还全部本金。</div>
                </#if>
                </li>
                <!--<li><em><img src="${base}/theme/proj_wenandai/images/baoz_icon.jpg" />保障方式：本金保障-->
                <a href="${base}/html/secureSingle.do?toWhere=projectDesc#projectDesc"><!--<span style="display:inline-block;" >[?]</span>--></a>
                </li>
            </ul>
        </div>
        <!-- start 已满标-->
         <div class="inner-right1">
        	<h3>还款中</h3>
            <div class="sumb">
            	
               
            </div>
        </div>
        <!--end 已满标-->
        <!-- start还款中-->
        
    </div>
    <div class="finlist">
       <ul class="finlist_title">
          <li class="active">标的详情</li>          
          <li>投标记录</li>
       </ul>
       <div class="finlist_hide">
         <ol style="display:block">
          <div class="content">    
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                <h5>基本信息</h5>
               </div> 
             <div style="float:left; width:350px; padding: 10px 0 0 0px;">
              <dl class="contentleft2">
              	
                <dt class="dt1">用户名：</dt>
                <dd>${planPro.persionName?substring(0,1)}****${planPro.persionName?substring(planPro.persionName?length-1)}</dd>
             </dl>
              <dl class="contentleft2">
                <dt class="dt1">年　龄：</dt>
                <dd>${planPro.age!}</dd>
             </dl>
             <dl class="contentleft2">
                <dt class="dt1">学　历：</dt>
                <dd>${planPro.education!}</dd>
             </dl>
             <dl class="contentleft2">
                <dt class="dt1">工作单位：</dt>
                <dd>${planPro.currentcompany!}</dd>
             </dl>
             <dl class="contentleft2">
                <#--<dt class="dt1">婚　姻：</dt>
                <dd>${planPro.marriage!}</dd>-->
                <dt class="dt1">月收入：</dt>
                <dd><#if planPro.jobincome==0>未提供<#else>${planPro.jobincome!}元</#if></dd>
              </dl>
              </div>
              <div style="float:left; width:350px;padding: 10px 0 0 100px;">
	             <dl class="contentleft2">
	             	<dt class="dt2">公司行业：</dt>
		            <dd>
		            <#if planPro.companyIndustry!="null">${planPro.companyIndustry!}<#else>未填</#if>
		            </dd>
	             </dl>
				  <dl class="contentleft2">
		                <dt  class="dt2">公司规模：</dt>
		                <dd>${planPro.companyScale!}人</dd>
	               </dl>
	                <dl class="contentleft2">
		                <dt  class="dt2">岗位职位：</dt>
		                <dd>${planPro.position!}</dd>
	                </dl>
	                <dl class="contentleft2">
		                <dt  class="dt2">工作城市：</dt>
		                <dd>${planPro.workCity!}</dd>
	                </dl>
	                <dl class="contentleft2">
		                <dt  class="dt2">工作时间：</dt>
		                <dd>${planPro.workTime!}</dd>
	                </dl> 
              </div>                           
            </div>  
        </div> 
        
        <div class="content">    
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                  <h5>信用评级：
                  <#if planKeep??>
                  	<span class="<#if planKeep.plKeepCreditlevel.name??>
					<#if planKeep.plKeepCreditlevel.name=="AA">icon1
					<#elseif planKeep.plKeepCreditlevel.name=="HR">icon7
					<#elseif planKeep.plKeepCreditlevel.name=="A">icon2
					<#elseif planKeep.plKeepCreditlevel.name=="B">icon3
					<#elseif planKeep.plKeepCreditlevel.name=="C">icon4
					<#elseif planKeep.plKeepCreditlevel.name=="D">icon5
					<#else>icon6
					</#if>
					<#else>icon2</#if>">${(planKeep.plKeepCreditlevel.name)!}</span>
                  </#if>
                  </h5>
               </div> 
            </div>  
        </div> 
        <div class="content">    
            <div class="contentleft">
               <div class="tab tab1">
            	<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
                       <tr>                        
                        <th width="150" height="40"align="center" bgcolor="#47B2D6">审核项目</th>
                        <th width="150" align="center" bgcolor="#47B2D6">状态</th>
                        <th width="150" align="center" bgcolor="#47B2D6">通过日期</th>
                      </tr>
                      <#list listMaterials as list>
                      <tr>
                        <td align="center" height="40" bgcolor="#FAFBFD">${list.materialsName}</td>
                        <td align="center" bgcolor="#FAFBFD">通过</td>
                        <td align="center" bgcolor="#FAFBFD"><#if list.createTime==null>-- --<#else>${list.createTime!}</#if></td>
                      </tr>
                      </#list>
                      
                </table>
            </div>                
            </div>  
        </div>  
        <div class="content">    
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                <h5>项目信息</h5>
               </div> 
              
                <dl class="contentleft3 contentleft4">
               	<dt>项目描述：</dt>
                <dd><#if planKeep??>${planKeep.proDes}</#if></dd>
                </dl>
                 <dl class="contentleft3 contentleft4">
               	<dt>资金用途：</dt>
                <dd><#if planKeep??>${planKeep.proUseWay!}</#if></dd>
                </dl>
                
                <dl class="contentleft3 contentleft4">
                <dt>还款来源：</dt>
                <dd><#if planKeep??>${planKeep.proPayMoneyWay!}</#if></dd>
              </dl> 
            </div>  
        </div> 
        <div class="content">    
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                <h5>风险控制</h5>
               </div> 
             	<div class="txt"><#if planKeep??>${planKeep.proRiskCtr!}</#if></div>             
            </div>  
        </div> 
       </ol>
       	
       	<ol>
       	 <div class="content">    
       	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
             <#else> 
          <h2 class="title"><span>加入人次${plan.persionNum}人</span> 投标总额${(plan.afterMoneyTotal)?string(',##0.##')}元</h2>
            <div class="tab">
            	<table width="800" border="0" class="tab_css_plan" align="center" cellpadding="0" cellspacing="0">
                       <tr>
                        <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                        <th width="150" align="center" bgcolor="#47B2D6">投标人</th>
                        <th width="50"  align="center" bgcolor="#47B2D6">年利率</th>
                        <th width="150" align="center" bgcolor="#47B2D6">投标金额</th>
                        <th width="150" align="center" bgcolor="#47B2D6">投标时间</th>
                      </tr>
                       <#if (childOrList!=null)>
                        <#list childOrList as list>
                        <tr>
                           <td height="40" align="center" bgcolor="#FAFBFD">${list_index+1}</td>
                           <td align="center" bgcolor="#FAFBFD">${list.investPersonName?substring(0,2)}****${list.investPersonName?substring(list.investPersonName?length-2)}</td>
                           <td align="center" bgcolor="#FAFBFD">${list.yearRate}%</td>
                           <td align="center" bgcolor="#FAFBFD">${list.buyMoney?string(',###.00')}</td>
                           <td align="center" bgcolor="#FAFBFD"><#if (list.buyDate==null)><#else>${list.buyDate?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                        </tr>
                        </#list>
                        </#if>
                </table>
            </div>  
            </#if>
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
<#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl">
</div>

 <input type="hidden" id="bidId" value="${plan.bidId}">
 <input type="hidden" id="bidName" value="${plan.bidProName}">
 <input type="hidden" id="hiddenMoney" value="${plan.afterMoney}"/>
</body>
</html>
