package com.xiaoming.service;

import java.util.List;

import com.xiaoming.domain.Image;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.Pager;
import com.xiaoming.domain.Project;
import com.xiaoming.dto.ImageDto;
import com.xiaoming.dto.OrganizationDto;

public interface OrganizationService {
	/**
	 * 查询当前成员对应的组织
	 * @param currentMember
	 * @return
	 */
	public Organization queryByMemberId(long memberId);
	/**
	 * 查询orgId对应的组织信息
	 * @param orgId
	 * @return
	 */
	public Organization queryByOrgId(long orgId);
	/**
	 * 获取组织对象
	 * @param id
	 * @return
	 */
	public Organization get(long id);
	/**
	 * 获取某个大学的组织列表（分页）
	 * @param universityId
	 * @return
	 */
	public Pager<Organization> findByUniversity(long universityId);
	/**
	 * 获取某个大学的组织列表
	 * @param universityId
	 * @return
	 */
	public List<Organization> listByUniversity(long universityId);
	/**
	 * 获取届的信息
	 * @param orgId
	 * @return
	 */
	public List<String> getEdition(long orgId);
	/**
	 * 更新
	 * @param org
	 */
	public Organization update(OrganizationDto org,long orgId);
	/**
	 * 更新logo
	 * @param image
	 */
	public void updateLogo(Image image, long orgId);
	
	public List<Project> listProject(long orgId);
	
	public ImageDto getJoinQRCode(long orgId);
}
