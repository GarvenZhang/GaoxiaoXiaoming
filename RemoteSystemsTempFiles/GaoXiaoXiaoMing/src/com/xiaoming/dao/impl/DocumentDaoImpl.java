package com.xiaoming.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.xiaoming.base.impl.BaseDaoImpl;
import com.xiaoming.dao.DocumentDao;
import com.xiaoming.domain.Document;

@Repository
public class DocumentDaoImpl extends BaseDaoImpl<Document> implements
		DocumentDao {
	@Resource
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> getByFolderId(Long id) {

		return (ArrayList<Document>)sessionFactory.getCurrentSession()
				.createQuery("From Document as d where d.folder.id=?")
				.setParameter(0, id).list();
	}

}
