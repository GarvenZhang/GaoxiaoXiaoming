package com.xiaoming.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.xiaoming.base.impl.BaseDaoImpl;
import com.xiaoming.dao.FolderDao;
import com.xiaoming.domain.Folder;

@Repository
public class FolderDaoImpl extends BaseDaoImpl<Folder> implements FolderDao {

	@Resource
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * @author zmj
	 * @param id 组织的id 
	 *        isPublic 是否为公开的文件夹
	 */
	public List<Folder> findList(Long id, Boolean isPublic, Boolean isTop) {
		String hql = "";
		if(isTop){
			hql = "FROM Folder as f where f.organization.id=? and f.parent "
					+ "is null" + " and f.isPublic="
					+ (isPublic ? 1 : 0) + " and f.avariable = 1";
		}else{
			hql = "FROM Folder as f where "
					+ "f.id = ?" + " and f.isPublic="
					+ (isPublic ? 1 : 0) + " and f.avariable = 1";
		}
		return (ArrayList<Folder>) sessionFactory.getCurrentSession()
				.createQuery(hql).setParameter(0, id).list();
	}
	
	private ArrayList<Long> deleteFolderIds(Folder f,ArrayList<Long> result){
		f.setAvariable(false);
		if(null!=f.getChilders()&&f.getChilders().size()>0){
			for(Folder f1:f.getChilders()){
				result.addAll(deleteFolderIds(f1,result));
			}
		}
		result.add(f.getId());
		return result;
	}

	@Override
	public void delete(Folder folder) {
		//获取要删除的id
		ArrayList<Long> ids = deleteFolderIds(folder,new ArrayList<Long>());
		String hql = "Update from Folder as f SET f.avariable = 0 where f.id in (:ids)";
		sessionFactory.getCurrentSession().createQuery(hql).setParameterList("ids", ids).isReadOnly();
	}

}
