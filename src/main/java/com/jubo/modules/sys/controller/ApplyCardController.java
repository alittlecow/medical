package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.entity.ApplyCardEntity;
import com.jubo.modules.sys.service.ApplyCardService;
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
@RequestMapping("applycard")
public class ApplyCardController {
	@Autowired
	private ApplyCardService applyCardService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("applycard:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ApplyCardEntity> applyCardList = applyCardService.queryList(query);
		int total = applyCardService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(applyCardList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("applycard:info")
	public R info(@PathVariable("id") String id){
		ApplyCardEntity applyCard = applyCardService.queryObject(id);
		
		return R.ok().put("applyCard", applyCard);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("applycard:save")
	public R save(@RequestBody ApplyCardEntity applyCard){
		applyCardService.save(applyCard);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("applycard:update")
	public R update(@RequestBody ApplyCardEntity applyCard){
		applyCardService.update(applyCard);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("applycard:delete")
	public R delete(@RequestBody String[] ids){
		applyCardService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
