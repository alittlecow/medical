package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.entity.RefundOrderEntity;
import com.jubo.modules.sys.service.RefundOrderService;
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
@RequestMapping("refundorder")
public class RefundOrderController {
	@Autowired
	private RefundOrderService refundOrderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("refundorder:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RefundOrderEntity> refundOrderList = refundOrderService.queryList(query);
		int total = refundOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(refundOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("refundorder:info")
	public R info(@PathVariable("id") String id){
		RefundOrderEntity refundOrder = refundOrderService.queryObject(id);
		
		return R.ok().put("refundOrder", refundOrder);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("refundorder:save")
	public R save(@RequestBody RefundOrderEntity refundOrder){
		refundOrderService.save(refundOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("refundorder:update")
	public R update(@RequestBody RefundOrderEntity refundOrder){
		refundOrderService.update(refundOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("refundorder:delete")
	public R delete(@RequestBody String[] ids){
		refundOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
