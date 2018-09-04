
Ext.define('hrmobile.public.myhome.myloan.biddloan', {
	
    extend: 'mobile.List',
    name:'biddloan',
    constructor: function (config) {
    	config = config || {};
    	recordsitoInvestlist=new Array();
    	Ext.apply(config,{
    		modeltype:"biddloan",
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
						name : 'bidTime'
					},{
						name : 'interestRate'
					},{
						name : 'loanLife'
					},{
						name : 'payMoney'
					},{
						name : 'proName'
					}],
    	         url : __ctxPath + '/user/newLoanmanagementBpCustMember.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
	    	    		planstate:'1',
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
		                        '<div class="inves-nav-title" style="margin-left: 15px;"><h1>{proName}</h1><!--<b class="inves-cur">房</b><b class="inves-grades">AAA</b>--></div>'+
		             
		                        '<div class="figure-nav">'+
		                            '<ul>'+
		                                '<li style="width:20%"><span>{bidTime}</span><br>发标时间</li>'+
		                                '<li style="width:25%"><span>{payMoney}</span><br>招标金额</li>'+
		                                '<li style="width:25%"><span>{loanLife}</span><br>期限</li>'+
		                                '<li style="width:20%"><span>{interestRate}<label></label></span><br>年化利率</li>'+
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
             resize :function () {
                  recordsitoInvestlist.forEach(function(e){
                	  
                	  var canvas = document.getElementById("bgab"+e.data.bidId);  
		            if(!Ext.isEmpty(canvas)){
                        progress(e.data.progress,canvas)
		            }
				
				   
								}) 
              
            } ,       
             itemswipe :function(){
             alert(1)
             },
             painted : function (this1) {
	          		// ST刷新列表
	          		this.store.loadPage(1);
	          	}
        }
    	});
       
    	this.callParent([config]);
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


