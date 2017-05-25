package com.xiaoming.service;

import java.util.List;

import com.xiaoming.domain.University;

public interface UniversityService {
	/**
	 * 获取
	 * @param id
	 * @return
	 */
	public University get(Long id);
	/**
	 * 获取所有学校
	 * @return
	 */
	public List<University> listAll();
}
