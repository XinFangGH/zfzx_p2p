var  width=100;
var  stateflag=false;
var  data;
var	 churangmoney;
var	 qianshoumoney;
Ext.define('hrmobile.public.myhome.plandebts.buyplanbid', {
	extend: 'Ext.Panel',
    name: 'buyplanbid',
    constructor: function (config) {
		config = config || {};
		data=config.data;
	    Ext.apply(config,{
        	title:'<font color=#ffffff style="font-size:17px">'+data.bidProName+'</font>',
            width:"100%",
			height:"100%",
		    listPaging: true,
			scrollable:{
			direction: 'vertical'
			},
		            items: [{
		            	xtype:"label",
		            	html:"<div class='allplan' style='background: #fff;'>"+
						    		"<div>"+
						    			"<li style='padding:4%;'>截止至<span id='newDatepde'></span>日，债权交易信息如下</li>"+
						    			"<span>债权信息</span>"+
						    		"</div>"+
						    		"<div style='width:100%;padding: 10px 0;overflow: hidden;'>"+
							    		'<div class="planleft">'+
							    			"<li>年化收益 	<font id='yearAccrualRatepde'>12%</font></li>"+
							    			"<li>已收本金	<font id='accrualMoneypde'>0.00</font></li>"+
							    			"<li>未收本金	<font id='loadMoneypde'>0.00</font></li>"+
							    			"<li>已付收益	<font id='afterInterestpde'>0.00</font></li>"+
							    			"<li>未付收益	<font id='notLoanInterestpde'>0.00</font></li>"+
							    		'</div>'+
							    		'<div class="planreight">'+
							    			"<li>期限		<font id='dayspde'>0天</font></li>"+
							    			"<li>总金额		<font id='saleMoneypde'>1000元</font></li>"+
							    			"<li>总收益		<font id='loanInterestpde'>0.00元</font></li>"+
							    			"<li>起始日		<font id='startDatepde'></font></li>"+
							    			"<li>到期日		<font id='intentDatepde'></font></li>"+
							    		'</div>'+
						    		'</div>'+
						     "<div>"+
					    			"<li style='padding:2%;border-top:1px solid #eee;'></li>"+
					    			"<span>交易结算清单</span>"+
					    		"</div>"+
					    		"<div style='width:100%;padding: 10px 0;overflow:hidden;'>"+
					    			"<div class='plancenter'>"+
						    			"<li>可出让本金 	<font id='loadMoneyoppde'>0.00</font></li>"+
						    			"<li>欠收利息	<font id='sumInteresteMoneypde'>0.00</font></li>"+
						    			"<li>折让金		<font id='changeMoneypde'>0.00</font></li>"+
						    			"<li>结算金额	<font id='sumMoneypde'>0.00</font></li>"+
						    		'</div>'+
					    		'</div>'+
						    	  	"<span style='margin-right: 20%;margin-left: 25%;'>" +
						    	  		"<input type='radio' name='jiajian' value='1'>加价" +
						    	  	"</span>" +
						    	  	"<span>" +
						    	  		"<input type='radio' checked='checked' name='jiajian' value='2'>降价" +
						    	  	"</span>" +
						    "</div>",
						   listeners : {
							   	resize:function(){
							   		Ext.Ajax.request({ 
							               url: __ctxPath+'/creditFlow/financingAgency/orStartTransferPlBidSale.do',
							                  params: {
											    isMobile : "1",
											    bidInfoId:data.bidInfoID,
											    yearAccrualRate:data.yearAccrualRate,
											    intentDate:data.intentDate,
											    startDate:data.startDate,
											    type:"transfer",
									        },
							               success : function(response) {
							            		    var PL = Ext.util.JSON.decode(response.responseText);
							            		    var PlSalebid=PL.data;
							            		    var accrualMoney=PL.accrualMoney;				//年化收益率
							            		    var afterInterest=PL.afterInterest;				//年化收益率
							            		    var days=PL.days;								//天数
							            		    var intentDate=PL.intentDate;					//到期时间
							            		    var loadMoney=PL.loadMoney;						//年化收益率
							            		    var loanInterest=PL.loanInterest;				//年化收益率
							            		    var newDate=PL.newDate;							//当前截至时间
							            		    var notLoanInterest=PL.notLoanInterest;			//年化收益率
							            		    var startDate=PL.startDate;						//开始时间
							            		    var sumInteresteMoney=PL.sumInteresteMoney;		//年化收益率
							            		    var yearAccrualRate=PL.yearAccrualRate; 		//年化收益率
							            		    var saleMoney=PL.saleMoney;				//年化收益率
							            		    churangmoney=saleMoney;
							            		    qianshoumoney=notLoanInterest;
							            		    
							            		    document.getElementById("afterInterestpde").innerHTML=afterInterest;
							            		    document.getElementById("newDatepde").innerHTML=newDate;
							            		    document.getElementById("yearAccrualRatepde").innerHTML=yearAccrualRate+"%";
							            		    document.getElementById("dayspde").innerHTML=days+"天";
							            		    document.getElementById("loadMoneypde").innerHTML=loadMoney+"元";
							            		    document.getElementById("loanInterestpde").innerHTML=loanInterest+"元";
							            		    document.getElementById("startDatepde").innerHTML=startDate
							            		    document.getElementById("intentDatepde").innerHTML=intentDate
							            		    document.getElementById("notLoanInterestpde").innerHTML=notLoanInterest+"元"
							            		    document.getElementById("loadMoneyoppde").innerHTML=saleMoney+"元"
							            		    document.getElementById("sumInteresteMoneypde").innerHTML=notLoanInterest+"元"
							            		    
							            		   
							            		    
							               }
							   		})
							   	}
							}
						},{
							xtype:"numberfield",
	                        label: "出让本金千分之：",
	                        labelWidth:"40%",
	                        inputCls:"loaninput",
	                        style:"padding:5px;border: 1px solid #eee;",
	                        name:"qianfenzhi",
	                    	listeners: {
		    				   	scope : this,
		    			        change: function(field, newValue, oldValue) {
		    			        	var changeMoneypde =churangmoney/1000*newValue;
		    			        	var jiajian = document.getElementsByName("jiajian");
		    			        	var jiesuanjine
		    			        	if(jiajian[0].checked){
		    			        		jiesuanjine =churangmoney+changeMoneypde+qianshoumoney;
		    			        		document.getElementById("changeMoneypde").innerHTML=changeMoneypde+"元"
		    			        	}else{
		    			        		jiesuanjine =churangmoney-changeMoneypde+qianshoumoney;
		    			        		document.getElementById("changeMoneypde").innerHTML="-"+changeMoneypde+"元"
		    			        	}
		    			        	document.getElementById("sumMoneypde").innerHTML=jiesuanjine+"元"
		    			        }
			    			}
	                        	
		            	},{
							xtype:"label",
						    html: "<div style='height:10px;background-color: #f3f3f3'></div>"
						    
						},{	
                    	style:"margin: 20px 10px;height:44px;background:"+themeColor+";font-color:white;margin-top: 20%;",
                    	xtype: 'button',
                        text:"<font color=white>挂牌</font>",
                        handler:this.plsaleguapai
					   }
                 ]
	    	          
          
	            
	          
	        
            
        });
    
        
        this.callParent([config]);
	},
//	changeMoneyRate:0
//	changeMoneyType:1
	plsaleguapai:function(){
		var changeMoneyType;
		var changeMoneyRate=this.parent.getCmpByName("qianfenzhi").getValue();
		if(document.getElementsByName("jiajian")[0].checked){
			changeMoneyType=1;
		}else{ 		changeMoneyType=0;	}
		
		var url = __ctxPath + '/creditFlow/financingAgency/saveorStartTransferPlBidSale.do?startDate='+data.startDate+'&bidInfoId='+data.bidInfoID+'&changeMoneyRate='+changeMoneyRate+'&changeMoneyType='+changeMoneyType;
		window.open(url,"_blank");
	}
});

