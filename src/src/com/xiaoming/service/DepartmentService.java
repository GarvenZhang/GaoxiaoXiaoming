package com.xiaoming.service;

import java.util.List;

import com.xiaoming.domain.Department;

public interface DepartmentService {
	/**
	 * 查詢該組織的所有部門
	 * @param orgId
	 * @return
	 */
	public List<Department> list(long orgId);
}
