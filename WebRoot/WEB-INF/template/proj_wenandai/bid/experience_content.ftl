<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<title>升升投 - 体验标招标详情页</title>
    <meta name="description" content="${systemConfig.metaTitle} - 体验标招标详情页,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 体验标招标详情页,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/xy-lc.css">
<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
<script type="text/javascript">
$(function(){

	
	
$('.tab-title ul li').click(function(){ 
$(this).addClass("selected").siblings().removeClass('selected');//removeClass就是删除当前其他类；只有当前对象有addClass("selected")；siblings()意思就是当前对象的同级元素，removeClass就是删除； 
$("#tab-content .tb-c").hide().eq($('.tab-title ul li').index(this)).show(); 
});

var _move=false;//移动标记  
var _x,_y;//鼠标离控件左上角的相对位置  
    $(".xy-lc-box").click(function(){  
    	
        }).mousedown(function(e){  
        _move=true;  
        _x=e.pageX-parseInt($(".xy-lc-box").css("left"));  
        _y=e.pageY-parseInt($(".xy-lc-box").css("top"));  
        $(".xy-lc-box").fadeTo(20, 0.5);//点击后开始拖动并透明显示  
    	   $(this).mousemove(function(e){  
    		   if(_move){  
    			   var x=e.pageX-_x;//移动时根据鼠标位置计算控件左上角的绝对位置  
    			   var y=e.pageY-_y;  
    			   $(".xy-lc-box").css({top:y,left:x});//控件新位置  
    		   }  
    	   }).mouseup(function(){  
    		   _move=false;  
    		   $(".xy-lc-box").fadeTo("fast", 1);//松开鼠标后停止移动并恢复成不透明  
    	   });
    	  
    });  
    

    
	})
	
	$(function() {
	
	
		$('a[rel*=leanModal]').leanModal({ top : 200 });


       <#if plmmExperience.afterTime??>
       <#if plmmExperience.afterTime gte 0>
		    timer(Number("${plmmExperience.afterTime}"));
	   </#if>  
	   </#if>  
	   
	   <#if plmmExperience.saleTime??>
       <#if plmmExperience.saleTime gte 0>
		    timer1(Number("${plmmExperience.saleTime}"));
	   </#if>  
	   </#if>  
    });
    
	
</script>
</head>
 <input type="hidden" id="bidId" value="${plmmExperience.mmplanId}">
 <input type="hidden" id="bidName" value="${plmmExperience.mmName}">
 <input type="hidden" id="hiddenMoney" value="${plmmExperience.afterMoney}"/>
 <input type="hidden" id="bidType" value="experience">
 <input type="hidden" id="planprogress" value="${plmmExperience.progress}"/>
 <input type="hidden" id="planafterTime" value="${plmmExperience.afterTime}"/>
 <input type="hidden" id="dayRate" value="${plmmExperience.dayRate}"/>
  <input type="hidden" id="yeaRate" value="${plmmExperience.yeaRate}"/>
 <input type="hidden" id="investlimit" value="${plmmExperience.investlimit}"/>
 
<input type="hidden" id="bidType" value="experience">
<input value="${(bpCustMember.grade)??}" type="hidden" id="grade">
<input value="${(bpCustMember.id)??}" type="hidden" id="memberId">

    <style type="text/css">  
    /*模块拖拽*/  
    .xy-lc-box{position:absolute;width:100px;height:100px;text-align:center;padding:5px;top:25%;left:50%;cursor:move;}  
    </style> 
<body style="background: #eff0f2">
<div>
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div style="width:100%;background:#eff3f3;overflow:hidden; ">
<div class="main">
	<!--<div class="porduct">
    	<a href="#">产品详情</a>
    </div>-->
  
    <div id="xy-lc-wrap">
        <div class="xy-lc-content">
            <div class="xy-lc-install">
                <div class="xy-lc-custom">
                    <h1><em >${plmmExperience.mmName} </em><i><img src="${base}/theme/${systemConfig.theme}/images/hot.png"></i></h1>
                    <form id="bid_Form333" style="height:218px;"action="${base}/creditFlow/financingAgency/bidingPlBidInfo.do" method="post">
                    <ul class="xy-lc">
                        <li><p class="xy-lc-key">预期年化收益率</p><p class="xy-lc-num" style="color:#fd7754;"><span style="color:#fd7754;font-size:42px;">${plmmExperience.yeaRate}</span>%</p></li>
                        <li><p class="xy-lc-key">计划金额(元)</p><p class="xy-lc-num"><#if plmmExperience.sumMoney==0>0.00<#else><#if plmmExperience.sumMoney lt 1000>${plmmExperience.sumMoney}<#else>${plmmExperience.sumMoney?string(',###.00')}</#if></#if><span></span></p></li>
                        <li><p class="xy-lc-key">投资期限</p><p class="xy-lc-num">${plmmExperience.investlimit}<span style="font-size:14px; color:#666;">天</span></p></li>
                    </ul>
                    <div class="xy-lc-detail xy-lc">
                     <div class="xy-lc-item-line"></div>
                        <div class="xy-lc-detail-item">
                            <p class="xy-lc-item-content"><img class="fl" src="${base}/theme/${systemConfig.theme}/images/ty-icon1.png" alt=""> 借款余额：<em><#if plmmExperience.afterMoney==0>0.00<#else><#if plmmExperience.afterMoney lt 1000>${plmmExperience.afterMoney}<#else>${plmmExperience.afterMoney?string(',###.00')}</#if></#if></em>元</p>
                        </div>
                        <div class="xy-lc-detail-item">
                            <p class="xy-lc-item-content"><img class="fl" src="${base}/theme/${systemConfig.theme}/images/ty-icon2.png" alt=""> 进度：
                           
	                            <span class="jdt">
	                            <span class="jdtbg" style="width:${plmmExperience.progress}%"></span>
	                            </span>
	                           <em> ${plmmExperience.progress}</em>%
                            </p>
                        </div>
						 <div class="xy-lc-detail-item">
                            <p class="xy-lc-item-content"><img class="fl" src="${base}/theme/${systemConfig.theme}/images/ty-icon3.png" alt=""> 还款方式：<em>一次性支付利息</em></p>
                        </div>
                          <div class="xy-lc-detail-item">
                            <p class="xy-lc-item-content"><img class="fl" src="${base}/theme/${systemConfig.theme}/images/ty-icon4.png" alt=""> 剩余时间：
                            <#if plmmExperience.state==1>
                           		<#if plmmExperience.startTime?date("yyyy-MM-dd HH:mm:ss") lt plmmExperience.nowDate?date("yyyy-MM-dd HH:mm:ss") >
		                            <span>还剩：
		                               <span id="day_show">0天</span>
								       <span id="hour_show">0时</span>
								       <span id="minute_show">0分</span>
								       <span id="second_show">0秒</span>
		                            </span>
	                            <#else>
	                             	<span>
		                               <span id="day_show1">0天</span>
								       <span id="hour_show1">0时</span>
								       <span id="minute_show1">0分</span>
								       <span id="second_show1">0秒</span>
								                 后开售
		                            </span>
	                            </#if>
                            <#else>
                               <span>
	                               <span>0天</span>
							       <span>0时</span>
							       <span>0分</span>
							       <span>0秒</span>
	                            </span>
                            </#if>
                            </p>
                        </div>
                    </div>
                    </form>
                </div>             
            </div>
            <div class="xy-lc-apply">
            	<i class="bg"></i>
                <div class="xy-lc-apply-hd">
                	<p class="fonts">投资余额：<em class="big"><#if plmmExperience.afterMoney==0>0.00<#else><#if plmmExperience.afterMoney lt 1000>${plmmExperience.afterMoney}<#else>${plmmExperience.afterMoney?string(',###.00')}</#if></#if></em>元</p>
                	
                	<p class="regular">预期收益：<em class="middle"><#if plmmExperience.yqincomMoney==0||plmmExperience.yqincomMoney==null>0.00<#else>${plmmExperience.yqincomMoney?string(',###0.00')}</#if></em>元</p>
                	<#if plmmExperience.state==1>
                		<#if plmmExperience.startTime?date("yyyy-MM-dd HH:mm:ss") lt plmmExperience.nowDate?date("yyyy-MM-dd HH:mm:ss") >
                				<p class="bton"><a id="go" href="#signup" rel="leanModal" name="signup" onClick="check('${plmmExperience.mmplanId!}','${plmmExperience.afterMoney!}','${(plmmExperience.yeaRate)!}',true);" class="font-blue" ><span class="big">马上投标</span></a></p>
                        <#else>
                                <p class="bton"><a id="goo" href="#" class="font-blue" ><span class="big">预售中</span></a></p>
                        </#if>
                    <#elseif plmmExperience.state==2>
                    <p class="bton"><a id="goo" href="#" class="font-blue" ><span class="big">已满标</span></a></p>
                    <#elseif plmmExperience.state==-2 || plmmExperience.state==-1>
                    <p class="bton"><a id="goo" href="#" class="font-blue" ><span class="big">已流标</span></a></p>
                    <#elseif plmmExperience.state==7>
                    <p class="bton"><a id="goo" href="#" class="font-blue" ><span class="big">还款中</span></a></p>
                    <#else>
                    <p class="bton"><a id="goo" href="#" class="font-blue" ><span class="big">已完成</span></a></p>
                    </#if>  
                </div>
            </div>
        </div>
        <div class="txt-box middle"><img src="${base}/theme/${systemConfig.theme}/images/laba.png">本标的投资收益为新手体验投标流程的活动奖励，<em>由本网站全额支付。</em></div>
        <div class="txt-box1">
            <p class="middle">${plmmExperience.bidRemark}</p>
        <#--<p class="middle">1. 本产品为新用户体验产品而设计，模拟真实标的投标和收益过程，并为新用户赠送投资收益，作为活动奖励。</p>
        	<p class="middle">2. 投资人需绑定手机，并通过实名认证，方可体验本虚拟产品，且每个新用户仅有一次新产品体验机会，投资上限1000元。</p>
        	<p class="middle">3. 对于涉嫌骗取活动奖励的用户，网站有权不发放奖励。</p>
        	<p class="middle">4. 本活动最终解释权归本网站。</p>-->
		</div>

	<div class="frame-tabs">
			<div class="tab-titles">
				<ul>
					<li class="selected">投标记录</li>
					<span class="sept">|</span>
				</ul>
			</div>
	     <div class="table-box">
	     	<table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
                 <tr>
                      <th align="center" >投标人</th>
                      <th align="center">有效金额</th>
                      <th align="center">投资时间</th>
                     
                  </tr> 
                  <#if infoList??>
                  <#list infoList as list>                            
                  <tr>    
                      <td align="center">${list.investPersonName?substring(0,2)}****${list.investPersonName?substring(list.investPersonName?length-2)}</td>
                      <td align="center">￥${list.buyMoney}</td>
                      <td align="center">${list.buyDatetime}</td>
                  </tr>
                  </#list>
                  </#if>
      
          </table>
	     </div> 
	</div>

<!--end-->  
   
</div>
</div>


<form id="bid_Form"  method="post">
<input name="plBidInfo.bidId" type="hidden" id="plBidInfobidId">
<input name="plBidInfo.bidName" type="hidden" id="plBidInfobidName">
<input name="plBidInfo.userMoney" type="hidden" id="plBidInfouserMoney">
<input name="plBidInfo.userName" type="hidden" id="plBidInfouserName">
<input name="plBidInfo.userId" type="hidden" id="plBidInfouserId">
<input name="bpCoupons.couponId" type="hidden" id="couponId">
<input name="formtoken" type="hidden" id="formtoken" value="${formtoken}">    	
<div id="signup">
	<div id="signup-ct">
		<div id="signup-header">
        	<div style="float:left; width:330px;line-height:40px; height:40px; padding:10px 0px 0px 30px; color:#808080;"><span  class="regular">我要投标</span></div>
            <div id="lean_overlay_close" style="float:right;color:#808080;  padding:20px 0px 0px 0px;width:60px; height:20px; line-height:20px; cursor:pointer;"  class="regular">关闭</div>
		</div>
		<div>
        <div style="float:left; width:348px; height:150px; margin:30px 0px 0px 30px;">
        <span id="bidLoad"></span>
        <!--提示信息开始-->
        	<span id="msgWin" style="display:none">
        	<ul>
				  <li>
				    	<span class="big black">提示信息：</span>
				  </li>
				  <li>
				    <span id="bidMsg"></span>
				  </li>
			 	  <li>
			 	    <a href="${base}/htmllogin.do?retUrl=" target="_self" id="bidLogin"> <input class="buttonorange button"   value="&nbsp;&nbsp;&nbsp;立即登录 &nbsp;&nbsp;" style="height:30px;"></input></a>
			 	 
			 	  </li>
			 	  	   <li>
			 	    <a href="javascript:location.reload()" target="_self" id="bidReset" style="display:none"> <input class="buttonorange button" type="submit"   value="&nbsp;&nbsp;&nbsp;重新投标 &nbsp;&nbsp;" style="height:30px;"></input></a>
			 	  </li>
			 	  </ul>
        	</span>
        	<!--提示信息结束-->
			<div class="conts1">
				 <b class="subNav">
		 	  		<span>可用优惠券（<em id="sumExperienceNum"></em>） </span>&nbsp;&nbsp;
		 	  		<span>总金额：（<em class="middle" id="sumExperienceMoney"></em>）元 </span>
				 </b>
				 <div style="width:350px;height:150px; overflow-y:scroll;">
					<table  width="320px;" id="addExperienceBpCoupons" border="0" cellspacing="0" cellpadding="0" style="border-collapse:separate; border-spacing:10px;">
								
					</table>
				 </div>
			 	 </div>
			 	 <p style=" margin-left:50px;">
			 	  	<input class="buttonorange1 button" type="button"  onClick="bidHandler()"   value="马上投标" ></input>
			 	 </p>
            </div>
            
    	    <div style="float:left; height:350px; margin:10px 0px 0px 0px; width:1px; border:0px; background-color:#e0e0e0;"></div>
    	
            <div style="float:left; width:300px; margin:20px 0px 0px 20px;">
		    	<ul>
		    		<span id="setMoney" style="display:none;"></span>
		    		<#if plmmExperience??>
			    		<li>剩余投标金额：<span id="">${plmmExperience.afterMoney?string(',###.00')}元</span></li>
		    		<#else>
		    			<li>剩余投标金额：<span id="">0.00元</span></li>
		    		</#if>
		    		<li>预期收益：<span id="expectMoney" style="color:red;"></span>元</li>
		    		<li><a class="buttonorange1" style="padding:8px 40px;" href="${base}/financePurchase/rechargeFinancePurchase.do" target="_self"><span class="white">立刻充值</span></a></li>
		    	</ul>        
            </div>
        </div>
	</div>
</div>
</form>




 <!-- 弹出框 -->
         <div class="xy-lc-box" id="xy-lc-box" style="display: none">
            <div class="xy-ui-box-mask"></div>
            <div class="xy-lc-box-show">
                <a class="xy-xbox-close" title="Close" href="javascript:void(0);" onClick="hid();" style="display: inline;">×</a>
                <div class="xy-lc-box-content">
                    <h1>确认购买信息</h1>
                    <div class="xy-confirm-hd">
                        <i class="xy-icon-confirm"></i>
                        <div class="xy-confirm-hd-main">
                            <h2>
                            	<#if plmmExperience.mmName?length &gt; 21>${plmmExperience.mmName?substring(0,21)}...<#else>${plmmExperience.mmName}</#if>
                            </h2>
                            <p style="text-align:left; padding-left:65px;"><span class="xy-confirm-label">预期年化收益率：</span><span>${plmmExperience.yeaRate}%</span><span class="xy-confirm-label xy-confirm-label-last"></p>
                            <p style="text-align:left; padding-left:65px;"><span class="xy-confirm-label">还款方式：</span><span>一次性支付利息 </span></p>
                        </div>
                    </div>
                    <div class="xy-confirm-bd">
                        <div class="xy-ui-form-confirm" id="mymess">
                          <label class="xy-ui-label">购买金额：</label>
                          <div class="xy-ui-form-text" style="text-align:left;"><strong class="xy-ft-orange" id="signyuan"></strong> 元</div>
                        </div>
                        <div class="xy-ui-securitycore" id="mymessage"  style="padding-left:20px;"><p>确认以上信息后，页面将调转易宝支付进行支付操作。</p></div>
                        
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
    </div>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</div>
<script type="text/javascript">
function show(){
	var object = $("#signMoney");
	var s = $("#signMoney").val();
	var afterM = $('#afterM').val();
	var currM = $('#currM').val();
	var sx = '${plmmExperience.limitMoney}';
	var lx = $('.xy-lc-amount-value').text();
	var a_plan = $("#A_plan").val();
	var grade=$("#grade").val();
	if(a_plan==undefined){
		$(".xy-ui-form-item-explain").text("请您登录后购买！");
		$(".xy-ui-form-item-explain").show();
			return false;
		}
	 var reg = /(?!^0\d*$)^\d{1,16}(\.\d{1,2})?$/;
     flag = reg.test(object.val());
	if(s==''||s==null){
	$(".xy-ui-form-item-explain").text("金额不能为空");
	$(".xy-ui-form-item-explain").show();
		return false;
	}
	if(!flag){
	$(".xy-ui-form-item-explain").text("金额必须大于0且为整数或小数。");
	$(".xy-ui-form-item-explain").show();
		return false;
	}
	if(!s.match(/^[0-9]*$/)){
	$(".xy-ui-form-item-explain").text("金额必须为数字");
	$(".xy-ui-form-item-explain").show();
		return false;
	}
	if(parseInt(object.val())> parseInt(sx)){
	$(".xy-ui-form-item-explain").text("您输入的金额大于了当前标的投资上限。");
	$(".xy-ui-form-item-explain").show();
		return false;
	
	}
	if(parseInt(object.val())>parseInt(afterM)){
	$(".xy-ui-form-item-explain").text("您输入的投资金额大于标的剩余金额。");
	$(".xy-ui-form-item-explain").show();
		return false;
	}
	if(parseInt(object.val())>parseInt(currM)){
	$(".xy-ui-form-item-explain").text("您输入的投资金额大于您的账户余额。");
	$(".xy-ui-form-item-explain").show();
		return false;
	}
	
	if(parseInt(s)%1!=0){
	$(".xy-ui-form-item-explain").text("金额必须为100的倍数");
	$(".xy-ui-form-item-explain").show();
		return false;
	}
	$("#signyuan").text(s);
	document.getElementById("xy-lc-box").style.display="";
}

function hid(){
	document.getElementById("xy-lc-box").style.display="none";
}

function lx(){
	var yqn = '${plmmExperience.yeaRate}';//预期年化收益率
	var gmje = $("#signMoney").val();
	if(!gmje.match(/^[0-9]*$/)){
		$(".xy-ui-form-item-explain").text("金额必须为数字");
		$(".xy-ui-form-item-explain").show();
			return false;
	}
	$(".xy-ui-form-item-explain").text("");
	if(gmje==''||gmje==null)gmje=0;
	var qs = '${plmmExperience.investlimit}';
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