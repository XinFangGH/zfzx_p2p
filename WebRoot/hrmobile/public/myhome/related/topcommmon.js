
/**
 * 手机功能菜单
 * by cjj
 */

Ext.define('hrmobile.public.myhome.related.topcommmon', {
    extend: 'Ext.Container',
    
    name:'topcommmon',
    
    constructor:function(config){

		Ext.apply(config,{/*docked:'top',*/laout:'hbox',items:[
			{html:"<div class='account_member'><div class='user'>Hi,"+(null==curUserInfo?"":curUserInfo.loginname)+"</div><span>邀请码：</span>" +
							            "<div >账户余额:"+moneyFormat((null==curUserInfo?"":curUserInfo.availableInvestMoney))+"元&nbsp;&nbsp;&nbsp;&nbsp;<td>累计收益：</td></div>"+
				"<div style='margin: 8% 6% 8% 6%;'><img onclick='javascript:loaddata1();' style='width: 31%;margin-right: 10%;margin-right: 10%;' src='"+__ctxPath+"/hrmobile/resources/imagesP2p/but01.png'/> "+
	    		"<img onclick='javascript:loaddata1();' style='width: 31%;' src='"+__ctxPath+"/hrmobile/resources/imagesP2p/but02.png'/></div>"+
	    		"</div>"},
			]});
		
		this.callParent([config]);
  
	  loaddata1 = function() {
		  mobileNavi.push(
		            Ext.create('hrmobile.public.myhome.rechargeAndwithdraw',{
		        	})
		    	);
	  }
	  
    }
    
});