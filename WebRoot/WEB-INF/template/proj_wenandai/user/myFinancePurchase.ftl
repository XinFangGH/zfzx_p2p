<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 我的散标</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="个人中心",m2="我的散标",m3="我的投资";</script>
    <style>
        #pager7,#pager1,#pager10,#pager-1,#pager30{
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }

        .m-pagination-page{
            width: auto;
            vertical-align: middle;
        }
    </style>

</head>
<body>
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<div class="main">
	<div class="user-cont">
	<!--start: 左侧页面-->
	 	<div class="user-name-nav fl">
			 <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
		    <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
		</div>
		<#--储存数据(个人账户总览的点击更多)-->
        <input type="hidden"  id="type" value="${type}" />
    	<!-- 左侧页面 end -->
    	<!-- 右侧主体内容 start  右侧内容宽740px -->   	
	   <div  class="user-cont-fr fr" >
	   		<div class="user-cont-right">
	   			<div class="head-num">
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-one fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">累计赚取收益(元)</p>
	   						<p class="middle" id="BenefitMoney"><em>0</em>元</p>
	   					</div>
	   				</div>
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-two fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">累计投资(笔)</p>
	   						<p class="middle" id="Investment"><em>0</em>笔</p>
	   					</div>
	   				</div>
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-three fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">累计投资金额(元)</p>
	   						<p class="middle" id="InvestMoney"><em>0</em>元</p>
	   					</div>
	   				</div>
	   			</div>
	   		<h2 class="big-tit">散标管理</h2>
		    <div class="cont-list">
		       <ul class="finlist_title">
		          <li id="backing" class="active">回款中</li>
		          <li id="biding">投标中</li>
		          <li id="alreadyClear">已结清</li>
		          <li id="Failure_bidding">投标失败</li>
		          <li id="Experience_mark">体验标</li>
		       </ul>
		       <span id="myState" style="display:none">${state}</span>
		       <div class="finlist_hide">
		       <!--回款中-->
		         <ol  class="rent" id="7">
		           <div class="tab-list">
		            <div class="worm-tips">
		            	 <span class="icon1">处于还款期中的产品</span>
		    	   </div>
		                <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s7">
		                    <tr >
		                      <th align="center" >名称</th>
		                      <th align="center" >投标时间</th>
		                      <th align="center" >投资金额</th>
		                      <th align="center" >投资期限</th>
		                      <th align="center" >年化利率</th>
		                      <th align="center">计划还款日</th>
		                      <th align="center">还款日应还金额</th>
		                      <th align="center">查看</th>
		                    </tr>
		              	</table>
                       <div id="pager7" style="/*width:100%;height:0px;*/"></div>
		            </div>

		       </ol>
		        <!--投标中-->
		       	<ol id="1">
		       	<div class="tab-list">
		       	 	<div class="worm-tips">
		            	 <span class="icon1">投标成功，还未进入还款期的产品</span>
		    	   </div>
		           <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s1">
		               <tr>
		                   <th class="top-line left-line" align="center" >名称</th>
		                   <th class="top-line" align="center" width="20%">投资时间</th>
		                   <th class="top-line" align="center"  width="20%">投资金额</th>
		                   <th class="top-line" align="center"  width="20%">投资期限</th>
		                   <th class="top-line" align="center"  width="20%">年化利率</th>
		               </tr>
		            </table>
		            <div id="pager1"  style="/*width:100%;height:0px;*/"></div>
		       </div>
		       </ol>
		       <!--已结清-->
				<ol id="10">
			     <div class="tab-list">
			      <div class="worm-tips">
			            	 <span class="icon1">已经完成全部还款的产品</span>
			    	   </div>
			                <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s10">
			                    <tr>
			                      <th align="center">名称</th>
			                      <th align="center">投资时间</th>
			                      <th align="center">投资金额</th>
			                      <th align="center">投资期限</th>
			                      <th align="center">年化利率</th>
			                      <th align="center">已回收金额</th>
			                      <th align="center">已赚金额</th>
			                      <th align="center">结清日期</th>
			                      <th align="center">查看</th>
			                    </tr>
			              	</table>
			              
			         </div>
			          <div id="pager10"  style="/*width:100%;height:0px;*/"></div>
			     </ol>
			     <!--投标失败-->
				<ol id="-1">
			       	<div class="tab-list">
			       	 <div class="worm-tips">
			            	 <span class="icon1">投标失败的产品</span>
			    	   </div>
			                <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s-1">
			                    <tr>
			                      <th align="center" >名称</th>
			                      <th align="center" >投资时间</th>
			                      <th align="center"  >投资金额</th>
			                      <th align="center"  >投资期限</th>
			                      <th align="center" >年化利率</th>
			                    </tr>
			              	</table>
			             
			            </div>
			              <div id="pager-1"   style="/*width:100%;height:0px;*/"></div>
			       </ol>
       				<!--体验标-->
			       <ol id="30">
			       	<div class="tab-list">
			       	 <div class="worm-tips">
			            	 <span class="icon1">体验标</span>
			    	 </div>
			         <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s30">
			                    <tr>
			                      <th align="center" >名称</th>
			                      <th align="center" >投资时间</th>
			                      <th align="center"  >投资金额</th>
			                      <th align="center"  >投资期限</th>
			                      <th align="center" >年化利率</th>
			                      <th align="center" >预期收益</th>
			                      <th align="center" >到期日</th>
			                      <th align="center" >状态</th>
			                    </tr>
			           </table>
			         
			        </div>
			         <div id="pager30"   style="/*width:100%;height:0px;*/"></div>
			       </ol>
     			</div> 
     		</div>
		</div>		  
	</div>
</div>
</div>							
<!--end: Container -->
      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</body>
</html>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript" src="${base}/js/bidplanpager.js"></script>	


<#-- jsrender引用相关的文件-->
<script type="text/javascript" src="${base}/js/page/jquery.pagination-1.2.3.js"></script>
<script type="text/javascript" src="${base}/js/jsrender/jsrender.min.js"></script>
<script type="text/javascript" src="${base}/js/common.js"></script>


<!--回款中模版-->
<script id="page_tmp7" type="text/x-jsrender">
	 <tr>
         <td align="center" class="td-top"  style="padding-left:15px;white-space:nowrap;overflow: hidden;text-overflow: ellipsis;"><a style="color:#0096c4;font-size:14px;" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:planId}}" title="{{:projectName}}">{{:projectName}}</a></td>
         <td align="center">{{:bidtime}}</td>
         <td align="center">{{:userMoney}}元</td>
         <td align="center">{{:loanLife}}</td>
         <td align="center">{{:interestRate}}%</td>
         <td align="center">{{:intentDate}}</td>
         <td align="center">{{:planPaymentsMoney}}元</td>
         <td align="center" >
      		<span>
          		<a href="javascript:void(0);" onClick="clickFinancing({{:planId}},'{{:orderNo}}','Financing')"  class="btn1">回款计划</a>
              	<a  href="{{isurl:url}} " class="btn2">合同</a>
          	</span>
          </td>
      <tr>
</script>


<!--投标中-->
<script id="page_tmp1" type="text/x-jsrender">
	<tr>
      <td align="center" class="td-top"  style="padding-left:15px;white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
            <a style="color:#0096c4;font-size:14px;" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId={{:planId}}" title="{{:projectName}}">{{:projectName}}</a>
      </td>
      <td align="center">{{:bidtime}}</td>
      <td align="center">{{:userMoney}}元</td>
      <td align="center">{{:loanLife}}</td>
      <td align="center">{{:interestRate}}%</td>
    </tr>
</script>


<!--已结清-->
<script id="page_tmp10" type="text/x-jsrender">
	<tr>
	    <td align="center" class="td-top"  style="padding-left:15px;white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
	        <a href="#" target="_blank"  title="{{:projectName}}"> {{:projectName}}</a>
	    </td>
	    <td align="center">{{:bidtime}}</td>
	    <td align="center">{{:userMoney}}元</td>
	    <td align="center">{{:loanLife}}</td>
	    <td align="center">{{:interestRate}}%</td>
	    <td align="center" >{{:factPaymentsMoney}}元</td>
	    <td align="center" >{{sub:factPaymentsMoney userMoney}}元</td>
	    <td align="center" >{{:finishDate}}</td>
	    <td align="center">
	    	<span><a href="javascript:void(0);" onClick="clickFinancing({{:planId}},'{{:orderNo}}','Financing')"  class="btn1">回款计划</a>
	    	<a  href="{{if url!=""}}${base}/financePurchase/downLoadFinancePurchase.do?customerType=invest&contractUrl={{:url}} {{else}} {{/if}}" class="btn2">合同</a>
	    	</span>
	    </td>
    </tr>
</script>


<!--已失败-->
<script id="page_tmp-1" type="text/x-jsrender">
	<tr>
    	<td align="left" class="td-top"  style="padding-left:15px;white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
	    	<a href="#" target="_blank" title="{{:projectName}}"> {{:projectName}}</a>
	    </td>
	    <td align="center">{{:bidtime}}</td>
	    <td align="center">{{:userMoney}}元</td>
	    <td align="center">{{:loanLife}}</td>
	    <td align="center">{{:interestRate}}%</td>                
    </tr>
</script>



<!--体验标-->
<script id="page_tmp30" type="text/x-jsrender">
	<tr>
        <td align="left" class="td-top"  style="padding-left:15px;white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
        <a style="color:#0096c4;font-size:14px;" href="${base}/creditFlow/financingAgency/getExperienceDetailPlManageMoneyPlan.do?mmplanId={{:bidId}}" title="{{:proName}}">{{:proName}}</a>
        </td>
        <td align="center">{{:bidTime}}</td>
        <td align="center">{{:payMoney}}元</td>
        <td align="center">{{:loanLife}}天</td>
        <td align="center">{{:interestRate}}%</td>                
        <td align="center">{{:plCount}}元</td>
        <td align="center">{{:repaymentDate}}</td>
        <td align="center">{{:orderNo}}</td>
    </tr>
</script>
<!--产品列表模版-->
<script id="temp" type="text/x-jsrender">
	<em>{{:allBenefit}}元</em>   	
</script>
<script id="temp1" type="text/x-jsrender">
	<em>{{:allBidMoney}}元</em>  	
</script>
<script id="temp2" type="text/x-jsrender">
	<em>{{:allBidTimes}}笔</em>    	
</script>
<script type="text/javascript">
	$(function () {
	    if($("#type").val()!=null&&$("#type").val()!='undefined'&&$("#type").val()!=''){
	    	addPage(1);
	    	$(".cont-list .finlist_hide ol").eq(1).addClass("rent");
	    	$("#backing").removeClass("active");
	    	$("#7").removeClass("rent");
	    	$("#biding").addClass("active");
	    }else{
	    	addPage(7);
	    }
		getBidData();
	});


	$.views.converters("formartDate", function(val,str) { 
		return  formartDate(val,str);  
	});

	$.views.converters("sub", function(var0,var1) {  
		var val=Number(var0)-Number(var1);
		var Dight   =   Math.round   (val*Math.pow(10,2))/Math.pow(10,2); 
		return  Dight;  
	});
	
	$.views.converters("substring", function(v,l) {  
		return  subString(v,l);  
	});

    function formatNum(num,n){//参数说明：num 要格式化的数字 n 保留小数位
	    //num = String(num.toFixed(n));
	    var re = /(-?\d+)(\d{3})/;
	    while(re.test(num)) num = num.toString().replace(re,"$1,$2");
	    return num;
	}
	
	$.views.converters("isurl", function(v) {  
		if(v==""||v==undefined){
			return "javascript:void(0)";
		}else{
			return "${base}/financePurchase/downLoadFinancePurchase.do?customerType=invest&contractUrl="+v;
		}
	});


	function addPage(params){
		var url="";
		var param={};
		if(params==30){//体验标
			url= "${base}/financePurchase/myExperienceFinancePurchase.do";
			param.planstate=params;
		}else{
			url= "${base}/financePurchase/myManageMoneyFinancePurchase.do";
			param.planstate=params;
	}

	$("#pager"+params).page({
	    pageSize: 10,
	    remote: {
	        url:url,  //请求地址
	        params:param,       //自定义请求参数
	        callback: function (result) {
                console.log(result);
	            //回调函数
	            //result 为 请求返回的数据，呈现数据
	            var data=result.result;
	            //使用模板绑定数据
	            var tr=$("#s"+params).find('tr')[0];
	           $("#s"+params).empty().append(tr).append($("#page_tmp"+params).render(data));
	        }
	    }
	});
}

	function getBidData(){
	    var new_map0={age:2,start:0,limit:10};
	    ajaxFunction("${base}/user/queryBidInfoBpCustMember.do",new_map0,callback0_0);
	}

    //获取标的的统计数据
	function callback0_0(data){
	 	var result=data.result;
	 	$("#BenefitMoney").empty().html($("#temp").render({allBenefit:formatNum(result.allBenefit,2)}));//累计赚取收益
	 	$("#InvestMoney").empty().html($("#temp1").render({allBidMoney:formatNum(result.allBidMoney,2)}));//累计投资金额
	 	$("#Investment").empty().html($("#temp2").render(formatNum(result,2)));//累计投资笔数
	}

</script>