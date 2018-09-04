<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 材料认证</title>
<!-- 上传文件js开始-->
<link href="${base}/js/swfupload/scripts/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script language= "javascript" src="${base}/js/swfupload/scripts/json.js"></script>
<script type="text/javascript" src="${base}/js/swfupload/scripts/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${base}/js/swfupload/scripts/swfupload/swfupload.queue.js"></script>
<script type="text/javascript" src="${base}/js/swfupload/scripts/swfupload/fileprogress.js"></script>
<script type="text/javascript" src="${base}/js/swfupload/scripts/swfupload/swfuploadProperties.js"></script>
<script type="text/javascript" src="${base}/js/swfupload/scripts/swfupload/handlers.js"></script>
<script type="text/javascript" src="${base}/js/upload.js"></script>
<style type="text/css">
body{ font-size:14px;}
input{ vertical-align:middle; margin:0; padding:0}
.file-box{ position:relative;width:340px}
.txt{ height:22px; border:1px solid #cdcdcd; width:180px;}
.abtn{ background-color:#0080FF; border:1px solid #CDCDCD;height:34px; width:90px;color:#fff}
.file{ position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px }
.file2{ position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px }
.img_box{width:100px; height:80px; display:block; float:left; margin:10px; position: relative;}
.deleteImg{display:none;cursor: pointer;position: absolute; top:-5px; right:-5px; width:20px; height:20px; background:url(${base}/theme/${systemConfig.theme}/images/erricon.jpg) no-repeat; }

.bot .sendList {
	margin-right:6px
}
.explore {
	width:936px;
	margin:-18px auto 0;
	overflow:hidden;
	position:relative;
	font-size:0px;
}
.explore li {
	width:160px;
	height:150px;
	overflow:hidden;
	display:inline-block;
	position:relative;
}
.explore li {
*display:inline;
}
.explore li .wqPic {
	width:160px;
	height:150px;
	overflow:hidden;
}
.explore .wqLink {
	display:block;
	width:160px;
	height:150px;
	color:#FFF;
	text-align:center;
	font-family:"微软雅黑"
}
.explore .wqItem .wqName {
	position:absolute;
	bottom:0;
	left:0;
	width:130px;
	height:40px;
	line-height:40px;
	font-size:16px;
	overflow:hidden;
	padding:0 10px;
}
.explore .wqItem .bg {
	background:#333;
	opacity:0.8;
	filter:alpha(opacity = 80);
	position:absolute;
	bottom:0;
	left:0;
	width:150px;
	height:40px;
}
.explore .wqLink:hover {
	cursor:pointer;
	text-decoration:none;
}
.explore .wqLink:hover .wqItem .bg {
	height:150px;
}
.explore .wqLink:hover .wqItem .wqName, .explore .detail {
	visibility:hidden;
}
.explore .wqLink:hover .detail {
	visibility:visible;
}
.explore .detail {
	background:#000;
	position:absolute;
	top:133px;
	left:7;
	width:160px;
	line-height:22px;
	height:22px;
	font-size:12px;
	filter:alpha(opacity=60);  
	opacity: 0.5;  

}
.explore .detail .wqName {
	font-size:16px;
	padding:0 10px;
	line-height:22px;
}
.explore .detail .info {
	margin-top:10px;
}

</style>
<!-- 上传文件js结束-->
<script type="text/javascript">var m1="";var m2="";var m3="";</script>
</head>
<body >
<span style="display:none" id="myBase">${base}</span>
	<div class="bpbox">
	
	<#if materialList!=""><!-- 贷款材料-->
		<h3>${materialList.conditionContent}</h3>
	    <div  id="attestSlide">
	    <ul class="attes">
	    	<h4>认证说明：</h4>
	    	<#if materialList.remark=="">
	    		<li>无认证说明</li>
	    	<#else>
	    	<li>${materialList.remark}</li>
	    	</#if>
	    </ul>
	    
	    <ul class="attes">
			<h4>认证材料样例：</h4>
			<li><span class="sample"><a class="sample" title="${materialList.conditionContent}" href="${base}/${materialList.imgUrl1}">
		                <i style="font-style:normal;">   （点击查看样例）</i></a>
		                <a class="sample" title="${materialList.conditionContent}" href="${base}/${materialList.imgUrl2}">
		               </a></span></li>
	    </ul>
	
	    <ul class="attes">
	    	<h4>认证有效期：<span>永久</span></h4>
	    	<li>上传资料：请确认您上传的资料是清晰的、完整的、未经修改的数码照片或彩色扫描照片。每张图片大小不大于1.5M。</li>
	    </ul>
	    </div>
		    <div class="picture">
		    	<h4 style="color:red"><span>*</span>上传${materialList.conditionContent}：</h4>
		    	<div class="fieldset flash" id="fsUploadProgress1">
				<span class="legend"></span>
				<span id="imgJpg"></span>
				<span id="imgNone">
	
				<div id="topWrap">
				  <div class="wqSquare">
				    <div class="explore">
				      <ul>
				      <#list webFinanceApplyUploadsList as list>
				        <li> <a onmousedown="MI.Bos('wQBtnBigImgQun')" href="#" class="wqLink">
				          <div class="wqItem"> <img  src="${base}/${list.files}" class="wqPic" /> </div>
				          <div class="detail">
				            <div class="wqName"  onclick="deleteImg(${list.id})">删除</div>
				          </div>
				          </a> 
				          </li>
				          </#list>
				      </ul>
				    </div>
				  </div>
				</div>
				<span>
			</div>
	    	<div class="file-box">  
				<span  class='abtn'>&nbsp;选&nbsp;择&nbsp;资&nbsp;料&nbsp;</span>
				<input type="file" id="headImage" name="upfile" class="file" size="28"  />
				<span style="display:none">${materialList.conditionId}</span>
			</div>
			<span style="display:none" id="uploadWay">2</span>
	<#else>
			<h3>${baseMaterial.materialName}</h3><!-- 基础材料-->
		    <div  id="attestSlide">
		    <ul class="attes">
		    	<h4>认证说明：</h4>
		    	<li>${baseMaterial.remark}</li>
		    </ul>
		    
		    <ul class="attes">
				<h4>认证材料样例：</h4>
				<li><span class="sample"><a class="sample" title="${baseMaterial.materialName}" href="${base}/${baseMaterial.imgUrl1}">
			                <i style="font-style:normal;">   （点击查看样例）</i></a>
			                <a class="sample" title="${baseMaterial.materialName}" href="${base}/${baseMaterial.imgUrl2}">
			              </a></span></li>
		    </ul>
		
		    <ul class="attes">
		    	<h4>认证有效期：<span>永久</span></h4>
		    	<li>上传资料：请确认您上传的资料是清晰的、完整的、未经修改的数码照片或彩色扫描照片。每张图片大小不大于1.5M。</li>
		    </ul>
		    </div>
			    <div class="picture">
			    	<h4 style="color:red"><span>*</span>上传${baseMaterial.materialName}：</h4>
			    	<div class="fieldset flash" id="fsUploadProgress1">
					<span class="legend"></span>
					<span id="imgJpg"></span>
					<span id="imgNone">
		
					<div id="topWrap">
					  <div class="wqSquare">
					    <div class="explore">
					      <ul>
					      <#list webFinanceApplyUploadsList as list>
					        <li> <a onmousedown="MI.Bos('wQBtnBigImgQun')" href="#" class="wqLink">
					          <div class="wqItem"> <img  src="${base}/${list.files}" class="wqPic" /> </div>
					          <div class="detail">
					            <div class="wqName"  onclick="deleteImg(${list.id})">删除</div>
					          </div>
					          </a> 
					          </li>
					          </#list>
					      </ul>
					    </div>
					  </div>
					</div>
					<span>
				</div>
		    	<div class="file-box">  
		    	<span  class='abtn'>&nbsp;选&nbsp;择&nbsp;资&nbsp;料&nbsp;</span>
					<input type="file" id="headImage" name="upfile" class="file" size="28"  />
					<span style="display:none">${baseMaterial.materialId}</span>
				</div>
				<span style="display:none" id="uploadWay">1</span>
	</#if>
		
         <input type="button" class="btn"  onClick="closeDiag()" value="提交审核"/>
         <div class="warn">
         <span>★注：</span>
         <br />
         升升投是一个信用交易平台。一旦发现用户上传的资料含有造假行为，升升投会将用户永久拉黑，适当情况下会进行依法披露。请让我们携手，为构建和谐诚信社会共同努力！
         </div>
    </div>
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