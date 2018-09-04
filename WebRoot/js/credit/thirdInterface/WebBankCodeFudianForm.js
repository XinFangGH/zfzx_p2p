/**
 * @author 
 * @createtime 
 * @class WebBankCodeFudianForm
 * @extends Ext.Window
 * @description WebBankCodeFudian表单
 * @company 智维软件
 */
WebBankCodeFudianForm = Ext.extend(Ext.Window, {
			//构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				//必须先初始化组件
				this.initUIComponents();
				WebBankCodeFudianForm.superclass.constructor.call(this, {
							id : 'WebBankCodeFudianFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 400,
							width : 500,
							maximizable : true,
							title : '[WebBankCodeFudian]详细信息',
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
							//id : 'WebBankCodeFudianForm',
							defaults : {
								anchor : '96%,96%'
							},
							defaultType : 'textfield',
							items : [{
								name : 'webBankCodeFudian.id',
								xtype : 'hidden',
								value : this.id == null ? '' : this.id
							}
																																																	,{
																fieldLabel : '银行编码',	
								 								name : 'webBankCodeFudian.cardCode'
								 																 								,maxLength: 20
								 							}
																																										,{
																fieldLabel : '银行名称',	
								 								name : 'webBankCodeFudian.bankName'
								 																 								,maxLength: 50
								 							}
																																										,{
																fieldLabel : '单笔限额',	
								 								name : 'webBankCodeFudian.signDealQuota'
								 								 							}
																																										,{
																fieldLabel : '日限额',	
								 								name : 'webBankCodeFudian.dayDealQuota'
								 								 							}
																																										,{
																fieldLabel : '1支持0不支持',	
								 								name : 'webBankCodeFudian.isSupportShortcut'
								 																,xtype:'numberfield'
								 							}
																																			]
						});
				//加载表单对应的数据	
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.loadData({
								url : __ctxPath + '/credit.thirdInterface/getWebBankCodeFudian.do?id='+ this.id,
								root : 'data',
								preName : 'webBankCodeFudian'
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
						url:__ctxPath + '/credit.thirdInterface/saveWebBankCodeFudian.do',
						callback:function(fp,action){
							var gridPanel = Ext.getCmp('WebBankCodeFudianGrid');
							if (gridPanel != null) {
								gridPanel.getStore().reload();
							}
							this.close();
						}
					}
				);
			}//end of save

		});