package com.jubo.modules.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private String id;
    //用户id
    private Long userId;
    //订单创建时间
    private Date createTime;
    //订单支付完成时间
    private Date payTime;
    //支付类型（0支付宝 1微信 2银联 3个人账户）
    private Byte payType;
    //一次交易的商品id
    private Long goodsId;
    //订单金额
    private BigDecimal orderMoney;
    //订单状态（0待支付 1支付中 20 支付成功 21支付失败）
    private Integer payStatus;

    //订单类型  10 扫码使用 11 ID卡刷卡使用
    private Byte orderType;

    private String deviceId;

    private String cardCode;

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public Byte getOrderType() {
        return orderType;
    }

    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
