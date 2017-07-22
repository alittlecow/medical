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
public class AccountEnchashmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//提现表主键
	private String id;
	//accout_info表中用户id对应
	private String userId;
	//支付时间
	private Date payAt;
	//int类型金额以分为单位的金额
	private Integer totalFee;
	//银行全名
	private String bankFullName;
	//账户类型 区分对公和对私 P代表私户，C代表公户
	private String accountType;
	//收款方的银行卡号
	private String accountNo;
	//收款方的姓名或者单位名
	private String accountName;
	//0提现中，1提现成功
	private Integer status;
	//创建时间
	private Date createAt;
	//卡类型 DE代表借记卡，CR代表信用卡，其他值为非法
	private String cardType;

	/**
	 * 设置：提现表主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：提现表主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：accout_info表中用户id对应
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：accout_info表中用户id对应
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：支付时间
	 */
	public void setPayAt(Date payAt) {
		this.payAt = payAt;
	}
	/**
	 * 获取：支付时间
	 */
	public Date getPayAt() {
		return payAt;
	}
	/**
	 * 设置：int类型金额以分为单位的金额
	 */
	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * 获取：int类型金额以分为单位的金额
	 */
	public Integer getTotalFee() {
		return totalFee;
	}
	/**
	 * 设置：银行全名
	 */
	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}
	/**
	 * 获取：银行全名
	 */
	public String getBankFullName() {
		return bankFullName;
	}
	/**
	 * 设置：账户类型 区分对公和对私 P代表私户，C代表公户
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/**
	 * 获取：账户类型 区分对公和对私 P代表私户，C代表公户
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * 设置：收款方的银行卡号
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * 获取：收款方的银行卡号
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * 设置：收款方的姓名或者单位名
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * 获取：收款方的姓名或者单位名
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * 设置：0提现中，1提现成功
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0提现中，1提现成功
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateAt() {
		return createAt;
	}
	/**
	 * 设置：卡类型 DE代表借记卡，CR代表信用卡，其他值为非法
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	/**
	 * 获取：卡类型 DE代表借记卡，CR代表信用卡，其他值为非法
	 */
	public String getCardType() {
		return cardType;
	}
}
