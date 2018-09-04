<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} -账户设置</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/bindPhone.js"></script>
<script type="text/javascript" src="${base}/js/user/verifBindPhone.js"></script>
<script type="text/javascript" src="${base}/js/user/changepassword.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/bindEmail.js"></script>
<script type="text/javascript">var m1="个人账户",m2="账户设置",m3="账户安全";</script>
<script type="text/javascript">
	

</script>
</head>
<body >
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div class="main">
	<div class="user-cont">
	<!--start: 左侧页面-->
	 	<div class="user-name-nav fl">
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
			 <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">  
		</div>
		<div  class="user-cont-fr fr">
		   <div class="user-cont-right">
				<div >
				 <div class="ucrightdiv">
				<!-- 我的个人信息 start -->
			    	<#if (toAction=="updatePwd")>
		        	<div class="zjxx" style="padding:20px 0">
		        	   <div class="titlebar">
							<div class="titlebarleft"></div>
							<div class="titlebarcenter"><span class="large"  >修改密码</span>
		            </div>
					   </div>
		    </div>
		     
        	<div class="cont-list userborder">
            	<!--此处放置内容-->
            	 <!--<#include "/WEB-INF/template/common/user/updateUserLoginPassword.ftl">-->
            	 
            <form  id="changeLoginPasswordForm" action="${base}/user/updatePasswordBpCustMember.do" method="post" class="m-t_20">
	            <input type="hidden" name="path" id="path" value="${base}" />
	            <ul class="updatelist">
		           <li>
			        <label>原密码</label>
			        <span style="margin-top:10px;">
		            <input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
		            <input type="password" class="colorful1" size="50" id="oldpassword" name="oldpassword"  placeholder="请输入原密码"/>
		           </span>
		           <div class="common-tip" style="display:none;"></div>
		           </li>
		           
		           <li>
			        <label>新密码</label>
			        <span style="margin-top:10px;">
		           <input type="password"  class="colorful1" size="50"  id="password" name="password" placeholder="新密码" maxlength="16"/>
		           </span>
		           </li>
		           
		           <li>
			        <label>确认新密码</label>
			        <span style="margin-top:10px;">
		           <input type="password" class="colorful1" size="50"  id="repeat_password" placeholder="确认新密码" maxlength="16"/>
		              </span>
		           </li>
		           
				<li  style="height:40px">
					<div tabindex="4" style="width:140px; height:30px; line-height:30px; float:left; margin:6px 0px 0px 116px; font-size:16px;"
					 id="changeLoginPassword"  class="buttonorange">确&nbsp;&nbsp;认</div>
				</li>
			</ul>
		    </form> <!--放置内容结束-->
		  </div>
		  <div class="userborder tipbg" style="padding:20px 40px; ">
					    <h4>温馨提示：</h4>
					    <p>1.   请牢记您设置的新密码，登录密码可通过密码找回功能找回。</p>
		 				<p>2.   邮箱验证过程遇到问题，请联系客服，400-9266-114</p>
		
					</div>
		  </#if></div>
		  
	    	<!-- 我的个人信息 start -->
	    	<#if bpCustMember.telphone==null>
	    	<#if (toAction=="updateTelphone")>
	      <div>
	    	<!-- 我的个人信息 start -->
        	<div style=" height:40px; padding:10px 0px 0px 50px; ">
            	<span class="large"  >手机绑定</span>
            </div>
             
        	<div style="padding:20px 20px; overflow:hidden; class="cont-list">
            	<!--此处放置内容-->
            	 
	            <form id="beforeBindMobileForm" action="${base}/user/bindPhoneBpCustMember.do" method="post">
		           
		            <div class="hdlh">
	             <div class="hdlh1">
		            <ul style="width:400px;background:#f8f6f6;padding:40px 20px 40px 80px;">
		            	 <div class="common-tip" style="display:none;"></div>
				           <li style="margin-bottom:10px;">
					        <div>绑定手机号码</div>
					        <div style="margin-top:10px;">
				              <input type="hidden" id="id" name="id" value="${bpCustMember.id}" />
				              <input  id="telphone" name="telphone"  placeholder="没加校验"/>
				              <input type="hidden" id="Smstelphone" name="Smstelphone" value="<#if (Sms ==null)><#else>${Sms}</#if>"/>
				              <span style="padding-right:50px"></span>
					           <a id="getBeforeVerifySms" class="getcode m-l_20 disabled" href="javascript:void(0);">获取验证码</a>
					          <!-- onclick="getVerifySms('getBeforeVerifySms', 'beforeTelphone', 'beforeVerify_sms', 60 , 'bind_telphone')"-->
				           </div>
				           </li>
				           
				           <li style="margin-bottom:10px;">
					        <div>收到短信验证码</div>
					        <div style="margin-top:10px;">
				               <input type="text" size="40"  id="beforeVerify_sms" class="colorful"  name="verify_sms" placeholder="请输入短信验证码"/>
				           </div>
				           </li>
				           
				           
				<li  style="height:40px">
					 <div tabindex="4" style="width:284px; height:30px;line-height:30px; float:left; margin:8px 0px 0px 0px; font-size:16px;"
					 id="beforeBindMobile" class="buttonorange">确认</div>
				</li>
			</ul>
		</div></div>
	</form>    	            	 
	</div><!--放置内容结束-->
	</div>
  </#if></#if>
  	
  	<!--手机绑定开始 -->
  		<#if bpCustMember.telphone!=null>
  			<#if bpCustMember.isCheckPhone==1>
  			<#if (toAction=="updateTelphone")>
  				<div>
		    	<!-- 我的个人信息 start -->
	        	<div style=" height:40px; padding:10px 0px 0px 50px; ">
            		<span class="large">更换手机号码</span>
           		</div>
	             
	        	<div style=" padding:20px 20px; overflow:hidden; class="cont-list">
	            	<!--此处放置内容-->
	            	 
		            <form id="beforeBindMobileForm" action="${base}/user/verifPhoneBpCustMember.do" method="post">			          
			            <div class="hdlh">
		             <div class="hdlh1">
			            <ul style="width:400px;padding:20px 20px 40px 100px; ">
			            	<#--<div class="common-tip" style="display:none"></div>-->
				           <li style="margin-bottom:10px;">
					        <div class="group_lable" id="validataCodeLabel">
							<span>图形验证码： 
								<input type="text" style="width:90px;" id="checkCode" name="checkCode"  size="4" maxlength="4"  class="colorful1" placeholder="输入图形验证码"  onkeyup="validatCheckCode(this)" tabindex="4" />
								<img width="110" height="36" id="loginCode" class="verify-code" alt="点击更换" title="点击更换" onclick="javascript:refresh(document.getElementById('loginCode'));"  src="${base}/getCode.do" style="cursor:pointer;" />
								<span id ="spancheckCode_img" style="display: none"></span>
							    <span id ="spancheckCode"></span>
							</span>
							</div>
					        
					        <div style="margin-top:10px;">
				              <input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
				              <span>当前手机号码：</span>
				              <input type="hidden" id="telphone" name="telphone" value="${bpCustMember.telphone}"/>
				              <input type="hidden" id="Smstelphone" name="Smstelphone" value="<#if (Sms ==null)><#else>${Sms}</#if>"/>
				              <span style="padding-right:50px">${bpCustMember.telphone?substring(0,3)}****${bpCustMember.telphone?substring(7)}</span>
				           </div>
				          <div onclick="updategetBeforeVerifySms()" tabindex="4" style="width:154px; height:30px;line-height:30px; float:left; margin:8px 0px 0px 0px; font-size:16px;"
					 class="buttonorange" id="getBeforeVerify_Sms">获取验证码</div>
					 
					 <div  tabindex="4" id="timeMsg" style="display:none;width:154px; height:30px;line-height:30px; float:left; margin:8px 0px 0px 0px; font-size:16px;"
					 class="buttonorange" ></div>
					 
				            <br>  <span style="color:red;padding-top:12px;" id="errorMsg"></span>
				           </li>
				           <br>
				           <li style="margin-bottom:10px;">
				           <br>
					        <div>填写手机验证码</div>
					        <div style="margin-top:10px;">
				               <input type="text" size="40"  id="beforeVerify_sms" class="colorful1"  name="verify_sms" placeholder="请输入短信验证码"/>
				           </div>
				           </li>
				           
				           <li>
                               <div class="common-tip" style="display:none"></div>
						   </li>
				<li  style="height:40px">
					 <div tabindex="4" style="width:254px; height:30px;line-height:30px; float:left; margin:8px 0px 0px 0px; font-size:16px;"
					 id="beforeBindMobile" class="buttonorange">确认</div>
				</li>
			</ul>
			</div></div>
		</form>    	            	 
		</div><!--放置内容结束-->
		</div>
  			<#elseif (toAction!="updatePwd")>
  				<div>
	    	<!-- 我的个人信息 start -->
        	<div style=" height:40px; padding:10px 0px 0px 50px; ">
            		<span class="large"  >手机绑定</span>
           	</div>
	         
        	<div style=" padding:20px 20px; overflow:hidden; class="cont-list">
            	<!--此处放置内容-->
            	 
	            <form id="bindMobileForm" action="${base}/user/updatePhoneBpCustMember.do" method="post">
		            
		            <span id="true_telphone" style="display:none"></span>
		            <div class="hdlh">
	             <div class="hdlh1">
		            <ul style="padding:20px 20px 40px 100px;">
		            	<div class="common-tip" style="display:none;"></div>
			           <li style="margin-bottom:10px;">
				        <div>手机号码</div>
				        <div style="margin-top:10px;">
			              <input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
				          <input type="text" size="40" class="colorful" onBlur="validatTel2CheckCode(this)" id="telphone" name="telphone" placeholder="请输入手机号码" maxlength="11"/>
			           </div>
			           <br>
			             <div class="group_lable" id="validataCodeLabel">
							<span>图形验证码： 
								<input type="text" style="width:80px;" id="checkCode" name="checkCode"  size="4" maxlength="4"  class="colorful1" placeholder="输入图形验证码"  onkeyup="validatCheckCode(this)" tabindex="4" />
								<img width="110" height="36" id="loginCode" class="verify-code" alt="点击更换" title="点击更换" onclick="javascript:refresh(document.getElementById('loginCode'));"  src="${base}/getCode.do" style="cursor:pointer;" />
								<span id ="spancheckCode_img" style="display:none"></span>
							    <span id ="spancheckCode"></span>
							</span>
							</div>
			            <div onclick="updateTe()" tabindex="4" style="width:254px; height:30px;line-height:30px; float:left; margin:15px 0px 0px 0px; font-size:16px;"
					    class="buttonorange" id="getBeforeVerify_Sms">获取验证码</div>
					    
					    <div  tabindex="4" id="timeMsg" style="display:none;width:154px; height:30px;line-height:30px; float:left; margin:8px 0px 0px 0px; font-size:16px;"
					 class="buttonorange" ></div>
			           </li>
			           <br>
			           <li style="margin-bottom:10px;">
				        <br> <div style="clear:both; margin-top:30px;">短信验证码</div>
				        <div style="margin-top:10px;">
			               <input type="text" size="40"  id="tellnews" class="colorful"  name="verify_sms" placeholder="请输入短信验证码"/>
			           </div>
			           </li>
			           
			           
			<li  style="height:40px">
				 <div tabindex="4" style="width:254px; height:30px;line-height:30px; float:left; margin:8px 0px 0px 0px; font-size:16px;"
				 id="bindMobile8" class="buttonorange" onclick="upDateTellPhone()">确认</div>
			</li>
		</ul>
		</div></div>
	</form>    	            	 
	</div><!--放置内容结束-->
	</div>
  			</#if>
	         
		</#if>
		
  		</#if>
  		<!--手机绑定结束 -->
  		
  		<!-- 实名认证开始-->
  		<#if (toAction=="updateName")>
  		<div>
	    	<!-- 我的个人信息 start -->
        	<div style="width:640px; height:40px; padding:10px 0px 0px 40px;">
            	<span class="large"  >实名认证</span>
            </div>
             
        	<div class="cont-list">
            	<!--此处放置内容-->
            	 
            <form id="authenticateForm" action="${base}/user/updateAuthenticateBpCustMember.do" method="post">
	           
	            <div class="hdlh">
             <div class="hdlh1">
	            <ul style="padding:20px 20px 40px 80px;">
	            	<div class="common-tip" style="display:none;"></div>
		           <li style="margin-bottom:10px;">
			        <div>用户姓名</div>
			        <div style="margin-top:10px;">
		              <input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
			          <input type="text" size="40" class="colorful1"   name="truename" placeholder="请输入姓名" maxlength="11"/>
		           </div>
		           </li>
		           
		           <li style="margin-bottom:10px;">
			        <div>身份证</div>
			        <div style="margin-top:10px;">
		               <input type="text" size="40"   class="colorful1"  name="cardcode" placeholder="请输入身份证号码"/>
		           </div>
		           </li>
		<li  style="height:40px">
			 <div tabindex="4" style="width:254px; height:30px;line-height:30px; float:left; margin:8px 0px 0px 0px; font-size:16px;"
			 id="authenticate" class="buttonorange">确认</div>
		</li>
	</ul>
	</div></div>
</form>    	            	 
</div><!--放置内容结束-->
</div>
</#if>
	<!-- 实名认证结束-->
	
	<!-- 邮箱绑定开始-->
    <#if bpCustMember.telphone!=null>
    	<#if (toAction=="email")>
    	<#if bpCustMember.isCheckEmail==1>
	         <div>
		    	<!-- 我的个人信息 start -->
	        	<div style=" height:40px; padding:10px 0px 0px 40px;">
	            	<span class="large" >邮箱绑定</span>
	            </div>
	             
	        	<div style=" padding:20px 20px; overflow:hidden; class="cont-list">
	            	<!--此处放置内容-->
	            	 
		            <form id="beforeBindMobileForm" action="${base}/user/verifEmailBpCustMember.do" method="post">
			           
			            <div class="hdlh">
		             <div class="hdlh1">
			            <ul style="padding:20px 20px 40px 80px;">
			             <div class="common-tip" style="display:none;"></div>
				           <li style="margin-bottom:10px;">
					        <div>绑定手机号码</div>
					        <div style="margin-top:10px;">
				              <input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
				              <input type="hidden" id="telphone" name="telphone" value="${bpCustMember.telphone}"/>
				               <input type="hidden" id="Smstelphone" name="Smstelphone" value="<#if (Sms ==null)><#else>${Sms}</#if>"/>
				              <span style="padding-right:50px">${bpCustMember.telphone?substring(0,3)}****${bpCustMember.telphone?substring(7)}</span>
					           <a id="getBeforeVerifySms" class="getcode m-l_20 disabled" href="javascript:void(0);">获取验证码</a>
					          <!-- onclick="getVerifySms('getBeforeVerifySms', 'beforeTelphone', 'beforeVerify_sms', 60 , 'bind_telphone')"-->
				           </div>
				           </li>
				           
				           <li style="margin-bottom:10px;">
					        <div>收到短信验证码</div>
					        <div style="margin-top:10px;">
				               <input type="text" size="40"  id="beforeVerify_sms" class="colorful1"  name="verify_sms" placeholder="请输入短信验证码"/>
				           </div>
				           </li>
				           
				           
				<li  style="height:40px">
					 <div tabindex="4" style="width:254px; height:30px;line-height:30px; float:left; margin:8px 0px 0px 0px; font-size:16px;"
					 id="beforeBindMobile" class="buttonorange">确认</div>
				</li>
			</ul>
			</div></div>
		</form>    	            	 
		</div><!--放置内容结束-->
		</div>
		<#else>
			<div>
			<!-- 我的个人信息 start -->
		    <div style="height:40px; padding:10px 0 0 40px; ">
		            	<span class="large" >邮箱认证</span>
		     </div>
		      
		     <div style=" padding:20px 20px; overflow:hidden; class="cont-list">
		            	<!--此处放置内容-->
		            <!--此处放置内容-->
		     <form id="bindemailForm" action="${base}/user/bindEmailBpCustMember.do" method="post">
		         <input type="hidden" name="path" id="path" value="${base}" />
				
		
				 <div class="hdlh">
		        <div class="hdlh1">
		             
		        <ul style="padding:20px 20px 40px 100px;">
		        <div class="common-tip" style="display:none;"></div>
				  <li style="margin-bottom:10px;">
					<div>电子邮箱</div>
					 <div style="margin-top:10px;">
				        <input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
						<input type="text" size="40"  class="colorful1"  id="email" name="email" placeholder="请输入邮箱" value="${bpCustMember.email}"/>
				     </div>
				 </li>
		
							
		
				<li  style="height:40px">
					<div tabindex="4" style="width:254px; height:30px; float:left; margin:8px 0px 0px 0px; padding:5px 0px 0px 0px; font-size:16px;"
		
						id="bindemail" class="buttonorange">确认</div>
					</li>
				</ul></div></div>
						</div>
						</div>
					</form>
						<!--放置内容结束-->	   	            	 
		</div><!--放置内容结束-->
		</div>
		</#if>
		</#if>
			<#if (toAction=="updateEmail")>
		    	<div>
			    	<!-- 我的个人信息 start -->
		        	<div style="height:40px; padding:10px 0 0 40px; ">
		            	<span class="large"  >邮箱认证</span>
		            </div>
		             
		        	<div  class="cont-list">
		            	<!--此处放置内容-->
		            <!--此处放置内容-->
		            	<form id="bindemailForm" action="${base}/user/bindEmailBpCustMember.do" method="post">
		            	 <input type="hidden" name="path" id="path" value="${base}" />
						
		
					 <div class="hdlh">
		             <div class="hdlh1">
		             
		              <ul style="padding:20px 20px 40px 100px;">
		              	<div class="common-tip" style="display:none;"></div>
				           <li style="margin-bottom:10px;">
					        <div>电子邮箱</div>
					        <div style="margin-top:10px;">
				              <input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
								<input type="text" size="40"  class="colorful1"  id="email" name="email" placeholder="请输入邮箱" value="${bpCustMember.email}"/>
				           </div>
				           </li>
		
							
		
							<li  style="height:40px">
								<div tabindex="4" style="width:254px; height:30px; float:left; margin:8px 0px 0px 0px; padding:5px 0px 0px 0px; font-size:16px;"
		
								 id="bindemail" class="buttonorange">确认</div>
							</li>
						</ul></div></div>
						</div>
						</div>
					</form>
						<!--放置内容结束-->	   	            	 
		</div><!--放置内容结束-->
		</div>
		    </#if>
    <#else>
    	<#if (toAction=="email")>
	    	<div>
		    	<!-- 我的个人信息 start -->
	        	<div style=" height:40px; padding:10px 0 0 40px; ">
	            	<span class="large"  >邮箱认证</span>
	            </div>
	             
	        	<div style=" padding:20px 20px; overflow:hidden; class="cont-list">
	            	<!--此处放置内容-->
	            <!--此处放置内容-->
	            	<form id="bindemailForm" action="${base}/user/bindEmailBpCustMember.do" method="post">
	            	 <input type="hidden" name="path" id="path" value="${base}" />
					
	
				 <div class="hdlh">
	             <div class="hdlh1">
	             
	              <ul style="padding:20px 20px 40px 100px;">
	              	<div class="common-tip" style="display:none;"></div>
			           <li style="margin-bottom:10px;">
				        <div>电子邮箱</div>
				        <div style="margin-top:10px;">
			              <input type="hidden" id="id" name="id" value="${bpCustMember.id}"/>
							<input type="text" size="40"  class="colorful1"  id="email" name="email" placeholder="请输入邮箱" value="${bpCustMember.email}"/>
			           </div>
			           </li>
	
						
	
						<li  style="height:40px">
							<div tabindex="4" style="width:254px; height:30px; float:left; margin:8px 0px 0px 0px; padding:5px 0px 0px 0px; font-size:16px;"
	
							 id="bindemail" class="buttonorange">确认</div>
						</li>
					</ul></div></div>
					</div>
					</div>
				</form>
					<!--放置内容结束-->	   	            	 
	</div><!--放置内容结束-->
	</div>
	    </#if>
    </#if>
   <!-- 邮箱绑定结束-->
  
		</div>
		</div>
</div>
</div>
</div>
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>