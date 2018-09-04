
//creditorList.js
Ext.define('hrmobile.public.myhome.fundDetails', {
    extend: 'mobile.List',
    
    name: 'fundDetails',

    constructor: function (config) {
    	config = config || {};
    	
	    var panel=Ext.create('Ext.Panel',{
		docked:'top',
		items:[
	        {
	        	html:'<div class="record-table" style="padding:0px 0 0 0 !important;">'+
            '<table width="100%" border="0" cellspacing="0" cellpadding="0">'+
              '<tbody><tr>'+
                  '<th width="40%">交易类型</th>'+
                  '<th>交易金额</th>'+
                  '<th>交易状态</th>'+
                '</tr>'+
            '</tbody></table>'+
          '</div>'
	        }]});

    	Ext.apply(config,{
    		modeltype:"fundDetails",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>资金明细</font>",
    		items:[panel],
    		pullRefresh: true,
    		scrollToTopOnRefresh:false,//刷新不跳转到顶部
    		style: 'background-color: #fff; padding:0 0 42px !important;margin-top:42px;',
		    listPaging: true,
    		fields:[ {
						name : 'bidId'
					},{
						name : 'transferType' //
					},{
						name : 'incomMoney' //
					},{
						name : 'payMoney' //
					},{
						name : 'transferDate' //
					},{
						name : 'dealRecordStatus' //transferTypeName
					},{
						name : 'transferTypeName' //transferTypeName
					},{
						name : 'msg' //transferTypeName
					},{
						name : 'createDate' //transferTypeName
					}],
    	        url : __ctxPath + '/financePurchase/detailFinancePurchase.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
						isMobile : "1",
						limit:'12'
			},
			baseCls:'padding:0;',
			padding:0,
	    itemTpl: new Ext.XTemplate( 
	    	 
		    	'<div class="record-table" style="padding:0 !important;">'+
	            '<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:0 !important;">'+
	             '<tbody style="padding:0 !important;">' +
	                '<tr style="padding:0 !important;">'+
	                  '<td style="padding:0 !important;  width:40%;">' +
	                  	'<span class="font14">{transferTypeName}</span><br>{createDate}' +
	                  '</td>'+
	                  '<td>'+
		                  '{incomMoney:this.adddelete}'+
		                  '{payMoney:this.adddeletes}'+
		                  '<tpl if="incomMoney==0 &&payMoney== 0"><p style="color:black;">0元</p></tpl>'+
	                 '</td>'+
	                  '<td style="padding:0 !important;  width:30%;">{msg}</td>'+
	                '</tr>'+
	             '</tbody></table>'+
	           '</div>',
            	{
		    			adddelete: function(incomMoney) {
		    				var incomMoney = new Number(incomMoney);
		    				if(incomMoney>0){
		    					return  '<span class="mColor">+'+incomMoney+'元</span><!--增加钱class是红色 mColor 减少钱class是绿色green-->';
		    				}
						    return ;
						},
		    		    adddeletes: function(payMoney) {
		    				var payMoney = new Number(payMoney);
		    				if(payMoney>0){
		    					return  '<span class="green">-'+payMoney+'元</span><!--增加钱class是红色 mColor 减少钱class是绿色green-->';
		    				}else if(payMoney<0){
		    					return  '<span class="mColor">'+-payMoney+'元</span><!--增加钱class是红色 mColor 减少钱class是绿色green-->';
		    				}
						    return ;
						}  
		    		}),	    		
		    listeners : {
				itemsingletap : this.itemsingletap,
                painted:function(){
                    $('#navbar').css({"position":"fixed","width":"100%","z-index":"9"});
                }
			}
    	});

    	this.callParent([config]);

    },
	itemsingletap : function(obj, index, target, record) {
		    

},

});
