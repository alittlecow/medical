package com.jubo.modules.sys.entity;

import com.jubo.common.validator.group.AddGroup;
import com.jubo.common.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:50
 */
public class ApplyCardEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    @NotBlank(message = "id不能为空", groups = {UpdateGroup.class})
    private String id;
    //联系人姓名
    @NotBlank(message = "联系人姓名不能为空", groups = {AddGroup.class})
    private String contactName;
    //联系人电话
    @NotBlank(message = "联系人电话不能为空", groups = {AddGroup.class})
    private String contactPhone;
    //联系人地址
    @NotBlank(message = "联系人地址不能为空", groups = {AddGroup.class,})
    private String contactAddress;
    //身份证号码
    @NotBlank(message = "身份证号码不能为空", groups = {AddGroup.class})
    private String idCard;
    //申请时间
    private Date applyTime;
    //状态更新时间
    private Date updateTime;
    //申请状态 0 提交申请 1审批失败 2 审批成功
    private Integer status;

    private Long applyUserId;

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
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

    /**
     * 设置：联系人姓名
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * 获取：联系人姓名
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * 设置：联系人电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * 获取：联系人电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 设置：联系人地址
     */
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    /**
     * 获取：联系人地址
     */
    public String getContactAddress() {
        return contactAddress;
    }

    /**
     * 设置：身份证号码
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取：身份证号码
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置：申请时间
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 获取：申请时间
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * 设置：状态更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：状态更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：申请状态 0 提交申请 1审批失败 2 审批成功
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：申请状态 0 提交申请 1审批失败 2 审批成功
     */
    public Integer getStatus() {
        return status;
    }
}
