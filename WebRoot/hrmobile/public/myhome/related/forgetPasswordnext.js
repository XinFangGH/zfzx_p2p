
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.related.forgetPasswordnext', {
    extend: 'Ext.Container',
    name: 'forgetPasswordnext',
    constructor: function (config) {
    	config = config || {};
		 this.telphone=config.telphone;
    	Ext.apply(config,{
    		width:"100%",
		    height:"100%",
		    title:"<font color="+themeColor+">忘记密码</font>",
		    fullscreen: true,
		    scrollable:{
		    	/*direction: 'vertical'*/
                direction: 'false'
		    },
		    items: [
		            {
		                xtype: 'panel',
		                style : 'margin:15px;border: 1px solid #eee;border-radius: 5px;margin-top:57px;',
		                defaults:{
		                	xtype: 'textfield',
		                	labelWidth:"60px"
		                },
		                items: [{
		                    	xtype:"label",
		                        html: "<div style='height:40px;padding-top:10px;padding-left:10px; font-weight:bold;font-size:16px; color:#000;'>手机验证:</div>"
		                        
		                    },
		                    {   
		                        name:"telphone",
		                        value:this.telphone,
		                        readOnly:true,
		                        style:'margin:10px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;'
		                        
		                    },
		                    {
		                    	 label: "<img src='hrmobile/resources/imagesP2p/tel_yanzheng.png' alt='' />",
		                         placeHolder:"请输入您收到的验证码",
		                         name:"phonecode",
		                         style:'margin:10px;font-size:1em;color:#fff;border-bottom: 1px solid #dddddd;'
		                     
		                        
		                    },{
		                    	style:"margin:20px;background:"+themeColor+";font-color:white",
		                    	xtype: 'button',
		                        text:"<font color=white>提交</font>",
		                        scope:this,
		                        handler:this.submit
		                       
		                    }
		          
		          ]}]
    	});

  

    	this.callParent([config]);
    	
    },
    submit:function(){
    	        var verifySmsbtn=this.parent.getCmpByName("verifySmsbtn"); 
    		    var  phonecode=this.parent.getCmpByName("phonecode").getValue(); 
    		    var  telphone=this.parent.getCmpByName("telphone").getValue(); 
            	Ext.Ajax.request({
					url : __ctxPath + '/user/checkCodeBpCustMember.do',
					params:{
						isMobile:"1",
						checkCode:phonecode,
						telphone:telphone
					
					},
				    success : function(response) {
					var responseText = Ext.util.JSON.decode(response.responseText);
					if (responseText.result=="1") {
					
					  mobileNavi.push(Ext.create('hrmobile.public.myhome.related.forgetPasswordnextTwo',{
					  uuid:responseText.data
		        	}));
					}else{
						
						  Ext.Msg.alert("", responseText.errMsg);
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
