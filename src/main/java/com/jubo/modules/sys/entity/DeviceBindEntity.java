package com.jubo.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public class DeviceBindEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//设备id
	private String deviceId;
	//分销商id
	private String dealerId;
	//绑定时间
	private Date bindTime;

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
	 * 设置：分销商id
	 */
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	/**
	 * 获取：分销商id
	 */
	public String getDealerId() {
		return dealerId;
	}
	/**
	 * 设置：绑定时间
	 */
	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}
	/**
	 * 获取：绑定时间
	 */
	public Date getBindTime() {
		return bindTime;
	}
}
