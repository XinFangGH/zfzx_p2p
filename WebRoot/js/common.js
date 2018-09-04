/* 控制全局头部登陆后的显示状态
 
 $(function(){ 
 	if($.cookie("login")==null||$.cookie("login")==""){
 	$.ajax({
			type : "POST",
			url : basepath + "checkUserIsLoginlogin.do",
			dataType : 'json',
			success : function(responseText, statusText) {
				
				if (responseText.success) {
					
					//登录成功以后 对 div 的隐藏  和显示
					//$("#exitLogin").remove();
					$.cookie("login",responseText.displayhtml)
					$("#login_div").html(""+responseText.displayhtml+"");
					//$("#login_div").css("padding-top","15px");
					
					$("#logindiv").remove();
				} 
			},
			error : function() {
				alert(error)
			}
		});
 	}else{
 	$("#login_div").html("<div>"+$.cookie("login")+"</div>");
 	}

}); 
*//**
 * 退出系统
 *//*
function exit(){
	//清空cookie
	$.cookie("login",null);
}*/

/**
 * 倒计时
 * 
 * @param {}
 *            intDiff
 */
function timer(intDiff) {
	window.setInterval(function() {
				var day = 0, hour = 0, minute = 0, second = 0;// 时间默认值
				if (intDiff > 0) {
					day = Math.floor(intDiff / (60 * 60 * 24));
					hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
					minute = Math.floor(intDiff / 60) - (day * 24 * 60)
							- (hour * 60);
					second = Math.floor(intDiff) - (day * 24 * 60 * 60)
							- (hour * 60 * 60) - (minute * 60);
				}
				if (minute <= 9)
					minute = '0' + minute;
				if (second <= 9)
					second = '0' + second;
				$('#day_show').html(day + "天");
				$('#hour_show').html('<s id="h"></s>' + hour + '时');
				$('#minute_show').html('<s></s>' + minute + '分');
				$('#second_show').html('<s></s>' + second + '秒');
				intDiff--;
			}, 1000);
}

function timer1(intDiff) {
	window.setInterval(function() {
				var day = 0, hour = 0, minute = 0, second = 0;// 时间默认值
				if (intDiff > 0) {
					day = Math.floor(intDiff / (60 * 60 * 24));
					hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
					minute = Math.floor(intDiff / 60) - (day * 24 * 60)
							- (hour * 60);
					second = Math.floor(intDiff) - (day * 24 * 60 * 60)
							- (hour * 60 * 60) - (minute * 60);
				}
				if (minute <= 9)
					minute = '0' + minute;
				if (second <= 9)
					second = '0' + second;
				$('#day_show1').html(day + "天");
				$('#hour_show1').html('<s id="h"></s>' + hour + '时');
				$('#minute_show1').html('<s></s>' + minute + '分');
				$('#second_show1').html('<s></s>' + second + '秒');
				intDiff--;
			}, 1000);
}
function timer2(intDiff) {
	window.setInterval(function() {
				var day = 0, hour = 0, minute = 0, second = 0;// 时间默认值
				if (intDiff > 0) {
					day = Math.floor(intDiff / (60 * 60 * 24));
					hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
					minute = Math.floor(intDiff / 60) - (day * 24 * 60)
							- (hour * 60);
					second = Math.floor(intDiff) - (day * 24 * 60 * 60)
							- (hour * 60 * 60) - (minute * 60);
				}
				if (minute <= 9)
					minute = '0' + minute;
				if (second <= 9)
					second = '0' + second;
				$('#day_show2').html(day + "天");
				$('#hour_show2').html('<s id="h"></s>' + hour + '时');
				$('#minute_show2').html('<s></s>' + minute + '分');
				$('#second_show2').html('<s></s>' + second + '秒');
				intDiff--;
			}, 1000);
}
/**
 * 内页左侧导航菜栏
 */
function innerNav() {

	// $('#innerNav').on('click', 'li', function() {
	// var $curr = $('<em class="curr">&nbsp;</a>');
	// $(this).append($curr);
	// alert(0);

	// }).on('click', 'li', function() {
	// $(this).find('em').remove();
	// });

}

// 增加Cookie
function addCookie(name, value, options) {

	if (arguments.length > 1 && name != null) {
		if (options == null) {
			options = {};
		}
		if (value == null) {
			options.expires = -1;
		}
		if (typeof options.expires == "number") {
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		document.cookie = encodeURIComponent(String(name))
				+ "="
				+ encodeURIComponent(String(value))
				+ (options.expires ? "; expires="
						+ options.expires.toUTCString() : "")
				+ (options.path ? "; path=" + options.path : "")
				+ (options.domain ? "; domain=" + options.domain : ""), (options.secure
				? "; secure"
				: "");
	}
}

// 获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name))
				+ "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// 删除Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}

// 设置为主页
function SetHome(obj, vrl) {
	try {
		obj.style.behavior = 'url(#default#homepage)';
		obj.setHomePage(vrl);
	} catch (e) {
		if (window.netscape) {
			try {
				netscape.security.PrivilegeManager
						.enablePrivilege("UniversalXPConnect");
			} catch (e) {
				alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
			}
			var prefs = Components.classes['@mozilla.org/preferences-service;1']
					.getService(Components.interfaces.nsIPrefBranch);
			prefs.setCharPref('browser.startup.homepage', vrl);
		} else {
			alert("浏览器不支持，请按照下面步骤操作：1.打开浏览器设置。2.点击设置网页。3.输入：" + vrl + "点击确定。");
		}
	}
}
// 收益计算器弹出
function IncomeBox() {
	$('a[rel*=leanModalIncome]').leanModal({
				top : 200
			});
	$("<div id=\"signupInterest\">"
			+ "<div id=\"signup-ct\">"
			+ "<div id=\"signup-header\">"
			+ "<div style=\"float:left; width:330px; height:40px; padding:10px 0px 0px 30px\">"
			+ "<span  class=\"large blue\">收益计算器</span>"
			+ "</div>"
			+ "<div id=\"lean_overlay_close_Income\" style=\"float:right;  padding:20px 0px 0px 0px;width:60px; height:20px; text-decoration:underline; cursor:pointer;\"  class=\"normal blue\">关闭</div>"
			+ "</div>"
			+ "<div style=\"overflow:hidden;\">"
			+ "<div style=\"float:left; width:350px; height:200px; margin:30px 0px 0px 80px;\">"
			+ "<ul>"
			+ "<li><span class=\"big black\">投资金额：</span> <input class=\"colorful\" id=\"money_mth\" class=\"good_input\"  type=\"text\" />元</li>"
			+ "<li> <span class=\"big black\">年化收益：</span> <input class=\"colorful\" id=\"intrest_mth\" class=\"good_input\" type=\"text\" />%</li> "
			+ "<li> <span class=\"big black\">计息时长：</span> <input class=\"colorful\" id=\"time_mth\" class=\"good_input\"  type=\"text\" />天</li> "
			+ "<li> <span class=\"big black\">预计收益：</span> <span class=\"big black\" id=\"shouyi_mth\">0</span>"
			+ "</ul>"
			+ "</div></div><div class=\"dashedline\"></div></div><div style='width:100px; height:30px; margin-top:20px; padding:0px 0px 0px 400px;'><input class=\"buttonorange button\" type=\"submit\" onClick=\"methIncome()\"  value=\"&nbsp;&nbsp;&nbsp;计算 &nbsp;&nbsp;\" style=\"height:30px;\"></input></div></div>")
			.appendTo("body")
}

function Caculator(){
	$('a[rel*=leanModalIncome]').leanModal({
		top : 200
	});	
}


// 计算收益
function methIncome() {
	var money = $("#money_mth").val();
	var intrestY = $("#intrest_mth").val();
	var time = $("#time_mth").val();
	var intrestD = intrestY / 365 / 100;
	var shouyi = (money * intrestD * time);
	$("#shouyi_mth").empty();
	$("#shouyi_mth").append(shouyi.toFixed(2));
}

// 加入收藏
function toFavorites(sTitle, sURL) {
	try {
		window.external.addFavorite(sURL, sTitle);
	} catch (e) {
		try {
			window.sidebar.addPanel(sTitle, sURL, "");
		} catch (e) {
			alert("加入收藏失败，请使用Ctrl+D进行添加");
		}
	}
}

// 选择日期
// $( "#from" ).datepicker({
// defaultDate: "+1w",
// dateFormat: "yy-mm-dd",
// onClose: function( selectedDate ) {
// $( "#to" ).datepicker( "option", "minDate", selectedDate );
// }
// });
// $( "#to" ).datepicker({
// defaultDate: "+1w",
// dateFormat: "yy-mm-dd",
// onClose: function( selectedDate ) {
// $( "#from" ).datepicker( "option", "maxDate", selectedDate );
// }
// });

/*
 * 提示信息 Modified by Lianjun Date: 2013.11.20
 */
function showTip(id, tip, offsetX, offsetY) {
	$('.tip-wrap').remove();
	var html = $("<div class=\"tip-wrap\" style=\"position:absolute;\">"
			+ "<div class=\"tip-cont\"></div>"
			+ "<span class=\"inner\"></span>" + "<span class=\"outer\"></span>"
			+ "</div>");
	var container = $("#" + id), after = null;

	if (container.length > 0) {
		after = container;
	} else {
		after = $("#" + id);
	}

	var xy = after.position(), x = xy.left -15, y = xy.top - 55;
	// 由于算出的提示位置不太准确(以后有时间需计算出精确的位置),加上两个x,y 轴的偏移量 add by wenjl 2013-10-18
	if (offsetX) {
		x = x + offsetX;
	}
	if (offsetY) {
		y = y + offsetY;
	}

	html.css({
				left : x,
				top : y
			}).insertAfter(after).find(".tip-cont").html(tip);

	container.focusin(function() {
				html.remove();
			}).click(function() {
				html.remove();
			});
}

// 通用提示信息
function showCommonTip(selector, tip, type) {
	if (type != 'info') {
		$(selector).removeClass('information').addClass('error');
	} else {
		$(selector).removeClass('error').addClass('information');
	}
	$(selector).html(tip);
	$(selector).show();
	$(selector).parents("form").find("input[type!='button']").each(
			function(k, v) {
				$(v).focus(function() {
							$(selector).hide();
						});
				$(v).click(function() {
							$(selector).hide();
						});
			});
}

// 显示上传提示
function showUploadTip(id, msg, domainName) {
	if (undefined == domainName) {
		domainName = domainNameHttp;
	}

	var tip = "<div class=\"uploadifyQueueItem\">"
			+ "<div class=\"cancel\">"
			+ "<a href=\"javascript:void(0);\" onclick=\"$(this).parents('.uploadifyQueueItem').remove();\" "
			+ "style=\"display:block;width:16px;height:16px;background:url("
			+ domainName + "/man/js/uploadify/cancel.png)\"></a>" + "</div>"
			+ "<span class='font-red'>" + msg + "</span>" + "</div>";

	$("#" + id + "Queue").html(tip);
}

// 清除上传提示
function clearUploadTip(id) {
	$("#" + id + "Queue").empty();
}


/*
 * @ 取得字符串的真实长度 英文为一个字符,中文为两个字符 @ str目标字符串 @ 返回 str 的长度
 */
function getStringLength(str) {
	var len = 0;
	if (str.length <= 0) {
		len = 0;
	} else {
		for (var i = 0; i < str.length; i++) {
			if (str.charCodeAt(i) < 256) {
				len = len + 1;
			} else {
				len = len + 2;
			}
		}
	}
	return len;
}

function substr(str, len) {
	if (!str || !len) {
		return '';
	}

	// 预期计数：中文2字节，英文1字节
	var a = 0;

	// 循环计数
	var i = 0;

	// 临时字串
	var temp = '';

	for (i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) > 255) {
			// 按照预期计数增加2
			a += 2;
		} else {
			a++;
		}
		// 如果增加计数后长度大于限定长度，就直接返回临时字符串
		if (a > len) {
			return temp;
		}

		// 将当前内容加到临时字符串
		temp += str.charAt(i);
	}
	// 如果全部是单字节字符，就直接返回源字符串
	return str;
}

/* 数字转换成中文大写 */
function Chinese(num) {
	if (!/^\d*(\.\d*)?$/.test(num))
		throw (new Error(-1, "Number is wrong!"));
	var AA = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖");
	var BB = new Array("", "拾", "佰", "仟", "萬", "億", "圆", "");
	var CC = new Array("角", "分", "厘");
	var a = ("" + num).replace(/(^0*)/g, "").split("."), k = 0, re = "";
	for (var i = a[0].length - 1; i >= 0; i--) {
		switch (k) {
			case 0 :
				re = BB[7] + re;
				break;
			case 4 :
				if (!new RegExp("0{4}\\d{" + (a[0].length - i - 1) + "}$")
						.test(a[0]))
					re = BB[4] + re;
				break;
			case 8 :
				re = BB[5] + re;
				BB[7] = BB[5];
				k = 0;
				break;
		}
		if (k % 4 == 2 && a[0].charAt(i) == "0" && a[0].charAt(i + 2) != "0")
			re = AA[0] + re;
		if (a[0].charAt(i) != 0)
			re = AA[a[0].charAt(i)] + BB[k % 4] + re;
		k++;
	}
	if (a.length > 1) {
		re += BB[6];
		for (var i = 0; i < a[1].length; i++) {
			re += AA[a[1].charAt(i)] + CC[i];
			if (i == 2)
				break;
		}
		if (a[1].charAt(0) == "0" && a[1].charAt(1) == "0") {
			re += "元整";
		}
	} else {
		re += "元整";
	}
	return re;
}

// 格式话金额
// 将数字转换成逗号分隔的样式,保留两位小数s:value,n:小数位数
function fmoney(s, n) {
	var n = n > 0 && n <= 20 ? n : 2;
	var s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse();
	var r = s.split(".")[1];
	var t = "";
	for (var i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}
// 还原金额
function rmoney(s) {
	return parseFloat(s.replace(/[^\d\.-]/g, ""));
}

/**
 * 获取验证短信 getVerifySmsId 获取短信验证码的按钮ID mobileId 手机号输入框ID verifySmsId 短信验证码输入框ID
 * seconds 倒计时 type 短信类型
 */
function getVerifySms(getVerifySmsId, mobileId, verifySmsId, seconds, type) {
	var temp = seconds;
		var getVerifySmsObj = $("#" + getVerifySmsId);
		var token = null;
		// 该按钮没有禁用
		if (!getVerifySmsObj.is(".disabled")) {
			if (type == "update_trade_password"
					|| type == "forget_trade_password") {
				var mobile = $("#" + mobileId).text();
				/*
				 * if (isEmpty(mobile)) { showCommonTip(".common-tip",
				 * "您还未绑定手机"); return false; }
				 */
			} else {
				// 校验手机号
				if($("#token").length>0){
					token = $("#token").val();
				}
				
				if (isEmpty($("#" + mobileId).val())) {
					showTip(mobileId, "手机号码不能为空");
					return false;
				}

				if (!isMobile($("#" + mobileId).val())) {
					showTip(mobileId, "手机号码格式不正确");
					return false;
				}

			}

			// 禁用获取短信验证码按钮
			getVerifySmsObj.addClass("disabled");
			$("#" + verifySmsId).removeAttr("readonly");

			var text = getVerifySmsObj.html();
			//每次进入先清空
			getVerifySmsObj.html(text);
			
			

			/*var interval = window.setInterval(function() {
						seconds--;

						if (seconds == 0) {
							seconds = temp;
							window.clearInterval(interval);
							getVerifySmsObj.removeClass("disabled");
							// $("#" + verifySmsId).attr("readonly",
							// "readonly");
							getVerifySmsObj.html(text.indexOf("重新") == -1
									? "重新" + text
									: text);
						} else {
							getVerifySmsObj.html(text + "(" + seconds + ")");
						}

					}, 1000);*/

			//getVerifySmsObj.html(text + "(" + seconds + ")")
			var url = basepath + "codecreate.do?sms_code_type=" + type
					+ "&telphone=" + $("#" + mobileId).val() + '&randomCode='
					+ (Math.random())+"&token="+token;
			/*
			 * if (type == "update_trade_password" || type ==
			 * "forget_trade_password") { url =
			 * "/member/sendVerifySmsNoMobile.html?sms_code_type=" + type +
			 * '&randomCode=' + (Math.random()); }
			 */

			$.ajax({
						url : url,
						dataType : "json",
						success : function(result) {
							if (result.status == "200") {
									ymPrompt.succeedInfo({title:'系统提示信息',message:'验证码已成功发送，请注意查收！',width:280,height:150,handler:function(btn){
										//showCommonTip(".common-tip","短信验证码已经发送成功，请注意查收", "info");
										
										var interval = window.setInterval(function() {
											seconds--;
					
											if (seconds == 0) {
												seconds = temp;
												window.clearInterval(interval);
												getVerifySmsObj.removeClass("disabled");
												// $("#" + verifySmsId).attr("readonly",
												// "readonly");
												getVerifySmsObj.html(text.indexOf("重新") == -1
														? "重新" + text
														: text);
											} else {
												getVerifySmsObj.html(text + "(" + seconds + ")");
											}
					
										}, 1000);
									}});
								} else {
								showTip(mobileId, result.remark);
								//showCommonTip(".common-tip", result.remark);
								getVerifySmsObj.removeClass("disabled");
								// $("#" + verifySmsId).attr("readonly",
								// "readonly");
								getVerifySmsObj.html(text.indexOf("重新") == -1
										? "重新" + text
										: text);
								};
						},
						error : function() {
							// showCommonTip(".common-tip","短信验证码获取失败，请重新获取");
						}
					});

	};
}
/**
 * 倒计时 跳转页面
 */
function timeBank(obj ,seconds,retURL){
		var interval = window.setInterval(function() {
						seconds--;
						if (seconds == 0) {
							$("#"+obj).html(0);
							window.clearInterval(interval);
							location.href = retURL;
						} else {
							$("#"+obj).html(seconds);
						}
					}, 1000);
}

/**
 * 获取动态码
 * 
 * @author wenjl
 */
function getAdCodeUrl() {
	$.ajax({
				dataType : "jsonp",
				jsonp : "callback",
				async : false,
				cache : false,
				url : domainNameHttps + "/member/getAdCodeUrl.html",
				success : function(data) {
					$("#adCodeUrl").attr("src", domainNameHttps + data);
				}
			});
}

/**
 * 按钮触击变灰
 * 
 * @param id
 * @author wenjl
 */
function activeGray(id) {
	$("#" + id).attr('disabled', 'true').addClass("act");
}

/**
 * 删除按钮灰色
 * 
 * @param id
 */
function removeGray(id) {
	$("#" + id).removeAttr('disabled').removeClass("act");
}

	
function refresh(obj) {
		var path = $("#path").val();
        obj.src = basepath+"getCode.do?"+Math.random();
        $("#spancheckCode").css("background","");
 }
 
 
 /**
 * 验证验证码
 * @param {} obj
 * @return {Boolean}
 */
function validatCheckCode(obj){
	$("#spancheckCode").html("");
		document.getElementById("spancheckCode").style.background = "";
 	if ($("#checkCode").val().length ==4 ) {
		var checkCode =  $("#checkCode").val();
			$.ajax({	                                             
			type: "POST", 
			dataType: "JSON",
			url: basepath+"user/isRightCheckCodeBpCustMember.do?checkCode="+checkCode,
			cache: false,
			success: function(responseText, statusText) {
				if(responseText.result==0||responseText.result==1){
					if(responseText.result==1){
						$("#spancheckCode_img").text("1");
						$("#errorMsg").text("");
						$("#spancheckCode").empty().append("<img src='"+themepath+"images/erricon.jpg'>");
					}else{
						$("#spancheckCode_img").text("0");
						$("#errorMsg").text("");
						$("#spancheckCode").empty().append("<img src='"+themepath+"images/icon.JPG'>");
					}
				}else{
					$("#spancheckCode_img").text("1");
					$("#spancheckCode").empty().append("<img src='"+themepath+"images/erricon.jpg'>");	
				}
			},
			error: function(request) {
			}
		});
 	}else{
 		$("#spancheckCode_img").text("1");
        $("#spancheckCode").empty().append("<img src='"+themepath+"images/erricon.jpg'>");
       /* $("#spancheckCode_img").html("图形验证码错误");
        $("#spancheckCode_img").css({"display":"block","height":"30px","line-height":"30px","color":"red","margin-top":"10px"});*/
        /*return false;*/
 	}
}




function newsCheckCode(obj){
 	if ($("#checkCode").val().length ==4 ) {
		var checkCode =  $("#checkCode").val();
			$.ajax({	                                             
			type: "POST", 
			dataType: "JSON",
			url: basepath+"user/isRightCheckCodeBpCustMember.do?checkCode="+checkCode,
			cache: false,
			success: function(responseText, statusText) {
				if(responseText.result==0){
					$("#errorCode").css("display","none");
					$("#successCode").css("display","block");
				}else{
					$("#successCode").css("display","none");
					$("#errorCode").css("display","block");
				}
			}
		});
 	}
}
 /**
 * 手机验证码
 * @param {} obj
 * @return {Boolean}
 */
function phoneCheckCode(obj){
	
	$("#sp_phonecheckCode").html("");
		document.getElementById("sp_phonecheckCode").style.background = "";
 	if ($("#verify_sms").val().length ==6 ) {
		var checkCode =  $("#verify_sms").val();
			$.ajax({	                                             
			type: "POST", 
			dataType: "JSON",
			url: basepath+"user/checkCodeBpCustMember.do?checkCode="+checkCode,
			cache: false,
			success: function(responseText, statusText) {
				if(responseText.success){
					$("#sp_phonecheckCode").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				document.getElementById("sp_phonecheckCode").style.background = "url("+themepath+"images/icon.JPG) no-repeat";
				}else{
					$("#sp_phonecheckCode").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				document.getElementById("sp_phonecheckCode").style.background = "url("+themepath+"images/erricon.jpg) no-repeat";
				}
			},
			error: function(request) {
			}
		});
 	}else{
 	$("#sp_phonecheckCode").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				document.getElementById("sp_phonecheckCode").style.background = "url("+themepath+"images/erricon.jpg) no-repeat";
 	}
}
/**
 * 合作伙伴图标水平不间断滚动
 */
function scrollPartner() {
	var $cur = 1; // 初始化显示的版面
	var $i = 5; // 每版显示数
	var $len = $('.showbox > ul > li').length; // 计算列表总长度(个数)
	var $pages = Math.ceil($len / $i); // 计算展示版面数量
	var $w = $('.ibox').width(); // 取得展示区外围宽度
	var $showbox = $('.showbox');
	var $prev = $('a.prev');
	var $next = $('a.next');
	// 向前滚动
	$prev.click(function() {
				if (!$showbox.is(':animated')) { // 判断展示区是否动画
					if ($cur == 1) { // 在第一个版面时,再向前滚动到最后一个版面
						$showbox.animate({
									left : '-=' + $w * ($pages - 1)
								}, 500); // 改变left值,切换显示版面,500(ms)为滚动时间,下同
						$cur = $pages; // 初始化版面为最后一个版面
					} else {
						$showbox.animate({
									left : '+=' + $w
								}, 500); // 改变left值,切换显示版面
						$cur--; // 版面累减
					}
				}
			});
	// 向后滚动
	$next.click(function() {
				if (!$showbox.is(':animated')) { // 判断展示区是否动画
					if ($cur == $pages) { // 在最后一个版面时,再向后滚动到第一个版面
						$showbox.animate({
									left : 0
								}, 500); // 改变left值,切换显示版面,500(ms)为滚动时间,下同
						$cur = 1; // 初始化版面为第一个版面
					} else {
						$showbox.animate({
									left : '-=' + $w
								}, 500); // 改变left值,切换显示版面
						$cur++; // 版面数累加
					}
				}
			});
}

/* 广告图切换 */
function changeBannerSimple() {
	$('.banner').each(function() {
				var timer; // 定时器
				var SPEED = 3000; // 切换速度
				var $this = $(this);

				// 点击小圆点
				$('.clickbtn a').click(function() {
							var index = $(this).index();
							changeImg(index);
						}).eq(0).click();

				// 循环播放
				function play() {
					var j = $('.clickbtn a').length;
					var i = $('.clickbtn a.clicked').index();
					if (i >= j - 1) {
						i = 0;
					} else {
						i++;
					}
					changeImg(i);
				}

				// 鼠标滑入，停止切换，鼠标滑出，继续切换
				$this.mouseenter(function() {
							clearInterval(timer);
						}).mouseleave(function() {
							timer = setInterval(play, SPEED);
						});

				// 切换图片，动态改变大图的src
				function changeImg(index) {
					
					var $img = $('.slide img');
					var $btn = $('.clickbtn a');
					
					//$img.hide();
					// 动态切换图片背景色
					if($img.eq(index).attr('c')!="undefined"){
					$('.slide').css("background",$img.eq(index).attr('c'));
					}

					$('.slide img.current').hide();
					$('.slide img.current').removeClass('current');
					$img.eq(index).fadeIn();
					$img.eq(index).fadeIn("slow"); //
					$img.eq(index).fadeIn(3000);

					$img.eq(index).addClass('current');
					$btn.removeClass('clicked').eq(index).addClass('clicked');
					return;
				}
				timer = setInterval(play, SPEED);
			});
}


function fromBidSubmit(bidId,url){
	document.write("<form action='"+url+"' method='post' name='formx1' style='display:none'>");
	document.write("<input type='hidden' name='bidId' value='"+bidId+"'/>");
	document.write("</form>");
	document.formx1.submit();
	return false;
}
function formMoneyPlanSubmit(moneyPlanId,url){
	document.write("<form action='"+url+"' method='post' name='formx2' style='display:none'>");
	document.write("<input type='hidden' name='mmplanId' value='"+moneyPlanId+"'/>");
	document.write("</form>");
	document.formx2.submit();
	return false;
}


 /**
 * 封装基本ajax请求的方法
 * @param {} _url
 * @param {} _map
 * @param {} _callBack
 * @param {} _errorCallBack
 * @arguments _url,_map, _callBack, _errorCallBack
 */
function ajaxFunction(_url, _map, _callBack, _errorCallBack) {
	
	var argNum = arguments.length;
	if (argNum == 0) {
		alert("ajax调用异常！");
		return;
	}
	var _data_type = _map.dataType && _map.dataType != null && _map.dataType != "" ? _map.dataType : "JSON";
	var _request_type = _map.type && _map.type != null && _map.type != "" ? _map.type : "POST";
	$.ajax({
		url : _url,
		dataType : _data_type,
        type : _request_type,
		cache : false,
		asyns : false,
		data : _map,
		error : argNum >= 4 ? _errorCallBack : ajaxErrorFunction,
		success : argNum >= 3 ? _callBack : function() {
		}
	});
}
/**
 * ajax错误请求的后做的处理
 * @param {} XMLHttpRequest
 * @param {} textStatus
 * @param {} errorThrown
 */
function ajaxErrorFunction(XMLHttpRequest, textStatus, errorThrown) {
	var text = XMLHttpRequest.responseText;
	if (text && text != "") {
		//alert(text);
		return;
	}
}
 
function showMeg(){
	window.location.href=basepath+"message/getUserAllOaNewsMessage.do"
	
}
$(document).ready(function () {
    setInterval("reFreshMeg()",1800*1000);
 });

function reFreshMeg(){
	 $.ajax({
		type:"POST",
       	url:basepath+"message/refMegOaNewsMessage.do",
       	data:"",
       	dataType:"String",
       	success:function(data){}	
     });
}

