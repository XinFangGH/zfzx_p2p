<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

 
<title>${systemConfig.metaTitle} - 我的积分</title>
    <meta name="description" content="${systemConfig.metaTitle} - 我的积分,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 我的积分,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/xy-lc.css">
<link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" /> 

<script type="text/javascript">var m1="个人中心",m2="我的积分",m3="个人资料";</script>
<script type="text/javascript">
function hid(){
	document.getElementById("recommandDiv").style.display="none";
}
window.onload=function() {
	$(".contents .finlist_title li").each(function(index) { //带参数遍历各个选项卡
		var className = $(this).attr("class");
		var show = $("#show").val();
		
		if(show=="Repayment"){
			$(".contents .finlist_hide ol:eq(0)").show()
			.siblings().hide();
		}
		if(show=="loan"){
			$(".contents .finlist_hide ol:eq(1)").show()
			.siblings().hide();
		}
		if(show=="invite"){
			$(".contents .finlist_title li.active").removeClass("active"); //移除已选中的样式
			$("#invite").addClass("active"); //增加当前选中项的样式
			$(".contents .finlist_hided ol:eq(2)").show()
			.siblings().hide();
		}
		$(this).click(function() {
			//注册每个选卡的单击事件
			$(".contents .finlist_title li.active").removeClass("active"); //移除已选中的样式
			$(this).addClass("active"); //增加当前选中项的样式
			//显示选项卡对应的内容并隐藏未被选中的内容
			$(".contents .finlist_hide ol:eq(" + index + ")").show()
			.siblings().hide();
		});
	});
	
	$('a[rel*=leanModal]').leanModal({ top : 200 });
	
	 <#list basisMaterialList as list>
	 	<#list list.webFinanceApplyUploadsList as uploadList>
	 	$("a[rel=group_${uploadList.materialId}]").fancybox({ 
	        'titlePosition' : 'over', 
	        openEffect: 'elastic',
	        'cyclic'        : true, 
	        'centerOnScroll': true,
	        'titleFormat'   : function(title, currentArray, currentIndex, currentOpts) { 
	                    return '<span id="fancybox-title-over">' + (currentIndex + 1) + 
	                    ' / ' + currentArray.length + (title.length ? '   ' + title : '') + '</span>'; 
	        },
	        onStart: function () {
	            $('body').css('overflow','hidden');
	        },
	        onClosed: function () {
	            $('body').css('overflow','auto');
	        }
	        
	    });
	 	</#list>
  	</#list>
	
}

/*
function copyText(){
	 var obj = $('#recommandurl').text();
	 // window.clipboardData.setData('Text',obj);
	 copyToClipboard(obj);
	 alert("复制成功!");
	 }
function copyToClipboard(maintext){
  if (window.clipboardData){
    window.clipboardData.setData("Text", maintext);
    }else if (window.netscape){
      try{
        netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
    }catch(e){
        alert("该浏览器不支持一键复制！n请手工复制文本框链接地址～");
    }
    var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
    if (!clip) return;
    var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
    if (!trans) return;
    trans.addDataFlavor('text/unicode');
    var str = new Object();
    var len = new Object();
    var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
    var copytext=maintext;
    str.data=copytext;
    trans.setTransferData("text/unicode",str,copytext.length*2);
    var clipid=Components.interfaces.nsIClipboard;
    if (!clip) return false;
    clip.setData(trans,null,clipid.kGlobalClipboard);
  }
  alert("以下内容已经复制到剪贴板nn" + maintext);
}*/



</script>
<style type="text/css">
textarea {
border-style: solid;
border-width: 1px;
border-color: #a8a8a8 #d8d8d8 #d8d8d8 #a8a8a8;
border-radius: 3px;
background-color: #f4f4f4;
padding: 5px;
overflow: auto;
resize: none;
}
</style>

</head>
<body>
<!--整体布局
<div class="docment docment-711-234">-->

<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">

<div class="main">
	<div class="user-cont">
	<!--start: 左侧页面-->
	 	<div class="user-name-nav fl">
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
		    <#--<#if bpCustMember.entCompanyType==1> 
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_guarantee.ftl">
			<#elseif bpCustMember.perCompanyType==1 >
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_financial.ftl">
			 <#else>
			 <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">  
			 </#if>-->
		</div>
    <!-- 左侧页面 end -->
    <!-- 右侧主体内容 start  右侧内容宽740px -->   	
	   <div  class="user-cont-fr fr">
	   		<div class="user-cont-right">
	   			<div class="head-num">
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-nine fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">累计推荐人（人）</p>
	   						<p class="middle"><em  id="usableRecordNumber">10</em>人</p>
	   					</div>
	   				</div>
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-three fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">近一月推荐人（人）</p>
	   						<p class="middle"><em id="totalRecordNumber">2</em>人</p>
	   					</div>
	   				</div>
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-ten fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">近一月推荐人（人）</p>
	   						<p class="middle"><em id="useRecordNumber">2</em>人</p>
	   					</div>
	   				</div>
	   			</div>
				<div class="keep">
	        		<div class="big" style="float:left; width:100px;margin-left:10px;">积分记录：</div>
	        		<div style="width:170px; height:30px; float:left;">
	        			<select id="sel" onchange="sell()"  class="colorful" style="width:150px;"><#--onchange="sel()"-->
	        				<option value="1">最近一个月</option>
	        				<option value="3">最近三个月</option>
	        				<option value="33">三个月以上</option>
	        			</select>
					</div>
					<a href="${base}/activity/activityAllListBpActivityManage.do">积分兑换</a>
					<a href="${base}/bonusSystem/pageListWebBonusRecord.do" style="cursor:pointer; float:right;font-size:12px;">查看更多</a>
	        	</div>
		   		<div class="cont-list contents"  style="margin-bottom:20px;">
			    	<div class="user-invite">
	            	<!--此处放置内容-->
	            		<table width="100%" id="addTr" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0" >	
	            			<!--ajax异步查询数据-->
	            			<tr>
	            				<th align="center" width="20%">时间<th>
	            				<th align="center" width="10%">积分<th>
	            				<th align="center" width="70%">详情<th>
	            			</tr>
						</table>
            			<ul id="pager1" class="pager"><ul>
				
						
					<!--此处放置内容 结束-->	
	        	</div>
        		</div>
        		<h2 class="big-tit">积分兑换：
        			<a href="${base}/activity/activityAllListBpActivityManage.do" class="fr" style="cursor:pointer;color:#fff;padding:2px 10px;font-size:12px; background:#fd7754; border-radius:5px;">查看更多</a>
        		</h2> 	
        		<div class="cont-list contents">
        			<!--新样式-->
	        		<div class="trade-credits" id="s2">
						<ul id="s3">
							<li>
								<h5 class="kee_bg"></h5>
								<p class='large'><em>￥</em>0</p>
								<p class="normal">【有限期0天】</p>
								<p class='jifen normal'>需要0分</p>
								<p class='btn'><a href='javascript:;' onClick="activitybutton(46,1)">立即兑换</a></p>
							</li>
						</ul>
					</div>
					<!--end:新样式-->
        		<div class="box">
					<ul id="addli" class="pager1">
						
					</ul>
				</div>
        
           </div>
      </div>
	  </div>
</div>

<!-- 弹出框 -->
<div class="xy-lc-box" id="recommandDiv" style="display: none">
        <div class="xy-ui-box-mask"></div>
        <div class="xy-lc-box-show" style="width:500px;height:230px;top: 90%">
            <a class="xy-xbox-close" title="Close" href="javascript:void(0);" onClick="hid();" style="display: inline;">×</a>
            <div class="xy-lc-box-content" style="height:230px;">
                <h1>推荐码</h1>
                <br/>
                      <div style="padding-left:64px">
                      		<span style="font-size:14px;padding-left:50px">推荐码:</span>
                    	    <input style="width:154px;" id="recommand" name="recommandPerson" type="text" maxlength="16" size="20" class="colorful1" onblur="chkRecommand(this)"/>
	                        <span  style="width:10px;float:right;padding-right:140px;padding-top:8px;display:none" id ="recommandImg"></span>
	                        <span  style="width:100px;float:right;padding-right:58px;padding-top:8px;" id ="recommandMark"></span>
                      </div>
                      <br/>
                      <div class="xy-form-btn-box_mm" >
                        <a style="padding-left:48px" href="#" class="xy-form-btn-link"><input type="button"  onclick="saveRecommand()"  value="保存" class="xy-form-btn-box-input"></a>
                     </div>
               </div>
            </div>
        </div>
    </div> 
</div>
<!-- 弹出框 结束-->
 
<!--end: Container -->
<div id="signup" style="height:300px!important;">
<div id="signup-ct">
	<div id="signup-header">
    	<div style="float:left; width:330px; height:40px; padding:10px 0px 0px 30px"><span  class="large blue">优惠券使用规则</span></div>
        <div id="lean_overlay_close" style="float:right;  padding:20px 0px 0px 0px;width:60px; height:20px; text-decoration:underline; cursor:pointer;"  class="normal blue">关闭</div>
	</div>
    <div style="float:left; width:650px; height:300px; margin:10px 0px 0px 30px; overflow:auto; line-height:30px;">
    		<span id="bidLoad">此规则只针对使用了优惠券投标的用户，即当投标金额大于等于使用返现券面值则优惠券面值乘以返现比例为返现金额，反之则以投标金额*返现比例为返现金额，当标的正式放款时平台一次性以红包形式返到用户的账户余额</span>
    </div>
</div>
</div>
	<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	</body>
</html>
<script type="text/javascript" src="${base}/js/upload.js"></script>
<script type="text/javascript" src="${base}/js/user/authentication.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript" src="${base}/js/user/editUserInfo.js?version=1638120"></script>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>

<#--jsrender 资源加载-->
<script type="text/javascript" src="${base}/js/page/jquery.pagination-1.2.3.js"></script>
<script type="text/javascript" src="${base}/js/jsrender/jsrender.min.js"></script>
<script src='${base}/js/sale_jsrender.js'></script>

<script type="text/x-jsrender" id="page_tmp1">
	<tr>
		<td align="center">{{:createTime}}<td>
		<td align="center">{{:recordNumber}}<td>
		<td align="center">{{:bonusDescription}}<td>
	</tr>
</script>
<script type="text/x-jsrender" id="temp">
	<div class="num-icon fl">
		<div class="icon-img icon-img-nine fl"></div>
		<div class="num-data fl">
			<p class="normal">可用积分</p>
			<p class="middle"><em  id="usableRecordNumber">{{:totalRecordNumber - useRecordNumber}}</em>分</p>
			<input type="hidden" id="usableRecordNumber1" value="{{:totalRecordNumber - useRecordNumber}}" />
		</div>
	</div>
	<div class="num-icon fl">
		<div class="icon-img icon-img-three fl"></div>
		<div class="num-data fl">
			<p class="normal">累计积分</p>
			<p class="middle"><em id="totalRecordNumber">{{:totalRecordNumber}}</em>分</p>
		</div>
	</div>
	<div class="num-icon fl">
		<div class="icon-img icon-img-ten fl"></div>
		<div class="num-data fl">
			<p class="normal">已用积分</p>
			<p class="middle"><em id="useRecordNumber">{{:useRecordNumber}}</em>分</p>
		</div>
	</div>
</script>
<script type="text/x-jsrender" id="page_tmp2">
	<li>
		{{if couponType == 1}}
			<!--返现券-->
			<h5 class="kee_bg"></h5>
		{{else couponType == 2}}
			<!--体验券-->
			<h5 class="kee_bg1"></h5>
		{{else couponType == 3}}
			<!--加息券-->
			<h5 class="kee_bg2"></h5>
		{{/if}}
		<p class='large'><em>￥</em>{{:parValue}}</p>
		<p class="normal">【有限期 {{:validNumber}} 天】</p>
		<p class='jifen normal'>需要{{:needIntegral}}分</p>
		<p class='btn'><a href='javascript:;' onClick="activitybutton({{:activityId}},{{:needIntegral}})">立即兑换</a></p>
	</li>
</script>
<script type="text/javascript">
    $(function(){
    	addPage(1,{sel:1,start:0,limit:10});
    	addPage1(2);
    	showStatistic();
    	//showStatistic1();
    });
    
    
 	function sell(){
		var val = $("#sel").val();
		var params = {sel:val,start:0,limit:10};
		$("#pager1").empty();
		addPage(1,params);
 	}   
    
    //查询统计数据	
	function showStatistic(){
	    var new_map0={age:2,start:0,limit:10};
	    ajaxFunction("${base}/bonusSystem/loadMyIntegralDataWebBonusRecord.do",new_map0,callback0_0);
	}	
	
    //获取债权交易的统计数据
	 function callback0_0(data){
	 	var result=data.result;
	 	$(".head-num").empty().html($("#temp").render(result));//累计赚取收益
	 }
    
    
    //查询我的积分列表
	function addPage(Nums,params){
		var url="${base}/bonusSystem/loadMyIntegralWebBonusRecord.do";
		var param={};
		$("#pager"+Nums).page({
		    pageSize: 5,
		    remote: {
		        url: url,  //请求地址
		        params:params ,       //自定义请求参数
		        callback: function (result) {
		            //回调函数
		            //result 为 请求返回的数据，呈现数据
		            var data=result.result;
		            //使用模板绑定数据
		             var tr=$("#addTr").find('tr')[0];
		           $("#addTr").empty().append(tr).append($("#page_tmp1").render(data));
		        }
		    }
		});
    }
    
     	//查询积分兑换的数据
		function showStatistic1(){
		    var new_map0={age:2,start:0,limit:10};
		    ajaxFunction("${base}/activity/showMyCouponsBpActivityManage.do?tab=3",new_map0,callback0_1);
		}	
		
		function callback0_1(data){
		 	var result=data.result;
		 	console.log(result);
		 	$("#s3").empty().html($("#page_tmp2").render(data));//累计赚取收益
		 }
		
    
    	function addPage1(Nums){debugger;
			var url="${base}/activity/showMyCouponsBpActivityManage.do?tab=3";
			var param={};
			$("#addli").page({
			    pageSize: 10,
			    remote: {
			        url: url,  //请求地址
			        params: {start:0,limit:10}, //自定义请求参数
			        callback: function (result) {
			            //回调函数
			            //result 为 请求返回的数据，呈现数据
			            var data=result.result;
			            //使用模板绑定数据
			           $("#s3").empty().append($("#page_tmp2").render(data));
			           console.log(data);
			        }
			    }
			});
		}
</script>



