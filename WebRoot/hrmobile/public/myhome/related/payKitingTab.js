Ext.define('hrmobile.public.myhome.related.payKitingTab', {
	extend: 'Ext.form.Panel',
    name: 'payKitingTab',
    constructor: function (config) {
		config = config || {};
	    Ext.apply(config,{
        	width:"100%",
		    height:"100%",
		    fullscreen: true,
		    title:"<font color="+topColor+" style='font-size:"+topsize+"'>贷款试算</font>",
            scrollable:{
		    	direction: 'vertical'
		    },
    		items: [{
		                defaults:{
		                	xtype: 'textfield',
		                	labelWidth:"100px"
		            },
		            items: [
		            	{
							xtype:"label",
						    html: "<div style='height:1px;></div>"
						    
						},
			           {
	                        label: "贷款金额：",
	                        inputCls:"loaninput",
	                        placeHolder:"请输入借款金额（元）",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"loanmoney"
	                        
	                    },{
	                        label: "年化利率：",
	                        inputCls:"loaninput",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"loanlimit"
	                      
	                        
	                    },{
	                        label: "贷款期限：",
	                        inputCls:"loaninput",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"loanmonth"
	                      
	                        
	                    },{
	                        label: "还款选项：",
	                        value:"每月付息",
	                        disabled:true,
	                        inputCls:"loaninput",
	                        style:"margin:5px 0px 5px 15px",
	                        name:"loanselect"
	                      
						},{
							xtype:"button",
							ui:"action",
							text:"计算",
							style:"float: left;width: 40%;margin-left: 5%;background:"+themeColor+";border-color:"+themeColor+";color: #fff;",
							handler:this.sumloan
						},{
							xtype:"button",
							ui:"action",
							text:"重置",
							style:"float: right;width: 40%;margin-right:5%;background:"+themeColor+";border-color:"+themeColor+";color: #fff;",
							handler:this.alldelete
						},{
							xtype:"panel",
							style:"padding-top: 10%;",
							id:"loanbill"
						}]
		      }]
	        });
    
        this.callParent([config]);
	},
	sumloan:function(){
		var loanbill=Ext.getCmp('loanbill');
		var loanmoney=this.parent.getCmpByName("loanmoney").getValue();  //借款金额
		var loanlimit=this.parent.getCmpByName("loanlimit").getValue();  //年化利率
		var loanmonth=this.parent.getCmpByName("loanmonth").getValue();  //借款期限
		var loanselect=this.parent.getCmpByName("loanselect").getValue();  //还款选项
		if(Ext.isEmpty(loanmoney)){
			loanbill.setHtml("<div style='margin: 10px 20px;'><font color='"+themeColor+"'>借款金额不能为空</font></div>");
			return;
		}
		if(Ext.isEmpty(loanlimit)){
			loanbill.setHtml("<div style='margin: 10px 20px;'><font color='"+themeColor+"'>年化利率不能为空</font></div>");
			return;
		}
		if(Ext.isEmpty(loanmonth)){
			loanbill.setHtml("<div style='margin: 10px 20px;'><font color='"+themeColor+"'>借款期限不能为空</font></div>");
			return;
		}
		if(Ext.isEmpty(loanselect)){
			loanbill.setHtml("<div style='margin: 10px 20px;'><font color='"+themeColor+"'>还款选项不能为空</font></div>");
			return;
		}
		loanselect="1";
		var html11=allsumloan(loanmoney,loanlimit,loanmonth,loanselect);
		var html22='<div class="record-table" style="padding:0 !important;">'+
		            '<table style="margin: 0% 5% !important;width: 90%;">'+
		             '<tbody style="padding:0 !important;">' +html11
		             '</tbody></table>'+
		           '</div>'
			
		loanbill.setHtml(html22);
		return;
	},
	alldelete:function(){
		var loanbill=Ext.getCmp('loanbill');
		this.parent.getCmpByName("loanmoney").setValue();  //借款金额
		this.parent.getCmpByName("loanlimit").setValue();  //年化利率
		this.parent.getCmpByName("loanmonth").setValue();  //借款期限
		this.parent.getCmpByName("loanselect").setValue();  //还款选项
	}

});