
//creditorList.js
Ext.define('hrmobile.public.myhome.mmplan.mmplansettled', {
	
    extend: 'mobile.List',
    name:'mmplansettled',
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
    		fields:[ {
						name : 'orderId'
					},{
						name : 'mmName'
					},{
						name : 'buyMoney'
					},{
						name : 'promisYearRate'
					},{
						name : 'startinInterestTime'
					},{
						name : 'orderlimit'
					},{
						name : 'plManageMoneyPlan'
					}],
    	        url : __ctxPath + '/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
	    	    		state10:"10",
	    	    		show:"successList",
						isMobile : "1"
			},
 	       pressedCls:'',
		   itemTpl: new Ext.XTemplate( '<div class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">'+
		        '<nav class="inves-nav swiper-slide swiper-slide-next">'+
		           ' <ul>'+
		                '<li>'+
		                    '<div class="box">'+
		                        '<div class="inves-nav-title" style="margin-left:15px;"><h1  onclick="javascript:mmplanbidDetail({plManageMoneyPlan.mmplanId});">{mmName}</h1>' +
		                        '<b class="myloanhetong"  onclick="javascript:Paymentplanmmplanset({orderId:\'{orderId}\'})" style="float: right;margin-right: 10%; margin-top: 1.5%;">回款</b>' +
		                    '</div>'+
		             
		                        '<div class="figure-nav"  onclick="javascript:mmplanbidDetail({plManageMoneyPlan.mmplanId});">'+
		                            '<ul>'+
		                                '<li><span>{buyMoney}<label>元</label></span><br>加入金额</li>'+
		                                '<li><span class="buy-red">'+
		                                	'{promisYearRate}'+
		                                '<label>%</label></span><br>预期收益率</li>'+
		                                '<li><span>{startinInterestTime}<label></label></span><br>加入时间</li>'+
		                                '<li><span>{orderlimit}<label></label></span><br>计划期限</li>'+
		                            '</ul>'+
		                        '</div>'+
		                    '</div>'+
		                '</li>'+
		            '</ul>'+
		        '</nav>'+
        	'</div>'
//							'<div class="container">'+
//							    '<div class="content">'+
//							        '<ul>'+
//							            '<li class="box-white border-gray">'+
//							                '<div class="box-content">'+
//							                    '<div class="content_left lf">'+
//							                        '<h3 style="font-size: 17px;color: #000;">'+
//							                            '<img src="hrmobile/resources/imagesP2p/s_logo.png" alt=""><span style="">{bidProName}</span>'+
//							                            '<img src="hrmobile/resources/imagesP2p/quan.png" alt="">'+
//							                            '<img src="hrmobile/resources/imagesP2p/new.png" alt="">'+
//							                        '</h3>'+
//							                        '<p>'+
//							                            '<span>项目年化</span>'+
//							                            '<b class="text-red">{interestRate}%</b>'+
//							                            '<span class="pleft">金额</span>'+
//							                            '<b class="text-red">{bidMoney}</b>'+
//							                            '<span>元</span><br>'+
//							                            '<span class="pleft">期限</span>'+
//							                            '<b class="text-red">{loanLife}</b>'+
//							                        '</p>'+
//							                    '</div>'+
//							                    '<div class="content_right rg">'+
//							                        '<div class="payRadius">'+
//							                            '<p class="text-red">抢购中</p>'+
//							                        '</div>'+
//							                    '</div>'+
//							                '</div>'+
//							            '</li>'+
//							        '</ul>'+
//							    '</div>'+
//							'</div>'
//						"<div class='percentBox'>"+
//						"<canvas id='bgab{bidId}' width='59' height='59' >+style='border: 1px solid;'</canvas>"+
//						"</div>"+
                       ,{
		    			projMoney: function(bidMoney) {
		    				return bidMoney/10000;
						},
						ruturnstae: function(publishSingeTime) {
							if(publishSingeTime<new Date().getTime()){
		    				  return "<img src='hrmobile/resources/imagesP2p/btn.jpg' alt='' width='100%' 	onclick='javascript:immediatelyInvest({" +
						     "bidId:\""
		    				}
		    				if(publishSingeTime>=new Date().getTime()){
		    				  return "<img src='hrmobile/resources/imagesP2p/yushouzhong.jpg' alt='' width='100%' onclick='javascript:immediatelyInvest({" +
						     "bidId:\""
		    				   
		    				}
		    				
						}
						})
    	});
       
    	this.callParent([config]);
    			//
    	Paymentplanmmplanset=function(data){
    		var orderId=data.orderId;
    		var url=__ctxPath +"/creditFlow/financingAgency/assigninterestlistPlManageMoneyPlanBuyinfo.do?orderId="+orderId;
    		mobileNavi.push(Ext.create('hrmobile.public.myhome.mmplan.Paymentplan',{url:url}));
    	}
    	setcontract=function(data){
         	var orderId =data.orderId;
         	var url=__ctxPath +"/creditFlow/financingAgency/buyInfoContractPlManageMoneyPlan.do?orderId="+orderId;
         	window.open(url,"_bank")
    	}
    	 bidDetail =function(data) {
    		   var productType=data.productType;
             		Ext.Ajax.request({
					url: __ctxPath +"/creditFlow/financingAgency/bidPlanDetailisMobilePlBidPlan.do",
					 params : {
						isMobile : "1",
						bidId:data.bidId
			         },
				    success : function(response) {
				    	var  responseText1=response.responseText.replace(/[\n]/ig,'');
				    	var responseText = Ext.util.JSON.decode(responseText1);
				    	var data=responseText.data;
				    	var planKeep=responseText.planKeep;
				    	var listMaterials=responseText.listMaterials;
				    	var enterPrise = responseText.enterPrise;
				    	data.investEnterpriseEnterprisename=responseText.investEnterpriseEnterprisename;
				    	if(Ext.isEmpty(responseText.data.proDes)){
				    		data.proDes=responseText.data.remark;
				    	}
				    	if(!Ext.isEmpty(responseText.formtoken)){
				    		data.formtoken=responseText.formtoken;
				    	}
				    	hidebottomBarIndex();
				    	    mobileNavi.push(Ext.create('hrmobile.public.myhome.bidDetail',{data:data,productType:productType,planKeep:planKeep,listMaterials:listMaterials,enterPrise:enterPrise}));
    	            }});
        	}
    },
    a:function(e){}
});


