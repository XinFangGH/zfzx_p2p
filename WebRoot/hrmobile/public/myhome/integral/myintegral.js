
//ExtUD.Ext.BidPlanFinanceInfo
Ext.define('hrmobile.public.myhome.integral.myintegral', {
    extend: 'Ext.Container',
    name: 'myintegral',
     constructor:function(config){
		var username = config.username;
		var userId=config.userId;
		Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>会员积分</font>",
			name:'menu',
			scrollable: {
    	        direction: 'vertical'
    	    },
			items:[
		       {
				xtype: 'container',
                items: [{
                	xtype: 'label',
                    html: '<body>'+
						    '<div class="scoreTop">'+
						        '<div class="peoScore">'+
						            '<dl>'+
						                '<dt><img src="hrmobile/resources/imageMain/headIcon.png" alt=""></dt>'+
						                '<dd>'+
						                    '<div>'+
						                        '<span>'+curUserInfo.truename+'</span>'+
						                        '<a>LV3</a>'+
						                        '<i>铜牌会员</i>'+
						                        '<b><img src="hrmobile/resources/imageMain/camera.png" alt=""></b>'+
						                    '</div>'+
						                    '<p>还差53分升级到4级会员 <span>'+curUserInfo.score+'/80</span></p>'+
						                    '<p class="posBot"><i></i></p>'+
						                '</dd>'+
						            '</dl>'+
						        '</div>'+
						        '<div class="signIn">'+
//						                '<span>签到</span>'+
						                '<div>'+
						                     '<p>已连续签到13天</p>'+
						                    '<p>明天继续到将获得 10 点积分</p>'+
						                '</div>'+
						        '</div>'+
						    '</div>'+
						    '<div class="myScore">'+
						        '<dl class="scoreShow" style="border-right: 1px solid #dbdbdb;">'+
						            '<dt>'+
						                '<img src="hrmobile/resources/imageMain/totalScore.png" alt="">'+
						            '</dt>'+
						            '<dd>'+
						                '<p>积分  180</p>'+
						                '<span>如何获取积分？</span>'+
						            '</dd>'+
						        '</dl>'+
						        '<dl class="scoreShow">'+
						            '<dt>'+
						                '<img src="hrmobile/resources/imageMain/searchScore.png" alt="">'+
						            '</dt>'+
						            '<dd onclick="javascript:getjifen();">'+
						                '<p>查看积分</p>'+
						                '<p>获取记录</p>'+
						            '</dd>'+
						        '</dl>'+
						    '</div>'+
						    '<ul class="score">'+
						        '<li class="hasArrow">积分兑换</li>'+
						        '<li>可用积分 <span><a>3000</a>分</span></li>'+
						        '<li>累计积分 <span><a>7000</a>分</span></li>'+
						        '<li>已用积分 <span><a>5000</a>分</span></li>'+
						    '</ul>'+
						'</body>'
                }]
		       }
			]
		});
		
		this.callParent([config]);
		getjifen=function(){
			mobileNavi.push(Ext.create('hrmobile.public.myhome.integral.membership',{}));
		}
//		Ext.Ajax.request({ 
//               url: __ctxPath+'/user/safeWebBonusRecordAction.do',
//                  params: {
//				    isMobile : "1"
//		        },
//               success : function(response) {
//            		    var responseText = Ext.util.JSON.decode(response.responseText);
//				    }
//			});
		
    }

});