
	<div style="width:100%;overflow:hidden; border-bottom:2px solid #2770b8;background:#fff">
		<div class="logobar" >
			<div class="logodiv"><a href="${base}/" target="_self"><img src="${base}/theme/${systemConfig.theme}/images/logo.png" height="53" alt="Logo" /></a></div>
			<div class="navbardiv" >
				<div class="mainnavbar">
			        <ul>
			            <li><a  href="${base}/creditFlow/financingAgency/listPlBidPlan.do?Q_proType_S_LK=Dir" target="_self"><span>我要出借</span></a></li>
			            <li><a  href="${base}/financePurchase/toFinancePurchase.do" target="_self"><span>信用借款</span></a></li>            
			            <!--  <li><a href="${base}/financePurchase/applyComFinancePurchase.do" target="_self"><span>企业借款</span></a></li> -->

			            <li><a href="${base}/html/secureSingle.do" target="_self"><span>安全保障</span></a></li>
			            <li><a href="${base}/html/companySingle.do" target="_self"><span>关于我们</span></a></li>                       
			        </ul>
			       
   				 </div>   
			</div>
			<div class="regdiv">
				<#if Session.successHtml=='undefined'||Session.successHtml==null||bpCustMember==null>
					<div id="login_div" class="login_div">	
						<a href="${base}/htmllogin.do"style="padding:0px 15px; color:#737373; border-right:1px dotted #ccc; margin-right:15px;" class="big">登录</a>
						 <a href="${base}/htmlreg.do" class="big"  style="padding:6px 20px;border-radius:2px;line-height:50px;color:#fff; background:#79c3fd; height:50px; ">安全注册</a>
						 
					</div>
				<#else>
					<div  class="login_div">${Session.successHtml}</div>
				</#if> 
				
			</div>  		 			     
		</div>
		
	</div>
	
	
	
