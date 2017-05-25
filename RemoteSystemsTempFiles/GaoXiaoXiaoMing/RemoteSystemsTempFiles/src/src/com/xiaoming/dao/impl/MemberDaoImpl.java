package com.xiaoming.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaoming.base.impl.BaseDaoImpl;
import com.xiaoming.dao.MemberDao;
import com.xiaoming.domain.Member;

@Repository
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {

	@Override
	public Member getByOrgAndUser(long orgId, long userId) {
		String hql = "from Member m "
				+ "where m.user.id= :userId "
				+ "and m.department.orgBelong.id = :orgId";
		Map<String,Object> alias = new HashMap<>();
		alias.put("userId", userId);
		alias.put("orgId", orgId);
		Object obj = queryObject(hql, alias);
		if(obj==null){
			return null;
		}
		return (Member) obj;
	}

	@Override
	public Long getCountJoin(Long OrgId,String edition) throws Exception {
		String hql = "from Member m where m.department.orgBelong.id=:id and m.state=:state and m.edition=:edition";
		byte b = 1;
		Map<String,Object> alias = new HashMap<>();
		alias.put("id", OrgId);
		alias.put("state", b);
		alias.put("edition", edition);
		return getCount(hql, false,alias);
	}

}
