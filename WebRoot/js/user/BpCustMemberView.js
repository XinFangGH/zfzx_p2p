/**
 * @author
 * @class BpCustMemberView
 * @extends Ext.Panel
 * @description [BpCustMember]管理
 * @company 智维软件
 * @createtime:
 */
BpCustMemberView = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				BpCustMemberView.superclass.constructor.call(this, {
							id : 'BpCustMemberView',
							title : '[BpCustMember]管理',
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
									fieldLabel:'loginname',
									name : 'Q_loginname_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'truename',
									name : 'Q_truename_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'password',
									name : 'Q_password_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'plainpassword',
									name : 'Q_plainpassword_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'telphone',
									name : 'Q_telphone_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'email',
									name : 'Q_email_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																													 								 								{
									fieldLabel:'type',
									name : 'Q_type_N_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																													 								 								{
									fieldLabel:'sex',
									name : 'Q_sex_N_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																													 								 								{
									fieldLabel:'cardtype',
									name : 'Q_cardtype_N_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'cardcode',
									name : 'Q_cardcode_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'birthday',
									name : 'Q_birthday_D_EQ',
									flex:1,
																		xtype:'datefield',
									format:'Y-m-d'
																	}
																,
															 							 																																					 								{
									fieldLabel:'headImage',
									name : 'Q_headImage_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'nativePlaceProvice',
									name : 'Q_nativePlaceProvice_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'nativePlaceCity',
									name : 'Q_nativePlaceCity_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'nation',
									name : 'Q_nation_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'homePhone',
									name : 'Q_homePhone_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'relationAddress',
									name : 'Q_relationAddress_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'postCode',
									name : 'Q_postCode_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'QQ',
									name : 'Q_QQ_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'MSN',
									name : 'Q_MSN_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'paymentCode',
									name : 'Q_paymentCode_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'securityQuestion',
									name : 'Q_securityQuestion_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'securityAnswer',
									name : 'Q_securityAnswer_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																													 								 								{
									fieldLabel:'roleId',
									name : 'Q_roleId_N_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'registrationDate',
									name : 'Q_registrationDate_D_EQ',
									flex:1,
																		xtype:'datefield',
									format:'Y-m-d'
																	}
																,
															 							 																																					 								{
									fieldLabel:'liveProvice',
									name : 'Q_liveProvice_L_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'liveCity',
									name : 'Q_liveCity_L_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																													 								 								{
									fieldLabel:'marry',
									name : 'Q_marry_N_EQ',
									flex:1,
																		xtype:'numberfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'fax',
									name : 'Q_fax_S_EQ',
									flex:1,
																		xtype : 'textfield'
																	}
																,
															 							 																																					 								{
									fieldLabel:'memberOrderId',
									name : 'Q_memberOrderId_L_EQ',
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
									text : '添加[BpCustMember]',
									xtype : 'button',
									scope : this,
									handler : this.createRs
								}, {
									iconCls : 'btn-del',
									text : '删除[BpCustMember]',
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
					id:'BpCustMemberGrid',
					url : __ctxPath + "/user/listBpCustMember.do",
					fields : [{
									name : 'id',
									type : 'int'
								}
																																																	,'loginname'
																																										,'truename'
																																										,'password'
																																										,'plainpassword'
																																										,'telphone'
																																										,'email'
																																										,'type'
																																										,'sex'
																																										,'cardtype'
																																										,'cardcode'
																																										,'birthday'
																																										,'headImage'
																																										,'nativePlaceProvice'
																																										,'nativePlaceCity'
																																										,'nation'
																																										,'homePhone'
																																										,'relationAddress'
																																										,'postCode'
																																										,'QQ'
																																										,'MSN'
																																										,'paymentCode'
																																										,'securityQuestion'
																																										,'securityAnswer'
																																										,'roleId'
																																										,'registrationDate'
																																										,'liveProvice'
																																										,'liveCity'
																																										,'marry'
																																										,'fax'
																																										,'memberOrderId'
																																			],
					columns:[
								{
									header : 'id',
									dataIndex : 'id',
									hidden : true
								}
																																																								,{
																	header : 'loginname',	
																	dataIndex : 'loginname'
								}
																																																,{
																	header : 'truename',	
																	dataIndex : 'truename'
								}
																																																,{
																	header : 'password',	
																	dataIndex : 'password'
								}
																																																,{
																	header : 'plainpassword',	
																	dataIndex : 'plainpassword'
								}
																																																,{
																	header : 'telphone',	
																	dataIndex : 'telphone'
								}
																																																,{
																	header : 'email',	
																	dataIndex : 'email'
								}
																																																,{
																	header : 'type',	
																	dataIndex : 'type'
								}
																																																,{
																	header : 'sex',	
																	dataIndex : 'sex'
								}
																																																,{
																	header : 'cardtype',	
																	dataIndex : 'cardtype'
								}
																																																,{
																	header : 'cardcode',	
																	dataIndex : 'cardcode'
								}
																																																,{
																	header : 'birthday',	
																	dataIndex : 'birthday'
								}
																																																,{
																	header : 'headImage',	
																	dataIndex : 'headImage'
								}
																																																,{
																	header : 'nativePlaceProvice',	
																	dataIndex : 'nativePlaceProvice'
								}
																																																,{
																	header : 'nativePlaceCity',	
																	dataIndex : 'nativePlaceCity'
								}
																																																,{
																	header : 'nation',	
																	dataIndex : 'nation'
								}
																																																,{
																	header : 'homePhone',	
																	dataIndex : 'homePhone'
								}
																																																,{
																	header : 'relationAddress',	
																	dataIndex : 'relationAddress'
								}
																																																,{
																	header : 'postCode',	
																	dataIndex : 'postCode'
								}
																																																,{
																	header : 'QQ',	
																	dataIndex : 'QQ'
								}
																																																,{
																	header : 'MSN',	
																	dataIndex : 'MSN'
								}
																																																,{
																	header : 'paymentCode',	
																	dataIndex : 'paymentCode'
								}
																																																,{
																	header : 'securityQuestion',	
																	dataIndex : 'securityQuestion'
								}
																																																,{
																	header : 'securityAnswer',	
																	dataIndex : 'securityAnswer'
								}
																																																,{
																	header : 'roleId',	
																	dataIndex : 'roleId'
								}
																																																,{
																	header : 'registrationDate',	
																	dataIndex : 'registrationDate'
								}
																																																,{
																	header : 'liveProvice',	
																	dataIndex : 'liveProvice'
								}
																																																,{
																	header : 'liveCity',	
																	dataIndex : 'liveCity'
								}
																																																,{
																	header : 'marry',	
																	dataIndex : 'marry'
								}
																																																,{
																	header : 'fax',	
																	dataIndex : 'fax'
								}
																																																,{
																	header : 'memberOrderId',	
																	dataIndex : 'memberOrderId'
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
					new BpCustMemberForm({id:rec.data.id}).show();
				});
			},
			//创建记录
			createRs : function() {
				new BpCustMemberForm().show();
			},
			//按ID删除记录
			removeRs : function(id) {
				$postDel({
					url:__ctxPath+ '/user/multiDelBpCustMember.do',
					ids:id,
					grid:this.gridPanel
				});
			},
			//把选中ID删除
			removeSelRs : function() {
				$delGridRs({
					url:__ctxPath + '/user/multiDelBpCustMember.do',
					grid:this.gridPanel,
					idName:'id'
				});
			},
			//编辑Rs
			editRs : function(record) {
				new BpCustMemberForm({
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
