package com.thirdPayInterface;

import com.hurong.core.Constants;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.service.customer.InvestPersonInfoService;
import com.hurong.credit.service.thirdInterface.OpraterBussinessDataService;
import com.hurong.credit.util.HttpRequestDeviceUtils;
import com.hurong.credit.util.TemplateConfigUtil;
import com.rd.bds.sign.SecurityException;
import com.thirdPayInterface.FuDianPay.FuDianInterfaceUtil;
import com.thirdPayInterface.FuDianPay.InvestBean;
import com.thirdPayInterface.ThirdPayLog.model.ThirdPayLog;
import com.thirdPayInterface.ThirdPayLog.model.ThirdPayRecord;
import com.thirdPayInterface.ThirdPayLog.service.ThirdPayLogService;
import com.thirdPayInterface.ThirdPayLog.service.ThirdPayRecordService;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author linyan
 * 2015-5-4
 */
public class ThirdPayInterfaceAction extends BaseAction {

    private static final long serialVersionUID = -1930117672600279243L;
    @Resource
    private OpraterBussinessDataService opraterBussinessDataService;
    @Resource
    private ThirdPayRecordService thirdPayRecordService;
    @Resource
    private InvestPersonInfoService investPersonInfoService;
    @Resource
    private ThirdPayLogService thirdPayLogService;

    /**
     * 页面回调通知
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public String pageUrl() {
        try {
            Thread.sleep(1500);
            String isMobile = this.getRequest().getParameter("isMobile");
            Map maps = createResponseMap(this.getRequest());
            System.out.println("pageUrl回调通知maps=" + maps);
            if (maps.isEmpty()) {
                webMsgInstance("0", Constants.CODE_FAILED, "数据接收失败,请稍后查看结果！", "", "", "", "", "");
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
                return "freemarker";
            }
            //解析第三方返回值
            CommonResponse commonResponse = ThirdPayInterfaceUtil.returnPageOprate(maps, this.getRequest());
            //更新第三方日志
            thirdPayRecordService.insertOrUpdateLog(null, commonResponse);
            //根据  commonResponse.getRequestNo()流水号 查询第三方记录判断实际业务类型
            ThirdPayRecord thirdPayRecord = null;
            if (commonResponse.getRequestNo() != null) {
                thirdPayRecord = thirdPayRecordService.getByOrderNo(commonResponse.getRequestNo());
                if( null != thirdPayRecord) {
                    thirdPayRecordService.evict(thirdPayRecord);
                }
            }
            //获取响应编码，选择消息展示模式（是成功标识，还是失败标识）
            if (thirdPayRecord != null && commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {//交易成功
                if (ThirdPayConstants.BT_PREGISTER.equals(thirdPayRecord.getInterfaceType())) {//个人开通第三方
                    Map<String, String> map = new HashMap<String, String>();
//						map.put("platformUserNo", thirdPayRecord.getThirdPayFlagId().toString());
                    map.put("custermemberId", thirdPayRecord.getUserId().toString());
                    map.put("custermemberType", "0");
                    if (commonResponse.getThirdPayConfigId() != null && !"".equals(commonResponse.getThirdPayConfigId())) {
//						map.put("thirdPayFlagId", commonResponse.getThirdPayConfigId());//
                        map.put("platformUserNo", commonResponse.getThirdPayConfigId());
                    }
                    map.put("platFormUserName", commonResponse.getThirdPayConfigId0() == null ? "" : commonResponse.getThirdPayConfigId0());
                    opraterBussinessDataService.regedit(map);

                    if (StringUtils.isNotEmpty(commonResponse.getBankName()) && StringUtils.isNotEmpty(commonResponse.getBankCode())) {
                        map.put("requestNo", commonResponse.getRequestNo() + "_1");
                        map.put("bankCardNo", commonResponse.getBankCode());//银行卡号
                        map.put("bankCode", commonResponse.getBankCardNumber());//银行代码
                        map.put("bankName", commonResponse.getBankName());//银行名称
                        map.put("bandType", "1");
//						map.put("bankType", commonResponse.getBankCardType());//银行卡类型
//						map.put("province", commonResponse.getProvince());//省份
//						map.put("city", commonResponse.getCity());//城市
                        map.put("bankstatus", "");
                        System.out.println("实名认证进来的绑卡，参数map:" + map.toString());
                        opraterBussinessDataService.bandCard(map);
                    } else {
                        System.out.println("获取卡号为空！！");
                    }

                } else if (ThirdPayConstants.BT_EREGISTER.equals(thirdPayRecord.getInterfaceType())) {//企业开通第三方
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("platformUserNo", commonResponse.getThirdPayConfigId());
                    map.put("custermemberId", thirdPayRecord.getUserId().toString());
                    map.put("custermemberType", "1");
                    map.put("platFormUserName", commonResponse.getThirdPayConfigId0() == null ? "" : commonResponse.getThirdPayConfigId0());
                    map.put("trueName", commonResponse.getTruename());//企业名称
                    map.put("cardCode", commonResponse.getCardCode());//统一社会信用代码
                    map.put("corpType", commonResponse.getRoleType());//公司类型  1：借款企业；2：担保公司 3 出借企业
                    map.put("artificialIdentityCode", commonResponse.getArtificialIdentityCode());//法人证件号码
                    map.put("artificialRealName", commonResponse.getArtificialRealName());//法人代表真实姓名
                    map.put("licenceCode", commonResponse.getLicenceCode());//营业执照编号
                    map.put("mobilePhone", commonResponse.getMobilePhone());//法人手机号
                    map.put("orgCode", commonResponse.getOrgCode());//组织机构代码
                    map.put("taxRegCode", commonResponse.getTaxRegCode());//税务登记号
                    map.put("threeCertUnit", commonResponse.getThreeCertUnit());//是否三证合一    0否1是
                    map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                    opraterBussinessDataService.regeditBus(map);
                    if (StringUtils.isNotEmpty(commonResponse.getBankName()) && StringUtils.isNotEmpty(commonResponse.getBankCode())) {
                        map.put("requestNo", commonResponse.getRequestNo() + "_1");
                        map.put("bankCardNo", commonResponse.getBankCode());//银行卡号
                        map.put("bankCode", commonResponse.getBankCardNumber());//银行代码
                        map.put("bankName", commonResponse.getBankName());//银行名称
                        map.put("bandType", "1");
                        map.put("bankstatus", "");
                        System.out.println("实名认证进来的绑卡，参数map:" + map.toString());
                        opraterBussinessDataService.bandCard(map);
                    }

                } else if (ThirdPayConstants.BT_BINDCARD.equals(thirdPayRecord.getInterfaceType())) {//绑定银行卡
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("custermemberId", thirdPayRecord.getUserId().toString());
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("bankCardNo", commonResponse.getBankCode());//银行卡号
                    map.put("bankCode", commonResponse.getBankCardNumber());//银行代码
                    map.put("bankName", commonResponse.getBankName());//银行名称
                    map.put("bankType", commonResponse.getBankCardType());//银行卡类型
                    map.put("province", commonResponse.getProvince());//省份
                    map.put("city", commonResponse.getCity());//城市
                    if (commonResponse.getActiveStatus().equals("1")) {//成功
                        map.put("bankstatus", "");
                    } else if (commonResponse.getActiveStatus().equals("2")) {//处理中
                        map.put("bankstatus", WebBankcard.BINDCARD_STATUS_ACCEPT);
                    } else {//失败
                        map.put("bankstatus", WebBankcard.BINDCARD_STATUS_FAILD);
                    }
                    opraterBussinessDataService.bandCard(map);
                } else if (ThirdPayConstants.BT_RECHAGE.equals(thirdPayRecord.getInterfaceType())) {//充值成功
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("receivedAmount", commonResponse.getReceivedAmount());//实际到账金额
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                    if (commonResponse.getBankCode() != null) {//银行卡号
                        map.put("bankCode", commonResponse.getBankCode());
                    }
                    if (commonResponse.getBankCardNumber() != null) {//银行代码
                        map.put("bankCardNumber", commonResponse.getBankCardNumber());
                    }
                    opraterBussinessDataService.recharge(map);
                } else if (ThirdPayConstants.BT_WITHDRAW.equals(thirdPayRecord.getInterfaceType())) {//取现成功
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("receivedAmount", commonResponse.getReceivedAmount());//实际到账金额
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());

                    if (commonResponse.getFee() != null) {//手续费
                        map.put("feeUser", commonResponse.getFee());
                    } else {
                        map.put("feeUser", new BigDecimal(0).toString());
                    }
                    if (commonResponse.getFeePlatform() != null) {
                        map.put("feePlatform", commonResponse.getFeePlatform().toString());
                    } else {
                        map.put("feePlatform", new BigDecimal(0).toString());
                    }
                    opraterBussinessDataService.withDraw(map);
                } else if (ThirdPayConstants.BT_BID.equals(thirdPayRecord.getInterfaceType())) {//投标成功
                   /* Map<String,String> map = new HashMap<String, String>();
                    map.put("custmerType", "0");
					if(commonResponse.getRequestNo()!=null&&!commonResponse.getRequestNo().equals("")){
						map.put("requestNo", commonResponse.getRequestNo());
					}
					if(commonResponse.getRemark2()!=null&&!commonResponse.getRemark2().equals("")){
						map.put("trxId",commonResponse.getRemark2());
					}
					if(commonResponse.getContract_no()!=null&&!"".equals(commonResponse.getContract_no())){
						map.put("contract_no", commonResponse.getContract_no());
					}
					if(commonResponse.getRemark1()!=null&&!commonResponse.getRemark1().equals("")){
						map.put("freezeTrx", commonResponse.getRemark1());//冻结的唯一标识
					}
					
					if(commonResponse.getLoanNo()!=null&&!commonResponse.getLoanNo().equals("")){
						map.put("loanNo", commonResponse.getLoanNo());
					}
					map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
					opraterBussinessDataService.biding(map);//散标投资*/
                } else if (ThirdPayConstants.BT_CANCELCARD.equals(thirdPayRecord.getInterfaceType())) {//取消绑定银行卡
                    System.out.println("页面回调取消绑卡1");
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("bankstatus", "bindCard_status_cancel");
//					if(commonResponse.getBankCode()!=null){
//						map.put("bankCode",commonResponse.getBankCode()); 
//					}
                    map.put("custermemberId", thirdPayRecord.getUserId().toString());
                    if (StringUtils.isNotEmpty(commonResponse.getActiveStatus()) && commonResponse.getActiveStatus().equals("1")) {
                        System.out.println("页面回调取消绑卡执行");
                        opraterBussinessDataService.cancelBindBank(map);
                    }
                } else if (ThirdPayConstants.BT_CANCELCARDBIND.equals(thirdPayRecord.getInterfaceType())) {//企业取消绑定银行卡
                    System.out.println("页面回调取消绑卡1");
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("bankstatus", "bindCard_status_cancel");
//					if(commonResponse.getBankCode()!=null){
//						map.put("bankCode",commonResponse.getBankCode());
//					}
                    map.put("custermemberId", thirdPayRecord.getUserId().toString());
                    map.put("type", "pageUrl");
                    if (StringUtils.isNotEmpty(commonResponse.getActiveStatus()) && commonResponse.getActiveStatus().equals("1")) {
                        System.out.println("页面回调取消绑卡执行");
                        opraterBussinessDataService.cancelBindBankCard(map);
                    }
                } else if (ThirdPayConstants.BUSSINESSTYPE_CANCELBINDBANKCARD.equals(thirdPayRecord.getInterfaceType())) {//取消绑定银行卡-没有调用的
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("bankstatus", "bindCard_status_cancel");
                    if (commonResponse.getBankCode() != null) {
                        map.put("bankCode", commonResponse.getBankCode());
                    }
                    opraterBussinessDataService.bandCard(map);
                } else if (ThirdPayConstants.BT_REPLACECARD.equals(thirdPayRecord.getInterfaceType())) {//更换银行卡
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("custermemberId", thirdPayRecord.getUserId().toString());
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("bankCardNo", commonResponse.getBankCode());//银行卡号
                    map.put("bankCode", commonResponse.getBankCardNumber());//银行代码
                    map.put("bankName", commonResponse.getBankName());//银行名称
                    map.put("bankType", commonResponse.getBankCardType());//银行卡类型
                    map.put("province", commonResponse.getProvince());//省份
                    map.put("city", commonResponse.getCity());//城市
                    if (commonResponse.getActiveStatus().equals("1")) {//成功
                        map.put("bankstatus", "");
                    } else if (commonResponse.getActiveStatus().equals("2")) {//处理中
                        map.put("bankstatus", WebBankcard.BINDCARD_STATUS_ACCEPT);
                    } else {//失败
                        map.put("bankstatus", WebBankcard.BINDCARD_STATUS_FAILD);
                    }
                    opraterBussinessDataService.replaceCard(map);
                } else if (ThirdPayConstants.BT_APPQRECHARGE.equals(thirdPayRecord.getInterfaceType())) {//个人免登陆app快速充值
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                    opraterBussinessDataService.recharge(map);
                }else if (ThirdPayConstants.BT_REPAYMENT.equals(thirdPayRecord.getInterfaceType())) {//p2p立即返款
                    //放到异步回调中处理了！！！
					/*Map<String,String> map = new HashMap<String, String>();
					map.put("bidId",thirdPayRecord.getBidId().toString());//标id
					map.put("peridId",thirdPayRecord.getRemark1());
					map.put("intentDate",thirdPayRecord.getIntentDate().toString());//计划还款日期
					map.put("requestTime", thirdPayRecord.getRequestTime().toString());//请求接口时间
					map.put("requestNum", thirdPayRecord.getRequestNum().toString());//请求接口次数
					map.put("requestNo", commonResponse.getRequestNo());
					map.put("thirdPayConfig", thirdPayRecord.getThirdPayConfig());
					map.put("thirdConfigType", "loaner");


					//还款完成以后，先调用投资人回款
					List<InvestBean> list = new ArrayList<InvestBean>();
					CommonRequst commonQ = new CommonRequst();
					String orderNumFirst =ContextUtil.createRuestNumber();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					commonQ.setBussinessType(ThirdPayConstants.BT_CALLBACK);//业务类型
					commonQ.setTransferName(ThirdPayConstants.TN_CALLBACK);//名称
					commonQ.setLoanNoList(thirdPayRecord.getRemark2());//三方标的标识
					//查询还款记录
					List<CommonRequestInvestRecord> repayList=investPersonInfoService.getRepaymentList(thirdPayRecord.getBidId().toString(),thirdPayRecord.getRemark1());
					//获取参数
					for (CommonRequestInvestRecord record : repayList) {
						String orderNum =ContextUtil.createRuestNumber();
						InvestBean invest = new InvestBean();
						invest.setCapital(record.getPrincipal().toString());//本金
						invest.setInterest(record.getInterest().toString());//利息
//						invest.setInterestFee(record.getFee().toString());//管理费
						invest.setInterestFee("0.00");//管理费，这个费用是投资人给平台的
						invest.setRateInterest(record.getAccrual().toString());//罚息
						invest.setInvestAccountNo(record.getInvest_thirdPayConfigId());//三方账号
						invest.setInvestUserName(record.getInvest_thirdPayConfigId0());//三方有用户名
						invest.setInvestOrderDate(sdf.format(record.getRequestDate()));//原有投资订单日期
						invest.setInvestOrderNo(record.getBidRequestNo());//原有投资订单号
						invest.setOrderDate(sdf.format(new Date()));//当前交易订单日期，格式yyyyMMdd
						invest.setOrderNo(orderNum);//当前流水订单号
						list.add(invest);
					}
					commonQ.setInvestList(list);
					commonQ.setRequsetNo(orderNumFirst);
					commonQ.setThirdPayConfigId(null);
					commonQ.setThirdPayConfigId0(null);
					CommonResponse thirdCommon = ThirdPayInterfaceUtil.thirdCommon(commonQ);
					if (thirdCommon.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
						if(thirdPayRecord.getThirdPayConfig().equals("umpayConfig")){
							opraterBussinessDataService.umpayRepayment(map);
						}else if(thirdPayRecord.getThirdPayConfig().equals("yeepayConfig")){
							opraterBussinessDataService.yeepayRepayment(map);
						}else{
							opraterBussinessDataService.repayment(map);
						}
					}*/
                } else if (ThirdPayConstants.BT_BEFOREPAY.equals(thirdPayRecord.getInterfaceType())) {//提前还款
                    //放到异步回调中处理了！！！
					/*Map<String,String> map = new HashMap<String, String>();
					map.put("bidId",thirdPayRecord.getBidId().toString());//标id
					map.put("intentDate",thirdPayRecord.getIntentDate().toString());//计划还款日期
					map.put("requestTime", thirdPayRecord.getRequestTime().toString());//请求接口时间
					map.put("requestNum", thirdPayRecord.getRequestNum().toString());//请求接口次数
					map.put("requestNo", commonResponse.getRequestNo());
					map.put("thirdPayConfig", thirdPayRecord.getThirdPayConfig());
					map.put("slEarlyRepaymentId", thirdPayRecord.getRemark1());
					map.put("thirdConfigType", "loaner");

					//还款完成以后，先调用投资人回款
					List<InvestBean> list = new ArrayList<InvestBean>();
					CommonRequst commonQ = new CommonRequst();
					String orderNumFirst =ContextUtil.createRuestNumber();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					commonQ.setBussinessType(ThirdPayConstants.BT_CALLBACK);//业务类型
					commonQ.setTransferName(ThirdPayConstants.TN_CALLBACK);//名称
					commonQ.setLoanNoList(thirdPayRecord.getRemark2());//三方标的标识
					//查询还款记录
					List<CommonRequestInvestRecord> repayList=investPersonInfoService.getRepayEarlymentList(thirdPayRecord.getBidId().toString(),thirdPayRecord.getRemark1());
					//获取参数
					for (CommonRequestInvestRecord record : repayList) {
						String orderNum =ContextUtil.createRuestNumber();
						InvestBean invest = new InvestBean();
						invest.setCapital(record.getPrincipal().toString());//本金
						invest.setInterest(record.getInterest().toString());//利息
//						invest.setInterestFee(record.getFee().toString());//管理费
						invest.setInterestFee("0.00");//管理费，这个费用是投资人给平台的
						invest.setRateInterest("0.00");//罚息  提前还款没有罚息
						invest.setInvestAccountNo(record.getInvest_thirdPayConfigId());//三方账号
						invest.setInvestUserName(record.getInvest_thirdPayConfigId0());//三方有用户名
						invest.setInvestOrderDate(sdf.format(record.getRequestDate()));//原有投资订单日期
						invest.setInvestOrderNo(record.getBidRequestNo());//原有投资订单号
						invest.setOrderDate(sdf.format(new Date()));//当前交易订单日期，格式yyyyMMdd
						invest.setOrderNo(orderNum);//当前流水订单号
						list.add(invest);
					}
					commonQ.setInvestList(list);
					commonQ.setRequsetNo(orderNumFirst);
					commonQ.setThirdPayConfigId(null);
					commonQ.setThirdPayConfigId0(null);
					CommonResponse thirdCommon = ThirdPayInterfaceUtil.thirdCommon(commonQ);
					if (thirdCommon.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
						opraterBussinessDataService.repayment(map);
					}*/
                } else if (ThirdPayConstants.BT_COMPENSATORY.equals(thirdPayRecord.getInterfaceType())) {//担保公司代偿还款
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("bidId", thirdPayRecord.getBidId().toString());//标id
                    map.put("intentDate", thirdPayRecord.getIntentDate().toString());//计划还款日期
                    map.put("requestTime", thirdPayRecord.getRequestTime().toString());//请求接口时间
                    map.put("requestNum", thirdPayRecord.getRequestNum().toString());//请求接口次数
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("thirdPayConfig", thirdPayRecord.getThirdPayConfig());
                    opraterBussinessDataService.compensatory(map);

                } else if (ThirdPayConstants.BT_MMBACKMONEY.equals(thirdPayRecord.getInterfaceType())) {//UD计划收款账户为投资人还款
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("bidId", thirdPayRecord.getBidId().toString());//标id
                    map.put("intentDate", thirdPayRecord.getIntentDate().toString());//计划还款日期
                    map.put("requestTime", thirdPayRecord.getRequestTime().toString());//请求接口时间
                    map.put("requestNum", thirdPayRecord.getRequestNum().toString());//请求接口次数
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("thirdPayConfig", thirdPayRecord.getThirdPayConfig());
                    map.put("thirdConfigType", "loaner");
                    if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                        //	opraterBussinessDataService.umpayRepayment(map);
                    } else {
                        opraterBussinessDataService.financialRepayment(map);
                    }
                } else if (ThirdPayConstants.BT_MMADVANCEUSER.equals(thirdPayRecord.getInterfaceType())) {//UD计划收款账户为投资人提前赎回还款
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("bidId", thirdPayRecord.getBidId().toString());//标id
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("thirdPayConfig", thirdPayRecord.getThirdPayConfig());
                    map.put("dealMoney", thirdPayRecord.getDealMoney().toString());
                    map.put("userId", thirdPayRecord.getUserId().toString());
                    map.put("thirdConfigType", "loaner");
                    if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                        //	opraterBussinessDataService.umpayRepayment(map);
                    } else {
                        opraterBussinessDataService.advanceFinancialRepayment(map);
                    }
                }  else if (ThirdPayConstants.BT_OPENPAYAUTH.equals(thirdPayRecord.getInterfaceType())) {//无密还款授权
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("custermemberId", thirdPayRecord.getUserId().toString());//用户Id
                    map.put("bidId", thirdPayRecord.getBidId().toString());
                    if (thirdPayRecord.getThirdPayConfig().equals("moneyMoreMoreConfig")) {
                        map.put("AuthorizeTypeOpen", maps.get("AuthorizeTypeOpen").toString());//开启类型
                        map.put("AuthorizeTypeClose", maps.get("AuthorizeTypeClose").toString());//关闭类型
                        opraterBussinessDataService.mmmoreLoanAuthorize(map);
                    } else if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                        map.put("open", ThirdPayConstants.BT_OPENPAYAUTH);//开启类型
                        map.put("close", "");//关闭类型
                        opraterBussinessDataService.umpayLoanAuthorize(map);
                    } else {
                        map.put("orderNo", "");
                        opraterBussinessDataService.repaymentAuthorization(map);
                    }

                } else if (ThirdPayConstants.BT_CLOSEPAYAUTH.equals(thirdPayRecord.getInterfaceType())) {//解除无密还款授权
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("custermemberId", thirdPayRecord.getUserId().toString());//用户Id
                    map.put("bidId", thirdPayRecord.getBidId().toString());
                    map.put("ThirdPayConfigId", thirdPayRecord.getThirdPayFlagId());
                    if (thirdPayRecord.getThirdPayConfig().equals("moneyMoreMoreConfig")) {
                        map.put("AuthorizeTypeOpen", maps.get("AuthorizeTypeOpen").toString());//开启类型
                        map.put("AuthorizeTypeClose", maps.get("AuthorizeTypeClose").toString());//关闭类型
                        opraterBussinessDataService.mmmoreLoanAuthorize(map);
                    } else if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                        map.put("open", "");//开启类型
                        map.put("close", ThirdPayConstants.BT_CLOSEPAYAUTH);//关闭类型
                        opraterBussinessDataService.umpayLoanAuthorize(map);
                    } else {
                        map.put("oprateType", "cancel");
                        opraterBussinessDataService.rAuthorization(map);
                    }
                } else if (ThirdPayConstants.BT_UPDATEPHONE.equals(thirdPayRecord.getInterfaceType())) {//修改手机号
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("userId", thirdPayRecord.getUserId().toString());
                    map.put("newTelphone", commonResponse.getNewMobile());
                    map.put("dealstatus", "2");//表示修改成功
                    opraterBussinessDataService.chageMobile(map);
                } else if (ThirdPayConstants.BT_MODIFY.equals(thirdPayRecord.getInterfaceType())) {//修改企业信息
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("userId", thirdPayRecord.getUserId().toString());
                    map.put("newTelphone", commonResponse.getNewMobile());
                    map.put("artificialRealName", commonResponse.getTruename());
                    map.put("artificialIdentityCode", commonResponse.getCardCode());
                    map.put("modifyType", commonResponse.getModifyType());
                    map.put("corpName", commonResponse.getCorpName());
                    map.put("dealstatus", "2");//表示修改成功
                    System.out.println("修改企业信息map=" + map.toString());
                    opraterBussinessDataService.modifyEnterprise(map);
                } else if (ThirdPayConstants.BT_MMPLATFORM.equals(thirdPayRecord.getInterfaceType())) {//理财计划购买(收款账户为平台)
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("custmerType", "0");
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("dealRecordStatus", "2");
                    if (commonResponse.getLoanNo() != null && !commonResponse.getLoanNo().equals("")) {
                        map.put("loanNo", commonResponse.getLoanNo());//第三方返回的流水号
                    }
                    opraterBussinessDataService.bidMoneyPlan(map);
                } else if (ThirdPayConstants.BT_MMUSER.equals(thirdPayRecord.getInterfaceType())) {//理财计划购买(收款账户为用户)
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("custmerType", "0");
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                    if (commonResponse.getLoanNo() != null && !commonResponse.getLoanNo().equals("")) {
                        map.put("loanNo", commonResponse.getLoanNo());//第三方返回的流水号
                    }
                    opraterBussinessDataService.bidMoneyPlan(map);
                } else if (ThirdPayConstants.BT_OPENBIDAUTH.equals(thirdPayRecord.getInterfaceType())) {//自动投标授权
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("ThirdPayConfigId", thirdPayRecord.getThirdPayFlagId());//第三方方标识
                    map.put("custermemberId", thirdPayRecord.getUserId().toString());//用户Id
                    if (thirdPayRecord.getThirdPayConfig().equals("moneyMoreMoreConfig")) {
                        map.put("AuthorizeTypeOpen", maps.get("AuthorizeTypeOpen").toString());//开启类型
                        map.put("AuthorizeTypeClose", maps.get("AuthorizeTypeClose").toString());//关闭类型
                        opraterBussinessDataService.mmmoreLoanAuthorize(map);
                    } else if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                        map.put("open", ThirdPayConstants.BT_OPENBIDAUTH);//开启类型
                        map.put("close", "");//关闭类型
                        opraterBussinessDataService.umpayLoanAuthorize(map);
                    } else {
                        opraterBussinessDataService.bidingAuthorization(map);
                    }
                } else if (ThirdPayConstants.BT_CLOSEBIDAUTH.equals(thirdPayRecord.getInterfaceType())) {//关闭自动投标授权
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("ThirdPayConfigId", thirdPayRecord.getThirdPayFlagId());//第三方标识
                    map.put("custermemberId", thirdPayRecord.getUserId().toString());//用户id
                    if (thirdPayRecord.getThirdPayConfig().equals("moneyMoreMoreConfig")) {
                        map.put("AuthorizeTypeOpen", maps.get("AuthorizeTypeOpen").toString());//开启类型
                        map.put("AuthorizeTypeClose", maps.get("AuthorizeTypeClose").toString());//关闭类型
                        opraterBussinessDataService.mmmoreLoanAuthorize(map);
                    } else if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                        map.put("open", "");//开启类型
                        map.put("close", ThirdPayConstants.BT_CLOSEBIDAUTH);//关闭类型
                        opraterBussinessDataService.umpayLoanAuthorize(map);
                    } else {
                        opraterBussinessDataService.closeBidingAuthorization(map);
                    }
                } else if (ThirdPayConstants.BT_HANGDEAL.equals(thirdPayRecord.getInterfaceType())) {//挂牌交易
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("requestNo", commonResponse.getRequestNo());
                    if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                        map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                    } else {
                        map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_7.toString());
                    }
                    map.put("loanNo", thirdPayRecord.getThirdRecordNumber() == null ? "" : thirdPayRecord.getThirdRecordNumber());//第三方返回的流水号
                    opraterBussinessDataService.doObligationPublish(map);
                } else if (ThirdPayConstants.BT_BUYDEAL.equals(thirdPayRecord.getInterfaceType())) {//购买债权
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("orderDate", commonResponse.getOrderDate());
                    map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                    map.put("fee", commonResponse.getFee());//手续费
                    map.put("feeType", commonResponse.getModifyType());//转让手续费方式  1:转让人出2：承接人出
                    map.put("amount", commonResponse.getAmount());//承接金额
                    map.put("receivedAmount", commonResponse.getReceivedAmount());//认购金额

                    opraterBussinessDataService.doObligationDeal(map);
                } else if (ThirdPayConstants.BT_HRBIN.equals(thirdPayRecord.getInterfaceType())) {// 互融宝转入申请
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("requestNo", commonResponse.getRequestNo());
                    if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig") || thirdPayRecord.getThirdPayConfig().equals("HuifuConfig")) {
                        map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                    } else {
                        map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_7.toString());
                    }
                    map.put("loanNo", thirdPayRecord.getThirdRecordNumber() == null ? "" : thirdPayRecord.getThirdRecordNumber());//第三方返回的流水号
                    opraterBussinessDataService.doFianceProductBuy(map);
                } else if (ThirdPayConstants.BT_REPLACEMONEY.equals(thirdPayRecord.getInterfaceType())) {// 代偿还款申请
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("requestNo", commonResponse.getRequestNo());
                    if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig") || thirdPayRecord.getThirdPayConfig().equals("HuifuConfig")) {
                        map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                    } else {
                        map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_7.toString());
                    }
                    map.put("loanNo", thirdPayRecord.getThirdRecordNumber() == null ? "" : thirdPayRecord.getThirdRecordNumber());//第三方返回的流水号
                    opraterBussinessDataService.doCompensatory(map);
                } else if (ThirdPayConstants.BT_AUTOLOAN.equals(thirdPayRecord.getInterfaceType())) {//放款授权
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("custermemberId", thirdPayRecord.getUserId().toString());//用户Id
                    if (thirdPayRecord.getThirdPayConfig().equals("moneyMoreMoreConfig")) {
                        map.put("AuthorizeTypeOpen", maps.get("AuthorizeTypeOpen").toString());//开启类型
                        map.put("AuthorizeTypeClose", maps.get("AuthorizeTypeClose").toString());//关闭类型
                        opraterBussinessDataService.mmmoreLoanAuthorize(map);
                    }
                }
                webMsgInstance("0", Constants.CODE_SUCCESS, thirdPayRecord.getInterfaceName() + "申请成功", "", "", "", "", "");
            } else if (thirdPayRecord != null && commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_RETURN)) {//交易退回(双乾提现成功后退回)
                if (ThirdPayConstants.BT_WITHDRAW.equals(thirdPayRecord.getInterfaceType())) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                    if (commonResponse.getFeeUser() != null) {
                        map.put("feeUser", commonResponse.getFeeUser().toString());
                    } else {
                        map.put("feeUser", new BigDecimal(0).toString());
                    }
                    if (commonResponse.getFeePlatform() != null) {
                        map.put("feePlatform", commonResponse.getFeePlatform().toString());
                    } else {
                        map.put("feePlatform", new BigDecimal(0).toString());
                    }
                    opraterBussinessDataService.withDraw(map);
                }
                webMsgInstance("0", Constants.CODE_FAILED, thirdPayRecord.getInterfaceName() + "提现退回", "", "", "", "", "");
            } else if (thirdPayRecord != null && commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_ISNOTPASSSIGN)) {//签名验证失败
                webMsgInstance("0", Constants.CODE_FAILED, thirdPayRecord.getInterfaceName() + ":签名验证失败", "", "", "", "", "");
            } else if (thirdPayRecord != null && commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {//交易处理中
                if (ThirdPayConstants.BT_BINDCARD.equals(thirdPayRecord.getInterfaceType())) {//绑定银行卡
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("custermemberId", thirdPayRecord.getUserId().toString());
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("bankCardNo", commonResponse.getBankCode());//银行卡号
                    map.put("bankCode", commonResponse.getBankCardNumber());//银行代码
                    map.put("bankName", commonResponse.getBankName());//银行名称
                    map.put("bankType", commonResponse.getBankCardType());//银行卡类型
                    map.put("province", commonResponse.getProvince());//省份
                    map.put("city", commonResponse.getCity());//城市
                    if (commonResponse.getActiveStatus().equals("1")) {//成功
                        map.put("bankstatus", "");
                    } else if (commonResponse.getActiveStatus().equals("2")) {//处理中
                        map.put("bankstatus", WebBankcard.BINDCARD_STATUS_ACCEPT);
                    } else {//失败
                        map.put("bankstatus", WebBankcard.BINDCARD_STATUS_FAILD);
                    }
                    opraterBussinessDataService.bandCard(map);
                } else if (ThirdPayConstants.BT_RECHAGE.equals(thirdPayRecord.getInterfaceType())) {//充值处理中
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("receivedAmount", commonResponse.getReceivedAmount());//实际到账金额
                    map.put("requestNo", commonResponse.getRequestNo());
                    map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_9.toString());
                    if (commonResponse.getBankCode() != null) {//银行卡号
                        map.put("bankCode", commonResponse.getBankCode());
                    }
                    if (commonResponse.getBankCardNumber() != null) {//银行代码
                        map.put("bankCardNumber", commonResponse.getBankCardNumber());
                    }
                    opraterBussinessDataService.recharge(map);
                } else if (ThirdPayConstants.BT_EREGISTER.equals(thirdPayRecord.getInterfaceType())) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("custermemberId", thirdPayRecord.getUserId().toString());
                    map.put("custermemberType", "1");
                    map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_1.toString());
                    opraterBussinessDataService.regeditBus(map);
                }
                if (StringUtils.isNotEmpty(thirdPayRecord.getInterfaceName()) && StringUtils.isNotEmpty(commonResponse.getResponseMsg())) {
                    webMsgInstance("0", Constants.CODE_SUCCESS, thirdPayRecord.getInterfaceName() + ":" + commonResponse.getResponseMsg(), "", "", "", "", "");
                } else {
                    webMsgInstance("0", Constants.CODE_SUCCESS, "申请成功！！", "", "", "", "", "");
                }
            } else {
                if (thirdPayRecord != null && commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_FAILD)) {
                    //债权交易失败以后要 做对应的操作
                    if (ThirdPayConstants.BT_BUYDEAL.equals(thirdPayRecord.getInterfaceType())) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("requestNo", commonResponse.getRequestNo());
                        map.put("orderDate", commonResponse.getOrderDate());
                        map.put("dealRecordStatus", "0");
                        map.put("thirdPayConfig", thirdPayRecord.getThirdPayConfig());
                        if (thirdPayRecord.getThirdPayConfig().equals("yeepayConfig")) {
                            opraterBussinessDataService.yeepayObligationDeal(map);
                        } else {
                            opraterBussinessDataService.doObligationDeal(map);
                        }
                    } else if (ThirdPayConstants.BT_RECHAGE.equals(thirdPayRecord.getInterfaceType())) {//充值失败
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("receivedAmount", commonResponse.getReceivedAmount());//实际到账金额
                        map.put("requestNo", commonResponse.getRequestNo());
                        map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_3.toString());
                        if (commonResponse.getBankCode() != null) {//银行卡号
                            map.put("bankCode", commonResponse.getBankCode());
                        }
                        if (commonResponse.getBankCardNumber() != null) {//银行代码
                            map.put("bankCardNumber", commonResponse.getBankCardNumber());
                        }
                        opraterBussinessDataService.recharge(map);
                    } else if (ThirdPayConstants.BT_EREGISTER.equals(thirdPayRecord.getInterfaceType())) {//企业开通第三方
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("custermemberId", thirdPayRecord.getUserId().toString());
                        map.put("custermemberType", "1");
                        map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_3.toString());
                        opraterBussinessDataService.regeditBus(map);
                    } else if (ThirdPayConstants.BT_BINDCARD.equals(thirdPayRecord.getInterfaceType())) {//绑定银行卡
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("custermemberId", thirdPayRecord.getUserId().toString());
                        map.put("requestNo", commonResponse.getRequestNo());
                        map.put("bankCardNo", commonResponse.getBankCode());//银行卡号
                        map.put("bankCode", commonResponse.getBankCardNumber());//银行代码
                        map.put("bankName", commonResponse.getBankName());//银行名称
                        map.put("bankType", commonResponse.getBankCardType());//银行卡类型
                        map.put("province", commonResponse.getProvince());//省份
                        map.put("city", commonResponse.getCity());//城市
                        map.put("bankstatus", WebBankcard.BINDCARD_STATUS_FAILD);
                        opraterBussinessDataService.bandCard(map);
                    }
                }

                ThirdPayLog log = thirdPayLogService.getByUniqueId(thirdPayRecord.getUniqueId());
                if (log != null) {
                    log.setStatus(3);
                    thirdPayLogService.merge(log);
                }
                if (StringUtils.isNotEmpty(thirdPayRecord.getInterfaceName()) && StringUtils.isNotEmpty(commonResponse.getResponseMsg())) {
                    webMsgInstance("0", Constants.CODE_FAILED, thirdPayRecord.getInterfaceName() + ":" + commonResponse.getResponseMsg(), "", "", "", "", "");
                } else {
                    webMsgInstance("0", Constants.CODE_FAILED, "操作失败", "", "", "", "", "");
                }
            }


            //手机端跳转  此处获取不到isMobile
            String mobileDevice = HttpRequestDeviceUtils.isMobileDevice1(getRequest());
            if ("1".equals(mobileDevice)  ) {//ret
                //this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/back_msg.ftl");
                getRequest().setAttribute("attr", maps);
                return "back_msg1";
            } else if ("2".equals(mobileDevice) ) {
                getRequest().setAttribute("attr", maps);
                return "back_msg";
                //this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/back_msg.ftl");
            } else {
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
            }
            return "freemarker";
        } catch (Exception e) {
            e.printStackTrace();
            String mobileDevice = HttpRequestDeviceUtils.isMobileDevice1(getRequest());
            if("1".equals(mobileDevice)){
                this.getRequest().setAttribute("mes","系统异常！");
                return "error_message";
            }
            webMsgInstance("0", Constants.CODE_FAILED, "系统发生异常，请联系管理员！", "", "", "", "", "");
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
            return "freemarker";
        }
    }


    /**
     * 服务器端回调通知
     *
     * @return 由于每家第三方return对象不同    return  对象来自每家第三方接口返	回对象
     */
    @SuppressWarnings("rawtypes")
    public String notifyUrl() {
        Map maps = createResponseMap(this.getRequest());
        System.out.println("服务器回调通知maps=" + maps);
        //解析第三方返回值
        CommonResponse commonResponse = ThirdPayInterfaceUtil.returnPageOprate(maps, this.getRequest());
        //更新第三方日志
        thirdPayRecordService.insertOrUpdateLog(null, commonResponse);
        //根据  commonResponse.getRequestNo()流水号 查询第三方记录判断实际业务类型
        ThirdPayRecord thirdPayRecord = null;
        if (commonResponse.getRequestNo() != null) {
            thirdPayRecord = thirdPayRecordService.getByOrderNo(commonResponse.getRequestNo());
            if( null != thirdPayRecord) {
                thirdPayRecordService.evict(thirdPayRecord);
            }
        }
        //获取响应编码，选择消息展示模式（是成功标识，还是失败标识）
        if (thirdPayRecord != null && commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {//交易成功
            if (ThirdPayConstants.BT_PREGISTER.equals(thirdPayRecord.getInterfaceType())) {//个人开通第三方
                Map<String, String> map = new HashMap<String, String>();
//					map.put("platformUserNo", thirdPayRecord.getThirdPayFlagId().toString());//第三方账号
                map.put("custermemberId", thirdPayRecord.getUserId().toString());
                map.put("custermemberType", "0");
                if (commonResponse.getThirdPayConfigId() != null && !"".equals(commonResponse.getThirdPayConfigId())) {
//					map.put("thirdPayFlagId", commonResponse.getThirdPayConfigId());//
                    map.put("platformUserNo", commonResponse.getThirdPayConfigId());//第三方账号
                }
                map.put("platFormUserName", commonResponse.getThirdPayConfigId0() == null ? "" : commonResponse.getThirdPayConfigId0());
                map.put("mobilePhone", commonResponse.getMobilePhone());
                opraterBussinessDataService.regedit(map);

                if (StringUtils.isNotEmpty(commonResponse.getBankName()) && StringUtils.isNotEmpty(commonResponse.getBankCode())) {
                    map.put("requestNo", commonResponse.getRequestNo() + "_1");
                    map.put("bankCardNo", commonResponse.getBankCode());//银行卡号
                    map.put("bankCode", commonResponse.getBankCardNumber());//银行代码
                    map.put("bankName", commonResponse.getBankName());//银行名称
                    map.put("bandType", "1");
//						map.put("bankType", commonResponse.getBankCardType());//银行卡类型
//						map.put("province", commonResponse.getProvince());//省份
//						map.put("city", commonResponse.getCity());//城市
                    map.put("bankstatus", "");
                    System.out.println("实名认证进来的绑卡，参数map:" + map.toString());
                    opraterBussinessDataService.bandCard(map);
                } else {
                    System.out.println("获取卡号为空！！");
                }
            } else if (ThirdPayConstants.BT_EREGISTER.equals(thirdPayRecord.getInterfaceType())) {//企业开通第三方
//                Map<String ,String> map=new HashMap<String,String>();
//                map.put("platformUserNo", commonResponse.getThirdPayConfigId());//第三方账号accountNo
//                map.put("custermemberId", thirdPayRecord.getUserId().toString());
//                map.put("custermemberType", "0");
//                //userName
//                map.put("platFormUserName",commonResponse.getThirdPayConfigId0()==null?"":commonResponse.getThirdPayConfigId0());
//                opraterBussinessDataService.regedit(map);
                Map<String, String> map = new HashMap<String, String>();
                map.put("platformUserNo", commonResponse.getThirdPayConfigId());
                map.put("custermemberId", thirdPayRecord.getUserId().toString());
                map.put("custermemberType", "1");
                map.put("platFormUserName", commonResponse.getThirdPayConfigId0() == null ? "" : commonResponse.getThirdPayConfigId0());
                map.put("trueName", commonResponse.getTruename());//企业名称
                map.put("cardCode", commonResponse.getCardCode());//统一社会信用代码
                map.put("corpType", commonResponse.getRoleType());//公司类型  1：借款企业；2：担保公司 3 出借企业
                map.put("artificialIdentityCode", commonResponse.getArtificialIdentityCode());//法人证件号码
                map.put("artificialRealName", commonResponse.getArtificialRealName());//法人代表真实姓名
                map.put("licenceCode", commonResponse.getLicenceCode());//营业执照编号
                map.put("mobilePhone", commonResponse.getMobilePhone());//法人手机号
                map.put("orgCode", commonResponse.getOrgCode());//组织机构代码
                map.put("taxRegCode", commonResponse.getTaxRegCode());//税务登记号
                map.put("threeCertUnit", commonResponse.getThreeCertUnit());//是否三证合一    0否1是
                map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                opraterBussinessDataService.regeditBus(map);
                if (StringUtils.isNotEmpty(commonResponse.getBankName()) && StringUtils.isNotEmpty(commonResponse.getBankCode())) {
                    map.put("requestNo", commonResponse.getRequestNo() + "_1");
                    map.put("bankCardNo", commonResponse.getBankCode());//银行卡号
                    map.put("bankCode", commonResponse.getBankCardNumber());//银行代码
                    map.put("bankName", commonResponse.getBankName());//银行名称
                    map.put("bandType", "1");
                    map.put("bankstatus", "");
                    System.out.println("实名认证进来的绑卡，参数map:" + map.toString());
                    opraterBussinessDataService.bandCard(map);
                }
            } else if (ThirdPayConstants.BT_BINDCARD.equals(thirdPayRecord.getInterfaceType())) {//绑定银行卡
                Map<String, String> map = new HashMap<String, String>();
                map.put("custermemberId", thirdPayRecord.getUserId().toString());
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("bankCardNo", commonResponse.getBankCode());//银行卡号
                map.put("bankCode", commonResponse.getBankCardNumber());//银行代码
                map.put("bankName", commonResponse.getBankName());//银行名称
                map.put("bankType", commonResponse.getBankCardType());//银行卡类型
                map.put("province", commonResponse.getProvince());//省份
                map.put("city", commonResponse.getCity());//城市
                if (commonResponse.getActiveStatus().equals("1")) {//成功
                    map.put("bankstatus", "");
                } else if (commonResponse.getActiveStatus().equals("2")) {//处理中
                    map.put("bankstatus", WebBankcard.BINDCARD_STATUS_ACCEPT);
                } else {//失败
                    map.put("bankstatus", WebBankcard.BINDCARD_STATUS_FAILD);
                }
                opraterBussinessDataService.bandCard(map);
            } else if (ThirdPayConstants.BT_CANCELCARD.equals(thirdPayRecord.getInterfaceType())) {//取消绑定银行卡
                Map<String, String> map = new HashMap<String, String>();
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("bankstatus", "bindCard_status_cancel");
                map.put("custermemberId", thirdPayRecord.getUserId().toString());
                if (commonResponse.getBankCode() != null) {
                    map.put("bankCode", commonResponse.getBankCode());
                }
                opraterBussinessDataService.cancelBindBank(map);
            } else if (ThirdPayConstants.BT_CANCELCARDBIND.equals(thirdPayRecord.getInterfaceType())) {//企业取消绑定银行卡
                Map<String, String> map = new HashMap<String, String>();
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("bankstatus", "bindCard_status_cancel");
                map.put("custermemberId", thirdPayRecord.getUserId().toString());
                if (commonResponse.getBankCode() != null) {
                    map.put("bankCode", commonResponse.getBankCode());
                }
                opraterBussinessDataService.cancelBindBankCard(map);
            } else if (ThirdPayConstants.BT_REPLACECARD.equals(thirdPayRecord.getInterfaceType())) {//更换银行卡
                Map<String, String> map = new HashMap<String, String>();
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("bankCardNo", commonResponse.getBankCode());//银行卡号
                map.put("bankCode", commonResponse.getBankCardNumber());//银行代码
                map.put("bankName", commonResponse.getBankName());//银行名称
                map.put("bankType", commonResponse.getBankCardType());//银行卡类型
                map.put("province", commonResponse.getProvince());//省份
                map.put("city", commonResponse.getCity());//城市
                if (StringUtils.isNotEmpty(commonResponse.getActiveStatus())) {
                    if ("1".equals(commonResponse.getActiveStatus())) {
                        map.put("bankstatus", WebBankcard.BINDCARD_STATUS_SUCCESS);
                    } else if ("0".equals(commonResponse.getActiveStatus())) {
                        map.put("bankstatus", WebBankcard.BINDCARD_STATUS_FAILD);
                    } else {
                        map.put("bankstatus", WebBankcard.BINDCARD_STATUS_ACCEPT);
                    }
                }

                opraterBussinessDataService.replaceCard(map);
            } else if (ThirdPayConstants.BT_RECHAGE.equals(thirdPayRecord.getInterfaceType())) {//充值成功
                Map<String, String> map = new HashMap<String, String>();
                map.put("receivedAmount", commonResponse.getReceivedAmount());//实际到账金额
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                if (commonResponse.getBankCode() != null) {//银行卡号
                    map.put("bankCode", commonResponse.getBankCode());
                }
                if (commonResponse.getBankCardNumber() != null) {//银行代码
                    map.put("bankCardNumber", commonResponse.getBankCardNumber());
                }
                opraterBussinessDataService.recharge(map);
            } else if (ThirdPayConstants.BT_APPQRECHARGE.equals(thirdPayRecord.getInterfaceType())) {//个人免登陆app快速充值
                Map<String, String> map = new HashMap<String, String>();
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                opraterBussinessDataService.recharge(map);
            } else if (ThirdPayConstants.BT_BID.equals(thirdPayRecord.getInterfaceType())) {//投标成功
                Map<String, String> map = new HashMap<String, String>();
                map.put("custmerType", "0");
                if (commonResponse.getRequestNo() != null && !commonResponse.getRequestNo().equals("")) {
                    map.put("requestNo", commonResponse.getRequestNo());
                }
				/*if(commonResponse.getRemark2()!=null&&!commonResponse.getRemark2().equals("")){
					map.put("trxId",commonResponse.getRemark2());
				}
				if(commonResponse.getContract_no()!=null&&!"".equals(commonResponse.getContract_no())){
					map.put("contract_no", commonResponse.getContract_no());
				}
				if(commonResponse.getRemark1()!=null&&!commonResponse.getRemark1().equals("")){
					map.put("freezeTrx", commonResponse.getRemark1());//冻结的唯一标识
				}
				
				if(commonResponse.getLoanNo()!=null&&!commonResponse.getLoanNo().equals("")){
					map.put("loanNo", commonResponse.getLoanNo());
				}*/
                map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                opraterBussinessDataService.biding(map);//散标投资
            }else if (ThirdPayConstants.BT_REPAYMENT.equals(thirdPayRecord.getInterfaceType())) {//p2p立即返款

                Map<String, String> map = new HashMap<String, String>();
                map.put("bidId", thirdPayRecord.getBidId().toString());//标id
                map.put("peridId", thirdPayRecord.getRemark1());
                map.put("intentDate", thirdPayRecord.getIntentDate().toString());//计划还款日期
                map.put("requestTime", thirdPayRecord.getRequestTime().toString());//请求接口时间
                map.put("requestNum", thirdPayRecord.getRequestNum().toString());//请求接口次数
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("thirdPayConfig", thirdPayRecord.getThirdPayConfig());
                map.put("thirdConfigType", "loaner");


                //还款完成以后，先调用投资人回款
                List<InvestBean> list = new ArrayList<InvestBean>();
                CommonRequst commonQ = new CommonRequst();
                String orderNumFirst = ContextUtil.createRuestNumber();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                commonQ.setBussinessType(ThirdPayConstants.BT_CALLBACK);//业务类型
                commonQ.setTransferName(ThirdPayConstants.TN_CALLBACK);//名称
                commonQ.setLoanNoList(thirdPayRecord.getRemark2());//三方标的标识
                //查询还款记录
                List<CommonRequestInvestRecord> repayList = investPersonInfoService.getRepaymentList(thirdPayRecord.getBidId().toString(), thirdPayRecord.getRemark1());
                //获取参数
                for (CommonRequestInvestRecord record : repayList) {
                    /*
                     * 扣款顺序：本金+利息从标的账户号扣除给投资人账户，然后从投资人账户扣除利息管理费给商户账户，最后从商户账户
                     * 扣除加息利息费用到投资人账户
                     */
                    String orderNum = ContextUtil.createRuestNumber();
                    InvestBean invest = new InvestBean();

                    if (record.getPrincipal().compareTo(BigDecimal.ZERO) == 0) {
                        invest.setCapital("0");//本金
                    } else {
                        invest.setCapital(record.getPrincipal().toString());//本金
                    }
                    if (record.getInterest().compareTo(BigDecimal.ZERO) == 0) {
                        invest.setInterest("0");//利息
                    } else {
                        if (record.getAccrual().compareTo(BigDecimal.ZERO) == 0) {//如果有罚息，加上罚息
                            invest.setInterest((record.getInterest().add(record.getAccrual())).toString());
                        } else {//如果没有罚息，只用转利息就可以
                            invest.setInterest(record.getInterest().toString());//利息
                        }
                    }

                    invest.setRateInterest("0");//加息利息

//					invest.setCapital(record.getPrincipal().toString());//本金
//					invest.setInterest(record.getInterest().toString());//利息
//					invest.setRateInterest(record.getAccrual().toString());//罚息

//					invest.setInterestFee(record.getFee().toString());//管理费
                    invest.setInterestFee("0");//管理费，这个费用是投资人给平台的
                    invest.setInvestAccountNo(record.getInvest_thirdPayConfigId());//三方账号
                    invest.setInvestUserName(record.getInvest_thirdPayConfigId0());//三方有用户名
                    invest.setInvestOrderDate(sdf.format(record.getRequestDate()));//原有投资订单日期
                    invest.setInvestOrderNo(record.getBidRequestNo());//原有投资订单号
                    invest.setOrderDate(sdf.format(new Date()));//当前交易订单日期，格式yyyyMMdd
                    invest.setOrderNo(orderNum);//当前流水订单号
                    list.add(invest);
                }
                commonQ.setInvestList(list);
                commonQ.setRequsetNo(orderNumFirst);
                commonQ.setThirdPayConfigId(null);
                commonQ.setThirdPayConfigId0(null);
                CommonResponse thirdCommon = ThirdPayInterfaceUtil.thirdCommon(commonQ);
                if (thirdCommon.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
                    if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                        opraterBussinessDataService.umpayRepayment(map);
                    } else if (thirdPayRecord.getThirdPayConfig().equals("yeepayConfig")) {
                        opraterBussinessDataService.yeepayRepayment(map);
                    } else {
                        System.out.println("执行还款方法");
                        opraterBussinessDataService.repayment(map);
                    }
                }
            } else if (ThirdPayConstants.BT_BEFOREPAY.equals(thirdPayRecord.getInterfaceType())) {//提前还款
                Map<String, String> map = new HashMap<String, String>();
                map.put("bidId", thirdPayRecord.getBidId().toString());//标id
                map.put("intentDate", thirdPayRecord.getIntentDate().toString());//计划还款日期
                map.put("requestTime", thirdPayRecord.getRequestTime().toString());//请求接口时间
                map.put("requestNum", thirdPayRecord.getRequestNum().toString());//请求接口次数
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("thirdPayConfig", thirdPayRecord.getThirdPayConfig());
                map.put("slEarlyRepaymentId", thirdPayRecord.getRemark1());
                map.put("thirdConfigType", "loaner");

                //还款完成以后，先调用投资人回款
                List<InvestBean> list = new ArrayList<InvestBean>();
                CommonRequst commonQ = new CommonRequst();
                String orderNumFirst = ContextUtil.createRuestNumber();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                commonQ.setBussinessType(ThirdPayConstants.BT_CALLBACK);//业务类型
                commonQ.setTransferName(ThirdPayConstants.TN_CALLBACK);//名称
                commonQ.setLoanNoList(thirdPayRecord.getRemark2());//三方标的标识
                //查询还款记录
                List<CommonRequestInvestRecord> repayList = investPersonInfoService.getRepayEarlymentList(thirdPayRecord.getBidId().toString(), thirdPayRecord.getRemark1());
                //获取参数
                for (CommonRequestInvestRecord record : repayList) {
                    String orderNum = ContextUtil.createRuestNumber();
                    InvestBean invest = new InvestBean();
                    invest.setCapital(record.getPrincipal().toString());//本金
                    invest.setInterest(record.getInterest().toString());//利息
//					invest.setInterestFee(record.getFee().toString());//管理费
                    invest.setInterestFee("0.00");//管理费，这个费用是投资人给平台的
                    invest.setRateInterest("0.00");//罚息  提前还款没有罚息
                    invest.setInvestAccountNo(record.getInvest_thirdPayConfigId());//三方账号
                    invest.setInvestUserName(record.getInvest_thirdPayConfigId0());//三方有用户名
                    invest.setInvestOrderDate(sdf.format(record.getRequestDate()));//原有投资订单日期
                    invest.setInvestOrderNo(record.getBidRequestNo());//原有投资订单号
                    invest.setOrderDate(sdf.format(new Date()));//当前交易订单日期，格式yyyyMMdd
                    invest.setOrderNo(orderNum);//当前流水订单号
                    list.add(invest);
                }
                commonQ.setInvestList(list);
                commonQ.setRequsetNo(orderNumFirst);
                commonQ.setThirdPayConfigId(null);
                commonQ.setThirdPayConfigId0(null);
                CommonResponse thirdCommon = ThirdPayInterfaceUtil.thirdCommon(commonQ);
                if (thirdCommon.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
                    opraterBussinessDataService.repayment(map);
                }

            } else if (ThirdPayConstants.BT_MMBACKMONEY.equals(thirdPayRecord.getInterfaceType())) {//UD计划收款账户为投资人还款
                Map<String, String> map = new HashMap<String, String>();
                map.put("bidId", thirdPayRecord.getBidId().toString());//标id
                map.put("intentDate", thirdPayRecord.getIntentDate().toString());//计划还款日期
                map.put("requestTime", thirdPayRecord.getRequestTime().toString());//请求接口时间
                map.put("requestNum", thirdPayRecord.getRequestNum().toString());//请求接口次数
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("thirdPayConfig", thirdPayRecord.getThirdPayConfig());
                map.put("thirdConfigType", "loaner");
                if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                    //	opraterBussinessDataService.umpayRepayment(map);
                } else {
                    opraterBussinessDataService.financialRepayment(map);
                }
            } else if (ThirdPayConstants.BT_MMADVANCEUSER.equals(thirdPayRecord.getInterfaceType())) {//UD计划收款账户为投资人提前赎回还款
                Map<String, String> map = new HashMap<String, String>();
                map.put("bidId", thirdPayRecord.getBidId().toString());//标id
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("thirdPayConfig", thirdPayRecord.getThirdPayConfig());
                map.put("dealMoney", thirdPayRecord.getDealMoney().toString());
                map.put("userId", thirdPayRecord.getUserId().toString());
                map.put("thirdConfigType", "loaner");
                if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                    //	opraterBussinessDataService.umpayRepayment(map);
                } else {
                    opraterBussinessDataService.advanceFinancialRepayment(map);
                }
            } else if (ThirdPayConstants.BT_WITHDRAW.equals(thirdPayRecord.getInterfaceType())) {//取现成功
                Map<String, String> map = new HashMap<String, String>();
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                map.put("receivedAmount", commonResponse.getReceivedAmount());//实际到账金额
                if (commonResponse.getFee() != null) {
                    map.put("feeUser", commonResponse.getFee().toString());
                } else {
                    map.put("feeUser", new BigDecimal(0).toString());
                }
                if (commonResponse.getFeePlatform() != null) {
                    map.put("feePlatform", commonResponse.getFeePlatform().toString());
                } else {
                    map.put("feePlatform", new BigDecimal(0).toString());
                }
                opraterBussinessDataService.withDraw(map);
            } else if (ThirdPayConstants.BT_OPENPAYAUTH.equals(thirdPayRecord.getInterfaceType())) {//无密还款授权
                Map<String, String> map = new HashMap<String, String>();
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("custermemberId", thirdPayRecord.getUserId().toString());//用户Id
                map.put("bidId", thirdPayRecord.getBidId().toString());
                if (thirdPayRecord.getThirdPayConfig().equals("moneyMoreMoreConfig")) {
                    map.put("AuthorizeTypeOpen", maps.get("AuthorizeTypeOpen").toString());//开启类型
                    map.put("AuthorizeTypeClose", maps.get("AuthorizeTypeClose").toString());//关闭类型
                    opraterBussinessDataService.mmmoreLoanAuthorize(map);
                } else if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                    map.put("open", ThirdPayConstants.BT_OPENPAYAUTH);//开启类型
                    map.put("close", "");//关闭类型
                    opraterBussinessDataService.umpayLoanAuthorize(map);
                } else {
                    map.put("orderNo", "");
                    opraterBussinessDataService.repaymentAuthorization(map);
                }

            } else if (ThirdPayConstants.BT_CLOSEPAYAUTH.equals(thirdPayRecord.getInterfaceType())) {//解除无密还款授权
                Map<String, String> map = new HashMap<String, String>();
                map.put("custermemberId", thirdPayRecord.getUserId().toString());//用户Id
                map.put("bidId", thirdPayRecord.getBidId().toString());
                map.put("ThirdPayConfigId", thirdPayRecord.getThirdPayFlagId());
                if (thirdPayRecord.getThirdPayConfig().equals("moneyMoreMoreConfig")) {
                    map.put("AuthorizeTypeOpen", maps.get("AuthorizeTypeOpen").toString());//开启类型
                    map.put("AuthorizeTypeClose", maps.get("AuthorizeTypeClose").toString());//关闭类型
                    opraterBussinessDataService.mmmoreLoanAuthorize(map);
                } else if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                    map.put("open", "");//开启类型
                    map.put("close", ThirdPayConstants.BT_CLOSEPAYAUTH);//关闭类型
                    opraterBussinessDataService.umpayLoanAuthorize(map);
                } else {
                    map.put("oprateType", "cancel");
                    opraterBussinessDataService.rAuthorization(map);
                }
            } else if (ThirdPayConstants.BT_UPDATEPHONE.equals(thirdPayRecord.getInterfaceType())) {//修改手机号
                Map<String, String> map = new HashMap<String, String>();
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("userId", thirdPayRecord.getUserId().toString());
                map.put("newTelphone", commonResponse.getNewMobile());
                map.put("dealstatus", "2");//表示修改成功
                opraterBussinessDataService.chageMobile(map);
            } else if (ThirdPayConstants.BT_MMPLATFORM.equals(thirdPayRecord.getInterfaceType())) {//理财计划购买(收款账户为平台)
                Map<String, String> map = new HashMap<String, String>();
                map.put("custmerType", "0");
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("dealRecordStatus", "2");
                if (commonResponse.getLoanNo() != null && !commonResponse.getLoanNo().equals("")) {
                    map.put("loanNo", commonResponse.getLoanNo());//第三方返回的流水号
                }
                opraterBussinessDataService.bidMoneyPlan(map);
            } else if (ThirdPayConstants.BT_MMUSER.equals(thirdPayRecord.getInterfaceType())) {//理财计划购买(收款账户为用户)
                Map<String, String> map = new HashMap<String, String>();
                map.put("custmerType", "0");
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                if (commonResponse.getLoanNo() != null && !commonResponse.getLoanNo().equals("")) {
                    map.put("loanNo", commonResponse.getLoanNo());//第三方返回的流水号
                }
                opraterBussinessDataService.bidMoneyPlan(map);
            } else if (ThirdPayConstants.BT_OPENBIDAUTH.equals(thirdPayRecord.getInterfaceType())) {//自动投标授权
                Map<String, String> map = new HashMap<String, String>();
                map.put("ThirdPayConfigId", thirdPayRecord.getThirdPayFlagId());//第三方方标识
                map.put("custermemberId", thirdPayRecord.getUserId().toString());//用户Id
                if (thirdPayRecord.getThirdPayConfig().equals("moneyMoreMoreConfig")) {
                    map.put("AuthorizeTypeOpen", maps.get("AuthorizeTypeOpen").toString());//开启类型
                    map.put("AuthorizeTypeClose", maps.get("AuthorizeTypeClose").toString());//关闭类型
                    opraterBussinessDataService.mmmoreLoanAuthorize(map);
                } else if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                    map.put("open", ThirdPayConstants.BT_OPENBIDAUTH);//开启类型
                    map.put("close", "");//关闭类型
                    opraterBussinessDataService.umpayLoanAuthorize(map);
                } else {
                    opraterBussinessDataService.bidingAuthorization(map);
                }
            } else if (ThirdPayConstants.BT_CLOSEBIDAUTH.equals(thirdPayRecord.getInterfaceType())) {//关闭自动投标授权
                Map<String, String> map = new HashMap<String, String>();
                map.put("ThirdPayConfigId", thirdPayRecord.getThirdPayFlagId());//第三方标识
                map.put("custermemberId", thirdPayRecord.getUserId().toString());//用户id
                if (thirdPayRecord.getThirdPayConfig().equals("moneyMoreMoreConfig")) {
                    map.put("AuthorizeTypeOpen", maps.get("AuthorizeTypeOpen").toString());//开启类型
                    map.put("AuthorizeTypeClose", maps.get("AuthorizeTypeClose").toString());//关闭类型
                    opraterBussinessDataService.mmmoreLoanAuthorize(map);
                } else if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig")) {
                    map.put("open", "");//开启类型
                    map.put("close", ThirdPayConstants.BT_CLOSEBIDAUTH);//关闭类型
                    opraterBussinessDataService.umpayLoanAuthorize(map);
                } else {
                    opraterBussinessDataService.closeBidingAuthorization(map);
                }
            } else if (ThirdPayConstants.BT_HANGDEAL.equals(thirdPayRecord.getInterfaceType())) {//挂牌交易
                Map<String, String> map = new HashMap<String, String>();
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                map.put("loanNo", thirdPayRecord.getThirdRecordNumber() == null ? "" : thirdPayRecord.getThirdRecordNumber());//第三方返回的流水号
                opraterBussinessDataService.doObligationPublish(map);
            } else if (ThirdPayConstants.BT_BUYDEAL.equals(thirdPayRecord.getInterfaceType())) {//购买债权
                Map<String, String> map = new HashMap<String, String>();
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                map.put("thirdPayConfig", thirdPayRecord.getThirdPayConfig());
                if (thirdPayRecord.getThirdPayConfig().equals("yeepayConfig")) {
                    opraterBussinessDataService.yeepayObligationDeal(map);
                } else {
                    opraterBussinessDataService.doObligationDeal(map);
                }
            } else if (ThirdPayConstants.BT_HRBIN.equals(thirdPayRecord.getInterfaceType())) {// 互融宝转入申请
                Map<String, String> map = new HashMap<String, String>();
                map.put("requestNo", commonResponse.getRequestNo());
                if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig") || thirdPayRecord.getThirdPayConfig().equals("HuifuConfig")) {
                    map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                } else {
                    map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_7.toString());
                }
                map.put("loanNo", thirdPayRecord.getThirdRecordNumber() == null ? "" : thirdPayRecord.getThirdRecordNumber());//第三方返回的流水号
                opraterBussinessDataService.doFianceProductBuy(map);
            } else if (ThirdPayConstants.BT_REPLACEMONEY.equals(thirdPayRecord.getInterfaceType())) {// 代偿还款申请
                Map<String, String> map = new HashMap<String, String>();
                map.put("requestNo", commonResponse.getRequestNo());
                if (thirdPayRecord.getThirdPayConfig().equals("umpayConfig") || thirdPayRecord.getThirdPayConfig().equals("HuifuConfig")) {
                    map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
                } else {
                    map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_7.toString());
                }
                map.put("loanNo", thirdPayRecord.getThirdRecordNumber() == null ? "" : thirdPayRecord.getThirdRecordNumber());//第三方返回的流水号
                opraterBussinessDataService.doCompensatory(map);
            } else if (ThirdPayConstants.BT_AUTOLOAN.equals(thirdPayRecord.getInterfaceType())) {//放款授权
                Map<String, String> map = new HashMap<String, String>();
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("custermemberId", thirdPayRecord.getUserId().toString());//用户Id
                if (thirdPayRecord.getThirdPayConfig().equals("moneyMoreMoreConfig")) {
                    map.put("AuthorizeTypeOpen", maps.get("AuthorizeTypeOpen").toString());//开启类型
                    map.put("AuthorizeTypeClose", maps.get("AuthorizeTypeClose").toString());//关闭类型
                    opraterBussinessDataService.mmmoreLoanAuthorize(map);
                }
            }
            webMsgInstance("0", Constants.CODE_SUCCESS, thirdPayRecord.getInterfaceName() + "申请成功", "", "", "", "", "");
        } else if (thirdPayRecord != null && commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
            if (ThirdPayConstants.BT_RECHAGE.equals(thirdPayRecord.getInterfaceType())) {//充值处理中
                Map<String, String> map = new HashMap<String, String>();
                map.put("receivedAmount", commonResponse.getReceivedAmount());//实际到账金额
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_9.toString());
                if (commonResponse.getBankCode() != null) {//银行卡号
                    map.put("bankCode", commonResponse.getBankCode());
                }
                if (commonResponse.getBankCardNumber() != null) {//银行代码
                    map.put("bankCardNumber", commonResponse.getBankCardNumber());
                }
                opraterBussinessDataService.recharge(map);
            }

        } else {
            if (ThirdPayConstants.BT_RECHAGE.equals(thirdPayRecord.getInterfaceType())) {//充值失败
                Map<String, String> map = new HashMap<String, String>();
                map.put("receivedAmount", commonResponse.getReceivedAmount());//实际到账金额
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_3.toString());
                if (commonResponse.getBankCode() != null) {//银行卡号
                    map.put("bankCode", commonResponse.getBankCode());
                }
                if (commonResponse.getBankCardNumber() != null) {//银行代码
                    map.put("bankCardNumber", commonResponse.getBankCardNumber());
                }
                opraterBussinessDataService.recharge(map);
            } else if (ThirdPayConstants.BT_EREGISTER.equals(thirdPayRecord.getInterfaceType())) {//企业开通第三方失败
                Map<String, String> map = new HashMap<String, String>();
                map.put("platformUserNo", commonResponse.getThirdPayConfigId());
                map.put("custermemberId", thirdPayRecord.getUserId().toString());
                map.put("custermemberType", "0");
                map.put("platFormUserName", commonResponse.getThirdPayConfigId0() == null ? "" : commonResponse.getThirdPayConfigId0());
                map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_3.toString());
                opraterBussinessDataService.regedit(map);
            } else if (ThirdPayConstants.BT_BINDCARD.equals(thirdPayRecord.getInterfaceType())) {//绑定银行卡
                Map<String, String> map = new HashMap<String, String>();
                map.put("custermemberId", thirdPayRecord.getUserId().toString());
                map.put("requestNo", commonResponse.getRequestNo());
                map.put("bankCardNo", commonResponse.getBankCode());//银行卡号
                map.put("bankCode", commonResponse.getBankCardNumber());//银行代码
                map.put("bankName", commonResponse.getBankName());//银行名称
                map.put("bankType", commonResponse.getBankCardType());//银行卡类型
                map.put("province", commonResponse.getProvince());//省份
                map.put("city", commonResponse.getCity());//城市
                if (commonResponse.getActiveStatus().equals("1")) {//成功
                    map.put("bankstatus", "");
                } else if (commonResponse.getActiveStatus().equals("2")) {//处理中
                    map.put("bankstatus", WebBankcard.BINDCARD_STATUS_ACCEPT);
                } else {//失败
                    map.put("bankstatus", WebBankcard.BINDCARD_STATUS_FAILD);
                }
                opraterBussinessDataService.bandCard(map);
            }
        }
        PrintWriter writer = null;
        try {
            writer = this.getResponse().getWriter();
            writer.write("success");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        return null;
    }

    /**
     * 准备将回调通知参数整合成map
     */
    public static Map createResponseMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration paramEnu = request.getParameterNames();
        String parameter = "";
        while (paramEnu.hasMoreElements()) {
            String paramName = (String) paramEnu.nextElement();
            String paramValue = new String(request.getParameter(paramName));
            map.put(paramName, paramValue);
            parameter = parameter + (paramName + "=" + paramValue + "&");
        }
        return map;
    }

    ;


    public String test() throws SecurityException {
        String a = "{\"accountNo\":\"UA0219320750\",\"capital\":10000.0,\"extMark\":\"hry20005\",\"interest\":0.0" +
                ",\"loanFee\":0.0,\"loanTxNo\":\"LU1367970407\",\"merchantNo\":\"M0536856896\",\"notifyUrl\":" +
                "\"http://47.94.173.28:80/thirdPay/notifyUrlThirdPayInterface.do\",\"orderDate\":\"20171025\",\"orderNo\"" +
                ":\"77620171025163252103\",\"returnUrl\":\"http://47.94.173.28:80/thirdPay/pageUrlThirdPayInterface.do\",\"userName\":" +
                "\"UU0671403234\"}";
        String b = this.getRequest().getParameter("b");
        String signReturn = FuDianInterfaceUtil.signReturn(b);
        System.out.println(signReturn);
        return SUCCESS;
    }

}
