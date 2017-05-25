package com.xiaoming.dao;

import java.util.Map;

import com.xiaoming.base.BaseDao;
import com.xiaoming.domain.User;

public interface UserDao extends BaseDao<User> {
	/**
	 * 通过电话号码判断是否存在用户
	 * @param phone
	 * @return
	 */
	public boolean existByPhone(String phone);
	/**
	 * 通過電話號碼獲取用戶
	 * @param phone
	 * @return
	 */
	public User getByPhone(String phone);
}
