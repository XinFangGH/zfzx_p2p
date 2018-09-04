
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.noticeDetails1', {
    extend: 'Ext.Container',
    name: 'noticeDetails1',
    constructor: function (config) {
    	config = config || {};
    	var title=config.title;
    	var createDate=config.createDate;
    	var content=config.content;
    	var author=config.author;
        /*var bottomBar= Ext.create('hrmobile.public.myhome.related.bottomBar',{
        });*/
    	Ext.apply(config,{
    		modeltype:"noticeDetails",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>消息详情</font>",
    		width:"100%",
		    height:"100%",
		    fullscreen: true,
		    scrollable:{
		    	direction: 'vertical'
		    },
		    items: [
		            {html:'<div class="newsDetail">'+
				        '<div class="detail">'+
				            '<span>3-21  17:21</span>'+
				            '<p class="detailCon">'+
				                '<i class="detailArr"></i>'+
				                '您好，您账户中的2000.00优惠券即将过期，优惠券有效期到：2016-01-07请登录平台及时使用，如有疑问请致电：4000-903-910【升升投】'+
				            '</p>'+
				        '</div>'+
				    '</div>'}
		    /*,bottomBar*/]
		            
    	});

    	this.callParent([config]);
    	
    }

});
