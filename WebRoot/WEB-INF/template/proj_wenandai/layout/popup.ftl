<form id="bid_Form"  method="post">
<input name="plBidInfo.bidId" type="hidden" id="plBidInfobidId">
<input name="plBidInfo.bidName" type="hidden" id="plBidInfobidName">
<input name="plBidInfo.userMoney" type="hidden" id="plBidInfouserMoney">
<input name="plBidInfo.userName" type="hidden" id="plBidInfouserName">
<input name="plBidInfo.userId" type="hidden" id="plBidInfouserId">
<input name="formtoken" type="hidden" id="formtoken" value="${formtoken}">    	
<div id="signup" class="xy-lc-box">
	<div id="signup-ct">
		<div id="signup-header">
        	<div style="float:left; width:330px;line-height:40px; height:40px; padding:10px 0px 0px 30px; color:#808080;"><span  class="regular">我要投标</span></div>
            <div id="lean_overlay_close" style="float:right;color:#808080;  padding:20px 0px 0px 0px;width:60px; height:20px; line-height:20px; cursor:pointer;"  class="regular">关闭</div>
		</div>
		<div>
        <div style="float:left; width:348px; height:150px; margin:30px 0px 0px 30px;">
        <span id="bidLoad"></span>
        <!--提示信息开始-->
        	<span id="msgWin" style="display:none">
        	<ul>
				  <li>
				    	<span class="big black">提示信息：</span>
				  </li>
				  <li>
				    <span id="bidMsg"></span>
				  </li>
			 	  <li>
			 	    <a href="${base}/htmllogin.do?retUrl=${base}" target="_self" id="bidLogin"> <input class="buttonorange button"   value="&nbsp;&nbsp;&nbsp;立即登录 &nbsp;&nbsp;" style="height:30px;"></input></a>
			 	  </li>
			 	  	   <li>
			 	    <a href="javascript:location.reload()" target="_self" id="bidReset" style="display:none"> <input class="buttonorange button" type="submit"   value="&nbsp;&nbsp;&nbsp;重新投标 &nbsp;&nbsp;" style="height:30px;"></input></a>
			 	  </li>
			 	  </ul>
        	</span>
        	<!--提示信息结束-->
				<span id="bidForm">
				  <p>
				    	<span class="regular black">请输入金额：</span><span id="money_span" style="color:red"></span>
				  </p>
				  <p style="height:40px; margin-left:50px;">
				    <input style="border:1px solid #ddd; width:150px; padding:2px 5px; height:30px; line-height:30px;float:left;" id="mySignMoney" name="mySignMoney" onkeyup="chkMoney('mySignMoney',${plan.afterMoney},'');"  type="text" />
				    <span style="background:#2fa8e1; color:#fff; float:left;width:35px; text-align:center; height:36px; line-height:36px; display:block;">元</span>
				  </p>
			 	  <p style=" margin-left:50px;">
			 	  	<input class="buttonorange1 button" type="button"  onClick="bidHandler()"   value="马上投标" ></input>
			 	  </p>
			 	  <div class="reg_agree" style="padding:5px 0;">
		          		<label style="float:left;">
		            		<input type="checkbox" id="readAgreement" tabindex="" />
		            		<span>我已阅读并同意</span>
		          		</label>
		         		<a style="float:left;" target="_blank" href="${base}/creditFlow/financingAgency/riskContractPlManageMoneyPlan.do" name="signup" class="font-blue"><span class="blue middle">《风险提示书》</span></a>
		           		<span id="readAgreement_msg_img" style="float:left;margin:1px 3px 0 20px;"></span>
		            	<span id="readAgreement_msg"></span>
		           </div>
			 	  </span>
			 	  <div class="conts1" style="clear:both;">
			 	  	<b class="subNav" id="onSubNav">
			 	  		<span>可用优惠券（<em id="sumNum"></em>） </span>&nbsp;&nbsp;
			 	  		<#if plan.rebateType!=4>
			 	  		<span>总金额：（<em class="middle" id="sumMoney"></em>）元 </span>
			 	  		</#if>
			 	  	</b>
					<div style="width:350px;height:150px; overflow-y:scroll;">
						<table  width="320px;" id="addCoupons" border="0" cellspacing="0" cellpadding="0" style="border-collapse:separate; border-spacing:10px;">
							
						</table>
					</div>
			 	</div>
					
        </div>
    	<div style="float:left; height:350px; margin:10px 0px 0px 0px; width:1px; border:0px; background-color:#e0e0e0;"></div>
    	
        <div style="float:left; width:300px; margin:20px 0px 0px 20px;">
        	<ul>
        		<span id="setMoney" style="display:none;"></span>
        		<span id="mycouponsType" style="display:none;">${plan.rebateType}</span>
        		<li>剩余投标金额：<span id="currM">${plan.afterMoney?string(',##0.00')}元</span></li>
        		<li>账户可用金额：
        			<#if bpCustMember??>${bpCustMember.availableInvestMoney?string(',##0.00')}元</#if>
        		</li>
        		<li>是否可用优惠券：<span id="sycoupon"><#if plan.coupon==1>是<#else>否</#if></span></li>
        		<li>是否新手专享：<span id=""><#if plan.novice==1>是<#else>否</#if></span></li>
        		
        		<#if plan.addRate!=0&&plan.addRate!="">
        		<li>加息金额：<span id="validMoney" style="color:red">随息计算随息派发</span></li>
        		</#if>
        		<#if plan.coupon==1>
        		<#if plan.rebateType!=4>
        		<li>面值折现比：<span id="returnRatio">${plan.returnRatio}</span>%</li>
        		<li>有效面值：<span id="validMoney" style="color:red">0</span>元</li>
        		<li>优惠券单笔最大面值：<span id="maxCouponMoney">${plan.maxCouponMoney}</span>元</li>
        		</#if>
        		<li>返利方式：<#if plan.rebateWay==1>立返</#if><#if plan.rebateWay==2>随期</#if><#if plan.rebateWay==3>到期</#if></li>
        		<li>返利类型：<#if plan.rebateType==1>返现</#if><#if plan.rebateType==2>返息</#if>
            			<#if plan.rebateType==3>返息现</#if><#if plan.rebateType==4>加息</#if></li>
        		</#if>
        		<#-- <li>返现金额：<span id="ratioMoney" style="color:red;"></span>元</li>-->
        		<#--<li>加息金额：<span id="addrate"><#if plan.addRate!=0>投标金额*日化贷款利率*贷款天数</#if></span></li>-->
        		
        		<li><a class="buttonorange1" style="padding:8px 40px;" href="${base}/financePurchase/rechargeFinancePurchase.do" target="_self"><span class="white">立刻充值</span></a></li>
        	</ul>        
        </div>
        </div>
	</div>
</div>
</form>
