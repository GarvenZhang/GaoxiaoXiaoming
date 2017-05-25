package com.xiaoming.test.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiaoming.base.Gender;
import com.xiaoming.base.Role;
import com.xiaoming.domain.User;
import com.xiaoming.test.TestService;
import com.xiaoming.util.MD5Util;


public class Install {
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	
	@Test
	public void install(){
		System.out.println(ac.getBean("sessionFactory"));
		TestService testService = (TestService)ac.getBean("testService");
		User user = new User();
		user.setLoginName("abcabc");
		user.setPassword(MD5Util.encode("123456"));
		testService.saveUser(user);
	}
}
