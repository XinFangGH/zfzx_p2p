 <#if CreditRecord!='2'>               
     <dl class="ver-bg-icon-ul">
                        <dt><span>微信认证</dt>
         				<dd><span data="0" class="CreditRecord">
         					 
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="CreditRecord">
	                        			<@authen.authentication state=list.status />
	                        		</#if>
	                        	</#list>
       
         				</span>
	        			</dd><br/>
                         <dd>
                         	<#list webFinanceApplylist as idCardState>
	                        	<#if idCardState.materialstype=="CreditRecord">
	                        		<#if idCardState.status!='3'>
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriWechat()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>	
	                        		</#if>
                         		</#if>
	                        </#list>
                         </dd>
                    </dl>
     
     </#if>
     
     
     <!--
     <dl class="ver-bg-icon-ul">
                        <dt><span>奉献认证</dt>
         				<dd><span data="0" class="House">
         					 <#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="House">
	                        			<@authen.authentication state=list.status/>
	                        		</#if>
		                        	</#list>
	                        	</#if>
       
         				</span>
	        			</dd><br/>
                         <dd>
                         	<#list webFinanceApplylist as idCardState>
	                        	<#if idCardState.materialstype=="House">
	                        		<#if idCardState.status!='3'>
	                        	 		<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriHouse()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        	 		</#if>
                         		</#if>
	                        </#list>
                         </dd>
                    </dl>
     
     
     <dl class="ver-bg-icon-ul">
                        <dt><span>成绩认证</dt>
         				<dd><span data="0" class="Vehicle">
         					 <#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="Vehicle">
	                        			<@authen.authentication state=list.status/>
	                        		</#if>
		                        	</#list>
	                        	</#if>
       
         				</span>
	        			</dd><br/>
                         <dd>
                         	<#list webFinanceApplylist as idCardState>
	                        	<#if idCardState.materialstype=="Vehicle">
	                        		<#if idCardState.status!='3'>
	                        	 		<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriVehicle()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        	 		</#if>
                         		</#if>
	                        </#list>
                         </dd>
                    </dl>
     
               
     <dl class="ver-bg-icon-ul">
                        <dt><span>奖学金认证</dt>
         				<dd><span data="0" class="Marriage">
         					 
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Marriage">
	                        			<@authen.authentication state=list.status />
	                        		</#if>
	                        	</#list>
       
         				</span>
	        			</dd><br/>
                         <dd>
                         	<#list webFinanceApplylist as idCardState>
	                        	<#if idCardState.materialstype=="Marriage">
	                        		<#if idCardState.status!='3'>
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriMarriage()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>	
	                        		</#if>
                         		</#if>
	                        </#list>
                         </dd>
                    </dl>
     
     
     
     
     
     <dl class="ver-bg-icon-ul">
                        <dt><span>考级认证</dt>
         				<dd><span data="0" class="Education">
         					 <#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="Education">
	                        			<@authen.authentication state=list.status/>
	                        		</#if>
		                        	</#list>
	                        	</#if>
       
         				</span>
	        			</dd><br/>
                         <dd>
                         	<#list webFinanceApplylist as idCardState>
	                        	<#if idCardState.materialstype=="Education">
	                        		<#if idCardState.status!='3'>
	                        	 		<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriEducation()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        	 		</#if>
                         		</#if>
	                        </#list>
                         </dd>
                    </dl>
     
     <dl class="ver-bg-icon-ul">
                        <dt><span>四六级认证</dt>
         				<dd><span data="0" class="Residence">
         					 <#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="Residence">
	                        			<@authen.authentication state=list.status/>
	                        		</#if>
		                        	</#list>
	                        	</#if>
       
         				</span>
	        			</dd><br/>
                         <dd>
                         	<#list webFinanceApplylist as idCardState>
	                        	<#if idCardState.materialstype=="Residence">
	                        		<#if idCardState.status!='3'>
	                        	 		<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriResidence()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        	 		</#if>
                         		</#if>
	                        </#list>
                         </dd>
                    </dl>-->
     
     
     
     