
Ext.define('hrmobile.public.myhome.planbid.backin', {

    extend: 'mobile.List',
    name:'backin',
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
						name : 'planId'
					},{
						name : 'projectName'
					},{
						name : 'userMoney'
					},{
						name : 'payintentPeriod'
					},{
						name : 'payIntestPrinMoney'
					},{
						name : 'loanLife'
					},{
						name : 'interestRate'
					},{
						name : 'intentDate'
					},{
						name : 'factPaymentsMoney'
					},{
						name : 'couponMoney'
					},{
						name : 'compensationMoney'
					},{
						name : 'bidtime'
					},{
						name : 'orderNo'
					},{
           			     name : 'url'
           			 }],
    	        url : __ctxPath + '/financePurchase/myManageMoneyFinancePurchase.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
	    	    		planstate:"7",
						isMobile : "1"
			},
 	       pressedCls:'',
		   itemTpl: new Ext.XTemplate( '<div class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">'+
		        '<nav class="inves-nav swiper-slide swiper-slide-next">'+
		           ' <ul>'+
		                '<li>'+
		                    '<div class="box">'+
		                        '<div class="inves-nav-title" style="margin-left: 15px;"><h1>{projectName}</h1>'+
               '<div style="float: right;margin: 2% 10% 0% 0%;">'+
               '<a class="myloanhetong"  href="{url}" target="_blank">查看合同</a></div>'+
		             			'<div style="float: right;margin: 2% 10% 0% 0%;">'+
		                        	'<b class="myloanhetong" onclick="javascript:Paymentplanbackin({planId:\'{planId}\',orderNo:\'{orderNo}\'})">回款计划</b></div>'+
		             			'</div>'+
		                        '<div class="figure-nav">'+
		                            '<ul>'+
		                                '<li><span>{userMoney}<label>元</label></span><br>投资金额</li>'+
		                                '<li><span class="buy-red">'+
		                                	'{interestRate}'+
		                                '<label>%</label></span><br>年化利率</li>'+
		                                '<li><span>{bidtime}<label></label></span><br>投标时间</li>'+
		                                '<li><span>{loanLife}<label></label></span><br>投资期限</li>'+
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
						}),
        listeners:{  
             resize :function(){

                  recordsitoInvestlist.forEach(function(e){
                	  var canvas = document.getElementById("bgab"+e.data.bidId);  
		            if(!Ext.isEmpty(canvas)){
                        progress(e.data.progress,canvas)
		            }
				}) 
            },       
             itemswipe :function(){}       
        }
    	});
       
    	this.callParent([config]);
    	
    	this.getScrollable().getScroller().on('scroll',function(scroller,x,y){  
                  recordsitoInvestlist.forEach(function(e){  
		     	  	
		            //  var progress=  parseFloat(e.data.progress)/100;
		            var canvas = document.getElementById("bgab"+e.data.bidId);   
		            if(!Ext.isEmpty(canvas)){
                        progress(e.data.progress,canvas)
		            }
				   
				     
								}) 
              
            });
        
            
        // 回款计划
    	Paymentplanbackin = function(data) {

         	var planId = data.planId;
         	var orderNo = data.orderNo;
         	var url =__ctxPath +"/user/paymentplanBpCustMember.do?str=" + planId + ",Financing," + orderNo;
         	
         	mobileNavi.push(Ext.create('hrmobile.public.myhome.planbid.Paymentplan',{url:url}));
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


