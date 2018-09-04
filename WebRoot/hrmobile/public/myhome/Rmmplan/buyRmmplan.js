var salseId,
	intentDate,
	bidInfoID,
	yearAccrualRate,
	startDate,
	bidPlanID,
	orderNo,
	saleMoney,
	sumInteresteMoney,
	money,
	changeMoneyRate,//buyRmmplan
	changeMoneyType,
	changeMoney,
	sumMoney,
	changeMoneyRate,
	bidProName,
	isList;
Ext.define('hrmobile.public.myhome.Rmmplan.buyRmmplan', {
    extend: 'Ext.Container',
    name:'buyRmmplan',
    constructor:function(config){
    		bidInfoID = config.bidInfoID;
    		yearAccrualRate = config.yearAccrualRate;
    		orderNo = config.orderNo;
    		bidPlanID = config.bidPlanID
    		salseId = config.salseId;
    		
    		// 判断是否列表点击
    		isList = config.isList;
    		
    		// 出让本金千分之
    		changeMoneyRate = config.changeMoneyRate != null ? config.changeMoneyRate : '';
    		
    		changeMoneyType = config.changeMoneyType;
    		
			// 获取项目名
    		bidProName = config.bidProName;
    		
    		// 当前可出让本金
    		saleMoney = config.saleMoney;
    		
    		
    		// 债权起始日
    		startDate = config.startDate;
    		
    		// 债权到期日
    		intentDate = config.intentDate;
    		
    		// 判断折让类型 0是+ 1是-
    		if (changeMoneyType == 0) {
    			// 获取折让金
    			changeMoney = config.changeMoney;
    		} else if (changeMoneyType == 1) {
    			// 获取折让金
    			changeMoney = config.changeMoney == 0 ? config.changeMoney : '-' + config.changeMoney;
    		}
    		
    		// 获取总结算金额
//    		sumMoney = config.sumMoney != null ? config.sumMoney : '';
    		
    		
			Ext.apply(config,{
				title:"<font color="+topColor+" style='font-size:"+topsize+"'>债权详情</font>",
				name:'menu',
				scrollable: {
	    	        direction: 'vertical'
	    	    },
				items:[{
					xtype: 'container',
	                items: [{
	                	xtype: 'label',
	                    html: '<body class="fill">'+
	                    		//'<li style="padding: 10px 0; background: white;"><span class="left biddetailleft">截止到<span id="nowdate" style="color:red;"></span>，债权交易信息如下：</span></li>'+
	                    			'<div style="font-size:17px;color:#000;color:' + listColor+ ';"><div style=" margin:10px">债权信息</div></div>'+
								    '<nav class="assets-nav assets1-top">'+
								        '<ul>'+//个人基本信息																								  bpPersionDirPro
								            '<li class="biddetailli">' +
								            	'<span class="left biddetailleft">债权原名称：</span>' +
								            	'<span class="biddetailspanreighr" id="zqymc">' +
								            	bidProName +
								            '</span>' +
								            '</li>'+
								            '<li class="biddetailli">' +
								            	'<span class="left biddetailleft">年化收益率：</span>' +
								            	'<span class="biddetailspanreighr" id="nhsyl">0%</span>' +
								            '</li>'+
								            '<li class="biddetailli">' +
								            	'<span class="left biddetailleft">债权总金额：</span>' +
								            	'<span class="biddetailspanreighr" id="zqzje" style="color:' + btnBg + '">0.00元</span>' +
								            '</li>'+
								            '<li class="biddetailli">' +
								            	'<span class="left biddetailleft">已回收本金：</span>' +
								            	'<span class="biddetailspanreighr" id="yhsbj" style="color:' + btnBg + '">0.00元</span>' +
								            '</li>'+
								            '<li class="biddetailli">' +
								            	'<span class="left biddetailleft">未回收本金：</span>' +
								            	'<span class="biddetailspanreighr" id="whsbj" style="color:' + btnBg + '">0.00元</span>' +
								            '</li>'+
								            '<li class="biddetailli">' +
								            	'<span class="left biddetailleft">债权起始日：</span>' +
								            	'<span class="biddetailspanreighr" id="zqqsr">' +
								            		startDate +
								            	'</span>' +
								            '</li>'+
								            '<li class="biddetailli">' +
								            	'<span class="left biddetailleft">债权到期日：</span>' +
								            	'<span class="biddetailspanreighr" id="zqdqr">' +
								            		intentDate +
								            	'</span>' +
								            '</li>'+
								            '<li class="biddetailli">' +
								            	'<span class="left biddetailleft">债 权 期 限：</span>' +
								            	'<span class="biddetailspanreighr" id="zqqx">0天</span>' +
								            '</li>'+
								            '<li class="biddetailli">' +
								            	'<span class="left biddetailleft">债权总收益：</span>' +
								            	'<span class="biddetailspanreighr" id="zqzsy" style="color:' + btnBg + '">0.00元</span>' +
								            '</li>'+
								            '<li class="biddetailli">' +
								            	'<span class="left biddetailleft">已支付收益：</span>' +
								            	'<span class="biddetailspanreighr" id="yzfsy" style="color:' + btnBg + '">0.00元</span>' +
								            '</li>'+
								            '<li class="biddetailli" style="margin-bottom: 0px;">' +
								            	'<span class="left biddetailleft">未支付收益：</span>' +
								            	'<span class="biddetailspanreighr" id="wzfsy" style="color:' + btnBg + '">0.00元</span>' +
								            '</li>'+
								        '</ul>'+
								    '</nav>'+
								'</body>'
	                },{
	                xtype: 'label',
	                html: '<body class="fill">'+
	                		'<div style="font-size:17px;color:#000;color:' + listColor+ ';"><div style=" margin:10px">交易结算单</div></div>'+
							    '<nav class="assets-nav assets1-top">'+
							        '<ul>'+
							            '<li class="biddetailli">' +
								            '<span class="biddetailleft left">' +
								            	'当前可出让本金：' +
								            '</span>' +
								            '<span class="biddetailspanreighr" id="dqkcrbj" style="color:' + btnBg + '">' +
								            	changeMoneyRate +
								            	'元' +
								           	'</span>' +
							            '</li>'+
							            '<li class="biddetailli">' +
								            '<span class="biddetailleft left">' +
								           		'欠收利息：' +
								            '</span>' +
								            '<span class="biddetailspanreighr" id="qslx" style="color:' + btnBg + '">' +
								           		'0.00元' +
								            '</span>' +
							            '</li>'+
							            '<li class="biddetailli">' +
								            '<span class="biddetailleft left">' +
								           		'折让金：' +
								            '</span>' +
								            '<span class="biddetailspanreighr" id="zrj" style="color:' + btnBg + '">' +
								           		changeMoney +
								           		'元' +
								            '</span>' +
							            '</li>'+
							            '<li class="biddetailli" style="margin-bottom: 0;">' +
								            '<span class="biddetailleft left" >' +
								           		'总结算金额：' +
								            '</span>' +
								            '<span class="biddetailspanreighr" id="zjsje" style="color:red;font-weight: bold;">' +
//								           		sumMoney +
								            	'元' +
								            '</span>' +
							            '</li>'+
							         '</ul>'+
							    '</nav>'+
							 '</body>'
					},{
					 xtype: 'label',
					 html: '<body class="fill">'+
							    '<nav class="assets-nav">'+
							        '<ul>'+    
							        	"<div>" +
									    	"<p style='text-align:center;padding:.7em .4em;border-bottom: 1px solid #f3f3f3;'>" +
									    	  	"<span style='margin-right:70px;' style='padding: 1rem 0;'>" +
									    	  		"<input type='radio' name='jiaqian' value='1'>降价" +
									    	  	"</span>" +
									    	  	"<span>" +
									    	  		"<input type='radio' checked='checked' name='jiaqian' value='2'>加价" +
									    	  	"</span>" +
									    	  "</p>"+
								    	  "<div>"+
							            '<li class="biddetailli">' +
							            '<span class="biddetailleft left">' +
							            	'出让本金千分之：' +
							            '</span>' +
							            '<input  value="' + changeMoneyRate + '" type="text" id="borr" disabled placeholder="0" style="border:1px solid transparent; text-indent: 5px;">' +
//							            '<input type="text" id="borr" placeholder="0" style="border:1px solid transparent;">' +
//							            '<a class="biddetailspanreighrr" onclick="JavaScript:count();">计算</a>' +
							            '</li>'+//onkeyup="javascript:count();"自动计算
							       		'<li class="biddetailli" style="margin-bottom: 0px;">' +
								       		'<span class="biddetailleft left">账户余额：</span>' +
								       		'<span class="biddetailspanreighr" id="zhanghuyue">0.00元</span>' +
							       		'</li>'+
							       		'<li class="biddetailli" style="margin-bottom: 0px;">' +
							       		'	<span class="biddetailspanreighrr" style="float: right; margin-right: 4%;" onclick="javascript:feringset()">' +
							       				'回款计划' +
							       		'	</span>' +
							       		'</li>'+
							        '</ul>'+
							    '</nav>'+
	                		'</body>'	
	                },{
                    	style:"margin: 20px 10px;height:44px;background:" + themeColor + "; font-color:white",
                    	xtype: 'button',
                        text:"<font color=white>购买</font>",
                        handler:this.buy
                    }],
					listeners:{
		                resize:function(){
		                	// 当前可出让本金
							var dqkcrbj = saleMoney; 
							// 欠收利息
							var qslx = sumInteresteMoney 
								var allMoney = moneyFormat(dqkcrbj + qslx);
//								$("#zjsje").html(allMoney + "元");
							
							// 设置截止时间
							var myDate = new Date();
//							document.getElementById("nowdate").innerHTML = myDate.toLocaleDateString();
							
							// 降价 & 加价
//       						$radioChecked('jiaqian',changeMoneyType, true);
							
						},
						show : function () {
							// 账户余额
							Ext.Ajax.request({
							    url: __ctxPath+'/user/isSessinonValidBpCustMember.do',
							    method: 'Get',
							    async: true,
								success : function(response) {
									// 转换json对象
							    	var responseText = Ext.util.JSON.decode(response.responseText);
							    	// 获取数据
							    	var data = responseText.data;
							    	
							    	// 判断是否请求成功
							    	if(responseText.success == true){
							    		// 给全局变量更新数据
										 curUserInfo = data;
										 
							    		 // 给账户余额赋值
			    		 				$('#zhanghuyue').html(moneyFormat(curUserInfo.availableInvestMoney) + '元');
							    	}
							    	
							    }
							});
						},
						

						// 页面加载事件
				    	painted : function () {
				    		// 截止到日期
				    		$('#nowdate').css('color','black');
				    		
				    		// 数据全部颜色
				    		$('li.biddetailli').find('span:eq(1)').css('color', btnBg);
				    		
//				    		console.log(config.yearAccrualRate);
//				    		console.log(config.intentDate);
//				    		console.log(config.startDate);
//				    		console.log(config.bidInfoID);
				    		
				    		// ajax请求 获取挂牌交易数据
	 						Ext.Ajax.request({
				               url: __ctxPath+'/creditFlow/financingAgency/orStartTransferPlBidSale.do',
				                  params: {
								    isMobile : "1",
								    type : "none",
								    yearAccrualRate : config.yearAccrualRate,
								    intentDate : config.intentDate,
								    startDate : config.startDate,
								    bidInfoId : config.bidInfoID
						        },
				               success : function(response) {
				               			// 将json字符串转换成对象
				            		    var responseText = Ext.util.JSON.decode(response.responseText);
				            		    
				            		    // =============↓截至日期=============
				            		    $('#nowdate').html(responseText.newDate);
				            		    
				            		    // =============↓债权信息=============
				            		    // 债权原名称
				            		    $('#zqymc').html(responseText.bidProName);
				            		    // 年化收益率
				            		    $('#nhsyl').html(responseText.yearAccrualRate + '%');
				            		    // 债权总金额
				            		    $('#zqzje').html(moneyFormat(responseText.loadMoney) + '元');
				            		    // 已回收本金
				            		    $('#yhsbj').html(moneyFormat(responseText.getMoney) + '元');
				            		    // 未回收本金--当前可本金
				            		    $('#whsbj').html(moneyFormat(responseText.saleMoney) + '元');
				            		    
				            		    // 债权起始日
				            		   /* var startDate = null;
				            		    	startDate = responseText.startDate;
				            		    	startDate = startDate.substr(0, 10);*/
				            		    
//				            		    $('#zqqsr').html(startDate);
				            		    
				            		    // 到期日
//				            		    $('#zqdqr').html(intentDate);
				            		    
				            		     // 债权期限
				            		    $('#zqqx').html(responseText.days + '天');
				            		     // 债权总收益
				            		    $('#zqzsy').html(responseText.loanInterest + '元');
				            		     // 已支付收益
				            		     $('#yzfsy').html(responseText.afterInterest + '元');
				            		  	// 未支付收益
				            		     $('#wzfsy').html(responseText.notLoanInterest + '元');
				            		     
				            		    // =============↓交易结算单=============
				            		    // 当前可出让本金
				            		     $('#dqkcrbj').html(responseText.saleMoney + '元');
				            		    // 欠收利息
				            		     $('#qslx').html(responseText.sumInteresteMoney + '元');
				            		     // 折让金
				            		     $('#zrj').html(changeMoney + '元');
				            		     
				            		    // 若是折让金是0,则加到总金额
				            		    /* if (changeMoneyType == 0) {
				            		    	sumMoney = String(changeMoney) + sumMoney;
				            		    } else { // 否则减去总金额
				            		    	// 去掉前面-号
				            		    	changeMoney = String(changeMoney).replace('-','');
				            		    	
					            		     // 总结算金额
				            		    	sumMoney =  sumMoney - String(changeMoney);
				            		    }*/
				            		     
				            		    
				            		    // 总结算金额  - 赋值
				            		    $('#zjsje').html(moneyFormat(responseText.sumMoney) + '元');
				            		     
				            		     // 降价  或 加价
	 									 $radioChecked('jiaqian', changeMoneyType, false);
	 									 
				            		     // 出让本金千分之
//				            		     $('#borr').val(responseText.changeMoneyRate);
				            		     // 账户余额
//				            		     $('#zhanghuyue').html(moneyFormat(curUserInfo.availableInvestMoney) + '元');
				            		    
				            		    // =============↓交易费用=============
				            		    // 转让服务费
				            		    $('#zrfwf').html(responseText.transferFee + '元');
				            		    
				            		    // 调用一次计算
//				            		    count();
				            		    
								    }
							});
							
							
							
				    		
				    		
				    	}
					}
			       }]
			});

		this.callParent([config]);
		
		
		
		
		// 账户余额
			Ext.Ajax.request({
			    url: __ctxPath+'/user/isSessinonValidBpCustMember.do',
			    method: 'Get',
			    async: true,
				success : function(response) {
					// 转换json对象
			    	var responseText = Ext.util.JSON.decode(response.responseText);
			    	// 获取数据
			    	var data = responseText.data;
			    	
			    	// 判断是否请求成功
			    	if(responseText.success == true){
			    		// 给全局变量更新数据
						 curUserInfo = data;
						 
						 // 给账户余额赋值
			    		 $('#zhanghuyue').html(moneyFormat(curUserInfo.availableInvestMoney) + '元');
			    	}
			    	
			    }
			});
		
		Ext.Ajax.request({
               url: __ctxPath+'/creditFlow/financingAgency/orStartTransferPlBidSale.do',
                  params: {
				    isMobile : "1",
				    type : "none",
				    yearAccrualRate : config.yearAccrualRate,
				    intentDate : config.intentDate,
				    startDate : config.startDate,
				    bidInfoId : config.bidInfoID
		        },
               success : function(response) {
            		    var responseText = Ext.util.JSON.decode(response.responseText);
            		     // 欠收利息
            		     sumInteresteMoney = responseText.sumInteresteMoney;
				    }
			});
			
			
							
		
		// 计算
		count = function ()  {
			// 出让本金千分之
			var borr = this.document.getElementById("borr").value;

			
			if(Ext.isEmpty(borr)){
					var borr1 = parseFloat(0);
			} else {
				// 转型
				var borr1 = parseFloat(borr);
			}
				
			// 当前可出让本金
			var dqkcrbj = saleMoney;
			
			// 欠收利息
			var qslx = sumInteresteMoney;
			
			 // 降价  或 加价
			var jiaqian = this.document.getElementsByName("jiaqian")
			
			// 降价
			if (jiaqian[0].checked == true) {
				// 得到转让金额
				var allmoney1 = borr1 / 1000;
				
				// 转让金额  乘 可出让本金
				var allmoney2 = dqkcrbj * allmoney1;
				
				// 折让金赋值
				var zrj = -allmoney2.toFixed(2);
				
				// 总结算金额  = (当前可出让本金  + 欠收利息 + 折让金)
				var allMoney = (dqkcrbj + qslx + zrj).toFixed(2);
				
				// 转让服务费 = (总结算金额 * 0.005 服务费)
				var zhuanrang = (allMoney * 0.005).toFixed(2);
				
				// 折让金 赋值
				document.getElementById("zrj").innerHTML = zrj + "元";
				
				// 总结算金额 赋值
				document.getElementById("zjsje").innerHTML = moneyFormat(allMoney) + "元";
				
				// 转让服务费 赋值
				$('#zrfwf').html(zhuanrang + "元");
			} else { // 加价
				// 得到转让金额
				var allmoney1 = borr1 / 1000;
				
				// 转让金额  乘 可出让本金
				var allmoney2 = dqkcrbj * allmoney1;
				
				// 折让金赋值
				var zrj = allmoney2.toFixed(2);
				
				// 总结算金额  = (当前可出让本金  + 欠收利息 + 折让金)
				var allMoney = ((dqkcrbj / 1)  + (qslx / 1) + (zrj / 1));
				
				// 转让服务费 = (总结算金额 * 0.005 服务费)
				var zhuanrang = moneyFormat(allMoney * 0.005);
				
				// 折让金 赋值
				document.getElementById("zrj").innerHTML = zrj + "元";
				
				// 总结算金额 赋值
				document.getElementById("zjsje").innerHTML = moneyFormat(allMoney) + "元";
				
				// 转让服务费 赋值
				document.getElementById("zrfwf").innerHTML = zhuanrang + "元";
			}
		}
		

		
		// 回款计划
		feringset = function () {
			// 拼接url
         	var str = bidPlanID + ",Financing," + orderNo;//拼接字符串
         	mobileNavi.push(Ext.create('hrmobile.public.myhome.Paymentplan',{
         			str:str
         		}
         	));
    	 	/*orderNo =config.orderNo;
    	 	bidPlanID =config.bidPlanID
         	var url=__ctxPath +"/user/paymentplanBpCustMember.do?str="+bidPlanID+",Financing,"+orderNo;
         	window.open(url,"_bank");*/
    	 }

    },
    
        // 立即购买
    	buy : function(){
	    	if (Ext.isEmpty(curUserInfo)) {
				mobileNavi.push(Ext.create('hrmobile.public.myhome.login', {}));
			} else {
	    		bidInfoId = bidInfoID;
			    startDate = startDate;
			    salseId = salseId;
			    
			    // 获取url数据
				var url = __ctxPath + "/creditFlow/financingAgency/buyorStartTransferPlBidSale.do" +
						"?isMobile=1" +
						"&startDate=" +
						startDate +
						"&bidInfoId=" + 
						bidInfoId + 
						"&saleId=" + 
						salseId;
			   		
	    		// Ajax get请求数据
	    		/* Ext.Ajax.request({
	    		 	url: url,
	    		 	params: {
	    		 	},
	    		 	method: 'GET',
	    		 	async: true,
	    		 	success: function (response) {
	    		 		// 解析json串为对象
	    		 		var responseText = Ext.util.JSON.decode(response.responseText);
	    		 		
	    		 		// 返回状态
	    		 		var resultStatus = responseText.success;
	    		 		
	    		 		// 返回文本
	    		 		var resultText = responseText.result;
	    		 		
	    		 		// 判断状态
	    		 		if (resultStatus == true) {
	    		 			//  提示框
	    		 			Ext.Msg.alert('提示', resultText, function () {
	    		 				
	    		 				// 如果是是从列表点击进来,直接返回
	    		 				if (isList != false) {
		    		 				// 返回上一页
		    		 				mobileNavi.pop();
	    		 				} else { // 否则从详情点进来,返回两次进列表
	    		 					// 返回上一页
		    		 				mobileNavi.pop(2);
	    		 					
	    		 				}
	    		 				
	    		 			}
	    		 			);
	    		 		}  else {
	    		 			//  提示框
	    		 			Ext.Msg.alert('提示', resultText);
	    		 		}
	    		 	}
	    		 })
	    		 
	    		 // 开启请稍等提示......
	    	 	 mobileView.setMasked(
	    	 	 	{
	    	 	 		xtype: "loadmask",
	    	 	 		message: "请稍等..."
	    	 	 	}
	    	 	 );*/
						
						
			  window.open(url,"_bank");
			    }
		    
    	}
});
