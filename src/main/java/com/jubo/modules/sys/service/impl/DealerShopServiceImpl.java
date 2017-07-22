package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.DealerShopDao;
import com.jubo.modules.sys.entity.DealerShopEntity;
import com.jubo.modules.sys.service.DealerShopService;



@Service("dealerShopService")
public class DealerShopServiceImpl implements DealerShopService {
	@Autowired
	private DealerShopDao dealerShopDao;
	
	@Override
	public DealerShopEntity queryObject(String id){
		return dealerShopDao.queryObject(id);
	}
	
	@Override
	public List<DealerShopEntity> queryList(Map<String, Object> map){
		return dealerShopDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dealerShopDao.queryTotal(map);
	}
	
	@Override
	public void save(DealerShopEntity dealerShop){
		dealerShopDao.save(dealerShop);
	}
	
	@Override
	public void update(DealerShopEntity dealerShop){
		dealerShopDao.update(dealerShop);
	}
	
	@Override
	public void delete(String id){
		dealerShopDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		dealerShopDao.deleteBatch(ids);
	}
	
}
