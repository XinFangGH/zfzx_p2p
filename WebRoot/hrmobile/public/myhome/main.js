var curUserInfoo,
	isCheckCard,
	flag;
var amoney;
/**
 * 手机功能菜单
 * by cjj
 */

Ext.define('hrmobile.public.myhome.main', {
    extend: 'Ext.Container',
    name:'main',
    alias: 'widget.main',
    constructor:function(config){
		
		var username = config.username;
		var userId=config.userId;
		Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>我的账户</font>",
			name:'menu',
			style:'margin-bottom:55px;',
			scrollable: {
    	        direction: 'vertical'
    	    },
				xtype: 'container',
                items: [{
                	xtype: 'label',
                    html: '<header class="header-bg my_header" style="color:#fff;opacity:0.77">'+
							/*'<div class="header-top">'+
								'<div class="uesr-name">'+curUserInfo.loginname+'</div>'+
								'<div class="uesr-icon">'+
									'<a onclick="javascript:information();" class="fl mr10 mt1"><img src="hrmobile/resources/imagesP2p/main/messg-icon.png" width="22" height="17;margin-right:5px;"></a>'+
//									'<a href="#" class="fl "><img src="hrmobile/resources/imagesP2p/main/data-icon.png" width="17" height="17"></a>'+
								'</div>'+
							'</div>'+*/
							'<div class="my_htop">' +
								'<div class="uesr-icon">'+
									'<a onclick="javascript:information();" class="fl mr10 mt1"><img src="hrmobile/resources/mypic/messg-icon.png" width="15px" height="17px"></a>'+
			//									'<a href="#" class="fl "><img src="hrmobile/resources/imagesP2p/main/data-icon.png" width="17" height="17"></a>'+
								'</div>'+
								'<div class="user-num">'+
									'<p class="pt"><i class="icon"></i>账户可用余额</p>'+
									'<p class="pb"><span id="moneyky">0.00</span>元</p>'+
								'</div>'+
								'<div class="pos_a">' +
                   					 '<a class="cz" onclick="javascript:personals(1,0);">充值</a>'+
                   					 '<a class="tx" onclick="javascript:personals(1,1);">提现</a>'+
								'</div>'+
							'</div>'+
                    		'<p class="line"></p>'+
							'<div class="data-box">'+
								'<div class="fl data-time txtc white">'+
									'<p class="pt">账户总资产（元）</p>'+
									'<p class="pb" id="moneyall">0.00</p>'+
								'</div>'+
								'<p class="hline fl"></p>'+
								'<div class="fr data-time txtc white">'+
									'<p class="pt">累计收益（元）</p>'+
									'<p class="pb" id="moneysy">0.00</p>'+
								'</div>'+
							'</div>'+
						'</header>'+
						'<nav class="member-nav my-member-nav">'+
					        '<ul>'+
								'<li   onclick="javascript:personals(2);"><i class="icon icon1"></i><c>账户总览</c><i class="icon-angle-right icon-large"></i></li>'+
								'<li   onclick="javascript:personals(14);"><i class="icon icon2"></i><c>资金明细</c><i class="icon-angle-right icon-large"></i></li>'+
								'<li   onclick="javascript:personals(13);"><i class="icon icon3"></i><c>我的银行卡</c><i class="icon-angle-right icon-large"></i></li>'+
					            '<li   onclick="javascript:personals(3);"><i class="icon icon4"></i><c>散标出借</c><i class="icon-angle-right icon-large"></i></li>'+
//					        	'<li   onclick="javascript:personals(11);"><i class="icon-trophy icon-meb"></i><c>理财计划</c><span class="assets-green" style="font: 14px/52px ;position: absolute;right: 10%;"></span><i class="icon-angle-right icon-large"></i></li>'+
//					            '<li   onclick="javascript:personals(4);"><i class="icon-money icon-meb"></i><c>债权交易</c><i class="icon-angle-right icon-large"></i></li>'+
					            /*'<li   onclick="javascript:personals(5);"><i class="icon-bar-chart icon-meb"></i><c>我的借款</c><i class="icon-angle-right icon-large"></i></li>'+*/
//					            '<li   onclick="javascript:personals(6);"><i class=" icon-folder-close-alt icon-meb"></i><c>会员积分</c><i class="icon-angle-right icon-large"></i></li>'+
					            '<li   onclick="javascript:personals(7);"><i class="icon icon5"></i><c>优惠券</c><i class="icon-angle-right icon-large"></i></li>'+
					            '<li   onclick="javascript:personals(8);"><i class="icon icon6"></i><c>邀请码</c><i class="icon-angle-right icon-large"></i></li>'+
					           /* '<li   onclick="javascript:personals(8);"><i class="icon icon6"></i><c>我的邀请</c><i class="icon-angle-right icon-large"></i></li>'+*/
					        	'<li   onclick="javascript:personals(12);"><i class="icon icon8"></i><c>账号安全</c><i class="icon-angle-right icon-large"></i></li>'+
					        '</ul>'+
						'</nav>'
                }],
                listeners : {
                	show: function () {
                		// 可用余额
						Ext.Ajax.request({
						    url: __ctxPath+'/user/isSessinonValidBpCustMember.do',
					        method: 'Get',
					    	success : function(response) {
					        	var obj = Ext.util.JSON.decode(response.responseText);
					        	
					        	if(obj.success == true){
					        		// 全局变量保存数据,好及时更新数据
					        		curUserInfoo = obj.data;
					        		
									// 判断是否开通存管用户账号
									isCheckCard = curUserInfoo.isCheckCard == '1' ? true : false;
									
									// 设置localStroage是否实名认证
									localStorage.setItem('isCheckCard',isCheckCard);
					        	}
					        }
						});
                	},
					painted:function(){
                        $('#ext-button-3').addClass('wyjk_select').removeClass('wyjk');
                        $('#ext-button-1').removeClass('home_select').addClass('home');
					}
                	
                }
		});
		
		this.callParent([config]);

		// 全局获取金额		
		Ext.Ajax.request({
		      url: __ctxPath + '/user/mobileaccountBpCustMember.do',
		      params: {
		          isMobile: "1"
		      },
		      success: function(response) {
		          var responseText = Ext.util.JSON.decode(response.responseText);
		          // 账户可用金额
		          var money = responseText.money;
                  amoney = money;
		
		          // 冻结资金总额
		          var money1 = responseText.money1; 
		
		          // 待收投资总额第一部分：散标投资的本金和利息
		          var money3 = responseText.money3; 
		
		          // 第二部分：理财计划的本金和利息
		          var money4 = responseText.money4;
		
		          // 待还借款总额
		          var money5 = responseText.money5;
		
		          // 累计投资总额
		          var money6 = responseText.money6;
		
		          // 累计投资总额
		          var money7 = responseText.money7;
		
		          // 预期待收收益
		          var money8 = responseText.money8; 
		
		          // 预期待收收益
		          var money9 = responseText.money9;
		
		          // 上月到账收益
		          var money10 = responseText.money10;
		
		          // 累计回收本金
		          var money11 = responseText.money11;
		
		          // 累计到账收益
		          var money12 = responseText.money12;
		
		          // 还款中借款笔数
		          var count = responseText.count;
		
		          // 招标中借款笔数
		          var count1 = responseText.count1;
		
		          // 待回款投资笔数
		          var count2 = responseText.count2;
		
		          // 待回款投资笔数
		          var count3 = responseText.count3;
		
		          // 投标冻结中笔数 
		          var count4 = responseText.count4;
		
		          // 投标冻结中笔数
		          var count5 = responseText.count5;
		              money3 = money3 + money4;
		              money6 = money6 + money7;
		              money8 = money8 + money9;
		              count2 = count2 + count3;
		              count4 = count4 + count5;
		
		          var moneyall = money + money3 + money1 - money5;
					
		          // 可用余额
		          $('#moneyky').html(moneyFormat(money));
		          
		          // 净资产
		          $('#moneyall').html(moneyFormat(moneyall));
		          
		          // 累计收益
		          $('#moneysy').html(moneyFormat(money12));
		          
//		           		document.getElementById("count").innerHTML =count;
//                        document.getElementById("count1").innerHTML =count1;
//                        document.getElementById("count2").innerHTML =count2;
//                        document.getElementById("count4").innerHTML =count4;
//                        document.getElementById("money1").innerHTML =moneyFormat(money1);
//                        document.getElementById("money3").innerHTML =moneyFormat(money3);
//                        document.getElementById("money5").innerHTML =moneyFormat(money5);
//                        document.getElementById("money6").innerHTML =moneyFormat(money6);
//                        document.getElementById("money8").innerHTML =moneyFormat(money8);
//                        document.getElementById("money10").innerHTML =moneyFormat(money10);
//                        document.getElementById("money11").innerHTML =moneyFormat(money11);
//                        document.getElementById("money12").innerHTML =moneyFormat(money12);
		
		
		      }
		  });
		

		// 跳转列表页
		personals = function(data,flag) {
			
			
			if(data == 1 && flag == 1 ){

                Ext.Ajax.request({
                    url: __ctxPath + '/user/getTrueMoneyBpCustMember.do',
                    params: {
                        isMobile: "1"

                    },
                    method: 'GET',
                    async: true,
                    success: function (response) {
                        // 解析json串为对象
                        var responseText = Ext.util.JSON.decode(response.responseText);
                        if (responseText.trueMoney > 0 ){
                            mobileNavi.push(Ext.create('hrmobile.public.myhome.rechargeAndwithdraw',{
                                isCheckCard : isCheckCard,
                                flag:flag
                            }));
						}else {
                            Ext.Msg.alert('温馨提示','您当前账户可提现金额为:'+responseText.trueMoney+'元，'+amoney+'元为当日充值或当日到账金额，可在1-2个工作日后(节假日顺延)进行提现')
                        }
                    }
                })





				// 判断是否实名认证
				/*if (isCheckCard == false) {
	          		Ext.Msg.alert('提示','您尚未进行实名认证!',function () {
	          			mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
	          			}
	          		);
			   } else { // 如果实名认证了,跳转页面
			        mobileNavi.push(Ext.create('hrmobile.public.myhome.rechargeAndwithdraw',{
			        	isCheckCard : isCheckCard
			        	
			        }));
			    }*/
			}else if(data == 1 && flag==0){
                mobileNavi.push(Ext.create('hrmobile.public.myhome.rechargeAndwithdraw',{
                    isCheckCard : isCheckCard,
                    flag:flag
                }))

            }else if(data==2){
				// 判断是否实名认证
				if (isCheckCard == false) {
	          		Ext.Msg.alert('提示','您尚未进行实名认证!',function () {
	          			mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
	          			}
	          		);
			   } else { // 如果实名认证了,跳转页面
			        mobileNavi.push(Ext.create('hrmobile.public.myhome.account',{}));
			    }
				
			}else if(data==3){
				// 判断是否实名认证
				if (isCheckCard == false) {
	          		Ext.Msg.alert('提示','您尚未进行实名认证!',function () {
	          			mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
	          			}
	          		);
			   } else { // 如果实名认证了,跳转页面
			        mobileNavi.push(Ext.create('hrmobile.public.myhome.planbid.basicinformation',{}));
			    }
			}else if(data==11){
				// 判断是否实名认证
				if (isCheckCard == false) {
	          		Ext.Msg.alert('提示','您尚未进行实名认证!',function () {
	          			mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
	          			}
	          		);
			   } else { // 如果实名认证了,跳转页面
			        mobileNavi.push(Ext.create('hrmobile.public.myhome.planmplan',{}));
			    }
			}else if(data==4){
				// 判断是否实名认证
				if (isCheckCard == false) {
	          		Ext.Msg.alert('提示','您尚未进行实名认证!',function () {
	          			mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
	          			}
	          		);
			   } else { // 如果实名认证了,跳转页面
			        mobileNavi.push(Ext.create('hrmobile.public.myhome.plandebts',{}));
			    }
			}else if(data==5){
				// 判断是否实名认证
				if (isCheckCard == false) {
	          		Ext.Msg.alert('提示','您尚未进行实名认证!',function () {
	          			mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
	          			}
	          		);
			   } else { // 如果实名认证了,跳转页面
			        mobileNavi.push(Ext.create('hrmobile.public.myhome.myloan.myloan',{}));
			    }
			}else if(data==8){
				// 判断是否实名认证
				if (isCheckCard == false) {
	          		Ext.Msg.alert('提示','您尚未进行实名认证!',function () {
	          			mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
	          			}
	          		);
			   } else { // 如果实名认证了,跳转页面
			        mobileNavi.push(Ext.create('hrmobile.public.myhome.invitation.myinvitation',{}));
			    }
			}else if(data==6){
				// 判断是否实名认证
				if (isCheckCard == false) {
	          		Ext.Msg.alert('提示','您尚未进行实名认证!',function () {
	          			mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
	          			}
	          		);
			   } else { // 如果实名认证了,跳转页面
			        mobileNavi.push(Ext.create('hrmobile.public.myhome.integral.myintegral',{}));
			    }
			}else if(data==13){
				// 判断是否实名认证
				if (isCheckCard == false) {
	          		Ext.Msg.alert('提示','您尚未进行实名认证!',function () {
	          			mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
	          			}
	          		);
			   } else { // 如果实名认证了,跳转页面
			        mobileNavi.push(Ext.create('hrmobile.public.myhome.bankCard',{}));
			    }
			}else if(data==14){
				// 判断是否实名认证
				if (isCheckCard == false) {
	          		Ext.Msg.alert('提示','您尚未进行实名认证!',function () {
	          			mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
	          			}
	          		);
			   } else { // 如果实名认证了,跳转页面
			        mobileNavi.push(Ext.create('hrmobile.public.myhome.fundDetails',{}));
			    }
			}else if(data==7){
				// 判断是否实名认证
				if (isCheckCard == false) {
	          		Ext.Msg.alert('提示','您尚未进行实名认证!',function () {
	          			mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
	          			}
	          		);
			   } else { // 如果实名认证了,跳转页面
			        mobileNavi.push(Ext.create('hrmobile.public.myhome.coupon.coupon',{}));
			    }
			}else if(data==12){
				Ext.Ajax.request({
					url: __ctxPath +"/creditFlow/financingAgency/automaticbidPlBidAuto.do",
					 params : {
						isMobile : "1"
			         },
				    success : function(response) {
				    	var  responseText1=response.responseText.replace(/[\n]/ig,'');
				    	var responseText = Ext.util.JSON.decode(responseText1);
				    	var bidauto=responseText.data;
					    mobileNavi.push(Ext.create('hrmobile.public.myhome.safety',{bidauto:bidauto}));
    	            }});
				
			}
		  }
	 information = function() {
		 mobileNavi.push(Ext.create('hrmobile.public.myhome.news',{}));
	 }
		
    }

});