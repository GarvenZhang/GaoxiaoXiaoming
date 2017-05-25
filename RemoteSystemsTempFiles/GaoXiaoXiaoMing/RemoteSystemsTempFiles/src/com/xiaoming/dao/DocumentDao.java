package com.xiaoming.dao;

import java.util.List;

import com.xiaoming.base.BaseDao;
import com.xiaoming.domain.Document;

public interface DocumentDao extends BaseDao<Document> {
	//通过文件夹查找文件
	/***
	 * 
	 * @param id 文件夹id
	 * @return
	 */
	public List<Document> getByFolderId(Long id);
	
}	
