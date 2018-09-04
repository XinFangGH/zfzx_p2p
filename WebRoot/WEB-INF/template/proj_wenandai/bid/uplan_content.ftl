<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<title>升升投 - U计划</title>
    <meta name="description" content="${systemConfig.metaTitle} - U计划,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - U计划,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/xy-lc.css">
<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
<script type="text/javascript">
$(function(){
	check('${plan.mmplanId!}','${plan.afterMoney!}','${(plan.yeaRate)!}',false);
	$('.tab-title ul li').click(function(){ 
		$(this).addClass("selected").siblings().removeClass('selected');//removeClass就是删除当前其他类；只有当前对象有addClass("selected")；siblings()意思就是当前对象的同级元素，removeClass就是删除； 
		$("#tab-content .tb-c").hide().eq($('.tab-title ul li').index(this)).show(); 
    });
})
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
<input value="${(bpCustMember.grade)??}" type="hidden" id="grade">
<input type="hidden" id="bidId" value="${plan.mmplanId}">
<input type="hidden" id="bidName" value="${plan.mmName}">
<input type="hidden" id="hiddenMoney" value="${plan.afterMoney}"/>
<input type="hidden" id="bidType" value="uplmm">
<input type="hidden" id="planprogress" value="${plan.progress}"/>
<input value="${plan.maxCouponMoney}" type="hidden" id="planMaxCouponMoney">
<input type="hidden" id="planafterTime" value="${plan.afterTime}"/>
<style type="text/css">  
   /*模块拖拽*/  
   .xy-lc-box{position:absolute;width:100px;height:100px;text-align:center;padding:5px;top:20%;left:40%;}
</style> 
<body style="background:#eff0f2; ">
<div>
	<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
	<div style="width:100%;background:#eff3f3;overflow:hidden; ">
		<div class="main">
			<#include "/WEB-INF/template/${systemConfig.theme}/layout/share.ftl">
			<!--<div class="porduct">
    			<a href="#">产品详情</a>
    		</div>-->
    	<div id="xy-lc-wrap">
        <div class="xy-lc-content">
            <div class="xy-lc-install">
                <div class="xy-lc-custom">
                    <h1>${plan.mmName}&nbsp;&nbsp;&nbsp;&nbsp;<span>[${plan.manageMoneyTypeName}]</span></h1>
                    <form id="bid_Form" style="height:218px;"action="${base}/creditFlow/financingAgency/bidingPlBidInfo.do" method="post">
                    <input name="plBidInfo.bidId" type="hidden" id="plBidInfobidId">
					<input name="plBidInfo.bidName" type="hidden" id="plBidInfobidName">
					<input name="plBidInfo.userMoney" type="hidden" id="plBidInfouserMoney">
					<input name="plBidInfo.userName" type="hidden" id="plBidInfouserName">
					<input name="investType" type="hidden" id="investType">
					<input name="plBidInfo.userId" type="hidden" id="plBidInfouserId">
					<input name="type" type="hidden" id="type" value="${plan.yeaRate}">
					<input type="hidden" value="${plan.manageMoneyTypeId}" id="typeid" name="typeid">
					<input  type="hidden" id="upperMoney" value="">
					<input name="plBidInfo.couponId" type="hidden" id="couponId" value="">
                    <ul class="xy-lc">
                        <!--<li><p class="xy-lc-key" style="color:#fd7754;">预期年化收益率</p><p class="xy-lc-num" style="color:#fd7754;"><span style="color:#fd7754;font-size:42px;">${plan.yeaRate}</span>%</p></li> -->
                        <li><p class="xy-lc-key">预期年化收益率</p><p class="xy-lc-num">${plan.yeaRate}<span style="font-size:14px; color:#666;">%</span></p></li>
                        <li><p class="xy-lc-key">计划金额</p><p class="xy-lc-num"><#if plan.sumMoney==0>0.00<#else><#if plan.sumMoney lt 1000>${plan.sumMoney}<#else>${plan.sumMoney?string(',###.00')}</#if></#if><span style="font-size:14px; color:#666;">元</span></p></li>
                        <li><p class="xy-lc-key">投资期限</p><p class="xy-lc-num">${plan.investlimit}<span style="font-size:14px; color:#666;">个月</span></p></li>
                    </ul>
                    <div class="xy-lc-detail xy-lc">
                     <div class="xy-lc-item-line"></div>
                        <div class="xy-lc-detail-item">
                        	<p class="xy-lc-item-content"><img class="fl" src="${base}/theme/proj_wenandai/images/ty-icon1.png" alt="">起投金额：<#if plan.startMoney==0>0.00<#else><#if plan.startMoneyy lt 1000>${plan.startMoney}<#else>${plan.startMoney?string(',###.00')}</#if></#if>元</p>
                        </div>
                        <div class="xy-lc-detail-item">
                            <p class="xy-lc-item-content"><img class="fl" src="${base}/theme/proj_wenandai/images/ty-icon2.png" alt="">上限金额：${plan.limitMoney}元</p>
                        </div>
                        <div class="xy-lc-detail-item">
                            <p class="xy-lc-item-content"><img class="fl" src="${base}/theme/proj_wenandai/images/ty-icon3.png" alt="">投资期限：${plan.investlimit}个月</p>
                        </div>
                        <div class="xy-lc-detail-item">
                            <p class="xy-lc-item-content"><img class="fl" src="${base}/theme/proj_wenandai/images/ty-icon4.png" alt="">还款方式：<#if  plan.isOne==0>按期收息,到期还本<#else>一次性支付利息 </#if></p>
                        </div>
                         <#if plan.coupon==1>
                        <div class="xy-lc-detail-item">
                            <p class="xy-lc-item-content">面值折现比：</p><p>${plan.returnRatio}%<span style="display:inline-block;" class="icon-one">[?]</span></p>
                            <div class="sidnext" style="display:none;">
				        	<p align="left"><b>有效面值：</b>面值*折现比例</p>
				        	<p align="left"><b>返利方式：</b>
				        	<#if plan.rebateWay==1>
				        	立返(放款时一次性返利)，
				        	</p>	
				        		<p align="left">
				        		<b>返利类型：</b>
				            	<#if plan.rebateType==1>
				            		返现。立返=面值*折现比例</#if>
				            	<#if plan.rebateType==2>
				            	返息。立返=以面值*折现比例 按标的计息方式生成收息表(没有本金)进行加合利息</#if>
				            	<#if plan.rebateType==3>
				            	返息现。立返=以面值*折现比例 按标的计息方式生成收息表(有本金)进行加合本息</#if>
				            	<#if plan.rebateType==4>
				            	加息。利率为加息利率,投资金额按照利息计算方式（只生成利息）
				            	</#if>
				            	</p>
				        	</#if>
				        	<#if plan.rebateWay==2>
				        	随期(随期进行返利)
				        	</p><p align="left">	
				        	<b>返利类型：</b>
				            	<#if plan.rebateType==1>
				            		返现。随期=当期本金*【面值*折现比例/实际投资额】</#if>
				            	<#if plan.rebateType==2>
				            	返息。随期=以面值*折现比例按标的计息方式生成收息表(没有本金)</#if>
				            	<#if plan.rebateType==3>
				            	返息现。随期=以面值*折现比例按标的计息方式生成收息表(有本金) 随息发放本息</#if>
				            	<#if plan.rebateType==4>
				            	加息。利率为加息利率,投资金额按照利息计算方式（只生成利息）
				            	</#if>
				            	</p>
				        	</#if>
				        	<#if plan.rebateWay==3>
				        	到期(贷款结束时返利)，
				        	</p>	
				        		<p align="left">
				        		<b>返利类型：</b>
				            	<#if plan.rebateType==1>
				            		返现。立返=面值*折现比例</#if>
				            	<#if plan.rebateType==2>
				            	返息。立返=以面值*折现比例 按标的计息方式生成收息表(没有本金)进行加合利息</#if>
				            	<#if plan.rebateType==3>
				            	返息现。立返=以面值*折现比例 按标的计息方式生成收息表(有本金)进行加合本息</#if>
				            	<#if plan.rebateType==4>
				            	加息。利率为加息利率,投资金额按照利息计算方式（只生成利息）进行加合利息
				            	</#if>
				            	</p>
				        	</#if>
				        	</div>
                        </div>
                        </#if>
                         <#if plan.raiseRate gt 0>
                         <div class="xy-lc-detail-item">
                            <p class="xy-lc-item-content">募集期利率：</p>${plan.raiseRate}%/年
                            	<span style="display:inline-block;" class="icon-two">[?]</span>
                            </p>                       	
               				 <div class="sidnext1" style="display:none;">
               				    <p align="left"><b>募集天数：</b>起息日-投资日（投资当天起息不计算）</p>
                    			<p align="left"><b>计算公式：</b>投资金额*日化募集期利率*募集天数</p>
               				 </div>
                        </div>
                        </#if>
                    </div>
                    </form>
                </div>             
            </div>
            <div class="xy-lc-apply">
            	<i class="bg"></i>
                <div class="xy-lc-apply-hd">
                    <h2>账户可用余额(元)
                     <a class="icon-recharge" href="${base}/financePurchase/rechargeFinancePurchase.do" target="_blank">充值</a> 
                    </h2>
                    <span id="mySpan" style="display:none"></span>
                    <strong class="amount">
					<#if Session.successHtml=='undefined'||Session.successHtml==null> 
					<a href="${base}/htmllogin.do">请先登录</a>
					<#else>
	               	<input type="hidden" id="currM" value="${currM}"/><span style="color:#8E8E8E">${currM?string(',##0.00')}</span><span id="myMoney" hidden="true"></span> 
	                </#if> 	               
					</strong>
                </div>
                <div class="xy-lc-apply-bd">
                    <h2>剩余可购买金额(元)：<span><input type="hidden" id="afterM" value="${plan.afterMoney}"/><#if plan.mmplanId=='2'>83,000.00<#elseif plan.mmplanId=='3'>78,000.00<#elseif plan.mmplanId=='4'>80,500.00<#elseif plan.mmplanId=='6'>69,000.00<#elseif plan.mmplanId=='7'>54,000.00<#else>${plan.afterMoney?string(',##0.00')}</#if></span></h2>
                    <div class="xy-ui-form-item">
                        <input id="signMoney" onkeyup="lx();" onblur="chkMoney('signMoney','currM');" class="xy-lc-input" placeholder="<#if plan.startMoney==0>0.00<#else><#if plan.startMoneyy lt 1000>${plan.startMoney}<#else>${plan.startMoney?string(',###.00')}</#if></#if>元起"><span class="xy-ft-unit">元</span>
                        <div class="xy-ui-form-item-explain" style="display: none"></div>
                    </div>
                    <p class="xy-lc-amount-container">预估到期收益:<span class="xy-lc-amount-value"></span>元</p>
                    <p class="xy-lc-amount-container-explain">到期后自动转回您的账户余额</p>
                    <p class="xy-lc-amount_box"><span style="margin-right:70px;"><input type="radio" name="investType" value="1"/> 收益再投资</span><span><input type="radio" checked="checked" name="investType" value="2"/> 提取主账户</span></p>
                    <div class="xy-lc-apply-sub">
                        <input type="hidden" id="J_pfAmptIsEdit" value="false">
                        <#if plan.state=='1'>
                         	
                         	<#if plan.startTime?date("yyyy-MM-dd HH:mm:ss") lt plan.nowDate?date("yyyy-MM-dd HH:mm:ss") >
    				            <a href="javascript:void(0);" class="xy-lc-apply-btn" onClick="show();">立即购买</a>
                            <#else>
                               <a href="javascript:void(0);" class="xy-lc-apply-btn" >  预售中</a>
                            </#if>
                        <#elseif plan.state=='2'>
                         	<a href="javascript:void(0);" class="xy-lc-apply-btn" style="background-color:#92a1b0; box-shadow: 0 2px 0 #92a1b0;">已售罄</a>
                        <#elseif plan.state=='4'>
                         	<a href="javascript:void(0);" class="xy-lc-apply-btn" >已过期</a>
                        <#elseif plan.state=='7'>
                         	<a href="javascript:void(0);" class="xy-lc-apply-btn" >回款中</a>
                        <#elseif plan.state=='10'>
                         	<a href="javascript:void(0);" class="xy-lc-apply-btn" style="background-color:#48cfae; box-shadow: 0 2px 0 #48cfae;">已完成</a>
                        <#elseif plan.state=='-1'||plan.state=='-2'>
                         	<a href="javascript:void(0);" class="xy-lc-apply-btn" >已关闭</a>
                         </#if>
                    </div>
                </div>
            </div>
        </div>
<!--start-->
<div class="frame-tabs">
        	<div class="tab_left">
                <div class="tab-title">
                    <ul>
                         <li class="selected"><a>产品介绍</a></li>
                         <li><a>加入记录<em>（最近${buyInfoList?size!}条）</em></a></li>
                         <li><a>项目进程</a></li>
                         <li><a>债权信息</a></li>
                    </ul>
                </div>
                <div class="xy-lc-match-count">
			         <!--<a>预约人次<i class="xy-lc-match-count-content">${(listSchedules?size)!}</i></a>-->
			    </div> 
                 <div id="tab-content">	
                    <div class="tb-c" >
                        <div class="new">           
                                <ul class="xy-intro-list">
					                <li class="xy-intro-row">
					                    <span class="xy-intro-label"><i class="xy-icon-origin"></i>名称</span>
					                    <p>${plan.mmName}</p>
					                </li>
					                <li class="xy-intro-row">
					                <span class="xy-intro-label">招标说明</span>
					                    <p>${plan.bidRemark}</p>
					                </li>
					                <li class="xy-intro-row">
					                    <span class="xy-intro-label">投资范围</span>
					                   <p>${plan.investScope} &nbsp;</p>
					                </li>
					                <li class="xy-intro-row">
					                    <span class="xy-intro-label">收益方式</span>	
					                    <p><#if  plan.isOne==0>按期收息,到期还本<#else>一次性支付利息 </#if></p>
					                   <!--<p> 收益返还，提至账户</p>-->
					                </li>
					                <li class="xy-intro-row">
					                    <span class="xy-intro-label">锁定期</span>
					                    <p>${plan.lockingLimit}&nbsp;月</p>
					                </li>
					                <li class="xy-intro-row">
					                    <span class="xy-intro-label">投资起投金额</span>
					                    <p> ${plan.startMoney}&nbsp;元起投</p>
					                </li>
					                <li class="xy-intro-row">
					                    <span class="xy-intro-label">加入费率</span>
					                    <p><#if plan.joinRate gt 0>${plan.joinRate}%<#else>0</#if></p>
					                </li>
					                 <li class="xy-intro-row">
					                    <span class="xy-intro-label">提前退出费率</span>
					                    <p><#if plan.earlierOutRate gt 0>${plan.earlierOutRate}%<#else>0</#if></p>
					                </li>
					                <li class="xy-intro-row">
					                    <span class="xy-intro-label">起息模型</span>
					                    <p><#if plan.startinInterestCondition==1>投标日起息
					                       <#elseif plan.startinInterestCondition==2>投标日+1起息
					                       <#elseif plan.startinInterestCondition==3>满标日起息
					                       <#elseif plan.startinInterestCondition==3>招标截止日起息
					                       <#else>满标日起息
					                       </#if>
					                    </p>
					                </li>
					                <li class="xy-intro-row">
					                    <span class="xy-intro-label">购买开放时间</span>
					                    <p>${plan.buyStartTime?string("yyyy-MM-dd HH:mm")}</p>
					                </li>
					                <li class="xy-intro-row">
					                    <span class="xy-intro-label">购买截止时间</span>
					                    <p> ${plan.buyEndTime?string("yyyy-MM-dd HH:mm")}</p>
					                </li>
					                 <li class="xy-intro-row">
					                    <span class="xy-intro-label">起息时间</span>
					                    <p> <#if plan.startinInterestTime==null||plan.startinInterestTime=="">未起息<#else>${plan.startinInterestTime?string("yyyy-MM-dd HH:mm")}</#if>&nbsp;</p>
					                </li>
					                <li class="xy-intro-row">
					                    <span class="xy-intro-label">到期赎回方式</span>
					                    <p>${plan.expireRedemptionWay}</p>
					                </li>
					                <li class="xy-intro-row">
					                    <span class="xy-intro-label">是否支持提前退出</span>
					                    <p><#if (plan.lockingLimit<plan.investlimit)>支持<#else>不支持</#if></p>
					                </li>
					                 <li class="xy-intro-row">
					                    <span class="xy-intro-label">保障方式 </span>
					                    <p>${plan.guaranteeWay}</p>
					                </li>
					                <li class="xy-intro-row">
					                    <span class="xy-intro-label">服务协议 </span>
					                    <p><a class="btn1" target="_blank" href="${base}/creditFlow/financingAgency/planContractPlManageMoneyPlan.do" class="btn2"  >点击查看</a></p>
					                </li>
								</ul>
           				 </div>                     
                 </div>
                  <div class="tb-c none" >
                        <div class="new">
                           <table class="xy-lc-match-table">			                 
			                        <tr>
			                            <th>序号</th>
			                            <th>理财人</th>
			                            <th>加入金额</th>
			                            <th>加入时间</th>
			                        </tr>
			                        <#list buyInfoList as list1>
			                        <tr>
			                           <td >${list1_index+1}</td>
				                       <td >${list1.investPersonName?substring(0,1)}*****${list1.investPersonName?substring(list1.investPersonName?length-1)}</td>         <#--[0]  ${list.investPersonName?substring(list.investPersonName?length-1)}-->                            
                                        <td >${list1.buyMoney}</td>
                                       <td ><#if list1.buyDatetime!="">${list1.buyDatetime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                                     </tr>  
			                       </#list>
			                </table>
                        </div>                     
                 </div>
                  <div class="tb-c none" >
                    <div class="new">                     
			        	<ul class="mmplan_box">
			        		<li class="mmplan_list">
			        			<div class="mmplan_pic"><img src="${base}/theme/${systemConfig.theme}/images/mmplan_pic1.png" ></div>
			        			<p class="tit">发布时间</p>
			        			<p class="cont">${plan.buyStartTime?string("yyyy-MM-dd HH:mm")}</p>
			        		</li>
			        		<li class="mmplan_line">
			        			<div class=""></div>
			        		</li>
			        		<li class="mmplan_list">
			        			<div class="mmplan_pic"><img src="${base}/theme/${systemConfig.theme}/images/mmplan_pic2.png" ></div>
			        			<p class="tit">计划金额</p>
			        			<p class="cont"><em><#if plan.sumMoney==0>0.00<#else><#if plan.sumMoney lt 1000>${plan.sumMoney}<#else>${plan.sumMoney?string(',###.00')}</#if></#if></em>元</p>
			        		</li>
			        		<li class="mmplan_line">
			        			<div class=""></div>
			        		</li>
			        		<li class="mmplan_list">
			        			<div class="mmplan_pic"><img src="${base}/theme/${systemConfig.theme}/images/mmplan_pic3.png" ></div>
			        			<p class="tit">加入人数</p>
			        			<p class="cont"><em>${buyInfoList?size!}</em>人</p>
			        		</li>
			        		<li class="mmplan_line">
			        			<div class=""></div>
			        		</li>
			        		<li class="mmplan_list" style="margin-right:0;">
			        			<div class="mmplan_pic"><img src="${base}/theme/${systemConfig.theme}/images/mmplan_pic4.png" ></div>
			        			<p class="tit">为用户赚取</p>
			        			<p class="cont"><em><#if curentInte==0>0.00<#else><#if curentInte lt 1000>${curentInte}<#else>${curentInte?string(',###.00')}</#if></#if></em>元</p>
			        		</li>
			        	</ul>
					</div>                     
                 </div>
                 
                 
                 <div class="tb-c none" >
                        <div class="new">
                           <table class="xy-lc-match-table">			                 
			                        <#if Session.successHtml=='undefined'||Session.successHtml==null>
			                        	<p align="center"><a href="${base}/htmllogin.do">请先登录</a></p> 
									<#else>
										<tr>
				                            <th>原始债权人</th>
				                            <th>债权名称</th>
				                            <th>债权剩余金额</th>
				                            <th>剩余期限</th>
				                        </tr>
										<#if orderChildrenList??>
											<#list orderChildrenList as oclist>
						                        <tr>
						                           <td >${oclist.moneyReceiver}</td>
						                           <td >${oclist.bidName}</td>
						                           <td >${oclist.matchingMoney}元</td>
						                           <td >${oclist.days}天</td>
			                                   </tr>  
					                       </#list>
				                       </#if> 
					                </#if> 
			                </table>
                        </div>                     
                 </div>
                 
                 
             </div>
            </div>
        </div>
<!--end-->  
</div>
</div>

 <!-- 弹出框 -->
 	<div class="xy-lc-box" id="xy-lc-box" style="display: none">
            <div class="xy-ui-box-mask"></div>
            <div class="xy-lc-box-show" style="width:800px;height:580px; margin: -290px 0 -400px 0;position: fixed!important;left: 20%;top: 50%;">
                <a class="xy-xbox-close" title="Close" href="javascript:void(0);" onClick="hid();" style="display: inline;">×</a>
                <div class="xy-lc-box-content" style="height:580px;">
                    <h1>确认购买信息</h1>
                    <div class="xy-confirm-hd">
                        <i class="xy-icon-confirm"></i>
                        <div class="xy-confirm-hd-main">
                            <h2>
                            	<#if plan.mmName?length &gt; 21>${plan.mmName?substring(0,21)}...<#else>${plan.mmName}</#if>
                            </h2>

                            <p style="text-align:left; padding-left:65px;"><span class="xy-confirm-label">预期年化收益率：</span><span>${plan.yeaRate}<#-- -${plan.maxYearRate}-->%</span><span class="xy-confirm-label xy-confirm-label-last">锁定期：</span><span>${plan.lockingLimit}个月</span></p>
                            <p style="text-align:left; padding-left:65px;"><span class="xy-confirm-label">还款方式：</span><span><#if  plan.isOne==0>按期收息,到期还本<#else>一次性支付利息 </#if></span></p>
                        </div>
                    </div>
                    <div class="" style="float:left;width:50%;height:350px;border-right:1px dashed #E0E0E0"">
                          <div class="">
                          <div class="xy-ui-form-item-explain" style="display: none; background:none;" ></div>
                          		购买金额：
                        	<input id="sign_Money" onkeyup="lx('sign_Money');"  class="xy-lc-input" placeholder="<#if plan.startMoney==0>0.00<#else><#if plan.startMoneyy lt 1000>${plan.startMoney}<#else>${plan.startMoney?string(',###.00')}</#if></#if>元起"><span class="xy-ft-unit">元</span>
                          </div>
                          <p class="xy-lc-amount-container">预估到期收:<span class="xy-lc-amount-value" style="color:red;"></span>元</p>
                          <div class="conts1">
        			 	  	<b class="mm_subNav " id="onSubNav">
        			 	  		<span>可用优惠券（<em id="sumNum" style="color:red"></em>） </span>&nbsp;&nbsp;
        			 	  		<#if plan.rebateType!=4>
        			 	  		<span>总金额：（<em class="middle" id="sumMoney" style="color:red"></em>）元 </span>
        			 	  		</#if>
        			 	  	</b>
        					<div style="width:360px;height:150px; overflow-y:scroll;float:right">
        						<table  width="320px;" id="addCoupons" border="0" cellspacing="0" cellpadding="0" style="border-collapse:separate; border-spacing:10px;">
        							
        						</table>
        					</div>
        			 	</div>
      			 	
                        <#-- <div class="xy-ui-securitycore" id="mymessage"><p>确认以上信息后，页面将调转易宝支付进行支付操作。</p></div>-->
 
                        <div style="height: 64px;color: #000;font-weight: 400;font-size: 16px;text-align: center;display:none;" id="mymessage2">
                        	<p>本标为新用户专享优惠理财标<br>您的帐号不符合此次的优惠活动条件。</p>
                        </div>
                        <br>
                        
                        <div style="padding:33px 60px; overflow:hidden;clear:both;width:480px;">
				          	<label style="float:left;">
				            	<input type="checkbox" id="readAgreement" tabindex="" />
				            	<span>我已阅读并同意</span>
				          	</label>
				         	<a style="float:left;" target="_blank" href="${base}/creditFlow/financingAgency/planContractPlManageMoneyPlan.do" name="signup" class="font-blue"><span class="red middle">《服务计划协议》</span>及</a> 
				          	<a style="float:left;" target="_blank" href="${base}/creditFlow/financingAgency/riskContractPlManageMoneyPlan.do" name="signup" class="font-blue"><span class="red middle">《风险提示书》</span></a> 
				            <span id="readAgreement_msg"></span>
				     		<span id="readAgreement_msg_img" style="float:left;margin:1px 5px 0 40px;"></span>
				        </div>	
                        <div class="xy-form-btn-box_mm" >
                            <a href="#" class="xy-form-btn-link"><input id="btn1" type="button" onclick="checkShow()"  value="确认投资" class="xy-form-btn-box-input"></a>
                        </div>
                   </div>
                   
                    <div style="float:left;>
                    <span id="setMoney" style="display:none;"></span>
            		<span id="mycouponsType" style="display:none;">${plan.rebateType}</span>
            		<table height="300px;" width="300px;">
            			<tr><td align="right">剩余投标金额：</td><td><span id="">${plan.afterMoney?string(',###.00')}元</span></td></tr>
            			<tr><td align="right">账户可用金额：</td><td><#if bpCustMember??>${bpCustMember.availableInvestMoney?string(',###.00')}元</#if></td></tr>
            			<tr><td align="right">是否可用优惠券：</td><td><span id="sycoupon"><#if plan.coupon==1>是<#else>否</#if></span></td></tr>
            			<tr><td align="right">是否新手专享：</td><td><span id=""><#if plan.novice==1>是<#else>否</#if></span></td></tr>
            			<#if plan.addRate!=0&&plan.addRate!="">
            			<tr><td align="right">加息金额：</td><td><span id="validMoney" style="color:red">随息计算随息派发</span></td></tr>
                		</#if>
            		<#if plan.coupon==1>
	            		<#if plan.rebateType!=4>
	            		<tr><td align="right">面值折现比：</td><td><span id="returnRatio">${plan.returnRatio}</span>%</td></tr>
	            		<tr><td align="right">有效面值：</td><td><span id="validMoney" style="color:red">0</span>元</td></tr>
	            		<tr><td align="right">优惠券单笔最大面值：</td><td><span id="maxCouponMoney">${plan.maxCouponMoney}</span>元</td></tr>
	            		</#if>
	            		<tr><td align="right">返利方式：</td><td><#if plan.rebateWay==1>立返</#if><#if plan.rebateWay==2>随期</#if><#if plan.rebateWay==3>到期</#if></td></tr>
	            		<tr><td align="right">返利类型：</td><td><#if plan.rebateType==1>返现</#if><#if plan.rebateType==2>返息</#if>
                			<#if plan.rebateType==3>返息现</#if><#if plan.rebateType==4>加息</#if></li></td></tr>
            		</#if>
            		</table>
            		
                    </div>
                    
                </div>
            </div>
        </div> 
    </div>
  <#--       <div class="xy-lc-box" id="xy-lc-box" style="display: none">
            <div class="xy-ui-box-mask"></div>
            <div class="xy-lc-box-show">
                <a class="xy-xbox-close" title="Close" href="javascript:void(0);" onClick="hid();" style="display: inline;">×</a>
                <div class="xy-lc-box-content">
                    <h1>确认购买信息</h1>
                    <div class="xy-confirm-hd">
                        <i class="xy-icon-confirm"></i>
                        <div class="xy-confirm-hd-main">
                            <h2>
                            	<#if plan.mmName?length &gt; 21>${plan.mmName?substring(0,21)}...<#else>${plan.mmName}</#if>
                            </h2>
                            <p style="text-align:left; padding-left:65px;"><span class="xy-confirm-label">预期年化收益率：</span><span>${plan.yeaRate}%</span></p>
                            <p style="text-align:left; padding-left:65px;"><span class="xy-confirm-label xy-confirm-label-last" style="margin:0;">锁定期：</span><span>${plan.lockingLimit}个月</span></p>
                            <p style="text-align:left; padding-left:65px;"><span class="xy-confirm-label">还款方式：</span><span><#if  plan.isOne==0>按期收息,到期还本<#else>一次性支付利息 </#if></span></p>
                        </div>
                    </div>
                    <div class="xy-confirm-bd">
                        <div class="xy-ui-form-confirm" id="mymess">
                          <label class="xy-ui-label">购买金额：</label>
                          <div class="xy-ui-form-text" style="text-align:left;"><strong class="xy-ft-orange" id="signyuan"></strong> 元</div>
                        </div>
                        <div class="xy-ui-securitycore"  style="padding-left:20px;" id="mymessage"><p>确认以上信息后，页面将调转易宝支付进行支付操作。</p></div>
                        
                        <div style="height: 64px;color: #000;font-weight: 400;font-size: 16px;text-align: center;display:none;" id="mymessage2">
                        	<p>本标为新用户专享优惠理财标<br>您的帐号不符合此次的优惠活动条件。</p>
                        </div>
                        
                        <div class="xy-form-btn-box">
                            <a href="#" class="xy-form-btn-link"><input id="btn1" type="button" onclick="bidHandler();" value="确认投资" class="xy-form-btn-box-input"></a>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </div>-->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</div>
<script type="text/javascript">
function hid(){
	document.getElementById("xy-lc-box").style.display="none";
}

function lx(){
	var yqn = '${plan.yeaRate}';//预期年化收益率
	var gmje = $("#signMoney").val();
	if(!gmje.match(/^[0-9]*$/)){
		$(".xy-ui-form-item-explain").text("金额必须为数字");
		$(".xy-ui-form-item-explain").show();
			return false;
	}
	$(".xy-ui-form-item-explain").text("");
	if(gmje==''||gmje==null)gmje=0;
	var qs = '${plan.investlimit}';
	var myqn = parseFloat(yqn)/parseFloat(12);
	var lx = parseFloat(myqn)/parseFloat(100)*parseFloat(gmje)*parseFloat(qs).toFixed(3);
	$(".xy-lc-amount-value").text(lx.toFixed(3));
}
function myfunction(){
	window.location.reload(); 
}
</script>
</body>
</html>