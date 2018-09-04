
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.notice', {
    extend: 'mobile.List',
    name: 'notice',
    alias: 'widget.notice',
    constructor: function (config) {
    	config = config || {};
       /* var bottomBar= Ext.create('hrmobile.public.myhome.related.bottomBarNotice',{
        });*/
    	Ext.apply(config,{
    		modeltype:"notice",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>平台公告</font>",
    		width:"100%",
		    height:"100%",
		    fullscreen: true,
		    items: [/*bottomBar*/],
		    pullRefresh: true,
		    listPaging: true,
		    noMoreRecordsText:false,
    		fields:[{
				name : 'modifyDate'
			},{
				name : 'author'
			},{
				name : 'content' //
			},{
				name : 'title' //
			},{
				name : 'createDate' //
			}],
	        url : __ctxPath + '/article/newslistArticle.do?lid=30',
			root:'result',
		    totalProperty: 'totalCounts',
		    params : {
					isMobile : "1"
			},
		itemTpl:
				   "<div class='list' style='margin: 0 0 5px 8px;'>"+
					"<h1 style='font-size: 17px;margin-top: 15px;margin-bottom: 5px;'>{title}</h1>"+
					"<span style='font-size:12px;'>{createDate}</span></div>", 
			listeners : {
				itemsingletap : this.itemsingletap
			}
    	});

  

    	this.callParent([config]);
    	
    },
	itemsingletap : function(obj, index, target, record) {
    	var data=record.data;
    	var title= data.title;
    	var createDate=data.createDate;
    	var content=data.content;
    	var author=data.author;
    	mobileNavi.push(
	    	Ext.create('hrmobile.public.myhome.noticeDetails',{
				title:title,
				createDate:createDate,
				content:content,
				author:author
	        })
	        )
        
    }

});
