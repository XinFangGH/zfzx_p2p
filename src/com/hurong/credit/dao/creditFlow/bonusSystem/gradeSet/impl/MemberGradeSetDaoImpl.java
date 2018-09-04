package com.hurong.credit.dao.creditFlow.bonusSystem.gradeSet.impl;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.bonusSystem.gradeSet.MemberGradeSetDao;
import com.hurong.credit.model.creditFlow.bonusSystem.gradeSet.MemberGradeSet;

/**
 * 会员等级设置dao实现
 * @author songwenjie
 *
 */
@SuppressWarnings("unchecked")
public class MemberGradeSetDaoImpl extends BaseDaoImpl<MemberGradeSet>
		implements MemberGradeSetDao {

	public MemberGradeSetDaoImpl() {
		super(MemberGradeSet.class);
	}


}
