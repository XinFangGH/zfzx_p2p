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
</script>
<script type="text/javascript">	
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
<!--企业债权标-->
<div style="background:#eff3f3;width:100%;overflow:hidden;">
<div class="main2">
	<!--<div class="porduct">
    	<a href="#">产品详情</a>
    </div>-->
    <div class="inner">
    	<h2 class="title"><span class="pic"></span><span class="txt">${(plan.bidProName)![0..10]}</span>&nbsp;&nbsp;<#if plan.novice==1><font class="one">新</font></#if></h2>
    	<div class="inner-left">       	
            <div class="condition">
            	<dl class="didt">
	            	<dt>年利率</dt>
                	<dd><i>${(planPro.yearInterestRate)!}</i>% <#if plan.addRate!=0&&plan.addRate!=""><font color="#EA0000" size="5px;">+${plan.addRate}%</font>&nbsp;<font  class="two">息</font></#if></dd>
                </dl>
                <dl class="didt">
                	<dt>总额</dt>
                	<dd><i>${plan.bidMoney?string(',###.00')}</i>元</dd>
                    
                </dl>
                <dl class="didt didt-none">
                	<dt>还款期限</dt>
                	<dd><i>${plan.acctulData!}</i>个${plan.repaymentPeriod}</dd>
                   
                </dl>
            </div>
            <ul>
            	<li><em><img src="${base}/theme/proj_wenandai/images/huank_iocn.jpg" /></em>还款方式：${plan.theWayBack!}
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
                 <#--${(planKeep.plKeepGtorz.name)!}--></li>
                <#--<li class="none">月还款额（元）：${planPro.monthInterestRate?string(',###.00')}</li>-->
            </ul>
        </div>
        <!-- start 已满标-->
         <div class="inner-right1">
         	<i class="bg"></i>
        	<h3>回款中</h3>
            <div class="sumb">
            
            </div>
        </div>
        
      
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
                <h5>企业基本信息</h5>
               </div> 
             
               <dl class="contentleft2">
                <dt>企业名称:</dt>
                <dd>${(enterPrise.enterprisename)!}</dd>
               </dl>
               
                <dl class="contentleft2">
                <dt>所有制性质:</dt>
                <dd>${(enterPrise.ownership)!}</dd>
                </dl>
               
                 <dl class="contentleft2">
                <dt>注册资金:</dt>
                <dd>${(enterPrise.registermoney)!}万元</dd>
                </dl>
                <dl class="contentleft2">
                <dt>注册时间:</dt>
                <dd>${(enterPrise.opendate)!}</dd>
                </dl>
                 <dl class="contentleft2">
                <dt>经营所在地:</dt>
                <dd>${(enterPrise.managecityName)!}</dd>
                </dl>
                <dl class="contentleft2">
                <dt>行业:</dt>
                <dd>${(enterPrise.hangyeName)!}</dd>
                </dl>
                 <dl class="contentleft2">
               	<dt>营业范围:</dt>
                <dd>${planKeep.proBusinessScope!}</dd>
              </dl> 
                 <dl class="contentleft2">
               	<dt>经营描述:</dt>
                <dd>${planKeep.proBusinessStatus!}</dd>
              </dl>               
            </div>  
        </div> 
        <div class="content">    
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                <h5>项目信息</h5>
               </div> 
              
               <dl class="contentleft2">
               	<dt>项目说明:</dt>
                <dd>${planKeep.proDes}</dd>
                </dl>
                <dl class="contentleft2">
               	<dt>资金用途:</dt>
                <dd>${planKeep.proUseWay!}</dd>
                </dl>
                <dl class="contentleft2">
                <dt>还款来源:</dt>
                <dd>${planKeep.proPayMoneyWay}</dd>
              </dl> 
            </div>  
        </div>
        <div class="content">    
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                <h5>担保信息</h5>
               </div> 
              
               <dl class="contentleft2">
               	<dt>担保公司名称:</dt>
                <dd>${planKeep.guarantorsName}</dd>
                </dl>
                <dl class="contentleft2">
               	<dt>担保机构简介:</dt>
                <dd>${planKeep.guarantorsDes}</dd>
                </dl>
                <dl class="contentleft2">
                <dt>担保意见:</dt>
                <dd>${planKeep.guarantorsAdvise}</dd>
              </dl>               
            </div>  
        </div> 
        <div class="content">    
            <div class="contentleft">
              <div class="contentleft1">
              	<span class="bg"></span>
                <h5>风险控制</h5>
               </div> 
              
               <dl class="contentleft2">
               	<dt>${planKeep.proRiskCtr!}</dt>
   
               
              </dl>               
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
                        <#if (list_index<8)>
                        <tr>
                           <td height="40" align="center" bgcolor="#FAFBFD">${list_index+1}</td>
                           <td align="center" bgcolor="#FAFBFD">${list.investPersonName?substring(0,2)}****${list.investPersonName?substring(list.investPersonName?length-2)}</td>
                           <td align="center" bgcolor="#FAFBFD">${list.yearRate}%</td>
                           <td align="center" bgcolor="#FAFBFD">${list.buyMoney?string(',###.00')}</td>
                           <td align="center" bgcolor="#FAFBFD"><#if (list.buyDate==null)><#else>${list.buyDate?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                        </tr>
                        </#if>
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

 <input type="hidden" id="bidId" value="${plan.bidId}">
 <input type="hidden" id="bidName" value="${plan.bidProName}">
 <input type="hidden" id="hiddenMoney" value="${plan.afterMoney}"/>
 <input type="hidden" id="bidType" value="plan">
</body>
</html>
