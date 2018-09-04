Ext.define('hrmobile.public.myhome.related.payKitingTab', {
	extend: 'Ext.form.Panel',
    name: 'payKitingTab',
    constructor: function (config) {
		config = config || {};
	    Ext.apply(config,{
        	width:"100%",
		    height:"100%",
		    fullscreen: true,
		    title:"<font color="+topColor+" style='font-size:"+topsize+"'>贷款试算</font>",
            scrollable:{
		    	direction: 'vertical'
		    },

    		items: [{
                        label: "<div class='fieldlabel'>充值金额:</div>",
                        name:"username",
                        xtype:"textfield"
		                        
		           },{
                    	style:"margin:20px;background:"+themeColor+";font-color:white",
                    	xtype: 'button',
                        text:"<font color=white>充值</font>",
                        handler:this.paykitingbtn
		           }]
	        });
    
        this.callParent([config]);
	}

});