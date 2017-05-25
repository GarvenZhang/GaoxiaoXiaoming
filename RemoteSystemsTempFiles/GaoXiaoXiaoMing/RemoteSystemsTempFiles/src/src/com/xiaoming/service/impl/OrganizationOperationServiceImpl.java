package com.xiaoming.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.dao.OrganizationDao;
import com.xiaoming.dao.OrganizationOperationDao;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.OrganizationOperation;
import com.xiaoming.domain.Pager;
import com.xiaoming.service.OrganizationOperationService;
import com.xiaoming.util.SystemContext;

@Service
@Transactional
public class OrganizationOperationServiceImpl implements OrganizationOperationService {
	
	@Resource
	OrganizationOperationDao organizationOperationDao;
	@Resource
	OrganizationDao organizationDao;
	
	@Override
	public Pager<OrganizationOperation> list(long id) {
		SystemContext.setSort("oo.time");
		String hql = "from OrganizationOperation oo where oo.organization.id= :id";
		Map<String,Object> alias = new HashMap<>();
		alias.put("id", id);
		return organizationOperationDao.findByAlias(hql, alias);
	}
	@Override
	public void operateMaterial(long orgId, String operation, String target, String name, int preCount, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void operateMember(long orgId, String operation, String target, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Organization org, String description) {
		OrganizationOperation oo = new OrganizationOperation();
		oo.setOrganization(org);
		oo.setTime(new Date());
		oo.setDescription(description);
		
		organizationOperationDao.save(oo);
	}

	@Override
	public void add(long orgId, String description) {
		Organization org = organizationDao.get(orgId);
		add(org, description);
	}


}
