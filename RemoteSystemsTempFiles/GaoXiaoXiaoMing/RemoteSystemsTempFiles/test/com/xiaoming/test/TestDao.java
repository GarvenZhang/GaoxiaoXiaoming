package com.xiaoming.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.xiaoming.base.Role;
import com.xiaoming.domain.User;



@Repository
public class TestDao {
	@Resource
	SessionFactory sessionFactory;

	public void save(User u){
		getSession().save(u);
	}
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
}
