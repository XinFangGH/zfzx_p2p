Ext.define('hrmobile.public.myhome.apply.workMessage', {
	
	    extend: 'Ext.form.Panel',
	    name: 'workMessage',
	    constructor: function (config) {
	    	config = config || {};
	    	Ext.apply(config,{
	    	    title:"<font color="+topColor+" style='font-size:"+topsize+"'>图片信息录入</font>",
				width:"100%",
			    height:"100%",
		    	fullscreen: true,
			    scrollable: {
	    	        direction: 'vertical'
	    	    },
				items:[{
		                defaults:{
		                	xtype: 'textfield',
		                	labelWidth:"100px"
		            	},
		            	items:[{	
		            				xtype: 'panel',
								    html:  '<div class="infoNav">'+
									                '<li style="border-radius: 8px 0 0 8px "><a>基本信息</a></li>'+
									                '<li><a>家庭信息</a></li>'+
									                '<li><a >工作信息</a></li>'+
									                '<li><a>网店信息</a></li>'+
									                '<li  class="navBg" style="border-radius: 0 8px 8px 0 "><a>资料上传</a></li>'+
									       '</div>'+
									        '<div class="application">'+
										        '<p class="card">身份证 <span>*</span></p>'+
										        '<div>'+
										            '<img src="'+__ctxPath+'/hrmobile/resources/imageMain/usercode.png" alt="" width="263" height="140"/>'+
										        '</div>'+
										        '<p class="card">学生证</p>'+
										        '<div>'+
										            '<img src="'+__ctxPath+'/hrmobile/resources/imageMain/studentcode.png" alt="" width="263" height="140"/>'+
										        '</div>'+
//										        '<div class="infoBtn">'+
//										            '<button>提交申请</button>'+
//										        '</div>'+
										    '</div>'
								},{
			                    	style:"margin:20px 20px 250px 20px;background:"+themeColor+";font-color:white",
			                    	xtype: 'button',
			                        text:"<font color=white>提交申请</font>",
			                        handler:this.workMessage
				            	}]
					}]
			     
	    	});
	
	    	this.callParent([config]);
	    },
	 	  workMessage:function(){
					mobileNavi.push(Ext.create('hrmobile.public.myhome.loanList',{}));
					}
	});
