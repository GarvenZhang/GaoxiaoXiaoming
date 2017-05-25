package com.xiaoming.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.dao.UniversityDao;
import com.xiaoming.domain.University;
import com.xiaoming.service.UniversityService;

@Service
@Transactional
public class UniversityServiceImpl implements UniversityService {

	@Resource
	UniversityDao universityDao;
	
	@Override
	public University get(Long id) {
		return universityDao.get(id);
	}

	@Override
	public List<University> listAll() {
		String hql = "from University uni";
		return universityDao.list(hql, null,null);
	}

}
