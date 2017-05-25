package com.xiaoming.test;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.base.BaseDao;
import com.xiaoming.dao.UserDao;
import com.xiaoming.dao.impl.UserDaoImpl;
import com.xiaoming.domain.User;

@Service
@Transactional
public class TestService {
	@Resource
	private UserDao userDao;
	@Resource
	private BaseDao<User> baseDao;
	@Resource
	private SessionFactory sessionFactory;
	
	public void saveUser(User u){
		userDao.save(u);
	}
}
