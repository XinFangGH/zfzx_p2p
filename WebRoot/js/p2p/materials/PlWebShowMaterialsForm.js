/**
 * @author 
 * @createtime 
 * @class PlWebShowMaterialsForm
 * @extends Ext.Window
 * @description PlWebShowMaterials表单
 * @company 智维软件
 */
PlWebShowMaterialsForm = Ext.extend(Ext.Window, {
			//构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				//必须先初始化组件
				this.initUIComponents();
				PlWebShowMaterialsForm.superclass.constructor.call(this, {
							id : 'PlWebShowMaterialsFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 400,
							width : 500,
							maximizable : true,
							title : '[PlWebShowMaterials]详细信息',
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
							//id : 'PlWebShowMaterialsForm',
							defaults : {
								anchor : '96%,96%'
							},
							defaultType : 'textfield',
							items : [{
								name : 'plWebShowMaterials.webMaterialsId',
								xtype : 'hidden',
								value : this.webMaterialsId == null ? '' : this.webMaterialsId
							}
																																																	,{
																fieldLabel : '贷款材料列表id',	
								 								name : 'plWebShowMaterials.proMaterialsId'
								 																,xtype:'numberfield'
								 							}
																																										,{
																fieldLabel : '项目表id',	
								 								name : 'plWebShowMaterials.projId'
																,allowBlank:false
								 																 								,maxLength: 64
								 							}
																																										,{
																fieldLabel : '贷款材料配置表id',	
								 								name : 'plWebShowMaterials.materialsId'
								 																,xtype:'numberfield'
								 							}
																																										,{
																fieldLabel : '贷款材料名称',	
								 								name : 'plWebShowMaterials.materialsName'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.isReceive'
								 																 								,maxLength: 0
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.isPigeonhole'
								 																 								,maxLength: 0
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.datumNums'
								 																,xtype:'numberfield'
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.remark'
								 																 								,maxLength: 255
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.businessTypeId'
								 																,xtype:'numberfield'
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.parentId'
								 																,xtype:'numberfield'
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.archiveConfirmRemark'
								 																 								,maxLength: 50
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.operationTypeKey'
								 																 								,maxLength: 30
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.businessTypeKey'
								 																 								,maxLength: 30
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.isArchiveConfirm'
								 																 								,maxLength: 0
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.datumNumsOfLine'
								 																,xtype:'numberfield'
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.ruleExplain'
								 																 								,xtype:'textarea'
								 								,maxLength: 65535
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.xxnums'
								 																,xtype:'numberfield'
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.recieveTime'
								 																,xtype:'datefield',
								format:'Y-m-d',
								value:new Date()
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.confirmTime'
								 																,xtype:'datefield',
								format:'Y-m-d',
								value:new Date()
								 							}
																																										,{
																fieldLabel : '',	
								 								name : 'plWebShowMaterials.recieveRemarks'
								 																 								,maxLength: 255
								 							}
																																			]
						});
				//加载表单对应的数据	
				if (this.webMaterialsId != null && this.webMaterialsId != 'undefined') {
					this.formPanel.loadData({
								url : __ctxPath + '/p2p.materials/getPlWebShowMaterials.do?webMaterialsId='+ this.webMaterialsId,
								root : 'data',
								preName : 'plWebShowMaterials'
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
						url:__ctxPath + '/p2p.materials/savePlWebShowMaterials.do',
						callback:function(fp,action){
							var gridPanel = Ext.getCmp('PlWebShowMaterialsGrid');
							if (gridPanel != null) {
								gridPanel.getStore().reload();
							}
							this.close();
						}
					}
				);
			}//end of save

		});