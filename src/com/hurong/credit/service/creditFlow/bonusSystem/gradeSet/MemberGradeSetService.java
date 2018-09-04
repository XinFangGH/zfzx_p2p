package com.hurong.credit.service.creditFlow.bonusSystem.gradeSet;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.bonusSystem.gradeSet.MemberGradeSet;

public interface MemberGradeSetService extends BaseService<MemberGradeSet> {
	
	/**
	 * 计算用户当前积分等级
	 * 
	 * @param Score   用户积分数值
	 * @return 	返回积分等级对象
	 */
	public MemberGradeSet findByUserScore(Long socre);

}
