//,
//				            	{
//									xtype:"panel",
//								    html: 
//									    "<div style='font-size: 16px;margin: 5px;border: 1px solid #eee;'>" +
//									    	"<p style='text-align:center;padding: .4em;'>" +
//									    	"<span style='margin-left:10px;color:#000;float: left;'>性    别：</span>"+
//									    	  	"<span style='margin-right:70px;'>" +
//									    	  		"<input type='radio' name='investType' value='1'>男" +
//									    	  	"</span>" +
//									    	  	"<span>" +
//									    	  		"<input type='radio' checked='checked' name='investType' value='2'>女" +
//									    	  	"</span>" +
//									    	  "</p>"+
//								    	  "<div>"
//								}	
Ext.define('hrmobile.public.myhome.apply.basicMessage', {
	    extend: 'Ext.form.Panel',
	    name: 'basicMessage',
	    constructor: function (config) {
	    	config = config || {};
	    	Ext.apply(config,{
	    	    title:"<font color="+topColor+" style='font-size:"+topsize+"'>个人信息</font>",
				width:"100%",
			    height:"100%",
		    	fullscreen: true,
			    scrollable: {
	    	        direction: 'vertical'
	    	    },
				items:[{
		                defaults:{
		                	xtype: 'textfield',
		                	labelWidth:"40%"
		            	},
		            	items:[{
			                        label: "真实姓名：",
			                        inputCls:"messagee",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:(isStringEmpty(curUserInfo.truename)?"":curUserInfo.truename),
			                        name:"financeApply.productName"
				            	}
				            	,{
			                        label: "身份证号：",
			                        inputCls:"message",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:(isStringEmpty(curUserInfo.cardcode)?"":curUserInfo.cardcode),
			                        name:"financeApply.productName"
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
			                        label: "手机号码：",
			                        inputCls:"message",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        name:"financeApply.productName",
			                        value:curUserInfo.telphone,
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
			                        label: "出生日期：",
			                        inputCls:"message",
			                        disabled:true,
			                        format : 'Y-m-d',
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:curUserInfo.birthday,
			                        name:"financeApply.productName"
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
									xtype:"selectfield",
			                        label: "最高学历：",
			                        inputCls:"message",
			                        placeHolder:"请输入最高学历",
									style:"margin: 5px;border: 1px solid #eee;",
			                        name:"financeApply.productName"
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
			                        label: "入学年份：",
			                        inputCls:"message",
			                        placeHolder:"入学年份",
			                        value:curUserInfo.collegeYear,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        name:"financeApply.productName"
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
			                        label: "毕业院校：",
			                        inputCls:"message",
			                        placeHolder:"毕业院校",
			                        value:curUserInfo.collegename,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        name:"financeApply.productName"
				            	},{
			                        label: "户口所在地：",
			                        inputCls:"message",
			                        placeHolder:"户口所在地",
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        name:"financeApply.productName"
				            	},{
			                        label: "现居住地址：",
			                        inputCls:"message",
			                        placeHolder:"现居住地址",
			                        value:curUserInfo.relationAddress,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        name:"financeApply.productName"
				            	},{
			                        label: "邮政编码：",
			                        inputCls:"message",
			                        placeHolder:"邮政编码",
			                        value:curUserInfo.postCode,
			                        regex : /^[0-9]{6}$/,
									regexText : '邮政编码格式不正确',
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        name:"financeApply.productName"
				            	},{
			                    	style:"margin:20px 20px 250px 20px;background:"+themeColor+";font-color:white",
			                    	xtype: 'button',
			                        text:"<font color=white>下一步</font>",
			                        handler:this.next
				            	}]
					}]
			     
	    	});
	
	    	this.callParent([config]);
	    },
			 next:function(){
					mobileNavi.push(Ext.create('hrmobile.public.myhome.apply.homeMessage',{}));
			 	
//		var  loanTitle=this.parent.getCmpByName("financeApply.loanTitle").getValue(); //姓名直接查出来的实名认证后取出来
//		var  loanMoney=this.parent.getCmpByName("financeApply.loanMoney").getValue(); //身份证
//		var  loanTimeLen=this.parent.getCmpByName("financeApply.loanTimeLen").getValue(); //手机号
//		if(Ext.isEmpty(loanTitle)){
//			 Ext.Msg.alert('', "<font color='#fff'>姓名不能为空</font>");
//			  return;
//			}
//			if(Ext.isEmpty(loanMoney)){
//			 Ext.Msg.alert('', "<font color='#fff'>身份证不能为空</font>");
//			  return;
//			}
//			if(!isAmount(loanMoney)){
//			 Ext.Msg.alert('', "<font color='#fff'>借款金额格式不对</font>");
//			  return;
//			}
//			if(Ext.isEmpty(loanTimeLen)){
//			 Ext.Msg.alert('', "<font color='#fff'>借款期限不能为空</font>");
//			  return;
//			}
//        var loginForm = this.up('formpanel');
//		if (loginForm.validate(loginForm.items)) {
//				loginForm.submit({
//					url : __ctxPath + '/financePurchase/saveApplyFinancePurchase.do',
//					params:{
//						isMobile:"1"
//					},
//				    success: function(form,action,response) {
//		        	var responseText = Ext.util.JSON.decode(response);
//								if (responseText.result=="1") {
//									Ext.Msg.alert('', "申请成功，请等待工作人员审核!");
//									mobileNavi.pop();
//									
//							    } else {
//							      mobileNavi.reset();
//							      mobileNavi.push(
//							      Ext.create('hrmobile.public.myhome.login',{
//						        	})
//						    	);
//							}
//				
//				}
//			});    	
//		}
	
    
					}	   
	});
