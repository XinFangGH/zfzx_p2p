package com.hurong.credit.action.pay.YeePay;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hurong.core.Constants;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.thirdInterface.YeePayReponse;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.thirdInterface.OpraterBussinessDataService;
import com.hurong.credit.service.thirdInterface.YeePayService;
import com.hurong.credit.util.TemplateConfigUtil;



public class YeePayFrontAction extends BaseAction {
	@Resource
	private YeePayService yeePayService;
	@Resource
	private OpraterBussinessDataService opraterBussinessDataService;
	@Resource
	private BpFundIntentService bpFundIntentService;
	/**
	 * 页面回调函数处理方法
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String webfrontBackValue() throws IOException {
		String isMobile =this.getRequest().getParameter("isMobile");
		String keystr=null;//U计划用到
		//验证充值返回值
		System.out.println("测试地址开始");
		Object[]  ret =yeePayService.webfrontBackValue(this.getRequest());
		System.out.println("开始执行页面回调");
		try{
			if(ret[0].toString().equals(Constants.CODE_SUCCESS)){
				YeePayReponse response=(YeePayReponse) ret[1];
				if(response.getCode().equals("1")){//返回交易成功的记录
					String mesg="第三方支付账号开通成功";
					if(response.getService().equals("REGISTER")){//注册
						String[] num=response.getRequestNo().split("-");
						String customerId=num[num.length-2];
						Map<String ,String> map=new HashMap<String,String>();
						map.put("custermemberId", customerId);
						map.put("custermemberType", "0");
						map.put("platformUserNo", response.getRequestNo());
						map.put("platFormUserName",response.getRequestNo());
						String[]  bussinessResult=opraterBussinessDataService.regedit(map);
					}else if(response.getService().equals("RECHARGE")){//充值
						mesg="用户充值成功";
						Map<String ,String> map=new HashMap<String,String>();
						map.put("custermemberId", "");
						map.put("requestNo",response.getRequestNo());
						map.put("custmerType", "0");
						map.put("dealRecordStatus","2");
						String[]  bussinessResult=opraterBussinessDataService.recharge(map);
					}else if(response.getService().equals("WITHDRAW")){//取现
						mesg="用户取现申请成功";
						Map<String ,String> map=new HashMap<String,String>();
						map.put("custermemberId", "");
						map.put("requestNo",response.getRequestNo());
						map.put("custmerType", "0");
						map.put("dealRecordStatus","2");
						String[]  bussinessResult=opraterBussinessDataService.withDraw(map);
					}else if(response.getService().equals("BIND_BANK_CARD")){//绑银行卡
						mesg="用户绑定银行卡申请受理成功";
						Map<String ,String> map=new HashMap<String,String>();
						map.put("custermemberId", "");
						map.put("requestNo",response.getRequestNo());
						map.put("custmerType", "0");
						map.put("bankCardNo","");
						map.put("bankCode","");
						map.put("bankName","");
						map.put("bankstatus","bindCard_status_repare");
						String[]  bussinessResult=opraterBussinessDataService.bandCard(map);
					}else if(response.getService().equals("TRANSFER")){
						mesg="投资资金冻结成功，等待订单确认";
						Map<String ,String> map=new HashMap<String,String>();
						map.put("custermemberId", "");
						map.put("custmerType", "0");
						map.put("requestNo",response.getRequestNo());
						map.put("dealRecordStatus","2");
						String JZWWmethod=this.getRequest().getParameter("JZWWmethod");
						if(JZWWmethod!=null){
							String []  rrett=opraterBussinessDataService.bidMoneyPlan(map);
							keystr=rrett[2];//U计划用到
						}else{
							//标
							String[]  bussinessResult=opraterBussinessDataService.biding(map);
							if(bussinessResult[0].contains("successOther")){
								ret[0]=Constants.CODE_FAILED;
								ret[1]=bussinessResult[1];
								keystr=bussinessResult[2];//U计划用到
							}
						}
						
					}else if(response.getService().equals("REPAYMENT")){
						mesg="借款人自助还款申请成功";
						Map<String ,String> map=new HashMap<String,String>();
						map.put("orderNo", "");
						map.put("requestNo",response.getRequestNo());
						String[]  bussinessResult=opraterBussinessDataService.repayment(map);
						//平台派发优惠券，加息奖励
						if(bussinessResult[0]==Constants.CODE_SUCCESS){
							System.out.println("平台派发优惠券，加息奖励");
							List<BpFundIntent> list= bpFundIntentService.getByRequestNo(response.getRequestNo());
							//平台发送奖励金额
							opraterBussinessDataService.checkCouponsIntent(list.get(0).getBidPlanId().toString(),list.get(0).getPayintentPeriod().toString(),response.getRequestNo(),this.getBasePath());
						}
					}else if(response.getService().equals("AUTHORIZE_AUTO_TRANSFER")){
						mesg="自动投标授权成功";
						Map<String ,String> map=new HashMap<String,String>();
						map.put("custermemberId", "");
						map.put("custmerType", "0");
						map.put("requestNo",response.getRequestNo());
						String[]  bussinessResult=opraterBussinessDataService.bidingAuthorization(map);
					}else if(response.getService().equals("AUTHORIZE_AUTO_REPAYMENT")){
						mesg="自动还款授权成功";
						Map<String ,String> map=new HashMap<String,String>();
						map.put("orderNo", "");
						map.put("requestNo",response.getRequestNo());
						String[]  bussinessResult=opraterBussinessDataService.repaymentAuthorization(map);
					}else if(response.getService().equals("UNBIND_BANK_CARD")){
						mesg="取消绑卡成功";
						Map<String ,String> map=new HashMap<String,String>();
						map.put("custermemberId", "");
						map.put("custmerType", "0");
						map.put("orderNo", "");
						map.put("requestNo",response.getRequestNo().split("-")[0]);
						String[]  bussinessResult=opraterBussinessDataService.cancelBindBank(map);
					}else if(response.getService().equals("TRANSFER_CLAIMS")){
						mesg="债权购买成功";
						Map<String ,String> map=new HashMap<String,String>();
						map.put("requestNo",response.getRequestNo());
						map.put("dealstatus", "2");
						String[]  bussinessResult=opraterBussinessDataService.doObligationDeal(map);
					}else if(response.getService().equals("TRANSACTION")){//通用转账授权接口
						mesg="通用转账授权成功";
						String requestNo=/*PFUI*/response.getRequestNo();
						if(requestNo.contains("DCHK")){//表示代偿还款
							mesg="代偿还款申请成功,审核中";
						//	Object[]  bussinessResult=opraterBussinessDataService.doCompensatory(requestNo,Short.valueOf("7"));
						//	mesg=bussinessResult[1].toString();
						}else if(requestNo.contains("PFUI")){//表示站岗资金的购买
							mesg="购买申请成功,审核中";
						//	Object[]  bussinessResult=opraterBussinessDataService.doFianceProductBuy(requestNo,Short.valueOf("7"));
							//mesg=bussinessResult[1].toString();
						}else if(requestNo.contains("MMPLAN")){//D计划收款户为平台账户
							Map<String ,String> map=new HashMap<String,String>();
							map.put("requestNo",requestNo);
							map.put("custmerType", "0");
							map.put("dealRecordStatus", "2");
							String []  rrett=opraterBussinessDataService.bidMoneyPlan(map);
							keystr=rrett[2];//U计划用到
						}else{
							mesg="挂牌成功";
							Map<String ,String> map=new HashMap<String,String>();
							map.put("requestNo",requestNo);
							map.put("dealRecordStatus", "2");
							String[]  bussinessResult=opraterBussinessDataService.doObligationPublish(map);
						}
					}else if(response.getService().equals("RESET_MOBILE")){//修改手机号码	
						mesg="修改手机号码成功";
						String requestNo=response.getRequestNo();
						Map<String ,String> map=new HashMap<String,String>();
						map.put("requestNo",response.getRequestNo());
						map.put("dealstatus", "2");//表示修改成功
						opraterBussinessDataService.chageMobile(map);
					}else{
						
					}
					ret[0]=Constants.CODE_SUCCESS;
					ret[1]=mesg;
				}else{
					String mesg="用户注册";
					if(response.getService().equals("REGISTER")){//注册
						
					}else if(response.getService().equals("RECHARGE")){//充值
						mesg="用户充值失败：原因"+response.getDescription();
						Map<String ,String> map=new HashMap<String,String>();
						map.put("custermemberId", "");
						map.put("requestNo",response.getRequestNo());
						map.put("custmerType", "0");
						map.put("dealRecordStatus","3");
						String[]  bussinessResult=opraterBussinessDataService.recharge(map);
					}else if(response.getService().equals("WITHDRAW")){//取现
						mesg="用户取现失败：原因"+response.getDescription();
						Map<String ,String> map=new HashMap<String,String>();
						map.put("custermemberId", "");
						map.put("requestNo",response.getRequestNo());
						map.put("custmerType", "0");
						map.put("dealRecordStatus","3");
						String[]  bussinessResult=opraterBussinessDataService.recharge(map);
					}else if(response.getService().equals("BIND_BANK_CARD")){//绑银行卡
						mesg="用户绑定银行卡失败：原因"+response.getDescription();
						Map<String ,String> map=new HashMap<String,String>();
						map.put("custermemberId", "");
						map.put("requestNo",response.getRequestNo());
						map.put("custmerType", "0");
						map.put("bankCardNo","");
						map.put("bankCode","");
						map.put("bankName","");
						map.put("bankstatus","bindCard_status_faild");
						String[]  bussinessResult=opraterBussinessDataService.bandCard(map);
					}else if(response.getService().equals("TRANSFER")){
						mesg="投资资金冻结失败，等待订单取消，失败原因："+response.getDescription();
						Map<String ,String> map=new HashMap<String,String>();
						map.put("custermemberId", "");
						map.put("custmerType", "0");
						map.put("requestNo",response.getRequestNo());
						map.put("dealRecordStatus","3");
						String[]  bussinessResult=opraterBussinessDataService.biding(map);
					}else if(response.getService().equals("REPAYMENT")){
						mesg="借款人自助还款失败：原因"+response.getDescription();
					}else if(response.getService().equals("AUTHORIZE_AUTO_TRANSFER")){
						mesg="自动投标授权失败：原因"+response.getDescription();
					}else if(response.getService().equals("AUTHORIZE_AUTO_REPAYMENT")){
						mesg="自动还款授权失败：原因"+response.getDescription();
					}else if(response.getService().equals("TRANSFER_CLAIMS")){
						mesg="债权交易"+response.getDescription();
						Map<String ,String> map=new HashMap<String,String>();
						map.put("requestNo",response.getRequestNo());
						map.put("dealstatus", "0");
						String[]  bussinessResult=opraterBussinessDataService.doObligationDeal(map);
					} else if(response.getService().equals("TRANSACTION")){//通用转账授权接口
						mesg="通用转账授权失败：原因"+response.getDescription();
						String requestNo=response.getRequestNo();
						if(requestNo.contains("PFUI")){//表示站岗资金的购买
							Map<String ,String> map=new HashMap<String,String>();
							map.put("requestNo",requestNo);
							map.put("dealstatus", "0");
							//Object[]  bussinessResult=opraterBussinessDataService.doFianceProductBuy(requestNo,Short.valueOf("3"));
						}else{
							Map<String ,String> map=new HashMap<String,String>();
							map.put("requestNo",requestNo);
							map.put("dealstatus", "0");
							String[]  bussinessResult=opraterBussinessDataService.doObligationPublish(map);
						}
						
					}else if(response.getService().equals("RESET_MOBILE")){//	修改手机号码	
						String requestNo=response.getRequestNo();
						Map<String ,String> map=new HashMap<String,String>();
						map.put("telephone",response.getRequestNo().substring(0, 10));
						map.put("dealstatus", "1");//表示修改失败
						opraterBussinessDataService.chageMobile(map);
					}else{
						
					}
					ret[0]=Constants.CODE_FAILED;
					ret[1]=mesg+"失败：易宝支付报错信息-"+response.getDescription();
				}
				
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="易宝验签失败";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="出错了，请联系管理员";
			if("mobile".equals(this.getRequest().getParameter("mobile"))){
				this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileerr.ftl");
				return "freemarker";
			}
		}		
		//设置 返回提示消息
		webMsgInstance("0", ret[0].toString(), ret[1].toString(),  "", "", "", "", "");
		
		if(null!=keystr && !"".equals(keystr) && null!=webMsg){//U计划需要,把keystr拼接在购买的请求返回url不可以,所以只能用这种方式
			webMsg.setUrl(webMsg.getUrl()+"&keystr="+keystr);
		}
		// 后台去掉
		if("mobile".equals(this.getRequest().getParameter("mobile"))){
			String url=this.getBasePath()+"user/getBpCustMember.do?mobile=mobile";
			this.getResponse().sendRedirect(url);
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileCenter.ftl");
			//this.getResponse().sendRedirect();
			//this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileCenter.ftl");
		}else if("mobiletransfer".equals(this.getRequest().getParameter("mobile"))){
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/ht.ftl");
		}else if (isMobile!=null){
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}
		return "freemarker";
	}
}