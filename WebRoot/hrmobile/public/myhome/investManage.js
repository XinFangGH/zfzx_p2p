
Ext.define('hrmobile.public.myhome.investManage', {
    extend: 'Ext.tab.Panel',
    name: 'investManage',
    alias: 'widget.investManage',
    constructor: function (config) {
	config = config || {};
	var	itoInvestList = Ext.create('hrmobile.public.myhome.itoInvestList',{
    });
	var	rmmplanlist = Ext.create('hrmobile.public.myhome.Rmmplan.RmmplanList',{
    });
    Ext.apply(config,{
           title:"<font color=" + topColor + " style='font-size:" + topsize + "'>我要出借</font>",
			 layoutOnTabChange: true,
			tabBar:
			{   defaults:{flex:1},
				layout:{
					pack:'center'
				},
			  style: 'width:100%;height:50px;line-height:50px;background:#D6450C;font-size:16px;color:#FFFFFF;position:fixed;left:0;top:0;z-index:9;'
			},
	        items: [
	                {
	                  title: '散标投资',
                        style:'font-size:18px',
                        id:'t1',
		                items:[{
		                	xtype: 'container',
		    	            items:itoInvestList
		    	        }],

	                },
	                /*
					{
	                  title: 'D债权',
		                items:[{
		                	xtype: 'container',
		    	            items:mmplanList
		    	        }]
	    	        },{
	                  title: 'U债权',
		                items:[{
		                	xtype: 'container',
		    	            items:ummplanlist
		    	        }]
	    	        }*/
	    	     /*   {
	                  title: '债权交易',
		                items:[{
		                	xtype: 'container',
		    	            items:rmmplanlist
		    	        }]
	    	        }*/
	    	       /* ,{
	                  title: 'VIP专享',
		                items:[{
		                	xtype: 'container',
		    	            items:mmplanList
		    	        }],
					listeners : {
						// 点击事件
					    	show: function (this1, eOpts) {
					    		// 判断是否登录
					    		if (Ext.isEmpty(curUserInfo)) {

					    			//  提示框
    								Ext.Msg.alert('提示','请先登录!', function () {
    									mobileNavi.push(Ext.create('hrmobile.public.myhome.login', {}));

						    			// 隐藏返回按钮
						    			mobileNavi.getNavigationBar().getBackButton().hide();
    								}
    								);

					    		}
					    	}

					}
	    	        },
	    	        {
	                  title: 'VIP债权交易',
		                items:[{
		                	xtype: 'container',
		    	            items:ummplanlist
		    	        }],
						listeners : {
							// 点击事件
						    	show: function (this1, eOpts) {
						    		// 判断是否登录
						    		if (Ext.isEmpty(curUserInfo)) {

						    			//  提示框
	    								Ext.Msg.alert('提示','请先登录!', function () {
	    									mobileNavi.push(Ext.create('hrmobile.public.myhome.login', {}));

							    			// 隐藏返回按钮
							    			mobileNavi.getNavigationBar().getBackButton().hide();
	    								}
	    								);

						    		}
						    	}

						}
	    	        }*/
	            ],
        listeners : {
            painted : function () {
          /*      $("#ext-tab-1").css({"background":"#D6450C","color":"#FFFFFF","height":"35px","line-height":"35px","border-radius":"0","font-size":"14px","padding":"0px","margin-top":"0px"});
                $("#ext-tab-2").css({"background":"#D6450C","color":"#FFFFFF","height":"35px","line-height":"35px","border-radius":"0","font-size":"14px","padding":"0px","margin-top":"0px"});

                $("#ext-element-88").css({"font-size":"18px","color":"#FFFFFF","line-height":"30px"});
                $("#ext-element-93").css({"font-size":"14px","color":"#FFFFFF","line-height":"30px","margin-top":"0px"});*/


             /*   $("#ext-element-88").click(function(){
                    $("#ext-element-88").css({"font-size":"18px","border-bottom":"2px solid #FFF"});
                    $("#ext-element-93").css({"font-size":"14px","border-bottom":"0px solid #FFF"});
				});

                $("#ext-element-93").click(function(){
                    $("#ext-element-93").css({"font-size":"18px","border-bottom":"2px solid #FFF"});
                    $("#ext-element-88").css({"font-size":"14px","border-bottom":"0px solid #FFF"});
                });
*/
            }
        }

    	});
    	this.callParent([config]);


    }

});
