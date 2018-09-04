<#if IDCard!='2'>   
     <dl class="ver-bg-icon-ul">
                        <dt><span>身份认证</dt>
         				<dd><span data="0" class="IDCard">
         					 
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="IDCard">
	                        			<@authen.authentication state=list.status />
	                        		</#if>
	                        	</#list>
       
         				</span>
	        			</dd><br/>
                         <dd>
                         	<#list webFinanceApplylist as idCardState>
	                        	<#if idCardState.materialstype=="IDCard">
	                        		<#if idCardState.status!='3'>
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriIDCard()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>	
	                        		</#if>
                         		</#if>
	                        </#list>
                         </dd>
                    </dl>
     
</#if>
     
     
<#if Income!='2'>  
     <dl class="ver-bg-icon-ul">
                        <dt><span>学生认证</dt>
         				<dd><span data="0" class="Student">
         					 <#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="Income">
	                        			<@authen.authentication state=list.status/>
	                        		</#if>
		                        	</#list>
	                        	</#if>
       
         				</span>
	        			</dd><br/>
                         <dd>
                         	<#list webFinanceApplylist as idCardState>
	                        	<#if idCardState.materialstype=="Income">
	                        		<#if idCardState.status!='3'>
	                        	 		<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriStudent()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        	 		
	                        	 	</#if>
                         		</#if>
	                        </#list>
                         </dd>
                    </dl>
     
     </#if>
     
     