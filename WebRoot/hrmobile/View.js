
/**
 * 手机登录跳转 by cjj
 */
var dasb = true;
Ext.define('mobile.View', {
	extend : 'hurong.navigation.View',

	constructor : function(config) {
		this.isLogoutBtn = true;
		this.isRegisterBtn = false;
		this.isLoginBtn = false;
		config = config || {};
		this.isLogoutBtn = Ext.isEmpty(config.isLogoutBtn)
				? true
				: config.isLogoutBtn;
		this.isRegisterBtn = Ext.isEmpty(config.isRegisterBtn)
				? false
				: config.isRegisterBtn;
		this.isLoginBtn = Ext.isEmpty(config.isLoginBtn)
				? false
				: config.isLoginBtn;
		var me = this;
		var bottomBar = Ext.create(
				'hrmobile.public.myhome.related.bottomBarIndex', {});
		Ext.apply(config, {
			defaultBackButtonText : '<img src="hrmobile/resources/imagesP2p/back.png" width="35%">',
			items : [
					// 登录页
//					Ext.create('hrmobile.public.myhome.login', {}),
			
					// 注册页 (?onePageType=register&from=qrcode&recommendCode=10208932)
//					Ext.create('hrmobile.public.myhome.register1', {}),

					// 注册页  - 手机验证
//					Ext.create('hrmobile.public.myhome.telephoneCheck', {}),
					
					// 登录页 - 忘记密码	
//					Ext.create('hrmobile.public.myhome.forgetPassword', {}),
					// 登录页 - 忘记密码	- 验证手机
//					Ext.create('hrmobile.public.myhome.related.forgetPasswordnext', {}),
					// 登录页 - 忘记密码	- 修改密码
//					Ext.create('hrmobile.public.myhome.related.forgetPasswordnextTwo', {}),

					// 自定义导航页
//					Ext.create('hrmobile.public.myhome.related.publicTop', {}),
					// 首页
					Ext.create('hrmobile.public.myhome.index', {}),

					// 首页 - 马上投资  - 投标详情
//					Ext.create('hrmobile.public.myhome.bidDetail', {}),
					// 首页 - 马上投资  - 投标详情 - 项目信息(基本信息)
//					 Ext.create('hrmobile.public.myhome.planbid.biddetail', {}),
					// 首页 - 马上投资  - 投标详情 - 安全保证
					// Ext.create('hrmobile.public.myhome.planbid.bidrelated', {}),
					// 首页 - 马上投资  - 投标详情 - 审核资料
//					 Ext.create('hrmobile.public.myhome.planbid.audit_data', {}),
					// 首页 - 马上投资  - 投标详情 - 投标记录
//					 Ext.create('hrmobile.public.myhome.planbid.biddinglist', {}),
					// 首页 - 马上投资  - 投标详情 - 风险控制
//					Ext.create('hrmobile.public.myhome.scatteredMark.RiskManagement', {}),

					// 首页 - 马上投资  - 投标详情 - 立即投资
//					Ext.create('hrmobile.public.myhome.planbid.buyplanbid', {}),
					
					// 投资
//					Ext.create('hrmobile.public.myhome.investManage', {}),
					// 投资 - 散标投资 
//					Ext.create('hrmobile.public.myhome.itoInvestList', {}),
					// 投资 - 散标投资  - 投标详情
//					Ext.create('hrmobile.public.myhome.bidDetail', {}),
					// 投资 - 散标投资  - 投标详情 - 项目信息(基本信息)
//					 Ext.create('hrmobile.public.myhome.planbid.biddetail', {}),
					// 投资 - 散标投资   - 投标详情 - 安全保证(审核资料)
					// Ext.create('hrmobile.public.myhome.planbid.bidrelated', {}),
					// 投资 - 散标投资   - 投标详情 - 审核资料
//					 Ext.create('hrmobile.public.myhome.planbid.audit_data', {}),
					// 投资 - 散标投资  - 投标详情 - 投标记录
//					 Ext.create('hrmobile.public.myhome.planbid.biddinglist', {}),
					// 投资 - 散标投资  - 投标详情  - 立即投资
//					Ext.create('hrmobile.public.myhome.planbid.buyplanbid', {}),
					// 投资 - 散标投资  - 投标详情  - 立即投资 - 选择优惠券
//					Ext.create('hrmobile.public.myhome.coupon.newconpunlist', {}),
					
					// 投资 - 众拓理财计划 
//					Ext.create('hrmobile.public.myhome.Ummplan.UmmplanList', {}),
					// 投资 - 众拓理财计划 - 计划详情
//					Ext.create('hrmobile.public.myhome.mmplanDetail', {}),
					// 投资 - 众拓理财计划 - 计划详情 - 立即投资
//					Ext.create('hrmobile.public.myhome.mmplanImmediatelyInvest', {}),
					// 投资 - 众拓理财计划 - 计划详情 - 立即投资 - 选择优惠券
//					Ext.create('hrmobile.public.myhome.coupon.financingPlanCoupon', {}),

					// 投资 - 体验标
//					Ext.create('hrmobile.public.myhome.experienceMark', {}),
					// 投资 - 体验标 - 详情
//					Ext.create('hrmobile.public.myhome.experienceMarkDetail', {}),
					// 投资 - 体验标 - 体验标介绍
//					Ext.create('hrmobile.public.myhome.experienceMarkText', {}),
					// 投资 - 体验标 - 投标记录
//					Ext.create('hrmobile.public.myhome.planbid.experienceMarkList', {}),
					
					// 投资 - 体验标 - 详情 - 立即投资
//					Ext.create('hrmobile.public.myhome.planbid.buyExperienceBid', {}),
					// 投资 - 体验标 - 详情 - 立即投资- 选择优惠券
//					Ext.create('hrmobile.public.myhome.coupon.experienceMarkConpunlist', {}),
						
					// 投资 - 债权交易
//					Ext.create('hrmobile.public.myhome.Rmmplan.RmmplanList', {}),
					// 投资 - 债权交易 - 债权详情
//					Ext.create('hrmobile.public.myhome.Rmmplan.RmmplanDetail', {}),
					// 投资 - 债权交易 - 债权详情 - 标的详情
//					Ext.create('hrmobile.public.myhome.Rmmplan.Rmmdetail', {}),
					// 投资 - 债权交易 - 债权详情 - 相关材料
//					Ext.create('hrmobile.public.myhome.Rmmplan.Rmmrelated', {}),
					// 投资 - 债权交易 - 债权详情 - 投标记录(旧)
					// Ext.create('hrmobile.public.myhome.Rmmplan.Rmmlist', {}),
					// 投资 - 债权交易 - 债权详情 - 投标记录(新)
//					Ext.create('hrmobile.public.myhome.Rmmplan.bidRecord', {}),
					// 投资 - 债权交易 - 债权详情 - 债权信息
//					Ext.create('hrmobile.public.myhome.Rmmplan.Rmmdebtinfolist', {}),

					// 投资 - 债权交易 - 购买
//					Ext.create('hrmobile.public.myhome.Rmmplan.buyRmmplan', {}),
					// 投资 - 债权交易 - 购买 - 回款计划
//					Ext.create('hrmobile.public.myhome.Paymentplan', {}),
					
					// 借款
//					Ext.create('hrmobile.public.myhome.loanList', {}),
					// 借款 - 贷款类型
//					Ext.create('hrmobile.public.myhome.loanRelated.teacherLoan', {}),
					// 借款  - 个人借款意向登记
//					Ext.create('hrmobile.public.myhome.loanRelated.teacherPersonalLoan', {}),
					// 借款  - 企业借款意向登记
//					Ext.create('hrmobile.public.myhome.loanRelated.teacherEnterpriseLoan', {}),
					
					// 账户
//					Ext.create('hrmobile.public.myhome.main', {}),
//
					// 账户  - 充值/提现
//					Ext.create('hrmobile.public.myhome.rechargeAndwithdraw', {}),

					// 账户  - 资金明细
//					Ext.create('hrmobile.public.myhome.fundDetails', {}),
					// 账户  - 消息通知(站内消息)
//					Ext.create('hrmobile.public.myhome.news', {}),
					// 账户  - 账户总览
//					Ext.create('hrmobile.public.myhome.account', {}),
					// 账户  - 银行列表
//					Ext.create('hrmobile.public.myhome.bankCard', {}),
					// 账户  - 银行列表 - 绑定第三方支付
//					Ext.create('hrmobile.public.myhome.changeCard', {}),
					
					// 账户 - 散标理财(散标投资)
//					Ext.create('hrmobile.public.myhome.planbid.basicinformation', {}),

					// 账户 - 散标投资 - 回款中
//					Ext.create('hrmobile.public.myhome.planbid.backin', {}),
					// 账户 - 散标投资 - 回款中 - 回款计划
//					Ext.create('hrmobile.public.myhome.myloan.Paymentplan1', {}),
					
					// 账户 - 散标投资 - 投标中
//					Ext.create('hrmobile.public.myhome.planbid.bidding', {}),
					// 账户 - 散标投资 - 已结清
//					Ext.create('hrmobile.public.myhome.planbid.settledMark', {}),
					// 账户 - 散标投资 - 已关闭
//					Ext.create('hrmobile.public.myhome.planbid.failure', {}),
					// 账户 - 散标投资 - 体验标
//					Ext.create('hrmobile.public.myhome.planbid.experience', {}),
	
					// 账户 - 债权交易
//					Ext.create('hrmobile.public.myhome.plandebts', {}),
					// 账户 - 债权交易 - 可交易
//					Ext.create('hrmobile.public.myhome.plandebts.canTransferingList', {}),
					// 账户 - 债权交易 - 可交易 - 基本信息(标的详情)
//					Ext.create('hrmobile.public.myhome.planbid.bidrelatedInfo', {}),
					// 账户 - 债权交易 - 可交易 - 挂牌交易
//					Ext.create('hrmobile.public.myhome.plandebts.debtstrade', {}),
					// 账户 - 债权交易 - 可交易 - 回款计划
//					Ext.create('hrmobile.public.myhome.myloan.Paymentplan2', {}),
					
					// 账户 - 债权交易 - 交易中
//					Ext.create('hrmobile.public.myhome.plandebts.transferingList', {}),
					// 账户 - 债权交易 - 已卖出
//					Ext.create('hrmobile.public.myhome.plandebts.transferedList', {}),

					// 账户 - 债权交易 - 已购买
//					Ext.create('hrmobile.public.myhome.plandebts.buyedList', {}),
					// 账户 - 债权交易 - 已购买 - 结算清单(交易信息/债权清单)
//					Ext.create('hrmobile.public.myhome.plandebts.accountList', {}),

					// 账户 - 债权交易 - 已关闭
//					Ext.create('hrmobile.public.myhome.plandebts.closeedList', {}),
//
//					// 账户 - 理财计划
//					Ext.create('hrmobile.public.myhome.planmplan', {}),
					// 账户 - 理财计划 - 计划详情
//					Ext.create('hrmobile.public.myhome.mmplanDetail', {}),

					// 账户 - 理财计划 - 购买中
//					Ext.create('hrmobile.public.myhome.mmplan.mmplanbackin', {}),
					
					// 账户 - 理财计划 - 持有中
//					Ext.create('hrmobile.public.myhome.mmplan.mmplanbidding', {}),
					// 账户 - 理财计划 - 持有中 - 回款计划
//					Ext.create('hrmobile.public.myhome.mmplan.Paymentplan3', {}),
					// 账户 - 理财计划 - 持有中 - 债权清单
//					Ext.create('hrmobile.public.myhome.mmplan.creditorList', {}),
					// 账户 - 理财计划 - 持有中 - 债权清单 - 详情
//					Ext.create('hrmobile.public.myhome.mmplan.creditorDetail', {}),
					// 账户 - 理财计划 - 持有中 - 债权清单 - 详情 - 项目信息
//					Ext.create('hrmobile.public.myhome.planbid.creditorInfo', {}),

					// 账户 - 理财计划 - 持有中 - 退出(提前支取)
//					Ext.create('hrmobile.public.myhome.mmplan.earlyOutDetail', {}),

					// 账户 - 理财计划 - 已完成
//					Ext.create('hrmobile.public.myhome.mmplan.mmplansettled', {}),
					// 账户 - 理财计划 - 已退出
//					Ext.create('hrmobile.public.myhome.mmplan.mmplanoutList', {}),
					// 账户 - 理财计划- 已失败
//					Ext.create('hrmobile.public.myhome.mmplan.mmplanfailure', {}),

					// 账户 - 借款管理
//					Ext.create('hrmobile.public.myhome.myloan.myloan', {}),
					// 账户 - 借款管理 - 还款中
//					Ext.create('hrmobile.public.myhome.myloan.repayment', {}),
					// 账户 - 借款管理 - 还款中 - 基本信息
					//Ext.create('hrmobile.public.myhome.planbid.biddetail', {}),

					// 账户 - 借款管理 - 招标中
//					Ext.create('hrmobile.public.myhome.myloan.biddloan', {}),
					// 账户 - 借款管理 - 已结清
//					Ext.create('hrmobile.public.myhome.myloan.settled', {}),

					// 账户 - 借款管理 - 申请中(已注释)
//					Ext.create('hrmobile.public.myhome.myloan.appliloan', {}),

					// 账户 - 会员积分 (旧)
//					Ext.create('hrmobile.public.myhome.integral.myintegral', {}),
					// 账户 - 会员积分 (新)
//					Ext.create('hrmobile.public.myhome.main.integralSelect', {}),
					// 账户 - 会员积分 - 积分记录
//					Ext.create('hrmobile.public.myhome.integral.IntegralRecord', {}),


					// 账户 - 优惠券
//					Ext.create('hrmobile.public.myhome.coupon.coupon', {}),
					// 账户 - 优惠券 - 未使用(已使用/已过期)
//					Ext.create('hrmobile.public.myhome.coupon.couponList', {}),
	
					// 账户 - 推荐码
//					Ext.create('hrmobile.public.myhome.invitation.myinvitation', {}),

					// 账户 - 账户安全
//					Ext.create('hrmobile.public.myhome.safety', {}),
					// 账户 - 账户安全 - 实名认证   Ext.create('hrmobile.public.myhome.newPersonInfo', {})
//					Ext.create('hrmobile.public.myhome.binding', {}),
					// 账户  - 账户安全 - 邮箱认证
//					Ext.create('hrmobile.public.myhome.checkemail', {}),
					//  账户  - 账户安全 - 自动投标
//					Ext.create('hrmobile.public.myhome.aiplan.aubiddingupdate', {}),
					// 账户  - 账户安全 - 修改登录密码[密码修改]
//					Ext.create('hrmobile.public.myhome.related.updatePassword', {}),
				bottomBar
			],
			navigationBar : {
				ui : 'confirm',
				docked : 'top',
				id:'navbar',
				style : {
					/*'background' : themeColor,*/
					'background' : '#d6450c',
					'border' : 'none',
					'position':'fixed',
					'width':'100%',
					'height':'42px',
				},
				backButton : {
					labelCls : "labelclsleft",
					style : 'border:none;background:none;margin-left:5px;margin-top:5px;padding-top:0px;'
				},
				items : [{
					xtype : 'button',
					id : 'login',
//					text : '<img src="hrmobile/resources/imagesP2p/main/data-icon.png" width="17" height="17">',
					align : 'right',
					hidden : dasb,
					style : 'background:' + themeColor
							+ '; border-radius:5px;color:#FFFFFF;',
					handler : function() {
						/*hidebottomBarIndex();
						mobileNavi.push(Ext.create(
								'hrmobile.public.myhome.related.payKitingTab',
								{}));
*/
					}
				}]
			}

		});

		this.callParent([config]);


		allback = function() {
			mobileNavi.reset();
		}
		login = function() {
			mobileNavi.push(Ext.create('hrmobile.public.myhome.login', {}));
		}
	}

});
