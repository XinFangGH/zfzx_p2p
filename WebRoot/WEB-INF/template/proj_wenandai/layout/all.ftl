<meta name="subject" content="${systemConfig.metaSubject}">
<#--<meta name="description" content="${systemConfig.metaDescription}">-->
<#--<meta name="keywords" content="${systemConfig.metaKeywords}">-->
<meta name="versions" content="${systemConfig.host}">
<meta name="language" content="${systemConfig.metaLanguage}">
<#--<meta name="author" content="${systemConfig.metaAuthor}">-->
<meta name="author" content="中发展信（北京）投资管理有限公司">
<#--<meta name="generator" content="${systemConfig.metaGenerator}">-->
<meta name="generator" content="升升投平台1.0版">
<meta name="robots" content="${systemConfig.metaRobots}">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit">
<meta name="force-rendering" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/layout.css" />
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/module.css?version=1.2.4" />
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/account.css" />
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/tooltips.css" />
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/uc.css" />
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/wad_common.css" />
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/navigation.css" />
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/blueimp-gallery.min.css" />
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/blueimp-gallery-indicator.css" />
<link rel="stylesheet" type="text/css" href="${base}/js/ymPrompt/skin/simple/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/login_register.css" />

<#--<#if systemConfig.p2pIconFile>-->
	<#--<link rel="icon" type="image/x-icon" href="${base}/${systemConfig.p2pIconFile}"/>-->
<#--<#else>-->
	<link rel="icon" type="image/x-icon" href="${base}/theme/proj_wenandai/images/favicon.ico"/>
<#--</#if>-->
<link rel="icon" type="image/x-icon" href="${base}/theme/proj_wenandai/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/xy-common.css"/>
<link rel="stylesheet" type="text/css" href="${base}/js/page/jquery.pagination.css" />
<script type="text/javascript" src="${base}/js/globl.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/jquery-1.8.2-min.js"></script>
<script type="text/javascript" src="${base}/js/clipboard/clipboard.min.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/jquery.form.min.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/jquery.placeholder.min.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/jquery.easing.min.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/jquery.lavalamp.min.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/jquery.leanModal.min.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/jquery.progressbar.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/jquery.cookie.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/jquery.circleprogress.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/jquery.pure.tooltips.js"></script>
<script type="text/javascript" src="${base}/js/jQuery/jquery.blueimp-gallery.js"></script>
<script type="text/javascript" src="${base}/js/common.js"></script>
<script type="text/javascript" src="${base}/js/setmenu.js"></script>
<script type="text/javascript" src="${base}/js/zDialog/zDialog.js"></script>
<script type="text/javascript" src="${base}/js/divselect.js"></script>
<script type="text/javascript" src="${base}/js/tallyAll-3gmin.js"></script>
<script type="text/javascript" src="${base}/js/ymPrompt/ymPrompt.js"></script>
<script type="text/javascript" src="${base}/js/slider.js"></script>
<script type="text/javascript" src="${base}/js/computer/GainCalc.js"></script>
<script type="text/javascript" src="${base}/static/bootstrap/js/bootstrap.min.js"></script>
<#--<script type="text/javascript" src="${base}/js/jQuery/jquery-3.1.1.min.js"></script>-->


<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b36eb85dbf7b6583f55526ef0aaf75d5";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>

<script type="text/javascript">
$(function(){
	//百翼宝进度圆环显示
	circleprogress(90,".circleprogress90");
	circleprogress(45,".circleprogress45");
	circleprogress(130,".circleprogress130");
	
	
	
	$(function(){
	//导航条下拉框  我要投资	
	/*var content = $(".navbarinventlist").html();	
	$('.navbarinvent').pt({
		position: 'b',
		width:120,
		content: content
	});	*/
	//导航条下拉框  我要借款
/*	var content = $(".navbarloanlist").html();	
	$('.navbarloan').pt({
		position: 'b',
		width:120,
		content: content
	});	  */
});

	//新浪微博提示框

	var content="";
	<#list fileList as fileList>
		<#if fileList.remark=="sina_weibo">
			content = '<span class="middle black">${systemConfig.metaTitle}新浪微博</span><br/><img src="${base}/attachFiles/upload/image/${fileList.setname}" width="140" alt="官方新浪微博二维码" />';
		</#if>
	</#list>	  	
	$('.weibo').pt({
		position: 'b',
		content: content
	});	
	//腾讯微博提示框
	var content="";
	<#list fileList as fileList>
		<#if fileList.remark=="tencent_weibo">
			content = '<span class="middle black">${systemConfig.metaTitle}腾讯微博</span><br/><img src="${base}/attachFiles/upload/image/${fileList.setname}" width="140" alt="官方腾讯微博二维码" />';
		</#if>
	</#list>	  	
	$('.weibo_tx').pt({
		position: 'b',
		content: content
	});	
	//微信
	var content = '<span class="middle black">微信二维码</span><br/><img src="${base}/theme/${systemConfig.theme}/images/gdwx.jpg" width="140" alt="官方微信二维码" />';	
	$('.weixintips').pt({
		position: 'b',
		width:150,
		content: content
	});
	//腾讯
	var content2="";
	<#list fileList as fileList>
		<#if fileList.remark=="tencent_weibo">
			content2 = '<span class="middle black">${systemConfig.metaTitle}腾讯微博</span><br/><img src="${base}/attachFiles/upload/image/${fileList.setname}" width="140" alt="官方微信二维码" />';
		</#if>
	</#list>	
	$('.xy-tengxunweibo').pt({
		position: 'b',
		width:150,
		content: content2
	});
	
});
</script>
