
Ext.define('hrmobile.public.myhome.myloan.settled', {
	
    extend: 'mobile.List',
    name:'settled',
    constructor: function (config) {
    	config = config || {};
    	recordsitoInvestlist=new Array();
    	Ext.apply(config,{
    		modeltype:"settled",
    		flex:1,
    		width:"100%",
		    height:"100%",
    		pullRefresh: true,
		    listPaging: true,
		    isload:true,
		    loadfunction:function(this1,records){
		    	for (var i=records.length-1; i >= 0; i--) {  
				    recordsitoInvestlist.push( records[i] );  
		    	}  
					
		    },
    		fields:[{
    					name:'proName'
    				},{
						name : 'bidId'
					},{
						name : 'payMoney'
					},{
						name : 'bidTime'
					},{
						name : 'loanLife'
					},{
						name : 'interestRate'
					},{
						name : 'repaymentTotal'
					}],
    	       url : __ctxPath + '/user/newLoanmanagementBpCustMember.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
	    	    		planstate:'10',
	    	    		start:"0",
						isMobile : "1"
				},
 	       pressedCls:'',
		   itemTpl: new Ext.XTemplate( '<div class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">'+
		        '<nav class="inves-nav swiper-slide swiper-slide-next">'+
		           ' <ul>'+
		                '<li>'+
		                    '<div class="box">'+
		                        '<div class="inves-nav-title" style="margin-left: 15px;"><h1>{proName}</h1>' +
		                        '<div style="float: right;margin: 2% 10% 0% 0%;">'+
		                        	'<b  class="myloanguanl" onclick="javascript:loansetihkjh({bidId})">回款计划</b></div>'+
		             			'</div>'+
		                        '<div class="figure-nav">'+
		                            '<ul>'+
		                                '<li style="width:20%"><span>{payMoney}</span>元<br>招标金额</li>'+
		                                '<li style="width:20%;margin:0%"><span>{bidTime}</span><br>发标时间</li>'+
		                                '<li style="width:20%"><span>{loanLife}<label></label></span><br>期限</li>'+
		                                '<li style="width:20%"><span>{interestRate}<label>%</label></span><br>年化利率</li>'+
		                                '<li style="width:20%"><span>{repaymentTotal}<label>元</label></span><br>应还总额	</li>'+
		                            '</ul>'+
		                        '</div>'+
		                    '</div>'+
		                '</li>'+
		            '</ul>'+
		        '</nav>'+
        	'</div>'),
	          listeners :  {
	          	painted : function (this1) {
	          		// ST刷新列表
	          		this.store.loadPage(1);
	          	}
	          }
    	});
       
    	this.callParent([config]);
    	loansetihkjh = function(bidId) {
    			mobileNavi.push(Ext.create('hrmobile.public.myhome.myloan.Paymentplan',{bidId:bidId}));
    	 }
    },
    a:function(e){}
});


