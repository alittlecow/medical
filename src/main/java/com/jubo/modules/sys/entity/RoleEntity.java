package com.jubo.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//角色名称(管理员，分销商，普通用户，财务人员)
	private String roleName;

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
	 * 设置：角色名称(管理员，分销商，普通用户，财务人员)
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 获取：角色名称(管理员，分销商，普通用户，财务人员)
	 */
	public String getRoleName() {
		return roleName;
	}
}
