package com.xiaoming.dao;

import java.util.List;

import com.xiaoming.base.BaseDao;
import com.xiaoming.domain.Grade;

/**
 * 年级
 * @author Yunfei
 *
 */
public interface GradeDao extends BaseDao<Grade> {
	public Grade getByName(String gradeName);
	public List<Grade> getAll();
}
