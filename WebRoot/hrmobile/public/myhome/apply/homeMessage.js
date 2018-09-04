Ext.define('hrmobile.public.myhome.apply.homeMessage', {
	
	    extend: 'Ext.form.Panel',
	    name: 'homeMessage',
	    constructor: function (config) {
	    	config = config || {};
	    	Ext.apply(config,{
	    	    title:"<font color="+topColor+" style='font-size:"+topsize+"'>家庭信息填写</font>",
				width:"100%",
			    height:"100%",
		    	fullscreen: true,
			    scrollable: {
	    	        direction: 'vertical'
	    	    },
				items:[{
		                defaults:{
		                	xtype: 'textfield',
		                	labelWidth:"20%"
		            	},
		            	items:[{xtype: 'panel',
							    html:  '<div class="infoNav">'+
								                '<li style="border-radius: 8px 0 0 8px "><a>基本信息</a></li>'+
								                '<li  class="navBg"><a >家庭信息</a></li>'+
								                '<li><a >工作信息</a></li>'+
								                '<li><a >网店信息</a></li>'+
								                '<li><a style="border-radius: 0 8px 8px 0 ">资料上传</a></li>'+
								       '</div>'
								},{
				    			  xtype:'panel',
				    			  style:"margin: 5px;border: 1px solid #eee;",
				    			  html: 
							            '<div class="" style="margin:5px 5px 5px 5px;font-size:16px;color:black;">婚姻状况<div style="color:red;display: inline-block;">*</div>'+
								            '<span class="">'+
						  		             	'<select name="" id="maritalStatus" style="-webkit-appearance: none;border: none;width:70%;margin-left: 11%;height: 30px;background: #fff;">'+
													'<option value="0">请选择</option>'+
												'</select>'+
											'</span>'+
								        '</div>'
				    		    },{
			                        label: "配偶：",
			                        placeHolder:"请输入姓名",
			                        inputCls:"message",
			                        value:curUserInfo.relDirName,
									style:"margin: 5px;border: 1px solid #eee;",
			                        name:""
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
				    			  xtype:'panel',
				    			  style:"margin: 5px;border: 1px solid #eee;",
				    			  html: 
							            '<div class="" style="margin:5px 5px 5px 5px;font-size:16px;color:black;">关系<div style="color:red;display: inline-block;">*</div>'+
								            '<span class="">'+
						  		             	'<select name="" id="maritalStatus" style="-webkit-appearance: none;border: none;width:79%;margin-left: 11%;height: 30px;background: #fff;">'+
													'<option value="0">请选择</option>'+
												'</select>'+
											'</span>'+
								        '</div>'
				    		    },{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
			                        label: "手机号：",
			                        placeHolder:"请输入手机号",
			                        inputCls:"message",
			                        value:curUserInfo.relDirPhone,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        name:""
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
									label: "亲属：",
									inputCls:"message",
									value:curUserInfo.relOtherName,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        name:""
			                    },{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
				    			  xtype:'panel',
				    			  style:"margin: 5px;border: 1px solid #eee;",
				    			  html: 
							            '<div class="" style="margin:5px 5px 5px 5px;font-size:16px;color:black;">关系<div style="color:red;display: inline-block;">*</div>'+
								            '<span class="">'+
						  		             	'<select name="" id="maritalStatus" style="-webkit-appearance: none;border: none;width:79%;margin-left: 11%;height: 30px;background: #fff;">'+
													'<option value="0">请选择</option>'+
												'</select>'+
											'</span>'+
								        '</div>'
				    		    },{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
			                        label: "手机号：",
			                        inputCls:"message",
			                        placeHolder:"请输入手机号",
			                        regex : /^[1][34578][0-9]{9}$/,
									regexText : '手机号码格式不正确',
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        value:curUserInfo.relOtherPhone,
			                        name:""
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
									label: "联系人：",
									value:curUserInfo.relFriendName,
									inputCls:"message",
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        name:""
								},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
				    			  xtype:'panel',
				    			  style:"margin: 5px;border: 1px solid #eee;",
				    			  html: 
							            '<div class="" style="margin:5px 5px 5px 5px;font-size:16px;color:black;">关系<div style="color:red;display: inline-block;">*</div>'+
								            '<span class="">'+
						  		             	'<select name="" id="maritalStatus" style="-webkit-appearance: none;border: none;width:79%;margin-left: 11%;height: 30px;background: #fff;">'+
													'<option value="0">请选择</option>'+
												'</select>'+
											'</span>'+
								        '</div>'
				    		    },{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
			                        label: "手机号：",
			                        inputCls:"message",
			                        placeHolder:"请输入手机号",
			                        regex : /^[1][34578][0-9]{9}$/,
									regexText : '手机号码格式不正确',
									value:curUserInfo.relFriendPhone,
			                        style:"margin: 5px;border: 1px solid #eee;",
			                        name:""
				            	},{
									xtype:"label",
								    html: "<div style='height:1px;></div>"
								},{
									xtype:"panel",
								    html: 
//								    "<div style='>"+
									    	"<div style='font-size: 16px;margin: 5px;border: 1px solid #eee;'>" +
									    	"<p style='text-align:center;padding: .4em;'>" +
									    	"<span style='margin-left:10px;color:#000;float: left;'>是否子女：</span>"+
									    	  	"<span style='margin-right:70px;'>" +
									    	  		"<input type='radio' name='investType' value='1'>有" +
									    	  	"</span>" +
									    	  	"<span>" +
									    	  		"<input type='radio' checked='checked' name='investType' value='2'>无" +
									    	  	"</span>" +
									    	  "</p>"+
								    	  "<div>"
								},
				            	{
									xtype:"panel",
								    html: 
									    	"<div style='font-size: 16px;margin: 5px;border: 1px solid #eee;'>" +
									    	"<p style='text-align:center;padding: .4em;'>" +
									    	"<span style='margin-left:10px;color:#000;float: left;'>是否有房：</span>"+
									    	  	"<span style='margin-right:70px;'>" +
									    	  		"<input type='radio' name='investType' value='1'>有" +
									    	  	"</span>" +
									    	  	"<span>" +
									    	  		"<input type='radio' checked='checked' name='investType' value='2'>无" +
									    	  	"</span>" +
									    	  "</p>"+
								    	  "<div>"
								},{
									xtype:"panel",
								    html: 
									    	"<div style='font-size: 16px;margin: 5px;border: 1px solid #eee;'>" +
									    	"<p style='text-align:center;padding: .4em;'>" +
									    	"<span style='margin-left:10px;color:#000;float: left;'>有无房贷：</span>"+
									    	  	"<span style='margin-right:70px;'>" +
									    	  		"<input type='radio' name='investType' value='1'>有" +
									    	  	"</span>" +
									    	  	"<span>" +
									    	  		"<input type='radio' checked='checked' name='investType' value='2'>无" +
									    	  	"</span>" +
									    	  "</p>"+
								    	  "<div>"
								},{
									xtype:"panel",
								    html: 
									    	"<div style='font-size: 16px;margin: 5px;border: 1px solid #eee;'>" +
									    	"<p style='text-align:center;padding: .4em;'>" +
									    	"<span style='margin-left:10px;color:#000;float: left;'>是否有车：</span>"+
									    	  	"<span style='margin-right:70px;'>" +
									    	  		"<input type='radio' name='investType' value='1'>有" +
									    	  	"</span>" +
									    	  	"<span>" +
									    	  		"<input type='radio' checked='checked' name='investType' value='2'>无" +
									    	  	"</span>" +
									    	  "</p>"+
								    	  "<div>"
								},{
									xtype:"panel",
								    html: 
									    	"<div style='font-size: 16px;margin: 5px;border: 1px solid #eee;'>" +
									    	"<p style='text-align:center;padding: .4em;'>" +
									    	"<span style='margin-left:10px;color:#000;float: left;'>有无车贷：</span>"+
									    	  	"<span style='margin-right:70px;'>" +
									    	  		"<input type='radio' name='investType' value='1'>有" +
									    	  	"</span>" +
									    	  	"<span>" +
									    	  		"<input type='radio' checked='checked' name='investType' value='2'>无" +
									    	  	"</span>" +
									    	  "</p>"+
								    	  "<div>"
								},{
			                    	style:"margin:20px 20px 250px 20px;background:"+themeColor+";font-color:white",
			                    	xtype: 'button',
			                        text:"<font color=white>下一步</font>",
			                        handler:this.next
				            	}]
					}],
		listeners: {
		    	activate : function(){
		    		Ext.Ajax.request({
		      			url : __ctxPath + '/loadItemByNodeKeyreg.do',
		      			async:false,
		      			params:{
		      			   isMobile : "1",
		      			   nodeKey : "8"
		      			},
		      		   	success : function(response) {
		    	  		   	var responseText = Ext.util.JSON.decode(response.responseText);
		    	    		var province = document.getElementById("maritalStatus");
		    	    		province.length = 1;
		    	    		for(var i=0;i<responseText.length;i++){
		    	    			var Option=document.createElement("OPTION");
		    	    		     Option.value=responseText[i].value;
		    	    		     Option.text=responseText[i].text;
		    	    		     province.options.add(Option);
		    	    		}
		    				}
		      		});
		    	}
			}
			     
	    	});
	
	    	this.callParent([config]);
	    },
			 next: function(){
					mobileNavi.push(Ext.create('hrmobile.public.myhome.apply.workinformation',{}));
					}
	});
