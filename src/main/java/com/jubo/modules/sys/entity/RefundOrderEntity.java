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
public class RefundOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//退款申请时间
	private Date applyTime;
	//退款申请备注
	private String applyMemo;
	//退款申请原因
	private String applyReason;
	//退款单状态
	private String status;
	//退款完成时间
	private Date refundTime;
	//拒绝退款原因
	private String refuseReason;
	//拒绝退款备注
	private String refuseMemo;
	//订单是否取消（0未取消 1取消）
	private Integer isCancel;

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
	 * 设置：退款申请时间
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	/**
	 * 获取：退款申请时间
	 */
	public Date getApplyTime() {
		return applyTime;
	}
	/**
	 * 设置：退款申请备注
	 */
	public void setApplyMemo(String applyMemo) {
		this.applyMemo = applyMemo;
	}
	/**
	 * 获取：退款申请备注
	 */
	public String getApplyMemo() {
		return applyMemo;
	}
	/**
	 * 设置：退款申请原因
	 */
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	/**
	 * 获取：退款申请原因
	 */
	public String getApplyReason() {
		return applyReason;
	}
	/**
	 * 设置：退款单状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：退款单状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：退款完成时间
	 */
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	/**
	 * 获取：退款完成时间
	 */
	public Date getRefundTime() {
		return refundTime;
	}
	/**
	 * 设置：拒绝退款原因
	 */
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	/**
	 * 获取：拒绝退款原因
	 */
	public String getRefuseReason() {
		return refuseReason;
	}
	/**
	 * 设置：拒绝退款备注
	 */
	public void setRefuseMemo(String refuseMemo) {
		this.refuseMemo = refuseMemo;
	}
	/**
	 * 获取：拒绝退款备注
	 */
	public String getRefuseMemo() {
		return refuseMemo;
	}
	/**
	 * 设置：订单是否取消（0未取消 1取消）
	 */
	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}
	/**
	 * 获取：订单是否取消（0未取消 1取消）
	 */
	public Integer getIsCancel() {
		return isCancel;
	}
}
