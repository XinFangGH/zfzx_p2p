var biddata;
var listMaterials
var enterPrise
var proDes
var pltype
var Rmmdata
Ext.define('hrmobile.public.myhome.myloan.bidDetail', {
	extend: 'Ext.Panel',
    
    name: 'bidDetail',

    constructor: function (config) {
    	biddata=config.data;
    	enterPrise=config.enterPrise;
    	listMaterials=config.listMaterials;
    	proDes=config.proDes;
    	pltype=config.pltype;
    	Rmmdata=config.Rmmdata;
	    var html1='<div class="container" style="margin:0px 10px 0px 10px;">'+
        '<div class="topCon">'+
            '<div class="year">'+
                '<p>年化利率</p>'+
                '<div class="text-red"><span>'+biddata.interestRate+'</span>%</div>'+
                '<p>'+biddata.startMoney+'元起投 | '+biddata.payMoneyTime+'个月  |  '+biddata.theWayBack+'</p>'+
            '</div>'+
            '<div class="progress">'+
                '<div>'+
//                    '<b><img src="images/tip.png" alt=""></b>'+
                '</div>'+
                '<p><span style="width:'+biddata.progress+'%"></span></p>'+
                '<ul class="lf">'+
                    '<li>融资金额</li>'+
                    '<li class="text-red">'+biddata.bidMoney/10000+'万元</li>'+
                '</ul>'+
                '<ul class="rg">'+
                    '<li>剩余金额</li>'+
                    '<li class="text-red">'+biddata.afterMoney/10000+'万元</li>'+
                '</ul>'+
            '</div>'+
        '</div>'+
        '<div class="bottCon clear">'+
            '<ul>'+
                '<li><a >项目编号</a><span>'+biddata.bidProNumber+'</span></li>'+
                '<li><a >截止日期</a><span>'+biddata.bidEndTime+'</span></li>'+
                '<li  onclick="javascript:bidplan(1);" class="hasArrow"><a>标的详情</a></li>'+
                '<li  onclick="javascript:bidplan(2);" class="hasArrow"><a>相关文件</a></li>'+
                '<li  onclick="javascript:bidplan(3);" class="hasArrow"><a>投标记录</a></li>'+
            '</ul>'+
        '</div>'+
    '</div>'
		config = config || {};
	    Ext.apply(config,{
        	title:"<font color=#ffffff style='font-size:17px;'>"+biddata.bidProName+"</font>",
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
            		style:"margin:20px 10px 40px 10px;height:44px;background:"+themeColor+";font-color:white",
            		xtype: 'button',
            		text:"<font color=white>购买</font>",
            		handler:this.buy
            	}]
		   }]    
        });
        this.callParent([config]);
        bidplan=function(data){
        	if(data==1){
        			mobileNavi.push(Ext.create('hrmobile.public.myhome.planbid.biddetail',{biddata:biddata,enterPrise:enterPrise,proDes:proDes,pltype:pltype}));
        	}else if(data==2){
        			mobileNavi.push(Ext.create('hrmobile.public.myhome.planbid.bidrelated',{listMaterials:listMaterials}));
        	}else if(data==3){
        			mobileNavi.push(Ext.create('hrmobile.public.myhome.planbid.biddinglist',{data:biddata}));
        	}
        }
	},
	buy:function(){
		   if(Ext.isEmpty(curUserInfo)){
			   mobileNavi.push(Ext.create('hrmobile.public.myhome.login',{}));
		   }else{
			   mobileNavi.push(Ext.create('hrmobile.public.myhome.Rmmplan.buyplanbid',{Rmmdata:Rmmdata}));
		   }
	}
 
});

