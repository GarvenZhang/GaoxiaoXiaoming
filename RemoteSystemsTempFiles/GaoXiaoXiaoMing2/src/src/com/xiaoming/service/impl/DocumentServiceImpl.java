package com.xiaoming.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.dao.DocumentDao;
import com.xiaoming.domain.Document;
import com.xiaoming.service.DocumentService;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {
	
	@Resource
	private DocumentDao documentDao;

	@Override
	public List<Document> getByFolderId(Long id) {
		return documentDao.getByFolderId(id);
	}

	@Override
	public void save(Document document) {
		documentDao.save(document);
	}

	@Override
	public Document get(Long id) {
		return documentDao.get(id);
	}

	@Override
	public void delete(Long id) {
		documentDao.delete(id);
	}

	@Override
	public void update(Document document) {
		documentDao.update(document);
	}
	

}
