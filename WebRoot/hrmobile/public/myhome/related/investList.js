
//creditorList.js
Ext.define('hrmobile.public.myhome.related.investList', {
    extend: 'mobile.List',
    
    name: 'investList',

    constructor: function (config) {
    	config = config || {};
    	var paneltop=Ext.create('Ext.Panel',{docked:'top',laout:'hbox',items:[
			{html:"<div class='banner' style='text-align:center;padding-top:60px;height:170px;'></div>"},
			{
			xtype:"panel",
			layout: {
						type: 'hbox',
						align: 'middle'
					},
			items:[{html:"33223232332<br>可用金额",width:"33%",style:{background:'#F5DA58',height:'50px'}},
				{html:"33223232332<br>累积已获收益",width:"33%",style:{background:'#FDD118',height:'50px'}},
				{html:"33223232332<br>累积投资金额",width:"33%",style:{background:'#E48C13',height:'50px'}}]		
			
			}]});
    	var bottomBar= Ext.create('hrmobile.public.myhome.related.bottomBar',{
        });
    	var panel=Ext.create('tableHeader',{style:"margin-left:10px;",header:new Array("<span class='tablehead' >债务人</span>",
    	                                                      "<span class='tablehead' >借款金额(万)</span>",
    	                                                      "<span class='tablehead' >已还金额(万)</span>")});
    	Ext.apply(config,{
    		modeltype:"investList",
    		//title:"投资列表",
    		items:[paneltop,panel,bottomBar],
    		fields:[ {
						name : 'bidId'
					},{
						name : 'bidProName' //
					}],
    	        url : __ctxPath + '/creditFlow/financingAgency/listPlBidPlan.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
						isMobile : "1"
			},
		    itemTpl: "<span  class='tablelist'>{bidProName}</span>&nbsp;&nbsp;" +
		    		"<div class='index_xm'>"+
		            "<div class='left_txt fl'>"+
			"<h3><a href='#'>矿业企业资金流转（二）</a></h3>"+
			"<p>项目规模：<span>50万</span></p>"+
			"<p>年化收益：<span>11 % </span></p>"+
			"<p>项目期限：<span>180天</span></p>"+
		"</div>"+
		"<div class='right_txt fr'>"+
			"<div><img src='images/btn.jpg' alt='' width='100%' /></div>"+
			"<div class='percentBox'>"+
			"	<div id='bg1'></div>"+
			"	<!-- 承载进度文字 -->"+
			"	<div id='txt1' class='pertxt'></div>"+
			"</div>"+
		"</div>"+
		"<div class='cb'></div>"+
	"</div>" ,
		    		
 	        listeners : {
				itemsingletap : this.itemsingletap
			}
    	});

    	this.callParent([config]);

    },
	itemsingletap : function(obj, index, target, record) {
    	  var data=record.data;
    	  var label = new Array("债务人","借款金额(万)","已还金额(万)","借款余额(万)","借款截止日期",
    	 "偿还方式","凭证方式"); 
    	  var value = new Array(data.zqrpname,data.creditmoney,data.repayment,data.bowmoney,
    	  data.creditenddate,data.repaywayValue,data.voucherwayValue);  
          getListDetail(label,value);
		    

}
});
