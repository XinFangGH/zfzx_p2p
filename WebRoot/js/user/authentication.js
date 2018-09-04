/*
 *材料认证的相关验证
 **/
 
 function veriIDCard(){
	 
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	 var bpCustMemberId = $("#bpCustMemberId").val();
	
 	showAlert(800,600,"身份认证","../material/veriidcardMaterial.do?type=IDCard&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriCreditRecord(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"信用认证",basepath+"material/vericreditRecordMaterial.do?type=CreditRecord&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriIncome(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"收入认证",basepath+"material/veriincomeMaterial.do?type=Income&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriStudent(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"学生认证",basepath+"material/veristudentMaterial.do?type=Income&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 function veriWebShop(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"网店认证",basepath+"material/veriwebShopMaterial.do?type=WebShop&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriHouse(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"房产认证",basepath+"material/verihouseMaterial.do?type=House&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 
 
 function veriWechat(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"微信认证",basepath+"material/veriwechatMaterial.do?type=House&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriVehicle(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"购车认证",basepath+"material/verivehicleMaterial.do?type=Vehicle&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriMarriage(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"结婚认证",basepath+"material/verimarriageMaterial.do?type=Marriage&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriEducation(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"学历认证",basepath+"material/verieducationMaterial.do?type=Education&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriCareer(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"工作认证",basepath+"material/vericareerMaterial.do?type=Career&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriJobTitle(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"职称认证",basepath+"material/verijobtitleMaterial.do?type=JobTitle&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriMobilePhone(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"手机认证",basepath+"material/verimobilephoneMaterial.do?type=MobilePhone&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriMicroBlog(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"微博认证",basepath+"material/verimicroblogMaterial.do?type=MicroBlog&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriResidence(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"居住认证",basepath+"material/veriresidenceMaterial.do?type=Residence&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriCompanyPlace(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"经营场所认证",basepath+"material/vericompanyplaceMaterial.do?type=CompanyPlace&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 
 function veriCompanyRevenue(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"经营收入认证",basepath+"material/vericompanyrevenueMaterial.do?type=CompanyRevenue&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 function veriTeacher(){
	 var loadid=$("#loadid").val();
	 var current=$("#currentnode").val();
	  var bpCustMemberId = $("#bpCustMemberId").val();
	  
 	showAlert(800,600,"教师资格认证",basepath+"material/veriTeacherMaterial.do?type=Teacher&loadid="+loadid+"&current="+current+"&userId="+bpCustMemberId);
 }
 function loginBox(){
	 showAlert(400,400,"登录",basepath+"");
 }
 
 function newMaterialDialog(conditionId,productId){
 	showAlert(800,600,"材料认证","../loan/geteProductMaterialP2pLoanProduct.do?conditionId="+conditionId+"&productId="+productId);
 }
 
 
 
 
 function showAlert(width,height,title,url){
	/*
	 * width:弹出层的显示宽度
	 * height:弹出层的显示高度
	 * title：弹出层的显示标题
	 * url：文件的路径
	 * */
	var diag = new Dialog();
	diag.Width = width;
	diag.Height = height;
	diag.Title = title;
	diag.URL = url;
	diag.show();
}
