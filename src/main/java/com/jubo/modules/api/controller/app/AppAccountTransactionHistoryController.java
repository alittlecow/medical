//package com.jubo.modules.api.controller.app;
//
//import com.jubo.common.utils.PageUtils;
//import com.jubo.common.utils.Query;
//import com.jubo.common.utils.R;
//import com.jubo.modules.sys.entity.AccountTransactionHistoryEntity;
//import com.jubo.modules.sys.service.AccountTransactionHistoryService;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//
///**
// *
// *
// * @author chenshun
// * @email sunlightcs@gmail.com
// * @date 2017-07-21 22:46:51
// */
//@RestController
//@RequestMapping("accounttransactionhistory")
//public class AppAccountTransactionHistoryController {
//	@Autowired
//	private AccountTransactionHistoryService accountTransactionHistoryService;
//
//	/**
//	 * 列表
//	 */
//	@RequestMapping(value="/list",method= RequestMethod.GET)
//	public R list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//
//		List<AccountTransactionHistoryEntity> accountTransactionHistoryList = accountTransactionHistoryService.queryList(query);
//		int total = accountTransactionHistoryService.queryTotal(query);
//
//		PageUtils pageUtil = new PageUtils(accountTransactionHistoryList, total, query.getLimit(), query.getPage());
//
//		return R.ok().put("page", pageUtil);
//	}
//
//
//	/**
//	 * 信息
//	 */
//	@RequestMapping("/info/{id}")
//	@RequiresPermissions("accounttransactionhistory:info")
//	public R info(@PathVariable("id") String id){
//		AccountTransactionHistoryEntity accountTransactionHistory = accountTransactionHistoryService.queryObject(id);
//
//		return R.ok().put("accountTransactionHistory", accountTransactionHistory);
//	}
//
//	/**
//	 * 保存
//	 */
//	@RequestMapping("/save")
//	@RequiresPermissions("accounttransactionhistory:save")
//	public R save(@RequestBody AccountTransactionHistoryEntity accountTransactionHistory){
//		accountTransactionHistoryService.save(accountTransactionHistory);
//
//		return R.ok();
//	}
//
//	/**
//	 * 修改
//	 */
//	@RequestMapping("/update")
//	@RequiresPermissions("accounttransactionhistory:update")
//	public R update(@RequestBody AccountTransactionHistoryEntity accountTransactionHistory){
//		accountTransactionHistoryService.update(accountTransactionHistory);
//
//		return R.ok();
//	}
//
//	/**
//	 * 删除
//	 */
//	@RequestMapping("/delete")
//	@RequiresPermissions("accounttransactionhistory:delete")
//	public R delete(@RequestBody String[] ids){
//		accountTransactionHistoryService.deleteBatch(ids);
//
//		return R.ok();
//	}
//
//}
