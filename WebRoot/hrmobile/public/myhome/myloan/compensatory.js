
Ext.define('hrmobile.public.myhome.myloan.compensatory', {
    extend: 'mobile.List',
    name:'compensatory',
    constructor: function (config) {
    	config = config || {};
    	recordsitoInvestlist=new Array();
    	Ext.apply(config,{
    		modeltype:"backin",
    		flex:1,
    		width:"100%",
		    height:"100%",
    		pullRefresh: true,
		    listPaging: true,
    		fields:[ {
						name : 'id'
					},{
						name : 'bidName'
					},{
						name : 'compensatoryDate'
					},{
						name : 'compensatoryMoney'
					},{
						name : 'punishMoney'
					},{
						name : 'plateMoney'
					},{
						name : 'backPunishMoney'
					},{
						name : 'backCompensatoryMoney'
					},{
						name : 'compensatoryDays'
					},{
						name : 'surplusMoney'
					},{
						name : 'payintentPeriod'
					}],
    	        url : __ctxPath + '/compensatory/getCompensatoryListPlBidCompensatory.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
	    	    		start:"0",
						isMobile : "1"
				},
 	       pressedCls:'',
		   itemTpl: new Ext.XTemplate( '<div class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">'+
		        '<nav class="inves-nav swiper-slide swiper-slide-next">'+
		           ' <ul>'+
		                '<li>'+
		                    '<div class="box">'+
		                        '<div class="inves-nav-title" style="margin-left: 15px;"><h1>{bidName}</h1>'+
		                    	'<div style="float: right;margin: 2% 10% 0% 0%;"><b class="myloanguanl"  onclick="javascript:lijihuankuan({id:\'{id}\',surplusMoney:\'{surplusMoney}\'})">立即还款</b></div></div>'+
		                        '<div class="figure-nav">'+
		                            '<ul>'+
		                                '<li style="width:20%"><span>{payintentPeriod}</span><br>代偿期数</li>'+
		                                '<li style="width:20%;margin:0%"><span>{compensatoryDate}</span><br>代偿日期</li>'+
		                                '<li style="width:20%"><span>{compensatoryMoney}<label></label></span><br>代偿金额</li>'+
		                                '<li style="width:20%"><span>{punishMoney}<label></label></span><br>罚息</li>'+
		                                '<li style="width:20%"><span>{surplusMoney}<label></label></span><br>剩余金额</li>'+
		                            '</ul>'+
		                        '</div>'+
		                    '</div>'+
		                '</li>'+
		            '</ul>'+
		        '</nav>'+
        	'</div>'
		  ),
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
    	lijihuankuan=function(data){
    		Ext.Msg.prompt(
    	            '输入还款金额',
    	            '<div id="huankuanmoney">最大不超过'+data.surplusMoney+'元</div>',
    	            function(btn, value) {
    	            	if(!value||value<=0||value>data.surplusMoney){
    	            		  Ext.Msg.alert("","请输入还款金额，且不大于"+data.surplusMoney+"元!")
    	            		  return;
    	            	}
    	                if (btn == 'ok') {
    	                	var url =__ctxPath+"/pay/repayCompensatoryPay.do?isMobile=1&atoryId="+data.id+"&checkMoney="+value;
    	                	window.open(url,"_blank")
    	                }
    	            },
    	            null,
    	            true,
    	            null,
    	            { maxLength: 10,
    	              xtype:'numberfield'
    	            }
    	        );
        }
    },
    a:function(e){}
});


