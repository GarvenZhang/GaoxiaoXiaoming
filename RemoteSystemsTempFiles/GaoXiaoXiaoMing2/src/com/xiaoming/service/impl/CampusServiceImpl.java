package com.xiaoming.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.dao.CampusDao;
import com.xiaoming.domain.Campus;
import com.xiaoming.service.CampusService;

@Service
@Transactional
public class CampusServiceImpl implements CampusService {
	
	@Resource
	CampusDao campusDao;
	
	@Override
	public Campus get(long id) {
		return campusDao.get(id);
	}

	@Override
	public List<Campus> getByUniversity(long universityId) {
		String hql = "from Campus c where c.university.id= :id";
		Map<String,Object> alias = new HashMap<>();
		alias.put("id", universityId);
		return campusDao.list(hql, alias);
	}
	
	
}
