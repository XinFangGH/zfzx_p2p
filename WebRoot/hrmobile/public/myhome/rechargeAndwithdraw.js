var isCheckCard,flag;
var trueMoneys;
Ext.define('hrmobile.public.myhome.rechargeAndwithdraw', {
	extend : 'Ext.TabPanel',
	name : 'rechargeAndwithdraw',
	constructor : function(config) {
		config = config || {};
		
		// 获取是否实名认证
		isCheckCard = config.isCheckCard;
		flag=config.flag;
		Ext.apply(config, {
			title : "<font color=" + topColor + " style='font-size:" + topsize
					+ "'>充值提现</font>",
			//	        fullscreen: true,
			resizeTabs : true,
			style:"margin:42px 0 42px;",
			tabWidth : 300,
			defaults : {
				styleHtmlContent : true
			},
            activeItem:flag,
			tabBar : {
				defaults : {
					flex : 1
				},
				layout : {
					pack : 'center'
				},
				style : 'padding: 0em;border-color: rgb(158, 35, 40);margin:10px 15px 5px 15px;border-radius: 2px;background:#fff;height:40px;line-height:40px;border: 2px solid '
						+ themeColor + ';border-radius:40px;'
			},
			items : [{
				title : '充值',
				cls : 'hd',
				scrollable : {
                    direction : 'false'
					//direction : 'vertical'
				},
                //scrollToTopOnRefresh:true,//刷新不跳到头部
				items : [

				{
					padding : '10 8',
					html : "<div style='font-size:1.1em'><strong>账户余额:</strong><span style='padding-left:31px;'>"
							+ moneyFormat((null == curUserInfo
									? ""
									: curUserInfo.availableInvestMoney))
							+ "元</span></div>"
				},
						// 充值方式
						{
							xtype : "selectfield",
							label : "<span>充值方式:</span>",
							id : 'Recharge_mode',
							options : [{
										text : '快捷充值',
										value : '1'
									}, {
										text : '网银充值',
										value : '6'
									}],
							value : '1',
							readOnly : true
						},
						// 快捷充值 - 选择银行
                      /* {
                           label: '选择银行:',
                           labelWidth:'95px',
                           xtype : 'selectfield', 
                           style:'font-size:1.3em;',
                           id:"Quick_bank",
		                   style: {
							 'font-size':'1.3em','height':'3em'
							},
	                       store:Ext.create('StoreSelect', {
			          	       url : __ctxPath + "/financePurchase/getBindBankListFinancePurchase.do?isMobile=2"
			                })
                       },*/
						{
							xtype : 'numberfield',
							labelWidth : '95px',
							label : '充值金额:',
							style : {
								'font-size' : '1.3em'
							},
							name : 'rechargeamount',
							listeners : {
								scope : this,
								focus : function(){
									hidebottomBarIndex();
								},
								blur : function(obj, e, eOpts) {
                                    showbottomBarIndex();
									var hiddenAmount = this
											.getCmpByName("hiddenrechargeamount");
									var hiddenvalue = obj.getValue();
									hiddenAmount
											.setValue(parseFloat(hiddenvalue));
									//	obj.setValue(moneyFormat(hiddenvalue));   

								}

							}
						}, {
							xtype : "hiddenfield",
							name : "hiddenrechargeamount"
						}/*,
						{
						   margin:'12 110',
						   xtype:"label",
						   html:"<span style='font-size:12px;font-color:"+themeColor+";'>充值限额查询</span>"
						}*/, {
							xtype : "label",
							name : "note",
							id : "noterecharge",
							html : "  "

						}, {
							padding : '10px',
							style : "margin:20px;background:" + themeColor
									+ ";font-color:white",
							xtype : 'button',
							align : 'center',
							text : "<font color=white>立即充值</font>",
							scope : this,
							handler : this.recharge
						}, {

							html : "<div class='cz_ms'>"+
				                	   "<div class='title'>温馨提示：</div></div>"+
				                	   "<div valign='top' style='vertical-align:top' width='5%'>1）"+
				                	   "为了您的帐户安全，请在充值前进行邮箱绑定、手机绑定、实名认证和开通第三方账户。</br>"+
				                	   "2）"+
				                	   "请注意您的银行卡充值限额，以免造成不便。</br>"+
				                	   "3）"+
				                	   "禁止洗钱、信用卡套现、虚假交易等行为，一经发现并确认将终止该账户的使用。</br>"+
				                	   "4）"+
				                	   "如果充值金额没有及时到帐，请及时联系客服。客服热线电话：400-9266-114</div>"
						}]
			}, 
			
			
			// 提现
			{
				title : '提现',
				scrollable : {
					direction : 'false'
//					direction : 'vertical'
				},
				items : [{
                    padding : '10 4',
                    html : "<div style='font-size:1.1em;float:left'><strong>可提现金额:</strong><span style='padding-left:3px;' id='ktx'></span></div>"+
                    '</div>'+"<div style='overflow:hidden'>" +
                    "<div style='font-size:1.1em;float:right'><strong>可用余额:</strong><span style='padding-left:3px;'>"
                    + moneyFormat((null == curUserInfo? "": curUserInfo.availableInvestMoney))+ "元</span></div>",
                    listeners:{
                        painted:function(){
                            Ext.Ajax.request({
                                url: __ctxPath + '/user/getTrueMoneyBpCustMember.do',
                                params: {
                                    isMobile: "1"

                                },
                                method: 'GET',
                                async: true,
                                success: function (response) {
                                    // 解析json串为对象
                                    var responseText = Ext.util.JSON.decode(response.responseText);
                                    trueMoneys = responseText.trueMoney;
                                    $('#ktx').html(moneyFormat(responseText.trueMoney)+'元');
                                }
                            })
                        }
                    }
				}, {
					/*label : '选择银行:',*/
					label : '银行卡:',
					labelWidth : '64px',
					xtype : 'selectfield',
					readOnly : true,
					//style : 'font-size:1.3em;',
					name : "cardNo",
					style : {
						/*'font-size' : '1.3em',*/
						'height' : '2.4em',
					},
					/*html :'<span class="abs"></span>',*/
					store : Ext.create('StoreSelect', {
						url : __ctxPath
								+ "/financePurchase/getBindBankListFinancePurchase.do?isMobile=2"
					})
				}, {
					xtype : 'numberfield',
					label : '提现金额:',
					labelWidth : '85px',
					name : 'amount',
					style : 'font-size: 1.3em;margin-top: 2px;',
					listeners : {
						scope : this,
                        focus : function(){
                            hidebottomBarIndex();
                        },
						blur : function(obj, e, eOpts) {
							var amounthidden = this
									.getCmpByName("amounthidden");
							var hiddenvalue = obj.getValue();
							if(hiddenvalue){
                                amounthidden.setValue(parseFloat(hiddenvalue));
                                obj.setValue(hiddenvalue);

							}
						}

					}
				}, {
					xtype : 'hiddenfield',
					name : 'amounthidden'
				}, {
					xtype : "label",
					name : "note",
					id : "notewithdraw",
					html : "  "

				}, {
					padding : '10px',
					style : "margin:20px;background:" + themeColor
							+ ";font-color:white",
					xtype : 'button',
					align : 'center',
					text : "<font color=white>确认提现</font>",
					scope : this,
					handler : this.withdraws
				}, {
					html : "<div class='cz_ms'>"
							+ "<div class='title'>温馨提示：</div></div>"
							+ "<div valign='top' style='vertical-align:top'>"
							+ "当日充值及回款金额请于1-2个工作日之后提取，节假日顺延。</br>"
							+ "</div>"
				}]
			}],
			listeners:{
				painted:function(){
                    $('#navbar').css({"position":"fixed","width":"100%","z-index":"9"});
                    showbottomBarIndex();
                    /*$('#ext-button-3').addClass('wyjk_select').removeClass('wyjk');
                    $('#ext-button-1').removeClass('home_select').addClass('home');
                    $('#ext-button-2').removeClass('wytz_select').addClass('wytz');*/
				}
			}

		});

		this.callParent([config]);

	},
	
	// 充值
	recharge : function() {
		// 判断是否实名认证
		if (isCheckCard == false) {
      		Ext.Msg.alert('提示','您尚未进行实名认证!',function () {
      			mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
      			}
      		);
	   } else { // 如果实名认证了,跳转页面
			// 获取充值方式
	    	var Recharge_mode = Ext.getCmp("Recharge_mode").getValue();
	    	
	    	// 获取选择银行
	//    	var Option_bank = Ext.getCmp("Quick_bank").getValue();
	    	
	    	// 获取充值金额
	    	var amount = this.getCmpByName("rechargeamount").getValue();
			
			
			var noterechargeAndwithdraw = Ext.getCmp('noterecharge');
			if (Ext.isEmpty(amount)) {
				noterechargeAndwithdraw
						.setHtml("<div style='margin:10px;'><font color='"
								+ themeColor + "'>金额不能为空</font></div>");
				return;
			}
			if (parseFloat(amount) == 0) {
				noterechargeAndwithdraw
						.setHtml("<div style='margin:10px;'><font color='"
								+ themeColor + "'>金额要大于0</font></div>");
				return;
			}
			if (parseFloat(amount) < 1) {
				noterechargeAndwithdraw
						.setHtml("<div style='margin:10px;'><font color='"
								+ themeColor + "'>金额不能小于1</font></div>");
				return;
			}

			
			// js open打开新窗口
		 	var url = __ctxPath + '/pay/rechargePay.do' +
		 			'?payType=' + Recharge_mode +
		 			'&tradeId=front_toRechargePayRedirect' +
		 			'&isMobile=1' +
		 			'&amount=' + amount +
		 			 '&backpath=hrmobile.public.myhome.main';
					
			window.open(url, '_blank');
	//		getThirdPayLink(url);
	   }
	},
	
	
	// 提现
	withdraws : function() {
		// 判断是否实名认证
		if (isCheckCard == false) {
      		Ext.Msg.alert('提示','您尚未进行实名认证!',function () {
      			mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
      			}
      		);
	   } else { // 如果实名认证了,跳转页面
			var amounthidden = this.getCmpByName("amounthidden").getValue();
			var cardNo = this.getCmpByName("cardNo").getValue();
	
			var noterechargeAndwithdraw = Ext.getCmp('notewithdraw');
			if (Ext.isEmpty(cardNo)) {
				noterechargeAndwithdraw
						.setHtml("<div style='margin:10px;'><font color='"
								+ themeColor + "'>银行不能为空</font></div>");
				return;
			}
			if (Ext.isEmpty(amounthidden)) {
				noterechargeAndwithdraw
						.setHtml("<div style='margin:10px;'><font color='"
								+ themeColor + "'>金额不能为空</font></div>");
				return;
			}
			if (parseFloat(amounthidden) == 0) {
				noterechargeAndwithdraw
						.setHtml("<div style='margin:10px;'><font color='"
								+ themeColor + "'>金额要大于0</font></div>");
				return;
			}
			if (parseFloat(amounthidden) > trueMoneys ) {
				Ext.Msg.alert('温馨提示','您当前账户可提现金额为:'+trueMoneys+'元，'+curUserInfo.availableInvestMoney+'元为当日充值或当日到账金额，可在1-2个工作日后(节假日顺延)进行提现')
				return;
			}
	
			var url = __ctxPath + '/pay/withdrawsPay.do?amount=' + amounthidden
					+ '&isMobile=1' + '&cardNo=' + cardNo + '&aa=' + curUserInfo.id
					+ "ae19b127b" + curUserInfo.password+'&backpath=hrmobile.public.myhome.main';
			getThirdPayLink(url);
	    }
		
		

	}
});
