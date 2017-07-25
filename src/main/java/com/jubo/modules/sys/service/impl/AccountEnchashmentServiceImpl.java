package com.jubo.modules.sys.service.impl;

import cn.beecloud.BCPay;
import cn.beecloud.BCUtil;
import cn.beecloud.bean.BCException;
import cn.beecloud.bean.BCTransferParameter;
import com.jubo.common.exception.RRException;
import com.jubo.modules.sys.dao.AccountEnchashmentDao;
import com.jubo.modules.sys.entity.AccountEnchashmentEntity;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.service.AccountEnchashmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("accountEnchashmentService")
public class AccountEnchashmentServiceImpl implements AccountEnchashmentService {
	@Autowired
	private AccountEnchashmentDao accountEnchashmentDao;
	
	@Override
	public AccountEnchashmentEntity queryObject(String id){
		return accountEnchashmentDao.queryObject(id);
	}
	
	@Override
	public List<AccountEnchashmentEntity> queryList(Map<String, Object> map){

		return accountEnchashmentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return accountEnchashmentDao.queryTotal(map);
	}
	
	@Override
	public void save(AccountEnchashmentEntity accountEnchashment){
		String billNo = BCUtil.generateRandomUUIDPure();
		BCTransferParameter bCTransferParameter = new BCTransferParameter();
		bCTransferParameter.setBillNo(billNo);
		bCTransferParameter.setTotalFee(accountEnchashment.getTotalFee());
		bCTransferParameter.setTitle("企业打款");
		bCTransferParameter.setTradeSource("OUT_PC");
		bCTransferParameter.setBankFullName(accountEnchashment.getBankFullName());
		bCTransferParameter.setCardType(accountEnchashment.getCardType());
		bCTransferParameter.setAccountType(accountEnchashment.getAccountType());
		bCTransferParameter.setAccountNo(accountEnchashment.getAccountNo());
		bCTransferParameter.setAccountName(accountEnchashment.getAccountName());
		try {
			//调用Beecloud打款SDK
			BCPay.startBCTransfer(bCTransferParameter);
			//保存打款订单
			accountEnchashment.setStatus(0);
			accountEnchashment.setId(billNo);
			accountEnchashmentDao.save(accountEnchashment);
		} catch (BCException e) {
			throw new RRException("BC_ENCHASHMENT_FAILED",e);
		}

	}
	
	@Override
	public void update(AccountEnchashmentEntity accountEnchashment){
		accountEnchashmentDao.update(accountEnchashment);
	}
	
	@Override
	public void delete(String id){
		accountEnchashmentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		accountEnchashmentDao.deleteBatch(ids);
	}
	
}
