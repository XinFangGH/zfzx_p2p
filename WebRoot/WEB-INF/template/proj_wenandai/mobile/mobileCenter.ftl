<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <title>升升投 - 我的</title>
       <link rel="stylesheet"  media="all" type="text/css" href="${base}/theme/${systemConfig.theme}/css/mobile.css" />
</head>
<style type="text/css">
    .wad_contentgeren .wad_yibao button{
        float: right;
        margin-top: -8px;
        border: 0;
        background: none;
        width: 30%;
        text-align: center;
        background: #ec6941;
        height: 40px;
        border-radius: 2px;
        color: #fff;
        font-size: 16px;
    }

   .wad_contentgeren .wad_yibao_bangding {
        color: #38b54a;
        display: block;
    }

    .wad_contentgeren .wad_yibao p {
        font-size: 14px;
        color: #999;
        line-height: 24px;
        padding: 22px;
        margin: 0 -20px;
        border-bottom: 1px dashed #ccc;
    }

</style>

<body onload="${retUrl}">
    <div id="wrap">
        <!-- 顶部   -->
        <div class="wad_header">
            <div class="wad_topbar">
                <a href="#" class="wad_bak"></a>
                <em>我的</em>
                <span class="wad_allnav"></span>
            </div>

            <#include "/WEB-INF/template/${systemConfig.theme}/mobilelayout/navbar.ftl">
        </div>

        <!-- 内容 -->
         <div class="wad_contentgeren">
             <!--<p class="wad_yonghu"><span class="wad_yonghuming">欢迎回来，${bpCustMember.loginname}</span><a class="wad_tuichu" href="${base}/exitlogin.do?mobile=mobile">退出</a></p> -->
            
            <div class="wad_yonghu_header">
                <p class="wad_yonghu"><span class="wad_yonghuming">欢迎回来，${bpCustMember.loginname}</span><a class="wad_tuichu" href="${base}/exitlogin.do?mobile=mobile">退出</a></p>
                <p>
                	<#if bpCustMember.thirdPayFlagId!=null> 
	                	<#if isbind != "1">
		                	<a href="${base}/pay/bindCardPay.do?mobile=mobile">绑定银行卡</a>
		                <#else>
		                	<a href="${base}/pay/bindCardPay.do?mobile=mobile" class="current">绑定银行卡</a>
		                </#if>	
	                <#else>
	                	<a href="#bindbankcard">绑定银行卡</a>
	                </#if>
	                
	                
	               <#if bpCustMember.thirdPayFlagId==null> 
	              	 <a href="${base}/thirdreg.do?mobile=mobile" >开通易宝</a>
	               <#else>
	               	 <a href="#yeepay" class="current">查看易宝</a>
	               </#if>
	                <a href="#chongzhi">充值</a>
                </p>
            </div>
            
            <ul>
	           <!-- <li class="wad_yibao">
				    
				   <#if bpCustMember.thirdPayFlagId!=null> 
				   <p class="wad_yibao_bangding"><span>您已绑定易宝支付账户~</span></p>
				   <#else>
				   <p><span>您还未易宝支付账户~</span><a href="${base}/thirdreg.do?mobile=mobile"><button>立即绑定</button></a></p> 
				   </#if>
				   
				</li>-->
            
<!--                 <li class="wad_shouyi">
                    <p class="wad_syjeheader"><span>截止昨日收益(元)</span><a href="#alert">？</a></p>
                    <p class="wad_shouyijine"><#if bpCustMember.allInterest==0>0.00<#else><#if bpCustMember.allInterest lt 1000>${bpCustMember.allInterest}<#else>${bpCustMember.allInterest?string(',###.00')}</#if></#if>元</p>
                </li>  -->               
                <li class="wad_shouyi wad_touzi">
                    <p class="wad_syjeheader"><span>截止昨日收益</span></p>
                    <p class="wad_shouyijine"><#if bpCustMember.allInterest==0>0.00<#else><#if bpCustMember.allInterest lt 1000>${bpCustMember.allInterest}<#else>${bpCustMember.allInterest?string(',###.00')}</#if></#if>元</p>
                </li>
                <li class="wad_xiangxizichan">
                    <span class="wad_kyyue"><i>累计投资</i><i class="wad_jines"><#if bpCustMember.totalInvestMoney==0>0.00<#else><#if bpCustMember.totalInvestMoney lt 1000>${bpCustMember.totalInvestMoney}<#else>${bpCustMember.totalInvestMoney?string(',###.00')}</#if></#if>元</i></span>
                    <span class="wad_kyyue"><i>可用余额</i><i class="wad_jines"><#if bpCustMember.availableInvestMoney==0>0.00<#else><#if bpCustMember.availableInvestMoney lt 1000>${bpCustMember.availableInvestMoney}<#else>${bpCustMember.availableInvestMoney?string(',###.00')}</#if></#if>元</i></span>
                    <span class="wad_djzijin"><i>冻结资金</i><i class="wad_jines"><#if bpCustMember.freezeMoney==0>0.00<#else>${bpCustMember.freezeMoney?string(',###.00')}</#if>元</i></span>
                    <span class="wad_zzichan"><i>总资产</i><i class="wad_jines"><#if InvestLoanMoneytotal==null>0.00<#else><#if InvestLoanMoneytotal lt 1000>${InvestLoanMoneytotal}<#else>${InvestLoanMoneytotal?string(',###.00')}</#if></#if>元</i></span>
                    <span class="wad_yjsy"><i>预计未来收益</i><i class="wad_jines">0.00元</i></span>
                </li>
                 <li class="wad_grzijin wad_grhuikuan">
                    <p class="wad_jilu"><span class="current">资金流水记录</span><span>回款计划</span></p>
                    <div class="wad_jiluitem">
                    	<#list listcount as list>
                       		 <p>
                       		 	<span class="wad_leixing">
                       			<#if list.transferDate??>${list.transferDate?string("yyyy-MM-dd HH:mm:ss")}<#else>未填</#if>
                       		 <em>
                       			<#if (list.transferType==1)>
							充值
							</#if><#if (list.transferType==2)>
							提现
							</#if><#if (list.transferType==3)>
							利息到账
							</#if><#if (list.transferType==4)>
							投资金额
							</#if><#if (list.transferType==5)>
							本金收回
							</#if> <#if (list.transferType==6)>
							充值/提现手续费
							</#if><#if (list.transferType==7)>
							还款支出
							</#if><#if (list.transferType==8)>
							借款到账
							</#if><#if (list.transferType==9)>
							剩余未还本息
							</#if><#if (list.transferType==10)>
							系统红包
							</#if>
                       		 </em></span>
                       		 <span class="wad_lxjine">
                       		 <#if list.incomMoney??>+<i>${list.incomMoney?string(',##0.00')}</i><#else>-<i>${list.payMoney?string(',##0.00')}</i></#if>
                       		 <b>元</b>
                       		 </span>
                       		 </p>
                   		</#list>
                    </div>

                    <!-- 回款计划 -->
                    <div class="wad_jiluitem  wad_jiluitemspec" style="display:none;">
                        <p><span class="wad_leixing">李先生装修借款<em>2014-09-06 15:58</em></span><span class="wad_lxjine"><i>1,300,000</i><b>元</b></span></p>
                        <p><span class="wad_leixing">家居家装(女,29岁,湖北省十堰市) <em>2014-09-06 15:58</em></span><span class="wad_lxjine"><i>5,000</i><b>元</b></span></p>
                        <p><span class="wad_leixing">其他用途(女,42岁,山东省东营市) <em>2014-09-06 15:58</em></span><span class="wad_lxjine"><i>5,000</i><b>元</b></span></p>
                        <p><span class="wad_leixing">买车置业(男,53岁,陕西省西安市) <em>2014-09-06 15:58</em></span><span class="wad_lxjine"><i>5,000</i><b>元</b></span></p>
                        <p><span class="wad_leixing">其他用途(男,45岁,内蒙古自治区锡林郭勒盟) <em>2014-09-06 15:58</em></span><span class="wad_lxjine"><i>673,043</i><b>元</b></span></p>
                    </div>
                    <div class="pagination m-t_15" id="fund_pager" style="height:30px; position:relative;width:100%;text-align:center;overflow:hidden;">
				</div>
                </li>
            </ul>
        </div>


		<!--yee弹出层 -->
 		<div class="box-target" id="yeepay">
             <div class="neirong"><span>易宝账号：</br>${bpCustMember.thirdPayFlagId}</span></div>
             <a class="box-close" href="#"></a>
        </div>

		<!--绑卡弹出层 -->
 		<div class="box-target" id="bindbankcard">
             <div class="neirong"><span>如需绑卡，</br>请先开通易宝账户</span></div>
             <a class="box-close" href="#"></a>
        </div>
		<!-- 充值弹出层 -->
        <div class="box-target" id="chongzhi">
             <div class="neirong"><span>如需充值，</br>请到PC端进行充值操作，感谢</span></div>
             <a class="box-close" href="#"></a>
        </div>

        <!-- 弹出层 -->
        <div class="box-target" id="alert">
             <div class="neirong"><span>一键加入，自动投标，省心省力<br />收益稳定，预期年化收益率10%<br />资金安全，担保公司全额本息担保<br />多种期限满足不同流动性需求</span></div>
             <a class="box-close" href="#"></a>
        </div>

        <!--star footer  -->
          <#include "/WEB-INF/template/${systemConfig.theme}/mobilelayout/footerbar.ftl">
        <!-- end footer  -->
    </div>
    <script src='${base}/js/mobile/zepto.min.js'></script>
    <script type="text/javascript">
        $(".wad_allnav").click(function () {
            $(".wad_slidernav").toggle();
        });
    </script>
</body>
</html>
