package com.hurong.credit.service.thirdInterface.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import javax.annotation.Resource;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.thirdInterface.WebBankcardDao;
import com.hurong.credit.dao.user.BpCustMemberDao;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.model.webFeedBack.BankCard;
import com.hurong.credit.service.thirdInterface.WebBankcardService;

/**
 * 
 * @author 
 *
 */
public class WebBankcardServiceImpl extends BaseServiceImpl<WebBankcard> implements WebBankcardService{
	@SuppressWarnings("unused")
	private WebBankcardDao dao;
	@Resource
	private  BpCustMemberDao bpCustMemberDao;
	
	public WebBankcardServiceImpl(WebBankcardDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public WebBankcard getByCardNum(String openAcctId) {
		return dao.getByCardNum(openAcctId);
	}

	
	
	@Override
	public WebBankcard getByCardNumAndState(String openAcctId, String state) {
		return dao.getByCardNumAndState(openAcctId,state);
	}

	@Override
	public WebBankcard checkCardExit(WebBankcard webBankcard, BpCustMember mem) {
		// TODO Auto-generated method stub
		return dao.checkCardExit(webBankcard,mem);
	}

	@Override
	public List<WebBankcard> getBycusterId(Long id) {
		// TODO Auto-generated method stub
		return dao.getBycusterId(id);
	}

	@Override
	public WebBankcard getByRequestNo(String requestNo) {
		// TODO Auto-generated method stub
		return dao.getByRequestNo(requestNo);
	}

	/**
	 * 更新用户绑定银行卡列表 (non-Javadoc)
	 * @see com.hurong.credit.service.thirdInterface.WebBankcardService#updateBankList(com.hurong.credit.model.user.BpCustMember, java.lang.String, java.lang.String)
	 */
	@Override
	public String updateBankList(BpCustMember bpCustMember,String bankId, String bankName,String cardNo) {
		try{
			BpCustMember bp=bpCustMemberDao.get(bpCustMember.getId());
			if(bp!=null&&bp.getThirdPayConfig()!=null&&!"".equals(bp.getThirdPayConfig())&&bp.getThirdPayFlagId()!=null&&!"".equals(bp.getThirdPayFlagId())){
				List<WebBankcard> listd=null;
				if(cardNo!=null&&!"".equals(cardNo)){
					String hql1="from WebBankcard as p where p.thirdConfig='"+bp.getThirdPayConfig()+"' and p.userFlg='"+bp.getThirdPayFlagId()+"' and p.customerId="+bp.getId()+" and p.cardNum='"+cardNo+"' and p.bindCardStatus='bindCard_status_success' order by p.cardId desc";
					List<WebBankcard> list=dao.findByHql(hql1, null);
					System.out.println(list==null||list.size()<=0);
					if(list==null||list.size()<=0){//判断是否有已经存在已经成功绑定的银行卡
						String hql="from WebBankcard as p where p.thirdConfig='"+bp.getThirdPayConfig()+"' and p.userFlg='"+bp.getThirdPayFlagId()+"' and p.customerId="+bp.getId()+" and p.bindCardStatus in ('bindCard_status_accept','bindCard_status_repare') order by p.cardId desc";
						List<WebBankcard> list2=dao.findByHql(hql, null);
						if(list2!=null&&list2.size()>0){
							WebBankcard web=list2.get(0);
							web.setCardNum(cardNo);
							web.setBindCardStatus(WebBankcard.BINDCARD_STATUS_SUCCESS);
							dao.merge(web);
						}else{
							WebBankcard webn=new WebBankcard();
							webn.setBankname(bankName);
							webn.setCardNum(cardNo);
							webn.setBindCardStatus(WebBankcard.BINDCARD_STATUS_SUCCESS);
							webn.setUserFlg(bp.getThirdPayFlagId());
							webn.setThirdConfig(bp.getThirdPayConfig());
							webn.setCustomerId(bp.getId());
							webn.setUsername(bp.getTruename());
							webn.setCustomerType(Short.valueOf("0"));//线上用户
							webn.setAccountname(bp.getTruename());
							webn.setBankId(bankId);
							webn.setBankname(bankName);
							dao.save(webn);
						}
					}else{
						WebBankcard web=list.get(0);
						if(bankName!=null&&!bankName.equals("")){
							web.setBankname(bankName);
						}
						dao.merge(web);
					}
					String hqld="from WebBankcard as p where p.thirdConfig='"+bp.getThirdPayConfig()+"' and p.userFlg='"+bp.getThirdPayFlagId()+"' and p.customerId="+bp.getId()+" and p.bindCardStatus in ('bindCard_status_accept','bindCard_status_repare')  order by p.cardId desc";
					listd=dao.findByHql(hqld, null);
				}else{
					String hqld="from WebBankcard as p where p.thirdConfig='"+bp.getThirdPayConfig()+"' and p.userFlg='"+bp.getThirdPayFlagId()+"' and p.customerId="+bp.getId()+" and p.bindCardStatus in ('bindCard_status_repare')  order by p.cardId desc";
					listd=dao.findByHql(hqld, null);
				}
				if(listd!=null&&listd.size()>0){
					for(WebBankcard temp:listd){
						dao.remove(temp);
					}
				}
				return "成功处理";
			}else{
				return "没有用户信息";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "系统报错";
		}
	}

	@Override
	public List<WebBankcard> getbanklist(Long userId, String cardStatus,
			String remak) {
		// TODO Auto-generated method stub
		return dao.getbanklist(userId, cardStatus, remak);
	}

	@Override
	public List<WebBankcard> getByUserId(Long id) {
		// TODO Auto-generated method stub
		return dao.getByuserId(id);
	}

	@Override
	public List<WebBankcard> getBycustAndState(Long id,
			String bindcardStatusSuccess) {
		return dao.getBycustAndState(id,bindcardStatusSuccess);
	}

	@Override
	public List<BankCard> getBankCardList(Long id) {
		return dao.getBankCardList(id);
	}

	@Override
	public List<BankCard> getBankCardQuota() {
		List<BankCard> list = dao.getBankCardQuota();
		return list;
	}


}