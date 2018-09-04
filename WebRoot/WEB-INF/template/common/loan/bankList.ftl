<script type="text/javascript" >
	$(function(){
		//默认第一个单选框选中
		 $(".card:eq(0)").attr("checked",true);
	});
</script>
<script type="text/javascript" >
	$(function(){
		//默认第一个单选框选中
		 $(".card:eq(0)").attr("checked",true);
		 $("#cancel").click(function(){
			 //web_card Id
			 var id=$('input:radio:checked').val();
			 //解绑地址
			  window.open("${base}/pay/cancelBindCardPay.do?cardNoId="+id);
		 });
	});
    $(function(){
        //默认第一个单选框选中
        $(".card:eq(0)").attr("checked",true);
        $("#cancelEnterprise").click(function(){
            //web_card Id
            var id=$('input:radio:checked').val();
            //解绑地址
            window.open("${base}/pay/cancelCardBindPay.do?cardNoId="+id);
        });
    });
</script>
<form action="${base}/pay/toThirdBankBindPay.do?tradeId=front_Withdraw" method="post" name="withdrawForm" id="withdrawForm" style=" padding:0 20px;">
	<div style="padding:20px 0;">
		<#--<a href="javaScript:(0)" id="cancel" class="buttongray fr" style="color:#333; margin-left:20px;">解除绑定</a>
			<a href="${base}/pay/toThirdBankBindPay.do"  target="_Blank"  class="buttonorange fr" style="color:#fff;">绑定银行卡</a><br>
			<a href="${base}/pay/updateBankPay.do"  target="_Blank"  class="buttonorange fr" style="color:#fff;">更换银行卡</a>
		-->
		<#if (listBankCard?size>0) >
			<#if bpCustMember.customerType==0>
				<#--<a href="javaScript:(0)" id="cancel" class="buttongray fr" style="color:#333; margin-left:20px;">解除绑定</a>-->
				<a href="javaScript:;" id="cancel" class="buttongray fr" style="color:#333; margin-left:20px;">解除绑定</a>
			</#if>
			<#if bpCustMember.customerType==1>
			<#--<a href="javaScript:(0)" id="cancel" class="buttongray fr" style="color:#333; margin-left:20px;">解除绑定</a>-->
				<a href="javaScript:;" id="cancelEnterprise" class="buttongray fr" style="color:#333; margin-left:20px;">企业解除绑定</a>
			</#if>
		<#else>
			<#--<a href="javaScript:(0)" &lt;#&ndash;target="_Blank"&ndash;&gt;  id="buttonorange" class="buttonorange fr" style="color:#fff;">绑定银行卡</a>-->
			<a href="javaScript:;"  id="buttonorange" class="buttonorange fr" style="color:#fff;">绑定银行卡</a>
		</#if>
	</div>
    <div id="lean_overlay" style="display: none; opacity: 0.5;"></div>
    <div id="signup" style="width: 450px; height: 230px; display: none; position: fixed; opacity: 1; z-index: 11000; left: 50%; margin-left: -225px; top: 120px;">
        <div id="signup-ct">
            <div id="signup-header">
                <div style="float:left; width:350px; height:40px; padding:10px 0px 0px 30px"><span class="large blue">确认绑卡</span></div>
                <div id="lean_overlay_close" style="float:right;  padding:20px 0px 0px 0px;width:60px; height:20px; text-decoration:underline; cursor:pointer;" class="normal blue;font-size:18px;">关闭</div>
            </div>
            <div style="width:380px; height:100px; margin:30px auto 0px auto;">
                <!--提示信息开始-->
                <!--提示信息开始-->
                <h3 style="font-size:18px;text-indent:2em">绑定的银行卡必须是您本人银行卡，并且注册时使用的手机号码必须为该卡的银行预留手机号码。</h3>
                <div style="text-align:center;margin-top:10px;">
                    <a href="${base}/pay/bindCardPay.do" target="_Blank">
						<input class="buttonorange button" type="button" id="submitBidcark" value="&nbsp;&nbsp;&nbsp;确定绑卡 »&nbsp;&nbsp;">
					</a>
                  <#--  <input class="buttonorange button" type="button" id="updatemoney" value="&nbsp;&nbsp;&nbsp;返回修改 »&nbsp;&nbsp;">-->
                </div>
            </div>
        </div>
    </div>
	<div class="tab-cont" style="margin:20px 0;">
						<div class=" bd">
							<table class="tab_css_three" border="0"cellpadding="0"  cellspacing="0">
								<tr>
									<#--<th class="black normal" width="28%">操作流水号</th>-->
									<th class="black normal" width="20%">姓名</th>
									<th class="black normal" width="35%">银行</th>
									<th class="black normal" width="35%">卡号</th>
									<th class="black normal" width="25%">绑卡状态</th>
									<th class="black normal" width="10%">选择</th>
									</tr>
									
									<#list listBankCard as list>
        	          <tr>
        	           <#--<td>${list.requestNo!}</td> -->
        	           <td>${list.username!}</td> 
        	          <td>${list.bankname!}</td> 
        	          <td><#if list.cardNum!=null><#if list.cardNum!="">${list.cardNum?substring(0,4)}****${list.cardNum?substring(12)}<#else>***********</#if><#else>***********</#if></td>
						<td>${list.bindCardStatusmsg!}</td>
						<td><input class="card" name="cardNo" type="radio" value="${list.cardId}" /></td>
        	           </tr>
        	</#list>
        	
							</table>
							
						</div>
					</div>
		

</form>

<script>
		$("#buttonorange").click(function(){
            $("#lean_overlay").css("display","block");
            $("#signup").css("display","block");
		});

        $("#lean_overlay_close").click(function(){
            $("#lean_overlay").css("display","none");
            $("#signup").css("display","none");
        });

        $("#submitrecharge").click(function(){
            $("#lean_overlay").css("display","none");
            $("#signup").css("display","none");
        });
</script>