package com.xiaoming.service;

import com.xiaoming.domain.Unread;
import com.xiaoming.dto.UserUnreadDto;

/**
 * 未读消息
 * @author Yunfei
 *
 */
public interface UnreadService {
	/**
	 * 添加未读消息数量
	 * @param type
	 */
	public void add(long id, int type);
	/**
	 * 归零未读消息数量
	 * @param type
	 */
	public void clear(long id, int type);
	/**
	 * 获取一个成员的未读消息
	 * @param id
	 * @return
	 */
	public Unread getByMember(long id);
	/**
	 * 获取一个用户的未读消息
	 * @param id
	 * @return
	 */
	public UserUnreadDto getByUser(long id);
}
