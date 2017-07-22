package io.renren.modules.api.controller;

import java.util.List;
import java.util.Map;

import io.renren.modules.api.entity.CardEntity;
import io.renren.modules.api.service.CardService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;




/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 21:42:07
 */
@RestController
@RequestMapping("card")
public class CardController {
	@Autowired
	private CardService cardService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("card:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CardEntity> cardList = cardService.queryList(query);
		int total = cardService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(cardList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("card:info")
	public R info(@PathVariable("id") String id){
		CardEntity card = cardService.queryObject(id);
		
		return R.ok().put("card", card);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("card:save")
	public R save(@RequestBody CardEntity card){
		cardService.save(card);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("card:update")
	public R update(@RequestBody CardEntity card){
		cardService.update(card);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("card:delete")
	public R delete(@RequestBody String[] ids){
		cardService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
