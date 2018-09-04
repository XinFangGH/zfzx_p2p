
Ext.define('hrmobile.public.myhome.planbid.basicinformation', {
    extend: 'Ext.TabPanel',
    name: 'basicinformation',
    constructor: function (config) {
	config = config || {};
	var	backin= Ext.create('hrmobile.public.myhome.planbid.backin',{
    });
	var	bidding= Ext.create('hrmobile.public.myhome.planbid.bidding',{
    });
    var	settled= Ext.create('hrmobile.public.myhome.planbid.settled',{
    });
    var	failure= Ext.create('hrmobile.public.myhome.planbid.failure',{
    });
    var	experience= Ext.create('hrmobile.public.myhome.planbid.experience',{
    });
    Ext.apply(config,{
			title:"<font color=#ffffff style='font-size:"+topsize+"'>散标理财管理</font>",
			layoutOnTabChange: true,
        	style:"margin-top:42px;",
			tabBar:  
	        {   defaults:{flex:1},
	            layout:{  
	                pack:'center'
	            },
	           style: 'border-color:#9E2328;background:#eee;margin:5px 10px 0px 10px;font-color:#000;border-radius:2px;display:table !important;'
	        },
	        items: [
	                {
	                  title: '<span class="displantab">回款中</span>',
		                items:[{
		                	xtype: 'container',
		    	            items:backin
		    	        }]
	                },{
	                  title: '<span class="displantab">投标中</span>',
		                items:[{
		                	xtype: 'container',
		    	            items:bidding
		    	        }]
	    	        },{
	                  title: '<span class="displantab">已结清</span>',
		                items:[{
		                	xtype: 'container',
		    	            items:settled
		    	        }]
	    	        },{
	                  title: '<span class="displantab">已关闭</span>',
		                items:[{
		                	xtype: 'container',
		    	            items:failure
		    	        }]
	    	        },{
	                  title: '<span class="displantab">体验标</span>',
		                items:[{
		                	xtype: 'container',
		    	            items:experience
		    	        }]
	    	        }
	            ],
			listeners:{
                painted:function(){
                    $('#navbar').css({"position":"fixed","width":"100%","z-index":"9"});
                }
			}
    	});
    	this.callParent([config]);
    }

});
