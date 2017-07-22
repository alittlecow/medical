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
public class DeviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//设备编码
	private String code;
	//设备使用状态（0未使用 1使用中）
	private Integer useStatus;
	//是否故障（0正常 1故障）
	private Integer isBreakdown;
	//设备使用产生的总金额
	private BigDecimal totalMoney;
	//设备使用的总时间
	private Long totalTime;

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
	 * 设置：设备编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：设备编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：设备使用状态（0未使用 1使用中）
	 */
	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}
	/**
	 * 获取：设备使用状态（0未使用 1使用中）
	 */
	public Integer getUseStatus() {
		return useStatus;
	}
	/**
	 * 设置：是否故障（0正常 1故障）
	 */
	public void setIsBreakdown(Integer isBreakdown) {
		this.isBreakdown = isBreakdown;
	}
	/**
	 * 获取：是否故障（0正常 1故障）
	 */
	public Integer getIsBreakdown() {
		return isBreakdown;
	}
	/**
	 * 设置：设备使用产生的总金额
	 */
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	/**
	 * 获取：设备使用产生的总金额
	 */
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	/**
	 * 设置：设备使用的总时间
	 */
	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}
	/**
	 * 获取：设备使用的总时间
	 */
	public Long getTotalTime() {
		return totalTime;
	}
}
