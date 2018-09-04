var  width=100;
var  stateflag=false;
var  style='<span style="font-size:13px;margin-left:15px">优惠券</span>'
var datamax;
Ext.define('hrmobile.public.myhome.mmplanImmediatelyInvest', {
	extend: 'Ext.Container',
    name: 'mmplanImmediatelyInvest',
    constructor: function (config) {
		config = config || {};
		datamax=config.data;
	    Ext.apply(config,{
        	title:'<font color=#ffffff style="font-size:17px">投标详情</font>',
            width:"100%",
			height:"100%",
		    listPaging: true,
			scrollable:{
			direction: 'vertical'
			},
					    
		    items: [
		            {
		                xtype: 'panel',
		                defaults:{
		                	xtype: 'textfield',
		                	labelWidth:"100px"
		            },
		            items: [
		            	{
							xtype:"label",
						    html: "<div style='height:10px;background-color:#f3f3f3'></div>"
						    
						},{xtype: 'panel',layout: {type: 'hbox',align: 'middle'},
				                items:[{
				                    	width:"70%",	
				                    	label:"可用余额:",
				                    	style:"margin-left:10px;",
				                    	labelWidth:"40%",
				                    	html:"<div style='margin-left:15px;float:left;'>可用余额</div><font style='margin-left:80px;color:"+themeColor+";'>￥"+curUserInfo.availableInvestMoney+"元</font>"
				                    },{
				                    	style:"background:#fff;border:1px solid "+themeColor+";color:"+themeColor+";margin: 5px;font-size:13px",
				                    	text:"充值",
				                    	width:"60px",
				                    	height:"28px",
				                    	xtype: 'button',
				                    	handler:this.recharge
				                    }]
		                },{
							xtype:"label",
						    html: "<div style='height:10px;background-color: #f3f3f3'></div>"
						    
						},
						{				
				                    	xtype: 'textfield',
				                    	requiredCls:"displayflex",
									    name:"buymoney",
									    placeHolder:"请输入投资金额",
									    style:'font-size:1em;margin:30px 30px 0px 30px;border-bottom: 1px solid #eee;'
				         }	
//						{xtype: 'panel',layout: {type: 'hbox',align: 'middle'},
//						items: [Ext.create('Ext.field.Spinner', {
//											width:"73%",
//										    label     : '<span style="font-size:13px;margin-left: 15px;float:left;">投入金额:</span>',
//										    labelWidth:"40%",
//										    id:"buymoney",
//										    minValue  : 0,
//										    maxValue  : 10000,
//										    stepValue : 100,
//										    cycle     : true 
//										}),{
//				                    	style:"background:#fff;border:1px solid #f35a54;color: #f35a54;font-size:13px;",
//				                    	text:"全投",
//				                    	width:"60px",
//				                    	height:"28px",
//				                    	xtype: 'button',
//				                    	handler:this.buy
//				                    }]
//		                    }
		                    ,{
							xtype:"label",
						    html: "<div style='position: relative;height:4px;background:#e8e6e6;margin:15px auto;width: 90%;'>" +
						    	     "<div style='display:inline-block;width:"+width+"%; height: 4px;position: absolute;top: 0px;left: 0px; background: "+themeColor+";'></div>"+
						     	  "</div>"+
						     	  '<div style="width:90%;margin: 15px auto;">'+
						             '<span>可投金额</span>'+
						             '<span style="margin:15px">￥'+datamax.afterMoney+'</span>'+
						          '</div>'
						    
						},{
							xtype:"label",
						    html: "<div style='height:10px;background-color: #f3f3f3'></div>"
						},{
				            labelWidth:'36%',
				            xtype: 'textfield',
				            id:'slSmallloanProjectappUserName',
				            value:curUserInfo.fullname,
				            name:'slSmallloanProject.appUserName',
				            label:style,
				            placeHolder:"请选择",
				            readOnly:true,
				            disabled:stateflag,
							listeners : {
								scope:this,
								'focus' : function(f) {
								mobileNavi.push(Ext.create('hrmobile.public.myhome.coupon.newconpunlist',
								   	{callback:function(data){
								   	  var appUserName= Ext.getCmp("slSmallloanProjectappUserName");
								   	  var appUserId= Ext.getCmp("slSmallloanProjectappUserId");
								      appUserName.setValue(data.couponValue+"元优惠券"); 
							          couponId=data.couponId;
								   }}));
								}
							}
				        },{
							xtype:"panel",
						    html: "<div style='height:10px;background-color: #f3f3f3'></div>"+
						    	  "<p style='text-align:center;margin: 3%;'>" +
						    	  	"<span style='margin-right:70px;'>" +
						    	  		"<input type='radio' name='investType' value='1'>收益再投资" +
						    	  	"</span>" +
						    	  	"<span>" +
						    	  		"<input type='radio' checked='checked' name='investType' value='2'>提取主账户" +
						    	  	"</span>" +
						    	  "</p>"
						},{
	                       xtype: 'checkboxfield',
	                       style:'position: fixed;width:100%;',
	                       id:'agree1',
	                       checked:true,
	                       handler:this.agreement,
	                       html:"<div style='text-align:center;font-size: 13px;position: absolute; top:17px;margin-left: 15%;'>"+
                             		 "<label>我已阅读并同意《<span  onclick='javascript:agreement();' style='color:red;'>升升投云平台服务协议</span>》</label>"+
                         		"</div>"
					   },{	
                    	style:"margin: 20px 10px;height:44px;background:"+themeColor+";font-color:white;margin-top: 20%;",
                    	xtype: 'button',
                        text:"<font color=white>立即投资</font>",
                        handler:this.buy
                    }
	          
                 ]}]
	    	          
          
	            
	          
	        
            
        });
    
        
        this.callParent([config]);
        
	},
	recharge:function(){
		mobileNavi.push(Ext.create('hrmobile.public.myhome.rechargeAndwithdraw',{}));
	},
	buy:function(){
			var investType=document.getElementsByName("investType");
		  	 var investTypeValue;
		  	 for(var i=0;i<investType.length;i++){
		  		 if(investType[i].checked){
		  			 investTypeValue=investType[i].value;
		  		 }
		  	 }
			var data =datamax;
		    var buystates=true;
			var userMoney=""+this.parent.getCmpByName("buymoney").getValue()+"";
	        if(curUserInfo==null){
	                mobileNavi.push(Ext.create('hrmobile.public.myhome.login',{}));
	        }else{
	        	ajaxgetUser();
	        	if(Ext.isEmpty(userMoney)){
					Ext.Msg.alert('', "投资金额不能为空");
					return;
	        	}
	        	if(isAmountErr(userMoney)){
		  	    	Ext.Msg.alert('', '投标金额格式不对');
				    return;
				}
				if(parseFloat(userMoney)>curUserInfo.availableInvestMoney){
		        	Ext.Msg.alert('', '投标金额不能大于账户余额')
				  	return;
				}
		        if(parseFloat(userMoney)>parseFloat(data.afterMoney)){
		        	Ext.Msg.alert('', '投标金额不能大于可投金额')
				    return;
				}
	         	var url = __ctxPath + '/creditFlow/financingAgency/buyBidplanPlManageMoneyPlan.do?isMobile=1&plBidInfo.userMoney='+userMoney+'&plBidInfo.bidId='+data.mmplanId+'&investType='+investTypeValue;
				if(!Ext.isEmpty(data.formtoken)){
					 url=url+"&formtoken="+data.formtoken
				    }
	         	window.open(url,"_blank");
	  }
	
	}
 
});

