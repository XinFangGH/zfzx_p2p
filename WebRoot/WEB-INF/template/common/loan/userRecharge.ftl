 <form action="${base}/pay/rechargePay.do?userType=1&tradeId=front_toRechargePayRedirect"
		method="post" name="rechargeNextForm" id="rechargeNextForm"   target="_blank">
			<div class="instant_recharge_two">
			   <ul style="overflow:hidden;">
					<#if listBankCode?exists>
						<#list listBankCode as list>
						<li style="float:left; width:160px; margin:5px 10px;">
							<input type="radio" style="float:left; margin:15px 5px;" name="bankCode" value="${list.bankCode}" />
							<img style="float:left;width:130px; height:40px" src="${base}/${list.bankLogo}" style="boder:0px" alt="${list.bankName}"/>
						</li>
						</#list>
					</#if>
				</ul>
				<div class="_div"><span class=""></span>请输入您的充值金额 </div>
				<div style="font-size: 12px; color: #999; text-align: center;">
					<!--可用免费充值流量：  ¥0.00 元，超出部分1%充值手续费，财付通9折。-->
                	<input type="hidden" id="txtFreeAmount" value="0" />
                 </div>
                 <br/>
                
                 <div class=" banks">
						<ul class="certname">
							<li  style="height:40px">
								<input type='hidden' id='isSupportShortcut' value='${isSupportShortcut}' />
								<label  style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">目前账户可用余额：</label> 
								<label>${bpCustMember.availableInvestMoney?string(',##0.00')}<span class="m-l_10">元</span>
								</label>
							</li>
							<li style="height:60px">
								<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">充值金额：</label>
								<input type="text" class="colorful" name="amount" id="amountShow" onblur="chkRegMoney('amountShow');"  onkeyup="value=value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3')"  maxlength="9">&nbsp;<span id="money_span">请输入数字或者小数点后两位</span><span id="Quota" style="margin-left: 10px;color:#409af6;cursor: pointer;">充值限额</span>
							  </li>
							 <li style="height:60px">
							 <label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">支付方式：</label>
							<#--<select name='payType' id='payType'>-->
                                 <input type="radio" name='payType' value="6" checked style="width:16px;height: 16px;vertical-align: text-bottom;"/>
                                 <label style="font-size:16px;margin-right:10px;">网关充值</label>
								<#if isSupportShortcut==1>
									<#--<option value='1'>快捷充值</option>-->

								<input type="radio" name='payType' value="1"    style="width:16px;height: 16px;vertical-align: text-bottom;"/>
								<label style="font-size:16px;">快捷充值</label>
								</#if>
								<#--<option value='6'>网关充值</option>-->

                                 <#--<input type="radio" name='payType' value="5"    style="width:16px;height: 16px;vertical-align: text-bottom;"/>-->
                                 <#--<label style="font-size:16px;">银行直连</label>-->
								<#--   <option value='5'>银行直连</option>   -->
							<#--</select>-->
							</li>
							<#if isSupportShortcut==1>
							<li style="height:30px">
								<input type='hidden' id='signDealQuota' value='${fudian.signDealQuota}' />
								<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">&nbsp;</label>
								<span >${fudian.bankName}快捷充值单笔限额${fudian.signDealQuota},单日限额${fudian.dayDealQuota}</span>
							</li>
							</#if>
							<li  style="height:80px">
								<label style="float: left;margin-top: 0;width: 180px;text-align: right;margin-right: 15px;">&nbsp;</label> 
								<lable >
								<div  tabindex="4" class="buttonorange white" style="width:120px;float:left;  font-size:16px;">
				 						  <a href="#signup" rel="leanModal"  name="signup">
				 						  	<span class="big white" id="rechargeNext" style="color:#FFFFFF">下一步</span>
				 						  </a>
			 						  </div>
								</lable>
								
							</li>
						</ul>
                 </div>

                <!--充值限额-->
                <div id="chongzhiBG" style="display: none; opacity: 0.5;"></div>
         <#-- <div id="signupBG" style="width: 680px; height:601px;background:#FFF;display:none;position:fixed;opacity:1;z-index:11000;left:44%;margin-left:-225px;top:50px;border-radius:5px !important;">-->
         <div id="signupBG" style="">
              <div id="signup-ct">
                  <div id="signupBG_header" <#--style="position: relative"-->>
                      <span class="zhichi">支持银行及限额</span>
                      <div id="chongzhiBG_close" style="position:absolute;width:60px; height:20px; cursor:pointer;top:5px;right:0px;" class="normal blue;font-size:18px;">关闭</div>
                  </div>
                  <div id="tab_BG">
                      <div class="menu">
                          <ul id="menu_BG">
                              <li><a  <#--href="#" --> class="active_BG" id="only_BG" style="cursor: pointer">快捷支付限额</a></li>
                              <li><a  <#--href="#"--> id="other_BG"  style="cursor: pointer">网关支付限额</a></li>
                          </ul>
                      </div>
                      <div id="tab_content">
                          <div id="one">
                              <ul class="first_BG">
                                  <li>银行名称</li>
                                  <li>单笔限额（万）</li>
                                  <li>日累计限额（万）</li>
                              </ul>
                              <ul  class="secod_BG">
                                  <li><span>邮储银行</span><span>0.5（需开通在线认证支付）</span><span>0.5</span></li>
                                  <li><span>工商银行</span><span>5</span><span>5</span></li>
                                  <li><span>中国银行</span><span>5</span><span>5</span></li>
                                  <li><span>中国建设银行</span><span>0.5</span><span>5</span></li>
                                  <li><span>交通银行</span><span>1</span><span>20</span></li>
                                  <li><span>中信银行</span><span>0.5</span><span>0.5</span></li>
                                  <li><span>光大银行</span><span>5</span><span>5</span></li>
                                  <li><span>平安银行</span><span>5</span><span>5</span></li>
                                  <li><span>兴业银行</span><span>5</span><span>5</span></li>
                                  <li><span>浦发银行</span><span>5</span><span>5</span></li>
                                  <#--<li><span>浙商银行</span><span>2</span><span>2</span></li>
                                  <li><span>北京银行</span><span>0.5</span><span>0.5</span></li>-->
                              </ul>
                          </div>
                          <div id="two">
                              <table id="two_BG">
                                  <thead>
                                     <tr>
                                         <th>银行名称</th><th>单笔限额（万元）</th><th>日累计限额（万元）</th><th>原路退款</th><th>备注</th>
                                     </tr>
                                  </thead>
                                  <tbody>
                                      <tr><td>邮储银行</td><td>手机短信：1 电子令牌+短信：20 Ukey+短信：200</td><td>手机短信：1 电子令牌+短信：20 Ukey+短信：200</td><td>支持</td><td>需开通网银支付功能</td></tr>
                                      <tr><td>工商银行</td><td>"电子口令卡：0.05
                                          短信：0.2
                                          电子密码：5
                                          U盾：100"</td><td>"电子口令卡：0.1
                                          短信：0.5
                                          电子密码：10
                                          U盾：100"</td><td>支持</td><td>需开通网银支付功能</td></tr>
                                     <#-- <tr><td>农业银行</td><td>"动态口令：0.1
                                          一代key:50
                                          二代key:100"</td><td>"动态口令：0.3
                                          一代key:50
                                          二代key:100"</td><td>支持</td><td>需开通网银支付功能</td></tr>-->
                                      <tr><td>中国银行</td><td>1</td><td>20</td><td>支持</td><td>需开通网银支付功能</td></tr>
                                      <tr><td>建设银行</td><td>"账号支付：0.1
                                          动态口令：0.5
                                          一代key:5
                                          二代key:50"</td><td>
                                          账号支付：0.1
                                          动态口令：0.5
                                          一代key:10
                                          二代key:50</td><td>支持</td><td>需开通网银支付功能</td></tr>

                                      <tr><td>交通银行</td><td>"短信：5
                                          key：100"</td><td>"短信：5
                                          key：100"</td><td>支持</td><td>需开通网银支付功能</td></tr>
                                      <tr><td>中信银行</td><td>"动态密码：0.1
                                          U盾：100"</td><td>"动态密码：0.5
                                          U盾：100"</td><td>支持</td><td>需开通网银支付功能</td></tr>
                                      <tr><td>光大银行</td><td>"动态密码：1
                                          U盾：50"</td><td>"动态密码：1
                                          U盾：50"</td><td>支持</td><td>需开通网银支付功能</td></tr>
                                      <#--                                       <tr><td>华夏银行</td><td>"大众版：0.03
                                                                               手机动态：0.1
                                                                               数字证书：50"</td><td>"大众版：0.1
                                                                               手机动态：0.5
                                                                               数字证书：50"</td><td></td><td>需开通网银支付功能</td></tr>
                                                                         <tr><td>民生银行</td><td>"大众版：0.03
                                                                               贵宾版：0.5
                                                                               U宝：50"</td><td>"大众版：0.03
                                                                               贵宾版：0.5
                                                                               U宝：50"</td><td></td><td>需开通网银支付功能</td></tr>-->
                                      <tr><td>广发银行</td><td>个人设置</td><td>个人设置</td><td>支持</td><td>需开通网银支付功能</td></tr>
                                      <tr><td>兴业银行</td><td>个人设置</td><td>个人设置</td><td></td><td>需开通网银支付功能</td></tr>
                                      <tr><td>浦发银行</td><td>"动态密码：20
                                          数字证书：无"</td><td>"动态密码：20
                                          数字证书：无"</td><td></td><td>需开通网银支付功能</td></tr>
                                     <#-- <tr><td>浙商银行</td><td>个人设置</td><td>个人设置</td><td>支持</td><td>需开通网银支付功能</td></tr>
                                      <tr><td>上海银行</td><td>"E盾版网银：50
                                          动态密码网银：0.6"</td><td>"E盾版网银：100
                                          动态密码版网银：1"</td><td></td><td>需开通网银支付功能</td></tr>
                                      <tr><td>宁波银行</td><td>个人设置</td><td>个人设置</td><td></td><td>需开通网银支付功能</td></tr>



                                      <tr><td>齐鲁银行</td><td>个人设置</td><td>个人设置</td><td>支持</td><td>需开通网银支付功能</td></tr>
                                      <tr><td>杭州银行</td><td>卡密支付：300元；证书支付：100万</td><td>卡密支付：300元；证书支付：500万</td><td></td><td>需开通网银支付功能</td></tr>
                                      <tr><td>南京银行</td><td>5</td><td>5</td><td></td><td></td></tr>
                                      <tr><td>天津银行</td><td>个人设置</td><td>个人设置</td><td></td><td>需开通网银支付功能</td></tr>
                                      <tr><td>徽商银行</td><td>"动态密码：0.1
                                          USBKey：50"</td><td>"动态密码：0.5
                                          USBKey：200"</td><td>支持</td><td>需开通网银支付功能</td></tr>
                                      <tr><td>兰州银行</td><td>100万</td><td>300万</td><td></td><td></td></tr>
                                      <tr><td>江西银行</td><td>个人设置</td><td>个人设置</td><td>支持</td><td>需开通网银支付功能</td></tr>


                                      <tr><td>桂林银行</td><td>个人设置</td><td>个人设置</td><td></td><td></td></tr>
                                      <tr><td>广州农村商业银行</td><td>300万</td><td>300万</td><td></td><td>需开通网银支付功能</td></tr>
                                      <tr><td>珲春农商银行</td><td>个人设置</td><td>个人设置</td><td>支持</td><td></td></tr>
                                      <tr><td>颖淮农商行</td><td>个人设置</td><td>个人设置</td><td></td><td></td></tr>
                                      <tr><td>延边农村商业银行</td><td>个人设置</td><td>个人设置</td><td>支持</td><td>需开通网银支付功能</td></tr>-->
                                      <tr><td>平安银行</td><td>个人设置</td><td>个人设置</td><td>支持</td><td>需开通网银支付功能</td></tr>
                                      <#--<tr><td>招商银行</td><td>大众版：0.05
                                          专业版：无</td><td>大众版：0.05
                                          专业版：无</td><td>支持</td><td>需开通网银支付功能</td></tr>

                                      <tr><td>北京银行</td><td>个人设置</td><td>个人设置</td><td></td><td>需开通网银支付功能</td></tr>
                                      <tr><td>贵州银行</td><td>个人设置</td><td>个人设置</td><td></td><td>需开通网银支付功能</td></tr>-->
                                  </tbody>
                              </table>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </div>
<!--<div class="instant_recharge_three">
      <div>温馨提示：</div>
      <ul>
          <li>1、充值资金可用于进行多项认证、投标、还款等。</li>-->
              <#-- <li>2、所有资金将由第三方平台 易生支付托管，${systemConfig.metaTitle}在线本身不存放用户的投标保证金。</li>-->
             <!--<li>3、省手续费，推荐使用"<a href="javascript:;" target="_self">非即时到账充值</a>"，最高10元/笔，<a href="javascript:;"
                    target="_self">VIP</a>单笔充值1千（含）元以上，免手续费！</li>

                <li>4、充值失败时，您所使用的免费流量有5分钟的锁定。可等待系统返还免费流量后，再继续充值。
                        	</li> 
           </ul>
       </div>-->
</form>
 <script>
     $("#Quota").click(function(){
         $("#chongzhiBG").css("display","block");
         $("#signupBG").css("display","block");
     });

     $("#chongzhiBG_close").click(function(){
         $("#chongzhiBG").css("display","none");
         $("#signupBG").css("display","none");
     });

 /*    $("#menu_BG li a").click(function(){
         $(this).addClass('active_BG');
         $(this).parent().siblings().children().removeClass("active_BG");
	 });*/

     $("#signupBG").css("border-radius","5px");

     $(function(){
         $("#menu_BG").on("click","li",function(){
             // 设index为当前点击
             var index = $(this).index();
             // 点击添加样式利用siblings清除其他兄弟节点样式
             $(this).children("a").addClass("active_BG").parent("li").siblings("li").children("a").removeClass("active_BG");
             // 同理显示与隐藏
             $("#tab_content").children("div").eq(index).show().siblings().hide();
         })
     })
 </script>



