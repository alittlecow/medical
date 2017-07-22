package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.entity.ChargePolicyEntity;
import com.jubo.modules.sys.service.ChargePolicyService;
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
@RequestMapping("chargepolicy")
public class ChargePolicyController {
	@Autowired
	private ChargePolicyService chargePolicyService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("chargepolicy:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ChargePolicyEntity> chargePolicyList = chargePolicyService.queryList(query);
		int total = chargePolicyService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(chargePolicyList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("chargepolicy:info")
	public R info(@PathVariable("id") String id){
		ChargePolicyEntity chargePolicy = chargePolicyService.queryObject(id);
		
		return R.ok().put("chargePolicy", chargePolicy);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("chargepolicy:save")
	public R save(@RequestBody ChargePolicyEntity chargePolicy){
		chargePolicyService.save(chargePolicy);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("chargepolicy:update")
	public R update(@RequestBody ChargePolicyEntity chargePolicy){
		chargePolicyService.update(chargePolicy);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("chargepolicy:delete")
	public R delete(@RequestBody String[] ids){
		chargePolicyService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
