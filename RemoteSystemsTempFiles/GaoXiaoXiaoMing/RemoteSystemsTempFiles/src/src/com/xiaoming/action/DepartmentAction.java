package com.xiaoming.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.domain.Department;
import com.xiaoming.dto.DepartmentDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.StringUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype") 
public class DepartmentAction extends BaseAction<DepartmentDto> {
	
	public String list(){
		try{
			long orgId;
			if(!StringUtil.isNull(model.getId())){
				orgId = Long.parseLong(model.getId());
			}else{
				orgId = getOrgId();
			}
			List<Department> departmentList = departmentService.list(orgId);
			List<DepartmentDto> dtoList = new ArrayList<>();
			for (Department department : departmentList) {
				dtoList.add(new DepartmentDto(department));
			}
			result = JsonUtil.succList(dtoList);
		}catch(Exception e){
			result = JsonUtil.fail();
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
}
