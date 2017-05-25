package com.xiaoming.dao;

import java.util.List;

import com.xiaoming.base.BaseDao;
import com.xiaoming.domain.Folder;

public interface FolderDao extends BaseDao<Folder> {
	public List<Folder> findList(Long id,Boolean isPublic,Boolean isTop);
	public void delete(Folder folder);
}
