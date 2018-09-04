
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.related.forgetPasswordnextTwo', {
    extend: 'Ext.form.Panel',
    name: 'forgetPasswordnextTwo',
    constructor: function (config) {
    	config = config || {};
    	this.uuid=config.uuid;
    	Ext.apply(config,{
    		width:"100%",
		    height:"100%",
		    fullscreen: true,
		    scrollable:{
		    	/*direction: 'vertical'*/
				direction: 'false'
		    },
		    items: [{
                xtype: 'panel',
                style : 'margin:15px;border: 1px solid #eee;border-radius: 5px;margin-top:57px;',
                defaults:{
                	xtype: 'passwordfield',
                	labelAlign:"top"
                	
                },
		    items: [{
                        label: "<div style='font-size:14px'>新登录密码</div>",
                        placeHolder:"请输入新的登录密码",
                        style:'margin:10px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;',
                        name:'password'
                        	
                    }  
                   ,
                    {
                    	xtype:"hiddenfield",
                    	name:"random",
                        value:this.uuid
                        
                    },
                    {
                        label: "<div style='font-size:14px'>确认新登录密码</div>",
                        placeHolder:"请确认新登录密码",
                        style:'margin:10px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;',
                         name:'repeat_password'
                    } ,
                    {
                    	xtype:"label",
                    	name:"note",
                    	id:"note",
                        html: "  "
                        
                    }  ,
                    {
                    	style:"margin:20px 20px 200px 20px;background:"+themeColor+";font-color:white",
                    	xtype: 'button',
                        text:"<font color=white>提交</font>",
                        handler:this.submit1
                       
                    }  ]}]
		    	
		    	
    	});

  
  

    	this.callParent([config]);
    	
    },
    submit1:function(){
    var loginForm = this.up('formpanel');
    if (loginForm.validate(loginForm.items)) {
           var password=loginForm.getCmpByName('password').getValue();
           var repeat_password=loginForm.getCmpByName('repeat_password').getValue();
		    var note=Ext.getCmp('note');
			
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
		     note.setHtml("<div style='margin:10px;'><font color='"+themeColor+"'>两次输入密码不一致</font></div>");
		     return ;
		 }
    		loginForm.submit({
			    url: __ctxPath+'/user/appResetPasswordBpCustMember.do',
		        params: {
		            isMobile: "1"
		        },
		        method: 'POST',
		        success: function(form,action,response) {
		        	var obj = Ext.util.JSON.decode(response);
		        	if(obj.result==1){
                        Ext.Msg.alert('', '修改成功，请牢记您的密码');
					  //登录信息
					  curUserInfo=obj.data;
					   if(!Ext.isEmpty( Ext.get("login"))){ Ext.get("login").destroy();}
			    	  if(!Ext.isEmpty( Ext.get("register"))){ Ext.get("register").destroy();}
		    	      if(!Ext.isEmpty( Ext.get("logout"))){ Ext.get("logout").show();}
					   mobileNavi.reset();
		        	}else{
		        		Ext.Msg.alert('', obj.errMsg);
		        	}
		        },
		        failure: function(form,action,response){
					var obj = Ext.util.JSON.decode(response);
					Ext.Msg.alert('', obj.errMsg);
		        }
			});
    }
			
    }

});
