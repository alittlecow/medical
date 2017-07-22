package com.jubo.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:50
 */
public class OrderLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//订单id
	private String orderId;
	//旧状态
	private String oldStatus;
	//新状态
	private String newStatus;
	//创建时间
	private Date createTime;
	//创建人
	private String createBy;
	//备注
	private String memo;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：订单id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单id
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * 设置：旧状态
	 */
	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}
	/**
	 * 获取：旧状态
	 */
	public String getOldStatus() {
		return oldStatus;
	}
	/**
	 * 设置：新状态
	 */
	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}
	/**
	 * 获取：新状态
	 */
	public String getNewStatus() {
		return newStatus;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 获取：备注
	 */
	public String getMemo() {
		return memo;
	}
}
