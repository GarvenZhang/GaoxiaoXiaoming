package com.xiaoming.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoming.domain.Activity;
import com.xiaoming.domain.ActivityEnroll;
import com.xiaoming.util.JsonIgnore;

/**
 * 报名
 * 
 * @author Yunfei
 *
 */
public class ActivityDto {
	private String id;
	/**
	 * 创建者
	 */
	private MemberDto creater;
	/**
	 * 名称
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 信息
	 */
	private String info;
	/**
	 * 创建的时间
	 */
	private Date createTime;
	/**
	 * 邀请的成员
	 */
	private ArrayList<MemberDto> members;
	/**
	 * 所有报名情况
	 */
	private ArrayList<ActivityEnrollDto> enroll;
	/**
	 * 查询的信息类型： 0：发出的 1：收到的
	 */
	private int type;

	private int pageSize;
	private int pageNum;
	private String sort;
	private String order;

	private String[] memberId;
	private String[] infos;

	public ActivityDto() {

	}

	public ActivityDto(Activity activity) {
		MemberDto creater = new MemberDto();
		creater.setName(activity.getCreater().getUser().getRealName());
		creater.setId(activity.getCreater().getId().toString());

		id = activity.getId().toString();
		title = activity.getTitle();
		content = activity.getContent();
		createTime = activity.getCreateTime();
		info = activity.getInfo();
		this.creater = creater;

		// 配置报名情况
		ArrayList<ActivityEnrollDto> activityEnrollDtos = new ArrayList<>();
		for (ActivityEnroll activityEnroll : activity.getActivityEnrolls()) {
			activityEnrollDtos.add(new ActivityEnrollDto(activityEnroll));
		}

		enroll = activityEnrollDtos;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MemberDto getCreater() {
		return creater;
	}

	public void setCreater(MemberDto creater) {
		this.creater = creater;
	}
	@JsonIgnore
	public String[] getMemberId() {
		return memberId;
	}

	public void setMemberId(String[] memberId) {
		this.memberId = memberId;
	}
	@JsonIgnore
	public String[] getInfos() {
		return infos;
	}

	public void setInfos(String[] infos) {
		this.infos = infos;
	}

	public ArrayList<MemberDto> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<MemberDto> members) {
		this.members = members;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public ArrayList<ActivityEnrollDto> getEnroll() {
		return enroll;
	}

	public void setEnroll(ArrayList<ActivityEnrollDto> enroll) {
		this.enroll = enroll;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	@JsonIgnore
	public static Map<String, Class> getClassMap() {
		Map<String, Class> classMap = new HashMap<>();
		classMap.put("members", MemberDto.class);
		classMap.put("enroll", ActivityEnrollDto.class);
		return classMap;
	}

	@JsonIgnore
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	@JsonIgnore
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	@JsonIgnore
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@JsonIgnore
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
