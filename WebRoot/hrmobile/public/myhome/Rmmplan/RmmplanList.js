/*
//creditorList.js
Ext.define('hrmobile.public.myhome.Rmmplan.RmmplanList', {
	
    extend: 'mobile.List',
    name: 'RmmplanList',
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
*/


var limit,
    listData;

Ext.define('hrmobile.public.myhome.Rmmplan.RmmplanList', {

    extend: 'mobile.List',
    name: 'RmmplanList',
    constructor: function (config) {
        config = config || {};
        Ext.apply(config, {
            modeltype: "RmmplanList",
            width: "100%",
            height: "100%",
            scrollToTopOnRefresh: false,//刷新不跳转到顶部
            pullRefresh: true,
            listPaging: true,//这是个列表  下拉可以加载更多
            fields: [{
                name: 'bidInfoID'//2
            }, {
                name: 'bidPlanID' //
            }, {
                name: 'bidProName' //项目名称
            }, {
                name: 'saleMoney' //原始债权金额
            }, {
                name: 'saleStartTime' // 挂牌时间
            }, {
                name: 'changeMoney' //折让金
            }, {
                name: 'changeMoneyRate' //折让利率
            }, {
                name: 'saleStatus' //状态3 正在交易1 购买中
            }, {
                name: 'accrualtype' //
            }, {
                name: 'changeMoneyType' //折让类型0+1-
            }, {
                name: 'id' //==salseId
            }, {
                name: 'sumMoney' //
            }, {
                name: 'endIntentDate'//债权到期日
            }, {
                name: 'days'//剩余天数
            }, {
                name: 'factMoney'//转让本金
            }, {
                name: 'intentDate'//3
            }, {
                name: 'startDate'//4
            }, {
                name: 'yearAccrualRate' //1
            }, {
                name: 'orderNo'
            }, {
                name: 'proKeepType' //
            }],
            url: __ctxPath + '/creditFlow/financingAgency/alltransferinglistPlBidSale.do',
            root: 'result',
            totalProperty: 'totalCounts',
            params: {
                isMobile: "1"
            },
            pressedCls: '',
            itemTpl: new Ext.XTemplate(
                '<div class="conTemplate">' +
                    '<div class="contentTem">' +
                        '<ul>' +
                            '<li class="li_One">' +
                                '{proKeepType:this.proKeepType}' +
                                '<em  onclick="javascript:itemsingletapp({id:\'{id}\'},{saleMoney:\'{saleMoney}\'},{startTime:\'{startTime}\'},{endTime:\'{endTime}\'})" >{bidProName:this.sbrname}</em>' +
                              /*  '<span>' +
                                '<tpl if="saleStatus==3">' +
                                '<b style="background: #ddd;text-align: right;border-radius: 15px;padding: 2px 10px;">正在交易</b>' +
                                '</tpl>' +
                                '<tpl if="saleStatus==1">' +
                                '<b onclick="javascript:buy(' +
                                '{id:\'{id}\'},' +
                                '{bidInfoID:\'{bidInfoID}\'},' +
                                '{startDate:\'{startDate}\'},' +
                                '{intentDate:\'{intentDate}\'},' +
                                '{yearAccrualRate:\'{yearAccrualRate}\'},' +
                                '{orderNo:\'{orderNo}\'},' +
                                '{bidPlanID:\'{bidPlanID}\'},' +
                                '{changeMoneyType:\'{changeMoneyType}\'},' +
                                '{changeMoneyRate:\'{changeMoneyRate}\'' +
                                '});" style="background: #e62129;text-align: right;border-radius: 15px;color:white;padding: 2px 10px;">购买</b>' +
                                '</tpl>' +
                                '</span>'+*/
                                '<span  id="startDate">挂牌时间: {saleStartTime}</span>' +
                             /*   '<span  id="startDate">{saleStartTime}</span>' +*/

                            '</li>' +
                            '<li class="li_six"  onclick="javascript:itemsingletapp({id:\'{id}\'},{saleMoney},{startTime:\'{startTime}\'},{endTime:\'{endTime}\'})">' +
                                '<span id="forbidNum">{saleMoney:this.projMoney}<em>元</em></span>' +
                                '<span>' +
                                    '<tpl if="changeMoneyType == 0">{changeMoney}</tpl>' +
                                    '<tpl if="changeMoneyType == 1">{changeMoney:this.change1}</tpl>' +
                                    '<em>元</em>' +
                                '</span>' +
                                '<span>' +
                                    '<tpl if="changeMoneyType==0">{changeMoneyRate}%</tpl>' +
                                    '<tpl if="changeMoneyType==1">{changeMoneyRate:this.change2}</tpl>' +
                                    '<em>‰</em>'+
                                '</span>' +
                              /*  '<span  id="startDate">{saleStartTime}</span>' +*/
                            '</li>' +
                            '<li class="li_Three"  onclick="javascript:itemsingletapp({id:\'{id}\'},{saleMoney},{startTime:\'{startTime}\'},{endTime:\'{endTime}\'})">' +
                                '<span>出让本金</span>' +
                                '<span>折让金</span>' +
                                '<span>折让率</span>' +
                               /* '<span>挂牌时间</span>' +*/
                            '</li>' +
                        '</ul>' +
                    '</div>' +
                '</div>',


                {
                    projMoney: function (saleMoney) {
                        return moneyFormat(saleMoney);

                    },
                    gshbfb: function (progress) {
                        if (progress == 100) {
                            return "<p style='position: absolute;top:35%;margin-left:27%;' class='text-red'>" + progress + "%</p>";
                        } else if (progress == 0) {
                            return "<p style='position: absolute;top:35%;margin-left:35%;' class='text-red'>" + progress + "%</p>";
                        } else {
                            return "<p style='position: absolute;top:35%;margin-left:32%;' class='text-red'>" + progress + "%</p>";
                        }

                    },
                    sbrname: function (bidProName) {
                        if (bidProName.length > 10) {
                            return bidProName.substr(0, 8);
                        } else {
                            return bidProName;
                        }
                    },
                    change1: function (changeMoney) {
                        if (changeMoney == 0) {
                            return changeMoney;
                        } else {
                            return "-" + changeMoney;
                        }
                    },
                    change2: function (changeMoneyRate) {
                        if (changeMoneyRate == 0) {
                            return changeMoneyRate;
                        } else {
                            return "-" + changeMoneyRate;
                        }
                    },
                    proKeepType: function (proKeepType) {
                        if (proKeepType == "信用审核标") {
                            return '<span style="display:inline-block;background:#409AF6;width:28px;height:18px;border-radius:4px;color:#fff;text-align:center;line-height:18px;font-size:10px;margin-right: 10px;">信用</span>';
                        } else if (proKeepType == "实地核查标") {
                            return '<span style="display:inline-block;background:#409AF6;width:28px;height:18px;border-radius:4px;color:#fff;text-align:center;line-height:18px;font-size:10px;margin-right: 10px;">实地</span>';
                        } else if (proKeepType == "机构担保标") {
                            return '<span style="display:inline-block;background:#409AF6;width:28px;height:18px;border-radius:4px;color:#fff;text-align:center;line-height:18px;font-size:10px;margin-right: 10px;">担保</span>';
                        } else if (proKeepType == "福利标") {
                            return '<span style="display:inline-block;background:#409AF6;width:28px;height:18px;border-radius:4px;color:#fff;text-align:center;line-height:18px;font-size:10px;margin-right: 10px;">福利</span>';
                        } else {
                            return '<span style="display:inline-block;background:#409AF6;width:28px;height:18px;border-radius:4px;color:#fff;text-align:center;line-height:18px;font-size:10px;margin-right: 10px;">信用</span>';
                        }
                    }

                }),
            listeners: {
                painted: function () {
                    // Aajx Get读取数据
                    Ext.Ajax.request({
                        url: __ctxPath + '/creditFlow/financingAgency/alltransferinglistPlBidSale.do',
                        params: {
                            isMobile: "1",
                            page: '1',
                            limit: '200'
                        },
                        method: 'GET',
                        async: false,
                        success: function (response) {
                            // 解析json串为对象
                            var responseText = Ext.util.JSON.decode(response.responseText);

                            // 获取长度
                            limit = responseText.totalCounts;

                            // 获取列表页数据
                            listData = responseText;
                        }
                    });

                    // 刷新页面
                    this.store.loadPage(1);

                    $("#ext-element-85").css("background-color", "#EAEDFB");
                    $("#ext-element-68").css("background-color", "#EAEDFB");

                }
//				itemsingletap:this.itemsingletap
            }
        });
        this.callParent([config]);


        // 购买
        buy = function (id, data2, data3, data4, data5, data6, data7, changeMoneyType, changeMoneyRate) {
            // 判断是否登录
            if (Ext.isEmpty(curUserInfo)) {
                mobileNavi.push(Ext.create('hrmobile.public.myhome.login', {}));
            } else {
                // 获取名称
                /*var bidProName = bidProName.bidProName;

                // 获取总结算金额
                var sumMoney = sumMoney.sumMoney;

                // 获取折让金类型
                var changeMoneyType = changeMoneyType.changeMoneyType;*/

                // 获取点击列表id
                var salseId = id.id;

                // 获取接口参数
                window._curData = listData.result;

                // 获取当前id索引
                var indexId = $getIndexId(window._curData, 'id', salseId);

                mobileNavi.push(Ext.create('hrmobile.public.myhome.Rmmplan.buyRmmplan', {
                    result: window._curData[indexId],
                    salseId: window._curData[indexId].id,
                    bidInfoID: window._curData[indexId].bidInfoID,
                    startDate: window._curData[indexId].startDate,
                    intentDate: window._curData[indexId].intentDate,
                    yearAccrualRate: window._curData[indexId].yearAccrualRate,
                    orderNo: window._curData[indexId].orderNo,
                    bidPlanID: window._curData[indexId].bidPlanID,
                    changeMoney: window._curData[indexId].changeMoney,
                    changeMoneyType: window._curData[indexId].changeMoneyType,
                    changeMoneyRate: window._curData[indexId].changeMoneyRate,
                    bidProName: window._curData[indexId].bidProName,
                    sumMoney: window._curData[indexId].sumMoney,
                    saleMoney: window._curData[indexId].saleMoney,
                    startDate: window._curData[indexId].startDate

                }));

            }
            /*mobileNavi.push(Ext.create('hrmobile.public.myhome.Rmmplan.buyRmmplan',{
                salseId : id.id,
                bidInfoID : data2.bidInfoID,
                startDate : data3.startDate,
                intentDate : data4.intentDate,
                yearAccrualRate : data5.yearAccrualRate,
                orderNo : data6.orderNo,
                bidPlanID : data7.bidPlanID,
                changeMoneyType : changeMoneyType.changeMoneyType,
                changeMoneyRate : changeMoneyRate.changeMoneyRate
                }));*/
        }

        //
        itemsingletapp = function (id, saleMoney, startTime, endTime) {
            // 获取点击列表id
            var salseId = id.id;

            // 获取接口参数
            window._curData = listData.result;

            // 获取当前id索引
            var indexId = $getIndexId(window._curData, 'id', salseId);

            Ext.Ajax.request({
                url: __ctxPath + "/creditFlow/financingAgency/saleInfo1PlBidPlan.do",
                params: {
                    isMobile: "1",
                    salseId: id.id
                },
                success: function (response) {
                    var responseText1 = response.responseText.replace(/[\n]/ig, '');
                    var responseText = Ext.util.JSON.decode(responseText1);
                    var data = responseText.data;
                    var listMaterials = responseText.listMaterials;//相关材料
                    var proDes = responseText.proDes;
                    var listPlBid = responseText.listPlBid;//投标记录
                    var bondListBid = responseText.bondListBid;//债权记录
                    var enterPrise = responseText.enterPrise;//
                    var plBidSale = responseText.plBidSale;
//				    	//出让本金
//				    	var forbid = forbidNum[0].innerText.substring(0,forbidNum[0].innerText.length-1) * 10000;
//				    	//起始日
//				    	var startTime = startDate[0].innerText.substring(0,startDate[0].innerText.length-1);
//				    	//到期日
//				    	var endTime = intentDate[0].innerText.substring(0,intentDate[0].innerText.length-2);

                    hidebottomBarIndex();
                    mobileNavi.push(Ext.create('hrmobile.public.myhome.Rmmplan.RmmplanDetail', {
                        listResult: window._curData[indexId],
                        enterPrise1: enterPrise,
                        listPlBid: listPlBid,
                        bondListBid: bondListBid,
                        data: data,
                        listMaterials: listMaterials,
                        proDes: proDes,
                        plBidSale: plBidSale

                    }));
                }
            });
        }
    }
});

