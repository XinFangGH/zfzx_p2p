<script type="text/javascript">
       function youthNodeCredit(){
        var hireCompanyname = $("#hireCompanyname").val();
        if(hireCompanyname.trim()==''){
        	$("#hireCompanyname").focus();
			showTip("hireCompanyname", "所在院系不能为空");
        	return false;
        }
         var hireMonthlyincome = $("#hireMonthlyincome").val();
        if(hireMonthlyincome==''){
        	$("#hireMonthlyincome").focus();
			showTip("hireMonthlyincome", "每月生活费不能为空");
        	return false;
        } 
         var regu = /(^([1-9]\d*|[0])\.\d{1,2}$|^[1-9]\d*$)/;
    	 var re = new RegExp(regu);
        if(!re.test(hireMonthlyincome)){
				$("#hireMonthlyincome").select();
				showTip("hireMonthlyincome", "请输入正整数或小数");
				return false;
			}

			var hirePosition = $("#hirePosition").val();	
			if(hirePosition==''){
				alert("请选择年级");
				$("#hirePositionspan").focus();
        	return false;
			}
			
			
        var hireEmail = $("#hireEmail").val();
        if(hireEmail==''){
        	$("#hireEmail").focus();
			showTip("hireEmail", "QQ或微信不能为空");
        	return false;
        }
        
        
        var hireAddress = $("#hireAddress").val();
        if(hireAddress.trim()==''){
        	$("#hireAddress").focus();
			showTip("hireAddress", "学校（校区）不能为空");
        	return false;
        }
        var havechildren = $("#havechildren").val();
        var reguw = /(^[1-9]*[1-9][0-9]*$)/;
         var rew = new RegExp(reguw);
        if(havechildren==''||havechildren==null){
        
        }else if(!rew.test(havechildren)){
				$("#havechildren").select();
				showTip("havechildren", "请输入正整数");
				return false;
			}
       }
</script>
<div class="loanboder">
	 <dl class="dl-list">
                    	
                        <dd>                                                                      
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="Income">
                        					<span class="Income updd">
		                        				<@authen.authentication state=list.status  />
                        					</span>
                            				<span class="sfrz">学生认证（必要认证）<br />
		                        				<@authen.material state=list.status materialstype="Student"  />
		                        				<a id="Student" class="veribtn1"title="" href="${base}/theme/${systemConfig.theme}/images/samples/xueshengzheng.jpg">查看样例</a>
		                        		</#if>
		                        	</#list>
                        </dd>   
                    </dl>
<!--
					<dl class="dl-list">
                        <dd>                                                                      
	                        	<#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="Teacher">
                        					<span class="Income updd">
		                        				<@authen.authentication state=list.status  />
                        					</span>
                            				<span class="sfrz">校园一卡通认证（必要认证）<br />
		                        				<@authen.material state=list.status materialstype=list.materialstype  />
		                        		</#if>
		                        	</#list>
	                        	</#if>
                        </dd>   
                    </dl>
					-->
					<dl class="dl-list">
                    	<dt><span class="star">*</span>所在院系</dt>
                        <dd>
                       			<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.teacherCompanyname" id="hireCompanyname"  value="${(bpCustMember.teacherCompanyname)!}" maxlength="30" size="32" class="colorful1" />
                        </dd>
                    </dl>
                    
                    
					<dl class="dl-list">
                    	<dt><span class="star">*</span>年级</dt>
                        <dd>
                        <div class="divselectall" >
						      <cite><span id="hirePositionspan"><#if listJob??><#list listJob as list><#if bpCustMember.bossPosition==list.dicId>${list.itemValue}</#if></#list></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.bossPosition" type="hidden" value="${bpCustMember.bossPosition!}" id="hirePosition"/></cite>
			  				<#if isPass==null>
						      <ul>
						      	<#if listJob??>
						      	<#list listJob as list>
								  <li><a class="selt" href="javascript:;" selectid="${list.dicId}">${list.itemValue}</a></li>
								 </#list>
								 </#if>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
				
				<dl class="dl-list">
                    	<dt><span class="star">*</span>每月生活费</dt>
                        <dd>
                        	<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.teacherMonthlyincome" id="hireMonthlyincome"  value="${(bpCustMember.teacherMonthlyincome)!}" maxlength="30" size="32" class="colorful1" chkLoanMoney(this);funFormat(this)/>
                        </dd>
                           
                    </dl>
				
				<dl class="dl-list">
                    	<dt><span class="star">*</span>QQ或微信</dt>
                        <dd>
                        	<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.teacherEmail" id="hireEmail"  value="${(bpCustMember.teacherEmail)!}" maxlength="30" size="32" class="colorful1" />
                        	
                        </dd>
                    </dl>
				
				
				 <dl class="dl-list">
                    	<dt><span class="star">*</span>学校（校区）</dt>
                        <dd>
                        	<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.teacherAddress" id="hireAddress"  value="${(bpCustMember.teacherAddress)!}" maxlength="30" size="32" class="colorful1" />
                        </dd>
                    </dl>
				
				
				
				
					<dl class="dl-list">
                            <dt><span class="star">*</span>有无兼职</dt>
                            <dd>
                           	 <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havehouse"  <#if bpCustMember.havehouse=='1'> checked="checked" </#if>  value="1" checked="checked"/>有</span>
                            <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havehouse" <#if bpCustMember.havehouse=='0'> checked="checked" </#if>  value="0" />无</span>
                            </dd>
                    </dl>
				
				
				
				
			<!--	<dl class="dl-list">
                    	<dt><span class="star">*</span>有无兼职</dt>
                        <dd>
                        	<div class="divselectall" >
                        	<#if bpCustMember.hireCompanytype!=null>
						      <cite><span><#if listUnitp??><#list listUnitp as list><#if bpCustMember.hireCompanytype==list.dicId>${list.itemValue}</#if></#list></#if></span>
			  				<input name="bpCustMember.hireCompanytype" type="hidden" value="${bpCustMember.hireCompanytype!}" /></cite>
			  				<#else>
			  				 <cite><span>无</span>
			  				<input name="bpCustMember.hireCompanytype" type="hidden" value="957"></cite>			  				
			  				</#if>
			  				<#if isPass==null>
						      <ul>
						      	<#if listUnitp??>
						      	<#list listUnitp as list>
								 <li><a class="selt" href="javascript:;" selectid="${list.dicId}">${list.itemValue}</a></li>
								 </#list>
								 </#if>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>-->
				<dl class="dl-list">
                    	<dt><span class="star">*</span>兼职收入/月</dt>
                        <dd>
                        	<input type="text"  name="bpCustMember.havechildren" <#if isPass==null><#else>disabled="true"</#if> value="${(bpCustMember.havechildren)!}" maxlength="30" size="32" class="colorful1" id="havechildren"/>元
                        </dd>
                    </dl>
				
			<!--	<dl class="dl-list">
                    	<dt><span class="star">*</span>兼职收入</dt>
                        <dd>
                        	<div class="divselectall" >
						      <cite><span><#if listCompanysize??><#list listCompanysize as comsize><#if bpCustMember.hireCompanysize==comsize.itemValue>${bpCustMember.hireCompanysize}</#if></#list></#if></span>
			  				<input name="bpCustMember.hireCompanysize" type="hidden" value="${bpCustMember.hireCompanysize!}" /></cite>
			  				<#if isPass==null>
						      <ul>
						      	<#if listCompanysize??>
						      	<#list listCompanysize as comsize>
								 <li><a class="selt" href="javascript:;" selectid="${comsize.itemValue}">${comsize.itemValue}</a></li>
								 </#list>
								 </#if>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>-->
</div>