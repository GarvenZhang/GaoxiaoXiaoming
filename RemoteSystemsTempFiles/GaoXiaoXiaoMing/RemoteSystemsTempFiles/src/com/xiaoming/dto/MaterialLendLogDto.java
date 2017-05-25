package com.xiaoming.dto;

import java.util.Date;

import com.xiaoming.domain.MaterialLendLog;

public class MaterialLendLogDto {
	/**
	 * 主键，id
	 */
	private String id;
	/**
	 * 借出数量
	 */
	private int count;
	/**
	 * 已还数量
	 */
	private int revertCount;
	/**
	 * 借方姓名
	 */
	private String borrower;
	/**
	 * 借方联系方式
	 */
	private String phone;
	/**
	 * 借出日期
	 */
	private Date borrowDate;
	/**
	 * 应该归还日期
	 */
	private Date revertDate;
	/**
	 * 归还完成日期
	 */
	private Date finishDate;
	/**
	 * 状态： 0 未归还 1 已归还 2 超期未归还
	 */
	private int state;

	public MaterialLendLogDto() {

	}

	public MaterialLendLogDto(MaterialLendLog lendLog) {
		id = lendLog.getId().toString();
		count = lendLog.getCount();
		revertCount = lendLog.getRevertCount();
		borrower = lendLog.getBorrowerName();
		phone = lendLog.getBorrowerPhoneNumber();
		borrowDate = lendLog.getBorrowDate();
		revertDate = lendLog.getRevertDate();
		finishDate = lendLog.getFinishDate();
		state = lendLog.getState();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getRevertDate() {
		return revertDate;
	}

	public void setRevertDate(Date revertDate) {
		this.revertDate = revertDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getRevertCount() {
		return revertCount;
	}

	public void setRevertCount(int revertCount) {
		this.revertCount = revertCount;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

}
