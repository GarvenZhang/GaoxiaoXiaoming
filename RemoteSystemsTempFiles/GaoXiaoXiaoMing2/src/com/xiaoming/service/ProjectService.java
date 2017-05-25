package com.xiaoming.service;

import com.xiaoming.domain.Member;
import com.xiaoming.domain.Project;

public interface ProjectService {
	/**
	 * 创建一个项目
	 * @param project
	 * @param member
	 */
	public void save(Project project,Member member);
	
}
