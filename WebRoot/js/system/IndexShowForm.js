/**
 * @author 
 * @createtime 
 * @class IndexShowForm
 * @extends Ext.Window
 * @description IndexShow表单
 * @company 智维软件
 */
IndexShowForm = Ext.extend(Ext.Window, {
			//构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				//必须先初始化组件
				this.initUIComponents();
				IndexShowForm.superclass.constructor.call(this, {
							id : 'IndexShowFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 400,
							width : 500,
							maximizable : true,
							title : '[IndexShow]详细信息',
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
							//id : 'IndexShowForm',
							defaults : {
								anchor : '96%,96%'
							},
							defaultType : 'textfield',
							items : [{
								name : 'indexShow.id',
								xtype : 'hidden',
								value : this.id == null ? '' : this.id
							}
																																																	,{
																fieldLabel : '类型（1： 2:  3:   4： 5：）',	
								 								name : 'indexShow.type'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '内容',	
								 								name : 'indexShow.description'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '添加时间',	
								 								name : 'indexShow.insertTime'
								 																,xtype:'datefield',
								format:'Y-m-d',
								value:new Date()
								 							}
																																			]
						});
				//加载表单对应的数据	
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.loadData({
								url : __ctxPath + '/system/getIndexShow.do?id='+ this.id,
								root : 'data',
								preName : 'indexShow'
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
						url:__ctxPath + '/system/saveIndexShow.do',
						callback:function(fp,action){
							var gridPanel = Ext.getCmp('IndexShowGrid');
							if (gridPanel != null) {
								gridPanel.getStore().reload();
							}
							this.close();
						}
					}
				);
			}//end of save

		});