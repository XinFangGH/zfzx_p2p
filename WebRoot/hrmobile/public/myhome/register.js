
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.register', {
    extend: 'Ext.form.Panel',
    name: 'register',
   constructor: function (config) {
    	config = config || {};
    	Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>注册</font>",
    	     scrollable: {
    	        direction: 'vertical'
    	    },
    	    items:[{
				style : 'margin:15px;border: 1px solid #eee;border-radius: 5px;',
		       items: [
			          {
	                        name:"financeApplyUser.userID",
	                        xtype:'hiddenfield'
	                      
	                        
	                    },
						{   xtype:"textfield",
	                        name:"telephone",
	                        placeHolder:"手机号",
	                        style:'margin:10px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;'
	                        
	                    },
	                    {	
	                    	xtype:"passwordfield",
	                        name:"password",
	                        placeHolder:"登录密码，6-16位数字或字母",
	                        style:'margin:10px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;'
	                    },{
	                    	xtype:"passwordfield",
	                        name:"thanpassword",
	                        placeHolder:"确认登录密码，6-16位数字或字母",
	                        style:'margin:10px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;'
	                    },{
	                    	xtype:"textfield",
	                        name:"recommandPerson",
	                        placeHolder:"邀请码（选填）",
	                        style:'margin:10px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;'
	                    },{
	                        xtype: 'panel',
	                        style:'margin-right:10px;margin-left:10px;border-bottom: 1px solid #dddddd;back-ground:#ffffff',
	                        layout: {
								type: 'hbox',
								align: 'middle'
								},
								items:[
				                    {
										xtype: 'textfield',
								      	width:'80%',
				                        name:"registercheckCode1",
				                        placeHolder:"验证码",
				                        id:"getcodecheckCode"
				                        
				                        
				                    },
				                    {   
				                        style:'back-ground:#F7F7F7',
										xtype:"label",
									    html: "<img  id='registerCode' style='height:42px; width:75px; cursor:pointer;' alt='点击更换' onclick='javascript:refresh(\"registerCode\");' src='"+__ctxPath+"/getCode.do'>"
									    
									}
	                    ]
	                    },
	                    {		
	                    	   id:'registerxieyi',
		                       name:"registerxieyi",
		                       xtype: 'checkboxfield',
		                       style:"margin:0px 20px 0px 20px",
		                       checked:true,
		                       handler:this.agreement,
		                       html:"<div style='text-align:center;font-size: 13px;position: absolute; top:17px;margin-left: 15%;'>"+
	                             		 "<label>我已阅读并同意《<span  onclick='javascript:agreement();' style='color:red;'>升升投云平台服务协议</span>》</label>"+
	                         		"</div>"
						   },
	                    {
	                    	xtype:"label",
	                    	style:"margin:0px 20px 0px 20px",
	                    	name:"register",
	                    	id:"register",
	                        html:""
                        
                       },{   
	                    	baseCls:"btn",
		                	xtype: 'button',
		                	margin:"10px 0px 10px",
		                	style:"background:"+themeColor+"",
		                    text:"<font color=white style='line-height:40px;'>立即注册</font>",
		                    scope:this,
		                    handler:this.register
      
	               }]
    	    	}]
		          
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
						title:'<div style="font-size:17px;color:#fff;">升升投服务协议</div>'
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
					scrollable:true
		    		
		    	})
		    	this.overlay.show();
		}
    	
    },
   register:function(){
		 var  telephone=this.parent.getCmpByName("telephone").getValue();  
		 var  password=this.parent.getCmpByName("password").getValue();  
		 var  thanpassword=this.parent.getCmpByName("thanpassword").getValue();  
		 var  recommandPerson=this.parent.getCmpByName("recommandPerson").getValue();  
		 var  getcodecheckCode=this.parent.getCmpByName("getcodecheckCode").getValue();  
		 var  registerxieyi=this.parent.getCmpByName("registerxieyi").getChecked()
		 var  note=Ext.getCmp('register');
	// 校验用户名
		 
		 if(Ext.isEmpty(telephone)){
			  note.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">请输入您的手机号</p></div>');
			  return;
		 }
		 if(!isMobile(telephone)){
			  note.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">请输入正确的手机号码</p></div>');
			  return;
		 }
		 if(!Ext.isEmpty(password)){
				if (password.length < 6 || password.length > 16) {
					note.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">密码长度为6~16个字符</p></div>');
					return;
				}
				if (!password.match(/^[a-zA-Z0-9]+$/)) {
					note.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">密码只能由字母和数字组成</p></div>');
					return;
				}
		 }else{
			  note.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">请输入您密码</p></div>');
			  return;
		 }
		 if(Ext.isEmpty(thanpassword)){
			  note.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">请确认您的密码</p></div>');
			  return;
		 }
		 
		 if(password!=thanpassword){
			  note.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">两次输入的密码不一致</p></div>');
			  return;
		 }
		 
		 if(Ext.isEmpty(getcodecheckCode)){
			  note.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">请输入图形验证码</p></div>');
			  return;
		 }
		 
		 
		 if(registerxieyi==false){
			  note.setHtml('<div class="prompt" style="display: block;"><i class="icon-exclamation-sign icon-large"></i> <p class="prompt-font">请勾选服务协议</p></div>');
			  return;
		 }
		 var url = __ctxPath + "/codecreate.do?isMobile=1&sms_code_type=bind_telphone&telphone="+telephone+'&randomCode='+(Math.random())+"&token="+"";
 			Ext.Ajax.request({
				url:url,
			    success : function(response) {
			    	var responseText = Ext.util.JSON.decode(response.responseText);
					if (responseText.status==200) {
						Ext.Msg.alert("", "手机验证码已发送至您的手机，请注意查收！");
						mobileNavi.push(
				    	    	Ext.create('hrmobile.public.myhome.telephoneCheck',{
				    	    		telephone:telephone,
				    	    		password:password,
				    	    		recommandPerson:recommandPerson,
				    	        })
				    	        )
					} else {
						Ext.Msg.alert("", responseText.remark);
					}
							
			    }
 			}); 
		 
		 
		 
		 
		 
		 
		 
    }
});
