package com.xiaoming.service;

import java.util.List;

import com.xiaoming.domain.Material;
import com.xiaoming.domain.MaterialLendLog;
import com.xiaoming.domain.MaterialLendLogChild;
import com.xiaoming.domain.Pager;

public interface MaterialService {
	
	public Material get(Long id);

	/**
	 * 添加
	 * 
	 * @param material
	 *            要添加的物资
	 * @return
	 */
	public Material save(Material material, long orgId);

	/**
	 * 修改
	 * 
	 * @param material
	 *            新的物资
	 * @return
	 * @throws Exception 
	 */
	public void update(Material material) throws Exception;
	
	public Material updateMaterial(Material material);

	/**
	 * 删除
	 * 
	 * @param id
	 *            要删除的id
	 * @return
	 */
	public void delete(long id);

	/**
	 * 查询
	 * 
	 * @param id
	 *            组织的id
	 * @return
	 */
	public Pager<Material> list(long id);

	/**
	 * 出借
	 * 
	 * @param lendLong
	 * @return
	 * @throws Exception 
	 */
	public MaterialLendLog lend(MaterialLendLog lendLong, long MaterialId) throws Exception;
	/**
	 * 归还
	 * @param id 出借记录的id
	 * @param count 归还数量
	 * @throws Exception 
	 */
	public void revert(long id, int count) throws Exception;
	/**
	 * 获取记录详情
	 * @param id
	 * @return
	 */
	public List<MaterialLendLogChild> getChildLogs(long id);
	
	public Pager<MaterialLendLog> getLogs(long orgId);
}
