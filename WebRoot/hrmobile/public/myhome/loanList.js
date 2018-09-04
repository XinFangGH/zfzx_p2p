
//creditorList.js
Ext.define('hrmobile.public.myhome.loanList', {
    extend: 'mobileloan.List',
    xtype: 'loanList',
    name: 'loanList',
    constructor: function (config) {
    	config = config || {};
    	 var panel=Ext.create('Ext.Panel',{
    			docked:'top',
    			items:[
    		        {html:'<body>'+
		    				'<div class="onlineBorr">'+
		    					'<p><b>快</b> <font>贷款最快一天到账，额度最高50万元</font></p>'+
		    					'<p><b style="float: left;" >简</b> <font style="float: left;line-height: 20px;display: inline-block;width: 85%;">申请简单无需任何担保，纯信用，仅需网上提交信息、资料</font></p>'+
		    				'</div>'+
		    			  '</body>'
    		        }]});
    	Ext.apply(config,{
    		modeltype:"loanList",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>我要贷款</font>",
    		items:[panel],
    		pullRefresh: true,
		    listPaging: false,
    		fields:[ {
						name : 'productId'
					},{
						name : 'assure' //
					},{
						name : 'productName' //
					},{
						name : 'loanLife' //
					},{
						name : 'payintentPeriod' //
					},{
						name : 'money' //
					},{
						name : 'conditiomContent' //
					},{
						name : 'userScope' //
					}],
    	        url : __ctxPath + '/loan/listP2pLoanProduct.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
						isMobile : "1"
			},
 	       pressedCls:'',
 	       noMoreRecordsText:'',
		   itemTpl:new Ext.XTemplate(  
				   '<div onclick="javascript:loankuan({productId});" class="rechargeMon box-white"><p class="hasArrow">{productName}（{userScope}）</p></div>',
				   {
		    			projMoney: function(bidMoney) {
		    				return bidMoney/10000;
						}
						
		    		}),
        listeners:{  
               
        }
    	});
       
    	this.callParent([config]);
    		loankuan=function(productId) {
				 Ext.Ajax.request({
					url: __ctxPath +"/loan/creditInfoP2pLoanProduct.do",
					 params : {
					 	isMobile:'1',
					 	productId:productId
					 	
			         },
				    success : function(response) {
				    	var responseText = Ext.util.JSON.decode(response.responseText);
				    	if(responseText.success==true){
					    	hidebottomBarIndex();
					        mobileNavi.push(Ext.create('hrmobile.public.myhome.apply.loanapply',{data:responseText.data}));
				    	}else{
				    		Ext.Msg.alert("",responseText.message);
				    		return ;
				    	}
				    	
    	            }});
    	}
	
    },
    a:function(e){}
});

