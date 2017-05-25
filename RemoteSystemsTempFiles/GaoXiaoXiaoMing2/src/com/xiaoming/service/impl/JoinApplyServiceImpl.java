package com.xiaoming.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.constants.Constants;
import com.xiaoming.constants.ErrorConstants;
import com.xiaoming.dao.DepartmentDao;
import com.xiaoming.dao.MemberDao;
import com.xiaoming.dao.OrgJoinApplicationDao;
import com.xiaoming.dao.OrganizationDao;
import com.xiaoming.dao.UnreadDao;
import com.xiaoming.dao.UserDao;
import com.xiaoming.domain.Department;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.OrgJoinApplication;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.Pager;
import com.xiaoming.domain.Unread;
import com.xiaoming.domain.User;
import com.xiaoming.dto.UserInfoDto;
import com.xiaoming.service.JoinApplyService;

@Service
@Transactional
public class JoinApplyServiceImpl implements JoinApplyService {

	@Resource
	OrganizationDao orgDao;
	@Resource
	MemberDao memberDao;
	@Resource
	UserDao userDao;
	@Resource
	DepartmentDao departmentDao;
	@Resource
	OrgJoinApplicationDao joinApplyDao;
	@Resource
	UnreadDao unreadDao;

	@Override
	public void apply(long orgId, long userId, UserInfoDto userInfo) throws Exception {
		User user = userDao.get(userId);
		Organization org = orgDao.get(orgId);
		if (org == null) {
			throw new Exception(ErrorConstants.DEPARTMENT_NOT_EXIST);
		}
		OrgJoinApplication apply = new OrgJoinApplication();
		// 设置部门
		Department department = departmentDao.get(Long.parseLong(userInfo.getDepartmentId()));
		if (department == null) {
			throw new Exception(ErrorConstants.DEPARTMENT_NOT_EXIST);
		}
		departmentDao.save(department);
		// 设置组织
		apply.setOrganization(org);
		// 设置部门
		apply.setDepartment(department);
		// 设置职位
		apply.setPosition(userInfo.getPosition());
		// 设置该成员属于当前用户
		apply.setUser(user);
		apply.setApplyTime(new Date());
		// 保存该成员
		joinApplyDao.save(apply);
	}

	@Override
	public OrgJoinApplication handle(long applyId, boolean agree, String reason) throws Exception {
		OrgJoinApplication apply = joinApplyDao.get(applyId);
		if (apply == null) { // apply不存在，退出
			throw new Exception(ErrorConstants.JOINAPPLY_NOT_EXIST);
		}
		apply.setIsPassed(agree);
		apply.setHandleTime(new Date());
		apply.setIsHandled(true);
		apply.setReason(reason);
		// 如果同意，爲用戶創建一個成員（Member）
		if (agree) {
			User user = apply.getUser();
			Member member = new Member();
			member.setDepartment(apply.getDepartment()); // 設置部門爲他申請的部門
			member.setEdition(apply.getOrganization().getCurrentEdition()); // 屆爲當前屆
			member.setPosition(apply.getPosition()); // 職位爲申請的職位
			member.setUser(user); // 用戶爲申請的用戶
			member.setState(Constants.MEMBER_STATE_NORMAL); // 狀態設這爲正常
			
			memberDao.save(member);
			
			//开始为改成员统计未读消息
			Unread unread = new Unread();
			unread.setId(member.getId());
			unread.setMember(member);
			unreadDao.save(unread);
			
			if (user.getDefaultMember() == null) { //如果这个用户没有默认的组织，把这个组织设为默认
				user.setDefaultMember(member);
			}
		}
		// 更新
		joinApplyDao.update(apply);
		return apply;
	}

	@Override
	public Pager<OrgJoinApplication> findByOrg(long orgId) {
		String hql = "from OrgJoinApplication apply where apply.organization.id= :id order by apply.applyTime desc";
		Map<String, Object> alias = new HashMap<>();
		alias.put("id", orgId);
		return joinApplyDao.findByAlias(hql, alias);
	}

	@Override
	public Pager<OrgJoinApplication> findByUser(long userId) {
		String hql = "from OrgJoinApplication apply where apply.user.id= :id order by apply.applyTime desc";
		Map<String, Object> alias = new HashMap<>();
		alias.put("id", userId);
		return joinApplyDao.findByAlias(hql, alias);
	}

}
