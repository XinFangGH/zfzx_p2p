<script type="text/javascript">
    var MyOFF = 0;
    function myshow(a){
        document.getElementById(a).style.display="";
    }
    function myhidden(a){
        document.getElementById(a).style.display="none";
    }
    function myclick(){
    <#--已经点击的不在显示向session中加标识-->
        $.ajax({
            url : basepath+"/article/cloudArticle.do",
            dataType : "json",
            success : function(result) {
                $("#cloud").attr("style","display: none");
            }
        });
    }

    $(function () {
        var map1 = {};
        //收益信息
        ajaxFunction("${base}/user/getMoneyAccumulativeIncomeBpCustMember.do", map1, ljsy);
    });

    function ljsy(data){
        var result=data.result;
        $("#ljsy").empty().html(result);
    }

</script>

    <div class="xy-all-header" id="header">
        <div class="xy-site-nav">
        <#--<ul class="xy-site-nav-bd-l">
            <li><a href="#">客服热线 ：4009266114</a></li>
            <!--
            <li><a href="#" alt="新浪微博" class="top-icon weibo" onmouseover="myshow('xy-arale-tip xy-arale-tip-01');" onmouseout="myhidden('xy-arale-tip xy-arale-tip-01');"></a></li>
            <li><a href="#" alt="腾讯微博" class="top-icon weibo_tx" onmouseover="myshow('xy-arale-tip xy-arale-tip-02');" onmouseout="myhidden('xy-arale-tip xy-arale-tip-02');"></a></li>
            <li><a href="#" alt="微信" class="top-icon weixin" onmouseover="myshow('xy-arale-tip xy-arale-tip-02');" onmouseout="myhidden('xy-arale-tip xy-arale-tip-02');"></a></li>
            <li><a class="top-icon qq" href="http://wpa.qq.com/msgrd?v=3&uin=2931407238&site=qq&menu=yes" target="_blank"></a></li>
            &ndash;&gt;
        </ul>-->
            <ul class="xy-site-nav-bd-l">
                <li><a href="#">客服热线 ：400-9266-114</a></li>
              <li style="margin-left: 30px"  class="li_slideDown">
                    <#--<a href="javascript:;" target="_blank">-->
                    <a href="javascript:;">
                    <span class="weixin_Unselected icon_weixin">
                        <#--<i class="icon_weixin"></i>-->
                    </span>
                        <i class="">&nbsp;</i>官方微信
                        <span class="pic_weixin"></span>
                    </a>
                </li>
                <li  class="li_slideDown1">
                    <a href="javascript:;">
                    <span class="weibo_Unselected icon_weibo">
                        <#--<i class="icon_weibo"></i>-->
                    </span>
                        <i class="">&nbsp;</i>官方微博
                    </a>
                </li>
              <li  class="li_slideDown2">
                    <a href="javascript:;">
                   <#-- <span class="App_Unselected icon_App">
                        &lt;#&ndash;<i class="icon_App"></i>&ndash;&gt;
                    </span>-->
                        <span class="App_Unselected icon_App">
                        <i class="icon_App"></i>
                    </span>
                        <i class="">&nbsp;</i>安卓客户端
                    </a>
                </li>
            </ul>
            <div class="img_slide"></div>
            <div class="img_slide1"></div>
            <div class="img_slide2"></div>
            <ul class="xy-site-nav-bd-r">
                <#if Session.successHtml=='undefined'||Session.successHtml==null>
                <li>
                    <div class="xy-menu-hd">
                        <!--
                        <a href="http://182.247.239.6/" class="xy-regin" style="color:red;"><b>返回旧版</b></a>
                        <span>|</span>
                        -->
                        <a href="${base}/htmllogin.do" style="color: #cccccc;">立即登录</a>
                        <span>|</span>
                        <a href="${base}/htmlreg.do" class="xy-regin" style="color: #cccccc;">免费注册</a>
                        <span>|</span>
                        <a href="${base}/html/loginregSingle.do" class="xy-regin" style="color: #cccccc;">帮助</a>

                    </div>
                <#else>
				<li onmouseover="myshow('xy-menu-bd');" onmouseout="myhidden('xy-menu-bd');">
                   <div class="xy-menu-hd" >
                       ${Session.successHtml}
                   <#-- <a href="#" class="xy-login xy-meny-link-slid">欢迎回来，liyunfei</a>-->
                       <input type="hidden" value="${A_plan}" id="A_plan">
                   </div>
				
                   <div class="xy-menu-bd" style="display: none" id="xy-menu-bd" >
                       <div class="xy-menu-bd-panel">
                           <a href="#"><img src="${base}/theme/proj_wenandai/images/profile.png"/></a>
                           <div class="xy-user-info">
                              <#-- <p style="float: left;margin-right: 10px;margin-left: 9px;color:#3c3c3c;">我的升升投</p>-->
                              <#-- <p class="xy-user-operate">-->
                                  <p style="float: left;margin-right: 10px;margin-left: 9px;color:#3c3c3c;margin-bottom:10px;">我的升升投</p>
                                  <span>|</span>
                                  <a href="${base}/exitlogin.do">退出</a>
                                <#--  </p>-->
                               <p class="xy-user-money"><a href="#"><strong>账户可用余额(元)：</strong><br/><em><#if bpCustMember!=null><#if bpCustMember.availableInvestMoney==0>0.00<#else><#if bpCustMember.availableInvestMoney lt 1000>${bpCustMember.availableInvestMoney}<#else>${bpCustMember.availableInvestMoney?string(',###.00')}</#if></#if><#else>0.00</#if></em></a></p>
                           </div>

                           <div class="xy-user-privilege-tip">
                               <p class="xy-user-privilege-ing">
                                   <!-- <a href="#">累计理财收益(元)：<em><#if bpCustMember!=null><#if bpCustMember.allInterest==0>0.00<#else><#if bpCustMember.allInterest lt 1000>${bpCustMember.allInterest}<#else>${bpCustMember.allInterest?string(',###.00')}</#if></#if></em><#else>0.00</#if></em></a> -->
                                   <a href="#">累计理财收益(元)：<em id="ljsy">0.00</em></a>
                               </p>
                           </div>

                           <div class="xy-user-medal hide">
                               <div class="xy-user-medal-bd">
                                   <div class="xy-user-medal-cont">
                                    
                                        <#if bpCustMember.thirdPayFlagId!=null>
                                         	 <a href="javascript:void(0);"><img src="${base}/theme/proj_wenandai/images/xy-index/idcardon.png"/>身份验证</a>
                                        <#else>
                                         	<a href="${base}/thirdreg.do"><img src="${base}/theme/proj_wenandai/images/xy-index/idcardoff.png"/>银联验证</a>
                                        </#if>
                                    
                                    
                                       
                                        <#if bpCustMember.isCheckPhone==1>
                                        	<a href="javascript:void(0);"><img src="${base}/theme/proj_wenandai/images/xy-index/phoneon.png"/>手机验证</a>
                                        <#else>
                                       		<a href="${base}/user/getBpCustMember.do?typ=2&action=updateTelphone&retUrl=user/getBpCustMember.do?typ=1"><img src="${base}/theme/proj_wenandai/images/xy-index/phoneoff.png"/>手机验证</a>
                                        </#if>
                                        
                                        
                                        <#if bpCustMember.isCheckEmail!=1>
                                        	<a href="${base}/emailreg.do?type=2&action=email&retUrl=user/getBpCustMember.do"><img src="${base}/theme/proj_wenandai/images/xy-index/mailoff.png"/>邮箱验证</a>
                                        <#else>
                                        	<a href="javascript:void(0);"><img src="${base}/theme/proj_wenandai/images/xy-index/mailon.png"/>邮箱验证</a>
                                        </#if>
                                        
                                        
                                        <#if bpCustMember.thirdPayFlagId!=null>
                                         	<a href="javascript:void(0);"><img src="${base}/theme/proj_wenandai/images/xy-index/bankon.png"/>银联验证</a>
                                        <#else>
                                         	<a href="${base}/thirdreg.do"><img src="${base}/theme/proj_wenandai/images/xy-index/bankoff.png"/>银联验证</a>
                                        </#if>

                                   </div>
                               </div>
                           </div>

                       </div>
                   </div>
                </#if>
            </li>
                <!--  <li class="xy-site-tmsg">
                    <a class="xy-tmsg-basic" href="#">
                        <span class="xy-tmsg-icon"></span>
                        <span class="xy-tmsg-text">消息</span>
                        <span class="xy-tmsg-logo-unread">1</span>
                    </a>
                </li> -->
            </ul>
        <#--<div class="xy-yuntip" style="width: 418px;margin-top: 49px;position: absolute;">
        <登录按钮提示：是否登录Session.hurong_website；是否点击cloud Session.cloud；
            <img id="cloud" src="${base}/theme/proj_wenandai/images/yun_tips.png"
            <#if Session.hurong_website=='undefined'||Session.hurong_website==null>
                <#if Session.cloud=='1'>
                    style="display: none;margin-top: -60px;margin-left: -42px;"
                <#else>
                    style="display: block;margin-top: -60px;margin-left: -42px;"
                </#if>
            <#else>
                style="display: none;margin-top: -60px;margin-left: -42px;"
            </#if> onclick="myclick()"/>
        </div>-->
        </div>
    </div>
<script>
    $(function(){
        $(".li_slideDown").on("mouseover",function(){
            $(".img_slide").addClass("slideD");
        });
        $(".li_slideDown").on("mouseout",function(){
            $(".img_slide").removeClass("slideD");
        });
        $(".li_slideDown1").on("mouseover",function(){
            $(".img_slide1").addClass("slideD1");
        });
        $(".li_slideDown1").on("mouseout",function(){
            $(".img_slide1").removeClass("slideD1");
        });
        $(".li_slideDown2").on("mouseover",function(){
            $(".img_slide2").addClass("slideD1");
        });
        $(".li_slideDown2").on("mouseout",function(){
            $(".img_slide2").removeClass("slideD1");
        });
    });

</script>

<!-- 顶部bar -->
    <script>




        //phpcode
        //avoid namespace


        (function(){
            try{



                var Device = {
                    isIphone:function() {
                        return /(iPhone|iPod)/i.test(navigator.userAgent);
                    },
                    isAndroid:function() {
                        return /(Android)/i.test(navigator.userAgent);
                    },
                    isMobile:function(){

                        //phpcode
                        //checkmetaviewport

                        var documentHeader = document.head || document.getElementsByTagName("head")[0];

                        var metaList = documentHeader.getElementsByTagName('meta');
                        var isWap = false;
                        for(var i=0;i<metaList.length;i++) {
                            if(metaList[i].name=='viewport' && metaList[i].content.indexOf('initial-scale=1')!=-1) {
                                isWap = true;
                            }
                        }
                        return isWap && ( this.isIphone() || this.isAndroid());
                    }
                };

                var trustedHtml = '<div id="trusted_container"';

                var trustedHeight = 91;
                var trustedControlBtnHeight = 0;

                if(Device.isMobile()) {
                    trustedHeight = screen.availWidth/1080*(132+120);

                }

                trustedHtml += 'style="width: 100%;height:'+trustedHeight+'px;';

                //php code

                trustedHtml += 'left: 0;top: 0;position: relative;z-index: 100000000; overflow:hidden;">';/*transition:height 2s*/
                trustedHtml += '<iframe' +
                        ' id="trusted_iframe" ' +
                        'style="width: 100%; height:0px; overflow:hidden;' +
                        'height: '+trustedHeight+'px;" ' +
                        'src="//trusted.shuidi.cn/?did=2081&jump=0&mobile='+(Device.isMobile()?1:0)+'"' +
                        ' frameborder="false"></iframe></div>';


                //is real mobile

                //phpcode
                if(!Device.isMobile()) {

                    var trustedPcControlBtnRight =  0;


                    trustedHtml += '<div style="'

                    //php code
                    trustedHtml += 'position:absolute;';

                    //php code
                    trustedHtml +=   'top: 0;right: '+trustedPcControlBtnRight+'px;z-index: 100000001;">';

                    trustedHtml += '<img id="trusted_hide_image" onclick="hideTrustedFrame();" style="width:38px;height:79px;" src="//static.pacra.cn/shuidi/images/trusted-web3_01.png">';
                    trustedHtml += '<img id="trusted_show_image" onclick="showTrustedFrame();" style="display: none;width:38px;height:40px;" src="//static.pacra.cn/shuidi/images/trusted-web4_01.png">' +
                            '</div>';

                } else {

                    trustedControlBtnTop = screen.availWidth/1080*132;

                    trustedControlBtnHeight = screen.availWidth/1080*88;
                    trustedControlBtnWidth = screen.availWidth/1080*88;

                    var trustedControlBtnLeft = 0;

                    //first mobile button
                    trustedHtml += '<img style="'+
                            'width:'+trustedControlBtnWidth+'px;'+
                            'height:'+trustedControlBtnHeight+'px;';


                    //php code
                    trustedHtml += 'position: absolute;';
                    //php code
                    trustedHtml += 'top: '+trustedControlBtnTop+'px;left:'+trustedControlBtnLeft+'px;z-index: 100000002;" id="trusted_hide_image" onclick="hideTrustedFrame();"  src="//static.pacra.cn/shuidi/images/trusted-mobile-hide.png">';

                    //second button
                    trustedHtml += '<img style="'+
                            'width:'+trustedControlBtnWidth+'px;'+
                            'height:'+trustedControlBtnHeight+'px;';
                    //php code
                    trustedHtml += 'position: absolute;';
                    //php code


                    trustedHtml += 'top: 0;left:'+trustedControlBtnLeft+'px;z-index: 100000003; display:none;" id="trusted_show_image" onclick="showTrustedFrame();"  src="//static.pacra.cn/shuidi/images/trusted-mobile-show.png">';
                }

                //phpcode

                var trustedSuperContainer = document.createElement('div');
                trustedSuperContainer.style.width = "100%";
                //trustedSuperContainer.style.display = "none";
                //trustedSuperContainer.style.height = trustedHeight+"px";
                trustedSuperContainer.innerHTML = trustedHtml;

                var checkBodyHandler = 0;

                var addToHandler = function(){
                    if(document.body &&document.body.childNodes.length>=1) {
                        window.clearInterval(checkBodyHandler);

                        document.body.insertBefore(trustedSuperContainer,document.body.childNodes[0]);

                        document.getElementById("trusted_iframe").onload = function(){
                            //alert('loaded');
                            trustedSuperContainer.style.display = "";
                        };

                        //document.body.appendChild(trustedSuperContainer);


                        var show = sessionStorage.getItem("show");
                     /*   console.log(show);*/
                        if(sessionStorage.getItem("show") == null){
                            setTimeout(function(){
                              /*隐藏*/
                                document.getElementById("trusted_hide_image").style.display = 'none';
                                document.getElementById('trusted_show_image').style.display = '';
                                $("#trusted_container").slideUp("slow");
                                setTimeout(function(){
                                    document.getElementById('trusted_container').style.display = 'none';
                                },1000);

                                /*隐藏*/
                                sessionStorage.setItem("show", "0");

                            },5000);

                        }else if(sessionStorage.getItem("show") == "0"){
                            document.getElementById("trusted_hide_image").style.display = 'none';
                            document.getElementById('trusted_show_image').style.display = '';
                            document.getElementById('trusted_container').style.display = 'none';


                        }else if(sessionStorage.getItem("show") == "1"){
                            document.getElementById("trusted_show_image").style.display = 'none';
                            document.getElementById('trusted_hide_image').style.display = '';
                            document.getElementById('trusted_container').style.display = '';
                        }

                        //outside
                        window.hideTrustedFrame = function() {
                          /*  image.style.display = 'none';*/
                            document.getElementById("trusted_hide_image").style.display = 'none';
                            document.getElementById('trusted_show_image').style.display = '';
                            document.getElementById('trusted_container').style.display = 'none';
                            /*隐藏*/
                            sessionStorage.setItem("show", "0");
                        };

                        //outside
                        window.showTrustedFrame = function() {
                            /*image.style.display = 'none';*/
                            document.getElementById("trusted_show_image").style.display = 'none';
                            document.getElementById('trusted_hide_image').style.display = '';
                            document.getElementById('trusted_container').style.display = '';
                            /*显示*/
                            sessionStorage.setItem("show", "1");
                        };




                    }
                };
                if(document.body && document.body.childNodes.length>=1) {
                    addToHandler();
                } else {
                    checkBodyHandler = window.setInterval(addToHandler, 100);
                }
                //phpcode
            } catch(e) {
                //console.log(e);
            }
        })();
        //phpcode
    </script>
