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
public class ChargePolicyEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//充值金额
	private BigDecimal baseValue;
	//赠送金额
	private BigDecimal addValue;
	//是否生效（0失效 1生效）
	private Integer isEnable;

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
	 * 设置：充值金额
	 */
	public void setBaseValue(BigDecimal baseValue) {
		this.baseValue = baseValue;
	}
	/**
	 * 获取：充值金额
	 */
	public BigDecimal getBaseValue() {
		return baseValue;
	}
	/**
	 * 设置：赠送金额
	 */
	public void setAddValue(BigDecimal addValue) {
		this.addValue = addValue;
	}
	/**
	 * 获取：赠送金额
	 */
	public BigDecimal getAddValue() {
		return addValue;
	}
	/**
	 * 设置：是否生效（0失效 1生效）
	 */
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	/**
	 * 获取：是否生效（0失效 1生效）
	 */
	public Integer getIsEnable() {
		return isEnable;
	}
}
