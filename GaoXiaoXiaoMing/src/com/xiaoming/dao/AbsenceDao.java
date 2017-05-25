package com.xiaoming.dao;

import java.util.Map;

import com.xiaoming.base.BaseDao;
import com.xiaoming.domain.Absence;
import com.xiaoming.domain.Pager;

public interface AbsenceDao extends BaseDao<Absence> {
	public Pager<Absence> findByAlias(String hql, Map<String, Object> alias);
}
