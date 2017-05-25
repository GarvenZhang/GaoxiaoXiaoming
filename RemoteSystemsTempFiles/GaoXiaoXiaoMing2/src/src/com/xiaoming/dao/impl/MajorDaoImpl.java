package com.xiaoming.dao.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaoming.base.impl.BaseDaoImpl;
import com.xiaoming.dao.MajorDao;
import com.xiaoming.domain.Major;

@Repository
public class MajorDaoImpl extends BaseDaoImpl<Major> implements MajorDao {

	@Override
	public Major getByName(String name, long universityId) {
		String hql = "from Major m where m.name= :name and m.university.id= :uId";
		Map<String,Object> alias = new HashMap<>();
		alias.put("name", name);
		alias.put("uId", universityId);
		return (Major)queryObject(hql, alias);
	}
	

}
