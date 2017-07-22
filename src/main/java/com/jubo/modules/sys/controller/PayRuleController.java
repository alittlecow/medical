package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.entity.PayRuleEntity;
import com.jubo.modules.sys.service.PayRuleService;
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
 * @date 2017-07-21 22:46:50
 */
@RestController
@RequestMapping("payrule")
public class PayRuleController {
	@Autowired
	private PayRuleService payRuleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("payrule:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PayRuleEntity> payRuleList = payRuleService.queryList(query);
		int total = payRuleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(payRuleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("payrule:info")
	public R info(@PathVariable("id") String id){
		PayRuleEntity payRule = payRuleService.queryObject(id);
		
		return R.ok().put("payRule", payRule);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("payrule:save")
	public R save(@RequestBody PayRuleEntity payRule){
		payRuleService.save(payRule);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("payrule:update")
	public R update(@RequestBody PayRuleEntity payRule){
		payRuleService.update(payRule);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("payrule:delete")
	public R delete(@RequestBody String[] ids){
		payRuleService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
