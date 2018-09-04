Ext.define('hrmobile.public.myhome.apply.carLoanApply', {
	
	    extend: 'Ext.form.Panel',
	    name: 'carLoanApply',
	    constructor: function (config) {
	    	config = config || {};
	    	Ext.apply(config,{
	    	    title:"<font color="+topColor+" style='font-size:"+topsize+"'>车易贷款申请</font>",
				width:"100%",
			    height:"100%",
		    	fullscreen: true,
			    scrollable: {
	    	        direction: 'vertical'
	    	    },
	    	    items:[{
	    	    	html: '<div class="">'+
						 '<div class="">申请条件<br/>'+
						     '<select name="" id="sqtj">'+
								 '<option value="">请选择</option>'+
							 '</select>'+
//						     '<a>1.22-55周岁的中国公民</a><br/>'+
//						     '<a>2.在现单位工作满6个月，已正式签 署劳动合同</a><br/>'+
//						     '<a>3.月收入2000元以上</a><br/>'+
						     '<a onclick="javascript:apply();">申请借款</a>'+
						 '</div>'+
						 '<div class="re-titleses">必要申请资料<br/>'+
//								 '<p name="" id="bysqzl"></p>'+
								 '<select name="" id="bysqzl">'+
								 '<option value="">请选择</option>'+
								 '</select>'+
				         '</div>'+
						 '<div class="">借款方式<br/>'+
							 '<div class="">借款期限'+
							 	 '<select name="" id="jkqx">'+
									 '<option value="">请选择</option>'+
								 '</select>'+
							 '</div>'+
							 '<div class="">借款年利率'+
							 	 '<select name="" id="jknlv">'+
									 '<option value="">请选择</option>'+
								 '</select>'+
							 '</div>'+
						 '</div>'+
//						 '<div class="">'+
//						 	 '<p>可选认证资料</p>'+
//						 '</div>'+
//						 '<div class="">'+
//						 	 '<p>借款方式</p>'+
//						 '</div>'+
//						 '<div class="">'+
//							 '<p>费用说明</p>'+
//						 '</div>'+
//						 '<div class="">'+
//						 	 '<p>每月还款额</p>'+
//						 '</div>'+
						 '<div class="infoBtnBtn">'+
				            '<button onclick="javascript:apply();">申请借款</button>'+
				         '</div>'+
			         '</div>'}],
			     
				listeners :{
					    activate : function(){
					    			Ext.Ajax.request({
							     		url : __ctxPath + '/loan/showProductP2pLoanProduct.do',
					    	  			async:false,
					    	  			params:{
					    	  			   isMobile : "1",
					    	  			   productId: '13'
					    	  			},
					    	  		   	success : function(response) {
						    	  		   	var responseText = Ext.util.JSON.decode(response.responseText);
					    	  		   		var bysqzl = document.getElementById("bysqzl");
						    	  		   	bysqzl.length = 1;
								    		for(var i=0;i<responseText.newCounts.length;i++){
								    			var Option=document.createElement("OPTION");
									    		     Option.value=responseText.newCounts[i].conditionId;
									    		     Option.text=responseText.newCounts[i].conditionContent;
									    		     bysqzl.options.add(Option);
								    		}
								    		var sqtj= document.getElementById("sqtj");
								    		sqtj.length = 1;
								    		for(var i=0;i<responseText.newsCount.length;i++){
								    			var Option=document.createElement("OPTION");
									    		     Option.value=responseText.newsCount[i].conditionId;
									    		     Option.text=responseText.newsCount[i].conditionContent;
									    		     sqtj.options.add(Option);
								    		}
								    		var jkqx= document.getElementById("jkqx");
								    		jkqx.length = 1;
								    		for(var i=0;i<responseText.data.length;i++){
								    			var Option=document.createElement("OPTION");
									    		     Option.value=responseText.data[i].rateId;
									    		     Option.text=responseText.data[i].loanTime+'个月';
									    		     jkqx.options.add(Option);
								    		}
								    		var jknlv= document.getElementById("jknlv");
								    		jknlv.length = 1;
								    		for(var i=0;i<responseText.data.length;i++){
								    			var Option=document.createElement("OPTION");
									    		     Option.value=responseText.data[i].rateId;
									    		     Option.text=responseText.data[i].yearAccrualRate+'%';
									    		     jknlv.options.add(Option);
								    		}
					       				}
					    	  		});
					
							}
					   }
			});
	    	this.callParent([config]);
			 apply= function(){
			 	var aaa=document.getElementById("jkqx").value;
					mobileNavi.push(Ext.create('hrmobile.public.myhome.apply.basicMessage',{}));
					}		
	    }
	});
