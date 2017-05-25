package com.xiaoming.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.constants.Constants;
import com.xiaoming.constants.MaterialContants;
import com.xiaoming.dao.MaterialDao;
import com.xiaoming.dao.MaterialLendLogChildDao;
import com.xiaoming.dao.MaterialLendLogDao;
import com.xiaoming.dao.OrganizationDao;
import com.xiaoming.domain.Material;
import com.xiaoming.domain.MaterialLendLog;
import com.xiaoming.domain.MaterialLendLogChild;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.Pager;
import com.xiaoming.service.MaterialService;
import com.xiaoming.service.OrganizationOperationService;

@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {
	
	@Resource
	MaterialDao materialDao;
	@Resource
	OrganizationDao organizationDao;
	@Resource
	MaterialLendLogDao materialLendLogDao;
	@Resource
	MaterialLendLogChildDao materialLendLogChildDao;
	@Resource
	OrganizationOperationService organizationOperationService;
	
	public Material get(Long id){
		return materialDao.get(id);
	}
	
	@Override
	public Material updateMaterial(Material material){
		materialDao.update(material);
		return material;
	}

	@Override
	public Material save(Material material,long orgId) {
		Organization org = organizationDao.get(orgId);
		material.setOrgBelongTo(org);
		material.setIsDeleted(false);
		
		//操作日志
		organizationOperationService.add(org, "添加了物资:"+material.getName());
		
		return materialDao.save(material);
	}

	@Override
	public void update(Material material) throws Exception {
		
		Material m = materialDao.get(material.getId());
		m.setName(material.getName());
		m.setTotalCount(material.getTotalCount());
		m.setStorageLocation(material.getStorageLocation());
		System.out.println("m:"+m+"matelial.getTotalCount():"+material.getTotalCount()+"|m.getLentCount():"+m.getLentCount());
		m.setExistCount(material.getTotalCount()-m.getLentCount());
		if(m.getExistCount()<0){
			throw new RuntimeException(MaterialContants.AMOUNT_ERROR);
		}
		
		//操作日志
		organizationOperationService.add(m.getOrgBelongTo(), "更新了物资"+material.getName());
		
		materialDao.update(m);
	}

	@Override
	public void delete(long id) {
		Material material = materialDao.get(id);
		material.setIsDeleted(true);
		
		//操作日志
		organizationOperationService.add(material.getOrgBelongTo(), "删除了物资"+material.getName());
		
		materialDao.update(material);
	}

	@Override
	public Pager<Material> list(long id) {
		String hql = "from Material material where material.orgBelongTo.id= :id and isDeleted=false";
		Map<String,Object> alias = new HashMap<>();
		alias.put("id", id);
		Pager<Material> pager = materialDao.findByAlias(hql, alias);
		return pager;
	}

	@Override
	public MaterialLendLog lend(MaterialLendLog lendLog,long materialId) throws Exception {
		//更新物资储存情况
		Material material = materialDao.get(materialId);
		
		if(lendLog.getCount()>material.getExistCount()){
			throw new RuntimeException(MaterialContants.OUT_OF_AMOUNT);
		}
		material.setLentCount(material.getLentCount()+lendLog.getCount());
		material.setExistCount(material.getTotalCount()-material.getLentCount());
		materialDao.update(material);
		//添加出借记录
		lendLog.setMaterial(material);
		lendLog.setRevertCount(0);
		lendLog.setState(Constants.MATERIAL_LENT);
		
		//操作日志
		String description = "借出物资:"+material.getName()+",数量:"+lendLog.getCount()+",借方:"+lendLog.getBorrowerName();
		organizationOperationService.add(material.getOrgBelongTo(), description);
		
		return materialLendLogDao.save(lendLog);
	}

	@Override
	public void revert(long id, int count) throws Exception {
		MaterialLendLog lendLog = materialLendLogDao.get(id);
		
		if(count > (lendLog.getCount()-lendLog.getRevertCount())){
			throw new RuntimeException("归还数量超出借出数量");
		}
		//添加子记录
		MaterialLendLogChild childLog = new MaterialLendLogChild();
		childLog.setCount(count);
		childLog.setFatherLog(lendLog);
		childLog.setRevertTime(new Date());
		materialLendLogChildDao.save(childLog);
		
		lendLog.setRevertCount(lendLog.getRevertCount()+count);
		//如果已经全部归还
		if(lendLog.getRevertCount().equals(lendLog.getCount())){
			lendLog.setState(Constants.MATERIAL_REVERTED);
			lendLog.setFinishDate(new Date());
		}
		
		//修改物资表中的数量
		Material material = lendLog.getMaterial();
		material.setLentCount(material.getLentCount()-count);
		
		//操作日志
		String description = lendLog.getBorrowerName()+"归还了物资:"+material.getName()+",数量:"+lendLog.getCount();
		organizationOperationService.add(material.getOrgBelongTo(), description);
		
		materialDao.update(material);
		materialLendLogDao.update(lendLog);
	}
	
	@Override
	public List<MaterialLendLogChild> getChildLogs(long id){
		return new ArrayList<>(materialLendLogDao.get(id).getChildLogs());
	}

	@Override
	public Pager<MaterialLendLog> getLogs(long orgId) {
		String hql = "from MaterialLendLog ml where ml.material.orgBelongTo.id="+orgId;
		return materialLendLogDao.find(hql, null,null);
	}

}
