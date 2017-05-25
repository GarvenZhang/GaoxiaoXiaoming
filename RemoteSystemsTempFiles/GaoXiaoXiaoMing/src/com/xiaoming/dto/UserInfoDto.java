package com.xiaoming.dto;

import com.xiaoming.base.Gender;
import com.xiaoming.domain.Campus;
import com.xiaoming.domain.Grade;
import com.xiaoming.domain.Image;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.User;
import com.xiaoming.util.JsonIgnore;

/**
 * 用户详细信息
 * 
 * @author Yunfei
 *
 */
public class UserInfoDto {
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 组织名
	 */
	private String organization;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 学校
	 */
	private UniversityDto university;
	/**
	 * 校区
	 */
	private CampusDto campus;
	/**
	 * 部门
	 */
	private DepartmentDto department;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 年级
	 */
	private GradeDto grade;
	/**
	 * 电话号码
	 */
	private String phoneNumber;
	/**
	 * logo的url
	 */
	private String logoUrl;
	/**
	 * logo的id
	 */
	private String logoId;
	/**
	 * 校区的id
	 */
	private String campusId;
	/**
	 * 部门的id
	 */
	private String departmentId;
	/**
	 * 年级的id
	 */
	private String gradeId;

	public UserInfoDto() {
	}

	public UserInfoDto(Member member) {
		User user = member.getUser();
		name = user.getRealName();
		organization = member.getDepartment().getOrgBelong().getName();
		department = new DepartmentDto(member.getDepartment());
		position = member.getPosition();
		// 年级，防止空指针
		Grade grade = user.getGrade();
		if (grade != null) {
			this.grade = new GradeDto(grade);
		} else {
			this.grade = new GradeDto();
		}
		if(member.getUser().getGender()!=null){
			gender = member.getUser().getGender().getName();
		}else{
			gender = Gender.MALE.getName();
		}
		phoneNumber = user.getPhoneNumber();
		Image logo = user.getLogo();
		if(logo!=null){
			logoUrl = logo.getUrl();
		}
		loginName = user.getloginName();
		// 学校信息，判断非空，防止空指针异常
		Campus campus = user.getCampus();
		if (campus != null) {
			university = new UniversityDto(campus);
			this.campus = new CampusDto(campus);
		} else {
			university = new UniversityDto();
			this.campus = new CampusDto();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getLoginName() {
		return loginName;
	}

	public GradeDto getGrade() {
		return grade;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public UniversityDto getUniversity() {
		return university;
	}

	public void setUniversity(UniversityDto university) {
		this.university = university;
	}

	public CampusDto getCampus() {
		return campus;
	}

	public void setCampus(CampusDto campus) {
		this.campus = campus;
	}

	public DepartmentDto getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDto department) {
		this.department = department;
	}

	public void setGrade(GradeDto grade) {
		this.grade = grade;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonIgnore
	public String getLogoId() {
		return logoId;
	}

	public void setLogoId(String logoId) {
		this.logoId = logoId;
	}

	@JsonIgnore
	public String getCampusId() {
		return campusId;
	}

	public void setCampusId(String campusId) {
		this.campusId = campusId;
	}

	@JsonIgnore
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	@JsonIgnore
	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
}
