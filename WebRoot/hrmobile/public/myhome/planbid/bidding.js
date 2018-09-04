
//creditorList.js
Ext.define('hrmobile.public.myhome.planbid.bidding', {
	
    extend: 'mobile.List',
    name:'bidding',
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
					}],
    	        url : __ctxPath + '/financePurchase/myManageMoneyFinancePurchase.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
	    	    		planstate:"1",
						isMobile : "1"
			},
 	       pressedCls:'',
		   itemTpl: new Ext.XTemplate( '<div class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">'+
		        '<nav class="inves-nav swiper-slide swiper-slide-next">'+
		           ' <ul>'+
		                '<li>'+
		                    '<div class="box">'+
		                        '<div class="inves-nav-title" style="margin-left: 15px;"><h1>{projectName}</h1><!--<b class="inves-cur">房</b><b class="inves-grades">AAA</b>--></div>'+
		             
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
						}),
        listeners:{  
            
             resize :function(){  
                  recordsitoInvestlist.forEach(function(e){
                	  
                	  var canvas = document.getElementById("bgab"+e.data.bidId);  
		            if(!Ext.isEmpty(canvas)){
                        progress(e.data.progress,canvas)
		            }
					    
				
				   
				     
								}) 
              
            } ,       
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


