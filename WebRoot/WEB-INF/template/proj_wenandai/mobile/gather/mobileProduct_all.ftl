<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <meta  http-equiv="x-rim-auto-match" content="none">
   <title>升升投 - 青春贷申请</title>
<script type="text/javascript" src="${base}/js/jQuery/jquery-1.8.2-min.js"></script>
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript" src="${base}/js/user/financeCheck.js"></script>
<script type="text/javascript" src="${base}/js/user/authentication.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
 <link href="${base}/theme/${systemConfig.theme}/css/mobile.css" rel="stylesheet" media="all">


<style type="text/css">
@charset "UTF-8";
body,input {margin: 0;padding: 0;font-family: "Myriad Set Pro","Lucida Grande","Helvetica Neue","Helvetica","Arial","Verdana","sans-serif";font-size:12px;line-height:1.5;text-rendering: optimizeLegibility;-webkit-font-smoothing: antialiased;-moz-osx-font-smoothing: grayscale;-moz-font-feature-settings: "liga", "kern";color: #ccc;border: 0;}
ul,li,ol,dl,dt,dd,h1,h2,h3,h4,h5,h6,em,i,p {padding: 0;margin: 0;list-style: none;font-style: normal;font-weight: normal;}
#wrap {background: #efefef;}
a,img {border: none;text-decoration: none;}
img{display: block;}
/* reset */

/*同意并继续按钮*/
.login_submit {
    padding: 22px 0 16px;
    text-align: center;
    background: #fff;
    border-top:1px solid #ddd;
}

.login_submit input {
    padding: 10px 24px;
    border: none;
    outline: none;
    border-radius: 2px;
    background: #3396CE;
    color: #FFF;
    text-shadow: 0 -1px rgba(0,0,0,0.15);
    box-shadow: 0 1px 2px rgba(0,0,0,0.2);
    cursor: pointer;
    -webkit-appearance: none;
    font: 14px Cambria, Georgia, serif;
    font-weight: bolder;
    margin: 0 auto;
    text-align: center;
}

/*申请进度样式①②③*/
.wadlc {
    padding: 15px 0;
    position: relative;
    background: #fff;
    overflow: hidden;
    margin-top: 1px;
}

.wadlc ul {
    position: relative;
}

.wadlc li {
    width: 33.333333%;
    float: left;
    z-index: 2;
    position: relative;
}

.wadlc a,.wadlc b,.wadlc span {
    display: block;
    text-align: center;
}

.wadlc span {
    padding: 2px;
    border: 2px dashed #ccc;
    width: 30px;
    height: 30px;
    margin: 0 auto;
    border-radius: 20px;
}

.wadlc a {
    font-size: 20px;
    color: #fff;
    width: 30px;
    margin: 0 auto;
    height: 30px;
    background: #ccc;
    border-radius: 15px;
    text-align: center;
    font-family: "Hiragino Sans GB","Microsoft YaHei","WenQuanYi Micro Hei",sans-serif;
    font-weight: bolder;
    line-height: 30px;
    box-shadow: 0 0 4px #ccc
}

.wadlc b {
    font-size: 10px;
    font-style: normal;
    margin-top: 3px;
}

.wadlc i {
    display: none;
}

.wadlc .spanpasstext {
    display: none;
}

.wadlc li:before {
    content: "";
    width: 81%;
    border-bottom: 2px dashed #ccc;
    position: absolute;
    top: 35.7%;
    left: 60%;
    z-index: -1;
}

.wadlc .spancurrent span {
    border-color: #01b0f1;
}

.wadlc .spancurrent a {
    background: #01b0f1;
    box-shadow: 0 0 4px #01b0f1
}

.wadlc .spancurrent b {
    color: #01b0f1
}

.wadlc li:nth-child(3):before {
    content: none;
}

.wadlc .spanpass span {
    border-color: #46ba5e;
}

.wadlc .spanpass a {
    text-indent: -9999px;
    background: #46ba5e;
    box-shadow: 0 0 4px #46ba5e
}

.wadlc .spanpass i {
    display: block;
    color: #fff;
    text-indent: 0;
    float: left;
    width: 30px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    font-weight: bolder;
    font-size: 24px;
    font-family: "Hiragino Sans GB","WenQuanYi Micro Hei","sans-serif";
}

.wadlc .spanpass b {
    color: #46ba5e;
    text-align: center;
    display: none;
}

.wadlc .spanpass:before {
    content: "";
    width: 81%;
    border-bottom: 2px dashed #46ba5e;
    position: absolute;
    top: 35.7%;
    left: 60%;
    z-index: -1;
}

.wadlc .spanpass .spanpasstext {
    display: block;
}

.textarea {
    padding: 0;
    line-height: 18px;
    height: auto;
    margin: 13px 0 10px 0;
    border: 0;
    width: 100%;
    font-size: 14px;
    color: #666;
    overflow-x: visible;
    overflow-y: visible;
    max-height: 100px;
}
/*表单样式*/
.wadqcShenqing li {
    width: 100%;
    overflow: hidden;
    background: #fff;
    height: 44px;
    box-shadow: 0 0 1px #ccc;
    padding: 1px 0;
}

.wadqcShenqing li>div {
    overflow: hidden;
    background: #fcfcfc;
    height: 44px;
}

.wadqcShenqing label {
    height: 44px;
    float: left;
    line-height: 44px;
    font-size: 14px;
    color: #333;
    text-align: left;
    padding: 0 2%;
    width: 22%;
    /*padding: 0 3%;*/
}

.wadqcShenqing em {
    color: #f50700;
    vertical-align: middle;
}

.wadqcShenqing li input {
    height: 24px;
    padding: 8px 0;
    color: #999;
    line-height: 24px;
    overflow: hidden;
    width: 70%;
    text-align: left;
    font-size: 14px;
    margin: 2px 0 2px 0;
    float: left;
    border: 0;
    background: none;
    font-weight: bolder;
}

.wadqcShenqing select {
    border: 0;
    background: transparent;
    -webkit-appearance: none;
    appearance: none;
    padding: 0;
    overflow: hidden;
    width: 70%;
    text-align: left;
    font-size: 14px;
    display: inline-block;
    vertical-align: bottom;
    height: 44px;
}

.wadqcShenqing .spec {
    padding: 20px 0 0 3%;
    height: 24px;
    line-height: 24px;
    color: #666;
    background: #fff;
    margin: 0;
    width: 97%;
}

.wadqcShenqing .specmore {
    margin-top: 1px;
}

.wadqcShenqing .spectips {
    height: auto;
    line-height: 18px;
    padding: 2px 0 20px 3%;
    margin-top: -1px;
    border-top: 1px solid #ddd;
    text-indent: 8px;
}

.wadqcShenqing li:nth-child(1) {
    margin-top: 0.2%;
}

.wadqcShenqing textarea {
    line-height: 20px;
    height: 60px;
    margin: 10px 0 10px 8.2%;
    border: 0;
    width: 65%;
    font-size: 14px;
    color: #666;
}

.wadqcShenqing .selectcolor {
    color: #999;
}

.wadqcShenqing input[readonly] {
    color: #999;
    width: auto
}

.wadqcShenqing .textareawrap,.wadqcShenqing .textareawrap div,.wadqcShenqing .textareawrap input {height: auto;}

/*弹出层申请表单验证*/
.tipswrap {
    position: fixed;
    top: 0;
    width: 100%;
    background: rgba(0,0,0,.7);
    display: none;
    transition: opacity .5s ease-in-out;
    -webkit-transition: opacity .5s ease-in-out;
    -moz-transition: opacity .5s ease-in-out;
    overflow: hidden;
    z-index: 10;
    bottom: 0;
    opacity: 1;
}

.tipswrap .tipscontent {
    margin: auto;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    max-height: 100%;
    max-width: 100%;
    box-shadow: 0 0 8px rgba(0,0,0,.3);
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    transform:scale(1,1);
    z-index: 0;
}

.tipswrap .tipscontent span {
    color: #000;
    font-size: 14px;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -52px;
    margin-left: -35%;
    background: #fff;
    display: block;
    border-radius: 5px;
    width: 70%;
}

.tipswrap .tipsclose {
    display: block;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    color: #007aff;
    height: 44px;
    text-align: center;
    line-height: 44px;
    border-top: 1px solid #bdbdbd;
    font-size: 15px;
}

.tipswrap .box_close:before,.tipswrap .box_close:after {
    content: none;
}

.tipswrap em {
    display: block;
    width: 100%;
    text-align: center;
    height: 60px;
    line-height: 60px;
    font-size: 18px;
    color: #222;
}
.wadqcShenqing .two .spec {margin:1px 0;height:14px;}
.wadqcShenqing .two label {width:26%;}
.wadqcShenqing .hukou .selectcolor{width: 34%;margin-left:-4px;}

.wadqcShenqing .label1 {text-indent: 40px;}
.wadqcShenqing .label2 {text-indent: 27px;}

.wadqcShenqing p {text-align: center;line-height: 24px;color: #000;font-size: 16px;}
.wadqcShenqing .three {border-top:1px solid #ddd;padding: 20px 0;}

.wadqcShenqing .gudingdianhua .selectcolor1 {width: 14%;}
.wadqcShenqing .gudingdianhua .selectcolor2 {width: 54%;}

.wadqcShenqing .qinshu{height:auto;}
.wadqcShenqing .qinshu div {height: auto;}
.wadqcShenqing .qinshu .selectcolor1{margin:-8px 0 2px 30%;width:20%;}
.wadqcShenqing .qinshu .selectcolor2{width:50%;margin:-8px 0 2px;}

</style>
</head>
<body >
 <div id="wrap">
        <!-- 顶部   -->
        <div class="wad_header">
            <div class="wad_topbar">
                <a href="#" class="wad_bak"></a>
                <em>青春贷申请</em>
                <span class="wad_allnav"></span>
            </div>

            <div class="wad_slidernav">
                <div class="wad_nav">
                    <p><img src="../theme/proj_wenandai/images/mobile/shouye.png"><a class="current">首页</a></p>
                    <p><img src="../theme/proj_wenandai/images/mobile/touzi.png"><a class="current">投资</a></p>
                    <p><img src="../theme/proj_wenandai/images/mobile/yonghu.png"><a class="current">我的</a></p>
                    <p><img src="../theme/proj_wenandai/images/mobile/guanyu.png"><a class="current">关于</a></p>
                </div>
            </div>
        </div>

        <!-- 内容 -->
        <div class="wad_qc">
            <div class="wadlc">
                <ul>
                    <li class="<#if bpCustMember.bambooJoint==1>spancurrent<#else>spanpass</#if>">
                        <span>
                            <a>1<i>√</i></a>
                        </span>
                        <b>填写借款信息</b>
                        <b class="spanpasstext">完成</b>
                    </li>
                    <li class="<#if bpCustMember.bambooJoint==1><#elseif bpCustMember.bambooJoint==2>spancurrent<#else>spanpass</#if>">
                        <span>
                            <a>2<i>√</i></a>
                        </span>
                        <b>填写个人信息</b>
                        <b class="spanpasstext">完成</b>
                    </li>
                    <li class="<#if bpCustMember.bambooJoint==1><#elseif bpCustMember.bambooJoint==2><#else>spancurrent</#if>">
                        <span>
                            <a>3<i>√</i></a>
                        </span>
                        <b>申请成功</b>
                        <b class="spanpasstext">完成</b>
                    </li>
                </ul>
            </div>

             <div class="wadqcShenqing">
                <#if bpCustMember.bambooJoint==1>
               		<#include "/WEB-INF/template/proj_wenandai/mobile/gather/mobileProduct_1.ftl">
                <#elseif bpCustMember.bambooJoint==2>
					<#include "/WEB-INF/template/proj_wenandai/mobile/gather/mobileProduct_2.ftl">
                <#elseif bpCustMember.bambooJoint==3> 
                   <div class="three">
                        <p>您已经申请成功</p>
                        <p>请耐心等待<br>我们的业务人员和您联系</p>
                   </div>
				</#if>
            </div>
        </div>

        <!-- 弹出层申请表单验证 -->
        <div class="tipswrap">
            <div class="tipscontent">
                <span>
                    <em class="tips"></em>
                    <i><a class="tipsclose" href="#">知道了</a></i>
                </span>
            </div>
        </div>
        <!-- footer  -->
        <div class="footer">
            <div class="version"><a href="#">Phone版</a><a href="#">PAD版</a><a href="javascript:;">电脑版</a></div>
            <div class="footer_bottom">
                <p class="wad_lianxi"><a><img src="../theme/proj_wenandai/images/mobile/qq.png">4006918987</a><a href="tel:4006918987"><img src="../theme/proj_wenandai/images/mobile/phone1.png">4006-918-987</a></p>
            </div>
            <div class="footer_banquan">版权所有 升升投商务顾问（北京）有限公司<br>© 2014</div>
        </div>
    </div>
    <script src='../js/zepto.min.js'></script>
    <script type="text/javascript">
        $(".wad_allnav").click(function(){
            $(".wad_slidernav").toggle();
        });
        $(function () {
            $('.bottom_box_up_target').click();
        });

        $('.wad_wcont').click(function () {
            $('#tipshide').show();
        });

        $('.wadlc').click(function () {
            $('.tips').text('您还没有填写贷款金额');
            $(".tipswrap").show();
        });

        $('.tipsclose').click(function () {
            $('.tipswrap').hide();
        });
    </script>
</body>
</html>