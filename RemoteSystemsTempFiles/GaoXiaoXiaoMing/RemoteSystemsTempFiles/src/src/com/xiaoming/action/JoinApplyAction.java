package com.xiaoming.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.base.Role;
import com.xiaoming.constants.Constants;
import com.xiaoming.constants.ErrorConstants;
import com.xiaoming.domain.OrgJoinApplication;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.JoinApplyDto;
import com.xiaoming.dto.UserInfoDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.StringUtil;
import com.xiaoming.util.SystemContext;
import com.xiaoming.util.authority.Authority;

@Controller
@Scope("prototype")
public class JoinApplyAction extends BaseAction<JoinApplyDto> {

	@Authority(role = Role.ORG_USER)
	public String apply() {
		try {
			model = getModelFromJson(JoinApplyDto.getClassMap());
			String id = model.getId();
			long orgId;
			// 如果收到的id为空，错误
			if (id != null && !"".equals(id)) {
				orgId = Long.parseLong(id);
			} else {
				result = JsonUtil.fail(ErrorConstants.ID_IS_NULL);
				return ERROR;
			}
			// 验证用户信息非空
			if (model.getUserInfo() == null) {
				result = JsonUtil.fail("userInfoNull");
			}
			// 验证部门id非空
			if (StringUtil.isNull(model.getUserInfo().getDepartmentId())) {
				result = JsonUtil.fail(ErrorConstants.ID_IS_NULL);
			}
			// 调用service
			joinApplyService.apply(orgId, getCurrentUserId(), model.getUserInfo());
			result = JsonUtil.succ();
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 处理请假请求
	 * 
	 * @return
	 */
	@Authority(role = Role.ORG_ADMIN)
	public String handle() {
		try {
			model = getModelFromJson();

			if (StringUtil.isNull(model.getId())) {
				result = JsonUtil.fail(ErrorConstants.ID_IS_NULL);
				return ERROR;
			}
			long id = Long.parseLong(model.getId());
			boolean agree = model.getAgree();
			String reason = model.getReason();
			// 处理申请
			OrgJoinApplication apply = joinApplyService.handle(id, agree, reason);
			result = JsonUtil.succObject(new JoinApplyDto(apply));
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	public String list() {
		try {
			model = getModelFromJson();
			// 设置分页参数
			SystemContext.setPageArgs(model.getPageSize(), model.getPageNum());
			Pager<OrgJoinApplication> applyPager = null;
			if(getRole().equals(Role.ORG_USER)){ //查询用户发出的申请
				applyPager = joinApplyService.findByUser(getCurrentUserId());
			}else if(getRole().equals(Role.ORG_ADMIN)){
				//查询组织收到的
				applyPager = joinApplyService.findByOrg(getOrgId());
			}else{ //没有权限
				result = JsonUtil.fail(ErrorConstants.AUTHORITY);
				return ERROR;
			}
			Pager<JoinApplyDto> dtoPager = new Pager<>();
			dtoPager.setPageArgs(applyPager);
			List<JoinApplyDto> dtoList = new ArrayList<>(applyPager.getRecordList().size());
			for (OrgJoinApplication apply : applyPager.getRecordList()) {
				dtoList.add(new JoinApplyDto(apply));
			}
			dtoPager.setRecordList(dtoList);
			result = JsonUtil.succObject(dtoPager);
			System.out.println(result.get("userInfo"));
		} catch (Exception e) {
			result = JsonUtil.fail();
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
}