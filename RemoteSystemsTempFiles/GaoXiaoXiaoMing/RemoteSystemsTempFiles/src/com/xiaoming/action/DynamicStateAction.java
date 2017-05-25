package com.xiaoming.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.base.Role;
import com.xiaoming.constants.ErrorConstants;
import com.xiaoming.domain.DynamicState;
import com.xiaoming.domain.OrganizationOperation;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.DynamicStateDto;
import com.xiaoming.dto.OrganizationOperationDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.StringUtil;
import com.xiaoming.util.SystemContext;
import com.xiaoming.util.authority.Authority;

/**
 * 工作动态
 * 
 * @author Yunfei
 *
 */
@Controller
@Scope("prototype")
public class DynamicStateAction extends BaseAction<DynamicStateDto> {

	/**
	 * 查询工作动态
	 * 
	 * @return
	 */
	public String list() {
		try {
			model = getModelFromJson(DynamicStateDto.getClassMap());
			String projectId = model.getProjectId(); // 所属项目的id
			// 设置分页
			SystemContext.setPageArgs(model.getPageSize(), model.getPageNum());

			// 查
			Pager<DynamicState> pager = dynamicStateService.list(projectId, getCurrentMemberId());
			// 改装
			List<DynamicStateDto> dtoList = new ArrayList<DynamicStateDto>();
			for (DynamicState dynamicState : pager.getRecordList()) {
				dtoList.add(new DynamicStateDto(dynamicState));
			}
			Pager<DynamicStateDto> pagerDto = new Pager<>();
			pagerDto.setPageArgs(pager);
			pagerDto.setRecordList(dtoList);

			// 要忽略的字段
			String[] ignore = { "content", "exercisers", "finished", "project", "publishDate", "valid", "publisher",
					"deadline" };

			result = JsonUtil.succObject(pagerDto, ignore);
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	@Authority(role = Role.ORG_ADMIN)
	public String list_org() {
		try {
			model = getModelFromJson();

			// 设置分页
			if (model.getPageSize() > 0){
				SystemContext.setPageSize(model.getPageSize());
			}
			if (model.getPageNum() > 0){
				SystemContext.setPageOffset(model.getPageNum());
			}

			Pager<OrganizationOperation> ooPager = organizationOperationService.list(getOrgId());
			Pager<OrganizationOperationDto> dtoPager = new Pager<>();
			List<OrganizationOperationDto> dtoList = new ArrayList<>(ooPager.getRecordList().size());
			for (OrganizationOperation oo : ooPager.getRecordList()) {
				dtoList.add(new OrganizationOperationDto(oo));
			}
			dtoPager.setRecordList(dtoList);
			dtoPager.setPageArgs(ooPager);

			result = JsonUtil.succObject(dtoPager);
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

}
