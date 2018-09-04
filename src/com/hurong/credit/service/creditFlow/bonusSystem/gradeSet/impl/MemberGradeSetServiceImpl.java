package com.hurong.credit.service.creditFlow.bonusSystem.gradeSet.impl;

import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.creditFlow.bonusSystem.gradeSet.MemberGradeSetDao;
import com.hurong.credit.model.creditFlow.bonusSystem.gradeSet.MemberGradeSet;
import com.hurong.credit.service.creditFlow.bonusSystem.gradeSet.MemberGradeSetService;

public class MemberGradeSetServiceImpl extends BaseServiceImpl<MemberGradeSet>
					implements MemberGradeSetService {

	
	public  MemberGradeSetDao dao;
	public MemberGradeSetServiceImpl(MemberGradeSetDao dao) {
		super(dao);
		  this.dao = dao;
	}
	
	@Override
	public MemberGradeSet findByUserScore(Long socre) {
		
		if(socre==null){
			socre = new Long(0);
		}
		
		List<MemberGradeSet> list = dao.getAll();
		MemberGradeSet result = null;
		
		if(list!=null&&list.size()>0){
			//List排序---按会员积分倒序排序   ----排序方式，选择排序
			//排序后的List
			for(int i=0 ; i < list.size()-1 ; i++){
				for(int j=i+1 ; j<list.size(); j++ ){
					MemberGradeSet temp = list.get(i);
					MemberGradeSet nextTemp = list.get(j);
					if(temp.getLevelMinBonus().compareTo(nextTemp.getLevelMinBonus())<0){
						list.set(i, nextTemp);
						list.set(j, temp);
					}
				}
			}
			
			for(MemberGradeSet memberGradeSet : list){
				//如果用户积分大于等于  并且  积分规则已开启则 返回这个规则
				if((socre.compareTo(memberGradeSet.getLevelMinBonus())>=0)){
					result = memberGradeSet;
					break;
				}	
			}
		}
		
		//排序完成
		return result;
	}

}
