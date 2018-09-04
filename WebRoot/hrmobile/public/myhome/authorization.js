
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.authorization', {
    extend: 'Ext.Container',
    name: 'authorization',
    constructor: function (config) {
    var	topcommmon= Ext.create('hrmobile.public.myhome.related.topcommmon',{
    });
    var bottomBar= Ext.create('hrmobile.public.myhome.related.bottomBar',{
    });
    	config = config || {};
    	Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>授权管理</font>",
    		width:"100%",
		    height:"100%",
		    fullscreen: true,
		    scrollable:{
		    	direction: 'vertical'
		    },
		    items: [topcommmon,
		    	{
		    		 xtype: 'togglefield',
			         name: 'bid',
			         label: '投标授权',
			         value:1
		    	},{
		    		 xtype: 'togglefield',
			         name: 'repayment',
			         label: '还款授权',
			         value:1
		    	},
		    	{
		    		 xtype: 'togglefield',
			         name: 'allocation',
			         label: '分配授权',
			         value:1
		    	} ,
		    	{
                	style:"padding-top:10px;background:"+themeColor+";font-color:white",
                	xtype: 'button',
                    text:"<font color=white>提交授权</font>",
                    handler:this.submit
                   
                }
                
		            
		    ,bottomBar]
    	});

  

    	this.callParent([config]);
    	
    },
    submit:function(){
        mobileNavi.push(
 		Ext.create('hrmobile.public.myhome.main',{
 			        data:this.data
 		        	})
 		    	);
     }

});
