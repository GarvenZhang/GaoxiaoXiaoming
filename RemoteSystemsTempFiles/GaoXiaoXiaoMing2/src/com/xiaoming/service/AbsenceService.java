package com.xiaoming.service;

import java.util.List;

import com.xiaoming.domain.Absence;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.MemberDto;

public interface AbsenceService {
	/**
	 * 添加一条请假请求
	 * @param absence
	 * @param receiver
	 * @param currentMember
	 */
	@Deprecated
	public void save(Absence absence,List<MemberDto> receiver,long memberId);
	public void save(Absence absence,long[] receiver,long memberId);
	public void save(Absence absence, String[] receiver, long memberId);
	/**
	 * 查询当前用户发出的请假
	 * @param currentMember
	 * @return
	 */
	public Pager<Absence> findPublished(Member currentMember);
	public Pager<Absence> findPublished(long memberId);
	/**
	 * 查询收到的请假请求
	 * @param currentMember
	 * @return
	 */
	public Pager<Absence> findReceived(Member currentMember);
	public Pager<Absence> findReceived(long memberId);
	
	/**
	 * 处理请求
	 */
	public void handle(long id, boolean agree);
	/**
	 * 更新一个请假
	 * @param absence
	 */
	public void update(Absence absence, List<MemberDto> receiver);
	/**
	 * 删除一个请假
	 * @param id
	 */
	public void delete(long id);
}
