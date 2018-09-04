//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.apply.loanapply', {
    extend: 'Ext.form.Panel',
    name: 'loanapply',
    constructor: function (config) {
    	loandata=config.data;
    	config = config || {};
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
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						},
			           {	xtype: 'textfield',
	                        label: "借款标题：",
	                        clearIcon: false,
	                        inputCls:"loaninput",
	                        placeHolder:"请输入借款标题",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"loanTitle"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						}, 
						{
						   xtype : 'selectfield', 
                           label: '借款用途:',
                           labelWidth:'30%',
                           inputCls:"loaninput",
                           style:"margin:5px 0px 5px 15px",
                           name:"financeApply.productName",
                           options: [
                                     {text: '流动资金周转',  value: '1'},
                                     {text: '固定资产投资贷款', value: '2'},
                                     {text: '项目融资贷款',  value: '3'},
                                     {text: '其他用途',  value: '4'},
                                     {text: '个人消费',  value: '5'}
                                 ]
	                    },
	                    {
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						},{
							xtype: 'numberfield',
	                        label: "借款金额：",
	                        inputCls:"loaninput",
	                        placeHolder:"单位（元）",
	                        clearIcon: false,
	                        style:"margin:5px 0px 5px 15px",
	                        name:"productmoney",
	                        listeners: {
		    				   	scope : this,
		    			        change: function(field, newValue, oldValue) {
		    			        	var productmoney=this.getCmpByName("productmoney").getValue();
		    			        	var monthmoney=this.getCmpByName("monthmoney");
		    			        	 monthmoney.setValue("￥"+moneyFormat(productmoney/200));
			    			    }
		    			    }
	                        	
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						},{
							   xtype : 'selectfield', 
	                           label: '借款期限:',
	                           labelWidth:'30%',
	                           inputCls:"loaninput",
	                           style:"margin:5px 0px 5px 15px",
	                           name:"loanTimeLen",
	                           options: [
	                                     {text: '3个月',  value: '3'},
	                                     {text: '6个月', value: '6'},
	                                     {text: '9个月',  value: '9'},
	                                     {text: '23个月',  value: '23'},
	                                 ],
		                       listeners: {
			    				   	scope : this,
			    			        change: function(field, newValue, oldValue) {
				    			        var ratevalue;
				    			        var ratevaluetran;
				    			        if(newValue=="6"){
				    			        	ratevalue="6%";
				    			        	ratevaluetran="贷款本金的6%";
				    			        }else if(newValue=="9"){
				    			        	ratevalue="9%";
				    			        	ratevaluetran="贷款本金的9%";
				    			        }else if(newValue=="23"){
				    			        	ratevalue="3%";
				    			        	ratevaluetran="贷款本金的32%";
				    			        }
				    			         var dimension=this.getCmpByName("financeApply.RATDE");
				    			         dimension.setValue(ratevalue);
				    			         var loanadmin=this.getCmpByName("loanadmin");
				    			         loanadmin.setValue(ratevalue);
				    			         var translate=this.getCmpByName("translate");
				    			         translate.setValue(ratevaluetran);
				    			    }
			    			    }
		                    },{
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						},{
							xtype: 'textfield',
	                        label: "年化利率：",
	                        inputCls:"loaninput",
	                        value:"3%",
	                        disabled:true,
	                        style:"margin:5px 0px 5px 15px",
	                        name:"financeApply.RATDE"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;background-color: #d7d7d7'></div>"
						    
						},{
							xtype: 'textfield',
	                        label: "还款方式：",
	                        value:"按期付息，到期还本",
	                        inputCls:"loantextinput",
	                        disabled:true,
	                        style:"margin:5px 0px 5px 15px",
	                        name:"financeApply.productName"
	                      
	                        
	                    },{
	                    	xtype: 'textfield',
	                        label: "月还金额：",
	                        value:"0.00（支付给理财人）",
	                        inputCls:"loantextinput",
	                        disabled:true,
	                        style:"margin:5px 0px 5px 15px",
	                        name:"monthmoney"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;background-color: #d7d7d7'></div>"
						    
						},{
							xtype: 'textfield',
	                        label: "管理费：",
	                        value:"3%（升升投平台收取）",
	                        inputCls:"loantextinput",
	                        disabled:true,
	                        style:"margin:5px 0px 5px 15px",
	                        name:"loanadmin"
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;background-color: #d7d7d7'></div>"
						    
						},{
							xtype: 'textfield',
	                        label: "服务费：",
	                        value:"贷款本金的3%（升升投平台收取）",
	                        inputCls:"loantextinput",
	                        disabled:true,
	                        style:"margin:5px 0px 5px 15px",
	                        name:"translate"
	                      
	                        
	                    },{
							xtype:"label",
						    html: "<div style='height:1px;background-color: #d7d7d7'></div>"
						    
						},{
							xtype: 'textfield',
	                        label: "借款描述：",
	                        style:"margin:5px 0px 5px 15px",
	                        inputCls:"loaninput",
	                        xtype: 'textareafield',
	                        maxRows: 4,
	                        minHeight:'100px',
	                        name:"loanmiaoshu"
	                      
	                        
	                    },
	                    {
	                    	xtype:"label",
	                    	style:"margin:0px 20px 0px 20px;text-align:center;",
	                        html:'<div style="font-size:12px;margin:20px 20px 20px 0px;">我已阅读并同意<span style="color:coral;" onclick="javascript:overlays();">《升升投服务协议》</span></div>'
                        
                       },{
	                    	xtype:"label",
	                    	style:"margin:0px 20px 0px 20px",
	                    	name:"loanapply",
	                    	id:"loanapply",
	                        html:""
                        
                       },{
	                    	style:"margin:20px 20px 250px 20px;background:"+themeColor+";font-color:white",
	                    	xtype: 'button',
	                        text:"<font color=white>同意并继续</font>",
	                        handler:this.next
	                    }
	          
                 ]
    	});

  

    	this.callParent([config]);
    	overlays=function(){
	    	this.overlay =Ext.Viewport.add({
	    		xtype:'panel',
	    		modal:true,
	    		hideOnMaskTap:true,
	    		centered:true,
	    		width:'80%',
	    		height:'500px',
	    		styleHtmlContent:false,
	    		html:superhtml1,
				items:[{
					docked:'top',
					xtype:'toolbar',
					style:"background:"+themeColor+"",
					title:'<div style="font-size:17px;color:#fff;">升升投云服务协议</div>'
				},{     docked:'bottom',
                    	baseCls:"btn",
	                	xtype: 'button',
	                	margin:"10px 0px 10px",
	                	style:"background:"+themeColor+"",
	                    text:"<font color=white style='line-height:40px;'>我已同意并阅读此协议</font>",
	                    scope:this,
	                    handler:function (){
				           this.overlay.hide();
                       }
                    }],
				scrollable:true
	    		
	    	})
	    	this.overlay.show();
	}
    },
    next:function(){
    	var loanapply=Ext.getCmp('loanapply');
    	var productId=loandata.productId;		// 1
    	var productName=loandata.productName;   //青春贷
    	var flownodes=loandata.flowNode;		//nodePerson|nodeFamily|nodeWork|nodeStore|nodeOver
    	var finishState=loandata.finishState;   //finishStatus:0|0|0|0|0|
    	var currnodeid= loandata.currnodeid;	//nodePerson
    	var loanMoney =this.parent.getCmpByName("productmoney").getValue();  
    	var loanTitle=this.parent.getCmpByName("loanTitle").getValue(); 
    	var loanTimeLen=this.parent.getCmpByName("loanTimeLen").getValue(); 
    	var loanmiaoshu=this.parent.getCmpByName("loanmiaoshu").getValue(); 
    	
    	if(Ext.isEmpty(loanTitle)){
    		loanapply.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">借款标题不能为空</p></div>');
			return false;
    	}
    	if(Ext.isEmpty(loanMoney)){
    		loanapply.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">借款金额不能为空</p></div>');
			return false;
    	}
    	if(!isAmount(loanMoney)){
    		loanapply.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">借款金额格式不对</p></div>');
			return false;
    	}
    	if(Ext.isEmpty(loanTimeLen)){
			loanapply.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">借款期限不能为空</p></div>');
			return false;
		}
    	if(Ext.isEmpty(loanmiaoshu)){
			loanapply.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">借款描述不能为空</p></div>');
			return false;
		}
    	
    	Ext.Ajax.request({
					url : __ctxPath + '/financePurchase/saveApplyUserapplyUser.do',
					params:{
						isMobile:"1",
			    		"financeApplyUser.loanId":"",
			    		"financeApplyUser.productName":productName,
			    		"financeApplyUser.productId":productId,
			    		"financeApplyUser.flownodes":flownodes,
			    		"financeApplyUser.currentnode":currnodeid,
			    		"financeApplyUser.finishStatus":finishState,
			    		"financeApplyUser.monthlyInterest":"",
			    		"financeApplyUser.monthlyCharge":"",
			    		"financeApplyUser.expectAccrual":"",
				    	"financeApplyUser.state":"0",
			    		"financeApplyUser.loanTitle":loanTitle,
			    		"financeApplyUser.loanUse":curUserInfo.id,
			    		"financeApplyUser.loanMoney":loanMoney,
			    		"financeApplyUser.loanTimeLen":loanTimeLen,
			    		"financeApplyUser.payIntersetWay":"singleInterest",
			    		"financeApplyUser.remark":loanmiaoshu
					},
					success : function(response) {
						var  responseText1=response.responseText.replace(/[\n]/ig,'');
				    	var responseText = Ext.util.JSON.decode(responseText1);
				    	if (responseText.success==true) {
				    		curUserInfo=responseText.bpcustmem;
				    		getnextleixing(responseText.next);
							mobileNavi.push(Ext.create('hrmobile.public.myhome.apply.basicMessage',{}));
					    }else{
								mobileNavi.reset();
								mobileNavi.push(Ext.create('hrmobile.public.myhome.login',{}));
					    }
		            }
		});    	
	
    }

});
