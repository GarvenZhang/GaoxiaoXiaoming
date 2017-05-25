package com.xiaoming.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.dao.OrganizationDao;
import com.xiaoming.domain.Department;
import com.xiaoming.domain.Organization;

@Service
@Transactional
public class DepartmentService implements com.xiaoming.service.DepartmentService {

	@Resource
	OrganizationDao orgDao;
	
	@Override
	public List<Department> list(long orgId) {
		Organization org = orgDao.get(orgId);
		return new ArrayList<>(org.getDepartments());
	}

}
