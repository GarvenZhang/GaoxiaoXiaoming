package com.xiaoming.domain;

import java.util.Date;

/**
 * 成员_任务 中间表
 * 
 * @author Yunfei
 *
 */
public class AssignmentMember {
	/**
	 * 任务id
	 */
	private Long id;
	/**
	 * 执行者
	 */
	private Member exerciser;
	/**
	 * 完成时间
	 */
	private Date finishTime;
	/**
	 * 是否完成
	 */
	private Boolean isFinished = false;

	/*
	 * 映射
	 */
	/**
	 * 任务
	 */
	private Assignment assignment;

	/*
	 * Getter and Setter
	 */
	/**
	 * @return the assignment
	 */
	public Assignment getAssignment() {
		return assignment;
	}

	/**
	 * @param assignment
	 *            the assignment to set
	 */
	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	/**
	 * @return the exerciser
	 */
	public Member getExerciser() {
		return exerciser;
	}

	/**
	 * @param exerciser
	 *            the exerciser to set
	 */
	public void setExerciser(Member exerciser) {
		this.exerciser = exerciser;
	}

	/**
	 * @return the finishTime
	 */
	public Date getFinishTime() {
		return finishTime;
	}

	/**
	 * @param finishTime
	 *            the finishTime to set
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * @return the isFinished
	 */
	public Boolean getIsFinished() {
		return isFinished;
	}

	/**
	 * @param isFinished
	 *            the isFinished to set
	 */
	public void setIsFinished(Boolean isFinished) {
		this.isFinished = isFinished;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
