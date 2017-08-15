package com.jubo.modules.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author pengxiao
 * @date 2017-08-15 19:20:47
 */
public class SettlementRuleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//钜柏分成
	private BigDecimal admin;
	//商户分成
	private BigDecimal merchant;
	//备用金
	private BigDecimal prettyCash;
	//省分销商
	private BigDecimal provinceDealer;
	//市分销商
	private BigDecimal cityDealer;


	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setAdmin(BigDecimal admin) {
		this.admin = admin;
	}

	public BigDecimal getAdmin() {
		return admin;
	}

	public void setMerchant(BigDecimal merchant) {
		this.merchant = merchant;
	}

	public BigDecimal getMerchant() {
		return merchant;
	}

	public void setPrettyCash(BigDecimal prettyCash) {
		this.prettyCash = prettyCash;
	}

	public BigDecimal getPrettyCash() {
		return prettyCash;
	}

	public void setProvinceDealer(BigDecimal provinceDealer) {
		this.provinceDealer = provinceDealer;
	}

	public BigDecimal getProvinceDealer() {
		return provinceDealer;
	}

	public void setCityDealer(BigDecimal cityDealer) {
		this.cityDealer = cityDealer;
	}

	public BigDecimal getCityDealer() {
		return cityDealer;
	}
}
