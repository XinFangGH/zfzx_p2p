var  yearlilv=25;
var  mixqixianvar =37;

Ext.define('hrmobile.public.myhome.aiplan.aubidding', {
    extend: 'Ext.Container',
    name: 'aubidding',
     constructor:function(config){
    	var this1= this;
    	var bidauto =config.bidauto;
		Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>自动投标</font>",
			name:'menu',
			scrollable: {
    	        direction: 'vertical'
    	    },
			items:[
		       {
		    	   xtype:'panel',
		    	   html:'<div class="m-user-addrAdd">'+
		    			'<div class="m-user-bar">'+
						'<div class="w-inputBar w-bar">'+
							'<div class="w-bar-label">'+
								'每次投标金额:'+
							'</div>'+
							'<span class="w-bar-input-clear" style="display: none;">×</span>'+
							'<div class="w-bar-control" style="float: right;">'+bidauto.bidMoney+//这里赋值
							'元</div>'+
						'</div>'+
						'<div class="w-selectBar w-bar">'+
							'<div class="w-bar-label">'+
								'利息范围:'+
							'</div>'+
							'<div class="w-bar-control" style="float: right;">'+bidauto.interestStart+'% ~ '+bidauto.interestEnd+'%'+
							'</div>'+
						'</div>'+
						'<div class="w-selectBar w-bar">'+
							'<div class="w-bar-label">'+
								'借款期限'+
							'</div>'+
							'<div class="w-bar-control" style="float: right;">'+bidauto.periodStart+'个月 ~ '+bidauto.periodEnd+'个月'+
							'</div>'+
						'</div>'+
						'<div class="w-selectBar w-bar">'+
							'<div class="w-bar-label">'+
								'信用等级范围'+
							'</div>'+
							'<div class="w-bar-control" style="float: right;">'+bidauto.rateStartShow+'~ '+bidauto.rateEndShow+
							'</div>'+
						'</div>'+
						'<div class="w-inputBar w-bar">'+
							'<div class="w-bar-label">'+
								'账户保留金额:'+
							'</div>'+
							'<span class="w-bar-input-clear" style="display: none;">×</span>'+
							'<div class="w-bar-control" style="float: right;">'+bidauto.keepMoney+
							'元</div>'+
						'</div>'+
					'</div>'+
				'</div>'
    		   },
    		   {
                    	style:"margin: 10px 20px;height:44px;background:"+themeColor+";font-color:white",
                    	xtype: 'button',
                        text:"<font color=white>修改</font>",
                        handler:this.updateauto
               }
			]
		});
		
		this.callParent([config]);
    },
    updateauto:function(){
    	 mobileNavi.push(Ext.create('hrmobile.public.myhome.aiplan.aubiddingupdate',{}));
    }

});