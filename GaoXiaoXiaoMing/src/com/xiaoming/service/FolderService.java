package com.xiaoming.service;

import java.util.ArrayList;
import java.util.List;

import com.xiaoming.domain.Folder;

public interface FolderService {
	public List<Folder> findList(Long id, Boolean isPublic,Boolean isTop);
	public Folder get(Long id);
	public void save(Folder folder);
	public void delete(Folder folder);
	public void update(Folder folder);
}
