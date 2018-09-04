<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <meta charset="UTF-8">
    <title>Title</title>
  <#--<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/nav_list.css" />
<script type="text/javascript" src="${base}/js/jQuery/nav_list.js"></script>-->
    <style>
        .change{
            color:#ffffff;
            background-color: #4789e7;
            border-radius:5px;
        }
    </style>
</head>
<body>

<!-- logo和导航 -->
<div class="xy-logo">
    <div class="xy-logo-wrap">
        <h1>
            <a href="${base}/" target="_self">
            	<#if systemConfig.p2pLogoFile>
                    <img width="179" height="53" src="${base}/${systemConfig.p2pLogoFile}"/>
                <#else>
                	<img style="width:277px;height:62px;margin-top:12px;" src="${base}/theme/proj_wenandai/images/xy-header/logo.png"/>
                </#if>
            </a>
            <!--<span>
            		专业融资租赁产业中间服务商！<br>
            		升好运&nbsp;升富贵&nbsp;就选升升投
                    <img src="${base}/theme/proj_wenandai/images/xy-header/logo-txt.png"/>
            	</span>-->
        </h1>

        <ul class="xy-logo-nav my-xy-logo-nav" id="xy-logo-nav">
            <li ><a id="shouye" href="${base}/" >首页</a></li>

            <li  class="J-downMenu" id="J-downMenu">
                <a class="current_cli" id="chujie" href="${base}/creditFlow/financingAgency/listPlBidPlan.do">我要出借</a>
                <#--<ul class="nav-show J-downMenu-show" id="nav-show">
                    <li><a href="${base}/creditFlow/financingAgency/listPlBidPlan.do" style="color:#FFFFFF;font-size:14px;">散标投资</a></li>
                    &lt;#&ndash;<li><a href="${base}/creditFlow/financingAgency/alltransferinglistPlBidSale.do"  style="color:#FFFFFF;font-size:14px;">债权交易</a></li>&ndash;&gt;
                &lt;#&ndash;<li><a href="${base}/creditFlow/financingAgency/listVipPlBidPlan.do"  style="color:#FFFFFF;font-size:14px;">VIP专享</a></li>
                <li class="radius"><a href="${base}/creditFlow/financingAgency/alltransferinglistVipPlBidSale.do"  style="color:#FFFFFF;font-size:14px;" >VIP债权交易</a></li>&ndash;&gt;
                </ul>-->
            </li>


          <#--  <li  class="J-downMenu" id="J-downMenu">
                <a class="current_cli" id="chujie" href="${base}/creditFlow/financingAgency/listPlBidPlan.do">我要出借<i class="nav-icon"></i></a>
                <ul class="nav-show J-downMenu-show" id="nav-show">
                    <li>&lt;#&ndash;<span class="nav_pull"></span>&ndash;&gt;<a href="${base}/creditFlow/financingAgency/listPlBidPlan.do" style="color:#FFFFFF;font-size:14px;">散标投资</a></li>
                    <li><a href="${base}/creditFlow/financingAgency/alltransferinglistPlBidSale.do"  style="color:#FFFFFF;font-size:14px;">债权交易</a></li>
                    &lt;#&ndash;<li><a href="${base}/creditFlow/financingAgency/listVipPlBidPlan.do"  style="color:#FFFFFF;font-size:14px;">VIP专享</a></li>
                    <li class="radius"><a href="${base}/creditFlow/financingAgency/alltransferinglistVipPlBidSale.do"  style="color:#FFFFFF;font-size:14px;" >VIP债权交易</a></li>&ndash;&gt;
                </ul>
            </li>-->
        <#--<li><a href="${base}/loan/listP2pLoanProduct.do" >我要借款</a></li>-->
            <!--<li><a href="${base}/user/solidactivityBpCustMember.do">优惠活动</a></li>-->
            <li><a id="baozhang" href="${base}/html/secureSingle.do" >安全保障</a></li>
            <li><a id="pilou" href="${base}/html/commitmentSingle.do?lid=32">信息披露</a></li>
            <li style="width:130px;">
                <a id="zhanghu" href="${base}/user/getBpCustMember.do" style="width:106px;" <#--style="margin-left: 10px;"-->><i class="my-head" id="my-head" <#--style="margin-left:-17px;"-->></i>我的账户</a>
            </li>
           <#-- <li id="nav_anzuo">
                <span id="pic_anzuo"></span>
                <p>安卓客户端</p>

            </li>-->

                <#if bpCustMember != null && bpCustMember != "">
                <#--<li><a href="${base}/user/getBpCustMember.do"><em class="my-head"></em>我的账户</a></li>-->
                <#else>
                </#if>
        </ul>
    </div>
</div>
<#--<script type="text/javascript" src="${base}/js/jQuery/nav_list.js"></script>-->
<#--<script>
    $(".current").css("color","#0a6aac")
</script>-->
<script>
  $("#J-downMenu").hover(function(){
       $("#nav-show").slideDown("fast");
    }, function(){
       $("#nav-show").slideUp("fast");
    });

/*
   $("#shouye").addClass("change");
   $("#shouye").css("color","#ffffff");
*/

   /*正则 判断url？参数*/
 /*   function GetQueryString(name) {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  unescape(r[2]); return null;
    }
    console.log(GetQueryString("page"));*/


/*    if(GetQueryString("page") == "1"){
        $("#shouye").addClass("change");
        $("#shouye").css("color","#ffffff");
       /!* $("#shouye").css({"background-color":"#000000",});
        $("#shouye").addClass("change").parent().siblings().children().removeClass("change");*!/
    }else if(GetQueryString("page") == "2"){
        $("#chujie").addClass("change");
        $("#chujie").css("color","#ffffff");
        $(".nav-icon").addClass("lanse");

        $("#shouye").removeClass("change");
        $("#shouye").css("color","#545454");

        $("#shouye").mouseover(function(){
            $("#shouye").css("color","#2770b8");
        });
       /!* $("#chujie").addClass("change").parent().siblings().children().removeClass("change");*!/
    }else if(GetQueryString("page") == "3"){
        $("#baozhang").addClass("change");
        $("#baozhang").css("color","#ffffff");
      /!*  $("#baozhang").addClass("change").parent().siblings().children().removeClass("change");*!/

        $("#shouye").removeClass("change");
        $("#shouye").css("color","#545454");
        $("#shouye").mouseover(function(){
            $("#shouye").css("color","#2770b8");
        });
    }else if(GetQueryString("page") == "4"){
        $("#pilou").addClass("change");
        $("#pilou").css("color","#ffffff");
       /!* $("#pilou").addClass("change").parent().siblings().children().removeClass("change");*!/
        $("#shouye").removeClass("change");
        $("#shouye").css("color","#545454");
        $("#shouye").mouseover(function(){
            $("#shouye").css("color","#2770b8");
        });
    }else if(GetQueryString("page") == "5"){
        $("#zhanghu").addClass("change");
        $("#zhanghu").css("color","#ffffff");
        $("#my-head").removeClass("my-head");
        $("#my-head").addClass("head_change");
        /!*$("#zhanghu").addClass("change").parent().siblings().children().removeClass("change");*!/

        $("#shouye").removeClass("change");
        $("#shouye").css("color","#545454");

        $("#shouye").mouseover(function(){
            $("#shouye").css("color","#2770b8");
        });

    }*/

</script>
</body>
</html>

    