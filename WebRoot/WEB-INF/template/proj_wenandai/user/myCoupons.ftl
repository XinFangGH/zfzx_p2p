<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title>${systemConfig.metaTitle} - 我的优惠券</title>
    <meta name="description" content="${systemConfig.metaTitle} - 我的优惠券,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 我的优惠券,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="个人中心",m2="我的优惠券",m3="";</script>
<script type="text/javascript">
	window.onload=function() {
	$('a[rel*=leanModal]').leanModal({ top : 200 });
}
</script>
</head>
<body>
	<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
	<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
	<div class="main">
		<div class="user-cont">
			<!--左侧三级导航条 -->
			<div class="user-name-nav fl">
				<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
				<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
			</div>
			<div class="user-cont-fr fr">
				<div class="user-cont-right">
					<div class="user-invite" style="margin:0 auto;padding:0;">
						<div class="data-id">
							<p>优惠券激活码：
								<input type="text" id="colorText" class="colorful" />
								<a href="#" id="activateButton">激活</a>
								<span id="resultText" style="color:red"></span>
							</p>
						</div>
						<div class="per">
							<span>优惠券列表：</span>
							<a  onclick="searchData(5)" id="p5" href="javascript:void(0)" class="one">未使用</a>
							<a  onclick="searchData(10)" id="p10" href="javascript:void(0)">已使用</a>
							<a  onclick="searchData(4)" id="p4" href="javascript:void(0)">已过期</a>
							<a href="${base}/coupon/couponsAllListBpCoupons.do" style="border:0; text-decoration: underline;float:right;">查看更多</a>
						</div>
						<table width="95%" id="addCoupons" class="tab_css_three" style="margin:0 auto;" border="0" align="center" cellpadding="0" cellspacing="0">
							<!--ajax异步查询数据    未使用-->
						</table>

						<table width="95%" id="addCoupons2" class="tab_css_three" style="margin:0 auto;display:none;" border="0" align="center" cellpadding="0" cellspacing="0">
							<!--ajax异步查询数据    已使用-->
						</table>

						<table width="95%" id="addCoupons3" class="tab_css_three" style="margin:0 auto;display:none" border="0" align="center" cellpadding="0" cellspacing="0">
							<!--ajax异步查询数据    已过期-->
						</table>
						<table width="100%" border="0" cellspacing="0" class="tab_css_three" cellpadding="0" id="s7">
							<tr>
								<th align="center"  width="15%">优惠券</th>
								<th align="center"  width="15%">有效期</th>
								<th align="center"  width="15%">来源</th>
							</tr>

						</table>
						<ul id="pager7" class="pager"></ul>
						<div class="box1">
							<h2 class="middle">如何获取优惠券： <a id="go" href="#signup" rel="leanModal" name="signup" class="font-blue" style="float:right;margin-right:40px;font-size:15px;"><span class="middle" style="color:#ff7453;">《优惠券使用规则》</span></a></h2>
							<ul>
								<li>
									<div class="icon1"></div>
									<p class="middle">
										<span style="color:#ff7453;">邀请好友投资</span>
										<a class="btn" href="${base}/user/safeBpCustMember.do">立即邀请</a>
									</p>
								</li>
								<li>
									<div class="icon2"></div>
									<p class="middle">
										<span>投标返优惠券</span>
										<span class="txt">起息后返优惠券</span>
									</p>
								</li>
								<li>
									<div class="icon3"></div>
									<p class="middle">
										<span>积分兑换</span>
										<span class="txt">用主站积分兑换优惠券</span>
									</p>
								</li>
								<li>
									<div class="icon4"></div>
									<p class="middle">
										<span>参加活动</span>
										<span class="txt">参加、微信、微博活动</span>
									</p>
								</li>
							</ul>
						</div>
						<!--此处放置内容 结束-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	<div id="signup" style="height:300px!important;">
		<div id="signup-ct">
			<div id="signup-header">
				<div style="float:left; width:330px; height:40px; padding:10px 0px 0px 30px"><span class="large blue">优惠券使用规则</span></div>
				<div id="lean_overlay_close" style="float:right;  padding:20px 0px 0px 0px;width:60px; height:20px; text-decoration:underline; cursor:pointer;" class="normal blue">关闭</div>
			</div>
			<div style="float:left; width:650px; height:300px; margin:10px 0px 0px 30px; overflow:auto; line-height:30px;">
				<span id="bidLoad">此规则只针对使用了优惠券投标的用户，即当投标金额大于等于使用返现券面值则优惠券面值乘以返现比例为返现金额，反之则以投标金额*返现比例为返现金额，当标的正式放款时平台一次性以红包形式返到用户的账户余额</span>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src="${base}/js/common.js"></script>
<#--引入jsrender -->
<script type="text/javascript" src="${base}/js/page/jquery.pagination-1.2.3.js"></script>
<script type="text/javascript" src="${base}/js/jsrender/jsrender.min.js"></script>
<script type="text/javascript">
   $("#activateButton").click(function(){
    	var colorText = $("#colorText").val();
    	if(colorText==""){
    		$("#resultText").text("请填写该优惠券激活码");
    	}else{
    		$.ajax({
    			type:"POST",
	    		dataType:"JSON",
	    		url:basepath+"coupon/activateCouponsBpCoupons.do?colorText="+colorText,
	    		cache:false,
	    		success:function(responseText,statusText){
	    			if(responseText.result=="ok"){
	    				$("#resultText").text("激活成功");
	    				ajaxCoupons();
	    			}else{
		    			$("#resultText").text(responseText.result);
	    			}
	    		},
	    		error:function(request){
	    			$("#resultText").text("激活失败");
	    		}
    		})
    	}
    })
</script>
<script type="text/x-jsrender" id="page_tmp7">
	<tr>
		<td align='center' class="bg">
			<#--<em class="yiguoq"></em>-->
			
			<span style='float: left;width: 10px;padding-left: 80px;font-size: 14px;'>
			{{if couponType == 1}}
				优惠券
			{{else}}
				{{if couponType==2}}
					体验券
				{{else}}
				    {{if  couponType==3}}
				                   加息券
				    {{/if}}
				 {{/if}}
			{{/if}}	                   
			</span>
			<span style='float: right;padding: 15px 90px 0 0;'>
				{{if  couponType==3}}
				     <em>{{:couponValue}}</em>%
				{{else}}
					<em>￥{{:couponValue}}</em>
				{{/if}}
					
			</span>
			{{if couponStatus==4}}
				<i class="red-ygq"></i>
			{{else}}	
				{{if couponStatus==10}}
				<i class="red-ysy"></i>
				{{/if}}	
				{{if couponStatus==1}}
				<i class="red-ysy"></i>
				{{/if}}	
			{{/if}}	
			
		</td>
		<td align='center'>{{:couponStartDate}}<br>--<br>{{:couponEndDate}}</td>
		<td align='center'>{{:couponsDescribe}}</td>
	</tr>
</script>
<script type="text/javascript">
	$(function(){
		addPage(7,{couponStatus:5,start:0,limit:10});
		$(".user-invite .per a").eq(0)[0].className = "one";
	});
	
	function searchData(Num){/*debugger*/
		var param = {couponStatus:Num,start:0,limit:10};
		Array.prototype.slice.call($(".user-invite .per a")).forEach(function(item){/*debugger*/
			item.className = "";
		});
		$("#p"+Num).addClass("one");
		addPage(7,param);
	}


	function addPage(Nums,param){/*debugger;*/
		var url="${base}/coupon/listCouponsNewBpCoupons.do";
		if(Nums==7){
		    if(param!=null){

		    	params = param;
		    }else{
				params = {type:"CanTransfering",start:"0",limit:"10"};
		    }
		}
		/*$("#pager"+Nums).page({
		    pageSize: 5,
		    remote: {
		        url: url,  //请求地址
		        params: params,       //自定义请求参数
		        callback: function (result) {
		            //回调函数
		            //result 为 请求返回的数据，呈现数据
					if (result != null){

		            var data=result.result;
					//使用模板绑定数据
					var tr=$("#s"+Nums).find('tr')[0];
					$("#s7").empty().append(tr).append($("#page_tmp"+Nums).render(data));
					}

		        }
		    }
		});*/
	}
</script>

