Ext.define('mobile.usermanage.GesturePasswordLogin', {
    extend: 'Ext.Panel',
    name: 'GesturePasswordLogin',
    constructor: function (config) {
    	loginOther=function(){
    	     gesturePasswordLogin.destroy();
    	     mobileNavi = Ext.create('mobile.View',{fullscreen: true});
    	    /* login = Ext.create('hrmobile.public.myhome.login',{fullscreen: true});
             mobileNavi.add(login);*/
             
             mobileView.add(mobileNavi);
             
    	}
    	forgetGesturePassword=function(){
    		
    		var username = localStorage.getItem("userName")!=null?localStorage.getItem("userName"):'';
    	   localStorage.setItem(username+"isGesturePassword","false");
    	     gesturePasswordLogin.destroy();
    	    mobileNavi = Ext.create('mobile.View',{fullscreen: true});
    	    /* login = Ext.create('hrmobile.public.myhome.login',{fullscreen: true});
             mobileNavi.add(login);*/
             
             mobileView.add(mobileNavi);
    	
    	}
    	config = config || {};
    	var userName = localStorage.getItem("userName")!=null?localStorage.getItem("userName"):'';
    	Ext.apply(config,{
    		style:"background-color:#0D1B38",
    		items : [
    	/*		{
	        	    docked: 'top',
	        	     title: '金智万维ERP管理系统',
	        	    xtype: 'titlebar',
	        	    items:[{
	        	    	align : 'right',
			            xtype: 'button',
			            name: 'submit',
			            text:'设置',
			            cls : 'buttonCls',
			            handler:this.setConfig
	        	    }]
	        	},*/
		        {
		         //docked: 'top',
    			 xtype: 'panel',
    			 //height:"120px",
    			 html:'<div  class="gestureLoginname">欢迎你'+userName+'</div></br><div id="errorMsg"  style="font-size:18px;text-align:center;color:#ED080D;" class="gestureLoginerror"></div></br>' 
 
    			}
    			,{
    			 docked: 'top',
    			 xtype: 'panel',
    			 html:'<div id="myCanvasdiv" ><canvas id="myCanvas"></canvas></div>' 
       
 
    			},
		        {
    			 xtype: 'panel',
    			 html:'<div class="gestureLogin"><span  onclick="javascript:loginOther();" >登录其它账户</span>'+ 
                       '&nbsp;&nbsp;&nbsp;&nbsp;<span  onclick="javascript:forgetGesturePassword();" >忘记手势密码</span></div>'
    			 //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 
    			}
    			
	        ],
	         listeners: {
	         
	        	 painted: function() {
	                windowonload();
	
	            }
	         }
    	});
      
        	
    	this.callParent([config]);
    	 //window.onload =windowonload;
    	 //<div >'+userName+'</div></br><div id="errorMsg">adfadfsdad</div></br>
    }
    
  

});