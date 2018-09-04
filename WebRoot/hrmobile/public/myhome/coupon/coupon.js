//upAndDownContract.js

Ext.define('hrmobile.public.myhome.coupon.coupon', {
	extend: 'Ext.TabPanel',
    
    name: 'coupon',

    constructor: function (config) {
    	this.data1=config;
    	var data=config;
    	
    	
	     var couponList1=Ext.create('hrmobile.public.myhome.coupon.couponList',{
    	 		flag:'wsy' //未使用
	     }); 
	     var couponList2=Ext.create('hrmobile.public.myhome.coupon.couponList',{
    	 		flag:'ysy'//已使用
	     });
	     var couponList3=Ext.create('hrmobile.public.myhome.coupon.couponList',{
		    	flag:'ygq' //已过期
	     }); 
	     var couponList4=Ext.create('hrmobile.public.myhome.coupon.couponList',{
	    	flag:'xlc' //
	     }); 

		config = config || {};
	    Ext.apply(config,{
        	title:'<font color='+topColor+' style="font-size:'+topsize+'">优惠券</font>',
//            layoutOnTabChange: true,
            style:"margin-top:42px;",
	        tabBar:  
	        {   defaults:{flex:1},
	            layout:{  
	                pack:'center'
	            },
	           style: 'border-color:#9E2328;background:#eee;margin:5px 10px 0px 10px;font-color:#000;border-radius:2px;'
	        },
            items: [
	            
                {
            	title: '<div style="font-size:1em;">未使用</div>',
                items:[{
                	xtype: 'container',
    	            items:couponList1
    	        }]
                },{
	            	title: '<div style="font-size:1em;">已使用</div>',
	                items:[{
	                	xtype: 'container',
	    	            items:couponList2
	    	        }]
	            },
	            {
	            	title: '<div style="font-size:1em;">已过期</div>',
	                items:[{
	                	xtype: 'container',
	    	            items:couponList3
	    	        }]
	            }
//	            ,
//	            {
//	            	title: '<div style="font-size:1em;">鑫里程</div>',
//	            	hidden:true,
//	                items:[{
//	                	xtype: 'container',
//	    	            items:couponList4
//	    	        }]
//	            }
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

