package com.jubo.modules.sys.controller;

import com.jubo.common.utils.Constant;
import com.jubo.common.utils.R;
import com.jubo.modules.sys.vo.DeptVo;
import com.jubo.modules.sys.entity.SysDeptEntity;
import com.jubo.modules.sys.service.SysDeptService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 分销商管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-06-20 15:23:47
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends AbstractController {

    @Autowired
    private SysDeptService sysDeptService;


    public static final Map<Integer, String> DEPT_LEV_NAME = new HashMap() {{
        put(-1, "超级管理员");
        put(0, "省分销商");
        put(1, "市分销商");
        put(2, "商户");
    }};
    //部门等级对应的角色
    public static final Map<Integer, Long> DEPT_LEV_ROLE = new HashMap() {{
        put(-1, 0L);
        put(0, 1L);
        put(1, 2L);
        put(2, 3L);
    }};


    /**
     * 列表
     */
    @RequestMapping("/getMerchantList")
    @RequiresPermissions("sys:dept:list")
    public R getMerchantList(@RequestBody Map<String, String> params) {

        Map<String, String> map = new HashMap<>();
        String merchantName = MapUtils.getString(params, "merchantName");
        if (StringUtils.isNotBlank(merchantName)) {
            map.put("name", "%" + merchantName + "%");
        }
        List<SysDeptEntity> deptList = sysDeptService.getMerchantList(map);

        return R.ok().putData(deptList);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dept:list")
    public List<SysDeptEntity> list() {
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只能查询本部门及子部门数据
//        if (getUserId() != Constant.SUPER_ADMIN) {
//            map.put("deptFilter", sysDeptService.getSubDeptIdList(getDeptId()));
//        }
        List<SysDeptEntity> deptList = sysDeptService.queryList(map);
        for (SysDeptEntity dept : deptList) {
            dept.setLevelName(DEPT_LEV_NAME.get(dept.getLevel()));
        }

        return deptList;
    }

    /**
     * 选择部门(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:dept:select")
    public R select() {
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只能查询本部门及子部门数据
//        if (getUserId() != Constant.SUPER_ADMIN) {
//            map.put("deptFilter", sysDeptService.getSubDeptIdList(getDeptId()));
//        }
        List<SysDeptEntity> deptList = sysDeptService.queryList(map);

        //添加一级部门
//        if (getUserId() == Constant.SUPER_ADMIN) {
        SysDeptEntity root = new SysDeptEntity();
        root.setDeptId(0L);
        root.setName("钜栢健康");
        root.setParentId(-1L);
        root.setOpen(true);
        deptList.add(root);
//        }

        return R.ok().put("deptList", deptList);
    }

    /**
     * 上级部门Id(管理员则为0)
     */
    @RequestMapping("/info")
    @RequiresPermissions("sys:dept:list")
    public R info() {
        long deptId = 0;
//        if (getUserId() != Constant.SUPER_ADMIN) {
//            SysDeptEntity dept = sysDeptService.queryObject(getDeptId());
//            deptId = dept.getParentId();
//        }

        return R.ok().put("deptId", deptId);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{deptId}")
    @RequiresPermissions("sys:dept:info")
    public R info(@PathVariable("deptId") Long deptId) {
        SysDeptEntity dept = sysDeptService.queryObject(deptId);

        return R.ok().put("dept", dept);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dept:save")
    public R save(@RequestBody DeptVo dept) throws InvocationTargetException, IllegalAccessException {

        return sysDeptService.save(dept);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dept:update")
    public R update(@RequestBody SysDeptEntity dept) {
        sysDeptService.update(dept);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dept:delete")
    public R delete(long deptId) {
        //判断是否有子部门
        List<Long> deptList = sysDeptService.queryDetpIdList(deptId);
        if (deptList.size() > 0) {
            return R.error("请先删除子部门");
        }

        sysDeptService.delete(deptId);

        return R.ok();
    }

}
