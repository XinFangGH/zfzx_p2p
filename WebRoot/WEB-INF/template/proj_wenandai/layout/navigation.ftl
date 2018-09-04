<!--浮标开始-->
<script type="text/javascript">	
  $(document).ready(function(){
      $("#go_top").hide();
      $(function () {
          //检测屏幕高度
          var height=$(window).height();
          //scroll() 方法为滚动事件
          $(window).scroll(function(){
              if ($(window).scrollTop()>height){
                  $("#go_top").fadeIn(500);
              }else{
                  $("#go_top").fadeOut(500);
              }
          });
          $("#go_top").click(function(){
              $('body,html').animate({scrollTop:0},300);
              return false;
          });
      });


      /*$(".top_e").click(function() {
				$('body,html').animate({scrollTop:0},300);
			});
   		ititcount();
   		handleCheck();
   		hideCal();*/
   });

   function showCal(){
		 $('.sea_containerbg').show();
	};
	function handleCheck(type){
	$("span").siblings(".iType").removeClass('checked');
	   if(type==1){
	   		$("#typeCount").val('每月付息')
	   		$("#repay1").addClass("checked")
	  		$("#lblRepay1").attr("checked",true);
	   }else if(type==2){
	   		$("#typeCount").val('一次性')
	   		$("#repay3").addClass("checked")
	   		$("#lblRepay3").attr("checked",true);
	   }else if(type==3){
	   		$("#typeCount").val('先息后本')
			$("#repay4").addClass("checked")
	   		$("#lblRepay4").attr("checked",true);
	   }else if(type==4){
	   $("#repay5").addClass("checked")
	   $("#lblRepay5").attr("checked",true);
	   		$("#typeCount").val('等额本息')
	   }else if(type==5){
	   $("#repay6").addClass("checked")
	   $("#lblRepay6").attr("checked",true);
	   		$("#typeCount").val('等本等息')
	   }else{
	   		$("#typeCount").val('每月付息')
	   }
	}
	
	function hideCal(){
	   		$("#sea_containerbg_id").hide();
	   }
</script>
<script type="text/javascript">
function ititcount() {
    var serviceMoney = 0;
    var loanMonth = 3;
    $('.loantypespec a').click(function () {
        $('.loantypespec a').removeClass('curr');
        $(this).addClass('curr');

        serviceRateArray = [0, 0, 0.03, 0.05, 0.03, 0.06, 0.06];
        serviceRate = serviceRateArray[$(this).index()];
        if ($('.timeqingchunspec').hasClass('curr')) {
            $('.timeqingchun').show();
            $('.timeqingchun36').hide();
        } else{
            $('.timeqingchun').hide();
            $('.timeqingchun36').show();
        };
    });

    $('.time a').click(function () {
        $('.time a').removeClass('curr');
        $(this).addClass('curr').siblings().removeClass('curr');
        loanMonth = $(this).find('span').text();

        var index = Math.min($(this).index()-1, 4);
        $('.rate a').eq(index).addClass('curr').siblings().removeClass('curr');
    });

    function roundFloat(number, n) {
        var accuracy = n || 2;
        var tmp = Math.pow(10, accuracy);
        return Math.round(number * tmp) / tmp;
    };

    function LoanCalculator(a){this.principal=parseInt(a.principal)||0;this.rateYear=parseFloat(a.rateYear)||0.09;this.month=parseInt(a.month)||3;this.accuracy=parseInt(a.accuracy)||2;this.rateMonth=parseFloat(this.rateYear/12)||0.005;this.interest=0;this.pay=0;this.round=function(c,e){var d=e||this.accuracy;var b=Math.pow(10,d);return Math.round(c*b)/b};this.getMonthDetail_Benxi=function(j){var d=[];var e=this.principal*this.rateMonth*Math.pow(1+this.rateMonth,this.month)/(Math.pow(1+this.rateMonth,this.month)-1);var h=0;for(var c=0;c<this.month;c++){var g=this.principal-h;var f=g*this.rateMonth;var b=e-f;h+=b;d[c]={month:c+1,pay:this.round(e),principal:this.round(b),interest:this.round(f),principalLeft:this.round(g-b)};typeof j=="function"&&j(d[c].month,d[c].pay,d[c].principal,d[c].interest,d[c].principalLeft);this.pay+=e;this.interest+=f}this.pay=this.round(this.pay);this.interest=this.round(this.interest);return d};this.getMonthDetail_Benjin=function(h){var d=[];var b=this.principal/this.month;for(var c=0;c<this.month;c++){var g=this.principal-b*c;var f=g*this.rateMonth;var e=b+f;d[c]={month:c+1,pay:this.round(e),principal:this.round(b),interest:this.round(f),principalLeft:this.round(g-b)};typeof h=="function"&&h(d[c].month,d[c].pay,d[c].principal,d[c].interest,d[c].principalLeft);this.pay+=e;this.interest+=f}this.pay=this.round(this.pay);this.interest=this.round(this.interest);return d}};

    $('#btn').on('click', function () {
        var money = $('#loanTotal').val();
        var moneycostoperation = 0.003;
        if ($('.timeqingchunspec').hasClass('curr')) {
            moneycostoperation = 0.005;
            if (money < 100) {
                alert('100块钱起贷');
                $('#loanTotal').val('100');
                return false;
            } else if (money >= 100 && money <= 500 ) {
                serviceMoney = 30;
            } else if (money > 500 && money <= 1000) {
                serviceMoney = 60;
            } else if (money > 1000 && money <= 2000) {
                serviceMoney = 110;
            } else if (money > 2000 && money <= 3000) {
                serviceMoney = 160;
            } else if (money > 3000 && money <= 4000) {
                serviceMoney = 210;
            } else if (money > 4000 && money <= 5000) {
                serviceMoney = 320;
            } else if (money > 5000 && money <= 6000) {
                serviceMoney = 450;
            } else if (money > 6000 && money <= 10000) {
                serviceMoney = 680;
            } else {
                alert('小学生贷那么多钱干嘛，最多给你1万');
                $('#loanTotal').val('10000');
                return false;
            }     
        } else {
            if (money < 3000) {
                alert('3000元起贷');
                $('#loanTotal').val('3000');
                return false;
            } else if (money > 500000) {
                alert('最多50万，真的');
                $('#loanTotal').val('500000');
                return false;
            }
            serviceMoney = roundFloat(money*serviceRate);
        }
        

        loanCalculator = new LoanCalculator({
            principal: $('#loanTotal').val(), 
            rateYear: $('#loanRate').val()/100, 
            // month: $('#loanDeadline').val(),
            month: loanMonth,
        });

        var loanManagerMonthMoney = loanCalculator.round(loanCalculator.principal*moneycostoperation, 2);
        var loanManagerMoney = loanCalculator.round(loanManagerMonthMoney*loanCalculator.month, 2);

        $('#loanToalRes').html(loanCalculator.principal);
        $('#loanDeadlineRes').html(loanCalculator.month);
        $('.qcfwf').text(serviceMoney);
        $('.jkglf').text(loanManagerMonthMoney);

        var tmpString = '';
        var detail = loanCalculator.getMonthDetail_Benxi();
        for (var i = 0; i < detail.length; i++) {
            // if (i > 0) {
                serviceMoney = 0;
            // }
            detail[i].pay = loanCalculator.round(detail[i].pay + loanManagerMonthMoney + serviceMoney);
            if (i < detail.length - 1) {
                detail[i].principalLeft = loanCalculator.round(detail[i].principalLeft + detail[i+1].interest + loanManagerMonthMoney);
            }

            // tmpString += '<tr><td>第'+detail[i].month+'期</td><td><strong>'+detail[i].pay+'元</strong></td><td>'+detail[i].principal+'元</td><td>'+detail[i].interest+'元</td><td>'+serviceMoney+'元</td><td>'+loanManagerMonthMoney+'元</td><td>'+detail[i].principalLeft+'元</td></tr>';
            tmpString += '<tr><td>第'+detail[i].month+'期</td><td><strong>'+detail[i].pay+'元</strong></td><td>'+detail[i].principal+'元</td><td>'+detail[i].interest+'元</td><td>'+loanManagerMonthMoney+'元</td><td>'+detail[i].principalLeft+'元</td></tr>';
        }
        
        var averageMonthPay = detail[0].pay
        $('#averageMonth').text(averageMonthPay);
        $('#loanInterestRes').html(loanCalculator.interest);
        $('#payDetail').html(tmpString);
    });
};

</script>
<div class="page_top">
	<#--<ul>
		<li>
			   <#if bpCustMember!=null>
					<a class="top_a" href="${base}/loan/listP2pLoanProduct.do">我的<br>申请
        				<span class="tx_bar" id="applymessage">去申请</span>
        			</a>
			   <#else>
			   	<a class="top_a" href="${base}/htmllogin.do">我的<br>申请
			      <span class="tx_bar">去登录</span>
			   </a>
			   </#if>  
		</li>
		<li>
			<a class="top_d" href="http://wpa.qq.com/msgrd?v=3&uin=2931407238&site=qq&menu=yes" target="_blank" target="_blank"><i class="g-icon g-icon-qq"></i>在线咨询</a>
		</li>
		<li>
			<a href="javascript:;"  onclick="showCal();" class="top_b count">贷款<br>计算
				<span class="blind"></span>
			</a>
		</li>
        <li>
			 <a class="top_e" href="javascript:void(0)">返回顶部
             <span class="blind"></span>
             </a>
		</li>
	</ul>-->
        <ul>
            <li>
                <a class="top_a" href="http://wpa.qq.com/msgrd?v=3&uin=2162273477&site=qq&menu=yes" target="_blank" ><#--联系<br>客服--></a>
            </li>
            <li>
                <a class="top_d" href="http://wpa.qq.com/msgrd?v=3&uin=2716189196&site=qq&menu=yes" target="_blank" ><#--投诉<br>客服--></a>
            </li>
            <li>
                <a href="javascript:;"  onclick="showCal();" class="top_b count"><#--收益<br>计算--><span class="blind"></span></a>
            </li>
        <#--    <li>
                <a href="javascript:;" class="top_f " >&lt;#&ndash;安卓二维码&ndash;&gt;
                    <span class="toolbar-layer2 " id="toolbar-layer2"></span>
                </a>
            </li>-->
            <li>
                <a href="javascript:;" class="top_c " ><#--官方<br>微信-->
                    <span class="toolbar-layer"></span>
                </a>
            </li>
            <li id="go_top" style="margin-top: 10px">
                <a class="top_e" href="javascript:void(0)"><#--返回<br>顶部--><span class="blind"></span> </a>
            </li>
        </ul>



</div>
<!--浮标结束-->
    <!-- 计算器弹出层 -->
    <div class="sea_containerbg" id="sea_containerbg_id">
        <div class="sea_container" style="width:950px;">
           <div id="container" sessionid="xkkdk3o20kks12mhjpwplvmh">
		    <div class="detail_logo_bg">
		    </div>
    <div class="tool_content">
    	<a href="javascript:;" onclick="hideCal()"  class="debg">
    		<img src="${base}/theme/${systemConfig.theme}/images/aside/fancy_close.png" width="30" />
    	</a>
        <dl class="tool_introduce clearfloat">
            <dt><b class="calc_max"></b></dt>
            <dd>
                <h2>
                    贷款计算器</h2>
                <span class="tool_detail">只要根据提示填写相关信息，即可一键计算您的还款情况，助您高效理财。</span></dd>
        </dl>
        <div class="tool_box clearfloat">
            <div class="tool_left clearfloat">
                <h3 style="padding-bottom: 26px;">
                    选择参数</h3>
                <div class="tool_item">
                    <span>贷款金额</span>
                    <div class="tool_form">
                        <input type="text" maxlength="9" class="tool_txt" name="amount" id="amount"></div>
                    <div class="tool_unit">
                        元</div>
                </div>
                <div class="tool_prompt" id="tipAmount" style="display: none;">
                    请输入投资金额，只能为大于100的正整数</div>
                <div class="tool_item">
                    <span>年化利率</span>
                    <div class="tool_form">
                        <input type="text" class="tool_txt" name="apr" id="apr"></div>
                    <div class="tool_unit">
                        %</div>
                </div>
                <div class="tool_prompt" id="tipApr" style="display: none;">
                    请输入年化利率，只能大于0的数字</div>
                <div class="tool_item">
                    <span>借款期限</span>
                    <div class="tool_form">
                        <input type="text" class="tool_txt" name="mons" id="mons"></div>
                    <div class="tool_unit">
                        个月</div>
                </div>
                <div class="tool_prompt" id="tipMons" style="display: none;">
                    请输入借款期限，1-36个月</div>
                <div class="tool_item" style="display: none;">
                    <span>借款人奖励</span>
                    <div class="tool_form">
                        <input type="text" class="tool_txt" name="txtBid" id="txtBid"></div>
                    <div class="tool_unit">
                        %</div>
                </div>
                <div class="tool_prompt" id="tipBid" style="display: none;">
                    奖励格式不正确，只能为正数</div>
                <div class="tool_item" style="display: none;">
                    <span>奖励</span>
                    <div class="tool_form">
                        <input type="text" class="tool_txt" name="txtTender" id="txtTender"></div>
                    <div class="tool_unit">
                        %</div>
                </div>
                <div class="tool_prompt" id="tipTender" style="display: none;">
                奖励格式不正确，只能为正数</div>
                <div class="tool_item">
                    <span></span>
                    <div class="tool_form" style="width: 320px;">
                    <input type="hidden" id="typeCount" />
                        <div id="rd">
                            <span class="iCheck"  style="display: none">
                                <input type="radio"   name="Repayment" id="lblRepay1" class="iType"  value="每月付息" checked="checked"></span>
                            <label for="repay1" id="repay1" onclick="handleCheck(1)"  name="lblRepay1" >
                                每月付息</label>
                            
                            <span class="iCheck" style="display: none">
                                <input type="radio" name="Repayment" id="lblRepay3" value="一次性"></span>
                                <label for="repay3" onclick="handleCheck(2)" class="iType" name="lblRepay3" id="repay3">一次性</label>
                           <#-- <span class="iCheck" style="display: none">
                                <input type="radio" name="Repayment" id="lblRepay4" value="先息后本"></span>
                                <label for="repay4" onclick="handleCheck(3)" class="iType" name="lblRepay4" id="repay4">先息后本</label>-->
                            <span class="iCheck" style="display: none">
                                <input type="radio" name="Repayment" id="lblRepay5" value="等额本息"></span>
                                <label for="repay5" onclick="handleCheck(4)" class="iType" name="lblRepay5" id="repay5">等额本息</label>
                            <span class="iCheck" style="display: none">
                                <input type="radio" name="Repayment" id="lblRepay6" value="等本等息"></span>
                                <label for="repay6" onclick="handleCheck(5)" class="iType" name="lblRepay6" id="repay6">等本等息</label>
                        </div>
                    </div>
                </div>
                <div class="tool_item_btn">
                    <input type="submit" name="button" class="tool_btn" onmouseover="this.className=&#39;tool_btn_mouseover test&#39;" onmouseout="this.className=&#39;tool_btn_mouseout test&#39;" id="btnCalculate" value="计算">
                    <input type="reset" name="button" class="tool_btn" onmouseover="this.className=&#39;tool_btn_mouseover test&#39;" onmouseout="this.className=&#39;tool_btn_mouseout test&#39;" id="btnReset" value="重置" style="margin-left: 20px;"></div>
            </div>
            <div class="tool_right" style="width:485px;float: left;margin-left: 83px;">
                <dl class="tool_result clearfloat" style="padding: 0px 0px 15px;">
                    <dt>计算结果</dt>                                  
                    <dd>
                        <table width="100%" border="0" class="tool_table">
                            <tbody>
                            <tr> 
                            	<td>
                                  本息合计：<strong><span id="span1" >￥ 0</span></strong>   
                                </td>                              
                                <td>
                                    利息合计：<strong><span id="span2">￥ 0</span></strong>
                                </td>
                                
                            </tr>
                           
                        </tbody></table>
                    </dd>
                    <dd>每月本息：<strong><span id="span3" style="color: #ff8c00;">￥ 0</span></strong></dd> 
                </dl>
                <dl class="tool_result clearfloat" style="width: 100%;padding:0px">
                    <dt>本息收款时间表</dt>
                    <dd id="newdiv" style="display: none;width:100%;height:290px; overflow:auto;">
                    </dd>
                    <dd id="gain_initial">
                        <p class="tool_initial">
                            <img src="${base}/theme/${systemConfig.theme}/images/gain_contrast.gif"><br>
                            算一算吧，不会让你失望的</p>
                    </dd>
                </dl>
            </div>
        </div>
    </div>

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

$(function(){
    var key="show_anzuo",value="2";
    if(sessionStorage.getItem("show_anzuo")){
        $("#toolbar-layer2").removeClass("toolbar-layer3");
    }else{

        $(".toolbar-layer2").addClass("toolbar-layer3");
        var timer=setTimeout(function(){
            $("#toolbar-layer2").removeClass("toolbar-layer3");
        },3000);
        sessionStorage.setItem("show_anzuo",value);
    }
});



</script>
<link rel="stylesheet" type="text/css" href="${base}/js/computer/public2014.8.8.css">
<script type="text/javascript" src="${base}/js/computer/common.js"></script>
<link href="${base}/theme/${systemConfig.theme}/css/tool_style.css" rel="stylesheet" type="text/css">
<script src="${base}/js/computer/bd2.js" type="text/javascript"></script>
<script src="${base}/js/computer/GainCalc.js" type="text/javascript"></script>