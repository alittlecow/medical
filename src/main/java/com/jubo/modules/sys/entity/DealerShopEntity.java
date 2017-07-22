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
public class DealerShopEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String id;
	//门店所属经销商（经销商角色为0）
	private String parentId;
	//用户id
	private String userId;
	//分成比例
	private BigDecimal rate;
	//门店（经销商）名称
	private String name;
	//是否允许提现（0不允许 1允许）
	private Integer isWithdraw;
	//联系地址
	private String address;

	/**
	 * 设置：id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：门店所属经销商（经销商角色为0）
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：门店所属经销商（经销商角色为0）
	 */
	public String getParentId() {
		return parentId;
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
	 * 设置：分成比例
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	/**
	 * 获取：分成比例
	 */
	public BigDecimal getRate() {
		return rate;
	}
	/**
	 * 设置：门店（经销商）名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：门店（经销商）名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：是否允许提现（0不允许 1允许）
	 */
	public void setIsWithdraw(Integer isWithdraw) {
		this.isWithdraw = isWithdraw;
	}
	/**
	 * 获取：是否允许提现（0不允许 1允许）
	 */
	public Integer getIsWithdraw() {
		return isWithdraw;
	}
	/**
	 * 设置：联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：联系地址
	 */
	public String getAddress() {
		return address;
	}
}
