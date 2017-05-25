package com.xiaoming.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoming.constants.Constants;
import com.xiaoming.domain.Assignment;
import com.xiaoming.domain.AssignmentMember;
import com.xiaoming.util.JsonIgnore;

public class AssignmentDto extends PageSupport {
	/**
	 * 主键，id
	 */
	private String id;
	/**
	 * 任务内容
	 */
	private String content;
	/**
	 * 任务发布时间
	 */
	private Date publishDate;
	/**
	 * 截止日期
	 */
	private Date deadline;

	/**
	 * 创建者
	 */
	private String publisher;
	/**
	 * 所属的项目
	 */
	private ProjectDto project;
	/**
	 * 所属项目的id
	 */
	private Long projectId;
	/**
	 * 延期的天数，0表示没有延期
	 */
	private Long delay;
	/**
	 * 是否完成
	 */
	private boolean isFinished;
	/**
	 * 类型
	 * received：我收到的任务
	 * published：发出的任务
	 */
	private String type;
	/**
	 * 执行者
	 */
	private ArrayList<AssignmentMemberDto> exercisers;
	/**
	 * 执行者
	 */
	private String[] members;
	/**
	 * 标记该任务是否有效，删除之后设置为false
	 */
	private boolean isValid;
	/**
	 * 接收任务数组
	 */
	private ArrayList<AssignmentDto> assignList;
	
	@JsonIgnore
	public ArrayList<AssignmentDto> getAssignList() {
		return assignList;
	}
	
	public void setAssignList(ArrayList<AssignmentDto> assignList) {
		this.assignList = assignList;
	}
	/**
	 * @return the isValid
	 */
	@JsonIgnore
	public boolean isValid() {
		return isValid;
	}


	/**
	 * @param isValid the isValid to set
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public AssignmentDto(){
	
	}
	
//	/**
//	 * 跟据Assignment生成Dto
//	 */
//	public AssignmentDto(Assignment assign){
//		this.id = assign.getId();
//		this.setContent(assign.getContent());
//		this.setDeadline(assign.getDeadline());
//		this.setPublishDate(assign.getPublishDate());
//		this.setPublisher(assign.getPublisher().getUser().getRealName());
//		
//		//设置执行者
//		ArrayList<MemberDto> members = new ArrayList<>();
//		for (AssignmentMember am : assign.getMembers()) {
//			MemberDto mDto = new MemberDto();
//			//此处的id是，成员-工作中间表中的id
//			mDto.setId(am.getId().toString());
//			mDto.setName(am.getExerciser().getUser().getRealName());
//			mDto.setIsFinished(am.getIsFinished());
//			//计算延误时间
//			long delay = (new Date().getTime() - am.getAssignment().getDeadline().getTime())/(1000 * 24 * 60 *60);
//			mDto.setDelay(delay);
//			members.add(mDto);
//		}
//		this.setExercisers(members);
//		this.setProject(new ProjectDto(assign.getProjectBelongTo()));
//	}
	
	/**
	 * 跟据Assignment生成Dto
	 */
	public AssignmentDto(Assignment assign,Long memberId){
		this.id = assign.getId().toString();
		this.setContent(assign.getContent());
		this.setDeadline(assign.getDeadline());
		this.setPublishDate(assign.getPublishDate());
		this.setPublisher(assign.getPublisher().getUser().getRealName());
		
		//设置执行者
		ArrayList<AssignmentMemberDto> members = new ArrayList<>();
		for (AssignmentMember am : assign.getMembers()) {
			AssignmentMemberDto mDto = new AssignmentMemberDto();
			//此处的id是，成员-工作中间表中的id
			mDto.setId(am.getId().toString());
			mDto.setName(am.getExerciser().getUser().getRealName());
			mDto.setFinished(am.getIsFinished());
			//计算延误时间
			long delay = (new Date().getTime() - am.getAssignment().getDeadline().getTime())/(1000 * 24 * 60 *60);
			mDto.setDelay(delay);
			mDto.setMemberId(am.getExerciser().getId().toString());
			members.add(mDto);
		}
		if(memberId!=null && memberId!=0){
			if(assign.getPublisher().getId().equals(memberId)){
				this.setType("published");
			}else{
				this.setType("received");
			}
		}
		this.setExercisers(members);
		this.setProject(new ProjectDto(assign.getProjectBelongTo()));
	}
	
	public AssignmentDto(Assignment assign){
		this(assign,null);
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the delay
	 */
	public Long getDelay() {
		return delay;
	}

	/**
	 * @param delay the delay to set
	 */
	public void setDelay(Long delay) {
		this.delay = delay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the publishDate
	 */
	public Date getPublishDate() {
		return publishDate;
	}
	/**
	 * @param publishDate the publishDate to set
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	/**
	 * @return the deadline
	 */
	public Date getDeadline() {
		return deadline;
	}
	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * @return the project
	 */
	public ProjectDto getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(ProjectDto project) {
		this.project = project;
	}

	/**
	 * @return the projectId
	 */
	public Long getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	public ArrayList<AssignmentMemberDto> getExercisers() {
		return exercisers;
	}

	public void setExercisers(ArrayList<AssignmentMemberDto> exercisers) {
		this.exercisers = exercisers;
	}

	public static Map<String, Class> getClassMap(){
		Map<String, Class> classMap = new HashMap<>();
		classMap.put("project", ProjectDto.class);
		classMap.put("exercisers", AssignmentMemberDto.class);
		classMap.put("assignList", AssignmentDto.class);
		return classMap;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	@JsonIgnore
	public String[] getMembers() {
		return members;
	}

	public void setMembers(String[] members) {
		this.members = members;
	}
	
}
