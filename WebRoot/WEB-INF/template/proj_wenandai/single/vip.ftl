<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - VIP服务</title>
    <meta name="description" content="${systemConfig.metaTitle} - VIP服务,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - VIP服务,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>

<script type="text/javascript" src="${base}/js/Calendar3.js"></script>

<script type="text/javascript" src="${base}/js/jquery-1.4.2.min(1).js"></script>
 <script type="text/javascript">
$(document).ready(function(){
	$('.close-btn').click(function(){
		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	
	$('.popbox-link').click(function(){
		var h = $(document).height();
		$('#screen').css({ 'height': 2000 });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		return false;
	});
	
});

jQuery.fn.center = function(loaded) {
	var obj = this;
	body_width = parseInt($(window).width());
	body_height = parseInt($(window).height());
	block_width = parseInt(obj.width());
	block_height = parseInt(obj.height());
	
	left_position = parseInt((body_width/2) - (block_width/2)  + $(window).scrollLeft());
	if (body_width<block_width) { left_position = 0 + $(window).scrollLeft(); };
	
	top_position = parseInt((body_height/2) - (block_height/2) + $(window).scrollTop());
	if (body_height<block_height) { top_position = 0 + $(window).scrollTop(); };
	
	if(!loaded) {
		
		obj.css({'position': 'absolute'});
		obj.css({ 'top': top_position, 'left': left_position });
		$(window).bind('resize', function() { 
			obj.center(!loaded);
		});
		$(window).bind('scroll', function() { 
			obj.center(!loaded);
		});
		
	} else {
		obj.stop();
		obj.css({'position': 'absolute'});
		obj.animate({ 'top': top_position }, 200, 'linear');
	}
}
</script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
 

<div class="main">
	<h2>VIP服务</h2> 
    	<a href="#" class="popbox-link">挂牌</a>	
</div>
<div id="screen" style=" display: none;"></div><!--screen end-->
<div class="popbox" style="position: absolute; top: 331px; left: 660px; display: none;">
	<h2><a class="close-btn" href="#"><img src="${base}/theme/${systemConfig.theme}/images/close-bg.png" width="22" height="22" style="margin:10px 5px 0px 0;" /></a></h2>
	<div class="mainlist">
	<div class="zhaiquan">
	<h3>截止到2015年01月16日，债权交易信息如下：</h3>
    <div class="box">
    <h4>债权信息</h4> 
    <p>债权原名称：<a href="#">w14p041105--长城电脑承诺DVD深</a><span>年化收益率：12%</span> </p>	
	<ul>	

        <li><span>债权总金额：</span><span class="one">20，000.00元</span></li>
        <li><span>已回收本金：</span><span class="one">20，000.00元</span></li>
        <li class="last"><span>未回收本金：</span><span class="one">20，000.00元</span></li> 

    </ul>
    <ul>	
        <li><span>债权起始日：</span><span>2014-12-12</span></li>
        <li><span>债权到期日：</span><span>2015-12-12</span></li>
        <li class="last"><span>债 权 期 限：</span<span>237天</span></li> 
    </ul>
    <ul>	

        <li ><span>债权总收益：</span><span class="one">20，000.00元</span></li>
        <li ><span>已支付收益：</span><span class="one">20，000.00元</span></li>
        <li class="last"><span>未支付收益：</span><span class="one">20，000.00元</span></li> 
    </ul>  
    </div>
     <div class="box">
     	<h4>交易结算单</h4>
     	<div class="left">
     		 <dl>
            <dt>当前可出让本金：</dt>
            <dd><em>2,000.00</em><span>元</span></dd>
	        </dl>
	         <dl>
	            <dt>欠收利益：</dt>
	            <dd><em>480.12</em><span>元</span></dd>
	        </dl>
	         <dl>
	            <dt>逾期罚息：</dt>
	            <dd><em>0.00</em><span>元</span></dd>
	        </dl>
	         <dl>
	            <dt>折让金：</dt>
	            <dd><em>+30.00</em><span>元</span></dd>
	        </dl>
	         <dl>
	            <dt style="font-weight:bold;">总结算金额：</dt>
	            <dd><em style="font-weight:bold;">7,200.00</em><span>元</span></dd>
	        </dl>
     	</div>
     	<div class="right">
     		<div class="btns"><a href="#">回款计划</a></div> 
     		<div class="iut">
     		<span><input class="say" type="radio" value=""/><i> 降价</i><input type="radio" class="say" value=""/ ><i> 加价</i></span>
            <span> 出让本金千分之： <input type="text" class="colorful1" style="width:50px; height:25px;padding:0;line-height:25px;"/> 
            <a href="#" class="butt">计算</a></span>
            </div>
     	</div>
       
    </div>
    <div class="box">
    	<h4>交易费用</h4>
    	
    	<p>转让服务费：5.00元 <em style="color:#d6d6d6;margin-left:135px;">结算总金额的1%，交易成功时，从交易总金额中扣除，平台收取<em></p>
   
    </div>
    <div class="box">
    	<h4>获取验证码</h4>
    	<div class="inpt">
	    	<input type="text" value="15*******061" readonly="readonly" class="colorful1 colorfull2" style="width:120px;padding:1px 10px; float:left;background:#FFFFCD;"  />
	    	<input type="text" class="colorful1 " style="width:80px;padding:1px 10px; float:left;margin:0 10px;"  /> 
	    	 <a href="#" class="buttonoblue4" style="padding:5px 10px;color:#fff; float:left;font-size:12px;">请输入验证码</a>
    	</div>
    	<p style="color:#666;height:30px; line-height:30px;">Tips: 尊敬的投资人，因转让无时限制要求，您可/只可手动关闭转让以避免交易信息被遗忘造成损失。</p>
        <div class="buttons"><a href="#" class="buttonoblue4">挂牌</a><a href="#" class="buttonogray1">取消</a></div>
    </div>
</div>
	</div>
</div><!--popbox end-->




<!--<div id="signupInterest1">
   <div id="signup-header1">
       <div style="float:left; width:280px; height:20px; padding:10px 0px 0px 30px"><span  class="bigger black"></span></div>
       <div id="lean_overlay_close_Income" style="float:right;  padding:13px 10px 0px 20px; height:20px; text-decoration:underline; cursor:pointer;"  class="normal blue"><img src="${base}/theme/${systemConfig.theme}/images/close-bg.png" width="22" height="22" /></div>
   </div>
   <div class="zhaiquan">
	<h3>截止到2015年01月16日，债权交易信息如下：</h3>
    <div class="box">
    <h4>债权信息</h4> 
    <p>债权原名称：<a href="#">w14p041105--长城电脑承诺DVD深</a><span>年化收益率：12%</span> </p>	
	<ul>	

        <li><span>债权总金额：</span><span class="one">20，000.00元</span></li>
        <li><span>已回收本金：</span><span class="one">20，000.00元</span></li>
        <li class="last"><span>未回收本金：</span><span class="one">20，000.00元</span></li> 

    </ul>
    <ul>	
        <li><span>债权起始日：</span><span>2014-12-12</span></li>
        <li><span>债权到期日：</span><span>2015-12-12</span></li>
        <li class="last"><span>债 权 期 限：</span<span>237天</span></li> 
    </ul>
    <ul>	

        <li ><span>债权总收益：</span><span class="one">20，000.00元</span></li>
        <li ><span>已支付收益：</span><span class="one">20，000.00元</span></li>
        <li class="last"><span>未支付收益：</span><span class="one">20，000.00元</span></li> 
    </ul>  
    </div>
     <div class="box">
     	<h4>交易结算单</h4>
     	<div class="left">
     		 <dl>
            <dt>当前可出让本金：</dt>
            <dd><em>2,000.00</em><span>元</span></dd>
	        </dl>
	         <dl>
	            <dt>欠收利益：</dt>
	            <dd><em>480.12</em><span>元</span></dd>
	        </dl>
	         <dl>
	            <dt>逾期罚息：</dt>
	            <dd><em>0.00</em><span>元</span></dd>
	        </dl>
	         <dl>
	            <dt>折让金：</dt>
	            <dd><em>+30.00</em><span>元</span></dd>
	        </dl>
	         <dl>
	            <dt style="font-weight:bold;">总结算金额：</dt>
	            <dd><em style="font-weight:bold;">7,200.00</em><span>元</span></dd>
	        </dl>
     	</div>
     	<div class="right">
     		<div class="btns"><a href="#">回款计划</a></div> 
     		<div class="iut">
     		<span><input class="say" type="radio" value=""/><i> 降价</i><input type="radio" class="say" value=""/ ><i> 加价</i></span>
            <span> 出让本金千分之： <input type="text" class="colorful1" style="width:50px; height:25px;padding:0;line-height:25px;"/> 
            <a href="#" class="butt">计算</a></span>
            </div>
     	</div>
       
    </div>
    <div class="box">
    	<h4>交易费用</h4>
    	
    	<p>转让服务费：5.00元 <em style="color:#d6d6d6;margin-left:135px;">结算总金额的0.5%，交易成功时，从交易总金额中扣除，平台收取<em></p>
   
    </div>
    <div class="box">
    	<h4>获取验证码</h4>
    	<div class="inpt">
	    	<input type="text" value="15*******061" readonly="readonly" class="colorful1 colorfull2" style="width:120px;padding:2px 10px; float:left;background:#FFFFCD;"  />
	    	<input type="text" class="colorful1 " style="width:80px;padding:2px 10px; float:left;margin:0 10px;"  /> 
	    	 <a href="#" class="buttonoblue4" style="padding:6px 10px;color:#fff; float:left;font-size:12px;">请输入验证码</a>
    	</div>
    	<p style="color:#666;">Tips: 尊敬的投资人，因转让无时限制要求，您可/只可手动关闭转让以避免交易信息被遗忘造成损失。</p>
        <div class="buttons"><a href="#" class="buttonoblue4">挂牌</a><a href="#" class="buttonogray1">取消</a></div>
    </div>
</div>
</div>-->
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>