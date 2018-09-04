Ext.define('hrmobile.public.myhome.apply.workinformation', {
	    extend: 'Ext.form.Panel',
	    name: 'workinformation',
	    constructor: function (config) {
	    	config = config || {};
	    	Ext.apply(config,{
	    	    title:"<font color="+topColor+" style='font-size:"+topsize+"'>工作信息填写</font>",
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
									                '<li><a >家庭信息</a></li>'+
									                '<li class="navBg"><a >工作信息</a></li>'+
									                '<li><a >网店信息</a></li>'+
									                '<li><a style="border-radius: 0 8px 8px 0 ">资料上传</a></li>'+
									       '</div>'
								},{
			                        label: "单位名称：",
			                        inputCls:"messagee",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:(isStringEmpty(curUserInfo.truename)?"":curUserInfo.truename),
			                        name:"oldname"
				            	},{
			                        label: "职位：",
			                        inputCls:"messagee",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:(isStringEmpty(curUserInfo.truename)?"":curUserInfo.truename),
			                        name:""
				            	},{
			                        label: "月收入：",
			                        inputCls:"messagee",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:(isStringEmpty(curUserInfo.truename)?"":curUserInfo.truename),
			                        name:"financeApply.productName"
				            	},{
			                        label: "工作邮箱：",
			                        inputCls:"messagee",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:(isStringEmpty(curUserInfo.truename)?"":curUserInfo.truename),
			                        name:"financeApply.productName"
				            	},{
			                        label: "工作城市：",
			                        inputCls:"messagee",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:(isStringEmpty(curUserInfo.truename)?"":curUserInfo.truename),
			                        name:"financeApply.productName"
				            	},{
			                        label: "真实姓名：",
			                        inputCls:"messagee",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:(isStringEmpty(curUserInfo.truename)?"":curUserInfo.truename),
			                        name:"financeApply.productName"
				            	},{
			                        label: "真实姓名：",
			                        inputCls:"messagee",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:(isStringEmpty(curUserInfo.truename)?"":curUserInfo.truename),
			                        name:"financeApply.productName"
				            	},{
			                        label: "真实姓名：",
			                        inputCls:"messagee",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:(isStringEmpty(curUserInfo.truename)?"":curUserInfo.truename),
			                        name:"financeApply.productName"
				            	},{
			                        label: "真实姓名：",
			                        inputCls:"messagee",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:(isStringEmpty(curUserInfo.truename)?"":curUserInfo.truename),
			                        name:"financeApply.productName"
				            	},{
			                        label: "真实姓名：",
			                        inputCls:"messagee",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:(isStringEmpty(curUserInfo.truename)?"":curUserInfo.truename),
			                        name:"financeApply.productName"
				            	},{
			                        label: "真实姓名：",
			                        inputCls:"messagee",
			                        disabled:true,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:(isStringEmpty(curUserInfo.truename)?"":curUserInfo.truename),
			                        name:"financeApply.productName"
				            	},{
			                    	style:"margin:20px 20px 250px 20px;background:"+themeColor+";font-color:white",
			                    	xtype: 'button',
			                        text:"<font color=white>下一步</font>",
			                        handler:this.workinfornext
				            	}]
					}]
			     
	    	});
	
	    	this.callParent([config]);
	    	
	    },

		workinfornext:function(){
			mobileNavi.push(Ext.create('hrmobile.public.myhome.apply.interMessage',{}));
			}
	});
