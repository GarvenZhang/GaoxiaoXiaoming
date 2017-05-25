package com.xiaoming.dao;

import java.util.Map;

import com.xiaoming.base.BaseDao;
import com.xiaoming.domain.Assignment;
import com.xiaoming.domain.Pager;

public interface AssignmentDao extends BaseDao<Assignment> {
	public Pager<Assignment> findByAlias(String hql,Map<String, Object> alias);
}
