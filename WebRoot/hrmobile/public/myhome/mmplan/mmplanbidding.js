
Ext.define('hrmobile.public.myhome.mmplan.mmplanbidding', {
	
    extend: 'mobile.List',
    name:'mmplanbidding',
    constructor: function (config) {
    	config = config || {};
    	recordsitoInvestlist=new Array();
    	Ext.apply(config,{
    		modeltype:"bidding",
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
						name : 'state'
					},{
						name : 'plManageMoneyPlan'
					}],
    	        url : __ctxPath + '/creditFlow/financingAgency/managelistPlManageMoneyPlanBuyinfo.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
	    	    		state2:"2",
	    	    		state7:"7",
	    	    		show:"owningList",
						isMobile : "1"
			},
 	       pressedCls:'',
		   itemTpl: new Ext.XTemplate( '<div class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">'+
		        '<nav class="inves-nav swiper-slide swiper-slide-next">'+
		           ' <ul>'+
		                '<li>'+
		                    '<div class="box">'+
		                        '<div class="inves-nav-title" style="margin-left:15px;" ><h1  onclick="javascript:mmplanbidDetail({plManageMoneyPlan.mmplanId});">{mmName}</h1>'+
		                    '<tpl if="state==7"><b class="myloanguanl"  style="float: right;margin-right:10%;">退审中</b></tpl>'+
		                    '<tpl if="state==2"><b class="myloanguanl"   onclick="javascript:outmmplan({orderId:\'{orderId}\'})" style="float: right;margin-right:10%;">退出</b></tpl>'+
		                        '<b class="myloanguanl"  onclick="javascript:Paymentplanmmplan({orderId:\'{orderId}\'})" style="float: right;margin-right: 2%;">回款</b>' +
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
    	Paymentplanmmplan=function(data){
    		var orderId=data.orderId;
    		var url=__ctxPath +"/creditFlow/financingAgency/assigninterestlistPlManageMoneyPlanBuyinfo.do?orderId="+orderId;
    		mobileNavi.push(Ext.create('hrmobile.public.myhome.mmplan.Paymentplan',{url:url}));
    	}
		outmmplan=function(data){
//			Ext.Ajax.request({
//					url: __ctxPath +"/creditFlow/financingAgency/earlyOutDetailPlManageMoneyPlanBuyinfo.do",
//					 params : {
//						isMobile : "1",
//						orderId:data.orderId
//			         },
//				    success : function(response) {
//				    	var  responseText1=response.responseText.replace(/[\n]/ig,'');
//				    	var responseText = Ext.util.JSON.decode(responseText1);
//				    	var data=responseText.data;
//				    	Ext.Msg.alert("","想退出？做梦！！")
				    	
//				    }});
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


