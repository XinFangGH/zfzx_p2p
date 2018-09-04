/**
 * @author
 * @class PlWebShowMaterialsView
 * @extends Ext.Panel
 * @description [PlWebShowMaterials]管理
 * @company 智维软件
 * @createtime:
 */
PlWebShowMaterialsView = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				PlWebShowMaterialsView.superclass.constructor.call(this, {
							id : 'PlWebShowMaterialsView',
							title : '[PlWebShowMaterials]管理',
							region : 'center',
							layout : 'border',
							items : [this.searchPanel, this.gridPanel]
						});
			},// end of constructor
			// 初始化组件
			initUIComponents : function() {
				// 初始化搜索条件Panel
				this.searchPanel=new HT.SearchPanel({
							layout : 'form',
							region : 'north',
							colNums:3,
							items:[
																					 																																					 								{
									fieldLabel:'proMaterialsId',
									name : 'Q_proMaterialsId_L_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'projId',
									name : 'Q_projId_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'materialsId',
									name : 'Q_materialsId_L_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'materialsName',
									name : 'Q_materialsName_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'isReceive',
									name : 'Q_isReceive_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'isPigeonhole',
									name : 'Q_isPigeonhole_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																													 								 								{
									fieldLabel:'datumNums',
									name : 'Q_datumNums_N_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'remark',
									name : 'Q_remark_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																													 								 								{
									fieldLabel:'businessTypeId',
									name : 'Q_businessTypeId_N_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																													 								 								{
									fieldLabel:'parentId',
									name : 'Q_parentId_N_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'archiveConfirmRemark',
									name : 'Q_archiveConfirmRemark_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'operationTypeKey',
									name : 'Q_operationTypeKey_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'businessTypeKey',
									name : 'Q_businessTypeKey_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'isArchiveConfirm',
									name : 'Q_isArchiveConfirm_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																													 								 								{
									fieldLabel:'datumNumsOfLine',
									name : 'Q_datumNumsOfLine_N_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'ruleExplain',
									name : 'Q_ruleExplain_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																													 								 								{
									fieldLabel:'xxnums',
									name : 'Q_xxnums_N_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'recieveTime',
									name : 'Q_recieveTime_D_EQ',
									flex:1,
																		xtype:'datefield',
									format:'Y-m-d'
																	}
																,
															 							 																																					 								{
									fieldLabel:'confirmTime',
									name : 'Q_confirmTime_D_EQ',
									flex:1,
																		xtype:'datefield',
									format:'Y-m-d'
																	}
																,
															 							 																																					 								{
									fieldLabel:'recieveRemarks',
									name : 'Q_recieveRemarks_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
															 							 							 															],
								buttons:[
									{
										text:'查询',
										scope:this,
										iconCls:'btn-search',
										handler:this.search
									},{
										text:'重置',
										scope:this,
										iconCls:'btn-reset',
										handler:this.reset
									}							
								]	
				});// end of searchPanel

				this.topbar = new Ext.Toolbar({
						items : [{
									iconCls : 'btn-add',
									text : '添加[PlWebShowMaterials]',
									xtype : 'button',
									scope : this,
									handler : this.createRs
								}, {
									iconCls : 'btn-del',
									text : '删除[PlWebShowMaterials]',
									xtype : 'button',
									scope:this,
									handler : this.removeSelRs
								}]
				});
	
				this.gridPanel=new HT.GridPanel({
					region:'center',
					tbar:this.topbar,
					//使用RowActions
					rowActions:true,
					id:'PlWebShowMaterialsGrid',
					url : __ctxPath + "/p2p.materials/listPlWebShowMaterials.do",
					fields : [{
									name : 'webMaterialsId',
									type : 'int'
								}
																																																	,'proMaterialsId'
																																										,'projId'
																																										,'materialsId'
																																										,'materialsName'
																																										,'isReceive'
																																										,'isPigeonhole'
																																										,'datumNums'
																																										,'remark'
																																										,'businessTypeId'
																																										,'parentId'
																																										,'archiveConfirmRemark'
																																										,'operationTypeKey'
																																										,'businessTypeKey'
																																										,'isArchiveConfirm'
																																										,'datumNumsOfLine'
																																										,'ruleExplain'
																																										,'xxnums'
																																										,'recieveTime'
																																										,'confirmTime'
																																										,'recieveRemarks'
																																			],
					columns:[
								{
									header : 'webMaterialsId',
									dataIndex : 'webMaterialsId',
									hidden : true
								}
																																																								,{
																	header : 'proMaterialsId',	
																	dataIndex : 'proMaterialsId'
								}
																																																,{
																	header : 'projId',	
																	dataIndex : 'projId'
								}
																																																,{
																	header : 'materialsId',	
																	dataIndex : 'materialsId'
								}
																																																,{
																	header : 'materialsName',	
																	dataIndex : 'materialsName'
								}
																																																,{
																	header : 'isReceive',	
																	dataIndex : 'isReceive'
								}
																																																,{
																	header : 'isPigeonhole',	
																	dataIndex : 'isPigeonhole'
								}
																																																,{
																	header : 'datumNums',	
																	dataIndex : 'datumNums'
								}
																																																,{
																	header : 'remark',	
																	dataIndex : 'remark'
								}
																																																,{
																	header : 'businessTypeId',	
																	dataIndex : 'businessTypeId'
								}
																																																,{
																	header : 'parentId',	
																	dataIndex : 'parentId'
								}
																																																,{
																	header : 'archiveConfirmRemark',	
																	dataIndex : 'archiveConfirmRemark'
								}
																																																,{
																	header : 'operationTypeKey',	
																	dataIndex : 'operationTypeKey'
								}
																																																,{
																	header : 'businessTypeKey',	
																	dataIndex : 'businessTypeKey'
								}
																																																,{
																	header : 'isArchiveConfirm',	
																	dataIndex : 'isArchiveConfirm'
								}
																																																,{
																	header : 'datumNumsOfLine',	
																	dataIndex : 'datumNumsOfLine'
								}
																																																,{
																	header : 'ruleExplain',	
																	dataIndex : 'ruleExplain'
								}
																																																,{
																	header : 'xxnums',	
																	dataIndex : 'xxnums'
								}
																																																,{
																	header : 'recieveTime',	
																	dataIndex : 'recieveTime'
								}
																																																,{
																	header : 'confirmTime',	
																	dataIndex : 'confirmTime'
								}
																																																,{
																	header : 'recieveRemarks',	
																	dataIndex : 'recieveRemarks'
								}
																																								, new Ext.ux.grid.RowActions({
									header:'管理',
									width:100,
									actions:[{
											 iconCls:'btn-del',qtip:'删除',style:'margin:0 3px 0 3px'
										},{
											 iconCls:'btn-edit',qtip:'编辑',style:'margin:0 3px 0 3px'
										}
									],
									listeners:{
										scope:this,
										'action':this.onRowAction
									}
								})
					]//end of columns
				});
				
				this.gridPanel.addListener('rowdblclick',this.rowClick);
					
			},// end of the initComponents()
			//重置查询表单
			reset : function(){
				this.searchPanel.getForm().reset();
			},
			//按条件搜索
			search : function() {
				$search({
					searchPanel:this.searchPanel,
					gridPanel:this.gridPanel
				});
			},
			//GridPanel行点击处理事件
			rowClick:function(grid,rowindex, e) {
				grid.getSelectionModel().each(function(rec) {
					new PlWebShowMaterialsForm({webMaterialsId:rec.data.webMaterialsId}).show();
				});
			},
			//创建记录
			createRs : function() {
				new PlWebShowMaterialsForm().show();
			},
			//按ID删除记录
			removeRs : function(id) {
				$postDel({
					url:__ctxPath+ '/p2p.materials/multiDelPlWebShowMaterials.do',
					ids:id,
					grid:this.gridPanel
				});
			},
			//把选中ID删除
			removeSelRs : function() {
				$delGridRs({
					url:__ctxPath + '/p2p.materials/multiDelPlWebShowMaterials.do',
					grid:this.gridPanel,
					idName:'webMaterialsId'
				});
			},
			//编辑Rs
			editRs : function(record) {
				new PlWebShowMaterialsForm({
					webMaterialsId : record.data.webMaterialsId
				}).show();
			},
			//行的Action
			onRowAction : function(grid, record, action, row, col) {
				switch (action) {
					case 'btn-del' :
						this.removeRs.call(this,record.data.webMaterialsId);
						break;
					case 'btn-edit' :
						this.editRs.call(this,record);
						break;
					default :
						break;
				}
			}
});
