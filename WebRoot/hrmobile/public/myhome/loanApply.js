
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.loanApply', {
    extend: 'Ext.form.Panel',
    name: 'loanApply',
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
	                        name:"loantitle"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						},{
	                        label: "借款用途：",
	                        inputCls:"loaninput",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"loanusage"
	                      
	                       
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						},{
	                        label: "借款金额：",
	                        inputCls:"loaninput",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"loanmoney"
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						},{
	                        label: "借款期限：",
	                        inputCls:"loaninput",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"loanterm"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						},{
	                        label: "年华利率：",
	                        inputCls:"loaninput",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"loanyearrate"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;background-color: #d7d7d7'></div>"
						    
						},{
	                        label: "月还金额：",
	                        value:"1500（支付给理财人）",
	                        inputCls:"loantextinput",
	                        readOnly:true,
	                        style:"margin:5px 0px 5px 15px",
	                        name:"monthmoney"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;background-color: #d7d7d7'></div>"
						    
						},{
	                        label: "管理费：",
	                        value:"1500（升升投平台收取）",
	                        inputCls:"loantextinput",
	                        readOnly:true,
	                        style:"margin:5px 0px 5px 15px",
	                        name:"loanadmin"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;background-color: #d7d7d7'></div>"
						    
						},{
	                        label: "服务费：",
	                        value:"贷款本金5%",
	                        inputCls:"loantextinput",
	                        readOnly:true,
	                        style:"margin:5px 0px 5px 15px",
	                        name:"loanservice"
	                      
	                        
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
	                        name:"loandescribe"
	                      
	                        
	                    },
	                    {
	                    	xtype:"label",
	                    	style:"margin:0px 20px 0px 20px;text-align:center;",
	                        html:'<div style="font-size:12px;margin:20px 20px 20px 0px;">我已阅读并同意<span style="color:coral;" onclick="javascript:overlays();">《升升投服务协议》</span></div>'
                        
                       },{
	                    	style:"margin:20px 20px 250px 20px;background:"+themeColor+";font-color:white",
	                    	xtype: 'button',
	                        text:"<font color=white>下一步</font>",
	                        handler:this.register
	                    }
	          
                 ]}]
    	});

  

    	this.callParent([config]);
    	
    },
    register:function(){
//    	financeApplyUser.loanId:
//		financeApplyUser.productName:青春贷
//		financeApplyUser.productId:1
//		financeApplyUser.flownodes:nodePerson|nodeFamily|nodeWork|nodeStore|nodeOver
//		financeApplyUser.currentnode:nodePerson
//		financeApplyUser.finishStatus:0|0|0|0|0|
//		financeApplyUser.monthlyInterest:
//		financeApplyUser.monthlyCharge:3
//		financeApplyUser.expectAccrual:
//		financeApplyUser.state:0
//		financeApplyUser.loanTitle:梵蒂冈地方官
//		financeApplyUser.loanUse:710
//		financeApplyUser.loanMoney:1000
//		financeApplyUser.loanTimeLen:9
//		financeApplyUser.payIntersetWay:singleInterest
//		financeApplyUser.remark:10000
		var  productName=this.parent.getCmpByName("loantitle").getValue(); 
		var  loanTitle=this.parent.getCmpByName("loanusage").getValue(); 
		var  loanTitle=this.parent.getCmpByName("loanmoney").getValue(); 
		var  loanTitle=this.parent.getCmpByName("loanterm").getValue(); 
		var  loanTitle=this.parent.getCmpByName("loanyearrate").getValue(); 
		var  loanTitle=this.parent.getCmpByName("monthmoney").getValue(); 
		var  loanTitle=this.parent.getCmpByName("loanadmin").getValue(); 
		var  loanTitle=this.parent.getCmpByName("loanservice").getValue(); 
		var  loanTitle=this.parent.getCmpByName("loandescribe").getValue(); 
		
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
					url : __ctxPath + '/financePurchase/saveApplyUserapplyUser.do',
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
