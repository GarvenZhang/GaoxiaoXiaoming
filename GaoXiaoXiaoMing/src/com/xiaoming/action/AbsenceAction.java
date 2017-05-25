package com.xiaoming.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.xiaoming.base.Role;
import com.xiaoming.domain.Absence;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.AbsenceDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.SystemContext;
import com.xiaoming.util.authority.Authority;

@Controller
@Scope("prototype")
public class AbsenceAction extends BaseAction<AbsenceDto> implements ModelDriven<AbsenceDto> {

	/**
	 * /org_user/absence_save.action 添加一个请假申请
	 * 
	 * @return
	 */
	@Authority(role = Role.ORG_USER)
	public String save() {
		try {
			model = getModelFromJson(AbsenceDto.getClassMap());
			// 判断空
			if (null == model.getContent() || "".equals(model.getContent())) {
				result = JsonUtil.fail("content is null");
				return ERROR;
			}
			if (null == model.getAbsenceTime()) {
				result = JsonUtil.fail("time of absences is null");
				return ERROR;
			}
			Absence absence = new Absence();
			absence.setAbsenceTime(model.getAbsenceTime());
			absence.setEndTime(model.getEndTime());
			absence.setContent(model.getContent());
			absence.setCreateTime(new Date());
			if (null != model.getMembers() && model.getMembers().length != 0) { //新接口
				absenceService.save(absence, model.getMembers(), getCurrentMemberId());
			} else if (null != model.getReceivers() && model.getReceivers().size() != 0) { //旧接口
				absenceService.save(absence, model.getReceivers(), getCurrentMemberId());
			} else {
				result = JsonUtil.fail("receiver is null");
				return ERROR;
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
		}
		result = JsonUtil.succ();
		return SUCCESS;
	}

	public String list() {
		try {
			model = getModelFromJson();
			// 设置排序方式
			model.setOrder("desc");
			model.setSort("absence.createTime");
			// 设置分页参数
			SystemContext.setPageArgs(model);

			Pager<Absence> pager;
			if ("received".equals(model.getType())) {
				pager = absenceService.findReceived(getCurrentMemberId());
			} else {
				pager = absenceService.findPublished(getCurrentMemberId());
			}
			// 转成dto
			Pager<AbsenceDto> pagerDto = new Pager<>();
			pagerDto.setPageArgs(pager);
			List<AbsenceDto> dtoList = new ArrayList<>();
			for (Absence absence : pager.getRecordList()) {
				dtoList.add(new AbsenceDto(absence));
			}
			pagerDto.setRecordList(dtoList);
			result = JsonUtil.succObject(pagerDto,"receivers");

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
	@Authority(role = Role.ORG_USER)
	public String handle() {
		try {
			model = getModelFromJson();
			long applyId = model.getId();
			boolean agree = model.isAgree();

			absenceService.handle(applyId, agree);
			result = JsonUtil.succ();
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 修改请假请求
	 * 
	 * @return
	 */
	@Authority(role = Role.ORG_USER)
	public String update() {
		try {
			model = getModelFromJson(AbsenceDto.getClassMap());
			// 判断空
			if (null == model.getContent() || "".equals(model.getContent())) {
				result = JsonUtil.fail("content is null");
				return ERROR;
			}
			if (null == model.getAbsenceTime()) {
				result = JsonUtil.fail("time of absences is null");
				return ERROR;
			}
			if (null == model.getReceivers() || model.getReceivers().size() == 0) {
				result = JsonUtil.fail("receiver is null");
				return ERROR;
			}

			Absence absence = new Absence();
			absence.setAbsenceTime(model.getAbsenceTime());
			absence.setContent(model.getContent());
			absenceService.update(absence, model.getReceivers());
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		result = JsonUtil.succ();
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Authority(role = Role.ORG_USER)
	public String delete() {
		try {
			model = getModelFromJson();
			absenceService.delete(model.getId());
			result = JsonUtil.succ();
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

}
