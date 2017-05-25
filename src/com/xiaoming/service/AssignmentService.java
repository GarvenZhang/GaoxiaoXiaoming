package com.xiaoming.service;

import java.util.List;

import com.xiaoming.domain.Assignment;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Pager;
import com.xiaoming.domain.Project;
import com.xiaoming.dto.AssignmentMemberDto;
import com.xiaoming.dto.MemberDto;

public interface AssignmentService {
	public Assignment get(long id);
	/**
	 * 查询
	 * @param type 类型
	 * 				published，received，all
	 * @param currentMember	当前的成员
	 * @return
	 */
	public Pager<Assignment> find(String type,Member currentMember);
	/**
	 * 查询所有工作
	 * @return
	 */
	public Pager<Assignment> findAll(Member currentMember);
	public Pager<Assignment> findAll(long memberId);
	/**
	 * 查询发出的工作
	 * @return
	 */
	public Pager<Assignment> findPublished(Member currentMember);
	/**
	 * 创建一个任务
	 * 过时，应使用public Assignment save(Assignment assignment, Project project, long[] receiverIds,long memberId)
	 * @param assignment
	 * @param projectId
	 */
	@Deprecated
	public Assignment save(Assignment assignment, Project project, List<AssignmentMemberDto> exercisers,long memberId);
	/**
	 * 添加任务
	 * @param assignment
	 * @param project
	 * @param receiverIds
	 * @param memberId
	 * @return
	 */
	public Assignment save(Assignment assignment, Project project, long[] receiverIds,long memberId);
	/**
	 * 添加任务
	 * @param assignment
	 * @param project
	 * @param receiverIds
	 * @param memberId
	 * @return
	 */
	public Assignment save(Assignment assignment, Project project, String[] receiverIds,long memberId);
	/**
	 * 设置任务的完成状态
	 * @param amID 任务-成员中间表的id
	 * @param isFinished 是否完成
	 */
	public void finish(Long amID, Boolean isFinished);
	/**
	 * 更新一个任务
	 * @param assignment
	 */
//	public void update(Assignment assignment, Project project, List<MemberDto> exercisers,Member currentMember);
	public void update(Assignment assignment, Project project, List<AssignmentMemberDto> exercisers,long memberId);
	public Assignment update(Assignment assignment, Project project, long[] memberIds,long memberId);
	public Assignment update(Assignment assignment, Project project, String[] memberIds,long memberId);
	/**
	 * 删除一个任务（假删除）
	 * @param id
	 */
	public void delete(Long id);
//	/**
//	 * 查找收到的工作
//	 * @return
//	 */
//	public Pager<Assignment> findReceived(Member currentMember);
	/**
	 * 跟据项目查找
	 * @return
	 */
	public Pager<Assignment> findByProjcet(Long id);
}