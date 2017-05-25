package com.xiaoming.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.xiaoming.base.Role;
import com.xiaoming.constants.ErrorConstants;
import com.xiaoming.constants.SessionConstants;
import com.xiaoming.util.authority.Authority;

@Component
public class AuthorityInterceptor implements Interceptor {

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//获取被请求的方法名
		String methodName = invocation.getProxy().getMethod();
		//获取被请求的方法对象
		Method currentMethod = invocation.getAction().getClass().getMethod(methodName, null);
		//获取session
		Map<String,Object> session = invocation.getInvocationContext().getSession();
		//获取request
		HttpServletRequest request = (HttpServletRequest)
				invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		//获取当前用户权限
		Role role = (Role) session.get(SessionConstants.ROLE);
		//未登录（权限为空）
		if(session.get(SessionConstants.USER_ID) == null){
			return "noLogin";
		}
		//没有加入组织
		if(session.get(SessionConstants.ORG_ID) == null){
			return "noOrg";
		}
		//如果该请求方法需要权限控制（使用了Authority注解）
		if(currentMethod.isAnnotationPresent(Authority.class)){
			Authority authority = currentMethod.getAnnotation(Authority.class);
			//如果权限不符合，返回权限错误的json
			if(!role.equals(authority.role())){
				request.setAttribute("errorMessage", ErrorConstants.AUTHORITY);
				return "noPermission";
			}
		}
		//权限正确
		//或无需判断权限
		return invocation.invoke();
	}
	
}
