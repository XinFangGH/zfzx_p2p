
Ext.define('hrmobile.public.myhome.myloan.repayment', {
    extend: 'mobile.List',
    name:'repayment',
    constructor: function (config) {
    	config = config || {};
    	recordsitoInvestlist=new Array();
    	Ext.apply(config,{
    		modeltype:"backin",
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
    		fields:[ {
						name : 'bidId'
					},{
						name : 'accMoney'
					},{
						name : 'backmoney'
					},{
						name : 'compensationMoney'
					},{
						name : 'intentDate'
					},{
						name : 'intenttotal'
					},{
						name : 'loanInterest'
					},{
						name : 'payintentPeriod'
					},{
						name : 'principal'
					},{
						name : 'proName'
					},{
						name : 'repaymentTotal'
					},{
						name : 'serviceMoney'
					}],
    	        url : __ctxPath + '/user/newLoanmanagementBpCustMember.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
	    	    		planstate:'7',
	    	    		toAction:"Repayment",
	    	    		start:"0",
						isMobile : "1"
				},
 	       pressedCls:'',
		   itemTpl: new Ext.XTemplate( '<div class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">'+
		        '<nav class="inves-nav swiper-slide swiper-slide-next">'+
		           ' <ul>'+
		                '<li>'+
		                    '<div class="box">'+
		                        '<div class="inves-nav-title" style="margin-left: 15px;"><h1>{proName}</h1>'+
		                    	'<div style="float: right;margin: 2% 10% 0% 0%;">'+
//		                    		'<b class="myloanguanl"  onclick="javascript:huankuanauto({bidId})">还款授权</b>'+
		                    		'<b class="myloanguanl"  onclick="javascript:huankuanauto({bidId})">立即还款</b>'+
		                    	'</div></div>'+
		                        '<div class="figure-nav">'+
		                            '<ul>'+
		                                '<li style="width:20%">' +
		                                	'<span id="peridId">{payintentPeriod}</span><br>还款期号</li>'+
		                                '<li style="width:20%;margin:0%">' +
		                                	'<span>{intentDate}</span>' +
		                                	'<br>还款日' +
		                                '</li>'+
		                                '<li style="width:20%">' +
		                                	'<span>{intenttotal}<label></label></span>' +
		                                	'<br>本息合计' +
		                                '</li>'+
		                                '<li style="width:20%">' +
		                                	'<span>{accMoney}<label></label></span>' +
		                                	'<br>当前罚息' +
		                                '</li>'+
		                                '<li style="width:20%">' +
		                                	'<span id="notMoney">{repaymentTotal}<label></label></span>' +
		                                	'<br>应还总额' +
		                                '</li>'+
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
    	
    	 // 立即还款
    	 huankuanauto = function(bidId) {
    	 	// 获取应还金额
    	 	var $notMoney = $('#notMoney').text();
    	 	
    	 	// 获取期数
    	 	var $peridId = $('#peridId').text();
    	 	var url = __ctxPath + "/pay/repayMentByLoanerPay.do" + 
    	 				"?planId="+ 
		 				 bidId + 
		 				'&peridId=' +
		 				 $peridId +
		 				"&notMoney=" + 
		 				 $notMoney;
//    		 var url =__ctxPath+"/pay/autoRepaymentAuthorizationPay.do?isMobile=1&planId="+bidId+"&actionStatus=";
    		 window.open(url, "_blank");
        	}
    },
    a:function(e){}
});


