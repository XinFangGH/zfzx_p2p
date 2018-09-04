<form id="updateBaseUserInfoForm" action="${base}/user/updateBpCustMember.do" enctype="multipart/form-data"  method="post" class="bd">
	<ul class="certname">
		<li> <input type="hidden" name="id" id="id" value="${bpCustMember.id}"/>
			 <input type="hidden" name="path" id="path" value="${base}"/></li>
		<li style="height:40px">
			<label style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">真实姓名：</label>
			<input type="text"  class="colorful" id="truename" <#if bpCustMember.truename!> readonly="true"</#if> name="truename" value="${bpCustMember.truename}" maxlength="10"/>
		</li>
		<li  style="height:40px">
			<label style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">生日：</label>
			<input type="text" class="colorful" id="birthday" name="birthday" <#if bpCustMember.birthday!> readonly="true"</#if> value="${bpCustMember.birthday}" onclick="new Calendar().show(this);"/>
		</li>
		<li  style="height:40px">
			<label style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">婚姻状态：</label>
			<span class="sex">
				<input type="radio" name="marry" value="1" id="married" <#if bpCustMember.marry ==1 >checked</#if>/><label for="married">已婚</label>
				<input type="radio" name="marry" value="0" id="notMarried" <#if bpCustMember.marry ==0 >checked</#if>/><label for="notMarried">未婚</label>
			</span>
		</li>
		<li  style="height:40px">
			<label style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">籍贯:</label>
			<input type="hidden" id="nativePlaceProvice1" name="nativePlaceProvice1" value='${bpCustMember.nativePlaceProvice}' />
			<select class="colorful" id="nativePlaceProvice" name="nativePlaceProvice">
	    		<option value="">请选择</option>
	    	</select>
	    	<input type="hidden" id="nativePlaceCity1" name="nativePlaceCity1" value='${bpCustMember.nativePlaceCity}' />
	    	<select class="colorful" id="nativePlaceCity" name="nativePlaceCity">
	    		<option value="">请选择</option>
	    	</select>
		</li>
		<li  style="height:40px">
			<label style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">固定电话:</label>
			<input type="text" class="colorful" id="homePhone" name="homePhone" value="${bpCustMember.homePhone}" maxlength="25"/>
		</li>
		<li  style="height:40px;">
			<label style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">联系地址:</label>
			<textarea name="relationAddress" class="" id="relationAddress" maxlength="120" style="width: 500px;" value="${bpCustMember.relationAddress}">${bpCustMember.relationAddress}</textarea>
		</li>
		<li  style="height:40px;margin-top:16px;">
			<label style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">邮政编码:</label>
			<input type="text" class="colorful" id="postCode" name="postCode" value="${bpCustMember.postCode}" maxlength="6"/>
		</li>
		<!--<li  style="height:40px">-->
			<!--<label style="float: left;margin-top: 0;width: 80px;text-align: right;margin-right: 15px;">传真:</label>-->
			<#--<input type="text"  class="colorful" id="fax" name="fax" value="${bpCustMember.fax}" maxlength="20"/> -->
		<!--</li>-->
		<li  style="height:40px">
			<div tabindex="4" style="text-align:center;width:120px; height:30px; line-height:30px; float:left; margin:8px 0px 0px 98px;  font-size:16px;"
			 id="updateBaseUserInfo" class="buttonorange">确认修改</div>
		</li>
	</ul>
</form>