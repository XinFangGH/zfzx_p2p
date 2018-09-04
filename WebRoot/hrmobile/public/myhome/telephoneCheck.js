
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.telephoneCheck', {
    extend: 'Ext.Container',
    name: 'telephoneCheck',
    constructor: function (config) {
    	config = config || {};
		 this.telphone=config.telephone;
		 this.password=config.password;  
		 this.recommandPerson=config.recommandPerson;  
    	Ext.apply(config,{
    		width:"100%",
		    height:"100%",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>手机验证</font>",
		    fullscreen: true,
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
	                        name:"phonecode",
	                        placeHolder:"手机验证码",
	                        style:'margin:10px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;'
	                        
	                    },
	                    {
	                    	xtype:"label",
	                    	style:"margin:0px 20px 0px 20px",
	                        html:'<div style="font-size:12px;margin:20px 20px 20px 0px;">注册表示您已同意<span style="color:coral;" onclick="javascript:overlays();">《宾和服务协议》</span></div>'
                        
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
    	
    },
    register:function(){
    		    var  phonecode=this.parent.getCmpByName("phonecode").getValue(); 
    		    if(Ext.isEmpty(phonecode)){
    				 Ext.Msg.alert('', "请输入您收到的验证码");
    				  return;
    				}
            	Ext.Ajax.request({
					url : __ctxPath + '/user/newsignreg.do',
					params:{
//						struts.token.name:token
//						token:BDKRTR0T8R1VGXWP9BGFF2PD9HDQCF5Z
//						struts.token.name:token
//						token:BDKRTR0T8R1VGXWP9BGFF2PD9HDQCF5Z
//						kehu:Personal
//						custType:0
//						loginname:mangguo122
//						password:111111
//						repeat_password:111111
//						truename:
//						cardcode:
//						contactPerson:
//						telphone:13264391752
//						checkCode:3yt1
//						checkCode:408703
//						recommandPerson:
						kehu:"Personal",
						custType:0,
						isMobile:"1",
						loginname:this.telphone,
						password:this.password,
						repeat_password:this.password,
						checkCode:"isMobile",
						telphone:this.telphone,
						checkCode:phonecode,
						recommandPerson:this.recommandPerson
						
					},
				    success : function(response) {
					var responseText = Ext.util.JSON.decode(response.responseText);
					if (responseText.success==true) {
					  Ext.Msg.alert("", "恭喜您注册成功！");
					  //登陆信息
					  curUserInfo=responseText.data;
					   if(!Ext.isEmpty( Ext.get("login"))){ Ext.get("login").destroy();}
			    	  if(!Ext.isEmpty( Ext.get("register"))){ Ext.get("register").destroy();}
		    	      if(!Ext.isEmpty( Ext.get("logout"))){ Ext.get("logout").show();}
					  mobileNavi.reset();
					
					  
					/*  mobileNavi.push(Ext.create('hrmobile.public.myhome.personInfo',{
		        	}));*/
					}else{
						
						  Ext.Msg.alert("", "验证码输入错误");
				/*		window.clearInterval(interval);
						verifySmsbtn.setText(text.indexOf("重新") == -1
								? "<font color=white>重新</font>" + text
								: text);
					   verifySmsbtn.setDisabled(false);
					   Ext.Msg.alert("", responseText.result);
					   return;*/
					}
					
				}
			});    	
    }

});
