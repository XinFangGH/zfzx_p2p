
/**
 * 手机功能菜单
 * by cjj
 */

Ext.define('hrmobile.public.myhome.related.topIndex', {
    extend: 'Ext.Container',
    
    name:'topIndex',
    
    constructor:function(config){

		Ext.apply(config,{docked:'top',/*scrollDock:'top',*/laout:'hbox',items:[
			{html:" <div class='banner'><img src='"+__ctxPath+"/hrmobile/resources/imagesP2p/banner.jpg' /></div>"},
			{
			html:"<div class='zjtj'>"+
			"<div class='left fl'><p style='font-size: 0.75em;'>累计交易</p><span id='indexobjs0'>0.00</span></div>"+
			"<div class='center fl'><p style='font-size: 0.75em;'>累计还款</p><span id='indexobjs1'>0.00</span></div>"+
			"<div class='right fr'><p style='font-size: 0.75em;'>累计收益</p><span id='indexobjs2'>0.00</span></div>"+
			"<div class='cb'></div>"+
		    "</div>"
			}]});
		
		this.callParent([config]);
	  /* var s=0;
	   var interval = window.setInterval(function() {
				s=s+parseFloat(Math.floor((Math.random())*1000));
					var indexobjs0obj=document.getElementById("indexobjs0");
					    indexobjs0obj.innerHTML=s;
					var indexobjs1obj=document.getElementById("indexobjs1");
					    indexobjs1obj.innerHTML=s;
					var indexobjs2obj=document.getElementById("indexobjs2");
					    indexobjs2obj.innerHTML=s;

			}, 0.1);
		可以定时刷新	  	var this1=this;
    	Ext.Ajax.request({
					url : __ctxPath + '/user/mystatisticalBpCustMember.do',
					params:{
					   isMobile : "1"
					},
				   	success : function(response) {
				    	var responseText = Ext.util.JSON.decode(response.responseText);
							if (responseText.success==true) {
								var objs=responseText.errMsg.split(",");
								 var obj1=  this1.items.items[1];
								 obj1.setHtml("<div class='zjtj'>"+
								"<div class='left fl'><span id='objs0'>"+moneyFormat(objs[0])+"</span>累计交易</div>"+
								"<div class='center fl'><span id='objs1'>"+moneyFormat(objs[1])+"</span>累计还款</div>"+
								"<div class='right fr'><span id='objs2'>"+moneyFormat(objs[2])+"</span>累计收益</div>"+
								"<div class='cb'></div>"+
							"</div>");
							window.clearInterval(interval);
							var objs0=parseFloat(objs[0]);
							var objs1=parseFloat(objs[1]);
							var objs2=parseFloat(objs[2]);
						    var indexobjs0obj=document.getElementById("indexobjs0");
							    indexobjs0obj.innerHTML=moneyFormat(objs[0]);
							var indexobjs1obj=document.getElementById("indexobjs1");
							    indexobjs1obj.innerHTML=moneyFormat(objs[1]);
							var indexobjs2obj=document.getElementById("indexobjs2");
							    indexobjs2obj.innerHTML=moneyFormat(objs[2]);
							}else{
							    mobileNavi.push(
						            Ext.create('hrmobile.public.myhome.login',{
						        	})
						    	);
	  
							
							}
								
				
				}
			});   
    },
	loaddata:function(){
            	Ext.Ajax.request({
					url : __ctxPath + '/user/mystatisticalBpCustMember.do',
					params:{
					},
				   	success : function(response) {
				   		alert(response.responseText);
				       var responseText = Ext.util.JSON.decode(response.responseText);
				   	}
				   	});
	*/
	}
});