<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 主页</title>
    <meta name="description" content="${systemConfig.metaTitle} - 主页,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 主页,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/bindPhone.js"></script>
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript">var m1="";var m2="";var m3="";</script>
<script type="text/javascript">
$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200 });
});
</script>
<script type="text/javascript">
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200 });		
	});
	window.onload=function() {
	$(".finlist1 .finlist_title1 li").each(function(index) { //带参数遍历各个选项卡
		$(this).click(function() { //注册每个选卡的单击事件
			$(".finlist1 .finlist_title1 li.active").removeClass("active"); //移除已选中的样式
			$(this).addClass("active"); //增加当前选中项的样式
			//显示选项卡对应的内容并隐藏未被选中的内容
			$(".finlist1 .finlist_hide1 ol:eq(" + index + ")").show()
			.siblings().hide();
		});
	});
	}

</script>
<script type="text/javascript">
	function checkShow(){
	var logName=$("#loginname2").val();
	var pwd=$("#pwd2").val();
	if(logName==""){
		showTip("loginname2", "请输入用户名");
		return false;
	}
	if(pwd==""){
		showTip("pwd2", "请输入密码");
		return false;
	}
	var openID=$("#openID").val();
	var sinaID=$("#sinawb").val();
	debugger
	 $.ajax({
        	url:basepath + "/BindJudgereg.do?logName="+logName+"&pwd="+pwd+"&qq="+openID+"&sinaID="+sinaID,
        	data:"",
        	dataType:"JSON",
        	success:function(responseText,status){in
        		if(responseText.Msg=='0'){
        			showTip("loginname2", "用户名或密码错误！");
        			return false;
        		}else{
        			location.href=basepath;
        		}
        	}
        });
}
</script>
</head>
<body>
  <!-- topbar -->
  <!-- header --><!-- navbar -->
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">	
 
  <!-- main -->
  <div style="width:100%; background:#f1f1f1; overflow:hidden;">
  <div class="main">
  <div class="paner bigger">合作账号登录</div>
 <div class="finlist1">
       <ul class="finlist_title1">
          <li class="active"><a>没有${systemConfig.metaTitle}账号</a></li>          
          <li><a>绑定${systemConfig.metaTitle}账号</a></li>      
       </ul>
       <div class="finlist_hide1">  
       	<ol style="display:block">
         <div class="tab-box" >
			    <form id="registerFormWen" action="${base}/signreg.do?autoReg=autoReg" method="post">
				<!--<input type="hidden" id="token" value="${token}"/> -->
				<#if QQNickname!=null>
					<input id="" name="QQ" type="hidden"  value="${QQNickname}"/>
				</#if>
				<#if (bpCustMember.sinawb)!=null>
					<input id="" name="sinawb" type="hidden"  value="${(bpCustMember.sinawb)!}"/>
				</#if>
			    <div style="height:600px; background:#fff; margin:10px auto;">
			      <div style="float:left;  margin:0px 0px 0px 0px;">						       
			        <div style="width:819px; height:30px; padding:60px 0px 0px 60px;">
			          <div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span style="color:red;">*</span>&nbsp;<span class="black middle">账号：</span></div>
			          <div style="width:220px; height:30px; float:left;">
			            <input id="loginname" name="loginname" type="text" tabindex="1" maxlength="20" size="30" class="colorful1" placeholder="输入账号"  />
			          </div>
			          <!--<div id="tip-username"  style="width:548px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span class="orange middle" id ="spanUserId2"></span><span id="spanUserId" class="gray middle">用户名只允许字母、数字、下划线、横线；首位只能为字母；至少需要 6 个字符</span></div>-->
			        </div>
			        <!--<div style="width:819px; height:30px; padding:40px 0px 0px 60px;">
			          <div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span style="color:red;">*</span>&nbsp;<span class="black middle">手机号码：</span></div>
			          <div style="width:220px; height:30px; float:left;">			      
			            <input type="text" class="colorful1"tabindex="5"  id="telphone" name="telphone" placeholder="请输入手机号码" maxlength="11" size="30"/>
			          </div>
			          <div id="tip-username"  style="width:548px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span class="orange middle" id ="spanUserId2"></span><span id="spanUserId" class="gray middle">用户名只允许字母、数字、下划线、横线；首位只能为字母；至少需要 6 个字符</span></div>
			        </div> -->
			        <div style="width:819px; height:30px; padding:40px 0px 0px 60px;">
			          <div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle"><span style="color:red;">*</span>&nbsp;登录密码：</span></div>
			          <div style="width:220px; height:30px; float:left;">
			            <input id="password" name="password" type="password" tabindex="2" maxlength="20" size="30"  placeholder="输入密码"class="colorful1"   />
			          </div>
			          <!--<div id="tip-password" style="width:400px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span class="orange middle"></span><span id="password_span" class="gray middle">请输入6-16个字母或数字</span></div>-->
			        </div>
			        <div style="width:819px; height:30px; padding:40px 0px 0px 60px;">
			          <div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle"><span style="color:red;">*</span>&nbsp;重复密码：</span></div>
			          <div style="width:220px; height:30px; float:left;">
			            <input id="repeat_password" name="repeat_password" type="password"  tabindex="3" maxlength="20" size="30" class="colorful1"placeholder="再次输入密码"  />
			          </div>
			          <!--<div id="tip-repeat-password"  style="width:400px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span class="orange middle"></span><span id="repeat_password_span" class="gray middle">请重复输入上面的密码</span></div>-->
			        </div>
			        <div style="width:819px; height:30px; padding:40px 0px 0px 60px;">
			          <div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle">&nbsp;推荐码：</span></div>
			          <div style="width:220px; height:30px; float:left;">
			            <input id="recommandPerson" name="recommandPerson" type="text"  tabindex="3" maxlength="16" size="30" class="colorful1" placeholder="请输入邀请人的推荐码，推荐码为16位"  />
			          </div> 
			        </div>
			      
			        <div style="width:819px; height:30px; padding:40px 0px 0px 60px;">
			        <div style="width:919px; height:30px; padding:0px 0px 0px 0px;">
			          <div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span style="color:red;">*</span>&nbsp;<span class="black middle">验证码：</span></div>
			          <div style="width:80px; height:30px; float:left;">
			            <input id="checkCode" name="checkCode" type="text" tabindex="5" size="4" maxlength="4"style="width:73px;" class="colorful1" placeholder="输入验证码"  onkeyup="validatCheckCode(this)">
			            
			          </div>
			          <span class="orange middle" style="padding-top:10px; display:inline-block;"id ="spancheckCode"></span>
			          <div style="width:60px; height:29px; float:left; margin-left:30px;padding-top:1px"><img id="loginCode" class="verify-code"  style="vertical-align: middle; margin:0;height:35px;width:60px; cursor:pointer;" onclick="javascript:refresh(document.getElementById('loginCode'));"  src="${base}/getCode.do"></div>
			          <div style="width:100px; height:24px; float:left; padding-top:6px; margin-left:10px;">&nbsp;<span class="blue normal" style="cursor:pointer;" onclick="javascript:refresh(document.getElementById('loginCode'));">看不清，换一张</span></div>
			        </div>
			        <div style="width:919px; height:30px; padding:40px 0px 0px 100px;">
			          <label>
			            <input type="checkbox" id="readAgreement" tabindex="7" class="colorful1" style="height:16px;width:15px; float:left;margin:5px 10px 0 0;" />
			            <span class="black middle">我已阅读并且同意</span></label>
			          <a id="go" href="#signup" rel="leanModal" name="signup" class="font-blue"><span class="blue middle">《${systemConfig.metaTitle}服务协议》</span></a> </div>
			        <div style="width:699px; height:60px; padding:26px 0 0 180px; ">
						<a href="javascript:void(0)"><span class="buttonoblue1" style="font-size:18px; padding:6px 18px;cursor: pointer;" id="registerBtnWen"  tabindex="8">立&nbsp;即&nbsp;注&nbsp;册</span></a>
						
					</div>
			      </div>
			    </div>
			  </div>
			</form>
         </div>
       	</ol>
       	<ol>
       	<div class="tab-box">
       	 	<form id="form2" action="" method="">
       	 	<#if (bpCustMember.qq)!=null>
					<input id="openID" name="qq" type="hidden"  value="${(bpCustMember.qq)!}"/>
				</#if>
       	 	<#if (bpCustMember.sinawb)!=null>
       	 		<input id="sinawb" name="sinawb" type="hidden"  value="${(bpCustMember.sinawb)!}"/>
       	 	</#if>
				
				
			    <div style=" height:400px;background:#fff; margin:10px auto;">
			      <div style="float:left;   margin:0px 0px 0px 0px;">						       
			        <div style="width:819px; height:30px; padding:60px 0px 0px 60px;">
			          <div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span style="color:red;"></span>&nbsp;<span class="black middle">账号：</span></div>
			          <div style="width:220px; height:30px; float:left;">
			            <input id="loginname2" name="bpCustMember.loginname" type="text" tabindex="1" maxlength="20" size="30" class="colorful1" placeholder="输入账号"/>
			          </div>
			          <!--<div id="tip-username"  style="width:548px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span class="orange middle" id ="spanUserId2"></span><span id="spanUserId" class="gray middle">用户名只允许字母、数字、下划线、横线；首位只能为字母；至少需要 6 个字符</span></div>-->
			        </div>
			        
			        <div style="width:819px; height:30px; padding:40px 0px 0px 60px;">
			          <div style="width:100px; height:27px; float:left; text-align:right; padding-top:8px"><span class="black middle"><span style="color:red;"></span>&nbsp;密码：</span></div>
			          <div style="width:220px; height:30px; float:left;">
			            <input id="pwd2" name="bpCustMember.password" type="password" tabindex="2" maxlength="20" size="30"  placeholder="输入密码"class="colorful1"   />
			          </div>
			          <!--<div id="tip-password" style="width:400px; height:24px; float:left; padding-top:6px">&nbsp;&nbsp;<span class="orange middle"></span><span id="password_span" class="gray middle">请输入6-16个字母或数字</span></div>-->
			        </div>
		          
			        <div style="width:699px; height:60px; padding:50px 0 0 210px; ">
						<input type="button" onclick="checkShow()" class="buttonoblue1" style="font-size:18px; padding:6px 18px;cursor: pointer;" id="" value="立&nbsp;即&nbsp;绑&nbsp;定 "  tabindex="7" />
					</div>
			      </div>
			    </div>
			  </div>
			</form>
       </div>
       </ol>
       </div>
    </div>     
  </div>
</div>
<div id="signup" style="height:400px!important;">
	<div id="signup-ct">
		<div id="signup-header">
        	<div style="float:left; width:330px; height:40px; padding:10px 0px 0px 30px"><span  class="large blue">升升投网站服务协议</span></div>
            <div id="lean_overlay_close" style="float:right;  padding:20px 0px 0px 0px;width:60px; height:20px; text-decoration:underline; cursor:pointer;"  class="normal blue">关闭</div>
		</div>
        <div style="float:left; width:650px; height:300px; margin:10px 0px 0px 30px; overflow:auto; line-height:30px;">
        <span id="bidLoad"></span>
        <!--提示信息开始-->
    <h4 style="font-size:14px; color:#555;margin:0; text-indent:2em;">本网站由升升投商务顾问（北京）有限公司负责运营（以下“升升投”均指本网站及升升投商务顾问（北京）有限公司）,并依据本协议的规定为升升投用户（以下简称“用户”）提供服务。本协议在用户和升升投间具有法律效力。在您注册成为升升投用户前请务必必认真、仔细阅读并充分理解本协议全部内容。若您一旦注册，则表示您已经充分理解和同意本协议全部内容，同意接受升升投的服务并受以下条款的约束。若您不接受以下条款，请您立即停止注册或主动停止使用升升投的服务。</h4>
    <h4 style="font-size:14px; color:#555;margin:0;text-indent:2em;">在协议履行过程中，升升投可根据实际情况对协议的相关条款进行修改变化。一旦协议内容发生变动，升升投将公布最新的服务协议，一切变更以升升投最新公布条款为准，不再向用户作个别通知。经修订的协议、规则一经公布，立即自动生效，如果用户不同意升升投对协议所做的修改，有权停止使用升升投服务。如果用户继续使用升升投服务，则视为接受升升投对协议所做的修改，并应遵照修改后的协议履行应尽义务。本网站有权不经任何告知终止、中止本协议或者限制你进入本网站的全部或者部分板块且不承担任何法律责任。但该终止、中止或限制行为并不能豁免你在本网站已经进行的交易下所应承担的任何法律责任。本公司不承担任何因此导致的法律责任。</h4>
    <h4 style="font-size:14px; color:#555;margin:0;text-indent:2em;">本服务协议包括以下所有条款，同时也包括升升投已经发布的或者将来可能发布的各类规则。所有规则均为本服务协议不可分割的一部分，与本服务协议具有同等法律效力。</h4>
${systemConfig.regDeal}

	 <div class="serve">
	 <h4 style="margin:0; padding:10px 0; font-size:16px;">一、使用限制</h4>
	 <p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">互融时代只接受持有中国有效身份证明（不包括香港特区、澳门特区及台湾地区）的18周岁以上具有完全民事权利和民事行为能力，能够独立承担民事责任的自然人成为网站用户。互融时代保留中止或终止用户身份的权利。</p>
	 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">用户承诺合法使用互融时代提供的服务及网站内容。禁止在互融时代从事任何可能违反中国现行的法律、法规、规章和政府规范性文件的行为或者任何未经授权使用互融时代的行为，如擅自进入互融时代的未公开系统、不正当地使用密码和网站任何内容等。</p>
	 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">用户注册成功后，不得将互融时代的用户名转让给第三方或者授权给第三方使用。用户确认，使用本人的用户的用户名和密码登陆互融时代后在互融时代的一切行为均代表用户本人并由用户本人承担相应的法律后果。</p>
	 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">用户有义务在注册时提供自己的真实资料，并保证诸如电子邮件地址、联系电话、联系地址、邮政编码等内容的有效性及安全性。如用户因网上交易与其他用户产生诉讼的，互融时代将根据隐私规则披露相关用户资料。</p>
	 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">经国家生效法律文书或行政处罚决定确认用户存在违法行为，或者互融时代有足够事实依据可以认定用户存在违法或违反本服务协议的行为的或者用户借款逾期未还的，用户在此同意并授权互融时代在因特网络上公布用户的违法、违约行为及用户事先提供给互融时代的信息和资料，并将该等内容记入任何与用户相关的信用资料和档案。</p>
	 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">互融时代中的全部内容的版权均属于互融时代所有,该等内容包括但不限于文本、数据、文章、设计、源代码、软件、图片、照片及其他全部信息等(以下称“网站内容”)。网站内容受中华人民共和国著作权法及各国际版权公约的保护。未经互融时代事先书面同意,您承诺不以任何方式、不以任何形式复制、模仿、传播、出版、公布、展示网站内容,包括但不限于电子的、机械的、复印的、录音录像的方式和形式等。您承认网站内容是属于互融时代的财产。未经互融时代书面同意,您亦不得将互融时代包含的资料等任何内容镜像到任何其他网站或者服务器。任何未经授权对网站内容的使用均属于违法行为,互融时代将追究您的法律责任。</p>
	 </div>
	 <div class="serve">
		 <h4>二、 涉及第三方网站</h4>
		 <p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">本网站不能保证也没有义务保证第三方网站上的信息的真实性和有效性。您应按照第三方网站的服务协议使用第三方网站，而不是按照本协议。第三方网站的内容、产品、广告和其他任何信息均由您自行判断并承担风险，而与本网站无关。</p>
		
	 </div>	
	  <div class="serve">
		 <h4>三、 不保证</h4>
		 <p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">因为本网站或者涉及的第三方网站的设备、系统存在缺陷、黑客攻击、网络故障、电力中断、计算机病毒或其他不可抗力因素造成的损失，本网站均不负责赔偿，您的补救措施只能是与本网站协商终止本协议并停止使用本网站。但是，中国现行法律、法规另有规定的除外。</p>
		 <p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">在任何情况下，本网站及其股东、创建人、高级职员、董事、代理人、关联公司、母公司、子公司和雇员（以下称“本网站方”）均不以任何明示或默示的方式对您使用本网站服务而产生的任何形式的直接或间接损失承担法律责任，包括但不限于资金损失、利润损失、营业中断损失等，无论您通过本网站形成的借贷关系是否适用本网站的风险备用金规则或者是否存在第三方担保，并且本网站方不保证网站内容的真实性、充分性、及时性、可靠性、完整性和有效性，并且免除任何由此引起的法律责任。</p>	 
	 </div>	
	 <div class="serve">
		 <h4>四、责任限制</h4>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">在任何情况下,互融时代方对您使用互融时代服务而产生的任何形式的的直接或间接损失均不承担法律责任,包括但不限于资金损失、利润损失、营业中断损失等。因为互融时代或者涉及的第三方网站的设备、系统存在缺陷或者因为计算机病毒造成的损失,互融时代均不负责赔偿,您的补救措施只能是与互融时代终止本协议并停止使用互融时代。但是,中国现行法律、法规另有规定的除外。</p>
	</div>
	 <div class="serve">
		 <h4>五、网站内容监测</h4>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">互融时代没有义务监测网站内容，但是用户确认并同意互融时代有权不时地根据法律、法规、政府要求透露、修改或者删除必要的、合适的信息，以便更好地运营互融时代并保护自身及互融时代的其他合法用户。</p>
		 
	</div>
	 <div class="serve">
		 <h4>六、 个人信息的使用</h4>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">本网站对于你提供的、本网站自行收集到的、经认证的个人信息将按照本网站的隐私规则予以保护、使用或者披露。当你作为借入人借款逾期未还时，作为借出人的其他用户或其授权代理人可以采取发布你的个人信息方式追索债权，但本网站对该等借出人的行为免责。本网站对你的个人信息的具体使用规则的具体条款应适用本网站的《个人隐私规则》。</p>
      </div>
       <div class="serve">
		 <h4>七、索赔</h4>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">就任何第三方提出的,由于您违反本协议及纳入本协议的条款和规则或您违反任何法律、法规或侵害第三方的权利而产生或引起的每一种类和性质的任何索赔、要求、诉讼、损失和损害(实际的、特别的及有后果的),无论是已知或未知的,包括合理的律师费,您同意就此对互融时代和(如适用)互融时代的母公司、 子公司、关联公司、高级职员、董事、代理人和雇员进行补偿并使其免受损害。</p>		 
      </div>
      <div class="serve">
		 <h4>八、 通知</h4>
		 <p style="margin:0 0 0 10px; font-size:14px;">本协议项下的通知如以公示方式作出，一经在本网站公示即视为已经送达。除此之外，其他向您个人发布的具有专属性的通知将由本网站向您在注册时提供的电子邮箱，或本网站在您的个人账户中为您设置的站内消息系统栏，或您在注册后在本网站绑定的手机发送，一经发送即视为已经送达。请您密切关注您的电子邮箱 、站内消息系统栏中的邮件、信息及手机中的短信信息。您同意本网站出于向您提供服务之目的，可以向您的电子邮箱、站内消息系统栏和手机发送有关通知或提醒；若您不愿意接收，请在本网站相应系统板块进行设置。但您同时同意并确认，若您设置了不接收有关通知或提醒，则您有可能收不到该等通知信息，您不得以您未收到或未阅读该等通知信息主张相关通知未送达于您。</p>
		
      </div>
       <div class="serve">
		 <h4>九、 终止</h4>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">除非本网站终止本协议或者您申请终止本协议且经本网站同意，否则本协议始终有效。在您违反了本协议、相关规则，或在相关法律法规、政府部门的要求下，本网站有权通过站内信、电子邮件通知等方式终止本协议、关闭您的账户或者限制您使用本网站。但本网站的终止行为不能免除您根据本协议或在本网站生成的其他协议项下的还未履行完毕的义务。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">您若发现有第三人冒用或盗用您的用户账户及密码，或其他任何未经合法授权的情形，应立即以有效方式通知本网站，要求本网站暂停相关服务，否则由此产生的一切责任由您本人承担。同时，您理解本网站对您的请求采取行动需要合理期限，在此之前，本网站对第三人使用该服务所导致的损失不承担任何责任。</p>
      	 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">您决定不再使用用户账户时，应首先清偿所有应付款项（包括但不限于借款本金、利息、罚息、违约金、服务费、管理费等），再将用户账户中的可用款项（如有）全部提现或者向本网站发出其它合法的支付指令，并向本网站申请注销该用户账户，经本网站审核同意后可正式注销用户账户。会员死亡或被宣告死亡的，其在本协议项下的各项权利义务由其继承人承担。若会员丧失全部或部分民事权利能力或民事行为能力，本网站有权根据有效法律文书（包括但不限于生效的法院判决等）或其法定监护人的指示处置与用户账户相关的款项。</p>
         <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">本网站有权基于单方独立判断，在认为可能发生危害交易安全等情形时，不经通知而先行暂停、中断或终止向您提供本协议项下的全部或部分会员服务，并将注册资料移除或删除，且无需对您或任何第三方承担任何责任。前述情形包括但不限于：</p>
         <p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">(1)、本网站认为您提供的个人资料不具有真实性、有效性或完整性；</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">(2)、本网站发现异常交易或有疑义或有违法之虞时；</p>	
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">(3)、 本网站认为您的账户涉嫌洗钱、套现、传销、被冒用或其他本网站认为有风险之情形；</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">(4)、 本网站认为您已经违反本协议中规定的各类规则及精神；</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">(5)、 本网站基于交易安全等原因，根据其单独判断需先行暂停、中断或终止向您提供本协议项下的全部或部分会员服务，并将注册资料移除或删除的其他情形。</p>
     	 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">您同意在必要时，本网站无需进行事先通知即有权终止提供用户账户服务，并可能立即暂停、关闭或删除您的用户账户及该用户账户中的所有相关资料及档案，并将您滞留在这些账户的全部合法资金退回到您的银行账户。</p>
     	 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">您同意，用户账户的暂停、中断或终止不代表您责任的终止，您仍应对您使用本网站服务期间的行为承担可能的违约或损害赔偿责任，同时本网站仍可保有您的相关信息。</p>
     	 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">若您使用第三方网站账号注册本网站用户账户，则您应对您本网站用户账户所对应的第三方网站账号拥有合法的使用权，如您因故对该第三方网站账号丧失使用权的，则本网站可停止为您的该用户账户提供服务。但如该用户账户尚存有余额的，本网站将会为您妥善保管。此时，如您欲取回其原有余额，本网站将提供更换本网站账户名的服务，即您可把您原本网站账户下余额转移到您另外合法注册的本网站账户中；如因故无法自助完成更换账户名，您可以向本网站提出以银行账户接受原有资金的请求，经核验属实后，本网站可配合您将原有资金转移到以您真实姓名登记的银行账户下。</p>
         
      </div>
       <div class="serve">
		 <h4>十、适用法律和管辖</h4>
		 <p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">因互融时代所提供服务而产生的争议均适用中华人民共和国法律，并由互融时代住所地的人民法院管辖。</p>
		 <p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">互融时代对本协议拥有最终的解释权。本协议及互融时代有关页面的相关名词可互相引用参照，如有不同理解，则以本协议条款为准。此外，若本协议的部分条款被认定为无效或者无法实施时，本协议中的其他条款仍然有效。</p>
		
      </div>
       <div class="serve">
		 <h4>个人隐私规则：</h4>
		 <p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">本网站对本协议拥有最终的解释权。本协议及本网站有关页面的相关名词可互相引用参照，如有不同理解，则以本协议条款为准。此外，若本协议的部分条款被认定为无效或者无法实施时，本协议中的其他条款仍然有效。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">(1)、本网站认为您提供的个人资料不具有真实性、有效性或完整性；</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">互融时代只接受持有中国有效身份证明（不包括香港特区、澳门特区及台湾地区）的18周岁以上具有完全民事权利和民事行为能力，能够独立承担民事责任的自然人成为网站用户。若用户违反前述限制注册使用互融时代的，其监护人应承担所有责任。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">互融时代保留随时中止或终止你用户资格的权利。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">互融时代对于用户提供的、自行收集的、经认证的个人信息将按照本规则予以保护、使用或者披露。当用户作为借入人借款逾期未还时，互融时代及作为借出人的其他用户可以采取发布用户的个人信息的方式追索债权，但互融时代对上述行为免责。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">(2)、用户信息、资料的提供、搜集及核实</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">您有义务在注册时及后续修改时，提供自己的真实资料，并保证诸如电子邮件地址、联系电话、联系地址、邮政编码等内容的有效性、安全性。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">互融时代可能自公开及私人资料来源收集您的额外资料，以更好地了解互融时代用户，并为其度身订造本网站服务、解决争议和确保在网站进行交易的安全性。互融时代仅收集互融时代认为就此目的及达成该目的所必须的关于您的个人资料。</p>		 
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">您同意互融时代可以自行或通过合作的第三方机构对您提交或互融时代搜集的用户信息（包括但不限于您的个人身份证信息等）进行核实，并对获得的核实结果根据本协议及有关文件进行查看、使用和留存等操作。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">互融时代按照您在互融时代上的行为自动追踪关于您的某些资料。本网站利用这些资料进行有关互融时代之用户的人口统计、兴趣及行为的内部研究，以更好地了解您以便向您和互融时代的其他用户提供更好的服务。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">互融时代在本网站的某些网页上使用诸如“Cookies”的资料收集装置。“Cookies”是设置在您的硬盘上的小档案，以协助互融时代为您提供度身订造的服务。互融时代亦提供某些只能通过使用“Cookies”才可得到的功能。互融时代还利用“Cookies”使您能够在某段期间内减少输入密码的次数。“Cookies”还可以协助互融时代提供专门针对您的兴趣而提供的资料。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">如果您将个人通讯信息（例如：手机短信、电邮或信件）交付给互融时代，或如果其他用户或第三方向互融时代发出关于您在互融时代上的活动或登录事项的通讯信息，互融时代可以将这些资料收集在您的专门档案中。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">(3)、本网站认为您提供的个人资料不具有真实性、有效性或完整性；</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">您同意互融时代可使用关于您的个人资料（包括但不限于互融时代持有的有关您的档案中的资料，及互融时代从您目前及以前在互融时代上的活动所获取的其他资料）以解决争议、对纠纷进行调停、确保在互融时代进行安全交易，并执行互融时代的服务协议及相关规则。互融时代有时候可能调查多个用户以识别问题或解决争议，特别是互融时代可审查您的资料以区分使用多个用户名或别名的用户。为限制在网站上的欺诈、非法或其他刑事犯罪活动，使本网站免受其害，您同意互融时代可通过人工或自动程序对您的个人资料进行评价。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">您同意互融时代可以使用您的个人资料以改进互融时代的推广和促销工作、分析网站的使用率、改善互融时代的内容和产品推广形式，并使互融时代的网站内容、设计和服务更能符合用户的要求。这些使用能改善互融时代的网页，以调整互融时代的网页使其更能符合您的需求，从而使您在使用互融时代服务时得到更为顺利、有效、安全及度身订造的交易体验。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">您同意互融时代利用您的资料与您联络并（在某些情况下）向您传递针对您的兴趣而提供的信息，例如：有针对性的广告条、行政管理方面的通知、产品提供以及有关您使用互融时代的通讯。您接受本协议即视为您同意收取这些资料。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">互融时代对于您提供的、自行收集到的、经认证的个人信息将按照本协议及有关规则予以保护、使用或者披露。互融时代将采用行业标准惯例以保护您的个人资料，但鉴于技术限制，互融时代不能确保您的全部私人通讯及其他个人资料不会通过本协议中未列明的途径泄露出去。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">您使用互融时代服务进行交易时，您即授权本公司将您的包括但不限于真实姓名、联系方式、信用状况等必要的个人信息和交易信息披露给与您交易的另一方或互融时代的合作机构（仅限于互融时代为完成拟向您提供的服务而合作的机构）。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">互融时代有义务根据有关法律要求向司法机关和政府部门提供您的个人资料。在您未能按照与本协议、互融时代有关规则或者与互融时代其他用户签订的有关协议的约定履行自己应尽的义务时（包括但不限于当您作为借款人借款逾期或有其他违约时），互融时代有权根据自己的判断、有关协议和规则、国家生效裁决文书或者与该笔交易有关的其他用户的合理请求披露您的个人资料（包括但不限于在互融时代及互联网络上公布您的违法、违约行为，并有关将该内容记入任何与您相关的信用资料、档案或数据库），并且作为出借人的其他用户可以采取发布您的个人信息的方式追索债权或通过司法部门要求互融时代提供相关资料，互融时代对此不承担任何责任。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">(4)、您对其他用户信息、资料的使用</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">在互融时代提供的交易活动中，您无权要求互融时代提供其他用户的个人资料，除非符合以下条件：</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">(1)、 您已向法院起诉其他用户的在互融时代活动中的违约行为；</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">(2)、 与您有关的其他用户逾期未归还借款本息；</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">(3)、 与您有关的其他用户逾期未归还借款本息；</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">如您通过签署有关协议等方式获得其他用户的个人信息，您同意不将该等个人信息用于除还本付息或向借款人追索债权以外的其他任何用途，除非该等信息根据适用的法律规定、被有管辖权的法院或政府部门要求披露。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">(5)、账户及密码的安全性</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">您注册成功后应妥善保管您的用户名和密码。您确认，无论是您还是您的代理人，用您的用户名和密码登录互融时代后在互融时代的一切行为均代表您并由您承担相应的法律后果。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">(6)、电子邮件和群组</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">你不得使用互融时代提供的服务或其他电子邮件转发服务发出垃圾邮件或其他可能违反本网站的用户协议或隐私规则的内容。</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">如果你利用互融时代的服务向没有在本网站内注册的电子邮件地址发出电子邮件,本网站除了利用该电子邮件地址发出你的电子邮件之外将不作任何其他用途。互融时代不会出租或出售这些电子邮件地址。互融时代不会永久储存电子邮件信息或电子邮件地址</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">(7)、规则修改</p>
		 <p style="margin:0 0 0 10px; font-size:14px;text-indent:2em;">互融时代可能不时按照你的意见和本网站的需要修改本隐私规则,以准确地反映本网站的资料收集及披露惯例。本规则的所有修改,在本网站于拟定生效日期前在网站公布有关修改通知后生效。</p>
      </div>
	</div>
</div>
</div>
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl"> 
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl">
</body>
</html>
