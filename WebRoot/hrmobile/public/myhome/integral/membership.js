
//creditorList.js
Ext.define('hrmobile.public.myhome.integral.membership', {
    extend: 'mobile.List',
    name: 'membership',
    constructor: function (config) {
    	config = config || {};
	    var panel=Ext.create('Ext.Panel',{
		docked:'top',
		items:[
	        {
	        	html:'<div class="record-table" style="padding:10px 0 0 0 !important;">'+
            '<table width="100%" border="0" cellspacing="0" cellpadding="0">'+
              '<tbody><tr>'+
                  '<th width="40%">时间</th>'+
                  '<th>积分</th>'+
                  '<th>详情</th>'+
                '</tr>'+
            '</tbody></table>'+
          '</div>'
	        }]});

    	Ext.apply(config,{
    		modeltype:"fundDetails",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>我的积分</font>",
    		items:[panel],
    		pullRefresh: true,
    		scrollToTopOnRefresh:false,//刷新不跳转到顶部
    		style: 'background-color: #fff; padding:0 !important;',
		    listPaging: true,
    		fields:[ {
						name : 'bidId'
					},{
						name : 'createTime' //
					},{
						name : 'recordNumber' //
					},{
						name : 'bonusDescription' //
					}],
    	        url : __ctxPath + '/bonusSystem/pageListWebBonusRecord.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
						isMobile : "1"
			},
			baseCls:'padding:0;',
			padding:0,
	    itemTpl: new Ext.XTemplate( 
	    	 
		    	'<div class="record-table" style="padding:0 !important;">'+
	            '<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:0 !important;">'+
	             '<tbody style="padding:0 !important;">' +
	                '<tr style="padding:0 !important;">'+
	                  '<td style="padding:0 !important;  width:40%;">' +
	                  	'<span class="font14">{createTime}</span><br>' +
	                  '</td>'+
	                  '<td>'+
		                  '{recordNumber:this.adddelete}'+
		                  '{payMoney:this.adddeletes}'+
		                  '<tpl if="incomMoney==0 &&payMoney== 0"><p style="color:black;">0元</p></tpl>'+
	                 '</td>'+
	                  '<td style="padding:0 !important;  width:30%;">{bonusDescription}</td>'+
	                '</tr>'+
	             '</tbody></table>'+
	           '</div>',
            	{
		    			adddelete: function(incomMoney) {
		    				var incomMoney = new Number(incomMoney);
		    				if(incomMoney>0){
		    					return  '<span class="mColor">+'+incomMoney+'分</span><!--增加钱class是红色 mColor 减少钱class是绿色green-->';
		    				}
						    return ;
						},
		    		    adddeletes: function(payMoney) {
		    				var payMoney = new Number(payMoney);
		    				if(payMoney>0){
		    					return  '<span class="green">-'+payMoney+'元</span><!--增加钱class是红色 mColor 减少钱class是绿色green-->';
		    				}
						    return ;
						}  
		    		}),	    		
		    listeners : {
				itemsingletap : this.itemsingletap
			}
    	});

    	this.callParent([config]);

    },
	itemsingletap : function(obj, index, target, record) {
		    

},

});
