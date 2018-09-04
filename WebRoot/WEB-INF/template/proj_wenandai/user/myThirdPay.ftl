<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

 
<title>${systemConfig.metaTitle} - 第三方支付</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript" src="${base}/js/user/editUserInfo.js?version=1638120"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/xy-lc.css">
<script type="text/javascript" src="${base}/js/user/authentication.js"></script>
<script type="text/javascript" src="${base}/js/jquery.fancybox-1.3.1.pack.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" /> 
<script type="text/javascript" src="${base}/js/upload.js"></script>
<script type="text/javascript">var m1="个人中心",m2="第三方支付",m3="个人资料";</script>
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

	   	<div class="cont-list contents" >
      	<div class="con-ui-list">
        	<ul class="finlist_title">
                  <li class="active">账户信息</li>
                  <li>第三方支付</li>
                  <li id="invite">认证信息</li>
                  <li>邀请信息</li>
                  <li id="bonusRecord">我的积分</li>
                  <li id="clickCoupons">我的优惠券</li>
                   <input type="hidden" id="show" value="${show}">
       		</ul>
        </div>
        <div class="finlist_hide">
        <ol class="rent">
	        <div class="user-infor" style="margin:0;">
		     <ul>
					<#if bpCustMember.isCheckPhone==1>
					<li class="none">
						<span class="accredi1"><input type="radio"  checked="checked"/>已认证</span>
						<span class="accredi-tit">手机号码</span>
						<span class="accredi-con">您绑定的手机：
						<em><#if bpCustMember.telphone!=null>${bpCustMember.telphone?substring(0,3)}****${bpCustMember.telphone?substring(7)}<#else></#if></em></span>
						<span class="accredi-open"><a href="${base}/user/getBpCustMember.do?typ=2&action=updateTelphone&retUrl=user/getBpCustMember.do?typ=1" style="color:#0096c4">修改</a></span>
					</li>
					<#else>
					<li>
						<span class="accredi"><input type="radio" disabled="true"/>未认证</span>
						<span class="accredi-tit">手机号码</span>
						<span class="accredi-con">手机号是您在升升投重要的身份凭证</span>
						<span class="accredi-open"><a href="${base}/user/getBpCustMember.do?typ=2&action=updateTelphone&retUrl=user/getBpCustMember.do?typ=1" style="color:#0096c4">开通</a></span>
					</li>
					</#if>
					<#if bpCustMember.customerType==1>
						<#if bpCustMember.isCheckCard==1>
							<li class="none">
								<span class="accredi1"><input type="radio"  checked="checked"/>已认证</span>
								<span class="accredi-tit">企业认证</span>
								<span class="accredi-con" ><#if bpCustMember!=null>
								<em style="color:red;" class="middle"><#if bpCustMember.truename?length gt 2>${bpCustMember.truename?substring(0,1)}****${bpCustMember.truename?substring(bpCustMember.truename?length-1,bpCustMember.truename?length)}<#else>*****${bpCustMember.truename}</#if></em><#else>
								</#if></span>
							</li>
							<#if bpCustMember!=null>
								<li>
									<span class="accredi" style="width:120px;margin-right:20px; display:inline-block;"></span>
									<span class="accredi-con" style="width:160px; padding-left:162px; color:red;">法人姓名：<#if bpCustMember.legalPerson!=""><#if bpCustMember.legalPerson?length gt 2>${bpCustMember.legalPerson?substring(0,1)}***${bpCustMember.legalPerson?substring(bpCustMember.legalPerson?length-1,bpCustMember.legalPerson?length)}<#else>${(bpCustMember.legalPerson)!}</#if><#else></#if></span> 
									<span  class="accredi-con" style="width:250px; color:red;">组织机构代码：<#if bpCustMember.cardcode!=""><#if bpCustMember.cardcode?length gt 2>${bpCustMember.cardcode?substring(0,1)}****${bpCustMember.cardcode?substring(bpCustMember.cardcode?length-1,bpCustMember.cardcode?length)}<#else>***${(bpCustMember.cardcode)!}</#if><#else>***</#if></span>
									<span class="accredi-open" style="width:300px; color:red;">营业执照号码： <#if bpCustMember.businessLicense!=""><#if bpCustMember.businessLicense?length gt 2>${bpCustMember.businessLicense?substring(0,1)}****${bpCustMember.businessLicense?substring(bpCustMember.businessLicense?length-1,bpCustMember.businessLicense?length)}<#else>***${(bpCustMember.businessLicense)!}</#if><#else>***</#if></span>
								</li>
							<#else>
							</#if>
					
						<#else>
							<li>
								<span class="accredi"><input type="radio" disabled="true"/>未认证</span>
								<span class="accredi-tit">企业认证</span>
								<span class="accredi-con" ><#if bpCustMember!=null>
								<em style="color:red;" class="middle"><#if bpCustMember.truename?length gt 6>${bpCustMember.truename?substring(0,4)}***${bpCustMember.truename?substring(bpCustMember.truename?length-2,bpCustMember.truename?length)}<#else>${(bpCustMember.truename)!}</#if></em><br><#else>
								</#if>
								是账户所有权归属的最主要判断依据，企业认证会在开通资金托管账户过程中同时完成</span>
							</li>
							<#if bpCustMember!=null>
								<li>
									<span class="accredi" style="width:120px;margin-right:20px; display:inline-block;"></span>
									<span class="accredi-con" style="width:130px; padding-left:162px; color:red;">法人姓名：<#if bpCustMember.legalPerson!=""><#if bpCustMember.legalPerson?length gt 2>${bpCustMember.legalPerson?substring(0,0)}***${bpCustMember.legalPerson?substring(1,2)}<#else>${(bpCustMember.legalPerson)!}</#if><#else></#if></span> 
									<span  class="accredi-con" style="width:250px; color:red;">组织机构代码：<#if bpCustMember.cardcode!="">${(bpCustMember.cardcode)!}<#else></#if></span>
									<span class="accredi-open" style="width:300px; color:red;">营业执照号码： <#if bpCustMember.businessLicense!="">${(bpCustMember.businessLicense)!}<#else></#if></span>
								</li>
							<#else>
							</#if>
							
						</#if>
					<#else>
						<#if bpCustMember.isCheckCard==1>
							<li class="none">
								<span class="accredi1"><input type="radio"  checked="checked"/>已认证</span>
								<span class="accredi-tit">实名认证</span>
								<span class="accredi-con">
								<#if bpCustMember!=null>
								<#if bpCustMember.truename?length gt 2>${bpCustMember.truename?substring(0,0)}***${bpCustMember.truename?substring(1,2)}<#else>${(bpCustMember.truename)!}</#if>
								</#if>&nbsp;&nbsp;
								<#if bpCustMember==null><#else>
								 	<#if bpCustMember.cardcode?length lt 15>${bpCustMember.cardcode}<#else>${bpCustMember.cardcode?substring(0,4)}****${bpCustMember.cardcode?substring(14)}</#if>
								 </#if>
								</span>
							</li>
						<#else>
							<li>
								<span class="accredi"><input type="radio" disabled="true"/>未认证</span>
								<span class="accredi-tit">实名认证</span>
								<span class="accredi-con">是账户所有权归属的最主要判断依据，实名认证会在开通资金托管账户过程中同时完成</span>
								<#-- <span class="accredi-open"><a href="${base}/user/getBpCustMember.do?typ=2&action=updateName&retUrl=user/getBpCustMember.do?typ=1" style="color:#0096c4">开通</a></span>-->
							</li>
						</#if>
					</#if>
					
					
					
					<#if bpCustMember.thirdPayFlagId!=null>
					<li class="none">
						<span class="accredi1"><input type="radio"  checked="checked"/>已认证</span>
						<span class="accredi-tit">资金托管账户</span>
						<span class="accredi-con">${bpCustMember.thirdPayFlagId!}</span>
					</li>
					<#else>
					<li>
						<span class="accredi"><input type="radio" disabled="true"/>未认证</span>
						<span class="accredi-tit">资金托管账户22</span>
						<span class="accredi-con">由第三方（${payType}支付）提供资金托管服务，保障资金独立与安全</span>
						<span class="accredi-open"><a href="${base}/thirdreg.do" style="color:#0096c4">开通</a></span>
					</li>
					</#if>
					<#if bpCustMember.isCheckEmail==1>
					<li class="none">
						<span class="accredi1"><input type="radio"  checked="checked"/>已认证</span>
						<span class="accredi-tit">电子邮箱</span>
						<span class="accredi-con">
						 <#if bpCustMember.isCheckEmail==1><#if emailName??><#if emailName?length gt 3>${emailName[0..3]}</#if></#if>****@${emailNameAfter!}<#else> ${bpCustMember.email!}</#if>
						</span>
					</li>
					<#else>
					<li>
						<span class="accredi"><input type="radio" disabled="true"/>未认证</span>
						<span class="accredi-tit">电子邮箱</span>
						<span class="accredi-con">绑定后账户与产品变动会随时通知到邮箱</span>
						<span class="accredi-open"><a href="${base}/emailreg.do?type=2&action=email&retUrl=user/getBpCustMember.do" style="color:#0096c4">开通</a></span>
					</li>
					</#if>
					<li >
						<span class="accredi"style="width:120px;margin-right:20px;"></span>
						<span class="accredi-tit"style="padding-left:119px;">登录密码</span>
						<span class="accredi-con"style="width:454px;"><#if userloginlogs!=null>上次登录时间：${userloginlogs.loginTime?string('yyyy-MM-dd HH:mm:ss')}<#else>初次登录</#if></span>
						<span class="accredi-open"><a href="${base}/user/getBpCustMember.do?typ=2&action=updatePwd&retUrl=user/getBpCustMember.do?typ=1" style="color:#0096c4">修改</a></span>
					</li>
					<li class="none">
						<span class="accredi" style="width:120px;margin-right:20px;"></span>
						<span class="accredi-tit"style="padding-left:119px;">推荐码</span>
						<span class="accredi-con" style="width:454px;" id="plainCode">${bpCustMember.plainpassword!}</span>
					   <span class="accredi-open"><a id="copy_b" href="javascript:void(0);" style="color:#0096c4">复制</a></span>
					</li>
					<li class="none">
						<span class="accredi" style="width:120px;margin-right:20px;"></span>
						<span class="accredi-tit" style="padding-left:119px;">推荐链接</span><span id="recommandurl" style="width:500px;">${mypath}htmlreg.do?recommand=${bpCustMember.plainpassword!}</span>
						<span class="accredi-open"><a id="copy_a" href="javascript:void(0);" style="color:#0096c4">复制</a></span>
					</li>
					<li class="none">
						<span class="accredi" style="width:120px;margin-right:20px;"></span>
						<span class="accredi-tit"style="padding-left:119px;">推荐人</span>
						<span class="accredi-con" style="width:454px;" id="recommandPerson_span">${bpCustMember.recommandPerson!}</span>
					   <span class="accredi-open"><a id="put_b" href="javascript:void(0);" style="color:#0096c4">录入</a></span>
					</li>
				</ul>
	        </div>
        </ol>
         <ol>
	        <div class="user-infor" style="margin:0 auto;padding:0;" >
	        <ul>
			    <li >
					<span class="accredi">支付账号：</span>
					<span class="accredi-con">${bpCustMember.getThirdPayFlagId()!}</span>
				</li>
		        <li >
					<span class="accredi">账户总金额：</span>
					<span class="accredi-con">${commonResponse.getBalance()!}</span>
				</li>
				<li >
					<span class="accredi">账户可用余额：</span>
					<span class="accredi-con">${commonResponse.getAvailableAmount()!}</span>
				</li>
				<li >
					<span class="accredi">账户冻结金额：</span>
					<span class="accredi-con">${commonResponse.getFreezeAmount()!}</span>
				</li>
				<li >
					<span class="accredi">支付银行卡号：</span>
					<span class="accredi-con">${commonResponse.getBankCode()!}</span>
				</li>
				<li >
					<span class="accredi">支付银行：</span>
					<span class="accredi-con">${commonResponse.getBankName()!}</span>
				</li>
       	 	</ul>
	        </div>
        </ol>
        <ol>
	        <div class="user-infor" style="margin:0 auto;padding:0;" >
	            	<!--此处放置内容-->
	            		<table width="100%" class="tab_css_three" border="0" align="center" cellpadding="0" cellspacing="0">
							  <tr>
							  	<th></th>
							    <th align="left">认证材料</th>
							    <th align="center">状态</th>
							  </tr>
							  <!-- 必要申请资料开始-->
							  
							  <tr><td colspan="3" align="left"><b>必要申请资料</b></td></tr>
							  <#list basisMaterialList as list>
							   <#if list.materialState==1>
								  <tr><td></td>
								    <td align="left">
								    	${list.materialName}
								    </td>
								    <td align="center">
								    	<#if list.status=="">
								    		<span style="color:#DCB5FF"><b>未上传</b></span>
								    		<span style="color:#DCB5FF">&nbsp;&nbsp;|</span>
								    		<span style="color:#84C1FF;cursor:pointer" onClick="newMaterialDialog(${list.materialId},'')"  target="_self">（上传资料）</span>
								    	</#if>
								    	<#if list.status=="1">
							    			<span style="color:#00DB00"><b>待审核 </b></span>
							    			<span style="color:#DCB5FF">&nbsp;&nbsp;|</span>
							    			<span style="color:#84C1FF;cursor:pointer" onClick="newMaterialDialog(${list.materialId},'')"  target="_self">（上传资料）</span>
							    			<span style="color:#DCB5FF">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
							    			<#list list.webFinanceApplyUploadsList as uploadList>
							    				<#if uploadList_index!=0>
								    				<a rel="group_${uploadList.materialId}" class="spec_img" href="${base}/${uploadList.files}" title="${uploadList.materialstype}"><i class="fancy_big"></i>
								    				<img  src="" /></a>
								                </#if>
							    			</#list>
							    			 <a rel="group_${list.materialId}" class="spec_img" href="${base}/${list.imgUrl}" title="${list.materialName}"><i class="fancy_big"></i>
								                <img  src="" /><span style="color:#2894FF">查&nbsp;&nbsp;看</span></a>
							                
							    		</#if>
							    		<#if list.status=="2">
						    				<span style="color:red"><b>已驳回</b></span>
						    				<span style="color:#DCB5FF">&nbsp;&nbsp;|</span>
						    				<span style="color:#84C1FF;cursor:pointer" onClick="newMaterialDialog(${list.materialId},'')"  target="_self">（上传资料）</span>
						    				<span style="color:#DCB5FF">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
								    		<#list list.webFinanceApplyUploadsList as uploadList>
								    				<#if uploadList_index!=0>
									    				<a rel="group_${uploadList.materialId}" class="spec_img" href="${base}/${uploadList.files}" title="${uploadList.materialstype}(${uploadList.rejectReason})"><i class="fancy_big"></i>
									    				<img  src="" /></a>
									                </#if>
								    		</#list>
								    			 <a rel="group_${list.materialId}" class="spec_img" href="${base}/${list.imgUrl}" title="${list.materialName}(${list.rejectReason})"><i class="fancy_big"></i>
									                <img  src="" /><span style="color:#2894FF">查看(驳回原因)</span></a>
						    			</#if>
						    			<#if list.status=="3">
					    					<span style="color:blue"><b>已认证</b></span>
					    					<span style="color:#DCB5FF">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
						    					<#list list.webFinanceApplyUploadsList as uploadList>
							    				<#if uploadList_index!=0>
								    				<a rel="group_${uploadList.materialId}" class="spec_img" href="${base}/${uploadList.files}" title="${uploadList.materialstype}"><i class="fancy_big"></i>
								    				<img  src="" /></a>
								                </#if>
							    			</#list>
							    			 <a rel="group_${list.materialId}" class="spec_img" href="${base}/${list.imgUrl}" title="${list.materialName}"><i class="fancy_big"></i>
								                <img  src="" /><span style="color:#2894FF">查&nbsp;&nbsp;看</span></a>
					    				</#if>
								    </td>
								  </tr>
								 </#if>
							  </#list>
							  <!-- 必要申请资料结束-->
							  
							  
							  <!-- 可选申请资料开始-->
							  <tr><td colspan="3" align="left"><b>可选申请资料</b></td></tr>
							  <#list basisMaterialList as list>
							   <#if list.materialState==2>
								  <tr><td></td>
								    <td align="left">
								    	${list.materialName}
								    </td>
								    <td align="center">
											<#if list.status=="">
								    		<span style="color:#DCB5FF"><b>未上传</b></span>
								    		<span style="color:#84C1FF;cursor:pointer" onClick="newMaterialDialog(${list.materialId},'')"  target="_self">（上传资料）</span>
								    	</#if>
								    	<#if list.status=="1">
							    			<span style="color:#00DB00"><b>待审核 </b></span>
							    			<span style="color:#DCB5FF">&nbsp;&nbsp;|</span>
							    			<span style="color:#84C1FF;cursor:pointer" onClick="newMaterialDialog(${list.materialId},'')"  target="_self">（上传资料）</span>
							    			<span style="color:#DCB5FF">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
							    			<#list list.webFinanceApplyUploadsList as uploadList>
						    				<#if uploadList_index!=0>
							    				<a rel="group_${uploadList.materialId}" class="spec_img" href="${base}/${uploadList.files}" title="${uploadList.materialstype}"><i class="fancy_big"></i>
							    				<img  src="" /></a>
							                </#if>
						    			</#list>
						    			 <a rel="group_${list.materialId}" class="spec_img" href="${base}/${list.imgUrl}" title="${list.materialName}"><i class="fancy_big"></i>
							                <img  src="" /><span style="color:#2894FF">查&nbsp;&nbsp;看</span></a>
							                
							    		</#if>
							    		<#if list.status=="2">
						    				<span style="color:red"><b>已驳回</b></span>
						    				<span style="color:#DCB5FF">&nbsp;&nbsp;|</span>
						    				<span style="color:#84C1FF;cursor:pointer" onClick="newMaterialDialog(${list.materialId},'')"  target="_self">（上传资料）</span>
						    				<span style="color:#DCB5FF">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
						    				<#list list.webFinanceApplyUploadsList as uploadList>
						    				<#if uploadList_index!=0>
							    				<a rel="group_${uploadList.materialId}" class="spec_img" href="${base}/${uploadList.files}" title="${uploadList.materialstype}"><i class="fancy_big"></i>
							    				<img  src="" /></a>
							                </#if>
						    			</#list>
						    			 <a rel="group_${list.materialId}" class="spec_img" href="${base}/${list.imgUrl}" title="${list.materialName}"><i class="fancy_big"></i>
							                <img  src="" /><span style="color:#2894FF">查&nbsp;&nbsp;看</span></a>
						    			</#if>
						    			<#if list.status=="3">
					    					<span style="color:blue"><b>已认证</b></span>
					    					<span style="color:#DCB5FF">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
					    					<#list list.webFinanceApplyUploadsList as uploadList>
						    				<#if uploadList_index!=0>
							    				<a rel="group_${uploadList.materialId}" class="spec_img" href="${base}/${uploadList.files}" title="${uploadList.materialstype}"><i class="fancy_big"></i>
							    				<img  src="" /></a>
							                </#if>
						    			</#list>
						    			 <a rel="group_${list.materialId}" class="spec_img" href="${base}/${list.imgUrl}" title="${list.materialName}"><i class="fancy_big"></i>
							                <img  src="" /><span style="color:#2894FF">查&nbsp;&nbsp;看</span></a>
					    				</#if>
							    	</td>
								  </tr>
								 </#if>
							  </#list>
							  <!-- 可选申请资料结束-->
							  
							 <!-- <tr>
							    <td rowspan="3" align="center">升升投记录</td>
							    <td align="left">还清笔数（+1分/笔、加分间隔28天、上限20分）</td>
							    <td align="center">0笔</td>
							  </tr>
							  <tr>
							    <td align="left">逾期次数（-1分）</td>
							    <td align="center">0次</td>
							  </tr>
							  <tr>
							    <td align="left">严重逾期次数（-30次）</td>
							    <td align="center">0次</td>
							  </tr>-->
						</table>
            	

						
					<!--此处放置内容 结束-->	
	        </div>
        </ol>
       <ol>
	        <div class="user-invite" style="margin:0 auto;padding:0;" >
	            	<!--此处放置内容-->
	            		<table width="100%" class="tab_css_three" border="0" align="center" cellpadding="0" cellspacing="0">
							   <#if invite?size gt 0>
							  <tr>
							    <th align="center">用户名</th>
							    <th align="center" colspan="2">真实姓名</th>
							    <th align="center">手机号码</th>
							    <th align="center">邮箱</th>
							    <th align="center" style="width:190px;">注册时间</th>
							  </tr>
							 
							  <#list invite as list>
							  <tr>
							    <td align="center">${list.loginname}</td>
							    <td colspan="2" align="center"><#if list.truename!=null||list.truename!=''>${list.truename?substring(0,1)}*<#else>未认证</#if></td>
							    <td align="center">${list.telphone?substring(0,list.telphone?length-4)}****</td>
							    <td align="center">${list.email}</td>
							    <td align="center">${list.registrationDate?string("yyyy-MM-dd HH:mm:ss")}</td>
							  </tr>
							  </#list>
							  <#else>
							  <tr><td></td></tr>
							  <tr>
							   <td align="center" colspan="3">您暂时没有邀请信息</td>
							  </tr>
							  <br/>
							  <br/>
							  <br/>
							  <br/>
							  
							  </#if>
							  <!-- 可选申请资料结束-->
							  
							 <!-- <tr>
							    <td rowspan="3" align="center">升升投记录</td>
							    <td align="left">还清笔数（+1分/笔、加分间隔28天、上限20分）</td>
							    <td align="center">0笔</td>
							  </tr>
							  <tr>
							    <td align="left">逾期次数（-1分）</td>
							    <td align="center">0次</td>
							  </tr>
							  <tr>
							    <td align="left">严重逾期次数（-30次）</td>
							    <td align="center">0次</td>
							  </tr>-->
						</table>
            	

						
					<!--此处放置内容 结束-->	
	        </div>
        </ol>
        <!--我的积分-->
         <ol>
	        <div class="user-invite" style="margin:0 auto;padding:0;" >
	        	<div class="integral_data">
	        		<ul>
	        			<li><i class="lock1"></i><span class="text big">可用积分</span></li>
	        			<li class="blue bigger" id="usableRecordNumber"></li>
	        		</ul>
	        		<ul>
	        			<li><i class="lock2"></i><span class="text big">累计积分</span></li>
	        			<li  class="green bigger" id="totalRecordNumber"></li>
	        		</ul>
	        		<ul class="none">
	        			<li><i class="lock3"></i><span class="text big">已用积分</span></li>
	        			<li  class="red bigger" id="useRecordNumber"></li>
	        		</ul>
	        	</div>
	        	<div class="keep">
	        		<div class="big" style="float:left; width:100px;margin-left:10px;">积分记录：</div>
	        		<div style="width:170px; height:30px; float:left;">
	        			<select id="sel" onchange="sel()" class="colorful" style="width:150px;">
	        				<option value="1">最近一个月</option>
	        				<option value="3">最近三个月</option>
	        				<option value="33">三个月以上</option>
	        			</select>
					</div>
					<a href="${base}/activity/activityAllListBpActivityManage.do">积分兑换</a>
	        	</div>
	            	<!--此处放置内容-->
	            	<span  style="float:right;margin-right:40px;"><a href="${base}/bonusSystem/pageListWebBonusRecord.do" style="cursor:pointer">查看更多</a></span>
	            		<table width="90%" id="addTr" class="tab_css_three" style="margin:0 auto;" border="0" align="center" cellpadding="0" cellspacing="0">							
	            			<!--ajax异步查询数据-->
						</table>
            	
				<div class="box">
					<h2 class="big" >积分兑换：<span  style="float:right;margin-right:40px;"><a href="${base}/activity/activityAllListBpActivityManage.do" style="cursor:pointer">查看更多</a></span></h2>
					<ul id="addli">
						
					</ul>
				</div>
						
					<!--此处放置内容 结束-->	
	        </div>
        </ol>
        <!--我的优惠券-->
         <ol>
	        <div class="user-invite" style="margin:0 auto;padding:0;" >
	        		<div class="data-id">
	        			<p>优惠券激活码：
	        			<input type="text" id="colorText" class="colorful"/>
	        			<a href="#" id="activateButton">激活</a>
	        			<span id="resultText" style="color:red"></span>
	        			</p>
	        			
	        		</div>
	        		<div class="per">
	        			<span>优惠券列表：</span>
	        			<a id="wsyCoupons" href="#" class="one">未使用</a>
	        			<a id="ysyCoupons" href="#">已使用</a>
	        			<a id="ygqCoupons" href="#">已过期</a>
	        			<span  style="float:right;margin-right:40px;"><a href="${base}/coupon/couponsAllListBpCoupons.do" style="cursor:pointer">查看更多</a></span>
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
            	
	            <div class="box1">
						<h2 class="big" >如何获取优惠券： <a id="go" href="#signup" rel="leanModal" name="signup" class="font-blue" style="float:right;margin-right:40px;font-size:15px;"><span class="blue middle">《优惠券使用规则》</span></a></h2>
						
						<ul>
							<li>
								<p class="middle"><i class="icon1"></i><span class="txt">邀请好友投资</span></p>
								<p class="btn"><a href="${base}/user/safeBpCustMember.do">立即邀请</a></p>
							</li>
							<li>
								<p class="middle"><i class="icon2"></i><span class="txt">投标返优惠券</span></p>
								<p class="normal">起息后返优惠券</p>
							</li>
							<li>
								<p class="middle"><i class="icon3"></i><span class="txt">积分兑换</span></p>
								<p class="normal">用主站积分兑换优惠券</p>
							</li>
							<li>
								<p class="middle"><i class="icon4"></i><span class="txt">参加活动</span></p>
								<p class="normal">参加、微信、微博活动</p>
							</li>						
						</ul>
					</div>
					<!--此处放置内容 结束-->	
	        </div>
        </ol>
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
      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>