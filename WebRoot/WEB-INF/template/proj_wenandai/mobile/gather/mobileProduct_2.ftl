 <form action="../mobileProduct/getNodeMem_PDAction.do" method="post" onsubmit="return valsub()">
  				<input type="hidden" name="bpCustMember.id" value="${(bpCustMember.id)!}" />
                <input type="hidden" name="loadid" id="loadid" value="${(financeApplyUser.loanId)!}"/>
                    <ul class="two">
                        <li>
                            <div>
                                <label class="label1">姓名:</label>
                                <input type="text" name="bpCustMember.truename" value="${(bpCustMember.truename)!}" placeholder="请填写真实姓名">
                            </div>
                        </li>
                        <li>
                            <div>
                                <label class="label2">手机号:</label>
                                <input type="text" disabled="disabled" name="bpCustMember.telphone" value="${bpCustMember.telphone}" placeholder="请填写真实手机号">
                            </div>
                        </li>
                        <li>
                            <div>
                                <label class="label2">身份证:</label>
                                <input type="text" name="bpCustMember.cardcode" value="${bpCustMember.cardcode}" placeholder="请填写身份证号码">
                            </div>
                        </li>
                        
                        
                        
                        <li class="spec"></li>
                        <li class="qinshu">
                            <div>
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;直系亲属:</label>
                                <select class="selectcolor" name="bpCustMember.relDirType" id="relDirType">
                                    <option value="1">请选择亲属关系</option>
                                    <option value="952"<#if bpCustMember.relDirType=='952'> selected="selected"</#if>>父亲</option>
                                    <option value="954"<#if bpCustMember.relDirType=='954'> selected="selected"</#if>>母亲</option>
                                    <option value="953"<#if bpCustMember.relDirType=='953'> selected="selected"</#if>>子女</option>
                                </select>
                                <input type="text" name="bpCustMember.relDirName" id="relDirName" value="${(bpCustMember.relDirName)!}" placeholder="姓名" class="selectcolor1">
                                <input type="text" name="bpCustMember.relDirPhone" id="relDirPhone" value="${(bpCustMember.relDirPhone)!}" placeholder="该亲属的手机号" class="selectcolor2">
                            </div>
                        </li>
                        <li class="qinshu">
                            <div>
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;其它亲属:</label>
                                <select class="selectcolor" name="bpCustMember.relOtherType">
                                    <option value="575">亲属</option>
                                </select>
                                <input type="text" name="bpCustMember.relOtherName" id="relOtherName" value="${(bpCustMember.relOtherName)!}" placeholder="姓名" class="selectcolor1">
                                <input type="text" name="bpCustMember.relOtherPhone" id="relOtherPhone" value="${(bpCustMember.relOtherPhone)!}" placeholder="该亲属的手机号" class="selectcolor2">
                            </div>
                        </li>
                        <li class="qinshu">
                            <div>
                                <label>其它联系人:</label>
                                <select class="selectcolor" name="bpCustMember.relFriendType" id="relFriendType">
                                    <option value="1">请选择亲属关系</option>
                                    <option value="577" <#if bpCustMember.relFriendType=='577'> selected="selected"</#if>>同事</option>
                                    <option value="955" <#if bpCustMember.relFriendType=='955'> selected="selected"</#if>>同学</option>
                                    <option value="576" <#if bpCustMember.relFriendType=='576'> selected="selected"</#if>>朋友</option>
                                </select>
                                <input type="text" id="relFriendName" name="bpCustMember.relFriendName" value="${(bpCustMember.relFriendName)!}" placeholder="姓名" class="selectcolor1">
                                <input type="text" id="relFriendPhone" name="bpCustMember.relFriendPhone" value="${(bpCustMember.relFriendPhone)!}" placeholder="该亲属的手机号" class="selectcolor2">
                            </div>
                        </li>
                        
                        
                        
                        
                        <li class="spec"></li>
                        <li>
                            <div>
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;入学年份:</label>
                             <!--   <select name="bpCustMember.collegeYear" value="${bpCustMember.collegeYear}"  id="collegeYear" placeholder="请选择">
                                 <option value="1" id="1startschool">请选择入学年份</option>
                                <#list listDate as startschool>
                                	<#if bpCustMember.collegeYear==startschool>
                                    	<option value="${startschool}" id="${startschool}startschool" selected="selected">${startschool}</option>
                                    <#else>
                                   		<option value="${startschool}" id="${startschool}startschool">${startschool}</option>
                                    </#if>
                                </#list>
                                </select>-->
                                <input type="date" name="bpCustMember.collegeYear" id="collegeYear" placeholder="请选择">
                            </div>
                        </li>
                        <li>
                            <div>
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;就读院校:</label>
                                <input type="text" name="bpCustMember.collegename" id="collegename" value="${(bpCustMember.collegename)!}" placeholder="请填写您所就读的学校" class="selectcolor1">
                            </div>
                        </li>
                        <li class="hukou">
                            <div>
                                <label> 户口所在地:</label>
                                <select class="selectcolor" id="family" name="bpCustMember.liveProvice">
                                <#list listArea6591 as area>
                                    <option value="${area.id}" id="${area.id}">${area.title}</option>
                                </#list>
                                </select>
                                <select class="selectcolor" id="familychild">
                                    
                                </select>
                            </div>
                        </li>
                        <li class="spec"></li>
                        <li class="gudingdianhua">
                            <div>
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;固定电话:</label>
                                <input type="text" name="bpCustMember.homePhonePrefix" id="home_PhonePrefix" value="${bpCustMember.homePhonePrefix}" placeholder="区号" class="selectcolor1">
                                <input type="text" name="bpCustMember.homePhoneSuffix" id="home_PhoneSuffix" value="${bpCustMember.homePhoneSuffix}" placeholder="现所居住地的电话号码" class="selectcolor2">
                            </div>
                        </li>
                        <li class="textareawrap">
                            <div>
                                <label>&nbsp;&nbsp;&nbsp;&nbsp;居住地址:</label>
                                <input type="text" name="bpCustMember.relationAddress" id="relationAddress" value="${(bpCustMember.relationAddress)!}">
                            </div>
                        </li>
                        <li>
                            <div>
                                <label> 居住地邮编:</label>
                                <input type="text" name="bpCustMember.postCode" id="postCode" value="${(bpCustMember.postCode)!}" placeholder="请填写您现在所居住地的邮政编码">
                            </div>
                        </li>
                    </ul>
                     <div class="login_submit"><input type="submit" value="同意并继续"></div>
   </form>
    <script type="text/javascript">
   $(function(){
   var a = '${(bpCustMember.collegeYear)!""}';
   if(a!=""){
	    $("#${(bpCustMember.collegeYear)!}startschool").attr("selected", true);
   }
    	var province = '${(csDicAreaLiveProvice.id)!}';
    	var city = '${(csDicAreaLiveCity.id)!}';
    		$("#"+province).attr("selected", true);
    		if(province=='')province='7433';
    		$.ajax({
				type : "POST",
				dataType : "JSON",
				async:false,
				url : "../financePurchase/getNextLevelapplyUser.do?parentId="+ province,
				success : function(responseText, statusText) {
					
					if(statusText == "success"){
						var list = eval(responseText.result);
						var opt = document.getElementById("familychild");
						$("#familychild option").remove();
						$.each(list,function(index,item){
							if(item.id==city){
								$("#familychild").append("<option value="+item.id+" id="+item.id+" selected=\"selected\">"+item.title+"</option>");
							}else{
								$("#familychild").append("<option value="+item.id+" id="+item.id+">"+item.title+"</option>");
							}
						});
						
					}else{
						$('.tips').text("额，没有对应的数据哦！");
           				$(".tipswrap").show();
					}
				},
				error : function(request) {
					$('.tips').text("处理数据时出现异常！");
           			$(".tipswrap").show();
				}
			});
    
    
    
    
    });
    
    
    
    
    $("#family").change(function (){//户籍联动
	        var objS = document.getElementById("family");
	        var grade = objS.options[objS.selectedIndex].value;
	        if(grade==''||grade==null)grade='7433';
	        //子集查询
	        $.ajax({
				type : "POST",
				dataType : "JSON",
				async:false,
				url : "../financePurchase/getNextLevelapplyUser.do?parentId="+ grade,
				success : function(responseText, statusText) {
					
					if(statusText == "success"){
						var list = eval(responseText.result);
						var opt = document.getElementById("familychild");
						$("#familychild option").remove();
						$.each(list,function(index,item){
						var s = "ss";
							$("#familychild").append("<option value="+item.id+" id="+item.id+">"+item.title+"</option>");
						});
						
					}else{
						$('.tips').text("额，没有对应的数据哦！");
           				$(".tipswrap").show();
					}
				},
				error : function(request) {
					$('.tips').text("处理数据时出现异常！");
           			$(".tipswrap").show();
				}
			});
	        
	        
	        
       });
       
       
       
       
     function  valsub(){
     
				     var relDirType = $("#relDirType").val();
				     var relFriendType = $("#relFriendType").val();
				     var familychild = $("#familychild").val();
				     var relDirName = $("#relDirName").val();
				     var relOtherName = $("#relOtherName").val();
				     var home_PhonePrefix = $("#home_PhonePrefix").val();
				     var home_PhoneSuffix = $("#home_PhoneSuffix").val();
				     var postCode = $("#postCode").val();
				     var collegename = $("#collegename").val();
				     var relFriendName = $("#relFriendName").val();
				     var relationAddress = $("#relationAddress").val();
				     var relDirPhone = $("#relDirPhone").val();
				     var relOtherPhone = $("#relOtherPhone").val();
				     var relFriendPhone = $("#relFriendPhone").val();
				     var collegeYear = $("#collegeYear").val();
				     
				     if(relDirType=="1"||relDirType==null){
				        al("请选择直系亲属");
				     	return false;
				     }
				     if(relFriendType=="1"||relFriendType==null){
				        al("请选择其他联系人");
				     	return false;
				     }
				     if(relDirName==""||relDirName==null){
				        al("直系亲属姓名不能为空");
				     	return false;
				     }
				     if(relOtherName==""||relOtherName==null){
				        al("其他亲属姓名不能为空");
				     	return false;
				     }
				     if(relOtherName==""||relOtherName==null){
				        al("其他联系人姓名不能为空");
				     	return false;
				     }
				     if(collegename==""||collegename==null){
				        al("就读院校不能为空")
				     	return false;
				     }
				     if(home_PhonePrefix==""||home_PhonePrefix==null){
				     	 al("座机区号不能为空")
				     	 return false;
				     }else if(!home_PhonePrefix.match(/^[0-9]{3,4}$/)){
				      	 al("座机区号是由3~4位数字组成")
				     	 return false;
				     }
				     
				     if(home_PhoneSuffix==""||home_PhoneSuffix==null){
				     	 al("电话号码不能为空")
				     	 return false;
				     }else if(!home_PhoneSuffix.match(/^[0-9]*$/)){
				     	 al("座机号码是由数字组成")
				     	 return false;
				     }
				     if(postCode==""||postCode==null){
				     	 al("邮编不能为空")
				     	 return false;
				     }else if(!postCode.match(/^[0-9]{6}$/)){
				     	 al("邮编是有6位数字组成")
				     	 return false;
				     
				     }
				     
				     if(relationAddress==""||relationAddress==null){
				     	 al("居住地址不能为空")
				     	 return false;
				     }
				     if(relFriendName==""||relFriendName==null){
				     	 al("其他亲属姓名不能为空")
				     	 return false;
				     }
				     
				      if(relDirPhone==""||relDirPhone==null){
				     	 al("亲属手机号码不能为空")
				     	 return false;
				     }else if(!relDirPhone.match(/^1[3|4|7|5|8][0-9]\d{8}$/)){
				      	al("亲属手机号码格式不对")
				     	 return false;
				     
				     }
				      if(relOtherPhone==""||relOtherPhone==null){
				     	 al("其他亲属手机号码不能为空")
				     	 return false;
				     }else if(!relOtherPhone.match(/^1[3|4|7|5|8][0-9]\d{8}$/)){
				      	al("其他亲属手机号码格式不对")
				     	 return false;
				     
				     }
				      if(relFriendPhone==""||relFriendPhone==null){
				     	 al("其他联系人手机号码不能为空")
				     	 return false;
				     }else if(!relFriendPhone.match(/^1[3|4|7|5|8][0-9]\d{8}$/)){
				      	al("其他联系人手机号码格式不对")
				     	 return false;
				     }
				     if(collegeYear=="1"||collegeYear==null){
				     	al("请选择入学年份")
				     	 return false;
				     }
     	return true;
     }
       
    function al(text){
    	$('.tips').text(text);
        $(".tipswrap").show();
     	return false;
    }
    </script>