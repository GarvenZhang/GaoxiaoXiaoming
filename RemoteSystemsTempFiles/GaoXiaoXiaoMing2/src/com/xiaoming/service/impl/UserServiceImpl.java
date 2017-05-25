package com.xiaoming.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.constants.Constants;
import com.xiaoming.constants.DefaultConstants;
import com.xiaoming.dao.CampusDao;
import com.xiaoming.dao.DepartmentDao;
import com.xiaoming.dao.GradeDao;
import com.xiaoming.dao.ImageDao;
import com.xiaoming.dao.MemberDao;
import com.xiaoming.dao.OrganizationDao;
import com.xiaoming.dao.UserDao;
import com.xiaoming.domain.Department;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.User;
import com.xiaoming.dto.UserInfoDto;
import com.xiaoming.service.UserService;
import com.xiaoming.util.MD5Util;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	@Resource
	private MemberDao memberDao;
	@Resource
	private ImageDao imageDao;
	@Resource
	private OrganizationDao organizationDao;
	@Resource
	private CampusDao campusDao;
	@Resource
	private DepartmentDao departmentDao;
	@Resource
	private GradeDao gradeDao;
	
	@Override
	public User login(String loginName, String password, String code) {
		Map<String, Object> alias = new HashMap<>();
		String hql = "from User u where u.loginName= :loginName"; // 账号登陆
		alias.put("loginName", loginName);
		User user = (User) userDao.queryObject(hql, alias);
		if (null == user) {
			hql = "from User u where u.phoneNumber= :phoneNumber"; // 手机号登陆
			alias.clear();
			alias.put("phoneNumber", loginName);
			user = (User) userDao.queryObject(hql, alias);
		}
		if (null != user && password.equals(MD5Util.encode(user.getPassword() + code))) {
			return user;
		}
		return null;
	}

	@Override
	public User login(String loginName, String password) {
		Map<String, Object> alias = new HashMap<>();
		String hql = "from User u where u.loginName= :loginName"; // 账号登陆
		alias.put("loginName", loginName);
		User user = (User) userDao.queryObject(hql, alias);
		if (null == user) {
			hql = "from User u where u.phoneNumber= :phoneNumber"; // 手机号登陆
			alias.clear();
			alias.put("phoneNumber", loginName);
			user = (User) userDao.queryObject(hql, alias);
		}
		if (null != user && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public void updateLoginInfo(User user, String ip) {
		user.setLastLoginIP(ip);
		user.setLastLoginTime(new Date());
		userDao.update(user);
	}

	@Override
	public void updatePassword(User user, String newPassword) {
		user = userDao.get(user.getId());
		user.setPassword(newPassword);
		userDao.update(user);
	}

	@Override
	public void updatePassword(long id, String newPassword) {
		User user = userDao.get(id);
		user.setPassword(newPassword);
		userDao.update(user);
	}

	@Override
	public boolean checkPassword(String loginName, String password, String code) {
		Map<String, Object> alias = new HashMap<>();
		String hql = "from User u where u.loginName=:loginName";
		alias.put("loginName", loginName);
		User user = (User) userDao.queryObject(hql, alias);
		if (null != user && password.equals(MD5Util.encode(user.getPassword() + code))) {
			return true;
		}
		return false;
	}

	@Override
	public User get(long id) {
		return userDao.get(id);
	}

	@Override
	public Member update(UserInfoDto userInfoDto, Member currentMember) {
		Member member = memberDao.get(currentMember.getId());
		User user = member.getUser();
		user.setPhoneNumber(userInfoDto.getPhoneNumber());

		// 更新logo，没传id的话就不更新
		String logoId = userInfoDto.getLogoId();
		if (logoId != null && !"".equals(logoId)) {
			user.setLogo(imageDao.get(Long.parseLong(logoId)));
		}

		// 更新校区，没传就不更新
		String campusId = userInfoDto.getCampusId();
		if (campusId != null && !"".equals(campusId)) {
			user.setCampus(campusDao.get(Long.parseLong(campusId)));
		}
		// 修改部门
		Department department = null;
		String departmentId = userInfoDto.getDepartmentId();
		if (departmentId != null && !"".equals(departmentId)) {
			member.setDepartment(department);
		}
		if (null != department) {
			member.setDepartment(department);
		}
		// 修改职位
		member.setPosition(userInfoDto.getPosition());
		return member;
	}

	@Override
	public Member update(UserInfoDto userInfoDto, long memberId) {
		Member member = memberDao.get(memberId);
		User user = member.getUser();
		user.setPhoneNumber(userInfoDto.getPhoneNumber());
		
		//修改年級，沒傳id不更新
		String gradeId = userInfoDto.getGradeId();
		if (gradeId != null && !"".equals(gradeId)) {
			user.setGrade(gradeDao.get(Long.parseLong(gradeId)));
		}

		// 更新logo，没传id的话就不更新
		String logoId = userInfoDto.getLogoId();
		if (logoId != null && !"".equals(logoId)) {
			user.setLogo(imageDao.get(Long.parseLong(logoId)));
		}

		// 更新校区，没传就不更新
		String campusId = userInfoDto.getCampusId();
		if (campusId != null && !"".equals(campusId)) {
			user.setCampus(campusDao.get(Long.parseLong(campusId)));
		}
		// 修改部门，没传不更新
		String departmentId = userInfoDto.getDepartmentId();
		if (departmentId != null && !"".equals(departmentId)) {
			member.setDepartment(departmentDao.get(Long.parseLong(departmentId)));
		}
		// 修改职位
		member.setPosition(userInfoDto.getPosition());
		return member;
	}

	@Override
	public User register(User user) {
		User trueUser = getByPhone(user.getPhoneNumber());
		// 判断是否已经被其他组织导入,即存在数据库中，如果存在，则完善信息
		if (trueUser != null) { //存在
			trueUser.setLoginName(user.getloginName());
			trueUser.setPassword(user.getPassword());
			trueUser.setRegisterDate(user.getRegisterDate());
			trueUser.setLastLoginTime(user.getLastLoginTime());
			trueUser.setLastLoginIP(user.getLastLoginIP());
			trueUser.setState(Constants.USER_NORMAL);

			userDao.update(trueUser);
		} else { //不存在
			user.setState(Constants.USER_NORMAL);
			userDao.save(user);
		}
		return user;
	}

	@Override
	public User register(User user, Organization org) {
		organizationDao.save(org);
		//添加一个默认部门
		Department department = new Department();
		department.setName(DefaultConstants.DEFAULT_DEPARTMENT_NAME);
		department.setOrgBelong(org);
		departmentDao.save(department);
		//关联管理员用户与组织
		user.setOrganization(org);
		user.setState(Constants.USER_NORMAL);
		user = userDao.save(user);
		return user;
	}

	@Override
	public boolean isExist(String phoneNumber) {
		return getByPhone(phoneNumber) != null ? true : false;
	}

	@Override
	public User getByPhone(String phone) {
		Map<String, Object> alias = new HashMap<>();
		String hql = "from User u where u.phoneNumber= :phone";
		alias.put("phone", phone);
		return (User) userDao.queryObject(hql, alias);
	}

	@Override
	public List<Member> getMembers(long userId) {
		String hql = "from Member m where m.user.id= :id";
		Map<String, Object> alias = new HashMap<>();
		alias.put("id", userId);
		return memberDao.list(hql, alias);
	}

	@Override
	public boolean checkPassword(long id, String password) {
		Map<String, Object> alias = new HashMap<>();
		String hql = "from User u where u.id= :id";
		alias.put("id", id);
		User user = (User) userDao.queryObject(hql, alias);
		if (null != user && password.equals(user.getPassword())) {
			return true;
		}
		return false;
	}

}
