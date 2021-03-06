package com.jubo.modules.sys.service;


import com.jubo.common.utils.R;
import com.jubo.modules.sys.vo.DeptVo;
import com.jubo.modules.sys.entity.SysDeptEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-06-20 15:23:47
 */
public interface SysDeptService {


	List<SysDeptEntity> getMerchantList(Map<String, String> map);


	SysDeptEntity queryObject(Long deptId);
	
	List<SysDeptEntity> queryList(Map<String, Object> map);

	R save(DeptVo sysDept) throws InvocationTargetException, IllegalAccessException;
	
	void update(SysDeptEntity sysDept);
	
	void delete(Long deptId);

	/**
	 * 查询子部门ID列表
	 * @param parentId  上级部门ID
	 */
	List<Long> queryDetpIdList(Long parentId);

	/**
	 * 获取子部门ID(包含本部门ID)，用于数据过滤
	 */
	String getSubDeptIdList(Long deptId);

	//根据用户id查询所有商户
	List<Long> getAllMerchantByUserId(Long userId);

}
