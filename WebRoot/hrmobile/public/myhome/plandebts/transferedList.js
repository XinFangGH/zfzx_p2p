
//creditorList.js
Ext.define('hrmobile.public.myhome.plandebts.transferedList', {
	
    extend: 'mobile.List',
    name: 'transferedList',
    constructor: function (config) {
    	config = config || {};
    	recordsitoInvestlist=new Array();
    	Ext.apply(config,{
    		modeltype:"transferedList",
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
						name : 'bidPlanID' //
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
						name : 'preparesellTime' //saleId
					},{
						name : 'saleId' //saleId
					}],
    	        url : __ctxPath + '/creditFlow/financingAgency/transferedListPlBidSale.do',
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
					                    '<div class="content_left lf"  onclick="javascript:Plbidplan({bidPlanID});">'+
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
					                        '<button	onclick="javascript:transset({saleId:\'{saleId}\'})">结算清单</button>'+
					                        '<button	onclick="javascript:transht({saleId:\'{saleId}\'})">合　　同</button>'+
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
						})
    	});
       
    	this.callParent([config]);
    	transset=function(data){
    		var saleId =data.saleId;
         	var url=__ctxPath +"/creditFlow/financingAgency/contractPlBidSale.do?saleId="+saleId;
         	window.open(url,"_bank")
    	}
    	
    	transht=function(data){
    		var saleId =data.saleId;
         	var url=__ctxPath +"/creditFlow/financingAgency/contractPlBidSale.do?saleId="+saleId;
         	window.open(url,"_bank")
    	}
    },
    a:function(e){}
});
