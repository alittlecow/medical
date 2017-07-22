package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.entity.PushMessageEntity;
import com.jubo.modules.sys.service.PushMessageService;
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
@RequestMapping("pushmessage")
public class PushMessageController {
	@Autowired
	private PushMessageService pushMessageService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pushmessage:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PushMessageEntity> pushMessageList = pushMessageService.queryList(query);
		int total = pushMessageService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(pushMessageList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("pushmessage:info")
	public R info(@PathVariable("id") String id){
		PushMessageEntity pushMessage = pushMessageService.queryObject(id);
		
		return R.ok().put("pushMessage", pushMessage);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("pushmessage:save")
	public R save(@RequestBody PushMessageEntity pushMessage){
		pushMessageService.save(pushMessage);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("pushmessage:update")
	public R update(@RequestBody PushMessageEntity pushMessage){
		pushMessageService.update(pushMessage);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("pushmessage:delete")
	public R delete(@RequestBody String[] ids){
		pushMessageService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
