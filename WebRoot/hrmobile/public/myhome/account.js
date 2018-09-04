
/**
 * 手机功能菜单
 * by cjj
 */
var curUserInfoo,availableInvestMoney, freezeMoney;
Ext.define('hrmobile.public.myhome.account', {
    extend: 'Ext.Container',
    name:'account',
    constructor:function(config){
    	
    	curUserInfoo = config.data;
    	
    	/*if (curUserInfo != null) {
    		// 判断 账户可用金额
    		if (curUserInfoo.freezeMoney == 0) {
    			freezeMoney = '0.00';
    		} else {
    			if (curUserInfo.curUserInfoo < 1000) {
    				freezeMoney = curUserInfoo.freezeMoney;
    			} else {
    				freezeMoney = curUserInfoo.freezeMoney + '.00';
    			}
    		}
    		
    		// 判断 投标冻结金额
    		if (curUserInfoo.availableInvestMoney == 0) {
    			availableInvestMoney = '0.00';
    		} else {
    			if (curUserInfoo.availableInvestMoney < 1000) {
    				availableInvestMoney = curUserInfoo.availableInvestMoney;
    			} else {
    				availableInvestMoney = curUserInfoo.availableInvestMoney + '.00';
    			}
    		}
    	}*/

    	
    	// 设置样式
    	var paddingRight = 'padding-right:15px;';
		Ext.apply(config,{
			title:"<font color=" + topColor + " style='font-size:" + topsize + "'>资金总览</font>",
			name:'menu',
			style:"margin:42px 0 52px;",
			scrollable: {
    	        direction: 'vertical'
    	    },
			items:[
		       {
				xtype: 'container',
                items: [{
                	xtype: 'label',
                    html: '<body class="fill">'+
                    			'<div style="font-size:16px;color:#4a4a4a;font-weight:bold' +
	                    				/*listColor + */
	                    			 ';"><div style=" margin:10px">资产信息</div></div>'+
							    '<nav class="assets-nav assets1-top acc_my_fill">'+
							        '<ul>'+
							            '<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">账户可用金额</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<span id="money1">' +
							            		'<label  class="assets-red" id="availableBalance">' +
//							           				 availableInvestMoney +
							            		'</label> 元</span>' +
							            	'</span>' + 
							            '</li>'+
							            '<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">投标冻结金额</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="FrozenBalance">' +
//							            			freezeMoney +
							            		'</label>' + 
							            	' 元</span>' + 
							            '</li>'+
							            '<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">待收投资总额</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="money3">0.00</label>' +
							            	' 元</span>' + 
							            '</li>'+
							            '<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">待还借款总额</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="money4">0.00</label>' +
							            	' 元</span>' + 
							            '</li>'+
							            /*'<li><span class="assets-name left">应还借款总额</span><span class="assets-money right moneyregiht">￥<label class="assets-red" id="money22">0.00</label></span></li>'+*/
							            '<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">账户净资产</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="money5">0.00</label>' +
							            	' 元</span>' + 
							            '</li>'+
							        '</ul>'+
							    '</nav>'+
							    '<div style="font-size:16px;color:#4a4a4a;font-weight:bold' +
	                    				/*listColor + */
	                    			 ';">' + 
							    	'<div style=" margin:10px">交易信息</div>' + 
							    '</div>'+
							    '<nav class="assets-nav assets1-top acc_my_fill">'+
							        '<ul>'+
							            '<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">累计投资总额</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="money6">0.00</label>' +
							            	' 元</span>' + 
							            '</li>'+
							       		'<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">投标冻结中笔数</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="money7">0.00</label>' +
							            	' 笔</span>' + 
							            '</li>'+
							       		'<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">待回款投资笔数</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="money8">0.00</label>' +
							            	' 笔</span>' + 
							            '</li>'+
							            '<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">招标中借款笔数</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="money9">0.00</label>' +
							            	' 笔</span>' + 
							            '</li>'+
							       		'<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">还款中借款笔数</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="money10">0.00</label>' +
							            	' 笔</span>' + 
							            '</li>'+
							        '</ul>'+
							    '</nav>'+
							    '<div style="font-size:16px;color:#4a4a4a;font-weight:bold' +
	                    				/*listColor + */
	                    			 ';">' + 
							    	'<div style=" margin:10px">收益信息</div>' + 
							    '</div>'+
							    '<nav class="assets-nav assets1-top acc_my_fill">'+
							        '<ul>'+
							            '<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">累计到账收益</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="money11">0.00</label>' +
							            	' 元</span>' + 
							            '</li>'+
							       		'<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">累计回收本金</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="money12">0.00</label>' +
							            	' 元</span>' + 
							            '</li>'+
							       		'<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">30天内收益</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="money13">0.00</label>' +
							            	' 元</span>' + 
							            '</li>'+
							            '<li style="' + paddingRight + '">' + 
							            	'<span class="assets-name left">预期待收收益</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="money14">0.00</label>' +
							            	' 元</span>' + 
							            '</li>'+
							       		/*'<li style="' + paddingRight + '">' +
							            	'<span class="assets-name left">累计收益率</span>' + 
							            	'<span class="assets-money right moneyregiht">' + 
							            		'<label class="assets-red" id="money15" style="color:' +btnBg + ';">0.00</label>' + 
							            	'</span>' + 
							            '</li>'+*/
							        '</ul>'+
							    '</nav>'+
							'</body>'
                }]
		       }
			],
			listeners : {
				// 页面加载事件
				painted : function () {
					// 数据全部颜色
				    //$('li').find('span:eq(1)').css('color', btnBg);
				    
				    // 待收投资总额
//					$callAjaxs('/user/getMoneyInvestAllBpCustMember.do', '#money3', 'result');
//				
//					// 待还借款总额
//					$callAjaxs('/user/getMoneyDueinAllBpCustMember.do', '#money4', 'result');
//					
//					// 账户净资产
//					$callAjaxs('/user/getMoneyAccountNetassetBpCustMember.do', '#money5', 'result');
//					
//					// 累计投资总额
//					$callAjaxs('/user/getMoneyAccumulativeInvestBpCustMember.do', '#money6', 'result');
//					
//					// 投标冻结中笔数
//					$callAjaxs('/user/getCountBidFrozenBpCustMember.do', '#money7', 'result');
//					
//					// 待回款投资笔数
//					$callAjaxs('/user/getCountInvestBackBpCustMember.do', '#money8', 'result');
//					
//					// 招标中借款笔数
//					$callAjaxs('/user/getCountBidLoanBpCustMember.do', '#money9', 'result');
//					
//					// 还款中借款笔数
//					$callAjaxs('/user/getCountRepaymentLoanBpCustMember.do', '#money10', 'result');
//					
//					// 累计到账收益
//					$callAjaxs('/user/getMoneyAccumulativeIncomeBpCustMember.do', '#money11', 'result');
//					
//					// 累计回收本金
//					$callAjaxs('/user/getMoneyPreweekIncomeBpCustMember.do', '#money12', 'result');
//					
//					// 30天内收益
//					$callAjaxs('/user/getMoneyPremonthIncomeBpCustMember.do', '#money13', 'result');
//					
//					// 预期待收收益
//					$callAjaxs('/user/getMoneyExpectIncomeBpCustMember.do', '#money14', 'result');
//					
//					// 累计收益率
//					$callAjaxs('/user/getMoneyAccumulativeIncomeRateBpCustMember.do', '#money15', 'result');
                    $('#navbar').css({"position":"fixed","width":"100%","z-index":"9"});
				},
				// 页面显示事件
				show : function () {
					// 全局获取金额		
					Ext.Ajax.request({
					      url: __ctxPath + '/user/mobileaccountBpCustMember.do',
					      params: {
					          isMobile: "1"
					      },
					      success: function(response) {
					          var responseText = Ext.util.JSON.decode(response.responseText);
					          // 账户可用金额(元)
					          var money = responseText.money;
					
					          // 投标冻结金额(元)
					          var money1 = responseText.money1; 
					
					          // 待收投资总额(元) - 待收投资总额第一部分：散标投资的本金和利息
					          var money3 = responseText.money3; 
					
					          // 第二部分：理财计划的本金和利息
					          var money4 = responseText.money4;
					
					          // 待还借款总额(元)
					          var money5 = responseText.money5;
					
					          // 累计投资总额(元)
					          var money6 = responseText.money6;
					
					          // 累计投资总额(元)
					          var money7 = responseText.money7;
					
					          // 预期待收收益(元)
					          var money8 = responseText.money8; 
					
					          // 预期待收收益
					          var money9 = responseText.money9;
					
					          // 30天内收益 - 上月到账收益
					          var money10 = responseText.money10;
					
					          // 累计收益(元) - 累计回收本金
					          var money11 = responseText.money11;
					
					          // 累计到账收益(元)
					          var money12 = responseText.money12;
					
					          // 还款中借款笔数(笔)
					          var count = responseText.count;
					
					          // 招标中借款笔数(笔)
					          var count1 = responseText.count1;
					
					          // 待回款投资笔数(笔)
					          var count2 = responseText.count2;
					
					          // 待回款投资笔数(笔)
					          var count3 = responseText.count3;
					
					          // 投标冻结中笔数(笔) 一
					          var count4 = responseText.count4;
					
					          // 投标冻结中笔数(笔) 二
					          var count5 = responseText.count5;
					          
					          	 // 待收投资总额(元)
					              money3 = money3 + money4;
					              
					              // 累计投资总额(元)
					              money6 = money6 + money7;
					              
					              // 预期待收收益(元)
					              money8 = money8 + money9;
					              
					              // 待回款投资笔数(笔)
					              count2 = count2 + count3;
					              
					              // 投标冻结中笔数(笔)
					              count4 = count4 + count5;
					
							 // 账户净资产(元)
					          var moneyall = money + money3 + money1 - money5;
					          
					          // 获取累计收益率(%)
					          var rate = responseText.rate;
					          
					         // 资金信息 - 可用余额
					         $('#availableBalance').html(moneyFormat(money));
							 // 资金信息 - 投标冻结金额
							 $('#FrozenBalance').html(moneyFormat(money1));
							 // 资金信息 - 待收投资总额
							 $('#money3').html(moneyFormat(money3));
							 // 资金信息 - 待还借款总额
							 $('#money4').html(moneyFormat(money5));
							  // 资金信息 - 账户净资产
							 $('#money5').html(moneyFormat(moneyall));
							 
							  // 交易信息 - 累计投资总额
							 $('#money6').html(moneyFormat(money6));
							  // 交易信息 - 投标冻结中笔数
							 $('#money7').html(count4);
							  // 交易信息 - 待回款投资笔数
							 $('#money8').html(count2);
							  // 交易信息 - 招标中借款笔数
							 $('#money9').html(count1);
							  // 交易信息 - 还款中借款笔数
							 $('#money10').html(count);
							 
							  // 收益信息 - 累计到账收益
							 $('#money11').html(moneyFormat(money12));
							  // 收益信息- 累计回收本金
							 $('#money12').html(moneyFormat(money11));
							  // 收益信息 - 30天内收益
							 $('#money13').html(moneyFormat(money10));
							  // 收益信息 - 预期待收收益
							 $('#money14').html(moneyFormat(money8));
							  // 收益信息 - 累计收益率
							 $('#money15').html(rate);
							 
							 
							 // 可用余额
//					          $('#moneyky').html(moneyFormat(money));
					          
					          // 净资产
//					          $('#moneyall').html(moneyFormat(moneyall));
					          
					          // 累计收益
//					          $('#moneysy').html(moneyFormat(money12));
										 
					      }
					});	
					
				
				}
				
			}
		});

		
		this.callParent([config]);

		


    }
  


});