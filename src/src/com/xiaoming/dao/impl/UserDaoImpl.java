package com.xiaoming.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaoming.base.impl.BaseDaoImpl;
import com.xiaoming.dao.UserDao;
import com.xiaoming.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public boolean existByPhone(String phone) {
		String hql = "from User u where u.phoneNumber= :phone";
		Map<String, Object> alias = new HashMap<>();
		alias.put("phone", phone);
		if (queryObject(hql, alias) != null) {
			return true;
		}
		return false;
	}

	@Override
	public User getByPhone(String phone) {
		String hql = "from User u where u.phoneNumber= :phone";
		Map<String, Object> alias = new HashMap<>();
		alias.put("phone", phone);
		Object obj = queryObject(hql, alias);
		if (obj == null) {
			return null;
		}
		return (User) obj;
	}

}
