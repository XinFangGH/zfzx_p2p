<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title>${systemConfig.metaTitle} - 我的债权</title>
    <meta name="description" content="${systemConfig.metaTitle} - 我的债权,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 我的债权,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<script type="text/javascript">var m1="个人中心",m2="我的债权",m3="";</script>
<script type="text/javascript">
	$(function(){
		$(".cont-list .finlist_title li").click(function(){
			$(this).addClass("active").siblings().removeClass();
			$(".cont-list .finlist_hide ol").eq($(this).index()).addClass("rent").siblings().removeClass();
		});
	})
</script>

</head>
<body>
		<div class="main">
			<div class="user-cont">
				<!--左侧三级导航条 -->
				 <#--<div class="user-name-nav fl">
				      <#if bpCustMember.entCompanyType==1>
			    	  <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_guarantee.ftl">
			    	  <#elseif bpCustMember.perCompanyType==1 >
			    	  <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_financial.ftl"> 
			    	  <#else>
			    	  <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">  
			    	  </#if>
				   </div> -->  
				<div class="user-name-nav fl">
					<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
				</div>
				<input type="hidden" id="tagId" />
				<div class="user-cont-fr fr">
					<div class="user-cont-right">
					   <input type="hidden" id="memberId" value="${bpCustMember}"/>
						<div class="data-list">
							<div class="date-left fl" style="padding-top: 25px;">
								<div class="icon-img">
									<span class="data-icon"></span>
								</div>
								<div class="title" style="padding-top: 15px;">
									<p class="f20">理财数据</p>
								</div>
							</div>
							<div class="data-right fl" style="width:440px;" id="po3">
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-01"></i>预售中（个）</p>
									<p class="middle pd-30"><em>0</em>个</p>
								</div>
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-02"></i>招标中（个）</p>
									<p class="middle pd-30"><em>0</em>个</p>
								</div>
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-03"></i>回款中（个）</p>
									<p class="middle pd-30"><em>1</em>个</p>
								</div>
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-04"></i>已完成（个）</p>
									<p class="middle pd-30"><em>1</em>个</p>
								</div>
							</div>
							<div class="data-right fr" style="width:200px;padding:50px 0 0 0;" id="po2">
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-01"></i>已齐标（个）</p>
									<p class="middle pd-30"><em>0</em>个</p>
								</div>
							</div>
						</div>
						<div class="dashedline"></div>
						<div class="data-list" style="padding-top:20px;">
							<div class="date-left fl">
								<div class="icon-img">
									<span class="data-icon"></span>
								</div>
								<div class="title" style="padding-top: 15px;">
									<p class="f20">资金数据</p>
								</div>
							</div>
							<div class="data-right fl" id="po1">
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-01"></i>应派本金（元）</p>
									<p class="middle pd-30"><em>0.00</em>元</p>
								</div>
								<div class="data-invent">
									<p class="normal"><i class="icon-num icon-02"></i>应派利息（元）</p>
									<p class="middle pd-30"><em>0.00</em>元</p>
								</div>
							</div>
						</div>
						<div class="cont-list">
							<ul class="finlist_title">
								<li class="active">回款中</li>
								<li>已齐标</li>
								<li>招标中</li>
								<li>预售中</li>
								<li>已完成</li>
								<li>退出审核</li>
							</ul>
							<div class="finlist_hide">
								<ol class="rent" id="7">
									<div class="tab-list">
										<div class="worm-tips fl">
											<span class="icon1">回款中</span>
										</div>
										<p style="padding:5px 30px 10px 0; float:left;">
											理财计划名称:
											<input id="mmName7" type="text" style="width:120px" name="mmName" class="colorful"  value="" " />
											<span style="font-size:14px; margin: 2px 0px 0px 5px; padding:6px 18px; id="selectUseraccountinfo" name="selectUseraccountinfo" onclick="searchData()" class="buttonoblue7">查&nbsp;询</span><#--color:#fd7754;"-->
										</p>
										<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s7">
											<tr>
												<th align="center">计划名称</th>
												<th align="center">招标金额</th>
												<th align="center">预期收益率</th>
												<th align="center">投资期限</th>
												<th align="center">锁定期至</th>
												<th align="center">计划派息日</th>
												<th align="center">派息金额</th>
												<th align="center">查看</th>
												<th align="center">操作</th>
											</tr>
										</table>
										<ul id="pager7" class="pager" style="margin:20px auto"></ul>
									</div>
								</ol>
								<ol id="2">
									<div class="tab-list">
										<div class="worm-tips fl">
											<span class="icon1">已齐标</span>
										</div>
										<p style="padding:5px 30px 10px 0; float:left;">
											理财计划名称:
											<input id="mmName2" type="text" style="width:120px" name="mmName" class="colorful"  value="" " />
											<span style="font-size:14px; margin: 2px 0px 0px 5px; padding:6px 18px; id="selectUseraccountinfo" name="selectUseraccountinfo" class="buttonoblue2" onclick="searchData()">查&nbsp;询</span><#--color:#fd7754;"-->
										</p>
										<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s2">
											<tr>
												<th align="center">计划名称</th>
												<th align="center">招标金额</th>
												<th align="center">预期收益率</th>
												<th align="center">投资期限</th>
												<th align="center">投标开始日</th>
												<th align="center">加入人次</th>
											</tr>
										</table>
										<ul id="pager2" class="pager" style="margin:20px auto"></ul>
									</div>
								</ol>
								<ol id="1">
									<div class="tab-list">
										<div class="worm-tips fl">
											<span class="icon1">招标中</span>
										</div>
										<p style="padding:5px 30px 10px 0; float:left;">
											理财计划名称:
											<input id="mmName1" type="text" style="width:120px" name="mmName" class="colorful"  value="" " />
											<span style="font-size:14px; margin: 2px 0px 0px 5px; padding:6px 18px; id="selectUseraccountinfo" name="selectUseraccountinfo" class="buttonoblue1" onclick="searchData()">查&nbsp;询</span><#--color:#fd7754;"-->
										</p>
										<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s1">
											<tr>
												<th align="center">计划名称</th>
												<th align="center">招标金额</th>
												<th align="center">预期收益率</th>
												<th align="center">投资期限</th>
												<th align="center">投标开始日</th>
												<th align="center">加入人次</th>
												<th align="center">剩余可投金额</th>
											</tr>
										</table>
										<ul id="pager1" class="pager" style="margin:20px auto">
									</div>
								</ol>
								<ol id="5">
									<div class="tab-list">
										<div class="worm-tips fl">
											<span class="icon1">预售中</span>
										</div>
										<p style="padding:5px 30px 10px 0; float:left;">
											理财计划名称:
											<input id="mmName5" type="text" style="width:120px" name="mmName" class="colorful"  value="" " />
											<span style="font-size:14px; margin: 2px 0px 0px 5px; padding:6px 18px; id="selectUseraccountinfo" name="selectUseraccountinfo" class="buttonoblue1" onclick="searchData()">查&nbsp;询</span><#--color:#fd7754;"-->
										</p>
										<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s5">
											<tr>
												<th align="center">计划名称</th>
												<th align="center">招标金额</th>
												<th align="center">预期收益率</th>
												<th align="center">投资期限</th>
												<th align="center">预售时间</th>
												<th align="center">投标开始时间</th>
											</tr>
										</table>
										<ul id="pager5" class="pager" style="margin:20px auto"></ul>
									</div>
								</ol>
								<ol id="10">
									<div class="tab-list">
										<div class="worm-tips fl">
											<span class="icon1">已完成</span>
										</div>
										<p style="padding:5px 30px 10px 0; float:left;">
											理财计划名称:
											<input id="mmName10" type="text" style="width:120px" name="mmName" class="colorful"  value="" " />
											<span style="font-size:14px; margin: 2px 0px 0px 5px; padding:6px 18px; id="selectUseraccountinfo" name="selectUseraccountinfo" class="buttonoblue10" onclick="searchData()">查&nbsp;询</span><#--color:#fd7754;"-->
										</p>
										<table table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s10">
											<tr>
												<th align="center">计划名称</th>
												<th align="center">招标金额</th>
												<th align="center">预期收益率</th>
												<th align="center">投资期限</th>
												<th align="center">派息总金额</th>
												<th align="center">完成状态</th>
												<th align="center">查看</th>
												<th align="center">操作</th>
											</tr>
										</table>
										<ul id="pager10" class="pager" style="margin:20px auto"><ul>
									</div>
								</ol>
								<ol id="8">
									<div class="tab-list">
										<div class="worm-tips fl">
											<span class="icon1">退出审核</span>
										</div>
										<p style="padding:5px 30px 10px 0; float:left;">
											理财计划名称:
											<input id="mmName8" type="text" style="width:120px" name="mmName" class="colorful"  " />
											<span>状态：</span>
											<span>
            	  								<select style="width:80px;height:28px;" id="checkStatus"> 
            	 									<option value="">--请选择--</option>
            	 									<option value="0">未审核</option>
            	 									<option value="1">已通过</option>
            	 									<option value="3">已驳回</option>		
            	  								</select>
            	  							</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<span class="buttonoblue1" style="font-size:14px; padding:4px 15px;" onclick="searchData()" name="queryBtn">
												查&nbsp;询
											</span>
										</p>
										<table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s8">
											<tr>
												<th align="center">计划名称</th>
												<th align="center">投资人</th>
												<th align="center">购买金额</th>
												<th align="center">计息起日</th>
												<th align="center">计息止日</th>
												<th align="center">申请日期</th>
												<th align="center">状态</th>
												<th align="center">操作</th>
											</tr>
										</table>
										<ul id="pager8" class="pager" style="margin:20px auto"></ul>
									</div>
								</ol>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/user/mySaleContainer.ftl">
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/user/uploadImage.js"></script>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/payment.js"></script>
<script type="text/javascript" src="${base}/js/user/mmplan.js"></script>
<script src='${base}/js/sale_jsrender.js'></script>

<#-- jsrender引用相关的文件-->
<script type="text/javascript" src="${base}/js/page/jquery.pagination-1.2.3.js"></script>
<script type="text/javascript" src="${base}/js/jsrender/jsrender.min.js"></script>
<script type="text/javascript" src="${base}/js/common.js"></script>


<script type="text/javascript">
	//注册格式化金额的方法
	$.views.converters("formatNum", function(val) {
	 	var re = /(-?\d+)(\d{3})/;
	    while(re.test(val)) val = val.toString().replace(re,"$1,$2");
	    return val;
	});

	//格式化日期的方法
	$.views.converters("formartDate", function(val) {  
	    if(val==""||val=='undefined'||val==null){debugger
	        return "";
	    }else{
		    var date = new Date(val);
		    var date1 = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	    	return date1;
	    }
	});

	$(function () {
		var map1 = {};
		//理财信息
		getMySaleData();
		addPage(7);//查询回款中的数据
		var Num = $("#tagId").val();
	    //鼠标点击事件触发查询     
	 });
	 
	 function searchData(){debugger
		var Num = $("#tagId").val();
		var select = $("#checkStatus").val();
	    //鼠标点击事件触发查询     
			var val = $("#mmName"+$("#tagId").val()).val();
		//	if(val==null||val=='undefined'||val==''){
		//		showTip("mmName"+$("#tagId").val(), "查询内容不能为空");
		//		return false;
		//	}
			var params; 
			if(Num == 5){ //预售中
				params = {state:"1",mmName:val,isPresale:"ysz",start:"0",limit:"10"};
			}else if(Num == 8){//退出审核
				params = {state:"", checkStatus :select,mmName:val,start:"0",limit:"10"};			
			}else{
				params = {mmName:val,state:Num,start:"0",limit:"10"};
			}
			 $("#pager"+Num).empty();
			addPage($("#tagId").val(),params);
	 }
	 
	 
   //获取相应的统计信息
	function getMySaleData(){
	    var new_map0={age:2,start:0,limit:10};
	    ajaxFunction("${base}/user/loadMySaleDataBpCustMember.do",new_map0,callback0_0);
	}
    //获取标的的统计数据
	 function callback0_0(data){
	 	var result=data.result;
	 	$("#po3").empty().html($("#temp").render(result));
	 	$("#po2").empty().html($("#temp-1").render(result));
	 	$("#po1").empty().html($("#temp-2").render(result));//{unbackRepayment:formatNum(result.unbackRepayment,2),unbackInterest:formatNum(result.unbackInterest,2)}
	 }
	 
	function addPage(Nums,param){debugger;
		var url="${base}/user/showMyDebtBpCustMember.do";
		var param1={};
		if(Nums==7){//回款中的数据
		    if(param!=null&&param!='undefined'&&param!=''){
				params = param;
		    }else{
				params = {state:"7",start:"0",limit:"10"};
		    }
		}else if(Nums==2){//已齐标的数据
		 	if(param!=null&&param!='undefined'&&param!=''){
		 		params = param;
		 	}else{
				params = {state:"2",start:"0",limit:"10"};
		 	}
		}else if(Nums==1){//招标中的数据
			if(param!=null&&param!='undefined'&&param!=''){
			 	params = param;
			}else{
				params = {state:"1",start:"0",limit:"10"};
			}
					
		}else if(Nums==5){//预售中的数据
			if(param!=null&&param!='undefined'&&param!=''){
			 	params = param;
			}else{
				params = {state:"1",isPresale:"ysz",start:"0",limit:"10"};
			}
		}else if(Nums==10){//已完成的数据
			if(param!=null&&param!='undefined'&&param!=''){
			 	params = param;
			}else{
				params = {state:"10",start:"0",limit:"10"};
			}
		}else if(Nums==8){
			if(param!=null&&param!='undefined'&&param!=''){
			 	params = param;
			}else{
				params = {state:"",start:"0",limit:"10"};
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
		           $("#s"+Nums).empty().append(tr).append($("#page_tmp"+Nums).render(data));
		     	   $("#tagId").val(Nums);
		        }
		    }
		});
	}
</script>