package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;
import com.jubo.modules.sys.entity.DeviceDataHistoryEntity;
import com.jubo.modules.sys.service.DeviceDataHistoryService;
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
 * @date 2017-07-21 22:46:51
 */
@RestController
@RequestMapping("/app/devicedatahistory")
public class AppDeviceDataHistoryController {
	@Autowired
	private DeviceDataHistoryService deviceDataHistoryService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("devicedatahistory:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DeviceDataHistoryEntity> deviceDataHistoryList = deviceDataHistoryService.queryList(query);
		int total = deviceDataHistoryService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(deviceDataHistoryList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("devicedatahistory:info")
	public R info(@PathVariable("id") String id){
		DeviceDataHistoryEntity deviceDataHistory = deviceDataHistoryService.queryObject(id);
		
		return R.ok().put("deviceDataHistory", deviceDataHistory);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("devicedatahistory:save")
	public R save(@RequestBody DeviceDataHistoryEntity deviceDataHistory){
		deviceDataHistoryService.save(deviceDataHistory);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("devicedatahistory:update")
	public R update(@RequestBody DeviceDataHistoryEntity deviceDataHistory){
		deviceDataHistoryService.update(deviceDataHistory);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("devicedatahistory:delete")
	public R delete(@RequestBody String[] ids){
		deviceDataHistoryService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
