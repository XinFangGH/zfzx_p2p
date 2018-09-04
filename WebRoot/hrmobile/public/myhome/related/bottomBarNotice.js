
/**
 * 手机功能菜单
 * by cjj
 */

Ext.define('hrmobile.public.myhome.related.bottomBarNotice', {
    extend: 'Ext.Container',
    
    name:'bottomBarNotice',
    
    constructor:function(config){

		Ext.apply(config,{docked:'bottom',laout:'hbox',items:[
			
			{
			xtype:"panel",
			layout: {
						type: 'hbox',
						align: 'middle'
					},
			items:[{
				        xtype:'button',
						text:"<img  style='width:100%; height:auto; ' src='hrmobile/resources/imagesP2p/home.jpg'/>",
						width:"25%",
						style:'border-radius:0px; padding:0;border:0',
						handler:this.toindex},
					{
						xtype:'button',
						style:'border-radius:0px; padding:0;border:0',
						text:"<img  style='width:100%; height:auto; ' src='hrmobile/resources/imagesP2p/wytz.jpg' />",
						width:"25%",
					    handler:this.toInvset},
					{
						xtype:'button',
						text:"<img  style='width:100%; height:auto; '  src='hrmobile/resources/imagesP2p/wdzh.jpg' />",
						width:"25%",
						style:'border-radius:0px; padding:0;border:0',
						handler:this.myaccount},
					{
						xtype:'button',
						text:"<img style='width:100%; height:auto; ' src='hrmobile/resources/imagesP2p/ptgg_select.jpg' />",
						width:"25%",
						style:'border-radius:0px; padding:0;border:0',
						handler:this.toptgg}]		
			
			}]});
		
		this.callParent([config]);
		
    },
	toindex:function(){
				mobileNavi.reset();
	    
			},
	toInvset: function(){
		 mobileNavi.reset();
				mobileNavi.push(
			      Ext.create('hrmobile.public.myhome.itoInvestList',{
			        	})
			    	);
	    
			},
	 myaccount: function(){
	 	
			  if(curUserInfo==null){
		        mobileNavi.push(
							Ext.create('hrmobile.public.myhome.login',{
						        	})
						    	);
	  
	     }else{
	     	 mobileNavi.reset();
		      mobileNavi.push(
		      Ext.create('hrmobile.public.myhome.main',{
			        	})
			    	);
			 
		     }
	    
			},
	toptgg: function(){/*
				mobileNavi.push(
			      Ext.create('hrmobile.public.myhome.notice',{
		        	})
		    	);
	
		*/}
});