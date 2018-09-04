
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.noticeDetails', {
    extend: 'Ext.Container',
    name: 'noticeDetails',
    constructor: function (config) {
    	config = config || {};
    	var title=config.title;
    	var createDate=config.createDate;
        var sendTime=config.sendTime;
    	var content=config.content;
    	var author=config.author;
//        var bottomBar= Ext.create('hrmobile.public.myhome.related.bottomBar',{
//        });
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
		            {html:"<div class='news_details'>"+
		    		"<div class='header'>"+
					"<h1 style='text-align:center;line-height:60px;font-size:1.2rem;'>"+title+"</h1>"+
					"<div style='font-size:14px !important;text-align:center;'>"+sendTime+"</div>"+
//					"<span class='fl' style='font-size:12px !important'>作者："+(author==null?"无":author)+"</span>"+//作者没有 舍去
					"<div class='cb'></div></div>"+
					"<div class='details' style='font-size:14px !important;margin:10px 15px;line-height:24px;'>"+content+"</div></div>"}
		    /*,bottomBar*/]
		            
    	});

    	this.callParent([config]);
    	
    }

});
