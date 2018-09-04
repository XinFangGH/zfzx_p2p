<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 体验标信息详情</title>

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>

<script type="text/javascript">var m1="",m2="",m3="";</script>
<script type="text/javascript">
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200 });		
	});
</script>
<script type="text/javascript">
$(function(){
	$(".subNav").click(function(){
		$(this).toggleClass("currentDt").siblings(".subNav").removeClass("currentDt");
		$(this).next("ol").slideToggle(300).siblings("ol").slideUp(300);
	})	
})
</script> 
</head>
<body> 
  <!-- topbar -->
  <!-- header -->
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
  <!-- navbar -->

<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!--体验标详情页-->
<div style="background:#eff3f3;width:100%;overflow:hidden;">
<!-- main --> 
<div class="main">
	<div class="details">
    	<h3>
        	<i class="novice"></i>
        	<img src="${base}/theme/${systemConfig.theme}/images/xy-index/new.png">
          		  新手体验--模拟借款
        </h3>
        <div class="list_all">
            <div class="dl-list">
                <dl>
                    <dt>借款总额</dt>
                    <dd><em>¥</em><span>10,000</span></dd>
                </dl>
                <dl>
                    <dt>年利率</dt>
                    <dd><span>20</span><em>%</em></dd>
                </dl>
                <dl class="none">
                    <dt>期限</dt>
                    <dd><span>4</span><em>周</em></dd>
                </dl>
                <div class="ul-list">
                    <ul>
                        <li>借款余额：<span>12元</span></li>
                        <li>进度：<span></span>45%</li>
                    </ul>
                    <ul>
                        <li>还款方式：<span>等额本息（周）</span></li>
                        <li>剩余时间：<span class="color">1 天 01 小时 20 分 37秒</span></li>
                    </ul>
            	</div>
            </div>
           <div class="login">
                <form>
                   <p><span>账户余额</span><a href="#" class="bb"> 登录 </a>后可见</p>
                   <p><input type="text" /><span class="bg">元</span></p>
                   <p>预计收益：<em>￥18.90</em>元</p>
                   <p><a id="go" href="#signup" rel="leanModal" name="signup"  class="btn">马上投标</a></p>
                </form>
           </div>
    	</div>
        <p class="text"><i></i>本标的投资收益为新手体验投标流程的活动奖励，由升升投全额支付。</p>
    </div>
    <div class="conts">
    	<p>1、本产品为新用户体验拍拍贷产品而设计，模拟真实标的投标和收益过程，并为新用户赠送投资收益，作为活动奖励。</p>
        <p>2、投资人需绑定手机，并通过实名认证，方可体验本虚拟产品，且每个新用户仅有一次新产品体验机会，投资上限1000元。</p>
        <p>3、对于涉嫌骗取活动奖励的用户，拍拍贷有权不予发放奖励。</p>
        <p>4、本活动最终解释权归属于拍拍贷。</p>
    </div>
     <div class="table">
    	<h3>借款详细信息</h3>
    	  <div class="tab">
            	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>                        
                        <th width="150" height="40"align="center" bgcolor="#2fa8e1">投标人 </th>
                        <th width="150" align="center" bgcolor="#2fa8e1">有效金额</th>
                        <th width="150" align="center" bgcolor="#2fa8e1">投标时间   </th>
                      </tr>                    
                      <tr>
                        <td align="center" height="40" bgcolor="#f5f5f5">wang</td>
                        <td align="center" bgcolor="#f5f5f5">￥12,0000</td>
                        <td align="center" bgcolor="#f5f5f5">2015-04-12 &nbsp;&nbsp;&nbsp;22:12:45</td>
              		 </tr>
                       <tr>
                        <td align="center" height="40" bgcolor="#fff">wang</td>
                        <td align="center" bgcolor="#fff">￥12,0000</td>
                        <td align="center" bgcolor="#fff">2015-04-12 &nbsp;&nbsp;&nbsp;22:12:45</td>
                      </tr>
                       <tr>
                        <td align="center" height="40" bgcolor="#f5f5f5">wang</td>
                        <td align="center" bgcolor="#f5f5f5">￥12,0000</td>
                        <td align="center" bgcolor="#f5f5f5">2015-04-12 &nbsp;&nbsp;&nbsp;22:12:45</td>
                      </tr>
               </table>
		   </div>        
    </div>
</div>
<!-- end main --> 
</div>                                                                                         		                                                                                                                                          
<!-- 结束 只需要拿这个说明标签之内的部分 --> 

<!-- copyright -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl">

 <input type="hidden" id="bidId" value="${plan.mmplanId}">
 <input type="hidden" id="bidName" value="${plan.mmName}">
  <input type="hidden" id="bidType" value="plmm">
 
</body>
</html>
