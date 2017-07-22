package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.entity.OrderLogEntity;
import com.jubo.modules.sys.service.OrderLogService;
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
@RequestMapping("orderlog")
public class OrderLogController {
	@Autowired
	private OrderLogService orderLogService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("orderlog:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderLogEntity> orderLogList = orderLogService.queryList(query);
		int total = orderLogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(orderLogList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("orderlog:info")
	public R info(@PathVariable("id") String id){
		OrderLogEntity orderLog = orderLogService.queryObject(id);
		
		return R.ok().put("orderLog", orderLog);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("orderlog:save")
	public R save(@RequestBody OrderLogEntity orderLog){
		orderLogService.save(orderLog);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("orderlog:update")
	public R update(@RequestBody OrderLogEntity orderLog){
		orderLogService.update(orderLog);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("orderlog:delete")
	public R delete(@RequestBody String[] ids){
		orderLogService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
