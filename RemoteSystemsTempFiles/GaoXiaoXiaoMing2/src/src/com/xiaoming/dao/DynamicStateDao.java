package com.xiaoming.dao;

import java.util.Map;

import com.xiaoming.base.BaseDao;
import com.xiaoming.domain.DynamicState;
import com.xiaoming.domain.Pager;

public interface DynamicStateDao extends BaseDao<DynamicState> {
	public Pager<DynamicState> findByAlias(String hql, Map<String, Object> alias);
}
