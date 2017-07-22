package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.entity.DeviceBindEntity;
import com.jubo.modules.sys.service.DeviceBindService;
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
@RequestMapping("devicebind")
public class DeviceBindController {
	@Autowired
	private DeviceBindService deviceBindService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("devicebind:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DeviceBindEntity> deviceBindList = deviceBindService.queryList(query);
		int total = deviceBindService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(deviceBindList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("devicebind:info")
	public R info(@PathVariable("id") String id){
		DeviceBindEntity deviceBind = deviceBindService.queryObject(id);
		
		return R.ok().put("deviceBind", deviceBind);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("devicebind:save")
	public R save(@RequestBody DeviceBindEntity deviceBind){
		deviceBindService.save(deviceBind);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("devicebind:update")
	public R update(@RequestBody DeviceBindEntity deviceBind){
		deviceBindService.update(deviceBind);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("devicebind:delete")
	public R delete(@RequestBody String[] ids){
		deviceBindService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
