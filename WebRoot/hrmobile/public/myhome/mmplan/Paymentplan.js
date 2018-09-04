
//creditorList.js
Ext.define('hrmobile.public.myhome.mmplan.Paymentplan', {
    extend: 'mobileloan.List',
    name: 'Paymentplan',
    constructor: function (config) {
    	config = config || {};
    	var url =config.url;
	    var panel=Ext.create('Ext.Panel',{
		docked:'top',
		items:[
	        {
	        	html:'<div class="record-table" style="padding:10px 0 0 0 !important;">'+
            '<table width="100%" border="0" cellspacing="0" cellpadding="0">'+
              '<tbody><tr>'+
              	  '<th width="10%">期数</th>'+
              	  '<th width="40%">计划回款额</th>'+
                  '<th width="30%">计划到账日</th>'+
                  '<th width="20%">实际到账日</th>'+
                '</tr>'+
            '</tbody></table>'+
          '</div>'
	        }]});

    	Ext.apply(config,{
    		modeltype:"fundDetails",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>回款计划</font>",
    		items:[panel],
    		pullRefresh: true,
    		scrollToTopOnRefresh:false,//刷新不跳转到顶部
    		style: 'background-color: #fff; padding:0 !important;margin-top:42px;',
		    listPaging: true,
    		fields:[ {
						name : 'periods'
					},{
						name : 'assignInterestId' //
					},{
						name : 'incomeMoney' //
					},{
						name : 'intentDate' //
					},{
						name : 'investPersonId' //
					},{
						name : 'intentDate' //
					},{
						name : 'factDate' //
					}],
    	        url : url,
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
	                  '<td width="10%">{periods}</td>'+
	                  '<td style="padding:0 !important;width:40%;"><span class="font14">{incomeMoney}</span><br></td>'+
	                  '<td width="30%">{intentDate}</td>'+
	                  '<td style="padding:0 !important;  width:20%;">'+
	                  '</td>'+
	                '</tr>'+
	             '</tbody></table>'+
	           '</div>')
    	});

    	this.callParent([config]);

    },

});
