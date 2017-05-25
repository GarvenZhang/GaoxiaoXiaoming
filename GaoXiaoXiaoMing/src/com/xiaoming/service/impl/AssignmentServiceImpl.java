package com.xiaoming.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.constants.Constants;
import com.xiaoming.dao.AssignmentDao;
import com.xiaoming.dao.AssignmentMemberDao;
import com.xiaoming.dao.DynamicStateDao;
import com.xiaoming.dao.MemberDao;
import com.xiaoming.dao.ProjectDao;
import com.xiaoming.domain.Assignment;
import com.xiaoming.domain.AssignmentMember;
import com.xiaoming.domain.DynamicState;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Pager;
import com.xiaoming.domain.Project;
import com.xiaoming.dto.AssignmentMemberDto;
import com.xiaoming.dto.MemberDto;
import com.xiaoming.service.AssignmentService;
import com.xiaoming.service.UnreadService;
import com.xiaoming.util.SystemContext;

@Repository
@Transactional
public class AssignmentServiceImpl implements AssignmentService {
	@Resource
	AssignmentDao assignmentDao;
	@Resource
	AssignmentMemberDao assignmentMemberDao;
	@Resource
	ProjectDao projectDao;
	@Resource
	MemberDao memberDao;
	@Resource
	DynamicStateDao dynamicStateDao;
	@Resource
	private UnreadService unreadService;

	@Override
	public Pager<Assignment> find(String type, Member currentMember) {
		return null;
	}

	@Override
	public Pager<Assignment> findAll(Member currentMember) {
		String hql = "from AssignmentMember am where am.assignment.publisher.id= :publisherId or am.exerciser.id= :exerciserId";
		Map<String, Object> alias = new HashMap<>();
		alias.put("publisherId", currentMember.getId());
		alias.put("exerciserId", currentMember.getId());
		Pager<AssignmentMember> memberPager = assignmentMemberDao.findByAlias(hql, alias);
		// 转化成Assignment
		Pager<Assignment> pager = new Pager<>();
		pager.setPageArgs(memberPager); // 设置分页的参数

		List<AssignmentMember> memberList = memberPager.getRecordList();
		Set<Assignment> set = new LinkedHashSet<>(); // 去重复
		for (AssignmentMember assignmentMember : memberList) {
			set.add(assignmentMember.getAssignment());
			// 清除该成员工作维度消息
			unreadService.clear(assignmentMember.getExerciser().getId(), Constants.UNREAD_ASSIGNMENT);
		}
		pager.setRecordList(new ArrayList<>(set));
		return pager;
	}

	@Override
	public Pager<Assignment> findAll(long memberId) {
		SystemContext.setSort("assign.publishDate");		
		String hql = "from Assignment assign"
				+ "   where (assign.id in ("
				+ "		select id "
				+ " 	from AssignmentMember am"
				+ " 	where am.assignment.id=assign.id"
				+ "			and am.exerciser.id= :exerciserId"
				+ "		)"
				+ " or assign.publisher.id= :publisherId)"
				+ "	and assign.isValid=true ";
		Map<String,Object> alias = new HashMap<>();
		alias.put("publisherId", memberId);
		alias.put("exerciserId", memberId);
		Pager<Assignment> pager = assignmentDao.findByAlias(hql, alias);
		return pager;
	}

	public Pager<Assignment> findPublished(Member currentMember) {
		String hql = "from Assignment assign where assign.publisher.id = :id and assign.isValid=true";
		Map<String, Object> alias = new HashMap<>();
		alias.put("id", currentMember.getId());
		Pager<Assignment> pager = assignmentDao.findByAlias(hql, alias);
		return pager;
	}

	@Override
	public Pager<Assignment> findByProjcet(Long id) {
		String hql = "from Assignment assign where assign.projectBelongTo.id = :id and assign.isValid=true";
		Map<String, Object> alias = new HashMap<>();
		alias.put("id", id);
		Pager<Assignment> pager = assignmentDao.findByAlias(hql, alias);
		return pager;
	}

	@Override
	public Assignment save(Assignment assignment, Project project, long[] receiverIds, long memberId) {
		Member currentMember = memberDao.get(memberId);
		// 如果没ID说明是新项目，将任务加到这个project对象里
		if (null == project.getId() || project.getId().longValue() == 0) {
			project.setPublisher(currentMember);
			project.setOrgBelong(currentMember.getDepartment().getOrgBelong());
			project.setId(null);
			Set<Assignment> assignSet = new HashSet<>();
			assignSet.add(assignment);
			project.setAssignments(assignSet);
			assignment.setProjectBelongTo(project);
		} else { // 不是新对象则从数据库中找出该对象，然后在设置
			System.out.println(project.getId());
			Project pro = projectDao.get(project.getId());
			pro.getAssignments().add(assignment);
			assignment.setProjectBelongTo(pro);
		}
		// 设置成员
		Set<AssignmentMember> members = new HashSet<>();
		for (long receiverId : receiverIds) {
			AssignmentMember am = new AssignmentMember();
			Member member = memberDao.get(receiverId);
			am.setAssignment(assignment);
			am.setExerciser(member);
			am.setIsFinished(false);
			members.add(am);

			// 添加工作动态
			DynamicState dynamicState = new DynamicState();
			dynamicState.setAssignment(assignment);
			dynamicState
					.setDescription("收到了" + currentMember.getUser().getRealName() + "布置的任务：" + assignment.getContent());
			dynamicState.setOperaTime(new Date());
			dynamicState.setMember(member);
			dynamicState.setProject(assignment.getProjectBelongTo());
			dynamicStateDao.save(dynamicState);

			// 为该成员添加一条未读消息
			unreadService.add(receiverId, Constants.UNREAD_ASSIGNMENT);
		}
		assignment.setMembers(members);
		assignmentDao.save(assignment);
		// 添加工作动态
		DynamicState dynamicState = new DynamicState();
		dynamicState.setAssignment(assignment);
		dynamicState.setDescription("添加了任务：" + assignment.getContent());
		dynamicState.setOperaTime(new Date());
		dynamicState.setMember(currentMember);
		dynamicState.setProject(assignment.getProjectBelongTo());
		dynamicStateDao.save(dynamicState);

		return assignment;
	}

	@Override
	public Assignment save(Assignment assignment, Project project, List<AssignmentMemberDto> exercisers,
			long memberId) {
		long[] ids = new long[exercisers.size()];
		for (int i = 0; i < exercisers.size(); i++) {
			ids[i] = Long.parseLong(exercisers.get(i).getId());
		}
		return save(assignment, project, ids, memberId);
	}

	@Override
	public Assignment save(Assignment assignment, Project project, String[] receiverIds, long memberId) {
		long[] ids = new long[receiverIds.length];
		for (int i = 0; i < receiverIds.length; i++) {
			ids[i] = Long.parseLong(receiverIds[i]);
		}
		return save(assignment, project, ids, memberId);
	}

	@Override
	public void update(Assignment assignment, Project project, List<AssignmentMemberDto> exercisers, long memberId) {
		Assignment newAssign = assignmentDao.get(assignment.getId());
		Member currentMember = memberDao.get(memberId);
		// 如果没ID说明是新项目，将任务加到这个project对象里
		if (null == project.getId() || project.getId().longValue() == 0) {
			project.setOrgBelong(currentMember.getDepartment().getOrgBelong());
			project.setPublisher(currentMember);
			project.setId(null);
			Set<Assignment> assignSet = new HashSet<>();
			assignSet.add(newAssign);
			project.setAssignments(assignSet);
			newAssign.setProjectBelongTo(project);
		} else { // 不是新对象则从数据库中找出该对象，然后在设置
			Project pro = projectDao.get(project.getId());
			pro.getAssignments().add(assignment);
			newAssign.setProjectBelongTo(pro);
		}
		// 设置成员
		Set<AssignmentMember> members = new HashSet<>();
		for (AssignmentMemberDto memberDto : exercisers) {
			AssignmentMember am = new AssignmentMember();
			Member member = memberDao.get(Long.parseLong(memberDto.getId()));
			am.setAssignment(newAssign);
			am.setExerciser(member);
			am.setIsFinished(false);
			members.add(am);
		}
		newAssign.setContent(assignment.getContent());
		newAssign.setDeadline(assignment.getDeadline());
		newAssign.setMembers(members);
		newAssign.setUpdateTime(new Date());
		assignmentDao.update(newAssign);
	}

	@Override
	public void finish(Long amID, Boolean isFinished) {
		AssignmentMember am = assignmentMemberDao.get(amID);
		am.setIsFinished(isFinished);
		am.setFinishTime(new Date());
		assignmentMemberDao.update(am);
	}

	@Override
	public void delete(Long id) {
		Assignment assignment = assignmentDao.get(id);
		assignment.setIsValid(false);
		assignmentDao.update(assignment);
	}

	@Override
	public Assignment get(long id) {
		return assignmentDao.get(id);
	}

	@Override
	public Assignment update(Assignment assignment, Project project, long[] memberIds, long memberId) {
		Assignment newAssign = assignmentDao.get(assignment.getId());
		Member currentMember = memberDao.get(memberId);
		// 如果没ID说明是新项目，将任务加到这个project对象里
		if (null == project.getId() || project.getId().longValue() == 0) {
			project.setOrgBelong(currentMember.getDepartment().getOrgBelong());
			project.setPublisher(currentMember);
			project.setId(null);
			Set<Assignment> assignSet = new HashSet<>();
			assignSet.add(newAssign);
			project.setAssignments(assignSet);
			newAssign.setProjectBelongTo(project);
		} else { // 不是新对象则从数据库中找出该对象，然后在设置
			Project pro = projectDao.get(project.getId());
			pro.getAssignments().add(assignment);
			newAssign.setProjectBelongTo(pro);
		}
		// 设置成员
		Set<AssignmentMember> members = newAssign.getMembers();
		List<Long> idList = new ArrayList<Long>(memberIds.length);
		for (int i = 0; i < memberIds.length; i++) {
			idList.add(memberIds[i]);
		}
		System.out.println(idList);
		// 删除修改后没有的成员
		Iterator<AssignmentMember> itr = members.iterator();
		while (itr.hasNext()) {
			AssignmentMember am = itr.next();
			System.out.println(am.getExerciser().getId() + "|" + !idList.contains(am.getExerciser().getId()));
			if (!idList.contains(am.getExerciser().getId())) {// 在新的成员列表中查找，原有成员是否存在，不存在则删除
				itr.remove();
			}

		}
		for (long id : memberIds) {
			String hql = "from AssignmentMember am where am.exerciser.id=" + id + " and am.assignment.id="
					+ newAssign.getId();
			AssignmentMember am = (AssignmentMember) assignmentMemberDao.queryObject(hql, null, null);
			if (am == null) {
				am = new AssignmentMember();
				Member member = memberDao.get(id);
				am.setAssignment(newAssign);
				am.setExerciser(member);
				am.setIsFinished(false);
				members.add(am);
			}
		}
		newAssign.setContent(assignment.getContent());
		newAssign.setDeadline(assignment.getDeadline());
		newAssign.setMembers(members);
		newAssign.setUpdateTime(new Date());
		assignmentDao.update(newAssign);

		return newAssign;
	}

	@Override
	public Assignment update(Assignment assignment, Project project, String[] memberIds, long memberId) {
		long[] ids = new long[memberIds.length];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = Long.parseLong(memberIds[i]);
		}
		return update(assignment, project, ids, memberId);
	}

}
