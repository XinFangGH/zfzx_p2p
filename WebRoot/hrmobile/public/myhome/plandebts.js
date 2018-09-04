
//creditorList.js
Ext.define('hrmobile.public.myhome.plandebts', {
    extend: 'Ext.TabPanel',
	name: 'plandebts',
	constructor: function (config) {
	config = config || {};
	var	canTransferingList= Ext.create('hrmobile.public.myhome.plandebts.canTransferingList',{
    });
	var	transferingList= Ext.create('hrmobile.public.myhome.plandebts.transferingList',{
    });
	var	transferedList= Ext.create('hrmobile.public.myhome.plandebts.transferedList',{
    });
	var	buyedList= Ext.create('hrmobile.public.myhome.plandebts.buyedList',{
    });
	var	closeedList= Ext.create('hrmobile.public.myhome.plandebts.closeedList',{
    });

    Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>债权交易</font>",
			layoutOnTabChange: true,
	        tabBar:  
	        {   defaults:{flex:1},
	            layout:{  
	                pack:'center'
	            },
	           style: 'padding: 0em;border-color: rgb(158, 35, 40);margin:10px 15px 5px 15px;border-radius: 2px;background:#fff;height:40px;line-height:40px;border: 2px solid '+themeColor+';border-radius:40px;'
	        },
	        items: [
	                {
	                  title: '可交易',
		                items:[{
		                	xtype: 'container',
		    	            items:canTransferingList
		    	        }]
	                },{
	                  title: '交易中',
		                items:[{
		                	xtype: 'container',
		    	            items:transferingList
		    	        }]
	                },{
	                  title: '已卖出',
		                items:[{
		                	xtype: 'container',
		    	            items:transferedList
		    	        }]
	                },{
	                  title: '已购买',
		                items:[{
		                	xtype: 'container',
		    	            items:buyedList
		    	        }]
	                },{
	                  title: '已关闭',
		                items:[{
		                	xtype: 'container',
		    	            items:closeedList
		    	        }]
	                }
	            ]
    	});
    	this.callParent([config]);
    }

});
