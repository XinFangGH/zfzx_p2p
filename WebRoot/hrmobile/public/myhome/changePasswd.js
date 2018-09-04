
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.changePasswd', {
    extend: 'Ext.Container',
    name: 'changePasswd',
    constructor: function (config) {
	    var	topcommmon= Ext.create('hrmobile.public.myhome.related.topcommmon',{
	    });
	  /*  var bottomBar= Ext.create('hrmobile.public.myhome.related.bottomBar',{
	    });*/
    	config = config || {};
    	Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>密码修改</font>",
    		width:"100%",
		    height:"100%",
		    fullscreen: true,
		    scrollable:{
		    	direction: 'vertical'
		    },
		    items: [topcommmon,{
                xtype: 'fieldset',
                defaults:{
                	xtype: 'textfield',
                	labelAlign:"top"
                	
                },
                items: [{
                        label: "<div style='font-size:18px'>原登录密码</div>",    
                        placeHolder:"请输入原登录密码"
                    },
                    {
                        label: "<div style='font-size:18px'>新登录密码</div>",
                        placeHolder:"请输入新的登录密码"
                    }  
                   ,
                    {
                        label: "<div style='font-size:18px'>确认新登录密码</div>",
                        placeHolder:"请确认新登录密码"
                    }   
                   ,
                    {
                    	style:"padding-top:10px;background:"+themeColor+";font-color:white",
                    	xtype: 'button',
                        text:"<font color=white>修改</font>",
                        handler:this.submit
                       
                    }
          
          ]}/*,bottomBar*/]
		    	
		    	
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
