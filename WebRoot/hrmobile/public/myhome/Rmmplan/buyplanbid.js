var  width=100;
var  stateflag=false;
var Rmmdata;
Ext.define('hrmobile.public.myhome.Rmmplan.buyplanbid', {
	extend: 'Ext.Panel',
    name: 'buyplanbid',
    constructor: function (config) {
		config = config || {};
		Rmmdata=config.Rmmdata;
	    Ext.apply(config,{
        	title:'<font color=#ffffff style="font-size:17px">'+Rmmdata.bidProName+'</font>',
            width:"100%",
			height:"100%",
		    listPaging: true,
			scrollable:{
			direction: 'vertical'
			},
		            items: [{
		            	xtype:"label",
		            	html: 
		            		"<div class='allplan' style='background: #fff;'>"+
						    		"<div>"+
						    			"<li style='padding:4%;'>截止至<span id='newDate'></span>日，债权交易信息如下</li>"+
						    			"<span>债权信息</span>"+
						    		"</div>"+
						    		"<div style='width:100%;padding: 10px 0;overflow: hidden;'>"+
							    		'<div class="planleft">'+
							    			"<li>年化收益 	<font id='yearAccrualRate'>12%</font></li>"+
							    			"<li>已收本金	<font id='accrualMoney'>0.00</font></li>"+
							    			"<li>未收本金	<font id='loadMoney'>0.00</font></li>"+
							    			"<li>已付收益	<font id='afterInterest'>0.00</font></li>"+
							    			"<li>未付收益	<font id='notLoanInterest'>0.00</font></li>"+
							    		'</div>'+
							    		'<div class="planreight">'+
							    			"<li>期限		<font id='days'>0天</font></li>"+
							    			"<li>总金额		<font id='saleMoney'>1000元</font></li>"+
							    			"<li>总收益		<font id='loanInterest'>0.00元</font></li>"+
							    			"<li>起始日		<font id='startDate'></font></li>"+
							    			"<li>到期日		<font id='intentDate'></font></li>"+
							    		'</div>'+
						    		'</div>'+
						    		
						    		
						     "<div>"+
					    			"<li style='padding:2%;border-top:1px solid #eee;'></li>"+
					    			"<span>交易结算清单</span>"+
					    		"</div>"+
					    		"<div style='width:100%;padding: 10px 0;overflow:hidden;'>"+
					    			"<div class='plancenter'>"+
						    			"<li>可出让本金 	<font id='loadMoneyop'>0.00</font></li>"+
						    			"<li>欠收利息	<font id='sumInteresteMoney'>0.00</font></li>"+
						    			"<li>折让金		<font id='changeMoney'>0.00</font></li>"+
						    			"<li>折让率		<font id='changeMoneyRate'>0.00</font></li>"+
						    			"<li>结算金额	<font id='sumMoney'>0.00</font></li>"+
						    		'</div>'+
					    		'</div>'+
						    "</div>",
						   listeners : {
							   	resize:function(){
							   		Ext.Ajax.request({ 
							               url: __ctxPath+'/creditFlow/financingAgency/orStartTransferPlBidSale.do',
							                  params: {
											    isMobile : "1",
											    bidInfoId:Rmmdata.bidInfoID,
											    yearAccrualRate:Rmmdata.yearAccrualRate,
											    intentDate:Rmmdata.intentDate,
											    startDate:Rmmdata.startDate,
											    type:"buy",
											    saleId:Rmmdata.id,
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
							            		    var saleMoney=PlSalebid.saleMoney;				//年化收益率
							            		    var changeMoney=PlSalebid.changeMoney;			//折让金
							            		    var changeMoneyRate=PlSalebid.changeMoneyRate;	//年化收益率
							            		    var sumMoney=PlSalebid.sumMoney;
							            		    
							            		    document.getElementById("newDate").innerHTML=newDate
							            		    document.getElementById("yearAccrualRate").innerHTML=yearAccrualRate+"%";
							            		    document.getElementById("days").innerHTML=days+"天";
							            		    document.getElementById("loadMoney").innerHTML=loadMoney+"元";
							            		    document.getElementById("loanInterest").innerHTML=loanInterest+"元";
							            		    document.getElementById("startDate").innerHTML=startDate
							            		    document.getElementById("intentDate").innerHTML=intentDate
							            		    document.getElementById("sumInteresteMoney").innerHTML=sumInteresteMoney+"元";
							            		    document.getElementById("changeMoney").innerHTML=changeMoney+"元";
							            		    document.getElementById("changeMoneyRate").innerHTML=changeMoneyRate+"%"
							            		    document.getElementById("sumMoney").innerHTML=sumMoney+"元"
							            		    
							            		    document.getElementById("notLoanInterest").innerHTML=notLoanInterest+"元"
							            		    document.getElementById("loadMoneyop").innerHTML=saleMoney+"元"
							            		    
							            		    
							            		    
							               }
							   		})
							   	}
							}
						},{
							xtype:"label",
						    html: "<div style='height:10px;background-color: #f3f3f3'></div>"
						    
						},{
	                       xtype: 'checkboxfield',
	                       style:'position: fixed;width:100%;',
	                       id:'agree1',
	                       checked:true,
	                       handler:this.agreement,
	                       html:"<div style='text-align:center;font-size: 13px;position: absolute; top:17px;margin-left: 15%;'>"+
                             		 "<label>我已阅读并同意《<span  onclick='javascript:agreement();' style='color:red;'>升升投云平台服务协议</span>》</label>"+
                         		"</div>"
					   },{	
                    	style:"margin: 20px 10px;height:44px;background:"+themeColor+";font-color:white;margin-top: 20%;",
                    	xtype: 'button',
                        text:"<font color=white>购买</font>",
                        handler:this.buyPlSale
					   }
                 ]
	    	          
          
	            
	          
	        
            
        });
    
        
        this.callParent([config]);
        
	  prospective =function(){
	  var montchdata =config.data.acctulData;
	  var userMoney=document.getElementById("userMoney").value;
	  var prospective= userMoney*(interestRate/100);
	  prospective=prospective/12*montchdata;
	  prospective=Math.round(prospective*100)/100;
	  document.getElementById("aaaa").innerHTML =prospective;
     }
     agreement=function(){
     			    	this.overlay =Ext.Viewport.add({
		    		xtype:'panel',
		    		modal:true,
		    		hideOnMaskTap:true,
		    		centered:true,
		    		width:'80%',
		    		height:'500px',
		    		styleHtmlContent:false,
		    		html:superhtml1,
					items:[{
						docked:'top',
						xtype:'toolbar',
						style:"background:"+themeColor+"",
						title:'<div style="font-size:17px;color:#fff;">升升投云服务服务协议</div>'
					},{     docked:'bottom',
	                    	baseCls:"btn",
		                	xtype: 'button',
		                	margin:"10px 0px 10px",
		                	style:"background:"+themeColor+"",
		                    text:"<font color=white style='line-height:40px;'>我已同意并阅读此协议</font>",
		                    scope:this,
		                    handler: function () {
					           this.overlay.hide();
                           }
	                    }],
					scrollable:true
		    		
		    	})
		    	this.overlay.show();
     }
     borrowers=function(data){
        	if(data==1){
				    mobileNavi.push(Ext.create('hrmobile.public.myhome.personalbid.borrowers',{data:config.data}));
	      }else if(data==2){
			       	mobileNavi.push(Ext.create('hrmobile.public.myhome.personalbid.listMaterials',{listMaterials:listMaterials,listMaterialsdsb:listMaterialsdsb}));
		  }else if(data==3){
			       	mobileNavi.push(Ext.create('hrmobile.public.myhome.personalbid.auditInformation',{data:config.data}));
		  }else if(data==4){
			       	mobileNavi.push(Ext.create('hrmobile.public.myhome.personalbid.biddinglist',{data:config.data,bidId:bidId}));
		  }else if(data==5){
			        mobileNavi.push(Ext.create('hrmobile.public.myhome.personalbid.repaymentlist',{bidId:bidId}));
				    	
		  }
        };
     
	
        
	},
	buyPlSale:function(){
		var url = __ctxPath + '/creditFlow/financingAgency/buyorStartTransferPlBidSale.do?isMobile=1&bidInfoId='+Rmmdata.bidInfoID+'&saleId='+Rmmdata.id+'&startDate='+Rmmdata.startDate+'';
		window.open(url,"_blank");
	}
});

