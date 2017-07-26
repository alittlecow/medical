package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;
import com.jubo.modules.sys.entity.PushMessageEntity;
import com.jubo.modules.sys.service.PushMessageService;
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
@RequestMapping("/api/app/pushmessage")
public class AppPushMessageController {
	@Autowired
	private PushMessageService pushMessageService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
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
	@RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
	public R info(@PathVariable("id") String id){
		PushMessageEntity pushMessage = pushMessageService.queryObject(id);
		
		return R.ok().put("pushMessage", pushMessage);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody PushMessageEntity pushMessage){
		pushMessageService.save(pushMessage);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody PushMessageEntity pushMessage){
		pushMessageService.update(pushMessage);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		pushMessageService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
