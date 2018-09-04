<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${systemConfig.metaTitle} -更换企业信息</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/bindPhone.js"></script>
    <script type="text/javascript" src="${base}/js/user/verifBindPhone.js"></script>
    <script type="text/javascript" src="${base}/js/user/changepassword.js"></script>
    <script type="text/javascript" src="${base}/js/user/check.js"></script>
    <script type="text/javascript" src="${base}/js/user/bindEmail.js"></script>
    <script type="text/javascript">var m1="个人账户",m2="账户设置",m3="账户安全";</script>
    <script type="text/javascript">


    </script>
</head>
<body >
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div class="main">
<div class="user-cont">
    <!--start: 左侧页面-->
    <div class="user-name-nav fl">
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
			 <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
    </div>
<div  class="user-cont-fr fr">
    <div>
        <#if type == '1'>
            <!-- 我的个人信息 start -->
            <div style=" height:40px; padding:10px 0px 0px 50px; ">
                <span class="large">更换企业名称</span>
            </div>

            <div style=" padding:20px 20px; overflow:hidden; class="cont-list">
            <!--此处放置内容-->

            <form id="beforeBindMobileForm" action="${base}/user/changeEnterpriseBpCustMember.do?type=1" method="post">
                <div class="hdlh">
                    <div class="hdlh1">
                        <ul style="width:400px;padding:20px 20px 40px 100px; ">
                        <#--<div class="common-tip" style="display:none"></div>-->
                            <li style="margin-bottom:10px;">
                                <div class="group_lable" id="validataCodeLabel">
							<span  style="font-weight: bold;font-size: 15px">企业名称：
								<input type="text"  value="${enterprise.truename}" style="color: red;font-size: 15px " name="enterpriseName"     class="colorful1"   />
							</span>
                                </div>
                            </li>
                            <li style="margin-bottom:10px;">
                                <div class="group_lable" id="validataCodeLabel">
							<span  style="font-weight: bold;font-size: 15px">法人姓名：
								<input type="text"  value="${enterprise.legalPerson}"  name="enterpriseLegalPerson" style="color: #1b53a3;border: 0px;font-size: 15px "   readonly="readonly"   class="colorful1"   />
							</span>
                                </div>
                            </li>
                            <li style="margin-bottom:10px;">
                                <div class="group_lable" id="validataCodeLabel">
							<span  style="font-weight: bold;font-size: 15px">法人证件：
								<input type="text"   value="${enterprise.legalNo}"  name="enterpriseLegal" style="color: #1b53a3;border: 0px;font-size: 15px "    readonly="readonly"    class="colorful1"   />
							</span>
                                </div>
                            </li>


                            <li>
                                <div class="common-tip" style="display:none"></div>
                            </li>
                            <li  style="height:40px;text-align: center" >
                                <input type="button" onclick="beforeBindMobileFormPost()" value="确认更改" class="buttonorange" />
                            </li>
                        </ul>
                    </div></div>
            </form>
    </div><!--放置内容结束-->
<#elseif type == '2'>
        <!-- 我的个人信息 start -->
        <div style=" height:40px; padding:10px 0px 0px 50px; ">
            <span class="large">更换法人信息</span>
        </div>

        <div style=" padding:20px 20px; overflow:hidden; class="cont-list">
        <!--此处放置内容-->

        <form id="beforeBindMobileForm" action="${base}/user/changeEnterpriseBpCustMember.do?type=2" method="post">
            <div class="hdlh">
                <div class="hdlh1">
                    <ul style="width:400px;padding:20px 20px 40px 100px; ">
                    <#--<div class="common-tip" style="display:none"></div>-->
                        <li style="margin-bottom:10px;">
                            <div class="group_lable" id="validataCodeLabel">
							<span style="font-weight: bold;font-size: 15px">企业名称：
								<input type="text"  value="${enterprise.truename}" style="color: #1b53a3;border: 0px;font-size: 15px " name="enterpriseName" readonly="readonly"     class="colorful1"   />
							</span>
                            </div>
                        </li>
                        <li style="margin-bottom:10px;">
                            <div class="group_lable" id="validataCodeLabel">
							<span style="font-weight: bold;font-size: 15px">法人姓名：
								<input type="text"  value="${enterprise.legalPerson}"  name="enterpriseLegalPerson" style="color: red;font-size: 15px "     class="colorful1"   />
							</span>
                            </div>
                        </li>
                        <li style="margin-bottom:10px;">
                            <div class="group_lable" id="validataCodeLabel">
							<span style="font-weight: bold;font-size: 15px">法人证件：
								<input type="text"  value="${enterprise.legalNo}"  name="enterpriseLegal" style="color: red;font-size: 15px "   class="colorful1"   />
							</span>
                            </div>
                        </li>


                        <li>
                            <div class="common-tip" style="display:none"></div>
                        </li>
                        <li  style="height:40px">
                            <input type="button"  onclick="beforeBindMobileFormPost()" value="确认更改" class="buttonorange" />
                        </li>
                    </ul>
                </div></div>
        </form>
    </div><!--放置内容结束-->

        </#if>


</div>
</div>
</div>
</div>
<script type="text/javascript">
  function beforeBindMobileFormPost() {
      document.getElementById("beforeBindMobileForm").submit();
  }




</script>


<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">


</body>
</html>