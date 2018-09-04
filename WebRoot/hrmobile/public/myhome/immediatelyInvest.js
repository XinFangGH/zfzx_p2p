//upAndDownContract.js


/**
 * 手机功能菜单
 * by cjj
 */

Ext.define('hrmobile.public.myhome.immediatelyInvest', {
    extend: 'Ext.Container',
    name:'immediatelyInvest',
    
    constructor:function(config){
    this.data=config.data;
    var html1="<div class='ljtz'>"+
	"<table border='0'>"+
				"<tr>"+
					"<td align='left'><h2>"+ this.data.bidProName+"</h2></td>"+
				"</tr>"+
				"<tr>"+
					"<td align='right''><span class='xmbh'><font size='5px'>项目编号："+this.data.bidProNumber+"</font></span></td>"+
				"</tr>"+
			"</table>"+
	"<div class='top_left fl'>"+
		"<div class='one fl'>"+
			"<span>"+this.data.bidMoney/10000+"万</span>项目规模"+
		"</div>"+
		"<div class='two fl'>"+
			"<span>"+this.data.interestRate+" % </span>年化收益"+
		"</div>"+
		"<div class='three fl'>"+
			"<span>"+this.data.loanLife+"</span>项目期限"+
		"</div>"+
		"<div class='four fl'>"+
			"<canvas id='bgabc"+this.bidId+"' width='59' height='59'></canvas>"+
		"</div>"+
		"<div class='cb'></div>"+
	"</div>"+
	"<div class='top_right fr'>"+
		"<div class='percentBox'>"+
			"<div id='bg'></div>"+
			"<!-- 承载进度文字 -->"+
			"<div id='txt' class='pertxt'></div>"+
		"</div>"+
	"</div>"+
	"<div class='cb'></div>"+
	"<div class='details'>"+
		"<table border='0'>"+
			"<tr>"+
				"<td width='8%' align='center'><img src='hrmobile/resources/imagesP2p/icon_qtje.png' alt='' /></td>"+
				"<td width='20%' align='left'>起投金额</td>"+
				"<td colspan='2' align='right'>"+moneyFormat(this.data.startMoney)+"元</td>"+
			"</tr>"+
			"<tr>"+
				"<td align='center'><img src='hrmobile/resources/imagesP2p/icon_ktje.png' alt='' /></td>"+
				"<td>可投金额</td>"+
				"<td colspan='2' align='right'>"+moneyFormat(this.data.afterMoney)+"元</td>"+
			"</tr>"+
			"<tr>"+
				"<td align='center'><img src='hrmobile/resources/imagesP2p/icon_zhye.png' alt='' /></td>"+
				"<td>帐户余额</td>"+
				"<td colspan='2' align='right'><span>"+moneyFormat(this.data.availableInvestMoney)+"元</span></td>"+
			"</tr>"+
			"<tr>"+
				"<td align='center'><img src='hrmobile/resources/imagesP2p/icon_tbje.png' alt='' /></td>"+
				"<td>投标金额</td>"+
				"<td width='30%' align='left'><input name='userMoney' type='text' style='width:100%' id='userMoney'/></td>"+
				"<td width='20%' align='right'><a href='#' onclick='javascript:recharge()'>立即充值</a></td>"+
			"</tr>"+
		"</table>"+
		
	"</div>"+
"</div>";
    
recharge=function(){
	mobileNavi.push(Ext.create('hrmobile.public.myhome.rechargeAndwithdraw',{
  	}));
}

// onfocus=\"textfocus(this)\" onblur=\"textblur(this)\
textfocus=function(){
/*var o=document.getElementById("userMoney");
 o.style.position = 'fixed';
        o.style.bottom = '350px';
        o.style.height='25px';
        o.style.zIndex = '1';*/
	var this1=Ext.getCmp("immediatelyInvest");
	this1.setStyle('margin-bottom:500px;');
}
textblur=function(){
/*var o=document.getElementById("userMoney");
 o.style.position = 'static';
        o.style.top = 'auto';*/
}
//document.getElementById("block").scrollIntoView();
		Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>立即投资</font>",
    		width:"100%",
		    height:"100%",
		    fullscreen: true,
		    scrollable:{
		    	direction: 'vertical'
		    },
		    style:{
				    margin:'0px 0px 900px 0px'
				},
          items:[{
	            	title: '<div style="font-size:10px;">项目信息</div>',
	                items:[{
	    	            xtype: 'panel',
	    	            items:[
		                    {docked:'bottom',
		                    	style:"background:"+themeColor+";font-color:white",
		                    	xtype: 'button',
		                    	 margin:"20px 20px 250px 20px",
		                        text:"<font color=white>立即投资</font>",
		                        scope:this,
		                        handler:this.buying
		                    },{docked:'bottom',
		                    	xtype:"label",
		                    	name:"noteimmediatelyInvest",
		                       id:"noteimmediatelyInvest",
		                        html: "  "
		                        
		                    }],
		               html:html1
	    	        }]
	            }],
	            listeners:{  
					painted : function(){
			            var canvas = document.getElementById("bgabc"+this.bidId);   
			            if(!Ext.isEmpty(canvas)){
		                   progress(this.data.progress,canvas)
			            }
				}}
            });
		
		this.callParent([config]);
		
    },
	buying:function(){
	  if(curUserInfo==null){
	       mobileNavi.push(
						Ext.create('hrmobile.public.myhome.login',{
						        	})
						    	);
	  
	  }else{
	  	 var userMoney=document.getElementById("userMoney").value;
	  	  
            var note=Ext.getCmp('noteimmediatelyInvest');
			if(Ext.isEmpty(userMoney)){
			  note.setHtml("<div style='margin:10px;'><font color='"+themeColor+"'>投标金额不能为空</font></div>");
			   return ;
			}
	  	    if(isAmountErr(userMoney)){
			  note.setHtml("<div style='margin:10px;'><font color='"+themeColor+"'>投标金额格式不对</font></div>");
			   return ;
			}
		    if(parseFloat(userMoney)>parseFloat(this.data.maxMoney)){
			   note.setHtml("<div style='margin:10px;'><font color='"+themeColor+"'>投标金额不能大于最大投资金额</font></div>");
			   return ;
		    }
			if(parseFloat(userMoney)<parseFloat(this.data.startMoney)){
			  note.setHtml("<div style='margin:10px;'><font color='"+themeColor+"'>投标金额不能小于起投金额</font></div>");
			   return ;
			}
	        if(parseFloat(userMoney)>parseFloat(this.data.availableInvestMoney)){
			  note.setHtml("<div style='margin:10px;'><font color='"+themeColor+"'>投标金额不能大于账户余额</font></div>");
			   return ;
			}
	        if(parseFloat(userMoney)>parseFloat(this.data.afterMoney)){
			  note.setHtml("<div style='margin:10px;'><font color='"+themeColor+"'>投标金额不能大于可投金额</font></div>");
			   return ;
			}
	    
	 	var url = __ctxPath + '/creditFlow/financingAgency/bidingPlBidInfo.do?isMobile=1&moneyMoreMore.isMobile=1&plBidInfo.userMoney='+userMoney+'&' +
	 			'plBidInfo.userId='+curUserInfo.id+'&plBidInfo.userName='+curUserInfo.loginname+'&plBidInfo.plBidPlan.bidId='+this.data.bidId;
		if(!Ext.isEmpty(this.data.formtoken)){
			
			 url=url+"&formtoken="+this.data.formtoken
		    }
  		 getThirdPayLink(url);
//    Ext.Ajax.request({
//					url: url,
//					 params : {
//					 	isMobile:"1"
//					 	
//			         },
//				    success : function(response) {
//			        	var responseText = Ext.util.JSON.decode(response.responseText);
//			        	if(responseText.result==1){
//			        		Ext.Msg.alert("",responseText.errMsg);
//			        	}else{
//			        		Ext.Msg.alert("",responseText.errMsg);
//			        		mobileNavi.reset();
//			        	}
//				    	
//    	            }});
	  
	   
	  }
	
	}
});





