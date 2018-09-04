
var val;
Ext.define('hrmobile.public.myhome.itoInvestList', {
    extend: 'mobile.List',
    name: 'itoInvestList',
    constructor: function (config) {
    	config = config || {};
    	recordsitoInvestlist=new Array();
    	Ext.apply(config,{
    		modeltype:"itoInvestList",
    		width:"100%",
		    height:"100%",
		    scrollToTopOnRefresh:false,//刷新不跳到头部
    		pullRefresh:true,
		    listPaging: true,
		    isload:false,
			style:'margin:42px 0 55px;padding-bottom:42px;',
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
						name : 'showRate' //
					},{
						name : 'preparesellTime' //coupon
					},{
						name : 'coupon' //coupon
					},{
						name : 'novice' //coupon
					},{
						name : 'showRate' //coupon
					},{
						name : 'proKeepType'
					},{
                    name : 'afterMoney'
			      },{
    			name:'raiseRate'
			},{
                name:'theWayBack'
            }],
    	        url : __ctxPath + '/creditFlow/financingAgency/listPlBidPlan.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
						isMobile : "1"
			},
 	       pressedCls:'',
		   itemTpl:new Ext.XTemplate('<body">'+
					'<div class="conTemplate">'+
					    '<div class="contentTem"  onclick="javascript:itemsingletap({bidId:\'{bidId}\'});">'+
					        '<ul>'+
			                   '<li class="li_One">'+
								   '{novice:this.novice}'+
								  /* '{showRate:this.showRate}'+*/
								  /* '{coupon:this.coupon}'+*/
								   '{proKeepType:this.proKeepType}'+
			                       '<em>{bidProName}</em>'+
                                    '<input type="hidden" value="{showRate:this.showRate}">'+
                                   '{coupon:this.coupon}'+
			                      /* '<p>总额:{bidMoney:this.projMoney}</p>'+*/
					            '</li>'+
			                   '<li class="li_Two">'+
			                       '<span style="font-size:0.875rem"><em id="interestRate">{interestRate:this.interestRate}</em>%{showRate:this.showRate}</span>' +
                                   '<span style="font-size:18px">{raiseRate}<em  style="font-size:14px">%</em></span>' +
								   '<span style="font-size: 16px;">{loanLife}<b></b></span>' +
								/*   '<span>{bidMoney:this.projMoney}</span>'+*/
			                   '</li>'+
			                   '<li class="li_Three">'+
			                       '<span>预期年化收益率</span>' +
			                       '<span>募集期利率</span>'+
								   '<span>期限</span>' +
								  /* '<span>金额</span>'+*/
			                   '</li>'+
			                   '<li class="li_Four">'+
                                   '<span class="longPro"><b class="longNei" style="width:{progress}%"></b></span>'+
                                   '<span style="margin-left:0.625rem;">{progress}%</span>'+
                                   "{state:this.gshbfb}" +
					            '</li>'+
			                  '<li class="li_Five">'+
			                       '<span>{theWayBack}</span>' +
                                  '<span style="width:54%;text-align:right;">剩余<em>{afterMoney}</em>元</span>' +
					            '</li>'+
					        '</ul>'+
					    '</div>'+
					'</div>'+
				'</body>',


				{ 	projMoney: function(bidMoney) {
						if(bidMoney >= 10000){
		    				return bidMoney /10000 + '万元';
						} else {
							return bidMoney + '元'
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
							/*return "<p style='position: absolute;top:35%;' class='text-red'>已满标</p>";*/
                            return '<span style="background:url(hrmobile/resources/imagesP2p/fullStandard.png);position:absolute;top:0;right:6%;display:inline-block;width:4rem;height:4rem;"></span>';

						}else if(state==3){
							/*return "<p style='position: absolute;top:35%;' class='text-red'>已流标</p>";*/
                            return '<span style="background:url(hrmobile/resources/imagesP2p/current Standard.png);position:absolute;top:0;right:6%;display:inline-block;width:4rem;height:4rem;"></span>';
						}else if(state==4){
							/*return "<p style='position: absolute;top:35%;' class='text-red'>已过期</p>";*/
                            return '<span style="background:url(hrmobile/resources/imagesP2p/Expired.png);position:absolute;top:0;right:6%;display:inline-block;width:4rem;height:4rem;"></span>';
						}else if(state==10){
							/*return "<p style='position: absolute;top:35%;' class='text-red'>已还清</p>";*/
                            return '<span style="background:url(hrmobile/resources/imagesP2p/Reimbursement.png);position:absolute;top:0;right:6%;display:inline-block;width:4rem;height:4rem;"></span>';
						}else if(state==7){
							/*return "<p style='position: absolute;top:35%;' class='text-red'>还款中</p>";*/
                            return '<span style="background:url(hrmobile/resources/imagesP2p/Repayments.png);position:absolute;top:0;right:6%;display:inline-block;width:4rem;height:4rem;"></span>';
						}else{
							/*return "<p style='position: absolute;top:35%;' class='text-red'>立即投标</p>";*/
						}
					},
					proKeepType : function(proKeepType){
						if(proKeepType=="信用审核标"){
							return '<span style="display:inline-block;background:#409AF6;width:28px;height:18px;border-radius:4px;color:#fff;text-align:center;line-height:18px;font-size:10px;margin-right: 10px;">信用</span>';
						}else if(proKeepType=="实地核查标"){
							return '<span style="display:inline-block;background:#409AF6;width:28px;height:18px;border-radius:4px;color:#fff;text-align:center;line-height:18px;font-size:10px;margin-right: 10px;">实地</span>';
						}else if(proKeepType=="机构担保标"){
							return '<span style="display:inline-block;background:#409AF6;width:28px;height:18px;border-radius:4px;color:#fff;text-align:center;line-height:18px;font-size:10px;margin-right: 10px;">担保</span>';
						}else if(proKeepType=="福利标"){
							return '<span style="display:inline-block;background:#409AF6;width:28px;height:18px;border-radius:4px;color:#fff;text-align:center;line-height:18px;font-size:10px;margin-right: 10px;">福利</span>';
						}else{
							return '<span style="display:inline-block;background:#409AF6;width:28px;height:18px;border-radius:4px;color:#fff;text-align:center;line-height:18px;font-size:10px;margin-right: 10px;">信用</span>';
						}
					},
					novice : function(novice){
						if(novice==1){
							return '<span style="background:url(hrmobile/resources/imagesP2p/Novice.png);display: inline-block;float:left;width:14px;height:17px;margin-right: 10px;"></span>';
						}else{
							return;
						}
					},
                    showRate : function(showRate){
                        val=showRate;
						if(showRate!=null&&showRate!=""){
							/*return '<span style="background:url(hrmobile/resources/imagesP2p/Novice_xi.png);display: inline-block;float:left;width:14px;height:17px;margin-right: 10px;"></span>';*/
							/*return showRate;*/
                            return '+<em>'+showRate+"</em>%";
						}else{
							return ;
						}
					},
                    interestRate:function(interestRate){
						/*console.log(interestRate);
						console.log(val);*/
						if(val){
                          return (parseFloat(interestRate)-parseFloat(val)).toFixed(1);
						}else{
                            return interestRate;
						}
					},
					/*coupon : function(coupon){
						if(coupon==1){
							/!*return '<span style="background:url(hrmobile/resources/imagesP2p/Novice_quan.png);display: inline-block;float:left;width:14px;height:17px;margin-right: 10px;"></span>';*!/
						}else{
							return;
						}
					},*/
                    coupon : function(coupon){
                        if(val){
                            /*return '<span class="jx">限时加息</span>';*/
                            return '<img src="'+__ctxPath+'/hrmobile/resources/mypic/h5_jx.png" class="jx">';
                        }else{
                            return ;
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
    					/*show:function(){
							var interestR=this.interestRate;
							console.log(interestR);
							var addR=parseFloat($('#addRate').html());
                            $('#interestRate').html(interestR-addR);
						},*/
						painted : function () {
							// 返回刷新页面
							this.store.loadPage(1);
						}
				}
    	});
    	this.callParent([config]);

    var flag=true;
    itemsingletap = function(record) {
    	if(flag){
    		flag=false;
    		setTimeout(function(){
				flag=true;
			},2000);
			var bidId = record.bidId;
			Ext.Ajax.request({
				//url: __ctxPath +"/creditFlow/financingAgency/bidPlanDetailisMobilePlBidPlan.do",
					url: __ctxPath +"/creditFlow/financingAgency/bidPlanDetailisMobilePlBidPlan.do",
				 params : {
					isMobile : "1",
					bidId:bidId
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
                    mobileNavi.getNavigationBar().getBackButton().show();
					mobileNavi.push(Ext.create('hrmobile.public.myhome.bidDetail',{
								data:data,
								listMaterials:
								listMaterials,
								enterPrise:enterPrise,
								proDes:proDes,
								pltype:pltype,
								formtoken:formtoken,
								bidId: bidId
							}
						)
					);
				}
			});
        }
    }}
});


