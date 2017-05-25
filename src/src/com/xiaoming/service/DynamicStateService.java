package com.xiaoming.service;

import com.xiaoming.domain.DynamicState;
import com.xiaoming.domain.Pager;

/**
 * 工作的动态
 * @author Yunfei
 *
 */
public interface DynamicStateService {
	/**
	 * 查询
	 * @param projectId 项目id
	 * @param memberId 成员id
	 */
	public Pager<DynamicState> list(String projectId,long memberId);

}
