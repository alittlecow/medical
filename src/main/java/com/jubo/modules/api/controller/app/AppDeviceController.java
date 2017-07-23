package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;
import com.jubo.modules.sys.entity.DeviceEntity;
import com.jubo.modules.sys.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
@RestController
@RequestMapping("/app/device")
public class AppDeviceController {
	@Autowired
	private DeviceService deviceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
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
	public R info(@PathVariable("id") String id){
		DeviceEntity device = deviceService.queryObject(id);
		
		return R.ok().put("device", device);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody DeviceEntity device){
		deviceService.save(device);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody DeviceEntity device){
		deviceService.update(device);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		deviceService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 使用设备
	 */
	@RequestMapping("/useDevice")
	public R useDevice(@RequestBody DeviceEntity device){
		deviceService.useDevice(device);

		return R.ok();
	}
}
