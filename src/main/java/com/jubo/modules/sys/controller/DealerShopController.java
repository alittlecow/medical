package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.entity.DealerShopEntity;
import com.jubo.modules.sys.service.DealerShopService;
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
@RequestMapping("dealershop")
public class DealerShopController {
	@Autowired
	private DealerShopService dealerShopService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("dealershop:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DealerShopEntity> dealerShopList = dealerShopService.queryList(query);
		int total = dealerShopService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(dealerShopList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("dealershop:info")
	public R info(@PathVariable("id") String id){
		DealerShopEntity dealerShop = dealerShopService.queryObject(id);
		
		return R.ok().put("dealerShop", dealerShop);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("dealershop:save")
	public R save(@RequestBody DealerShopEntity dealerShop){
		dealerShopService.save(dealerShop);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("dealershop:update")
	public R update(@RequestBody DealerShopEntity dealerShop){
		dealerShopService.update(dealerShop);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("dealershop:delete")
	public R delete(@RequestBody String[] ids){
		dealerShopService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
