package com.xiaoming.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.xiaoming.domain.Pager;
import com.xiaoming.dto.DataStatisticDto;

/**
 * 数据统计
 * @author Yunfei
 *
 */
public interface StatisticService {
	/**
	 * 获取统计数据
	 * @param orgId
	 * @param begin 开始时间
	 * @param end	结束时间
	 * @return
	 */
	public Pager<DataStatisticDto> list(long orgId, Date begin, Date end);
	/**
	 * 查询
	 * @param name 姓名
	 * @param orgId 组织的id
	 * @return
	 */
	public DataStatisticDto query(String name,long orgId);
	/**
	 * 查询
	 * @param name 姓名
	 * @param orgId 组织的id
	 * @param begin 开始时间
	 * @param end	结束时间
	 * @return
	 */
	public List<DataStatisticDto> query(String name,Long orgId,Date begin,Date end);
	/**
	 * 下载统计结果
	 * @param orgId
	 * @return
	 */
	public InputStream download(long orgId);
}
