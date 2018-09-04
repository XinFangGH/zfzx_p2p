/**
 * @author 
 * @createtime 
 * @class BpMoneyManagerForm
 * @extends Ext.Window
 * @description BpMoneyManager表单
 * @company 智维软件
 */
BpMoneyManagerForm = Ext.extend(Ext.Window, {
			//构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				//必须先初始化组件
				this.initUIComponents();
				BpMoneyManagerForm.superclass.constructor.call(this, {
							id : 'BpMoneyManagerFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 400,
							width : 500,
							maximizable : true,
							title : '[BpMoneyManager]详细信息',
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
							//id : 'BpMoneyManagerForm',
							defaults : {
								anchor : '96%,96%'
							},
							defaultType : 'textfield',
							items : [{
								name : 'bpMoneyManager.id',
								xtype : 'hidden',
								value : this.id == null ? '' : this.id
							}
																																																	,{
																fieldLabel : '类型（充值，提现，转账）',	
								 								name : 'bpMoneyManager.type'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '银行卡类型（0.借记卡1.信用卡）',	
								 								name : 'bpMoneyManager.CardType'
								 																,xtype:'numberfield'
								 							}
																																										,{
																fieldLabel : '银行卡号',	
								 								name : 'bpMoneyManager.banknumber'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '操作状态（成功，失败）',	
								 								name : 'bpMoneyManager.status'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '要充值的账号的乾多多标识',	
								 								name : 'bpMoneyManager.RechargeMoneymoremore'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '金额',	
								 								name : 'bpMoneyManager.Amount'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '乾多多流水号',	
								 								name : 'bpMoneyManager.LoanNo'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '平台的充值订单号',	
								 								name : 'bpMoneyManager.OrderNo'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '加密验证信息',	
								 								name : 'bpMoneyManager.SignInfo'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '要提现的账号的乾多多标识',	
								 								name : 'bpMoneyManager.WithdrawMoneymoremore'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '平台乾多多标识',	
								 								name : 'bpMoneyManager.PlatformMoneymoremore'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '平台承担的手续费比例',	
								 								name : 'bpMoneyManager.FeePercent'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '平台承担的手续费金额',	
								 								name : 'bpMoneyManager.Fee'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '平台扣除的免费提现额',	
								 								name : 'bpMoneyManager.FreeLimit'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '关联平台用户ID',	
								 								name : 'bpMoneyManager.memberId'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '开户行名称',	
								 								name : 'bpMoneyManager.BranchBankName'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '开户行省份',	
								 								name : 'bpMoneyManager.Province'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '开户行城市',	
								 								name : 'bpMoneyManager.City'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '银行代码',	
								 								name : 'bpMoneyManager.BankCode'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '操作时间',	
								 								name : 'bpMoneyManager.dotime'
								 																,xtype:'datefield',
								format:'Y-m-d',
								value:new Date()
								 							}
																																										,{
																fieldLabel : '描述',	
								 								name : 'bpMoneyManager.description'
								 																 								,maxLength: 255
								 							}
																																			]
						});
				//加载表单对应的数据	
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.loadData({
								url : __ctxPath + '/financePurchase/getBpMoneyManager.do?id='+ this.id,
								root : 'data',
								preName : 'bpMoneyManager'
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
						url:__ctxPath + '/financePurchase/saveBpMoneyManager.do',
						callback:function(fp,action){
							var gridPanel = Ext.getCmp('BpMoneyManagerGrid');
							if (gridPanel != null) {
								gridPanel.getStore().reload();
							}
							this.close();
						}
					}
				);
			}//end of save

		});