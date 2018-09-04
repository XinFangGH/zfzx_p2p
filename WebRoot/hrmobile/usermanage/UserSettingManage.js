

Ext.define('hrmobile.usermanage.UserSettingManage', {
    extend: 'Ext.Panel',
    
    name: 'UserSettingManage',

    constructor: function (config) {
      
    	updateGesturePassword=function(){
    		var username = localStorage.getItem("userName")!=null?localStorage.getItem("userName"):'';
	    	localStorage.setItem(username+"updateGesturePassword","1");
	        localStorage.setItem(username+"settinggesturePasswordcount",1);
	          //是开始设置密码是是修改密码
	          localStorage.setItem(username+"isupdatepassword","yes");
			 gesturePasswordSetting = Ext.create('mobile.usermanage.GesturePasswordSetting', {fullscreen: true,errormsg:"请绘制原始解锁图案"});
	    	mobileNavi.getNavigationBar().hide();
	    	hidebottomBarIndex();
			 mobileNavi.push(gesturePasswordSetting);
    	}
    	config = config || {};
    	Ext.apply(config,{
		    fullscreen: true,
		     title:"<font color="+themeColor+">系统设置</font>",
		    scrollable:{
		    	direction: 'vertical'
		    },
		    items: [
		    {    xtype: 'fieldset',
		         items:[
		          {
		           xtype:"panel",
		           html:"<div style='background:white;height:50px;padding: 15px;'>"+(null==curUserInfo?"":curUserInfo.loginname)+"</div>"
		          }
		          
		         ]
		    },
		     /*{    xtype: 'fieldset',
		          items:[
		          {
		           xtype:"panel",
		           html:"<div style='background:white;height:50px;font-size:16px;padding: 15px;'>修改手续密码<span style='float:right;' onclick=\"javascript:updateGesturePassword();\">>&nbsp;&nbsp;&nbsp;&nbsp;</span></div>"
		          }
		         ]
		    },*/
		     {    xtype: 'fieldset',
		          items:[
		          {
		           xtype:"panel",
		           html:"<div style='background:white;height:50px;font-size:16px;padding: 15px;' onclick=\"javascript:exitappself();\">退出</div>"
		          }
		         ]
		    }
		    ]
    	});

   
        exitappself=function(){
          
							Ext.Msg.show({
								title : '提示',
								message : '请选择退出方式',
								width : 240,
								buttons : [{
											text : '注销帐号',
											itemId : '1'
										}, {
											text : '退出app',
											itemId : '0'
										}, {
											text : '取消',
											itemId : '2'
										}],
								fn : function(itemId) {
									if (itemId == '0') {
										Ext.Msg.show({
											message : '退出app不会删除历史数据,是否退出?',
											width : 180,
											buttons : [{
														text : '确认',
														itemId : '1'
													}, {
														text : '取消',
														itemId : '0'
													}],
											fn : function(itemId) {
												if (itemId == '1') {
													navigator.app.exitApp();
												}
											}
										});
										
									}else if(itemId == '1'){
										Ext.Msg.show({
											message : '注销账号会清除历史数据,是否注销?',
											width : 180,
											buttons : [{
														text : '确认',
														itemId : '1'
													}, {
														text : '取消',
														itemId : '0'
													}],
											fn : function(itemId) {
												if (itemId == '1') {
													mobileNavi.setMasked(true);
													/*Ext.Ajax.request({
																url : __ctxPath
																		+ '/user/setSessinonNotValidBpCustMember.do'
															});*/
													curUserInfo=null;
													window.location.reload();
													localStorage.setItem("userName",'');
													/*mobileNavi.destroy();
													mobileNavi = Ext.create('mobile.View',{fullscreen: true});
									                mobileView.add(mobileNavi);*/
												}
											}
										});
									}
								}
							});
							/*Ext.Msg.show({
								message : '是否退出当前帐号',
								width : 180,
								buttons : [{
											text : '确认',
											itemId : '1'
										}, {
											text : '取消',
											itemId : '0'
										}],
								fn : function(itemId) {
									if (itemId == '1') {
										mobileNavi.setMasked(true);
										Ext.Ajax.request({
													url : __ctxPath
															+ '/j_logout.do'
												});
										window.location.reload();
									}
								}
							});
							*/
					
        
        
        }
    	this.callParent([config]);
    	
    }

});
