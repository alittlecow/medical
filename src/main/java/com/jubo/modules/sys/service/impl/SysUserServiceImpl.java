package com.jubo.modules.sys.service.impl;

import com.jubo.common.exception.RRException;
import com.jubo.common.utils.UUIDUtil;
import com.jubo.modules.sys.dao.SysUserDao;
import com.jubo.modules.sys.entity.AccountInfoEntity;
import com.jubo.modules.sys.entity.SysRoleEntity;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.entity.SysUserRoleEntity;
import com.jubo.modules.sys.service.AccountInfoService;
import com.jubo.modules.sys.service.SysRoleService;
import com.jubo.modules.sys.service.SysUserRoleService;
import com.jubo.modules.sys.service.SysUserService;
import com.jubo.common.utils.Constant;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


/**
 * 系统用户
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private AccountInfoService AccountInfoService;

    @Override
    public int queryDeptDealerRole(Long deptId) {

        return sysUserDao.queryDeptDealerRole(deptId);
    }

    @Override
    @Transactional
    public void register(String mobile, String password) {
        SysUserEntity user = new SysUserEntity();
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(password, salt).toHex());
        user.setSalt(salt);
        Date nowTime = new Date();
        user.setCreateTime(nowTime);
        user.setMobile(mobile);
        user.setUsername(mobile);
        user.setStatus(Constant.ENABLE);
        sysUserDao.save(user);

        //用户角色
        sysUserRoleService.saveOrUpdate(user.getUserId(),
                Arrays.asList(new Long[]{Constant.Role.ROLE_APP_USER.getValue()}));
        //新建账户
        AccountInfoEntity account = new AccountInfoEntity();
        account.setBalance(new BigDecimal("0"));
        account.setCreateTime(nowTime);
        account.setUpdateTime(nowTime);
        account.setUserId(user.getUserId());
        account.setId(UUIDUtil.getUUId());
        AccountInfoService.save(account);
    }

    @Override
    public SysUserEntity queryByMobile(String mobile) {
        return sysUserDao.queryByMobile(mobile);
    }

    @Override
    public List<String> queryAllPerms(Long userId) {
        return sysUserDao.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserDao.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String username) {
        return sysUserDao.queryByUserName(username);
    }

    @Override
    public SysUserEntity queryObject(Long userId) {
        return sysUserDao.queryObject(userId);
    }

    @Override
    public List<SysUserEntity> queryList(Map<String, Object> map) {
        return sysUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysUserDao.queryTotal(map);
    }

    @Override
    @Transactional
    public Long save(SysUserEntity user) {
        Date nowTime = new Date();
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setSalt(salt);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setCreateTime(nowTime);
        //保存用户
        sysUserDao.save(user);
        //新建账户
        AccountInfoEntity account = new AccountInfoEntity();
        account.setBalance(new BigDecimal("0"));
        account.setCreateTime(nowTime);
        account.setUpdateTime(nowTime);
        account.setUserId(user.getUserId());
        account.setId(UUIDUtil.getUUId());
        AccountInfoService.save(account);

        return user.getUserId();

    }


    /**
     * app用户更新个人信息
     */
    public void updateAppUser(Map map) {
        sysUserDao.updateAppUser(map);
    }

    @Override
    @Transactional
    public void update(SysUserEntity user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        sysUserDao.update(user);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] userId) {
        sysUserDao.deleteBatch(userId);
    }

    @Override
    public int updatePassword(Long userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sysUserDao.updatePassword(map);
    }

    @Override
    public int getBackPassword(Long userId, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        return sysUserDao.updatePasswordByUserId(map);
    }

}
