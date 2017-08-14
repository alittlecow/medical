package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.jubo.common.validator.ValidatorUtils;
import com.jubo.modules.sys.entity.DeviceEntity;
import com.jubo.modules.sys.service.DeviceService;
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
@RequestMapping("device")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("device:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DeviceEntity> deviceList = deviceService.queryList(query);
		int total = deviceService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(deviceList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("device:info")
	public R info(@PathVariable("id") String id){
		DeviceEntity device = deviceService.queryObject(id);
		
		return R.ok().put("device", device);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("device:save")
	public R save(@RequestBody DeviceEntity device){

		ValidatorUtils.validateEntity(device);

		deviceService.save(device);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("device:update")
	public R update(@RequestBody DeviceEntity device){

		ValidatorUtils.validateEntity(device);

		deviceService.update(device);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("device:delete")
	public R delete(@RequestBody String[] ids){
		deviceService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
