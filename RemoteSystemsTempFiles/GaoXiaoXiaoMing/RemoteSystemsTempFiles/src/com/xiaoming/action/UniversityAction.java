package com.xiaoming.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.constants.ErrorConstants;
import com.xiaoming.constants.SessionConstants;
import com.xiaoming.domain.Campus;
import com.xiaoming.domain.Grade;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.University;
import com.xiaoming.dto.CampusDto;
import com.xiaoming.dto.GradeDto;
import com.xiaoming.dto.OrganizationDto;
import com.xiaoming.dto.UniversityDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.StringUtil;

@Controller
@Scope("prototype") 
public class UniversityAction extends BaseAction<UniversityDto> {
	public String campus(){
		try{
			model = getModelFromJson();
			
			String id = model.getId();
			
			//如果没传id就从当前登录的用户信息中获取
			if(id==null || "".equals(id)){
				id = organizationService.get(getOrgId()).getCampus().getUniversity().getId().toString();
			}
			
			List<Campus> campusList = campusService.getByUniversity(Long.parseLong(id));
			List<CampusDto> dtoList = new ArrayList<>();
			for (Campus campus : campusList) {
				dtoList.add(new CampusDto(campus));
			}
			
			result = JsonUtil.succList(dtoList);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String list(){
		List<University> universityList = universityService.listAll();
		List<UniversityDto> dtoList = new ArrayList<>();
		
		for (University uni : universityList) {
			UniversityDto dto = new UniversityDto();
			dto.setId(uni.getId().toString());
			dto.setName(uni.getName());
			
			dtoList.add(dto);
		}
		
		try {
			result = JsonUtil.succList(dtoList);
		} catch (InstantiationException e) {
			e.printStackTrace();
			result = JsonUtil.fail();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			result = JsonUtil.fail();
		}
		
		return SUCCESS;
	}
	
	public String org(){
		try{
			model = getModelFromJson();
			Long id = null;
			if(!StringUtil.isNull(model.getId())){
				id = Long.parseLong(model.getId());
			}else{
				if(getAttributeBySession(SessionConstants.ROLE)==null){
					result = JsonUtil.fail(ErrorConstants.NO_LOGIN);
					return ERROR;
				}
				Member member = memberService.get(getOrgId());
				id = member.getUser().getCampus().getUniversity().getId();
			}
			List<Organization> orgList = organizationService.listByUniversity(id);
			List<OrganizationDto> orgDtoList = new ArrayList<>(orgList.size());
			for (Organization organization : orgList) {
				OrganizationDto dto = new OrganizationDto();
				dto.setId(organization.getId());
				dto.setName(organization.getName());
				orgDtoList.add(dto);
			}
			
			result = JsonUtil.succList(orgDtoList,"pageSize","pageNum","public");
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String grade(){
		try{
			List<Grade> gradeList = gradeService.list();
			List<GradeDto> dtoList = new ArrayList<>(gradeList.size());
			for (Grade grade : gradeList) {
				dtoList.add(new GradeDto(grade));
			}
			result = JsonUtil.succList(dtoList);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
}
