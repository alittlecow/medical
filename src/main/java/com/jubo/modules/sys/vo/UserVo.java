package com.jubo.modules.sys.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jubo.common.validator.group.AddGroup;
import com.jubo.common.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
import java.util.List;

/**
 * @author pengxiao
 * @date 2017/8/14
 */
public class UserVo {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;



    private String email;


    private Date birthday;
    private Integer sex;

    private String mobile;


    private Long deptId;
    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色ID
     */
    private String roleId;


    private String realName;

    private String idCard;

    private Byte isAuth;
}
