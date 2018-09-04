
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.news', {
    extend: 'mobile.List',
    name: 'notice',
    constructor: function (config) {
    	config = config || {};
    	Ext.apply(config,{
    		modeltype:"notice",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>站内消息</font>",
    		width:"100%",
		    height:"100%",
		    fullscreen: true,
		    pullRefresh: true,
		    listPaging: true,
    		fields:[{
				name : 'id'
			},{
				name : 'modifyDate'
			},{
				name : 'addresser'
			},{
				name : 'content'
			},{
				name : 'userName' //
			},{
				name : 'userType' //
			},{
				name : 'title' //
			},{
				name : 'status' //
			},{
				name : 'sendTime' //readStatus
			},{
				name : 'readStatus' //readStatus
			}],
	        url : __ctxPath + '/message/getUserAllOaNewsMessage.do',
			root:'result',
		    totalProperty: 'totalCounts',
		    params : {
					isMobile : "1"
			},
		   itemTpl:'<div style="background-color: #fff;" id="news{id}">'+
					    '<div class="news">'+
					        '<dl>'+
					            '<dt style="    width: 10%;">'+
					            		'<tpl if="readStatus==0"  ><div id="weidupng{id}"><img src="hrmobile/resources/imagesP2p/weidu1.gif" style="width:100%" alt=""></div></tpl>'+
					        			'<tpl if="readStatus==1"  ><div id="weidupng{id}"><img src="hrmobile/resources/imagesP2p/yidu.gif" style="width:100%" alt=""></div></tpl>'+
					        	'</dt>'+
					            '<dd>'+
					                '<h3 style="font-size:17px;color:#000000">{title}<i></i></h3>'+
					                '<ul style="text-align: center;">'+
						                '<li style="width:33%;float:left;">发件时间</li>'+
										'<li style="width:33%;float:left;">发件人</li>'+
										'<li style="width:33%;float:right;"><a>状态</a></li>'+
									'</ul>'+
					                '<ul style="text-align: center;">'+
						                '<li style="width:33%;float:left;">{sendTime}</li>'+
										'<li style="width:33%;float:left;">{addresser}</li>'+
										'<li style="width:33%;float:right;"><tpl if="readStatus==0"><a style="color:red"  id="weidu{id}">未读</a></tpl><tpl if="readStatus==1"><a style="color:green"  id="weidu{id}">已读</a></tpl></li>'+
									'</ul>'+
					            '</dd>'+
					            '<dn style="float:right;">'+
					            '</dn>'+
					        '</dl>'+
					    '</div>'+
					'</div>',
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
        var sendTime=data.sendTime;
    	var content=data.content;
    	var author=data.author;
    	Ext.Ajax.request({
			url: __ctxPath +"/message/getOaNewsMessage.do",
			 params : {
				isMobile : "1",
				id:data.id
	         },
		    success : function(response) {
		    	document.getElementById("weidupng"+data.id).innerHTML ='<img src="hrmobile/resources/imagesP2p/yidu.gif" style="width:100%" alt="">';
		    	document.getElementById("weidu"+data.id).innerHTML ="<font color='green'>已读</font>";
		    	mobileNavi.push(
		    	    	Ext.create('hrmobile.public.myhome.noticeDetails',{
		    				title:title,
		    				createDate:createDate,
		    				content:content,
                            sendTime:sendTime,
		    				author:author
		    	        })
		    	        )
            }
    	});
    	
    	
    	
    	
        
    }

});
