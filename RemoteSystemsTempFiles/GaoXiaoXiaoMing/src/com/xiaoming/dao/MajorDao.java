package com.xiaoming.dao;

import com.xiaoming.base.BaseDao;
import com.xiaoming.domain.Major;

public interface MajorDao extends BaseDao<Major> {
	
	/**
	 * 跟据专业名查专业
	 * @param name
	 * @param universityId
	 * @return
	 */
	public Major getByName(String name,long universityId);
	
}
