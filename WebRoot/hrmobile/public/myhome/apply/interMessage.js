Ext.define('hrmobile.public.myhome.apply.interMessage', {
	
	    extend: 'Ext.form.Panel',
	    name: 'interMessage',
	    constructor: function (config) {
	    	config = config || {};
	    	Ext.apply(config,{
	    	    title:"<font color="+topColor+" style='font-size:"+topsize+"'>网店信息填写</font>",
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
									                '<li><a >工作信息</a></li>'+
									                '<li  class="navBg" ><a >网店信息</a></li>'+
									                '<li style="border-radius: 0 8px 8px 0 "><a>资料上传</a></li>'+
									       '</div>'
								},{
			                        label: "真实姓名：",
			                        inputCls:"messagee",
			                        placeHolder:"请输入姓名",
			                        style:"margin:5px 0px 5px 15px",
			                        name:"financeApply.productName"
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
			                        label: "身份证号：",
			                        inputCls:"message",
			                        placeHolder:"请输入身份证号",
			                        style:"margin:5px 0px 5px 15px",
			                        name:"financeApply.productName"
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
			                        label: "手机号码：",
			                        inputCls:"message",
			                        placeHolder:"请输入手机号码",
			                        style:"margin:5px 0px 5px 15px",
			                        name:"financeApply.productName"
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
			                        label: "出生日期：",
			                        inputCls:"message",
			                        placeHolder:"请输入出生日期",
			                        style:"margin:5px 0px 5px 15px",
			                        name:"financeApply.productName"
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
			                        label: "最高学历：",
			                        inputCls:"message",
			                        placeHolder:"请输入最高学历",
			                        style:"margin:5px 0px 5px 15px",
			                        name:"financeApply.productName"
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
			                        label: "入学年份：",
			                        inputCls:"message",
			                        placeHolder:"入学年份",
			                        style:"margin:5px 0px 5px 15px",
			                        name:"financeApply.productName"
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
			                        label: "毕业院校：",
			                        inputCls:"message",
			                        placeHolder:"毕业院校",
			                        style:"margin:5px 0px 5px 15px",
			                        name:"financeApply.productName"
				            	},{
			                    	style:"margin:20px 20px 250px 20px;background:"+themeColor+";font-color:white",
			                    	xtype: 'button',
			                        text:"<font color=white>下一步</font>",
			                        handler:this.interinfornext
				            	}]
					}]
			     
	    	});
	
	    	this.callParent([config]);
	    },
	    interinfornext:function(){
			mobileNavi.push(Ext.create('hrmobile.public.myhome.apply.workMessage',{}));
			}
	});
