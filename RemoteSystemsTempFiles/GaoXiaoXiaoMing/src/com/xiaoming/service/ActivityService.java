package com.xiaoming.service;

import java.io.InputStream;

import com.xiaoming.domain.Activity;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.ActivityDto;

public interface ActivityService {
	/**
	 * 添加一个活动报名
	 * @param activityDto
	 * @return
	 */
	public void save(ActivityDto activityDto,long currentMemberId);
	/**
	 * 处理报名请求
	 * @param id 报名信息的id
	 * @param info
	 */
	public void handle(long id,String info);
	/**
	 * 查询发送的报名信息
	 * @param memberId 成员的id
	 */
	public Pager<Activity> findPublished(long memberId);
	/**
	 * 查询收到的报名信息
	 * @param memberId 成员的id
	 */
	public Pager<Activity> findReceived(long memberId);
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public Activity get(long id);
	/**
	 * 获取报名信息的excel表格
	 * @return
	 */
	public InputStream getEnrollInfoExcel(Long activity);
}
