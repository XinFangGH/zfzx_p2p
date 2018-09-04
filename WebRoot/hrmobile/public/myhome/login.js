Ext.define('hrmobile.public.myhome.login', {
    extend: 'Ext.form.Panel',
    name: 'login', 
    alias: 'widget.login', 
    constructor: function (config) {
    	config = config || {};
    	Ext.apply(config,{
    		width:"100%",
		    height:"100%",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>登录</font>",
		    style:"background:#ffffff;margin-top:42px;",
		    scrollable:{
		    	direction: 'false'
		    },
		    items: [
	            {
	                xtype: 'panel',
	                defaults:{
	                	xtype: 'textfield',
	                	labelWidth:"16%"
	            },
				items:[
				    {
						xtype:"label",
					    html: "<div style='height:15px; font-weight:bold;font-size:16px; color:#000;'></div>"
					},
					{
						label: "<img src='hrmobile/resources/imagesP2p/user-name.png' alt='' />",
					    name:"username",
					    placeHolder:"请输入您的用户名",
					    record:true,
					    style:'font-size:1em;margin:30px 30px 0px 30px;border-bottom: 1px solid #eee;'
					    
					},
					 {
						xtype:"label",
					    html: "<div style='height:15px; font-weight:bold;font-size:16px; color:#000;'></div>"
					    
					},
                    {
                    	xtype:'passwordfield',
						label: "<img src='hrmobile/resources/imagesP2p/paddw.png' alt='' />",
                        name:"loginpassword",
                        placeHolder:"请输入您的密码",
                        style:'font-size:1em;margin:30px 30px 0px 30px;border-bottom: 1px solid #eee;'
                    },
                    {
                    	xtype:"label",
                        html: "<div style='height:15px; font-weight:bold;font-size:0.95em; color:#000;'></div>"
                        
                    },
                    {
                    	xtype:"label",
                    	name:"note",
                    	id:"note",
                        html: "  "
                        
                    },{
                    	style:"margin: 10px 20px;height:44px;background:"+themeColor+";font-color:white",
                    	xtype: 'button',
                        text:"<font color=white>登  录</font>",
                        handler:this.loginbtn
                    }, {
                    	xtype : 'panel',
                    	html:"<div style='color:"+themeColor+";'> " +
                    	"<span onclick='javascript:registerbtn();' style='float:left;margin:0px 20px;font-size:14px;'>免费注册</span>"+
                    	"<span onclick='javascript:forgetPassword();' style='float:right;margin:0px 20px;font-size:14px;'>忘记密码</span></div>"
                    }
                    
               ],listeners : {
					resize :function(){
						if(!Ext.isEmpty(localStorage.userName)){
							this.parent.getCmpByName("username").setValue(localStorage.userName);
						}
						if(!Ext.isEmpty(localStorage.appsavepassword)){
							this.parent.getCmpByName("loginpassword").setValue(localStorage.appsavepassword);
						}
						
					},
					painted:function(){
						$('#navbar').css({"position":"fixed","width":"100%","z-index":"9"});
					}
				}
			}]
		          
    	});


    	this.callParent([config]);
    	
    	// 注册页
		var flag=true;
		registerbtn=function(){
//			mobileNavi.push(Ext.create('hrmobile.public.myhome.register',{}));
			if(flag){
                mobileNavi.push(Ext.create('hrmobile.public.myhome.register1',{}));
                flag=false;
                setTimeout(function(){
                	flag=true;
				},2000);
			}

		},
		
		// 忘记密码
		forgetPassword = function(){
	       mobileNavi.push(Ext.create('hrmobile.public.myhome.forgetPassword',{}));
	    }
    },
	    
		registerbtn:function(){
			mobileNavi.push(Ext.create('hrmobile.public.myhome.register',{}));
		},
    loginbtn:function(){
		var loginForm = this.up('formpanel');
		if (loginForm.validate(loginForm.items)) {
            var username=loginForm.getCmpByName('username').getValue();
            var password=loginForm.getCmpByName('loginpassword').getValue();
            
            // md5加密
            var loginpassword = password;
            var note=Ext.getCmp('note');
			if(Ext.isEmpty(username)){
				note.setHtml("<div style='margin: 10px 20px;'><font color='" + themeColor + "'>用户名不能为空</font></div>");
				return ;
			}
			if(Ext.isEmpty(loginpassword)){
				note.setHtml("<div style='margin: 10px 20px;'><font color='" + themeColor + "'>密码不能为空</font></div>");
				return ;
			}
			if(!Ext.isEmpty(username)&&!Ext.isEmpty(loginpassword)){
				note.setHtml("");
			}
			Ext.Ajax.request({
				url: __ctxPath+'/tologin.do',
				params: {
					isMobile : "1",
					loginname:username,
					password: loginpassword
				},
		        method: 'POST',
		        success: function(response) {
		        	var  responseText1=response.responseText.replace(/[\n]/ig,'');
			    	var obj = Ext.util.JSON.decode(responseText1);
		        	localStorage.setItem("appsavepassword",password);
		        	if(Ext.isEmpty(obj.errMsg)){
		        		localStorage.setItem("allpassword",password);
		        		curUserInfo=obj.data;
		        		if(isweixin==true){
		        			localStorage.setItem("weixinuserName",username);
			        		localStorage.setItem("weixinpassword",password);
			        		if(!Ext.isEmpty( Ext.get("login"))){ Ext.get("login").destroy();}
				    	    if(!Ext.isEmpty( Ext.get("register"))){ Ext.get("register").destroy();}
				    	    if(!Ext.isEmpty( Ext.get("logout"))){ Ext.get("logout").show();}
					    	mobileNavi.reset();
		        	        return;
		        		}
		        		localStorage.setItem("userName",username);
		        		localStorage.setItem(username+"password",password);
		        	    localStorage.setItem(username+"updateGesturePassword","0");
				    	localStorage.setItem(username+"curUserInfo",curUserInfo);
				    	
				    	
				    	if(isApp==false||localStorage.getItem(username+"isGesturePassword")=="true"){
				    		/* mobileNavi.destroy();
				    		 mobileNavi = Ext.create('mobile.View',{fullscreen: true});
				    		  mobileView.add(mobileNavi);*/
                            // 刷新当前页面
                            if(window.location.href.indexOf('?')==-1){
                                window.location.href = window.location.href+'?rand='+parseInt(Math.random()*101);
                            }else{
                                window.location.href = window.location.href+parseInt(Math.random()*10);
                            }
				    		if(!Ext.isEmpty( Ext.get("login"))){ Ext.get("login").destroy();}
				    	    if(!Ext.isEmpty( Ext.get("register"))){ Ext.get("register").destroy();}
				    	    if(!Ext.isEmpty( Ext.get("logout"))){ Ext.get("logout").show();}
				    		
				    	}else{
		    					mobileNavi.destroy();
								mobileNavi = Ext.create('mobile.View',
										{
											fullscreen : true
										});
								mobileNavi.getNavigationBar().hide();
								// 隐藏自定义的导航,登录/和注册按钮
								$('#signBtn').hide();
								$('#loginBtn').hide();
								if (!Ext.isEmpty(Ext.get("login"))) {
									Ext.get("login").destroy();
								}
								if (!Ext.isEmpty(Ext.get("register"))) {
									Ext.get("register").destroy();
								}
								if (!Ext.isEmpty(Ext.get("logout"))) {
									Ext.get("logout").show();
								}
								mobileView.add(mobileNavi);
				    	/*	localStorage.setItem(username+"isGesturePassword",'false');
                            localStorage.setItem(username+"settinggesturePasswordcount",1);
		        	     
		        	        mobileNavi.destroy();
		        	       	gesturePasswordSetting = Ext.create('mobile.usermanage.GesturePasswordSetting', {fullscreen: true,errormsg:"请绘制解锁图案"});
				    	    mobileView.add(gesturePasswordSetting);*/
				    	}
		        	}else{
		        		Ext.Msg.alert('', obj.errMsg);
		        	}
		        },
		        failure: function(form,action,response){
					var obj = Ext.util.JSON.decode(response);
					Ext.Msg.alert('', obj.msg);
		        }
			});
		}
	}


});
