<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 微博认证</title>
<!-- 上传文件js开始-->
<link href="${base}/js/swfupload/scripts/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script language= "javascript" src="${base}/js/swfupload/scripts/json.js"></script>
<script type="text/javascript" src="${base}/js/swfupload/scripts/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${base}/js/swfupload/scripts/swfupload/swfupload.queue.js"></script>
<script type="text/javascript" src="${base}/js/swfupload/scripts/swfupload/fileprogress.js"></script>
<script type="text/javascript" src="${base}/js/swfupload/scripts/swfupload/swfuploadProperties.js"></script>
<script type="text/javascript" src="${base}/js/swfupload/scripts/swfupload/handlers.js"></script>
<!-- 上传文件js结束-->
<script type="text/javascript">

	function closeDiag(){
		 var id = document.getElementById("DiagId").value;
		 var loadid=$("#loadid").val();
		 var currentnode=$("#currentnode").val();
		 parent.document.getElementById("_DialogFrame_"+id).style.display = "none";
		 //隐藏table
		 parent.document.getElementById("_DialogTable_"+id).style.display = "none";
		 //去掉遮罩
		 fadeEffect(topWin.$id("_DialogBGMask"),0,0,isIE6?20:10);
		 //关闭后父窗体可点击
		 topWin.$id("_DialogBGDiv").style.cssText = "position:absolute;left:0px;top:0px;width:0;height:0;";
		 //刷新指定路径
		// parent.window.location.href="${base}/financePurchase/retPersonapplyUser.do?id="+loadid;
		//页面刷新后，为了显示下拉框中的数据（数据字典数据）
		parent.window.location.href="${base}/user/getNodeMemBpCustMember.do?menuNode="+currentnode+"&loadid="+loadid;
	}
	
</script>
</head>
<body >
	<div class="bpbox">
	<form>
	<h3>微博认证</h3>
    <p class="text">微博认证是衡量借款人信用的重要依据。</p>
    <ul class="attes">
    	<h4>认证内容：</h4>
    	<li>（1）请点击升升投新浪微博，<a href="http://weibo.com/wenandai" target="_blank" style="color:red;">http://weibo.com/wenandai</a>，并加为关注，或点击升升投QQ微博，<a style="color:red;" href="http://t.qq.com/wenandai" target="_blank">http://t.qq.com/wenandai</a>，并加为关注</li>
    	<li>（2）在个人微博上，转发一条最新的升升投的微博，并将截图上传给升升投</li>
       
    </ul>
    
    <ul class="attes">
    	<h4>认证有效期：<span style="color:red">6个月</span></h4>   
    </ul>
    <div class="picture">
    	<h4>上传资料：</h4>
       <form id="form1" action="${base}/FileUploadServlet" method="post" enctype="multipart/form-data">
				<div>
					<div>
						<div class="fieldset flash" id="fsUploadProgress1">
							<span class="legend">上传文件列表</span>
							<input type="hidden" id="saveDataId1"/>
						</div>
						<div style="padding-left: 5px;">
							<span id="spanButtonPlaceholder1"></span>
							<input id="btnCancel1" type="hidden" value="Cancel Uploads" onClick="cancelQueue(upload1);" disabled="disabled" style="margin-left: 2px; height: 22px; font-size: 8pt;" />
						</div>
					</div>
				</div>
			</form>
         <form id="myForm" action="${base}/material/updateStateMaterial.do" method="post" >
         	
         	  <input type="hidden" value="${diagId}" name="diagId" id="DiagId"/>
         <input type="hidden" value="MicroBlog" name="type"/>
          <input type="hidden" value="${loadid}" name="loadid" id="loadid"/>
          <input type="hidden" value="${currentnode}" name="currentnode" id="currentnode"/>
         <input type="button" class="btn"  onClick="closeDiag()" value="提交审核"/>
        </form>
         <div class="warn">
         <span>★注：</span>
         <br />
         升升投是一个信用交易平台。一旦发现用户上传的资料含有造假行为，升升投会将用户永久拉黑，适当情况下会进行依法披露。请让我们携手，为构建和谐诚信社会共同努力！
         </div>
    </div>
    </form>
</div>
</body>
</html>