package com.xiaoming.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.base.Role;
import com.xiaoming.constants.Constants;
import com.xiaoming.dao.DepartmentDao;
import com.xiaoming.dao.GradeDao;
import com.xiaoming.dao.MajorDao;
import com.xiaoming.dao.MemberDao;
import com.xiaoming.dao.OrganizationDao;
import com.xiaoming.dao.UserDao;
import com.xiaoming.domain.Department;
import com.xiaoming.domain.Major;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.Pager;
import com.xiaoming.domain.User;
import com.xiaoming.dto.MemberTableDto;
import com.xiaoming.service.MemberService;
import com.xiaoming.service.OrganizationOperationService;
import com.xiaoming.util.ExcelMaker;
import com.xiaoming.util.SystemContext;

@Repository
@Transactional
public class MemberServiceImpl implements MemberService {

	@Resource
	MemberDao memberDao;
	@Resource
	UserDao userDao;
	@Resource
	OrganizationDao organizationDao;
	@Resource
	GradeDao gradeDao;
	@Resource
	DepartmentDao departmentDao;
	@Resource
	MajorDao majorDao;
	@Resource
	OrganizationOperationService organizationOperationService;

	@Override
	public Pager<Member> list(long orgId, String edition, int sort) {
		// 设置排序
		switch (sort) {
		case Constants.DEFAULT_SORT:
			SystemContext.setSort("m.department.name");
			break;
		case Constants.NAME_SORT:
			SystemContext.setSort("m.user.realName");
			break;
		default:
			break;
		}
		Organization org = organizationDao.get(orgId);
		if(edition == null || "".equals(edition)){
			edition = org.getCurrentEdition();
		}
		// hql
		String hql = "from Member m where m.edition= :edition and m.department.orgBelong.id= :orgId";
		// 设置参数
		Map<String, Object> alias = new HashMap<>();
		alias.put("edition", edition);
		alias.put("orgId", orgId);
		// 查询
		return memberDao.findByAlias(hql, alias);
	}

	@Override
	public Member get(long id) {
		return memberDao.get(id);
	}

	@Override
	public InputStream downloadMemberTable(long orgId, String edition) {
		// hql
		String hql = "from Member m where m.edition= :edition and m.department.orgBelong.id= :orgId";
		// 设置参数
		Map<String, Object> alias = new HashMap<>();
		alias.put("edition", edition);
		alias.put("orgId", orgId);

		List<Member> memberList = memberDao.list(hql, alias);
		List<MemberTableDto> table = new ArrayList<>();
		for (Member member : memberList) {
			table.add(new MemberTableDto(member));
		}

		String[] headers = { "姓名", "部门", "职位", "联系电话" };

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		new ExcelMaker<MemberTableDto>().exportExcel(headers, table, out);

		return new ByteArrayInputStream(out.toByteArray());
	}

	@Override
	public void importFromMemberTable(Workbook workbook, long orgId) {
		Organization org = organizationDao.get(orgId);
		// 获取工作表
		Sheet sheet = workbook.getSheetAt(0);
		// 行数
		int rowCount = sheet.getPhysicalNumberOfRows();
		// 读取数据
		for (int i = 1; i < rowCount; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0;j<6;j++) {
				if(row.getCell(j)!=null){
			          row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
			     }
			}
			String name = row.getCell(0).getStringCellValue();
			String position = row.getCell(1).getStringCellValue();
			String department = row.getCell(2).getStringCellValue();
			String major = row.getCell(3).getStringCellValue();
			String grade = row.getCell(4).getStringCellValue();
			String phone = row.getCell(5).getStringCellValue();
			
			User user = userDao.getByPhone(phone);
			// 判断该用户是否存在，如果存在，帮他加入组织
			if (user == null) { // 如果不存在，创建临时用户
				// 设置用户基本信息
				User u = new User();
				u.setRealName(name);
				u.setPhoneNumber(phone);
				u.setCampus(org.getCampus());
				// 设置学校
				u.setCampus(org.getCampus());
				// 设置年级
				u.setGrade(gradeDao.getByName(grade));
				u.setRole(Role.ORG_USER);
				// 设置专业
				Major m = majorDao.getByName(major, u.getCampus().getUniversity().getId());
				if (m != null) {
					u.setMajor(m);
				}else{
					m = new Major();
					m.setName(major);
					m.setUniversity(org.getCampus().getUniversity());
					u.setMajor(m);
					majorDao.save(m);
				}

				// 创建成员，建立用户和组织的关系
				Member member = new Member();
				member.setPosition(position);

				// 设置部门信息
				Department realDepartment = null;
				for (Department d : org.getDepartments()) { // 跟据姓名查找所填的部门
					if (d.getName().equals(department)) {
						realDepartment = d;
					}
				}
				if (null == realDepartment) { // 如果该部门不存在，则创建一个新部门
					realDepartment = new Department();
					realDepartment.setName(department);
					realDepartment.setOrgBelong(org);
					departmentDao.save(realDepartment);
				}
				member.setDepartment(realDepartment);

				// 设置届
				member.setEdition(org.getCurrentEdition());
				// 设置为尚未加入
				member.setState(Constants.MEMBER_STATE_NOT_JOIN);
				member.setUser(u);
				member.setEdition(org.getCurrentEdition());
				// 设置用户的默认成员为该成员
				u.setDefaultMember(member);
				u.setState(Constants.USER_INVITED);

				memberDao.save(member);
				userDao.save(u);

			}else{//如果存在這個用戶
				//如果這個用戶還沒加入這個組織，幫助他加入
				if(memberDao.getByOrgAndUser(orgId, user.getId())==null){
					Member member  = new Member();
					
					//設置部門
					Department realDepartment = null;
					for (Department d : org.getDepartments()) { // 跟据姓名查找所填的部门
						if (d.getName().equals(department)) {
							realDepartment = d;
						}
					}
					if (null == realDepartment) { // 如果该部门不存在，则创建一个新部门
						realDepartment = new Department();
						realDepartment.setName(department);
						realDepartment.setOrgBelong(org);
						departmentDao.save(realDepartment);
					}
					//如果该用户还没有默认的组织，设置该组织为默认
					if(user.getDefaultMember()==null){
						user.setDefaultMember(member);
					}
					member.setDepartment(realDepartment);
					// 设置届
					member.setEdition(org.getCurrentEdition());
					// 设置为已加入
					member.setState(Constants.MEMBER_STATE_NORMAL);
					member.setUser(user);
					member.setEdition(org.getCurrentEdition());
					
					memberDao.save(member);
				}//如果用户存在，且已经加入组织，则跳过
				
			}
		}
		
		organizationOperationService.add(org, "导入人员表");

	}

	@Override
	public List<Member> listCurrentEditionAll(long orgId) {
		String hql = "from Member m where m.edition= :edition and m.department.orgBelong.id= :orgId and m.state=1";
		
		SystemContext.setSort("m.department.name");
		
		// 设置参数
		Organization org = organizationDao.get(orgId);
		Map<String, Object> alias = new HashMap<>();
		alias.put("edition", org.getCurrentEdition());
		alias.put("orgId", orgId);
		
		return memberDao.list(hql, alias);
	}

	@Override
	public Long getJoinCount(Long MemberTableId,String edition) throws Exception {
		return memberDao.getCountJoin(MemberTableId,edition);
	}

}
