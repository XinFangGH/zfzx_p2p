<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>${systemConfig.metaTitle} - ${helpTitle}</title>
    <meta name="description" content="${systemConfig.metaTitle} - ${helpTitle},专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - ${helpTitle},投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script src="${base}/js/user/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="${helpTitle}";</script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_common.css">
    <link rel="stylesheet" href="${base}/theme/proj_wenandai/css/swiper.min.css">
    <style>
     /*   .swiper-container {
            width: 100%;
            height: 100%;
        }
        .swiper-slide {
            text-align: center;
            font-size: 18px;
            background: #fff;
            !* Center slide text vertically *!
            display: -webkit-box;
            display: -ms-flexbox;
            display: -webkit-flex;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            -webkit-justify-content: center;
            justify-content: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            -webkit-align-items: center;
            align-items: center;
        }*/
     .swiper-container {
         width: 100%;
         height: 100%;
         margin-left: auto;
         margin-right: auto;
     }
     .swiper-slide {
         text-align: center;
         font-size: 18px;
         background: #fff;

         /* Center slide text vertically */
         display: -webkit-box;
         display: -ms-flexbox;
         display: -webkit-flex;
         display: flex;
         -webkit-box-pack: center;
         -ms-flex-pack: center;
         -webkit-justify-content: center;
         justify-content: center;
         -webkit-box-align: center;
         -ms-flex-align: center;
         -webkit-align-items: center;
         align-items: center;
     }


    </style>
</head>
<body >
<!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">

<div class="main">
    <div class="abouts-nav fl">
        <!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_helpdesk.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->
    <div class="abouts-cont fr">
        <div class="abouts-border">
           <#-- <div class="titie_h1">
                <span class="left_pic"></span><h1>${helpTitle}</h1><span class="right_pic"></span>
            </div>-->
            <div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">${helpTitle}</span>
            </div>

            <ul class="media-cont">
		 <#if helpTitle == "新手指引" >
         <div class="Novice" style="width:100%;height: 500px;margin-top:40px;">
            <div class="novice_title" style="width: 100%;height:200px;text-align: center;">
                 <ul  class="novice_ul"  id="novice_ul_li" style="width: 54%;margin: 0 auto;">
                     <li id="novice_list_one" class="active">注册</li>
                     <li id="novice_list_two" >绑卡</li>
                     <li id="novice_list_three">充值</li>
                     <li id="novice_list_four">出借</li>
                 </ul>
                 <div class="novice_div" style="margin-top: 70px">
                     <span id="novice_ul" class="novice_ul" ></span>
                     <p id="novice_p" class="novice_p">填写相应内容注册账号，注册后登录您的升升投账户</p>
                 </div>
             </div>
            <#-- <div class="swiper-container" style="width:740px;height:378px;">
                 <div class="swiper-wrapper">
                     <div class="swiper-slide" style="width:740px;height:378px;"><img src="${base}/theme/proj_wenandai/images/noviceGuidance/11.jpg" alt=""></div>
                     <div class="swiper-slide" style="width:740px;height:378px;"><img src="${base}/theme/proj_wenandai/images/noviceGuidance/12.jpg" alt=""></div>
                     <div class="swiper-slide" style="width:740px;height:378px;"><img src="${base}/theme/proj_wenandai/images/noviceGuidance/03.jpg" alt=""></div>
                     <div class="swiper-slide" style="width:740px;height:378px;"><img src="${base}/theme/proj_wenandai/images/noviceGuidance/04.jpg" alt=""></div>
                 </div>
                 <!-- Add Pagination &ndash;&gt;
                 <div class="swiper-pagination" id="swiper-pagination"&lt;#&ndash; style="display:none;"&ndash;&gt;></div>
                 <!-- Add Arrows &ndash;&gt;
                 <div class="swiper-button-prev" id="swiper-button-prev"></div>
                 <div class="swiper-button-next" id="swiper-button-next"></div>
             </div>
               <!-- Swiper -->
               <div class="swiper-container" style="width:740px;height:378px;">
                   <div class="swiper-wrapper">
                       <div class="swiper-slide"><img src="${base}/theme/proj_wenandai/images/noviceGuidance/01.jpg" alt=""></div>
                       <div class="swiper-slide"><img src="${base}/theme/proj_wenandai/images/noviceGuidance/02.jpg" alt=""></div>
                       <div class="swiper-slide"><img src="${base}/theme/proj_wenandai/images/noviceGuidance/03.jpg" alt=""></div>
                       <div class="swiper-slide"><img src="${base}/theme/proj_wenandai/images/noviceGuidance/04.jpg" alt=""></div>
                   </div>
                   <!-- Add Pagination -->
                   <div class="swiper-pagination" id="swiper-pagination" style="opacity:0;"></div>
                   <!-- Add Arrows -->
                   <div class="swiper-button-next"></div>
                   <div class="swiper-button-prev"></div>
                 </div>-

             <div class="why" style="width: 100%;margin-top:114px;height:300px;">
                 <span style="display:inline-block;width: 232px;height: 38px;margin-bottom: 20px" ><img style="display:inline-block;width: 232px;height: 38px" src="${base}/theme/proj_wenandai/images/noviceGuidance/icon5.png" alt=""></span>
                 <p style="text-indent:2em;font-size:14px">升升投一直秉承”以人为本、高效专业“的核心理念，在互联网金融信息服务领域深耕细作，整合行业优势资源，基于互联网和大数据风控技术为用户提供简单便捷的金融信息服务。</p>
                 <p  style="text-indent:2em;font-size:14px">未来，升升投将以为投资人提供多样化的投资产品、便捷的操作工具及优质的服务作为努力的方向，使投资人在获得资产稳健增值的同时享受到卓越的互联网金融信息服务所带来的优质体验，并将为全面构建、维护、推动中国金融创新与发展不遗余力！</p>
                 <ul style="margin: 0 auto;width: 80%;height:120px;margin-top: 40px;">
                     <li style="display: inline-block;width:170px;height:100px;text-align: center;"><img style="display:blockwidth: 94px;height:94px;" src="${base}/theme/proj_wenandai/images/noviceGuidance/icon01.png" alt=""><p style="margin-top:15px;">严选项目</p></li>
                     <li style="display: inline-block;width:170px;height:100px;text-align: center;"><img style="display:blockwidth: 94px;height:94px;"  src="${base}/theme/proj_wenandai/images/noviceGuidance/icon02.png" alt=""><p style="margin-top:15px;">风险分散</p></li>
                     <li style="display: inline-block;width:170px;height:100px;text-align: center;"><img style="display:blockwidth: 94px;height:94px;"  src="${base}/theme/proj_wenandai/images/noviceGuidance/icon03.png" alt=""><p style="margin-top:15px;">合规保障</p></li>
                     <li style="display: inline-block;width:170px;height:100px;text-align: center;"><img style="display:blockwidth: 94px;height:94px;"  src="${base}/theme/proj_wenandai/images/noviceGuidance/icon04.png" alt=""><p style="margin-top:15px;">资金安全</p></li>
                 </ul>
             </div>
         </div>
         </#if>
			<#list pager.list as list>
				<#--<h1>${list.id}</h1>-->
				<li class="media-list" >
					<span class="new-list fl">
						<em class="media-dian">●</em>
						<a href="${base}/article/helpcontentArticle.do?catId=${list.id}">${list.title}</a>
					</span>
                    <span class="fr">${list.createDate} </span>
                </li>
            </#list>
		<#import "/WEB-INF/template/common/pager.ftl" as p>
         			<#assign parameterMap = {} />
         			<@p.pager pager = pager baseUrl = "/article/helplistArticle.?lid=${articleCategorysign}" parameterMap = parameterMap />
            </ul>
        </div>
    </div>
</div>
<script src="${base}/js/user/swiper.min.js"></script>

<script>
    var swiper = new Swiper('.swiper-container', {
        slidesPerView: 1,
        spaceBetween: 30,
       /* loop: true,*/
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });
</script>
<script>
 /* var swiper = new Swiper('.swiper-container', {
        pagination: {
            el: '.swiper-pagination',
            type: 'fraction',
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });*/

/*  var pageVal = $(".swiper-pagination-current").html();*/

/* $("#swiper-pagination").children("span").eq(0).click(){
     alert("ok");
 }*/

$("#novice_list_one").click(function(){
    /*alert("ok");*/
    $("#novice_list_one").addClass("active");
    $("#novice_list_two").removeClass("active");
    $("#novice_list_three").removeClass("active");
    $("#novice_list_four").removeClass("active");
    $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/zhuce.png)");
    $("#novice_p").html("填写相应内容注册账号，注册后登录您的升升投账户");
    /* $(".swiper-wrapper").css({"transform":"translate3d(0px, 0px, 0px)","transition-duration":"300ms"});*/
    $("#swiper-pagination").children("span").eq(0).click();

  });
 $("#swiper-pagination").children("span").eq(0).click(function(){
     $("#novice_list_one").addClass("active");
     $("#novice_list_two").removeClass("active");
     $("#novice_list_three").removeClass("active");
     $("#novice_list_four").removeClass("active");
     $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/zhuce.png)");
     $("#novice_p").html("填写相应内容注册账号，注册后登录您的升升投账户");
 });

  $("#novice_list_two").click(function(){
      $("#novice_list_one").removeClass("active");
      $("#novice_list_two").addClass("active");
      $("#novice_list_three").removeClass("active");
      $("#novice_list_four").removeClass("active");
      $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/bangka.png)");
      $("#novice_p").html("填写相应内容绑卡信息，绑卡后登录您的升升投账户");
      $("#swiper-pagination").children("span").eq(1).click();
     /* $(".swiper-wrapper").css({"transform":"translate3d(-740px, 0px, 0px)","transition-duration":"300ms"});*/

  });

 $("#swiper-pagination").children("span").eq(1).click(function(){
     $("#novice_list_one").removeClass("active");
     $("#novice_list_two").addClass("active");
     $("#novice_list_three").removeClass("active");
     $("#novice_list_four").removeClass("active");
     $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/bangka.png)");
     $("#novice_p").html("填写相应内容绑卡信息，绑卡后登录您的升升投账户");
 });



  $("#novice_list_three").click(function(){
      $("#novice_list_one").removeClass("active");
      $("#novice_list_two").removeClass("active");
      $("#novice_list_three").addClass("active");
      $("#novice_list_four").removeClass("active");
      $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/chongzhi.png)");
      $("#novice_p").html("填写相应内容充值信息，充值后登录您的升升投账户");
      $("#swiper-pagination").children("span").eq(2).click();
     /* $(".swiper-wrapper").css({"transform":"translate3d(-1480px, 0px, 0px)","transition-duration":"300ms"});*/

  });

 $("#swiper-pagination").children("span").eq(2).click(function(){
     $("#novice_list_one").removeClass("active");
     $("#novice_list_two").removeClass("active");
     $("#novice_list_three").addClass("active");
     $("#novice_list_four").removeClass("active");
     $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/chongzhi.png)");
     $("#novice_p").html("填写相应内容充值信息，充值后登录您的升升投账户");
 });

  $("#novice_list_four").click(function(){
      $("#novice_list_one").removeClass("active");
      $("#novice_list_two").removeClass("active");
      $("#novice_list_three").removeClass("active");
      $("#novice_list_four").addClass("active");
      $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/chujie.png)");
      $("#novice_p").html("填写相应内容出借信息，出借后登录您的升升投账户");
      $("#swiper-pagination").children("span").eq(3).click();
      /*$(".swiper-wrapper").css({"transform":"translate3d(-2220px, 0px, 0px)","transition-duration":"300ms"});*/

  });

 $("#swiper-pagination").children("span").eq(3).click(function(){
     $("#novice_list_one").removeClass("active");
     $("#novice_list_two").removeClass("active");
     $("#novice_list_three").removeClass("active");
     $("#novice_list_four").addClass("active");
     $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/chujie.png)");
     $("#novice_p").html("填写相应内容出借信息，出借后登录您的升升投账户");
 });


 $(".swiper-button-next").click(function(){
     var pageVal= $(".swiper-pagination-bullet-active").index();
     console.log( $(".swiper-pagination-bullet-active").index());

     if(pageVal == "0"){
         $("#novice_list_one").addClass("active");
         $("#novice_list_two").removeClass("active");
         $("#novice_list_three").removeClass("active");
         $("#novice_list_four").removeClass("active");
         $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/zhuce.png)");
         $("#novice_p").html("填写相应内容注册账号，注册后登录您的升升投账户");

     }else if(pageVal == "1"){
         $("#novice_list_one").removeClass("active");
         $("#novice_list_two").addClass("active");
         $("#novice_list_three").removeClass("active");
         $("#novice_list_four").removeClass("active");
         $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/bangka.png)");
         $("#novice_p").html("填写相应内容绑卡信息，绑卡后登录您的升升投账户");
     }else if(pageVal == "2"){
         $("#novice_list_one").removeClass("active");
         $("#novice_list_two").removeClass("active");
         $("#novice_list_three").addClass("active");
         $("#novice_list_four").removeClass("active");
      $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/chongzhi.png)");
         $("#novice_p").html("填写相应内容充值信息，充值后登录您的升升投账户");

     }else if(pageVal == "3"){
         $("#novice_list_one").removeClass("active");
         $("#novice_list_two").removeClass("active");
         $("#novice_list_three").removeClass("active");
         $("#novice_list_four").addClass("active");
        $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/chujie.png)");
         $("#novice_p").html("填写相应内容出借信息，出借后登录您的升升投账户");
     }


 });

 $(".swiper-button-prev").click(function(){
     console.log( $(".swiper-pagination-bullet-active").index());

     var pageVal= $(".swiper-pagination-bullet-active").index();
     console.log( $(".swiper-pagination-bullet-active").index());

     if(pageVal == "0"){
         $("#novice_list_one").addClass("active");
         $("#novice_list_two").removeClass("active");
         $("#novice_list_three").removeClass("active");
         $("#novice_list_four").removeClass("active");
         $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/zhuce.png)");
         $("#novice_p").html("填写相应内容注册账号，注册后登录您的升升投账户");

     }else if(pageVal == "1"){
         $("#novice_list_one").removeClass("active");
         $("#novice_list_two").addClass("active");
         $("#novice_list_three").removeClass("active");
         $("#novice_list_four").removeClass("active");
         $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/bangka.png)");
         $("#novice_p").html("填写相应内容绑卡信息，绑卡后登录您的升升投账户");
     }else if(pageVal == "2"){
         $("#novice_list_one").removeClass("active");
         $("#novice_list_two").removeClass("active");
         $("#novice_list_three").addClass("active");
         $("#novice_list_four").removeClass("active");
         $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/chongzhi.png)");
         $("#novice_p").html("填写相应内容充值信息，充值后登录您的升升投账户");

     }else if(pageVal == "3"){
         $("#novice_list_one").removeClass("active");
         $("#novice_list_two").removeClass("active");
         $("#novice_list_three").removeClass("active");
         $("#novice_list_four").addClass("active");
         $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/chujie.png)");
         $("#novice_p").html("填写相应内容出借信息，出借后登录您的升升投账户");
     }




 });





/*  $("#swiper-pagination  .swiper-pagination-current").bind('DOMNodeInserted', function(e) {
      console.log($(e.target).html());
      var pageVal =$(e.target).html();
      if(pageVal == "1"){
          $("#novice_list_one").addClass("active");
          $("#novice_list_two").removeClass("active");
          $("#novice_list_three").removeClass("active");
          $("#novice_list_four").removeClass("active");
          $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/zhuce.png)");
          $("#novice_p").html("填写相应内容注册账号，注册后登录您的升升投账户");

      }else if(pageVal == "2"){
          $("#novice_list_one").removeClass("active");
          $("#novice_list_two").addClass("active");
          $("#novice_list_three").removeClass("active");
          $("#novice_list_four").removeClass("active");
          $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/bangka.png)");
          $("#novice_p").html("填写相应内容绑卡信息，绑卡后登陆您的升升投账户");
      }else if(pageVal == "3"){
          $("#novice_list_one").removeClass("active");
          $("#novice_list_two").removeClass("active");
          $("#novice_list_three").addClass("active");
          $("#novice_list_four").removeClass("active");
          $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/chongzhi.png)");
          $("#novice_p").html("填写相应内容充值信息，充值后登陆您的升升投账户");

      }else if(pageVal == "4"){
          $("#novice_list_one").removeClass("active");
          $("#novice_list_two").removeClass("active");
          $("#novice_list_three").removeClass("active");
          $("#novice_list_four").addClass("active");
          $("#novice_ul").css("background-image","url(${base}/theme/${systemConfig.theme}/images/noviceGuidance/chujie.png)");
         $("#novice_p").html("填写相应内容出借信息，出借后登陆您的升升投账户");
      }

  });*/

</script>

<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">


</body>
</html>