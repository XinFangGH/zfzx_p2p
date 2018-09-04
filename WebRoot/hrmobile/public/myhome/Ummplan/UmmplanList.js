
//creditorList.js
Ext.define('hrmobile.public.myhome.Ummplan.UmmplanList', {
	
    extend: 'mobile.List',
    name: 'UmmplanList',
    constructor: function (config) {
    	config = config || {};
    	recordsummplanList=new Array();
    	Ext.apply(config,{
    		modeltype:"UmmplanList",
    		width:"100%",
		    height:"100%",
		    scrollToTopOnRefresh:false,//刷新不跳转到顶部
    		pullRefresh: true,
		    listPaging: true,//这是个列表  下拉可以加载更多
		    isload:true,
		    loadfunction:function(this1,records){
		    	for (var i=records.length-1; i >= 0; i--) {  
				    recordsummplanList.push( records[i] );  
		    	}  
				recordsummplanList.forEach(function(e){
					var progress=e.data.progress;
					if(progress==0){
									progress=0.0001;
								}else if(progress==100){
									progress=99.9999
								}
					circleProgress({
					            id: "ummplan"+e.data.mmplanId,
					            progress:progress, // default: 100
					            duration: 1000, // default: 1000
					            color: themeColor, // default: 'rgb(52, 145, 204)'
					            bgColor: '#ededed', // default: 'rgb(230, 230, 230)'
					            textColor: 'transparent', // default: 'black'
					            progressWidth: 0.05, // default: 0.25 (r)
					            fontScale: 0.0, // default: 0.4 (r)
					            toFixed: 0  // default: 0
					        });
						})
					        
		    },
    		fields:[ {
						name : 'mmplanId'
					},{
						name : 'mmName'
					},{
						name : 'sumMoney' //
					},{
						name : 'lockingLimit' //
					},{
						name : 'investlimit' //
					},{
						name : 'investedMoney' //
					},{
						name : 'keystr' //
					},{
						name : 'manageMoneyTypeName' //
					},{
						name : 'mmNumber' //
					},{
						name : 'progress' //
					},{
						name : 'riseMoney' //
					},{
						name : 'startMoney' //
					}
					,{
						name : 'startinInterestCondition' //
					},{
						name : 'state' //
					},{
						name : 'yeaRate' //
					},{
						name : 'updatetime' //coupon
					},{
						name : 'coupon' //coupon
					}],
    	        url : __ctxPath + '/creditFlow/financingAgency/getUPlanPlManageMoneyPlan.do',
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
					                            '<img src="hrmobile/resources/imagesP2p/s_logo.png" alt="">'+
					                            '<i style="font-size:17px;">{mmName:this.sbrname}</i>'+
					                            '<tpl if="coupon==1"><img src="hrmobile/resources/imagesP2p/quan.png" alt=""></tpl>'+
					                        '</h3>'+
					                        '<p style="font-size:16px;">'+
					                            '<span>项目年化</span>'+
					                            '<span>金额</span>'+
					                            '<span>期限</span>'+
					                        '</p>'+
					                        '<p style="font-size:16px;">'+
					                        	'<span><b class="text-red">{yeaRate}%</b></span>'+
					                            '<span><b class="text-red">{sumMoney:this.projMoney}</b><em>万</em></span>'+
					                            '<span><b class="text-red">{investlimit}</b><em>个月</em></span>'+
					                        '</p>'+
					                    '</div>'+
					                     '<div class="content_right rg">'+
							                        '<div class="index-progress">'+
							                            '<center><canvas id="ummplan{mmplanId}" width="70" height="70"></canvas></center>'+
							                        	'<p style="position: absolute;top:35%;margin-left:35%;" class="text-red">{progress:this.gshbfb}</p>'+
							                        '</div>'+
							             '</div>'+
					                '</div>'+
					            '</li>'+
					        '</ul>'+
					    '</div>'+
					'</div>'+
				'</body>',
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
					sbrname:function(mmName){
						if(mmName.length>10){
							return mmName.substr(0 ,7);
						}else{
							return	mmName;
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
    			var data=record.data;
         		Ext.Ajax.request({
						url: __ctxPath +"/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do",
						 params : {
							isMobile : "1",
							mmplanId:data.mmplanId
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
});

