package com.xiaoming.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.dao.FolderDao;
import com.xiaoming.domain.Folder;
import com.xiaoming.service.FolderService;

@Service
@Transactional
public class FolderServiceImpl implements FolderService {
	
	@Resource
	private FolderDao folderDao;
	/**
	 * id 组织id
	 * isPublic 是否为公开文件夹
	 * isTop 是否为顶层文件夹
	 */
	public List<Folder> findList(Long id, Boolean isPublic,Boolean isTop){
		return folderDao.findList(id, isPublic,isTop);
	}
	@Override
	public Folder get(Long id) {
		return folderDao.get(id);
	}
	@Override
	public void save(Folder folder) {
		folderDao.save(folder);
		
	}
	@Override
	public void delete(Folder folder) {
		folderDao.delete(folder);
	}
	@Override
	public void update(Folder folder) {
		folderDao.update(folder);
	}
}
