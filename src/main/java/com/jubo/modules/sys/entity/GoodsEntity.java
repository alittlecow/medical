package com.jubo.modules.sys.entity;

import com.jubo.common.validator.group.AddGroup;
import com.jubo.common.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Date;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public class GoodsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "商品名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    //设备使用次数（单位：次）2账户充值（单位：元）
    @NotNull(message = "商品类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Byte type;
    //商品值
    @NotNull(message = "商品数值不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer value;
    //商品价格
    @NotNull(message = "商品价格不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal money;
    //
    private Date createTime;
    //状态 1生效 0失效
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 设置：商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：1设备使用次数（单位：次）2账户充值（单位：元）
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取：1设备使用次数（单位：次）2账户充值（单位：元）
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置：商品值
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * 获取：商品值
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 设置：商品价格
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取：商品价格
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：状态 1生效 0失效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态 1生效 0失效
     */
    public Integer getStatus() {
        return status;
    }
}
