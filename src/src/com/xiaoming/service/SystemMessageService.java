package com.xiaoming.service;

import com.xiaoming.domain.Pager;
import com.xiaoming.domain.SystemMessage;

public interface SystemMessageService {
	
	/**
	 * 查询
	 * @return
	 */
	public Pager<SystemMessage> list(long userId);
	/**
	 * 添加
	 * @return
	 */
	public SystemMessage save(SystemMessage systemMessage);
	
}
