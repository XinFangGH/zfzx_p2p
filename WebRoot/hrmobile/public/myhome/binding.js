
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.binding', {
    extend: 'Ext.Container',
    name: 'binding',
    constructor: function (config) {
  /*  var	topcommmon= Ext.create('hrmobile.public.myhome.related.topcommmon',{
    });*/
   /* var bottomBar= Ext.create('hrmobile.public.myhome.related.bottomBar',{
    });*/
    	config = config || {};
    	var me=this;
    	Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>实名认证</font>",
    		width:"100%",
		    height:"100%",
            style:"margin-top:42px;",
		    fullscreen: true,
		    scrollable:{
                direction: 'false'
                //direction: 'vertical'
		    },
		    items: [/*topcommmon,*/
		            {
		                xtype: 'fieldset',
		                defaults:{
		                	xtype: 'textfield',
		                	labelAlign:"top"
		                	
		                },
		                items: [{
		                        label: "用户名",
		                        value:(null==curUserInfo?"":curUserInfo.loginname),
		                        name:"loginname",
		                        readOnly:true
		                    },
		                    /*{
		                        label: "邮箱",
		                        value:(null==curUserInfo?"":curUserInfo.email),
		                         name:"email",
								hidden:true,
                                listeners:{
                                    scope : this,
                                    'focus' : function(){
                                        me.getScrollable().getScroller().scrollToTop();
                                    }
                                }
		                    }  
		                   ,*/
		                    {
		                        label: "手机",
		                        value:(null==curUserInfo?"":curUserInfo.telphone),
		                         name:"telphone",
		                        readOnly:true
		                    }  
		                   ,
		                    {
		                        label: "姓名",
		                        value:(null==curUserInfo?"":curUserInfo.truename),
		                         name:"truename",
                                listeners:{
                                    scope : this,
                                    'focus' : function(){
                                        me.getScrollable().getScroller().scrollToTop();
                                    }
                                }
		                    }  
		                   ,
		                    {
		                        label: "身份证号",
		                        value:(null==curUserInfo?"":curUserInfo.cardcode),
		                         name:"cardcode",
                                listeners:{
                                    scope : this,
                                    'focus' : function(){
                                        me.getScrollable().getScroller().scrollToTop();
                                    }
                                }
		                    }  
		                   ,
		                    {
		                    	style:"margin:20px 20px 250px 20px;padding-top:10px;background:"+themeColor+";font-color:white",
		                    	xtype: 'button',
		                        text:"<font color=white>确认无误进行实名认证</font>",
		                        scope:this,
		                        handler:this.submit
		                       
		                    }
		          
		          ]}/*,bottomBar*/],
			listeners:{
				painted:function(){
                    $('input').focus(function(){
                        me.getScrollable().getScroller().scrollToTop();
                        hidebottomBarIndex();
                    });
                    $('input').blur(function(){
                        showbottomBarIndex();
                    });
                    $('#navbar').css({"position":"fixed","width":"100%","z-index":"9"});
            }
			}
    	});

  

    	this.callParent([config]);
    	
    },
    submit:function(){
    	var  loginname=this.getCmpByName("loginname").getValue(); 
    	//var  email=this.getCmpByName("email").getValue();
    	var  telphone=this.getCmpByName("telphone").getValue(); 
    	var  truename=this.getCmpByName("truename").getValue(); 
    	var  cardcode=this.getCmpByName("cardcode").getValue();
    	/*var flagemail;
		if(email){
			flagemail=isEmail(email);
		}else{
			flagemail=false;
		}*/
    	if(truename&&cardcode){
            var url = __ctxPath + '/pay/registerAndBindPay.do?moneyMoreMore.isMobile=1&' +
                'loginname='+encodeURIComponent(loginname)+
                '&telphone='+telphone+'&truename='+encodeURIComponent(truename)
                +'&cardcode='+cardcode+'&isMobile=1&backpath=hrmobile.public.myhome.main';
            getThirdPayLink(url);
		}else{
    		/*if(flagemail){
                Ext.Msg.alert("邮箱格式不正确");
			}else */if(!truename){
                Ext.Msg.alert("姓名不能为空");
            }else{
                Ext.Msg.alert("身份证不能为空");
			}
		}

	 /*var url = __ctxPath + '/pay/registerAndBindPay.do?moneyMoreMore.isMobile=1&' +
	 		'loginname='+encodeURI(loginname)+'&email='+email+
	 		'&telphone='+telphone+'&truename='+encodeURI(truename)
	 		+'&cardcode='+cardcode+'&isMobile=1'; */

    
    
    
    
/*	document.addEventListener("deviceready", onDeviceReady, false);  
  
    var iabRef = null;  
  
    function iabLoadStart(event) {  
    }  
  
    function iabLoadStop(event) {  
    }  
  
    function iabLoadError(event) {  
    }  
  
    function iabClose(event) {  
    }  
  
    function onDeviceReady() {  
         iabRef = window.open(url, '_blank', 'location=no');  
    }  */
	 
    	       /* var truename=this.parent.getCmpByName("truename").getValue(); 
    		    var  cardcode=this.parent.getCmpByName("cardcode").getValue();  
            	Ext.Ajax.request({
					url : __ctxPath + '/pay/registerAndBindPay.do',
					params:{
						isMobile:"1",
						truename:curUserInfo.loginname,
						email:curUserInfo.email,
						telphone:curUserInfo.telphone,
						truename:curUserInfo.truename,
						cardcode:curUserInfo.cardcode
					},
				    success : function(response) {
					var responseText = Ext.util.JSON.decode(response.responseText);
					if (responseText.success==true) {
					  Ext.Msg.alert("", responseText.errMsg);
					  mobileNavi.pop();
					}else{
					  Ext.Msg.alert("", responseText.errMsg);
					}
					
				}
			});    	*/
    }

});
