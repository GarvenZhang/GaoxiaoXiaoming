package com.xiaoming.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.xiaoming.dao.SystemMessageDao;
import com.xiaoming.domain.Pager;
import com.xiaoming.domain.SystemMessage;
import com.xiaoming.service.SystemMessageService;
import com.xiaoming.service.UnreadService;

@Repository
public class SystemMessageServiceImpl implements SystemMessageService {
	
	@Resource
	SystemMessageDao systemMessageDao;
	@Resource
	UnreadService unreadService;
	
	@Override
	public Pager<SystemMessage> list(long userId) {
		String hql = "from SystemMessage sysm "
				+ "where sysm.id in ("
				+ "select sysmu from SystemMessageUser sysmu where sysmu.user.id= :userId) "
				+ "or sysm.toAll = true";
		Map<String,Object> alias = new HashMap<>();
		alias.put("userId", userId);
		return systemMessageDao.findByAlias(hql, alias);
	}

	@Override
	public SystemMessage save(SystemMessage systemMessage) {
		return systemMessageDao.save(systemMessage);
	}

}
