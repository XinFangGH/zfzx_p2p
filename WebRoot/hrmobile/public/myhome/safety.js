var bidauto,
	dimension,
	toogger = 0,
	isCheckEmail,
	isCheckPhone,
	isCheckCard,
	isthirdPayFlagId,
	styletrue,
	stylefalse,
	texttrue,
	textfalse,
	textkait,
	thirdPayConfig,
	tel,
	telephone;
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.safety', {
	extend : 'Ext.Container',
	name : 'safety',
	constructor : function(config) {
		config = config || {};
		bidauto = config.bidauto;
		styletrue = "padding-top:1px;font-color:" + themeColor + ";background: #fff;height:45px;'";
		stylefalse = "padding-top:1px;font-color:" + themeColor + ";background: #fff;height:45px;'";
		texttrue = "<span  style='color:green;'>已验证</span>";
		textfalse = "<span  style='color:red;'>验 证〉</span>";
		textkait = "<span  style='color:red;'>开 通〉</span>";
			
		if (bidauto.isOpen == 1) {
			toogger = 1;
		}
		Ext.apply(config, {
			title : "<font color=" + topColor + " style='font-size:" + topsize
					+ "'>账户安全</font>",
			width : "100%",
			/*height : "100%",*/
			style:"margin-top:42px;margin-bottom:55px;",
			fullscreen : true,
			id:'my_safety',
			scrollable : {
				direction : 'vertical'
			},
			items : [{
				xtype : 'panel',
				defaults : {
					xtype : 'textfield',
					labelWidth : "40%"
				},
				items : [{
							xtype : 'panel',
							layout : {
								type : 'hbox',
								align : 'middle'
							},
							items : [{
										width : "70%",
										label : "用户名:",
										style : "margin-left:10px;",
										labelWidth : "40%",
										xtype : 'textfield',
										readOnly : true,
										name : "telphone",
										value : curUserInfo.loginname
									}, {
										width : "30%",
										height : "45px",
										xtype : 'button',
										id: 'userValidate',
										style : isCheckPhone ? styletrue : stylefalse
									}]
						}, {
							xtype : "label",
							html : "<div style='height:1px;background-color:#f3f3f3;border-top:1px solid #d8d8d8'></div>"
						}, {
							xtype : 'panel',
							layout : {
								type : 'hbox',
								align : 'middle'
							},
							items : [{
										width : "70%",
										label : "手机号:",
										style : "margin-left:10px;",
										labelWidth : "40%",
										xtype : 'textfield',
										disabled : true,
										name : "telphone",
										id:'telephone',
										value : isStringCutout(curUserInfo.telphone)
									}, {

										width : "30%",
										xtype : 'button',
										id: 'phoneValidate',
										//style :styletrue,
                                        style:'font-size:16px;',
										//disabled : isCheckPhone,
										text : '更换&gt;',
										handler : this.telphoneCheck

									}]
						}, {
							xtype : "label",
							html : "<div style='height:16px;background-color:#f3f3f3;border-top:1px solid #d8d8d8'></div>"
						}, {
							xtype : 'panel',
							layout : {
								type : 'hbox',
								align : 'middle'
							},
							items : [{
										label : "实名认证:",
										style : "margin-left:10px;",
										labelWidth : "40%",
										width : "70%",
										xtype : 'textfield',
										disabled : true,
										name : "truename",
										id:'realName',
										value : isStringname(curUserInfo.truename)

									}, {
										width : "30%",
										xtype : 'button',
										id: 'realNameValidate',
										style : isCheckCard ? styletrue : stylefalse,
										disabled : isthirdPayFlagId,
										handler : this.bindingbtn,
										text : isthirdPayFlagId ? texttrue : textkait

									}]
						}, {
							xtype : "label",
							html : "<div style='height:1px;background-color:#f3f3f3;border-top:1px solid #d8d8d8'></div>"
						}, {
							xtype : 'panel',
							layout : {
								type : 'hbox',
								align : 'middle'
							},
							items : [{
								label : "邮箱认证:",
								style : "margin-left:10px;",
								labelWidth : "40%",
								width : "70%",
								xtype : 'textfield',
								id: 'emailText',
//								readOnly : true,
								disabled : true,
								name : "email",
								value : (curUserInfo.email = null ? "" : isStringEmail(curUserInfo.email))

							}, {
								width : "30%",
								xtype : 'button',
								id: 'emailValidate',
								style : isCheckCard ? styletrue : stylefalse,
								disabled : true,
								/*handler : this.bindingbtn*/

							}]
						}, {
							xtype : "label",
							html : "<div style='height:1px;background-color:#f3f3f3;border-top:1px solid #d8d8d8'></div>"
						}, {
							xtype : 'panel',
							layout : {
								type : 'hbox',
								align : 'middle'
							},
							items : [{
								label : "自动投标:",
								style : "margin-left:10px;color:green;",
								labelWidth : "40%",
								width : "70%",
								xtype : 'textfield',
								readOnly : true,
								name : "truename",
                                cls : 'input_foc',
								value : " ",
								listeners : {
									scope : this,
									'focus' : function(f) {
                                        document.activeElement.blur();
										/*Ext.Ajax.request({
											url : __ctxPath
													+ "/creditFlow/financingAgency/automaticbidPlBidAuto.do",
											params : {
												isMobile : "1"
											},
											success : function(response) {
												var responseText1 = response.responseText
														.replace(/[\n]/ig, '');
												var responseText = Ext.util.JSON
														.decode(responseText1);
												var bidauto = responseText.data;
												mobileNavi
														.push(Ext
																.create(
																		'hrmobile.public.myhome.aiplan.aubiddingupdate',
																		{
																			bidauto : bidauto
																		}));
											}
										});*/

									}
								}

							}, {
								width : "30%",
								xtype : 'togglefield',
								name : 'awesome',
								value : bidauto.isOpen,
								handler : this.bindingbtn,
								listeners : {
									scope : this,
									change : function(field, newValue, oldValue) {
										if (toogger == 0) {
											dimension = this
													.getCmpByName("awesome");
											if (newValue == 1) {
												var url = __ctxPath
														+ '/user/openBidAutoBpCustMember.do?isMobile=1';
												window.open(url, "_bank")
												openorclose();
											} else {
												var url = __ctxPath
														+ '/user/closeBidAutoBpCustMember.do?isMobile=1';
												window.open(url, "_bank")
												openorclose();
											}
										} else {
											toogger = 0;
										}
									}
								}
							}]
						}, {
							xtype : "label",
							html : "<div style='height:16px;background-color:#f3f3f3;border-top:1px solid #d8d8d8'></div>"
						}, {
							xtype : 'panel',
							layout : {
								type : 'hbox',
								align : 'middle'
							},
							items : [{
								label : "第三方认证:",
								style : "margin-left:10px;",
								labelWidth : "36%",
								width : "78%",
								xtype : 'textfield',
								disabled : true,
								name : "truename",
								id: 'ThreePartyText',
								value : curUserInfo.thirdPayConfig = null ? "" : curUserInfo.thirdPayConfig

							}, {
								width : "22%",
								xtype : 'button',
								id: 'ThreePartyValidate',
								style : isCheckCard ? styletrue : stylefalse,
								style :'padding-left:0;',
								disabled : isthirdPayFlagId,
								handler : this.bindingbtn,
								text : isthirdPayFlagId ? texttrue : textkait

							}]
						}, {
							xtype : "label",
							html : "<div style='height:1px;background-color:#f3f3f3;border-top:1px solid #d8d8d8'></div>"
						}, {
							xtype : 'panel',
							layout : {
								type : 'hbox',
								align : 'middle'
							},
							items : [{
								label : "我的身份证:",
								style : "margin-left:10px;",
								labelWidth : "44%",
								width : "70%",
								xtype : 'textfield',
								disabled : true,
								name : "truename",
								id:'cardCodeText'
								// value : isSubName(curUserInfo.cardcode, 4, 4)

							}, {
								width : "30%",
								xtype : 'button',
								id:'cardCodeValidate',
								style : isCheckCard ? styletrue : stylefalse,
								disabled : true,
								handler : this.bindingbtn

							}]
						}, {
							xtype : "label",
							html : "<div style='height:1px;background-color:#f3f3f3;border-top:1px solid #d8d8d8'></div>"
						}, {
							xtype : 'panel',
							layout : {
								type : 'hbox',
								align : 'middle'
							},
							items : [{
								label : "我的邀请码:",
								style : "margin-left:10px;",
								labelWidth : "41%",
								width : "76%",
								xtype : 'textfield',
								disabled : true,
								name : "truename",
								id : 'InvitationCode',
								value : ''

							}, {
								width : "30%",
								xtype : 'button',
								style : isCheckCard ? styletrue : stylefalse,
								disabled : true,
								handler : this.bindingbtn

							}]
						}, {
							xtype : "label",
							html : "<div style='height:16px;background-color:#f3f3f3;border-top:1px solid #d8d8d8'></div>"

						}, {
							xtype : 'panel',
							layout : {
								type : 'hbox',
								align : 'middle'
							},
							items : [{
										label : "修改登录密码",
										style : "margin-left:10px;",
										labelWidth : "100%",
										width : "40%",
										xtype : 'textfield',
										readOnly : true,
										handler : this.updatepassword,
										name : "truename"

									}, {
										width : "100%",
										xtype : 'button',
                                		text : "<font color=gray>&gt;</font>",
										/*style : isCheckCard
												? styletrue
												: stylefalse,*/
										style:"background:none;",
										disabled : false,
										handler : this.updatepassword

									}]
						}, {
							xtype : "label",
							html : "<div style='height:1px;background-color:#f3f3f3;border-top:1px solid #d8d8d8'></div>"

						}

				]
			}, {
				style : "margin: 20px 10px;height:44px;background:"
						+ themeColor + ";font-color:white",
				xtype : 'button',
				text : "<font color=white>退出登录</font>",
				handler : this.closeuser
			}],
			listeners :  {
				// 页面显示事件
				show : function () {
					// 账户余额
					Ext.Ajax.request({
					    url: __ctxPath+'/user/isSessinonValidBpCustMember.do',
					    method: 'Get',
					    async: true,
						success : function(response) {
							// 转换json对象
					    	var responseText = Ext.util.JSON.decode(response.responseText);
					    	// 获取数据
					    	var data = responseText.data;
					    	
					    	// 判断是否请求成功
					    	if(responseText.success == true){
					    		// 给全局变量更新数据
//								 curUserInfo = data;

								//给手机号重新赋值
								telephone=data.telphone;
                                Ext.getCmp("telephone").setValue(data.telphone);

								isCheckEmail = (my.isObjEmpty(curUserInfo, 'isCheckEmail',null) == 1) ? true : false;
								//isCheckPhone = (my.isObjEmpty(curUserInfo, 'isCheckPhone', null) == 1) ? true: false;
								isCheckCard =  (my.isObjEmpty(curUserInfo, 'isCheckCard', null) == 1)  ? true : false;
								
								// 判断实名认证
								isthirdPayFlagId = (my.isObjEmpty(curUserInfo, 'thirdPayFlagId', null) != null) ? true : false;
								 //console.log(isthirdPayFlagId);
								// 用户名
								if(Ext.getCmp("userValidate")){
                                    Ext.getCmp("userValidate").setStyle(isCheckPhone ? styletrue : stylefalse);
								}

								
								// 手机验证
								Ext.getCmp("phoneValidate").setStyle(styletrue);
								//Ext.getCmp("phoneValidate").setDisabled(isCheckPhone);

								//Ext.getCmp("phoneValidate").setText(isCheckPhone ? '更换&gt;' : textfalse);
								
								// 实名认证 文本
								Ext.getCmp("realName").setValue(isStringname(curUserInfo.truename));
								
								// 实名认证 状态
								Ext.getCmp("realNameValidate").setStyle(isCheckCard ? styletrue : stylefalse);
								Ext.getCmp("realNameValidate").setDisabled(isthirdPayFlagId);
								Ext.getCmp("realNameValidate").setText(isthirdPayFlagId ? texttrue : textkait);
								
								
								// 邮箱认证 文本
								Ext.getCmp("emailText").setValue(my.isObjEmpty(curUserInfo, 'email', ''));
								
								// 邮箱认证 状态
								Ext.getCmp("emailValidate").setStyle(isCheckEmail ? styletrue : stylefalse);
								Ext.getCmp("emailValidate").setDisabled(isCheckEmail);
								
								// 第三方认证 文本
								Ext.getCmp("ThreePartyText").setValue(my.isObjEmpty(curUserInfo, 'thirdPayFlagId', ''));
								
								// 第三方认证 状态
								Ext.getCmp("ThreePartyValidate").setStyle(isCheckCard ? styletrue : stylefalse);
								Ext.getCmp("ThreePartyValidate").setDisabled(isthirdPayFlagId);
								Ext.getCmp("ThreePartyValidate").setText(isthirdPayFlagId ? texttrue : textkait);
								
								// 我的银行卡 文本
								if( curUserInfo.isCheckCard ==1){
								Ext.getCmp("cardCodeText").setValue(isSubName(curUserInfo.cardcode, 4, 4));
								}else {
								Ext.getCmp("cardCodeText").setValue("");

								}

								// 我的银行卡 状态
								Ext.getCmp("cardCodeValidate").setStyle(isCheckCard ? styletrue : stylefalse);
								
								// 邀请码
								Ext.getCmp("InvitationCode").setValue(my.isObjEmpty(curUserInfo, 'plainpassword', ''));
								
					    	}
					    	
					    }
					});
					
					
				},
				painted:function(){
					$('#navbar').css({"position":"fixed","width":"100%","z-index":"9"});
				}

			}
		});
		
		
		
		// 是否要进行操作
		openorclose = function() {
			Ext.Msg.confirm("", '操作成功？', function(btn) {
				if (btn == "yes") {
					mobileNavi.setMasked(true);
					Ext.Ajax.request({
						url : __ctxPath
								+ "/creditFlow/financingAgency/automaticbidPlBidAuto.do",
						params : {
							isMobile : "1"
						},
						success : function(response) {
							var responseText1 = response.responseText.replace(
									/[\n]/ig, '');
							var responseText = Ext.util.JSON
									.decode(responseText1);
							var bidauto = responseText.data;
							if (bidauto.isOpen == 0) {
								if (dimension.getValue() == 0) {

								} else {
									toogger = 1;
									/*dimension.setValue("0");*/
									dimension.setValue("1");
								}
							} else {
								if (dimension.getValue() == 1) {

								} else {
									toogger = 1;
									/*dimension.setValue("1");*/
									dimension.setValue("0");
								}
							}
							mobileNavi.setMasked(false);
						}
					});
				} else {
					mobileNavi.setMasked(true);
					Ext.Ajax.request({
						url : __ctxPath
								+ "/creditFlow/financingAgency/automaticbidPlBidAuto.do",
						params : {
							isMobile : "1"
						},
						success : function(response) {
							var responseText1 = response.responseText.replace(
									/[\n]/ig, '');
							var responseText = Ext.util.JSON
									.decode(responseText1);
							var bidauto = responseText.data;
							if (bidauto.isOpen == 0) {
								toogger = 1;
								/*dimension.setValue("1");*/
								dimension.setValue("0");
							} else {
								toogger = 1;
								/*dimension.setValue("0");*/
								dimension.setValue("1");
							}
							mobileNavi.setMasked(false);
						}
					});
				}
			});
		}

		this.callParent([config]);
		
		

	},
	truenameCheck : function() {
		mobileNavi.push(Ext.create('hrmobile.public.myhome.personInfo', {}));
	},
	bindingbtn : function() {
		mobileNavi.push(Ext.create('hrmobile.public.myhome.binding', {}));
	},
    telphoneCheck:function(){

		mobileNavi.push(Ext.create('hrmobile.public.myhome.changeTelephone', {tel:telephone}));
		//Ext.Msg.alert("添加更换手机号方法");
		/*var url = __ctxPath + '/creditFlow/financingAgency/buyorStartTransferPlBidSale.do?isMobile=1&bidInfoId='+Rmmdata.bidInfoID+'&saleId='+Rmmdata.id+'&startDate='+Rmmdata.startDate+'';
		window.open(url,"_blank");*/

	},

	// 修改密码
	updatepassword : function() {
		mobileNavi.push(Ext.create(
				'hrmobile.public.myhome.related.updatePassword', {}));
	},
	pasetting : function() {
		mobileNavi.push(Ext.create(
				'hrmobile.public.myhome.aiplan.aubiddingupdate', {
					bidauto : bidauto
				}));
	},
	
	// 退出登录
	closeuser : function() {
		// 退出登录完整版
		Ext.Msg.confirm("", '是否退出登录', function(btn) {
			// 如果点击了是
			if (btn == "yes") {
				
				// 设置黑色透明背景
				mobileNavi.setMasked(true);
				// 请求退出Ajax
				Ext.Ajax.request({
							url : __ctxPath + '/exitlogin.do'
				});
				// 设置全局变量为null
				curUserInfo = null;
				
				// 判断是否为App
				if (isApp == true) {
					// 清除缓存
					window.localStorage.clear();
		   			
					if (my.isObjEmpty(mobileNavi, 'destroy',null)) {
						// 销毁页面
						mobileNavi.destroy();
						
						// 重新初始化页面,并覆盖
			    		mobileNavi = Ext.create('mobile.View', {
			    				fullscreen: true
			    			}
			    		);
			    		
			    		// 隐藏导航
			    		mobileNavi.getNavigationBar().hide();
			    		
			    		// 添加页面
			    		mobileView.add(mobileNavi);
					} else {
						window.location.href = window.location.href+'?rand='+parseInt(Math.random()*101);
					}
					
					
				} else {
					// 清除缓存
					window.localStorage.clear();
					
					// 刷新当前页面
					if(window.location.href.indexOf('?')==-1){
                        window.location.href = window.location.href+'?rand='+parseInt(Math.random()*101);
					}else{
                        window.location.href = window.location.href+parseInt(Math.random()*10);
					}

				}
				
			}
		});
				
		/*Ext.Msg.confirm("", '是否退出登录', function(btn) {
					if (btn == "yes") {
						mobileNavi.setMasked(true);
						Ext.Ajax.request({
									url : __ctxPath + '/exitlogin.do'
								});
						curUserInfo = null;
						
						// 清除缓存的变量和值
						window.localStorage.clear();
						
						window.location.reload();
					}
				});*/
	}
});
