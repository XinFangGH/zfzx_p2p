
//creditorList.js
Ext.define('hrmobile.public.myhome.record', {
    extend: 'Ext.TabPanel',
	name: 'record',
	constructor: function (config) {
	config = config || {};
	var	itoInvestList= Ext.create('hrmobile.public.myhome.itoInvestList',{
    });
//	var	mmplanList= Ext.create('hrmobile.public.myhome.mmplanList',{
//    });
    Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>散标理财管理</font>",
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
	                  title: '回款中',
	                  badgeText: '4',
		                items:[{
		                	xtype: 'container',
		    	            items:itoInvestList
		    	        }]
	                },{
	                  title: '投标中',
		                items:[{
		                	xtype: 'container',
		    	            items:itoInvestList
		    	        }]
	                },{
	                  title: '已结清',
		                items:[{
		                	xtype: 'container',
		    	            items:itoInvestList
		    	        }]
	                },{
	                  title: '投标失败',
		                items:[{
		                	xtype: 'container',
		    	            items:itoInvestList
		    	        }]
	                },{
	                  title: '体验标',
		                items:[{
		                	xtype: 'container',
		    	            items:mmplanList
		    	        }]
	    	        }
	            ]
    	});
    	this.callParent([config]);
    }

});
