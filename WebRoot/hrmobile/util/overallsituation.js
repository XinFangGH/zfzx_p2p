// 导航颜色
/*var themeColor = "#f86963"; // 常用主题色*/
var themeColor = "#D6450C"; // 常用主题色
var nobtncolor = "#cccccc"; // 非选取按钮色
var topColor = "#ffffff"; // 默认字体色
var topsize = "17px"; // 字体大小
var loandata;


// 底部导航字体颜色(绿色)
var bottomNavColor = '#1db88f';

// 灰色颜色
var e3Color = '#e3e3e3'; 

// ST默认常用字体灰色
var adColor = '#ADADAD';

// ST列表底部间距颜色
var e6Color = '#E6E6E6';

// 背景灰色
var f3Color = '#F3F3F3';

// 按钮红色
var btnBg = '#f86963';

// 列表标题颜色
var listColor = '#f86963';

// 设置字体为.75ren
var font75rem = 'font-size:.75rem;';

function getnextleixing(next){
//	nodeFamily
//	nodeOver
//	nodePerson
//	nodeStore
//	nodeWork
//	if(!isStringEmpty(next.nodeFamily)){
//		
//	}
}


// 账号余额
 function ajaxgetUser() {
	Ext.Ajax.request({
				url: __ctxPath +"/creditFlow/financingAgency/availableInvestMoneyPlBidPlan.do",
				async: false,
				 params : {
					isMobile : "1"
		         },
			    success : function(response) {
			    	var responseText = Ext.util.JSON.decode(response.responseText);
			    	curUserInfo.availableInvestMoney = responseText.availableInvestMoney;
 }
	    });
 }
 // 重新登陆
function ajaxgetuserid (md5) {
	// 是否使用md5加密
	var md5 = false || md5;
	
	// 判断是否需要加密密码登录
	var pwd = md5 == false ? localStorage.allpassword : $.md5(localStorage.allpassword);
	Ext.Ajax.request({
				url: __ctxPath+'/tologin.do',
				async: false,
				params: {
					isMobile : "1",
					loginname:curUserInfo.loginname || localStorage.userName,
					password:pwd
				},
		        success : function(response) {
			    	var responseText = Ext.util.JSON.decode(response.responseText);
			    	curUserInfo = responseText.data;
	            },
				 failure: function() { 
					 Ext.Msg.confirm("警告！","登录信息已失效，请重新登录", function(btn) {
							if (btn == "yes") {
								window.location.reload();
							}else{
								window.location.reload();
							}
							
					 });
				 }
	    });
 }
 
 
 
 
 
 //贷款计算器
function allsumloan(loanmoney,loanlimit,loanmonth,loanselect){
	//loanlimit年利率
	//loanmoney金额
	//loanmonth 期限
	//loanselect还款选项
	var html="";
	var loanmoney = new Number(loanmoney);
	var loanmonth = new Number(loanmonth);
	var loanlimit = new Number(loanlimit);
	var lixi=loanmoney*loanlimit*loanmonth/1200
	var allmoney=lixi+loanmoney;
	if(loanselect==1){
		var monthlixi =lixi/12;
		var endmonthlixi=monthlixi;
		for(i=1;i<loanmonth;i++){
			allmoney=allmoney-monthlixi;
			html=html+'<tr><td>'+i+'</td><td>￥'+moneyFormat(monthlixi)+'</td><td>￥0</td><td>￥'+moneyFormat(monthlixi)+'</td><td style="border-right: 1px solid #ccc;">￥'+moneyFormat(allmoney)+'</td></tr>'
		}
		html=html+'<tr><td>'+i+++'</td><td>￥'+moneyFormat(monthlixi+loanmoney)+'</td><td>'+moneyFormat(loanmoney)+'</td><td>￥'+moneyFormat(monthlixi)+'</td><td style="border-right: 1px solid #ccc;">￥0</td></tr>'
	}else if(loanselect==1){
		
	}else if(loanselect==1){
		
	}else if(loanselect==1){
		
	}else if(loanselect==1){
		
	}
	return html;
}
function enterPriseset(data,enterPrise,pltype,proDes){
		A=new Object();
		if(pltype=="B_Dir"){
			enterPrise=data;
			A=data.bpBusinessDirPro.plBusinessDirProKeep;
		}else if(pltype=="B_Or"){
			enterPrise=data;
			A=data.bpBusinessOrPro.plBusinessDirProKeep
		}else if(pltype=="P_Dir"){
			A=data.bpPersionDirPro.plPersionDirProKeep
		}else if(pltype=="P_Or"){
			A=data.bpPersionOrPro.plBusinessDirProKeep
		}else {
			
		}
		
		if(Ext.isEmpty(A)){
			B=new Object();
			B.guarantorsName="未填";
			B.guarantorsDes="未填";
			B.guarantorsName="未填";
			B.guarantorsAdvise="未填";
			return B;
		}
	return A;
}
function enterPrisesetsss(enterPrise){
	enterPrise=new Object();
	enterPrise.enterprisename="未填";
	enterPrise.registermoney=null;
	enterPrise.opendate="未填";
	enterPrise.managecityName="未填";
	enterPrise.hangyeName="未填";
	return enterPrise;
}
function proDeszhuan(proDes){
	proDesA=new Object();
	proDesA.proDes="未填";
	proDesA.proUseWay="未填";
	proDesA.proPayMoneyWay="未填";
	proDesA.proRiskCtr="<span style='float: right;' >未填</span>";
	proDesA.workCity="未填";
	return proDesA;
}

function pB1setkey(){
	pB1setkey=new Object();
	pB1setkey.mainFinance="未填";
	return pB1setkey;
}

var xyarrayObj = new Array();
xyarrayObj[1]="HR"
xyarrayObj[2]="E"
xyarrayObj[3]="D"
xyarrayObj[4]="C"
xyarrayObj[5]="B"
xyarrayObj[6]="A"
xyarrayObj[7]="AA"


var superhtml1='<div class="serve">'+
	 '<h4 style="margin:0; padding:10px 0; font-size:16px;">第一条 用户的身份限制</h4>'+
	 '<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">1.1 本网站只接受持有有效身份证件的18周岁以上的具有完全民事行为能力的自然人成为网站用户。如您不具备上述资格，您应立即停止在本网站的注册程序、停止使用本网站服务，本网站有权随时终止您的注册进程及本网站服务，您应对您的注册给本网站带来的损失承担全额赔偿责任，且您的监护人（如您为限制民事行为能力的自然人）应承担连带责任。</p>'+
	 '<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">1.2 在注册时和使用本网站服务的所有期间，您应提供您自身的真实、最新、有效及完整的信息资料并保证自您注册之时起至您使用本网站服务的所有期间，其所提交的所有资料和信息（包括但不限于电子邮件地址、联系电话、联系地址、邮政编码、个人身份信息）有效性及安全性。</p>'+
	 '<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;"></p>'+
	 '<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;"></p>'+
	 '<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;"></p>'+
	 '<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;"></p>'+
	 '</div>'+
	 '<div class="serve">'+
		 '<h4>第二条 服务内容</h4>'+
		  '<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">2.1 本网站专注于为有理财需求的投资人（以下简称“投资人”）和有资金需求的融资人（以下简称“借款人”）搭建一个安全、透明、便捷的投融资网络交易平台，竭诚为注册用户提供投资咨询、财务规划、投资管理等咨询服务，以及借款人推荐、贷后信用管理等服务。您成功注册成为用户后，可通过您设置的用户名和密码登录本网站进行资金投资（出借）、提现、签订合同、交易记录及收益查阅、密码重置以及参加本网站的有关活动等，具体以用户登录本网站公布的服务内容为准。</p>'+
	 	  '<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">2.2 本网站就向您提供的服务是否收取服务费及服务费的具体标准和规则由本网站与您另行签署其他协议，以及本网站公布的规则确定。</p>'+
	 '</div>	'+
	  '<div class="serve">'+
		 '<h4>第三条 使用须知</h4>'+
		'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">3.1 您不得利用本网站或本网站服务从事任何不符合中国法律法规或侵犯他人权益的活动。本网站在发现您从事该等活动时，有权不经通知而立即停止您对本网站的全部或部分功能的使用。</p>'+
	    '<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">3.2 在使用本网站提供的任何服务的过程中，您不得发送、公布或展示任何垃圾、广告推销邮件、信息或其他可能违反中国法律法规及本协议的内容。本网站在发现您从事该等活动或发布该等内容时，有权不经您同意而直接删除该等内容，并有权不经通知而立即暂停或停止您对本网站的全部或部分功能的使用。</p>'+
	 	'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">3.3 您在注册时向本网站提交的手机号码、电子邮箱、用户名、密码及安全问题答案是您在本网站的唯一识别信息。您注册成功后应妥善保管，不得将注册的相关信息转让或授权给第三方使用，如果本网站发现同一账户和密码在同一时间内被多人同时登陆使用，本网站有权暂停、终止或限制该用户访问直至取消该用户的用户资格，并不予任何赔偿或者退还任何已收取的服务费用。同时，您确认，使用您的用户名和密码登录本网站后在本网站的一切行为均视为代表您本人意志并由您承担相应的法律后果。如您因忘记密码或密码被盗向本网站查询密码时，必须提供完全、正确的注册信息，否则本网站有权本着为用户保密的原则不予告知。</p>'+
	 	'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">3.4 您发现有他人冒用或盗用您的用户名及密码或任何其他未经合法授权之情形时，应立即以有效方式通知本网站，要求暂停相关服务。同时，您理解本网站对您的请求采取行动需要合理期限，而在本网站采取实际行动之前，您应对任何或所有由未经授权人士使用此服务或此服务被用作未经授权用途负责，本网站对已执行的指令及(或)所导致的您的损失不承担任何责任。</p>'+
	 	'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">3.5 您保证合法使用本网站提供的资讯或服务，遵守所有与本网站服务有关的协议、规则和程序。未经本网站事先书面授权，不使用任何非法手段擅自进入本网站的未公开系统。</p>'+
	 	'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;"></p>'+
	 '</div>	'+
	 '<div class="serve">'+
		 '<h4>第四条 用户信息的保护及披露</h4>'+
		'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">4.1 您同意本网站在业务运营中收集和储存您的用户信息，包括但不限于您自行提供的资料和信息，以及本网站自行收集、取得的您在本网站的交易记录和使用信息等。本网站收集和储存您的用户信息的目的在于提高为您提供服务的效率和质量。</p>'+
		'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">4.2 您同意本网站在业务运营中使用您的用户信息，包括但不限于(1)进行用户身份、信息核实；（2）出于提供服务的需要在本网站公示您的相关信息；(3)向本网站的合作机构（该合作机构仅限于本网站为了完成拟向您提供的服务而合作的机构）提供您的用户信息；(4)由人工或自动程序对您信息进行评估、分类、研究；(5)使用您的用户信息以改进本网站的推广；(6)使用您提供的联系方式与您联络并向您传递有关业务和管理方面的信息。（7）用于配合有权机关依职权调取证据材料。</p>'+
		
	'</div>'+
	'<div class="serve">'+
		 '<h4>第五条 用户承诺和保证</h4>'+
		 '<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">5.1 本网站用户应保证严格遵守中国现行法律、法规、政府规章及其他应该遵守的规范性文件，不在本网站从事危害国家安全、洗钱、套现、传销等任何违法活动或者其他有违社会公共利益或公共道德的行为。同时，本网站的投资人应保证所用于投资（出借）的资金来源合法，投资人是该资金的合法所有权人，如果因第三人对用于投资（出借）的资金的来源合法性或归属问题发生争议，由投资人负责解决并承担一切由此而导致的损失和责任。</p>'+
		'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">5.2 您确认在签署本协议之前已阅读包括但不限于以下与本协议及相关协议的订立及履行有关的风险提示，并对该等风险有充分理解和预期，您自愿承担该等风险可能给带来的一切责任和损失和责任。'+
		
		'（1）政策风险：国家因宏观政策、财政政策、货币政策、行业政策、地区发展政策等因素引起的系统风险。</br>（2）借款人及担保信用风险：当借款人及担保（如有）短期或者长期丧失还款能力（包括但不限于借款人收入情况、财产状况发生变化、人身出现意外、发生疾病、死亡等情况），或者借款人及担保（如有）的还款意愿发生变化时，您的出借资金及利息可能无法按时回收甚至无法回收。</br>(3)不可抗力：由于本网站及相关第三方的设备、系统故障或缺陷、病毒、黑客攻击、网络故障、网络中断、地震、台风、水灾、海啸、雷电、火灾、瘟疫、流行病、战争、恐怖主义、敌对行为、暴动、罢工、交通中断、停止供应主要服务、电力中断、经济形势严重恶化或其它类似事件导致的风险。'+
		
		'</p>'+
		
	'</div>'+
	 '<div class="serve">'+
		 '<h4>第六条 免责声明</h4>'+
		'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">6.1 除非另有书面协议约定，本网站在任何情况下，对用户使用本网站服务而产生的任何形式的直接或间接损失均不承担法律责任，包括但不限于资金损失、收益损失等。</p>'+
		'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">6.2 用户信息主要由用户自行提供或发布，本网站无法保证所有用户信息的真实、及时和完整，用户应对自己的判断承担全部责任。任何因为交易而产生的风险概由各交易方自行承担。</p>'+
     	'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">6.3 任何本网站之外的第三方机构或个人所提供的服务，其服务品质及内容由该第三方自行、独立负责。</p>'+
     	'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">6.4 因不可抗力或本网站服务器死机、网络故障、数据库故障、软件升级等问题造成的服务中断和对用户个人数据及资料造成的损失，本网站不承担任何责任，亦不予赔偿，但将尽力减少因此而给用户造成的损失和影响。</p>'+
     	'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">6.5 因黑客、病毒或密码被盗、泄露等非本网站原因所造成损失概由您本人自行承担。</p>'+
     	'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">6.6 您须对您本人在使用本网站所提供的服务时的一切行为、行动（不论是否故意）负全部责任。</p>'+
     	'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">6.7 当司法机关、政府部门或其他监管机构根据有关法律、法规、规章及其他政府规范性文件的规定和程序，要求本网站提供用户信息资料，本网站对据此作出的任何披露，概不承担责任。</p>'+

      '</div>'+
      ' <div class="serve">'+
		 '<h4>第七条 用户注销、暂停、终止或限制访问</h4>'+
		'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">7.1 如果您决定不再使用本网站服务时，应首先清偿所有应付款项（包括但不限于服务费、违约金、管理费等），再将您的用户账户下所对应的可用款项（如有）全部提现或者向本网站发出其它合法的支付指令，并向本网站申请注销（或永久冻结）该用户账户，经本网站审核同意后可正式注销您的用户账户。</p>'+
     	'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">7.2 本网站有权基于单方独立判断，在认为可能发生危害交易安全等情形时，不经通知而先行暂停、中断或终止向您提供本协议项下的全部或部分用户服务（包括收费服务），并将注册资料移除或删除，且无需对用户或任何第三方承担任何责任。</p>'+
     	'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">7.3 无论本网站是否收费，只要您利用本网站从事违法活动或者严重违反本协议的约定，本网站可在不发出任何通知的情况下立即使您的账户无效，或撤销您的账户以及在您的账户内的所有相关资料和档案，和/或禁止用户进一步接入该等档案或服务。账号终止后，本网站没有义务为您保留原账号中或与之相关的任何信息，或转发任何未曾阅读或发送的信息给您或第三方。此外，本网站亦不会就终止用户账户使用而对您或任何第三者承担任何责任。</p>'+
     	'<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">7.4 用户账户的暂停、中断或终止不代表用户责任的终止，用户仍应对其使用本网站提供服务期间的行为承担可能的违约或损害赔偿责任，同时本网站仍可保有用户的相关信息。</p>'+
      '</div>'+
      '<div class="serve">'+
		 '<h4>第八条 知识产权保护</h4>'+
		 '<p style="margin:0 0 0 10px; font-size:14px;">8.1 本网站所有内容和服务，包括但不限于网站的整体结构、网页设计、文字、图片、图表、软件、视频文件、音频文件、源代码、广告以及本网站为用户提供的其它信息或资料，其知识产权属本网站所有。未经本网站书面许可，任何人不得为了商业目的进行复制或者以其它方式进行非法使用。</p>'+
		 '<p style="margin:0 0 0 10px; font-size:14px;">8.2 任何未经授权许可而使用本网站内容的行为均属于违法行为, 本网站保留追究相关使用人法律责任的权利。</p>'+
      '</div>'+
       '<div class="serve">'+
		' <h4>第九条 赔偿</h4>'+
		  '<p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">9.1 用户因违反本协议或本协议项下的其他文件，或违反了任何法律、法规的规定，给本网站造成损失的，应予赔偿，赔偿范围包括但不限于本网站的直接损失、间接损失以及维权费用等。</p>	'+
		   '<p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">9.2 用户因违反前款规定而侵害了第三方的权利，导致本网站遭受任何第三方提起的索赔、诉讼或行政责任，用户承诺无条件承担相应责任并使本网站免责。若因此给本网站造成损失的，应予赔偿，赔偿范围包括但不限于本网站的维权费用、名誉损失以及本网站向第三方支付的补偿金等。</p>	'+
		   '<p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;"></p>	'+
      '</div>'+
      ' <div class="serve">'+
		 '<h4>第十条 保密条款</h4>'+
		 '<p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">10.1 对于本网站用户在接受本网站提供的小微金融服务过程中了解到的所有信息，包括但不限于本网站信息资料、业务运营情况、商业秘密等，不得向任何第三人披露。</p>'+
		 '<p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">10.2 除本协议另有约定外，本网站须根据中国法律的规定对本网站用户个人信息、资产情况和相关资料予以保密。</p>'+

      '</div>'+
       '<div class="serve">'+
		 '<h4>第十一条 税费缴纳</h4>'+
		 '<p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">11.1 本网站用户在资金投资（出借）、收益过程产生的相关税收缴纳义务，请根据中国法律的规定自行向其主管税务机关申报、缴纳，本网站不承担任何代扣代缴的义务及责任。</p>'+
		 '<p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;"></p>'+
      '</div>'+
      '<div class="serve">'+
		 '<h4>第十二条 信息变更</h2>'+
		 '<p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">12.1 本网站用户如变更账户信息、通讯地址、电话等相关重要信息，须及时通知本网站。因您未及时通知而导致自身受到的一切损失，由您自行承担责任。</p>'+
      '</div>'+
       '<div class="serve">'+
		 '<h4>第十三条 继承或赠与</h2>'+
		 '<p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">13.1 如果本网站用户出现投资（出借）资金的继承或赠与，必须由主张权利的继承人或受赠人向本网站出示经公证机关公证的继承或赠与权利归属证明文件，经本网站确认后方可予以协助办理资产权属变更手续，由此产生的相关税费，由主张权利的继承人或受赠人向其主管税务机关申报、缴纳，本网站不负责相关事宜处理。</p>'+
      '</div>'+
       '<div class="serve">'+
		 '<h4>第十四条 争议的处理</h2>'+
		 '<p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">14.1 本协议在履行过程中，如发生任何争执或纠纷，双方应友好协商解决，协商不成的，任何一方有权向合同签署地北京市朝阳区人民法院提起诉讼。</p>'+
      '</div>'+
       '<div class="serve">'+
		 '<h4>第十五条 其他</h2>'+
		 '<p style="margin:0 0 0 10px; font-size:14px;text-indent:4em;">15.1 本协议中的任何条款或部分条款因违反中国法律而无效的，不影响本协议其他条款的效力。</p>'+
      '</div>'+
	'</div>'