 <!-- start:banner -->
	<!--banner开始-->
<!-- start:banner -->
 <div class="wrap-banner">
 <div class="Ilogin">
		
    <div id="demo01" class="flexslider">
        <ul class="slides">
        
          <#if bannerList!="[]">
		      	<#list bannerList as list>
		      		<li style="background:url(${list.url}) center 0 no-repeat;"><a href="${list.urlLink}"></a></li>
		      	</#list>
          <#else>
	          	<li class="li_1"><a href="#"></a></li>
	            <li class="li_2"><a href="#"></a></li>
	            <li class="li_3"><a href="#"></a></li> 
	            <li class="li_4"><a href="#"></a></li>	
	            <li class="li_5"><a href="${base}/financeProduct/getProductPlFinanceProduct.do"></a></li>
          </#if>
        </ul>
    </div>
    
</div>

<script type="text/javascript">
$(function(){
	$('#demo01').flexslider({
		animation: "slide",
		direction:"horizontal",
		easing:"swing"
	});
	
});
</script>

 <!--悬浮框-->
    <script type="text/javascript">
		$(document).ready(function(){
			 lgdh1();
		});	
		function lgdh1(){
			$(".login_block").animate({
				top:'20px'
			  },800);
			setTimeout("lgdh2()","100");  
		}	
		function lgdh2(){
			$(".login_block").animate({
				top:'0px'
			  },300);
		}	
		$(function(){ 
		$(".freebj").css("opacity","0.5"); //设置透明度 
		});
	</script>
    <div class="fronts">
       <div class="login_block"> 
	    <!--  -->
	    <div class="freebj"></div>
		<div class="freelogin">
			
			<div class="freereg ">
				<#if Session.successHtml=='undefined'||Session.successHtml==null>
					<p class="text_1" style="font-size:18px;color:#ffffff;">历史年化收益率</p>
					<!--登陆后显示下面这个p标签的内容  隐藏上面的p标签的内容-->
					<p class="text_2" style="margin-top:12px; font-size:40px;line-height:60px;color:#f65541;">8<i style=" font-size:18px;">%</i>-15<i style=" font-size: 18px;">%</i></p>
					<#else>
					<p class="text_dname"  style="font-size:18px;color:#ffffff;">您好：${bpCustMember.loginname}</p>
					<p class="text_2"  style="margin-top:12px; font-size:40px;line-height:60px;color:#f65541;">8<i style=" font-size:18px;">%</i>-15<i style=" font-size: 18px;">%</i></p>
					</#if>
					<p class="text_4" style=" font-size: 18px; line-height: 40px;">
					<#if Session.successHtml=='undefined'||Session.successHtml==null>
                      <a href="${base}/htmlreg.do" style="font-size:18px;height:40px;background-color:#f65541;">注册</a>
                      <p style="text-align:right;margin-top:15px;font-size: 14px;">已有账号？
						  <a href="${base}/htmllogin.do" title=""  style="font-size: 14px;color:#f65541">立即登录</a>
					  </p>
                      <#else>
                      <!--登录后显示下面这个a标签的内容  隐藏上面的a标签的内容-->
                      <a href="${base}/financePurchase/rechargeFinancePurchase.do"  style="font-size:18px;height:40px;background-color:#f65541;">充值</a>
                      </#if>
                    </p>	
			  </div>
	    </div>
        <!--  -->
	  </div>
   </div>
    <!--悬浮框-->
 </div>


	<!-- end:login form -->  
  <!-- end:banner -->