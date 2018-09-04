<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 优惠券列表</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="个人中心", m2="我的优惠券", m3="";</script>
<script type="text/javascript">
</script>
</head>
<body >
	 <!-- header --><!-- navbar -->
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
					<h2 class="big-tit">优惠券列表</h2>
					<div class="cont-list">

						<div>
							<div class="user-invite">
								<div class="per" style="float:right;margin-right:40px;">
									<a id="s5" href="javascript:void(0);" onclick="searchData(5);" class="one">未使用</a>
									<a id="s10"  href="javascript:void(0);" onclick="searchData(10);">已使用</a>
									<a id="s4"  href="javascript:void(0);" onclick="searchData(4);">已过期</a>
								</div>
							</div>
							<table width="100%" border="0" cellspacing="0" class="tab_css_three" cellpadding="0" id="s7">
								<tr>
									<th class="top-line" align="center" width="15%">优惠券</th>
									<th class="top-line" align="center" width="15%">有效期</th>
									<th class="top-line" align="center" width="15%">来源</th>
								</tr>
							</table>
							<ul id="pager7" class="pager"><ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--end: main -->
		<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	</body>
</html>
<script type="text/javascript" src="${base}/js/common.js"></script>
<script type="text/javascript" src="${base}/js/user/message.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<#--引入jsrender -->
<script type="text/javascript" src="${base}/js/page/jquery.pagination-1.2.3.js"></script>
<script type="text/javascript" src="${base}/js/jsrender/jsrender.min.js"></script>

<script type="text/x-jsrender" id="page_tmp7">
	<tr>
		<td align='center' class="bg">
			<#--<em class="yiguoq"></em>-->
			<#--<span>
				{{if  couponType==3}}
				     <em>{{:couponValue}}</em>%
				{{else}}
					<em>{{:couponValue}}</em>￥
				{{/if}}
			</span>-->
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
					   <em>{{:couponValue}}%</em>
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
		<td align='center'><a href="javascript:0;" title="{{:couponsDescribe}}">{{substr:couponsDescribe}}</a></td>
	</tr>
</script>
<script type="text/javascript">
	$(function(){
		$("#pager7").empty();
		addPage(7,'');
		Array.prototype.slice.call($(".user-invite .per a")).forEach(function(item){debugger
			item.className = "";
		});
		$(".user-invite .per a").eq(0)[0].className = "one";
	});
	
	$.views.converters("substr", function(val) {  
	    if(val==""||val=='undefined'||val==null){debugger
	        return "";
	    }else{
	        if(val.length<15){
	        	return val;
	        }else{
		    	var str = val.substring(0,15);
	        	return str + '...';
	        }
	    }
	});



	function searchData(Num){
		Array.prototype.slice.call($(".user-invite .per a")).forEach(function(item){debugger
			item.className = "";
		});
		var param = {detail:"1",couponStatus:Num,start:0,limit:10}
		$("#s"+Num).addClass("one");
		$("#pager7").empty();
		addPage(7,param);
	}


	function addPage(Nums,param){debugger;
		var url="${base}/coupon/listCouponsNewBpCoupons.do";
		if(Nums==7){
		    if(param!=null&&param!=""&&param!='undefined'){
		    	params = param;
		    }else{
				params = {detail:"detail",type:"CanTransfering",couponStatus:"5",start:"0",limit:"10"};
		    }
		}
		$("#pager"+Nums).page({
		    pageSize: 10,
		    remote: {
		        url: url,  //请求地址
		        params: params,       //自定义请求参数
		        callback: function (result) {
		            //回调函数
		            //result 为 请求返回的数据，呈现数据
		            var data=result.result;
		            //使用模板绑定数据
		           var tr=$("#s"+Nums).find('tr')[0];
		           $("#s7").empty().append(tr).append($("#page_tmp"+Nums).render(data));
		           console.log(data);
		        }
		    }
		});
	}
</script>