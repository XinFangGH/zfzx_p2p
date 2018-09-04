var token;
var coded = false;
var zhanghao = false;
var passwordd = false;
var typee = "0";
var telphone = false;
var flag=true;
var recommand;
Ext.define('hrmobile.public.myhome.register1', {
    extend: 'Ext.form.Panel',
    name: 'register1',
    id:'formPanel',
   constructor: function (config) {
    	config = config || {};
    	recommand=config.recommand;
    	var me=this;
    	Ext.apply(config,{
			title:"<font color=" + topColor + " style='font-size:" + topsize + "'>注册</font>",
    	     scrollable: {
                 //direction: 'false'
    	         direction: 'vertical'
    	    },
		    items:[{
		          xtype:'fieldset',
		          style:'margin:0 5px;margin-top:42px;',
		          items:[{
		               xtype:'panel',
		               items:[{
			                xtype: 'panel',
			                defaults:{
			                	xtype: 'textfield'
			                	
			            	},
				            items: [/*{
				    			   xtype: 'panel',
				                   style:'background:#ffffff;border-bottom: 1px solid #dbdbdb;',
				                   layout: {
										type: 'hbox',
										align: 'middle'
									},
				    			   items:[
										{
											xtype:'panel',
											width:'28%',
											html: '<div style="padding-left: 12px;">注册类型</div>' // 会员类型（0个人，1企业）
										},{
											xtype: 'radiofield',
											width: '32%',
											labelWidth : '60%',
											style:'margin:0 0 0 23px',
											name : 'person',
											id:'person',
											checked: true,
											label: '<b style="font-weight: 400;">个人</b>',
											listeners: {
														scope : this,
														change: function(field, newValue, oldValue) {
																if(newValue==true){
																	typee = "0";//个人
																	Ext.getCmp("truename1").setHidden(true);
																	this.getCmpByName("telphone").setPlaceHolder("手机号码");
																}else{

																}
														}
														}
										},{
											xtype: 'radiofield',
											width: '33%',
											labelWidth : '60%',
											name : 'person',
											id:'enterprise',
											label: '<b style="font-weight: 400;">企业</b>',
											listeners: {
															scope : this,
															change: function(field, newValue, oldValue) {
																	if(newValue==true){
																		typee = "1";//企业
																		Ext.getCmp("truename1").setHidden(false);
																		this.getCmpByName("telphone").setPlaceHolder("联系人手机号");
																	}else{
																	}
															}
														}
										}]
		    		   			},*/{
			                        name:"loginname",
									label:"<span style='font-size:14px;'>账号</span>",
			                        placeHolder:"请输入您的账号",
			                        style:'margin:0 5px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;',
			                        listeners : {
			                        	scope : this,
										'focus' : function(){
                                            me.getScrollable().getScroller().scrollToTop();
										},
 			                        	'blur' : function(nf){
			                        		var loginname = nf.getValue();
												if (loginname.length < 6 || loginname.length > 16) {
													//  提示框
													Ext.Msg.alert('提示','账号长度为6~16个字符!');
													return;
												}
											
												if (!loginname.match(/^[a-zA-Z0-9_]+$/)) {
													//  提示框
													Ext.Msg.alert('提示','账号只能由字母、数字 和下划线组成');
													return;
												}
											
												if (loginname.match(/^\d+$/)) {
													//  提示框
													Ext.Msg.alert('提示','账号不能使用纯数字');
													return;
												}
			                        		 Ext.Ajax.request({
													url: __ctxPath +"/user/isExistBpCustMember.do",
													 params : {
													 	loginname:loginname,
													 	mobile:'1'
											         },
												    success : function(response) {
											        	var responseText = Ext.util.JSON.decode(response.responseText);
											        	if(responseText.result==0){
											        		zhanghao = true;
											        	}else if(responseText.result==1){
											        		//  提示框
											        		Ext.Msg.alert('提示',responseText.errMsg);
											        		return;
											        	}
									   				}
										   	});
			                        	}
			                        
			                        }
			                    },{
			                    	xtype:'passwordfield',
			                        name:"password",
									label:"<span style='font-size:14px;'>登录密码</span>",
			                        id:"password",
			                        placeHolder:"请输入6-16位数字或字母",
			                        style:'margin:0 5px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;',
			                        listeners : {
			                        	scope : this,
                                        'focus' : function(){
                                            me.getScrollable().getScroller().scrollToTop();
                                        },
			                        	'blur' : function(nf){
			                        		var password = nf.getValue();
			                        		if (password.length < 6 || password.length > 16) {
			                        		 	//  提示框
												Ext.Msg.alert('提示','密码长度为6~16个字符');
												return;
											}
											if (!password.match(/^[a-zA-Z0-9]+$/)) {
			                        		 	//  提示框
												Ext.Msg.alert('提示','密码只能由字母和数字组成');
												return;
											}
			                        	}
			                        
			                        }
			                    },{
			                    	xtype:'passwordfield',
			                        name:"password1",
			                        id:"password1",
									label:"<span style='font-size:14px;'>确认登录密码</span>",
			                        placeHolder:"请输入6-16位数字或字母",
			                        style:'margin:0 5px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;',
			                        listeners : {
			                        	scope : this,
                                        'focus' : function(){
                                            me.getScrollable().getScroller().scrollToTop();
                                        },
			                        	'blur' : function(nf){
			                        		var password1 = nf.getValue();
			                        		var password = Ext.getCmp("password").getValue();
			                        		if(password!=password1){
			                        		 	//  提示框
												Ext.Msg.alert('提示','两次密码不一致');
												return;
			                        		}else{
			                        			passwordd = true;
			                        		}
			                        	}
			                        }
			                    },{   
			                        name:"truename",
			                        id:"truename1",
									label:"<span style='font-size:14px;'>企业名称</span>",
			                        placeHolder:"企业名称",
			                        hidden : true,
			                        style:'margin:0 5px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;',
			                        listeners : {
			                        	scope : this,
										'focus':function(){
                                            me.getScrollable().getScroller().scrollToTop();
										},
			                        	'blur' : function(nf){
			                        		var loginname = nf.getValue();
											if (loginname.length < 6 || loginname.length > 16) {
													//  提示框
													Ext.Msg.alert('提示','账号长度为6~16个字符!');
													return;
												}
											
												if (!loginname.match(/^[a-zA-Z0-9_]+$/)) {
													//  提示框
													Ext.Msg.alert('提示','账号只能由字母、数字 和下划线组成');
													return;
												}
											
												if (loginname.match(/^\d+$/)) {
													//  提示框
													Ext.Msg.alert('提示','账号不能使用纯数字');
													return;
												}
			                        		 Ext.Ajax.request({
													url: __ctxPath +"/user/isExistBpCustMember.do",
													 params : {
													 	loginname:loginname,
													 	mobile:'1'
											         },
												    success : function(response) {
											        	var responseText = Ext.util.JSON.decode(response.responseText);
											        	if(responseText.result==0){
											        	}else if(responseText.result==1){
											        		//  提示框
															Ext.Msg.alert('提示',responseText.msg);
											        		return;
											        	}
									   				}
										   	});
			                        	}
			                        
			                        }
			                    },{
			                        name:"recommandPerson",
									label:"<span style='font-size:14px;'>推荐码</span>",
			                        placeHolder:"推荐码（选填）",
			                        style:'margin:0 5px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;',
									id:'recommandPerson',
			                        listeners : {
			                        	scope : this,
                                        'focus':function(){
                                            me.getScrollable().getScroller().scrollToTop();
                                        },
										painted:function(){
			                        		//console.log(recommand);
			                        		if(recommand){
			                        			$('#recommandPerson input').val(recommand);
                                                $('#recommandPerson input').attr('disabled','disabled');
                                                $('#recommandPerson input').css('background','#fff');
											}
										},
			                        	'change' : function(nf){
			                        		var recommandPerson = nf.getValue();
			                        		 Ext.Ajax.request({
													url: __ctxPath +"/user/isExistRecommandBpCustMember.do",
													 params : {
													 	recommandPerson:recommandPerson,
													 	mobile:'1'
											         },
												    success : function(response) {
											        	var responseText = Ext.util.JSON.decode(response.responseText);
											        	if(responseText.result==0){
											        		//  提示框
															Ext.Msg.alert('提示',responseText.errMsg);
											        		return;
											        	}
									   				}
										   	});
			                        	}
			                        
			                        }
			                    },{
			                        xtype: 'panel',
			                        style:'margin-right:5px;margin-left:5px;border-bottom: 1px solid #dddddd;back-ground:#ffffff',
			                        layout: {
										type: 'hbox',
										align: 'middle'
										},
										items:[{
											xtype: 'textfield',
											label:"<span style='font-size:14px;'>图形验证码</span>",
											labelWidth:"38%",
									      	width:'80%',
					                        name:"registercheckCode1",
					                        placeHolder:"请输入图形验证码",
					                        id:"registercheckCode1",
					                        listeners : {
					                        	scope : this,
                                                'focus' : function(){
                                                    me.getScrollable().getScroller().scrollToTop();
                                                },
					                        	'blur' : function(nf){
					                        		var checkCode = nf.getValue();
					                        		 Ext.Ajax.request({
															url: __ctxPath +"/user/isRightCheckCodeBpCustMember.do",
															 params : {
															 	checkCode:checkCode,
															 	mobile:'1'
													         },
														    success : function(response) {
													        	var responseText = Ext.util.JSON.decode(response.responseText);
													        	if(responseText.result==1){
													        		//  提示框
																	Ext.Msg.alert('提示',responseText.errMsg);
													        		return;
													        	}else{
													        		coded = true;
													        	}
											   				}
												   	});
					                        	}
					                        
					                        }
					                    },{   
					                        style:'back-ground:#F7F7F7',
											xtype:"label",
										    html: "<img  id='registerCode' style='height:42px; width:75px; cursor:pointer;' alt='点击更换' onclick='javascript:refresh(\"registerCode\");' src='"+__ctxPath+"/getCode.do'>"
											    
										}]
			                    },/*{
				                        xtype: 'panel',
				                        style:'back-ground:#ffffff',
				                        layout: {
											type: 'hbox',
											align: 'middle'
										},
					                    items:[*/{
                                            	xtype: 'textfield',
							                   // width:"100%",
			//			                        label: "<img src='hrmobile/resources/imagesP2p/tel.png' alt='' />",
												label:"<span style='font-size:14px;'>手机号码</span>",
						                        name:'telphone',
						                        id : 'phoneNum',
						                    	placeHolder:"手机号码",
						                        //labelWidth:"60px",
						                        style:'margin:0 5px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;',
					                    	/*}],*/
                                		listeners : {
                                            /*'focus' : function(){
                                                me.getScrollable().getScroller().scrollToTop();
                                            },*/
                                            scope : this,
                                            'focus' : function(){
                                                me.getScrollable().getScroller().scrollToTop();
                                            },
                                            'blur' : function(nf){
                                                var phoneNum = nf.getValue();
                                                if (!phoneNum.match(/^\d{11}/)) {
                                                    //  提示框
                                                    Ext.Msg.alert('提示','手机号码长度应为11位');
                                                    return;
                                                }
                                                if (!phoneNum.match(/^[1][3,4,5,7,8][0-9]{9}$/)) {
                                                    //  提示框
                                                    Ext.Msg.alert('提示','手机号码格式不正确');
                                                    return;
                                                }
                                                Ext.Ajax.request({
                                                    url: __ctxPath +"/user/isTelphoneBpCustMember.do",
                                                    params : {
                                                        phoneNum:phoneNum,
                                                        mobile:'1'
                                                    },
                                                    success : function(response) {
                                                        var responseText = Ext.util.JSON.decode(response.responseText);
                                                        if(responseText.result==0){
                                                            telphone = true;
                                                        }else if(responseText.result==1){
                                                            //  提示框
                                                            Ext.Msg.alert('提示',responseText.errMsg);
                                                            return;
                                                        }
                                                    }
                                                });
                                            }

										}
			                    },
                                {
                                    xtype:'panel',
                                    html:'<p style="margin:0 8px;font-size:11px;line-height:40px;border-bottom:1px solid #ddd;"><i style="color:#D0021B;margin-right:5px;">*</i>手机号必须为您需要绑定银行卡的预留手机号</p>',
								}, {
			                    	
				                        xtype: 'panel',
				                        style:'back-ground:#ffffff',
				                        layout: {
											type: 'hbox',
											align: 'middle'
										},
					                    items:[{
							                    width:"52%",
			//			                        label: "<img src='hrmobile/resources/imagesP2p/tel.png' alt='' />",
												label:"<span style='font-size:14px;'>验证码</span>",
						                        name:"checkCode",
						                        xtype: 'textfield',
						                    	placeHolder:"手机验证码",
												labelWidth:"50%",
						                        style:'margin:0 5px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;',
                                            	listeners :{
                                                    'focus' : function(){
                                                        me.getScrollable().getScroller().scrollToTop();
                                                    },
												}
					                    	},{
						                    	width:"40%",
						                    	height:'41px',
						                    	style:"background:"+themeColor+";font-size:0.8em;padding:0;",
						                    	xtype: 'button',
						                    	name:"verifySmsbtn",
						                        text:"<font color=white>获取验证码</font>",
						                        handler : this.getVerifySms
				                    	}]
			                    
				                    	
	//		                    	 label: "<img src='hrmobile/resources/imagesP2p/tel_yanzheng.png' alt='' />",
			                        /* placeHolder:"请输入手机验证码",
			                         name:"checkCode",
			                         style:'margin:10px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;'*/
			                    }/*,{
                                    xtype:'panel',
                                    html:"<div style='height:60px;padding: 15px;font-size:16px;color:#aDaDaD;'>请填写本人" +
                                    "真实手机号,用于接收个人账户资金变动提醒</div>"
                                }*/,{
                                    xtype:"label",
                                    style:"margin:0px 20px 0px 20px",
                                    html:'<div style="font-size:12px;margin:20px 20px 20px 0px;"><input id="xycheck" type="checkbox" />我已阅读并且同意此<span style="color:coral;" onclick="javascript:overlays();">《服务协议》</span></div>'
                                },{
                                    xtype:"label",
                                    style:"margin:0px 20px 0px 20px",
                                    name:"register",
                                    id:"register",
                                    html:""
                                },{
                                    baseCls:"btn",
                                    xtype: 'button',
                                    margin:"10px 0px 10px",
                                    style:"background:" + themeColor + "",
                                    text:"<font color=white style='line-height:40px;'>立即注册</font>",
                                    scope:this,
                                    handler:this.register
                                }

                            ]
		                 }]
		          }]
		    }],
	    	listeners : {
	    		// 页面加载事件
	    		painted : function () {
                    $('#ext-button-3').removeClass('wyjk').addClass('wyjk_select');
                    $('#ext-button-1').addClass('home').removeClass('home_select');
	    			// 修改lable
					$('.x-container.x-field-radio.x-field').eq(0).css('width','33.33%');
					// 单选框
					/*$('.x-form-label').eq(2).css('width','60%');
					$('.x-form-label').eq(3).css('width','50%');*/
					
					// 更改单选框样式
					//$('.x-unsized.x-field-input.x-has-width').eq(0).css({transform: 'translate(-30px,0)'})
					//$('.x-unsized.x-field-input.x-has-width').eq(1).css({transform: 'translate(-30px,0)'})


					$('input[type!="checkbox"]').focus(function(){
                        me.getScrollable().getScroller().scrollToTop();
                        hidebottomBarIndex();
					});
					$('input').blur(function(){
                        me.getScrollable().getScroller().scrollTo(0,100);
						showbottomBarIndex();
					});
			   }
	    		
	    	}
    	}
    	
    	
    	);


    	this.callParent([config]);

    	
    	
    	overlays=function(){
				this.overlay =Ext.Viewport.add({
					xtype:'panel',
					modal:true,
					hideOnMaskTap:true,
					showAnimation:{
						type:'popIn',
						//type:'FadeIn',
						duration:250,
						easing:'ease-out' // 动画效果
					},
					hideAnimation:{
						type:'popOut',
						//type:'FadeOut',
						duration:250,
						easing:'ease-out'
					},
					centered:true,
					width:'80%',
					height:'500px',
					styleHtmlContent:false,
					html:'<div class="serve">'+
					'<h4 style="margin:0; padding:10px 0; font-size:16px;">本网站由中发展信（北京）投资管理有限公司负责运营（以下“本网站”均指网站及中发展信（北京）投资管理有限公司）。在您注册成为本网站用户前请务必仔细阅读以下条款。若您一旦注册，则表示您同意接受本网站的服务并受以下条款的约束。若您不接受以下条款，请您立即停止注册或主动停止使用本网站的服务。</h4>'+
					'</div>'+
					'<div class="serve">'+
					'<h4>一、协议主体</h4>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">1.服务提供方：即中发展信（北京）投资管理有限公司。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">2.服务接受方：即中发展信（北京）投资管理有限公司（以下简称“升升投”）平台网站用户，包含注册用户和非注册用户。注册用户是指通过www.zxzbol.com网站完成全部注册程序后，使用升升投平台网站服务的用户。非注册用户是指未进行注册,直接登录www.zxzbol.com网站或通过其他网站进入www.zxzbol.com使用升升投平台网站服务的用户。</p>'+
					/*'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">3.本网站有权根据需要不时地修改本协议或根据本协议制定、修改各类具体规则并在本网站相关系统板块发布，无需另行单独通知您。您应不时地注意本协议及具体规则的变更，若您在本协议及具体规则内容公告变更后继续使用本服务的，表示您已充分阅读、理解并接受修改后的协议和具体规则内容，也将遵循修改后的协议和具体规则使用本网站的服务；同时就您在协议和具体规则修订前通过本网站进行的交易及其效力，视为您已同意并已按照本协议及有关规则进行了相应的授权和追认。若您不同意修改后的协议内容，您应停止使用本网站的服务。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">4.您通过自行或授权有关方根据本协议及本网站有关规则、说明操作确认本协议后，本协议即在您和本网站之间产生法律效力。用户只要在“【我已阅读并且同意投融家的服务协议】”前的选择框里打钩并按照本网站规定的注册程序成功注册为用户，即表示同意并签署了本服务协议。本协议不涉及您与本网站的其他用户之间因网上交易而产生的法律关系或法律纠纷，但您在此同意将全面接受和履行与本网站其他用户在本网站签订的任何电子法律文本，并承诺按该等法律文本享有和/或放弃相应的权利、承担和/或豁免相应的义务。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">5、用户在此授权本网站及网站所属公司可以随时通过任何渠道采集用户的信用信息、信用报告和信用评价，包括但不限于向独立信用审查机构、中国人民银行征信中心（包括通过独立信用审查机构向中国人民银行征信中心）查询、采集。查询结果可用于网站及网站所属公司开展与用户相关联的各项业务。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;"></p>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;"></p>'+*/
					'</div>'+
					'<div class="serve">'+
					'<h4 style="margin:0; padding:10px 0; font-size:16px;">二、缔约前提</h4>'+
					/* '<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">1.在成为本网站用户前，用户确认已充分阅读并理解本《注册服务协议》（本协议）的所有条款。如用户对协议有任何疑问，可向升升投咨询。用户同意以下条款并注册后，升升投有权利依据本协议条款对用户进行约束、管理，用户有权依据本协议的接受升升投的服务。</p>'+*/
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">1.在成为本网站用户前，用户确认已充分阅读并理解本《注册服务协议》（本协议）的所有条款。如用户对协议有任何疑问，可向升升投咨询。用户同意以下条款并注册后，升升投有权利依据本协议条款对用户进行约束、管理，用户有权依据本协议，接受升升投的服务。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">2.本服务协议内容包括以下条款及已经发布的或将来可能发布的各类规则。所有规则均为协议不可分割的一部分，与协议正文具有同等法律效力。除另行明确声明外，升升投提供的任何服务均受本协议约束。用户承诺接受并遵守本协议的约定。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">3.升升投有权根据需要不时地修改本协议或制定其他各类规则，如本协议及规则有任何变更，平台将在网站上刊载公告，经修订的协议、规则一经公布后，立即自动生效。如用户不同意相关变更，请停止使用升升投平台网站服务，并退出升升投。如用户继续使用升升投平台网站服务，即表示用户理解并接受经修订的协议和规则。如新旧规则或协议之间冲突或矛盾的，除另行明确声明外，以最新修订的协议和规则为准。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">4.升升投是民间资金的需求、提供和担保等配套服务的信息公示平台，为会员之间订立投融资合同提供媒介服务，平台上关于升升投会员或其发布的相关信息（包括但不限于公司名称、 联系人及联络信息，资金的需求或供给描述和说明等）均由会员自行提供，会员应对其提供的信息承担全部责任。用户应自行结合自身的财务状况、风险承受能力等审慎作出投融资或担保行为，并自行承担因此产生的法律后果。</p>'+
					'</div>'+
					'<div class="serve">'+
					'<h4 style="margin:0; padding:10px 0; font-size:16px;">三、 服务内容</h4>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">1.升升投提供的服务包括但不限于通过本网站以及其他渠道和方式等向用户提供以下服务中的一项或多项：' +
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:4em;">(1)撮合形成借贷交易的民间服务；</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:4em;">(2)理财投融资服务；</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:4em;">(3)信贷咨询及管理服务；</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:4em;">(4)其他相关服务。具体详情以本网站当时提供的服务内容为准。</p>'+
					'</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">2.用户成功注册后，可以自行或授权代理人根据本网站有关规则和说明，通过本网站确认签署有关协议并经本网站审核通过后实现融资需求或资金的投资。细操作规则及方式请见有关协议及本网站相关页面的规则和说明。</p>'+
					'</div>'+
					'<div class="serve">'+
					'<h4 style="margin:0; padding:10px 0; font-size:16px;">四、 用户注册</h4>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:2em;">1.用户只要勾选位于注册页面下方的“我同意会员服务协议”选项并按照本网站的流程成功注册后，本协议即产生法律效力；不得以未签署书面协议为由否认本协议的效力，本协议适用于用户在本网站的全部活动。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:2em;">2.在注册时，用户应当按照法律法规要求和注册页面的提示，准确填写并及时更新信息。用户承诺在升升投上填写的资料、发布的信息等都真实、合法，否则，由此导致用户在使用升升投平台网站服务过程中产生任何损失或增加费用的，应完全由用户承担。</p>'+

					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:2em;">3.本协议不涉及用户与本网站的其他用户之间因网上交易而产生的法律关系及法律纠纷，用户可自行通过合法途径解决其纠纷，但用户在此同意将全面接受和履行与其他用户在升升投平台网站上签订的任何电子法律文本，并承诺按该法律文本享有相应的权利、承担相应的义务。</p>'+
					'</div>'+
					'<div class="serve">'+
					'<h4 style="margin:0; padding:10px 0; font-size:16px;">五、 承诺和保证</h4>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:2em;">1.用户承诺其为中华人民共和国（不包括香港特区、澳门特区及台湾地区）的具有完全民事行为能力和权利能力的自然人以及具有符合法律规定的能独立承担法律责任或有合法授权可以进行金融交易的法人、社团或其他组织。如用户不符合资格，请勿注册，否则由此产生的一切责任自负，升升投保留中止或终止用户身份的权利。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:2em;">2.用户承诺合法使用网站提供的服务及网站内容，禁止在升升投平台从事任何可能违反中国现行的法律、法规、规章和规范性文件的行为或者任何未经授权使用网站信息的行为。否则，网站保留追究其法律责任的权利。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:2em;">3.用户保证在注册时提供自己的真实资料，并保证诸如电子邮件地址、联系电话、联系地址等内容的有效性及安全性。如用户因网上交易与其他用户产生诉讼的，网站将根据隐私规则披露相关用户资料。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:2em;">4.用户注册成功后，不得将用户名等信息转让给第三方或者授权给第三方使用。用户确认，使用本人的用户名和密码等信息登录本网站及在本网站的一切行为均代表用户本人并由用户本人承担相应的法律后果。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:2em;">5.用户保证并承诺通过本网站平台进行交易的资金来源合法。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:2em;">6.若用户违反本承诺和保证的，升升投有权依据本协议的约定，做出相应处理或终止向用户提供服务，且无须征得用户的同意或提前通知用户。如使升升投遭受任何损失的（包括但不限于受到第三方的索赔、受到行政管理部门的处罚等），用户还应当赔偿或补偿升升投遭受的损失及（或）发生的费用，包括诉讼费、律师费、保全费等。</p>'+
					'</div>'+
					'<div class="serve">'+
					'<h4 style="margin:0; padding:10px 0; font-size:16px;">六、 资金管理</h4>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:2em;">1.用户在升升投进行的借款或出借，由升升投委托的第三方机构将为用户提供“资金管理服务”，主要包括但不限于：资金的充值、提现、代收、代付、查询等，用户承诺完全同意并遵守第三方机构的资金管理规则。用户可以通过本网站有关页面的具体规则或说明详细了解。</p>'+
					'</div>'+

					'<div class="serve">'+
					'<h4 style="margin:0; padding:10px 0; font-size:16px;">七、 保密</h4>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:4em;">1.对于用户提供的资料及数据信息，用户授予升升投永久的、免费的使用权利，但升升投承诺不用于本网站之外的其他不合理用途。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:4em;">2.用户同意升升投在业务运营中储存并使用用户信息，包括但不限于根据双方另行签署的协议在网站公示用户相关信息；向本网站的合作机构（该合作机构仅限于本网站为了完成拟向用户提供的服务而合作的机构）提供用户信息；由人工或自动程序对信息进行评估、分类、研究；使用用户信息以改进本网站的推广；以及使用用户提供的联系方式向用户传递有关业务和管理方面的信息。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:4em;">3.升升投平台对用户提供的经认证的个人信息将按照本规则予以保护、使用或者披露。当用户未能按照平台相关协议内容履行应尽义务时，升升投有权根据自己的判断或者根据与交易有关的其他用户的请求披露用户的个人资料，并有权决定对信息资料标记进入网站黑名单，并将黑名单对第三方披露，与第三方之间进行数据共享，由此因第三方行为造成用户损失的，升升投平台不承担任何法律责任。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:4em;">4.用户注册成功后应妥善保管用户名和密码。用户确认，使用用户名和密码登录本网站后在本网站的一切行为均代表用户的行为并自行承担相应的法律后果。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:4em;">5.升升投将采用行业标准惯例以保护用户的个人资料，但鉴于技术限制，本网站不能确保用户的全部私人通讯及其他个人资料不会通过本协议中未列明的途径泄露出去。</p>'+
					'</div>'+
					'<div class="serve">'+
					'<h4 style="margin:0; padding:10px 0; font-size:16px;">八、 电子合同</h4>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:2em;">1.在升升投平台交易需订立的协议采用电子合同方式，可以有一份或者多份并且每一份具有同等法律效力。用户根据有关协议及升升投的相关规则在本网站确认签署的电子合同即视为用户本人真实意愿并以用户本人名义签署的合同，具有法律效力。用户应妥善保管自己的账户密码等账户信息，用户通过前述方式订立的电子合同对合同各方具有法律约束力，用户不得以账户密码等账户信息被盗用或其他理由否认已订立的合同的效力或不按照该等合同履行相关义务。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:2em;">2.用户根据本协议以及本网站的相关规则签署电子合同后，不得擅自修改该合同。本网站向用户提供电子合同的保管查询、核对等服务，如对电子合同真伪或电子合同的内容有任何疑问，用户可通过本网站的相关系统板块查阅有关合同并进行核对。如对此有任何争议，应以本网站记录的合同为准。</p>'+
					'</div>'+
					'<div class="serve">'+
					'<h4 style="margin:0; padding:10px 0; font-size:16px;">九、 救济</h4>'+
					'<p style="margin:0 0 0 0px; font-size:14px;text-indent:2em;">1.用户在发现本网站任何内容不符合法律规定，或不符合本用户协议规定的，用户有义务及时通知升升投。如果用户发现用户的个人信息被盗用，或者用户的其他权利被侵害，请将此情况告知升升投经审查得到证实的，我们将及时删除相关信息。我们仅接受邮寄、电子邮件或传真方式的书面侵权通知。情况紧急的，用户可以通过客服电话先行告知，我们会视情况采取相应措施。</p>'+
					'</div>'+
					'<div class="serve">'+
					'<h4 style="margin:0; padding:10px 0; font-size:16px;">十、声明</h4>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">1.升升投平台的网站内容可能涉及第三方所有、控制、运营的其他网站（即“第三方网站”），升升投平台没有义务保证第三方网站上信息的真实性、有效性，由用户自行判断风险和承担风险，与升升投平台无关。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">2.除升升投平台明确保证提供担保的或者本金保障计划等有明确协议规则外，平台提供的服务中不带有对任何用户和交易提供任何保证和条件。</p>'+
					'</div>'+
					'<div class="serve">'+
					'<h4 style="margin:0; padding:10px 0; font-size:16px;">十一、 附则</h4>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">1.本协议始终有效，除非本网站终止或者本网站同意用户的终止申请。用户对升升投平台有任何投诉和建议的，请及时将意见反馈给客服人员。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">2.本协议某些条款被认定为无效或者无法实施时，不影响其他条款的效力，其他条款继续有效。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">3.因使用本网站产生争议的均适用中华人民共和国法律，协议管辖法院为升升投平台住所地法院。</p>'+
					'<p style="margin:0 0 0 0px; font-size:14px; text-indent:2em;">4.升升投网站保留对本规则的最终解释权。</p>'+
					'</div>'+
					'</div>',
					items:[{
						docked:'top',
						xtype:'toolbar',
						style:"background:"+themeColor+"",
						title:'<div style="font-size:17px;color:#fff;">服务协议</div>'
					},{     docked:'bottom',
						baseCls:"btn",
						xtype: 'button',
						margin:"10px 0px 10px",
						style:"background:"+themeColor+"",
						text:"<font color=white style='line-height:40px;'>我已同意并阅读此协议</font>",
						scope:this,
						handler: function () {
							this.overlay.hide();
						}
					}],
					scrollable:true,
				});
				//this.overlay.show();

		}
    	
    },
    
    // 获取验证码
    getVerifySms : function(){
    	 var  telphone = Ext.getCmp("phoneNum").getValue(); 
    	  if(Ext.isEmpty(telphone)){
    	  	//  提示框
			 Ext.Msg.alert('提示',"手机号不能为空");
			  return;
			}
		 if(!isMobile(telphone)){
		 	//  提示框
			 Ext.Msg.alert('提示',"手机号格式不正确");
		   return;
		 }
    	Ext.Ajax.request({
			url: __ctxPath +"/htmlreg.do",
			 params : {
				mobile : "1"
	         },
		    success : function(response) {
		    	var responseText1=response.responseText.replace(/[\n]/ig,'');
		    	var responseText = Ext.util.JSON.decode(responseText1);
		    	token = responseText.token;
        }});
    		var seconds = 120;
    	    var temp = 120;;
    	
    	 var getVerifySmsObj = this;
    	 var text1 = getVerifySmsObj.getText();
	     if(this._disabled == null || this._disabled == false){
	    	
	        this.setDisabled(true);
	      
	     var url = __ctxPath + "/codecreate.do?isMobile=1&mobile=1&sms_code_type=bind_telphone"
					+ "&telphone=" + telphone + '&randomCode='
					+ (Math.random())+"&token="+token;
            	Ext.Ajax.request({
					url : url,
				    success : function(response) {
				    	var responseText = Ext.util.JSON.decode(response.responseText);
						if (responseText.status==200) {
							var text=getVerifySmsObj.getText();
					    	var interval = window.setInterval(function() {
									seconds--;
			                      
									if (seconds == 0) {
										seconds = temp;
										window.clearInterval(interval);
										
										getVerifySmsObj.setText(text.indexOf("重新") == -1
												? "<font color=white>重新</font>" + text
												: text);
										 getVerifySmsObj.setDisabled(false);
									} else {
										getVerifySmsObj.setText(text1 + "(" + seconds + ")");
										//getVerifySmsObj.setText(seconds);
									}
							}, 1000);
						} else {
							//  提示框
			 				Ext.Msg.alert('提示',responseText.remark);
							getVerifySmsObj.setDisabled(false);
							getVerifySmsObj.setText(text1);
						}
								
				    }
			});    	
    
	    }
    
    },
   register : function () {
		   var checkCode = this.parent.getCmpByName("checkCode").getValue();
		   if(typee=="1"){
		   	   var truename = Ext.getCmp("truename1").getValue();
		   	   if(Ext.isEmpty(truename)){
		   	   	//  提示框
			 	Ext.Msg.alert('提示',"企业名称不能空");
			   	    return;
		   	   }
		   }
		   if(!zhanghao){
				//  提示框
			 	Ext.Msg.alert('提示',"账号不正确");
		   	    return;
		   }
		   if(!passwordd){
				//  提示框
			 	Ext.Msg.alert('提示',"密码或确认密码不正确");
		   	    return;
		   }
		   if(!coded){
				//  提示框
			 	Ext.Msg.alert('提示',"图形验证码不正确");
		   	    return;
		   }

       var  telphone = Ext.getCmp("phoneNum").getValue();
       if(Ext.isEmpty(telphone)){
           //  提示框
           Ext.Msg.alert('提示',"手机号不能为空");
           return;
       }

		   if(Ext.isEmpty(checkCode)){
		   	   
				//  提示框
			 	Ext.Msg.alert('提示',"短信验证码不能空");
		   	    return;
		   }


		   if(!$('#xycheck').is(':checked')){
               Ext.Msg.alert('提示',"请选中协议");
               return;
		   }
		   var note=Ext.getCmp('register');
		   this.submit({
				url: __ctxPath+'/user/newsignreg.do',
				params: {
					isMobile : "1",
					custType : typee//0个人//1企业
				},
		        method: 'POST',
		        success: function(form,action,response) {
		        	var obj = Ext.util.JSON.decode(response);
		        		curUserInfo=obj.data;
				    	localStorage.setItem("curUserInfo",curUserInfo);

						// 动画提示框
						Ext.toast('<div style="' +
							'padding:5px 0;' + 
							'color:red;' + 
							'border-radius:5px;' +
							'font-weight:bold;' +
							'background:rgba(255,255,255,1);' +
							'">注册成功！</div>',
							1500
						);
						$('div.x-stretched.x-toast-text').css('padding',0);

						setTimeout(function(){
                            mobileNavi.reset();
                            $('#ext-button-3').removeClass('wyjk_select').addClass('wyjk');
                            $('#ext-button-1').addClass('home_select').removeClass('home');
						},2000);

		        },
		        failure: function(form,action,response){
					var obj = Ext.util.JSON.decode(response);
					//  提示框
			 		Ext.Msg.alert('提示',obj.errMsg);
					
		        }
			});	
    }

});
