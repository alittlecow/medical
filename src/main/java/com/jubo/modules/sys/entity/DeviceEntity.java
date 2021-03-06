package com.jubo.modules.sys.entity;

import com.jubo.common.exception.RRException;
import com.jubo.common.utils.Constant;
import com.jubo.common.utils.R;
import com.jubo.common.validator.Assert;
import com.jubo.common.validator.group.AddGroup;
import com.jubo.common.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author pengxiao
 * @date 2017-07-21 22:46:51
 */
public class DeviceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private String id;
    //设备编码

    @NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String code;
    @NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String sim;
    //设备使用状态（0未使用 1使用中）
    private Byte useStatus;
    //是否故障（0正常 1故障）
    private Byte isBreakdown;
    //商户id
    private Long merchantId;
    //商户名称
    private String merchantName;

    //0 未绑定 1已经绑定
    private Byte bindStatus;

    private Date operateTime;



    //是否可以使用
    public boolean checkValid() {

        if (Constant.DeviceBindType.IS_BIND.getValue().compareTo(bindStatus) != 0) {
            throw new RRException("设备还未绑定,不能使用");
        }

        if (Constant.DeviceBreakdownStatus.BREAKDOWN.getValue().compareTo(isBreakdown) == 0) {
            throw new RRException("设备故障");

        }
        if (Constant.DeviceUseStatus.USE_ING.getValue().compareTo(useStatus) == 0) {
            throw new RRException("设备正在使用中");

        }
        return true;
    }


    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Byte useStatus) {
        this.useStatus = useStatus;
    }

    public Byte getIsBreakdown() {
        return isBreakdown;
    }

    public void setIsBreakdown(Byte isBreakdown) {
        this.isBreakdown = isBreakdown;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Byte getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Byte bindStatus) {
        this.bindStatus = bindStatus;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }


}
