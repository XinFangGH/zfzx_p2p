
//creditorList.js
Ext.define('hrmobile.public.myhome.myloan.myloan', {
	extend : 'Ext.TabPanel',
	name : 'loanList',
	constructor : function(config) {
		config = config || {};
//		var compensatory = Ext.create('hrmobile.public.myhome.myloan.compensatory', {});
		var repayment = Ext.create('hrmobile.public.myhome.myloan.repayment',{});
		var biddloan = Ext.create('hrmobile.public.myhome.myloan.biddloan', {});
		var settled = Ext.create('hrmobile.public.myhome.myloan.settled', {});
//		var appliloan = Ext.create('hrmobile.public.myhome.myloan.appliloan',{});
		Ext.apply(config, {
			title : "<font color=#ffffff style='font-size:" + topsize
					+ "'>借款管理</font>",
			layoutOnTabChange : true,
			tabBar : {
				defaults : {
					flex : 1
				},
				layout : {
					pack : 'center'
				},
				style : 'border-color:#9E2328;background:#eee;margin:5px 10px 0px 10px;font-color:#000;border-radius:2px;display:table !important;'
			},
			items : [
					/*{
						title : '<span class="displantab">代偿中</span>',
						items : [{
									xtype : 'container',
									items : compensatory
								}]
					}, */
						{
						title : '<span class="displantab">还款中</span>',
						items : [{
									xtype : 'container',
									items : repayment
								}]
					},
					//	                	{
					//	                  title: '<span class="displantab">代偿列表</span>',
					//		                items:[{
					//		                	xtype: 'container',
					//		    	            items:repayment
					//		    	        }]
					//	                },
					{
						title : '<span class="displantab">招标中</span>',
						items : [{
									xtype : 'container',
									items : biddloan
								}]
					}, {
						title : '<span class="displantab">已结清</span>',
						items : [{
									xtype : 'container',
									items : settled
								}]
					}/*, 
					{
						title : '<span class="displantab">申请中</span>',
						items : [{
									xtype : 'container',
									items : appliloan
								}]
					}*/
					]
		});
		this.callParent([config]);
	}

});
