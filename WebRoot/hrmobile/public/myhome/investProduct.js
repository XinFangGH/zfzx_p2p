
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.investProduct', {
    extend: 'mobile.List',
    name: 'investProduct',
    constructor: function (config) {
    	config = config || {};
    	
    	var investthis = this;
	    
	    var panel=Ext.create('Ext.Panel',{
		//	scrollDock:'top',
			 docked: 'top',
			items:[
			{
			xtype:'panel',
			layout: {
				type: 'vbox'
				},
			  items:[
		         {
	                xtype: 'selectfield',
                    label: '投资状态',
                    name:"buystate",
                    scope:this,
                    options: [
                        {text: '回款中',  value: '1'},
                        {text: '投标中', value: '2'},
                        {text: '已结束',  value: '3'},
                        {text: '投标失败',  value: '4'}
                    ],
                    listeners : {
                    	change : function( this1,newValue, oldValue, eOpts ){
                    		search();
                    	}
                    }
	         }]}]});
	   
    	Ext.apply(config,{
    		modeltype:"investProduct",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>投资产品</font>",
    		items:[/*topcommmon,*/panel/*,bottomBar*/],
    		itemsTpl:[panel].join(""),
    		pullRefresh: true,
		    scrollable:{
		    	direction: 'vertical'
		    },
		    listPaging: true,
    		fields:[ 
    		         {
						name : 'bidId'
					},{
						name : 'planId'
					},{
						name : 'bidProName' //
					},{
						name : 'afterMoneyTotal' //
					},{
						name : 'interestRate' //
					},{
						name : 'loanLife' //
					},{
						name : 'publishSingeTime' //
					},{
						name : 'notMoneyTotal' //
					},{name:'incomeMoney'}
					,{
						name : 'contract' //
					},{
						name : 'contract2' //
					}],
    	        url : __ctxPath + '/financePurchase/myFinancePurchase.do',
	    		root:'result',
	    	    totalProperty: 'totalCounts',
	    	    params : {
						isMobile : "1"
			},
		  itemTpl: new Ext.XTemplate(     		
			 "<div class='zjmxg'><div class='table1'>"+
				"<table border='0'>"+
				"	<tr>"+
				"		<td align='right' width='23.5%'>项目名称：</td>"+
				"		<td colspan='3'><a href='#' onclick='javascript:bidDetail({" +
							"bidId:\"{bidId}\"});'>{bidProName}</a></td>"+
				"	</tr>"+
				"	<tr>"+
				"		<td align='right'>金额(元)：</td>"+
				"		<td>{afterMoneyTotal:this.numberFormat}</td>"+
				"		<td align='right'>年化利率：</td>"+
				"		<td>{interestRate}%</td>"+
				"	</tr>"+
				"	<tr>"+
				
				"		<td align='right'>项目期限：</td>"+
				"		<td>{loanLife}</td>"+
				"		<td align='right'>投标时间：</td>"+
				"		<td>{publishSingeTime}</td>"+
				"	</tr>"+
				"	<tr>"+	
				"		<td align='right'>待回款：</td>"+
				"		<td>{notMoneyTotal}</td>"+
				"	</tr>"+
				" <tr>"+
			/*	"<td align='right'><span class='download' onclick=\"javascript:downLoadFilecontract('{contract}','{contract2}')\"><tpl if='contract2!=null||contract!=null' >合同下载</tpl><tpl if='contract2==null&&contract==null' >无合同</tpl></span></td>"+*/
				"<td>&nbsp;</td>"+
				"<td align='right'>&nbsp;</td>"+
				"<td>&nbsp;</td>"+
			    "</tr>"+
				"</table>"+
			"</div></div>"
			,{
		    		filepathFormat: function(filepath) {
		    			//      filepath='attachFiles/webfile/sss.docx';
		    			 	var reg = /\\/g;
                            var  filepath=filepath.replace(reg,"/");
                            return filepath;
						}  ,
					numberFormat: function(num) {
	    				var num = new Number(num);
					  return (num.toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
					}  
		    		})/*,
		    listeners : {
				itemsingletap : this.itemsingletap
			}*/
    	});

    	this.callParent([config]);
    	
    	bidDetail =function(data) {
          	Ext.Ajax.request({
					url: __ctxPath +"/creditFlow/financingAgency/bidPlanDetailisMobilePlBidPlan.do",
					 params : {
						isMobile : "1",
						bidId:data.bidId
			         },
				    success : function(response) {
				    	var  responseText1=response.responseText.replace(/[\n]/ig,'');
				    	var responseText = Ext.util.JSON.decode(responseText1);
				    	var data=responseText.data;
				    	data.investEnterpriseEnterprisename=responseText.investEnterpriseEnterprisename;
				    	data.proDes=responseText.proDes;
				    	if(!Ext.isEmpty(responseText.formtoken)){
				    		data.formtoken=responseText.formtoken;
				    	}
				    	hidebottomBarIndex();
				    	    mobileNavi.push(Ext.create('hrmobile.public.myhome.bidDetail',data));
 	            }});
 	};
    	
        downLoadFilecontract=function(contract,contract2){
        	var reg = /\\/g;
     
        	if(!Ext.isEmpty(contract2)){
        			
               var  contract2=contract2.replace(reg,"/");
        	   downLoadFile(contract2)
        	}else if(!Ext.isEmpty(contract)){
        	   var  contract=contract.replace(reg,"/");
        	    downLoadFile(contract)
        	}else{
        	}
       
        
        };
        
        search=function(){

        	var buystate=investthis.getCmpByName("buystate").getValue();
        	var deParams={};
            deParams.start = 0;
            deParams.limit = 10;
            deParams.buystate=buystate;
            gridstore= investthis.getStore();
            gridstore.getProxy().getExtraParams().buystate=buystate;
            investthis.getStore().load();

        };
    }

});
