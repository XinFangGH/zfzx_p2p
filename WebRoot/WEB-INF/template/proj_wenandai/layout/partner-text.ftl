<!-- partner-text -->
  <div class="partner-text">
  	<h2 class="partner-tit">友情链接</h2>
  	<div class="partner-pics">
   	 	
		<#if flinkPic=='[]'>
		<a href="#"><img src="${base}/theme/${systemConfig.theme}/images/partner/partner-text-01.png" alt=""></a>
		<a href="#"><img src="${base}/theme/${systemConfig.theme}/images/partner/partner-text-02.png" alt=""></a>
		<a href="#"><img src="${base}/theme/${systemConfig.theme}/images/partner/partner-text-03.png" alt=""></a>
		<a href="#"><img src="${base}/theme/${systemConfig.theme}/images/partner/partner-text-04.png" alt=""></a>
		<a href="#"><img src="${base}/theme/${systemConfig.theme}/images/partner/partner-text-05.png" alt=""></a>
		<a href="#"><img src="${base}/theme/${systemConfig.theme}/images/partner/partner-text-06.png" alt=""></a>
		<a href="#"><img src="${base}/theme/${systemConfig.theme}/images/partner/partner-text-07.png" alt=""></a>
		<a href="#"><img src="${base}/theme/${systemConfig.theme}/images/partner/partner-text-05.png" alt=""></a>
  	<#else>
  	<#list flinkPic as list>
			<a target="_blank" title="${list.name}" href="${list.url}"><span class="normal gray"><img src="http://192.168.0.107:8016/huifu_p2p/${list.imgURL}"></span></a>
		</#list>
		</#if>
	</div>
  </div>