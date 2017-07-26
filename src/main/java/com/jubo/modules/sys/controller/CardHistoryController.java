package com.jubo.modules.sys.controller;

import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;
import com.jubo.modules.sys.entity.CardHistoryEntity;
import com.jubo.modules.sys.service.CardHistoryService;
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
 * @date 2017-07-25 12:54:27
 */
@RestController
@RequestMapping("cardhistory")
public class CardHistoryController {
	@Autowired
	private CardHistoryService cardHistoryService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("cardhistory:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CardHistoryEntity> cardHistoryList = cardHistoryService.queryList(query);
		int total = cardHistoryService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(cardHistoryList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("cardhistory:info")
	public R info(@PathVariable("id") String id){
		CardHistoryEntity cardHistory = cardHistoryService.queryObject(id);
		
		return R.ok().put("cardHistory", cardHistory);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("cardhistory:save")
	public R save(@RequestBody CardHistoryEntity cardHistory){
		cardHistoryService.save(cardHistory);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("cardhistory:update")
	public R update(@RequestBody CardHistoryEntity cardHistory){
		cardHistoryService.update(cardHistory);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("cardhistory:delete")
	public R delete(@RequestBody String[] ids){
		cardHistoryService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
