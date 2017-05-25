package com.xiaoming.test.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.xiaoming.action.BaseAction;
import com.xiaoming.base.Role;
import com.xiaoming.domain.User;
import com.xiaoming.test.TestService;

@Controller("testUserAction")
public class UserAction extends BaseAction<User> {
	
	public String catalog(){
		return "catalog";
	}
	
	public String add(){
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		
		User u = new User();
		u.setLoginName(loginName);
		u.setPassword(password);
		u.setRole(Role.ORG_USER);
		
		new TestService().saveUser(u);
		return SUCCESS;
	}
	
}
