var bidId;
Ext.define('hrmobile.public.myhome.scatteredMark.RiskManagement', {
    extend: 'mobile.List',
    name: 'RiskManagement',
    constructor: function (config) {
    	config = config || {};
    	
    	// 获取列表页bidId
    	bidId = config.bidId;
    	
    	
	    var panel=Ext.create('Ext.Panel',{
		docked:'top',
		scrollDock:'top',
		items:[
	        {
	        	html:'<div class="record-table" style="padding:10px 0 0 0 !important;">'+
            '<table width="100%" border="0" cellspacing="0" cellpadding="0">'+
              '<tbody><tr>'+
              	  '<th width="33.33%">序号</th>'+
              	  '<th width="33.33%">债权人</th>'+
              	  '<th width="33.33%">持有金额</th>'+
                '</tr>'+
            '</tbody></table>'+
          '</div>'
	        }]});

    	Ext.apply(config,{
    		modeltype:"Paymentplan",
			title:"<font color=" + topColor + " style='font-size:" + topsize + "'>风险控制</font>",
    		items:[panel],
    		// 去除顶部边距
    		margin: '-10px 0 0 0',
    		pullRefresh: true,
    		scrollToTopOnRefresh:false, // 刷新不跳转到顶部
    		style: 'background-color: #fff; padding:0 !important;',
		    listPaging: true,
    		fields:[ 
    				{
						name : 'bidId'
					},
					{
						name : 'id'
					},
					{
						name : 'indexId'
					},
					{
						name : 'userName'
					},
					{
						name : 'bondTotelMoney'
					}
					],
    	        url : __ctxPath + "/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId="  + bidId,
	    		root:'bondListBid',
	    	    totalProperty: 'bondListLength',
	    	    params : {
						isMobile : "1"
			},
	    itemTpl: new Ext.XTemplate( 
	    	 
		    	'<div class="record-table" style="padding:0 !important;">'+
	            '<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:0 !important;">'+
	             '<tbody style="padding:0 !important; >' +
	                '<tr style="padding:0 !important;">'+
	                   // 序号
//	                  '<td width="33.33%">{id}</td>'+
	                  '<td width="33.33%">{indexId}</td>'+
	                  
	                  // 债权人
	                  '<td width="33.33%">{userName:this.userName}</td>'+
	                  
	                  // 持有金额
	                  '<td width="33.33%">{bondTotelMoney:this.bondTotelMoney}</td>'+
	                  
	                '</tr>'+
	             '</tbody></table>'+
	           '</div>',
	           	{
	           		// 债权人
	           		userName : function (userName) {
	           			return isStringname(userName);
	           		},
	           		 // 持有金额
	           		bondTotelMoney : function (bondTotelMoney) {
	           			return moneyFormat(bondTotelMoney);
	           		}
	           		
	           		
	           }
	        ),
			listeners :  {
				// 点击事件
		    	activate: function (this1, eOpts) {
		    		// 开启请稍等提示......
			    	mobileView.setMasked(
			    		{
			    			xtype: "loadmask",
			    			message: "请稍等..."
			    		}
			    	);
		    	},
				painted : function () {
					// 隐藏请稍等
					mobileView.setMasked(false);
				}
			}		
//		    listeners : {
//				itemsingletap : this.itemsingletap
//			}
    	});

    	this.callParent([config]);
    	
    	
    	

    }
//	itemsingletap : function(obj, index, target, record) {
//		    
//}

});

