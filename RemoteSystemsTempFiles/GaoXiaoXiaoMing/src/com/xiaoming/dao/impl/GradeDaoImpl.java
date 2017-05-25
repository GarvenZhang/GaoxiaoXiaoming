package com.xiaoming.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaoming.base.impl.BaseDaoImpl;
import com.xiaoming.dao.GradeDao;
import com.xiaoming.domain.Grade;

/**
 * 年级
 * @author Yunfei
 *
 */
@Repository
public class GradeDaoImpl extends BaseDaoImpl<Grade> implements GradeDao {

	@Override
	public Grade getByName(String gradeName) {
		String hql = "from Grade g where g.name= ? ";
		Object[] args = new Object[1];
		args[0] = gradeName;
		return (Grade)queryObject(hql, args);
	}

	@Override
	public List<Grade> getAll() {
		String hql = "from Grade g";
		return list(hql, null,null);
	}

}
