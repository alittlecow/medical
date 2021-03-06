package com.jubo.modules.sys.controller;

import com.jubo.common.annotation.SysLog;
import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;
import com.jubo.modules.api.annotation.LoginUser;
import com.jubo.modules.sys.entity.AccountEnchashmentEntity;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.service.AccountEnchashmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;




/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
@RestController
@RequestMapping("accountenchashment")
public class AccountEnchashmentController {
	@Autowired
	private AccountEnchashmentService accountEnchashmentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("accountenchashment:list")
	public R list(@RequestParam Map<String, Object> params,@LoginUser SysUserEntity user){
		//根据登录信息自动注入用户id
		params.put("userId",user.getUserId());
		//查询列表数据
        Query query = new Query(params);

		List<AccountEnchashmentEntity> accountEnchashmentList = accountEnchashmentService.queryList(query);
		int total = accountEnchashmentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(accountEnchashmentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("accountenchashment:info")
	public R info(@PathVariable("id") String id){
		AccountEnchashmentEntity accountEnchashment = accountEnchashmentService.queryObject(id);
		
		return R.ok().put("accountEnchashment", accountEnchashment);
	}
	
	/**
	 *
	 */
	@SysLog
	@RequestMapping("/save")
	public R save(@RequestBody AccountEnchashmentEntity accountEnchashment){
		accountEnchashmentService.save(accountEnchashment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 * 提现订单不允许修改
	 */
/*	@RequestMapping("/update")
	@RequiresPermissions("accountenchashment:update")
	public R update(@RequestBody AccountEnchashmentEntity accountEnchashment){
		accountEnchashmentService.update(accountEnchashment);
		
		return R.ok();
	}*/
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("accountenchashment:delete")
	public R delete(@RequestBody String[] ids){
		accountEnchashmentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
