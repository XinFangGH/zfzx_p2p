
/**
 * 手机功能菜单
 * by cjj
 */

Ext.define('hrmobile.public.myhome.related.bottomBarIndex', {
    extend: 'Ext.SegmentedButton',
    name:'bottomBarIndex',
    
    constructor:function(config){
	Ext.apply(config,{
		docked:'bottom',
		laout:'hbox',
		allowDepress :false,
		style:"    border-top: 1px solid #E7E7E7;position:fixed;left:0;bottom:0;width:100%;",
			items:[{	
						width:"33.33%",
						style:'border-radius:0px; padding:0;border:0;border-top:1px solid #ddd;',
						height:55,
						text:"首页",
						style:"padding-top: 37px; font-size: 14px;",
				    	cls:'home_select',
				        this1:this,
						handler:this.toindex},
					{	
						style:'border-radius:0px; padding:0;border:0;border-top:1px solid #ddd;',
						cls:'wytz',
						hiddenCls:'wytz_select',
						this1:this,
						width:"33.33%",
						height:55,
						text:"投资",
						style:"padding-top: 37px; font-size: 14px;",
					    handler:this.toInvset},
					/*{	
						cls:'wyjk',
					    style:'border-radius:0px; padding:0;border:0;border-top:1px solid #ddd;',
						this1:this,
						width:"25%",
						height:55,
						text:"借款",
						style:"padding-top: 37px; font-size: 14px;",
						handler:this.borrowMoney
					},*/
					{	
						cls:'wdzh',
//						pressedCls:'wdzh_select',
						this1:this,
						width:"33.33%",
						height:55,
						style:'border-radius:0px; padding:0;border:0;border-top:1px solid #ddd;',
						text:"账户",
						style:"padding-top: 37px; font-size: 14px;",
						handler:this.myaccount
					}],
					listeners: {
					        toggle: function(container, button, pressed){
					        	var otherbuttonobjarray=container.items.items;
				                otherbuttonobjarray[0].setCls("home");
				                otherbuttonobjarray[1].setCls("wytz");
				                otherbuttonobjarray[2].setCls("wyjk");
//				                otherbuttonobjarray[3].setCls("wdzh");
				                var	buttonCls =button.getCls()[0].split("_")[0]+"_select";
					        	button.setCls(buttonCls);
					        	if($(button).attr('id')!='ext-button-1'){
									$('#ext-button-1').removeClass('home_select').addClass('home');
								}
					        }


					   }	
			});
		
		this.callParent([config]);
		
    },
	toindex:function(){
				mobileNavi.reset();
				mobileNavi.getNavigationBar().hide();
			},
	toInvset: function(){
		if(mobileNavi.getActiveItem().$className !="hrmobile.public.myhome.investManage"){
		        mobileNavi.reset();
				mobileNavi.push(
			    Ext.create('hrmobile.public.myhome.investManage',{}));
			    //mobileNavi.getNavigationBar().getBackButton().hide();

               mobileNavi.getNavigationBar().hide();

			}
	},
	borrowMoney: function(){
	     	if(mobileNavi.getActiveItem().$className !="hrmobile.public.myhome.loanList"){
				mobileNavi.push(Ext.create('hrmobile.public.myhome.loanList',{}));
				mobileNavi.getNavigationBar().getBackButton().hide();
		     }
			},
	 myaccount: function(){
	 	
	    if(curUserInfo==null){
	    		mobileNavi.reset();
		        mobileNavi.push(Ext.create('hrmobile.public.myhome.login',{}));
	  			mobileNavi.getNavigationBar().getBackButton().hide();
	     }else{
	     	if(mobileNavi.getActiveItem().$className !="hrmobile.public.myhome.main"){
				mobileNavi.reset();
				mobileNavi.push(Ext.create('hrmobile.public.myhome.main',{}));
				mobileNavi.getNavigationBar().getBackButton().hide();
		     }
	     }
			},
	toptgg: function(){
		if(mobileNavi.getActiveItem().$className !="hrmobile.public.myhome.notice"){
		        mobileNavi.reset();
				mobileNavi.push(Ext.create('hrmobile.public.myhome.notice',{}));
	            mobileNavi.getNavigationBar().getBackButton().hide();
		}
	}
});