/**
 * @author
 * @class WebBankCodeFudianView
 * @extends Ext.Panel
 * @description [WebBankCodeFudian]管理
 * @company 智维软件
 * @createtime:
 */
WebBankCodeFudianView = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				WebBankCodeFudianView.superclass.constructor.call(this, {
							id : 'WebBankCodeFudianView',
							title : '[WebBankCodeFudian]管理',
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
									fieldLabel:'cardCode',
									name : 'Q_cardCode_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'bankName',
									name : 'Q_bankName_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'signDealQuota',
									name : 'Q_signDealQuota_S_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'dayDealQuota',
									name : 'Q_dayDealQuota_S_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																													 								 								{
									fieldLabel:'isSupportShortcut',
									name : 'Q_isSupportShortcut_N_EQ',
									flex:1,
																		xtype:'numberfield'
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
									text : '添加[WebBankCodeFudian]',
									xtype : 'button',
									scope : this,
									handler : this.createRs
								}, {
									iconCls : 'btn-del',
									text : '删除[WebBankCodeFudian]',
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
					id:'WebBankCodeFudianGrid',
					url : __ctxPath + "/credit.thirdInterface/listWebBankCodeFudian.do",
					fields : [{
									name : 'id',
									type : 'int'
								}
																																																	,'cardCode'
																																										,'bankName'
																																										,'signDealQuota'
																																										,'dayDealQuota'
																																										,'isSupportShortcut'
																																			],
					columns:[
								{
									header : 'id',
									dataIndex : 'id',
									hidden : true
								}
																																																								,{
																	header : 'cardCode',	
																	dataIndex : 'cardCode'
								}
																																																,{
																	header : 'bankName',	
																	dataIndex : 'bankName'
								}
																																																,{
																	header : 'signDealQuota',	
																	dataIndex : 'signDealQuota'
								}
																																																,{
																	header : 'dayDealQuota',	
																	dataIndex : 'dayDealQuota'
								}
																																																,{
																	header : 'isSupportShortcut',	
																	dataIndex : 'isSupportShortcut'
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
					new WebBankCodeFudianForm({id:rec.data.id}).show();
				});
			},
			//创建记录
			createRs : function() {
				new WebBankCodeFudianForm().show();
			},
			//按ID删除记录
			removeRs : function(id) {
				$postDel({
					url:__ctxPath+ '/credit.thirdInterface/multiDelWebBankCodeFudian.do',
					ids:id,
					grid:this.gridPanel
				});
			},
			//把选中ID删除
			removeSelRs : function() {
				$delGridRs({
					url:__ctxPath + '/credit.thirdInterface/multiDelWebBankCodeFudian.do',
					grid:this.gridPanel,
					idName:'id'
				});
			},
			//编辑Rs
			editRs : function(record) {
				new WebBankCodeFudianForm({
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
