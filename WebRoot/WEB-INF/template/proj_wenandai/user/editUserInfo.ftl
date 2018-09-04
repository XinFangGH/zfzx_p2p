<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

 
<title>${systemConfig.metaTitle} - 账户信息</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/xy-lc.css">
<link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" /> 
<#--<link rel='stylesheet' type='text/css' href='${base}/static/bootstrap/css/bootstrap.min.css'>-->

<link rel='stylesheet' type='text/css' href='${base}/static/js/jquery.imgareaselect/css/imgareaselect-default.css'>
<link rel='stylesheet' type='text/css' href='${base}/static/css/style.css'>


<script type="text/javascript">var m1="个人中心",m2="账户设置",m3="个人资料";</script>
<script type="text/javascript" src="https://cdn.staticfile.org/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
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
<script type="text/javascript">
$(function(){
    $("#picpath").change(function(){
       /* alert(this.value);*/
        document.form1.path.value=this.value;
    	$("form:first").submit();
    });
    $("#selectImg").click(function(){/*debugger*/
        $("#picpath").click();
    });
});
</script>
</head>
<body>
<!--整体布局
<div class="docment docment-711-234">-->

<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">

<div class="main">
	<div class="user-cont">
	<!--start: 左侧页面-->
	 	<div class="user-name-nav fl">
			 <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
			 <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">  
		</div>
    <!-- 左侧页面 end -->
    <!-- 右侧主体内容 start  右侧内容宽740px -->   	
	   <div  class="user-cont-fr fr">
		   <div class="user-cont-right">
		       <div class="cont-list contents" >
		       		<ul class="finlist_title">
						<li id ="acount" class="active">账户中心</li>
						<#--<li id="newsMess">消息中心</li>-->
					</ul>
					<div class="finlist_hide">
						<ol id="ol1" class="rent">
							 <div class="revisions-user-name">
				   <div class="revisions-head fl">
				        <#if imgurl  != "" && imgurl  != null>
			       			<p><img src="${base}/${imgurl}" style="margin-top:15px;width:61px;height:61px;border-radius:50%;"/></p>
			       		<#else>
			       			<p><img src="${base}/theme/${systemConfig.theme}/images/uc/urer-name-pic.png" style="width:61px;height:61px;border-radius:50%;"/></p>
			       		</#if>	
			       		<p style="margin-top:18px;"><a id="selectImg" href="javascript:;" class="myModa-btn">选择文件</a></p>
						<form id="form1" name = "form1" action ='${base}/user/uploadAvatarBpCustMember.do' enctype = 'multipart/form-data'  method = 'POST'>
								<input type="hidden" value="${bpCustMember.id}" name="mark" />
								<input type="file" accept="image/png, image/jpeg, image/gif, image/jpg" id="picpath" name="atvatar_image" class="fl" style="opacity:0 "/>
								<input type='hidden' name="path" readonly />
								<input type="submit" class="fl" style="opacity:0; " />
						</form>		
						<!-- Modal -->
						<div class="modal hide fade" id="myModal" tabindex="-1" role="dialog">
							<div class="modal-header">
								<button class="close" type="button" data-dismiss="modal">×</button>
							    <h3 id="myModalLabel">上传图片</h3>
							</div>
						<div class="modal-body">
						<!--引入图片选择-->
							<div id="image_area">
							<#--	<#if imgurl  != "" && imgurl  != null>
				       				<p><img src="${base}/${imgurl}" style="margin-top:15px; width:350px; height:350px" /></p>
				       			<#else> -->
				       				<img id="biuuu" src="${base}/theme/${systemConfig.theme}/images/uc/urer-name-pic.png" title="求真相">
				       			<#--</#if>-->	
					        </div>
						<!--<div id ="upload_area">
							<form id="form1" name = "form1" action ='${base}/user/uploadAvatarBpCustMember.do' enctype = 'multipart/form-data'  method = 'POST'>
								<input type="hidden" value="${bpCustMember.id}" name="mark" />
								<input type="file" id="picpath" name="atvatar_image">
								<a id="select" href="javascript:void(0);" class="button"> 上传照片</a>
								<input type='hidden' name="path" readonly>
						<div id="submit_button">
							<a href="javascript:void(0);" class='button'>确认</a>
						</div>-->
					</div>
						<!--结束图片选择-->
				</div>
				       <!--引入bootstrap结束-->
			       </div>
					<!--引入bootstrap开始-->
			       	<#--<input id="btntext" type="button" value="添加文本组件" data-toggle="modal" data-target="#myModal" />-->
				
			       <div class="safes-rating fl">
				       <p><em class="icon-clock"></em>您的安全等级：</p>
				       <p class="pdl-20"><span class="jdt"><span class="jdtbg" style="width:${safetyLevel!}%"></span></span></p>
				       <p><em class="icon-clock-01"></em>风险测评结果：${bpCustMember.grade}</p>
				       <p class="pdl-20"> 
					       <a  name="signup" onClick="check('${bidId}','${afterMoney}','${yearInterestRate}',true)" href="javascript:void(0);">
								重新测评
						   </a>
					   </p>
					   <input type="hidden" value="1" id="personCenter" />
					   <input type="hidden" id="memberId" value="${bpCustMember.id}" />
			       </div>
		       </div>
				<div class="user-infor">
					     		<ul>
									<#if bpCustMember.isCheckPhone==1>
									<li class="none">
										<span class="accredi">已认证</span>
										<span class="accredi-tit">手机号码</span>
										<span class="accredi-con">您绑定的手机：
										<em><#if bpCustMember.telphone!=null>${bpCustMember.telphone?substring(0,3)}****${bpCustMember.telphone?substring(7)}<#else></#if></em></span>
										<span class="accredi-open"><a href="${base}/user/getBpCustMember.do?typ=2&action=updateTelphone&retUrl=user/getBpCustMember.do?typ=1"  >修改</a></span>
									</li>
									<#else>
									<li>
										<span class="accredi">未认证</span>
										<span class="accredi-tit">手机号码</span>
										<span class="accredi-con">手机号是您在升升投重要的身份凭证</span>
										<span class="accredi-open"><a href="${base}/user/getBpCustMember.do?typ=2&action=updateTelphone&retUrl=user/getBpCustMember.do?typ=1" >开通</a></span>
									</li>
									</#if>
									<#if bpCustMember.customerType==1>
										<#if bpCustMember.isCheckCard==1>
											<li class="none">
												<span class="accredi">已认证</span>
												<span class="accredi-tit">企业认证</span>
												<span class="accredi-con" ><#if bpCustMember!=null>
												<em style="color:red;" class="middle"><#if bpCustMember.truename?length gt 2>${bpCustMember.truename?substring(0,1)}****${bpCustMember.truename?substring(bpCustMember.truename?length-1,bpCustMember.truename?length)}<#else>*****${bpCustMember.truename}</#if></em><#else>
												</#if></span><span class="accredi-open" ><a href="${base}/user/toChangeEnterpriseBpCustMember.do?type=1" >修改企业</a></span>      <span class="accredi-open" style="float: "><a href="${base}/user/toChangeEnterpriseBpCustMember.do?type=2" >修改法人</a></span>

											</li>
											<#if bpCustMember!=null>
												<li>
													<span style="width:120px;margin-right:20px; display:inline-block;"></span>
													<span class="accredi-con" style="width:160px; padding-left:105px; color:red;">法人姓名：<#if bpCustMember.legalPerson!=""><#if bpCustMember.legalPerson?length gt 2>${bpCustMember.legalPerson?substring(0,1)}***${bpCustMember.legalPerson?substring(bpCustMember.legalPerson?length-1,bpCustMember.legalPerson?length)}<#else>${(bpCustMember.legalPerson)!}</#if><#else></#if></span>

                                                    <span  class="accredi-con" style="width:250px; color:red;">组织机构代码：<#if bpCustMember.cardcode!=""><#if bpCustMember.cardcode?length gt 2>${bpCustMember.cardcode?substring(0,1)}****${bpCustMember.cardcode?substring(bpCustMember.cardcode?length-1,bpCustMember.cardcode?length)}<#else>***${(bpCustMember.cardcode)!}</#if><#else>***</#if></span>
													<span class="accredi-open" style="width:300px; color:red;border: 0;text-align: left;">营业执照号码： <#if bpCustMember.businessLicense!=""><#if bpCustMember.businessLicense?length gt 2>${bpCustMember.businessLicense?substring(0,1)}****${bpCustMember.businessLicense?substring(bpCustMember.businessLicense?length-1,bpCustMember.businessLicense?length)}<#else>***${(bpCustMember.businessLicense)!}</#if><#else>***</#if></span>
                                                 	</li>
											<#else>
											</#if>
									
										<#else>
											<li>
												<span class="accredi">未认证</span>
												<span class="accredi-tit">企业认证</span>
												<span class="accredi-con" ><#if bpCustMember!=null>
												<em style="color:red;" class="middle"><#if bpCustMember.truename?length gt 6>${bpCustMember.truename?substring(0,4)}***${bpCustMember.truename?substring(bpCustMember.truename?length-2,bpCustMember.truename?length)}<#else>${(bpCustMember.truename)!}</#if></em><br><#else>
												</#if>
												是账户所有权归属的最主要判断依据，企业认证会在开通资金托管账户过程中同时完成</span>
											</li>
											<#if bpCustMember!=null>
												<li>
													<span class="accredi" style="background:none;display:inline-block;"></span>
													<span class="accredi-con" style="width:130px; padding-left:182px; color:red;">法人姓名：<#if bpCustMember.legalPerson!=""><#if bpCustMember.legalPerson?length gt 2>${bpCustMember.legalPerson?substring(0,0)}***${bpCustMember.legalPerson?substring(1,2)}<#else>${(bpCustMember.legalPerson)!}</#if><#else></#if></span> 
													<span  class="accredi-con" style="width:220px; color:red;">组织机构代码：<#if bpCustMember.cardcode!="">${(bpCustMember.cardcode)!}<#else></#if></span>
													<span class="accredi-open" style="width:300px; color:red; border:0; text-align:left;">营业执照号码： <#if bpCustMember.businessLicense!="">${(bpCustMember.businessLicense)!}<#else></#if></span>
												</li>
											<#else>
											</#if>
											
										</#if>
									<#else>
										<#if bpCustMember.isCheckCard==1>
											<li class="none">
												<span class="accredi">已认证</span>
												<span class="accredi-tit">实名认证</span>
												<span class="accredi-con">
												<#if bpCustMember!=null>
												<#if bpCustMember.truename?length gt 2>${bpCustMember.truename?substring(0,0)}**${bpCustMember.truename?substring(2,3)}<#else>${(bpCustMember.truename)!}</#if>
												</#if>&nbsp;&nbsp;
												<#if bpCustMember==null><#else>
												 	<#if bpCustMember.cardcode?length lt 15>${bpCustMember.cardcode}<#else>${bpCustMember.cardcode?substring(0,4)}****${bpCustMember.cardcode?substring(14)}</#if>
												 </#if>
												</span>
											</li>
										<#else>
											<li>
												<span class="accredi">未认证</span>
												<span class="accredi-tit">实名认证</span>
												<span class="accredi-con">是账户所有权归属的最主要判断依据，实名认证会在开通资金托管账户过程中同时完成</span>
												<#-- <span class="accredi-open"><a href="${base}/user/getBpCustMember.do?typ=2&action=updateName&retUrl=user/getBpCustMember.do?typ=1"  >开通</a></span>-->
											</li>
										</#if>
									</#if>
									
									
									
									<#if bpCustMember.thirdPayFlagId!=null>
									<li class="none">
										<span class="accredi">已认证</span>
										<span class="accredi-tit">资金托管账户</span>
										<span class="accredi-con">${bpCustMember.thirdPayFlagId!}</span>
										<#--
										<#if  bpCustMember.thirdPayConfig!=''>
											<#if bpCustMember.thirdPayConfig!='allinpayConfig'>
												<span class="accredi-open"><a href="${base}/user/queryThirdPayBpCustMember.do"  >查看</a></span>
											</#if>
										</#if>
										-->
									</li>
									<#else>
									<li>
										<span class="accredi">未认证</span>
										<span class="accredi-tit">资金托管账户</span>
										<span class="accredi-con">由富滇银行（${payType}支付）提供资金托管服务，保障资金独立与安全</span>
							 			<span class="accredi-open"><a href="${base}/thirdreg.do"  >开通</a></span>
									</li>
									</#if>
									<#if bpCustMember.isCheckEmail==1>
									<li class="none">
										<span class="accredi">已认证</span>
										<span class="accredi-tit">电子邮箱</span>
										<span class="accredi-con">
										 <#if bpCustMember.isCheckEmail==1><#if emailName??><#if emailName?length gt 3>${emailName[0..3]}</#if></#if>****@${emailNameAfter!}<#else> ${bpCustMember.email!}</#if>
										</span>
									</li>
									<#else>
									<li>
										<span class="accredi">未认证</span>
										<span class="accredi-tit">电子邮箱</span>
										<span class="accredi-con">绑定后账户与产品变动会随时通知到邮箱</span>
										<span class="accredi-open"><a href="${base}/emailreg.do?type=2&action=email&retUrl=user/getBpCustMember.do"  >开通</a></span>
									</li>
									</#if>
									<li >
										<#--<span class="accredi">已设置</span>-->
										<span class="accredi"  style="background:#FFF;">已设置</span>
										<span class="accredi-tit">登录密码</span>
										<span class="accredi-open"><a href="${base}/user/getBpCustMember.do?typ=2&action=updatePwd&retUrl=user/getBpCustMember.do?typ=1"  >修改</a></span>
									</li>
									<li >
										<#--<span class="accredi">已设置</span>-->
										<span class="accredi"  style="background:#FFF;">已设置</span>
										<span class="accredi-tit">上次登录信息</span>
										<span class="accredi-con"style="width:454px;"><#if userloginlogs!=null>上次登录时间：${userloginlogs.loginTime?string('yyyy-MM-dd HH:mm:ss')}<#else>初次登录</#if></span>
										<!--<span class="accredi-open"><a href="${base}/user/getBpCustMember.do?typ=2&action=updatePwd&retUrl=user/getBpCustMember.do?typ=1"  >修改</a></span>-->
									</li>
									<li class="none">
										<span class="accredi-tit"style="padding-left:63px;">推荐码</span>
										<span class="accredi-con" style="width:454px;" id="plainCode">${bpCustMember.plainpassword!}</span>
									    <span class="accredi-open"><a id="copy_b" data-clipboard-action="copy" data-clipboard-target="#plainCode"  href="javascript:void(0);" >复制</a></span>
									</li>
									<li class="none">
										
										<span class="accredi-tit" style="padding-left:63px;">推荐链接</span><span id="recommandurl" style="width:500px;"><a href="https://www.zxzbol.com:443/htmlreg.do?recommand=${bpCustMember.plainpassword!}" target="_blank">https://www.zxzbol.com:443/htmlreg.do?recommand=${bpCustMember.plainpassword!}</a></span>
										<span class="accredi-open"><a id="copy_a" data-clipboard-action="copy" data-clipboard-target="#recommandurl" href="javascript:void(0);"  >复制</a></span>
									</li>

                                    <li class="none">
                                        <input type="hidden" value="${hide}" id="showRec"/>
                                        <span class="accredi-tit"style="padding-left:63px;">我的邀请二维码</span>
                                        <div id = "qrcodeid"  ></div>
                                    </li>
                                    <li class="none">
                                        <input type="hidden" value="${hide}" id="showRec"/>
                                        <span class="accredi-tit"style="padding-left:63px;">推荐人</span>
                                        <span class="accredi-con" style="width:454px;" id="recommandPerson_span">${bpCustMember.recommandPerson!}</span>
									<#--<span class="accredi-open1"><a id="put_b" href="javascript:void(0);"  >录入</a></span>-->
                                    </li>


									<#--<#if bpCustMember.recommandPerson != null>
									<#else>
									<li class="none">
									   <input type="hidden" value="${hide}" id="showRec"/>
										<span class="accredi-tit"style="padding-left:63px;">推荐人</span>
										<span class="accredi-con" style="width:454px;" id="recommandPerson_span">${bpCustMember.recommandPerson!}</span>
									   &lt;#&ndash;<span class="accredi-open1"><a id="put_b" href="javascript:void(0);"  >录入</a></span>&ndash;&gt;
									</li>
									</#if>-->
								</ul>
				       		 </div>
				       </ol>
				       <ol id="ol2">
				       	<div class="head-num">
			   				<div class="num-icon fl">
			   					<div class="icon-img icon-img-five fl"></div>
			   					<div class="num-data fl">
			   						<p class="normal">未读消息(个)</p>
			   						<p class="middle"><em>0</em>个</p>
			   					</div>
			   				</div>
			   				<div class="num-icon fl">
			   					<div class="icon-img icon-img-six fl"></div>
			   					<div class="num-data fl">
			   						<p class="normal">已读消息(个)</p>
			   						<p class="middle"><em>0</em>个</p>
			   					</div>
			   				</div>
			   			</div>
				       
					       	<p class="ched-btn">
	            	 	<span>
		            	  	<a href="#" onclick="updateMessage(1)" class="middle current" >标记为已读</a>
		            	  	<a href="#" onclick="updateMessage(2)" class="middle">批量删除</a>
	            	 	</span>
	            	 	<span class="common-tip" ></span>
	            	 </p>
	                <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0" id="s1">
	                    <tr>
	                     <!-- <th class="top-line left-line" align="center"width="20%" ><input type="checkbox" onClick="this.value=check(this.form.ids)"/></th>-->
	                     <th align="center" style="width:80px;"><input type="checkbox" name="all" id="all" onclick ="showInfo();"/></th>
	                     <th align="left">是否阅读</th>
	                      <th align="center">消息类型</th>
	                      <th align="center">消息标题</th>
	                      <th align="center">收件时间</th>
	                      <th align="center">操作</th>
	                    </tr>
	              	</table>
    				<div id="pager1"></div>          	
				  </ol>
	         </div>
           </div>
       </div>
	</div>
</div>
<input type="hidden" id="tab" value="${tab}" />
<!-- 弹出框 -->
<div class="xy-lc-box" id="recommandDiv" style="display: none">
        <div class="xy-ui-box-mask"></div>
        <div class="xy-lc-box-show" style="width:500px; height:230px; margin: -115px 0 0 -250px;position: fixed !important;">
            <a class="xy-xbox-close" title="Close" href="javascript:void(0);" onClick="hid();" style="display: inline;">×</a>
            <div class="xy-lc-box-content" style="height:230px;">
                <h1>推荐码</h1>
                <br/>
                      <div style="padding-left:64px">
                     		<input id="record" type="hidden" />
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
</div>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</body>
</html>
<#--<script type="text/javascript" src="${base}/js/wad_common.js"></script>-->
<script type="text/javascript" src="${base}/js/upload.js"></script>
<script type="text/javascript" src="${base}/js/user/authentication.js"></script>
<script type="text/javascript" src="${base}/js/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript" src="${base}/js/user/editUserInfo.js?version=1638120"></script>
<script type="text/javascript" src="${base}/js/user/message.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/static/bootstrap/js/index.js"></script>
<!--<script type="text/javascript" src="${base}/static/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/js/jquery.imgareaselect/scripts/jquery.min.js"></script>-->
<script type="text/javascript" src="${base}/static/js/jquery.imgareaselect/scripts/jquery.imgareaselect.pack.js"></script>

<#--jsrender 资源加载-->
<script type="text/javascript" src="${base}/js/page/jquery.pagination-1.2.3.js"></script>
<script type="text/javascript" src="${base}/js/jsrender/jsrender.min.js"></script>
<script type="text/javascript">
	function　showInfo(){
		if($("#all").attr("checked")=="checked"){
			$("table tr td [name='checkbox']").attr("checked",'true');//全选  
			//$("#check").attr("checked",'true');
		}else{
			$("table tr td [name='checkbox']").removeAttr("checked");//反选  
			//$("#check").removeAttr("checked");
		}
	}

</script>
<script id="temp" type="text/x-jsrender">
	<div class="num-icon fl">
		<div class="icon-img icon-img-five fl"></div>
		<div class="num-data fl">
			<p class="normal">未读消息(个)</p>
			<p class="middle"><em>{{:notReadNums}}</em>个</p>
		</div>
	</div>
	<div class="num-icon fl">
		<div class="icon-img icon-img-six fl"></div>
		<div class="num-data fl">
			<p class="normal">已读消息(个)</p>
			<p class="middle"><em>{{:readNums}}</em>个</p>
		</div>
	</div>
</script>

<script type="text/x-jsrender" id="page_tmp1">
    <tr>
	     <td align="center">
	     <input type="checkbox" name="checkbox" id="check" value="{{:id}}"/></td>
	       <td align="left">{{if readStatus!=null}}{{if readStatus==1}}
	       <img src="${base}/theme/${systemConfig.theme}/images/mail/yidu.gif" width="30px">{{else}}
	       <img src="${base}/theme/${systemConfig.theme}/images/mail/weidu1.gif" width="25px">{{/if}}{{else}}
	       <img src="${base}/theme/${systemConfig.theme}/images/mail/weidu1.gif" width="25px">{{/if}}</td>
	     <td align="center">{{if typename!=null}}{{:typename}}{{else}}&nbsp;{{/if}}</td>
	     <td align="center">{{if title!=null}}{{if title.length > 10}}{{formartString:title}}...{{else}}{{:title}}{{/if}}{{else}}&nbsp;{{/if}}</td>
	     <td align="center">{{if sendTime!=null}}{{:sendTime}}{{else}}&nbsp;{{/if}}</td>
	     <td align="center" >
	      	<a href="${base}/message/getOaNewsMessage.do?id={{:id}}&tab='news'"><span class="blue middle">查看</span></a>
	    	&nbsp;&nbsp;&nbsp;
	    	<a href="javascript:;" onclick="deleteMessage({{:id}})"><span class="blue middle">删除</span></a>
	     </td>
    </tr>
</script>



<script type="text/javascript">
   	$(function () {debugger;
   	    if($("#tab").val()!=null && $("#tab").val()!='undefined' && $("#tab").val()!=''){
   	    	$("#newsMess").addClass("active");
   	    	$("#ol2").addClass("rent");
   	    	$("#ol1").removeClass("rent");
   	    	$("#acount").removeClass("active");
   	    	if($("#tab").val()=='tab1'){
   	    		$(".common-tip").hide();
   	    	}
   	    }else{
   	        $(".common-tip").hide();
	   		$(".cont-list .finlist_title li").click(function(){debugger;
				$(this).addClass("active").siblings().removeClass();
				$(".cont-list .finlist_hide ol").eq($(this).index()).addClass("rent").siblings().removeClass();
			});
   	    }
		showStatistic();
		addPage(1,{start:0,limit:10});
	});
	

	$.views.converters("formartString", function(val) {  
	    if(val==""||val=='undefined'||val==null){debugger
	        return "";
	    }else{
		    var str = val.toString().substring(0,10);
	    	return str;
	    }
	});
	
    //查询统计数据	
	function showStatistic(){
	    var new_map0={age:2,start:0,limit:10};
	    ajaxFunction("${base}/message/showMessageNumsOaNewsMessage.do",new_map0,callback0_0);
	}	
    //获取我的借款的统计数据
	 function callback0_0(data){
	 	var result=data.result;
	 	$(".head-num").empty().html($("#temp").render(result));//累计赚取收益
	 }
	 
 	function addPage(Nums,param){debugger;
		var url="${base}/message/queryAllNewsOaNewsMessage.do";
		var param1={};
		 if(Nums==1){//招标中的数据
			if(param!=null&&param!='undefined'&&param!=''){
			 	params = param;
			}else{
				params = {state:"1",start:"0",limit:"10"};
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

<script>
var the_text = "https://www.zxzbol.com/htmlreg.do?backpath=hrmobile.public.myhome.register1&recommand=${bpCustMember.plainpassword!}";

jQuery('#qrcodeid').qrcode({
    width:140,
    height:140,
    render:"canvas", //设置渲染方式 table canvas
    typeNumber : -1,  //计算模式b
    correctLevel  : 0,//纠错等级
    background   : "#ffffff",//背景颜色
    foreground   : "#000000",//前景颜色
    text:the_text
});
</script>





