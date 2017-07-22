package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.GoodsDao;
import com.jubo.modules.sys.entity.GoodsEntity;
import com.jubo.modules.sys.service.GoodsService;



@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public GoodsEntity queryObject(String id){
		return goodsDao.queryObject(id);
	}
	
	@Override
	public List<GoodsEntity> queryList(Map<String, Object> map){
		return goodsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return goodsDao.queryTotal(map);
	}
	
	@Override
	public void save(GoodsEntity goods){
		goodsDao.save(goods);
	}
	
	@Override
	public void update(GoodsEntity goods){
		goodsDao.update(goods);
	}
	
	@Override
	public void delete(String id){
		goodsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		goodsDao.deleteBatch(ids);
	}
	
}
