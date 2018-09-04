
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.newsDetails', {
    extend: 'Ext.Container',
    name: 'news',
    constructor: function (config) {
    var	topcommmon= Ext.create('hrmobile.public.myhome.related.topcommmon',{
    });
   /* var bottomBar= Ext.create('hrmobile.public.myhome.related.bottomBar',{
    });*/
    	config = config || {};
    	var data=config.data;
    	Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>站内消息详情</font>",
		    fullscreen: true,
		    scrollable:{
		    	direction: 'vertical'
		    },
		    items: [topcommmon,
		            {
		    		xtype: 'fieldset',
		            items:[{
		            	html:"<div class='news_title'>"+
								"<h1>"+data.title+"！</h1>"+
								"<table border='0'>"+
									"<tr>"+
										"<td align='left'>发送人：邦理财</td>"+
										"<td align='right'>发送时间："+data.sendTime+"</td>"+
									"</tr>"+
								"</table>"+
							"</div>"+
							"<div class='news_details1'>"+data.content
							+
							"<br />"+
							"</div>"
		            },
		            {
		            defaults: {
		                xtype: 'button',
		                margin: 5
		            },
		            layout: {
						type: 'hbox',
						align: 'middle'
					}/*,
		            items:[
		            	 {
		                	style:"padding-top:10px;background:"+themeColor+";font-color:white",
		                    text:"<font color=white>返回</font>",
		                    handler:this.submit
		            	 },
		            	 {
		                 	style:"padding-top:10px;background:"+themeColor+";font-color:white",
		                    text:"<font color=white>删除</font>",
		                    handler:this.submit
		                    
		                 }
		            ]*/
		            }
		            ]}/*,
		            bottomBar*/]
    	});

  

    	this.callParent([config]);
    	
    }

});
