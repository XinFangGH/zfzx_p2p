
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.apply.applyMessage', {
    extend: 'Ext.form.Panel',
    name: 'applyMessage',
    constructor: function (config) {
    	config = config || {};
    	var data=config;
    	Ext.apply(config,{
    		width:"100%",
		    height:"100%",
		    fullscreen: true,
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>借款申请</font>",
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
						    html: "<div style='height:1px;></div>"
						    
						},
			           {
	                        label: "借款标题：",
	                        inputCls:"loaninput",
	                        placeHolder:"请输入借款标题",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"financeApply.productName"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						},{
	                        label: "借款用途：",
	                        inputCls:"loaninput",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"financeApply.productName"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						},{
	                        label: "借款金额：",
	                        inputCls:"loaninput",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"financeApply.productName"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						},{
	                        label: "借款期限：",
	                        inputCls:"loaninput",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"financeApply.productName"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						},{
	                        label: "年华利率：",
	                        inputCls:"loaninput",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"financeApply.productName"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;background-color: #d7d7d7'></div>"
						    
						},{
	                        label: "月还金额：",
	                        value:"1500（支付给理财人）",
	                        inputCls:"loantextinput",
	                        readOnly:true,
	                        style:"margin:5px 0px 5px 15px",
	                        name:"financeApply.productName"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;background-color: #d7d7d7'></div>"
						    
						},{
	                        label: "管理费：",
	                        value:"1500（升升投平台收取）",
	                        inputCls:"loantextinput",
	                        readOnly:true,
	                        style:"margin:5px 0px 5px 15px",
	                        name:"financeApply.productName"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;background-color: #d7d7d7'></div>"
						    
						},{
	                        label: "服务费：",
	                        value:"贷款本金5%",
	                        inputCls:"loantextinput",
	                        readOnly:true,
	                        style:"margin:5px 0px 5px 15px",
	                        name:"financeApply.productName"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;background-color: #d7d7d7'></div>"
						    
						},{
	                        label: "借款描述：",
	                        style:"margin:5px 0px 5px 15px",
	                        inputCls:"loaninput",
	                        xtype: 'textareafield',
	                        maxRows: 4,
	                        minHeight:'100px',
	                        name:"financeApply.productName"
	                      
	                        
	                    },
//	                    ,{
//	                        name:"financeApply.userID",
//	                        xtype:'hiddenfield',
//	                      
//	                        
//	                    },{
//							xtype:"label",
//						    html: "<div style='height:10px;padding-top:10px;padding-left:10px; font-weight:bold;font-size:16px; color:#000;'></div>"
//						    
//						},
//						{
//	                        label: "借款标题：",
//	                        name:"financeApply.loanTitle",
//	                        style:'font-size:1em;',
//	                        required:true
//	                      
//	                        
//	                    },
//	                    {
//							xtype:"label",
//						    html: "<div style='height:10px;padding-top:10px;padding-left:10px; font-weight:bold;font-size:16px; color:#000;'></div>"
//						    
//						},
//	                    {
//	                        label: "借款人：",
//	                        placeHolder:"",
//	                        style:'font-size:1em;',
//	                        readOnly:true
//	                        
//	                    },
//	                    {
//							xtype:"label",
//						    html: "<div style='height:10px;padding-top:10px;padding-left:10px; font-weight:bold;font-size:16px; color:#000;'></div>"
//						    
//						},
//	                    {
//	                        label: "手机号码：",
//	                        name:"telphone",
//	                        placeHolder:"请输您的手机号码",
//	                        style:'font-size:1em;',
//	                        readOnly:true
//	                        
//	                    },
//	                    {
//							xtype:"label",
//						    html: "<div style='height:10px;padding-top:10px;padding-left:10px; font-weight:bold;font-size:16px; color:#000;'></div>"
//						    
//						},
//	                    {
//	                        label: "借款金额(元):",
//	                        name:"financeApply.loanMoney",
//	                        placeHolder:"请输入金额",
//	                        required:true,
//	                        style:'font-size:1em;'
//	                        
//	                    },
//	                    {
//							xtype:"label",
//						    html: "<div style='height:10px;padding-top:10px;padding-left:10px; font-weight:bold;font-size:16px; color:#000;'></div>"
//						    
//						},
//	                    {
//	                        label:"借款期限：",
//	                        name:"financeApply.loanTimeLen",
//	                        placeHolder:"请输入借款期限",
//	                        required:true,
//	                        style:'font-size:1em;'
//	                    }, 
//	                    {
//							xtype:"label",
//						    html: "<div style='height:10px;padding-top:10px;padding-left:10px; font-weight:bold;font-size:16px; color:#000;'></div>"
//						    
//						},
////	                    {
////	                        label:"年化利率(%):",
////	                        name:"financeApplyUser.expectAccrual",
////	                        required:true,
////	                        style:'font-size:1em;'
////	                    },
////	                    {
////							xtype:"label",
////						    html: "<div style='height:10px;padding-top:10px;padding-left:10px; font-weight:bold;font-size:16px; color:#000;'></div>"
////						    
////						},
//	                    {
//	                        label:"借款用途：",
//	                        name:"financeApply.loanUse",
//	                        xtype: "selectfield",
//	                          required:true,
//	                        style:'font-size:1em;',
//	                        store:Ext.create('StoreSelect', {
//								          	        url : __ctxPath + "/mobile/getloanPurposeListMobile.do"
//								                })
//				                    
//				                        
//	                    },
//	                    {
//							xtype:"label",
//						    html: "<div style='height:10px;padding-top:10px;padding-left:10px; font-weight:bold;font-size:16px; color:#000;'></div>"
//						    
//						},
//	                    {
//	                        label:"其他用途说明：",
//	                        name:"financeApply.remark",
//	                        xtype: 'textareafield',
//	                        maxRows: 4,
//	                        minHeight:'100px',
//	                        style:'font-size:1em;'
//	                    },
//	                    {
//							xtype:"label",
//						    html: "<div style='height:10px;padding-top:10px;padding-left:10px; font-weight:bold;font-size:16px; color:#000;'></div>"
//						    
//						},
	                    {
	                    	xtype:"label",
	                    	style:"margin:0px 20px 0px 20px;text-align:center;",
	                        html:'<div style="font-size:12px;margin:20px 20px 20px 0px;">我已阅读并同意<span style="color:coral;" onclick="javascript:overlays();">《升升投服务协议》</span></div>'
                        
                       },{
	                    	style:"margin:20px 20px 250px 20px;background:"+themeColor+";font-color:white",
	                    	xtype: 'button',
	                        text:"<font color=white>同意并继续</font>",
	                        handler:this.next
	                    }
	          
                 ]}]
    	});

  

    	this.callParent([config]);
    	
    },
    next:function(){
		var  loanTitle=this.parent.getCmpByName("financeApply.loanTitle").getValue(); 
		var  loanMoney=this.parent.getCmpByName("financeApply.loanMoney").getValue(); 
		var  loanTimeLen=this.parent.getCmpByName("financeApply.loanTimeLen").getValue(); 
		if(Ext.isEmpty(loanTitle)){
			 Ext.Msg.alert('', "借款标题不能为空");
			  return;
			}
			if(Ext.isEmpty(loanMoney)){
			 Ext.Msg.alert('', "借款金额不能为空");
			  return;
			}
			if(!isAmount(loanMoney)){
			 Ext.Msg.alert('', "借款金额格式不对");
			  return;
			}
			if(Ext.isEmpty(loanTimeLen)){
			 Ext.Msg.alert('', "借款期限不能为空");
			  return;
			}
        var loginForm = this.up('formpanel');
		if (loginForm.validate(loginForm.items)) {
				loginForm.submit({
					url : __ctxPath + '/financePurchase/saveApplyFinancePurchase.do',
					params:{
						isMobile:"1"
					},
				    success: function(form,action,response) {
		        	var responseText = Ext.util.JSON.decode(response);
								if (responseText.result=="1") {
									Ext.Msg.alert('', "申请成功，请等待工作人员审核!");
									mobileNavi.pop();
									
							    } else {
							      mobileNavi.reset();
							      mobileNavi.push(
							      Ext.create('hrmobile.public.myhome.login',{
						        	})
						    	);
							}
				
				}
			});    	
		}
	
    }

});
