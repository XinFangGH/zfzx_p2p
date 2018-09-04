var str,
	Financing;
Ext.define('hrmobile.public.myhome.Paymentplan', {
    extend: 'mobile.List',
    name: 'Paymentplan',
    constructor: function (config) {
    	config = config || {};
    	str = config.str;
    	Financing =config.Financing;
	    var panel=Ext.create('Ext.Panel',{
		docked:'top',
		scrollDock:'top',
		items:[
	        {
	        	html:'<div class="record-table" style="padding:10px 0 0 0 !important;">'+
            '<table width="100%" border="0" cellspacing="0" cellpadding="0">'+
              '<tbody><tr>'+
              	  '<th width="10%" style="border-left: 1px solid transparent;">期数</th>'+
              	  '<th width="22%">计划到账日</th>'+
//              	  '<th width="22%">实际到账日</th>'+
              	  '<th width="22%">应回金额</th>'+
                  '<th width="20%">实回金额</th>'+
                  '<th width="15%">状态</th>'+
                '</tr>'+
            '</tbody></table>'+
          '</div>'
	        }]});

    	Ext.apply(config,{
    		modeltype:"Paymentplan",
			title:"<font color=" + topColor + " style='font-size:" + topsize + "'>回款计划</font>",
    		items:[panel],
    		// 去除顶部边距
    		margin: '-10px 0 0 0',
    		pullRefresh: true,
    		scrollToTopOnRefresh:false, // 刷新不跳转到顶部
    		style: 'background-color: #fff; padding:0 !important;',
		    listPaging: true,
    		fields:[  {
						name : 'bidId'
					},{
						name : 'payintentPeriod' //
					},{
						name : 'intentDate' //计划到账日
					},{
						name : 'compensationMoney' //
					},{
						name : 'intenttotal' //
					},
					// 计划回款款金额
					{
						name : 'planPaymentsMoney'
					},
					
					// 实际回款金额
					{
						name : 'factPaymentsMoney' // 
					},
					{
						name : 'loanerRepayMentStatus' //factPaymentsMoney
					},{
						name : 'factDate' // 实际回款金额
					}
					
					],
    	        url : __ctxPath + "/user/paymentplanBpCustMember.do?str="  +str,
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
						isMobile : "1"
			},
	    itemTpl: new Ext.XTemplate( 
		    	'<div class="record-table" style="padding:0 !important;">'+
	            '<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:0 !important;">'+
	             '<tbody style="padding:0 !important; >' +
	                '<tr style="padding:0 !important;">'+
	                   // 期数
	                  '<td width="10%" style="border-left: 1px solid transparent;">{payintentPeriod}</td>'+
	                  
	                  // 计划到账日
	                  '<td style="padding:0 !important;width:22%;"><span class="font14">{intentDate}</span><br></td>'+
	                  
	                  
//	                  '<td style="padding:0 !important;width:22%;"><span class="font14">{factDate:this.factDate}</span></td>'+
	                   // 计划回款款金额
	                  '<td width="22%">{planPaymentsMoney}元</td>'+
	                  
	                   // 实际回款金额
	                  '<td width="20%">{factPaymentsMoney}</td>'+
	                  
	                   // 回款状态
	                  '<tpl if="factPaymentsMoney==0"><td style="padding:0 !important;  width:15%;">未回款</td></tpl>'+
	                  '<tpl if="factPaymentsMoney!=0"><td style="padding:0 !important;  width:15%;">已回款</td></tpl>'+
//	                  '<td style="padding:0 !important;  width:20%;">已回款</td></tpl>'+
	                '</tr>'+
	             '</tbody></table>'+
	           '</div>',
	           	{
	           		// 实际到账日
	           		factDate : function (factDate) {
	           			return factDate == '' || factDate == null ? '-- --' : factDate;
	           		}
	           		
	           }
	        ),
			listeners :  {
				painted : function () {
					// 去除顶部边距
//					$('.x-panel').css('margin','-10px 0 0 0');
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

