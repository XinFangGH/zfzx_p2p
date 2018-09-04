<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <meta  http-equiv="x-rim-auto-match" content="none">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>升升投 - 注册</title>
    <link rel="stylesheet"  media="all" type="text/css" href="${base}/theme/${systemConfig.theme}/css/mobile.css" />

   <#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/newregister.js"></script>
    <script type="text/javascript" src="${base}/js/user/check.js"></script>
    <script type="text/javascript" src="${base}/js/ymPrompt/ymPrompt.js"></script>

 <#--   <script>
        var ROOT_URL = "${base}";
        $(function(){
            $('.cd-switcher li').click(function(){
                $(this).addClass("selected").siblings().removeClass('selected');
                if($(this).context.id!=null && $(this).context.id!='undefined' && $(this).context.id!=''){
                    if($(this).context.id == 'company'){
                        $("#getVerifySms").attr('onclick','validatTel2CheckCode_qy();')
                    }else{
                        $("#getVerifySms").attr('onclick','validatTel2CheckCode(this);')
                    }
                }
                $(".register-inner .login-block").hide().eq($('.cd-switcher li').index(this)).show();
            });
        });
    </script>-->


</head>
<body>
<div id="wrap" class="wad_loginbg">
    <div class="wad_mobile">
        <div class="login_header  my_header"  style="width:100%;height:50px;background-color:#e84518;border-bottom:10px solid #f3f3f3;position: relative;padding: 0">
            <a class="btn_back"  id="btn_back" href="${base}/indexp2pmobile.html"  style="position:absolute;display: inline-block;height:0px;left:13px;top: 16px;">
                <img src="${base}/theme/${systemConfig.theme}/images/massage_back.png" alt="" style="display:inline-block;width:11px;height:20px">
            </a>
            <h3 style="line-height: 50px;font-size: 18px;color:#FFF">注册</h3>
        </div>

       <#-- <div class="login_header">
            <a class="btn_back" href="${base}/indexp2pmobile.html"><i class="icon ico_back"  style="top:5px;width:40%;left:0px;text-align:left;">返回</i></a>
            <div style="width:100%;text-align:center;margin-top:25px;">
                <div style="display:inline-block;width:62px;">
                    <img style="display:inline-block;width:62px;height:62px;" src="${base}/theme/${systemConfig.theme}/images/fanhui_logo.png">
                    <img style="display:inline-block;width:56px;height:29px;margin-top:12px;" src="${base}/theme/${systemConfig.theme}/images/fanhui_shengshengtou.png">
               </div>
            </div>
        </div>-->


        <div class="register-left">
            <form id="registerFormWen" action="${base}/user/newsignreg.do?isMobile=1" method="post" style="    margin-top: 20px;">
			 <@s.token/>
			 <@s.token/>
                <input hidden="true" id="true_loginname">
                <input hidden="true" id="true_password">
                <input hidden="true" id="true_repeat_password">
                <input hidden="true" id="true_telphone">
                <input hidden="true" id="true_checkCode">
                <input hidden="true" id="typeTelphone">
                <input hidden="true" id="isExist">
                <input hidden="true" id="true_spancheckCode">
                <input hidden="true" id="true_phone_msg">
                <input hidden="true" id="true_recommandPerson_msg">
                <input hidden="true" id="true_departrecommandPerson_msg">
              <#--  <div class="reg_group" style="margin-bottom:20px;">
                    <ul class="cd-switcher">
                        <li id="person" class="pay_list_c1 selected"><a href="###"><em class="cked-bg"></em>个人注册</a></li>
                        <li id="company" class="pay_list_c1"><a href="###"><em class="cked-bg"></em>企业注册</a></li>
                    </ul>
                </div>-->

                <div class="reg_group" style=" margin-left:0px;">
                    <div class="group_lable" style="display:inline-block;margin-right: 5px"><span>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</span></div>
                    <div class="group_input" style="width: 150px;">
                        <input id="loginname" name="loginname"  style="display:inline-block;width: 150px;" type="text" tabindex="2" maxlength="20" size="30" class="input-text-style-3 input-wrap" placeholder="请编写6~16个字符" onblur="chkUserName(this)"  tabindex="1" />
                    </div>
                    <div class="login-form-error" style="width:100%;left:0px">
                        <div style="width: 150px;margin:0 auto">
                            <span class="icon-error" id="spanUserId_img" ></span>
                            <span id="spanUserId" class="error" style="float: inherit;"></span>
                        </div>
                    </div>
                </div>

                <div class="reg_group"  style=" margin-left:0px;">
                    <div class="group_lable"  style="display:inline-block;margin-right: 5px"><span>登录密码：</span></div>
                    <div class="group_input"  style="width: 150px;">
                        <input id="password" name="password"   style="display:inline-block;width: 150px;" type="password" maxlength="20" size="30" class="input-text-style-3 input-wrap" placeholder="输入密码" onblur="chkPass(this)" tabindex="2" />
                    </div>
                    <div class="login-form-error"  style="width:100%;left:0px">
                        <div style="width: 150px;margin:0 auto">
                            <span  class="icon-error" id="password_span_img"></span>
                            <span id="password_span"  class="error" style="float: inherit;"></span>
                        </div>
                    </div>
                </div>

                <div class="reg_group"  style=" margin-left:0px;">
                    <div class="group_lable"  style="display:inline-block;margin-right: 5px"><span>确认密码：</span></div>
                    <div class="group_input"   style="width: 150px;">
                        <input id="repeat_password"   style="display:inline-block;width: 150px;" name="repeat_password" type="password"  tabindex="4" maxlength="20" size="30" class="input-text-style-3 input-wrap" placeholder="再次输入密码"  onblur="chkRepPass(this)" tabindex="3" />
                    </div>
                    <div class="login-form-error"   style="width:100%;left:0px">
                        <div  style="width: 150px;margin:0 auto">
                            <span id="repeat_password_span_img" class="icon-error"></span>
                            <span id="repeat_password_span" class="error"  style="float: inherit;"></span>
                        </div>
                    </div>
                </div>

                <div class="login-block"  style=" margin-left:0px;">
                    <div class="reg_group"  style=" margin-left:0px;">
                        <div class="group_lable"   style="display:inline-block;margin-right: 5px"><span >手机号码：</span></div>
                        <div class="group_input "  style="width: 150px;">
                            <input type="text" class="input-text-style-3 input-wrap"  style="display:inline-block;width: 150px;"   id="telphone" name="telphone" placeholder="请输入手机号码" maxlength="11" size="30"  onblur="checkTelphone(this)" tabindex="7" />
                        </div>
                        <div class="login-form-error"   style="width:100%;left:0px">
                            <div  style="width: 150px;margin:0 auto">
                                <span class="icon-error" id="telphone_span_img" ></span>
                                <span id="telphone_span"  class="error"  style="float: inherit;"></span>
                            </div>
                        </div>
                    </div>
                </div>

                <!--企业客户注册--公司名称-->
             <#-- <div class="login-block none"  style=" margin-left:0px;">
                    <div class="reg_group">
                        <div class="group_lable"  ><span>企业名称：</span></div>
                        <div class="group_input">
                            <input id="truename" name="truename" type="text"  maxlength="20" size="30" class="input-text-style-3 input-wrap" placeholder="企业名称" tabindex="4"/>
                        </div>
                        <div class="login-form-error">
                            <span class="icon-error" id="truename_msg_img"></span>
                            <span id="truename_msg"  class="error"></span>
                        </div>
                    </div>
                    <!--联系人手机号码&ndash;&gt;
                    <div class="reg_group"  style=" margin-left:0px;">
                        <div class="group_lable"   style="display:inline-block;margin-right: 5px"><span>联系人手机号：</span></div>
                        <div class="group_input "   style="width: 150px;">
                            <input type="text" class="input-text-style-3 input-wrap"  style="display:inline-block;width: 150px;"  id="telphone_qy" name="telphone_qy" placeholder="请输入手机号码" maxlength="11" size="30"  onblur="checkTelphone_qy(this)" tabindex="7" />
                        </div>
                        <div class="login-form-error"  style="left:0px">
                            <span class="icon-error" id="telphone_span_img_qy" ></span>
                            <span id="telphone_span_qy"  class="error"></span>
                        </div>
                    </div>
              </div>-->

                <div class="reg_group"  style=" margin-left:0px;">
                    <div class="group_lable" id="validataCodeLabel"   style="display:inline-block;margin-right: 5px"><span>图形验证码：</span></div>
                    <div class="group_input" id="validataCode"  style="width:98px;">
                        <input type="text" id="checkCode" name="checkCode"  size="8" maxlength="4"  class="input-text-style-3 input-wrap" placeholder="输入图形验证码"  onkeyup="validatCheckCode(this)" tabindex="8" style="width:98px;" />
                    </div>
                    <div class="login-form-error"    style="width:100%;left:0px">
                        <div  style="width: 150px;margin:0 auto">
                            <span class="icon-error" id ="spancheckCode_img"></span>
                            <span id ="spancheckCode"  class="error"  style="float: inherit;"></span>
                        </div>
                    </div>
                    <div class="phone_msg">
                        <img width="100" height="36" id="loginCode" class="verify-code" alt="点击更换" title="点击更换" onclick="javascript:refresh(document.getElementById('loginCode'));"  src="${base}/getCode.do" style="cursor:pointer;" />
                    </div>
                </div>

               <div class="reg_group"  style=" margin-left:0px;">
                    <div class="reg_code" style="padding:0;width: 100%;">
                        <div class="group_lable"   style="display:inline-block;margin-right: 5px"><span>手机验证码：</span></div>
                        <div class="group_input phoneln"   style="width: 150px;">
                            <input type="text" id="tel_checkCode" name="checkCode" style="width:150px;"  size="15" maxlength="6" class="input-text-style-3 input-wrap" placeholder="输入验证码" onblur="checkTelphoneCode(this)" tabindex="9"  />
                        </div>
                        <div class="phone_msg"  style="padding-left:0px;width: 100px">
                            <a id="getVerifySms" class="getcode" onclick="validatTel2CheckCode(this);" href="javascript:void(0);">获取验证码</a>
                        </div>
                        <div class="login-form-error"   style="width:100%;left:0px">
                            <div  style="width: 150px;margin:0 auto">
                                <span class="icon-error"  id="phone_msg_img" ></span>
                                <span id="phone_msg"  class="error"  style="float: inherit;"></span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="reg_group"  style=" margin-left:0px;">
                    <div class="group_lable"   style="display:inline-block;margin-right: 5px"><span>邀请码(选填)：</span></div>
                    <div class="group_input"   style="width: 150px;">
                        <input id="recommandPerson" name="recommandPerson" style="width: 150px" <#if recommand!=null>disabled="disabled"</#if>  type="text" value="${recommand!}"   maxlength="16" size="30" class="input-text-style-3 input-wrap" placeholder="无邀请码可不填写" onblur="checkisExistRecommand(this)" tabindex="10" />
                        <input id="recommand" name="recommand" type="hidden" value="${recommand}" />
                    </div>
                    <div class="login-form-error"   style="width:100%;left:0px">
                        <div  style="width: 150px;margin:0 auto">
                            <span class="icon-error"  id="recommandPerson_msg_img"></span>
                            <span id="recommandPerson_msg"  class="error"  style="float: inherit;"></span>
                        </div>
                    </div>
                </div>

                <div class="reg_agree" style="width: 100%;padding:0px;">
                    <div style="margin-left:20px;" class="my_div">
                        <label>
                            <input type="checkbox" id="readAgreement" tabindex="" class="input-text-style-3 input-wrap"  />
                            <span>已阅读且同意</span></label>
                        <a id="go" href="#signup" rel="leanModal" name="signup" class="font-blue"><span class="blue middle" id="shengshengtouxieyi">《升升投服务协议》</span></a>
                        <div class="login-form-error"  style="width:100%;left:0px">
                            <div style="width: 150px;margin:0 auto">
                                <span id="readAgreement_msg_img" class="fl"></span>
                                <span id="readAgreement_msg"  class="error fl"   style="float: inherit;"></span>
                            </div>
                        </div>
                    </div>
                </div>

               <#if source!=null>
                <input type="hidden" name="source" id="source" value="${source!}">
                  </#if>
                <div class="reg_btn" style="width: 100%;margin-top:30px;padding:0px;">
                    <a href="javascript:void(0)" style="display: block;width: 200px;height:40px;margin:0 auto">
                        <input type="button" class="input-submit-style-3"  id="registerBtnWen" tabindex="8"  value="立&nbsp;即&nbsp;注&nbsp;册" style="width: 200px;line-height:40px;margin:0 auto;background-color: #e84518">
                    </a>
                </div>
            </form>
        </div>
    </div>
   <#--<div class="wad_footer_banquan" style="padding: 0px 5% 10px;">升升投携手易宝支付，<br>共同为客户打造诚心、可靠、安全的投资理财平台</div>-->
</div>

<div id="zhezhao" style="display: none;position: fixed;z-index:10;top:0px; left:0px;height: 100%; width: 100%;background: #000;opacity: 0.3;"></div>
<div id="signup" style="display: none; position: fixed;width:100%;height:500px;z-index:100;top:40px;left:0px;background:#B3B3B3;box-shadow: 0 0 4px rgba(208, 193, 193, 0.7)!important;" >
    <div id="signup-ct" style="width:300px;height:500px;margin:0px auto;border:1px solid #f5f5f5;background-color: #FFF;border-radius: 5px;">
        <div id="signup-header" style="width: 100%;height:40px;line-height:40px;padding:0px">
            <div style="float:left; width:200px; height:40px;line-height: 40px; padding-left:10px">
                <span  class="large blue" style="font-size: 16px">升升投会员服务协议</span>
            </div>
            <div id="lean_overlay_close" style="float:right;padding-right:10px; height:40px; text-decoration:underline; cursor:pointer;"  class="normal blue">关闭</div>
        </div>
        <div style="float:left; width:100%; height:440px; overflow:auto; ">
            <span id="bidLoad"></span>
            <!--提示信息开始-->
            <div class="serve">
                <h4 style="margin:0; padding:10px 0; font-size:14px;">一、协议主体</h4>
                <p style="margin:0 0 0 10px; font-size:12px; text-indent:2em;">1、服务提供方：即中发展信（北京）投资管理有限公司。</p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">2、服务接受方：即中发展信（北京）投资管理有限公司（以下简称“升升投”）平台网站用户，包含注册用户和非注册用户。注册用户是指通过www.zxzbol.com网站完成全部注册程序后，使用升升投平台网站服务的用户。非注册用户是指未进行注册,直接登录www.zxzbol.com网站或通过其他网站进入www.zxzbol.com使用升升投平台网站服务的用户。</p>
            </div>
            <div class="serve">
                <h4 style="margin:0; padding:10px 0; font-size:14px;">二、缔约前提</h4>
                <p style="margin:0 0 0 10px; font-size:12px; text-indent:2em;">1、在成为本网站用户前，用户确认已充分阅读并理解本《注册服务协议》（本协议）的所有条款。如用户对协议有任何疑问，可向升升投咨询。用户同意以下条款并注册后，升升投有权利依据本协议条款对用户进行约束、管理，用户有权依据本协议的接受升升投的服务。</p>
                <p style="margin:0 0 0 10px; font-size:12px; text-indent:2em;">2、本服务协议内容包括以下条款及已经发布的或将来可能发布的各类规则。所有规则均为协议不可分割的一部分，与协议正文具有同等法律效力。除另行明确声明外，升升投提供的任何服务均受本协议约束。用户承诺接受并遵守本协议的约定。</p>
                <p style="margin:0 0 0 10px; font-size:12px; text-indent:2em;">3、升升投有权根据需要不时地修改本协议或制定其他各类规则，如本协议及规则有任何变更，平台将在网站上刊载公告，经修订的协议、规则一经公布后，立即自动生效。如用户不同意相关变更，请停止使用升升投平台网站服务，并退出升升投。如用户继续使用升升投平台网站服务，即表示用户理解并接受经修订的协议和规则。如新旧规则或协议之间冲突或矛盾的，除另行明确声明外，以最新修订的协议和规则为准。</p>
                <p style="margin:0 0 0 10px; font-size:12px; text-indent:2em;">4、升升投是民间资金的需求、提供和担保等配套服务的信息公示平台，为会员之间订立投融资合同提供媒介服务，平台上关于升升投会员或其发布的相关信息（包括但不限于公司名称、 联系人及联络信息，资金的需求或供给描述和说明等）均由会员自行提供，会员应对其提供的信息承担全部责任。用户应自行结合自身的财务状况、风险承受能力等审慎作出投融资或担保行为，并自行承担因此产生的法律后果。
                </p>

            </div>
            <div class="serve">
                <h4 style="margin:0; padding:10px 0; font-size:14px;">三、 服务内容</h4>
                <p style="margin:0 0 0 10px; font-size:12px; text-indent:2em;">1、升升投提供的服务包括但不限于通过本网站以及其他渠道和方式等向用户提供以下服务中的一项或多项：(1)撮合形成借贷交易的民间服务；(2)理财投融资服务；(3)信贷咨询及管理服务；(4)其他相关服务。具体详情以本网站当时提供的服务内容为准。</p>
                <p style="margin:0 0 0 10px; font-size:12px; text-indent:2em;">2、用户成功注册后，可以自行或授权代理人根据本网站有关规则和说明，通过本网站确认签署有关协议并经本网站审核通过后实现融资需求或资金的投资。细操作规则及方式请见有关协议及本网站相关页面的规则和说明。</p>
            </div>
            <div class="serve">
                <h4 style="margin:0; padding:10px 0; font-size:14px;">四、用户注册</h4>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">1、用户只要勾选位于注册页面下方的“我同意会员服务协议”选项并按照本网站的流程成功注册后，本协议即产生法律效力；不得以未签署书面协议为由否认本协议的效力，本协议适用于用户在本网站的全部活动。</p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">2、在注册时，用户应当按照法律法规要求和注册页面的提示，准确填写并及时更新信息。用户承诺在升升投上填写的资料、发布的信息等都真实、合法，否则，由此导致用户在使用升升投平台网站服务过程中产生任何损失或增加费用的，应完全由用户承担。
                </p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:4em;">3、本协议不涉及用户与本网站的其他用户之间因网上交易而产生的法律关系及法律纠纷，用户可自行通过合法途径解决其纠纷，但用户在此同意将全面接受和履行与其他用户在升升投平台网站上签订的任何电子法律文本，并承诺按该法律文本享有相应的权利、承担相应的义务。</p>
            </div>
            <div class="serve">
                <h4 style="margin:0; padding:10px 0; font-size:14px;">五、承诺和保证</h4>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">1、用户承诺其为中华人民共和国（不包括香港特区、澳门特区及台湾地区）的具有完全民事行为能力和权利能力的自然人以及具有符合法律规定的能独立承担法律责任或有合法授权可以进行金融交易的法人、社团或其他组织。如用户不符合资格，请勿注册，否则由此产生的一切责任自负，升升投保留中止或终止用户身份的权利。 </p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">2、用户承诺合法使用网站提供的服务及网站内容，禁止在升升投平台从事任何可能违反中国现行的法律、法规、规章和规范性文件的行为或者任何未经授权使用网站信息的行为。否则，网站保留追究其法律责任的权利。 </p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">3、用户保证在注册时提供自己的真实资料，并保证诸如电子邮件地址、联系电话、联系地址等内容的有效性及安全性。如用户因网上交易与其他用户产生诉讼的，网站将根据隐私规则披露相关用户资料。</p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">4、用户注册成功后，不得将用户名等信息转让给第三方或者授权给第三方使用。用户确认，使用本人的用户名和密码等信息登录本网站及在本网站的一切行为均代表用户本人并由用户本人承担相应的法律后果。</p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">5、用户保证并承诺通过本网站平台进行交易的资金来源合法。</p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">6、若用户违反本承诺和保证的，升升投有权依据本协议的约定，做出相应处理或终止向用户提供服务，且无须征得用户的同意或提前通知用户。如使升升投遭受任何损失的（包括但不限于受到第三方的索赔、受到行政管理部门的处罚等），用户还应当赔偿或补偿升升投遭受的损失及（或）发生的费用，包括诉讼费、律师费、保全费等。</p>
            </div>
            <div class="serve">
                <h4 style="margin:0; padding:10px 0; font-size:14px;">六、资金管理</h4>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">1、用户在升升投进行的借款或出借，由升升投委托的第三方机构将为用户提供“资金管理服务”，主要包括但不限于：资金的充值、提现、代收、代付、查询等，用户承诺完全同意并遵守第三方机构的资金管理规则。用户可以通过本网站有关页面的具体规则或说明详细了解。</p>
            </div>
            <div class="serve">
                <h4 style="margin:0; padding:10px 0; font-size:14px;">七、保密</h4>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">1、对于用户提供的资料及数据信息，用户授予升升投永久的、免费的使用权利，但升升投承诺不用于本网站之外的其他不合理用途。</p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">2、用户同意升升投在业务运营中储存并使用用户信息，包括但不限于根据双方另行签署的协议在网站公示用户相关信息；向本网站的合作机构（该合作机构仅限于本网站为了完成拟向用户提供的服务而合作的机构）提供用户信息；由人工或自动程序对信息进行评估、分类、研究；使用用户信息以改进本网站的推广；以及使用用户提供的联系方式向用户传递有关业务和管理方面的信息。</p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">3、升升投平台对用户提供的经认证的个人信息将按照本规则予以保护、使用或者披露。当用户未能按照平台相关协议内容履行应尽义务时，升升投有权根据自己的判断或者根据与交易有关的其他用户的请求披露用户的个人资料，并有权决定对信息资料标记进入网站黑名单，并将黑名单对第三方披露，与第三方之间进行数据共享，由此因第三方行为造成用户损失的，升升投平台不承担任何法律责任。</p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">4、用户注册成功后应妥善保管用户名和密码。用户确认，使用用户名和密码登录本网站后在本网站的一切行为均代表用户的行为并自行承担相应的法律后果。</p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">5、升升投将采用行业标准惯例以保护用户的个人资料，但鉴于技术限制，本网站不能确保用户的全部私人通讯及其他个人资料不会通过本协议中未列明的途径泄露出去。
                </p>
            </div>
            <div class="serve">
                <h4 style="margin:0; padding:10px 0; font-size:14px;">八、 电子合同</h4>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">1、在升升投平台交易需订立的协议采用电子合同方式，可以有一份或者多份并且每一份具有同等法律效力。用户根据有关协议及升升投的相关规则在本网站确认签署的电子合同即视为用户本人真实意愿并以用户本人名义签署的合同，具有法律效力。用户应妥善保管自己的账户密码等账户信息，用户通过前述方式订立的电子合同对合同各方具有法律约束力，用户不得以账户密码等账户信息被盗用或其他理由否认已订立的合同的效力或不按照该等合同履行相关义务。</p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">2、用户根据本协议以及本网站的相关规则签署电子合同后，不得擅自修改该合同。本网站向用户提供电子合同的保管查询、核对等服务，如对电子合同真伪或电子合同的内容有任何疑问，用户可通过本网站的相关系统板块查阅有关合同并进行核对。如对此有任何争议，应以本网站记录的合同为准。</p>
            </div>
            <div class="serve">
                <h4 style="margin:0; padding:10px 0; font-size:14px;">九、救济</h4>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">1、用户在发现本网站任何内容不符合法律规定，或不符合本用户协议规定的，用户有义务及时通知升升投。如果用户发现用户的个人信息被盗用，或者用户的其他权利被侵害，请将此情况告知升升投</p>
                <p style="margin:0 0 0 10px; font-size:12px;text-indent:2em;">
                    经审查得到证实的，我们将及时删除相关信息。我们仅接受邮寄、电子邮件或传真方式的书面侵权通知。情况紧急的，用户可以通过客服电话先行告知，我们会视情况采取相应措施。
                </p>
            </div>
            <div class="serve">
                <h4 style="margin:0; padding:10px 0; font-size:14px;">十、声明</h4>
                <p style="margin:0 0 0 10px; font-size:12px; text-indent:2em;">1、升升投平台的网站内容可能涉及第三方所有、控制、运营的其他网站（即“第三方网站”），升升投平台没有义务保证第三方网站上信息的真实性、有效性，由用户自行判断风险和承担风险，与升升投平台无关。</p>
                <p style="margin:0 0 0 10px; font-size:12px; text-indent:2em;">2、除升升投平台明确保证提供担保的或者本金保障计划等有明确协议规则外，平台提供的服务中不带有对任何用户和交易提供任何保证和条件。</p>
            </div>
            <div class="serve">
                <h4 style="margin:0; padding:10px 0; font-size:14px;">十一、附则</h4>
                <p style="margin:0 0 0 10px; font-size:12px; text-indent:2em;">1、本协议始终有效，除非本网站终止或者本网站同意用户的终止申请。用户对升升投平台有任何投诉和建议的，请及时将意见反馈给客服人员。</p>
                <p style="margin:0 0 0 10px; font-size:12px; text-indent:2em;">2、本协议某些条款被认定为无效或者无法实施时，不影响其他条款的效力，其他条款继续有效。</p>
                <p style="margin:0 0 0 10px; font-size:12px; text-indent:2em;">3、因使用本网站产生争议的均适用中华人民共和国法律，协议管辖法院为升升投平台住所地法院。</p>
                <p style="margin:0 0 0 10px; font-size:12px; text-indent:2em;">4、 升升投网站保留对本规则的最终解释权。</p>
            </div>
        </div>

    </div>
</div>

<script src='${base}/js/mobile/zepto.min.js'></script>
<script>
    $("#readAgreement").click(function(){
        if($("#readAgreement").attr("checked")=="checked"){
            $("#readAgreement_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/successicon.jpg"/>');
            $("#readAgreement_msg").html("已阅读协议");
        }else{
            document.getElementById("readAgreement_msg").style.color = "red";
            $("#readAgreement_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
            $("#readAgreement_msg").html("请选中协议");
        }
    });

    $("#registerBtnWen").click(function() {
        checkForm();
    })
    $("#registerBtnWen").click(function() {
        checkForm();
    })
</script>
<script>
    $("#shengshengtouxieyi").click(function(){
        $("#zhezhao").css("display","block");
        $("#signup").css("display","block");
    });

    $("#lean_overlay_close").click(function(){
        $("#zhezhao").css("display","none");
        $("#signup").css("display","none");
    });

    $()

</script>


<#--<script type="text/javascript">
    var i = 60;
    function a(){
        $("#meg").html(i+"秒后可以重新获取");
        $("#meg").css("color","gray");
        $("#meg").attr("onclick","");

        i = i-1;
        if(i<=0){
            $("#meg").html("获取手机验证码");
            $("#meg").css("color","#fff");
            $("#meg").attr("onclick","tomsg()");
        }else{
            setTimeout(a, 1000);
        }

    }
    $(".wad_allnav").click(function(){
        $(".wad_slidernav").toggle();
    });

    function tomsg (){
        $("#errorMsg").html("");
        var telphone = $("#telphone_reg_random").val();
        if(telphone==''||telphone==null){
            $("#errorMsg").html("提示：请填写手机号码！");
            return false;
        }
        var url = 'codecreate.do'
        i = 60
        $.ajax({
            url:url,
            dataType:"json",
            data:{telphone:telphone},
            success:function(data){
                if(data.status!='200'){
                    $("#errorMsg").html("提示："+data.remark);
                }else{
                    a();
                }
            }
        });
    };

    $("#mreg").click(function(){
        if(!document.getElementById("agree").checked){
            $("#errorMsg").html("错误提示：请阅读并接受《服务协议》");
            return false;
        }
        $("#errorMsg").html("");
        var uss = $("#username").val();
        var pa = $("#pass").val();
        var wo =$("#word").val();
        var trr = $("#telphone_reg_random").val();
        var cc = $("#checkCode").val();
        $.ajax({
            url:'msignreg.do',
            dataType:"json",
            data:{username:uss,pass:pa,word:wo,telphone_reg_random:trr,checkCode:cc},
            success:function(data){
                if(data.success){
                    $("#errorMsg").html("错误提示："+data.errMsg);

                }else{
                    //window.location.href="${base}/user/getBpCustMember.do?mobile=mobile";
                    document.getElementById("wad_login").style.display="none";
                    document.getElementById("wad_chenggong").style.display="";
                }
            }

        });

    });

</script>-->
</body>
</html>