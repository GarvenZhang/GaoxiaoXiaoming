package com.xiaoming.dao;

import java.util.Map;

import com.xiaoming.base.BaseDao;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Pager;

public interface MemberDao extends BaseDao<Member>{
	/**
	 * 通過用戶和組織查找成員
	 * @param orgId
	 * @param userId
	 * @return
	 */
	public Member getByOrgAndUser(long orgId,long userId);
	
	/**
	 * 通过id获得加入的人数
	 * @param MemberId
	 * @return
	 */
	public Long getCountJoin(Long OrgId,String edition)throws Exception;
}
