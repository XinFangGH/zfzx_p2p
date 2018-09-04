
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="description" content="${systemConfig.metaTitle} ,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} ,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
<script type="text/javascript">var m1="债权转让",m2="",m3="";</script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript">

$(document).ready(function(){
    var type="${type}";
    if(type=="buy"){
     $("#box1").css({ display: 'none'});	
      $("#box2").css({ display: 'none'});	
        $("#refreshbtn").css({ display: 'none'});
    
          $("#changeMoneyRate").attr("readonly","readonly");
         $("#changeMoneyRate").css({background:'#E6E6E6'});
            $("#changeMoneyType1").attr("disabled","disabled");
               $("#changeMoneyType2").attr("disabled","disabled");
    }
   if(type=="see"){
      $("#box2").css({ display: 'none'});
       $("#box3").css({ display: 'none'});	
        $("#refreshbtn").css({ display: 'none'});	
          $("#changeMoneyRate").attr("readonly","readonly");
            $("#changeMoneyRate").css({background:'#E6E6E6'});
               $("#changeMoneyType1").attr("disabled","disabled");
               $("#changeMoneyType2").attr("disabled","disabled");
    }
    if(type=="transferpay"){
       $("#box3").css({ display: 'none'});	
        $("#refreshbtn").css({ display: 'none'});	
          $("#changeMoneyRate").attr("readonly","readonly");
            $("#changeMoneyRate").css({background:'#E6E6E6'});
               $("#changeMoneyType1").attr("disabled","disabled");
               $("#changeMoneyType2").attr("disabled","disabled");
    }
   if(type=="transfer"){
      $("#box3").css({ display: 'none'});	
    }
    var changeMoneyType="${changeMoneyType}"
    if(changeMoneyType=="1"){
      $("#changeMoneyType1").attr("checked","checked");
    }else{
      $("#changeMoneyType2").attr("checked","checked");
    }
});


/*$(".buttongray").click(function(){
    $("#xubox_shade1").css({"display":"none"});
});*/
</script>
</head>
<body >
<form id="transfer_Form"  method="post" action="${base}/creditFlow/financingAgency/saveorStartTransferPlBidSale.do" >
<input type="hidden" id="saleMoney" name="saleMoney" value="${saleMoney}"/> 
<input type="hidden" id="sumInteresteMoney" name="saleMoney" value="${sumInteresteMoney}"/> 
<input type="hidden" id="accrualMoney" name="accrualMoney" value="${accrualMoney}"/> 
<input type="hidden" id="changeMoney" name="changeMoney" value="${changeMoney}"/> 
<input type="hidden" id="transferFee" name="transferFee" value="${transferFee}"/> 
<input type="hidden" id="sumMoney" name="sumMoney" value="${sumMoney}"/> 
<input name="bidInfoId" type="hidden" id="bidInfoId" value=${bidInfoId} />
<input name="startDate" type="hidden" id="startDate" value=${startDate} />
<input name="saleId" type="hidden" id="saleId" value=${saleId} />
	<div class="zhaiquan  myzhaiquan">
	<h3>截止到${newDate}，债权交易信息如下：</h3>
    <div class="box">
    <h4>债权信息</h4> 	
    <ul>	
		<li><span>债权原名称：</span><span>${bidProName}</span></li>
		<li><span>年化收益率：</span><span>${yearAccrualRate}%</span></li>
        
    </ul>
	<ul>	
        <li><span>债权总金额：</span><span class="one"><#if loadMoney==0>0.00<#else><#if loadMoney lt 1000>${loadMoney}<#else>${loadMoney?string(',###.00')}</#if></#if>元</span></li>
        <li><span>已回收本金：</span><span class="one"><#if getMoney==0>0.00<#else><#if getMoney lt 1000>${getMoney}<#else>${getMoney?string(',###.00')}</#if></#if>元</span></li>
        <li class="last"><span>未回收本金：</span><span class="one"><#if saleMoney==0>0.00<#else><#if saleMoney lt 1000>${saleMoney}<#else>${saleMoney?string(',###.00')}</#if></#if>元</span></li> 

    </ul>
    <ul>	
        <li><span>债权起始日：</span><span >${startDate}</span></li>
        <li><span>债权到期日：</span><span>${intentDate}</span></li>
        <li class="last"><span>债 权 期 限：</span<span>${days}天</span></li> 
    </ul>
    <ul>	

        <li ><span>债权总收益：</span><span class="one">${loanInterest}元</span></li>
        <li ><span>已支付收益：</span><span class="one">${afterInterest}元</span></li>
        <li class="last"><span>未支付收益：</span><span class="one">${notLoanInterest}元</span></li> 
    </ul>  
    </div>
     <div class="box">
     	<h4>交易结算单</h4>
     	<div class="left">
     		 <dl>
            <dt>当前可出让本金：</dt>
            <dd><em>${saleMoney}</em><span>元</span></dd>
	        </dl>
	         <dl>
	            <dt>欠收利息：</dt>
	            <dd><em>${sumInteresteMoney}</em><span>元</span></dd>
	        </dl>
	         <dl>
	            <dt>折让金：</dt>
	            <dd><em id="changeMoneytext">${changeMoney}</em><span>元</span></dd>
	        </dl>
	         <dl>
	            <dt style="font-weight:bold;">总结算金额：</dt>
	            <dd><em style="font-weight:bold;" id="sumMoneytext">${sumMoney}</em><span>元</span></dd>
	        </dl>
     	</div>
     	<div class="right">
     		<div class="btns"> <a href="javascript:void(0);" onClick="clickFinancing(${bidPlanID},'${orderNo}','Financing')" class="btn1">回款计划</a></div> 
     		<div class="iut">
     		<span><input class="say" type="radio" id="changeMoneyType1" name="changeMoneyType" value="1" onclick="signMoney()"/><i> 降价</i>
     		<input type="radio" class="say" id="changeMoneyType2" name="changeMoneyType" value="0" onclick="signMoney()"/><i> 加价</i></span>
            <span> 出让本金千分之： <input type="text" value=${changeMoneyRate} class="colorful1" id="changeMoneyRate" name="changeMoneyRate" style="width:50px; height:25px;padding:0;line-height:25px;" /> 
          <a href="javascript:void(0)" class="butt" id="refreshbtn" onclick="signMoney()">计算</a></span> 
            </div>
     	</div>
       
    </div>
                账户余额：${bpCustMember.availableInvestMoney?string(',###.00')}元
      <div class="box" id="box3">
      <#if bpCustMember.availableInvestMoney gte sumMoney >
	     <div class="buttons" id="buyBtn"><a href="#" class="buttonoblue1" id="transferFormbuy">购买</a></div>
	  <#else>   
	     <div class="buttons" id="buyBtn"><a href="#" class="buttonoblue5">账户余额不足，请先充值！</a></div>
	  </#if> 
     </div>
    <div class="box" id="box1">
    	<h4>交易费用</h4>
    	
    	<p>转让服务费：<em id="transferFeetext">${transferFee}</em> 元<em style="color:#d6d6d6;margin-left:135px;">结算总金额的1%，交易成功时，从交易总金额中扣除，平台收取<em></p>
   
    </div>
    <div class="box" id="box2">
    <!--	<h4><a id="getVerifySms"  href="javascript:void(0);">获取验证码</a></h4>
    	<div class="inpt">
	    	<input type="text" id="telphone" name="telphone"  type="text" value=${telephone} readonly="readonly" class="colorful1 colorfull2" style="width:120px;padding:1px 10px; float:left;background:#FFFFCD;"  />
	    	<input type="text" class="colorful1 " id="verify_sms" tabindex="6"  name="verify_sms"  style="width:80px;padding:1px 10px; float:left;margin:0 10px;"  /> 
	    	 <a href="#" class="buttonoblue4" style="padding:5px 10px;color:#fff; float:left;font-size:12px;">请输入验证码</a>
    	</div>-->
    	<p style="color:#666;height:30px; line-height:30px;">Tips: 尊敬的投资人，因转让无时限制要求，您可/只可手动关闭转让以避免交易信息被遗忘造成损失。</p>
        <div class="buttons"><a href="#" class="buttonoblue1" id="transferFormPhone">挂牌</a><a href="#" id="close_btn" class="buttonoblue1" style="background-color:#dcdcdc;">取消</a></div>
    </div>
</div>
<!--popbox end-->
</form>
	</body>
</html>