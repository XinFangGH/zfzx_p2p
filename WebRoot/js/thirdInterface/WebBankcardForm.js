/**
 * @author 
 * @createtime 
 * @class WebBankcardForm
 * @extends Ext.Window
 * @description WebBankcard表单
 * @company 智维软件
 */
WebBankcardForm = Ext.extend(Ext.Window, {
			//构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				//必须先初始化组件
				this.initUIComponents();
				WebBankcardForm.superclass.constructor.call(this, {
							id : 'WebBankcardFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 400,
							width : 500,
							maximizable : true,
							title : '[WebBankcard]详细信息',
							buttonAlign : 'center',
							buttons : [
										{
											text : '保存',
											iconCls : 'btn-save',
											scope : this,
											handler : this.save
										}, {
											text : '重置',
											iconCls : 'btn-reset',
											scope : this,
											handler : this.reset
										}, {
											text : '取消',
											iconCls : 'btn-cancel',
											scope : this,
											handler : this.cancel
										}
							         ]
				});
			},//end of the constructor
			//初始化组件
			initUIComponents : function() {
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							bodyStyle : 'padding:10px',
							border : false,
							autoScroll:true,
							//id : 'WebBankcardForm',
							defaults : {
								anchor : '96%,96%'
							},
							defaultType : 'textfield',
							items : [{
								name : 'webBankcard.cardId',
								xtype : 'hidden',
								value : this.cardId == null ? '' : this.cardId
							}
																																																	,{
																fieldLabel : '',	
								 								name : 'webBankcard.thirdConfig'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '第三方唯一标识',	
								 								name : 'webBankcard.userFlg'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '卡号',	
								 								name : 'webBankcard.cardNum'
								 																 								,maxLength: 20
								 							}
																																										,{
																fieldLabel : '银行标识',	
								 								name : 'webBankcard.bankId'
								 																 								,maxLength: 30
								 							}
																																										,{
																fieldLabel : '持卡人姓名',	
								 								name : 'webBankcard.username'
								 																 								,maxLength: 255
								 							}
																																			]
						});
				//加载表单对应的数据	
				if (this.cardId != null && this.cardId != 'undefined') {
					this.formPanel.loadData({
								url : __ctxPath + '/thirdInterface/getWebBankcard.do?cardId='+ this.cardId,
								root : 'data',
								preName : 'webBankcard'
							});
				}
				
			},//end of the initcomponents

			/**
			 * 重置
			 * @param {} formPanel
			 */
			reset : function() {
				this.formPanel.getForm().reset();
			},
			/**
			 * 取消
			 * @param {} window
			 */
			cancel : function() {
				this.close();
			},
			/**
			 * 保存记录
			 */
			save : function() {
				$postForm({
						formPanel:this.formPanel,
						scope:this,
						url:__ctxPath + '/thirdInterface/saveWebBankcard.do',
						callback:function(fp,action){
							var gridPanel = Ext.getCmp('WebBankcardGrid');
							if (gridPanel != null) {
								gridPanel.getStore().reload();
							}
							this.close();
						}
					}
				);
			}//end of save

		});