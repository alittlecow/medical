package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.entity.AccountInfoEntity;
import com.jubo.modules.sys.service.AccountInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;




/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
@RestController
@RequestMapping("accountinfo")
public class AccountInfoController {
	@Autowired
	private AccountInfoService accountInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("accountinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<AccountInfoEntity> accountInfoList = accountInfoService.queryList(query);
		int total = accountInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(accountInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("accountinfo:info")
	public R info(@PathVariable("id") String id){
		AccountInfoEntity accountInfo = accountInfoService.queryObject(id);
		
		return R.ok().put("accountInfo", accountInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("accountinfo:save")
	public R save(@RequestBody AccountInfoEntity accountInfo){
		accountInfoService.save(accountInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("accountinfo:update")
	public R update(@RequestBody AccountInfoEntity accountInfo){
		accountInfoService.update(accountInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("accountinfo:delete")
	public R delete(@RequestBody String[] ids){
		accountInfoService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
