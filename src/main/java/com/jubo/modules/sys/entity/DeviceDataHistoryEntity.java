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
	//设备编码
	private String deviceCode;
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


	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
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
