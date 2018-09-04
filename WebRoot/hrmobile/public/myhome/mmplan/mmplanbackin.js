
Ext.define('hrmobile.public.myhome.mmplan.mmplanbackin', {
	
    extend: 'mobile.List',
    name:'mmplanbackin',
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
						isMobile : "1",
						state1:"1",
						show:"buyingList"
						
			},
 	       pressedCls:'',
		   itemTpl: new Ext.XTemplate( '<div class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">'+
		        '<nav class="inves-nav swiper-slide swiper-slide-next">'+
		           ' <ul>'+
		                '<li>'+
		                    '<div class="box" onclick="javascript:mmplanbidDetail({plManageMoneyPlan.mmplanId});">'+
		                        '<div class="inves-nav-title" style="margin-left:15px;"><h1>{mmName}</h1></div>'+
		                        '<div class="figure-nav">'+
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
    		mmplanbidDetail =function(mmplanId) {
    		   Ext.Ajax.request({
					url: __ctxPath +"/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do",
					 params : {
						isMobile : "1",
						mmplanId:mmplanId
			         },
				    success : function(response) {
				    	var  responseText1=response.responseText.replace(/[\n]/ig,'');
				    	var responseText = Ext.util.JSON.decode(responseText1);
				    	var data=responseText.data;
				    	hidebottomBarIndex();
				    	mobileNavi.push(Ext.create('hrmobile.public.myhome.mmplanDetail',{data:data}));
		            }
		    });
        	}
    },
    a:function(e){}
});


