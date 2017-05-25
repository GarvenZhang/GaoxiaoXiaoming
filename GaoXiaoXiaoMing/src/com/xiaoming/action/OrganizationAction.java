package com.xiaoming.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.xiaoming.base.Role;
import com.xiaoming.domain.Image;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.Pager;
import com.xiaoming.domain.Project;
import com.xiaoming.dto.ImageDto;
import com.xiaoming.dto.OrganizationDto;
import com.xiaoming.dto.ProjectDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.RandomString;
import com.xiaoming.util.SaveFile;
import com.xiaoming.util.SystemContext;
import com.xiaoming.util.authority.Authority;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype") 
public class OrganizationAction extends MediaBaseAction<OrganizationDto> {
	
	/**
	 * 查询详细信息
	 * @return
	 */
	public String info(){
		try{
			Organization org = organizationService.queryByOrgId(getOrgId());
			OrganizationDto orgDto = new OrganizationDto(org);
			result = JsonUtil.succObject(orgDto,"campusList");
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Authority(role=Role.ORG_ADMIN)
	public String list(){
		try{
			model = getModelFromJson();
			//设置默认的分页参数
			if(model.getPageSize()!=0){
				SystemContext.setPageSize(model.getPageSize());
			}
			if(model.getPageNum()!=0){
				SystemContext.setPageOffset(model.getPageNum());
			}
			
			Organization org = organizationService.get(getOrgId());
			Pager<Organization> orgPager = organizationService.findByUniversity(org.getCampus().getUniversity().getId());
			
			Pager<OrganizationDto> dtoPager = new Pager<>();
			List<OrganizationDto> dtoList = new ArrayList<>(orgPager.getRecordList().size());
			for (Organization organization : orgPager.getRecordList()) {
				if(organization.getIsPublic() != null && org.getIsPublic()){//如果是公开的
					dtoList.add(new OrganizationDto(organization));
				}
			}
			dtoPager.setPageArgs(orgPager);
			dtoPager.setRecordList(dtoList);
			
			String[] ignore = {"logoUrl","public"};
			
			result = JsonUtil.succObject(dtoPager,ignore);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Authority(role=Role.ORG_ADMIN)
	public String update(){
		try{
			model = getModelFromJson();
			Organization org = organizationService.update(model,getOrgId());
			result = JsonUtil.succObject(new OrganizationDto(org));
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String edition(){
		try{
			List<String> editionList = organizationService.getEdition(getOrgId());
			Map<String,Object> map = new HashMap<>();
			map.put("data",editionList);
			map.put("status", "true");
			result = JSONObject.fromObject(map);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String updateLogo(){
		try{
			String path = "upload\\image";
			// 重新设置文件名称
			setFileFileName("\\" + RandomString.createRandoString() + ".jpg");
			// logo文件路径
			String rootPath = ((ServletContext)ActionContext.getContext().get(ServletActionContext.SERVLET_CONTEXT)).getRealPath("/");
			String filePath = rootPath + path + getFileFileName();
			//保存图片
			SaveFile.save(file, filePath);
			Image image = imageService.save(file, path + getFileFileName() , getCurrentUserId());
			organizationService.updateLogo(image, getOrgId());
			ImageDto dto = new ImageDto();
			dto.setId(image.getId().toString());
			dto.setUrl(image.getUrl());
			result = JsonUtil.succObject(dto);
		}catch(IOException e){
			result = JsonUtil.fail("file");
			e.printStackTrace();
			return ERROR;
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}finally {
			
		}
		return SUCCESS;
	}
	
	public String projects(){
		try{
			List<Project> projectList = organizationService.listProject(getOrgId());
			List<ProjectDto> dtoList = new ArrayList<>();
			for (Project project : projectList) {
				dtoList.add(new ProjectDto(project));
			}
			result = JsonUtil.succList(dtoList);
		}catch(Exception e){
			result = JsonUtil.fail();
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String join_qcode(){
		
		ServletContext servletContext = (ServletContext)ActionContext.getContext().get(ServletActionContext.SERVLET_CONTEXT);
		System.out.println(servletContext.getContextPath());
		System.out.println(servletContext.getRealPath("/"));

		
		
		return SUCCESS;
	}
	
}
