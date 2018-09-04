<form  id="imageForm" action="${base}/user/updateImagesBpCustMember.do" method="post" enctype="multipart/form-data">
<!--		<div style="width:680px; height:160px;">
			<div style="float:left;width:126px; height:126px; background-image:url('${base}/theme/${systemConfig.theme}/images/avatarborder.jpg'); padding:12px;">
				<#if bpCustMember.headImage==null>
					<img id="user_image_img" src="${base}/theme/${systemConfig.theme}/images/avatar.jpg" width="125" height="125"/>
				<#else>
					<img id="user_image_img" src="${base}/${bpCustMember.headImage}" width="125" height="125"/>
				</#if>
			</div>
			<div style="float:left; width:400px; height:120px; padding:20px 30px;">
				<span class="small black" style="line-height:200%;">您可以上传jpg、jpeg、png、或bmp格式，文件大小不超过10MB。 <br/>
					为了您的安全，请不要上传个人隐私信息，如身份证等。
				</span>
				
					<dd> <input type="hidden" name="id" id="id" value="${bpCustMember.id}" /></dd>
					<dd> <input type="hidden" name="path" id="path" value="${base}"/></dd>
					<input id="file" name="file" type="file" />
					
					<div id="user_imageQueue"></div>
					<div style="display:none;"></div><br/>
					<a href="javascript:void(0);" id="imageBtn" name="imageBtn"><div  style="background-image:url('${base}/theme/${systemConfig.theme}/images/uploadpic.jpg'); width:112px; height:32px;"></div></a>
				
			</div>
		</div>	-->	
	</from>
		<ul class="certname">
			<li><label>用 户 名 ：</label><span> ${bpCustMember.loginname}</span></li>
			<li>
				<label>认证信息：</label>
				<span>							
					<#if bpCustMember.cardcode!=null>
					<a href="javascript:alert('您已进行过实名认证');"
						<#if bpCustMember.cardcode!=null> class="certified name" <#else> class="failed" </#if> 
						title="实名认证">&nbsp;</a>
					<#else>
						<a class="certified unname"	title="实名认证" href="${base}/user/getBpCustMember.do?id=${bpCustMember.id}&typ=3">&nbsp;</a>
					</#if>
					
					<#if bpCustMember.telphone!=null>
					<a href="javascript:alert('您已进行过手机验证');"
						<#if bpCustMember.telphone!=null> class="certified mobile" <#else> class="failed" </#if>
						title="手机验证">&nbsp;</a>
					<#else>
						<a  href="${base}/user/getBpCustMember.do?id=${bpCustMember.id}&typ=4"	class="certified unmobile"	title="手机验证">&nbsp;</a>
					</#if>
					
					<span class="normal gray">[请选择左侧的图标进行认证]</span>
					
					
				</span>
			</li>
			<!--<li><label>会员等级：</label><span></span></li>
			<li><label>我的积分：</label><span>123</span></li>-->
			<!--<li><label>平台账户金额：</label><span>￥${bpCustMember.myPMoneymoremoreMoney!}元</span></li> -->
			<li><label>累计已获收益：</label><span>￥<#if bpCustMember.allInterest==0>0.00<#else>${bpCustMember.allInterest?string(',##0.00')}</#if>元</span></li>
			<li><label>累计投资金额：</label><span>￥<#if bpCustMember.totalInvestMoney==0>0.00<#else>${bpCustMember.totalInvestMoney?string(',##0.00')}</#if>元</span></li>
			<li><label>累计收回本金：</label><span>￥<#if bpCustMember.principalRepayment==0>0.00<#else>${bpCustMember.principalRepayment?string(',##0.00')}</#if>元</span></li>
			<!--<li><label>累计充值金额：</label><span>￥<#if bpCustMember.allInterest==0>0.00<#else>${bpCustMember.totalRecharge?string(',##0.00')}</#if>元</span></li>
			<li><label>累计取现金额：</label><span>￥${bpCustMember.totalEnchashment?string(',##0.00')}元</span></li>-->
			<li><label>账户总额：</label><span>￥<#if bpCustMember.totalMoney==0>0.00<#else>${bpCustMember.totalMoney?string(',##0.00')}</#if>元</span></li>
			<li><label>冻结金额：</label><span>￥<#if bpCustMember.freezeMoney==0>0.00<#else>${bpCustMember.freezeMoney?string(',##0.00')}</#if>元</span></li>
			<li><label>可用金额：</label><span>￥<#if bpCustMember.availableInvestMoney==0>0.00<#else>${bpCustMember.availableInvestMoney?string(',##0.00')}</#if>元</span></li>

		</ul>            	