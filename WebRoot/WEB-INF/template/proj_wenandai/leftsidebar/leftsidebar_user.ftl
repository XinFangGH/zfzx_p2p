
            <div class="user">
                <div class="name">
                	<div class="ures-name">
                		<div class="name-header-pic">
                			<a href="${base}/user/safeBpCustMember.do?safe=all	">
                			<#if imgurl  != "" && imgurl  != null>
			       				<img src="${base}/${imgurl}" style="margin-top:15px; height:150px;width:150px; border-radius:75px;" />
			       			<#else>
			       				<img id="biuuu1" src="${base}/theme/${systemConfig.theme}/images/uc/urer-name-pic.png" title="求真相">
			       			</#if>
                			<!--<img src="${base}/theme/${systemConfig.theme}/images/uc/urer-name-pic.png"/>-->
                			</a>
                		</div>
                		<a href='${base}/user/safeBpCustMember.do?safe=all' target="_self">${(bpCustMember.loginname)!}</a>
                	</div>
                	<div class="vip-safes">
	               		 <p class="noraml">会员等级：<em><#if bpCustMember.isVip=='1'>Vip会员<#else>${levelMark}</#if></em></p>
                    </div>
	                <div class="name-icon">
	                   	<#if bpCustMember.isCheckCard==1>
	                   		<a title='已实名认证'  href="javascript:void(0);" class="user-iocn icon3"></a>	
	                   	<#elseif bpCustMember.thirdPayFlagId==1>
	                   		<a title='未实名认证'  href="javascript:void(0);" class="user-iocn <#if bpCustMember.isCheckCard==1>icon3<#else>icon03</#if>"></a>	
	                   	<#else>
							<#if bpCustMember.customerType ==1>
							<a title='未实名认证'  href="${base}/pay/thirdAndBindBudPay.do" class="user-iocn icon03"></a>
							<#else>
							<a title='未实名认证'  href="${base}/thirdreg.do" class="user-iocn icon03"></a>
							</#if>

	                   	</#if>
	                    <#if bpCustMember.isCheckEmail==1>
	                     <a title='已邮箱认证'  href="javascript:void(0);" class="user-iocn icon2"></a>	
	                    <#else>
	                     <a title='未邮箱认证'  href="${base}/emailreg.do?type=2&action=email&retUrl=user/getBpCustMember.do" class="user-iocn icon02"></a>	
	                    </#if>
	                   <#if bpCustMember.isCheckPhone==1>
	                   	    <a title='已手机认证'  href="javascript:void(0);" class="user-iocn icon1"></a> 
	                   
	                   <#else>
	                    	<a title='未手机认证'  href="${base}/user/getBpCustMember.do?typ=2&action=updateTelphone&retUrl=user/getBpCustMember.do?typ=1" class="user-iocn icon01"></a> 
	                   </#if>
	                   <#if bpCustMember.thirdPayFlagId!=null>
	                  		 <a title='已开通支付账户'  href="javascript:void(0);" class="user-iocn icon4"></a> 
	                   <#else>
	                   		 <a title='未开通支付账户'  href="${base}/thirdreg.do" class="user-iocn icon04"></a> 
	                   </#if>
	                   <#if bidAuto==null>
	                   		<a title='未授权自动投标'  href="${base}/creditFlow/financingAgency/automaticbidPlBidAuto.do" class="user-iocn icon05"></a>
	                   <#else>
		                   <#if bidAuto.isOpen==0>
		                   		<a title='未授权自动投标'  href="${base}/creditFlow/financingAgency/automaticbidPlBidAuto.do" class="user-iocn icon05"></a>
		                   <#else>
		                   		<a title='已授权自动投标'  href="${base}/creditFlow/financingAgency/automaticbidPlBidAuto.do" class="user-iocn icon5"></a>
		                   </#if>
	                   </#if>
	                    
	                    <#if bpCustMember.thirdPayConfig == "moneyMoreMoreConfig">
	                    <#if bpCustMember.refund==null>
	                    	<a title='未授权自动放款'  href="${base}/user/autoLoanBpCustMember.do" class="icon05"></a>  
	                    <#else>
		                    <#if bpCustMember.refund==1>
		                    <#else>
		                   		<a title='未授权自动放款'  href="${base}/user/autoLoanBpCustMember.do" class="icon05"></a>  
		                    	<a title='已授权自动放款'  href="javascript:void(0);" class="icon5"></a>  
		                    </#if>
	                    </#if>
	                    
	                    </#if>
	                </div>
	                <#if bpCustMember.thirdPayConfig == "moneyMoreMoreConfig">
		                <#if bpCustMember.refund==null>
			                <div class="zd-icon">
			                	<a class="red-bg" href="${base}/user/autoLoanBpCustMember.do">授权自动放款</a>
			                </div>	
			            <#else>
			            	<div class="zd-icon">
								<#if bpCustMember.refund==1>
			                   		<a class="gray-bgs"href="#">已授权自动放款</a>  
			                    <#else>
			                    	<a class="red-bg" href="${base}/user/autoLoanBpCustMember.do">授权自动放款</a>  
			                    </#if>	
			                </div>
		                </#if>
	                </#if>
	                <div class="safes">
	                	安全等级：<span class="jdt"><span class="jdtbg" style="width:${safetyLevel!}%"></span></span> <#--${safetyLevel!}%-->
	                	<#--${percent!}%-->
		            </div>
                </div>
            </div>