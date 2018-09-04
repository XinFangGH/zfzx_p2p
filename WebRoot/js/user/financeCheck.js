
	function peopleCheck(){
		var collegeDegree=$("#collegeDegree");
		if(collegeDegree.val()==''){
			alert("最高学历不能为空");
			return false;
		}
		var collegeYear=$("#collegeYear");
		if(collegeYear.val()==""){
			alert("入学年份不能为空");
			return false;
		}
		var nativePlaceCity=$("#nativePlaceCity");
		if(nativePlaceCity.val()==""){
			alert("籍贯不能为空");
			return false;
		}
		var liveCity=$("#liveCity");
		if(liveCity.val()==""){
			alert("户口所在地不能为空");
			return false;
		}
		var collegename = $("#collegename");
		if (collegename.val()=='') {
			collegename.focus();
			showTip("collegename", "毕业院校不能为空");
			return false;
		}
		var relationAddress = $("#relationAddress");
		if (relationAddress.val()=='') {
			relationAddress.focus();
			showTip("relationAddress", "现居住地址不能为空");
			return false;
		}
		var postCode = $("#postCode");
		if (postCode.val()=='') {
			postCode.focus();
			showTip("postCode", "现居住地址邮编不能为空");
			return false;
		}else{
			if (postCode.val().length>6||!postCode.val().match(/^[0-9]*$/)) {
				postCode.select();
				showTip("postCode", "邮编是有6位数字组成");
				return false;
			}
		}
		
		var homePhone_Prefix =$("#home_PhonePrefix");
		if(homePhone_Prefix.val()!=""){
			if(!homePhone_Prefix.val().match(/^[0-9]{3,4}$/)){
				homePhone_Prefix.select();
				showTip("home_PhonePrefix", "座机区号是由3~4位数字组成");
				return false;
			}
		}
		var homePhone_Suffix = $("#home_PhoneSuffix");
		if(homePhone_Suffix.val()!='')
		{
			if(!homePhone_Suffix.val().match(/^[0-9]*$/)){
				homePhone_Suffix.select();
				showTip("home_PhoneSuffix", "座机号后由数字组成（不算区号）");
				return false;
			}
		}
	}
	
	function familyCheck(){
		var relDirName = $("#relDirName");
		var relDirType=$("#relDirType");
		var relDirPhone =$("#relDirPhone");
		var relOtherName = $("#relOtherName");
		//var relOtherType =$("#relOtherType");
		var relOtherPhone = $("#relOtherPhone");
		var relFriendName=$("#relFriendName"); 
		var relFriendType=$("#relFriendType");
		
		var relFriendPhone=$("#relFriendPhone");
		if (relDirName.val().trim()=='') {
			relDirName.focus();
			showTip("relDirName", "直系亲属姓名不能为空");
			return false;
		}
		if(relDirType.val()==""){
			alert("直系亲属关系不能为空");
			return false;
		}
		if (relDirPhone.val()=='') {
			relDirPhone.focus();
			showTip("relDirPhone", "直系亲属号码不能为空");
			return false;
		}else{
			if(!isMobile(relDirPhone.val())){
				relDirPhone.select();
				showTip("relDirPhone", "手机号码格式不正确");
				return false;
			}else{
				if(relDirPhone.val()==relOtherPhone.val()){
					relDirPhone.select();
					showTip("relDirPhone", "该手机号已填写");
					return false;
				}
				if(relDirPhone.val()==relFriendPhone.val()){
					relDirPhone.select();
					showTip("relDirPhone", "该手机号已填写");
					return false;
				} 
			}
		}
		
		if (relOtherName.val().trim()=='') {
			relOtherName.focus();
			showTip("relOtherName", "其他亲属姓名不能为空");
			return false;
		}
		/*if (relOtherType.val()=='') {
			relOtherType.focus();
			showTip("relOtherType", "其他亲属关系不能为空");
			return false;
		}*/
		if (relOtherPhone.val()=='') {
			relOtherPhone.focus();
			showTip("relOtherPhone", "其他亲属号码不能为空");
			return false;
		}else{
			if(!isMobile(relOtherPhone.val())){
				relOtherPhone.select();
				showTip("relOtherPhone", "手机号码格式不正确");
				return false;
			}else{
				if(relDirPhone.val()==relOtherPhone.val()){
					relOtherPhone.select();
					showTip("relOtherPhone", "该手机号已填写");
					return false;
				}
				if(relOtherPhone.val()==relFriendPhone.val()){
					relOtherPhone.select();
					showTip("relOtherPhone", "该手机号已填写");
					return false;
				}
			}
		}
		
		if(relFriendName.val().trim()==''){
			relFriendName.focus();
			showTip("relFriendName", "其他联系人姓名不能为空");
			return false;
		}
		if(relFriendType.val()==""){
			alert("其他联系人关系不能为空");
			return false;
		}
		if(relFriendPhone.val()==''){
			relFriendPhone.focus();
			showTip("relFriendPhone", "手机号码不能为空");
			return false;
		}else{
			if(!isMobile(relFriendPhone.val())){
				relFriendPhone.select();
				showTip("relFriendPhone", "手机号码格式不正确");
				return false;
			}else{
				if(relFriendPhone.val()==relDirPhone.val()){
					relFriendPhone.select();
					showTip("relFriendPhone", "该手机号已填写");
					return false;
				}
				if(relFriendPhone.val()==relOtherPhone.val()){
					relFriendPhone.select();
					showTip("relFriendPhone", "该手机号已填写");
					return false;
				}
			}
		}
		return true;
	}
	
	function theNetWorkCheck(){
		var webshopName = $("#webshopName");
		
		if (webshopName.val()=='') {
			webshopName.focus();
			showTip("webshopName", "网点名称不能为空");
			return false;
		}
		/*if (webshopMonthlyincome.length<=0) {
			showTip("webshopMonthlyincome", "月收入不能为空");
			return false;
		}else{
			var reg = /(?!^0\d*$)^\d{1,16}(\.\d{1,2})?$/;
			 flag = reg.test(webshopMonthlyincome);
			if(!flag){
				showTip("webshopMonthlyincome", "金额必须由数字组成！");
				return false;
			}
		}*/
		var webshopEmail = $("#webshopEmail");
		if (webshopEmail.val()=='') {
			webshopEmail.focus();
			showTip("webshopEmail", "工作邮箱不能为空");
			return false;
		}else{
			if (!webshopEmail.val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
				webshopEmail.select();
				showTip("webshopEmail", "邮箱格式不正确");
				return false;
			}
		}
		
		var webshopAddress = $("#webshopAddress");
		if (webshopAddress.val()=='') {
			webshopAddress.focus();
			showTip("webshopAddress", "网站经营地址不能为空");
			return false;
		}
		var webshopStartyear=$("#webshopStartyear");
		if(webshopStartyear.val()==""){
			alert("网站经营年限不能为空");
			return false;
		}
		var webshopPhone_Prefix = $("#webshop_PhonePrefix");
		if(webshopPhone_Prefix.val()==''){
			webshopPhone_Prefix.focus();
			showTip("webshop_PhonePrefix", "请填写座机区号");
			return false;
		}else{
			if(!webshopPhone_Prefix.val().match(/^[0-9]{3,4}$/)){
				webshopPhone_Prefix.select();
				showTip("webshop_PhonePrefix", "座机区号是由3~4位数字组成");
				return false;
			}
		}
		var webshopPhone_Suffix = $("#webshop_PhoneSuffix");
		if(webshopPhone_Suffix.val()==''){
			webshopPhone_Suffix.focus();
			showTip("webshop_PhoneSuffix", "请填写座机号");
			return false;
		}else{
			if(!webshopPhone_Suffix.val().match(/^[0-9]*$/)){
				webshopPhone_Suffix.select();
				showTip("webshop_PhoneSuffix", "座机号后由数字组成（不算区号）");
				return false;
			}
		}
	}

	function workCheck(){
	
		var hireCompanyname =$("#hireCompanyname");
		if (hireCompanyname.val()=='') {
			hireCompanyname.focus();
			showTip("hireCompanyname", "单位名称不能为空");
			return false;
		}
		var hirePosition=$("#hirePosition");
		if(hirePosition.val()==""){
			alert("职位不能为空！");
			return false;
		}	
		var hireMonthlyincome =$("#hireMonthlyincome");
		if (hireMonthlyincome.val()=='') {
			hireMonthlyincome.focus();
			showTip("hireMonthlyincome", "月收入不能为空");
			return false;
		}else{
			var reg = /(?!^0\d*$)^\d{1,16}(\.\d{1,2})?$/;
			 flag = reg.test(hireMonthlyincome.val());
			if(!flag){
				hireMonthlyincome.select();
				showTip("hireMonthlyincome", "金额必须由数字组成！");
				return false;
			}
		}
		var hireEmail =$("#hireEmail");
		if (hireEmail.val()=='') {
			hireEmail.focus();
			showTip("hireEmail", "工作邮箱不能为空");
			return false;
		}else{
			if (!hireEmail.val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
				hireEmail.select();
				showTip("hireEmail", "邮箱格式不正确");
				return false;
			}
		}
		var hireAddress = $("#hireAddress");
		if (hireAddress.val()=='') {
			hireAddress.focus();
			showTip("hireAddress", "公司地址不能为空");
			return false;
		}
		var hireCompanytype=$("#hireCompanytype");
		if(hireCompanytype.val()==""){
			alert("公司类别不能为空！");
			return false;
		}
		var hireCompanycategory=$("#hireCompanycategory");
		if(hireCompanycategory.val()==""){
			alert("公司行业不能为空！");
			return false;
		}
		var hireCompanysize=$("#hireCompanysize");
		if(hireCompanysize.val()==""){
			alert("公司规模不能为空！");
			return false;
		}
		var purchaseTime=$("#purchase_time_start");
		if(purchaseTime.val()==""){
			alert("工作起始日期不能为空");
			return false;
		}
		
		var hireCompanyphone_Prefix =$("#hireCompanyphone_Prefix");
		if(hireCompanyphone_Prefix.val()==''){
			hireCompanyphone_Prefix.focus();
			showTip("hireCompanyphone_Prefix", "请填写座机区号");
			return false;
		}else{
			if(!hireCompanyphone_Prefix.val().match(/^[0-9]{3,4}$/)){
				hireCompanyphone_Prefix.select();
				showTip("hireCompanyphone_Prefix", "座机区号是由3~4位数字组成");
				return false;
			}
		}
		var hireCompanyphone_Suffix =$("#hireCompanyphone_Suffix");
		if(hireCompanyphone_Suffix.val()==''){
			hireCompanyphone_Suffix.focus();
			showTip("hireCompanyphone_Suffix", "请填写座机号");
			return false;
			
		}else{
			if(!hireCompanyphone_Suffix.val().match(/^[0-9]*$/)){
				hireCompanyphone_Suffix.select();
				showTip("hireCompanyphone_Suffix", "座机号后由数字组成（不算区号）");
				return false;
			}
		}
		return true;
	}
	
	function blurLoanTitle(obj){
		if(obj.length<1){
			showTip("loanTitle", "借款标题不能为空");
		}
	}
	
	function blurLoanMoney(obj){
		if(obj.length<1){
			showTip("loanMoney", "借款金额不能为空");
		}
		if(!obj.match(/^[0-9]*$/)){
			showTip("loanMoney", "借款金额必须有数字组成");
		}
	}
	function blurExpectAccrual(obj){
		if(obj.length<1){
			showTip("expectAccrual", "年化利率不能为空");
		}
		if(!obj.match(/^[0-9]*$/)){
			showTip("expectAccrual", "年化利率必须有数字组成");
		}
	}
	
	function blurPayIntersetWay(obj){
		if(obj.length<1){
			showTip("payIntersetWay", "还款方式不能为空");
		}
	}
	
	function blurRemark(obj){
		if(obj.length<1){
			showTip("remark", "借款描述不能为空");
			return false;
		}
	}
	
	function saveApply(){
		if($('#loanTitle').val()==''){
			showTip("loanTitle", "借款标题不能为空");
			return false;
		}
		/*if($("#selectid").val()==''){
			showTip("selectid", "借款用途不能为空");
			return false;
		}
*/		if($('#loanMoney').val()==''){
			showTip("loanMoney", "借款金额不能为空");
			return false;
		}
		if(!$('#loanMoney').val().match(/^[0-9]*$/)){
			showTip("loanMoney", "借款金额必须有数字组成");
			return false;
		}
		if($('#expectAccrual').val()==''){
			showTip("expectAccrual", "年化利率不能为空");
			return false;
		}
		if(!$('#expectAccrual').val().match(/^[0-9]*$/)){
			showTip("expectAccrual", "年化利率必须有数字组成");
			return false;
		}
		if($('#remark').val().length<1){
			showTip("remark", "借款描述不能为空");
			return false;
		}
		var chk = document.getElementById('readAgreement');
		if(!chk.checked){
			showTip("readAgreement", "您未选中升升投的服务协议");
		    return false;
		}
		return true;
	}
	function saveCom(){
		var businessName=$("#businessName").val();
		var loanMoney=$("#loanMoney").val();
		var loanTimeLen=$("#loanTimeLen").val();
		var linkPersion=$("#linkPersion").val();
		var phone=$("#phone").val();
		if(businessName==""){
			showTip("businessName", "企业名称不能为空");
			return false;
		}
		if(loanMoney==""){
			showTip("loanMoney", "期望贷款金额不能为空");
			return false;
		}
		if(loanTimeLen==""){
			showTip("loanTimeLen", "期望贷款期限不能为空");
			return false;
		}
		if(linkPersion==""){
			showTip("linkPersion", "联系人不能为空");
			return false;
		}else if (!isChina(linkPersion)) {
			showTip("linkPersion", "联系人必须为中文！");
			return false;
		} else if(linkPersion.length>4){
			showTip("linkPersion", "联系人不能超过4个字！");
			return false;
		}
		if(phone==""){
			showTip("phone", "手机号不能为空");
			return false;
		}else{
			if(!isMobile(phone)){
				showTip("phone", "手机号码格式不正确");
				return false;
			}
		}
	}
	function savePer(){//个人
		var typeCode=$("#typeCode").text();
		var linkPersion=$("#linkPersion").val();
		var phone=$("#phone").val();
		var loanMoney=$("#loanMoney").val();
		var loanTimeLen=$("#loanTimeLen").val();
		var checkCode = $("#checkCode").val();
		if(linkPersion==""){
			showTip("linkPersion", "联系人不能为空");
			return false;
		}else if (!isChina(linkPersion)) {
			showTip("linkPersion", "联系人必须为中文！");
			return false;
		} else if(linkPersion.length>4){
			showTip("linkPersion", "联系人不能超过4个字！");
			return false;
		}
		if(phone==""){
			showTip("phone", "手机号不能为空");
			return false;
		}else{
			if(!isMobile(phone)){
				showTip("phone", "手机号码格式不正确");
				return false;
			}
		}
		if(loanMoney==""){
			showTip("loanMoney", "期望贷款金额不能为空");
			return false;
		}
		if(loanTimeLen==""){
			showTip("loanTimeLen", "借款期限不能为空");
			return false;
		}
		if(checkCode==""){
			showTip("checkCodeB", "验证码不能为空");
			return false;
		}
		if(typeCode!="success"){
			showTip("checkCode", "验证码输入错误");
			return false;
		}
	}
	function saveBusiness(){//企业
		var typeCode = $("#typeCode").text();
		var businessNameB=$("#businessNameB").val();
		var linkPersion=$("#linkPersionB").val();
		var phone=$("#phoneB").val();
		var loanMoney=$("#loanMoneyB").val();
		var loanTimeLen=$("#loanTimeLenB").val();
		var checkCode = $("#checkCodeB").val();
		if(businessNameB==""){
			showTip("businessNameB", "企业名称不能为空");
			return false;
		}
		if(linkPersion==""){
			showTip("linkPersionB", "联系人不能为空");
			return false;
		}else if (!isChina(linkPersion)) {
			showTip("linkPersionB", "联系人必须为中文！");
			return false;
		} else if(linkPersion.length>4){
			showTip("linkPersionB", "联系人不能超过4个字！");
			return false;
		}
		if(phone==""){
			showTip("phoneB", "手机号不能为空");
			return false;
		}else{
			if(!isMobile(phone)){
				showTip("phoneB", "手机号码格式不正确");
				return false;
			}
		}
		if(loanMoney==""){
			showTip("loanMoneyB", "期望贷款金额不能为空");
			return false;
		}
		if(loanTimeLen==""){
			showTip("loanTimeLenB", "借款期限不能为空");
			return false;
		}
		if(checkCode==""){
			//alert("验证码不能为空");
			showTip("checkCodeB", "验证码不能为空");
			return false;
		}
		
		if(typeCode!="success"){
			showTip("checkCodeB", "验证码输入错误");
			return false;
		}
	}
	
	/*$(function(){
	$("#areaSelect").live("change",function(){
 		var areaCode = $("#areaSelect").val();//获取编号 
 		if(areaCode!=""){
 			var num = 0;
			$.ajax({
				type : "POST",
				dataType : "JSON",
				async:false,
				url : basepath + "/user/getLevelTwoArea.do?parentCode="+ areaCode,
				success : function(responseText, statusText) {
					if(statusText == "success"){
						num++;
						if(num!=0){
							$("#areaSelectLevel").remove();
						}
						$("#selecDiv").append("<select class='colorful'  style='width:100px;height:32px' id='areaSelectLevel' ></select>");
						$("#areaSelectLevel").append("<option value=''>--请选择地区 --</option>");
						var list=eval(responseText.result);
						
						$.each(list,function(index,item){
							//alert("item=="+item.AreaName);
							$("#areaSelectLevel").append("<option value="+item.AreaCode+">"+item.AreaName+"</option>");
						});
					}else{
						if(num!=0){
							$("#areaSelectLevel").remove();
						}
					}
				},
				error : function(request) {
					alert("处理数据时出现异常！");
				}
			});
 		}
	
	});
});*/