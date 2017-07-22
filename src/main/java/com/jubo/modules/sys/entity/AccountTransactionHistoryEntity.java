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
public class AccountTransactionHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//账户id
	private String accountId;
	//
	private BigDecimal beforeAdjustMoney;
	//
	private BigDecimal adjustMoney;
	//
	private BigDecimal afterAdjustMoney;
	//调整类型（0用户充值  1用户消费  2分销商结算）
	private Integer adjustType;
	//流水创建时间
	private Date createTime;
	//订单id
	private String orderId;

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
	 * 设置：账户id
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * 获取：账户id
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * 设置：
	 */
	public void setBeforeAdjustMoney(BigDecimal beforeAdjustMoney) {
		this.beforeAdjustMoney = beforeAdjustMoney;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getBeforeAdjustMoney() {
		return beforeAdjustMoney;
	}
	/**
	 * 设置：
	 */
	public void setAdjustMoney(BigDecimal adjustMoney) {
		this.adjustMoney = adjustMoney;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getAdjustMoney() {
		return adjustMoney;
	}
	/**
	 * 设置：
	 */
	public void setAfterAdjustMoney(BigDecimal afterAdjustMoney) {
		this.afterAdjustMoney = afterAdjustMoney;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getAfterAdjustMoney() {
		return afterAdjustMoney;
	}
	/**
	 * 设置：调整类型（0用户充值  1用户消费  2分销商结算）
	 */
	public void setAdjustType(Integer adjustType) {
		this.adjustType = adjustType;
	}
	/**
	 * 获取：调整类型（0用户充值  1用户消费  2分销商结算）
	 */
	public Integer getAdjustType() {
		return adjustType;
	}
	/**
	 * 设置：流水创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：流水创建时间
	 */
	public Date getCreateTime() {
		return createTime;
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
}
