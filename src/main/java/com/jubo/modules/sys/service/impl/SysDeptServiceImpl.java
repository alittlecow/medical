package com.jubo.modules.sys.service.impl;

import com.jubo.common.utils.ErrorMessage;
import com.jubo.common.utils.R;
import com.jubo.common.validator.Assert;
import com.jubo.modules.sys.dao.SysDeptDao;
import com.jubo.modules.sys.vo.DeptVo;
import com.jubo.modules.sys.entity.SysDeptEntity;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.service.SysDeptService;
import com.jubo.modules.sys.service.SysUserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.jubo.common.utils.ErrorMessage.EMAIL_FORMAT_ERROR;
import static com.jubo.common.utils.ErrorMessage.PASSWORD_FORMAT_ERROR;
import static com.jubo.common.utils.ErrorMessage.PHONE_FORMAT_ERROR;
import static com.jubo.modules.sys.controller.SysDeptController.DEPT_LEV_ROLE;


@Transactional
@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptDao sysDeptDao;

    @Autowired
    private SysUserService sysUserService;


    @Override
    public SysDeptEntity queryObject(Long deptId) {
        return sysDeptDao.queryObject(deptId);
    }

    @Override
    public List<SysDeptEntity> getMerchantList(Map<String, String> map) {
        return sysDeptDao.getMerchantList(map);
    }

    @Override
    public List<SysDeptEntity> queryList(Map<String, Object> map) {
        return sysDeptDao.queryList(map);
    }

    @Override
    public R save(DeptVo deptVo) throws InvocationTargetException, IllegalAccessException {

        //一级部门
        int parentLevel = -1;

        Long parenDeptId = deptVo.getParentId();
        if (parenDeptId != 0) {
            SysDeptEntity parenDept = sysDeptDao.queryObject(parenDeptId);
            parentLevel = parenDept.getLevel();
        }

        if (parentLevel >= 2) {
            return R.error("未开放该分销商等级!");
        }
        deptVo.setLevel(parentLevel + 1);

        Long roleId = DEPT_LEV_ROLE.get(parentLevel + 1);

        if (StringUtils.isBlank(deptVo.getUsername())) {
            return R.error("用户名不能为空");
        }

        //新建用户
        SysUserEntity user = new SysUserEntity();

        SysUserEntity existUser = sysUserService.queryByUserName(deptVo.getUsername());
        if (existUser != null) {
            return R.error(ErrorMessage.USERNAME_IS_EXIST);
        }
        existUser = sysUserService.queryByMobile(deptVo.getMobile());
        if (existUser != null) {
            return R.error(ErrorMessage.PHONE_IS_EXIST);
        }

        Assert.isNotPhone(deptVo.getMobile(), PHONE_FORMAT_ERROR);
        Assert.isNotEmail(deptVo.getEmail(), EMAIL_FORMAT_ERROR);
        Assert.isNotValidPassword(deptVo.getPassword(), PASSWORD_FORMAT_ERROR);

        user.setPassword(deptVo.getPassword());
        user.setUsername(deptVo.getUsername());
        user.setMobile(deptVo.getMobile());
        user.setRoleId(roleId);
        user.setEmail(deptVo.getEmail());

        Long userId = sysUserService.save(user);


        //保存部门信息
        SysDeptEntity deptEntity = new SysDeptEntity();

        BeanUtils.copyProperties(deptEntity, deptVo);
        deptEntity.setLevel(parentLevel + 1);
        deptEntity.setUserId(userId);
        //用户和角色信息写入表中
        sysDeptDao.save(deptEntity);


        return R.ok();
    }

    @Override
    public void update(SysDeptEntity sysDept) {
        sysDeptDao.update(sysDept);
    }

    @Override
    public void delete(Long deptId) {
        sysDeptDao.delete(deptId);
    }

    @Override
    public List<Long> queryDetpIdList(Long parentId) {
        return sysDeptDao.queryDetpIdList(parentId);
    }

    @Override
    public String getSubDeptIdList(Long deptId) {
        //部门及子部门ID列表
        List<Long> deptIdList = new ArrayList<>();

        //获取子部门ID
        List<Long> subIdList = queryDetpIdList(deptId);
        getDeptTreeList(subIdList, deptIdList);

        //添加本部门
        deptIdList.add(deptId);

        String deptFilter = StringUtils.join(deptIdList, ",");
        return deptFilter;
    }

    @Override
    public List<Long> getAllMerchantByUserId(Long userId) {

        List<Long> merchantList = new ArrayList<>();

        SysDeptEntity deptEntity = sysDeptDao.queryObjectByUserId(userId);

        if (deptEntity == null) {
            return merchantList;
        }
        //获取子部门ID
        List<Long> subIdList = queryDetpIdList(deptEntity.getDeptId());
        if (CollectionUtils.isNotEmpty(subIdList)) {

            getSubMerchantList(subIdList, merchantList);
        } else {
            merchantList.add(deptEntity.getDeptId());
        }


        return merchantList;
    }


    /**
     * 递归
     */
    public void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList) {
        for (Long deptId : subIdList) {
            List<Long> list = queryDetpIdList(deptId);
            if (list.size() > 0) {
                getDeptTreeList(list, deptIdList);
            }

            deptIdList.add(deptId);
        }
    }


    /**
     * 递归
     */
    public void getSubMerchantList(List<Long> subIdList, List<Long> merchantList) {

        for (Long deptId : subIdList) {
            List<Long> list = queryDetpIdList(deptId);
            if (list.size() > 0) {
                getDeptTreeList(list, merchantList);
            } else {

                //目前商户为最后一层
                merchantList.add(deptId);
            }
        }
    }
}
