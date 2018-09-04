<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 实名认证</title>
    <meta name="description" content="${systemConfig.metaTitle} - 实名认证,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 实名认证,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/thirdPay.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>

<script type="text/javascript">var m1="个人中心",m2="",m3="";</script>
</head>
<body >
  <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div style="width:100%; background:#f1f1f1; overflow:hidden;">
  <div class="main">  
    <div style="width:999px; height:550px; background:#fff; margin:10px auto;">
      <div style="float:left; width:999px; height:400px; margin:0px 0px 0px 0px;">
        <div style="width:999px; height:55px; padding:0px 0px 0px 0px;">
          <div style="width:899px; float:left; height:35px; padding:20px 0px 0px 50px;"><span class="big"  style="color:#0096c4;">实名认证</span></div>
        </div>
        <div style="width:910px; height:1px; padding:0px 40px;">
          <hr class="splitline" />
        </div>
        <br/>
        <form id="registerPayForm" name="registerForm" action="${base}/pay/registerAndBindPay.do" method="post">
         <div style="width:919px; height:15px; padding:30px 0px 0px 200px;">
        	<div class="common-tip" style="display:none;"></div>

         </div>
        <div style="width:919px; height:30px; padding:30px 0px 0px 200px;">
          <div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">账号：</span></div>
          <div style="width:220px; height:30px; float:left;">
           <input id="loginId" type="hidden" value="${bpCustMember.id}" />
            <input id="loginname" name="loginname" readonly type="text" tabindex="1" maxlength="20" size="30" class="colorful1" value="${bpCustMember.loginname}" />
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span class="orange middle"></span><span class="gray middle"></span></div>
        </div>
        
        <#--<div style="width:919px; height:30px; padding:20px 0px 0px 200px;">
          <div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">Email：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="email" name="email" type="text" tabindex="4"   size="30" class="colorful1" onblur="paybindEmail()" value="${bpCustMember.email}" />
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="gray middle">请输入您常用的邮箱地址</span></div>
        </div>-->
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 200px;">
          <div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">手机号码：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="telphone" name="telphone" type="text" tabindex="2" readonly maxlength="20" size="30" class="colorful1" value="${bpCustMember.telphone}" />
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray normal">请输入正确的手机号码</span></div>
        </div>
        <div style="width:919px; height:30px; padding:20px 0px 0px 200px;">
          <div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">真实姓名：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="truename" name="truename" type="text"  tabindex="3" maxlength="20" size="30" class="colorful1" onblur="checkTruename1()" value="${bpCustMember.truename}"/>
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray middle">请输入真实姓名</span></div>
        </div>
         <div style="width:919px; height:30px; padding:20px 0px 0px 200px;">
          <div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">身份证号：</span></div> 
          <div style="width:220px; height:30px; float:left;">
            <input id="cardcode" name="cardcode" type="text"  tabindex="3" maxlength="20" size="30" onblur="checkCardcode()" class="colorful1" value="${bpCustMember.cardcode}"/>
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray middle">请输入身份证号</span></div>
        </div>
        
       
       
        <div style="width:699px; height:60px; padding:40px 0 0 380px;" id="bandPayBtnDiv"> 
        <span class="buttonoblue1" style="font-size:18px; padding:6px 18px;" onClick="paybind()" id="bindPayBtn"  tabindex="7">&nbsp;绑&nbsp;定&nbsp;</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <!--<span class="buttonorange" style="font-size:18px; padding:6px 18px;" id="topBtn" onclick="goHome('${base}')"  tabindex="7">&nbsp;跳&nbsp;过&nbsp;</span>
        -->
         </div>
         </form>
      </div>
    </div>
  </div>

<div style="height:4px; border-bottom:1px #f0f0f0 solid; margin:15px auto 0px auto;" title="横线"></div>
<!-- end main --> 
    </div>
    </div>
    <!--login form end -->
<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</body>
</html>