<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 绑定第三方支付</title>
    <meta name="description" content="${systemConfig.metaTitle} - 绑定第三方支付,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 绑定第三方支付,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

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
    <div style="width:999px; min-height:840px; background:#fff; margin:10px auto;">
      <div style="float:left; width:999px; height:400px; margin:0px 0px 0px 0px;">
        <div style="width:999px; height:55px; padding:0px 0px 0px 0px;">
          <div style="width:899px; float:left; height:35px; padding:20px 0px 0px 50px;"><span class="big"  style="color:#0096c4;">绑定第三方支付</span></div>
        </div>
        <div style="width:910px; height:1px; padding:0px 40px;">
          <hr class="splitline" />
        </div>
        <br/>
        <form id="registerEnterprisePayForm" name="registerForm" action="${base}/pay/registerAndBindPay.do" method="post">
         <div style="width:919px; height:15px; padding:10px 0px 0px 200px;">
        	<div class="common-tip" style="display:none;"></div>
         </div>
        <div style="width:919px; height:30px; padding:30px 0px 0px 200px;">
          <div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">账号：</span></div>
          <div style="width:220px; height:30px; float:left;">
           <input id="loginId" type="hidden" value="${bpCustMember.id}" />
            <input id="loginname" name="loginname" readonly type="text" tabindex="1" maxlength="20" size="30" class="colorful1" value="${bpCustMember.loginname}" />
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span class="orange middle"></span><span class="gray middle"></span></div>
        </div>
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 200px;">
          <div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">企业名称：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="truename" name="truename" type="text" tabindex="4" onblur="checkTruename()"  size="30" class="colorful1" value="${bpCustMember.truename}" />
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray middle">请输入企业名称</span></div>
        </div>
        
         <div style="width:919px; height:30px; padding:20px 0px 0px 200px;">
          <div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">开户银行许可证：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="bankLicense" name="bankLicense" type="text"  tabindex="3" maxlength="40" size="30" class="colorful1" value="${bpCustMember.bankLicense}"/>
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray middle">请输入开户银行许可证</span></div>
        </div>
        <div style="width:919px; height:30px; padding:20px 0px 0px 200px;">
        	<div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">开户类型：</span></div>
	        <div style="width:120px; height:30px; float:left; line-height:30px;">
	          <input  name="guarType" type="radio"  tabindex="3" maxlength="20" size="30" checked="checked" style="float:left;margin:10px;" value="1"/>企业
	        </div>
	        <div style="width:120px; height:30px; float:left; line-height:30px;">
	          <input  name="guarType" type="radio"  tabindex="3" maxlength="20" size="30" style="float:left;margin:10px;" value="2"/>担保公司
	        </div>
        </div>
        <div style="width:919px; height:30px; padding:20px 0px 0px 200px;" id='threeType'>
        	<div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">是否三证合一：</span></div>
	        <div style="width:120px; height:30px; float:left; line-height:30px;">
	          <input  name="threeCard" type="radio"  tabindex="3" maxlength="20" size="30" checked="checked" style="float:left;margin:10px;" value="1"/>是
	        </div>
	        <div style="width:120px; height:30px; float:left; line-height:30px;">
	          <input  name="threeCard" type="radio"  tabindex="3" maxlength="20" size="30" style="float:left;margin:10px;" value="0"/>否
	        </div>
        </div>
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 200px;" id="unifiedCode">
        <div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">社会信用代码：</span></div>
        <div style="width:220px; height:30px; float:left;">
          <input id="threeCardCode" name="threeCardCode" type="text"  tabindex="3" maxlength="20" size="30" class="colorful1" value="${bpCustMember.cardcode}"/>
        </div>
        <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray middle">请输入社会信用代码</span></div>
      </div>
      
        <div style="width:919px; height:30px; padding:20px 0px 0px 200px;" class="myThreeCard">
          <div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">组织机构代码：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="cardcode" name="cardcode" type="text"  tabindex="3" maxlength="20" size="30" class="colorful1" value="${bpCustMember.cardcode}"/>
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray middle">请输入组织机构代码</span></div>
        </div>
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 200px;" class="myThreeCard">
          <div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">营业执照编号：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="businessLicense" name="businessLicense" type="text"  tabindex="3" maxlength="20" size="30" class="colorful1" value="${bpCustMember.businessLicense}"/>
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray middle">请输入营业执照编号</span></div>
        </div>
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 200px;" class="myThreeCard">
          <div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">税务登记号：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="taxNo" name="taxNo" type="text"  tabindex="3" maxlength="20" size="30" class="colorful1" value="${bpCustMember.taxNo}"/>
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray middle">请输入税务登记号</span></div>
        </div>
        
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 200px;">
          <div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">法人姓名：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="legalPerson" name="legalPerson" type="text"   tabindex="3" maxlength="20" size="30" class="colorful1" value="${bpCustMember.legalPerson}"/>
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray middle">请输入法人姓名</span></div>
        </div>
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 200px;">
          <div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">法人身份证号：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="legalNo" name="legalNo" type="text"  tabindex="3" maxlength="20" size="30" class="colorful1" value="${bpCustMember.legalNo}"/>
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray middle">请输入法人身份证号</span></div>
        </div>
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 200px;">
          <div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">企业联系人：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="contactPerson" name="contactPerson" type="text"  tabindex="3" maxlength="20" size="30" class="colorful1" value="${bpCustMember.contactPerson}"/>
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray middle">请输入企业联系人姓名</span></div>
        </div>
        
        <div style="width:919px; height:30px; padding:20px 0px 0px 200px;">
          <div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">联系人手机号：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="telphone" name="telphone" type="text" tabindex="2" readonly maxlength="20" size="30" class="colorful1" value="${bpCustMember.telphone}" />
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray normal">请输入正确的联系人手机号</span></div>
        </div>
        
       <#-- <div style="width:919px; height:30px; padding:20px 0px 0px 200px;">
          <div style="width:140px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">联系人邮箱：</span></div>
          <div style="width:220px; height:30px; float:left;">
            <input id="email" name="email" type="text" tabindex="4"   size="30" class="colorful1"  value="${bpCustMember.email}" />
          </div>
          <div style="width:400px; height:24px; float:left; padding-top:6px;margin-left:40px;">&nbsp;&nbsp;<span class="red middle">* </span><span class="gray middle">请输入联系人邮箱</span></div>
        </div>-->
       
        <div style="width:699px; height:60px; padding:40px 0 0 380px;" id="bandPayBtnDiv"> 
        <span class="buttonoblue1" style="font-size:18px; padding:6px 18px;" onClick="enterpriseThirdPaybind()" id="bindPayBtn"  tabindex="7">&nbsp;绑&nbsp;定&nbsp;</span>
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