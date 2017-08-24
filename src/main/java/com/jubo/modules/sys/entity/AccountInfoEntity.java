package com.jubo.modules.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public class AccountInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private String id;
    //用户id
    private Long userId;
    //账户余额
    private BigDecimal balance;

    //已缴纳押金
    private BigDecimal deposit;

    //允许提现标准位 1允许0 不允许
    private String allowEncashment;


    //账户创建时间
    private Date createTime;
    //账户最后一次更新时间
    private Date updateTime;


    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getAllowEncashment() {
        return allowEncashment;
    }

    public void setAllowEncashment(String allowEncashment) {
        this.allowEncashment = allowEncashment;
    }


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


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 设置：账户余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 获取：账户余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置：账户创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：账户创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：账户最后一次更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：账户最后一次更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }
}
