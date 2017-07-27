package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;
import com.jubo.modules.sys.entity.RechargeOrderEntity;
import com.jubo.modules.sys.service.RechargeOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-26 23:23:30
 */
@RestController
@RequestMapping("rechargeorder")
public class RechargeOrderController {
	@Autowired
	private RechargeOrderService rechargeOrderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("rechargeorder:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RechargeOrderEntity> rechargeOrderList = rechargeOrderService.queryList(query);
		int total = rechargeOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(rechargeOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("rechargeorder:info")
	public R info(@PathVariable("id") String id){
		RechargeOrderEntity rechargeOrder = rechargeOrderService.queryObject(id);
		
		return R.ok().put("rechargeOrder", rechargeOrder);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("rechargeorder:save")
	public R save(@RequestBody RechargeOrderEntity rechargeOrder){
		rechargeOrderService.save(rechargeOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("rechargeorder:update")
	public R update(@RequestBody RechargeOrderEntity rechargeOrder){
		rechargeOrderService.update(rechargeOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("rechargeorder:delete")
	public R delete(@RequestBody String[] ids){
		rechargeOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
