package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:43:39
 */
public interface SysUserService {

    int queryDeptDealerRole(Long deptId);

    /**
     * 注册用户
     *
     * @param mobile
     */
    void register(String mobile, String password);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByMobile(String mobile);


    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);

    /**
     * 根据用户ID，查询用户
     *
     * @param userId
     * @return
     */
    SysUserEntity queryObject(Long userId);

    /**
     * 查询用户列表
     */
    List<SysUserEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存用户
     */
    Long save(SysUserEntity user);


    /**
     * app用户修改个人信息
     */
    void updateAppUser(Map map);

    /**
     * 修改用户
     */
    void update(SysUserEntity user);

    /**
     * 删除用户
     */
    void deleteBatch(Long[] userIds);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     */
    int updatePassword(Long userId, String password, String newPassword);


    /**
     * 找回密码
     * @param userId
     * @param password
     * @return
     */
    int getBackPassword(Long userId, String password);


    void auth(Map<String,String> params, SysUserEntity userEntity);

}
