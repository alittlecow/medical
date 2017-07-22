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
public class AccountSettlementEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//分销商id
	private String dealerId;
	//结算单状态
	private String status;
	//实际金额
	private BigDecimal actualMoney;
	//计划金额
	private BigDecimal planMoney;
	//创建时间
	private Date createTime;
	//分销商确认收款时间
	private Date dealerConfirmTime;
	//总公司确定时间
	private Date adminConfirmAt;
	//总公司确认人
	private Date adminConfirmBy;

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
	 * 设置：结算单状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：结算单状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：实际金额
	 */
	public void setActualMoney(BigDecimal actualMoney) {
		this.actualMoney = actualMoney;
	}
	/**
	 * 获取：实际金额
	 */
	public BigDecimal getActualMoney() {
		return actualMoney;
	}
	/**
	 * 设置：计划金额
	 */
	public void setPlanMoney(BigDecimal planMoney) {
		this.planMoney = planMoney;
	}
	/**
	 * 获取：计划金额
	 */
	public BigDecimal getPlanMoney() {
		return planMoney;
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
	 * 设置：分销商确认收款时间
	 */
	public void setDealerConfirmTime(Date dealerConfirmTime) {
		this.dealerConfirmTime = dealerConfirmTime;
	}
	/**
	 * 获取：分销商确认收款时间
	 */
	public Date getDealerConfirmTime() {
		return dealerConfirmTime;
	}
	/**
	 * 设置：总公司确定时间
	 */
	public void setAdminConfirmAt(Date adminConfirmAt) {
		this.adminConfirmAt = adminConfirmAt;
	}
	/**
	 * 获取：总公司确定时间
	 */
	public Date getAdminConfirmAt() {
		return adminConfirmAt;
	}
	/**
	 * 设置：总公司确认人
	 */
	public void setAdminConfirmBy(Date adminConfirmBy) {
		this.adminConfirmBy = adminConfirmBy;
	}
	/**
	 * 获取：总公司确认人
	 */
	public Date getAdminConfirmBy() {
		return adminConfirmBy;
	}
}
