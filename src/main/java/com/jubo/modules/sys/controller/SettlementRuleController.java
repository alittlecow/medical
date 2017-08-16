package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jubo.modules.sys.entity.SettlementRuleEntity;
import com.jubo.modules.sys.service.SettlementRuleService;
import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;




/**
 * 
 * 
 * @author pengxiao
 * @date 2017-08-15 19:20:47
 */
@RestController
@RequestMapping("settlementrule")
public class SettlementRuleController {
	@Autowired
	private SettlementRuleService settlementRuleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("settlementrule:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SettlementRuleEntity> settlementRuleList = settlementRuleService.queryList(query);
		int total = settlementRuleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(settlementRuleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("settlementrule:info")
	public R info(@PathVariable("id") String id){
		SettlementRuleEntity settlementRule = settlementRuleService.queryObject(id);
		
		return R.ok().put("settlementRule", settlementRule);
	}
	
	/**
	 * 保存
	 */
//	@RequestMapping("/save")
//	@RequiresPermissions("settlementrule:save")
	public R save(@RequestBody SettlementRuleEntity settlementRule){
		settlementRuleService.save(settlementRule);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("settlementrule:update")
	public R update(@RequestBody SettlementRuleEntity settlementRule){
		settlementRuleService.update(settlementRule);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
//	@RequestMapping("/delete")
//	@RequiresPermissions("settlementrule:delete")
	public R delete(@RequestBody String[] ids){
		settlementRuleService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
