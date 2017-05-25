package com.xiaoming.service;

import java.util.List;

import com.xiaoming.domain.Grade;

public interface GradeService {
	/**
	 * 獲取所有年級
	 * @return
	 */
	public List<Grade> list();
}
