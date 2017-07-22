package com.jubo.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:50
 */
public class UserBankCardEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String id;
	//状态
	private Integer status;
	//用户id
	private String userId;
	//银行全名
	private String bankFullName;
	//卡类型 DE代表借记卡，CR代表信用卡，其他值为非法
	private String cardType;
	//账户类型 区分对公和对私 P代表私户，C代表公户，其他值为非法
	private String accountType;
	//收款方的银行卡号
	private String accountNo;
	//收款方的姓名或者单位名
	private String accountName;

	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
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
	/**
	 * 设置：账户类型 区分对公和对私 P代表私户，C代表公户，其他值为非法
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/**
	 * 获取：账户类型 区分对公和对私 P代表私户，C代表公户，其他值为非法
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
}
