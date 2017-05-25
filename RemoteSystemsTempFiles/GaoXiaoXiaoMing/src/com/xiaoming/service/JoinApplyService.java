package com.xiaoming.service;

import com.xiaoming.domain.OrgJoinApplication;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.UserInfoDto;

public interface JoinApplyService {
	/**
	 * 申请加入组织
	 * 
	 * @param orgId
	 *            组织id
	 * @param userId
	 *            用户id
	 * @param userInfo
	 *            用户信息
	 * @throws Exception
	 */
	public void apply(long orgId, long userId, UserInfoDto userInfo) throws Exception;

	/**
	 * 处理申请
	 * 
	 * @param applyId
	 *            申请的id
	 * @param agree
	 *            是否同意
	 * @throws Exception
	 */
	public OrgJoinApplication handle(long applyId, boolean agree, String reason) throws Exception;

	/**
	 * 获取该组织收到的所有申请
	 * 
	 * @param orgId
	 */
	public Pager<OrgJoinApplication> findByOrg(long orgId);
	/**
	 * 获取该用户的所有申请
	 * @param userId
	 * @return
	 */
	public Pager<OrgJoinApplication> findByUser(long userId);
}
