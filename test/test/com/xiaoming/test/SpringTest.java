package com.xiaoming.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiaoming.base.Role;
import com.xiaoming.domain.User;


public class SpringTest {
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
	Session s = sf.openSession();
	
	public void springStrutsTest() {
		System.out.println(ac.getBean("testAction"));
	}
	
	@Test
	public void springHibernateTest() {
		System.out.println(ac.getBean("sessionFactory"));
	}
	
	public void TestSave(){
		SessionFactory sf = (SessionFactory)ac.getBean("sessionFactory");
		Session s= sf.openSession();
		s.beginTransaction();
		User u = new User();
		u.setId(1L);
		u.setLoginName("hahaha");
		u.setPassword("123");
		u.setRole(Role.ORG_USER);
		u.setRegisterDate(new Date());
//		d.setMajor("mis");
//		d.setUser(u);
		s.save(u);
		s.getTransaction().commit();
	}
	
	
	public void TestGet(){
		User u = (User) s.get(User.class, 1L);
	}
}
