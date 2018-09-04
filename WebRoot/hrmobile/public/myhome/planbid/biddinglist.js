
Ext.define('hrmobile.public.myhome.planbid.biddinglist', {
	extend: 'mobile.List',
    name: 'biddinglist',
     constructor: function (config) {
    	config = config || {};
    	recordsbidplanlist=new Array();

    	this.data=config.data;
	    var panel=Ext.create('Ext.Panel',{
		scrollDock:'top',
		items:[
		   {
		    xtype:'panel',
		    layout: {
			  type: 'vbox'
			  }
		},{
          xtype:'panel',
    	  layout: {
  			width: '100'
			},
			items:[
	        {
	        	html:'<div class="record-table" style="padding:0px !important;">'+
            '<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:0 10px !important;margin-top:42px;">'+
              '<tbody style="padding:0 10px !important;"><tr>'+
                  '<th width="30%">用户名</th>'+
                  '<th width="30%">投资金额</th>'+
                  '<th width="28%">投资时间</th>'+
                '</tr>'+
            '</tbody></table>'+
          '</div>'
	        }]
		}]});

    	Ext.apply(config,{
    		modeltype:"biddinglist",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>投标记录</font>",
    		items:[panel],
    		itemsTpl:[panel].join(""),
    		pullRefresh: true,
    		scrollToTopOnRefresh:false,//刷新不跳转到顶部
    		isload:true,
    		loadfunction:function(this1,records){
		     	for (var i=records.length-1; i >= 0; i--) {
				    recordsbidplanlist.push( records[i] );
		     	}
		     	$('.userName').each(function(i,td){
					$(td).html(isSubName($(td).html(),2,4));
				});
		    },
    			fields:[ {
						name : 'userName'
					},{
						name : 'userMoney' //
					},{
						name : 'bidtime' //
					}],
    	        url : __ctxPath + '/creditFlow/financingAgency/listPlBidMobilePlBidPlan.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
    		            bidId:this.data.bidId,
						isMobile : "1"
			},
	    itemTpl: new Ext.XTemplate( 
	    	'<div class="record-table" style="padding:0!important;color:#4a4a4a;">'+
            '<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:0 10px !important;">'+
             '<tbody style="padding:0 !important;">' +
                '<tr style="padding:0 !important;">'+
                  '<td style="padding:0 !important;  width:30%;"><span class="font14 userName">{userName}</span><br>{createDate}</td>'+
                  '<td style="padding:0 !important;  width:30%;"><span>{userMoney}</span></td><!--增加钱class是红色 mColor 减少钱class是绿色green-->'+
                  '<td style="padding:0 !important;  width:28%;">{bidtime}</td>'+
                '</tr>'+
             '</tbody></table>'+
           '</div>'),	    		
		    listeners : {
				itemsingletap : this.itemsingletap,
			}
    	});

    	this.callParent([config]);

    },
	itemsingletap : function(obj, index, target, record) {


},

});
