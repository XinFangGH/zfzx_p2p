
//creditorList.js
Ext.define('hrmobile.public.myhome.plandebts.closeedList', {
	
    extend: 'mobile.List',
    name: 'closeedList',
    constructor: function (config) {
    	config = config || {};
    	recordsitoInvestlist=new Array();
    	Ext.apply(config,{
    		modeltype:"closeedList",
    		flex:1,
    		width:"100%",
		    height:"100%",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>我要投资</font>",
    		items:[/*bottomBar*/],
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
						name : 'bidProName' //
					},{
						name : 'saleMoney' //
					},{
						name : 'yearAccrualRate' //
					},{
						name : 'intentDate' //
					},{
						name : 'startMoney' //
					},{
						name : 'loanLife' //
					},{
						name : 'bidProName' //
					},{
						name : 'progress' //
					},{
						name : 'state' //
					},{
						name : 'publishSingeTime' //
					}
					,{
						name : 'addRate' //
					},{
						name : 'preparesellTime' //
					}],
    	        url : __ctxPath + '/creditFlow/financingAgency/closeedListPlBidSale.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
						isMobile : "1"
			},
 	       pressedCls:'',
		   itemTpl: new Ext.XTemplate(
			   '<body>'+
					'<div class="container">'+
					    '<div class="content">'+
					        '<ul>'+
					            '<li class="box-white border-gray">'+
					                '<div class="box-content">'+
					                    '<div class="content_left lf">'+
					                        '<h3>'+
					                            '<img src="hrmobile/resources/imagesP2p/s_logo.png" alt="">' +
					                            '<i>{bidProName}</i>'+
					                            '<img src="hrmobile/resources/imagesP2p/quan.png" alt="">'+
					                            '<img src="hrmobile/resources/imagesP2p/new.png" alt="">'+
					                        '</h3>'+
					                        '<p style="font-size:16px;">'+
					                            '<span>项目年化</span>'+
					                            '<span>金额</span>'+
					                            '<span>期限</span>'+
					                        '</p>'+
					                        '<p style="font-size:16px;">'+
					                        	'<span><b class="text-red">{yearAccrualRate}%</b></span>'+
					                            '<span><b class="text-red">{saleMoney:this.saleMoney}</b><em>万</em></span>'+
					                            '<span><b class="text-red">{intentDate}</span>'+
					                        '</p>'+
					                    '</div>'+
					                    '<div class="content_right rg" style="margin-top:5%;">'+
					                        '<button>回款计划</button>'+
					                        '<button>挂牌交易</button>'+
					                    '</div>'+
					                '</div>'+
					            '</li>'+
					        '</ul>'+
					    '</div>'+
					'</div>'+
				'</body>'
                       ,{
		    			saleMoney: function(saleMoney) {
		    				return saleMoney/10000;
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

//Ext.define('hrmobile.public.myhome.itoInvestList', {
//			extend : 'Ext.NavigationView',
//			requires : ['hrmobile.public.myhome.index'],
//			alias : 'widget.itoInvestList',
//			config : {
//				id : 'weinaiView',
//				title : '首页',
//				iconCls : 'home',
//				icon: 'img/bottle.png',
//				autoDestroy : false,
//				defaultBackButtonText : '返回',
//				navigationBar: false,
//			    items : [{
//							xtype : 'index'
//						}]
//			}
//		});

