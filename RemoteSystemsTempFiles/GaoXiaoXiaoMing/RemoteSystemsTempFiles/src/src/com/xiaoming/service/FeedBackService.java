package com.xiaoming.service;

import com.xiaoming.domain.FeedBack;

public interface FeedBackService {
	
	/**
	 * 添加一条反馈
	 */
	public void send(FeedBack feedBack);
	/**
	 * 回复一条反馈
	 * @param id
	 * @param content
	 */
	public void relpy(Long id,String content);
}
