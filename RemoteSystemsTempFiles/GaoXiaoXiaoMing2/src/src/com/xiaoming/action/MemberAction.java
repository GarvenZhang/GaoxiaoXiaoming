package com.xiaoming.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.base.Role;
import com.xiaoming.constants.Constants;
import com.xiaoming.domain.Grade;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.Pager;
import com.xiaoming.domain.User;
import com.xiaoming.dto.MemberDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.StringUtil;
import com.xiaoming.util.SystemContext;
import com.xiaoming.util.authority.Authority;

@Controller
@Scope("prototype") 
public class MemberAction extends MediaBaseAction<MemberDto> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4462069515106645774L;
	
	private String edition;
	
	public String list(){
		try{
			model = getModelFromJson();
			SystemContext.setPageSize(model.getPageSize());
			SystemContext.setPageOffset(model.getPageNum());

			Pager<Member> pager = memberService.list(getOrgId(), model.getEdition(), model.getSort());
			List<MemberDto> memberDtos = new ArrayList<MemberDto>();
			Pager<MemberDto> dtoPager = new Pager<>();
			for (Member member : pager.getRecordList()) {
				MemberDto dto = new MemberDto();
				User u = member.getUser();
				dto.setId(member.getId().toString());
				dto.setEdition(member.getEdition());
				dto.setName(member.getUser().getRealName());
				dto.setDepartment(member.getDepartment().getName());
				dto.setPhoneNumber(member.getUser().getPhoneNumber());
				dto.setPosition(member.getPosition());
				dto.setMajor(u.getMajor()==null?"":u.getMajor().getName());
				dto.setGrade(u.getGrade()==null?"":u.getGrade().getName());
				dto.setState(member.getState());
				memberDtos.add(dto);
			}
			dtoPager.setPageArgs(pager);
			
			dtoPager.setRecordList(memberDtos);
			Long joinCount = memberService.getJoinCount(getOrgId(),model.getEdition());
			result = JsonUtil.succObject(dtoPager,"isAgree","delay","finished","pageNum","pageSize","isRead","sort","isFinished");
			result.put("joinCount", joinCount);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String simpleList(){
		try{
			List<Member> memberList = memberService.listCurrentEditionAll(getOrgId());
			List<MemberDto> dtoList = new ArrayList<>(memberList.size());
			
			for (Member member : memberList) {
				MemberDto dto = new MemberDto();
				dto.setName(member.getUser().getRealName());
				dto.setId(member.getId().toString());
				dto.setPosition(member.getPosition());
				dtoList.add(dto);
			}
			
			String[] ignore = {"delay","isAgree","isFinished","isRead","state"};
			
			result = JsonUtil.succList(dtoList,ignore);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String download(){
		try{
			model = getModelFromJson();
			if(model.getEdition() == null || "".equals(model.getEdition())){
				if(edition!=null && !"".equals(edition)){
					model.setEdition(edition);	
				}
				else{
					result = JsonUtil.fail("届数 不能为空");
					return ERROR;
				}
			}
			
			stream = memberService.downloadMemberTable(getOrgId(), model.getEdition());
			Organization org = organizationService.get(getOrgId());
			String fileName = org.getName()+model.getEdition()+"届通讯录"+Constants.EXCEL;
			fileName = new String(fileName.getBytes(),"ISO8859-1");
			setFileName(fileName);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return "excel";
	}
	
	@Authority(role=Role.ORG_ADMIN)
	public String importFromTable(){
		try{
			InputStream in = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(in);
			memberService.importFromMemberTable(workbook, getOrgId());
			
			in.close();
			result = JsonUtil.succ();
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
	}
	
	public String getInfo(){
		try{
			Member member = memberService.get(getCurrentMemberId());
			
			MemberDto dto = new MemberDto();
			dto.setOrganization(member.getDepartment().getOrgBelong().getName());
			dto.setName(member.getUser().getRealName());
			
			String[] ignore = {"delay","isAgree","isFinished","isRead","sort"};
			result = JsonUtil.succObject(dto, ignore);
		}catch(Exception e){
			e.printStackTrace();;
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}
	

}
