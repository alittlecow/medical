package com.jubo.modules.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:50
 */
public class PayRuleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//收费规则（元/分钟）
	private BigDecimal fee;

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
	 * 设置：收费规则（元/分钟）
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	/**
	 * 获取：收费规则（元/分钟）
	 */
	public BigDecimal getFee() {
		return fee;
	}
}
