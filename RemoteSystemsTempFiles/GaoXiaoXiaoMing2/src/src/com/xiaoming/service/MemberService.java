package com.xiaoming.service;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.xiaoming.domain.Member;
import com.xiaoming.domain.Pager;

public interface MemberService {
	/**
	 * 查询成员列表
	 * @return
	 */
	public Pager<Member> list(long orgId,String edition,int sort);
	/**
	 * 获取所有成员信息
	 * @param orgId
	 * @return
	 */
	public List<Member> listCurrentEditionAll(long orgId);
	/**
	 * 获取一个成员
	 * @param id
	 * @return
	 */
	public Member get(long id);
	/**
	 * 下载通讯录
	 * @return
	 */
	public InputStream downloadMemberTable(long orgId,String edition);
	/**
	 * 从人员表中导入
	 * @param workbook
	 */
	public void importFromMemberTable(Workbook workbook,long orgId);
	
	/**
	 * 通过成员表id获取加入的人数
	 * @param MemberTableId
	 * @return
	 */
	public Long getJoinCount(Long MemberTableId,String edition)throws Exception;
}
