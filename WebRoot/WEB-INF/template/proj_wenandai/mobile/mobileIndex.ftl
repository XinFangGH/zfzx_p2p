<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <title>升升投 - 首页</title>
        <link rel="stylesheet"  media="all" type="text/css" href="${base}/theme/${systemConfig.theme}/css/mobile.css" />
</head>
<body>
    <div id="wrap">
        <!-- 顶部   -->
        <div class="wad_header">
            <div class="wad_topbar">
                <span class="wad_logo"></span>
                <span class="wad_allnav"></span>
                 <#if bpCustMember==null>
                <a href="${base}/mobileLoginlogin.do" style="float: right;height: 26px;line-height: 26px;background: #2770b8;margin: 13px 0;padding: 0 14px;color: #fff;border-radius: 2px;font-weight: bolder;font-size: 14px;">登录</a>
				<a href="${base}/mobileReglogin.do" style="float: right;height: 26px;line-height: 26px;background: #2770b8;margin: 13px 0;padding: 0 14px;color: #fff;border-radius: 2px;font-weight: bolder;font-size: 14px;margin-right: 15px;">注册</a>
           		 </#if>
            </div>
            <#include "/WEB-INF/template/${systemConfig.theme}/mobilelayout/navbar.ftl">
        </div>
        <!-- 滑动内容 -->
        <div class="wad_hdtupian">
            <div id="mySwipe" class="swipe">
                <div class="swipe-wrap">
                    <div><b><img src="${base}/theme/${systemConfig.theme}/images/mobile/1.jpg"></b></div>
                    <div><b><img src="${base}/theme/${systemConfig.theme}/images/mobile/2.jpg"></b></div>
                    <div><b><img src="${base}/theme/${systemConfig.theme}/images/mobile/3.jpg"></b></div>
                </div>
                <div class="wad_yuandian">
                    <ul class="hongp"><li class="">真正第三方托管，有效保障理财人资金安全</li><li class="current">理财人15秒注册，快速简单</li><li class="">智能投标，让您理财更省心</li></ul>
                    <div class="hongd"><a class="">●</a><a class="current">●</a><a class="">●</a></div>
                </div>
            </div>

            <div class="wad_tubtn">
              <button onclick="mySwipe.prev()">prev</button> 
              <button onclick="mySwipe.next()">next</button>
            </div>
        </div>
        <!-- 内容 -->
        <div class="wad_content">
            <ul>
            <#if (pager.list)??>
            	<#list pager.list as list>
            	<#if list.state=='1' || list.state=='2' || list.state=='5' || list.state=='6'>
                <li>
                <#if bpCustMember==null>
                <a href="#alertchoose" class="wad_wcont">
                <#else>
                <a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?mobile=mobile&bidId=${list.bidId}" class="wad_wcont">
                </#if>
                    
                        <div class="wad_fcon">
                            <img src="${base}/theme/${systemConfig.theme}/images/mobile/xin.png">
                            <h2>${list.bidProName}</h2>
                            <p class="wad_xxmes">
                                <span class="wad_jine"><i>${list.bidMoney?string(',###.00')}元</i><i class="wad_jines">借款金额</i></span>
                                <span class="wad_lilv"><i>
                                <#if (list.proType=="B_Dir")>
									${list.bpBusinessDirPro.yearInterestRate}%
								</#if><#if (list.proType=="B_Or")>
									${list.bpBusinessOrPro.yearInterestRate}%
								</#if><#if (list.proType=="P_Dir")>
									${list.bpPersionDirPro.yearInterestRate}%
								</#if><#if (list.proType=="P_Or")>
									${list.bpPersionOrPro.yearInterestRate}%
								</#if>
                                </i><i class="wad_jines">年化利率</i></span>
                                <span class="wad_qixian"><i>${list.loanLife}</i><i class="wad_jines">借款期限</i></span>
                            </p>
                            <p class="wad_graph"><span class="wad_yanse" id="plan${list.bidId}">${((list.bidMoney-list.afterMoney)/list.bidMoney*100)?string(',##0.##')}%</span></p>
                            <#--<p class="wad_jiekr">10出借人已投元</p>-->
                        </div>
                    </a>
                </li>
                </#if>
               </#list>
            </#if>
            </ul> 
        </div>
        <!-- 分页
        <div class="f_page"> 
            <div class="sectionone">上一页</div> 
            <div class="sectionone">第<span>1</span>页</div> 
            <div class="sectionone">下一页</div> 
        </div> -->
        <!-- 弹出层② -->
    <div class="box_targetchoose" id="alertchoose">
        <div class="neirong">
            <span>
                <em>登录后才可进行投标</em>
                <a class="box_closechoose" href="mobileLoginlogin.do">立即登录</a>
                </br>
                <a class="box_closechoose" href="#">我知道</a>
            </span>
        </div>
    </div>
        <!--star footer  -->
          <#include "/WEB-INF/template/${systemConfig.theme}/mobilelayout/footerbar.ftl">
        <!-- end footer  -->
    </div>
     <script src="${base}/js/mobile/zepto.min.js"></script>
    <script src="${base}/js/mobile/swipe.min.js"></script>
    <script type="text/javascript">
        $(".wad_allnav").click(function(){
            $(".wad_slidernav").toggle();
        });
        var elem = document.getElementById('mySwipe');
        window.mySwipe = Swipe(elem, {
          // startSlide: 4,
           auto: 2000,
           // continuous: true,
          // disableScroll: true,
          // stopPropagation: true,
          // callback: function(index, element) {},
          transitionEnd: function(index, element) {
            $('.hongp .current').removeClass('current');
            $('.hongp li').eq(index).addClass('current');

            $('.hongd .current').removeClass('current');
            $('.hongd a').eq(index).addClass('current');
          }
        });
        // with jQuery
        // window.mySwipe = $('#mySwipe').Swipe().data('Swipe');
         $(function(){
         
         <#list pager.list as list>
         $("#plan${list.bidId}").width($("#plan${list.bidId}").text());
         </#list>
         
         });
    </script>
</body>
</html>
