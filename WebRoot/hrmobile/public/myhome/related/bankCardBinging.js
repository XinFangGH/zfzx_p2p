
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.related.bankCardBinging', {
    extend: 'Ext.form.Panel',
    name: 'bankCardBinging',
    constructor: function (config) {
    	config = config || {};
    	Ext.apply(config,{
    		width:"100%",
		    height:"100%",
		    fullscreen: true,
		    
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>绑定提现银行卡</font>",
		    scrollable:{
		    	direction: 'vertical'
		    },
		    defaults:{
		                	xtype: 'textfield',
		                	labelAlign:"top"
		                },
		                items: [/*{
		                	     xtype:'label',
		                         html:'<div class="tx_card"><div class="title">开户名称：裴蔚</div><p>银行卡必须为"裴蔚"的借记卡，否则提现失败	</p></div></div>'
		                        
		                    },*/
		                         {
		                          xtype:"hiddenfield",
		                          name:"webBankcard.bankname"
		                         
		                         },
		                    	{
		                        label: '<div class="tx_card"><div class="title" style="font-size:1.2em">开户名称：'+(null==curUserInfo?"":curUserInfo.truename)+'</div><br><p>银行卡必须为"'+(null==curUserInfo?"":curUserInfo.truename)+'"的借记卡，否则提现失败	</p></div></div><div class="fieldlabel">开户银行:</div>',
		                        name:"webBankcard.bankId",
		                        xtype : 'selectfield', 
		                         store:Ext.create('StoreSelect', {
				          	        url : __ctxPath + "/pay/bindCardPay.do"
				                }),
				                listeners : {
									scope : this,
				                    change:function(selectfield, newValue, oldValue, eOpts){
				                    	var number=selectfield.getStore().findExact("value",newValue,0);
				                    	var text=selectfield.getStore().getAt(number).data.text;
				                    	
				                    	var bankname=this.getCmpByName("webBankcard.bankname");
				                    	bankname.setValue(text);
				                    	/*var obj=this.parent.parent.getCmpByName("provinceId");
				                    	obj.store=Ext.create('StoreSelect', {
						          	        url : __ctxPath + "/mobile/getListBankCodeMobile.do"
						                })*/
				                 
				                 }
				                
				                }
		                        
		                        
		                    },{
		                    	xtype:"textfield",
		                        label:"<div class='fieldlabel'>银行卡号:</div>",
		                         name:"webBankcard.cardNum"
		                    },{
		                          xtype:"hiddenfield",
		                          name:"webBankcard.provinceName"
		                         
		                         },{
		                       xtype : 'panel',
		                       layout: {
									type: 'hbox'
								},
								 defaults:{
				                	labelAlign:"top"
				                },
		                       items:[
				                       {
				                        width:"50%",
				                        xtype : 'selectfield', 
				                        label:"<div class='fieldlabel'>开户地区所在省:</div>",
				                        name:"webBankcard.provinceId",
				                        store:Ext.create('StoreSelect', {
								          	        url : __ctxPath + "/mobile/getListBankCodeMobile.do"
								                })
				                    
				                        ,
						                listeners : {
											scope : this,
						                    change:function(selectfield, newValue, oldValue, eOpts){
						                    	var number=selectfield.getStore().findExact("value",newValue,0);
						                    	var text=selectfield.getStore().getAt(number).data.text;
						                    	var provinceName=this.getCmpByName("webBankcard.provinceName");
						                    	provinceName.setValue(text);
						                    	
						                    	var obj=this.parent.parent.getCmpByName("webBankcard.cityId");
						                    	var store=Ext.create('StoreSelect', {
								          	        url : __ctxPath + "/mobile/getListBankCodeMobile.do?parentCode="+newValue
								                });
								                obj.setStore(store);
								                
						                 
						                 }
						                 
						                 
						                 }
				                    }
				                  ,{
		                          xtype:"hiddenfield",
		                          name:"webBankcard.cityName"
		                         
		                         },
				                    {
				                        width:"50%",
				                        xtype : 'selectfield',
				                      //  style:"padding:0 0 0 4px;",
				                        label:"市: ",
				                        name:"webBankcard.cityId",
				                        store:null,
						                listeners : {
											scope : this,
						                    change:function(selectfield, newValue, oldValue, eOpts){
						                    	var number=selectfield.getStore().findExact("value",newValue,0);
						                    	var text=selectfield.getStore().getAt(number).data.text;
						                    	
						                    	var cityName=this.getCmpByName("webBankcard.cityName");
						                    	cityName.setValue(text);
						                    	
						                    
								                
						                 
						                 }
						                 
						                 
						                 }
				                    }
				                       ]
		                    
		                    	
		                    },{
		                    
		                    style:"margin:20px;background:"+themeColor+";font-color:white",
					    	xtype: 'button',
					        text:"<font color=white>绑定提现银行卡</font>",
					        handler:this.binding
		                    }
		          
		          ]
		
		                    	
		                    
    	});

  

    	this.callParent([config]);
    	
    },
    binding:function(){
    	
    	//thirdInterface/bindCardWebBankcard.do?retUrl=financePurchase/withdrawFinancePurchase.do
		 
		var loginForm = this.up('formpanel');
		var webBankcardcardNum= loginForm.getCmpByName("webBankcard.cardNum");
		if(Ext.isEmpty(webBankcardcardNum.getValue())){
			 Ext.Msg.alert("", "银行卡不能为空");
			 return;
		}
		
		 if (loginForm.validate(loginForm.items)) {
           loginForm.submit({
					url : __ctxPath + '/thirdInterface/bindCardWebBankcard.do?retUrl=financePurchase/withdrawFinancePurchase.do',
					params:{
						'isMobile':"1"
					},
				     success: function(form,action,response) {
		        	var responseText = Ext.util.JSON.decode(response);
					if (responseText.success==true) {
					   Ext.Msg.alert("", responseText.msg);
					   mobileNavi.pop();
					   mobileNavi.getActiveItem().getStore().loadPage(1);
					
					}else{
					   Ext.Msg.alert("", responseText.msg);
					    mobileNavi.pop();
					}
				   	
				   	}
			});    	
		 }
    }

});
