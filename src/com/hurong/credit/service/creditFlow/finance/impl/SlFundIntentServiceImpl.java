package com.hurong.credit.service.creditFlow.finance.impl;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.DateUtil;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.creditFlow.finance.SlFundIntentDao;
import com.hurong.credit.model.creditFlow.finance.FundIncome;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.finance.SlFundIntent;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.finance.SlFundIntentService;


import com.hurong.credit.service.customer.InvestPersonInfoService;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;


public class SlFundIntentServiceImpl extends BaseServiceImpl<SlFundIntent> implements
		SlFundIntentService {

	@SuppressWarnings("unused")
	private SlFundIntentDao dao;
	@Resource
	private InvestPersonInfoService investPersonInfoService;
	public SlFundIntentServiceImpl(SlFundIntentDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<SlFundIntent> getListByBidPlanId(Long bidPlanId,
			PagingBean pb) {
		return dao.getListByBidPlanId(bidPlanId, pb);
	}

	@Override
	public List<SlFundIntent> getListByCustId(Long custId, String custType,HttpServletRequest request, PagingBean pb) {
		return dao.getListByCustId(custId, custType, request, pb);
	}

	@Override
	public Integer getListByBidPlanId(String investpersonName) {
		// TODO Auto-generated method stub
		return dao.getListByBidPlanId(investpersonName);
	}
	
	@Resource
	private BpFundIntentService bpFundIntentService;
	
	@Override
	public List<FundPay> getPay(String bidId,String type,String userName,String orderNo) {
		
		List<FundIncome> list= dao.getPay(bidId,type,userName,orderNo);
		List<FundPay> FundPaylist=new ArrayList<FundPay>();
		FundPaylist=changToFundPay(list);
		System.out.println(FundPaylist.size());
		List<FundPay> totalList = new ArrayList<FundPay>();
		//查出优惠券利息和加息利息----开始
		List<BpFundIntent> subjoinInterestList = bpFundIntentService.findByBidOrderNoFundType(orderNo,"subjoinInterest");	
		List<BpFundIntent> couponInterestList =  bpFundIntentService.findByBidOrderNoFundType(orderNo,"couponInterest");	
		
		if(subjoinInterestList!=null&&subjoinInterestList.size()>0){
			BpFundIntent bpFundIntent = subjoinInterestList.get(0);
			FundPay fundPay = new FundPay();
			fundPay.setPlanId(bpFundIntent.getBidPlanId());
			fundPay.setProjectName(bpFundIntent.getProjectName());
			fundPay.setIntentDate(bpFundIntent.getIntentDate());
			fundPay.setFactDate(bpFundIntent.getFactDate());
			fundPay.setLoanInterestMoney(bpFundIntent.getIncomeMoney());
			fundPay.setNotMoney(bpFundIntent.getNotMoney());
			fundPay.setAfterMoney(bpFundIntent.getAfterMoney());
			fundPay.setPayintentPeriod(bpFundIntent.getPayintentPeriod());
			fundPay.setFundType(bpFundIntent.getFundType());
			totalList.add(fundPay);
		}
		if(couponInterestList!=null&&couponInterestList.size()>0){
			BpFundIntent bpFundIntent = couponInterestList.get(0);
			FundPay fundPay = new FundPay();
			fundPay.setPlanId(bpFundIntent.getBidPlanId());
			fundPay.setProjectName(bpFundIntent.getProjectName());
			fundPay.setIntentDate(bpFundIntent.getIntentDate());
			fundPay.setFactDate(bpFundIntent.getFactDate());
			fundPay.setLoanInterestMoney(bpFundIntent.getIncomeMoney());
			fundPay.setNotMoney(bpFundIntent.getNotMoney());
			fundPay.setAfterMoney(bpFundIntent.getAfterMoney());
			fundPay.setPayintentPeriod(bpFundIntent.getPayintentPeriod());
			fundPay.setFundType(bpFundIntent.getFundType());
			totalList.add(fundPay);
		}
		//查出优惠券利息和加息利息----结束
		
		totalList.addAll(FundPaylist);
		
		return totalList;
	}
	@Override
	public List<FundPay> getPay(HttpServletRequest request, PagingBean pb,
			Long investPersonId,String type) {
		// TODO Auto-generated method stub
		List<FundIncome> list= dao.getPay(request, pb, investPersonId,type);
	
		List<FundPay> FundPaylist=new ArrayList<FundPay>();
		FundPaylist=changToFundPay(list);
 	    List<FundPay> FundPaylists=new ArrayList<FundPay>();     
      //查询
		String notMoney = request.getParameter("notMoney");
		if(null!=notMoney&&!"".equals(notMoney)){
			for(FundPay f:FundPaylist){
				if("1".equals(notMoney)){
					if(f.getNotMoney().compareTo(new BigDecimal(0))==1)FundPaylists.add(f);
				}else if("2".equals(notMoney)){
					if(f.getNotMoney().compareTo(new BigDecimal(0))==0)FundPaylists.add(f);
				}
			}
			
		}else{
			
			FundPaylists.addAll(FundPaylist);
			
		}
		if(!"Repaymented".equals(type)){
			List<FundPay> oneFundPaylist=new ArrayList<FundPay>();
			int i=0;
			 while(i<FundPaylist.size()){
				 FundPay fp=FundPaylist.get(i);
				 getPayintentPeriodnew(fp,FundPaylist,oneFundPaylist);
				 i++;
			}
			 FundPaylists= oneFundPaylist;
		}
		pb.setTotalItems(FundPaylists.isEmpty()?0:FundPaylists.size());
		List<FundPay> subFundPaylist=FundPaylists.subList(pb.getStart(),pb.getTotalItems()>( pb.getStart()+pb.getPageSize())? pb.getStart()+pb.getPageSize():pb.getTotalItems());
		return subFundPaylist;	
	
	}
	
	public void getPayintentPeriodnew  (FundPay fp,List<FundPay> FundPaylist,List<FundPay> oneFundPaylist){
		
		int j=0;
		 while(j<FundPaylist.size()){
			 FundPay fp1=FundPaylist.get(j);
			 if(fp1.getPlanId().equals(fp.getPlanId())&&fp1.getAfterMoney().compareTo(new BigDecimal("0"))==0){
				 if(!oneFundPaylist.contains(fp1)){
					 oneFundPaylist.add(fp1);
				 }
				
				 break;
			 }
			 j++;
		 }
		 
	}
	public List<FundPay> changToFundPay(List<FundIncome> list){
		List<FundPay> FundPaylist=new ArrayList<FundPay>();
		BigDecimal zero=new BigDecimal(0);
		 int i=0;
         while(i<list.size()){
			
			FundIncome fp=list.get(i);
			FundPay fpp=new FundPay();
			fpp.setAfterMoney(fp.getAfterMoney());
			fpp.setComprehensiveMoney(zero);
			fpp.setIntentDate(fp.getIntentDate());
		fpp.setFactDate(fp.getFactDate());
			fpp.setNotMoney(fp.getNotMoney());
			fpp.setPayintentPeriod(fp.getPayintentPeriod());
			fpp.setPlanId(fp.getPlanId());
			fpp.setIncomeMoney(fp.getIncomeMoney());
			fpp.setProjectName(fp.getProjectName());
			fpp.setPunishInterestMoney(zero);
			fpp.setPrincipalRepaymentMoney(zero);
			fpp.setComprehensiveMoney(zero);
			fpp.setComprehensiveMoney(zero);
			fpp.setPunishInterestMoney(zero);
			
		//	fpp.setIds(fp.getFundType()+"="+fp.getFundIntentId().toString());
	//	    Long investPersonId=	investPersonInfoService.get(fp.getInvestId()).getInvestPersonId();
			  String investPersonId=fp.getOrderNo();
		    fpp.setIds(investPersonId);
			 if(fp.getFundType().equals("principalRepayment")){
				
				 fpp.setPrincipalRepaymentMoney(fp.getIncomeMoney());
			 }else  if(fp.getFundType().equals("consultationMoney") || fp.getFundType().equals("serviceMoney")){
				  
				fpp.setComprehensiveMoney(fp.getIncomeMoney());
			 }else   if(fp.getFundType().equals("loanInterest")){
				
				 fpp.setLoanInterestMoney(fp.getIncomeMoney());
				  
				  
			  }
			FundPaylist.add(fpp);
			int j=0;
			  while(j<list.size()){
				FundIncome fps=list.get(j);
				if(!fp.equals(fps)&&fps.getPlanId().equals(fp.getPlanId())&&fps.getPayintentPeriod().equals(fp.getPayintentPeriod())){
					
					   if(fps.getFundType().equals("principalRepayment")){
						      fpp.setPrincipalRepaymentMoney(null!=fpp.getPrincipalRepaymentMoney()?fpp.getPrincipalRepaymentMoney().add(fps.getIncomeMoney()):fps.getIncomeMoney());
					   }else  if(fps.getFundType().equals("consultationMoney") || fps.getFundType().equals("serviceMoney")){
						      fpp.setComprehensiveMoney(null!=fpp.getComprehensiveMoney()?fpp.getComprehensiveMoney().add(fps.getIncomeMoney()):fps.getIncomeMoney());
				       }else   if(fps.getFundType().equals("loanInterest")){
						      fpp.setLoanInterestMoney(null!=fpp.getLoanInterestMoney()?fpp.getLoanInterestMoney().add(fps.getIncomeMoney()):fps.getIncomeMoney());
				        }
					   fpp.setNotMoney(fpp.getNotMoney().add(fps.getNotMoney()));
			//		   fpp.setPunishInterestMoney(fpp.getPunishInterestMoney().add(fps.getAccrualMoney()));
					   if(fps.getRepaySource().equals(Short.valueOf("2"))){
						   fpp.setCompensateMoney(fpp.getCompensateMoney()==null?BigDecimal.ZERO:fpp.getCompensateMoney().add(fps.getAfterMoney()));
					   }
					    investPersonId=fps.getOrderNo();
					    fpp.setAfterMoney(fpp.getAfterMoney().add(fps.getAfterMoney()));
					    if(!fpp.getIds().contains(investPersonId.toString())){
					    	
					    	  fpp.setIds( fpp.getIds()+","+investPersonId.toString());
					    }
					 
					   list.remove(fps);
			   }else{
				   
				   j++;
				   
			   }
			 
		   }
			  i++;
	    }
		
		return FundPaylist;
 		/*for(FundIncome fp:list){
			if(fp.getFundType().equals("loanInterest")){
				FundPay fpp=new FundPay();
				fpp.setAfterMoney(fp.getAfterMoney());
				fpp.setComprehensiveMoney(zero);
				fpp.setIntentDate(fp.getIntentDate());
				fpp.setLoanInterestMoney(fp.getIncomeMoney());
				fpp.setNotMoney(fp.getNotMoney());
				fpp.setPayintentPeriod(fp.getPayintentPeriod());
				fpp.setPlanId(fp.getPlanId());
				fpp.setPrincipalRepaymentMoney(zero);
				fpp.setProjectName(fp.getProjectName());
				fpp.setPunishInterestMoney(zero);
				fpp.setIds(fp.getFundIntentId().toString());
				FundPaylist.add(fpp);
			}
		}
		for(FundPay l:FundPaylist){
			for(FundIncome fp:list){
				if(fp.getProjectName().equals(l.getProjectName())&&fp.getPayintentPeriod()==l.getPayintentPeriod()){
				   if(fp.getFundType().equals("principalRepayment")){
					   l.setPrincipalRepaymentMoney(fp.getIncomeMoney());
					   l.setNotMoney(l.getNotMoney().add(fp.getNotMoney()));
					   l.setIds(l.getIds()+","+fp.getFundIntentId());
				   }else  if(fp.getFundType().equals("consultationMoney") || fp.getFundType().equals("serviceMoney")){
					  if(null!=l.getComprehensiveMoney()){
					   l.setComprehensiveMoney(l.getComprehensiveMoney().add(fp.getIncomeMoney()));
					   l.setNotMoney(l.getNotMoney().add(fp.getNotMoney()));
					   l.setIds(l.getIds()+","+fp.getFundIntentId());
					  }else{
						  
						  l.setComprehensiveMoney(fp.getIncomeMoney());
						  l.setNotMoney(l.getNotMoney().add(fp.getNotMoney()));
						  l.setIds(l.getIds()+","+fp.getFundIntentId());
					  }
				   }
				}
			}
			
		}*/
/*
		
		for(int i=0;i<list.size();i++){
			
			FundIncome fp=list.get(i);
			System.out.println(fp.getFundIntentId()+"=i=="+i);
			FundPay fpp=new FundPay();
			fpp.setAfterMoney(fp.getAfterMoney());
			fpp.setComprehensiveMoney(zero);
			fpp.setIntentDate(fp.getIntentDate());
			fpp.setLoanInterestMoney(fp.getIncomeMoney());
			fpp.setNotMoney(fp.getNotMoney());
			fpp.setPayintentPeriod(fp.getPayintentPeriod());
			fpp.setPlanId(fp.getPlanId());
			fpp.setPrincipalRepaymentMoney(zero);
			fpp.setProjectName(fp.getProjectName());
			fpp.setPunishInterestMoney(zero);
			fpp.setIds(fp.getFundIntentId().toString());
			FundPaylist.add(fpp);
			for(int j=0;j<list.size();j++){
				FundIncome fps=list.get(j);
				if(!fp.equals(fps)&&fps.getPlanId().equals(fp.getPlanId())&&fps.getPayintentPeriod().equals(fp.getPayintentPeriod())){
					
					   if(fps.getFundType().equals("principalRepayment")){
						   fpp.setPrincipalRepaymentMoney(fps.getIncomeMoney());
						   fpp.setNotMoney(fpp.getNotMoney().add(fps.getNotMoney()));
						   fpp.setIds(fpp.getIds()+","+fps.getFundIntentId());
					   }else  if(fps.getFundType().equals("consultationMoney") || fps.getFundType().equals("serviceMoney")){
						  if(null!=fpp.getComprehensiveMoney()){
						   fpp.setComprehensiveMoney(fpp.getComprehensiveMoney().add(fps.getIncomeMoney()));
						   fpp.setNotMoney(fpp.getNotMoney().add(fps.getNotMoney()));
						   fpp.setIds(fpp.getIds()+","+fps.getFundIntentId());
						  }else{
							  
							  fpp.setComprehensiveMoney(fp.getIncomeMoney());
							  fpp.setNotMoney(fpp.getNotMoney().add(fps.getNotMoney()));
							  fpp.setIds(fpp.getIds()+","+fp.getFundIntentId());
						  }
						 
				       }
					   
					   list.remove(fps);
						  System.out.println(+fps.getFundIntentId()+"==j=="+i);
			   }
			
		   }
	
	    }*/
	}

	@Override
	public BigDecimal getLoanTotal(Long projectId) {
		return dao.getLoanTotal(projectId);
	}

	@Override
	public BigDecimal getMoney(Long projectId, String type) {
		return dao.getMoney(projectId,type);
	}
   /**
    * ret[0];//待还本息
	ret[1];//当前期数
	ret[2];//总期数
	ret[3];//下次还款日
	ret[4];//还清日
    */
	@Override
	public String[] getFundInfoByPlanId(Long bidId, HttpServletRequest req) {
		String[] ret=new String[5];
		BigDecimal notMoney=new BigDecimal(0);//待还本息
		int num = 0;//当前期数
		int totalnum=0;//总期数
		Date d = null;//下次还款日
		Date dn = null;//还清日期
		QueryFilter filter=new QueryFilter();
		filter.getPagingBean().setPageSize(100);
		filter.addFilter("Q_bidPlanId_L_EQ", bidId.toString());
		List<SlFundIntent> listSlfund=this.getAll(filter);
		if(listSlfund.size()>0){
		for(SlFundIntent slf:listSlfund){
			if(slf.getFundType().equals("loanInterest")||slf.getFundType().equals("principalRepayment")){
				notMoney=notMoney.add(slf.getNotMoney());
				if(slf.getFactDate()==null&&d==null&&num==0){
					d=slf.getIntentDate();
					num=slf.getPayintentPeriod()-1;
				}
			}
			
		}
		totalnum=listSlfund.get(listSlfund.size()-1).getPayintentPeriod();
		dn=listSlfund.get(listSlfund.size()-1).getFactDate();
		}
		ret[0]=notMoney.toString();
		ret[1]=String.valueOf(num);
		ret[2]=String.valueOf(totalnum);
		ret[3]=(d==null?"":DateUtil.dateToStr(d, "yyyy-MM-dd"));
		ret[4]=(dn==null?"":DateUtil.dateToStr(dn, "yyyy-MM-dd"));
		return ret;
	}

@Override
public SlFundIntent getSlFoundIntent(Long projectId) {
	return dao.getSlFoundIntent(projectId);
}

@Override
public Date getIntentDate(Long projectId, int payintentPeriod, String type) {
	return dao.getIntentDate(projectId, payintentPeriod, type);
}

@Override
public Integer getNextCount(Long projectId) {
	return dao.getNextCount(projectId);
}

@Override
public Integer getPayintentPeriodMax(Long projectId) {
	return dao.getPayintentPeriodMax(projectId);
}

@Override
public BigDecimal getnotMoneySum(Long projectId, int payintentPeriod) {
	return dao.getnotMoneySum(projectId, payintentPeriod);
}

@Override
public Integer getCountDetailLoan(Long bidPlanId, String type,String investpersonName) {
	return dao.getCountDetailLoan( bidPlanId, type,investpersonName);
}

@Override
public BigDecimal getLoanTotle(Long bidPlanId, String type,String investpersonName) {
	return dao.getLoanTotle( bidPlanId,  type ,investpersonName);
}


}
