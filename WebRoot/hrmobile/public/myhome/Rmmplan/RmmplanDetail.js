var data1,
	data,
	listMaterials,
	proDes,
	listPlBid,
	salseId,
	bondListBid, // 债权记录
	enterPrise, // 投标记录
	plBidSale,
	listResult;
//var enterPrise;//RmmplanDetail
	
Ext.define('hrmobile.public.myhome.Rmmplan.RmmplanDetail', {
	extend: 'Ext.Panel',
    name: 'RmmplanDetail',
    constructor: function (config) {
    	
    	data = config.data;
    	proDes = config.proDes;
    	listMaterials = config.listMaterials;
    	bondListBid = config.bondListBid;
    	listPlBid = config.listPlBid;
    	enterPrise = config.enterPrise1;
    	plBidSale = config.plBidSale;
    	
    	// 获取列表页数据
    	listResult = config.listResult;
    	
//    	salseId=plBidSale.id;
   		var dateStr = "";
   		function date(date){
			if(date != undefined) {
				dateStr = "";
				var date = new Date(date);
				var nowDate = date;
				var year = nowDate.getFullYear();
				var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;
				var day = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();
				return dateStr = year + "-" + month + "-" + day;
			}
   		}
		
	    var html1='<div class="container" style="margin:0px 10px 0px 10px;">'+
        '<div class="topCon">'+
            '<div class="year">'+
                '<p>年化利率</p>'+
                '<div class="text-red"><span>'+data.interestRate+'</span>%</div>'+
                '<p>转让本金:'+ plBidSale.factMoney + '元 | 还有'+plBidSale.days+'天到期 | '+data.theWayBack+'</p>'+
            '</div>'+
            '<div class="progresss">'+
                '<ul class="progresss-ul">'+
                    '<li style="padding-bottom: 5%;">债权起始日</li>'+
                    '<li style="color: #e62129;font-size: 14px;font-weight: bolder;">' + date(plBidSale.startDate) + '</li>'+
                '</ul>'+
                '<ul class="progresss-ul">'+
                    '<li style="padding-bottom: 5%;">原始债权</li>'+
                    '<li style="color: #e62129;font-size: 14px;font-weight: bolder;">'+ my.isObjEmpty(plBidSale, 'saleMoney', '')+ '</li>'+
                '</ul>'+
                '<ul class="progresss-ul">'+
                    '<li style="padding-bottom: 5%;">债权到期日</li>'+
                    '<li style="color: #e62129;font-size: 14px;font-weight: bolder;">' + date(plBidSale.endIntentDate) + '</li>'+
                '</ul>'+
            '</div>'+
        '</div>'+
        '<div class="bottCon clear">'+
            '<ul>'+
                '<li  onclick="javascript:bidplan(1);" class="hasArrow"><a>标的详情</a></li>'+
                '<li  onclick="javascript:bidplan(2);" class="hasArrow"><a>相关材料</a></li>'+
                '<li  onclick="javascript:bidplan(3);" class="hasArrow"><a>投标记录</a></li>'+
                '<li  onclick="javascript:bidplan(4);" class="hasArrow"><a>债权信息</a></li>'+
            '</ul>'+
        '</div>'+
    '</div>';
    
    
            stateflag="margin:20px 10px 40px 10px;background:#e62129;"
            textmessage="<font color=white>立即购买</font>"
		config = config || {};
	    Ext.apply(config,{
        	title:"<font color=#ffffff style='font-size:17px;'>"+plBidSale.bidProName+"</font>",
            width:"100%",
			height:"100%",
		    listPaging: true,
			scrollable:{
			direction: 'vertical'
			},
					    
		   items: [{
			   baseCls:"baseClass",
			   items:[
			   {
				   html:html1
            	},{
            		xtype: 'button',
            		style:stateflag,
            		text:textmessage,
            		handler:this.buy
            	}]
		   }]    
        });
        this.callParent([config]);
        bidplan=function(rec){
        	var salseId1 = salseId;
        	if(rec==1){
        			mobileNavi.push(Ext.create('hrmobile.public.myhome.Rmmplan.Rmmdetail',{enterPrise:enterPrise,data:data}));
        	}else if(rec==2){
        			mobileNavi.push(Ext.create('hrmobile.public.myhome.Rmmplan.Rmmrelated',{salseId:salseId1,listMaterials:listMaterials}));
        	}else if(rec==3){
        			mobileNavi.push(Ext.create('hrmobile.public.myhome.Rmmplan.Rmmlist',{data11:data,listPlBid:listPlBid}));
        	}else if(rec==4){
        			mobileNavi.push(Ext.create('hrmobile.public.myhome.Rmmplan.Rmmdebtinfolist',{data11:data,bondListBid:bondListBid}));
        	}
        }
	},
        
		buy:function(){
			mobileNavi.push(Ext.create('hrmobile.public.myhome.Rmmplan.buyRmmplan', {
					result : listResult,
					salseId : listResult.id,
					bidInfoID : listResult.bidInfoID,
					startDate : listResult.startDate,
					intentDate : listResult.intentDate,
					yearAccrualRate : listResult.yearAccrualRate,
					orderNo : listResult.orderNo,
					bidPlanID : listResult.bidPlanID,
					changeMoney: listResult.changeMoney,
					changeMoneyType: listResult.changeMoneyType,
					changeMoneyRate : listResult.changeMoneyRate,
					bidProName: listResult.bidProName,
					sumMoney: listResult.sumMoney,
					saleMoney: listResult.saleMoney,
					startDate: listResult.startDate
				}));
			

		},
		sbrname:function(){
			var data1= data.bidProName;
						if(data1.length>12){
							return data1.substr(0 ,10);
						}else{
							return	data1;
						}
					}
 
});

