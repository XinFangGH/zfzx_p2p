var width = 100,
	stateflag = false,
	style = '<span style="font-size:13px;margin-left:10px">此标可使用优惠券</span>',
	datamax,
	formtoken,
	money,
	couponId,
	random,
	limitMoney,
	curUserInfoo,
	isIndex,
	isInvestment,
	isMain,
	maxCouponMoney,
	AccountBalance;
Ext.define('hrmobile.public.myhome.planbid.buyplanbid', {
	extend : 'Ext.Container',
	name : 'buyplanbid',
	constructor : function(config) {
		config = config || {};
		// 判断是否首页点击进来
		isIndex = !(config.isIndex) ? false : true;
		
		// 判断是否列表页点击进来
		isInvestment = !(config.isInvestment) ? false : true;
		
		// 判断是否账户页点击进来
		isMain = !(config.isMain) ? false : true;
		
		// 获取上个页面的投资详情数据
		datamax = config.data;
		
		// 获取formtoken参数
		formtoken = config.formtoken;
		
		// 获取随机数
		random = config.random;
		
		// 设置优惠券id默认为空
		couponId = '';
		
		// 最大面值
		maxCouponMoney = config.maxCouponMoney || 0;
		
		// 判断是否可使用优惠券
		if (datamax.coupon == 1) {
			style = '<span style="font-size:13px;margin-left:10px">此标可使用优惠券</span>';
			stateflag = false;
		} else {
			style = '<span style="font-size:13px;margin-left:10px">此标不可使用优惠券</span>';
			stateflag = true;
		}
		
		// 获取上限金额
		limitMoney = config.limitMoney;
		
		
		Ext.apply(config, {
			title : '<font color=#ffffff style="font-size:17px">投标详情</font>',
			width : "100%",
			height : "100%",
			style:"margin:42px 0",
			listPaging : true,
			scrollable : {
				/*direction : 'vertical'*/
				direction : false
			},
	
			items : [{
				xtype : 'panel',
				defaults : {
					xtype : 'textfield',
					labelWidth : "100px"
				},
				items : [{
					xtype : "label",
					html : "<div style='height:10px;background-color:#f3f3f3'></div>"

				},

						// 可用余额和充值按钮
						{
							xtype : 'panel',
							layout : {
								type : 'hbox',
								align : 'middle'
							},
							items : [{
								width : "70%",
								label : "可用余额:",
								style : "margin-left:10px;",
								labelWidth : "40%",
								html : "<div style='margin-left:15px;float:left;'>可用余额</div>" +
										"<font id='zhanghu' style='position: relative; left: 50%; margin-left: -4rem;color:" + 
										btnBg +
										";'></font>"
							}, {
								style : "background:#fff;border:1px solid " + 
										btnBg +
										";color: " + 
										btnBg +
										";margin: 5px;font-size:13px",
								text : "充值",
								width : "60px",
								height : "28px",
								xtype : 'button',
								handler : this.recharge
							}]
						}, {
							xtype : "label",
							html : "<div style='height:10px;background-color: #f3f3f3'></div>"

						},

						// 输入投资金额
						{
							xtype : 'textfield',
							requiredCls : "displayflex",
							name : "buymoney",
							placeHolder : "起投金额" + datamax.startMoney + "元",
							style : 'font-size:1em;margin:30px 30px 0px 30px;border-bottom: 1px solid #eee;'
						}
						//						{xtype: 'panel',layout: {type: 'hbox',align: 'middle'},
						//						items: [Ext.create('Ext.field.Spinner', {
						//											width:"73%",
						//										    label     : '<span style="font-size:13px;margin-left: 15px;float:left;">投入金额:</span>',
						//										    labelWidth:"40%",
						//										    id:"buymoney",
						//										    minValue  : 0,
						//										    maxValue  : 10000,
						//										    stepValue : 100,
						//										    cycle     : true 
						//										}),{
						//				                    	style:"background:#fff;border:1px solid #e62129;color: #e62129;font-size:13px;",
						//				                    	text:"全投",
						//				                    	width:"60px",
						//				                    	height:"28px",
						//				                    	xtype: 'button',
						//				                    	handler:this.buy
						//				                    }]
						//		                    }
						,

						// 可投金额 和 起始金额 	
						{
							xtype : "label",
							html : "<div style='position: relative;height:4px;background:#e8e6e6;margin:15px auto;width: 90%;'>"
									+ "<div style='display:inline-block;width:"
									+ width
									+ "%; height: 4px;position: absolute;top: 0px;left: 0px; background:" + 
										'white' +
//										btnBg +
										";'></div>"
									+ "</div>"
									+ '<div style="width: 100%; margin: 15px auto; padding: 0 0 0 19px;">'
									+ '<span>剩余可投金额</span>'
									+ '<span style="margin:10px;">￥'
/*									+ '<span style="margin:15px; color:'
									+ btnBg
									+ ';">￥'*/
									+ datamax.afterMoney
									+ '</span>'
									+ '<div style="float: right;">'
									+ '<span>上限金额</span>'
									+ '<span style="margin:10px;">￥'
/*									+ '<span style="margin:15px; color:'
									+ btnBg
									+ ';">￥'*/
									+ datamax.maxMoney
//									+ (limitMoney == null ? '0.00' : limitMoney)
									+ '</span>' 
									+ '</div>' 
									+ '</div>'

						}, {
							xtype : "label",
							html : "<div style='height:10px;background-color: #f3f3f3'></div>"

						},

						// 选择优惠券
						{
							labelWidth : '45%',
							xtype : 'textfield',
							id : 'slSmallloanProjectappUserName',
							value : curUserInfo.fullname,
							name : 'slSmallloanProject.appUserName',
							label : style,
							readOnly : true,
							disabled : stateflag,
							handler : this.conpunlist1,
							placeHolder : stateflag == false ? '> 点此选择' : '> 点此选择(当前不可点击)',
							listeners : {
								scope : this,
								'focus' : function(f) {
									mobileNavi.push(Ext.create('hrmobile.public.myhome.coupon.newconpunlist',
										{
											title : "<font color=" + topColor + " style='font-size:" + topsize + "'>选择优惠券</font>",
											rebateType : datamax.rebateType,
											maxCouponMoney: maxCouponMoney,
											callback : function(datamax) {
												var appUserName = Ext.getCmp("slSmallloanProjectappUserName");
												var appUserId = Ext.getCmp("slSmallloanProjectappUserId");
												
												// 返回文本
												var text = '';
												
												// 判断优惠券类型
												if (datamax.couponType == 1) {
													text = '特权金';
													
													// 设置选择优惠券显示文本
													appUserName.setValue(datamax.couponValue + "元优惠券");
												} else if (datamax.couponType == 2) {
													text = '体验券';
													
													// 设置选择优惠券显示文本
													appUserName.setValue(datamax.couponValue + "元优惠券");
												} else if (datamax.couponType == 3) {
													text = '加息券';
													
													// 设置选择优惠券显示文本
													appUserName.setValue(datamax.couponValue + "%" + text);
												}
												
												// 判断优惠券id是否为空
												couponId = datamax.couponId ? datamax.couponId : '';
												
											}
										}
									));
								}
							}
						}, {
							xtype : "label",
							html : "<div style='height:10px;background-color: "
									+ topColor + "'></div>"

						}

				]
			},
			
			
			
				//	                    	xtype:"checkboxfield",
				//	                    	style:"margin:0px 20px 0px 20px",
				//	                        html:'<div style="font-size:12px;margin:20px 20px 20px 0px;text-align:center;">我已阅读并同意<span style="color:coral;" onclick="javascript:overlays();">《平台服务协议》</span></div>'
			// 我已阅读并同意
			/*{
				xtype : 'checkboxfield',
				style : 'position: fixed;width:100%;height: 117px',
				id : 'agree1',
				checked : true,
				handler : this.agreement,
				html : "<div style='text-align:center;font-size: 13px;position: absolute; top:17px;margin-left: 25%;'>"
						+ "<label>我已阅读并同意《<span  onclick='javascript:overlays();' style='color:red;'>平台服务协议</span>》</label>"
						+ "</div>"

			},*/

			// 立即投资
			{
				style : "margin: 55px 10px;height:44px;background:"
						+ themeColor + ";font-color:white",
				xtype : 'button',
				id : 'btnClick',
				text : "<font color=white>立即投资</font>",
				handler : this.buy
			}],
			listeners : {
				// 显示事件
				show : function() {
								Ext.Ajax.request({
					               url: __ctxPath+'/user/mobileaccountBpCustMember.do',
					                  params: {
									    isMobile : "1"
							        },
					               success : function(response) {
					            		    var responseText = Ext.util.JSON.decode(response.responseText);
					            		    var money=responseText.money;//账户可用金额
					            		    var money1=responseText.money1;//冻结资金总额
					            		    var money3=responseText.money3;//待收投资总额第一部分：散标投资的本金和利息   
					            		    var money4=responseText.money4;//第二部分：理财计划的本金和利息
					            		    var money5=responseText.money5;//待还借款总额
					            		    var money6=responseText.money6;//累计投资总额
					            		    var money7=responseText.money7;//累计投资总额
					            		    var money8=responseText.money8;//预期待收收益
					            		    var money9=responseText.money9;//预期待收收益
					            		    var money10=responseText.money10;//上月到账收益
					            		    var money11=responseText.money11;//累计回收本金
					            		    var money12=responseText.money12;//累计到账收益
					                        var count=responseText.count;//还款中借款笔数
					                        var count1=responseText.count1;//招标中借款笔数
					                        var count2=responseText.count2;//待回款投资笔数
					                        var count3=responseText.count3;//待回款投资笔数
					                        var count4=responseText.count4;//投标冻结中笔数
					                        var count5=responseText.count5;//投标冻结中笔数
					            		    money3=money3+money4;
					            		    money6=money6+money7;
					            		    money8=money8+money9;
					                        count2=count2+count3;
					                        count4=count4+count5;
					                       var moneyall=money+money3+money1-money5;
					                       
					                       // 将账户余额赋给全局变量
					                       AccountBalance = money;
					                       
					                       // 账号余额
					                        $('#zhanghu').html(moneyFormat(money) + "元");
					                       
					//                        document.getElementById("count").innerHTML =count;
					//                        document.getElementById("count1").innerHTML =count1;
					//                        document.getElementById("count2").innerHTML =count2;
					//                        document.getElementById("count4").innerHTML =count4;
					                        
					//                        document.getElementById("money1").innerHTML =moneyFormat(money1);
					//                        document.getElementById("money3").innerHTML =moneyFormat(money3);
					//                        document.getElementById("money5").innerHTML =moneyFormat(money5);
					//                        document.getElementById("money6").innerHTML =moneyFormat(money6);
					//                        document.getElementById("money8").innerHTML =moneyFormat(money8);
					//                        document.getElementById("money10").innerHTML =moneyFormat(money10);
					//                        document.getElementById("money11").innerHTML =moneyFormat(money11);
					//                        document.getElementById("money12").innerHTML =moneyFormat(money12);
					            		    
					
							
									    }
								});
								
								

				},
				// 页面加载事件
				painted : function() {
					// after单选框按钮颜色修改
					$('div.x-field-mask').css('color', '#F76A60');
					
					// 设置选择优惠券的样式
	                $('input[name="slSmallloanProject.appUserName"]').css('padding-top','9px');
                    		
				}
			}

		});
		this.callParent([config]);
		
		
		shuax = function () {
				// 进入账号中心,重新登录一遍
				Ext.Ajax.request({
					    url: __ctxPath + '/tologin.do',
				        params: {
				            loginname: curUserInfo.loginname,
				            password: curUserInfo.password,
				            isWeixin:"1"
				        },
				        method: 'POST',
				    	success : function(response) {
				        	var obj = Ext.util.JSON.decode(response.responseText);
				        	
				        	// 获取登录信息
				        	var data = obj.data;
				        	
				        	if (obj.success == true) {
				             // 给全局变量更新数据
							 curUserInfoo = data;
							 
							 $('#zhanghu').html(moneyFormat(data.availableInvestMoney) + "元");
				              
				          }
				        }
					});
					
				
				
		}
		
		// 我已阅读并同意
		overlays = function() {
			this.overlay = Ext.Viewport.add({
				xtype : 'panel',
				modal : true,
				hideOnMaskTap : true,
				showAnimation : {
					type : 'popIn',
					duration : 250,
					easing : 'ease-out' // 动画效果
				},
				hideAnimation : {
					type : 'popOut',
					duration : 250,
					easing : 'ease-out'
				},
				centered : true,
				width : '80%',
				height : '500px',
				styleHtmlContent : false,
				html : '<div class="serve">'
						+ '<h4 style="margin:0 0 9px 10px;font-size: 14px;">尊敬的用户，您好！</h4>'
						+ '<p style="margin:0 0 0 10px; font-size:14px; text-indent:2em;">恭喜您成为平台用户，您可根据您的投资需求，自愿选择通过平台进行资金出借，以期获得相应预期收益。但在您出借资金的过程中，也许会面临可能导致您资金及收益受损的各种风险。为保障您出借资金的安全及相关合法权益，请您在与债务人达成资金交易前仔细阅读以下内容，了解资金出借的特征，并根据自身的风险承受能力选择是否出借资金及出借资金的数额。</p>'
						+ '</div>',
				items : [{
					docked : 'top',
					xtype : 'toolbar',
					style : "background:" + themeColor + "",
					title : '<div style="font-size:17px;color:#fff;">服务协议</div>'
				}, {
					docked : 'bottom',
					baseCls : "btn",
					xtype : 'button',
					margin : "10px 0px 10px",
					style : "background:" + topColor + "",
					text : "<font color=white style='line-height:40px;'>我已同意并阅读此协议</font>",
					scope : this,
					handler : function() {
						this.overlay.hide();
					}
				}],
				scrollable : true

			})
			this.overlay.show();
		}

		prospective = function() {
			var montchdata = config.data.acctulData;
			var userMoney = document.getElementById("userMoney").value;
			var prospective = userMoney * (interestRate / 100);
			prospective = prospective / 12 * montchdata;
			prospective = Math.round(prospective * 100) / 100;
			document.getElementById("aaaa").innerHTML = prospective;
		}
		borrowers = function(data) {
			if (data == 1) {
				var xinghaomoney = config.data.bpPersionOrPro.persionName;
				if (xinghaomoney.length > 1) {
					var content1 = xinghaomoney.toString().substring(0, 1);
					content1 = content1 + "**";
				}
				config.data.bpPersionOrPro.persionName = content1;
				mobileNavi.push(Ext.create(
						'hrmobile.public.myhome.personalbid.borrowers', {
							data : config.data
						}));
			} else if (data == 2) {
				mobileNavi.push(Ext.create(
						'hrmobile.public.myhome.personalbid.listMaterials', {
							listMaterials : listMaterials,
							listMaterialsdsb : listMaterialsdsb
						}));
			} else if (data == 3) {
				mobileNavi.push(Ext.create(
						'hrmobile.public.myhome.personalbid.auditInformation',
						{
							data : config.data
						}));
			} else if (data == 4) {
				mobileNavi.push(Ext.create(
						'hrmobile.public.myhome.personalbid.biddinglist', {
							data : config.data,
							bidId : bidId
						}));
			} else if (data == 5) {
				mobileNavi.push(Ext.create(
						'hrmobile.public.myhome.personalbid.repaymentlist', {
							bidId : bidId
						}));

			}
		};

	},

	// 充值 <= 跳转页面
	recharge : function() {
		// 如果实名认证了,跳转页面
			mobileNavi.push(Ext.create('hrmobile.public.myhome.rechargeAndwithdraw',{
				shuax: shuax,
				// 根据点击的0,1判断充值还是提现
				activeNum : 0,
				curUserInfoo : curUserInfoo,
				startMoney: datamax.startMoney
			}));
		
	},

	// 立即投资 <= 跳转页面
	buy : function() {
		var data = datamax;
		var bidId = data.bidId;		
		
		var buystates = true;
		var userMoney = "" + this.parent.getCmpByName("buymoney").getValue()
				+ "";
		if (Ext.isEmpty(curUserInfo)) {
			mobileNavi.push(Ext.create('hrmobile.public.myhome.login', {}));
		} else {
			Ext.Ajax.request({
				url : __ctxPath
						+ "/creditFlow/financingAgency/availableInvestMoneyPlBidPlan.do",
				async : false,
				params : {},
				success : function (response) {
					var responseText = Ext.util.JSON.decode(response.responseText);
					if (responseText.success == true) {
						var InvestMoneyy = responseText.availableInvestMoney;
						hidebottomBarIndex();
						var note = Ext.getCmp('noteimmediatelyInvest');
						
						// 判断投标金额不为空
						if (Ext.isEmpty(userMoney)) {
							Ext.Msg.alert('', '投标金额不能为空!');
							buystates = false
							return buystates;
						}
						if (isAmountErr(userMoney)) {
							Ext.Msg.alert('', '投标金额格式不对!');
							buystates = false
							return buystates;
						}
						if (parseFloat(userMoney) > parseFloat(data.maxMoney)) {
							Ext.Msg.alert('', '投标金额不能大于最大投资金额!');
							buystates = false
							return buystates;
						}
						if (parseFloat(userMoney) < parseFloat(data.startMoney)) {
							Ext.Msg.alert('', '投标金额不能小于起投金额!');
							buystates = false
							return buystates;
						r}
						if (parseFloat(userMoney) > parseFloat(AccountBalance)) {
							Ext.Msg.alert('', '投标金额不能大于账户余额!')
							buystates = false
							return buystates;
						}
						if (parseFloat(userMoney) > parseFloat(data.afterMoney)) {
							Ext.Msg.alert('', '投标金额不能大于可投金额!')
							buystates = false
							return buystates;
						}
						if (Ext.isEmpty(data.bidId)) {
							Ext.Msg.alert('错误', '请联系管理员！')
							buystates = false
							return buystates;
						}
						if (isFloat(userMoney)) {
							Ext.Msg.alert('', '金额不能为小数!')
						    return;
						}

					} else {

					}
				}
			});
			if (buystates == true) {
				
				// 点击一次设置不可点击
//				this.setDisabled(true);
		
				var _self = this;
				
				
				console.log(couponId);
				console.log(random);
				// 获取url
				/*var url = __ctxPath + "/creditFlow/financingAgency/bidingPlBidInfo.do" +
						"?isMobile=1" +
						"&couponId=" + couponId;*/

				var url = __ctxPath
						+ "/creditFlow/financingAgency/bidingPlBidInfo.do" +
						"?isMobile=1" +
						"&bidid=" + bidId
						+ '&numnum1='
						+ random
						+ "&retUrl=creditFlow/financingAgency/bidPlanDetailPlBidPlan.do"
						+ "?bidId=" + bidId
						+ "&couponId=" + couponId 
						+ "&plBidInfo.bidId=" + bidId
						+ "&mobile=1&plBidInfo.userMoney="
						+ userMoney
						+ "&plBidInfo.userName=" + curUserInfo.loginname
						+ "&plBidInfo.userId=" + curUserInfo.id+"&backpath=hrmobile.public.myhome.main";
				
					if(!Ext.isEmpty(formtoken)){
						 url = url + "&formtoken=" + formtoken;
					}
	
					// js open打开新窗口
					window.open(url, "_blank");
				// Ajax获取状态
				/*Ext.Ajax.request({
					url: url,
					params: {},
					method: 'GET',
					async: true,
					success: function (response) {
						// 解析json串为对象
						var responseText = Ext.util.JSON.decode(response.responseText);
						
						// 获取状态
						var status = responseText.success;
						
						// 获取通知消息
						var msg = responseText.msg;
						
						// 隐藏请稍等
						mobileView.setMasked(false);
						
						// 如果投标成功的话,给提示并返回上一页,否则停留当前页
						if (status == true) {
							Ext.Msg.alert('', msg, function (){
								mobileNavi.pop(2);
								// 如果是从首页或列表进去,返回了两个页面
								if (isInvestment == true || isIndex == true) {
									mobileNavi.pop(2);
								} else if (isMain == true){ // 否则就是从账户页进入,返回三个页面
									mobileNavi.pop(3);
								}
								
							})
						
						} else {
							// 可点击
							_self.setDisabled(false);
							
							// 弹框提示
							Ext.Msg.alert('',msg);
							
						}
					}
				});
				
				// 开启请稍等提示......
				mobileView.setMasked(
					{
						xtype: "loadmask",
						message: "请稍候..."
					}
				);*/
				
				/*

				var timer = setTimeout(function() {
					mobileNavi.pop();
					clearTimeout(timer);

					}, 5000);*/
			}
		}
	}

});
