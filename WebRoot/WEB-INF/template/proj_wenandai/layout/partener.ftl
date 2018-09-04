
<#assign flinkPic=flinkPic?eval />
  	<#assign flinkTxt=flinkTxt?eval />
<!-- partner-pic -->
  <div class="partner-pic">
  	<div style="width:700px; height:30px; padding:10px 0px 10px 70px;">
  		<span class="black large">合作伙伴</span>
  	</div>
    <div class="dashedline"></div>
  	<div class="ibox">
	<div class="showbox">
  	<ul>
		<#list flinkPic as list>
		<li><a target="_blank" href="${list.url}" title="${list.name}"><img src="${systemConfig.erpURL}attachFiles/${list.logo}" alt="中国工商银行" /></a></li>
		</#list>
		</ul>
	  </div>
	  </div>
  <div class="btn-wrap">
			<a class="prev" title="上一页">&laquo;向左滚动</a> 
			<a class="next" title="下一页">向右滚动&raquo;</a>
		</div>

  </div>
  
  
  <!-- partner-text -->
  <div class="partner-text">
  	<div style="margin:10px auto; width:961px; height:38px; padding-left:40px;"><span class="black middle">友情链接：</span>&nbsp;&nbsp;
   	 	<#list flinkTxt as list>
		<a target="_blank" title="${list.name}" href="${list.url}"><span class="normal gray">${list.name}</span></a>&nbsp;&nbsp;
		</#list>
  	</div>
  </div>