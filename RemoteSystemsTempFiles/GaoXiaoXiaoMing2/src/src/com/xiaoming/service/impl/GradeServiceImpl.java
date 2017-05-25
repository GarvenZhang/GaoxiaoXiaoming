package com.xiaoming.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.dao.GradeDao;
import com.xiaoming.domain.Grade;
import com.xiaoming.service.GradeService;

@Service
@Transactional
public class GradeServiceImpl implements GradeService {
	
	@Resource
	GradeDao gradeDao;
	
	@Override
	public List<Grade> list() {
		return gradeDao.getAll();
	}

}
