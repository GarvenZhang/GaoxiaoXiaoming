package com.xiaoming.dao;

import java.util.Map;

import com.xiaoming.base.BaseDao;
import com.xiaoming.domain.AssignmentMember;
import com.xiaoming.domain.Pager;

public interface AssignmentMemberDao extends BaseDao<AssignmentMember> {
	public Pager<AssignmentMember> findByAlias(String hql, Map<String, Object> alias);
}
