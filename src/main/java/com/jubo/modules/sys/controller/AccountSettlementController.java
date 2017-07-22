package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.entity.AccountSettlementEntity;
import com.jubo.modules.sys.service.AccountSettlementService;
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
@RequestMapping("accountsettlement")
public class AccountSettlementController {
	@Autowired
	private AccountSettlementService accountSettlementService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("accountsettlement:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<AccountSettlementEntity> accountSettlementList = accountSettlementService.queryList(query);
		int total = accountSettlementService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(accountSettlementList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("accountsettlement:info")
	public R info(@PathVariable("id") String id){
		AccountSettlementEntity accountSettlement = accountSettlementService.queryObject(id);
		
		return R.ok().put("accountSettlement", accountSettlement);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("accountsettlement:save")
	public R save(@RequestBody AccountSettlementEntity accountSettlement){
		accountSettlementService.save(accountSettlement);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("accountsettlement:update")
	public R update(@RequestBody AccountSettlementEntity accountSettlement){
		accountSettlementService.update(accountSettlement);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("accountsettlement:delete")
	public R delete(@RequestBody String[] ids){
		accountSettlementService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
