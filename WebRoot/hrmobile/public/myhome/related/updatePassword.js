
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.related.updatePassword', {
    extend: 'Ext.form.Panel',
    name: 'updatePassword',
    constructor: function (config) {
    	config = config || {};
    	Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>密码修改</font>",
    		width:"100%",
		    height:"100%",
            style:"margin-top:42px;",
		    fullscreen: true,
		    scrollable:{
		    	//direction: 'vertical',
		    	direction: 'false'
		    },
		    items: [{
                xtype: 'panel',
                defaults:{
                	xtype: 'textfield'
                },
                items: [{
		                label: "<div style='font-size:14px'>原登录密码</div>",
		                placeHolder:"请输入原登录密码",
		                style:"margin:5px 0px 5px 15px;width: 90%;",
		                name:'oldpassword',
                        xtype:'passwordfield'
						/*listeners : {
							scope:this,
							'focus' : function(f) {

                                var node = document.activeElement; //当前focus的dom元素
                                if(node){
                                    if (node.nodeName == "TEXTAREA" || node.nodeName == 'INPUT') { //如果是input或textarea

                                        if(node.style.textShadow === '') {
                                            node.style.textShadow = 'rgba(0,0,0,0) 0 0 0'; //改变某个不可见样式，触发dom重绘
                                        } else {
                                            node.style.textShadow = '';
                                        }
                                    }
                                }

							}
						}*/
                    },{
	                    xtype:"label",
	                    html: "<div style='height:1px;background-color:#f3f3f3;border-top:1px solid #d8d8d8'></div>"
					},{
						xtype:'passwordfield',
                        label: "<div style='font-size:14px'>新登录密码</div>",
                        placeHolder:"请输入新的登录密码", 
                        style:"margin:5px 0px 5px 15px;width: 90%;",
                        name:'password'
                    },{
	                    xtype:"label",
	                    html: "<div style='height:1px;background-color:#f3f3f3;border-top:1px solid #d8d8d8'></div>"
					},{
						xtype:'passwordfield',
                        label: "<div style='font-size:14px'>确认新密码</div>",
                        placeHolder:"请确认登录密码",
                        style:"margin:5px 0px 5px 15px;width: 90%;",
						name:'repeat_password'
                    },{
	                    xtype:"label",
	                    html: "<div style='height:1px;background-color:#f3f3f3;border-top:1px solid #d8d8d8'></div>"
					},{
                    	xtype:"label",
                    	name:"note",
                       	id:"note",
                        html: "  "
                        
                    },{
                    	style:"margin:20px 20px 200px 20px;background:" + themeColor + ";font-color:white",
                    	xtype: 'button',
                        text:"<font color=white>修改</font>",
                        handler:this.updatesubmit
                    }
          
          ]}]
		    	
		    	
    	});

  

    	this.callParent([config]);
    	
    },
    updatesubmit:function(){
    	var loginForm = this.up('formpanel');
		if (loginForm.validate(loginForm.items)) {
	       var oldpassword = loginForm.getCmpByName('oldpassword').getValue();
           var password = loginForm.getCmpByName('password').getValue();
           var repeat_password = loginForm.getCmpByName('repeat_password').getValue();
		    var note = Ext.getCmp('note');
		   if(Ext.isEmpty(oldpassword)){
			  note.setHtml("<div style='margin:10px;'><font color='" + themeColor + "'>原登录密码不能为空</font></div>");
			   return ;
			}
			
			if(Ext.isEmpty(password)){
			  note.setHtml("<div style='margin:10px;'><font color='"+themeColor+"'>新登录密码不能为空</font></div>");
			   return ;
			}
			
			
			if(Ext.isEmpty(repeat_password)){
			  note.setHtml("<div style='margin:10px;'><font color='"+themeColor+"'>确认新登录密码不能为空</font></div>");
			   return ;
			}
			
			
				// 校验密码
		if(!Ext.isEmpty(password)){
			if (password.length < 6 || password.length > 16) {
				note.setHtml("<div style='margin:10px;'><font color='"+themeColor+"'>密码长度为6~16个字符</font></div>");
				return ;
			}
			if (!password.match(/^[a-zA-Z0-9]+$/)) {
				note.setHtml( "<div style='margin:10px;'><font color='"+themeColor+"'>密码只能由字母和数字组成</font></div>");
				return ;
			}
		}
		 if(password!=repeat_password){
		     note.setHtml("<div style='margin:10px;'><font color='" + themeColor + "'>两次输入密码不一致</font></div>");
		     return ;
		 }
				loginForm.submit({
			    url: __ctxPath+'/user/updatePasswordBpCustMember.do',
		        params: {
		            isMobile: "1"
		        },
		        method: 'POST',
		        success: function(form,action,response) {
		        	var obj = Ext.util.JSON.decode(response);
		        	if(obj.success == true){
				    	Ext.Msg.alert('', obj.msg, function () {
				    		window.localStorage.clear();
				    		mobileNavi.push(Ext.create('hrmobile.public.myhome.login',{}));
				    	}
				    	)
		        	} else {
		        		Ext.Msg.alert('', obj.msg);
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
