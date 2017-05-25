package com.xiaoming.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.dao.ProjectDao;
import com.xiaoming.dao.impl.ProjectDaoImpl;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Project;
import com.xiaoming.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
	@Resource
	ProjectDao projectDao;
	
	@Override
	public void save(Project project, Member member) {
		project.setPublisher(member);
		project.setOrgBelong(member.getDepartment().getOrgBelong());
		projectDao.save(project);
	}

}
