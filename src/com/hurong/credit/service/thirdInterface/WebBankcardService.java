package com.hurong.credit.service.thirdInterface;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.model.webFeedBack.BankCard;

/**
 * 
 * @author 
 *
 */
public interface WebBankcardService extends BaseService<WebBankcard>{

	WebBankcard getByCardNum(String openAcctId);
	/**
	 * 根据卡号，状态查询银行卡
	 * @param openAcctId
	 * @param state
	 * @return
	 * 2017-11-6
	 * @tzw
	 */
	WebBankcard getByCardNumAndState(String openAcctId,String state);

	 public WebBankcard checkCardExit(WebBankcard webBankcard, BpCustMember mem);

	public List<WebBankcard> getBycusterId(Long id);
   
	public List<WebBankcard> getByUserId(Long id);
	
	public WebBankcard getByRequestNo(String requestNo);

	/**
	 * @param bpCustMember
	 * @param cardStatus
	 * @param cardNo
	 */
	public String  updateBankList(BpCustMember bpCustMember,String bankId, String cardStatus,String cardNo);
	/**
	 * 查询用户绑卡记录
	 * @param userId
	 * @param cardStatus
	 * @param remak
	 * @return
	 */
	public List<WebBankcard> getbanklist(Long userId,String cardStatus,String remak);

	/**
	 * 根据用户id,绑卡状态查询
	 * @param id
	 * @param bindcardStatusSuccess
	 * @return
	 * 2017-8-30
	 * @tzw
	 */
	List<WebBankcard> getBycustAndState(Long id, String bindcardStatusSuccess);


    List<BankCard> getBankCardList(Long id);
	//获取银行卡限额
	List<BankCard> getBankCardQuota();
}


