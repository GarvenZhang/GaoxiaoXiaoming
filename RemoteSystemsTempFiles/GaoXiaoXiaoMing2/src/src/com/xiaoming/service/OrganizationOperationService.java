package com.xiaoming.service;

import com.xiaoming.domain.Organization;
import com.xiaoming.domain.OrganizationOperation;
import com.xiaoming.domain.Pager;

public interface OrganizationOperationService {
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public Pager<OrganizationOperation> list(long id);
	/**
	 * 操作
	 * @param orgId
	 * @param description
	 */
	public void operateMaterial(long orgId,String operation,String target,String name,int preCount,int count);
	/**
	 * 操作
	 * @param orgId
	 * @param operation
	 * @param name
	 */
	public void operateMember(long orgId,String operation,String target,String name);
	/**
	 * 添加
	 * @param org
	 * @param description
	 */
	public void add(Organization org, String description);
	/**
	 * 添加
	 * @param orgId
	 * @param description
	 */
	public void add(long orgId,String description);
}
