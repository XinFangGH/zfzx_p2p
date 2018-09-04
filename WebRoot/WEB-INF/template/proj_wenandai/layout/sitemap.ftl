<div class="sitemap"">
	<div class="whole1" style="width:1001px; margin:0 auto; border-bottom: 1px solid #929292;overflow:hidden; padding-bottom:28px;">
	    <div class="leftdiv">
		  <ul style="padding:20px 0 10px 0; overflow:hidden;">
		  <!--<#list articleMap as list>
		   <li>
	          <a target="_self" href="${base}/article/detailArticle.do?id=${list.id}"><span>${list.title}</span></a>
	        </li>
		  </#list>-->
		   <li>
	          <a target="_self" href="${base}/html/companySingle.do"><span>公司介绍：</span></a>
	        </li>
		  <li>
	          <a target="_self" href="${base}/html/secureSingle.do"><span>安全保障</span></a>
	        </li>
	        <li>
	          <a target="_self" href="${base}/article/newslistArticle.do?lid=34"><span>媒体报道</span></a>
	        </li>
	        <li>
	          <a target="_self" href="#"><span>社会责任</span></a>
	        </li>
	        <li>
	          <a target="_self" href="${base}/html/careersSingle.do"><span>招贤纳士</span></a>
	        </li>
	        <li>
	          <a target="_self" href="#"><span>网站地图</span></a>
	        </li>
	       <li>
	          <a href="${base}/article/helplistArticle.do?lid=35" target="_self"><span>帮助中心</span></a>
	        </li>
	        <li>
	          <a target="_self" href="${base}/html/contactSingle.do"><span>联系我们</span></a>
	        </li>
	      </ul>
	      <ul class="ui-list">		  
		   <li class="tit"><span>友情链接：</span></li>
		  
	          <#if flinkTxt?exists>
			<#list flinkTxt as list>
			<li>
				<a target="_blank" title="${list.name}" href="${list.url}"><span class="normal gray">${list.name}</span></a>&nbsp;&nbsp;
			 </li>
			</#list>
		</#if>
	       
	      </ul>
	      
	      <ul class="logoline" style="clear:both;">
	     	<li><span  class="middle">客户服务：</span></li>
	        <li><a class="logo weibo" target="_self" href="http://weibo.com/wenandai"></a></li>
	        <li><a class="logo qqweibo" target="_self" href="#"></a></li>
	        <li><a class="logo weixin" target="_self" href="http://t.qq.com/wenandai"></a></li>
	        <li><a class="logo onlineservice" target="_blank" href="${base}/html/wad_qq.html"></a></li>
	      </ul>
	    </div>
		<div class="rightdiv">
	      <span style="display:block; float:left;padding-top:70px;"><img src="${base}/theme/${systemConfig.theme}/images/phone-1.png"/></span><br/>
	      <span class="bold large"style="float:left;padding:40px 0 0 10px;">4006-918-987</span><br/>
	      <span style="float:left;padding:10px 0 0 20px;">服务时间：9:00 - 21:00</span>
	    </div>
    </div>
</div>