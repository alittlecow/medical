package com.jubo.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-25 12:54:27
 */
public class CardHistoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private String id;
    //持卡人id
    private Long userId;
    //卡号
    private String code;
    //剩余次数
    private Integer count;
    //1设备使用 2充值（充值有充值类型字段）
    private Integer type;
    //0支付宝 1微信 2银联 3个人账户
    private String payType;
    //创建记录时间
    private Date createTime;

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
     * 设置：卡号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取：卡号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置：剩余次数
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取：剩余次数
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置：1设备使用 2充值（充值有充值类型字段）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：1设备使用 2充值（充值有充值类型字段）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：0支付宝 1微信 2银联 3个人账户
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取：0支付宝 1微信 2银联 3个人账户
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置：创建记录时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建记录时间
     */
    public Date getCreateTime() {
        return createTime;
    }
}
