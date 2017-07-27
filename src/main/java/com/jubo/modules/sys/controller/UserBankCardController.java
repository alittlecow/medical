package com.jubo.modules.sys.controller;

import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;
import com.jubo.modules.api.annotation.LoginUser;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.entity.UserBankCardEntity;
import com.jubo.modules.sys.service.UserBankCardService;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("userbankcard")
public class UserBankCardController {
	@Autowired
	private UserBankCardService userBankCardService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("userbankcard:list")
	public R list(@RequestParam Map<String, Object> params,@LoginUser SysUserEntity sysUser){
		//查询列表数据
        Query query = new Query(params);
		params.put("userId",sysUser.getUserId());

		List<UserBankCardEntity> userBankCardList = userBankCardService.queryList(query);
		int total = userBankCardService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userBankCardList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("userbankcard:info")
	public R info(@PathVariable("id") String id){
		UserBankCardEntity userBankCard = userBankCardService.queryObject(id);
		
		return R.ok().put("userBankCard", userBankCard);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("userbankcard:save")
	public R save(@RequestBody UserBankCardEntity userBankCard, @LoginUser SysUserEntity userEntity){

		userBankCardService.save(userBankCard,userEntity);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("userbankcard:update")
	public R update(@RequestBody UserBankCardEntity userBankCard){
		userBankCardService.update(userBankCard);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("userbankcard:delete")
	public R delete(@RequestBody String ids){
		if(StringUtils.isNotBlank(ids)){
			userBankCardService.deleteBatch(ids.split(","));
		}
		return R.ok();
	}
	
}
