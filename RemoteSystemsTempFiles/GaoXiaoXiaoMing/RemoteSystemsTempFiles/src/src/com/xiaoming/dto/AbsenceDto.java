package com.xiaoming.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoming.domain.Absence;
import com.xiaoming.domain.AbsenceApply;
import com.xiaoming.domain.Member;
import com.xiaoming.util.JsonIgnore;

/**
 * 请假类的dto
 * 
 * @author Yunfei
 *
 */
public class AbsenceDto extends PageSupport {
	/**
	 * id
	 */
	private long id;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 要请假的时间
	 */
	private Date absenceTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 申請
	 */
	private List<AbsenceApplyDto> applys;
	/**
	 * 接收者
	 */
	private List<MemberDto> receivers;
	/**
	 * 是否同意
	 */
	private boolean agree;

	/**
	 * 类型 received: published:
	 */
	private String type;
	
	private String[] members;

	public AbsenceDto() {

	}

	public AbsenceDto(Absence absence) {
		this.setAbsenceTime(absence.getAbsenceTime());
		this.setEndTime(absence.getEndTime());
		this.setContent(absence.getContent());
		this.setId(absence.getId());
		List<AbsenceApplyDto> applyDtos = new ArrayList<>();
		for (AbsenceApply apply : absence.getAbsenceApply()) {
			applyDtos.add(new AbsenceApplyDto(apply));
		}
		this.setApplys(applyDtos);
	}

	public static Map<String, Class> getClassMap() {
		Map<String, Class> classMap = new HashMap<>();
		classMap.put("receivers", MemberDto.class);
		return classMap;
	}

	/**
	 * @return the receivers
	 */
	public List<MemberDto> getReceivers() {
		return receivers;
	}

	/**
	 * @param receivers
	 *            the receivers to set
	 */
	public void setReceivers(List<MemberDto> receivers) {
		this.receivers = receivers;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the absenceTime
	 */
	public Date getAbsenceTime() {
		return absenceTime;
	}

	/**
	 * @param absenceTime
	 *            the absenceTime to set
	 */
	public void setAbsenceTime(Date absenceTime) {
		this.absenceTime = absenceTime;
	}

	public List<AbsenceApplyDto> getApplys() {
		return applys;
	}

	public void setApplys(List<AbsenceApplyDto> applys) {
		this.applys = applys;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the agree
	 */
	public boolean isAgree() {
		return agree;
	}

	/**
	 * @param agree the agree to set
	 */
	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@JsonIgnore
	public String[] getMembers() {
		return members;
	}

	public void setMembers(String[] members) {
		this.members = members;
	}

}
