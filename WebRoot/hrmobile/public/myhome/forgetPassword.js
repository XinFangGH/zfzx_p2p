
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.forgetPassword', {
    extend: 'Ext.Container',
    name: 'forgetPassword',
    constructor: function (config) {
    	config = config || {};
    	 this.email=config.email; 
		 this.telphone=config.telphone; 
		 this.loginname=config.loginname;  
		 this.password=config.password;  
		 this.repeat_password=config.repeat_password;  
		 this.recommandPerson=config.recommandPerson;  
		 this.checkCode=config.checkCode;  
    	Ext.apply(config,{
    		width:"100%",
		    height:"100%",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>忘记密码</font>",
		    fullscreen: true,
		    scrollable:{
		    	direction: 'false'
//		    	direction: 'vertical'
		    },
		    items: [
		            {
		                xtype: 'panel',
		                style : 'margin:15px;border: 1px solid #eee;border-radius: 5px;margin-top:57px;',
		                defaults:{
		                	xtype: 'textfield',
		                	labelWidth:"50px"
		                	
		                	
		                	
		                },
		                items: [
		                {
		                	xtype:"hiddenfield",
		                	style:'margin:10px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;'
		                },{   
	                        name:"frogettelphone",
	                        placeHolder:"手机号",
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
								      	width:'73%',
				                        name:"frogetcode",
				                        placeHolder:"验证码",
				                        id:"frogetcode"
				                        
				                        
				                    },
				                    {   
				                        style:'back-ground:#F7F7F7',
										xtype:"label",
									    html: "<img  id='registerCode' style='height:42px; width:75px; cursor:pointer;' alt='点击更换' onclick='javascript:refresh(\"registerCode\");' src='"+__ctxPath+"/getCode.do'>"
									    
									}
	                    ]
	                    },{
		                    	style:"margin: 10px 20px;height: 44px;background:"+themeColor+";font-color:white",
		                    	xtype: 'button',
		                        text:"<font color=white>获取验证码</font>",
		                        scope:this,
		                        handler:this.getVerifySms
		                       
		                    }
		          
		          ]}]
    	});

  

    	this.callParent([config]);
    	
    },
    getVerifySms:function(){
    	 var  telphone=this.parent.getCmpByName("frogettelphone").getValue(); 
    	  if(Ext.isEmpty(telphone)){
			 Ext.Msg.alert('', "手机号不能为空");
			  return;
			}
		 if(!isMobile(telphone)){
		   Ext.Msg.alert('', "手机号格式不正确");
		   return;
		 }
		 var  forgetCode1=this.parent.getCmpByName("frogetcode").getValue(); 
		  if(Ext.isEmpty(forgetCode1)){
		   Ext.Msg.alert('', "验证码不能为空");
		   return;
		 }
    		var seconds=120;
    	   var temp=120;;
	      
	      var url = __ctxPath + "/user/checkUerBpCustMember.do?isMobile=1&regType=telphone"
					+ "&telphone=" + telphone + "&checkCode=" + forgetCode1 ;
            	Ext.Ajax.request({
					url:url,
				    success : function(response) {
				    	var responseText = Ext.util.JSON.decode(response.responseText);
						if (responseText.result==1) {
							Ext.Msg.alert("","验证码已发送至您的手机，请注意查收！")
							mobileNavi.push(Ext.create('hrmobile.public.myhome.related.forgetPasswordnext',{
								telphone:telphone
		        	      }));
		        	
		        	
		        	} else {
						  Ext.Msg.alert('', responseText.errMsg);
					/*	getVerifySmsObj.removeClass("disabled");
						getVerifySmsObj.html(text.indexOf("重新") == -1? "重新" + text: text);*/
						}
								
				    }
			});    	
    
	    
    	
    
    }

});
