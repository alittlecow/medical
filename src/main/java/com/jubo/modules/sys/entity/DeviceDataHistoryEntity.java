package com.jubo.modules.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public class DeviceDataHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//用户类型（0app用户 1ID卡用户）
	private Integer userType;
	//用户id
	private String userId;
	//设备id
	private String deviceId;
	//上报时间
	private Date reportTime;
	//数值
	private BigDecimal value;
	//创建时间
	private Date createTime;

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
	 * 设置：用户类型（0app用户 1ID卡用户）
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	/**
	 * 获取：用户类型（0app用户 1ID卡用户）
	 */
	public Integer getUserType() {
		return userType;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：设备id
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * 获取：设备id
	 */
	public String getDeviceId() {
		return deviceId;
	}
	/**
	 * 设置：上报时间
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	/**
	 * 获取：上报时间
	 */
	public Date getReportTime() {
		return reportTime;
	}
	/**
	 * 设置：数值
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	/**
	 * 获取：数值
	 */
	public BigDecimal getValue() {
		return value;
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
}
