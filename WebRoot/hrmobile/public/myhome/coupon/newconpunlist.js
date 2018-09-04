var rebateType,
	maxCouponMoney;
Ext.define('hrmobile.public.myhome.coupon.newconpunlist', {
    extend: 'mobile.List',
    name: 'newconpunlist',
    constructor: function (config) {
    	config = config || {};
    	
    	// 获取回扣类型
    	rebateType = config.rebateType;
    	
    	// 获取最大面值
    	maxCouponMoney = config.maxCouponMoney;
    	
    	// 获取上个页面回调
    	this.callback = config.callback;
    	
    	
    	Ext.apply(config,{
    		modeltype:"newconpunlist",
    		title:config.title,
    		fields:[{
								name : 'belongUserId'// 标号
							}, {
								name : 'belongUserName'// 姓名
							}, {
								name : 'bindOpraterDate'// 时间
							}, {
								name : 'bindOpratorId'// 不知道
							}, {
								name : 'bindOpratorName'// 权限
							}, {
								name : 'companyId'// 优惠券ID
							}, {
								name : 'couponEndDate'// 到期时间
							}, {
								name : 'couponId'// 这又是什么ID
							}, {
								name : 'couponStartDate'// 开始时间
							}, {
								name : 'couponValue'// 加息劵百分之多少
							}, {
								name : 'couponsDescribe'// 来源
							}, {
								name : 'createDate'// 第四个时间
							}, {
								name : 'createName'// 超级管理员就是nb
							},
							{
								name: 'couponType' // 优惠券类型
							}
							
							
							],
    		url : __ctxPath + '/coupon/listBpCoupons.do?couponStatus=5&mycouponsType=' + rebateType,
    		params : {
				isMobile : '1',
				couType  : 'wsy',
				userid:curUserInfo.id
			},
    		root:'result',
		    itemTpl: new Ext.XTemplate('<div class="stamp stamp02">'+
					    '<div class="par">' +
//					    '<p>{belongUserName}</p>' +
//					    '<sub class="sign">￥</sub>' +
					    '<span id="CouponValue">{[this.couponValue(values.couponType,values.couponValue)]}</span>' +
					  	'<sub>{[this.couponType(values.couponType,values.couponValue)]}</sub>' +
//					    '<sub>优惠券</sub>' +
					    '<p class="dingdan"></p>' +
					  '</div>'+
					    '<div class="copy">' +
					    	'<p>{couponStartDate}<br>' +
					    		'{couponEndDate}' +
					    	'</p>' +
					    	'<a style="font-szie:14px">立即使用</a>' +
					    '</div>'+
					    '<i></i>'+
					'</div>',
					{
					// 判断优惠券类型
						couponType : function (couponType, couponValue) {
							// 返回文本
							var text = '';
							if (couponType == 1) {
								text = '特权金';
							} else if (couponType == 2) {
								text = '体验券';
							} else if (couponType == 3) {
								text = '加息券';
							}
							
							return text;
					
						},
						// 判断优惠券金额
						couponValue : function (couponType, couponValue) {
							// 返回文本
							var text = '';
							if (couponType == 1 || couponType == 2) {
								text = "￥" + couponValue;
							} else if (couponType == 3) {
								text = couponValue + "%";
							} else{
								text = '加息券';
							}
							
							return text;
						}
					}
			),
					
		    totalProperty: 'totalCounts',
		    pullRefresh: true,
		    listeners: {
		    	scope:this,
    			 itemsingletap:function( obj, index, target, record, e, eOpts ){
    			 	
    			 	// 判断可选的最大面值
    			 	if (maxCouponMoney != '' && !Ext.isEmpty(maxCouponMoney) && record.data.couponValue > maxCouponMoney) {
    			 		//  提示框
    			 		Ext.Msg.alert('提示','优惠券面值最大是' + maxCouponMoney + '!');
    			 	} else {
			 			// 返回上个页面
			 			mobileNavi.pop();
			 			
			 			// 将选择页面的数据回调给上个选择页面
			 			this.callback(record.data);
    			 	}
    			 	
    			 }
    		},
		    listPaging: true
    	});

    	this.callParent([config]);

    }

});
