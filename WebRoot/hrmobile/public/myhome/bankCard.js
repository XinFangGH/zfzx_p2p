	var disopen=null;
	var message;
	var cardNoId;
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.bankCard', {
    extend: 'Ext.Container',
    name: 'bankCard',
    constructor: function (config) {
	var bankcardtrue = "<div class='bank-bar clearfix bank-zhaoh'>"+
				            "<div class='fl bank-ico' style='background:#f86963; border: none;'>"+
//				                "<img src='hrmobile/resources/images/bank/zhaoshang.png'>"+
				            "</div>"+
				            "<div class='fl bank-txt'>"+
				             "<h1 class='bank-name' id='bank_name'></h1>"+
				             "<p class='bank-style'>储蓄卡</p>"+
				             "<p class='bank-num' id='bank_num'></p>"+
				            "</div>"+
				         "</div>";
    	config = config || {};
    	Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>银行卡设置</font>",
    		width:"100%",
		    height:"100%",
			style:"margin-top:42px;",
		    fullscreen: true,
		    scrollable:{
		    	direction: 'vertical'
		    },
		    
		    items: [
                {   id:'aaaaa'
                    
                }
               ,{
                    	style:"background:#ffffff;font-color:white",
                    	cls:'add-bank clearfix',
                        align: 'center',
                    	xtype: 'button',
                    	text:"<font class='fl add-bank-ico'>+</font>",
                        text:"<font color=ccc style='font-size:18px;font-weight: bold;display:inline-block;padding: 0 10px;'>添加银行卡</font>",
                        handler:this.submit
                }
                ,{
                    style:"background:#ffffff;font-color:white",
                    cls:'delete-bank clearfix',
                    align: 'center',
                    xtype: 'button',
                    text:"<font class='fl add-bank-ico'>+</font>",
                    text:"<font color=ccc style='font-size:18px;font-weight: bold;display:inline-block;padding: 0 10px;'>解绑银行卡</font>",
                    handler:this.submit_del
                }

                ],
		    	listeners :{
		    		activate : function(this1, eOpts ){
			    	    Ext.Ajax.request({
			    	    	url : __ctxPath + '/financePurchase/getBindBankListFinancePurchase.do',
								params:{
								   isMobile:'1'
								},
					              success : function(response) {
					    	          var responseText = Ext.util.JSON.decode(response.responseText);
						    	          if(responseText.success==true){
									          var responseText1=responseText.data;
									          if(responseText1.length){
                                                  var bankname=responseText1[0].bankname;
                                                  var banknum =isSubName(responseText1[0].cardNum, 2, 12);
                                                  cardNoId =responseText1[0].cardId;
                                                  // var banknum =responseText1[0].cardNum;

                                                  if(!Ext.isEmpty(banknum)){
                                                      document.getElementById("aaaaa").innerHTML =bankcardtrue;
                                                      document.getElementById("bank_name").innerHTML =bankname;
                                                      document.getElementById("bank_num").innerHTML =banknum;
                                                  }
                                                  $('.add-bank').hide();
											  }else{
									          	$('.delete-bank').hide();
											  }
						    	         }else{
						    	        	 message=responseText.message;
						    	        	 Ext.Msg.alert("",responseText.message);
						    	        	 disopen=1;
						    	        	 return;
						    	         }
		                       }
			
	                      }); 
	     	    			
		    		},
					painted:function(){
                        $('#ext-button-3').addClass('wyjk_select').removeClass('wyjk');
                        $('#ext-button-1').removeClass('home_select').addClass('home');
                        $('#navbar').css({"position":"fixed","width":"100%","z-index":"9"});
					}
		    
		    	}
	    })

  

    	this.callParent([config]);
    },
	submit:function(){
		if(!disopen){
    		window.open( __ctxPath +"/pay/bindCardPay.do?mobile=1&isMobile=1&backpath=hrmobile.public.myhome.bankCard");
		}else{
			Ext.Msg.alert("",message);
			return;
		}
	},
    submit_del:function(){
        if(!disopen){
    	window.open( __ctxPath +"/pay/cancelBindCardPay.do?mobile=1&isMobile=1&cardNoId="+cardNoId+"&backpath=hrmobile.public.myhome.bankCard");
        }else{
            Ext.Msg.alert("",message);
            return;
        }
    }
});
