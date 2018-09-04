var mmplandata;
var htmladd;
Ext.define('hrmobile.public.myhome.mmplanDetailtop', {
    extend: 'Ext.Container',
    name:'mmplanDetailtop',
    constructor:function(config){
		Ext.apply(config,
			{docked:'top',
			items:[{html:'<header class="header-bg" style="color:#fff;background:'+themeColor+'">'+
							'<div class="header-top">'+
							'</div>'+
							'<div class="year">'+
				                '<div class="mmplandetailtop"><span>'+mmplandata.yeaRate+'</span>%'+htmladd+'</div>'+
				            '</div>'+
							'<div class="data-box">'+
								'<div class="fl data-timemmplan txtc white">'+
									'<p class="font35">'+mmplandata.investlimit+'个月</p>'+
									'<p class="font35">投资期限</p>'+
								'</div>'+
								"<div class='four fl data-timemmplan'>"+
									"<canvas id='mmplandetailpro' style='width: 100%;margin-top:10px;'></canvas>"+
								"</div>"+
								'<div class="fr data-timemmplan txtc white">'+
									'<p class="font35">'+mmplandata.limitMoney/10000+'万元</p>'+
									'<p class="font35">计划金额</p>'+
								'</div>'+
							'</div>'+
						'</header>'+
						'<nav class="member-nav" style="border: none;">'+
					        '<div style="background: #f98882;height:3em">'+
								'<div class="mmplandetailmmname">'+mmplandata.mmName+'</div>'+
					        '</div>'+
						'</nav>'
			}],
			listeners:{  
				resize :function(){
					if(mmplandata.progress==0){
						progress=0.01;
					}else if(mmplandata.progress==100){
						progress=99.99;
					}else{
						progress=mmplandata.progress;
					}
		           circleProgress({
					    id: 'mmplandetailpro',
					    progress:progress, // default: 100
			            duration: 1000, // default: 1000
			            color: '#84c5fd', // default: 'rgb(52, 145, 204)'
			            bgColor: '#FFF', // default: 'rgb(230, 230, 230)'
			            textColor: '#FFF', // default: 'black'
			            progressWidth: 0.05, // default: 0.25 (r)
			            fontScale: 0.0, // default: 0.4 (r)
			            toFixed: 0  // default: 0
					        });
			}}
		});
		this.callParent([config]);
    }
});

Ext.define('hrmobile.public.myhome.mmplanDetail2', {
    extend: 'mobile.List',
    
    name: 'mmplanDetail2',

    constructor: function (config) {
    	config = config || {};
    	var mmplanId=config.mmplanId;
    	Ext.apply(config,{
    		modeltype:"bidDetail2",
    		flex:1,
    		width:"100%",
		    height:"100%",
		    pullRefresh: true,
	        listPaging: true,
    		fields:[		'orderId'
							,'investPersonName'
							,'buyMoney'
							,'buyDatetime'
					],
    	    	url: __ctxPath +"/creditFlow/financingAgency/getBuyInfoPlManageMoneyPlan.do",
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
	    	    	mmplanId:mmplanId,
	    	    	isMobile:'1'
				},
				pressedCls:'',
				itemTpl:new Ext.XTemplate(
						"<div class='details3 mmplandetaillist'>" +
							"<table border=\"0\" ><td width='30%'>{investPersonName:this.ellipsis}</td>" +
							"<td width='30%'>{buyMoney:this.numberFormat}元</td>" +
							"<td width='40%'>{buyDatetime}</td>" +
							"</table>" +
						"</div>",
					{
		    			numberFormat: function(num) {
		    				var num = new Number(num);
						  return (num.toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
						},
						ellipsis:function(num){
						  return Ext.util.Format.ellipsis(num,7);
						}
		    		})	
    	});

    	this.callParent([config]);

    }
});
Ext.define('hrmobile.public.myhome.mmplanDetail1', {
    extend: 'Ext.Container',
    name:'mmplanDetail1',
    constructor:function(config){
        var html='<body class="fill">'+
							    '<nav class="assets-nav assets1-top">'+
							        '<ul>'+
							            '<li class="biddetailli"><span class="left biddetailleft">名称</span><span class="biddetailspanreighr" >'+mmplandata.mmName+'</span></li>'+
							            '<li class="biddetailli"><span class="left biddetailleft">招标说明</span><span class="biddetailspanreighr">'+mmplandata.bidRemark+'</span></li>'+
							            '<li class="biddetailli"><span class="left biddetailleft">投资范围</span><span class="biddetailspanreighr">'+mmplandata.investScope+'</span></li>'+
							            '<li class="biddetailli"><span class="left biddetailleft">收益方式</span><span class="biddetailspanreighr">按期收息，到期还本</span></li>'+
							            '<li class="biddetailli"><span class="left biddetailleft">锁定期</span><span class="biddetailspanreighr">'+mmplandata.lockingLimit+'月</span></li>'+
							            '<li class="biddetailli"><span class="left biddetailleft">起投金额</span><span class="biddetailspanreighr">'+mmplandata.startMoney+'元</span></li>'+
							            '<li class="biddetailli"><span class="left biddetailleft">加入费率</span><span class="biddetailspanreighr">'+(mmplandata.raiseRate==null?"0":mmplandata.raiseRate)+'%</span></li>'+
							            '<li class="biddetailli"><span class="left biddetailleft">退出费率</span><span class="biddetailspanreighr">'+(mmplandata.raiseRate==null?"0":mmplandata.raiseRate)+'%</span></li>'+
							            '<li class="biddetailli"><span class="left biddetailleft">起息模型</span><span class="biddetailspanreighr">满标日起息</span></li>'+
							            '<li class="biddetailli"><span class="left biddetailleft">开放时间</span><span class="biddetailspanreighr">'+mmplandata.buyStartTime+'</span></li>'+
							            '<li class="biddetailli"><span class="left biddetailleft">截至时间</span><span class="biddetailspanreighr">'+mmplandata.buyEndTime+'</span></li>'+
							            '<li class="biddetailli"><span class="left biddetailleft">起息时间</span><span class="biddetailspanreighr">未起息</span></li>'+
							            '<li class="biddetailli" style="min-height:20px;height:55px !important;"><font class="left biddetailleft">赎回方式</font><font class="biddetailspanreighr" style="width:61%;height:50px;">'+mmplandata.expireRedemptionWay+'</font></li>'+
							            '<li class="biddetailli" style="margin-bottom: 0px;"><span class="left biddetailleft">保障方式</span><span class="biddetailspanreighr">'+mmplandata.guaranteeWay+'</span></li>'+
							        '</ul>'+
							    '</nav>'+
							'</body>';
         
        
        var state = mmplandata.state;
        if(state=="1"){
              stateflag=false;
        }else{
              stateflag=true;
        }
		Ext.apply(config,{
		width:"100%",
	    height:"100%",
	    fullscreen: true,
	    scrollable:{
	    	direction: 'vertical'
	    },
		items:[{
				docked:'bottom',
            	style:stateflag?"height: 40px;margin:10px 20px;background:#9D9D9D;font-color:white":"height: 40px;margin:10px 20px;background:"+themeColor+";font-color:white",
            	xtype: 'button',
                text:"<font color=white>立即投资</font>",
                scope:this,
                disabled:stateflag,
                handler:this.buy
            }],
            html:html});
		
		this.callParent([config]);
		
    },
	buy:function(){
	  if(curUserInfo==null){
	      mobileNavi.push(Ext.create('hrmobile.public.myhome.login',{}));
	  }else{
		 	Ext.Ajax.request({
				url: __ctxPath +"/creditFlow/financingAgency/availableInvestMoneyPlBidPlan.do",
				 params : {
		         },
			    success : function(response) {
			    	var responseText = Ext.util.JSON.decode(response.responseText);
			    	if(responseText.success==true){
				    	mmplandata.availableInvestMoney=responseText.availableInvestMoney;
				    	hidebottomBarIndex();
				        mobileNavi.push(Ext.create('hrmobile.public.myhome.mmplanImmediatelyInvest',{data:mmplandata}));
			    	}else{}
		        }});
	  }
	
	}
});
Ext.define('hrmobile.public.myhome.mmplanDetail', {
	extend: 'Ext.TabPanel',
    
    name: 'mmplanDetail',
    constructor: function (config) {
    	var data=config.data;
    	mmplandata=config.data;
    	if(mmplandata.addRate>0){
    		htmladd='<span style="font-size:14px">+'+mmplandata.addRate+'%</span>'
    	}else{
    		htmladd=''
    	}
    	var html1="<div class='ljtzg' style='margin-top:20px;'>" +
					"<div style='display: block;overflow: hidden;width: 100%;'>" +
					"<div class='fl' style='width:40%;'>" +
						"<img src='hrmobile/resources/imagesP2p/mmplan_pic1.png' style='width: 30%;margin-left:25%;'></img>" +
					"</div>" +
					"<div class='fr' style='margin: 0% 0%;;width: 60%;'>发布时间 </br>"+data.buyStartTime+"</div>" +
					"</div>" +
					"<div style='width: 2px;height: 50px;background: #c7ddf6;margin: 1% 17%;'></div>"+
					"<div style='display: block;overflow: hidden;width: 100%;'>" +
					"<div class='fl' style='width:40%;'>" +
						"<img src='hrmobile/resources/imagesP2p/mmplan_pic2.png' style='width: 30%;margin-left:25%;'></img>" +
					"</div>" +
					"<div class='fr' style='margin:0% 0%;;width: 60%;'>计划金额 </br><span style='color:#F89321;'>"+data.sumMoney/10000+"</span>万元</div>" +
					"</div>" +
					"<div style='width: 2px;height: 50px;background: #c7ddf6;margin: 1% 17%;'></div>"+
					"<div style='display: block;overflow: hidden;width: 100%;'>" +
					"<div class='fl' style='width:40%;'>" +
						"<img src='hrmobile/resources/imagesP2p/mmplan_pic3.png' style='width: 30%;margin-left:25%;'></img>" +
					"</div>" +
					"<div class='fr' style='margin:0% 0%;;width: 60%;'>加入人数 </br><span style='color:#F89321;'>"+data.persionNum+"</span>人</div>" +
					"</div>" +
					"<div style='width: 2px;height: 50px;background: #c7ddf6;margin: 1% 17%;'></div>"+
					"<div style='display: block;overflow: hidden;width: 100%;'>" +
					"<div class='fl' style='width:40%;'>" +
						"<img src='hrmobile/resources/imagesP2p/mmplan_pic4.png' style='width: 30%;margin-left:25%;'></img>" +
					"</div>" +
					"<div class='fr' style='margin:0% 0%;;width: 60%;'>为用户赚取 </br><span style='color:#F89321;'></span>元</div>" +
					"</div>" +
				"</div>";
		var bidDetail1=Ext.create('hrmobile.public.myhome.mmplanDetail1',{data:data}); 
	    var bidDetail2=Ext.create('hrmobile.public.myhome.mmplanDetail2',{mmplanId:data.mmplanId}); 
	    var	topcommmon= Ext.create('hrmobile.public.myhome.mmplanDetailtop',{data:data});
		config = config || {};
	    Ext.apply(config,{
        	title:"<font color="+topColor+" style='font-size:"+topsize+"'>计划详情</font>",
            layoutOnTabChange: true,
	        tabBar:  
	        	{defaults:{flex:2},
	            layout:{  
	                pack:'center'
	            },
	           style: 'border-color:'+themeColor+';background:#fff;font-color:#000;border-radius:5px;border-bottom:1px solid #D2D2D2;'
	        },
            items: [topcommmon,
	            {
	            	title: '<div style="font-size:1em;">产品介绍</div>',
	                items:[{
	    	        	xtype: 'container',
	    	            items:bidDetail1
	    	        }]
	            },
	            {
	            	title: '<div style="font-size:1em;">投资记录</div>',
	                items:[{
	                	xtype: 'container',
	    	            items:bidDetail2
	    	        }]
	            },
	            {
	            	title: '<div style="font-size:1em;">项目进程</div>',
	                items:[{
						xtype: 'panel',
						width:"100%",
						height:"100%",
						fullscreen: true,
						scrollable:{
							direction: 'vertical'
						},
						html:html1
	    	        }]
	            }
            ]
        });
    
        
        this.callParent([config]);
	}
});

