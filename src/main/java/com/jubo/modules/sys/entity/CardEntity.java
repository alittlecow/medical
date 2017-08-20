package com.jubo.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:50
 */
public class CardEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private String id;

    //是否激活
    private Byte isActive;

    private Long merchantId;
    //设备编码
    private String code;
    //剩余使用次数
    private Integer count;
    //创建时间
    private Date createTime;
    //上次使用时间
    private Date lastUseTime;
    //持卡人姓名
    private String cardHoldName;
    //持卡人电话
    private String cardHoldPhone;

    private Byte isBind;
    //绑定的用户id
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }


    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getCardHoldName() {
        return cardHoldName;
    }

    public void setCardHoldName(String cardHoldName) {
        this.cardHoldName = cardHoldName;
    }

    public String getCardHoldPhone() {
        return cardHoldPhone;
    }

    public void setCardHoldPhone(String cardHoldPhone) {
        this.cardHoldPhone = cardHoldPhone;
    }

    public Byte getIsBind() {
        return isBind;
    }

    public void setIsBind(Byte isBind) {
        this.isBind = isBind;
    }

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
     * 设置：设备编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取：设备编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置：剩余使用次数
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取：剩余使用次数
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：上次使用时间
     */
    public void setLastUseTime(Date lastUseTime) {
        this.lastUseTime = lastUseTime;
    }

    /**
     * 获取：上次使用时间
     */
    public Date getLastUseTime() {
        return lastUseTime;
    }
}
