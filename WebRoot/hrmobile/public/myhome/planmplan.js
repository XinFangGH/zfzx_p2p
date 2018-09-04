
//creditorList.js
Ext.define('hrmobile.public.myhome.planmplan', {
    extend: 'Ext.TabPanel',
	name: 'planmplan',
	constructor: function (config) {
	config = config || {};
	var	mmplanbackin= Ext.create('hrmobile.public.myhome.mmplan.mmplanbackin',{
    });
	var	mmplanbidding= Ext.create('hrmobile.public.myhome.mmplan.mmplanbidding',{
    });
    var	mmplansettled= Ext.create('hrmobile.public.myhome.mmplan.mmplansettled',{
    });
    var	mmplanfailure= Ext.create('hrmobile.public.myhome.mmplan.mmplanfailure',{
    });
    var	mmplanoutList= Ext.create('hrmobile.public.myhome.mmplan.mmplanoutList',{
    });
    Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>理财计划</font>",
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
	                  title: '购买中',
		                items:[{
		                	xtype: 'container',
		    	            items:mmplanbackin
		    	        }]
	                },{
	                  title: '持有中',
		                items:[{
		                	xtype: 'container',
		    	            items:mmplanbidding
		    	        }]
	                },{
	                  title: '已完成',
		                items:[{
		                	xtype: 'container',
		    	            items:mmplansettled
		    	        }]
	                },{
	                  title: '已退出',
		                items:[{
		                	xtype: 'container',
		    	            items:mmplanoutList
		    	        }]
	                },{
	                  title: '已失败',
		                items:[{
		                	xtype: 'container',
		    	            items:mmplanfailure
		    	        }]
	    	        }
	            ]
    	});
    	this.callParent([config]);
    }

});
