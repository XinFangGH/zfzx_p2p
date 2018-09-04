
//creditorList.js
Ext.define('hrmobile.public.myhome.Rmmplan.RmmplanList1', {
	
    extend: 'mobile.List',
    name: 'RmmplanList1',
    constructor: function (config) {
    	config = config || {};
    	Ext.apply(config,{
    		modeltype:"RmmplanList",
    		width:"100%",
		    height:"100%",
		    scrollToTopOnRefresh:false,//刷新不跳转到顶部
    		pullRefresh: true,
		    listPaging: true,//这是个列表  下拉可以加载更多
    		fields:[ {
						name : 'bidInfoID'
					},{
						name : 'bidPlanID' //
					},{
						name : 'bidProName' //
					},{
						name : 'changeMoney' //
					},{
						name : 'changeMoneyRate' //
					},{
						name : 'changeMoneyType' //
					},{
						name : 'id' //
					},{
						name : 'orderNo' //
					},{
						name : 'proKeepType' //
					},{
						name : 'saleMoney' //
					},{
						name : 'saleStartTime' //
					},{
						name : 'saleStatus' //
					},{
						name : 'startDate' //
					},{
						name : 'sumMoney' //saleStatus
					},{
						name : 'saleStatus' //saleStatus
					},{
						name : 'yearAccrualRate' //intentDate
					},{
						name : 'intentDate' //intentDate
					}],
    	        url : __ctxPath + '/creditFlow/financingAgency/alltransferinglistPlBidSale.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
						isMobile : "1"
			},
 	       pressedCls:'',
		   itemTpl: new Ext.XTemplate(
					'<div class="container">'+
					    '<div class="content">'+
					        '<ul>'+
					            '<li class="box-white border-gray">'+
					                '<div class="box-content">'+
					                    '<div class="content_left" style="width: 100%;">'+
					                    	'<h3>'+
					                            '<i style="font-size:17px;">{bidProName:this.sbrname}</i>'+
					                            '<tpl if="saleStatus==1">'+
					                            	'<i class="text-red" style="float: right;margin-right: 20px;border: 2px solid;border-radius: 8px;padding: 3px 10px;font-size: 13px;">购买</i>'+
					                            '</tpl>'+
					                            '<tpl if="saleStatus==3">'+
					                            	'<i class="text-red" style="color: #949494;float: right;margin-right: 20px;border: 2px solid;border-radius: 8px;padding: 3px 10px;font-size: 13px;">购买中</i>'+
					                            '</tpl>'+
					                        '</h3>'+
					                        '<p style="font-size:14px;">'+
					                       		'<span style="width:25%;">出让本金</span>'+
					                            '<span style="width: 25%;">折让金</span>'+
					                            '<span style="width: 25%;">折让率</span>'+
					                            '<span style="width: 25%;">挂牌时间</span>'+
					                        '</p>'+
					                        '<p style="font-size:14px;">'+
												'<span style="width:25%;><b class="text-red">{saleMoney:this.projMoney}</b><em>万</em></span>'+
					                        	'<span style="width:25%;><b class="text-red"><tpl if="changeMoneyType==0">+{changeMoney}</tpl><tpl if="changeMoneyType==1">-{changeMoney}</tpl></b>元</span>'+
					                            '<span style="width:25%;><b class="text-red"><tpl if="changeMoneyType==0">+{changeMoneyRate}</tpl><tpl if="changeMoneyType==1">-{changeMoneyRate}</tpl></b>%</span>'+
					                            '<span style="width:25%;><b class="text-red">{saleStartTime}</b><em></em></span>'+
					                        '</p>'+
					                    '</div>'+
					                '</div>'+
					            '</li>'+
					        '</ul>'+
					    '</div>'+
					'</div>',
				{ 	projMoney: function(bidMoney) {
		    				return bidMoney/10000;
						},
					gshbfb:function(progress){
						if(progress==100){
							return "<p style='position: absolute;top:35%;margin-left:27%;' class='text-red'>"+progress+"%</p>";
						}else if(progress==0){
							return "<p style='position: absolute;top:35%;margin-left:35%;' class='text-red'>"+progress+"%</p>";
						}else{
							return "<p style='position: absolute;top:35%;margin-left:32%;' class='text-red'>"+progress+"%</p>";
						}
						
					},
					sbrname:function(bidProName){
						if(bidProName.length>10){
							return bidProName.substr(0 ,7);
						}else{
							return	bidProName;
						}
					}
				}),
				listeners : {
				itemsingletap:this.itemsingletap
			}
    	});
    	this.callParent([config]);
    },
    itemsingletap : function(obj, index, target, record) {
    		var Rmmdata=record.data;
             		Ext.Ajax.request({
					url: __ctxPath +"/creditFlow/financingAgency/saleInfoPlBidPlan.do",
					 params : {
						isMobile : "1",
						salseId:Rmmdata.id
			         },
				    success : function(response) {
				    	var responseText1=response.responseText.replace(/[\n]/ig,'');
				    	var responseText = Ext.util.JSON.decode(responseText1);
				    	var data=responseText.data;
				    	var listMaterials=responseText.listMaterials;
				    	var enterPrise = responseText.enterPrise;
				    	var pltype = responseText.pltype;
				    	var proDes = responseText.proDes;
				    	hidebottomBarIndex();
				    	mobileNavi.push(Ext.create('hrmobile.public.myhome.Rmmplan.bidDetail',{data:data,listMaterials:listMaterials,enterPrise:enterPrise,proDes:proDes,pltype:pltype,Rmmdata:Rmmdata}));
    	            }});
    }
});

