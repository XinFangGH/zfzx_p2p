<div class="finlist">


       <ul class="finlist_title">
          <li class="active">福利标详情</li>          
          <li>投标记录</li>
       </ul>
       <div class="finlist_hide">
	 <ol style="display:block">
         <div class="wad_boon">
            <!-- 福利标特点 -->
            <div class="cw_main"> 
                <h3 class="h3 wad_font"><b>01.</b>福利标是啥？</h3>
                <p class="ft14 cl333">"福利标"是升升投公司为新客户送红包的活动。就能获得以下权益：</p>
                <p><img src="${base}/theme/${systemConfig.theme}/images/fulibiao.png"></p>
                <p class="friend-tips h"> 友情提示：1、参与活动均需开通自动投标功能。2、自动投标功能只适用于真实的借款标的，“福利标”只能手动投标。3、本活动最终解释权归升升投所有。</p>
                <p class="ft14 align-right"><a href="#" target="_self" class="tmallbao-qa wad_font">福利标常见问题答疑 &gt;</a></p>
            </div>
            <!-- 福利标规则 -->
            <div class="cw_main cw-rule">
                <h3 class="h3 wad_font"><b>02.</b>福利标规则</h3>
                <div class="cw-rule-left">
                    <p>用户在投资“福利标”后才会开始计算收益。收益显示时间如下：</p>
                    <table cellspacing="0">
                        <thead>
                            <tr class="strong">
                                <th>投标时间</th>
                                <th>首次计收益显示时间</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="strong">
                                <td>周一11:00～周五18:00</td>
                                <td>次日</td>
                            </tr>
                            <tr class="strong">
                                <td>周六00:00～周日00:00</td>
                                <td>下周一</td>
                            </tr>
                        </tbody>
                    </table>
                    <p class="center">（如遇国家法定假期，本公司将顺延至工作日才进行福利标份额确认）</p>
                    <p>在“福利标”投标成功后（投标时间即为用户投资“福利标”的成功投标时间），福利标的每日收益发放至用户的托管账户中（当日计息，次日11:00前发放收益）；如遇双休日及国家法定假期福利标收益将延迟至工作日发放，如遇其它情况请以升升投通知为准。 </p>
                </div>
                <div class="cw-rule-right">
                    <p>“福利标”每日11:00前发放，投资金额50元起步，单次投标金额上限是1000元。转入时支持使用账户余额、储蓄卡快捷方式（含卡通）进行充值。</p>
                    <p>投资“福利标”的资金不发生划转，投标成功后将冻结该用户的账户内投标本金，无论满标与否，投标成功当日开始计算利息（未开通自动投标无利息）；用户可以在升升投的稳安总览的累计收益进行查看具体详情；“福利标”的资金收益发放后（当日计息，次日11:00前发放收益），用户可随时进行提现操作。</p>
                    <p class="strong">每期“福利标”的活动时间为24小时，到期后用户参与活动的冻结资金将自行解冻。</p>
                    <p class="strong">根据《升升投“福利标”的服务协议》，试运营期间（2014.11.1-2014.12.31）升升投将联合易宝支付有限公司，对用户资金进行全面保障承保，被盗100%赔付，赔付无上限。 </p>
                </div>
            </div>
            <!-- 投资方法 -->
            <div class="cw_main">
                <h3 class="h3 wad_font"><b>03.</b>福利标使用方法</h3>
                <h4 class="h4"></h4>
                 <div><img src="${base}/theme/${systemConfig.theme}/images/tb.png"></div>
            </div>
	
        </div>
       	</ol>
       	<ol>
       	 <div class="content"> 
       	 	<#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
             <#else>   
            <h2 class="title"><span>加入人次${plan.persionNum}人</span> 投标总额${(plan.bidMoney-plan.afterMoney)?string(',##0.##')}元</h2>
            <div class="tab">
            
            	<table width="800" border="0" class="tab_css_plan" align="center" cellpadding="0" cellspacing="0">
                       <tr>
                        <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                        <th width="150" align="center" bgcolor="#47B2D6">投标人</th>
                        <th width="50"  align="center" bgcolor="#47B2D6">年利率</th>
                        <th width="150" align="center" bgcolor="#47B2D6">投标金额</th>
                        <th width="150" align="center" bgcolor="#47B2D6">投标时间</th>
                      </tr>
                       <#if (listPlBid!=null)>
                        <#list listPlBid as list>
                        <tr>
                           <td height="40" align="center" bgcolor="#FAFBFD">${list_index+1}</td>
                           <td align="center" bgcolor="#FAFBFD"><#if  list.userName?length lt 3>${list.userName}<#else>${list.userName?substring(0,1)}****</#if></td>
                           <td align="center" bgcolor="#FAFBFD">${(planPro.yearInterestRate)!}%</td>
                           <td align="center" bgcolor="#FAFBFD"><#if list.userMoney=null>0.00<#else>${list.userMoney?string(',###.00')}</#if></td>
                           <td align="center" bgcolor="#FAFBFD"><#if (list.bidtime==null)>-- --<#else>${list.bidtime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                        </tr>
                        </#list>
                        </#if>
                </table>
            </div>  
               </#if>
         </div> 
       </ol>
       
       	 <ol>
       	 <div class="content">   
       	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
             <#else> 
       	  <#assign sum1=0>
       	  <#assign sum2=0>
        <#list  slFundList as list>
          <#if list.afterMoney??>
            <#assign sum1=sum1 +list.afterMoney>
          </#if>
          <#if list.incomeMoney??>
            <#assign sum2=sum2 +list.incomeMoney>
          </#if>
        </#list>
         
            <!--<h2 class="title"><span>已还本息<#if sum1??>${sum1?string(",##0.00")}<#else>--</#if> 元</span> 待还本息<#if sum1?? && sum2??>${(sum2-sum1)?string(",##0.00")}<#else>--</#if>元</h2>-->
            <div class="tab">
            	<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
                       <tr>
                        <th width="150" height="40" align="center" bgcolor="#47B2D6">约定还款日期</th>
                        <th width="150" align="center" bgcolor="#47B2D6">状态</th>
                        <th width="150" align="center" bgcolor="#47B2D6">应还本息</th>
                        <th width="150" align="center" bgcolor="#47B2D6">应还罚息</th>
                         <th width="150" align="center" bgcolor="#47B2D6">实际还款日期</th>
                      </tr>
                      <#list slFundList as fundlist>
                      <tr>
                        <td height="40" align="center" bgcolor="#FAFBFD">${fundlist.intentDate}</td>
                        <td align="center" bgcolor="#FAFBFD">
                        	<#if (fundlist.notMoney=0)>
                        		已偿还
                        	<#else>
                        		未偿还
                        	</#if>
                        </td>
                        <td align="center" bgcolor="#FAFBFD">${fundlist.incomeMoney?string(',##0.00')}</td>
                        <td align="center" bgcolor="#FAFBFD">${fundlist.accrualMoney?string(',##0.00')}</td>
                        <td align="center" bgcolor="#FAFBFD"><#if (fundlist.factDate==null)>--<#else>${fundlist.factDate}</#if></td>
                      </tr>
                      </#list>
                </table>
            </div>  
            </#if>
         </div> 
       </ol>
       
      <ol>
       	 <div class="content">
       	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
             <#else>     
          		 <h2 class="title">持有债权人数<#if (bondListBid!=null)> ${bondListBid?size}<#else>0</#if>人</h2>
	            <div class="tab">
	            	<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
	                       <tr>
	                        <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
	                        <th width="150" align="center" bgcolor="#47B2D6">债权人</th>
	                        <th width="150" align="center" bgcolor="#47B2D6">持有金额</th>
	                      </tr>
	                      <#if (bondListBid!=null)>
	                        <#list bondListBid as list>
	                        <#if (list_index<8)>
	                        <tr>
	                           <td height="40" align="center" bgcolor="#FAFBFD">${list_index+1}</td>
	                           <td align="center" bgcolor="#FAFBFD">${list.userName?substring(0,1)}****</td>
	                           <td align="center" bgcolor="#FAFBFD">${list.bondTotelMoney?string(',###.00')}</td>
	                        </tr>
	                        </#if>
	                        </#list>
	                        </#if>
	                </table>
	            </div>  
             </#if>
         </div> 
       </ol>
       
       <ol>
       	 <div class="content">    
           <#-- <h2 class="title">持有债权人数313人</h2>
            <div class="tab">
            	<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
                       <tr>
                        <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                        <th width="150" align="center" bgcolor="#47B2D6">债权人</th>
                        <th width="150" align="center" bgcolor="#47B2D6">持有金额</th>
                      </tr>
                      <tr>
                        <td height="40" align="center" bgcolor="#FAFBFD">1</td>
                        <td align="center" bgcolor="#FAFBFD">投标人</td>
                        <td align="center" bgcolor="#FAFBFD">200.00元</td>
                      </tr>
                      <tr>
                        <td height="40" align="center" bgcolor="#E1E4E9">2</td>
                        <td align="center" bgcolor="#E1E4E9">投标人投标人</td>
                        <td align="center" bgcolor="#E1E4E9">500.00元</td>
                      </tr>
                </table>
            </div> -->
            <p>无转让中的债权</p>  
         </div> 
          
       </ol>
       
       <ol>
       	 <div class="content">    
            <h2 class="title"><span>以交易总额0.00元</span> 待交易总额0.00元</h2>
            <div class="tab">
            	<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
                       <tr>
                        <th width="140" height="40" align="center" bgcolor="#47B2D6">债权买入者</th>
                        <th width="140" align="center" bgcolor="#47B2D6">债权卖出者</th>
                        <th width="140" align="center" bgcolor="#47B2D6">交易金额</th>
                        <th width="140" align="center" bgcolor="#47B2D6">交易时间</th>
                      </tr>
                      <tr>
                        <td height="40" align="center" bgcolor="#FAFBFD"></td>
                        <td align="center" bgcolor="#FAFBFD"></td>
                        <td align="center" bgcolor="#FAFBFD"></td>
                        <td align="center" bgcolor="#FAFBFD"></td>
                      </tr>
                      <tr>
                        <td height="40" align="center" bgcolor="#E1E4E9"></td>
                        <td align="center" bgcolor="#E1E4E9"></td>
                        <td align="center" bgcolor="#E1E4E9"></td>
                        <td align="center" bgcolor="#E1E4E9"></td>
                      </tr>
                </table>
            </div>
            <p>无债权转让记录</p>  
         </div> 
       </ol>
        
	</div>
</div>