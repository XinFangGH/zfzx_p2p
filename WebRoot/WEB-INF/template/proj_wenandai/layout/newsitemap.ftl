<div id="ftw">
	<div id="ft">
	
		<div class="wrap" style="height:80px;">
		
	       <!--开始左边--> 
			<div class="wrap_left">
			    <!-合作伙伴-->
			    <div class="link partner">
					<h3 class="hzhb">合作伙伴</h3>
					<ul class="td">
                      <#list flinkPic?sort_by("orderList") as list>
					   <li>
					     <a target="_blank" href="${list.url}" title="${list.name}">
					     <img src="${systemConfig.fileURL}${list.imgURL}" alt="${list.name}"/>
					     </a>
					   </li>
					  </#list>
					</ul>
			     </div>
                 <!-友情链接->
				 <div class="link partner" style="display:none;">
                    <h3 class="yqlj"></h3>
                    <ul>
                       <#list flinkTxt as list>
                         <li>
					       <a target="_blank" title="${list.name}" href="${list.url}">
					        ${list.name}
					       </a>
					     </li>
					   </#list>
					    <!--<li>
					       <a target="_blank" title="招商银行" href="http://www.cmbchina.com/">
					        招商银行
					       </a>
					     </li>
					     <li>
					       <a target="_blank" title="中信银行" href="http://www.citicbank.com/">
					       中信银行
					       </a>
					     </li>
					     <li>
					       <a target="_blank" title="中国银行" href="http://www.boc.cn/">
					       中国银行
					       </a>
					     </li>
					     <li>
					       <a target="_blank" title="建设银行" href="http://www.ccb.com/cn/home/indexv3.html">
					       建设银行
					       </a>
					     </li>
					     <li>
					       <a target="_blank" title="工商银行" href="http://www.icbc.com.cn/icbc/">
					      工商银行
					       </a>
					     </li>
					     <li>
					       <a target="_blank" title="北京银行" href="http://www.bankofbeijing.com.cn/">
					      北京银行
					       </a>
					     </li>
					     <li>
					       <a target="_blank" title="平安银行" href="http://bank.pingan.com/">
					      平安银行
					       </a>
					     </li>
					     <li>
					       <a target="_blank" title=" 民生银行" href="http://www.cmbc.com.cn/">
					     民生银行
					       </a>
					     </li>
					     <li>
					       <a target="_blank" title="浦发银行" href="http://www.spdb.com.cn/index.shtml">
					     浦发银行
					       </a>
					     </li>-->
                    </ul>
                </div>
                <!-导航-->      	
				<div class="link partner" style="display:none">
                    <h3>导航</h3>
                    <ul>
                       <!-- <li><a href="${base}/article/newslistArticle.do?lid=42" title="312">关于我们</a></li>-->
                        <li><a href="${base}/html/teamSingle.do" title="管理团队">管理团队</a></li>
                        <li><a href="${base}/html/companySingle.do" title="企业文化">企业文化</a></li>
                        <li><a href="${base}/html/secureSingle.do" title="安全保障">安全保障</a></li>
                        <li><a href="${base}/html/companySingle.do" title="荣誉资质">荣誉资质</a></li>
                        <li><a href="${base}/article/helplistArticle.do?lid=39" title="常见问题">常见问题</a></li>
                        <li><a href="${base}/html/contactSingle.do" title="联系我们">联系我们</a></li>
                    </ul>
                </div>
		</div>
	    <!--结束左边--> 
	    <!--开始右边-->    
		<div class="wrap_right hide">
			<div class="customer">
				
				<a href="#" class="login">
				  <img src="${base}/theme/${systemConfig.theme}/images/xy-footer/logo_2.png">
				</a>
				<div class="telphone">
					<div class="f_r phone">
						<p>理财热线：<span class="telep b">400-926-6114</span></p>
						<p>贷款热线：<span class="telep b">400-926-6114</span></p>
						<p class="icon">
						  <span class=" f12">
						    <a style="margin-right:0;" href="" target="_blank">
						     <em></em>
						    </a>
						    <a href="" target="_blank">新浪微博</a>
						  </span>
						  <span class="f12">
						    <em class="wxfoot weixintips"></em>
						    <a href="#">官方微信</a>
						  </span>
						</p>
					</div>
				</div>
				
			</div>
	    </div>
	    <!--结束右边--> 
	 </div>
  </div>
</div>
 
<script type="text/javascript">
    $(function () {
        function asideAnimate() {
            $('.asidelink').hover(function () {
                $(this).find('span').animate({
                    width : '140px',
                    opacity: '1'
                });
            }, function () {
                $(this).find('span').animate({
                    width: '40px',
                    opacity: '0'
                });
            });
        }
        asideAnimate();
    });
</script>

