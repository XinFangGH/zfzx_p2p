/**
 * @author
 * @class BpMoneyManagerView
 * @extends Ext.Panel
 * @description [BpMoneyManager]管理
 * @company 智维软件
 * @createtime:
 */
BpMoneyManagerView = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				BpMoneyManagerView.superclass.constructor.call(this, {
							id : 'BpMoneyManagerView',
							title : '[BpMoneyManager]管理',
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
									fieldLabel:'type',
									name : 'Q_type_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																													 								 								{
									fieldLabel:'CardType',
									name : 'Q_CardType_N_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'banknumber',
									name : 'Q_banknumber_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'status',
									name : 'Q_status_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'RechargeMoneymoremore',
									name : 'Q_RechargeMoneymoremore_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'Amount',
									name : 'Q_Amount_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'LoanNo',
									name : 'Q_LoanNo_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'OrderNo',
									name : 'Q_OrderNo_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'SignInfo',
									name : 'Q_SignInfo_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'WithdrawMoneymoremore',
									name : 'Q_WithdrawMoneymoremore_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'PlatformMoneymoremore',
									name : 'Q_PlatformMoneymoremore_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'FeePercent',
									name : 'Q_FeePercent_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'Fee',
									name : 'Q_Fee_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'FreeLimit',
									name : 'Q_FreeLimit_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'memberId',
									name : 'Q_memberId_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'BranchBankName',
									name : 'Q_BranchBankName_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'Province',
									name : 'Q_Province_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'City',
									name : 'Q_City_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'BankCode',
									name : 'Q_BankCode_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'dotime',
									name : 'Q_dotime_D_EQ',
									flex:1,
																		xtype:'datefield',
									format:'Y-m-d'
																	}
																,
															 							 																																					 								{
									fieldLabel:'description',
									name : 'Q_description_S_EQ',
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
									text : '添加[BpMoneyManager]',
									xtype : 'button',
									scope : this,
									handler : this.createRs
								}, {
									iconCls : 'btn-del',
									text : '删除[BpMoneyManager]',
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
					id:'BpMoneyManagerGrid',
					url : __ctxPath + "/financePurchase/listBpMoneyManager.do",
					fields : [{
									name : 'id',
									type : 'int'
								}
																																																	,'type'
																																										,'CardType'
																																										,'banknumber'
																																										,'status'
																																										,'RechargeMoneymoremore'
																																										,'Amount'
																																										,'LoanNo'
																																										,'OrderNo'
																																										,'SignInfo'
																																										,'WithdrawMoneymoremore'
																																										,'PlatformMoneymoremore'
																																										,'FeePercent'
																																										,'Fee'
																																										,'FreeLimit'
																																										,'memberId'
																																										,'BranchBankName'
																																										,'Province'
																																										,'City'
																																										,'BankCode'
																																										,'dotime'
																																										,'description'
																																			],
					columns:[
								{
									header : 'id',
									dataIndex : 'id',
									hidden : true
								}
																																																								,{
																	header : 'type',	
																	dataIndex : 'type'
								}
																																																,{
																	header : 'CardType',	
																	dataIndex : 'CardType'
								}
																																																,{
																	header : 'banknumber',	
																	dataIndex : 'banknumber'
								}
																																																,{
																	header : 'status',	
																	dataIndex : 'status'
								}
																																																,{
																	header : 'RechargeMoneymoremore',	
																	dataIndex : 'RechargeMoneymoremore'
								}
																																																,{
																	header : 'Amount',	
																	dataIndex : 'Amount'
								}
																																																,{
																	header : 'LoanNo',	
																	dataIndex : 'LoanNo'
								}
																																																,{
																	header : 'OrderNo',	
																	dataIndex : 'OrderNo'
								}
																																																,{
																	header : 'SignInfo',	
																	dataIndex : 'SignInfo'
								}
																																																,{
																	header : 'WithdrawMoneymoremore',	
																	dataIndex : 'WithdrawMoneymoremore'
								}
																																																,{
																	header : 'PlatformMoneymoremore',	
																	dataIndex : 'PlatformMoneymoremore'
								}
																																																,{
																	header : 'FeePercent',	
																	dataIndex : 'FeePercent'
								}
																																																,{
																	header : 'Fee',	
																	dataIndex : 'Fee'
								}
																																																,{
																	header : 'FreeLimit',	
																	dataIndex : 'FreeLimit'
								}
																																																,{
																	header : 'memberId',	
																	dataIndex : 'memberId'
								}
																																																,{
																	header : 'BranchBankName',	
																	dataIndex : 'BranchBankName'
								}
																																																,{
																	header : 'Province',	
																	dataIndex : 'Province'
								}
																																																,{
																	header : 'City',	
																	dataIndex : 'City'
								}
																																																,{
																	header : 'BankCode',	
																	dataIndex : 'BankCode'
								}
																																																,{
																	header : 'dotime',	
																	dataIndex : 'dotime'
								}
																																																,{
																	header : 'description',	
																	dataIndex : 'description'
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
					new BpMoneyManagerForm({id:rec.data.id}).show();
				});
			},
			//创建记录
			createRs : function() {
				new BpMoneyManagerForm().show();
			},
			//按ID删除记录
			removeRs : function(id) {
				$postDel({
					url:__ctxPath+ '/financePurchase/multiDelBpMoneyManager.do',
					ids:id,
					grid:this.gridPanel
				});
			},
			//把选中ID删除
			removeSelRs : function() {
				$delGridRs({
					url:__ctxPath + '/financePurchase/multiDelBpMoneyManager.do',
					grid:this.gridPanel,
					idName:'id'
				});
			},
			//编辑Rs
			editRs : function(record) {
				new BpMoneyManagerForm({
					id : record.data.id
				}).show();
			},
			//行的Action
			onRowAction : function(grid, record, action, row, col) {
				switch (action) {
					case 'btn-del' :
						this.removeRs.call(this,record.data.id);
						break;
					case 'btn-edit' :
						this.editRs.call(this,record);
						break;
					default :
						break;
				}
			}
});
