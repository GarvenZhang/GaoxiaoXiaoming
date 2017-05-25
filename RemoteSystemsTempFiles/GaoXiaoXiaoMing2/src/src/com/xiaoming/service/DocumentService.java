package com.xiaoming.service;

import java.util.List;

import com.xiaoming.domain.Document;


public interface DocumentService {
	/***
	 * 
	 * @param id 文件夹id
	 * @return
	 */
	public List<Document> getByFolderId(Long id);
	
	/***
	 * 
	 * @param document 文件
	 */
	public void save(Document document);
	
	/***
	 * 
	 * @param id 文件id
	 * @return
	 */
	public Document get(Long id);
	
	/***
	 * 删除document
	 * @param id
	 */
	public void delete(Long id);
	
	
	public void update(Document document);
}
