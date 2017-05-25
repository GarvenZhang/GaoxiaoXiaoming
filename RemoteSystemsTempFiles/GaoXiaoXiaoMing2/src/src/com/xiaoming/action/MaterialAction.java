package com.xiaoming.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.base.Role;
import com.xiaoming.domain.Material;
import com.xiaoming.domain.MaterialLendLog;
import com.xiaoming.domain.MaterialLendLogChild;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.MaterialDto;
import com.xiaoming.dto.MaterialLendLogChildDto;
import com.xiaoming.dto.MaterialLendLogDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.StringUtil;
import com.xiaoming.util.SystemContext;
import com.xiaoming.util.authority.Authority;

@Controller
@Scope("prototype") 
public class MaterialAction extends BaseAction<MaterialDto> {
	
	@Authority(role=Role.ORG_ADMIN)
	public String list(){
		try{
			model = getModelFromJson();
			//设置分页参数
			SystemContext.setPageSize(model.getPageSize());
			SystemContext.setPageOffset(model.getPageNum());
			
			Pager<Material> materials = materialService.list(getOrgId());
			Pager<MaterialDto> pagerDto = new Pager<>();
			List<MaterialDto> dtoList = new ArrayList<>();
			for (Material material : materials.getRecordList()) {
				dtoList.add(new MaterialDto(material));
			}
			pagerDto.setPageArgs(materials);
			pagerDto.setRecordList(dtoList);
			
			result = JsonUtil.succObject(pagerDto);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Authority(role=Role.ORG_ADMIN)
	public String save(){
		try{
			model = getModelFromJson(MaterialDto.getClassMap());
			ArrayList<MaterialDto> dtoList = new ArrayList<>();
			for (MaterialDto materialDto : model.getMaterials()) {				
				Material material = new Material();
				material.setTotalCount(materialDto.getTotalCount());
				material.setName(materialDto.getName());
				material.setStorageLocation(materialDto.getStorageLocation());
				//默认参数
				material.setLentCount(0);
				material.setExistCount(materialDto.getTotalCount());
				material.setIsDeleted(false);
				material.setCreateTime(new Date());
				
				//添加
				material = materialService.save(material,getOrgId());
				dtoList.add(new MaterialDto(material));
			}
			
			result = JsonUtil.succList(dtoList);
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
			model = getModelFromJson(MaterialDto.getClassMap());
			ArrayList<MaterialDto> dtoList = new ArrayList<>();
			for (MaterialDto materialDto : model.getMaterials()) {
				Material material = materialService.get(Long.parseLong(materialDto.getId()));
				if(materialDto.getTotalCount()>0)
					material.setTotalCount(materialDto.getTotalCount());
				if(!StringUtil.isNull(materialDto.getName()))
					material.setName(materialDto.getName());
				if(!StringUtil.isNull(materialDto.getStorageLocation()))
					material.setStorageLocation(materialDto.getStorageLocation());
				materialService.updateMaterial(material);
				
				dtoList.add(new MaterialDto(material));
			}
			result = JsonUtil.succList(dtoList);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Authority(role=Role.ORG_ADMIN)
	public String lend(){
		try{
			model = getModelFromJson();
			//组装到实体
			MaterialLendLog lendLog = new MaterialLendLog();
			lendLog.setCount(model.getLentCount());
			lendLog.setBorrowerName(model.getName());
			lendLog.setBorrowDate(model.getBorrowDate());
			lendLog.setRevertDate(model.getRevertDate());
			lendLog.setBorrowerPhoneNumber(model.getPhone());
			//调用出借服务
			lendLog = materialService.lend(lendLog, Long.parseLong(model.getId()));
			
			result = JsonUtil.succObject(new MaterialLendLogDto(lendLog));
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Authority(role=Role.ORG_ADMIN)
	public String revert(){
		try{
			model = getModelFromJson();
			
			materialService.revert(Long.parseLong(model.getId()), model.getCount());
			
			result = JsonUtil.succ();
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Authority(role=Role.ORG_ADMIN)
	public String delete(){
		try{
			model = getModelFromJson();
			materialService.delete(Long.parseLong(model.getId()));
			result = JsonUtil.succ();
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String log(){
		try{
			model = getModelFromJson();
			SystemContext.setPageArgs(model.getPageSize(), model.getPageNum());
			Pager<MaterialLendLog> logPager = materialService.getLogs(getOrgId());
			List<MaterialLendLogDto> logDtos = new ArrayList<>();
			for (MaterialLendLog log : logPager.getRecordList()) {
				logDtos.add(new MaterialLendLogDto(log));
			}
			Pager<MaterialLendLogDto> dtoPager = new Pager<>();
			dtoPager.setPageArgs(logPager);
			dtoPager.setRecordList(logDtos);
			result = JsonUtil.succObject(dtoPager);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Authority(role=Role.ORG_ADMIN)
	public String child(){
		try{
			model = getModelFromJson();
			List<MaterialLendLogChild> childLogs = materialService.getChildLogs(Long.parseLong(model.getId()));
			List<MaterialLendLogChildDto> dtoList = new ArrayList<>();
			for (MaterialLendLogChild materialLendLogChild : childLogs) {
				dtoList.add(new MaterialLendLogChildDto(materialLendLogChild));
			}
			result = JsonUtil.succList(dtoList);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
}
