package com.xiaoming.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.xiaoming.dao.DynamicStateDao;
import com.xiaoming.domain.DynamicState;
import com.xiaoming.domain.Pager;
import com.xiaoming.service.DynamicStateService;

@Repository
public class DynamicStateServiceImpl implements DynamicStateService {
	
	@Resource
	DynamicStateDao dynamicStateDao;

	@Override
	public Pager<DynamicState> list(String projectId, long memberId) {
		StringBuffer hql = new StringBuffer("from DynamicState ds where ");
		Map<String,Object> alias = new HashMap<>();
		//如果projectId不为空，查询该项目下的工作动态
		if(null != projectId && !"".equals(projectId)){
			hql.append("ds.project.id= :projectId");
			alias.put("projectId", Long.parseLong(projectId));
		}else{
			hql.append("ds.member.id= :memberId");
			alias.put("memberId", memberId);
		}
		
		return dynamicStateDao.findByAlias(hql.toString(), alias);
	}

}
