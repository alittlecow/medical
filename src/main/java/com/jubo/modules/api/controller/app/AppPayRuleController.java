package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;
import com.jubo.modules.sys.entity.PayRuleEntity;
import com.jubo.modules.sys.service.PayRuleService;
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
 * @date 2017-07-21 22:46:50
 */
@RestController
@RequestMapping("/api/app/payrule")
public class AppPayRuleController {
	@Autowired
	private PayRuleService payRuleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
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
	public R info(@PathVariable("id") String id){
		PayRuleEntity payRule = payRuleService.queryObject(id);
		
		return R.ok().put("payRule", payRule);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody PayRuleEntity payRule){
		payRuleService.save(payRule);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody PayRuleEntity payRule){
		payRuleService.update(payRule);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		payRuleService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
