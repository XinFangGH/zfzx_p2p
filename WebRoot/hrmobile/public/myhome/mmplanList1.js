
//creditorList.js
Ext.define('hrmobile.public.myhome.mmplanList1', {
	
    extend: 'mobile.List',
    name: 'mmplanList1',
    constructor: function (config) {
    	config = config || {};
    	recordsmmplanList=new Array();
    	Ext.apply(config,{
    		modeltype:"mmplanList",
    		width:"100%",
		    height:"100%",
		    scrollToTopOnRefresh:false,//刷新不跳到头部
    		pullRefresh:true,
		    listPaging: true,
		    isload:false,
		    loadfunction:function(this1,records){
		    	for (var i=records.length-1; i >= 0; i--) {  
				    recordsitoInvestlist.push( records[i] );  
		    	}  
				recordsitoInvestlist.forEach(function(e){
					var progress=e.data.progress;
					if(progress==0){
									progress=0.0001;
								}else if(progress==100){
									progress=99.9999
								}
					circleProgress({
					            id: "databidp"+e.data.bidId,
					            progress:progress, // default: 100
					            duration:1000, // default: 1000
					            color: '#e62129', // default: 'rgb(52, 145, 204)'
					            bgColor: '#ededed', // default: 'rgb(230, 230, 230)'
					            textColor: 'transparent', // default: 'black'
					            progressWidth: 0.05, // default: 0.25 (r)
					            fontScale: 0.0, // default: 0.4 (r)
					            toFixed: 0  // default: 0
					        });
						})
					        
		    },
    		fields:[ {
						name : 'bidId'
					},{
						name : 'proType'//类型
					},{
						name : 'bidMoney' //
					},{
						name : 'interestRate' //
					},{
						name : 'loanLife' //
					},{
						name : 'bidProName' //
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
					},{
						name : 'addRate' //
					},{
						name : 'preparesellTime' //coupon
					},{
						name : 'coupon' //coupon
					},{
						name : 'novice' //coupon
					},{
						name : 'addRate' //coupon
					},{
						name : 'proKeepType'
					}],
    	        url : __ctxPath + '/creditFlow/financingAgency/listVipPlBidPlan.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
						isMobile : "1"
			},
 	       pressedCls:'',
		   itemTpl:new Ext.XTemplate('<body>'+
					'<div class="container">'+
					    '<div class="content">'+
					        '<ul>'+
					            '<li class="box-white border-gray">'+
					                '<div class="box-content" onclick="javascript:itemsingletap({bidId:\'{bidId}\'});">'+
					                    '<div class="content_left lf">'+
					                        '<h3>'+
					                            //'<img src="hrmobile/resources/imagesP2p/s_logo.png" alt="">'+
					                        	'{proKeepType:this.proKeepType}'+
					                            '<i style="font-size:17px;">{bidProName:this.sbrname}</i>'+
					                            '{novice:this.novice}'+
					                            '{addRate:this.addRate}'+
					                            '{coupon:this.coupon}'+
					                            //'<tpl if="coupon==1"><img src="hrmobile/resources/imagesP2p/quan.png" alt=""></tpl>'+
					                            //'{proKeepType:this.proKeepType}'+
					                            /*'<tpl if="coupon==1"><img src="hrmobile/resources/imagesP2p/quan2.png" style="width:20px;height:18px;" alt=""></tpl>'+
					                            '<tpl if="novice==1"><img src="hrmobile/resources/imagesP2p/xin1.png" style="width:20px;height:18px;" alt=""></tpl>'+
					                            '<tpl if="addRate!=0&&addRate!=null"><img src="hrmobile/resources/imagesP2p/xi2.png" style="width:20px;height:18px;" alt=""></tpl>'+*/
					                        '</h3>'+
					                        '<p style="font-size:16px;">'+
					                            '<span>年化利率</span>'+
					                            '<span>金额 </span>'+
					                            '<span>期限</span>'+
					                        '</p>'+
					                        '<p style="font-size:16px;">'+
					                        	'<span><b class="text-red">{interestRate}%</b></span>'+
					                            '<span><b class="text-red">{bidMoney:this.projMoney}</b><em style="color:#EB4950;"></em></span>'+
					                            '<span><b class="text-red">{loanLife}</b><em></em></span>'+
					                        '</p>'+
					                    '</div>'+
					                     '<div class="content_right rg">'+
						                        '<div class="index-progress">'+
						                            "<center><canvas id='databidp{bidId}' width='70' height='70'></canvas></center>{state:this.gshbfb}"+
						                        '</div>'+
							             '</div>'+
					                '</div>'+
					            '</li>'+
					        '</ul>'+
					    '</div>'+
					'</div>'+
				'</body>',
				{ 	projMoney: function(bidMoney) {
						if(bidMoney>=10000){
		    				return bidMoney/10000+'万元';
						}else{
							return bidMoney+'元'
						}
					},
					/*gshbfb:function(progress){
						if(progress==100){
							return "<p style='position: absolute;top:35%;left:21%;' class='text-red'>"+progress+"%</p>";
						}else if(progress==0){
							return "<p style='position: absolute;top:35%;left:35%;' class='text-red'>"+progress+"%</p>";
						}else{
							return "<p style='position: absolute;top:35%;left:32%;' class='text-red'>"+progress+"%</p>";
						}
						if(progress==100){
							return "<p style='position: absolute;top:35%;margin-left:-22px;left:50%;' class='text-red'>"+progress+"%</p>";
						}else if(progress==0){
							return "<p style='position: absolute;top:35%;margin-left:-12px;left:50%;' class='text-red'>"+progress+"%</p>";
						}else{
							return "<p style='position: absolute;top:35%;margin-left:-17px;left:50%;' class='text-red'>"+progress+"%</p>";
						}
						
					},*/
					gshbfb:function(state){
							
						if(state==2||state==5||state==6){
							return "<p style='position: absolute;top:35%;margin-left:-21px;left:50%;font-size:15px;' class='text-red'>已满标</p>";
						}else if(state==3){
							return "<p style='position: absolute;top:35%;margin-left:-21px;left:50%;font-size:15px;' class='text-red'>已流标</p>";
						}else if(state==4){
							return "<p style='position: absolute;top:35%;margin-left:-21px;left:50%;font-size:15px;' class='text-red'>已过期</p>";
						}else if(state==10){
							return "<p style='position: absolute;top:35%;margin-left:-21px;left:50%;font-size:15px;' class='text-red'>已还清</p>";
						}else if(state==7){
							return "<p style='position: absolute;top:35%;margin-left:-21px;left:50%;font-size:15px;' class='text-red'>还款中</p>";
						}else{
							return "<p style='position: absolute;top:35%;margin-left:-29px;left:50%;font-size:15px;' class='text-red'>立即抢标</p>";
						}
					},
					proKeepType : function(proKeepType){
						if(proKeepType=="信用审核标"){
							return '<span style="background:#eeae1a;width:40px;height:22px;color:#fff;padding:0;text-align:center;line-height:22px;float:left;">信 用</span>';						
						}else if(proKeepType=="实地核查标"){
							return '<span style="background:#59b8d7;width:40px;height:22px;color:#fff;padding:0;text-align:center;line-height:22px;float:left;">实 地</span>';
						}else if(proKeepType=="机构担保标"){
							return '<span style="background:#2770b8;width:40px;height:22px;color:#fff;padding:0;text-align:center;line-height:22px;float:left;">担 保</span>';
						}else if(proKeepType=="福利标"){
							return '<span style="background:#fca150;width:40px;height:22px;color:#fff;padding:0;text-align:center;line-height:22px;float:left;">福 利</span>';
						}else{
							return '<span style="background:#eeae1a;width:40px;height:22px;color:#fff;padding:0;text-align:center;line-height:22px;float:left;">信 用</span>';
						}
					},
					novice : function(novice){
						if(novice==1){
							return '<span style="background:url(hrmobile/resources/imagesP2p/xin_bg.png);width:20px;height:22px;color:#fff;padding:0;text-align:center;line-height:22px;margin:0 6px;">新</span>';
						}else{
							return;
						}
					},
					addRate : function(addRate){
						if(addRate!=null&&addRate!=""){
							return '<span style="background:url(hrmobile/resources/imagesP2p/xi_bg.png);width:20px;height:22px;color:#fff;padding:0;text-align:center;line-height:22px;margin:0 6px;">息</span>';
						}else{
							return ;
						}
					},
					coupon : function(coupon){
						if(coupon==1){
							return '<span style="background:url(hrmobile/resources/imagesP2p/quan_bg.png);width:20px;height:22px;color:#fff;padding:0;text-align:center;line-height:22px;margin:0 6px;">券</span>';
						}else{
							return;
						}
					},
					sbrname:function(bidProName){
						if(bidProName.length>10){
							return bidProName.substr(0 ,7);
						}else{
							return	bidProName;
						}
					}
				})
    	});
    	this.callParent([config]);
    itemsingletap = function(record) {
    	var bidid=record.bidId;
             		Ext.Ajax.request({
					//url: __ctxPath +"/creditFlow/financingAgency/bidPlanDetailisMobilePlBidPlan.do",
             			url: __ctxPath +"/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do",
					 params : {
						isMobile : "1",
						bidId:bidid
			         },
				    success : function(response) {
				    	var responseText1=response.responseText.replace(/[\n]/ig,'');
				    	var responseText = Ext.util.JSON.decode(responseText1);
				    	var data=responseText.data;
				    	var listMaterials=responseText.listMaterials;
				    	var enterPrise = responseText.enterPrise;
				    	var pltype = responseText.pltype;
				    	var proDes = responseText.proDes;
				    	var formtoken = responseText.formtoken;
				    	hidebottomBarIndex();
				    	mobileNavi.push(Ext.create('hrmobile.public.myhome.bidDetail',{data:data,listMaterials:listMaterials,enterPrise:enterPrise,proDes:proDes,pltype:pltype,formtoken:formtoken}));
    	            }});
    }}
});


