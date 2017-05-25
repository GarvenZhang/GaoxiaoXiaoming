package com.xiaoming.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.xiaoming.base.Role;
import com.xiaoming.constants.ErrorConstants;
import com.xiaoming.domain.Assignment;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Pager;
import com.xiaoming.domain.Project;
import com.xiaoming.dto.AssignmentDto;
import com.xiaoming.dto.PagerDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.StringUtil;
import com.xiaoming.util.SystemContext;
import com.xiaoming.util.authority.Authority;

import antlr.StringUtils;

@Controller
@Scope("prototype")
public class AssignmentAction extends BaseAction<AssignmentDto> {

	/**
	 * /org_user/assignment_list.action 获取任务列表
	 * 
	 * @return
	 */
	@Authority(role = Role.ORG_USER)
	public String list() {
		try {
			model = getModelFromJson();
			// 设置分页参数
			SystemContext.setSort("assign.publishDate");
			SystemContext.setOrder("desc");
			SystemContext.setPageArgs(model.getPageSize(), model.getPageNum());
			// 查询
			Pager<Assignment> pager;
			if (null != model.getId() && model.getId().length() != 0) {
				pager = assignmentService.findByProjcet(Long.parseLong(model.getId()));
			} else {
				pager = assignmentService.findAll(getCurrentMemberId());
			}
			// 转换成dto
			Pager<AssignmentDto> pagerDto = new PagerDto<>();
			pagerDto.setPageArgs(pager);
			List<AssignmentDto> dtoList = new ArrayList<>();
			if (null != pager && null != pager.getRecordList() && pager.getRecordCount() != 0) {
				for (Assignment assign : pager.getRecordList()) {
					dtoList.add(new AssignmentDto(assign, getCurrentMemberId()));
				}
			}
			pagerDto.setRecordList(dtoList);
			result = JsonUtil.succObject(pagerDto);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
	}
	
	/**
	 * 获取详情
	 * @return
	 */
	public String detail(){
		try{
			model = getModelFromJson();
			String idstr = model.getId();
			if(StringUtil.isNull(idstr)){
				result = JsonUtil.fail(ErrorConstants.ID_IS_NULL);
				return ERROR;
			}
			Long id = Long.parseLong(idstr);
			Assignment assignment = assignmentService.get(id);
			result = JsonUtil.succObject(new AssignmentDto(assignment),"projectId");
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	@Authority(role = Role.ORG_USER)
	public String save() {
		try {
			model = getModelFromJson(AssignmentDto.getClassMap());
			Member currentMember = memberService.get(getCurrentMemberId());
			ArrayList<AssignmentDto> resultList = new ArrayList<>();
			if (model.getAssignList() != null && model.getAssignList().size() > 0) {
				for (AssignmentDto assign : model.getAssignList()) {
					// 如果该任务所属的项目是新项目，则创建一个新项目
					Project project = new Project();
					if (assign.getProject().isNew()) {
						project.setName(assign.getProject().getName());
					} else {
						project.setId(assign.getProject().getId());
					}
					// 将dto转成assignment
					Assignment assignment = new Assignment();
					assignment.setContent(assign.getContent());
					assignment.setDeadline(assign.getDeadline());
					assignment.setUpdateTime(new Date());
					assignment.setPublishDate(new Date());
					assignment.setPublisher(currentMember);
					assignment.setIsValid(true);
					// 添加
					if (assign.getMembers() != null && assign.getMembers().length != 0) {
						assignment = assignmentService.save(assignment, project, assign.getMembers(),
								getCurrentMemberId());
					} else {
						assignment = assignmentService.save(assignment, project, assign.getExercisers(),
								getCurrentMemberId());
					}
					// 成功
					resultList.add(new AssignmentDto(assignment));
				}
				result = JsonUtil.succList(resultList);
			} else {
				// 如果该任务所属的项目是新项目，则创建一个新项目
				Project project = new Project();
				if (model.getProject().isNew()) {
					project.setName(model.getProject().getName());
				} else {
					project.setId(model.getProject().getId());
				}
				// 将dto转成assignment
				Assignment assignment = new Assignment();
				assignment.setContent(model.getContent());
				assignment.setDeadline(model.getDeadline());
				assignment.setUpdateTime(new Date());
				assignment.setPublishDate(new Date());
				assignment.setPublisher(currentMember);
				assignment.setIsValid(true);
				// 添加
				if (model.getMembers() != null && model.getMembers().length != 0) {
					assignment = assignmentService.save(assignment, project, model.getMembers(), getCurrentMemberId());
				} else {
					assignment = assignmentService.save(assignment, project, model.getExercisers(),
							getCurrentMemberId());
				}
				result = JsonUtil.succObject(new AssignmentDto(assignment));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * /org_user/assignment_finish.action 完成任务
	 * 
	 * @return
	 */
	@Authority(role = Role.ORG_USER)
	public String finish() {
		try {
			model = getModelFromJson();
			long amId = Long.parseLong(model.getId());
			boolean isFinished = model.isFinished();
			assignmentService.finish(amId, isFinished);

			result = JsonUtil.succ();
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * /org_user/assignment_update.action 更新一个任务
	 * 
	 * @return
	 */
	@Authority(role = Role.ORG_USER)
	public String update() {
		try {
			model = getModelFromJson(AssignmentDto.getClassMap());
			Assignment assignment = new Assignment();
			assignment.setContent(model.getContent());
			assignment.setDeadline(model.getDeadline());
			assignment.setId(Long.parseLong(model.getId()));
			assignment.setUpdateTime(new Date());
			// 如果是新项目则创建一个项目
			Project project = new Project();
			if (model.getProject().isNew()) {
				project.setName(model.getProject().getName());
			} else {
				project.setId(model.getProject().getId());
			}

			assignment = assignmentService.update(assignment, project, model.getMembers(), getCurrentMemberId());

			result = JsonUtil.succObject(new AssignmentDto(assignment), "isMine", "projectId");
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * /org_user/assignment_delete.action 删除一个任务
	 * 
	 * @return
	 */
	@Authority(role = Role.ORG_USER)
	public String delete() {
		try {
			model = getModelFromJson();
			long assignmentId = Long.parseLong(model.getId());
			assignmentService.delete(assignmentId);
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		result = JsonUtil.succ();
		return SUCCESS;
	}
}
