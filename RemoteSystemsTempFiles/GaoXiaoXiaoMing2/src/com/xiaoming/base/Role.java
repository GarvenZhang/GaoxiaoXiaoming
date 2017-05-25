package com.xiaoming.base;

import com.xiaoming.constants.Constants;

/**
 * 用户权限，枚举类
 * 共有三种权限：
 * 		ORG_USER:组织用户
 * 		ORG_ADMIN:组织管理员
 *		SYS_ADMIN:系统管理员
 * @author Yunfei
 *
 */
public enum Role {
	ORG_USER(Constants.ORG_USER),ORG_ADMIN(Constants.ORG_ADMIN),SYS_ADMIN(Constants.SYS_ADMIN);
	
	private String name;

	/**
	 * @param name 用户类别
	 */
	Role(String name) {
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
