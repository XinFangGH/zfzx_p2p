<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 工作认证</title>
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
		  parent.window.location.href="${base}/user/getNodeMemBpCustMember.do?menuNode="+currentnode+"&loadid="+loadid;
	}
	
</script>
</head>
<body >

	<div class="bpbox">
	<form>
	<h3>教师资格认证</h3>
    <p class="text">教师资格证是借款人申请“园丁贷”，并享受教师专有借款渠道的依据</p>
    <div  id="attestSlide">
    <ul class="attes">
    	<h4>认证内容：</h4>
    	<li>（1）★教师资格证照片<span class="sample"><a class="sample"title="在职证明" href="${base}/theme/${systemConfig.theme}/images/samples/teacher1.jpg">
                    <i style="font-style:normal;">   （点击样例）</i></a></span></li>
    </ul>
    </div>
    <ul class="attes">
    	<h4>认证有效期：<span style="color:red">永久</span></h4>
    	<li>上传资料：请确认您上传的资料清晰、完整，且是未经修改的数码照片或彩色扫描图片。每张图片大小不超过1.5M。</li>
    </ul>
    <div class="picture">
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
         	 <input type="hidden" value="${loadid}" name="loadid" id="loadid"/>
         	 <input type="hidden" value="${currentnode}" name="currentnode" id="currentnode"/>
         <input type="hidden" value="Career" name="type"/>
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


<div id="blueimp-gallery" class="blueimp-gallery" style="display: none;">
        <div class="slides" style="width: 5464px;margin-top:40px;"></div>
        <h3 class="title"></h3>
        <a class="prev">‹</a>
        <a class="next">›</a>
        <a class="close">×</a>
        <a class="play-pause"></a>
        <ol class="indicator"></ol>
    </div>
    
<script type="text/javascript">
		var attestSlide = document.getElementById('attestSlide');
		attestSlide.onclick = function (event) {
            event = event || window.event;
            
            var target = event.target || event.srcElement,
                link = target.src ? target.parentNode : target,
                options = {index: link, event: event},
                links = this.getElementsByTagName('a');
                	if(target.tagName=='I'){                	
		            	blueimp.Gallery(links, options);
		            }
              
               
        };
	</script>

</body>
</html>