package com.xiaoming.domain;

import java.util.Date;
import java.util.Set;

/**
 * 物资出借记录类
 * 
 * @author Yunfei
 *
 */
public class MaterialLendLog {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 借出数量
	 */
	private Integer count;
	/**
	 * 已还数量
	 */
	private Integer revertCount;
	/**
	 * 借方姓名
	 */
	private String borrowerName;
	/**
	 * 借方联系方式
	 */
	private String borrowerPhoneNumber;
	/**
	 * 借出日期
	 */
	private Date borrowDate;
	/**
	 * 应该归还日期
	 */
	private Date revertDate;
	/**
	 * 完成日期
	 */
	private Date finishDate;
	/**
	 * 状态： 0 未归还 1 已归还 -1 超期未归还
	 */
	private Integer state;

	/**
	 * 该条记录中的物资
	 */
	private Material material;
	/**
	 * 子记录
	 */
	private Set<MaterialLendLogChild> childLogs;

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

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @return the borrowerName
	 */
	public String getBorrowerName() {
		return borrowerName;
	}

	/**
	 * @param borrowerName
	 *            the borrowerName to set
	 */
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	/**
	 * @return the borrowerPhoneNumber
	 */
	public String getBorrowerPhoneNumber() {
		return borrowerPhoneNumber;
	}

	/**
	 * @param borrowerPhoneNumber
	 *            the borrowerPhoneNumber to set
	 */
	public void setBorrowerPhoneNumber(String borrowerPhoneNumber) {
		this.borrowerPhoneNumber = borrowerPhoneNumber;
	}

	/**
	 * @return the borrowDate
	 */
	public Date getBorrowDate() {
		return borrowDate;
	}

	/**
	 * @param borrowDate
	 *            the borrowDate to set
	 */
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	/**
	 * @return the revertDate
	 */
	public Date getRevertDate() {
		return revertDate;
	}

	/**
	 * @param revertDate
	 *            the revertDate to set
	 */
	public void setRevertDate(Date revertDate) {
		this.revertDate = revertDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * @param material
	 *            the material to set
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	public Integer getRevertCount() {
		return revertCount;
	}

	public void setRevertCount(Integer revertCount) {
		this.revertCount = revertCount;
	}

	public Set<MaterialLendLogChild> getChildLogs() {
		return childLogs;
	}

	public void setChildLogs(Set<MaterialLendLogChild> childLogs) {
		this.childLogs = childLogs;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
}
