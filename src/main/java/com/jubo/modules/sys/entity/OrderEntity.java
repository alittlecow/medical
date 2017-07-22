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
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//用户id
	private String userId;
	//订单创建时间
	private Date createTime;
	//订单支付完成时间
	private Date payTime;
	//支付类型（0支付宝 1微信 2银联 3个人账户）
	private Integer payType;
	//一次交易的商品id
	private String goodsId;
	//订单金额
	private BigDecimal orderMoney;
	//订单状态（0待支付 1支付中 20 支付成功 21支付失败）
	private Integer payStatus;

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
	 * 设置：订单创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：订单创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：订单支付完成时间
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	/**
	 * 获取：订单支付完成时间
	 */
	public Date getPayTime() {
		return payTime;
	}
	/**
	 * 设置：支付类型（0支付宝 1微信 2银联 3个人账户）
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	/**
	 * 获取：支付类型（0支付宝 1微信 2银联 3个人账户）
	 */
	public Integer getPayType() {
		return payType;
	}
	/**
	 * 设置：一次交易的商品id
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：一次交易的商品id
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：订单金额
	 */
	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}
	/**
	 * 获取：订单金额
	 */
	public BigDecimal getOrderMoney() {
		return orderMoney;
	}
	/**
	 * 设置：订单状态（0待支付 1支付中 20 支付成功 21支付失败）
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * 获取：订单状态（0待支付 1支付中 20 支付成功 21支付失败）
	 */
	public Integer getPayStatus() {
		return payStatus;
	}
}
